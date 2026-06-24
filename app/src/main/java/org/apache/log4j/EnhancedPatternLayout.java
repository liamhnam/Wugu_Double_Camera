package org.apache.log4j;

import org.apache.log4j.helpers.OptionConverter;
import org.apache.log4j.helpers.PatternConverter;
import org.apache.log4j.helpers.PatternParser;
import org.apache.log4j.pattern.BridgePatternConverter;
import org.apache.log4j.pattern.BridgePatternParser;
import org.apache.log4j.spi.LoggingEvent;

public class EnhancedPatternLayout extends Layout {
    public static final String DEFAULT_CONVERSION_PATTERN = "%m%n";
    public static final String PATTERN_RULE_REGISTRY = "PATTERN_RULE_REGISTRY";
    public static final String TTCC_CONVERSION_PATTERN = "%r [%t] %p %c %x - %m%n";
    protected final int BUF_SIZE;
    protected final int MAX_CAPACITY;
    private String conversionPattern;
    private boolean handlesExceptions;
    private PatternConverter head;

    @Override
    public void activateOptions() {
    }

    public EnhancedPatternLayout() {
        this("%m%n");
    }

    public EnhancedPatternLayout(String str) throws Throwable {
        this.BUF_SIZE = 256;
        this.MAX_CAPACITY = 1024;
        this.conversionPattern = str;
        PatternConverter patternConverter = createPatternParser(str == null ? "%m%n" : str).parse();
        this.head = patternConverter;
        if (patternConverter instanceof BridgePatternConverter) {
            this.handlesExceptions = !((BridgePatternConverter) patternConverter).ignoresThrowable();
        } else {
            this.handlesExceptions = false;
        }
    }

    public void setConversionPattern(String str) throws Throwable {
        String strConvertSpecialChars = OptionConverter.convertSpecialChars(str);
        this.conversionPattern = strConvertSpecialChars;
        PatternConverter patternConverter = createPatternParser(strConvertSpecialChars).parse();
        this.head = patternConverter;
        if (patternConverter instanceof BridgePatternConverter) {
            this.handlesExceptions = !((BridgePatternConverter) patternConverter).ignoresThrowable();
        } else {
            this.handlesExceptions = false;
        }
    }

    public String getConversionPattern() {
        return this.conversionPattern;
    }

    protected PatternParser createPatternParser(String str) {
        return new BridgePatternParser(str);
    }

    @Override
    public String format(LoggingEvent loggingEvent) {
        StringBuffer stringBuffer = new StringBuffer();
        for (PatternConverter patternConverter = this.head; patternConverter != null; patternConverter = patternConverter.next) {
            patternConverter.format(stringBuffer, loggingEvent);
        }
        return stringBuffer.toString();
    }

    @Override
    public boolean ignoresThrowable() {
        return !this.handlesExceptions;
    }
}
