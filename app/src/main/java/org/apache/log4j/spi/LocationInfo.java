package org.apache.log4j.spi;

import java.io.InterruptedIOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.io.StringWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import org.apache.log4j.Layout;
import org.apache.log4j.helpers.LogLog;

public class LocationInfo implements Serializable {
    static Class class$java$lang$Throwable = null;
    private static Method getClassNameMethod = null;
    private static Method getFileNameMethod = null;
    private static Method getLineNumberMethod = null;
    private static Method getMethodNameMethod = null;
    private static Method getStackTraceMethod = null;
    static boolean inVisualAge = false;
    static final long serialVersionUID = -1325822038990805636L;
    transient String className;
    transient String fileName;
    public String fullInfo;
    transient String lineNumber;
    transient String methodName;

    private static StringWriter f2802sw = new StringWriter();

    private static PrintWriter f2801pw = new PrintWriter(f2802sw);

    public static final String f2800NA = "?";
    public static final LocationInfo NA_LOCATION_INFO = new LocationInfo(f2800NA, f2800NA, f2800NA, f2800NA);

    static {
        inVisualAge = false;
        try {
            inVisualAge = Class.forName("com.ibm.uvm.tools.DebugSupport") != null;
            LogLog.debug("Detected IBM VisualAge environment.");
        } catch (Throwable unused) {
        }
        try {
            Class clsClass$ = class$java$lang$Throwable;
            if (clsClass$ == null) {
                clsClass$ = class$("java.lang.Throwable");
                class$java$lang$Throwable = clsClass$;
            }
            getStackTraceMethod = clsClass$.getMethod("getStackTrace", null);
            Class<?> cls = Class.forName("java.lang.StackTraceElement");
            getClassNameMethod = cls.getMethod("getClassName", null);
            getMethodNameMethod = cls.getMethod("getMethodName", null);
            getFileNameMethod = cls.getMethod("getFileName", null);
            getLineNumberMethod = cls.getMethod("getLineNumber", null);
        } catch (ClassNotFoundException unused2) {
            LogLog.debug("LocationInfo will use pre-JDK 1.4 methods to determine location.");
        } catch (NoSuchMethodException unused3) {
            LogLog.debug("LocationInfo will use pre-JDK 1.4 methods to determine location.");
        }
    }

    static Class class$(String str) throws Throwable {
        try {
            return Class.forName(str);
        } catch (ClassNotFoundException e) {
            throw new NoClassDefFoundError().initCause(e);
        }
    }

    public LocationInfo(Throwable th, String str) {
        String string;
        int i;
        int iIndexOf;
        int iLastIndexOf;
        if (th == null || str == null) {
            return;
        }
        if (getLineNumberMethod != null) {
            try {
                Object[] objArr = (Object[]) getStackTraceMethod.invoke(th, null);
                String str2 = f2800NA;
                int length = objArr.length - 1;
                while (length >= 0) {
                    String str3 = (String) getClassNameMethod.invoke(objArr[length], null);
                    if (str.equals(str3)) {
                        int i2 = length + 1;
                        if (i2 < objArr.length) {
                            this.className = str2;
                            this.methodName = (String) getMethodNameMethod.invoke(objArr[i2], null);
                            String str4 = (String) getFileNameMethod.invoke(objArr[i2], null);
                            this.fileName = str4;
                            if (str4 == null) {
                                this.fileName = f2800NA;
                            }
                            int iIntValue = ((Integer) getLineNumberMethod.invoke(objArr[i2], null)).intValue();
                            if (iIntValue < 0) {
                                this.lineNumber = f2800NA;
                            } else {
                                this.lineNumber = String.valueOf(iIntValue);
                            }
                            StringBuffer stringBuffer = new StringBuffer();
                            stringBuffer.append(this.className);
                            stringBuffer.append(".");
                            stringBuffer.append(this.methodName);
                            stringBuffer.append("(");
                            stringBuffer.append(this.fileName);
                            stringBuffer.append(":");
                            stringBuffer.append(this.lineNumber);
                            stringBuffer.append(")");
                            this.fullInfo = stringBuffer.toString();
                            return;
                        }
                        return;
                    }
                    length--;
                    str2 = str3;
                }
                return;
            } catch (IllegalAccessException e) {
                LogLog.debug("LocationInfo failed using JDK 1.4 methods", e);
            } catch (RuntimeException e2) {
                LogLog.debug("LocationInfo failed using JDK 1.4 methods", e2);
            } catch (InvocationTargetException e3) {
                if ((e3.getTargetException() instanceof InterruptedException) || (e3.getTargetException() instanceof InterruptedIOException)) {
                    Thread.currentThread().interrupt();
                }
                LogLog.debug("LocationInfo failed using JDK 1.4 methods", e3);
            }
        }
        synchronized (f2802sw) {
            th.printStackTrace(f2801pw);
            string = f2802sw.toString();
            f2802sw.getBuffer().setLength(0);
        }
        int iLastIndexOf2 = string.lastIndexOf(str);
        if (iLastIndexOf2 == -1) {
            return;
        }
        if (str.length() + iLastIndexOf2 < string.length() && string.charAt(str.length() + iLastIndexOf2) != '.' && (iLastIndexOf = string.lastIndexOf(new StringBuffer().append(str).append(".").toString())) != -1) {
            iLastIndexOf2 = iLastIndexOf;
        }
        int iIndexOf2 = string.indexOf(Layout.LINE_SEP, iLastIndexOf2);
        if (iIndexOf2 == -1 || (iIndexOf = string.indexOf(Layout.LINE_SEP, (i = iIndexOf2 + Layout.LINE_SEP_LEN))) == -1) {
            return;
        }
        if (!inVisualAge) {
            int iLastIndexOf3 = string.lastIndexOf("at ", iIndexOf);
            if (iLastIndexOf3 == -1) {
                return;
            } else {
                i = iLastIndexOf3 + 3;
            }
        }
        this.fullInfo = string.substring(i, iIndexOf);
    }

