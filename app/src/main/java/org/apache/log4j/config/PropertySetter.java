package org.apache.log4j.config;

import com.google.android.exoplayer2.text.ttml.TtmlNode;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.InterruptedIOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Enumeration;
import java.util.Properties;
import org.apache.log4j.Appender;
import org.apache.log4j.Level;
import org.apache.log4j.helpers.LogLog;
import org.apache.log4j.helpers.OptionConverter;
import org.apache.log4j.spi.OptionHandler;

public class PropertySetter {
    static Class class$java$lang$String;
    static Class class$org$apache$log4j$Priority;
    static Class class$org$apache$log4j$spi$ErrorHandler;
    static Class class$org$apache$log4j$spi$OptionHandler;
    protected Object obj;
    protected PropertyDescriptor[] props;

    public PropertySetter(Object obj) {
        this.obj = obj;
    }

    protected void introspect() {
        try {
            this.props = Introspector.getBeanInfo(this.obj.getClass()).getPropertyDescriptors();
        } catch (IntrospectionException e) {
            LogLog.error(new StringBuffer("Failed to introspect ").append(this.obj).append(": ").append(e.getMessage()).toString());
            this.props = new PropertyDescriptor[0];
        }
    }

    public static void setProperties(Object obj, Properties properties, String str) {
        new PropertySetter(obj).setProperties(properties, str);
    }

    public void setProperties(Properties properties, String str) {
        int length = str.length();
        Enumeration<?> enumerationPropertyNames = properties.propertyNames();
        while (enumerationPropertyNames.hasMoreElements()) {
            String str2 = (String) enumerationPropertyNames.nextElement();
            if (str2.startsWith(str) && str2.indexOf(46, length + 1) <= 0) {
                String strFindAndSubst = OptionConverter.findAndSubst(str2, properties);
                String strSubstring = str2.substring(length);
                if ((!TtmlNode.TAG_LAYOUT.equals(strSubstring) && !"errorhandler".equals(strSubstring)) || !(this.obj instanceof Appender)) {
                    PropertyDescriptor propertyDescriptor = getPropertyDescriptor(Introspector.decapitalize(strSubstring));
                    if (propertyDescriptor != null) {
                        Class clsClass$ = class$org$apache$log4j$spi$OptionHandler;
                        if (clsClass$ == null) {
                            clsClass$ = class$("org.apache.log4j.spi.OptionHandler");
                            class$org$apache$log4j$spi$OptionHandler = clsClass$;
                        }
                        if (clsClass$.isAssignableFrom(propertyDescriptor.getPropertyType()) && propertyDescriptor.getWriteMethod() != null) {
                            OptionHandler optionHandler = (OptionHandler) OptionConverter.instantiateByKey(properties, new StringBuffer().append(str).append(strSubstring).toString(), propertyDescriptor.getPropertyType(), null);
                            new PropertySetter(optionHandler).setProperties(properties, new StringBuffer().append(str).append(strSubstring).append(".").toString());
                            try {
                                propertyDescriptor.getWriteMethod().invoke(this.obj, optionHandler);
                            } catch (IllegalAccessException e) {
                                LogLog.warn(new StringBuffer("Failed to set property [").append(strSubstring).append("] to value \"").append(strFindAndSubst).append("\". ").toString(), e);
                            } catch (RuntimeException e2) {
                                LogLog.warn(new StringBuffer("Failed to set property [").append(strSubstring).append("] to value \"").append(strFindAndSubst).append("\". ").toString(), e2);
                            } catch (InvocationTargetException e3) {
                                if ((e3.getTargetException() instanceof InterruptedException) || (e3.getTargetException() instanceof InterruptedIOException)) {
                                    Thread.currentThread().interrupt();
                                }
                                LogLog.warn(new StringBuffer("Failed to set property [").append(strSubstring).append("] to value \"").append(strFindAndSubst).append("\". ").toString(), e3);
                            }
                        }
                    }
                    setProperty(strSubstring, strFindAndSubst);
                }
            }
        }
        activate();
    }

    static Class class$(String str) throws Throwable {
        try {
            return Class.forName(str);
        } catch (ClassNotFoundException e) {
            throw new NoClassDefFoundError().initCause(e);
        }
    }

    public void setProperty(String str, String str2) {
        if (str2 == null) {
            return;
        }
        String strDecapitalize = Introspector.decapitalize(str);
        PropertyDescriptor propertyDescriptor = getPropertyDescriptor(strDecapitalize);
        if (propertyDescriptor == null) {
            LogLog.warn(new StringBuffer("No such property [").append(strDecapitalize).append("] in ").append(this.obj.getClass().getName()).append(".").toString());
            return;
        }
        try {
            setProperty(propertyDescriptor, strDecapitalize, str2);
        } catch (PropertySetterException e) {
            LogLog.warn(new StringBuffer("Failed to set property [").append(strDecapitalize).append("] to value \"").append(str2).append("\". ").toString(), e.rootCause);
        }
    }

