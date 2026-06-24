package com.p020hp.printsdk;

import com.p020hp.open.printsdk.HpPrintJob;
import java.util.UUID;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;

@DebugMetadata(m1304c = "com.hp.printsdk.base.BasePrintService$job$1", m1305f = "BasePrintService.kt", m1306i = {}, m1307l = {93}, m1308m = "invokeSuspend", m1309n = {}, m1310s = {})
public final class C1722k2 extends SuspendLambda implements Function1<Continuation<? super Flow<? extends HpPrintJob>>, Object> {

    public int f1399a;

    public final AbstractC1717j2 f1400b;

    public final UUID f1401c;

    public final UUID f1402d;

    public static final class a implements Flow<C1668b> {

        public final Flow f1403a;

        public final UUID f1404b;

        public static final class C3363a implements FlowCollector<C1668b> {

            public final FlowCollector f1405a;

            public final UUID f1406b;

            @DebugMetadata(m1304c = "com.hp.printsdk.base.BasePrintService$job$1$invokeSuspend$$inlined$filter$1$2", m1305f = "BasePrintService.kt", m1306i = {}, m1307l = {137}, m1308m = "emit", m1309n = {}, m1310s = {})
            public static final class C3364a extends ContinuationImpl {

                public Object f1407a;

                public int f1408b;

                public C3364a(Continuation continuation) {
                    super(continuation);
                }

                @Override
                public final Object invokeSuspend(Object obj) {
                    this.f1407a = obj;
                    this.f1408b |= Integer.MIN_VALUE;
                    return C3363a.this.emit(null, this);
                }
            }

            public C3363a(FlowCollector flowCollector, UUID uuid) {
                this.f1405a = flowCollector;
                this.f1406b = uuid;
            }

            @Override
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public java.lang.Object emit(com.p020hp.printsdk.C1668b r6, kotlin.coroutines.Continuation r7) throws java.lang.Throwable {
                /*
                    r5 = this;
                    boolean r0 = r7 instanceof com.p020hp.printsdk.C1722k2.a.C3363a.C3364a
                    if (r0 == 0) goto L13
                    r0 = r7
                    com.hp.printsdk.k2$a$a$a r0 = (com.p020hp.printsdk.C1722k2.a.C3363a.C3364a) r0
                    int r1 = r0.f1408b
                    r2 = -2147483648(0xffffffff80000000, float:-0.0)
                    r3 = r1 & r2
                    if (r3 == 0) goto L13
                    int r1 = r1 - r2
                    r0.f1408b = r1
                    goto L18
                L13:
                    com.hp.printsdk.k2$a$a$a r0 = new com.hp.printsdk.k2$a$a$a
                    r0.<init>(r7)
                L18:
                    java.lang.Object r7 = r0.f1407a
                    java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                    int r2 = r0.f1408b
                    r3 = 1
                    if (r2 == 0) goto L31
                    if (r2 != r3) goto L29
                    kotlin.ResultKt.throwOnFailure(r7)
                    goto L4c
                L29:
                    java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
                    java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
                    r6.<init>(r7)
                    throw r6
                L31:
                    kotlin.ResultKt.throwOnFailure(r7)
                    kotlinx.coroutines.flow.FlowCollector r7 = r5.f1405a
                    r2 = r6
                    com.hp.printsdk.b r2 = (com.p020hp.printsdk.C1668b) r2
                    java.util.UUID r2 = r2.f891a
                    java.util.UUID r4 = r5.f1406b
                    boolean r2 = kotlin.jvm.internal.Intrinsics.areEqual(r2, r4)
                    if (r2 == 0) goto L4c
                    r0.f1408b = r3
                    java.lang.Object r6 = r7.emit(r6, r0)
                    if (r6 != r1) goto L4c
                    return r1
                L4c:
                    kotlin.Unit r6 = kotlin.Unit.INSTANCE
                    return r6
                */
                throw new UnsupportedOperationException("Method not decompiled: com.p020hp.printsdk.C1722k2.a.C3363a.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
            }
        }

        public a(Flow flow, UUID uuid) {
            this.f1403a = flow;
            this.f1404b = uuid;
        }

        @Override
        public Object collect(FlowCollector<? super C1668b> flowCollector, Continuation continuation) {
            Object objCollect = this.f1403a.collect(new C3363a(flowCollector, this.f1404b), continuation);
            return objCollect == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objCollect : Unit.INSTANCE;
        }
    }

    public static final class b implements Flow<HpPrintJob> {

        public final Flow f1410a;

        public final AbstractC1717j2 f1411b;

        public static final class a implements FlowCollector<C1668b> {

            public final FlowCollector f1412a;

            public final AbstractC1717j2 f1413b;

            @DebugMetadata(m1304c = "com.hp.printsdk.base.BasePrintService$job$1$invokeSuspend$$inlined$map$1$2", m1305f = "BasePrintService.kt", m1306i = {}, m1307l = {137, 137}, m1308m = "emit", m1309n = {}, m1310s = {})
            public static final class C3365a extends ContinuationImpl {

