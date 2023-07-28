/*
 * Copyright 2023 Salesforce, Inc. All rights reserved.
 */
package org.mule.runtime.module.embedded;

import static org.mule.runtime.module.embedded.api.Product.MULE;
import static org.mule.tck.MuleTestUtils.testWithSystemProperty;
import static org.mule.test.infrastructure.maven.MavenTestUtils.installMavenArtifact;

import static java.lang.String.valueOf;
import static java.util.Optional.empty;

import static org.apache.commons.io.FileUtils.deleteQuietly;
import static org.apache.commons.io.FileUtils.toFile;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import org.mule.runtime.module.artifact.api.descriptor.BundleDescriptor;
import org.mule.runtime.module.embedded.api.ArtifactConfiguration;
import org.mule.runtime.module.embedded.api.DeploymentConfiguration;
import org.mule.runtime.module.embedded.api.EmbeddedContainer;
import org.mule.tck.junit4.AbstractMuleTestCase;
import org.mule.tck.junit4.rule.FreePortFinder;
import org.mule.tck.junit4.rule.SystemProperty;
import org.mule.tck.probe.JUnitProbe;
import org.mule.tck.probe.PollingProber;

import java.io.File;
import java.net.URI;
import java.util.Optional;
import java.util.Properties;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Rule;


public abstract class AbstractEmbeddedTestCase extends AbstractMuleTestCase {

  protected static EmbeddedTestHelper embeddedTestHelper;

  private static final String ARTIFACTS_FOLDER = "artifacts";
  private static final String APPS_FOLDER = "apps";
  private static final String DOMAINS_FOLDER = "domains";

  @BeforeClass
  public static void initialise() {
    embeddedTestHelper = new EmbeddedTestHelper(false, false, false);
  }

  @AfterClass
  public static void dispose() {
    embeddedTestHelper.dispose();
  }

  @Rule
  public SystemProperty skipModuleTweakingValidation = new SystemProperty("mule.module.tweaking.validation.skip", "true");

  protected void doWithinApplication(BundleDescriptor applicationBundleDescriptor, String artifactFolder,
                                     Consumer<Integer> portConsumer)
      throws Exception {
    doWithinArtifact(applicationBundleDescriptor, artifactFolder, portConsumer, false, true, true, empty(), true,
                     APPS_FOLDER, true, (container, artifactConfiguration) -> container.getDeploymentService()
                         .deployApplication(artifactConfiguration),
                     false);
  }

  protected void doWithinApplication(BundleDescriptor applicationBundleDescriptor, String artifactFolder,
                                     Consumer<Integer> portConsumer, String muleVersion)
      throws Exception {
    doWithinArtifact(applicationBundleDescriptor, artifactFolder, portConsumer, false, true, true, empty(), true,
                     APPS_FOLDER, true, (container, artifactConfiguration) -> container.getDeploymentService()
                         .deployApplication(artifactConfiguration),
                     false, muleVersion, new Properties());
  }

  protected void doWithinApplicationRestartingEmbedded(BundleDescriptor applicationBundleDescriptor, String artifactFolder,
                                                       Consumer<Integer> portConsumer)
      throws Exception {
    doWithinArtifact(applicationBundleDescriptor, artifactFolder, portConsumer, false, true, true, empty(), true,
                     APPS_FOLDER, true, (container, artifactConfiguration) -> container.getDeploymentService()
                         .deployApplication(artifactConfiguration),
                     true);
  }

  protected void doWithinApplicationNotInstalled(BundleDescriptor applicationBundleDescriptor, String artifactFolder,
                                                 Consumer<Integer> portConsumer)
      throws Exception {
    doWithinArtifact(applicationBundleDescriptor, artifactFolder, portConsumer, false, true, true, empty(), true,
                     APPS_FOLDER, false, (container, artifactConfiguration) -> container.getDeploymentService()
                         .deployApplication(artifactConfiguration),
                     false);

  }

  protected void doWithinApplication(BundleDescriptor applicationBundleDescriptor,
                                     String artifactFolder,
                                     Consumer<Integer> portConsumer,
                                     boolean lazyInitializationEnabled,
                                     boolean xmlValidationsEnabled,
                                     boolean lazyConnectionsEnabled,
                                     Optional<URI> log4JConfigurationFileOptional,
                                     boolean validateUsageOfDeploymentService)
      throws Exception {
    doWithinApplication(applicationBundleDescriptor, artifactFolder, portConsumer, lazyInitializationEnabled,
                        xmlValidationsEnabled, lazyConnectionsEnabled, log4JConfigurationFileOptional,
                        validateUsageOfDeploymentService, new Properties());
  }

