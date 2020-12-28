/*
 * Copyright (c) MuleSoft, Inc.  All rights reserved.  http://www.mulesoft.com
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */

package org.mule.runtime.module.embedded;

import static com.mashape.unirest.http.Unirest.post;
import static java.lang.String.format;
import static java.lang.Thread.sleep;
import static java.nio.file.Files.delete;
import static java.util.Optional.empty;
import static java.util.Optional.of;
import static org.apache.commons.io.FileUtils.deleteQuietly;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;
import static org.junit.rules.ExpectedException.none;
import static org.mule.runtime.api.deployment.management.ComponentInitialStateManager.DISABLE_SCHEDULER_SOURCES_PROPERTY;
import static org.mule.runtime.core.api.util.FileUtils.newFile;
import static org.mule.runtime.core.api.util.UUID.getUUID;
import static org.mule.tck.MuleTestUtils.testWithSystemProperty;
import static org.mule.tck.probe.PollingProber.probe;
import static org.mule.test.allure.AllureConstants.DeploymentTypeFeature.DEPLOYMENT_TYPE;
import static org.mule.test.allure.AllureConstants.DeploymentTypeFeature.DeploymentTypeStory.EMBEDDED;
import static org.mule.test.allure.AllureConstants.EmbeddedApiFeature.EMBEDDED_API;
import static org.mule.test.allure.AllureConstants.EmbeddedApiFeature.EmbeddedApiStory.CONFIGURATION;
import static org.mule.test.infrastructure.FileContainsInLine.hasLine;
import static org.mule.test.infrastructure.maven.MavenTestUtils.getApplicationBundleDescriptor;
import static org.mule.test.infrastructure.maven.MavenTestUtils.installMavenArtifact;

import org.junit.AfterClass;
import org.mule.runtime.module.artifact.api.descriptor.BundleDescriptor;
import org.mule.runtime.module.embedded.api.ArtifactConfiguration;
import org.mule.runtime.module.embedded.api.EmbeddedContainer;
import org.mule.tck.junit4.rule.DynamicPort;

import java.io.File;
import java.io.IOException;
import java.net.ConnectException;
import java.nio.file.Paths;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.exceptions.UnirestException;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Features;
import io.qameta.allure.Stories;
import io.qameta.allure.Story;
import org.apache.commons.io.IOUtils;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.rules.TemporaryFolder;

@Features({@Feature(EMBEDDED_API), @Feature(DEPLOYMENT_TYPE)})
@Stories({@Story(CONFIGURATION), @Story(EMBEDDED)})
public class ApplicationTestCase extends AbstractEmbeddedTestCase {

  private static final String LOGGING_FILE = "app.log";

  private static final String LISTENER_URL = "http://localhost:%d/test";

  private static final String HTTP_ECHO = "http-echo";

  @Rule
  public ExpectedException expectedException = none();

  @Rule
  public DynamicPort isAlivePort = new DynamicPort("isAlivePort");

  @Rule
  public TemporaryFolder temporaryFolder = new TemporaryFolder();

  @AfterClass
  public static void tearDown() {
    deleteQuietly(Paths.get(LOGGING_FILE).toFile());
  }

  @Description("Embedded runs an application depending on a connector")
  @Test
  public void applicationWithConnector() throws Exception {
    BundleDescriptor bundleDescriptor = getApplicationBundleDescriptor(HTTP_ECHO, empty());
    doWithinApplication(bundleDescriptor, getAppFolder(HTTP_ECHO), createRetryTestOperation(port -> {
      assertTestMessage(port);
    }));
  }

  @Description("Embedded can be restarted, start an instance of the container, runs the test, stop it and start it again and runs the test again")
  @Test
  public void restartEmbedded() throws Exception {
    BundleDescriptor bundleDescriptor = getApplicationBundleDescriptor(HTTP_ECHO, empty());
    doWithinApplicationRestartingEmbedded(bundleDescriptor, getAppFolder(HTTP_ECHO), createRetryTestOperation(port -> {
      assertTestMessage(port);
    }));
  }

  @Description("Embedded runs an application that retrieves a resource from the JDK")
  @Test
  // This test may fail depending on the JDK used to run the tests
  @Ignore("MULE-19062")
  public void jdkResourceAvailableFromApp() throws Exception {
    BundleDescriptor bundleDescriptor = getApplicationBundleDescriptor("jdk-exported-resource-app", empty());
    doWithinApplication(bundleDescriptor, getAppFolder("jdk-exported-resource-app"), createRetryTestOperation(port -> {
      try {
        HttpResponse<String> response = post(format("http://localhost:%s/", port)).asString();
        assertThat(response.getBody(), containsString("Manifest-Version: 1.0"));
      } catch (UnirestException e) {
        throw new RuntimeException(e);
      }
    }));
  }

