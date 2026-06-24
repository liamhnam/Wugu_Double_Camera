package org.apache.log4j.pattern;

import org.apache.log4j.spi.LoggingEvent;

public final class NDCPatternConverter extends LoggingEventPatternConverter {
    private static final NDCPatternConverter INSTANCE = new NDCPatternConverter();

    private NDCPatternConverter() {
        super("NDC", "ndc");
    }

    public static NDCPatternConverter newInstance(String[] strArr) {
        return INSTANCE;
    }

    @Override
    public void format(LoggingEvent loggingEvent, StringBuffer stringBuffer) {
        stringBuffer.append(loggingEvent.getNDC());
    }
}
