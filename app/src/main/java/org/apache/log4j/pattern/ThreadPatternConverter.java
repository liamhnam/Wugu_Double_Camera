package org.apache.log4j.pattern;

import com.tom_roush.pdfbox.pdmodel.interactive.action.PDActionThread;
import org.apache.log4j.spi.LoggingEvent;

public class ThreadPatternConverter extends LoggingEventPatternConverter {
    private static final ThreadPatternConverter INSTANCE = new ThreadPatternConverter();

    private ThreadPatternConverter() {
        super(PDActionThread.SUB_TYPE, "thread");
    }

    public static ThreadPatternConverter newInstance(String[] strArr) {
        return INSTANCE;
    }

    @Override
    public void format(LoggingEvent loggingEvent, StringBuffer stringBuffer) {
        stringBuffer.append(loggingEvent.getThreadName());
    }
}
