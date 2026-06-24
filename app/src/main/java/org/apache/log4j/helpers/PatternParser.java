package org.apache.log4j.helpers;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;
import org.apache.log4j.Layout;
import org.apache.log4j.spi.LocationInfo;
import org.apache.log4j.spi.LoggingEvent;

public class PatternParser {
    static final int CLASS_LOCATION_CONVERTER = 1002;
    private static final int CONVERTER_STATE = 1;
    private static final int DOT_STATE = 3;
    private static final char ESCAPE_CHAR = '%';
    static final int FILE_LOCATION_CONVERTER = 1004;
    static final int FULL_LOCATION_CONVERTER = 1000;
    static final int LEVEL_CONVERTER = 2002;
    static final int LINE_LOCATION_CONVERTER = 1003;
    private static final int LITERAL_STATE = 0;
    private static final int MAX_STATE = 5;
    static final int MESSAGE_CONVERTER = 2004;
    static final int METHOD_LOCATION_CONVERTER = 1001;
    private static final int MIN_STATE = 4;
    static final int NDC_CONVERTER = 2003;
    static final int RELATIVE_TIME_CONVERTER = 2000;
    static final int THREAD_CONVERTER = 2001;
    static Class class$java$text$DateFormat;
    PatternConverter head;

    protected int f2789i;
    protected String pattern;
    protected int patternLength;
    PatternConverter tail;
    protected StringBuffer currentLiteral = new StringBuffer(32);
    protected FormattingInfo formattingInfo = new FormattingInfo();
    int state = 0;

    public PatternParser(String str) {
        this.pattern = str;
        this.patternLength = str.length();
    }

    private void addToList(PatternConverter patternConverter) {
        if (this.head == null) {
            this.tail = patternConverter;
            this.head = patternConverter;
        } else {
            this.tail.next = patternConverter;
            this.tail = patternConverter;
        }
    }

    protected String extractOption() {
        int iIndexOf;
        int i;
        int i2 = this.f2789i;
        if (i2 >= this.patternLength || this.pattern.charAt(i2) != '{' || (iIndexOf = this.pattern.indexOf(125, this.f2789i)) <= (i = this.f2789i)) {
            return null;
        }
        String strSubstring = this.pattern.substring(i + 1, iIndexOf);
        this.f2789i = iIndexOf + 1;
        return strSubstring;
    }

    protected int extractPrecisionOption() {
        String strExtractOption = extractOption();
        int i = 0;
        if (strExtractOption == null) {
            return 0;
        }
        try {
            int i2 = Integer.parseInt(strExtractOption);
            if (i2 > 0) {
                return i2;
            }
            try {
                LogLog.error(new StringBuffer("Precision option (").append(strExtractOption).append(") isn't a positive integer.").toString());
                return 0;
            } catch (NumberFormatException e) {
                e = e;
                i = i2;
                LogLog.error(new StringBuffer("Category option \"").append(strExtractOption).append("\" not a decimal integer.").toString(), e);
                return i;
            }
        } catch (NumberFormatException e2) {
            e = e2;
        }
    }

