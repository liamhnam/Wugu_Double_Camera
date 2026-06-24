package com.p020hp.printsdk;

import com.p020hp.jipp.encoding.MutableAttributeGroup;
import com.p020hp.open.printsdk.HpPrintFile;
import com.p020hp.open.printsdk.HpPrintJob;
import com.p020hp.open.printsdk.HpPrintRequest;
import java.io.InputStream;
import java.util.UUID;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;

@DebugMetadata(m1304c = "com.hp.printsdk.base.BasePrintService$print$1", m1305f = "BasePrintService.kt", m1306i = {0, 1, 1}, m1307l = {47, 50}, m1308m = "invokeSuspend", m1309n = {"createTime", "ippRequest", "createTime"}, m1310s = {"J$0", "L$2", "J$0"})
public final class C1727l2 extends SuspendLambda implements Function1<Continuation<? super Flow<? extends HpPrintJob>>, Object> {

    public long f1458a;

    public Object f1459b;

    public Object f1460c;

    public Object f1461d;

    public int f1462e;

    public final AbstractC1717j2 f1463f;

    public final HpPrintRequest f1464g;

    @DebugMetadata(m1304c = "com.hp.printsdk.base.BasePrintService$print$1$1", m1305f = "BasePrintService.kt", m1306i = {}, m1307l = {}, m1308m = "invokeSuspend", m1309n = {}, m1310s = {})
    public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super C1680d>, Object> {

        public final AbstractC1717j2 f1465a;

        public final HpPrintRequest f1466b;

        public a(AbstractC1717j2 abstractC1717j2, HpPrintRequest hpPrintRequest, Continuation<? super a> continuation) {
            super(2, continuation);
            this.f1465a = abstractC1717j2;
            this.f1466b = hpPrintRequest;
        }

        @Override
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new a(this.f1465a, this.f1466b, continuation);
        }

        @Override
        public Object invoke(CoroutineScope coroutineScope, Continuation<? super C1680d> continuation) {
            return new a(this.f1465a, this.f1466b, continuation).invokeSuspend(Unit.INSTANCE);
        }

