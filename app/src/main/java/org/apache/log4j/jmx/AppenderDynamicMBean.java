package org.apache.log4j.jmx;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.InterruptedIOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Hashtable;
import java.util.Vector;
import javax.management.Attribute;
import javax.management.AttributeNotFoundException;
import javax.management.JMException;
import javax.management.MBeanAttributeInfo;
import javax.management.MBeanConstructorInfo;
import javax.management.MBeanException;
import javax.management.MBeanInfo;
import javax.management.MBeanNotificationInfo;
import javax.management.MBeanOperationInfo;
import javax.management.MBeanParameterInfo;
import javax.management.MBeanServer;
import javax.management.MalformedObjectNameException;
import javax.management.ObjectName;
import javax.management.ReflectionException;
import javax.management.RuntimeOperationsException;
import org.apache.log4j.Appender;
import org.apache.log4j.Layout;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.helpers.OptionConverter;
import org.apache.log4j.spi.OptionHandler;

public class AppenderDynamicMBean extends AbstractDynamicMBean {
    private static Logger cat;
    static Class class$java$lang$String;
    static Class class$org$apache$log4j$Layout;
    static Class class$org$apache$log4j$Priority;
    static Class class$org$apache$log4j$jmx$AppenderDynamicMBean;
    private Appender appender;
    private MBeanConstructorInfo[] dConstructors = new MBeanConstructorInfo[1];
    private Vector dAttributes = new Vector();
    private String dClassName = getClass().getName();
    private Hashtable dynamicProps = new Hashtable(5);
    private MBeanOperationInfo[] dOperations = new MBeanOperationInfo[2];
    private String dDescription = "This MBean acts as a management facade for log4j appenders.";

