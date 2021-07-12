/*
 * Copyright (c) MuleSoft, Inc.  All rights reserved.  http://www.mulesoft.com
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
package org.mule.runtime.module.embedded.impl;

import static java.lang.String.valueOf;
import static java.lang.System.setProperty;
import static org.apache.commons.io.FileUtils.toFile;
import static org.apache.commons.io.FilenameUtils.getName;
import static org.mule.runtime.container.api.MuleFoldersUtil.getAppsFolder;
import static org.mule.runtime.container.api.MuleFoldersUtil.getConfFolder;
import static org.mule.runtime.container.api.MuleFoldersUtil.getDomainFolder;
import static org.mule.runtime.container.api.MuleFoldersUtil.getDomainsFolder;
import static org.mule.runtime.container.api.MuleFoldersUtil.getServerPluginsFolder;
import static org.mule.runtime.container.api.MuleFoldersUtil.getServicesFolder;
import static org.mule.runtime.core.api.config.MuleDeploymentProperties.MULE_LAZY_CONNECTIONS_DEPLOYMENT_PROPERTY;
import static org.mule.runtime.core.api.config.MuleDeploymentProperties.MULE_LAZY_INIT_DEPLOYMENT_PROPERTY;
import static org.mule.runtime.core.api.config.MuleDeploymentProperties.MULE_LAZY_INIT_ENABLE_XML_VALIDATIONS_DEPLOYMENT_PROPERTY;
import static org.mule.runtime.core.api.config.MuleProperties.MULE_HOME_DIRECTORY_PROPERTY;
import static org.mule.runtime.core.api.util.FileUtils.unzip;
import static org.mule.runtime.module.embedded.impl.SerializationUtils.deserialize;

import org.mule.runtime.api.artifact.Registry;
import org.mule.runtime.api.connectivity.ConnectivityTestingService;
import org.mule.runtime.api.exception.MuleException;
import org.mule.runtime.api.metadata.MetadataService;
import org.mule.runtime.api.value.ValueProviderService;
import org.mule.runtime.core.api.context.notification.MuleContextListener;
import org.mule.runtime.deployment.model.api.DeploymentStartException;
import org.mule.runtime.deployment.model.api.InstallException;
import org.mule.runtime.deployment.model.api.application.Application;
import org.mule.runtime.deployment.model.api.application.ApplicationDescriptor;
import org.mule.runtime.deployment.model.api.application.ApplicationPolicyManager;
import org.mule.runtime.deployment.model.api.application.ApplicationStatus;
import org.mule.runtime.deployment.model.api.artifact.ArtifactContext;
import org.mule.runtime.deployment.model.api.domain.Domain;
import org.mule.runtime.deployment.model.api.plugin.ArtifactPlugin;
import org.mule.runtime.module.artifact.api.classloader.ArtifactClassLoader;
import org.mule.runtime.module.artifact.api.classloader.RegionClassLoader;
import org.mule.runtime.module.embedded.api.ArtifactConfiguration;
import org.mule.runtime.module.embedded.api.ContainerInfo;
import org.mule.runtime.module.launcher.MuleContainer;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;
import java.util.Properties;

import net.lingala.zip4j.ZipFile;

/**
 * Controller class for the runtime. It spin ups a new container instance using a temporary folder and dynamically loading the
 * container libraries.
 * <p>
 * Invoked by reflection
 *
 * @since 4.0
 */
public class EmbeddedController {

  private final ContainerInfo containerInfo;
  private ArtifactClassLoader containerClassLoader;
  private MuleContainer muleContainer;

  public EmbeddedController(byte[] serializedContainerInfo)
      throws IOException, ClassNotFoundException {
    containerInfo = deserialize(serializedContainerInfo);
  }

  /**
   * Invoked by reflection
   */
  public void start() throws Exception {
    setUpEnvironment();
  }

  public synchronized void deployApplication(byte[] serializedArtifactConfiguration) throws IOException, ClassNotFoundException {
    ArtifactConfiguration artifactConfiguration = deserialize(serializedArtifactConfiguration);
    deployArtifactTemplateMethod(artifactConfiguration, deploymentProperties -> muleContainer.getDeploymentService()
        .deploy(artifactConfiguration.getArtifactLocation().toURI(), deploymentProperties));
  }

  public void undeployApplication(byte[] serializedApplicationName) throws IOException, ClassNotFoundException {
    String applicationName = deserialize(serializedApplicationName);
    muleContainer.getDeploymentService().undeploy(applicationName);
  }

  public synchronized void deployDomain(byte[] serializedArtifactConfiguration) throws IOException, ClassNotFoundException {
    ArtifactConfiguration artifactConfiguration = deserialize(serializedArtifactConfiguration);
    deployArtifactTemplateMethod(artifactConfiguration, deploymentProperties -> muleContainer.getDeploymentService()
        .deployDomain(artifactConfiguration.getArtifactLocation().toURI(), deploymentProperties));
  }

  public void undeployDomain(byte[] serializedApplicationName) throws IOException, ClassNotFoundException {
    String applicationName = deserialize(serializedApplicationName);
    muleContainer.getDeploymentService().undeployDomain(applicationName);
  }