                public Object f1414a;

                public int f1415b;

                public Object f1416c;

                public C3365a(Continuation continuation) {
                    super(continuation);
                }

                @Override
                public final Object invokeSuspend(Object obj) {
                    this.f1414a = obj;
                    this.f1415b |= Integer.MIN_VALUE;
                    return a.this.emit(null, this);
                }
            }

            public a(FlowCollector flowCollector, AbstractC1717j2 abstractC1717j2) {
                this.f1412a = flowCollector;
                this.f1413b = abstractC1717j2;
            }

            @Override
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public java.lang.Object emit(com.p020hp.printsdk.C1668b r7, kotlin.coroutines.Continuation r8) throws java.lang.Throwable {
                /*
                    r6 = this;
                    boolean r0 = r8 instanceof com.p020hp.printsdk.C1722k2.b.a.C3365a
                    if (r0 == 0) goto L13
                    r0 = r8
                    com.hp.printsdk.k2$b$a$a r0 = (com.p020hp.printsdk.C1722k2.b.a.C3365a) r0
                    int r1 = r0.f1415b
                    r2 = -2147483648(0xffffffff80000000, float:-0.0)
                    r3 = r1 & r2
                    if (r3 == 0) goto L13
                    int r1 = r1 - r2
                    r0.f1415b = r1
                    goto L18
                L13:
                    com.hp.printsdk.k2$b$a$a r0 = new com.hp.printsdk.k2$b$a$a
                    r0.<init>(r8)
                L18:
                    java.lang.Object r8 = r0.f1414a
                    java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                    int r2 = r0.f1415b
                    r3 = 2
                    r4 = 1
                    if (r2 == 0) goto L3c
                    if (r2 == r4) goto L34
                    if (r2 != r3) goto L2c
                    kotlin.ResultKt.throwOnFailure(r8)
                    goto L5f
                L2c:
                    java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
                    java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
                    r7.<init>(r8)
                    throw r7
                L34:
                    java.lang.Object r7 = r0.f1416c
                    kotlinx.coroutines.flow.FlowCollector r7 = (kotlinx.coroutines.flow.FlowCollector) r7
                    kotlin.ResultKt.throwOnFailure(r8)
                    goto L53
                L3c:
                    kotlin.ResultKt.throwOnFailure(r8)
                    kotlinx.coroutines.flow.FlowCollector r8 = r6.f1412a
                    com.hp.printsdk.b r7 = (com.p020hp.printsdk.C1668b) r7
                    com.hp.printsdk.j2 r2 = r6.f1413b
                    r0.f1416c = r8
                    r0.f1415b = r4
                    java.lang.Object r7 = r2.m560a(r7, r0)
                    if (r7 != r1) goto L50
                    return r1
                L50:
                    r5 = r8
                    r8 = r7
                    r7 = r5
                L53:
                    r2 = 0
                    r0.f1416c = r2
                    r0.f1415b = r3
                    java.lang.Object r7 = r7.emit(r8, r0)
                    if (r7 != r1) goto L5f
                    return r1
                L5f:
                    kotlin.Unit r7 = kotlin.Unit.INSTANCE
                    return r7
                */
                throw new UnsupportedOperationException("Method not decompiled: com.p020hp.printsdk.C1722k2.b.a.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
            }
        }

        public b(Flow flow, AbstractC1717j2 abstractC1717j2) {
            this.f1410a = flow;
            this.f1411b = abstractC1717j2;
        }

        @Override
        public Object collect(FlowCollector<? super HpPrintJob> flowCollector, Continuation continuation) {
            Object objCollect = this.f1410a.collect(new a(flowCollector, this.f1411b), continuation);
            return objCollect == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objCollect : Unit.INSTANCE;
        }
    }

    public C1722k2(AbstractC1717j2 abstractC1717j2, UUID uuid, UUID uuid2, Continuation<? super C1722k2> continuation) {
        super(1, continuation);
        this.f1400b = abstractC1717j2;
        this.f1401c = uuid;
        this.f1402d = uuid2;
    }

    @Override
    public final Continuation<Unit> create(Continuation<?> continuation) {
        return new C1722k2(this.f1400b, this.f1401c, this.f1402d, continuation);
    }

    @Override
    public Object invoke(Continuation<? super Flow<? extends HpPrintJob>> continuation) {
        return new C1722k2(this.f1400b, this.f1401c, this.f1402d, continuation).invokeSuspend(Unit.INSTANCE);
    }

    @Override
    public final Object invokeSuspend(Object obj) throws Throwable {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.f1399a;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            AbstractC1717j2 abstractC1717j2 = this.f1400b;
            this.f1399a = 1;
            obj = abstractC1717j2.mo561a((Continuation<? super InterfaceC1699g>) this);
            if (obj == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        return new b(new a(((InterfaceC1699g) obj).mo519a(this.f1401c), this.f1402d), this.f1400b);
    }
}
