/*
 * Copyright 2023 Salesforce, Inc. All rights reserved.
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
package org.mule.runtime.module.embedded.impl;

import static org.mule.runtime.api.util.MuleSystemProperties.FORCE_PARSE_CONFIG_XMLS_ON_DEPLOYMENT_PROPERTY;
import static org.mule.runtime.container.api.MuleFoldersUtil.getAppsFolder;
import static org.mule.runtime.container.api.MuleFoldersUtil.getConfFolder;
import static org.mule.runtime.container.api.MuleFoldersUtil.getDomainFolder;
import static org.mule.runtime.container.api.MuleFoldersUtil.getDomainsFolder;
import static org.mule.runtime.container.api.MuleFoldersUtil.getServerPluginsFolder;
import static org.mule.runtime.container.api.MuleFoldersUtil.getServicesFolder;
import static org.mule.runtime.core.api.config.MuleDeploymentProperties.MULE_ADD_ARTIFACT_AST_TO_REGISTRY_DEPLOYMENT_PROPERTY;
import static org.mule.runtime.core.api.config.MuleDeploymentProperties.MULE_ADD_TOOLING_OBJECTS_TO_REGISTRY;
import static org.mule.runtime.core.api.config.MuleDeploymentProperties.MULE_LAZY_CONNECTIONS_DEPLOYMENT_PROPERTY;
import static org.mule.runtime.core.api.config.MuleDeploymentProperties.MULE_LAZY_INIT_DEPLOYMENT_PROPERTY;
import static org.mule.runtime.core.api.config.MuleDeploymentProperties.MULE_LAZY_INIT_ENABLE_XML_VALIDATIONS_DEPLOYMENT_PROPERTY;
import static org.mule.runtime.core.api.config.MuleProperties.MULE_HOME_DIRECTORY_PROPERTY;
import static org.mule.runtime.core.api.util.FileUtils.unzip;
import static org.mule.runtime.module.embedded.impl.PathUtils.getPath;
import static org.mule.runtime.module.embedded.impl.util.FileUtils.copyFolder;

import static java.lang.String.valueOf;
import static java.lang.System.clearProperty;
import static java.lang.System.getProperty;
import static java.lang.System.setProperty;

import static org.apache.commons.io.FileUtils.toFile;
import static org.apache.commons.io.FilenameUtils.getName;

import org.mule.runtime.api.exception.MuleException;
import org.mule.runtime.api.lifecycle.InitialisationException;
import org.mule.runtime.module.artifact.api.classloader.ArtifactClassLoader;
import org.mule.runtime.module.embedded.commons.api.ArtifactConfiguration;
import org.mule.runtime.module.embedded.commons.api.ContainerInfo;
import org.mule.runtime.module.launcher.DefaultMuleContainer;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Properties;

import net.lingala.zip4j.ZipFile;

/**
 * Controller class for the runtime. It spin ups a new container instance using a temporary folder and dynamically loading the
 * container libraries.
 * <p>
 * Invoked by reflection
 *
 * @since 4.8, 4.7.2, 4.6.6
 */
public class IsolatedEmbeddedController {

  private final ContainerInfo containerInfo;
  private ArtifactClassLoader containerClassLoader;
  private DefaultMuleContainer muleContainer;

