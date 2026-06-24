package org.apache.log4j.config;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.InterruptedIOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import org.apache.log4j.helpers.LogLog;

public class PropertyGetter {
    protected static final Object[] NULL_ARG = new Object[0];
    static Class class$java$lang$String;
    static Class class$org$apache$log4j$Priority;
    protected Object obj;
    protected PropertyDescriptor[] props;

    public interface PropertyCallback {
        void foundProperty(Object obj, String str, String str2, Object obj2);
    }

    public PropertyGetter(Object obj) throws IntrospectionException {
        this.props = Introspector.getBeanInfo(obj.getClass()).getPropertyDescriptors();
        this.obj = obj;
    }

    public static void getProperties(Object obj, PropertyCallback propertyCallback, String str) {
        try {
            new PropertyGetter(obj).getProperties(propertyCallback, str);
        } catch (IntrospectionException e) {
            LogLog.error(new StringBuffer("Failed to introspect object ").append(obj).toString(), e);
        }
    }

    public void getProperties(PropertyCallback propertyCallback, String str) {
        int i = 0;
        while (true) {
            PropertyDescriptor[] propertyDescriptorArr = this.props;
            if (i >= propertyDescriptorArr.length) {
                return;
            }
            Method readMethod = propertyDescriptorArr[i].getReadMethod();
            if (readMethod != null && isHandledType(readMethod.getReturnType())) {
                String name = this.props[i].getName();
                try {
                    Object objInvoke = readMethod.invoke(this.obj, NULL_ARG);
                    if (objInvoke != null) {
                        propertyCallback.foundProperty(this.obj, str, name, objInvoke);
                    }
                } catch (IllegalAccessException unused) {
                    LogLog.warn(new StringBuffer("Failed to get value of property ").append(name).toString());
                } catch (RuntimeException unused2) {
                    LogLog.warn(new StringBuffer("Failed to get value of property ").append(name).toString());
                } catch (InvocationTargetException e) {
                    if ((e.getTargetException() instanceof InterruptedException) || (e.getTargetException() instanceof InterruptedIOException)) {
                        Thread.currentThread().interrupt();
                    }
                    LogLog.warn(new StringBuffer("Failed to get value of property ").append(name).toString());
                }
            }
            i++;
        }
    }

    static Class class$(String str) throws Throwable {
        try {
            return Class.forName(str);
        } catch (ClassNotFoundException e) {
            throw new NoClassDefFoundError().initCause(e);
        }
    }

    protected boolean isHandledType(Class cls) throws Throwable {
        Class clsClass$ = class$java$lang$String;
        if (clsClass$ == null) {
            clsClass$ = class$("java.lang.String");
            class$java$lang$String = clsClass$;
        }
        if (!clsClass$.isAssignableFrom(cls) && !Integer.TYPE.isAssignableFrom(cls) && !Long.TYPE.isAssignableFrom(cls) && !Boolean.TYPE.isAssignableFrom(cls)) {
            Class clsClass$2 = class$org$apache$log4j$Priority;
            if (clsClass$2 == null) {
                clsClass$2 = class$("org.apache.log4j.Priority");
                class$org$apache$log4j$Priority = clsClass$2;
            }
            if (!clsClass$2.isAssignableFrom(cls)) {
                return false;
            }
        }
        return true;
    }
}
