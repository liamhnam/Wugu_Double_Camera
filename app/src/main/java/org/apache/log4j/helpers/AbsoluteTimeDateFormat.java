package org.apache.log4j.helpers;

import java.text.DateFormat;
import java.text.ParsePosition;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class AbsoluteTimeDateFormat extends DateFormat {
    public static final String ABS_TIME_DATE_FORMAT = "ABSOLUTE";
    public static final String DATE_AND_TIME_DATE_FORMAT = "DATE";
    public static final String ISO8601_DATE_FORMAT = "ISO8601";
    private static long previousTime = 0;
    private static char[] previousTimeWithoutMillis = new char[9];
    private static final long serialVersionUID = -388856345976723342L;

    @Override
    public Date parse(String str, ParsePosition parsePosition) {
        return null;
    }

    public AbsoluteTimeDateFormat() {
        setCalendar(Calendar.getInstance());
    }

    public AbsoluteTimeDateFormat(TimeZone timeZone) {
        setCalendar(Calendar.getInstance(timeZone));
    }

    @Override
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.StringBuffer format(java.util.Date r9, java.lang.StringBuffer r10, java.text.FieldPosition r11) {
        /*
            r8 = this;
            long r0 = r9.getTime()
            r2 = 1000(0x3e8, double:4.94E-321)
            long r2 = r0 % r2
            int r11 = (int) r2
            long r2 = (long) r11
            long r0 = r0 - r2
            long r2 = org.apache.log4j.helpers.AbsoluteTimeDateFormat.previousTime
            int r2 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            r3 = 0
            r4 = 10
            r5 = 48
            if (r2 != 0) goto L21
            char[] r2 = org.apache.log4j.helpers.AbsoluteTimeDateFormat.previousTimeWithoutMillis
            char r6 = r2[r3]
            if (r6 != 0) goto L1d
            goto L21
        L1d:
            r10.append(r2)
            goto L72
        L21:
            java.util.Calendar r2 = r8.calendar
            r2.setTime(r9)
            int r9 = r10.length()
            java.util.Calendar r2 = r8.calendar
            r6 = 11
            int r2 = r2.get(r6)
            if (r2 >= r4) goto L37
            r10.append(r5)
        L37:
            r10.append(r2)
            r2 = 58
            r10.append(r2)
            java.util.Calendar r6 = r8.calendar
            r7 = 12
            int r6 = r6.get(r7)
            if (r6 >= r4) goto L4c
            r10.append(r5)
        L4c:
            r10.append(r6)
            r10.append(r2)
            java.util.Calendar r2 = r8.calendar
            r6 = 13
            int r2 = r2.get(r6)
            if (r2 >= r4) goto L5f
            r10.append(r5)
        L5f:
            r10.append(r2)
            r2 = 44
            r10.append(r2)
            int r2 = r10.length()
            char[] r6 = org.apache.log4j.helpers.AbsoluteTimeDateFormat.previousTimeWithoutMillis
            r10.getChars(r9, r2, r6, r3)
            org.apache.log4j.helpers.AbsoluteTimeDateFormat.previousTime = r0
        L72:
            r9 = 100
            if (r11 >= r9) goto L79
            r10.append(r5)
        L79:
            if (r11 >= r4) goto L7e
            r10.append(r5)
        L7e:
            r10.append(r11)
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.log4j.helpers.AbsoluteTimeDateFormat.format(java.util.Date, java.lang.StringBuffer, java.text.FieldPosition):java.lang.StringBuffer");
    }
}
