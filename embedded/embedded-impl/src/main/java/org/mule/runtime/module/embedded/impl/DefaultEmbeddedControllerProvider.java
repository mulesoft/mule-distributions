/*
 * Copyright (c) MuleSoft, Inc.  All rights reserved.  http://www.mulesoft.com
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
package org.mule.runtime.module.embedded.impl;

import org.mule.runtime.module.embedded.api.ContainerInfo;
import org.mule.runtime.module.embedded.internal.controller.EmbeddedController;
import org.mule.runtime.module.embedded.internal.controller.EmbeddedControllerProvider;

/**
 * Implementation of {@link EmbeddedControllerProvider}.
 */
public class DefaultEmbeddedControllerProvider implements EmbeddedControllerProvider {

  @Override
  public EmbeddedController createEmbeddedController(ContainerInfo containerInfo) {
    return new DefaultEmbeddedController(containerInfo);
  }

}
