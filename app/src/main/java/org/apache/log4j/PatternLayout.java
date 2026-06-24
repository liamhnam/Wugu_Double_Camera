package org.apache.log4j;

import org.apache.log4j.helpers.PatternConverter;
import org.apache.log4j.helpers.PatternParser;
import org.apache.log4j.spi.LoggingEvent;

public class PatternLayout extends Layout {
    public static final String DEFAULT_CONVERSION_PATTERN = "%m%n";
    public static final String TTCC_CONVERSION_PATTERN = "%r [%t] %p %c %x - %m%n";
    protected final int BUF_SIZE;
    protected final int MAX_CAPACITY;
    private PatternConverter head;
    private String pattern;
    private StringBuffer sbuf;

    @Override
    public void activateOptions() {
    }

    @Override
    public boolean ignoresThrowable() {
        return true;
    }

    public PatternLayout() {
        this("%m%n");
    }

    public PatternLayout(String str) {
        this.BUF_SIZE = 256;
        this.MAX_CAPACITY = 1024;
        this.sbuf = new StringBuffer(256);
        this.pattern = str;
        this.head = createPatternParser(str == null ? "%m%n" : str).parse();
    }

    public void setConversionPattern(String str) {
        this.pattern = str;
        this.head = createPatternParser(str).parse();
    }

    public String getConversionPattern() {
        return this.pattern;
    }

    protected PatternParser createPatternParser(String str) {
        return new PatternParser(str);
    }

    @Override
    public String format(LoggingEvent loggingEvent) {
        if (this.sbuf.capacity() > 1024) {
            this.sbuf = new StringBuffer(256);
        } else {
            this.sbuf.setLength(0);
        }
        for (PatternConverter patternConverter = this.head; patternConverter != null; patternConverter = patternConverter.next) {
            patternConverter.format(this.sbuf, loggingEvent);
        }
        return this.sbuf.toString();
    }
}
