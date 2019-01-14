/*
 * Copyright (c) MuleSoft, Inc.  All rights reserved.  http://www.mulesoft.com
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
package com.mycompany.log4j.logger;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.Core;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.appender.rewrite.RewritePolicy;
import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.core.config.plugins.PluginFactory;
import org.apache.logging.log4j.core.impl.ContextDataFactory;
import org.apache.logging.log4j.core.impl.Log4jLogEvent;
import org.apache.logging.log4j.message.SimpleMessage;
import org.apache.logging.log4j.status.StatusLogger;
import org.apache.logging.log4j.util.StringMap;

@Plugin(name = "CustomLogInterceptor", category = Core.CATEGORY_NAME, elementType = "rewritePolicy", printObject = true)
public final class CustomLogInterceptor implements RewritePolicy {
  
  @Override
  public LogEvent rewrite(final LogEvent event) {
    Log4jLogEvent.Builder builder = new Log4jLogEvent.Builder();
    builder.setLoggerName(event.getLoggerName());
    builder.setMarker(event.getMarker());
    builder.setLoggerFqcn(event.getLoggerFqcn());
    builder.setLevel(event.getLevel());
    builder.setMessage(new SimpleMessage("I have intercepted your message :)"));
    builder.setThrown(event.getThrown());
    builder.setContextStack(event.getContextStack());
    builder.setThreadName(event.getThreadName());
    builder.setSource(event.getSource());
    builder.setTimeMillis(event.getTimeMillis());
    return builder.build();
  }

  @PluginFactory
  public static CustomLogInterceptor createPolicy() {
    return new CustomLogInterceptor();
  }

}
