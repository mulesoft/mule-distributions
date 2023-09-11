/*
 * Copyright 2023 Salesforce, Inc. All rights reserved.
 */
package org.mule.test;

import java.io.InputStream;

public class ResourceGetter {

  public static InputStream getJdkResource() {
    return Thread.currentThread().getContextClassLoader().getResourceAsStream("META-INF/MANIFEST.MF");
  }
}
