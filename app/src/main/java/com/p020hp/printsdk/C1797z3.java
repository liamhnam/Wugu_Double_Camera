package com.p020hp.printsdk;

import com.p020hp.open.printsdk.HpPrinter;
import java.util.List;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlinx.coroutines.CoroutineScope;

@DebugMetadata(m1304c = "com.hp.printsdk.ipp.PrintService$refreshPrinterList$1", m1305f = "PrintService.kt", m1306i = {0}, m1307l = {90, 90}, m1308m = "invokeSuspend", m1309n = {"it"}, m1310s = {"L$2"})
public final class C1797z3 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {

    public Object f2064a;

    public Object f2065b;

    public Object f2066c;

    public int f2067d;

    public final C1787x3 f2068e;

    public static final class a extends Lambda implements Function1<List<? extends HpPrinter>, Unit> {

        public final C1787x3 f2069a;

        public a(C1787x3 c1787x3) {
            super(1);
            this.f2069a = c1787x3;
        }

        @Override
        public Unit invoke(List<? extends HpPrinter> list) {
            List<? extends HpPrinter> it = list;
            Intrinsics.checkNotNullParameter(it, "it");
            this.f2069a.f1966g.invoke("Current number of printers: " + it.size());
            this.f2069a.m682a().setValue(it);
            return Unit.INSTANCE;
        }
    }

    public C1797z3(C1787x3 c1787x3, Continuation<? super C1797z3> continuation) {
        super(2, continuation);
        this.f2068e = c1787x3;
    }

    @Override
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new C1797z3(this.f2068e, continuation);
    }

    @Override
    public Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return new C1797z3(this.f2068e, continuation).invokeSuspend(Unit.INSTANCE);
    }

    @Override
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r10) throws java.lang.Throwable {
        /*
            r9 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r9.f2067d
            r2 = 2
            r3 = 1
            if (r1 == 0) goto L36
            if (r1 == r3) goto L22
            if (r1 != r2) goto L1a
            java.lang.Object r1 = r9.f2065b
            java.util.Iterator r1 = (java.util.Iterator) r1
            java.lang.Object r4 = r9.f2064a
            com.hp.printsdk.x3 r4 = (com.p020hp.printsdk.C1787x3) r4
            kotlin.ResultKt.throwOnFailure(r10)
            goto L53
        L1a:
            java.lang.IllegalStateException r10 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r10.<init>(r0)
            throw r10
        L22:
            java.lang.Object r1 = r9.f2066c
            com.hp.open.printsdk.HpPrinter r1 = (com.p020hp.open.printsdk.HpPrinter) r1
            java.lang.Object r4 = r9.f2065b
            java.util.Iterator r4 = (java.util.Iterator) r4
            java.lang.Object r5 = r9.f2064a
            com.hp.printsdk.x3 r5 = (com.p020hp.printsdk.C1787x3) r5
            kotlin.ResultKt.throwOnFailure(r10)
            r6 = r5
            r5 = r1
            r1 = r0
            r0 = r9
            goto L75
        L36:
            kotlin.ResultKt.throwOnFailure(r10)
            com.hp.printsdk.x3 r10 = r9.f2068e
            androidx.lifecycle.MutableLiveData r10 = r10.m682a()
            java.lang.Object r10 = r10.getValue()
            java.util.List r10 = (java.util.List) r10
            if (r10 == 0) goto L90
            java.util.List r10 = kotlin.collections.CollectionsKt.toList(r10)
            if (r10 == 0) goto L90
            com.hp.printsdk.x3 r4 = r9.f2068e
            java.util.Iterator r1 = r10.iterator()
        L53:
            r10 = r9
        L54:
            boolean r5 = r1.hasNext()
            if (r5 == 0) goto L91
            java.lang.Object r5 = r1.next()
            com.hp.open.printsdk.HpPrinter r5 = (com.p020hp.open.printsdk.HpPrinter) r5
            r10.f2064a = r4
            r10.f2065b = r1
            r10.f2066c = r5
            r10.f2067d = r3
            java.lang.Object r6 = r4.mo561a(r10)
            if (r6 != r0) goto L6f
            return r0
        L6f:
            r8 = r0
            r0 = r10
            r10 = r6
            r6 = r4
            r4 = r1
            r1 = r8
        L75:
            com.hp.printsdk.g r10 = (com.p020hp.printsdk.InterfaceC1699g) r10
            java.util.UUID r5 = r5.getId()
            r0.f2064a = r6
            r0.f2065b = r4
            r7 = 0
            r0.f2066c = r7
            r0.f2067d = r2
            java.lang.Object r10 = r10.mo516a(r5, r0)
            if (r10 != r1) goto L8b
            return r1
        L8b:
            r10 = r0
            r0 = r1
            r1 = r4
            r4 = r6
            goto L54
        L90:
            r10 = r9
        L91:
            com.hp.printsdk.x3 r0 = r10.f2068e
            androidx.lifecycle.MutableLiveData r0 = r0.m682a()
            java.util.ArrayList r1 = new java.util.ArrayList
            r1.<init>()
            r0.setValue(r1)
            com.hp.printsdk.x3 r10 = r10.f2068e
            com.hp.printsdk.z3$a r0 = new com.hp.printsdk.z3$a
            r0.<init>(r10)
            r1 = 100
            r10.m683a(r1, r0)
            kotlin.Unit r10 = kotlin.Unit.INSTANCE
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.p020hp.printsdk.C1797z3.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
