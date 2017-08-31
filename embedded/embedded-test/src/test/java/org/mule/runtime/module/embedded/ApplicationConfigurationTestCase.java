/*
 * Copyright (c) MuleSoft, Inc.  All rights reserved.  http://www.mulesoft.com
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */

package org.mule.runtime.module.embedded;

import static com.mashape.unirest.http.Unirest.post;
import static java.lang.String.format;
import static java.lang.String.valueOf;
import static java.lang.Thread.sleep;
import static java.nio.file.Files.delete;
import static java.util.Optional.empty;
import static java.util.Optional.of;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;
import static org.mule.runtime.api.deployment.management.ComponentInitialStateManager.DISABLE_SCHEDULER_SOURCES_PROPERTY;
import static org.mule.runtime.container.api.MuleFoldersUtil.getAppsFolder;
import static org.mule.runtime.core.api.util.UUID.getUUID;
import static org.mule.runtime.module.embedded.api.Product.MULE;
import static org.mule.tck.MuleTestUtils.testWithSystemProperty;
import static org.mule.test.allure.AllureConstants.DeploymentTypeFeature.DEPLOYMENT_TYPE;
import static org.mule.test.allure.AllureConstants.DeploymentTypeFeature.DeploymentTypeStory.EMBEDDED;
import static org.mule.test.allure.AllureConstants.EmbeddedApiFeature.EMBEDDED_API;
import static org.mule.test.allure.AllureConstants.EmbeddedApiFeature.EmbeddedApiStory.CONFIGURATION;

import org.mule.runtime.module.embedded.api.ArtifactConfiguration;
import org.mule.runtime.module.embedded.api.DeploymentConfiguration;
import org.mule.runtime.module.embedded.api.EmbeddedContainer;
import org.mule.tck.junit4.AbstractMuleTestCase;
import org.mule.tck.junit4.rule.DynamicPort;
import org.mule.tck.junit4.rule.FreePortFinder;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.exceptions.UnirestException;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.function.Consumer;

import org.apache.commons.io.IOUtils;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.AfterClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Features;
import io.qameta.allure.Stories;
import io.qameta.allure.Story;

@Features({@Feature(EMBEDDED_API), @Feature(DEPLOYMENT_TYPE)})
@Stories({@Story(CONFIGURATION), @Story(EMBEDDED)})
public class ApplicationConfigurationTestCase extends AbstractMuleTestCase {

  private static final String LOGGING_FILE = "app.log";

  private static final String LISTENER_URL = "http://localhost:%d/test";

  private static EmbeddedTestHelper embeddedTestHelper = new EmbeddedTestHelper(false);


  @Rule
  public DynamicPort isAlivePort = new DynamicPort("isAlivePort");

  @Rule
  public TemporaryFolder temporaryFolder = new TemporaryFolder();

  @AfterClass
  public static void dispose() {
    embeddedTestHelper.dispose();
  }

  @Description("Embedded runs an application depending on a connector")
  @Test
  public void applicationWithConnector() throws Exception {
    doWithinApplication(embeddedTestHelper.getFolderForApplication("http-echo"), port -> {
      try {
        String httpBody = "test-message";
        HttpResponse<String> response = post(format("http://localhost:%s/", port)).body(httpBody).asString();
        assertThat(response.getBody(), is(httpBody));
      } catch (UnirestException e) {
        throw new RuntimeException(e);
      }
    });
  }

  @Description("Embedded runs an application using test dependencies and deploying a jar file")
  @Test
  public void applicationWithTestDependency() throws Exception {
    doWithinApplication(embeddedTestHelper
        .getPackagedApplication(embeddedTestHelper.getFolderForApplication("http-test-dependency")), port -> {
          try {
            String httpBody = "org.mobicents.xcap.client.impl.XcapClientImpl";
            HttpResponse<String> response = post(format("http://localhost:%s/", port)).body(httpBody).asString();
            assertThat(response.getBody(), is(httpBody));
          } catch (UnirestException e) {
            throw new RuntimeException(e);
          }
        }, true, empty());
  }

