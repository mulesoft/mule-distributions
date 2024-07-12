/*
 * Copyright 2023 Salesforce, Inc. All rights reserved.
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */


/**
 * Mule Embedded Implementation Tests.
 *
 * @since 4.5
 */
module org.mule.distribution.embedded.test {

  // This is only needed because some Runtime libs require log4j, but we're no longer providing it since `W-15522743`,
  // so we have to put it at the boot layer level.
  // log4j-api
  requires org.apache.logging.log4j;
  // log4j-core
  requires org.apache.logging.log4j.core;
  // log4j-1.2-api
  requires org.apache.log4j;
  // log4j-slf4j2-impl
  requires org.apache.logging.log4j.slf4j2.impl;

}
