package org.apache.log4j;

import java.util.ResourceBundle;
import org.apache.log4j.spi.LoggingEvent;

public final class LogSF extends LogXF {
    private static final String FQCN;
    static Class class$org$apache$log4j$LogSF;

    private LogSF() {
    }

    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static java.lang.String format(java.lang.String r8, java.lang.Object[] r9) {
        /*
            if (r8 == 0) goto Lc0
            java.lang.String r0 = "{"
            int r1 = r8.indexOf(r0)
            r2 = 0
            java.lang.String r3 = ""
            r4 = r3
            r3 = r2
        Ld:
            if (r1 < 0) goto Laa
            if (r1 == 0) goto L3a
            int r5 = r1 + (-1)
            char r6 = r8.charAt(r5)
            r7 = 92
            if (r6 == r7) goto L1c
            goto L3a
        L1c:
            java.lang.StringBuffer r6 = new java.lang.StringBuffer
            r6.<init>()
            java.lang.StringBuffer r4 = r6.append(r4)
            java.lang.String r2 = r8.substring(r2, r5)
            java.lang.StringBuffer r2 = r4.append(r2)
            java.lang.StringBuffer r2 = r2.append(r0)
            java.lang.String r2 = r2.toString()
            int r1 = r1 + 1
        L37:
            r4 = r2
            r2 = r1
            goto La4
        L3a:
            java.lang.StringBuffer r5 = new java.lang.StringBuffer
            r5.<init>()
            java.lang.StringBuffer r4 = r5.append(r4)
            java.lang.String r2 = r8.substring(r2, r1)
            java.lang.StringBuffer r2 = r4.append(r2)
            java.lang.String r2 = r2.toString()
            int r4 = r1 + 1
            int r5 = r8.length()
            if (r4 >= r5) goto L91
            char r5 = r8.charAt(r4)
            r6 = 125(0x7d, float:1.75E-43)
            if (r5 != r6) goto L91
            if (r9 == 0) goto L7b
            int r4 = r9.length
            if (r3 >= r4) goto L7b
            java.lang.StringBuffer r4 = new java.lang.StringBuffer
            r4.<init>()
            java.lang.StringBuffer r2 = r4.append(r2)
            int r4 = r3 + 1
            r3 = r9[r3]
            java.lang.StringBuffer r2 = r2.append(r3)
            java.lang.String r2 = r2.toString()
            r3 = r4
            goto L8e
        L7b:
            java.lang.StringBuffer r4 = new java.lang.StringBuffer
            r4.<init>()
            java.lang.StringBuffer r2 = r4.append(r2)
            java.lang.String r4 = "{}"
            java.lang.StringBuffer r2 = r2.append(r4)
            java.lang.String r2 = r2.toString()
        L8e:
            int r1 = r1 + 2
            goto L37
        L91:
            java.lang.StringBuffer r1 = new java.lang.StringBuffer
            r1.<init>()
            java.lang.StringBuffer r1 = r1.append(r2)
            java.lang.StringBuffer r1 = r1.append(r0)
            java.lang.String r1 = r1.toString()
            r2 = r4
            r4 = r1
        La4:
            int r1 = r8.indexOf(r0, r2)
            goto Ld
        Laa:
            java.lang.StringBuffer r9 = new java.lang.StringBuffer
            r9.<init>()
            java.lang.StringBuffer r9 = r9.append(r4)
            java.lang.String r8 = r8.substring(r2)
            java.lang.StringBuffer r8 = r9.append(r8)
            java.lang.String r8 = r8.toString()
            return r8
        Lc0:
            r8 = 0
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.log4j.LogSF.format(java.lang.String, java.lang.Object[]):java.lang.String");
    }

    private static String format(String str, Object obj) {
        if (str == null) {
            return str;
        }
        if (str.indexOf("\\{") >= 0) {
            return format(str, new Object[]{obj});
        }
        int iIndexOf = str.indexOf("{}");
        return iIndexOf >= 0 ? new StringBuffer().append(str.substring(0, iIndexOf)).append(obj).append(str.substring(iIndexOf + 2)).toString() : str;
    }

    private static String format(String str, String str2, Object[] objArr) {
        if (str != null) {
            try {
                str2 = ResourceBundle.getBundle(str).getString(str2);
            } catch (Exception unused) {
            }
        }
        return format(str2, objArr);
    }

    static {
        Class clsClass$ = class$org$apache$log4j$LogSF;
        if (clsClass$ == null) {
            clsClass$ = class$("org.apache.log4j.LogSF");
            class$org$apache$log4j$LogSF = clsClass$;
        }
        FQCN = clsClass$.getName();
    }

    static Class class$(String str) throws Throwable {
        try {
            return Class.forName(str);
        } catch (ClassNotFoundException e) {
            throw new NoClassDefFoundError().initCause(e);
        }
    }

    private static void forcedLog(Logger logger, Level level, String str) {
        logger.callAppenders(new LoggingEvent(FQCN, logger, level, str, null));
    }

    private static void forcedLog(Logger logger, Level level, String str, Throwable th) {
        logger.callAppenders(new LoggingEvent(FQCN, logger, level, str, th));
    }

    public static void trace(Logger logger, String str, Object[] objArr) {
        if (logger.isEnabledFor(TRACE)) {
            forcedLog(logger, TRACE, format(str, objArr));
        }
    }

    public static void debug(Logger logger, String str, Object[] objArr) {
        if (logger.isDebugEnabled()) {
            forcedLog(logger, Level.DEBUG, format(str, objArr));
        }
    }

    public static void info(Logger logger, String str, Object[] objArr) {
        if (logger.isInfoEnabled()) {
            forcedLog(logger, Level.INFO, format(str, objArr));
        }
    }

    public static void warn(Logger logger, String str, Object[] objArr) {
        if (logger.isEnabledFor(Level.WARN)) {
            forcedLog(logger, Level.WARN, format(str, objArr));
        }
    }

    public static void error(Logger logger, String str, Object[] objArr) {
        if (logger.isEnabledFor(Level.ERROR)) {
            forcedLog(logger, Level.ERROR, format(str, objArr));
        }
    }

    public static void fatal(Logger logger, String str, Object[] objArr) {
        if (logger.isEnabledFor(Level.FATAL)) {
            forcedLog(logger, Level.FATAL, format(str, objArr));
        }
    }

    public static void trace(Logger logger, Throwable th, String str, Object[] objArr) {
        if (logger.isEnabledFor(TRACE)) {
            forcedLog(logger, TRACE, format(str, objArr), th);
        }
    }

    public static void debug(Logger logger, Throwable th, String str, Object[] objArr) {
        if (logger.isDebugEnabled()) {
            forcedLog(logger, Level.DEBUG, format(str, objArr), th);
        }
    }

    public static void info(Logger logger, Throwable th, String str, Object[] objArr) {
        if (logger.isInfoEnabled()) {
            forcedLog(logger, Level.INFO, format(str, objArr), th);
        }
    }

    public static void warn(Logger logger, Throwable th, String str, Object[] objArr) {
        if (logger.isEnabledFor(Level.WARN)) {
            forcedLog(logger, Level.WARN, format(str, objArr), th);
        }
    }

    public static void error(Logger logger, Throwable th, String str, Object[] objArr) {
        if (logger.isEnabledFor(Level.ERROR)) {
            forcedLog(logger, Level.ERROR, format(str, objArr), th);
        }
    }

    public static void fatal(Logger logger, Throwable th, String str, Object[] objArr) {
        if (logger.isEnabledFor(Level.FATAL)) {
            forcedLog(logger, Level.FATAL, format(str, objArr), th);
        }
    }

    public static void trace(Logger logger, String str, boolean z) {
        if (logger.isEnabledFor(TRACE)) {
            forcedLog(logger, TRACE, format(str, valueOf(z)));
        }
    }

    public static void trace(Logger logger, String str, char c) {
        if (logger.isEnabledFor(TRACE)) {
            forcedLog(logger, TRACE, format(str, valueOf(c)));
        }
    }

    public static void trace(Logger logger, String str, byte b) {
        if (logger.isEnabledFor(TRACE)) {
            forcedLog(logger, TRACE, format(str, valueOf(b)));
        }
    }

    public static void trace(Logger logger, String str, short s) {
        if (logger.isEnabledFor(TRACE)) {
            forcedLog(logger, TRACE, format(str, valueOf(s)));
        }
    }

    public static void trace(Logger logger, String str, int i) {
        if (logger.isEnabledFor(TRACE)) {
            forcedLog(logger, TRACE, format(str, valueOf(i)));
        }
    }

    public static void trace(Logger logger, String str, long j) {
        if (logger.isEnabledFor(TRACE)) {
            forcedLog(logger, TRACE, format(str, valueOf(j)));
        }
    }

    public static void trace(Logger logger, String str, float f) {
        if (logger.isEnabledFor(TRACE)) {
            forcedLog(logger, TRACE, format(str, valueOf(f)));
        }
    }

    public static void trace(Logger logger, String str, double d) {
        if (logger.isEnabledFor(TRACE)) {
            forcedLog(logger, TRACE, format(str, valueOf(d)));
        }
    }

    public static void trace(Logger logger, String str, Object obj) {
        if (logger.isEnabledFor(TRACE)) {
            forcedLog(logger, TRACE, format(str, obj));
        }
    }

    public static void trace(Logger logger, String str, Object obj, Object obj2) {
        if (logger.isEnabledFor(TRACE)) {
            forcedLog(logger, TRACE, format(str, toArray(obj, obj2)));
        }
    }

    public static void trace(Logger logger, String str, Object obj, Object obj2, Object obj3) {
        if (logger.isEnabledFor(TRACE)) {
            forcedLog(logger, TRACE, format(str, toArray(obj, obj2, obj3)));
        }
    }

    public static void trace(Logger logger, String str, Object obj, Object obj2, Object obj3, Object obj4) {
        if (logger.isEnabledFor(TRACE)) {
            forcedLog(logger, TRACE, format(str, toArray(obj, obj2, obj3, obj4)));
        }
    }

    public static void debug(Logger logger, String str, boolean z) {
        if (logger.isDebugEnabled()) {
            forcedLog(logger, Level.DEBUG, format(str, valueOf(z)));
        }
    }

    public static void debug(Logger logger, String str, char c) {
        if (logger.isDebugEnabled()) {
            forcedLog(logger, Level.DEBUG, format(str, valueOf(c)));
        }
    }

    public static void debug(Logger logger, String str, byte b) {
        if (logger.isDebugEnabled()) {
            forcedLog(logger, Level.DEBUG, format(str, valueOf(b)));
        }
    }

    public static void debug(Logger logger, String str, short s) {
        if (logger.isDebugEnabled()) {
            forcedLog(logger, Level.DEBUG, format(str, valueOf(s)));
        }
    }

    public static void debug(Logger logger, String str, int i) {
        if (logger.isDebugEnabled()) {
            forcedLog(logger, Level.DEBUG, format(str, valueOf(i)));
        }
    }

    public static void debug(Logger logger, String str, long j) {
        if (logger.isDebugEnabled()) {
            forcedLog(logger, Level.DEBUG, format(str, valueOf(j)));
        }
    }

    public static void debug(Logger logger, String str, float f) {
        if (logger.isDebugEnabled()) {
            forcedLog(logger, Level.DEBUG, format(str, valueOf(f)));
        }
    }

    public static void debug(Logger logger, String str, double d) {
        if (logger.isDebugEnabled()) {
            forcedLog(logger, Level.DEBUG, format(str, valueOf(d)));
        }
    }

    public static void debug(Logger logger, String str, Object obj) {
        if (logger.isDebugEnabled()) {
            forcedLog(logger, Level.DEBUG, format(str, obj));
        }
    }

    public static void debug(Logger logger, String str, Object obj, Object obj2) {
        if (logger.isDebugEnabled()) {
            forcedLog(logger, Level.DEBUG, format(str, toArray(obj, obj2)));
        }
    }

    public static void debug(Logger logger, String str, Object obj, Object obj2, Object obj3) {
        if (logger.isDebugEnabled()) {
            forcedLog(logger, Level.DEBUG, format(str, toArray(obj, obj2, obj3)));
        }
    }

    public static void debug(Logger logger, String str, Object obj, Object obj2, Object obj3, Object obj4) {
        if (logger.isDebugEnabled()) {
            forcedLog(logger, Level.DEBUG, format(str, toArray(obj, obj2, obj3, obj4)));
        }
    }

    public static void info(Logger logger, String str, boolean z) {
        if (logger.isInfoEnabled()) {
            forcedLog(logger, Level.INFO, format(str, valueOf(z)));
        }
    }

    public static void info(Logger logger, String str, char c) {
        if (logger.isInfoEnabled()) {
            forcedLog(logger, Level.INFO, format(str, valueOf(c)));
        }
    }

    public static void info(Logger logger, String str, byte b) {
        if (logger.isInfoEnabled()) {
            forcedLog(logger, Level.INFO, format(str, valueOf(b)));
        }
    }

    public static void info(Logger logger, String str, short s) {
        if (logger.isInfoEnabled()) {
            forcedLog(logger, Level.INFO, format(str, valueOf(s)));
        }
    }

    public static void info(Logger logger, String str, int i) {
        if (logger.isInfoEnabled()) {
            forcedLog(logger, Level.INFO, format(str, valueOf(i)));
        }
    }

    public static void info(Logger logger, String str, long j) {
        if (logger.isInfoEnabled()) {
            forcedLog(logger, Level.INFO, format(str, valueOf(j)));
        }
    }

    public static void info(Logger logger, String str, float f) {
        if (logger.isInfoEnabled()) {
            forcedLog(logger, Level.INFO, format(str, valueOf(f)));
        }
    }

    public static void info(Logger logger, String str, double d) {
        if (logger.isInfoEnabled()) {
            forcedLog(logger, Level.INFO, format(str, valueOf(d)));
        }
    }

    public static void info(Logger logger, String str, Object obj) {
        if (logger.isInfoEnabled()) {
            forcedLog(logger, Level.INFO, format(str, obj));
        }
    }

    public static void info(Logger logger, String str, Object obj, Object obj2) {
        if (logger.isInfoEnabled()) {
            forcedLog(logger, Level.INFO, format(str, toArray(obj, obj2)));
        }
    }

    public static void info(Logger logger, String str, Object obj, Object obj2, Object obj3) {
        if (logger.isInfoEnabled()) {
            forcedLog(logger, Level.INFO, format(str, toArray(obj, obj2, obj3)));
        }
    }

    public static void info(Logger logger, String str, Object obj, Object obj2, Object obj3, Object obj4) {
        if (logger.isInfoEnabled()) {
            forcedLog(logger, Level.INFO, format(str, toArray(obj, obj2, obj3, obj4)));
        }
    }

    public static void warn(Logger logger, String str, boolean z) {
        if (logger.isEnabledFor(Level.WARN)) {
            forcedLog(logger, Level.WARN, format(str, valueOf(z)));
        }
    }

    public static void warn(Logger logger, String str, char c) {
        if (logger.isEnabledFor(Level.WARN)) {
            forcedLog(logger, Level.WARN, format(str, valueOf(c)));
        }
    }

    public static void warn(Logger logger, String str, byte b) {
        if (logger.isEnabledFor(Level.WARN)) {
            forcedLog(logger, Level.WARN, format(str, valueOf(b)));
        }
    }

    public static void warn(Logger logger, String str, short s) {
        if (logger.isEnabledFor(Level.WARN)) {
            forcedLog(logger, Level.WARN, format(str, valueOf(s)));
        }
    }

    public static void warn(Logger logger, String str, int i) {
        if (logger.isEnabledFor(Level.WARN)) {
            forcedLog(logger, Level.WARN, format(str, valueOf(i)));
        }
    }

    public static void warn(Logger logger, String str, long j) {
        if (logger.isEnabledFor(Level.WARN)) {
            forcedLog(logger, Level.WARN, format(str, valueOf(j)));
        }
    }

    public static void warn(Logger logger, String str, float f) {
        if (logger.isEnabledFor(Level.WARN)) {
            forcedLog(logger, Level.WARN, format(str, valueOf(f)));
        }
    }

    public static void warn(Logger logger, String str, double d) {
        if (logger.isEnabledFor(Level.WARN)) {
            forcedLog(logger, Level.WARN, format(str, valueOf(d)));
        }
    }

    public static void warn(Logger logger, String str, Object obj) {
        if (logger.isEnabledFor(Level.WARN)) {
            forcedLog(logger, Level.WARN, format(str, obj));
        }
    }

    public static void warn(Logger logger, String str, Object obj, Object obj2) {
        if (logger.isEnabledFor(Level.WARN)) {
            forcedLog(logger, Level.WARN, format(str, toArray(obj, obj2)));
        }
    }

    public static void warn(Logger logger, String str, Object obj, Object obj2, Object obj3) {
        if (logger.isEnabledFor(Level.WARN)) {
            forcedLog(logger, Level.WARN, format(str, toArray(obj, obj2, obj3)));
        }
    }

    public static void warn(Logger logger, String str, Object obj, Object obj2, Object obj3, Object obj4) {
        if (logger.isEnabledFor(Level.WARN)) {
            forcedLog(logger, Level.WARN, format(str, toArray(obj, obj2, obj3, obj4)));
        }
    }

    public static void log(Logger logger, Level level, String str, Object[] objArr) {
        if (logger.isEnabledFor(level)) {
            forcedLog(logger, level, format(str, objArr));
        }
    }

    public static void log(Logger logger, Level level, Throwable th, String str, Object[] objArr) {
        if (logger.isEnabledFor(level)) {
            forcedLog(logger, level, format(str, objArr), th);
        }
    }

    public static void log(Logger logger, Level level, String str, Object obj) {
        if (logger.isEnabledFor(level)) {
            forcedLog(logger, level, format(str, toArray(obj)));
        }
    }

    public static void log(Logger logger, Level level, String str, boolean z) {
        if (logger.isEnabledFor(level)) {
            forcedLog(logger, level, format(str, toArray(valueOf(z))));
        }
    }

    public static void log(Logger logger, Level level, String str, byte b) {
        if (logger.isEnabledFor(level)) {
            forcedLog(logger, level, format(str, toArray(valueOf(b))));
        }
    }

    public static void log(Logger logger, Level level, String str, char c) {
        if (logger.isEnabledFor(level)) {
            forcedLog(logger, level, format(str, toArray(valueOf(c))));
        }
    }

    public static void log(Logger logger, Level level, String str, short s) {
        if (logger.isEnabledFor(level)) {
            forcedLog(logger, level, format(str, toArray(valueOf(s))));
        }
    }

    public static void log(Logger logger, Level level, String str, int i) {
        if (logger.isEnabledFor(level)) {
            forcedLog(logger, level, format(str, toArray(valueOf(i))));
        }
    }

    public static void log(Logger logger, Level level, String str, long j) {
        if (logger.isEnabledFor(level)) {
            forcedLog(logger, level, format(str, toArray(valueOf(j))));
        }
    }

    public static void log(Logger logger, Level level, String str, float f) {
        if (logger.isEnabledFor(level)) {
            forcedLog(logger, level, format(str, toArray(valueOf(f))));
        }
    }

    public static void log(Logger logger, Level level, String str, double d) {
        if (logger.isEnabledFor(level)) {
            forcedLog(logger, level, format(str, toArray(valueOf(d))));
        }
    }

    public static void log(Logger logger, Level level, String str, Object obj, Object obj2) {
        if (logger.isEnabledFor(level)) {
            forcedLog(logger, level, format(str, toArray(obj, obj2)));
        }
    }

    public static void log(Logger logger, Level level, String str, Object obj, Object obj2, Object obj3) {
        if (logger.isEnabledFor(level)) {
            forcedLog(logger, level, format(str, toArray(obj, obj2, obj3)));
        }
    }

    public static void log(Logger logger, Level level, String str, Object obj, Object obj2, Object obj3, Object obj4) {
        if (logger.isEnabledFor(level)) {
            forcedLog(logger, level, format(str, toArray(obj, obj2, obj3, obj4)));
        }
    }

    public static void logrb(Logger logger, Level level, String str, String str2, Object[] objArr) {
        if (logger.isEnabledFor(level)) {
            forcedLog(logger, level, format(str, str2, objArr));
        }
    }

    public static void logrb(Logger logger, Level level, Throwable th, String str, String str2, Object[] objArr) {
        if (logger.isEnabledFor(level)) {
            forcedLog(logger, level, format(str, str2, objArr), th);
        }
    }

    public static void logrb(Logger logger, Level level, String str, String str2, Object obj) {
        if (logger.isEnabledFor(level)) {
            forcedLog(logger, level, format(str, str2, toArray(obj)));
        }
    }

    public static void logrb(Logger logger, Level level, String str, String str2, boolean z) {
        if (logger.isEnabledFor(level)) {
            forcedLog(logger, level, format(str, str2, toArray(valueOf(z))));
        }
    }

    public static void logrb(Logger logger, Level level, String str, String str2, char c) {
        if (logger.isEnabledFor(level)) {
            forcedLog(logger, level, format(str, str2, toArray(valueOf(c))));
        }
    }

    public static void logrb(Logger logger, Level level, String str, String str2, byte b) {
        if (logger.isEnabledFor(level)) {
            forcedLog(logger, level, format(str, str2, toArray(valueOf(b))));
        }
    }

    public static void logrb(Logger logger, Level level, String str, String str2, short s) {
        if (logger.isEnabledFor(level)) {
            forcedLog(logger, level, format(str, str2, toArray(valueOf(s))));
        }
    }

    public static void logrb(Logger logger, Level level, String str, String str2, int i) {
        if (logger.isEnabledFor(level)) {
            forcedLog(logger, level, format(str, str2, toArray(valueOf(i))));
        }
    }

    public static void logrb(Logger logger, Level level, String str, String str2, long j) {
        if (logger.isEnabledFor(level)) {
            forcedLog(logger, level, format(str, str2, toArray(valueOf(j))));
        }
    }

    public static void logrb(Logger logger, Level level, String str, String str2, float f) {
        if (logger.isEnabledFor(level)) {
            forcedLog(logger, level, format(str, str2, toArray(valueOf(f))));
        }
    }

    public static void logrb(Logger logger, Level level, String str, String str2, double d) {
        if (logger.isEnabledFor(level)) {
            forcedLog(logger, level, format(str, str2, toArray(valueOf(d))));
        }
    }

    public static void logrb(Logger logger, Level level, String str, String str2, Object obj, Object obj2) {
        if (logger.isEnabledFor(level)) {
            forcedLog(logger, level, format(str, str2, toArray(obj, obj2)));
        }
    }

    public static void logrb(Logger logger, Level level, String str, String str2, Object obj, Object obj2, Object obj3) {
        if (logger.isEnabledFor(level)) {
            forcedLog(logger, level, format(str, str2, toArray(obj, obj2, obj3)));
        }
    }

    public static void logrb(Logger logger, Level level, String str, String str2, Object obj, Object obj2, Object obj3, Object obj4) {
        if (logger.isEnabledFor(level)) {
            forcedLog(logger, level, format(str, str2, toArray(obj, obj2, obj3, obj4)));
        }
    }
}
