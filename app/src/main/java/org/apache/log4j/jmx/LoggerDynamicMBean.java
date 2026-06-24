package org.apache.log4j.jmx;

import com.tom_roush.fontbox.ttf.NamingTable;
import java.beans.IntrospectionException;
import java.util.Enumeration;
import java.util.Vector;
import javax.management.Attribute;
import javax.management.AttributeNotFoundException;
import javax.management.InvalidAttributeValueException;
import javax.management.JMException;
import javax.management.MBeanAttributeInfo;
import javax.management.MBeanConstructorInfo;
import javax.management.MBeanException;
import javax.management.MBeanInfo;
import javax.management.MBeanNotificationInfo;
import javax.management.MBeanOperationInfo;
import javax.management.MBeanParameterInfo;
import javax.management.MalformedObjectNameException;
import javax.management.Notification;
import javax.management.NotificationListener;
import javax.management.ObjectName;
import javax.management.ReflectionException;
import javax.management.RuntimeOperationsException;
import org.apache.log4j.Appender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.helpers.DateLayout;
import org.apache.log4j.helpers.OptionConverter;

public class LoggerDynamicMBean extends AbstractDynamicMBean implements NotificationListener {
    private static Logger cat;
    static Class class$org$apache$log4j$Appender;
    static Class class$org$apache$log4j$jmx$LoggerDynamicMBean;
    private Logger logger;
    private MBeanConstructorInfo[] dConstructors = new MBeanConstructorInfo[1];
    private MBeanOperationInfo[] dOperations = new MBeanOperationInfo[1];
    private Vector dAttributes = new Vector();
    private String dClassName = getClass().getName();
    private String dDescription = "This MBean acts as a management facade for a org.apache.log4j.Logger instance.";