  @Description("Embedded runs an application declaring a remote repository for a dependency")
  @Test
  @Ignore("MULE-19062")
  public void applicationWithRemoteRepositories() throws Exception {
    String appName = "pom-with-remote-repositories";
    BundleDescriptor bundleDescriptor = getApplicationBundleDescriptor(appName, empty());
    doWithinApplicationNotInstalled(bundleDescriptor, getAppFolder(appName),
                                    createRetryTestOperation(ApplicationTestCase::assertTestMessage));
  }

  @Description("Embedded runs an application using test dependencies and deploying a jar file")
  @Test
  @Ignore("MULE-19062")
  public void applicationWithTestDependency() throws Exception {
    BundleDescriptor bundleDescriptor =
        getApplicationBundleDescriptor("http-test-dependency", of("mule-application-light-package"));
    doWithinApplication(bundleDescriptor, getAppFolder("http-test-dependency-light-package"), createRetryTestOperation(port -> {
      try {
        String httpBody = "Something";
        HttpResponse<String> response = post(format("http://localhost:%s/", port)).body(httpBody).asString();
        assertThat(response.getBody(), is(httpBody));
      } catch (UnirestException e) {
        throw new RuntimeException(e);
      }
    }));
  }

  @Description("Embedded runs an application in lazy init mode")
  @Test
  @Ignore("MULE-19062")
  public void applicationDeploymentLazyInit() throws Exception {
    BundleDescriptor bundleDescriptor = getApplicationBundleDescriptor("http-echo", empty());
    doWithinApplication(bundleDescriptor, getAppFolder("http-echo"), port -> {
      try {
        post(format("http://localhost:%s/", port)).body("test-message").asString();
        fail("HTTP listener should not be initialized when deploying an application using lazy initialization");
      } catch (UnirestException e) {
        assertThat(org.apache.commons.lang3.exception.ExceptionUtils.getRootCause(e), instanceOf(ConnectException.class));
      }
    }, true, false, true, empty(), false);
  }

  @Description("Embedded runs an application in lazy init mode and enable xml validations")
  @Test
  public void applicationDeploymentLazyInitButEnableXmlValidations() throws Exception {
    BundleDescriptor bundleDescriptor = getApplicationBundleDescriptor("http-invalid-xml", empty());
    expectedException.expectMessage(containsString("There were '2' errors while parsing the given file 'mule-config.xml'."));
    doWithinApplication(bundleDescriptor, getAppFolder("http-invalid-xml"), port -> {
    }, true, true, true, empty(), false);
  }


  @Description("Embedded runs an application with scheduler not started by using the " + DISABLE_SCHEDULER_SOURCES_PROPERTY
      + " property as system property")
  @Test
  @Ignore("MULE-19062")
  public void applicationWithSchedulersStoppedByDefaultUsingSystemProperties() throws Exception {
    File fileWriteFolder = temporaryFolder.newFolder();
    File fileWriteDestination = new File(fileWriteFolder, getUUID());

    // start and stops the application, the scheduler within it should have been run if started
    testWithSystemProperty("file.path", fileWriteDestination.getAbsolutePath(), () -> {
      testWithSystemProperty(DISABLE_SCHEDULER_SOURCES_PROPERTY, "true", () -> {
        BundleDescriptor bundleDescriptor = getApplicationBundleDescriptor("scheduler-stopped", empty());
        doWithinApplication(bundleDescriptor, getAppFolder("scheduler-stopped"), port -> {
          waitForPollToBeExecuted();
        });
      });
    });
    assertThat(fileWriteDestination.exists(), is(false));
  }

  @Description("Embedded runs an application using a custom log4j configuration file")
  @Test
  @Ignore("MULE-19062")
  public void applicationWithCustomLogger() throws Exception {
    BundleDescriptor bundleDescriptor = getApplicationBundleDescriptor("http-echo", empty());
    doWithinApplication(bundleDescriptor, getAppFolder("http-echo"), createRetryTestOperation(port -> {
      assertTestMessage(port);
    }), false, true, true,
                        of(getClass().getClassLoader().getResource("log4j2-custom-file.xml").toURI()), true);
    File expectedLoggingFile = new File(LOGGING_FILE);
    assertThat(expectedLoggingFile.exists(), is(true));
    assertThat(expectedLoggingFile.length(), greaterThan(0l));
  }

