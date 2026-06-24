package org.apache.log4j;

import org.apache.http.client.methods.HttpTrace;
import org.apache.log4j.spi.Configurator;
import org.apache.log4j.spi.LocationInfo;
import org.apache.log4j.spi.LoggingEvent;

public abstract class LogXF {
    private static final String FQCN;
    protected static final Level TRACE = new Level(5000, HttpTrace.METHOD_NAME, 7);
    static Class class$org$apache$log4j$LogXF;

    static {
        Class clsClass$ = class$org$apache$log4j$LogXF;
        if (clsClass$ == null) {
            clsClass$ = class$("org.apache.log4j.LogXF");
            class$org$apache$log4j$LogXF = clsClass$;
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

    protected LogXF() {
    }

    protected static Boolean valueOf(boolean z) {
        if (z) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    protected static Character valueOf(char c) {
        return new Character(c);
    }

    protected static Byte valueOf(byte b) {
        return new Byte(b);
    }

    protected static Short valueOf(short s) {
        return new Short(s);
    }

    protected static Integer valueOf(int i) {
        return new Integer(i);
    }

    protected static Long valueOf(long j) {
        return new Long(j);
    }

    protected static Float valueOf(float f) {
        return new Float(f);
    }

    protected static Double valueOf(double d) {
        return new Double(d);
    }

    protected static Object[] toArray(Object obj) {
        return new Object[]{obj};
    }

    protected static Object[] toArray(Object obj, Object obj2) {
        return new Object[]{obj, obj2};
    }

    protected static Object[] toArray(Object obj, Object obj2, Object obj3) {
        return new Object[]{obj, obj2, obj3};
    }

    protected static Object[] toArray(Object obj, Object obj2, Object obj3, Object obj4) {
        return new Object[]{obj, obj2, obj3, obj4};
    }

    public static void entering(Logger logger, String str, String str2) {
        if (logger.isDebugEnabled()) {
            logger.callAppenders(new LoggingEvent(FQCN, logger, Level.DEBUG, new StringBuffer().append(str).append(".").append(str2).append(" ENTRY").toString(), null));
        }
    }

    public static void entering(Logger logger, String str, String str2, String str3) {
        if (logger.isDebugEnabled()) {
            logger.callAppenders(new LoggingEvent(FQCN, logger, Level.DEBUG, new StringBuffer().append(str).append(".").append(str2).append(" ENTRY ").append(str3).toString(), null));
        }
    }

    public static void entering(Logger logger, String str, String str2, Object obj) {
        String string;
        if (logger.isDebugEnabled()) {
            String string2 = new StringBuffer().append(str).append(".").append(str2).append(" ENTRY ").toString();
            if (obj == null) {
                string = new StringBuffer().append(string2).append(Configurator.NULL).toString();
            } else {
                try {
                    string = new StringBuffer().append(string2).append(obj).toString();
                } catch (Throwable unused) {
                    string = new StringBuffer().append(string2).append(LocationInfo.f2800NA).toString();
                }
            }
            logger.callAppenders(new LoggingEvent(FQCN, logger, Level.DEBUG, string, null));
        }
    }

    public static void entering(Logger logger, String str, String str2, Object[] objArr) {
        String string;
        if (logger.isDebugEnabled()) {
            String string2 = new StringBuffer().append(str).append(".").append(str2).append(" ENTRY ").toString();
            if (objArr != null && objArr.length > 0) {
                String str3 = "{";
                int i = 0;
                while (i < objArr.length) {
                    try {
                        string2 = new StringBuffer().append(string2).append(str3).append(objArr[i]).toString();
                    } catch (Throwable unused) {
                        string2 = new StringBuffer().append(string2).append(str3).append(LocationInfo.f2800NA).toString();
                    }
                    i++;
                    str3 = ",";
                }
                string = new StringBuffer().append(string2).append("}").toString();
            } else {
                string = new StringBuffer().append(string2).append("{}").toString();
            }
            logger.callAppenders(new LoggingEvent(FQCN, logger, Level.DEBUG, string, null));
        }
    }

    public static void exiting(Logger logger, String str, String str2) {
        if (logger.isDebugEnabled()) {
            logger.callAppenders(new LoggingEvent(FQCN, logger, Level.DEBUG, new StringBuffer().append(str).append(".").append(str2).append(" RETURN").toString(), null));
        }
    }

    public static void exiting(Logger logger, String str, String str2, String str3) {
        if (logger.isDebugEnabled()) {
            logger.callAppenders(new LoggingEvent(FQCN, logger, Level.DEBUG, new StringBuffer().append(str).append(".").append(str2).append(" RETURN ").append(str3).toString(), null));
        }
    }

    public static void exiting(Logger logger, String str, String str2, Object obj) {
        String string;
        if (logger.isDebugEnabled()) {
            String string2 = new StringBuffer().append(str).append(".").append(str2).append(" RETURN ").toString();
            if (obj == null) {
                string = new StringBuffer().append(string2).append(Configurator.NULL).toString();
            } else {
                try {
                    string = new StringBuffer().append(string2).append(obj).toString();
                } catch (Throwable unused) {
                    string = new StringBuffer().append(string2).append(LocationInfo.f2800NA).toString();
                }
            }
            logger.callAppenders(new LoggingEvent(FQCN, logger, Level.DEBUG, string, null));
        }
    }

    public static void throwing(Logger logger, String str, String str2, Throwable th) {
        if (logger.isDebugEnabled()) {
            logger.callAppenders(new LoggingEvent(FQCN, logger, Level.DEBUG, new StringBuffer().append(str).append(".").append(str2).append(" THROW").toString(), th));
        }
    }
}
