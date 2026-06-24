package org.apache.log4j.jmx;

import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.InterruptedIOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Hashtable;
import java.util.Vector;
import javax.management.Attribute;
import javax.management.AttributeNotFoundException;
import javax.management.MBeanAttributeInfo;
import javax.management.MBeanConstructorInfo;
import javax.management.MBeanException;
import javax.management.MBeanInfo;
import javax.management.MBeanNotificationInfo;
import javax.management.MBeanOperationInfo;
import javax.management.MBeanParameterInfo;
import javax.management.ReflectionException;
import javax.management.RuntimeOperationsException;
import org.apache.log4j.Layout;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.helpers.OptionConverter;
import org.apache.log4j.spi.OptionHandler;

public class LayoutDynamicMBean extends AbstractDynamicMBean {
    private static Logger cat;
    static Class class$java$lang$String;
    static Class class$org$apache$log4j$Level;
    static Class class$org$apache$log4j$Priority;
    static Class class$org$apache$log4j$jmx$LayoutDynamicMBean;
    private Layout layout;
    private MBeanConstructorInfo[] dConstructors = new MBeanConstructorInfo[1];
    private Vector dAttributes = new Vector();
    private String dClassName = getClass().getName();
    private Hashtable dynamicProps = new Hashtable(5);
    private MBeanOperationInfo[] dOperations = new MBeanOperationInfo[1];
    private String dDescription = "This MBean acts as a management facade for log4j layouts.";

    static {
        Class clsClass$ = class$org$apache$log4j$jmx$LayoutDynamicMBean;
        if (clsClass$ == null) {
            clsClass$ = class$("org.apache.log4j.jmx.LayoutDynamicMBean");
            class$org$apache$log4j$jmx$LayoutDynamicMBean = clsClass$;
        }
        cat = Logger.getLogger(clsClass$);
    }

    static Class class$(String str) throws Throwable {
        try {
            return Class.forName(str);
        } catch (ClassNotFoundException e) {
            throw new NoClassDefFoundError().initCause(e);
        }
    }

    public LayoutDynamicMBean(Layout layout) throws Throwable {
        this.layout = layout;
        buildDynamicMBeanInfo();
    }

    private void buildDynamicMBeanInfo() throws Throwable {
        int i = 0;
        this.dConstructors[0] = new MBeanConstructorInfo("LayoutDynamicMBean(): Constructs a LayoutDynamicMBean instance", getClass().getConstructors()[0]);
        PropertyDescriptor[] propertyDescriptors = Introspector.getBeanInfo(this.layout.getClass()).getPropertyDescriptors();
        int length = propertyDescriptors.length;
        int i2 = 0;
        while (i2 < length) {
            String name = propertyDescriptors[i2].getName();
            Method readMethod = propertyDescriptors[i2].getReadMethod();
            Method writeMethod = propertyDescriptors[i2].getWriteMethod();
            if (readMethod != null) {
                Class<?> returnType = readMethod.getReturnType();
                if (isSupportedType(returnType)) {
                    Class<?> clsClass$ = class$org$apache$log4j$Level;
                    if (clsClass$ == null) {
                        clsClass$ = class$("org.apache.log4j.Level");
                        class$org$apache$log4j$Level = clsClass$;
                    }
                    this.dAttributes.add(new MBeanAttributeInfo(name, returnType.isAssignableFrom(clsClass$) ? "java.lang.String" : returnType.getName(), "Dynamic", true, writeMethod != null ? 1 : i, false));
                    this.dynamicProps.put(name, new MethodUnion(readMethod, writeMethod));
                }
            }
            i2++;
            i = 0;
        }
        this.dOperations[0] = new MBeanOperationInfo("activateOptions", "activateOptions(): add an layout", new MBeanParameterInfo[i], "void", 1);
    }

    private boolean isSupportedType(Class cls) throws Throwable {
        if (cls.isPrimitive()) {
            return true;
        }
        Class clsClass$ = class$java$lang$String;
        if (clsClass$ == null) {
            clsClass$ = class$("java.lang.String");
            class$java$lang$String = clsClass$;
        }
        if (cls == clsClass$) {
            return true;
        }
        Class<?> clsClass$2 = class$org$apache$log4j$Level;
        if (clsClass$2 == null) {
            clsClass$2 = class$("org.apache.log4j.Level");
            class$org$apache$log4j$Level = clsClass$2;
        }
        return cls.isAssignableFrom(clsClass$2);
    }

