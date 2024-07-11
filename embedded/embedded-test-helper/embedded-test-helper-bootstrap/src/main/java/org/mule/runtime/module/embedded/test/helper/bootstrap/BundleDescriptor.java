/*
 * Copyright 2023 Salesforce, Inc. All rights reserved.
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
package org.mule.runtime.module.embedded.test.helper.bootstrap;

import static java.util.Optional.ofNullable;

import java.util.Optional;

public class BundleDescriptor {

  private final String artifactId;
  private final String classifier;
  private final boolean isApplication;

  BundleDescriptor(String artifactId, String classifier, boolean isApplication) {
    this.artifactId = artifactId;
    this.classifier = classifier;
    this.isApplication = isApplication;
  }

  public static BundleDescriptor forApplication(String artifactId) {
    return new BundleDescriptor(artifactId, null, true);
  }

  public static BundleDescriptor forApplication(String artifactId, String classifier) {
    return new BundleDescriptor(artifactId, classifier, true);
  }

  public static BundleDescriptor forDomain(String artifactId) {
    return new BundleDescriptor(artifactId, null, false);
  }

  public boolean isApplication() {
    return isApplication;
  }

  public String getArtifactId() {
    return artifactId;
  }

  public Optional<String> getClassifier() {
    return ofNullable(classifier);
  }

}
