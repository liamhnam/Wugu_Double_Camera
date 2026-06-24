package com.p020hp.printsdk;

import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;

public final class C1682d1<U> implements Flow<U> {

    public final Flow f1152a;

    public final C1676c1 f1153b;

    public static final class a<T> implements FlowCollector<T> {

        public final FlowCollector f1154a;

        public final C1676c1 f1155b;

        @DebugMetadata(m1304c = "com.hp.bgp.util.BroadcastingMap$flow$$inlined$map$2$2", m1305f = "BroadcastingMap.kt", m1306i = {}, m1307l = {137}, m1308m = "emit", m1309n = {}, m1310s = {})
        public static final class C3360a extends ContinuationImpl {

            public Object f1156a;

            public int f1157b;

            public C3360a(Continuation continuation) {
                super(continuation);
            }

            @Override
            public final Object invokeSuspend(Object obj) {
                this.f1156a = obj;
                this.f1157b |= Integer.MIN_VALUE;
                return a.this.emit(null, this);
            }
        }

        public a(FlowCollector flowCollector, C1676c1 c1676c1) {
            this.f1154a = flowCollector;
            this.f1155b = c1676c1;
        }

        @Override
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public java.lang.Object emit(java.lang.Object r5, kotlin.coroutines.Continuation r6) throws java.lang.Throwable {
            /*
                r4 = this;
                boolean r0 = r6 instanceof com.p020hp.printsdk.C1682d1.a.C3360a
                if (r0 == 0) goto L13
                r0 = r6
                com.hp.printsdk.d1$a$a r0 = (com.p020hp.printsdk.C1682d1.a.C3360a) r0
                int r1 = r0.f1157b
                r2 = -2147483648(0xffffffff80000000, float:-0.0)
                r3 = r1 & r2
                if (r3 == 0) goto L13
                int r1 = r1 - r2
                r0.f1157b = r1
                goto L18
            L13:
                com.hp.printsdk.d1$a$a r0 = new com.hp.printsdk.d1$a$a
                r0.<init>(r6)
            L18:
                java.lang.Object r6 = r0.f1156a
                java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                int r2 = r0.f1157b
                r3 = 1
                if (r2 == 0) goto L31
                if (r2 != r3) goto L29
                kotlin.ResultKt.throwOnFailure(r6)
                goto L47
            L29:
                java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
                java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
                r5.<init>(r6)
                throw r5
            L31:
                kotlin.ResultKt.throwOnFailure(r6)
                kotlinx.coroutines.flow.FlowCollector r6 = r4.f1154a
                com.hp.printsdk.c1 r2 = r4.f1155b
                java.util.Map<T, U> r2 = r2.f969a
                java.lang.Object r5 = r2.get(r5)
                r0.f1157b = r3
                java.lang.Object r5 = r6.emit(r5, r0)
                if (r5 != r1) goto L47
                return r1
            L47:
                kotlin.Unit r5 = kotlin.Unit.INSTANCE
                return r5
            */
            throw new UnsupportedOperationException("Method not decompiled: com.p020hp.printsdk.C1682d1.a.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
        }
    }

    public C1682d1(Flow flow, C1676c1 c1676c1) {
        this.f1152a = flow;
        this.f1153b = c1676c1;
    }

    @Override
    public Object collect(FlowCollector flowCollector, Continuation continuation) {
        Object objCollect = this.f1152a.collect(new a(flowCollector, this.f1153b), continuation);
        return objCollect == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objCollect : Unit.INSTANCE;
    }
}
