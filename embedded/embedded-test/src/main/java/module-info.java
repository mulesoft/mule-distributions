/*
 * Copyright 2023 Salesforce, Inc. All rights reserved.
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
