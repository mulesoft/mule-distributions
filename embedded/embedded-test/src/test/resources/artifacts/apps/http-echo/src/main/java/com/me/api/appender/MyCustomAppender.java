package com.me.api.appender;

import org.apache.logging.log4j.core.Filter;
import org.apache.logging.log4j.core.Layout;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.appender.AbstractAppender;
import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.core.config.plugins.PluginAttribute;
import org.apache.logging.log4j.core.config.plugins.PluginElement;
import org.apache.logging.log4j.core.config.plugins.PluginFactory;
import org.apache.logging.log4j.core.layout.PatternLayout;

import java.io.Serializable;

@Plugin(name = "MyCustomAppender", category = "Core", elementType = "appender", printObject = true)
public class MyCustomAppender extends AbstractAppender {

    protected MyCustomAppender(String name, Filter filter, Layout<? extends Serializable> layout) {
        super(name, filter, layout);

        System.out.printf("XXX MyCustomAppender constructor");
    }

    @PluginFactory
    public static MyCustomAppender createAppender(
            @PluginAttribute("name") String name,
            @PluginElement("Filter") Filter filter,
            @PluginElement("Layout") Layout<? extends Serializable> layout) {

        if (layout == null) {
            layout = PatternLayout.createDefaultLayout();
        }

        return new MyCustomAppender(name, filter, layout);
    }

    public void append(LogEvent logEvent) {
        System.out.printf("XXX %s", logEvent.getMessage().getFormattedMessage());
    }
}