    public void setProperty(PropertyDescriptor propertyDescriptor, String str, String str2) throws PropertySetterException {
        Method writeMethod = propertyDescriptor.getWriteMethod();
        if (writeMethod == null) {
            throw new PropertySetterException(new StringBuffer("No setter for property [").append(str).append("].").toString());
        }
        Class<?>[] parameterTypes = writeMethod.getParameterTypes();
        if (parameterTypes.length != 1) {
            throw new PropertySetterException("#params for setter != 1");
        }
        try {
            Object objConvertArg = convertArg(str2, parameterTypes[0]);
            if (objConvertArg == null) {
                throw new PropertySetterException(new StringBuffer("Conversion to type [").append(parameterTypes[0]).append("] failed.").toString());
            }
            LogLog.debug(new StringBuffer("Setting property [").append(str).append("] to [").append(objConvertArg).append("].").toString());
            try {
                writeMethod.invoke(this.obj, objConvertArg);
            } catch (IllegalAccessException e) {
                throw new PropertySetterException(e);
            } catch (RuntimeException e2) {
                throw new PropertySetterException(e2);
            } catch (InvocationTargetException e3) {
                if ((e3.getTargetException() instanceof InterruptedException) || (e3.getTargetException() instanceof InterruptedIOException)) {
                    Thread.currentThread().interrupt();
                }
                throw new PropertySetterException(e3);
            }
        } catch (Throwable th) {
            throw new PropertySetterException(new StringBuffer("Conversion to type [").append(parameterTypes[0]).append("] failed. Reason: ").append(th).toString());
        }
    }

    protected Object convertArg(String str, Class cls) throws Throwable {
        if (str == null) {
            return null;
        }
        String strTrim = str.trim();
        Class clsClass$ = class$java$lang$String;
        if (clsClass$ == null) {
            clsClass$ = class$("java.lang.String");
            class$java$lang$String = clsClass$;
        }
        if (clsClass$.isAssignableFrom(cls)) {
            return str;
        }
        if (Integer.TYPE.isAssignableFrom(cls)) {
            return new Integer(strTrim);
        }
        if (Long.TYPE.isAssignableFrom(cls)) {
            return new Long(strTrim);
        }
        if (Boolean.TYPE.isAssignableFrom(cls)) {
            if ("true".equalsIgnoreCase(strTrim)) {
                return Boolean.TRUE;
            }
            if ("false".equalsIgnoreCase(strTrim)) {
                return Boolean.FALSE;
            }
        } else {
            Class clsClass$2 = class$org$apache$log4j$Priority;
            if (clsClass$2 == null) {
                clsClass$2 = class$("org.apache.log4j.Priority");
                class$org$apache$log4j$Priority = clsClass$2;
            }
            if (clsClass$2.isAssignableFrom(cls)) {
                return OptionConverter.toLevel(strTrim, Level.DEBUG);
            }
            Class clsClass$3 = class$org$apache$log4j$spi$ErrorHandler;
            if (clsClass$3 == null) {
                clsClass$3 = class$("org.apache.log4j.spi.ErrorHandler");
                class$org$apache$log4j$spi$ErrorHandler = clsClass$3;
            }
            if (clsClass$3.isAssignableFrom(cls)) {
                Class clsClass$4 = class$org$apache$log4j$spi$ErrorHandler;
                if (clsClass$4 == null) {
                    clsClass$4 = class$("org.apache.log4j.spi.ErrorHandler");
                    class$org$apache$log4j$spi$ErrorHandler = clsClass$4;
                }
                return OptionConverter.instantiateByClassName(strTrim, clsClass$4, null);
            }
        }
        return null;
    }

    protected PropertyDescriptor getPropertyDescriptor(String str) {
        if (this.props == null) {
            introspect();
        }
        int i = 0;
        while (true) {
            PropertyDescriptor[] propertyDescriptorArr = this.props;
            if (i >= propertyDescriptorArr.length) {
                return null;
            }
            if (str.equals(propertyDescriptorArr[i].getName())) {
                return this.props[i];
            }
            i++;
        }
    }

    public void activate() {
        Object obj = this.obj;
        if (obj instanceof OptionHandler) {
            ((OptionHandler) obj).activateOptions();
        }
    }
}
