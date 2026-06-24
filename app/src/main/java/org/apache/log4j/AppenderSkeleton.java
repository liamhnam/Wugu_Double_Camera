package org.apache.log4j;

import org.apache.log4j.helpers.LogLog;
import org.apache.log4j.helpers.OnlyOnceErrorHandler;
import org.apache.log4j.spi.ErrorHandler;
import org.apache.log4j.spi.Filter;
import org.apache.log4j.spi.LoggingEvent;
import org.apache.log4j.spi.OptionHandler;

public abstract class AppenderSkeleton implements Appender, OptionHandler {
    protected Filter headFilter;
    protected Layout layout;
    protected String name;
    protected Filter tailFilter;
    protected Priority threshold;
    protected ErrorHandler errorHandler = new OnlyOnceErrorHandler();
    protected boolean closed = false;

    @Override
    public void activateOptions() {
    }

    protected abstract void append(LoggingEvent loggingEvent);

    public AppenderSkeleton() {
    }

    protected AppenderSkeleton(boolean z) {
    }

    @Override
    public void addFilter(Filter filter) {
        if (this.headFilter == null) {
            this.tailFilter = filter;
            this.headFilter = filter;
        } else {
            this.tailFilter.setNext(filter);
            this.tailFilter = filter;
        }
    }

    @Override
    public void clearFilters() {
        this.tailFilter = null;
        this.headFilter = null;
    }

    public void finalize() {
        if (this.closed) {
            return;
        }
        LogLog.debug(new StringBuffer("Finalizing appender named [").append(this.name).append("].").toString());
        close();
    }

    @Override
    public ErrorHandler getErrorHandler() {
        return this.errorHandler;
    }

    @Override
    public Filter getFilter() {
        return this.headFilter;
    }

    public final Filter getFirstFilter() {
        return this.headFilter;
    }

    @Override
    public Layout getLayout() {
        return this.layout;
    }

    @Override
    public final String getName() {
        return this.name;
    }

    public Priority getThreshold() {
        return this.threshold;
    }

    public boolean isAsSevereAsThreshold(Priority priority) {
        Priority priority2 = this.threshold;
        return priority2 == null || priority.isGreaterOrEqual(priority2);
    }

    @Override
    public synchronized void doAppend(LoggingEvent loggingEvent) {
        if (this.closed) {
            LogLog.error(new StringBuffer("Attempted to append to closed appender named [").append(this.name).append("].").toString());
            return;
        }
        if (isAsSevereAsThreshold(loggingEvent.getLevel())) {
            Filter next = this.headFilter;
            while (next != null) {
                int iDecide = next.decide(loggingEvent);
                if (iDecide == -1) {
                    return;
                }
                if (iDecide == 0) {
                    next = next.getNext();
                } else if (iDecide == 1) {
                    break;
                }
            }
            append(loggingEvent);
        }
    }

    @Override
    public synchronized void setErrorHandler(ErrorHandler errorHandler) {
        if (errorHandler == null) {
            LogLog.warn("You have tried to set a null error-handler.");
        } else {
            this.errorHandler = errorHandler;
        }
    }

    @Override
    public void setLayout(Layout layout) {
        this.layout = layout;
    }

    @Override
    public void setName(String str) {
        this.name = str;
    }

    public void setThreshold(Priority priority) {
        this.threshold = priority;
    }
}
