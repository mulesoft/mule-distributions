/*
 * Copyright 2023 Salesforce, Inc. All rights reserved.
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
package org.mule.runtime.module.embedded.test.helper;

import static org.mule.maven.client.api.model.RemoteRepository.newRemoteRepositoryBuilder;
import static org.mule.maven.client.test.MavenTestHelper.createDefaultCommunityMavenConfigurationBuilder;
import static org.mule.maven.client.test.MavenTestHelper.createDefaultEnterpriseMavenConfigurationBuilder;
import static org.mule.maven.client.test.MavenTestHelper.getLocalRepositoryFolder;
import static org.mule.runtime.module.embedded.api.EmbeddedContainer.builder;
import static org.mule.runtime.module.embedded.api.Product.MULE;
import static org.mule.runtime.module.embedded.internal.classloading.JdkOnlyClassLoaderFactory.create;
import static org.mule.tck.MuleTestUtils.testWithSystemProperty;
import static org.mule.test.infrastructure.maven.MavenTestUtils.getApplicationBundleDescriptor;
import static org.mule.test.infrastructure.maven.MavenTestUtils.getDomainBundleDescriptor;

import static java.lang.String.valueOf;
import static java.lang.Thread.currentThread;

import static org.apache.commons.io.FileUtils.deleteDirectory;
import static org.apache.commons.io.FileUtils.deleteQuietly;
import static org.apache.commons.io.FileUtils.toFile;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

import org.mule.maven.client.api.model.MavenConfiguration;
import org.mule.runtime.module.embedded.api.ArtifactConfiguration;
import org.mule.runtime.module.embedded.api.ContainerConfiguration;
import org.mule.runtime.module.embedded.api.DeploymentConfiguration;
import org.mule.runtime.module.embedded.api.EmbeddedContainer;
import org.mule.runtime.module.embedded.internal.classloading.FilteringClassLoader;
import org.mule.runtime.module.embedded.test.helper.bootstrap.BundleDescriptor;
import org.mule.tck.junit4.rule.FreePortFinder;
import org.mule.tck.probe.JUnitProbe;
import org.mule.tck.probe.PollingProber;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Properties;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

import net.lingala.zip4j.ZipFile;
import net.lingala.zip4j.model.ZipParameters;
import org.junit.rules.TemporaryFolder;

/**
 * Helper class for running embedded tests.
 *
 * @since 4.0
 */
public class EmbeddedTestHelper {

  private final TemporaryFolder temporaryFolder;
  private final boolean enterprise;
  private final boolean forceUpdateSnapshots;
  private boolean ignoreArtifactDescriptorRepositories = false;
  private File containerFolder;
  private EmbeddedContainer container;

