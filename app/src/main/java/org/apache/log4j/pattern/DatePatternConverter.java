package org.apache.log4j.pattern;

import java.text.DateFormat;
import java.text.FieldPosition;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import org.apache.log4j.helpers.LogLog;
import org.apache.log4j.spi.LoggingEvent;

public final class DatePatternConverter extends LoggingEventPatternConverter {
    private static final String ABSOLUTE_FORMAT = "ABSOLUTE";
    private static final String ABSOLUTE_TIME_PATTERN = "HH:mm:ss,SSS";
    private static final String DATE_AND_TIME_FORMAT = "DATE";
    private static final String DATE_AND_TIME_PATTERN = "dd MMM yyyy HH:mm:ss,SSS";
    private static final String ISO8601_FORMAT = "ISO8601";
    private static final String ISO8601_PATTERN = "yyyy-MM-dd HH:mm:ss,SSS";

    private final CachedDateFormat f2798df;

    private static class DefaultZoneDateFormat extends DateFormat {
        private static final long serialVersionUID = 1;
        private final DateFormat dateFormat;

        public DefaultZoneDateFormat(DateFormat dateFormat) {
            this.dateFormat = dateFormat;
        }

        @Override
        public StringBuffer format(Date date, StringBuffer stringBuffer, FieldPosition fieldPosition) {
            this.dateFormat.setTimeZone(TimeZone.getDefault());
            return this.dateFormat.format(date, stringBuffer, fieldPosition);
        }

        @Override
        public Date parse(String str, ParsePosition parsePosition) {
            this.dateFormat.setTimeZone(TimeZone.getDefault());
            return this.dateFormat.parse(str, parsePosition);
        }
    }

    private DatePatternConverter(String[] strArr) {
        String str;
        DateFormat simpleDateFormat;
        int maximumCacheValidity;
        super("Date", "date");
        String str2 = (strArr == null || strArr.length == 0) ? null : strArr[0];
        if (str2 == null || str2.equalsIgnoreCase("ISO8601")) {
            str = ISO8601_PATTERN;
        } else if (str2.equalsIgnoreCase("ABSOLUTE")) {
            str = ABSOLUTE_TIME_PATTERN;
        } else {
            str = str2.equalsIgnoreCase("DATE") ? DATE_AND_TIME_PATTERN : str2;
        }
        try {
            simpleDateFormat = new SimpleDateFormat(str);
            maximumCacheValidity = CachedDateFormat.getMaximumCacheValidity(str);
        } catch (IllegalArgumentException e) {
            LogLog.warn(new StringBuffer("Could not instantiate SimpleDateFormat with pattern ").append(str2).toString(), e);
            simpleDateFormat = new SimpleDateFormat(ISO8601_PATTERN);
            maximumCacheValidity = 1000;
        }
        if (strArr != null && strArr.length > 1) {
            simpleDateFormat.setTimeZone(TimeZone.getTimeZone(strArr[1]));
        } else {
            simpleDateFormat = new DefaultZoneDateFormat(simpleDateFormat);
        }
        this.f2798df = new CachedDateFormat(simpleDateFormat, maximumCacheValidity);
    }

    public static DatePatternConverter newInstance(String[] strArr) {
        return new DatePatternConverter(strArr);
    }

    @Override
    public void format(LoggingEvent loggingEvent, StringBuffer stringBuffer) {
        synchronized (this) {
            this.f2798df.format(loggingEvent.timeStamp, stringBuffer);
        }
    }

    @Override
    public void format(Object obj, StringBuffer stringBuffer) {
        if (obj instanceof Date) {
            format((Date) obj, stringBuffer);
        }
        super.format(obj, stringBuffer);
    }

    public void format(Date date, StringBuffer stringBuffer) {
        synchronized (this) {
            this.f2798df.format(date.getTime(), stringBuffer);
        }
    }
}
