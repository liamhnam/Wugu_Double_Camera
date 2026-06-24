package org.apache.log4j.spi;

import java.util.Enumeration;
import java.util.Vector;
import org.apache.log4j.Appender;
import org.apache.log4j.Category;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

public final class NOPLoggerRepository implements LoggerRepository {
    @Override
    public void addHierarchyEventListener(HierarchyEventListener hierarchyEventListener) {
    }

    @Override
    public void emitNoAppenderWarning(Category category) {
    }

    @Override
    public Logger exists(String str) {
        return null;
    }

    @Override
    public void fireAddAppenderEvent(Category category, Appender appender) {
    }

    @Override
    public boolean isDisabled(int i) {
        return true;
    }

    @Override
    public void resetConfiguration() {
    }

    @Override
    public void setThreshold(String str) {
    }

    @Override
    public void setThreshold(Level level) {
    }

    @Override
    public void shutdown() {
    }

    @Override
    public Level getThreshold() {
        return Level.OFF;
    }

    @Override
    public Logger getLogger(String str) {
        return new NOPLogger(this, str);
    }

    @Override
    public Logger getLogger(String str, LoggerFactory loggerFactory) {
        return new NOPLogger(this, str);
    }

    @Override
    public Logger getRootLogger() {
        return new NOPLogger(this, "root");
    }

    @Override
    public Enumeration getCurrentLoggers() {
        return new Vector().elements();
    }

    @Override
    public Enumeration getCurrentCategories() {
        return getCurrentLoggers();
    }
}
