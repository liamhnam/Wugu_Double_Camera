package org.apache.log4j.spi;

public abstract class Filter implements OptionHandler {
    public static final int ACCEPT = 1;
    public static final int DENY = -1;
    public static final int NEUTRAL = 0;
    public Filter next;

    @Override
    public void activateOptions() {
    }

    public abstract int decide(LoggingEvent loggingEvent);

    public void setNext(Filter filter) {
        this.next = filter;
    }

    public Filter getNext() {
        return this.next;
    }
}