    private static final void appendFragment(StringBuffer stringBuffer, String str) {
        if (str == null) {
            stringBuffer.append(f2800NA);
        } else {
            stringBuffer.append(str);
        }
    }

    public LocationInfo(String str, String str2, String str3, String str4) {
        this.fileName = str;
        this.className = str2;
        this.methodName = str3;
        this.lineNumber = str4;
        StringBuffer stringBuffer = new StringBuffer();
        appendFragment(stringBuffer, str2);
        stringBuffer.append(".");
        appendFragment(stringBuffer, str3);
        stringBuffer.append("(");
        appendFragment(stringBuffer, str);
        stringBuffer.append(":");
        appendFragment(stringBuffer, str4);
        stringBuffer.append(")");
        this.fullInfo = stringBuffer.toString();
    }

    public String getClassName() {
        String str = this.fullInfo;
        if (str == null) {
            return f2800NA;
        }
        if (this.className == null) {
            int iLastIndexOf = str.lastIndexOf(40);
            if (iLastIndexOf == -1) {
                this.className = f2800NA;
            } else {
                int iLastIndexOf2 = this.fullInfo.lastIndexOf(46, iLastIndexOf);
                int iLastIndexOf3 = inVisualAge ? this.fullInfo.lastIndexOf(32, iLastIndexOf2) + 1 : 0;
                if (iLastIndexOf2 == -1) {
                    this.className = f2800NA;
                } else {
                    this.className = this.fullInfo.substring(iLastIndexOf3, iLastIndexOf2);
                }
            }
        }
        return this.className;
    }

    public String getFileName() {
        String str = this.fullInfo;
        if (str == null) {
            return f2800NA;
        }
        if (this.fileName == null) {
            int iLastIndexOf = str.lastIndexOf(58);
            if (iLastIndexOf == -1) {
                this.fileName = f2800NA;
            } else {
                this.fileName = this.fullInfo.substring(this.fullInfo.lastIndexOf(40, iLastIndexOf - 1) + 1, iLastIndexOf);
            }
        }
        return this.fileName;
    }

    public String getLineNumber() {
        String str = this.fullInfo;
        if (str == null) {
            return f2800NA;
        }
        if (this.lineNumber == null) {
            int iLastIndexOf = str.lastIndexOf(41);
            int iLastIndexOf2 = this.fullInfo.lastIndexOf(58, iLastIndexOf - 1);
            if (iLastIndexOf2 == -1) {
                this.lineNumber = f2800NA;
            } else {
                this.lineNumber = this.fullInfo.substring(iLastIndexOf2 + 1, iLastIndexOf);
            }
        }
        return this.lineNumber;
    }

    public String getMethodName() {
        String str = this.fullInfo;
        if (str == null) {
            return f2800NA;
        }
        if (this.methodName == null) {
            int iLastIndexOf = str.lastIndexOf(40);
            int iLastIndexOf2 = this.fullInfo.lastIndexOf(46, iLastIndexOf);
            if (iLastIndexOf2 == -1) {
                this.methodName = f2800NA;
            } else {
                this.methodName = this.fullInfo.substring(iLastIndexOf2 + 1, iLastIndexOf);
            }
        }
        return this.methodName;
    }
}
