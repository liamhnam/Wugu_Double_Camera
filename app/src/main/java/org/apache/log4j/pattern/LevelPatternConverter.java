package org.apache.log4j.pattern;

import org.apache.log4j.spi.LoggingEvent;

public final class LevelPatternConverter extends LoggingEventPatternConverter {
    private static final LevelPatternConverter INSTANCE = new LevelPatternConverter();
    private static final int TRACE_INT = 5000;

    private LevelPatternConverter() {
        super("Level", "level");
    }

    public static LevelPatternConverter newInstance(String[] strArr) {
        return INSTANCE;
    }

    @Override
    public void format(LoggingEvent loggingEvent, StringBuffer stringBuffer) {
        stringBuffer.append(loggingEvent.getLevel().toString());
    }

    @Override
    public String getStyleClass(Object obj) {
        if (!(obj instanceof LoggingEvent)) {
            return "level";
        }
        LoggingEvent loggingEvent = (LoggingEvent) obj;
        int i = loggingEvent.getLevel().toInt();
        return i != 5000 ? i != 10000 ? i != 20000 ? i != 30000 ? i != 40000 ? i != 50000 ? new StringBuffer("level ").append(loggingEvent.getLevel().toString()).toString() : "level fatal" : "level error" : "level warn" : "level info" : "level debug" : "level trace";
    }
}
