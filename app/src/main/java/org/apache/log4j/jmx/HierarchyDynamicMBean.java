package org.apache.log4j.jmx;

import com.tom_roush.fontbox.ttf.NamingTable;
import java.util.Vector;
import javax.management.Attribute;
import javax.management.AttributeNotFoundException;
import javax.management.JMException;
import javax.management.ListenerNotFoundException;
import javax.management.MBeanAttributeInfo;
import javax.management.MBeanConstructorInfo;
import javax.management.MBeanException;
import javax.management.MBeanInfo;
import javax.management.MBeanNotificationInfo;
import javax.management.MBeanOperationInfo;
import javax.management.MBeanParameterInfo;
import javax.management.Notification;
import javax.management.NotificationBroadcaster;
import javax.management.NotificationBroadcasterSupport;
import javax.management.NotificationFilter;
import javax.management.NotificationFilterSupport;
import javax.management.NotificationListener;
import javax.management.ObjectName;
import javax.management.ReflectionException;
import javax.management.RuntimeOperationsException;
import org.apache.log4j.Appender;
import org.apache.log4j.Category;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.helpers.OptionConverter;
import org.apache.log4j.spi.HierarchyEventListener;
import org.apache.log4j.spi.LoggerRepository;

public class HierarchyDynamicMBean extends AbstractDynamicMBean implements HierarchyEventListener, NotificationBroadcaster {
    static final String ADD_APPENDER = "addAppender.";
    static final String THRESHOLD = "threshold";
    static Class class$org$apache$log4j$jmx$HierarchyDynamicMBean;
    private static Logger log;
    private MBeanConstructorInfo[] dConstructors = new MBeanConstructorInfo[1];
    private MBeanOperationInfo[] dOperations = new MBeanOperationInfo[1];
    private Vector vAttributes = new Vector();
    private String dClassName = getClass().getName();
    private String dDescription = "This MBean acts as a management facade for org.apache.log4j.Hierarchy.";
    private NotificationBroadcasterSupport nbs = new NotificationBroadcasterSupport();
    private LoggerRepository hierarchy = LogManager.getLoggerRepository();

    static {
        Class clsClass$ = class$org$apache$log4j$jmx$HierarchyDynamicMBean;
        if (clsClass$ == null) {
            clsClass$ = class$("org.apache.log4j.jmx.HierarchyDynamicMBean");
            class$org$apache$log4j$jmx$HierarchyDynamicMBean = clsClass$;
        }
        log = Logger.getLogger(clsClass$);
    }

    static Class class$(String str) throws Throwable {
        try {
            return Class.forName(str);
        } catch (ClassNotFoundException e) {
            throw new NoClassDefFoundError().initCause(e);
        }
    }

    public HierarchyDynamicMBean() {
        buildDynamicMBeanInfo();
    }

    private void buildDynamicMBeanInfo() {
        this.dConstructors[0] = new MBeanConstructorInfo("HierarchyDynamicMBean(): Constructs a HierarchyDynamicMBean instance", getClass().getConstructors()[0]);
        this.vAttributes.add(new MBeanAttributeInfo(THRESHOLD, "java.lang.String", "The \"threshold\" state of the hiearchy.", true, true, false));
        this.dOperations[0] = new MBeanOperationInfo("addLoggerMBean", "addLoggerMBean(): add a loggerMBean", new MBeanParameterInfo[]{new MBeanParameterInfo(NamingTable.TAG, "java.lang.String", "Create a logger MBean")}, "javax.management.ObjectName", 1);
    }

    public ObjectName addLoggerMBean(String str) {
        Logger loggerExists = LogManager.exists(str);
        if (loggerExists != null) {
            return addLoggerMBean(loggerExists);
        }
        return null;
    }

    ObjectName addLoggerMBean(Logger logger) {
        String name = logger.getName();
        ObjectName objectName = null;
        try {
            LoggerDynamicMBean loggerDynamicMBean = new LoggerDynamicMBean(logger);
            ObjectName objectName2 = new ObjectName("log4j", "logger", name);
            try {
                if (this.server.isRegistered(objectName2)) {
                    return objectName2;
                }
                registerMBean(loggerDynamicMBean, objectName2);
                NotificationFilterSupport notificationFilterSupport = new NotificationFilterSupport();
                notificationFilterSupport.enableType(new StringBuffer(ADD_APPENDER).append(logger.getName()).toString());
                log.debug(new StringBuffer("---Adding logger [").append(name).append("] as listener.").toString());
                this.nbs.addNotificationListener(loggerDynamicMBean, notificationFilterSupport, (Object) null);
                this.vAttributes.add(new MBeanAttributeInfo(new StringBuffer("logger=").append(name).toString(), "javax.management.ObjectName", new StringBuffer("The ").append(name).append(" logger.").toString(), true, true, false));
                return objectName2;
            } catch (RuntimeException e) {
                e = e;
                objectName = objectName2;
                log.error(new StringBuffer("Could not add loggerMBean for [").append(name).append("].").toString(), e);
                return objectName;
            } catch (JMException e2) {
                e = e2;
                objectName = objectName2;
                log.error(new StringBuffer("Could not add loggerMBean for [").append(name).append("].").toString(), e);
                return objectName;
            }
        } catch (JMException e3) {
            e = e3;
        } catch (RuntimeException e4) {
            e = e4;
        }
    }

