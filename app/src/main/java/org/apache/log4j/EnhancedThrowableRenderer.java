package org.apache.log4j;

import java.io.File;
import java.lang.reflect.Method;
import java.net.URL;
import java.security.CodeSource;
import java.util.HashMap;
import java.util.Map;
import org.apache.log4j.spi.ThrowableRenderer;

public final class EnhancedThrowableRenderer implements ThrowableRenderer {
    static Class class$java$lang$Throwable;
    private Method getClassNameMethod;
    private Method getStackTraceMethod;

    public EnhancedThrowableRenderer() throws Throwable {
        try {
            Class clsClass$ = class$java$lang$Throwable;
            if (clsClass$ == null) {
                clsClass$ = class$("java.lang.Throwable");
                class$java$lang$Throwable = clsClass$;
            }
            this.getStackTraceMethod = clsClass$.getMethod("getStackTrace", null);
            this.getClassNameMethod = Class.forName("java.lang.StackTraceElement").getMethod("getClassName", null);
        } catch (Exception unused) {
        }
    }

    static Class class$(String str) throws Throwable {
        try {
            return Class.forName(str);
        } catch (ClassNotFoundException e) {
            throw new NoClassDefFoundError().initCause(e);
        }
    }

    @Override
    public String[] doRender(Throwable th) {
        Method method = this.getStackTraceMethod;
        if (method != null) {
            try {
                Object[] objArr = (Object[]) method.invoke(th, null);
                String[] strArr = new String[objArr.length + 1];
                int i = 0;
                strArr[0] = th.toString();
                HashMap map = new HashMap();
                while (i < objArr.length) {
                    int i2 = i + 1;
                    strArr[i2] = formatElement(objArr[i], map);
                    i = i2;
                }
                return strArr;
            } catch (Exception unused) {
            }
        }
        return DefaultThrowableRenderer.render(th);
    }

    private String formatElement(Object obj, Map map) {
        String implementationVersion;
        URL location;
        StringBuffer stringBuffer = new StringBuffer("\tat ");
        stringBuffer.append(obj);
        try {
            String string = this.getClassNameMethod.invoke(obj, null).toString();
            Object obj2 = map.get(string);
            if (obj2 != null) {
                stringBuffer.append(obj2);
            } else {
                Class clsFindClass = findClass(string);
                int length = stringBuffer.length();
                stringBuffer.append('[');
                try {
                    CodeSource codeSource = clsFindClass.getProtectionDomain().getCodeSource();
                    if (codeSource != null && (location = codeSource.getLocation()) != null) {
                        if ("file".equals(location.getProtocol())) {
                            String path = location.getPath();
                            if (path != null) {
                                int iLastIndexOf = path.lastIndexOf(47);
                                int iLastIndexOf2 = path.lastIndexOf(File.separatorChar);
                                if (iLastIndexOf2 > iLastIndexOf) {
                                    iLastIndexOf = iLastIndexOf2;
                                }
                                if (iLastIndexOf <= 0 || iLastIndexOf == path.length() - 1) {
                                    stringBuffer.append(location);
                                } else {
                                    stringBuffer.append(path.substring(iLastIndexOf + 1));
                                }
                            }
                        } else {
                            stringBuffer.append(location);
                        }
                    }
                } catch (SecurityException unused) {
                }
                stringBuffer.append(':');
                Package r1 = clsFindClass.getPackage();
                if (r1 != null && (implementationVersion = r1.getImplementationVersion()) != null) {
                    stringBuffer.append(implementationVersion);
                }
                stringBuffer.append(']');
                map.put(string, stringBuffer.substring(length));
            }
        } catch (Exception unused2) {
        }
        return stringBuffer.toString();
    }

    private Class findClass(String str) throws ClassNotFoundException {
        try {
            try {
                return Thread.currentThread().getContextClassLoader().loadClass(str);
            } catch (ClassNotFoundException unused) {
                return getClass().getClassLoader().loadClass(str);
            }
        } catch (ClassNotFoundException unused2) {
            return Class.forName(str);
        }
    }
}