  @Test
  @Description("Deploys an app with an http listener an checks that communication works")
  @Ignore("MULE-19062")
  public void deployListenerIsAlive() throws Exception {
    BundleDescriptor bundleDescriptor = getApplicationBundleDescriptor("test-app", empty());
    File artifactFile = installMavenArtifact(getAppFolder("successful-app"), bundleDescriptor);
    runWithContainer((container) -> {
      container.getDeploymentService()
          .deployApplication(ArtifactConfiguration.builder().artifactLocation(artifactFile).build());
      executeWithRetry(() -> assertAppIsRunning(true));;
    });
  }

  @Test
  @Description("If config files are modified within an app. Redeployment should be triggered")
  public void redeploymentOfModifiedAppAfterFailingShouldExecute() throws Exception {
    runWithContainer((container) -> {
      File testAppLocation = embeddedTestHelper.getFolderForApplication("failing-app");

      deployExpectingFailureAndUndeploy(container, testAppLocation);

      overrideFileModificationTimeStamp(testAppLocation, System.currentTimeMillis()); // To force time to be different from first

      // Since redeployment will be triggered due to config file change, we should expect another failure
      try {
        container.getDeploymentService()
            .deployApplication(ArtifactConfiguration.builder().artifactLocation(testAppLocation).build());
        fail();
      } catch (RuntimeException e) {
        // Do nothing
      }

      // And app should not be running
      assertAppIsRunning(false);
    });
  }

  @Test
  @Description("If a well written app with the same name as a failing app is deployed after the failing one, it should work")
  @Ignore("MULE-19062")
  public void redeploymentOfSuccessfulAppAfterFailingWithSameNameShouldWork() throws Exception {
    runWithContainer((container) -> {
      BundleDescriptor bundleDescriptor = getApplicationBundleDescriptor("test-app", empty());
      File testAppLocation = installMavenArtifact(getAppFolder("failing-app"), bundleDescriptor);

      deployExpectingFailureAndUndeploy(container, testAppLocation);

      testAppLocation = installMavenArtifact(getAppFolder("successful-app"), bundleDescriptor);
      overrideFileModificationTimeStamp(testAppLocation, System.currentTimeMillis()); // To force time to be different from
      // failing app.
      container.getDeploymentService()
          .deployApplication(ArtifactConfiguration.builder().artifactLocation(testAppLocation).build());
      executeWithRetry(() -> assertAppIsRunning(true));
    });
  }

  @Test
  @Description("If only one config file is modified, redeployment should still be triggered")
  public void modificationOfOneConfigShouldAcceptRedeployment() throws Exception {
    runWithContainer((container) -> {

      long time = System.currentTimeMillis();
      BundleDescriptor bundleDescriptor = getApplicationBundleDescriptor("test-app", empty());
      File testAppLocation = installMavenArtifact(getAppFolder("failing-app"), bundleDescriptor);
      overrideFileModificationTimeStamp(testAppLocation, time);

      deployExpectingFailureAndUndeploy(container, testAppLocation);

      File muleConfig = new File(getAppFolderInContainer(container, testAppLocation), "mule-config.xml");
      assertThat(muleConfig.exists(), is(true));
      overrideFileModificationTimeStamp(muleConfig, time + 9999); // Change just mule-config.xml

      // Since redeployment should be triggered we should expect another exception
      try {
        container.getDeploymentService()
            .deployApplication(ArtifactConfiguration.builder().artifactLocation(testAppLocation).build());
        fail();
      } catch (RuntimeException e) {
        // Do nothing
      }

      // Since no redeployment, app should not be running
      assertAppIsRunning(false);

    });
  }

  @Test
  @Description("If a non config file is modified, no redeployment should take place")
  public void modificationOfNonConfigShouldNotRedeploy() throws Exception {
    runWithContainer((container) -> {
      long time = System.currentTimeMillis();
      BundleDescriptor bundleDescriptor = getApplicationBundleDescriptor("test-app", empty());
      File testAppLocation = installMavenArtifact(getAppFolder("failing-app"), bundleDescriptor);
      overrideFileModificationTimeStamp(testAppLocation, time);

      deployExpectingFailureAndUndeploy(container, testAppLocation);

      File pom = new File(getAppFolderInContainer(container, testAppLocation), "/META-INF/maven/test/test-app/pom.xml");
      assertThat(pom.exists(), is(true));
      overrideFileModificationTimeStamp(pom, time + 99999); // change time

      // App should not be running
      assertAppIsRunning(false);
    });
  }

