package org.apache.log4j.helpers;

import java.io.InputStream;
import java.io.InterruptedIOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.Properties;
import okhttp3.internal.p040ws.RealWebSocket;
import org.apache.log4j.Level;
import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.spi.Configurator;
import org.apache.log4j.spi.LoggerRepository;

public class OptionConverter {
    static String DELIM_START = "${";
    static int DELIM_START_LEN = 2;
    static char DELIM_STOP = '}';
    static int DELIM_STOP_LEN = 1;
    static Class class$java$lang$String;
    static Class class$org$apache$log4j$Level;
    static Class class$org$apache$log4j$spi$Configurator;

    private OptionConverter() {
    }

    public static String[] concatanateArrays(String[] strArr, String[] strArr2) {
        String[] strArr3 = new String[strArr.length + strArr2.length];
        System.arraycopy(strArr, 0, strArr3, 0, strArr.length);
        System.arraycopy(strArr2, 0, strArr3, strArr.length, strArr2.length);
        return strArr3;
    }

    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.lang.String convertSpecialChars(java.lang.String r7) {
        /*
            int r0 = r7.length()
            java.lang.StringBuffer r1 = new java.lang.StringBuffer
            r1.<init>(r0)
            r2 = 0
        La:
            if (r2 >= r0) goto L55
            int r3 = r2 + 1
            char r2 = r7.charAt(r2)
            r4 = 92
            if (r2 != r4) goto L50
            int r2 = r3 + 1
            char r3 = r7.charAt(r3)
            r5 = 110(0x6e, float:1.54E-43)
            if (r3 != r5) goto L26
            r3 = 10
        L22:
            r6 = r3
            r3 = r2
            r2 = r6
            goto L50
        L26:
            r5 = 114(0x72, float:1.6E-43)
            if (r3 != r5) goto L2d
            r3 = 13
            goto L22
        L2d:
            r5 = 116(0x74, float:1.63E-43)
            if (r3 != r5) goto L34
            r3 = 9
            goto L22
        L34:
            r5 = 102(0x66, float:1.43E-43)
            if (r3 != r5) goto L3b
            r3 = 12
            goto L22
        L3b:
            r5 = 8
            if (r3 != r5) goto L42
        L3f:
            r3 = r2
            r2 = r5
            goto L50
        L42:
            r5 = 34
            if (r3 != r5) goto L47
            goto L3f
        L47:
            r5 = 39
            if (r3 != r5) goto L4c
            goto L3f
        L4c:
            if (r3 != r4) goto L22
            r3 = r2
            r2 = r4
        L50:
            r1.append(r2)
            r2 = r3
            goto La
        L55:
            java.lang.String r7 = r1.toString()
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.log4j.helpers.OptionConverter.convertSpecialChars(java.lang.String):java.lang.String");
    }

    public static String getSystemProperty(String str, String str2) {
        try {
            return System.getProperty(str, str2);
        } catch (Throwable unused) {
            LogLog.debug(new StringBuffer("Was not allowed to read system property \"").append(str).append("\".").toString());
            return str2;
        }
    }

    public static Object instantiateByKey(Properties properties, String str, Class cls, Object obj) {
        String strFindAndSubst = findAndSubst(str, properties);
        if (strFindAndSubst == null) {
            LogLog.error(new StringBuffer("Could not find value for key ").append(str).toString());
            return obj;
        }
        return instantiateByClassName(strFindAndSubst.trim(), cls, obj);
    }

    public static boolean toBoolean(String str, boolean z) {
        if (str == null) {
            return z;
        }
        String strTrim = str.trim();
        if ("true".equalsIgnoreCase(strTrim)) {
            return true;
        }
        if ("false".equalsIgnoreCase(strTrim)) {
            return false;
        }
        return z;
    }

    public static int toInt(String str, int i) {
        if (str != null) {
            String strTrim = str.trim();
            try {
                return Integer.valueOf(strTrim).intValue();
            } catch (NumberFormatException e) {
                LogLog.error(new StringBuffer("[").append(strTrim).append("] is not in proper int form.").toString());
                e.printStackTrace();
            }
        }
        return i;
    }

    public static Level toLevel(String str, Level level) throws Throwable {
        if (str == null) {
            return level;
        }
        String strTrim = str.trim();
        int iIndexOf = strTrim.indexOf(35);
        if (iIndexOf == -1) {
            if (DateLayout.NULL_DATE_FORMAT.equalsIgnoreCase(strTrim)) {
                return null;
            }
            return Level.toLevel(strTrim, level);
        }
        String strSubstring = strTrim.substring(iIndexOf + 1);
        String strSubstring2 = strTrim.substring(0, iIndexOf);
        if (DateLayout.NULL_DATE_FORMAT.equalsIgnoreCase(strSubstring2)) {
            return null;
        }
        LogLog.debug(new StringBuffer("toLevel:class=[").append(strSubstring).append("]:pri=[").append(strSubstring2).append("]").toString());
        try {
            Class clsLoadClass = Loader.loadClass(strSubstring);
            Class<?>[] clsArr = new Class[2];
            Class<?> clsClass$ = class$java$lang$String;
            if (clsClass$ == null) {
                clsClass$ = class$("java.lang.String");
                class$java$lang$String = clsClass$;
            }
            clsArr[0] = clsClass$;
            Class<?> clsClass$2 = class$org$apache$log4j$Level;
            if (clsClass$2 == null) {
                clsClass$2 = class$("org.apache.log4j.Level");
                class$org$apache$log4j$Level = clsClass$2;
            }
            clsArr[1] = clsClass$2;
            return (Level) clsLoadClass.getMethod("toLevel", clsArr).invoke(null, strSubstring2, level);
        } catch (ClassCastException e) {
            LogLog.warn(new StringBuffer("class [").append(strSubstring).append("] is not a subclass of org.apache.log4j.Level").toString(), e);
            return level;
        } catch (ClassNotFoundException unused) {
            LogLog.warn(new StringBuffer("custom level class [").append(strSubstring).append("] not found.").toString());
            return level;
        } catch (IllegalAccessException e2) {
            LogLog.warn(new StringBuffer("class [").append(strSubstring).append("] cannot be instantiated due to access restrictions").toString(), e2);
            return level;
        } catch (NoSuchMethodException e3) {
            LogLog.warn(new StringBuffer("custom level class [").append(strSubstring).append("] does not have a class function toLevel(String, Level)").toString(), e3);
            return level;
        } catch (RuntimeException e4) {
            LogLog.warn(new StringBuffer("class [").append(strSubstring).append("], level [").append(strSubstring2).append("] conversion failed.").toString(), e4);
            return level;
        } catch (InvocationTargetException e5) {
            if ((e5.getTargetException() instanceof InterruptedException) || (e5.getTargetException() instanceof InterruptedIOException)) {
                Thread.currentThread().interrupt();
            }
            LogLog.warn(new StringBuffer("custom level class [").append(strSubstring).append("] could not be instantiated").toString(), e5);
            return level;
        }
    }

    static Class class$(String str) throws Throwable {
        try {
            return Class.forName(str);
        } catch (ClassNotFoundException e) {
            throw new NoClassDefFoundError().initCause(e);
        }
    }

    public static long toFileSize(String str, long j) {
        long j2;
        if (str == null) {
            return j;
        }
        String upperCase = str.trim().toUpperCase();
        int iIndexOf = upperCase.indexOf("KB");
        if (iIndexOf != -1) {
            upperCase = upperCase.substring(0, iIndexOf);
            j2 = RealWebSocket.DEFAULT_MINIMUM_DEFLATE_SIZE;
        } else {
            int iIndexOf2 = upperCase.indexOf("MB");
            if (iIndexOf2 != -1) {
                upperCase = upperCase.substring(0, iIndexOf2);
                j2 = 1048576;
            } else {
                int iIndexOf3 = upperCase.indexOf("GB");
                if (iIndexOf3 != -1) {
                    upperCase = upperCase.substring(0, iIndexOf3);
                    j2 = 1073741824;
                } else {
                    j2 = 1;
                }
            }
        }
        if (upperCase != null) {
            try {
                return Long.valueOf(upperCase).longValue() * j2;
            } catch (NumberFormatException e) {
                LogLog.error(new StringBuffer("[").append(upperCase).append("] is not in proper int form.").toString());
                LogLog.error(new StringBuffer("[").append(str).append("] not in expected format.").toString(), e);
            }
        }
        return j;
    }

    public static String findAndSubst(String str, Properties properties) {
        String property = properties.getProperty(str);
        if (property == null) {
            return null;
        }
        try {
            return substVars(property, properties);
        } catch (IllegalArgumentException e) {
            LogLog.error(new StringBuffer("Bad option value [").append(property).append("].").toString(), e);
            return property;
        }
    }

    public static Object instantiateByClassName(String str, Class cls, Object obj) {
        if (str != null) {
            try {
                Class<?> clsLoadClass = Loader.loadClass(str);
                if (!cls.isAssignableFrom(clsLoadClass)) {
                    LogLog.error(new StringBuffer("A \"").append(str).append("\" object is not assignable to a \"").append(cls.getName()).append("\" variable.").toString());
                    LogLog.error(new StringBuffer("The class \"").append(cls.getName()).append("\" was loaded by ").toString());
                    LogLog.error(new StringBuffer("[").append(cls.getClassLoader()).append("] whereas object of type ").toString());
                    LogLog.error(new StringBuffer("\"").append(clsLoadClass.getName()).append("\" was loaded by [").append(clsLoadClass.getClassLoader()).append("].").toString());
                    return obj;
                }
                return clsLoadClass.newInstance();
            } catch (ClassNotFoundException e) {
                LogLog.error(new StringBuffer("Could not instantiate class [").append(str).append("].").toString(), e);
            } catch (IllegalAccessException e2) {
                LogLog.error(new StringBuffer("Could not instantiate class [").append(str).append("].").toString(), e2);
            } catch (InstantiationException e3) {
                LogLog.error(new StringBuffer("Could not instantiate class [").append(str).append("].").toString(), e3);
            } catch (RuntimeException e4) {
                LogLog.error(new StringBuffer("Could not instantiate class [").append(str).append("].").toString(), e4);
            }
        }
        return obj;
    }

    public static String substVars(String str, Properties properties) throws IllegalArgumentException {
        StringBuffer stringBuffer = new StringBuffer();
        int i = 0;
        while (true) {
            int iIndexOf = str.indexOf(DELIM_START, i);
            if (iIndexOf == -1) {
                if (i == 0) {
                    return str;
                }
                stringBuffer.append(str.substring(i, str.length()));
                return stringBuffer.toString();
            }
            stringBuffer.append(str.substring(i, iIndexOf));
            int iIndexOf2 = str.indexOf(DELIM_STOP, iIndexOf);
            if (iIndexOf2 == -1) {
                throw new IllegalArgumentException(new StringBuffer("\"").append(str).append("\" has no closing brace. Opening brace at position ").append(iIndexOf).append('.').toString());
            }
            String strSubstring = str.substring(iIndexOf + DELIM_START_LEN, iIndexOf2);
            String systemProperty = getSystemProperty(strSubstring, null);
            if (systemProperty == null && properties != null) {
                systemProperty = properties.getProperty(strSubstring);
            }
            if (systemProperty != null) {
                stringBuffer.append(substVars(systemProperty, properties));
            }
            i = iIndexOf2 + DELIM_STOP_LEN;
        }
    }

    public static void selectAndConfigure(InputStream inputStream, String str, LoggerRepository loggerRepository) throws Throwable {
        Configurator propertyConfigurator;
        if (str != null) {
            LogLog.debug(new StringBuffer("Preferred configurator class: ").append(str).toString());
            Class clsClass$ = class$org$apache$log4j$spi$Configurator;
            if (clsClass$ == null) {
                clsClass$ = class$("org.apache.log4j.spi.Configurator");
                class$org$apache$log4j$spi$Configurator = clsClass$;
            }
            propertyConfigurator = (Configurator) instantiateByClassName(str, clsClass$, null);
            if (propertyConfigurator == null) {
                LogLog.error(new StringBuffer("Could not instantiate configurator [").append(str).append("].").toString());
                return;
            }
        } else {
            propertyConfigurator = new PropertyConfigurator();
        }
        propertyConfigurator.doConfigure(inputStream, loggerRepository);
    }

    public static void selectAndConfigure(URL url, String str, LoggerRepository loggerRepository) throws Throwable {
        Configurator propertyConfigurator;
        String file = url.getFile();
        if (str == null && file != null && file.endsWith(".xml")) {
            str = "org.apache.log4j.xml.DOMConfigurator";
        }
        if (str != null) {
            LogLog.debug(new StringBuffer("Preferred configurator class: ").append(str).toString());
            Class clsClass$ = class$org$apache$log4j$spi$Configurator;
            if (clsClass$ == null) {
                clsClass$ = class$("org.apache.log4j.spi.Configurator");
                class$org$apache$log4j$spi$Configurator = clsClass$;
            }
            propertyConfigurator = (Configurator) instantiateByClassName(str, clsClass$, null);
            if (propertyConfigurator == null) {
                LogLog.error(new StringBuffer("Could not instantiate configurator [").append(str).append("].").toString());
                return;
            }
        } else {
            propertyConfigurator = new PropertyConfigurator();
        }
        propertyConfigurator.doConfigure(url, loggerRepository);
    }
}
