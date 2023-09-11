/*
 * Copyright 2023 Salesforce, Inc. All rights reserved.
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