  public EmbeddedTestHelper(boolean enterprise, boolean forceUpdateSnapshots, boolean ignoreArtifactDescriptorRepositories) {
    try {
      temporaryFolder = new TemporaryFolder();
      temporaryFolder.create();
      this.enterprise = enterprise;
      this.forceUpdateSnapshots = forceUpdateSnapshots;
      this.ignoreArtifactDescriptorRepositories = ignoreArtifactDescriptorRepositories;
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  public void recreateContainerFolder() {
    try {
      this.containerFolder = temporaryFolder.newFolder();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * Sets the proper context for creating an embedded container
   *
   * @param runnable a task that will create a {@link org.mule.runtime.module.embedded.test.hepler.api.EmbeddedContainer} for
   *                 testing purposes.
   */
  public void test(Runnable runnable) {
    ClassLoader contextClassLoader = currentThread().getContextClassLoader();
    try {
      // Sets a classloader with the JDK only to ensure that dependencies are read form the embedded container classloader
      FilteringClassLoader jdkOnlyClassLoader = create(EmbeddedTestHelper.class.getClassLoader());
      currentThread().setContextClassLoader(jdkOnlyClassLoader);

      runnable.run();
    } finally {
      currentThread().setContextClassLoader(contextClassLoader);
    }
  }

  /**
   * Preconfigures the {@link EmbeddedContainer} with default settings
   *
   * @param embeddedContainerConfigurer function to add configuration to the embedded container
   * @param test                        function that run the tests
   */
  public void testWithDefaultSettings(Consumer<EmbeddedContainer.EmbeddedContainerBuilder> embeddedContainerConfigurer,
                                      Consumer<EmbeddedContainer> test) {
    testWithDefaultSettings(embeddedContainerConfigurer, System.getProperty("mule.version"), test);
  }

  /**
   * Preconfigures the {@link EmbeddedContainer} with default settings
   *
   * @param embeddedContainerConfigurer function to add configuration to the embedded container
   * @param muleVersion                 version of the Runtime to run against.
   * @param test                        function that run the tests
   */
  public void testWithDefaultSettings(Consumer<EmbeddedContainer.EmbeddedContainerBuilder> embeddedContainerConfigurer,
                                      String muleVersion,
                                      Consumer<EmbeddedContainer> test) {
    testWithEmbeddedNotStarted(embeddedContainerConfigurer, muleVersion, embeddedContainer -> {
      try {
        embeddedContainer.start();
        test.accept(container);
      } finally {
        if (embeddedContainer != null) {
          try {
            embeddedContainer.stop();
            deleteDirectory(containerFolder);
          } catch (Throwable containerStopException) {
            // Never mind
          }
        }
      }

    });
  }

  public void testWithEmbeddedNotStarted(Consumer<EmbeddedContainer.EmbeddedContainerBuilder> embeddedContainerConfigurer,
                                         String muleVersion, Consumer<EmbeddedContainer> test) {
    test(() -> {
      EmbeddedContainer.EmbeddedContainerBuilder embeddedContainerBuilder;
      try {
        recreateContainerFolder();
        MavenConfiguration.MavenConfigurationBuilder mavenConfigurationBuilder =
            enterprise ? createDefaultEnterpriseMavenConfigurationBuilder(forceUpdateSnapshots)
                : createDefaultCommunityMavenConfigurationBuilder(forceUpdateSnapshots);
        mavenConfigurationBuilder.ignoreArtifactDescriptorRepositories(ignoreArtifactDescriptorRepositories);
        embeddedContainerBuilder = builder()
            .muleVersion(muleVersion)
            .containerConfiguration(ContainerConfiguration.builder().containerFolder(containerFolder).build())
            .mavenConfiguration(mavenConfigurationBuilder
                .remoteRepository(newRemoteRepositoryBuilder().id("local.repo")
                    .url(getLocalRepositoryFolder().toURI().toURL())
                    .build())
                .build());
        embeddedContainerConfigurer.accept(embeddedContainerBuilder);
      } catch (Exception e) {
        throw new RuntimeException(e);
      }

      container = null;
      container = embeddedContainerBuilder.build();
      test.accept(container);
    });
  }


  public File getContainerFolder() {
    return containerFolder;
  }

  public void dispose() {
    temporaryFolder.delete();
  }

  public File getPackagedApplication(File applicationFolder) throws Exception {
    File compressedFile = temporaryFolder.newFile(applicationFolder.getName());
    ZipFile zipFile = new ZipFile(compressedFile.getAbsolutePath() + ".jar");
    File[] files = applicationFolder.listFiles();
    for (File file : files) {
      if (file.isDirectory()) {
        zipFile.addFolder(file, new ZipParameters());
      } else {
        zipFile.addFile(file, new ZipParameters());
      }
    }
    return zipFile.getFile();
  }

  public File getFolderForApplication(String applicationFolderName) {
    return toFile(getClass().getClassLoader().getResource(Paths.get("artifacts", "apps", applicationFolderName).toString()));
  }

  public File getFolderForDomain(String domainFolderName) {
    return toFile(getClass().getClassLoader().getResource(Paths.get("artifacts", "domains", domainFolderName).toString()));
  }

  public EmbeddedContainer getContainer() {
    return container;
  }



  protected void doWithinApplication(BundleDescriptor applicationBundleDescriptor, String artifactFolder,
                                     String artifactDeploymentFolder, Consumer<Integer> portConsumer)
      throws Exception {
    doWithinArtifact(applicationBundleDescriptor, artifactFolder, portConsumer, false, true, true, true,
                     artifactDeploymentFolder, true, (container, artifactConfiguration) -> container.getDeploymentService()
                         .deployApplication(artifactConfiguration),
                     false);
  }

  protected void doWithinApplication(BundleDescriptor applicationBundleDescriptor, String artifactFolder,
                                     String artifactDeploymentFolder, Consumer<Integer> portConsumer, String muleVersion)
      throws Exception {
    doWithinArtifact(applicationBundleDescriptor, artifactFolder, portConsumer, false, true, true, true,
                     artifactDeploymentFolder, true, (container, artifactConfiguration) -> container.getDeploymentService()
                         .deployApplication(artifactConfiguration),
                     false, muleVersion, new Properties());
  }

  protected void doWithinApplicationRestartingEmbedded(BundleDescriptor applicationBundleDescriptor, String artifactFolder,
                                                       String artifactDeploymentFolder, Consumer<Integer> portConsumer)
      throws Exception {
    doWithinArtifact(applicationBundleDescriptor, artifactFolder, portConsumer, false, true, true, true,
                     artifactDeploymentFolder, true, (container, artifactConfiguration) -> container.getDeploymentService()
                         .deployApplication(artifactConfiguration),
                     true);
  }

  protected void doWithinApplicationNotInstalled(BundleDescriptor applicationBundleDescriptor, String artifactFolder,
                                                 String artifactDeploymentFolder, Consumer<Integer> portConsumer)
      throws Exception {
    doWithinArtifact(applicationBundleDescriptor, artifactFolder, portConsumer, false, true, true, true,
                     artifactDeploymentFolder, false, (container, artifactConfiguration) -> container.getDeploymentService()
                         .deployApplication(artifactConfiguration),
                     false);

  }

  protected void doWithinApplication(BundleDescriptor applicationBundleDescriptor,
                                     String artifactFolder,
                                     String artifactDeploymentFolder,
                                     Consumer<Integer> portConsumer,
                                     boolean lazyInitializationEnabled,
                                     boolean xmlValidationsEnabled,
                                     boolean lazyConnectionsEnabled,
                                     boolean validateUsageOfDeploymentService)
      throws Exception {
    doWithinApplication(applicationBundleDescriptor, artifactFolder, artifactDeploymentFolder, portConsumer,
                        lazyInitializationEnabled,
                        xmlValidationsEnabled, lazyConnectionsEnabled, validateUsageOfDeploymentService,
                        new Properties());
  }

  protected void doWithinApplication(BundleDescriptor applicationBundleDescriptor,
                                     String artifactFolder,
                                     String artifactDeploymentFolder,
                                     Consumer<Integer> portConsumer,
                                     boolean lazyInitializationEnabled,
                                     boolean xmlValidationsEnabled,
                                     boolean lazyConnectionsEnabled,
                                     boolean validateUsageOfDeploymentService,
                                     Properties props)
      throws Exception {
    doWithinArtifact(applicationBundleDescriptor, artifactFolder, portConsumer, lazyInitializationEnabled,
                     xmlValidationsEnabled, lazyConnectionsEnabled, validateUsageOfDeploymentService,
                     artifactDeploymentFolder, true, (container, artifactConfiguration) -> container.getDeploymentService()
                         .deployApplication(artifactConfiguration),
                     false, props);
  }

  protected void doWithinDomain(BundleDescriptor applicationBundleDescriptor, String artifactFolder,
                                String artifactDeploymentFolder, Consumer<Integer> portConsumer)
      throws Exception {
    doWithinArtifact(applicationBundleDescriptor, artifactFolder, portConsumer, false, true, true, true,
                     artifactDeploymentFolder, true, (container, artifactConfiguration) -> container.getDeploymentService()
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

  protected void doWithinArtifact(BundleDescriptor artifactBundleDescriptor,
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
    org.mule.runtime.module.artifact.api.descriptor.BundleDescriptor bundleDescriptor = artifactBundleDescriptor.isApplication()
        ? getApplicationBundleDescriptor(artifactBundleDescriptor.getArtifactId(), artifactBundleDescriptor.getClassifier())
        : getDomainBundleDescriptor(artifactBundleDescriptor.getArtifactId());
    // File artifactFile =
    // installArtifact ? installMavenArtifact(artifactFolder, applicationBundleDescriptor, props) : new File(artifactFolder);
    File artifactFile = new File("/Users/rbourbon/.m2/repository/test/http-echo/1.0.0/http-echo-1.0.0-mule-application.jar");
    Integer httpListenerPort = new FreePortFinder(6000, 9000).find();
    testWithSystemProperty("httpPort", valueOf(httpListenerPort), () -> {
      recreateContainerFolder();
      Consumer<EmbeddedContainer.EmbeddedContainerBuilder> embeddedContainerBuilderConsumer = embeddedContainerBuilder -> {
        try {
          embeddedContainerBuilder.product(MULE).useIsolation(false).build();
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
        testWithEmbeddedNotStarted(embeddedContainerBuilderConsumer, muleVersion, container -> {
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
              deleteQuietly(getContainerFolder());
            }
          } catch (Exception e) {
            container.stop();
            deleteQuietly(getContainerFolder());
          }
        });
      } else {
        testWithDefaultSettings(embeddedContainerBuilderConsumer, muleVersion, embeddedContainerConsumer);
      }
    });
  }

  private void validateArtifactState(String artifactDeploymentFolder, File artifactFile, EmbeddedContainer container,
                                     boolean deployed) {
    assertThat(new File(new File(container.getContainerFolder(), artifactDeploymentFolder),
                        artifactFile.getName().replace(".jar", "")).exists(),
               is(deployed));
  }

  protected void runWithContainer(Consumer<EmbeddedContainer> task) {
    testWithDefaultSettings(embeddedContainerBuilder -> {
      try {
        embeddedContainerBuilder.product(MULE).useIsolation(false).build();
      } catch (Exception e) {
        throw new RuntimeException(e);
      }
    }, task);
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
