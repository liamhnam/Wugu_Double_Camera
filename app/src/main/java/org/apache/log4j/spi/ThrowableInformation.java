package org.apache.log4j.spi;

import java.io.Serializable;
import org.apache.log4j.Category;

public class ThrowableInformation implements Serializable {
    static final long serialVersionUID = -4748765566864322735L;
    private transient Category category;
    private String[] rep;
    private transient Throwable throwable;

    public ThrowableInformation(Throwable th) {
        this.throwable = th;
    }

    public ThrowableInformation(Throwable th, Category category) {
        this.throwable = th;
        this.category = category;
    }

    public ThrowableInformation(String[] strArr) {
        if (strArr != null) {
            this.rep = (String[]) strArr.clone();
        }
    }

    public Throwable getThrowable() {
        return this.throwable;
    }

    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public synchronized java.lang.String[] getThrowableStrRep() {
        /*
            r2 = this;
            monitor-enter(r2)
            java.lang.String[] r0 = r2.rep     // Catch: java.lang.Throwable -> L38
            if (r0 != 0) goto L2c
            org.apache.log4j.Category r0 = r2.category     // Catch: java.lang.Throwable -> L38
            if (r0 == 0) goto L18
            org.apache.log4j.spi.LoggerRepository r0 = r0.getLoggerRepository()     // Catch: java.lang.Throwable -> L38
            boolean r1 = r0 instanceof org.apache.log4j.spi.ThrowableRendererSupport     // Catch: java.lang.Throwable -> L38
            if (r1 == 0) goto L18
            org.apache.log4j.spi.ThrowableRendererSupport r0 = (org.apache.log4j.spi.ThrowableRendererSupport) r0     // Catch: java.lang.Throwable -> L38
            org.apache.log4j.spi.ThrowableRenderer r0 = r0.getThrowableRenderer()     // Catch: java.lang.Throwable -> L38
            goto L19
        L18:
            r0 = 0
        L19:
            if (r0 != 0) goto L24
            java.lang.Throwable r0 = r2.throwable     // Catch: java.lang.Throwable -> L38
            java.lang.String[] r0 = org.apache.log4j.DefaultThrowableRenderer.render(r0)     // Catch: java.lang.Throwable -> L38
            r2.rep = r0     // Catch: java.lang.Throwable -> L38
            goto L2c
        L24:
            java.lang.Throwable r1 = r2.throwable     // Catch: java.lang.Throwable -> L38
            java.lang.String[] r0 = r0.doRender(r1)     // Catch: java.lang.Throwable -> L38
            r2.rep = r0     // Catch: java.lang.Throwable -> L38
        L2c:
            java.lang.String[] r0 = r2.rep     // Catch: java.lang.Throwable -> L38
            java.lang.Object r0 = r0.clone()     // Catch: java.lang.Throwable -> L38
            java.lang.String[] r0 = (java.lang.String[]) r0     // Catch: java.lang.Throwable -> L38
            java.lang.String[] r0 = (java.lang.String[]) r0     // Catch: java.lang.Throwable -> L38
            monitor-exit(r2)
            return r0
        L38:
            r0 = move-exception
            monitor-exit(r2)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.log4j.spi.ThrowableInformation.getThrowableStrRep():java.lang.String[]");
    }
}
