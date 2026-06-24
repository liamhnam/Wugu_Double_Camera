package org.apache.log4j.helpers;

import org.apache.log4j.spi.LoggingEvent;

public class BoundedFIFO {
    LoggingEvent[] buf;
    int maxSize;
    int numElements = 0;
    int first = 0;
    int next = 0;

    int min(int i, int i2) {
        return i < i2 ? i : i2;
    }

    public BoundedFIFO(int i) {
        if (i < 1) {
            throw new IllegalArgumentException(new StringBuffer("The maxSize argument (").append(i).append(") is not a positive integer.").toString());
        }
        this.maxSize = i;
        this.buf = new LoggingEvent[i];
    }

    public LoggingEvent get() {
        int i = this.numElements;
        if (i == 0) {
            return null;
        }
        LoggingEvent[] loggingEventArr = this.buf;
        int i2 = this.first;
        LoggingEvent loggingEvent = loggingEventArr[i2];
        loggingEventArr[i2] = null;
        int i3 = i2 + 1;
        this.first = i3;
        if (i3 == this.maxSize) {
            this.first = 0;
        }
        this.numElements = i - 1;
        return loggingEvent;
    }

    public void put(LoggingEvent loggingEvent) {
        int i = this.numElements;
        int i2 = this.maxSize;
        if (i != i2) {
            LoggingEvent[] loggingEventArr = this.buf;
            int i3 = this.next;
            loggingEventArr[i3] = loggingEvent;
            int i4 = i3 + 1;
            this.next = i4;
            if (i4 == i2) {
                this.next = 0;
            }
            this.numElements = i + 1;
        }
    }

    public int getMaxSize() {
        return this.maxSize;
    }

    public boolean isFull() {
        return this.numElements == this.maxSize;
    }

    public int length() {
        return this.numElements;
    }

    public synchronized void resize(int i) {
        int iMin;
        int i2 = this.maxSize;
        if (i == i2) {
            return;
        }
        LoggingEvent[] loggingEventArr = new LoggingEvent[i];
        int iMin2 = min(min(i2 - this.first, i), this.numElements);
        System.arraycopy(this.buf, this.first, loggingEventArr, 0, iMin2);
        int i3 = this.numElements;
        if (iMin2 >= i3 || iMin2 >= i) {
            iMin = 0;
        } else {
            iMin = min(i3 - iMin2, i - iMin2);
            System.arraycopy(this.buf, 0, loggingEventArr, iMin2, iMin);
        }
        this.buf = loggingEventArr;
        this.maxSize = i;
        this.first = 0;
        int i4 = iMin2 + iMin;
        this.numElements = i4;
        this.next = i4;
        if (i4 == i) {
            this.next = 0;
        }
    }

    public boolean wasEmpty() {
        return this.numElements == 1;
    }

    public boolean wasFull() {
        return this.numElements + 1 == this.maxSize;
    }
}
