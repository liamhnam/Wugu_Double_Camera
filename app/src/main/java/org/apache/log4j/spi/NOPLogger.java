package org.apache.log4j.spi;

import java.util.Enumeration;
import java.util.ResourceBundle;
import java.util.Vector;
import org.apache.log4j.Appender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.Priority;

public final class NOPLogger extends Logger {
    @Override
    public void addAppender(Appender appender) {
    }

    @Override
    public void assertLog(boolean z, String str) {
    }

    @Override
    public void callAppenders(LoggingEvent loggingEvent) {
    }

    void closeNestedAppenders() {
    }

    @Override
    public void debug(Object obj) {
    }

    @Override
    public void debug(Object obj, Throwable th) {
    }

    @Override
    public void error(Object obj) {
    }

    @Override
    public void error(Object obj, Throwable th) {
    }

    @Override
    public void fatal(Object obj) {
    }

    @Override
    public void fatal(Object obj, Throwable th) {
    }

    @Override
    public Appender getAppender(String str) {
        return null;
    }

    @Override
    public ResourceBundle getResourceBundle() {
        return null;
    }

    @Override
    public void info(Object obj) {
    }

    @Override
    public void info(Object obj, Throwable th) {
    }

    @Override
    public boolean isAttached(Appender appender) {
        return false;
    }

    @Override
    public boolean isDebugEnabled() {
        return false;
    }

    @Override
    public boolean isEnabledFor(Priority priority) {
        return false;
    }

    @Override
    public boolean isInfoEnabled() {
        return false;
    }

    @Override
    public boolean isTraceEnabled() {
        return false;
    }

    @Override
    public void l7dlog(Priority priority, String str, Throwable th) {
    }

    @Override
    public void l7dlog(Priority priority, String str, Object[] objArr, Throwable th) {
    }

    @Override
    public void log(String str, Priority priority, Object obj, Throwable th) {
    }

    @Override
    public void log(Priority priority, Object obj) {
    }

    @Override
    public void log(Priority priority, Object obj, Throwable th) {
    }

    @Override
    public void removeAllAppenders() {
    }

    @Override
    public void removeAppender(String str) {
    }

    @Override
    public void removeAppender(Appender appender) {
    }

    @Override
    public void setLevel(Level level) {
    }

    @Override
    public void setPriority(Priority priority) {
    }

    @Override
    public void setResourceBundle(ResourceBundle resourceBundle) {
    }

    @Override
    public void trace(Object obj) {
    }

    @Override
    public void trace(Object obj, Throwable th) {
    }

    @Override
    public void warn(Object obj) {
    }

    @Override
    public void warn(Object obj, Throwable th) {
    }

    public NOPLogger(NOPLoggerRepository nOPLoggerRepository, String str) {
        super(str);
        this.repository = nOPLoggerRepository;
        this.level = Level.OFF;
        this.parent = this;
    }

    @Override
    public Enumeration getAllAppenders() {
        return new Vector().elements();
    }

    @Override
    public Level getEffectiveLevel() {
        return Level.OFF;
    }

    @Override
    public Priority getChainedPriority() {
        return getEffectiveLevel();
    }
}
