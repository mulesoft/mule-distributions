/*
 * Copyright 2023 Salesforce, Inc. All rights reserved.
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
package org.mule.runtime.module.embedded.impl.legacy;

import org.mule.runtime.module.embedded.api.ContainerInfo;
import org.mule.runtime.module.embedded.impl.CommonsEmbeddedController;
import org.mule.runtime.module.embedded.internal.controller.EmbeddedController;

/**
 * Controller class for the runtime. It spins up a new container instance using a temporary folder and dynamically loading the
 * container libraries.
 *
 * @since 4.5
 * @deprecated since 4.6 running with Java 17 the implementation used is
 *             {@link org.mule.runtime.module.embedded.impl.DefaultEmbeddedController}.
 */
@Deprecated
public class DefaultEmbeddedController extends CommonsEmbeddedController implements EmbeddedController {

  public DefaultEmbeddedController(ContainerInfo containerInfo) {
    super(containerInfo);
  }

}
