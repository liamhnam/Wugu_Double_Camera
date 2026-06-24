package org.apache.log4j;

import org.apache.log4j.helpers.AppenderAttachableImpl;
import org.apache.log4j.helpers.BoundedFIFO;

class Dispatcher extends Thread {
    private AppenderAttachableImpl aai;

    private BoundedFIFO f2783bf;
    AsyncAppender container;
    private boolean interrupted = false;

    Dispatcher(BoundedFIFO boundedFIFO, AsyncAppender asyncAppender) {
        this.f2783bf = boundedFIFO;
        this.container = asyncAppender;
        this.aai = asyncAppender.aai;
        setDaemon(true);
        setPriority(1);
        setName(new StringBuffer("Dispatcher-").append(getName()).toString());
    }

    void close() {
        synchronized (this.f2783bf) {
            this.interrupted = true;
            if (this.f2783bf.length() == 0) {
                this.f2783bf.notify();
            }
        }
    }

    @Override
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void run() {
        /*
            r3 = this;
        L0:
            org.apache.log4j.helpers.BoundedFIFO r0 = r3.f2783bf
            monitor-enter(r0)
            org.apache.log4j.helpers.BoundedFIFO r1 = r3.f2783bf     // Catch: java.lang.Throwable -> L45
            int r1 = r1.length()     // Catch: java.lang.Throwable -> L45
            if (r1 != 0) goto L1e
            boolean r1 = r3.interrupted     // Catch: java.lang.Throwable -> L45
            if (r1 == 0) goto L11
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L45
            goto L18
        L11:
            org.apache.log4j.helpers.BoundedFIFO r1 = r3.f2783bf     // Catch: java.lang.InterruptedException -> L17 java.lang.Throwable -> L45
            r1.wait()     // Catch: java.lang.InterruptedException -> L17 java.lang.Throwable -> L45
            goto L1e
        L17:
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L45
        L18:
            org.apache.log4j.helpers.AppenderAttachableImpl r0 = r3.aai
            r0.removeAllAppenders()
            return
        L1e:
            org.apache.log4j.helpers.BoundedFIFO r1 = r3.f2783bf     // Catch: java.lang.Throwable -> L45
            org.apache.log4j.spi.LoggingEvent r1 = r1.get()     // Catch: java.lang.Throwable -> L45
            org.apache.log4j.helpers.BoundedFIFO r2 = r3.f2783bf     // Catch: java.lang.Throwable -> L45
            boolean r2 = r2.wasFull()     // Catch: java.lang.Throwable -> L45
            if (r2 == 0) goto L31
            org.apache.log4j.helpers.BoundedFIFO r2 = r3.f2783bf     // Catch: java.lang.Throwable -> L45
            r2.notify()     // Catch: java.lang.Throwable -> L45
        L31:
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L45
            org.apache.log4j.AsyncAppender r0 = r3.container
            org.apache.log4j.helpers.AppenderAttachableImpl r2 = r0.aai
            monitor-enter(r2)
            org.apache.log4j.helpers.AppenderAttachableImpl r0 = r3.aai     // Catch: java.lang.Throwable -> L42
            if (r0 == 0) goto L40
            if (r1 == 0) goto L40
            r0.appendLoopOnAppenders(r1)     // Catch: java.lang.Throwable -> L42
        L40:
            monitor-exit(r2)     // Catch: java.lang.Throwable -> L42
            goto L0
        L42:
            r0 = move-exception
            monitor-exit(r2)     // Catch: java.lang.Throwable -> L42
            throw r0
        L45:
            r1 = move-exception
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L45
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.log4j.Dispatcher.run():void");
    }
}
