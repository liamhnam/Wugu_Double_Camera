package org.apache.log4j;

import com.arthenica.ffmpegkit.MediaInformation;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.log4j.helpers.AppenderAttachableImpl;
import org.apache.log4j.helpers.LogLog;
import org.apache.log4j.spi.AppenderAttachable;
import org.apache.log4j.spi.LoggingEvent;

public class AsyncAppender extends AppenderSkeleton implements AppenderAttachable {
    public static final int DEFAULT_BUFFER_SIZE = 128;
    AppenderAttachableImpl aai;
    private final AppenderAttachableImpl appenders;
    private boolean blocking;
    private final List buffer;
    private int bufferSize;
    private final Map discardMap;
    private final Thread dispatcher;
    private boolean locationInfo;

    @Override
    public boolean requiresLayout() {
        return false;
    }

    public AsyncAppender() {
        ArrayList arrayList = new ArrayList();
        this.buffer = arrayList;
        HashMap map = new HashMap();
        this.discardMap = map;
        this.bufferSize = 128;
        this.locationInfo = false;
        this.blocking = true;
        AppenderAttachableImpl appenderAttachableImpl = new AppenderAttachableImpl();
        this.appenders = appenderAttachableImpl;
        this.aai = appenderAttachableImpl;
        Thread thread = new Thread(new Dispatcher(this, arrayList, map, appenderAttachableImpl));
        this.dispatcher = thread;
        thread.setDaemon(true);
        thread.setName(new StringBuffer("AsyncAppender-Dispatcher-").append(thread.getName()).toString());
        thread.start();
    }

    @Override
    public void addAppender(Appender appender) {
        synchronized (this.appenders) {
            this.appenders.addAppender(appender);
        }
    }

