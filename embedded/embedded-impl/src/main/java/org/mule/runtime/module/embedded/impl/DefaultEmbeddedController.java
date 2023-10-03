/*
 * Copyright 2023 Salesforce, Inc. All rights reserved.
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
package org.mule.runtime.module.embedded.impl;

import org.mule.runtime.module.embedded.api.ContainerInfo;
import org.mule.runtime.module.embedded.internal.controller.EmbeddedController;

/**
 * Controller class for the runtime. It spins up a new container instance using a temporary folder and dynamically loading the
 * container libraries.
 *
 * @since 4.5
 * @deprecated moved to Java 8 MRJAR since 4.6 to avoid exporting an internal package in {@code mule-embedded-api}.
 */
@Deprecated
public class DefaultEmbeddedController extends CommonEmbeddedController implements EmbeddedController {

  public DefaultEmbeddedController(ContainerInfo containerInfo) {
    super(containerInfo);
  }

}
