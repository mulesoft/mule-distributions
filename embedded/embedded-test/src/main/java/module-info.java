/*
 * Copyright (c) MuleSoft, Inc.  All rights reserved.  http://www.mulesoft.com
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

  // TODO W-13205329 - remove, this is only needed because the requirement of org.slf4j in the boot layer messes with
  //  the log4j implementation in the class loader created by mule-embedded-api, and the fix is to provide it also at
  //  the boot layer level
  requires org.apache.logging.log4j;

  exports org.mule.runtime.module.embedded to
      junit;

}