  protected void doWithinApplication(BundleDescriptor applicationBundleDescriptor,
                                     String artifactFolder,
                                     Consumer<Integer> portConsumer,
                                     boolean lazyInitializationEnabled,
                                     boolean xmlValidationsEnabled,
                                     boolean lazyConnectionsEnabled,
                                     Optional<URI> log4JConfigurationFileOptional,
                                     boolean validateUsageOfDeploymentService,
                                     Properties props)
      throws Exception {
    doWithinArtifact(applicationBundleDescriptor, artifactFolder, portConsumer, lazyInitializationEnabled,
                     xmlValidationsEnabled, lazyConnectionsEnabled,
                     log4JConfigurationFileOptional, validateUsageOfDeploymentService,
                     APPS_FOLDER, true, (container, artifactConfiguration) -> container.getDeploymentService()
                         .deployApplication(artifactConfiguration),
                     false, props);
  }

  protected void doWithinDomain(BundleDescriptor applicationBundleDescriptor, String artifactFolder,
                                Consumer<Integer> portConsumer)
      throws Exception {
    doWithinArtifact(applicationBundleDescriptor, artifactFolder, portConsumer, false, true, true, empty(), true,
                     DOMAINS_FOLDER, true, (container, artifactConfiguration) -> container.getDeploymentService()
                         .deployDomain(artifactConfiguration),
                     false);
  }

  protected void doWithinArtifact(BundleDescriptor applicationBundleDescriptor,
                                  String artifactFolder,
                                  Consumer<Integer> portConsumer,
                                  boolean lazyInitializationEnabled,
                                  boolean xmlValidationsEnabled,
                                  boolean lazyConnectionsEnabled,
                                  Optional<URI> log4JConfigurationFileOptional,
                                  boolean validateUsageOfDeploymentService,
                                  String artifactDeploymentFolder,
                                  boolean installArtifact,
                                  BiConsumer<EmbeddedContainer, ArtifactConfiguration> deployConsumer,
                                  boolean restartingEmbedded)
      throws Exception {
    doWithinArtifact(applicationBundleDescriptor, artifactFolder, portConsumer, lazyInitializationEnabled, xmlValidationsEnabled,
                     lazyConnectionsEnabled, log4JConfigurationFileOptional, validateUsageOfDeploymentService,
                     artifactDeploymentFolder, installArtifact, deployConsumer, restartingEmbedded,
                     System.getProperty("mule.version"), new Properties());
  }

  protected void doWithinArtifact(BundleDescriptor applicationBundleDescriptor,
                                  String artifactFolder,
                                  Consumer<Integer> portConsumer,
                                  boolean lazyInitializationEnabled,
                                  boolean xmlValidationsEnabled,
                                  boolean lazyConnectionsEnabled,
                                  Optional<URI> log4JConfigurationFileOptional,
                                  boolean validateUsageOfDeploymentService,
                                  String artifactDeploymentFolder,
                                  boolean installArtifact,
                                  BiConsumer<EmbeddedContainer, ArtifactConfiguration> deployConsumer,
                                  boolean restartingEmbedded,
                                  Properties props)
      throws Exception {
    doWithinArtifact(applicationBundleDescriptor, artifactFolder, portConsumer, lazyInitializationEnabled, xmlValidationsEnabled,
                     lazyConnectionsEnabled, log4JConfigurationFileOptional, validateUsageOfDeploymentService,
                     artifactDeploymentFolder, installArtifact, deployConsumer, restartingEmbedded,
                     System.getProperty("mule.version"), props);
  }

