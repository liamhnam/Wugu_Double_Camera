package com.tom_roush.pdfbox.util;

import com.tom_roush.pdfbox.cos.COSString;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.SimpleTimeZone;
import java.util.TimeZone;

public final class DateConverter {
    private static final int DAY = 86400000;
    private static final int HALF_DAY = 43200000;
    private static final int MILLIS_PER_HOUR = 3600000;
    private static final int MILLIS_PER_MINUTE = 60000;
    private static final int MINUTES_PER_HOUR = 60;
    private static final int SECONDS_PER_MINUTE = 60;
    private static final String[] ALPHA_START_FORMATS = {"EEEE, dd MMM yy hh:mm:ss a", "EEEE, MMM dd, yy hh:mm:ss a", "EEEE, MMM dd, yy 'at' hh:mma", "EEEE, MMM dd, yy", "EEEE MMM dd, yy HH:mm:ss", "EEEE MMM dd HH:mm:ss z yy", "EEEE MMM dd HH:mm:ss yy"};
    private static final String[] DIGIT_START_FORMATS = {"dd MMM yy HH:mm:ss", "dd MMM yy HH:mm", "yyyy MMM d", "yyyymmddhh:mm:ss", "H:m M/d/yy", "M/d/yy HH:mm:ss", "M/d/yy HH:mm", "M/d/yy"};

    private DateConverter() {
    }

    public static String toString(Calendar calendar) {
        if (calendar == null) {
            return null;
        }
        return String.format(Locale.US, "D:%1$4tY%1$2tm%1$2td%1$2tH%1$2tM%1$2tS%2$s'", calendar, formatTZoffset(calendar.get(15) + calendar.get(16), "'"));
    }

    public static String toISO8601(Calendar calendar) {
        return String.format(Locale.US, "%1$4tY-%1$2tm-%1$2tdT%1$2tH:%1$2tM:%1$2tS%2$s", calendar, formatTZoffset(calendar.get(15) + calendar.get(16), ":"));
    }

    private static int restrainTZoffset(long j) {
        return (int) ((((((j + 43200000) % 86400000) + 86400000) % 86400000) - 43200000) % 43200000);
    }