        @Override
        public final Object invokeSuspend(Object obj) throws Throwable {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            ResultKt.throwOnFailure(obj);
            AbstractC1717j2 abstractC1717j2 = this.f1465a;
            HpPrintRequest hpPrintRequest = this.f1466b;
            abstractC1717j2.getClass();
            boolean zPrintingEnhancement$print_core_thirdPartyRelease = hpPrintRequest.getPrintFile().printingEnhancement$print_core_thirdPartyRelease();
            HpPrintFile printFile = hpPrintRequest.getPrintFile();
            UUID id = hpPrintRequest.getPrinter().getId();
            InputStream inputStream = printFile.getInputStream();
            String mimeType = printFile.getMimeType();
            String contentName = printFile.getContentName();
            String string = printFile.getUri().toString();
            Intrinsics.checkNotNullExpressionValue(string, "file.uri.toString()");
            C1662a c1662a = new C1662a(inputStream, mimeType, contentName, string);
            MutableAttributeGroup mutable = ((C1777v3) hpPrintRequest.getPrinter()).m672a(hpPrintRequest.getPrintOptions()).toMutable();
            mutable.put(C1748p3.f1582a.mo418of(Boolean.valueOf(zPrintingEnhancement$print_core_thirdPartyRelease)));
            return new C1680d(id, c1662a, mutable, null, 8);
        }
    }

    @DebugMetadata(m1304c = "com.hp.printsdk.base.BasePrintService$print$1$3$1", m1305f = "BasePrintService.kt", m1306i = {}, m1307l = {}, m1308m = "invokeSuspend", m1309n = {}, m1310s = {})
    public static final class b extends SuspendLambda implements Function2<FlowCollector<? super HpPrintJob>, Continuation<? super Unit>, Object> {
        public b(Continuation<? super b> continuation) {
            super(2, continuation);
        }

        @Override
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new b(continuation);
        }

        @Override
        public Object invoke(FlowCollector<? super HpPrintJob> flowCollector, Continuation<? super Unit> continuation) {
            return new b(continuation).invokeSuspend(Unit.INSTANCE);
        }

        @Override
        public final Object invokeSuspend(Object obj) throws Throwable {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            ResultKt.throwOnFailure(obj);
            return Unit.INSTANCE;
        }
    }

    public static final class c implements Flow<HpPrintJob> {

        public final Flow f1467a;

        public final AbstractC1717j2 f1468b;

        public final long f1469c;

        public final HpPrintRequest f1470d;

        public static final class a implements FlowCollector<C1668b> {

            public final FlowCollector f1471a;

            public final AbstractC1717j2 f1472b;

            public final long f1473c;

            public final HpPrintRequest f1474d;

            @DebugMetadata(m1304c = "com.hp.printsdk.base.BasePrintService$print$1$invokeSuspend$lambda-1$$inlined$map$1$2", m1305f = "BasePrintService.kt", m1306i = {}, m1307l = {139, 139}, m1308m = "emit", m1309n = {}, m1310s = {})
            public static final class C3366a extends ContinuationImpl {

                public Object f1475a;

                public int f1476b;

                public Object f1477c;

                public C3366a(Continuation continuation) {
                    super(continuation);
                }

                @Override
                public final Object invokeSuspend(Object obj) {
                    this.f1475a = obj;
                    this.f1476b |= Integer.MIN_VALUE;
                    return a.this.emit(null, this);
                }
            }

            public a(FlowCollector flowCollector, AbstractC1717j2 abstractC1717j2, long j, HpPrintRequest hpPrintRequest) {
                this.f1471a = flowCollector;
                this.f1472b = abstractC1717j2;
                this.f1473c = j;
                this.f1474d = hpPrintRequest;
            }

            @Override
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public java.lang.Object emit(com.p020hp.printsdk.C1668b r14, kotlin.coroutines.Continuation r15) throws java.lang.Throwable {
                /*
                    r13 = this;
                    boolean r0 = r15 instanceof com.p020hp.printsdk.C1727l2.c.a.C3366a
                    if (r0 == 0) goto L13
                    r0 = r15
                    com.hp.printsdk.l2$c$a$a r0 = (com.p020hp.printsdk.C1727l2.c.a.C3366a) r0
                    int r1 = r0.f1476b
                    r2 = -2147483648(0xffffffff80000000, float:-0.0)
                    r3 = r1 & r2
                    if (r3 == 0) goto L13
                    int r1 = r1 - r2
                    r0.f1476b = r1
                    goto L18
                L13:
                    com.hp.printsdk.l2$c$a$a r0 = new com.hp.printsdk.l2$c$a$a
                    r0.<init>(r15)
                L18:
                    java.lang.Object r15 = r0.f1475a
                    java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                    int r2 = r0.f1476b
                    r3 = 0
                    r4 = 2
                    r5 = 1
                    if (r2 == 0) goto L3e
                    if (r2 == r5) goto L36
                    if (r2 != r4) goto L2e
                    kotlin.ResultKt.throwOnFailure(r15)
                    goto La1
                L2e:
                    java.lang.IllegalStateException r14 = new java.lang.IllegalStateException
                    java.lang.String r15 = "call to 'resume' before 'invoke' with coroutine"
                    r14.<init>(r15)
                    throw r14
                L36:
                    java.lang.Object r14 = r0.f1477c
                    kotlinx.coroutines.flow.FlowCollector r14 = (kotlinx.coroutines.flow.FlowCollector) r14
                    kotlin.ResultKt.throwOnFailure(r15)
                    goto L96
                L3e:
                    kotlin.ResultKt.throwOnFailure(r15)
                    kotlinx.coroutines.flow.FlowCollector r15 = r13.f1471a
                    com.hp.printsdk.b r14 = (com.p020hp.printsdk.C1668b) r14
                    long r6 = r13.f1473c
                    java.lang.Long r2 = kotlin.coroutines.jvm.internal.Boxing.boxLong(r6)
                    com.hp.printsdk.j2 r6 = r13.f1472b
                    java.util.HashMap<java.util.UUID, java.lang.Long> r6 = r6.f1368d
                    java.util.UUID r7 = r14.f891a
                    r6.put(r7, r2)
                    com.hp.printsdk.j2 r2 = r13.f1472b
                    com.hp.open.printsdk.HpPrintRequest r6 = r13.f1474d
                    java.util.UUID r7 = r14.f891a
                    r2.getClass()
                    com.hp.open.printsdk.CoreManager r8 = com.p020hp.open.printsdk.CoreManager.INSTANCE
                    boolean r8 = r8.isPrintHistoryOpen$print_core_thirdPartyRelease()
                    if (r8 == 0) goto L86
                    com.hp.open.printsdk.HpPrinter r8 = r6.getPrinter()
                    com.hp.printsdk.n2 r9 = r2.f1366b
                    java.util.UUID r10 = r8.getId()
                    java.lang.String r11 = "printerId"
                    kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r10, r11)
                    java.lang.String r11 = "jobId"
                    kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r7, r11)
                    com.hp.printsdk.k2 r11 = new com.hp.printsdk.k2
                    r11.<init>(r2, r10, r7, r3)
                    androidx.lifecycle.LiveData r2 = r2.m559a(r11)
                    r9.mo588a(r8, r2, r6)
                L86:
                    com.hp.printsdk.j2 r2 = r13.f1472b
                    r0.f1477c = r15
                    r0.f1476b = r5
                    java.lang.Object r14 = r2.m560a(r14, r0)
                    if (r14 != r1) goto L93
                    return r1
                L93:
                    r12 = r15
                    r15 = r14
                    r14 = r12
                L96:
                    r0.f1477c = r3
                    r0.f1476b = r4
                    java.lang.Object r14 = r14.emit(r15, r0)
                    if (r14 != r1) goto La1
                    return r1
                La1:
                    kotlin.Unit r14 = kotlin.Unit.INSTANCE
                    return r14
                */
                throw new UnsupportedOperationException("Method not decompiled: com.p020hp.printsdk.C1727l2.c.a.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
            }
        }

        public c(Flow flow, AbstractC1717j2 abstractC1717j2, long j, HpPrintRequest hpPrintRequest) {
            this.f1467a = flow;
            this.f1468b = abstractC1717j2;
            this.f1469c = j;
            this.f1470d = hpPrintRequest;
        }

        @Override
        public Object collect(FlowCollector<? super HpPrintJob> flowCollector, Continuation continuation) {
            Object objCollect = this.f1467a.collect(new a(flowCollector, this.f1468b, this.f1469c, this.f1470d), continuation);
            return objCollect == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objCollect : Unit.INSTANCE;
        }
    }

    public C1727l2(AbstractC1717j2 abstractC1717j2, HpPrintRequest hpPrintRequest, Continuation<? super C1727l2> continuation) {
        super(1, continuation);
        this.f1463f = abstractC1717j2;
        this.f1464g = hpPrintRequest;
    }

    @Override
    public final Continuation<Unit> create(Continuation<?> continuation) {
        return new C1727l2(this.f1463f, this.f1464g, continuation);
    }

    @Override
    public Object invoke(Continuation<? super Flow<? extends HpPrintJob>> continuation) {
        return new C1727l2(this.f1463f, this.f1464g, continuation).invokeSuspend(Unit.INSTANCE);
    }

    @Override
    public final Object invokeSuspend(Object obj) throws Throwable {
        long jCurrentTimeMillis;
        C1680d c1680d;
        HpPrintRequest hpPrintRequest;
        AbstractC1717j2 abstractC1717j2;
        long j;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.f1462e;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            jCurrentTimeMillis = System.currentTimeMillis();
            CoroutineDispatcher io2 = Dispatchers.getIO();
            a aVar = new a(this.f1463f, this.f1464g, null);
            this.f1458a = jCurrentTimeMillis;
            this.f1462e = 1;
            obj = BuildersKt.withContext(io2, aVar, this);
            if (obj == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                if (i != 2) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                long j2 = this.f1458a;
                c1680d = (C1680d) this.f1461d;
                HpPrintRequest hpPrintRequest2 = (HpPrintRequest) this.f1460c;
                AbstractC1717j2 abstractC1717j22 = (AbstractC1717j2) this.f1459b;
                ResultKt.throwOnFailure(obj);
                hpPrintRequest = hpPrintRequest2;
                abstractC1717j2 = abstractC1717j22;
                j = j2;
                return new c(((InterfaceC1699g) obj).mo518a(c1680d), abstractC1717j2, j, hpPrintRequest);
            }
            jCurrentTimeMillis = this.f1458a;
            ResultKt.throwOnFailure(obj);
        }
        C1680d c1680d2 = (C1680d) obj;
        if (c1680d2 == null) {
            this.f1463f.f1367c.m447e("The printer with uuid " + this.f1464g.getPrinter().getId() + " is not found!");
            return FlowKt.flow(new b(null));
        }
        AbstractC1717j2 abstractC1717j23 = this.f1463f;
        HpPrintRequest hpPrintRequest3 = this.f1464g;
        this.f1459b = abstractC1717j23;
        this.f1460c = hpPrintRequest3;
        this.f1461d = c1680d2;
        this.f1458a = jCurrentTimeMillis;
        this.f1462e = 2;
        Object objMo561a = abstractC1717j23.mo561a((Continuation<? super InterfaceC1699g>) this);
        if (objMo561a == coroutine_suspended) {
            return coroutine_suspended;
        }
        c1680d = c1680d2;
        obj = objMo561a;
        long j3 = jCurrentTimeMillis;
        hpPrintRequest = hpPrintRequest3;
        abstractC1717j2 = abstractC1717j23;
        j = j3;
        return new c(((InterfaceC1699g) obj).mo518a(c1680d), abstractC1717j2, j, hpPrintRequest);
    }
}