    static {
        Class clsClass$ = class$org$apache$log4j$jmx$LoggerDynamicMBean;
        if (clsClass$ == null) {
            clsClass$ = class$("org.apache.log4j.jmx.LoggerDynamicMBean");
            class$org$apache$log4j$jmx$LoggerDynamicMBean = clsClass$;
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

    public LoggerDynamicMBean(Logger logger) {
        this.logger = logger;
        buildDynamicMBeanInfo();
    }

    public void handleNotification(Notification notification, Object obj) {
        cat.debug(new StringBuffer("Received notification: ").append(notification.getType()).toString());
        registerAppenderMBean((Appender) notification.getUserData());
    }

    private void buildDynamicMBeanInfo() {
        this.dConstructors[0] = new MBeanConstructorInfo("HierarchyDynamicMBean(): Constructs a HierarchyDynamicMBean instance", getClass().getConstructors()[0]);
        this.dAttributes.add(new MBeanAttributeInfo(NamingTable.TAG, "java.lang.String", "The name of this Logger.", true, false, false));
        this.dAttributes.add(new MBeanAttributeInfo("priority", "java.lang.String", "The priority of this logger.", true, true, false));
        this.dOperations[0] = new MBeanOperationInfo("addAppender", "addAppender(): add an appender", new MBeanParameterInfo[]{new MBeanParameterInfo("class name", "java.lang.String", "add an appender to this logger"), new MBeanParameterInfo("appender name", "java.lang.String", "name of the appender")}, "void", 1);
    }

    @Override
    protected Logger getLogger() {
        return this.logger;
    }

    public MBeanInfo getMBeanInfo() {
        MBeanAttributeInfo[] mBeanAttributeInfoArr = new MBeanAttributeInfo[this.dAttributes.size()];
        this.dAttributes.toArray(mBeanAttributeInfoArr);
        return new MBeanInfo(this.dClassName, this.dDescription, mBeanAttributeInfoArr, this.dConstructors, this.dOperations, new MBeanNotificationInfo[0]);
    }

    public Object invoke(String str, Object[] objArr, String[] strArr) throws Throwable {
        if (!str.equals("addAppender")) {
            return null;
        }
        addAppender((String) objArr[0], (String) objArr[1]);
        return "Hello world.";
    }

    public Object getAttribute(String str) throws MBeanException, AttributeNotFoundException, ReflectionException, RuntimeOperationsException {
        if (str == null) {
            throw new RuntimeOperationsException(new IllegalArgumentException("Attribute name cannot be null"), new StringBuffer("Cannot invoke a getter of ").append(this.dClassName).append(" with null attribute name").toString());
        }
        if (str.equals(NamingTable.TAG)) {
            return this.logger.getName();
        }
        if (str.equals("priority")) {
            Level level = this.logger.getLevel();
            if (level == null) {
                return null;
            }
            return level.toString();
        }
        if (str.startsWith("appender=")) {
            try {
                return new ObjectName(new StringBuffer("log4j:").append(str).toString());
            } catch (RuntimeException unused) {
                cat.error(new StringBuffer("Could not create ObjectName").append(str).toString());
            } catch (MalformedObjectNameException unused2) {
                cat.error(new StringBuffer("Could not create ObjectName").append(str).toString());
            }
        }
        throw new AttributeNotFoundException(new StringBuffer("Cannot find ").append(str).append(" attribute in ").append(this.dClassName).toString());
    }

    void addAppender(String str, String str2) throws Throwable {
        cat.debug(new StringBuffer("addAppender called with ").append(str).append(", ").append(str2).toString());
        Class clsClass$ = class$org$apache$log4j$Appender;
        if (clsClass$ == null) {
            clsClass$ = class$("org.apache.log4j.Appender");
            class$org$apache$log4j$Appender = clsClass$;
        }
        Appender appender = (Appender) OptionConverter.instantiateByClassName(str, clsClass$, null);
        appender.setName(str2);
        this.logger.addAppender(appender);
    }

    public void setAttribute(Attribute attribute) throws InvalidAttributeValueException, MBeanException, AttributeNotFoundException, ReflectionException, RuntimeOperationsException {
        if (attribute == null) {
            throw new RuntimeOperationsException(new IllegalArgumentException("Attribute cannot be null"), new StringBuffer("Cannot invoke a setter of ").append(this.dClassName).append(" with null attribute").toString());
        }
        String name = attribute.getName();
        Object value = attribute.getValue();
        if (name == null) {
            throw new RuntimeOperationsException(new IllegalArgumentException("Attribute name cannot be null"), new StringBuffer("Cannot invoke the setter of ").append(this.dClassName).append(" with null attribute name").toString());
        }
        if (name.equals("priority")) {
            if (value instanceof String) {
                String str = (String) value;
                this.logger.setLevel(str.equalsIgnoreCase(DateLayout.NULL_DATE_FORMAT) ? null : OptionConverter.toLevel(str, this.logger.getLevel()));
                return;
            }
            return;
        }
        throw new AttributeNotFoundException(new StringBuffer("Attribute ").append(name).append(" not found in ").append(getClass().getName()).toString());
    }

    void appenderMBeanRegistration() {
        Enumeration allAppenders = this.logger.getAllAppenders();
        while (allAppenders.hasMoreElements()) {
            registerAppenderMBean((Appender) allAppenders.nextElement());
        }
    }

    void registerAppenderMBean(Appender appender) {
        String appenderName = getAppenderName(appender);
        cat.debug(new StringBuffer("Adding AppenderMBean for appender named ").append(appenderName).toString());
        try {
            AppenderDynamicMBean appenderDynamicMBean = new AppenderDynamicMBean(appender);
            ObjectName objectName = new ObjectName("log4j", "appender", appenderName);
            if (this.server.isRegistered(objectName)) {
                return;
            }
            registerMBean(appenderDynamicMBean, objectName);
            this.dAttributes.add(new MBeanAttributeInfo(new StringBuffer("appender=").append(appenderName).toString(), "javax.management.ObjectName", new StringBuffer("The ").append(appenderName).append(" appender.").toString(), true, true, false));
        } catch (JMException e) {
            cat.error(new StringBuffer("Could not add appenderMBean for [").append(appenderName).append("].").toString(), e);
        } catch (IntrospectionException e2) {
            cat.error(new StringBuffer("Could not add appenderMBean for [").append(appenderName).append("].").toString(), e2);
        } catch (RuntimeException e3) {
            cat.error(new StringBuffer("Could not add appenderMBean for [").append(appenderName).append("].").toString(), e3);
        }
    }

    @Override
    public void postRegister(Boolean bool) {
        appenderMBeanRegistration();
    }
}