    public PatternConverter parse() throws Throwable {
        this.f2789i = 0;
        while (true) {
            int i = this.f2789i;
            if (i >= this.patternLength) {
                break;
            }
            String str = this.pattern;
            this.f2789i = i + 1;
            char cCharAt = str.charAt(i);
            int i2 = this.state;
            if (i2 == 0) {
                int i3 = this.f2789i;
                if (i3 == this.patternLength) {
                    this.currentLiteral.append(cCharAt);
                } else if (cCharAt == '%') {
                    char cCharAt2 = this.pattern.charAt(i3);
                    if (cCharAt2 == '%') {
                        this.currentLiteral.append(cCharAt);
                        this.f2789i++;
                    } else if (cCharAt2 == 'n') {
                        this.currentLiteral.append(Layout.LINE_SEP);
                        this.f2789i++;
                    } else {
                        if (this.currentLiteral.length() != 0) {
                            addToList(new LiteralPatternConverter(this.currentLiteral.toString()));
                        }
                        this.currentLiteral.setLength(0);
                        this.currentLiteral.append(cCharAt);
                        this.state = 1;
                        this.formattingInfo.reset();
                    }
                } else {
                    this.currentLiteral.append(cCharAt);
                }
            } else if (i2 == 1) {
                this.currentLiteral.append(cCharAt);
                if (cCharAt == '-') {
                    this.formattingInfo.leftAlign = true;
                } else if (cCharAt == '.') {
                    this.state = 3;
                } else if (cCharAt >= '0' && cCharAt <= '9') {
                    this.formattingInfo.min = cCharAt - '0';
                    this.state = 4;
                } else {
                    finalizeConverter(cCharAt);
                }
            } else if (i2 == 3) {
                this.currentLiteral.append(cCharAt);
                if (cCharAt >= '0' && cCharAt <= '9') {
                    this.formattingInfo.max = cCharAt - '0';
                    this.state = 5;
                } else {
                    LogLog.error(new StringBuffer("Error occured in position ").append(this.f2789i).append(".\n Was expecting digit, instead got char \"").append(cCharAt).append("\".").toString());
                    this.state = 0;
                }
            } else if (i2 == 4) {
                this.currentLiteral.append(cCharAt);
                if (cCharAt >= '0' && cCharAt <= '9') {
                    FormattingInfo formattingInfo = this.formattingInfo;
                    formattingInfo.min = (formattingInfo.min * 10) + (cCharAt - '0');
                } else if (cCharAt == '.') {
                    this.state = 3;
                } else {
                    finalizeConverter(cCharAt);
                }
            } else if (i2 == 5) {
                this.currentLiteral.append(cCharAt);
                if (cCharAt >= '0' && cCharAt <= '9') {
                    FormattingInfo formattingInfo2 = this.formattingInfo;
                    formattingInfo2.max = (formattingInfo2.max * 10) + (cCharAt - '0');
                } else {
                    finalizeConverter(cCharAt);
                    this.state = 0;
                }
            }
        }
        if (this.currentLiteral.length() != 0) {
            addToList(new LiteralPatternConverter(this.currentLiteral.toString()));
        }
        return this.head;
    }

    static Class class$(String str) throws Throwable {
        try {
            return Class.forName(str);
        } catch (ClassNotFoundException e) {
            throw new NoClassDefFoundError().initCause(e);
        }
    }

