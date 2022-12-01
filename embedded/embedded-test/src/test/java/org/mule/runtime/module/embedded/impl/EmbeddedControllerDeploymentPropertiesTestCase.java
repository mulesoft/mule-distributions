/*
 * Copyright (c) MuleSoft, Inc.  All rights reserved.  http://www.mulesoft.com
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
package org.mule.runtime.module.embedded.impl;

import static org.mule.runtime.container.api.MuleFoldersUtil.getMuleHomeFolder;
import static org.mule.runtime.core.api.config.MuleDeploymentProperties.MULE_ADD_ARTIFACT_AST_TO_REGISTRY_DEPLOYMENT_PROPERTY;
import static org.mule.runtime.core.api.config.MuleDeploymentProperties.MULE_ADD_TOOLING_OBJECTS_TO_REGISTRY;
import static org.mule.runtime.core.api.config.MuleDeploymentProperties.MULE_LAZY_CONNECTIONS_DEPLOYMENT_PROPERTY;
import static org.mule.runtime.core.api.config.MuleDeploymentProperties.MULE_LAZY_INIT_DEPLOYMENT_PROPERTY;
import static org.mule.runtime.core.api.config.MuleDeploymentProperties.MULE_LAZY_INIT_ENABLE_XML_VALIDATIONS_DEPLOYMENT_PROPERTY;
import static org.mule.test.allure.AllureConstants.DeploymentTypeFeature.DeploymentTypeStory.EMBEDDED;
import static org.mule.test.allure.AllureConstants.EmbeddedApiFeature.EMBEDDED_API;

import static java.util.Collections.emptyList;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentCaptor.forClass;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.mule.runtime.module.deployment.api.DeploymentService;
import org.mule.runtime.module.embedded.api.ArtifactConfiguration;
import org.mule.runtime.module.embedded.api.ContainerInfo;
import org.mule.runtime.module.embedded.api.DeploymentConfiguration;
import org.mule.runtime.module.embedded.api.DeploymentConfiguration.DeploymentConfigurationBuilder;
import org.mule.runtime.module.launcher.MuleContainer;
import org.mule.tck.junit4.AbstractMuleTestCase;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.net.URI;
import java.util.Properties;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Consumer;

import org.junit.Before;
import org.junit.Test;

import org.mockito.ArgumentCaptor;

import io.qameta.allure.Feature;
import io.qameta.allure.Story;

@Feature(EMBEDDED_API)
@Story(EMBEDDED)
public class EmbeddedControllerDeploymentPropertiesTestCase extends AbstractMuleTestCase {

  private DeploymentService deploymentService;
  private EmbeddedController embeddedController;

  @Before
  public void setUp() throws IOException, ClassNotFoundException {
    deploymentService = mock(DeploymentService.class);
    when(deploymentService.getLock()).thenReturn(new ReentrantLock());

    ContainerInfo containerInfo = new ContainerInfo("4.1.1", getMuleHomeFolder().toURI().toURL(), emptyList(), emptyList());
    embeddedController = new EmbeddedController(serialize(containerInfo)) {

      @Override
      public void start() throws Exception {
        // nothing to do
      }

      @Override
      public void stop() {
        // nothing to do
      }

      @Override
      protected MuleContainer getMuleContainer() {
        MuleContainer container = mock(MuleContainer.class);
        when(container.getDeploymentService()).thenReturn(deploymentService);
        return container;
      }
    };
  }

  @Test
  public void lazyInitializationFalseDeploymentConfigurationForwardedToDeploymentProperties()
      throws IOException, ClassNotFoundException {
    ArgumentCaptor<Properties> deploymentPropertiesCaptor = forClass(Properties.class);
    deployWithProperties(b -> b.lazyInitialization(false), deploymentPropertiesCaptor);
    assertThat(deploymentPropertiesCaptor.getValue().get(MULE_LAZY_INIT_DEPLOYMENT_PROPERTY), is("false"));
  }

  @Test
  public void lazyInitializationTrueDeploymentConfigurationForwardedToDeploymentProperties()
      throws IOException, ClassNotFoundException {
    ArgumentCaptor<Properties> deploymentPropertiesCaptor = forClass(Properties.class);
    deployWithProperties(b -> b.lazyInitialization(true), deploymentPropertiesCaptor);
    assertThat(deploymentPropertiesCaptor.getValue().get(MULE_LAZY_INIT_DEPLOYMENT_PROPERTY), is("true"));
  }

  @Test
  public void lazyConnectionsEnabledFalseDeploymentConfigurationForwardedToDeploymentProperties()
      throws IOException, ClassNotFoundException {
    ArgumentCaptor<Properties> deploymentPropertiesCaptor = forClass(Properties.class);
    deployWithProperties(b -> b.lazyConnectionsEnabled(false), deploymentPropertiesCaptor);
    assertThat(deploymentPropertiesCaptor.getValue().get(MULE_LAZY_CONNECTIONS_DEPLOYMENT_PROPERTY), is("false"));
  }

  @Test
  public void lazyConnectionsEnabledTrueDeploymentConfigurationForwardedToDeploymentProperties()
      throws IOException, ClassNotFoundException {
    ArgumentCaptor<Properties> deploymentPropertiesCaptor = forClass(Properties.class);
    deployWithProperties(b -> b.lazyConnectionsEnabled(true), deploymentPropertiesCaptor);
    assertThat(deploymentPropertiesCaptor.getValue().get(MULE_LAZY_CONNECTIONS_DEPLOYMENT_PROPERTY), is("true"));
  }

  @Test
  public void xmlValidationsFalseDeploymentConfigurationForwardedToDeploymentProperties()
      throws IOException, ClassNotFoundException {
    ArgumentCaptor<Properties> deploymentPropertiesCaptor = forClass(Properties.class);
    deployWithProperties(b -> b.xmlValidations(false), deploymentPropertiesCaptor);
    assertThat(deploymentPropertiesCaptor.getValue().get(MULE_LAZY_INIT_ENABLE_XML_VALIDATIONS_DEPLOYMENT_PROPERTY), is("false"));
  }

  @Test
  public void xmlValidationsTrueDeploymentConfigurationForwardedToDeploymentProperties()
      throws IOException, ClassNotFoundException {
    ArgumentCaptor<Properties> deploymentPropertiesCaptor = forClass(Properties.class);
    deployWithProperties(b -> b.xmlValidations(true), deploymentPropertiesCaptor);
    assertThat(deploymentPropertiesCaptor.getValue().get(MULE_LAZY_INIT_ENABLE_XML_VALIDATIONS_DEPLOYMENT_PROPERTY), is("true"));
  }

  @Test
  public void toolingObjectsNotToRegistryFalseDeploymentConfigurationForwardedToDeploymentProperties()
      throws IOException, ClassNotFoundException {
    ArgumentCaptor<Properties> deploymentPropertiesCaptor = forClass(Properties.class);
    deployWithProperties(b -> b.doNotAddToolingObjectsToRegistry(false), deploymentPropertiesCaptor);
    assertThat(deploymentPropertiesCaptor.getValue().get(MULE_ADD_TOOLING_OBJECTS_TO_REGISTRY), is("true"));
  }

  @Test
  public void toolingObjectsNotToRegistryTrueDeploymentConfigurationForwardedToDeploymentProperties()
      throws IOException, ClassNotFoundException {
    ArgumentCaptor<Properties> deploymentPropertiesCaptor = forClass(Properties.class);
    deployWithProperties(b -> b.doNotAddToolingObjectsToRegistry(true), deploymentPropertiesCaptor);
    assertThat(deploymentPropertiesCaptor.getValue().get(MULE_ADD_TOOLING_OBJECTS_TO_REGISTRY), is("false"));
  }

  @Test
  public void artifactAstFalseDeploymentConfigurationForwardedToDeploymentProperties()
      throws IOException, ClassNotFoundException {
    ArgumentCaptor<Properties> deploymentPropertiesCaptor = forClass(Properties.class);
    deployWithProperties(b -> b.addArtifactAstToRegistry(false), deploymentPropertiesCaptor);
    assertThat(deploymentPropertiesCaptor.getValue().get(MULE_ADD_ARTIFACT_AST_TO_REGISTRY_DEPLOYMENT_PROPERTY), is("false"));
  }

  @Test
  public void artifactAstTrueDeploymentConfigurationForwardedToDeploymentProperties() throws IOException, ClassNotFoundException {
    ArgumentCaptor<Properties> deploymentPropertiesCaptor = forClass(Properties.class);
    deployWithProperties(b -> b.addArtifactAstToRegistry(true), deploymentPropertiesCaptor);
    assertThat(deploymentPropertiesCaptor.getValue().get(MULE_ADD_ARTIFACT_AST_TO_REGISTRY_DEPLOYMENT_PROPERTY), is("true"));
  }

  @Test
  public void defaultDeploymentProperties() throws IOException, ClassNotFoundException {
    ArgumentCaptor<Properties> deploymentPropertiesCaptor = forClass(Properties.class);
    deployWithProperties(b -> {
    }, deploymentPropertiesCaptor);

    assertThat(deploymentPropertiesCaptor.getValue().get(MULE_LAZY_INIT_DEPLOYMENT_PROPERTY), is("false"));
    assertThat(deploymentPropertiesCaptor.getValue().get(MULE_LAZY_CONNECTIONS_DEPLOYMENT_PROPERTY), is("false"));
    assertThat(deploymentPropertiesCaptor.getValue().get(MULE_LAZY_INIT_ENABLE_XML_VALIDATIONS_DEPLOYMENT_PROPERTY), is("false"));
    assertThat(deploymentPropertiesCaptor.getValue().get(MULE_ADD_TOOLING_OBJECTS_TO_REGISTRY), is("true"));
    assertThat(deploymentPropertiesCaptor.getValue().get(MULE_ADD_ARTIFACT_AST_TO_REGISTRY_DEPLOYMENT_PROPERTY), is("false"));
  }

  protected void deployWithProperties(Consumer<DeploymentConfiguration.DeploymentConfigurationBuilder> deploymentConfigBuilderConfigurer,
                                      ArgumentCaptor<Properties> deploymentPropertiesCaptor)
      throws IOException, ClassNotFoundException {
    DeploymentConfigurationBuilder deploymentConfigBuilder = DeploymentConfiguration.builder();
    deploymentConfigBuilderConfigurer.accept(deploymentConfigBuilder);

    embeddedController.deployApplication(serialize(ArtifactConfiguration.builder()
        .artifactLocation(new File(""))
        .deploymentConfiguration(deploymentConfigBuilder
            .build())
        .build()));

    verify(deploymentService).deploy(any(URI.class), deploymentPropertiesCaptor.capture());
  }

  private static <T> byte[] serialize(T object) throws IOException {
    try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {
      try (ObjectOutput objectOutputStream = new ObjectOutputStream(byteArrayOutputStream)) {
        objectOutputStream.writeObject(object);
        return byteArrayOutputStream.toByteArray();
      }
    }
  }
}
