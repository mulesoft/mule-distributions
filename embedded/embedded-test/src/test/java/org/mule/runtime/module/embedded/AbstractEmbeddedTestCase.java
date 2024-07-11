/*
 * Copyright 2023 Salesforce, Inc. All rights reserved.
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
package org.mule.runtime.module.embedded;

import static java.lang.String.valueOf;
import static java.nio.file.Paths.get;
import static java.util.Optional.empty;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.rules.Timeout.millis;

import org.mule.runtime.module.embedded.test.hepler.bootstrap.EmbeddedTestHelper;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.Properties;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.rules.DisableOnDebug;
import org.junit.rules.TestRule;


public abstract class AbstractEmbeddedTestCase {

  protected static EmbeddedTestHelper embeddedTestHelper;

  private static final int TIMEOUT_SECS = 1200;

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

  protected static EmbeddedTestHelper getEmbeddedTestHelper() {
    return embeddedTestHelper;
  }

  @Rule
  public final TestRule timeoutRule = new DisableOnDebug(millis(TIMEOUT_SECS * 1000));

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

  private void doWithinApplication(String artifactId, String appFolder, Consumer<Integer> retryTestOperation) {
    doWithinArtifact(artifactId, appFolder, APPS_FOLDER, retryTestOperation);
  }

  private void doWithinDomain(String artifactId, String appFolder, Consumer<Integer> retryTestOperation) {
    doWithinArtifact(artifactId, appFolder, DOMAINS_FOLDER, retryTestOperation);
  }

  private void doWithinArtifact(String artifactId, String artifactFolder, String artifactDeploymentFolder,
                                Consumer<Integer> retryTestOperation) {
    embeddedTestHelper.doWithinApplication(artifactId, artifactFolder, artifactDeploymentFolder, retryTestOperation);
  }

  protected String getAppFolder(String appName) {
    try {
      return get(this.getClass().getClassLoader().getResource(ARTIFACTS_FOLDER + "/" + APPS_FOLDER + "/" + appName).toURI())
          .toFile().getAbsolutePath();
    } catch (URISyntaxException e) {
      throw new RuntimeException(e);
    }
  }

  // protected void validateDomainIsDeployed(EmbeddedContainer embeddedContainer, File domainFile) {
  // validateArtifactState(DOMAINS_FOLDER, domainFile, embeddedContainer, true);
  // }
  //
  // protected void validateDomainIsUndeployed(EmbeddedContainer embeddedContainer, File domainFile) {
  // validateArtifactState(DOMAINS_FOLDER, domainFile, embeddedContainer, false);
  // }

}
