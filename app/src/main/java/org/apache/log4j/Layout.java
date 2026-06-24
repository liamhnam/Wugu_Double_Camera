package org.apache.log4j;

import org.apache.http.protocol.HTTP;
import org.apache.log4j.spi.LoggingEvent;
import org.apache.log4j.spi.OptionHandler;

public abstract class Layout implements OptionHandler {
    public static final String LINE_SEP;
    public static final int LINE_SEP_LEN;

    public abstract String format(LoggingEvent loggingEvent);

    public String getContentType() {
        return HTTP.PLAIN_TEXT_TYPE;
    }

    public String getFooter() {
        return null;
    }

    public String getHeader() {
        return null;
    }

    public abstract boolean ignoresThrowable();

    static {
        String property = System.getProperty("line.separator");
        LINE_SEP = property;
        LINE_SEP_LEN = property.length();
    }
}
