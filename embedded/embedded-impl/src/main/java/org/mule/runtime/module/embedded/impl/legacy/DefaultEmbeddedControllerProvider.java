/*
 * Copyright 2023 Salesforce, Inc. All rights reserved.
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
package org.mule.runtime.module.embedded.impl.legacy;

import org.mule.runtime.module.embedded.api.ContainerInfo;
import org.mule.runtime.module.embedded.internal.controller.EmbeddedController;
import org.mule.runtime.module.embedded.internal.controller.EmbeddedControllerProvider;

/**
 * Implementation of {@link EmbeddedControllerProvider}.
 *
 * @since 4.5
 * @deprecated since 4.6 the implementation used is
 *             {@link org.mule.runtime.module.embedded.impl.DefaultEmbeddedControllerProvider}.
 */
public class DefaultEmbeddedControllerProvider implements EmbeddedControllerProvider {

  @Override
  public EmbeddedController createEmbeddedController(ContainerInfo containerInfo) {
    return new DefaultEmbeddedController(containerInfo);
  }

}
