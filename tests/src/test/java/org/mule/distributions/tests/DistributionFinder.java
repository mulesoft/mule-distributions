/*
 * Copyright (c) MuleSoft, Inc.  All rights reserved.  http://www.mulesoft.com
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
package org.mule.distributions.tests;

import static java.lang.String.format;
import static java.lang.System.getProperty;

import java.io.File;
import java.nio.file.Paths;

/**
 * Helper class that tries to discover the mule distribution zip file in the local environment
 */
public class DistributionFinder {

  private static final String MULE_DISTRIBUTION_PROPERTY = "mule.distribution";

  /**
   * @return a mule distribution location if one was found.
   */
  public static String findDistribution() {
    String muleDistribution = getProperty(MULE_DISTRIBUTION_PROPERTY);
    if (muleDistribution != null) {
      return muleDistribution;
    }
    File distributionTargetFolder =
        Paths.get(new File(getProperty("user.dir")).getParent()).resolve("standalone").resolve("target").toFile();
    if (distributionTargetFolder.exists()) {
      File[] files = distributionTargetFolder
          .listFiles((dir, name) -> name.startsWith("mule-enterprise-standalone-") && name.endsWith(".zip"));
      if (files.length > 0) {
        return files[0].getAbsolutePath();
      }
    }
    throw new IllegalStateException(format("No %s system property could be found and the standalone EE distribution seems it was not build",
                                           MULE_DISTRIBUTION_PROPERTY));
  }

}