    protected void finalizeConverter(char c) throws Throwable {
        PatternConverter classNamePatternConverter;
        PatternConverter mDCPatternConverter;
        DateFormat simpleDateFormat;
        if (c == 'C') {
            classNamePatternConverter = new ClassNamePatternConverter(this.formattingInfo, extractPrecisionOption());
            this.currentLiteral.setLength(0);
        } else if (c != 'F') {
            if (c == 'X') {
                mDCPatternConverter = new MDCPatternConverter(this.formattingInfo, extractOption());
                this.currentLiteral.setLength(0);
            } else if (c == 'p') {
                classNamePatternConverter = new BasicPatternConverter(this.formattingInfo, LEVEL_CONVERTER);
                this.currentLiteral.setLength(0);
            } else if (c == 'r') {
                classNamePatternConverter = new BasicPatternConverter(this.formattingInfo, 2000);
                this.currentLiteral.setLength(0);
            } else if (c == 't') {
                classNamePatternConverter = new BasicPatternConverter(this.formattingInfo, THREAD_CONVERTER);
                this.currentLiteral.setLength(0);
            } else if (c == 'x') {
                classNamePatternConverter = new BasicPatternConverter(this.formattingInfo, NDC_CONVERTER);
                this.currentLiteral.setLength(0);
            } else if (c == 'L') {
                classNamePatternConverter = new LocationPatternConverter(this.formattingInfo, 1003);
                this.currentLiteral.setLength(0);
            } else if (c == 'M') {
                classNamePatternConverter = new LocationPatternConverter(this.formattingInfo, 1001);
                this.currentLiteral.setLength(0);
            } else if (c == 'c') {
                classNamePatternConverter = new CategoryPatternConverter(this.formattingInfo, extractPrecisionOption());
                this.currentLiteral.setLength(0);
            } else if (c == 'd') {
                String strExtractOption = extractOption();
                if (strExtractOption == null) {
                    strExtractOption = AbsoluteTimeDateFormat.ISO8601_DATE_FORMAT;
                }
                if (strExtractOption.equalsIgnoreCase(AbsoluteTimeDateFormat.ISO8601_DATE_FORMAT)) {
                    simpleDateFormat = new ISO8601DateFormat();
                } else if (strExtractOption.equalsIgnoreCase(AbsoluteTimeDateFormat.ABS_TIME_DATE_FORMAT)) {
                    simpleDateFormat = new AbsoluteTimeDateFormat();
                } else if (strExtractOption.equalsIgnoreCase(AbsoluteTimeDateFormat.DATE_AND_TIME_DATE_FORMAT)) {
                    simpleDateFormat = new DateTimeDateFormat();
                } else {
                    try {
                        simpleDateFormat = new SimpleDateFormat(strExtractOption);
                    } catch (IllegalArgumentException e) {
                        LogLog.error(new StringBuffer("Could not instantiate SimpleDateFormat with ").append(strExtractOption).toString(), e);
                        Class clsClass$ = class$java$text$DateFormat;
                        if (clsClass$ == null) {
                            clsClass$ = class$("java.text.DateFormat");
                            class$java$text$DateFormat = clsClass$;
                        }
                        simpleDateFormat = (DateFormat) OptionConverter.instantiateByClassName("org.apache.log4j.helpers.ISO8601DateFormat", clsClass$, null);
                    }
                }
                mDCPatternConverter = new DatePatternConverter(this.formattingInfo, simpleDateFormat);
                this.currentLiteral.setLength(0);
            } else if (c == 'l') {
                classNamePatternConverter = new LocationPatternConverter(this.formattingInfo, 1000);
                this.currentLiteral.setLength(0);
            } else if (c == 'm') {
                classNamePatternConverter = new BasicPatternConverter(this.formattingInfo, MESSAGE_CONVERTER);
                this.currentLiteral.setLength(0);
            } else {
                LogLog.error(new StringBuffer("Unexpected char [").append(c).append("] at position ").append(this.f2789i).append(" in conversion patterrn.").toString());
                classNamePatternConverter = new LiteralPatternConverter(this.currentLiteral.toString());
                this.currentLiteral.setLength(0);
            }
            classNamePatternConverter = mDCPatternConverter;
        } else {
            classNamePatternConverter = new LocationPatternConverter(this.formattingInfo, 1004);
            this.currentLiteral.setLength(0);
        }
        addConverter(classNamePatternConverter);
    }

    protected void addConverter(PatternConverter patternConverter) {
        this.currentLiteral.setLength(0);
        addToList(patternConverter);
        this.state = 0;
        this.formattingInfo.reset();
    }

    private static class BasicPatternConverter extends PatternConverter {
        int type;

        BasicPatternConverter(FormattingInfo formattingInfo, int i) {
            super(formattingInfo);
            this.type = i;
        }

        @Override
        public String convert(LoggingEvent loggingEvent) {
            switch (this.type) {
                case 2000:
                    return Long.toString(loggingEvent.timeStamp - LoggingEvent.getStartTime());
                case PatternParser.THREAD_CONVERTER:
                    return loggingEvent.getThreadName();
                case PatternParser.LEVEL_CONVERTER:
                    return loggingEvent.getLevel().toString();
                case PatternParser.NDC_CONVERTER:
                    return loggingEvent.getNDC();
                case PatternParser.MESSAGE_CONVERTER:
                    return loggingEvent.getRenderedMessage();
                default:
                    return null;
            }
        }
    }

    private static class LiteralPatternConverter extends PatternConverter {
        private String literal;

        LiteralPatternConverter(String str) {
            this.literal = str;
        }

        @Override
        public final void format(StringBuffer stringBuffer, LoggingEvent loggingEvent) {
            stringBuffer.append(this.literal);
        }