    static String formatTZoffset(long j, String str) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("Z");
        simpleDateFormat.setTimeZone(new SimpleTimeZone(restrainTZoffset(j), "unknown"));
        String str2 = simpleDateFormat.format(new Date());
        return str2.substring(0, 3) + str + str2.substring(3);
    }

    private static int parseTimeField(String str, ParsePosition parsePosition, int i, int i2) {
        if (str == null) {
            return i2;
        }
        int index = parsePosition.getIndex();
        int iMin = Math.min(i, str.length() - index) + index;
        int i3 = 0;
        while (index < iMin) {
            int iCharAt = str.charAt(index) - '0';
            if (iCharAt < 0 || iCharAt > 9) {
                break;
            }
            i3 = (i3 * 10) + iCharAt;
            index++;
        }
        if (index == parsePosition.getIndex()) {
            return i2;
        }
        parsePosition.setIndex(index);
        return i3;
    }

    private static char skipOptionals(String str, ParsePosition parsePosition, String str2) {
        char c = ' ';
        while (str != null && parsePosition.getIndex() < str.length()) {
            char cCharAt = str.charAt(parsePosition.getIndex());
            if (str2.indexOf(cCharAt) < 0) {
                break;
            }
            if (cCharAt != ' ') {
                c = cCharAt;
            }
            parsePosition.setIndex(parsePosition.getIndex() + 1);
        }
        return c;
    }

    private static boolean skipString(String str, String str2, ParsePosition parsePosition) {
        if (!str.startsWith(str2, parsePosition.getIndex())) {
            return false;
        }
        parsePosition.setIndex(parsePosition.getIndex() + str2.length());
        return true;
    }

    static GregorianCalendar newGreg() {
        GregorianCalendar gregorianCalendar = new GregorianCalendar(Locale.ENGLISH);
        gregorianCalendar.setTimeZone(new SimpleTimeZone(0, "UTC"));
        gregorianCalendar.setLenient(false);
        gregorianCalendar.set(14, 0);
        return gregorianCalendar;
    }

    private static void adjustTimeZoneNicely(GregorianCalendar gregorianCalendar, TimeZone timeZone) {
        gregorianCalendar.setTimeZone(timeZone);
        gregorianCalendar.add(12, -((gregorianCalendar.get(15) + gregorianCalendar.get(16)) / MILLIS_PER_MINUTE));
    }

    static boolean parseTZoffset(String str, GregorianCalendar gregorianCalendar, ParsePosition parsePosition) {
        ParsePosition parsePosition2 = new ParsePosition(parsePosition.getIndex());
        TimeZone simpleTimeZone = new SimpleTimeZone(0, "GMT");
        char cSkipOptionals = skipOptionals(str, parsePosition2, "Z+- ");
        boolean z = cSkipOptionals == 'Z' || skipString(str, "GMT", parsePosition2) || skipString(str, "UTC", parsePosition2);
        if (z) {
            cSkipOptionals = skipOptionals(str, parsePosition2, "+- ");
        }
        int timeField = parseTimeField(str, parsePosition2, 2, -999);
        skipOptionals(str, parsePosition2, "': ");
        int timeField2 = parseTimeField(str, parsePosition2, 2, 0);
        skipOptionals(str, parsePosition2, "' ");
        if (timeField != -999) {
            simpleTimeZone.setRawOffset(restrainTZoffset(((long) (cSkipOptionals == '-' ? -1 : 1)) * (((long) (timeField * MILLIS_PER_HOUR)) + (((long) timeField2) * 60000))));
            simpleTimeZone.setID("unknown");
        } else if (!z) {
            simpleTimeZone = TimeZone.getTimeZone(str.substring(parsePosition.getIndex()).trim());
            if ("GMT".equals(simpleTimeZone.getID())) {
                return false;
            }
            parsePosition2.setIndex(str.length());
        }
        adjustTimeZoneNicely(gregorianCalendar, simpleTimeZone);
        parsePosition.setIndex(parsePosition2.getIndex());
        return true;
    }

    private static GregorianCalendar parseBigEndianDate(String str, ParsePosition parsePosition) {
        ParsePosition parsePosition2 = new ParsePosition(parsePosition.getIndex());
        int timeField = parseTimeField(str, parsePosition2, 4, 0);
        if (parsePosition2.getIndex() != parsePosition.getIndex() + 4) {
            return null;
        }
        skipOptionals(str, parsePosition2, "/- ");
        int timeField2 = parseTimeField(str, parsePosition2, 2, 1) - 1;
        skipOptionals(str, parsePosition2, "/- ");
        int timeField3 = parseTimeField(str, parsePosition2, 2, 1);
        skipOptionals(str, parsePosition2, " T");
        int timeField4 = parseTimeField(str, parsePosition2, 2, 0);
        skipOptionals(str, parsePosition2, ": ");
        int timeField5 = parseTimeField(str, parsePosition2, 2, 0);
        skipOptionals(str, parsePosition2, ": ");
        int timeField6 = parseTimeField(str, parsePosition2, 2, 0);
        if (skipOptionals(str, parsePosition2, ".") == '.') {
            parseTimeField(str, parsePosition2, 19, 0);
        }
        GregorianCalendar gregorianCalendarNewGreg = newGreg();
        try {
            gregorianCalendarNewGreg.set(timeField, timeField2, timeField3, timeField4, timeField5, timeField6);
            gregorianCalendarNewGreg.getTimeInMillis();
            parsePosition.setIndex(parsePosition2.getIndex());
            skipOptionals(str, parsePosition, " ");
            return gregorianCalendarNewGreg;
        } catch (IllegalArgumentException unused) {
            return null;
        }
    }

    private static GregorianCalendar parseSimpleDate(String str, String[] strArr, ParsePosition parsePosition) {
        for (String str2 : strArr) {
            ParsePosition parsePosition2 = new ParsePosition(parsePosition.getIndex());
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(str2, Locale.ENGLISH);
            GregorianCalendar gregorianCalendarNewGreg = newGreg();
            simpleDateFormat.setCalendar(gregorianCalendarNewGreg);
            if (simpleDateFormat.parse(str, parsePosition2) != null) {
                parsePosition.setIndex(parsePosition2.getIndex());
                skipOptionals(str, parsePosition, " ");
                return gregorianCalendarNewGreg;
            }
        }
        return null;
    }

    private static Calendar parseDate(String str, ParsePosition parsePosition) {
        int index;
        if (str == null || str.isEmpty()) {
            return null;
        }
        ParsePosition parsePosition2 = new ParsePosition(parsePosition.getIndex());
        skipOptionals(str, parsePosition2, " ");
        int index2 = parsePosition2.getIndex();
        GregorianCalendar bigEndianDate = parseBigEndianDate(str, parsePosition2);
        if (bigEndianDate == null || !(parsePosition2.getIndex() == str.length() || parseTZoffset(str, bigEndianDate, parsePosition2))) {
            bigEndianDate = null;
            index = -999999;
        } else {
            index = parsePosition2.getIndex();
            if (index == str.length()) {
                parsePosition.setIndex(index);
                return bigEndianDate;
            }
        }
        parsePosition2.setIndex(index2);
        GregorianCalendar simpleDate = parseSimpleDate(str, Character.isDigit(str.charAt(index2)) ? DIGIT_START_FORMATS : ALPHA_START_FORMATS, parsePosition2);
        if (simpleDate != null && (parsePosition2.getIndex() == str.length() || parseTZoffset(str, simpleDate, parsePosition2))) {
            int index3 = parsePosition2.getIndex();
            if (index3 == str.length()) {
                parsePosition.setIndex(index3);
                return simpleDate;
            }
            if (index3 > index) {
                index = index3;
                bigEndianDate = simpleDate;
            }
        }
        if (bigEndianDate == null) {
            return simpleDate;
        }
        parsePosition.setIndex(index);
        return bigEndianDate;
    }

    public static Calendar toCalendar(COSString cOSString) {
        if (cOSString == null) {
            return null;
        }
        return toCalendar(cOSString.getString());
    }

    public static Calendar toCalendar(String str) {
        if (str != null && !str.trim().isEmpty()) {
            ParsePosition parsePosition = new ParsePosition(0);
            skipOptionals(str, parsePosition, " ");
            skipString(str, "D:", parsePosition);
            Calendar date = parseDate(str, parsePosition);
            if (date != null && parsePosition.getIndex() == str.length()) {
                return date;
            }
        }
        return null;
    }
}
