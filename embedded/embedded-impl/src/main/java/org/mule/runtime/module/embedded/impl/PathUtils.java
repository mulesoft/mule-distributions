/*
 * Copyright 2023 Salesforce, Inc. All rights reserved.
 */
package org.mule.runtime.module.embedded.impl;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;

public final class PathUtils {

  private PathUtils() {
    // Empty private constructor in order to avoid incorrect instantiations.
  }

  public static String getPath(URL url) throws URISyntaxException {
    return new File(url.toURI()).getPath();
  }
}