  protected void doWithinArtifact(BundleDescriptor applicationBundleDescriptor,
                                  String artifactFolder,
                                  Consumer<Integer> portConsumer,
                                  boolean lazyInitializationEnabled,
                                  boolean xmlValidationsEnabled,
                                  boolean lazyConnectionsEnabled,
                                  Optional<URI> log4JConfigurationFileOptional,
                                  boolean validateUsageOfDeploymentService,
                                  String artifactDeploymentFolder,
                                  boolean installArtifact,
                                  BiConsumer<EmbeddedContainer, ArtifactConfiguration> deployConsumer,
                                  boolean restartingEmbedded,
                                  String muleVersion,
                                  Properties props)
      throws Exception {
    File artifactFile =
        installArtifact ? installMavenArtifact(artifactFolder, applicationBundleDescriptor, props) : new File(artifactFolder);
    Integer httpListenerPort = new FreePortFinder(6000, 9000).find();
    testWithSystemProperty("httpPort", valueOf(httpListenerPort), () -> {
      embeddedTestHelper.recreateContainerFolder();
      Consumer<EmbeddedContainer.EmbeddedContainerBuilder> embeddedContainerBuilderConsumer = embeddedContainerBuilder -> {
        try {
          embeddedContainerBuilder.log4jConfigurationFile(log4JConfigurationFileOptional
              .orElse(getClass().getClassLoader().getResource("log4j2-default.xml").toURI()))
              .product(MULE)
              .build();

        } catch (Exception e) {
          throw new RuntimeException(e);
        }
      };
      Consumer<EmbeddedContainer> embeddedContainerConsumer = (container) -> {
        ArtifactConfiguration artifactConfiguration = ArtifactConfiguration.builder()
            .artifactLocation(artifactFile)
            .deploymentConfiguration(DeploymentConfiguration.builder()
                .lazyInitialization(lazyInitializationEnabled)
                .xmlValidations(xmlValidationsEnabled)
                .lazyConnectionsEnabled(lazyConnectionsEnabled)
                .build())
            .build();
        deployConsumer.accept(container, artifactConfiguration);
        if (validateUsageOfDeploymentService) {
          validateArtifactState(artifactDeploymentFolder, artifactFile, container, true);
        }

        portConsumer.accept(httpListenerPort);
      };
      if (restartingEmbedded) {
        embeddedTestHelper.testWithEmbeddedNotStarted(embeddedContainerBuilderConsumer, muleVersion, container -> {
          // Runs the test with the embedded container for the first time
          container.start();
          try {
            embeddedContainerConsumer.accept(container);
            container.stop();
            // After container is stopped, it starts the same embedded container instance and runs the test again
            container.start();
            try {
              embeddedContainerConsumer.accept(container);
            } catch (Exception e) {
              container.stop();
              deleteQuietly(embeddedTestHelper.getContainerFolder());
            }
          } catch (Exception e) {
            container.stop();
            deleteQuietly(embeddedTestHelper.getContainerFolder());
          }
        });
      } else {
        embeddedTestHelper.testWithDefaultSettings(embeddedContainerBuilderConsumer, muleVersion, embeddedContainerConsumer);
      }
    });
  }


  protected void validateDomainIsDeployed(EmbeddedContainer embeddedContainer, File domainFile) {
    validateArtifactState(DOMAINS_FOLDER, domainFile, embeddedContainer, true);
  }

  protected void validateDomainIsUndeployed(EmbeddedContainer embeddedContainer, File domainFile) {
    validateArtifactState(DOMAINS_FOLDER, domainFile, embeddedContainer, false);
  }

  private void validateArtifactState(String artifactDeploymentFolder, File artifactFile, EmbeddedContainer container,
                                     boolean deployed) {
    assertThat(new File(new File(container.getContainerFolder(), artifactDeploymentFolder),
                        artifactFile.getName().replace(".jar", "")).exists(),
               is(deployed));
  }

  protected void runWithContainer(Consumer<EmbeddedContainer> task) {
    try {
      embeddedTestHelper.testWithDefaultSettings(embeddedContainerBuilder -> {
        try {
          embeddedContainerBuilder.log4jConfigurationFile(getClass().getClassLoader().getResource("log4j2-default.xml").toURI())
              .product(MULE)
              .build();
        } catch (Exception e) {
          throw new RuntimeException(e);
        }
      }, task);
    } catch (Exception e) {
      e.printStackTrace();
      fail();
    }
  }

  @Override
  public int getTestTimeoutSecs() {
    return 20 * super.getTestTimeoutSecs();
  }

  protected String getAppFolder(String appName) {
    return toFile(this.getClass().getClassLoader().getResource(ARTIFACTS_FOLDER + "/" + APPS_FOLDER + "/" + appName))
        .getAbsolutePath();
  }

  public Consumer<Integer> createRetryTestOperation(Consumer<Integer> originalTestOperation) {
    return value -> new PollingProber(10000, 100)
        .check(new JUnitProbe() {

          @Override
          protected boolean test() {
            originalTestOperation.accept(value);
            return true;
          }
        });
  }

  public void executeWithRetry(Runnable runnable) {
    new PollingProber(10000, 100)
        .check(new JUnitProbe() {

          @Override
          protected boolean test() {
            runnable.run();
            return true;
          }
        });
  }

}
