/*
 * Copyright 2023 Salesforce, Inc. All rights reserved.
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
package org.mule.runtime.module.embedded;

import static org.mule.maven.client.api.model.MavenConfiguration.newMavenConfigurationBuilder;
import static org.mule.maven.client.test.MavenTestHelper.createDefaultCommunityMavenConfigurationBuilder;
import static org.mule.maven.client.test.MavenTestHelper.getLocalRepositoryFolder;
import static org.mule.runtime.core.api.config.MuleManifest.getProductVersion;
import static org.mule.runtime.module.embedded.api.EmbeddedContainer.builder;
import static org.mule.runtime.module.embedded.api.Product.MULE;
import static org.mule.test.allure.AllureConstants.DeploymentTypeFeature.DeploymentTypeStory.EMBEDDED;
import static org.mule.test.allure.AllureConstants.EmbeddedApiFeature.EMBEDDED_API;
import static org.mule.test.allure.AllureConstants.EmbeddedApiFeature.EmbeddedApiStory.CONFIGURATION;

import static java.lang.System.getProperty;

import static com.google.common.primitives.Booleans.asList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.fail;

import org.mule.runtime.module.embedded.api.ArtifactConfiguration;
import org.mule.runtime.module.embedded.api.ContainerConfiguration;
import org.mule.runtime.module.embedded.api.DeploymentConfiguration;
import org.mule.runtime.module.embedded.api.EmbeddedContainer;
import org.mule.runtime.module.embedded.test.hepler.EmbeddedTestHelper;
import org.mule.tck.junit4.rule.SystemProperty;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Collection;
import java.util.Properties;

import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import io.qameta.allure.Feature;
import io.qameta.allure.Features;
import io.qameta.allure.Issue;
import io.qameta.allure.Stories;
import io.qameta.allure.Story;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

@Features(@Feature(EMBEDDED_API))
@Stories({@Story(CONFIGURATION), @Story(EMBEDDED)})
@RunWith(Parameterized.class)
public class EmbeddedLifecycleTestCase {

  private static EmbeddedTestHelper embeddedTestHelper;

  @BeforeClass
  public static void initialise() {
    embeddedTestHelper = new EmbeddedTestHelper(false, false, false);
  }

  @Rule
  public TemporaryFolder temporaryFolder = new TemporaryFolder();

  @Rule
  public SystemProperty skipModuleTweakingValidation = new SystemProperty("mule.module.tweaking.validation.skip", "true");

  // mule-embedded-api no longer supports log4j configuration since 1.7, this property has to be set so the log4j configuration
  // doesn't take place
  @Rule
  public SystemProperty simpleLogging =
      new SystemProperty("mule.simpleLog", "true");

  @Parameter
  public boolean useIsolation;

  @Parameters(name = "useIsolation: {0}")
  public static Collection<Boolean> data() {
    return asList(false);
  }

  @Test
  public void shouldFailToCreateDueToMissingVersionOfEmbedded() throws IOException {
    try {
      builder()
          .muleVersion("1.0.0")
          .containerConfiguration(ContainerConfiguration.builder()
              .containerFolder(temporaryFolder.newFolder())
              .build())
          .mavenConfiguration(newMavenConfigurationBuilder().localMavenRepositoryLocation(temporaryFolder.newFolder())
              .build())
          .product(MULE)
          .useIsolation(useIsolation)
          .build();
      fail("Should fail to create");
    } catch (IllegalStateException e) {
      assertThat(e.getCause().getMessage(), containsString("Could not find embedded container bom artifact"));
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
        .product(MULE)
        .useIsolation(useIsolation)
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

  @Test
  @Issue("W-11996026")
  public void getMuleContainerVersionBeforeStart() throws Exception {
    // System.setProperty("mule.version", "4.5.0");
    System.out.println("Mule version = " + getProperty("mule.version"));
    System.out.println("Mule manifest version = " + getProductVersion());
    EmbeddedContainer embeddedContainer = getBuilderWithDefaults().build();

    assertThat(embeddedContainer.getMuleContainerVersion(), is(getProductVersion()));
  }

  @Test
  public void checkJavaVersions() throws Exception {
    EmbeddedContainer embeddedContainer = getBuilderWithDefaults().build();

    assertThat("Java version `" + getProperty("java.version") + "` not recommended",
               embeddedContainer.isCurrentJvmVersionRecommended(), is(true));
    assertThat("Java version `" + getProperty("java.version") + "` not supported",
               embeddedContainer.isCurrentJvmVersionSupported(), is(true));
  }

  @Test
  @Issue("W-11193698")
  public void muleHomeIsCorrectlySetWhenStartingTheController() throws Exception {
    EmbeddedContainer embeddedContainer = getBuilderWithDefaults().build();
    String containerFolder = embeddedContainer.getContainerFolder().getAbsolutePath();

    // Control test
    assertThat(getProperty("mule.home"), is(not(containerFolder)));

    embeddedContainer.start();
    embeddedContainer.stop();

    assertThat(getProperty("mule.home"), is(containerFolder));
  }

  private EmbeddedContainer.EmbeddedContainerBuilder getBuilderWithDefaults() throws IOException {
    return builder()
        .muleVersion(System.getProperty("mule.version"))
        .containerConfiguration(ContainerConfiguration.builder()
            .containerFolder(temporaryFolder.newFolder())
            .build())
        .mavenConfiguration(createDefaultCommunityMavenConfigurationBuilder()
            .localMavenRepositoryLocation(getLocalRepositoryFolder())
            .build())
        .product(MULE)
        .useIsolation(useIsolation);
  }

}
