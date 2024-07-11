/*
 * Copyright 2023 Salesforce, Inc. All rights reserved.
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
package org.mule.runtime.module.embedded.test.helper.bootstrap;

import static org.mule.runtime.module.embedded.test.helper.bootstrap.Bootstrapper.getHelperClass;

import java.util.function.Consumer;

public class EmbeddedTestHelper {

  private final boolean enterprise;
  private final boolean forceUpdateSnapshots;
  private final boolean ignoreArtifactDescriptorRepositories;
  private Object embeddedTestHelper;

  public EmbeddedTestHelper(boolean enterprise, boolean forceUpdateSnapshots, boolean ignoreArtifactDescriptorRepositories) {
    this.enterprise = enterprise;
    this.forceUpdateSnapshots = forceUpdateSnapshots;
    this.ignoreArtifactDescriptorRepositories = ignoreArtifactDescriptorRepositories;
  }

  public void init() {
    try {
      embeddedTestHelper = getHelperClass().getDeclaredConstructor().newInstance(enterprise, forceUpdateSnapshots, ignoreArtifactDescriptorRepositories);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }

  }


  public Consumer<Integer> createRetryTestOperation(Consumer<Integer> originalTestOperation) {
    return null;
  }

  public void executeWithRetry(Runnable runnable) {

  }

}