    public MBeanInfo getMBeanInfo() {
        cat.debug("getMBeanInfo called.");
        MBeanAttributeInfo[] mBeanAttributeInfoArr = new MBeanAttributeInfo[this.dAttributes.size()];
        this.dAttributes.toArray(mBeanAttributeInfoArr);
        return new MBeanInfo(this.dClassName, this.dDescription, mBeanAttributeInfoArr, this.dConstructors, this.dOperations, new MBeanNotificationInfo[0]);
    }

    public Object invoke(String str, Object[] objArr, String[] strArr) throws MBeanException, ReflectionException {
        if (!str.equals("activateOptions")) {
            return null;
        }
        Layout layout = this.layout;
        if (!(layout instanceof OptionHandler)) {
            return null;
        }
        layout.activateOptions();
        return "Options activated.";
    }

    @Override
    protected Logger getLogger() {
        return cat;
    }

    public Object getAttribute(String str) throws MBeanException, AttributeNotFoundException, ReflectionException, RuntimeOperationsException {
        if (str == null) {
            throw new RuntimeOperationsException(new IllegalArgumentException("Attribute name cannot be null"), new StringBuffer("Cannot invoke a getter of ").append(this.dClassName).append(" with null attribute name").toString());
        }
        MethodUnion methodUnion = (MethodUnion) this.dynamicProps.get(str);
        cat.debug(new StringBuffer("----name=").append(str).append(", mu=").append(methodUnion).toString());
        if (methodUnion != null && methodUnion.readMethod != null) {
            try {
                return methodUnion.readMethod.invoke(this.layout, null);
            } catch (IllegalAccessException | RuntimeException unused) {
                return null;
            } catch (InvocationTargetException e) {
                if ((e.getTargetException() instanceof InterruptedException) || (e.getTargetException() instanceof InterruptedIOException)) {
                    Thread.currentThread().interrupt();
                }
                return null;
            }
        }
        throw new AttributeNotFoundException(new StringBuffer("Cannot find ").append(str).append(" attribute in ").append(this.dClassName).toString());
    }

    public void setAttribute(Attribute attribute) throws Throwable {
        if (attribute == null) {
            throw new RuntimeOperationsException(new IllegalArgumentException("Attribute cannot be null"), new StringBuffer("Cannot invoke a setter of ").append(this.dClassName).append(" with null attribute").toString());
        }
        String name = attribute.getName();
        Object value = attribute.getValue();
        if (name == null) {
            throw new RuntimeOperationsException(new IllegalArgumentException("Attribute name cannot be null"), new StringBuffer("Cannot invoke the setter of ").append(this.dClassName).append(" with null attribute name").toString());
        }
        MethodUnion methodUnion = (MethodUnion) this.dynamicProps.get(name);
        if (methodUnion != null && methodUnion.writeMethod != null) {
            Object[] objArr = new Object[1];
            Class<?> cls = methodUnion.writeMethod.getParameterTypes()[0];
            Class<?> clsClass$ = class$org$apache$log4j$Priority;
            if (clsClass$ == null) {
                clsClass$ = class$("org.apache.log4j.Priority");
                class$org$apache$log4j$Priority = clsClass$;
            }
            if (cls == clsClass$) {
                value = OptionConverter.toLevel((String) value, (Level) getAttribute(name));
            }
            objArr[0] = value;
            try {
                methodUnion.writeMethod.invoke(this.layout, objArr);
                return;
            } catch (IllegalAccessException e) {
                cat.error("FIXME", e);
                return;
            } catch (RuntimeException e2) {
                cat.error("FIXME", e2);
                return;
            } catch (InvocationTargetException e3) {
                if ((e3.getTargetException() instanceof InterruptedException) || (e3.getTargetException() instanceof InterruptedIOException)) {
                    Thread.currentThread().interrupt();
                }
                cat.error("FIXME", e3);
                return;
            }
        }
        throw new AttributeNotFoundException(new StringBuffer("Attribute ").append(name).append(" not found in ").append(getClass().getName()).toString());
    }
}