    @Override
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void append(org.apache.log4j.spi.LoggingEvent r4) {
        /*
            r3 = this;
            java.lang.Thread r0 = r3.dispatcher
            if (r0 == 0) goto L84
            boolean r0 = r0.isAlive()
            if (r0 == 0) goto L84
            int r0 = r3.bufferSize
            if (r0 > 0) goto L10
            goto L84
        L10:
            r4.getNDC()
            r4.getThreadName()
            r4.getMDCCopy()
            boolean r0 = r3.locationInfo
            if (r0 == 0) goto L20
            r4.getLocationInformation()
        L20:
            r4.getRenderedMessage()
            r4.getThrowableStrRep()
            java.util.List r0 = r3.buffer
            monitor-enter(r0)
        L29:
            java.util.List r1 = r3.buffer     // Catch: java.lang.Throwable -> L81
            int r1 = r1.size()     // Catch: java.lang.Throwable -> L81
            int r2 = r3.bufferSize     // Catch: java.lang.Throwable -> L81
            if (r1 >= r2) goto L40
            java.util.List r2 = r3.buffer     // Catch: java.lang.Throwable -> L81
            r2.add(r4)     // Catch: java.lang.Throwable -> L81
            if (r1 != 0) goto L7f
            java.util.List r4 = r3.buffer     // Catch: java.lang.Throwable -> L81
            r4.notifyAll()     // Catch: java.lang.Throwable -> L81
            goto L7f
        L40:
            boolean r1 = r3.blocking     // Catch: java.lang.Throwable -> L81
            if (r1 == 0) goto L60
            boolean r1 = java.lang.Thread.interrupted()     // Catch: java.lang.Throwable -> L81
            if (r1 != 0) goto L60
            java.lang.Thread r1 = java.lang.Thread.currentThread()     // Catch: java.lang.Throwable -> L81
            java.lang.Thread r2 = r3.dispatcher     // Catch: java.lang.Throwable -> L81
            if (r1 == r2) goto L60
            java.util.List r1 = r3.buffer     // Catch: java.lang.InterruptedException -> L59 java.lang.Throwable -> L81
            r1.wait()     // Catch: java.lang.InterruptedException -> L59 java.lang.Throwable -> L81
            r1 = 0
            goto L61
        L59:
            java.lang.Thread r1 = java.lang.Thread.currentThread()     // Catch: java.lang.Throwable -> L81
            r1.interrupt()     // Catch: java.lang.Throwable -> L81
        L60:
            r1 = 1
        L61:
            if (r1 == 0) goto L29
            java.lang.String r1 = r4.getLoggerName()     // Catch: java.lang.Throwable -> L81
            java.util.Map r2 = r3.discardMap     // Catch: java.lang.Throwable -> L81
            java.lang.Object r2 = r2.get(r1)     // Catch: java.lang.Throwable -> L81
            org.apache.log4j.AsyncAppender$DiscardSummary r2 = (org.apache.log4j.AsyncAppender.DiscardSummary) r2     // Catch: java.lang.Throwable -> L81
            if (r2 != 0) goto L7c
            org.apache.log4j.AsyncAppender$DiscardSummary r2 = new org.apache.log4j.AsyncAppender$DiscardSummary     // Catch: java.lang.Throwable -> L81
            r2.<init>(r4)     // Catch: java.lang.Throwable -> L81
            java.util.Map r4 = r3.discardMap     // Catch: java.lang.Throwable -> L81
            r4.put(r1, r2)     // Catch: java.lang.Throwable -> L81
            goto L7f
        L7c:
            r2.add(r4)     // Catch: java.lang.Throwable -> L81
        L7f:
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L81
            return
        L81:
            r4 = move-exception
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L81
            throw r4
        L84:
            org.apache.log4j.helpers.AppenderAttachableImpl r0 = r3.appenders
            monitor-enter(r0)
            org.apache.log4j.helpers.AppenderAttachableImpl r1 = r3.appenders     // Catch: java.lang.Throwable -> L8e
            r1.appendLoopOnAppenders(r4)     // Catch: java.lang.Throwable -> L8e
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L8e
            return
        L8e:
            r4 = move-exception
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L8e
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.log4j.AsyncAppender.append(org.apache.log4j.spi.LoggingEvent):void");
    }

    @Override
    public void close() {
        synchronized (this.buffer) {
            this.closed = true;
            this.buffer.notifyAll();
        }
        try {
            this.dispatcher.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            LogLog.error("Got an InterruptedException while waiting for the dispatcher to finish.", e);
        }
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

    public boolean getLocationInfo() {
        return this.locationInfo;
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

    public void setLocationInfo(boolean z) {
        this.locationInfo = z;
    }

    public void setBufferSize(int i) {
        if (i < 0) {
            throw new NegativeArraySizeException(MediaInformation.KEY_SIZE);
        }
        synchronized (this.buffer) {
            if (i < 1) {
                i = 1;
            }
            this.bufferSize = i;
            this.buffer.notifyAll();
        }
    }

    public int getBufferSize() {
        return this.bufferSize;
    }

    public void setBlocking(boolean z) {
        synchronized (this.buffer) {
            this.blocking = z;
            this.buffer.notifyAll();
        }
    }

    public boolean getBlocking() {
        return this.blocking;
    }

    private static final class DiscardSummary {
        private int count = 1;
        private LoggingEvent maxEvent;

        public DiscardSummary(LoggingEvent loggingEvent) {
            this.maxEvent = loggingEvent;
        }

        public void add(LoggingEvent loggingEvent) {
            if (loggingEvent.getLevel().toInt() > this.maxEvent.getLevel().toInt()) {
                this.maxEvent = loggingEvent;
            }
            this.count++;
        }

        public LoggingEvent createEvent() {
            return new LoggingEvent("org.apache.log4j.AsyncAppender.DONT_REPORT_LOCATION", Logger.getLogger(this.maxEvent.getLoggerName()), this.maxEvent.getLevel(), MessageFormat.format("Discarded {0} messages due to full event buffer including: {1}", new Integer(this.count), this.maxEvent.getMessage()), null);
        }
    }

    private static class Dispatcher implements Runnable {
        private final AppenderAttachableImpl appenders;
        private final List buffer;
        private final Map discardMap;
        private final AsyncAppender parent;

        public Dispatcher(AsyncAppender asyncAppender, List list, Map map, AppenderAttachableImpl appenderAttachableImpl) {
            this.parent = asyncAppender;
            this.buffer = list;
            this.appenders = appenderAttachableImpl;
            this.discardMap = map;
        }

        @Override
        public void run() {
            boolean z;
            LoggingEvent[] loggingEventArr;
            boolean z2 = true;
            while (z2) {
                try {
                    synchronized (this.buffer) {
                        int size = this.buffer.size();
                        boolean z3 = this.parent.closed;
                        while (true) {
                            z = !z3;
                            if (size != 0 || !z) {
                                break;
                            }
                            this.buffer.wait();
                            size = this.buffer.size();
                            z3 = this.parent.closed;
                        }
                        if (size > 0) {
                            loggingEventArr = new LoggingEvent[this.discardMap.size() + size];
                            this.buffer.toArray(loggingEventArr);
                            Iterator it = this.discardMap.values().iterator();
                            while (it.hasNext()) {
                                loggingEventArr[size] = ((DiscardSummary) it.next()).createEvent();
                                size++;
                            }
                            this.buffer.clear();
                            this.discardMap.clear();
                            this.buffer.notifyAll();
                        } else {
                            loggingEventArr = null;
                        }
                    }
                    if (loggingEventArr != null) {
                        for (LoggingEvent loggingEvent : loggingEventArr) {
                            synchronized (this.appenders) {
                                this.appenders.appendLoopOnAppenders(loggingEvent);
                            }
                        }
                    }
                    z2 = z;
                } catch (InterruptedException unused) {
                    Thread.currentThread().interrupt();
                    return;
                }
            }
        }
    }
}
