/*
 * Copyright (c) MuleSoft, Inc.  All rights reserved.  http://www.mulesoft.com
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
package org.mule.runtime.module.embedded;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.fail;
import static org.mule.maven.client.api.model.MavenConfiguration.newMavenConfigurationBuilder;
import static org.mule.maven.client.test.MavenTestHelper.createDefaultCommunityMavenConfigurationBuilder;
import static org.mule.maven.client.test.MavenTestHelper.getLocalRepositoryFolder;
import static org.mule.runtime.module.embedded.api.EmbeddedContainer.builder;
import static org.mule.runtime.module.embedded.api.Product.MULE;
import static org.mule.test.allure.AllureConstants.DeploymentTypeFeature.DeploymentTypeStory.EMBEDDED;
import static org.mule.test.allure.AllureConstants.EmbeddedApiFeature.EMBEDDED_API;
import static org.mule.test.allure.AllureConstants.EmbeddedApiFeature.EmbeddedApiStory.CONFIGURATION;

import org.mule.runtime.module.embedded.api.ArtifactConfiguration;
import org.mule.runtime.module.embedded.api.ContainerConfiguration;
import org.mule.runtime.module.embedded.api.DeploymentConfiguration;
import org.mule.runtime.module.embedded.api.EmbeddedContainer;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Properties;

import io.qameta.allure.Feature;
import io.qameta.allure.Features;
import io.qameta.allure.Stories;
import io.qameta.allure.Story;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

@Features(@Feature(EMBEDDED_API))
@Stories({@Story(CONFIGURATION), @Story(EMBEDDED)})
public class EmbeddedLifecycleTestCase {

  private static EmbeddedTestHelper embeddedTestHelper;

  @BeforeClass
  public static void initialise() {
    embeddedTestHelper = new EmbeddedTestHelper(false, false, false);
  }

  @Rule
  public TemporaryFolder temporaryFolder = new TemporaryFolder();

  @Test
  public void shouldFailToStartDueToMissingVersionOfEmbedded() throws IOException, URISyntaxException {
    EmbeddedContainer embeddedContainer = builder()
        .muleVersion("1.0.0")
        .containerConfiguration(ContainerConfiguration.builder()
            .containerFolder(temporaryFolder.newFolder())
            .build())
        .mavenConfiguration(newMavenConfigurationBuilder().localMavenRepositoryLocation(temporaryFolder.newFolder())
            .build())
        .log4jConfigurationFile(getClass().getClassLoader().getResource("log4j2-default.xml").toURI())
        .product(MULE)
        .build();
    try {
      embeddedContainer.start();
      fail("Should fail to start");
    } catch (IllegalStateException e) {
      assertThat(e.getCause().getMessage(), containsString("Could not find embedded container bom artifact"));
      // Should be stoppable at this point
      embeddedContainer.stop();
    }
  }

  @Test
  public void mavenUserProperties() throws IOException, URISyntaxException {
    File containerFolder = temporaryFolder.newFolder();

    Properties userProperties = new Properties();
    userProperties.put("mule.http.version.user.property", "1.5.15");

    EmbeddedContainer embeddedContainer = builder()
        .muleVersion(System.getProperty("mule.version"))
        .containerConfiguration(ContainerConfiguration.builder()
            .containerFolder(containerFolder)
            .build())
        .mavenConfiguration(createDefaultCommunityMavenConfigurationBuilder()
            .localMavenRepositoryLocation(getLocalRepositoryFolder())
            .userProperties(userProperties)
            .build())
        .log4jConfigurationFile(getClass().getClassLoader().getResource("log4j2-default.xml").toURI())
        .product(MULE)
        .build();

    embeddedContainer.start();

    try {
      embeddedContainer.getDeploymentService().deployApplication(ArtifactConfiguration.builder()
          .artifactLocation(embeddedTestHelper.getFolderForApplication("http-echo-user-property"))
          .deploymentConfiguration(DeploymentConfiguration.builder()
              .lazyInitialization(true)
              .xmlValidations(false)
              .build())
          .build());
    } catch (Exception e) {
      // Once the user property is propagated by Embedded the resolution should work
      new AssertionError(e);
    } finally {
      embeddedContainer.stop();
    }
  }

}
