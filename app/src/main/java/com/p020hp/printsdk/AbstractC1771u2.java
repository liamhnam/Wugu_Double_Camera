package com.p020hp.printsdk;

import com.p020hp.open.printsdk.HpPrintJob;
import com.p020hp.open.printsdk.HpPrintRequest;
import com.p020hp.open.printsdk.HpPrinter;
import java.util.UUID;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

public abstract class AbstractC1771u2 {

    @DebugMetadata(m1304c = "com.hp.printsdk.db.HistoryJobDao", m1305f = "HistoryJobDao.kt", m1306i = {0, 1}, m1307l = {79, 81}, m1308m = "getPrinterWithJobs", m1309n = {"earliestTime", "earliestTime"}, m1310s = {"J$0", "J$0"})
    public static final class a extends ContinuationImpl {

        public long f1847a;

        public Object f1848b;

        public int f1850d;

        public a(Continuation<? super a> continuation) {
            super(continuation);
        }

        @Override
        public final Object invokeSuspend(Object obj) {
            this.f1848b = obj;
            this.f1850d |= Integer.MIN_VALUE;
            return AbstractC1771u2.this.m665a(0L, null, this);
        }
    }

    @DebugMetadata(m1304c = "com.hp.printsdk.db.HistoryJobDao", m1305f = "HistoryJobDao.kt", m1306i = {0, 0, 0, 0}, m1307l = {45, 46}, m1308m = "insertPrinterWithJob", m1309n = {"this", "job", "request", "jobName"}, m1310s = {"L$0", "L$1", "L$2", "L$3"})
    public static final class b extends ContinuationImpl {

        public Object f1851a;

        public Object f1852b;

        public Object f1853c;

        public Object f1854d;

        public Object f1855e;

        public int f1857g;

        public b(Continuation<? super b> continuation) {
            super(continuation);
        }

        @Override
        public final Object invokeSuspend(Object obj) {
            this.f1855e = obj;
            this.f1857g |= Integer.MIN_VALUE;
            return AbstractC1771u2.this.m666a((HpPrinter) null, (HpPrintJob) null, (HpPrintRequest) null, (String) null, this);
        }
    }

    @DebugMetadata(m1304c = "com.hp.printsdk.db.HistoryJobDao", m1305f = "HistoryJobDao.kt", m1306i = {0, 0, 1, 2, 2, 2, 3}, m1307l = {65, 65, 67, 67}, m1308m = "jobs", m1309n = {"this", "earliestTime", "earliestTime", "this", "printerIds", "earliestTime", "earliestTime"}, m1310s = {"L$0", "J$0", "J$0", "L$0", "L$1", "J$0", "J$0"})
    public static final class c extends ContinuationImpl {

        public Object f1858a;

        public Object f1859b;

        public long f1860c;

        public Object f1861d;

        public int f1863f;

        public c(Continuation<? super c> continuation) {
            super(continuation);
        }

        @Override
        public final Object invokeSuspend(Object obj) {
            this.f1861d = obj;
            this.f1863f |= Integer.MIN_VALUE;
            return AbstractC1771u2.this.m664a(0L, (UUID[]) null, 0, 0, this);
        }
    }

    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object m665a(long r11, java.util.UUID[] r13, kotlin.coroutines.Continuation<? super java.util.List<com.p020hp.open.printsdk.PrinterWithJobs>> r14) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 289
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.p020hp.printsdk.AbstractC1771u2.m665a(long, java.util.UUID[], kotlin.coroutines.Continuation):java.lang.Object");
    }

    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object m666a(com.p020hp.open.printsdk.HpPrinter r43, com.p020hp.open.printsdk.HpPrintJob r44, com.p020hp.open.printsdk.HpPrintRequest r45, java.lang.String r46, kotlin.coroutines.Continuation<? super kotlin.Unit> r47) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 490
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.p020hp.printsdk.AbstractC1771u2.m666a(com.hp.open.printsdk.HpPrinter, com.hp.open.printsdk.HpPrintJob, com.hp.open.printsdk.HpPrintRequest, java.lang.String, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object m664a(long r17, java.util.UUID[] r19, int r20, int r21, kotlin.coroutines.Continuation<? super java.util.List<com.p020hp.open.printsdk.PrinterWithJobs>> r22) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 506
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.p020hp.printsdk.AbstractC1771u2.m664a(long, java.util.UUID[], int, int, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
