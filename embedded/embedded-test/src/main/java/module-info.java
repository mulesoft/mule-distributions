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

  exports org.mule.runtime.module.embedded to
      junit;

}