  @Description("Embedded runs an application with scheduler not started by using the " + DISABLE_SCHEDULER_SOURCES_PROPERTY
      + " property as system property")
  @Test
  public void applicationWithSchedulersStoppedByDefaultUsingSystemProperties() throws Exception {
    File fileWriteFolder = temporaryFolder.newFolder();
    File fileWriteDestination = new File(fileWriteFolder, getUUID());

    // start and stops the application, the scheduler within it should have been run if started
    testWithSystemProperty("file.path", fileWriteDestination.getAbsolutePath(), () -> {
      testWithSystemProperty(DISABLE_SCHEDULER_SOURCES_PROPERTY, "true", () -> {
        doWithinApplication(embeddedTestHelper.getFolderForApplication("scheduler-stopped"), port -> {
          waitForPollToBeExecuted();
        });
      });
    });
    assertThat(fileWriteDestination.exists(), is(false));
  }

  @Description("Embedded runs an application using a custom log4j configuration file")
  @Test
  public void applicationWithCustomLogger() throws Exception {
    doWithinApplication(embeddedTestHelper.getFolderForApplication("http-echo"), port -> {
      try {
        String httpBody = "test-message";
        HttpResponse<String> response = post(format("http://localhost:%s/", port)).body(httpBody).asString();
        assertThat(response.getBody(), is(httpBody));
      } catch (UnirestException e) {
        throw new RuntimeException(e);
      }
    }, false, of(getClass().getClassLoader().getResource("log4j2-custom-file.xml").toURI()));
    try {
      File expectedLoggingFile = new File(LOGGING_FILE);
      assertThat(expectedLoggingFile.exists(), is(true));
      assertThat(expectedLoggingFile.length(), greaterThan(0l));
    } finally {
      delete(Paths.get(LOGGING_FILE));
    }
  }

  @Test
  @Description("Deploys an app with an http listener an checks that communication works")
  public void deployListenerIsAlive() throws Exception {
    runWithContainer((container) -> {
      File testAppLocation = embeddedTestHelper.getFolderForApplication("successful/testapp");
      container.getDeploymentService()
          .deployApplication(ArtifactConfiguration.builder().artifactLocation(testAppLocation).build());
      assertAppIsRunning(true);
    });
  }

  @Test
  @Description("If config files are modified within an app. Redeployment should be triggered")
  public void redeploymentOfModifiedAppAfterFailingShouldExecute() throws Exception {
    runWithContainer((container) -> {
      File testAppLocation = embeddedTestHelper.getFolderForApplication("failing/testapp");

      deployExpectingFailureAndUndeploy(container, testAppLocation);

      overrideFileModificationTimeStamp(testAppLocation, System.currentTimeMillis()); //To force time to be different from first

      //Since redeployment will be triggered due to config file change, we should expect another failure
      try {
        container.getDeploymentService()
            .deployApplication(ArtifactConfiguration.builder().artifactLocation(testAppLocation).build());
        fail();
      } catch (RuntimeException e) {
        //Do nothing
      }

      //And app should not be running
      assertAppIsRunning(false);
    });
  }

  @Test
  @Description("If a well written app with the same name as a failing app is deployed after the failing one, it should work")
  public void redeploymentOfSuccessfulAppAfterFailingWithSameNameShouldWork() throws Exception {
    runWithContainer((container) -> {
      File testAppLocation = embeddedTestHelper.getFolderForApplication("failing/testapp");

      deployExpectingFailureAndUndeploy(container, testAppLocation);

      testAppLocation = embeddedTestHelper.getFolderForApplication("successful/testapp");
      overrideFileModificationTimeStamp(testAppLocation, System.currentTimeMillis()); //To force time to be different from failing app.
      container.getDeploymentService()
          .deployApplication(ArtifactConfiguration.builder().artifactLocation(testAppLocation).build());
      assertAppIsRunning(true);
    });
  }

  @Test
  @Description("If only one config file is modified, redeployment should still be triggered")
  public void modificationOfOneConfigShouldAcceptRedeployment() throws Exception {
    runWithContainer((container) -> {

      long time = System.currentTimeMillis();
      File testAppLocation = embeddedTestHelper.getFolderForApplication("failing/testapp");
      overrideFileModificationTimeStamp(testAppLocation, time);

      deployExpectingFailureAndUndeploy(container, testAppLocation);

      File muleConfig = new File(testAppLocation, "mule-config.xml");
      assertThat(muleConfig.exists(), is(true));
      overrideFileModificationTimeStamp(muleConfig, time + 9999); //Change just mule-config.xml

      //Since redeployment should be triggered we should expect another exception
      try {
        container.getDeploymentService()
            .deployApplication(ArtifactConfiguration.builder().artifactLocation(testAppLocation).build());
        fail();
      } catch (RuntimeException e) {
        //Do nothing
      }

      //Since no redeployment, app should not be running
      assertAppIsRunning(false);

    });
  }

