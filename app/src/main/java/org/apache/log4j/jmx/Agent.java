package org.apache.log4j.jmx;

import java.io.InterruptedIOException;
import java.lang.reflect.InvocationTargetException;
import javax.management.JMException;
import javax.management.MBeanServer;
import javax.management.MBeanServerFactory;
import javax.management.ObjectName;
import org.apache.log4j.Logger;

public class Agent {
    static Class class$org$apache$log4j$jmx$Agent;
    static Logger log;

    static {
        Class clsClass$ = class$org$apache$log4j$jmx$Agent;
        if (clsClass$ == null) {
            clsClass$ = class$("org.apache.log4j.jmx.Agent");
            class$org$apache$log4j$jmx$Agent = clsClass$;
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

    private static Object createServer() {
        try {
            return Class.forName("com.sun.jdmk.comm.HtmlAdapterServer").newInstance();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e.toString());
        } catch (IllegalAccessException e2) {
            throw new RuntimeException(e2.toString());
        } catch (InstantiationException e3) {
            throw new RuntimeException(e3.toString());
        }
    }

    private static void startServer(Object obj) {
        try {
            obj.getClass().getMethod("start", new Class[0]).invoke(obj, new Object[0]);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e.toString());
        } catch (NoSuchMethodException e2) {
            throw new RuntimeException(e2.toString());
        } catch (InvocationTargetException e3) {
            Throwable targetException = e3.getTargetException();
            if (targetException instanceof RuntimeException) {
                throw ((RuntimeException) targetException);
            }
            if (targetException != null) {
                if ((targetException instanceof InterruptedException) || (targetException instanceof InterruptedIOException)) {
                    Thread.currentThread().interrupt();
                }
                throw new RuntimeException(targetException.toString());
            }
            throw new RuntimeException();
        }
    }

    public void start() {
        MBeanServer mBeanServerCreateMBeanServer = MBeanServerFactory.createMBeanServer();
        Object objCreateServer = createServer();
        try {
            log.info("Registering HtmlAdaptorServer instance.");
            mBeanServerCreateMBeanServer.registerMBean(objCreateServer, new ObjectName("Adaptor:name=html,port=8082"));
            log.info("Registering HierarchyDynamicMBean instance.");
            mBeanServerCreateMBeanServer.registerMBean(new HierarchyDynamicMBean(), new ObjectName("log4j:hiearchy=default"));
            startServer(objCreateServer);
        } catch (RuntimeException e) {
            log.error("Problem while registering MBeans instances.", e);
        } catch (JMException e2) {
            log.error("Problem while registering MBeans instances.", e2);
        }
    }
}
