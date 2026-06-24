package org.apache.log4j.pattern;

import org.apache.log4j.Layout;
import org.apache.log4j.spi.LoggingEvent;

public final class LineSeparatorPatternConverter extends LoggingEventPatternConverter {
    private static final LineSeparatorPatternConverter INSTANCE = new LineSeparatorPatternConverter();
    private final String lineSep;

    private LineSeparatorPatternConverter() {
        super("Line Sep", "lineSep");
        this.lineSep = Layout.LINE_SEP;
    }

    public static LineSeparatorPatternConverter newInstance(String[] strArr) {
        return INSTANCE;
    }

    @Override
    public void format(LoggingEvent loggingEvent, StringBuffer stringBuffer) {
        stringBuffer.append(this.lineSep);
    }

    @Override
    public void format(Object obj, StringBuffer stringBuffer) {
        stringBuffer.append(this.lineSep);
    }
}