  @Test
  @Description("Even if 2 apps have the same name and were created at the same time, if one of them have different config files, redeployment should be triggered")
  @Ignore("MULE-19062")
  public void redeploymentOfSuccessfulAppAfterFailingWithSameNameAndTimeStampButDifferentConfigShouldWork() throws Exception {
    runWithContainer((container) -> {
      long time = System.currentTimeMillis();
      BundleDescriptor bundleDescriptor = getApplicationBundleDescriptor("test-app", empty());
      File testAppLocation = installMavenArtifact(getAppFolder("failing-app"), bundleDescriptor);
      overrideFileModificationTimeStamp(testAppLocation, time);
      deployExpectingFailureAndUndeploy(container, testAppLocation);

      testAppLocation = installMavenArtifact(getAppFolder("successful-app"), bundleDescriptor);
      overrideFileModificationTimeStamp(testAppLocation, time); // To force time to be the same of failing app.
      container.getDeploymentService()
          .deployApplication(ArtifactConfiguration.builder().artifactLocation(testAppLocation).build());

      executeWithRetry(() -> assertAppIsRunning(true));
    });
  }

  @Test
  @Description("Custom Log4j plugins are applied correctly on apps deployed to an embedded container")
  @Ignore("MULE-19062")
  public void applicationWithLog4jCustomPlugin() throws Exception {
    BundleDescriptor bundleDescriptor = getApplicationBundleDescriptor("log4j-plugin", empty());
    doWithinApplication(bundleDescriptor, getAppFolder("log4j-plugin"), createRetryTestOperation(port -> {
      String logPath = format("%s/log4j-plugin.log", new File(embeddedTestHelper.getContainerFolder(), "logs").getAbsoluteFile());
      File logFile = newFile(logPath);

      assertTestMessage(port);

      String expectedMessage = "I have intercepted your message :)";
      String unexpectedMessage = "This log message should be intercepted...";
      probe(() -> hasLine(containsString(expectedMessage)).matches(logFile),
            () -> format("Text '%s' not present in the logs", expectedMessage));
      probe(() -> !hasLine(containsString(unexpectedMessage)).matches(logFile),
            () -> format("Text '%s' is present in the logs", unexpectedMessage));
    }));
  }


  private File getAppFolderInContainer(EmbeddedContainer container, File testAppLocation) {
    return new File(container.getContainerFolder(), Paths.get("apps", testAppLocation.getName().replace(".jar", "")).toString());
  }

  private void deployExpectingFailureAndUndeploy(EmbeddedContainer container, File app) {
    try {
      container.getDeploymentService()
          .deployApplication(ArtifactConfiguration.builder().artifactLocation(app).build());
      fail();
    } catch (RuntimeException e) {
      container.getDeploymentService().undeployApplication(app.getName().replace(".jar", ""));
    }
  }

  private void overrideFileModificationTimeStamp(File root, long time) {
    File[] files = root.listFiles();
    if (files != null) {
      for (File file : files) {
        if (file.isDirectory()) {
          overrideFileModificationTimeStamp(file, time);
        } else {
          file.setLastModified(time);
        }
      }
    }
    root.setLastModified(time);
  }

  private void assertAppIsRunning(boolean shouldRun) {
    try {
      HttpClient client = HttpClientBuilder.create().build();
      HttpGet request = new HttpGet(String.format(LISTENER_URL, isAlivePort.getNumber()));
      org.apache.http.HttpResponse response = client.execute(request);
      assertThat(IOUtils.toString(response.getEntity().getContent()), containsString("ok"));
      if (!shouldRun) {
        fail();
      }
    } catch (IOException e) {
      if (shouldRun) {
        fail();
      }
    }
  }

  private void waitForPollToBeExecuted() {
    try {
      sleep(200);
    } catch (InterruptedException e) {
      // do nothing
    }
  }

  static void assertTestMessage(Integer port) {
    try {
      String httpBody = "test-message";
      HttpResponse<String> response = post(format("http://localhost:%s/", port)).body(httpBody).asString();
      assertThat(response.getBody(), is(httpBody));
    } catch (UnirestException e) {
      throw new RuntimeException(e);
    }
  }

}
