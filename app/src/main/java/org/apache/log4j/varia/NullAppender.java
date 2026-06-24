package org.apache.log4j.varia;

import org.apache.log4j.AppenderSkeleton;
import org.apache.log4j.spi.LoggingEvent;

public class NullAppender extends AppenderSkeleton {
    private static NullAppender instance = new NullAppender();

    @Override
    public void activateOptions() {
    }

    @Override
    protected void append(LoggingEvent loggingEvent) {
    }

    @Override
    public void close() {
    }

    @Override
    public void doAppend(LoggingEvent loggingEvent) {
    }

    @Override
    public boolean requiresLayout() {
        return false;
    }

    public NullAppender getInstance() {
        return instance;
    }

    public static NullAppender getNullAppender() {
        return instance;
    }
}