        @Override
        public String convert(LoggingEvent loggingEvent) {
            return this.literal;
        }
    }

    private static class DatePatternConverter extends PatternConverter {
        private Date date;

        private DateFormat f2790df;

        DatePatternConverter(FormattingInfo formattingInfo, DateFormat dateFormat) {
            super(formattingInfo);
            this.date = new Date();
            this.f2790df = dateFormat;
        }

        @Override
        public String convert(LoggingEvent loggingEvent) {
            this.date.setTime(loggingEvent.timeStamp);
            try {
                return this.f2790df.format(this.date);
            } catch (Exception e) {
                LogLog.error("Error occured while converting date.", e);
                return null;
            }
        }
    }

    private static class MDCPatternConverter extends PatternConverter {
        private String key;

        MDCPatternConverter(FormattingInfo formattingInfo, String str) {
            super(formattingInfo);
            this.key = str;
        }

        @Override
        public String convert(LoggingEvent loggingEvent) {
            String str = this.key;
            if (str == null) {
                StringBuffer stringBuffer = new StringBuffer("{");
                Map properties = loggingEvent.getProperties();
                if (properties.size() > 0) {
                    Object[] array = properties.keySet().toArray();
                    Arrays.sort(array);
                    for (int i = 0; i < array.length; i++) {
                        stringBuffer.append('{');
                        stringBuffer.append(array[i]);
                        stringBuffer.append(',');
                        stringBuffer.append(properties.get(array[i]));
                        stringBuffer.append('}');
                    }
                }
                stringBuffer.append('}');
                return stringBuffer.toString();
            }
            Object mdc = loggingEvent.getMDC(str);
            if (mdc == null) {
                return null;
            }
            return mdc.toString();
        }
    }

    private class LocationPatternConverter extends PatternConverter {
        int type;

        LocationPatternConverter(FormattingInfo formattingInfo, int i) {
            super(formattingInfo);
            this.type = i;
        }

        @Override
        public String convert(LoggingEvent loggingEvent) {
            LocationInfo locationInformation = loggingEvent.getLocationInformation();
            switch (this.type) {
                case 1000:
                    return locationInformation.fullInfo;
                case 1001:
                    return locationInformation.getMethodName();
                case 1002:
                default:
                    return null;
                case 1003:
                    return locationInformation.getLineNumber();
                case 1004:
                    return locationInformation.getFileName();
            }
        }
    }

    private static abstract class NamedPatternConverter extends PatternConverter {
        int precision;

        abstract String getFullyQualifiedName(LoggingEvent loggingEvent);

        NamedPatternConverter(FormattingInfo formattingInfo, int i) {
            super(formattingInfo);
            this.precision = i;
        }

        @Override
        public String convert(LoggingEvent loggingEvent) {
            String fullyQualifiedName = getFullyQualifiedName(loggingEvent);
            if (this.precision <= 0) {
                return fullyQualifiedName;
            }
            int length = fullyQualifiedName.length();
            int iLastIndexOf = length - 1;
            for (int i = this.precision; i > 0; i--) {
                iLastIndexOf = fullyQualifiedName.lastIndexOf(46, iLastIndexOf - 1);
                if (iLastIndexOf == -1) {
                    return fullyQualifiedName;
                }
            }
            return fullyQualifiedName.substring(iLastIndexOf + 1, length);
        }
    }

    private class ClassNamePatternConverter extends NamedPatternConverter {
        ClassNamePatternConverter(FormattingInfo formattingInfo, int i) {
            super(formattingInfo, i);
        }

        @Override
        String getFullyQualifiedName(LoggingEvent loggingEvent) {
            return loggingEvent.getLocationInformation().getClassName();
        }
    }

    private class CategoryPatternConverter extends NamedPatternConverter {
        CategoryPatternConverter(FormattingInfo formattingInfo, int i) {
            super(formattingInfo, i);
        }

        @Override
        String getFullyQualifiedName(LoggingEvent loggingEvent) {
            return loggingEvent.getLoggerName();
        }
    }
}
