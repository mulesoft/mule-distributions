/*
 * Copyright (c) MuleSoft, Inc.  All rights reserved.  http://www.mulesoft.com
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
package org.mule.runtime.module.embedded;

import static java.lang.Thread.currentThread;
import static org.apache.commons.io.FileUtils.deleteDirectory;
import static org.apache.commons.io.FileUtils.toFile;
import static org.mule.maven.client.api.model.RemoteRepository.newRemoteRepositoryBuilder;
import static org.mule.maven.client.test.MavenTestHelper.createDefaultCommunityMavenConfigurationBuilder;
import static org.mule.maven.client.test.MavenTestHelper.createDefaultEnterpriseMavenConfigurationBuilder;
import static org.mule.maven.client.test.MavenTestHelper.getLocalRepositoryFolder;
import static org.mule.runtime.module.embedded.api.EmbeddedContainer.builder;
import static org.mule.runtime.module.embedded.internal.classloading.JdkOnlyClassLoaderFactory.create;

import org.mule.maven.client.api.model.MavenConfiguration;
import org.mule.runtime.module.embedded.api.ContainerConfiguration;
import org.mule.runtime.module.embedded.api.EmbeddedContainer;
import org.mule.runtime.module.embedded.internal.classloading.FilteringClassLoader;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
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
   * @param runnable a task that will create a {@link org.mule.runtime.module.embedded.api.EmbeddedContainer} for testing
   *                 purposes.
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
    testWithEmbeddedNotStarted(embeddedContainerConfigurer, embeddedContainer -> {
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
                                         Consumer<EmbeddedContainer> test) {
    test(() -> {
      EmbeddedContainer.EmbeddedContainerBuilder embeddedContainerBuilder;
      try {
        recreateContainerFolder();
        MavenConfiguration.MavenConfigurationBuilder mavenConfigurationBuilder =
            enterprise ? createDefaultEnterpriseMavenConfigurationBuilder(forceUpdateSnapshots)
                : createDefaultCommunityMavenConfigurationBuilder(forceUpdateSnapshots);
        mavenConfigurationBuilder.ignoreArtifactDescriptorRepositories(ignoreArtifactDescriptorRepositories);
        embeddedContainerBuilder = builder()
            .muleVersion(System.getProperty("mule.version"))
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

}
