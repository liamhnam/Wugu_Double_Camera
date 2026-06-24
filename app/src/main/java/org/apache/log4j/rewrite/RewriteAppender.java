package org.apache.log4j.rewrite;

import java.util.Enumeration;
import java.util.Properties;
import org.apache.log4j.Appender;
import org.apache.log4j.AppenderSkeleton;
import org.apache.log4j.helpers.AppenderAttachableImpl;
import org.apache.log4j.spi.AppenderAttachable;
import org.apache.log4j.spi.LoggingEvent;
import org.apache.log4j.spi.OptionHandler;
import org.apache.log4j.xml.DOMConfigurator;
import org.apache.log4j.xml.UnrecognizedElementHandler;
import org.w3c.dom.Element;

public class RewriteAppender extends AppenderSkeleton implements AppenderAttachable, UnrecognizedElementHandler {
    static Class class$org$apache$log4j$rewrite$RewritePolicy;
    private final AppenderAttachableImpl appenders = new AppenderAttachableImpl();
    private RewritePolicy policy;

    @Override
    public boolean requiresLayout() {
        return false;
    }

    @Override
    protected void append(LoggingEvent loggingEvent) {
        RewritePolicy rewritePolicy = this.policy;
        if (rewritePolicy != null) {
            loggingEvent = rewritePolicy.rewrite(loggingEvent);
        }
        if (loggingEvent != null) {
            synchronized (this.appenders) {
                this.appenders.appendLoopOnAppenders(loggingEvent);
            }
        }
    }

    @Override
    public void addAppender(Appender appender) {
        synchronized (this.appenders) {
            this.appenders.addAppender(appender);
        }
    }

    @Override
    public Enumeration getAllAppenders() {
        Enumeration allAppenders;
        synchronized (this.appenders) {
            allAppenders = this.appenders.getAllAppenders();
        }
        return allAppenders;
    }

    @Override
    public Appender getAppender(String str) {
        Appender appender;
        synchronized (this.appenders) {
            appender = this.appenders.getAppender(str);
        }
        return appender;
    }

    @Override
    public void close() {
        this.closed = true;
        synchronized (this.appenders) {
            Enumeration allAppenders = this.appenders.getAllAppenders();
            if (allAppenders != null) {
                while (allAppenders.hasMoreElements()) {
                    Object objNextElement = allAppenders.nextElement();
                    if (objNextElement instanceof Appender) {
                        ((Appender) objNextElement).close();
                    }
                }
            }
        }
    }

    @Override
    public boolean isAttached(Appender appender) {
        boolean zIsAttached;
        synchronized (this.appenders) {
            zIsAttached = this.appenders.isAttached(appender);
        }
        return zIsAttached;
    }

    @Override
    public void removeAllAppenders() {
        synchronized (this.appenders) {
            this.appenders.removeAllAppenders();
        }
    }

    @Override
    public void removeAppender(Appender appender) {
        synchronized (this.appenders) {
            this.appenders.removeAppender(appender);
        }
    }

    @Override
    public void removeAppender(String str) {
        synchronized (this.appenders) {
            this.appenders.removeAppender(str);
        }
    }

    public void setRewritePolicy(RewritePolicy rewritePolicy) {
        this.policy = rewritePolicy;
    }

    @Override
    public boolean parseUnrecognizedElement(Element element, Properties properties) throws Exception {
        if (!"rewritePolicy".equals(element.getNodeName())) {
            return false;
        }
        Class clsClass$ = class$org$apache$log4j$rewrite$RewritePolicy;
        if (clsClass$ == null) {
            clsClass$ = class$("org.apache.log4j.rewrite.RewritePolicy");
            class$org$apache$log4j$rewrite$RewritePolicy = clsClass$;
        }
        Object element2 = DOMConfigurator.parseElement(element, properties, clsClass$);
        if (element2 == null) {
            return true;
        }
        if (element2 instanceof OptionHandler) {
            ((OptionHandler) element2).activateOptions();
        }
        setRewritePolicy((RewritePolicy) element2);
        return true;
    }

    static Class class$(String str) throws Throwable {
        try {
            return Class.forName(str);
        } catch (ClassNotFoundException e) {
            throw new NoClassDefFoundError().initCause(e);
        }
    }
}
