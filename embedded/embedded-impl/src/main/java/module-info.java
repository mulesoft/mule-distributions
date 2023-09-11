/*
 * Copyright 2023 Salesforce, Inc. All rights reserved.
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
  requires org.mule.runtime.embedded.api;

  requires org.apache.commons.io;
  requires zip4j;

  provides org.mule.runtime.module.embedded.internal.controller.EmbeddedControllerProvider
      with org.mule.runtime.module.embedded.impl.DefaultEmbeddedControllerProvider;

}
