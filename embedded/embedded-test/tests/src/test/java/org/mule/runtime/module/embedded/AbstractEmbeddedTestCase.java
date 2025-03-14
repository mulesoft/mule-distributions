/*
 * Copyright 2023 Salesforce, Inc. All rights reserved.
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
package org.mule.runtime.module.embedded;

import static org.mule.runtime.module.embedded.api.Product.MULE;
import static org.mule.tck.MuleTestUtils.testWithSystemProperty;
import static org.mule.test.infrastructure.maven.MavenTestUtils.installMavenArtifact;

import static java.lang.String.valueOf;
import static java.util.Arrays.asList;

import static org.apache.commons.io.FileUtils.deleteQuietly;
import static org.apache.commons.io.FileUtils.toFile;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.MatcherAssert.assertThat;

import org.mule.runtime.module.artifact.api.descriptor.BundleDescriptor;
import org.mule.runtime.module.embedded.api.ArtifactConfiguration;
import org.mule.runtime.module.embedded.api.DeploymentConfiguration;
import org.mule.runtime.module.embedded.api.EmbeddedContainer;
import org.mule.runtime.module.embedded.test.hepler.EmbeddedTestHelper;
import org.mule.tck.junit4.AbstractMuleTestCase;
import org.mule.tck.junit4.rule.FreePortFinder;
import org.mule.tck.junit4.rule.SystemProperty;
import org.mule.tck.probe.JUnitProbe;
import org.mule.tck.probe.PollingProber;

import java.io.File;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

import org.apache.commons.lang3.tuple.Pair;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;


@RunWith(Parameterized.class)
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

  @Rule
  public SystemProperty jvmVersionExtensionEnforcementLoose =
      new SystemProperty("mule.jvm.version.extension.enforcement", "LOOSE");

  // mule-embedded-api no longer supports log4j configuration since 1.7, this property has to be set so the log4j configuration
  // doesn't take place
  @Rule
  public SystemProperty simpleLogging =
      new SystemProperty("mule.simpleLog", "true");

  @Parameter
  public boolean useIsolation;

  @Parameters(name = "useIsolation: {0}")
  public static Collection<Boolean> data() {
    return asList(false, true);
  }

  protected void doWithinApplication(BundleDescriptor applicationBundleDescriptor, String artifactFolder,
                                     Consumer<Integer> portConsumer)
      throws Exception {
    doWithinArtifact(applicationBundleDescriptor, artifactFolder, portConsumer, false, true, true, true,
                     APPS_FOLDER, true, (container, artifactConfiguration) -> container.getDeploymentService()
                         .deployApplication(artifactConfiguration),
                     false);
  }

  protected void doWithinApplication(BundleDescriptor applicationBundleDescriptor, String artifactFolder,
                                     Consumer<Integer> portConsumer, String muleVersion)
      throws Exception {
    doWithinArtifact(applicationBundleDescriptor, artifactFolder, portConsumer, false, true, true, true,
                     APPS_FOLDER, true, (container, artifactConfiguration) -> container.getDeploymentService()
                         .deployApplication(artifactConfiguration),
                     false, muleVersion, new Properties());
  }

  protected void doWithinApplicationRestartingEmbedded(BundleDescriptor applicationBundleDescriptor, String artifactFolder,
                                                       Consumer<Integer> portConsumer)
      throws Exception {
    doWithinArtifact(applicationBundleDescriptor, artifactFolder, portConsumer, false, true, true, true,
                     APPS_FOLDER, true, (container, artifactConfiguration) -> container.getDeploymentService()
                         .deployApplication(artifactConfiguration),
                     true);
  }

  protected void doWithinApplicationNotInstalled(BundleDescriptor applicationBundleDescriptor, String artifactFolder,
                                                 Consumer<Integer> portConsumer)
      throws Exception {
    doWithinArtifact(applicationBundleDescriptor, artifactFolder, portConsumer, false, true, true, true,
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
                                     boolean validateUsageOfDeploymentService)
      throws Exception {
    doWithinApplication(applicationBundleDescriptor, artifactFolder, portConsumer, lazyInitializationEnabled,
                        xmlValidationsEnabled, lazyConnectionsEnabled, validateUsageOfDeploymentService,
                        new Properties());
  }

  protected void doWithinApplication(BundleDescriptor applicationBundleDescriptor,
                                     String artifactFolder,
                                     Consumer<Integer> portConsumer,
                                     boolean lazyInitializationEnabled,
                                     boolean xmlValidationsEnabled,
                                     boolean lazyConnectionsEnabled,
                                     boolean validateUsageOfDeploymentService,
                                     Properties props)
      throws Exception {
    doWithinArtifact(applicationBundleDescriptor, artifactFolder, portConsumer, lazyInitializationEnabled,
                     xmlValidationsEnabled, lazyConnectionsEnabled, validateUsageOfDeploymentService,
                     APPS_FOLDER, true, (container, artifactConfiguration) -> container.getDeploymentService()
                         .deployApplication(artifactConfiguration),
                     false, props);
  }

  protected void doWithinDomain(BundleDescriptor applicationBundleDescriptor, String artifactFolder,
                                Consumer<Integer> portConsumer)
      throws Exception {
    doWithinArtifact(applicationBundleDescriptor, artifactFolder, portConsumer, false, true, true, true,
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
                                  boolean validateUsageOfDeploymentService,
                                  String artifactDeploymentFolder,
                                  boolean installArtifact,
                                  BiConsumer<EmbeddedContainer, ArtifactConfiguration> deployConsumer,
                                  boolean restartingEmbedded)
      throws Exception {
    doWithinArtifact(applicationBundleDescriptor, artifactFolder, portConsumer, lazyInitializationEnabled, xmlValidationsEnabled,
                     lazyConnectionsEnabled, validateUsageOfDeploymentService,
                     artifactDeploymentFolder, installArtifact, deployConsumer, restartingEmbedded,
                     System.getProperty("mule.version"), new Properties());
  }

  protected void doWithinArtifact(BundleDescriptor applicationBundleDescriptor,
                                  String artifactFolder,
                                  Consumer<Integer> portConsumer,
                                  boolean lazyInitializationEnabled,
                                  boolean xmlValidationsEnabled,
                                  boolean lazyConnectionsEnabled,
                                  boolean validateUsageOfDeploymentService,
                                  String artifactDeploymentFolder,
                                  boolean installArtifact,
                                  BiConsumer<EmbeddedContainer, ArtifactConfiguration> deployConsumer,
                                  boolean restartingEmbedded,
                                  Properties props)
      throws Exception {
    doWithinArtifact(applicationBundleDescriptor, artifactFolder, portConsumer, lazyInitializationEnabled, xmlValidationsEnabled,
                     lazyConnectionsEnabled, validateUsageOfDeploymentService,
                     artifactDeploymentFolder, installArtifact, deployConsumer, restartingEmbedded,
                     System.getProperty("mule.version"), props);
  }

  protected void doWithinArtifact(BundleDescriptor applicationBundleDescriptor,
                                  String artifactFolder,
                                  Consumer<Integer> portConsumer,
                                  boolean lazyInitializationEnabled,
                                  boolean xmlValidationsEnabled,
                                  boolean lazyConnectionsEnabled,
                                  boolean validateUsageOfDeploymentService,
                                  String artifactDeploymentFolder,
                                  boolean installArtifact,
                                  BiConsumer<EmbeddedContainer, ArtifactConfiguration> deployConsumer,
                                  boolean restartingEmbedded,
                                  String muleVersion,
                                  Properties props)
      throws Exception {
    File artifactFile = resolveArtifact(applicationBundleDescriptor, artifactFolder, installArtifact, props);
    Integer httpListenerPort = new FreePortFinder(6000, 9000).find();
    testWithSystemProperty("httpPort", valueOf(httpListenerPort), () -> {
      embeddedTestHelper.recreateContainerFolder();
      Consumer<EmbeddedContainer.EmbeddedContainerBuilder> embeddedContainerBuilderConsumer = embeddedContainerBuilder -> {
        try {
          embeddedContainerBuilder.product(MULE).useIsolation(useIsolation).build();
        } catch (Exception e) {
          throw new RuntimeException(e);
        }
      };
      Consumer<EmbeddedContainer> embeddedContainerConsumer = container -> {
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

  private static final Map<Pair<BundleDescriptor, Properties>, File> INSTALLED_ARTIFACT_CACHES = new HashMap<>();

  private File resolveArtifact(BundleDescriptor applicationBundleDescriptor,
                               String artifactFolder,
                               boolean installArtifact,
                               Properties props) {
    return installArtifact
        ? INSTALLED_ARTIFACT_CACHES.computeIfAbsent(Pair.of(applicationBundleDescriptor, props),
                                                    k -> installMavenArtifact(artifactFolder, k.getLeft(), k.getRight()))
        : new File(artifactFolder);
  }

  protected void validateApplicationIsDeployed(EmbeddedContainer embeddedContainer, File applicationFile) {
    validateArtifactState(APPS_FOLDER, applicationFile, embeddedContainer, true);
  }

  protected void validateApplicationIsUnDeployed(EmbeddedContainer embeddedContainer, File applicationFile) {
    validateArtifactState(APPS_FOLDER, applicationFile, embeddedContainer, false);
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
    embeddedTestHelper.testWithDefaultSettings(embeddedContainerBuilder -> {
      try {
        embeddedContainerBuilder.product(MULE).useIsolation(useIsolation).build();
      } catch (Exception e) {
        throw new RuntimeException(e);
      }
    }, task);
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
