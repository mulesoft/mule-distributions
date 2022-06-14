/*
 * Copyright (c) MuleSoft, Inc.  All rights reserved.  http://www.mulesoft.com
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
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
