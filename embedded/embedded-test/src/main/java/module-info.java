/*
 * Copyright 2023 Salesforce, Inc. All rights reserved.
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
/**
 * Mule Embedded Implementation Tests.
 *
 * @moduleGraph
 * @since 4.5
 */
module org.mule.distribution.embedded.test {

  requires org.mule.runtime.embedded.api;
  requires org.mule.runtime.maven.client.test;

  requires junit;
  requires org.apache.commons.io;
  requires org.slf4j;
  requires zip4j;

  // This is only needed because the requirement of `org.slf4j` in the boot layer messes with the `log4j` implementation in the
  // class loader created by `mule-embedded-api`, and the fix is to provide it also at the boot layer level.
  // log4j-api
  requires org.apache.logging.log4j;
  // log4j-core
  requires org.apache.logging.log4j.core;
  // log4j-1.2-api
  requires org.apache.log4j;
  // log4j-slf4j2-impl
  requires org.apache.logging.log4j.slf4j;

  exports org.mule.runtime.module.embedded.test.hepler;

}