    static {
        Class clsClass$ = class$org$apache$log4j$jmx$AppenderDynamicMBean;
        if (clsClass$ == null) {
            clsClass$ = class$("org.apache.log4j.jmx.AppenderDynamicMBean");
            class$org$apache$log4j$jmx$AppenderDynamicMBean = clsClass$;
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

    public AppenderDynamicMBean(Appender appender) throws Throwable {
        this.appender = appender;
        buildDynamicMBeanInfo();
    }

    private void buildDynamicMBeanInfo() throws Throwable {
        int i = 0;
        this.dConstructors[0] = new MBeanConstructorInfo("AppenderDynamicMBean(): Constructs a AppenderDynamicMBean instance", getClass().getConstructors()[0]);
        PropertyDescriptor[] propertyDescriptors = Introspector.getBeanInfo(this.appender.getClass()).getPropertyDescriptors();
        int length = propertyDescriptors.length;
        int i2 = 0;
        while (true) {
            if (i2 < length) {
                String name = propertyDescriptors[i2].getName();
                Method readMethod = propertyDescriptors[i2].getReadMethod();
                Method writeMethod = propertyDescriptors[i2].getWriteMethod();
                if (readMethod != null) {
                    Class<?> returnType = readMethod.getReturnType();
                    if (isSupportedType(returnType)) {
                        Class<?> clsClass$ = class$org$apache$log4j$Priority;
                        if (clsClass$ == null) {
                            clsClass$ = class$("org.apache.log4j.Priority");
                            class$org$apache$log4j$Priority = clsClass$;
                        }
                        this.dAttributes.add(new MBeanAttributeInfo(name, returnType.isAssignableFrom(clsClass$) ? "java.lang.String" : returnType.getName(), "Dynamic", true, writeMethod == null ? i : 1, false));
                        this.dynamicProps.put(name, new MethodUnion(readMethod, writeMethod));
                    }
                }
                i2++;
                i = 0;
            } else {
                this.dOperations[0] = new MBeanOperationInfo("activateOptions", "activateOptions(): add an appender", new MBeanParameterInfo[i], "void", 1);
                this.dOperations[1] = new MBeanOperationInfo("setLayout", "setLayout(): add a layout", new MBeanParameterInfo[]{new MBeanParameterInfo("layout class", "java.lang.String", "layout class")}, "void", 1);
                return;
            }
        }
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
        Class<?> clsClass$2 = class$org$apache$log4j$Priority;
        if (clsClass$2 == null) {
            clsClass$2 = class$("org.apache.log4j.Priority");
            class$org$apache$log4j$Priority = clsClass$2;
        }
        return cls.isAssignableFrom(clsClass$2);
    }

    public MBeanInfo getMBeanInfo() {
        cat.debug("getMBeanInfo called.");
        MBeanAttributeInfo[] mBeanAttributeInfoArr = new MBeanAttributeInfo[this.dAttributes.size()];
        this.dAttributes.toArray(mBeanAttributeInfoArr);
        return new MBeanInfo(this.dClassName, this.dDescription, mBeanAttributeInfoArr, this.dConstructors, this.dOperations, new MBeanNotificationInfo[0]);
    }

    public Object invoke(String str, Object[] objArr, String[] strArr) throws Throwable {
        if (str.equals("activateOptions")) {
            Appender appender = this.appender;
            if (appender instanceof OptionHandler) {
                ((OptionHandler) appender).activateOptions();
                return "Options activated.";
            }
        }
        if (str.equals("setLayout")) {
            String str2 = (String) objArr[0];
            Class clsClass$ = class$org$apache$log4j$Layout;
            if (clsClass$ == null) {
                clsClass$ = class$("org.apache.log4j.Layout");
                class$org$apache$log4j$Layout = clsClass$;
            }
            Layout layout = (Layout) OptionConverter.instantiateByClassName(str2, clsClass$, null);
            this.appender.setLayout(layout);
            registerLayoutMBean(layout);
        }
        return null;
    }

    void registerLayoutMBean(Layout layout) {
        if (layout == null) {
            return;
        }
        String string = new StringBuffer().append(getAppenderName(this.appender)).append(",layout=").append(layout.getClass().getName()).toString();
        cat.debug(new StringBuffer("Adding LayoutMBean:").append(string).toString());
        try {
            LayoutDynamicMBean layoutDynamicMBean = new LayoutDynamicMBean(layout);
            ObjectName objectName = new ObjectName(new StringBuffer("log4j:appender=").append(string).toString());
            if (this.server.isRegistered(objectName)) {
                return;
            }
            registerMBean(layoutDynamicMBean, objectName);
            this.dAttributes.add(new MBeanAttributeInfo(new StringBuffer("appender=").append(string).toString(), "javax.management.ObjectName", new StringBuffer("The ").append(string).append(" layout.").toString(), true, true, false));
        } catch (JMException e) {
            cat.error(new StringBuffer("Could not add DynamicLayoutMBean for [").append(string).append("].").toString(), e);
        } catch (IntrospectionException e2) {
            cat.error(new StringBuffer("Could not add DynamicLayoutMBean for [").append(string).append("].").toString(), e2);
        } catch (RuntimeException e3) {
            cat.error(new StringBuffer("Could not add DynamicLayoutMBean for [").append(string).append("].").toString(), e3);
        }
    }

    @Override
    protected Logger getLogger() {
        return cat;
    }

    public Object getAttribute(String str) throws MBeanException, AttributeNotFoundException, ReflectionException, RuntimeOperationsException {
        if (str == null) {
            throw new RuntimeOperationsException(new IllegalArgumentException("Attribute name cannot be null"), new StringBuffer("Cannot invoke a getter of ").append(this.dClassName).append(" with null attribute name").toString());
        }
        cat.debug(new StringBuffer("getAttribute called with [").append(str).append("].").toString());
        if (str.startsWith(new StringBuffer("appender=").append(this.appender.getName()).append(",layout").toString())) {
            try {
                return new ObjectName(new StringBuffer("log4j:").append(str).toString());
            } catch (MalformedObjectNameException e) {
                cat.error("attributeName", e);
            } catch (RuntimeException e2) {
                cat.error("attributeName", e2);
            }
        }
        MethodUnion methodUnion = (MethodUnion) this.dynamicProps.get(str);
        if (methodUnion != null && methodUnion.readMethod != null) {
            try {
                return methodUnion.readMethod.invoke(this.appender, null);
            } catch (IllegalAccessException unused) {
                return null;
            } catch (RuntimeException unused2) {
                return null;
            } catch (InvocationTargetException e3) {
                if ((e3.getTargetException() instanceof InterruptedException) || (e3.getTargetException() instanceof InterruptedIOException)) {
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
                methodUnion.writeMethod.invoke(this.appender, objArr);
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
        if (!name.endsWith(".layout")) {
            throw new AttributeNotFoundException(new StringBuffer("Attribute ").append(name).append(" not found in ").append(getClass().getName()).toString());
        }
    }

    @Override
    public ObjectName preRegister(MBeanServer mBeanServer, ObjectName objectName) {
        cat.debug(new StringBuffer("preRegister called. Server=").append(mBeanServer).append(", name=").append(objectName).toString());
        this.server = mBeanServer;
        registerLayoutMBean(this.appender.getLayout());
        return objectName;
    }
}
