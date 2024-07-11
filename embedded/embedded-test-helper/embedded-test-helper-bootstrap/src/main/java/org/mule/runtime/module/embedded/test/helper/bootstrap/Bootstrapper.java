/*
 * Copyright 2023 Salesforce, Inc. All rights reserved.
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
package org.mule.runtime.module.embedded.test.helper.bootstrap;

import static org.mule.runtime.module.embedded.test.helper.bootstrap.util.DependencyResolver.getLibs;

import static java.lang.System.getProperty;

import java.nio.file.Path;
import java.nio.file.Paths;

public class Bootstrapper {

  private static final Path outputDirectory = Paths.get(getProperty("user.dir"));

  static Class<?> getHelperClass() throws Exception {
    getLibs("", outputDirectory);
    return null;
  }

}
