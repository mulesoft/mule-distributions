/*
 * Copyright (c) MuleSoft, Inc.  All rights reserved.  http://www.mulesoft.com
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
package org.mule.runtime.module.embedded.impl;

import org.mule.runtime.module.embedded.api.EmbeddedController;
import org.mule.runtime.module.embedded.api.EmbeddedControllerProvider;

import java.io.IOException;

/**
 * Implementation of {@link EmbeddedControllerProvider}.
 */
public class EmbeddedControllerProviderImpl implements EmbeddedControllerProvider {

  @Override
  public EmbeddedController createEmbeddedController(byte[] serializedContainerInfo) throws IOException, ClassNotFoundException {
    return new EmbeddedControllerImpl(serializedContainerInfo);
  }

}
