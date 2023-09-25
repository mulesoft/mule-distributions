/*
 * Copyright 2023 Salesforce, Inc. All rights reserved.
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */

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
  // TODO W-14145969 Remove when launcher module is properly modularized
  requires org.mule.runtime.deployment.model.impl;
  requires org.mule.runtime.log4j;

  requires org.mule.runtime.embedded.api;

  requires org.apache.commons.io;
  requires zip4j;

  provides org.mule.runtime.module.embedded.api.controller.EmbeddedControllerProvider
      with org.mule.runtime.module.embedded.impl.DefaultEmbeddedControllerProvider;

}
