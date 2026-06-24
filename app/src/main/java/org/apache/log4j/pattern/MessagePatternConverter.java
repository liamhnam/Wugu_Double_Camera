package org.apache.log4j.pattern;

import org.apache.log4j.spi.LoggingEvent;

public final class MessagePatternConverter extends LoggingEventPatternConverter {
    private static final MessagePatternConverter INSTANCE = new MessagePatternConverter();

    private MessagePatternConverter() {
        super("Message", "message");
    }

    public static MessagePatternConverter newInstance(String[] strArr) {
        return INSTANCE;
    }

    @Override
    public void format(LoggingEvent loggingEvent, StringBuffer stringBuffer) {
        stringBuffer.append(loggingEvent.getRenderedMessage());
    }
}