  @Test
  @Description("If a non config file is modified, no redeployment should take place")
  public void modificationOfNonConfigShouldNotRedeploy() throws Exception {
    runWithContainer((container) -> {
      long time = System.currentTimeMillis();
      File testAppLocation = embeddedTestHelper.getFolderForApplication("failing/testapp");
      overrideFileModificationTimeStamp(testAppLocation, time);

      deployExpectingFailureAndUndeploy(container, testAppLocation);

      File pom = new File(testAppLocation, "/META-INF/maven/org.mule.test/testapp/pom.xml");
      assertThat(pom.exists(), is(true));
      //Change mule-artifact.json
      overrideFileModificationTimeStamp(pom, time + 99999); //change time

      //No exception should be triggered
      container.getDeploymentService()
          .deployApplication(ArtifactConfiguration.builder().artifactLocation(testAppLocation).build());

      //App should not be running
      assertAppIsRunning(false);
    });
  }

  @Test
  @Description("Even if 2 apps have the same name and were created at the same time, if one of them have different config files, redeployment should be triggered")
  public void redeploymentOfSuccessfulAppAfterFailingWithSameNameAndTimeStampButDifferentConfigShouldWork() throws Exception {
    runWithContainer((container) -> {
      long time = System.currentTimeMillis();
      File testAppLocation = embeddedTestHelper.getFolderForApplication("failing/testapp");
      overrideFileModificationTimeStamp(testAppLocation, time);
      deployExpectingFailureAndUndeploy(container, testAppLocation);

      testAppLocation = embeddedTestHelper.getFolderForApplication("successful/testapp");
      overrideFileModificationTimeStamp(testAppLocation, time); //To force time to be the same of failing app.
      File artifactFile = new File(testAppLocation, "META-INF/mule-artifact/mule-artifact.json");
      assertThat(artifactFile.exists(), is(true));
      overrideFileModificationTimeStamp(artifactFile, time + 99999);
      container.getDeploymentService()
          .deployApplication(ArtifactConfiguration.builder().artifactLocation(testAppLocation).build());

      assertAppIsRunning(true);
    });
  }

  private void deployExpectingFailureAndUndeploy(EmbeddedContainer container, File app) {
    try {
      container.getDeploymentService()
          .deployApplication(ArtifactConfiguration.builder().artifactLocation(app).build());
      fail();
    } catch (RuntimeException e) {
      container.getDeploymentService().undeployApplication(app.getName());
    }
  }

  private void overrideFileModificationTimeStamp(File root, long time) {
    File[] files = root.listFiles();
    if (files != null) {
      for (int i = 0; i < files.length; i++) {
        if (files[i].isDirectory()) {
          overrideFileModificationTimeStamp(files[i], time);
        } else {
          files[i].setLastModified(time);
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

  private void doWithinApplication(File applicationFolder, Consumer<Integer> portConsumer)
      throws Exception {
    doWithinApplication(applicationFolder, portConsumer, false, empty());
  }

  private void doWithinApplication(File applicationFolder, Consumer<Integer> portConsumer, boolean enableTestDependencies,
                                   Optional<URI> log4JConfigurationFileOptional)
      throws Exception {

    Integer httpListenerPort = new FreePortFinder(6000, 9000).find();
    testWithSystemProperty("httpPort", valueOf(httpListenerPort), () -> {
      embeddedTestHelper.recreateContainerFolder();
      embeddedTestHelper.testWithDefaultSettings(embeddedContainerBuilder -> {
        try {
          embeddedContainerBuilder.log4jConfigurationFile(log4JConfigurationFileOptional
              .orElse(getClass().getClassLoader().getResource("log4j2-default.xml").toURI()))
              .product(MULE)
              .build();

        } catch (Exception e) {
          throw new RuntimeException(e);
        }
      }, (container) -> {
        ArtifactConfiguration applicationConfiguration = ArtifactConfiguration.builder()
            .artifactLocation(applicationFolder)
            .deploymentConfiguration(DeploymentConfiguration.builder()
                .testDependenciesEnabled(enableTestDependencies)
                .build())
            .build();
        embeddedTestHelper.getContainer().getDeploymentService().deployApplication(applicationConfiguration);
        assertThat(new File(getAppsFolder(), applicationFolder.getName().replace(".jar", "")).exists(), is(true));
      });
    });

  }

  private void runWithContainer(Consumer<EmbeddedContainer> task) throws Exception {
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
}
