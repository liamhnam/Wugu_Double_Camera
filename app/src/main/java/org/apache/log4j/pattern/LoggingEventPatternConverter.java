package org.apache.log4j.pattern;

import org.apache.log4j.spi.LoggingEvent;

public abstract class LoggingEventPatternConverter extends PatternConverter {
    public abstract void format(LoggingEvent loggingEvent, StringBuffer stringBuffer);

    public boolean handlesThrowable() {
        return false;
    }

    protected LoggingEventPatternConverter(String str, String str2) {
        super(str, str2);
    }

    @Override
    public void format(Object obj, StringBuffer stringBuffer) {
        if (obj instanceof LoggingEvent) {
            format((LoggingEvent) obj, stringBuffer);
        }
    }
}