  private void deployArtifactTemplateMethod(ArtifactConfiguration artifactConfiguration, DeploymentTask deploymentTask) {
    try {
      muleContainer.getDeploymentService().getLock().lock();
      Properties deploymentProperties = new Properties();
      deploymentProperties.put(MULE_LAZY_INIT_DEPLOYMENT_PROPERTY,
                               valueOf(artifactConfiguration.getDeploymentConfiguration().lazyInitializationEnabled()));
      deploymentProperties.put(MULE_LAZY_INIT_ENABLE_XML_VALIDATIONS_DEPLOYMENT_PROPERTY,
                               valueOf(artifactConfiguration.getDeploymentConfiguration().xmlValidationsEnabled()));
      deploymentProperties.put(MULE_LAZY_CONNECTIONS_DEPLOYMENT_PROPERTY,
                               valueOf(artifactConfiguration.getDeploymentConfiguration().lazyConnectionsEnabled()));

      deploymentTask.deploy(deploymentProperties);
    } catch (IOException e) {
      throw new RuntimeException(e);
    } finally {
      if (muleContainer.getDeploymentService().getLock().isHeldByCurrentThread()) {
        muleContainer.getDeploymentService().getLock().unlock();
      }
    }
  }

  public void executeWithinContainerClassLoader(ContainerTask task) {
    ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
    try {
      Thread.currentThread().setContextClassLoader(containerClassLoader.getClassLoader());
      task.run();
    } catch (Exception e) {
      throw new RuntimeException(e);
    } finally {
      Thread.currentThread().setContextClassLoader(contextClassLoader);
    }
  }

  /**
   * Invoked by reflection
   */
  public void stop() {
    executeWithinContainerClassLoader(() -> {
      muleContainer.stop();
      muleContainer.getContainerClassLoader().dispose();
    });
  }

  private void setUpEnvironment() throws IOException, URISyntaxException {
    // Disable log4j2 JMX MBeans since it will fail when trying to recreate the container
    setProperty("log4j2.disable.jmx", "true");

    setProperty(MULE_HOME_DIRECTORY_PROPERTY, containerInfo.getContainerBaseFolder().toURI().getPath());
    getDomainsFolder().mkdirs();
    getDomainFolder("default").mkdirs();
    getServicesFolder().mkdirs();
    getServerPluginsFolder().mkdirs();
    getConfFolder().mkdirs();
    getAppsFolder().mkdirs();

    // this is used to signal that we are running in embedded mode.
    // Class loader model loader will not use try to use the container repository.
    setProperty("mule.mode.embedded", "true");

    for (URL url : containerInfo.getServices()) {
      File originalFile = toFile(url);
      File destinationFile = new File(getServicesFolder(), getName(originalFile.getPath()).replaceAll("-mule-service\\.jar", ""));
      destinationFile.mkdirs();
      unzip(originalFile, destinationFile, false);
    }
    containerInfo.getServerPlugins().stream().forEach(serverPluginUrl -> {
      File originalFile = toFile(serverPluginUrl);
      File destinationFile = new File(getServerPluginsFolder(), getName(originalFile.getPath()).replace(".zip", ""));
      try {
        ZipFile zipFile = new ZipFile(originalFile);
        zipFile.extractAll(destinationFile.getAbsolutePath());
      } catch (Exception e) {
        throw new RuntimeException(e);
      }
    });

    muleContainer = new MuleContainer(new String[0]);
    muleContainer.setEmbeddedMode(true);
    containerClassLoader = muleContainer.getContainerClassLoader();
    executeWithinContainerClassLoader(() -> {
      try {
        // Do not register shutdown hook since it will try to kill the JVM
        muleContainer.start(false);
      } catch (MuleException e) {
        throw new IllegalStateException(e);
      }
    });
  }

  @FunctionalInterface
  interface DeploymentTask {

    void deploy(Properties deploymentProperties) throws IOException;

  }

  /**
   * Interface for running tasks within the container class loader.
   */
  @FunctionalInterface
  interface ContainerTask {

    void run() throws Exception;

  }

  // TODO MULE-10392: To be removed once we have methods to deploy with properties, unify this code inside deploymentService!
  /**
   * Just a temporary implementation of an Application in order to handle both deployments until code is unified.
   */
  private class NoOpApplication implements Application {

    @Override
    public RegionClassLoader getRegionClassLoader() {
      return null;
    }

    @Override
    public String getArtifactName() {
      return null;
    }

    @Override
    public String getArtifactId() {
      return null;
    }

    @Override
    public File[] getResourceFiles() {
      return new File[0];
    }

    @Override
    public ArtifactClassLoader getArtifactClassLoader() {
      return null;
    }

    @Override
    public void install() throws InstallException {

    }

    @Override
    public void init() {

    }

    @Override
    public void lazyInit() {

    }

    @Override
    public void lazyInit(boolean disableXmlValidations) {

    }

    @Override
    public void start() throws DeploymentStartException {

    }

    @Override
    public void stop() {

    }

    @Override
    public ApplicationDescriptor getDescriptor() {
      return null;
    }

    @Override
    public void dispose() {

    }

    @Override
    public Registry getRegistry() {
      return null;
    }

    @Override
    public ArtifactContext getArtifactContext() {
      return null;
    }

    @Override
    public File getLocation() {
      return null;
    }

    @Override
    public ConnectivityTestingService getConnectivityTestingService() {
      return null;
    }

    @Override
    public MetadataService getMetadataService() {
      return null;
    }

    @Override
    public ValueProviderService getValueProviderService() {
      return null;
    }

    @Override
    public List<ArtifactPlugin> getArtifactPlugins() {
      return null;
    }

    @Override
    public void setMuleContextListener(MuleContextListener muleContextListener) {

    }

    @Override
    public Domain getDomain() {
      return null;
    }

    @Override
    public ApplicationStatus getStatus() {
      return null;
    }

    @Override
    public ApplicationPolicyManager getPolicyManager() {
      return null;
    }
  }

}
