/*
 * Copyright (c) MuleSoft, Inc.  All rights reserved.  http://www.mulesoft.com
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
package org.mule.test;

import java.io.InputStream;

public class ResourceGetter {

  public static InputStream getJdkResource() {
    return Thread.currentThread().getContextClassLoader().getResourceAsStream("META-INF/MANIFEST.MF");
  }
}
