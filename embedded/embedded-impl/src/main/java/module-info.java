/*
 * Copyright (c) MuleSoft, Inc.  All rights reserved.  http://www.mulesoft.com
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */

import org.mule.runtime.module.embedded.impl.EmbeddedControllerProviderImpl;

/**
 * Mule Embedded Implementation.
 *
 * @moduleGraph
 * @since 4.5
 */
module org.mule.distribution.embedded.impl {

  requires transitive org.mule.runtime.launcher;
  requires transitive org.mule.runtime.api;

  requires org.mule.runtime.core;
  requires org.mule.runtime.artifact;
  requires org.mule.runtime.container;
  requires org.mule.runtime.deployment;
  requires org.mule.runtime.deployment.model;
  requires org.mule.runtime.embedded.api;

  requires org.apache.commons.io;
  requires zip4j;

  provides org.mule.runtime.module.embedded.api.EmbeddedControllerProvider
      with EmbeddedControllerProviderImpl;

}