    public void addNotificationListener(NotificationListener notificationListener, NotificationFilter notificationFilter, Object obj) {
        this.nbs.addNotificationListener(notificationListener, notificationFilter, obj);
    }

    @Override
    protected Logger getLogger() {
        return log;
    }

    public MBeanInfo getMBeanInfo() {
        MBeanAttributeInfo[] mBeanAttributeInfoArr = new MBeanAttributeInfo[this.vAttributes.size()];
        this.vAttributes.toArray(mBeanAttributeInfoArr);
        return new MBeanInfo(this.dClassName, this.dDescription, mBeanAttributeInfoArr, this.dConstructors, this.dOperations, new MBeanNotificationInfo[0]);
    }

    public MBeanNotificationInfo[] getNotificationInfo() {
        return this.nbs.getNotificationInfo();
    }

    public Object invoke(String str, Object[] objArr, String[] strArr) throws MBeanException, ReflectionException, RuntimeOperationsException {
        if (str == null) {
            throw new RuntimeOperationsException(new IllegalArgumentException("Operation name cannot be null"), new StringBuffer("Cannot invoke a null operation in ").append(this.dClassName).toString());
        }
        if (str.equals("addLoggerMBean")) {
            return addLoggerMBean((String) objArr[0]);
        }
        throw new ReflectionException(new NoSuchMethodException(str), new StringBuffer("Cannot find the operation ").append(str).append(" in ").append(this.dClassName).toString());
    }

    public Object getAttribute(String str) throws MBeanException, AttributeNotFoundException, ReflectionException, RuntimeOperationsException {
        if (str == null) {
            throw new RuntimeOperationsException(new IllegalArgumentException("Attribute name cannot be null"), new StringBuffer("Cannot invoke a getter of ").append(this.dClassName).append(" with null attribute name").toString());
        }
        log.debug(new StringBuffer("Called getAttribute with [").append(str).append("].").toString());
        if (str.equals(THRESHOLD)) {
            return this.hierarchy.getThreshold();
        }
        if (str.startsWith("logger")) {
            int iIndexOf = str.indexOf("%3D");
            String string = iIndexOf > 0 ? new StringBuffer().append(str.substring(0, iIndexOf)).append('=').append(str.substring(iIndexOf + 3)).toString() : str;
            try {
                return new ObjectName(new StringBuffer("log4j:").append(string).toString());
            } catch (RuntimeException unused) {
                log.error(new StringBuffer("Could not create ObjectName").append(string).toString());
            } catch (JMException unused2) {
                log.error(new StringBuffer("Could not create ObjectName").append(string).toString());
            }
        }
        throw new AttributeNotFoundException(new StringBuffer("Cannot find ").append(str).append(" attribute in ").append(this.dClassName).toString());
    }

    @Override
    public void addAppenderEvent(Category category, Appender appender) {
        log.debug(new StringBuffer("addAppenderEvent called: logger=").append(category.getName()).append(", appender=").append(appender.getName()).toString());
        Notification notification = new Notification(new StringBuffer(ADD_APPENDER).append(category.getName()).toString(), this, 0L);
        notification.setUserData(appender);
        log.debug("sending notification.");
        this.nbs.sendNotification(notification);
    }

    @Override
    public void removeAppenderEvent(Category category, Appender appender) {
        log.debug(new StringBuffer("removeAppenderCalled: logger=").append(category.getName()).append(", appender=").append(appender.getName()).toString());
    }

    @Override
    public void postRegister(Boolean bool) {
        log.debug("postRegister is called.");
        this.hierarchy.addHierarchyEventListener(this);
        addLoggerMBean(this.hierarchy.getRootLogger());
    }

    public void removeNotificationListener(NotificationListener notificationListener) throws ListenerNotFoundException {
        this.nbs.removeNotificationListener(notificationListener);
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
        if (name.equals(THRESHOLD)) {
            this.hierarchy.setThreshold(OptionConverter.toLevel((String) value, this.hierarchy.getThreshold()));
        }
    }
}
