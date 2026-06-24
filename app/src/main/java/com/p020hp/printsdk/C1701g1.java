package com.p020hp.printsdk;

import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.sync.Mutex;

public final class C1701g1 {

    public final Mutex f1211a;

    public int f1212b;

    @DebugMetadata(m1304c = "com.hp.bgp.util.MultiMutex", m1305f = "MultiMutex.kt", m1306i = {0, 0, 1}, m1307l = {23, 27}, m1308m = "whileLocked", m1309n = {"this", "func", "this"}, m1310s = {"L$0", "L$1", "L$0"})
    public static final class a<T> extends ContinuationImpl {

        public Object f1213a;

        public Object f1214b;

        public Object f1215c;

        public int f1217e;

        public a(Continuation<? super a> continuation) {
            super(continuation);
        }

        @Override
        public final Object invokeSuspend(Object obj) {
            this.f1215c = obj;
            this.f1217e |= Integer.MIN_VALUE;
            return C1701g1.this.m525a(null, this);
        }
    }

    public C1701g1(Mutex mutex) {
        Intrinsics.checkNotNullParameter(mutex, "mutex");
        this.f1211a = mutex;
    }

    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final <T> java.lang.Object m525a(kotlin.jvm.functions.Function1<? super kotlin.coroutines.Continuation<? super T>, ? extends java.lang.Object> r7, kotlin.coroutines.Continuation<? super T> r8) throws java.lang.Throwable {
        /*
            r6 = this;
            boolean r0 = r8 instanceof com.p020hp.printsdk.C1701g1.a
            if (r0 == 0) goto L13
            r0 = r8
            com.hp.printsdk.g1$a r0 = (com.p020hp.printsdk.C1701g1.a) r0
            int r1 = r0.f1217e
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.f1217e = r1
            goto L18
        L13:
            com.hp.printsdk.g1$a r0 = new com.hp.printsdk.g1$a
            r0.<init>(r8)
        L18:
            java.lang.Object r8 = r0.f1215c
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.f1217e
            r3 = 2
            r4 = 0
            r5 = 1
            if (r2 == 0) goto L47
            if (r2 == r5) goto L3b
            if (r2 != r3) goto L33
            java.lang.Object r7 = r0.f1213a
            com.hp.printsdk.g1 r7 = (com.p020hp.printsdk.C1701g1) r7
            kotlin.ResultKt.throwOnFailure(r8)     // Catch: java.lang.Throwable -> L31
            goto L73
        L31:
            r8 = move-exception
            goto L7a
        L33:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r8)
            throw r7
        L3b:
            java.lang.Object r7 = r0.f1214b
            kotlin.jvm.functions.Function1 r7 = (kotlin.jvm.functions.Function1) r7
            java.lang.Object r2 = r0.f1213a
            com.hp.printsdk.g1 r2 = (com.p020hp.printsdk.C1701g1) r2
            kotlin.ResultKt.throwOnFailure(r8)
            goto L5e
        L47:
            kotlin.ResultKt.throwOnFailure(r8)
            int r8 = r6.f1212b
            if (r8 != 0) goto L64
            kotlinx.coroutines.sync.Mutex r8 = r6.f1211a
            r0.f1213a = r6
            r0.f1214b = r7
            r0.f1217e = r5
            java.lang.Object r8 = kotlinx.coroutines.sync.Mutex.DefaultImpls.lock$default(r8, r4, r0, r5, r4)
            if (r8 != r1) goto L5d
            return r1
        L5d:
            r2 = r6
        L5e:
            int r8 = r2.f1212b
            int r8 = r8 + r5
            r2.f1212b = r8
            goto L65
        L64:
            r2 = r6
        L65:
            r0.f1213a = r2     // Catch: java.lang.Throwable -> L78
            r0.f1214b = r4     // Catch: java.lang.Throwable -> L78
            r0.f1217e = r3     // Catch: java.lang.Throwable -> L78
            java.lang.Object r8 = r7.invoke(r0)     // Catch: java.lang.Throwable -> L78
            if (r8 != r1) goto L72
            return r1
        L72:
            r7 = r2
        L73:
            java.lang.Object r8 = kotlin.Result.m1772constructorimpl(r8)     // Catch: java.lang.Throwable -> L31
            goto L82
        L78:
            r8 = move-exception
            r7 = r2
        L7a:
            java.lang.Object r8 = kotlin.ResultKt.createFailure(r8)
            java.lang.Object r8 = kotlin.Result.m1772constructorimpl(r8)
        L82:
            int r0 = r7.f1212b
            int r0 = r0 + (-1)
            r7.f1212b = r0
            if (r0 != 0) goto L8f
            kotlinx.coroutines.sync.Mutex r7 = r7.f1211a
            kotlinx.coroutines.sync.Mutex.DefaultImpls.unlock$default(r7, r4, r5, r4)
        L8f:
            kotlin.ResultKt.throwOnFailure(r8)
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.p020hp.printsdk.C1701g1.m525a(kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