  public IsolatedEmbeddedController(byte[] serializedContainerInfo)
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
    deployArtifactTemplateMethod(artifactConfiguration, deploymentProperties -> getMuleContainer().getDeploymentService()
        .deploy(artifactConfiguration.getArtifactLocation().toURI(), deploymentProperties));
  }

  public void undeployApplication(byte[] serializedApplicationName) throws IOException, ClassNotFoundException {
    String applicationName = deserialize(serializedApplicationName);
    getMuleContainer().getDeploymentService().undeploy(applicationName);
  }

  public synchronized void deployDomain(byte[] serializedArtifactConfiguration) throws IOException, ClassNotFoundException {
    ArtifactConfiguration artifactConfiguration = deserialize(serializedArtifactConfiguration);
    deployArtifactTemplateMethod(artifactConfiguration, deploymentProperties -> getMuleContainer().getDeploymentService()
        .deployDomain(artifactConfiguration.getArtifactLocation().toURI(), deploymentProperties));
  }

  public void undeployDomain(byte[] serializedApplicationName) throws IOException, ClassNotFoundException {
    String applicationName = deserialize(serializedApplicationName);
    getMuleContainer().getDeploymentService().undeployDomain(applicationName);
  }

  private void deployArtifactTemplateMethod(ArtifactConfiguration artifactConfiguration, DeploymentTask deploymentTask) {
    final String forceParseConfigXmlsOnDeploymentOriginalValue = getProperty(FORCE_PARSE_CONFIG_XMLS_ON_DEPLOYMENT_PROPERTY);
    // Force parsing of MUnit test configs, which are not included in the serialized AST for an application
    setProperty(FORCE_PARSE_CONFIG_XMLS_ON_DEPLOYMENT_PROPERTY, "true");

    try {
      getMuleContainer().getDeploymentService().getLock().lock();
      Properties deploymentProperties = new Properties();
      deploymentProperties.put(MULE_LAZY_INIT_DEPLOYMENT_PROPERTY,
                               valueOf(artifactConfiguration.getDeploymentConfiguration().lazyInitializationEnabled()));
      deploymentProperties.put(MULE_LAZY_INIT_ENABLE_XML_VALIDATIONS_DEPLOYMENT_PROPERTY,
                               valueOf(artifactConfiguration.getDeploymentConfiguration().xmlValidationsEnabled()));
      deploymentProperties.put(MULE_LAZY_CONNECTIONS_DEPLOYMENT_PROPERTY,
                               valueOf(artifactConfiguration.getDeploymentConfiguration().lazyConnectionsEnabled()));
      deploymentProperties.put(MULE_ADD_TOOLING_OBJECTS_TO_REGISTRY,
                               valueOf(!artifactConfiguration.getDeploymentConfiguration().doNotAddToolingObjectsToRegistry()));
      deploymentProperties.put(MULE_ADD_ARTIFACT_AST_TO_REGISTRY_DEPLOYMENT_PROPERTY,
                               valueOf(artifactConfiguration.getDeploymentConfiguration().addArtifactAstToRegistry()));

      deploymentTask.deploy(deploymentProperties);
    } catch (IOException e) {
      throw new RuntimeException(e);
    } finally {
      if (getMuleContainer().getDeploymentService().getLock().isHeldByCurrentThread()) {
        getMuleContainer().getDeploymentService().getLock().unlock();
      }

      if (forceParseConfigXmlsOnDeploymentOriginalValue == null) {
        clearProperty(FORCE_PARSE_CONFIG_XMLS_ON_DEPLOYMENT_PROPERTY);
      } else {
        setProperty(FORCE_PARSE_CONFIG_XMLS_ON_DEPLOYMENT_PROPERTY, forceParseConfigXmlsOnDeploymentOriginalValue);
      }
    }
  }

  public void executeWithinContainerClassLoader(ContainerTask task) {
    ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
    try {
      // This is the unfiltered classloader, to be consistent with how the standalone is bootstrapped.
      // This is required to discover the server-plgins when running in a modularized environment.
      Thread.currentThread().setContextClassLoader(containerClassLoader.getClass().getClassLoader());
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

  private void setUpEnvironment() throws IOException, URISyntaxException, InitialisationException {
    // Disable log4j2 JMX MBeans since it will fail when trying to recreate the container
    setProperty("log4j2.disable.jmx", "true");

    setProperty(MULE_HOME_DIRECTORY_PROPERTY, getPath(containerInfo.getContainerBaseFolder()));
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
      if (originalFile.isDirectory()) {
        File destinationFile = new File(getServicesFolder(), originalFile.getName());
        copyFolder(originalFile.toPath(), destinationFile.toPath());
      } else {
        File destinationFile =
            new File(getServicesFolder(), getName(originalFile.getPath()).replaceAll("-mule-service\\.jar", ""));
        destinationFile.mkdirs();
        unzip(originalFile, destinationFile, false);
      }
    }
    containerInfo.getServerPlugins().stream().forEach(serverPluginUrl -> {
      File originalFile = toFile(serverPluginUrl);
      if (originalFile.isDirectory()) {
        File destinationFile = new File(getServerPluginsFolder(), originalFile.getName());
        try {
          copyFolder(originalFile.toPath(), destinationFile.toPath());
        } catch (IOException e) {
          throw new RuntimeException(e);
        }
      } else {
        File destinationFile = new File(getServerPluginsFolder(), getName(originalFile.getPath()).replace(".zip", ""));
        try (ZipFile zipFile = new ZipFile(originalFile)) {
          zipFile.extractAll(destinationFile.getAbsolutePath());
        } catch (Exception e) {
          throw new RuntimeException(e);
        }
      }
    });

    muleContainer = new DefaultMuleContainer();
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

  protected DefaultMuleContainer getMuleContainer() {
    return muleContainer;
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

  private <T> T deserialize(byte[] object) throws IOException, ClassNotFoundException {
    try (ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(object);
        ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream)) {
      return (T) objectInputStream.readObject();
    }
  }


}
