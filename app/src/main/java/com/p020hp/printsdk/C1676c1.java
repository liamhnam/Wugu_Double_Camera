package com.p020hp.printsdk;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.channels.BroadcastChannel;
import kotlinx.coroutines.channels.BroadcastChannelKt;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;

public final class C1676c1<T, U> {

    public final Map<T, U> f969a = Collections.synchronizedMap(new LinkedHashMap());

    public final BroadcastChannel<T> f970b = BroadcastChannelKt.BroadcastChannel(-2);

    public static final class a implements Flow<T> {

        public final Flow f971a;

        public final Object f972b;

        public static final class C3357a implements FlowCollector<T> {

            public final FlowCollector f973a;

            public final Object f974b;

            @DebugMetadata(m1304c = "com.hp.bgp.util.BroadcastingMap$flow$$inlined$filter$1$2", m1305f = "BroadcastingMap.kt", m1306i = {}, m1307l = {137}, m1308m = "emit", m1309n = {}, m1310s = {})
            public static final class C3358a extends ContinuationImpl {

                public Object f975a;

                public int f976b;

                public C3358a(Continuation continuation) {
                    super(continuation);
                }

                @Override
                public final Object invokeSuspend(Object obj) {
                    this.f975a = obj;
                    this.f976b |= Integer.MIN_VALUE;
                    return C3357a.this.emit(null, this);
                }
            }

            public C3357a(FlowCollector flowCollector, Object obj) {
                this.f973a = flowCollector;
                this.f974b = obj;
            }

            @Override
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public java.lang.Object emit(java.lang.Object r5, kotlin.coroutines.Continuation r6) throws java.lang.Throwable {
                /*
                    r4 = this;
                    boolean r0 = r6 instanceof com.p020hp.printsdk.C1676c1.a.C3357a.C3358a
                    if (r0 == 0) goto L13
                    r0 = r6
                    com.hp.printsdk.c1$a$a$a r0 = (com.p020hp.printsdk.C1676c1.a.C3357a.C3358a) r0
                    int r1 = r0.f976b
                    r2 = -2147483648(0xffffffff80000000, float:-0.0)
                    r3 = r1 & r2
                    if (r3 == 0) goto L13
                    int r1 = r1 - r2
                    r0.f976b = r1
                    goto L18
                L13:
                    com.hp.printsdk.c1$a$a$a r0 = new com.hp.printsdk.c1$a$a$a
                    r0.<init>(r6)
                L18:
                    java.lang.Object r6 = r0.f975a
                    java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                    int r2 = r0.f976b
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
                    kotlinx.coroutines.flow.FlowCollector r6 = r4.f973a
                    java.lang.Object r2 = r4.f974b
                    boolean r2 = kotlin.jvm.internal.Intrinsics.areEqual(r5, r2)
                    if (r2 == 0) goto L47
                    r0.f976b = r3
                    java.lang.Object r5 = r6.emit(r5, r0)
                    if (r5 != r1) goto L47
                    return r1
                L47:
                    kotlin.Unit r5 = kotlin.Unit.INSTANCE
                    return r5
                */
                throw new UnsupportedOperationException("Method not decompiled: com.p020hp.printsdk.C1676c1.a.C3357a.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
            }
        }

        public a(Flow flow, Object obj) {
            this.f971a = flow;
            this.f972b = obj;
        }

        @Override
        public Object collect(FlowCollector flowCollector, Continuation continuation) {
            Object objCollect = this.f971a.collect(new C3357a(flowCollector, this.f972b), continuation);
            return objCollect == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objCollect : Unit.INSTANCE;
        }
    }

    public static final class b implements Flow<U> {

        public final Flow f978a;

        public final C1676c1 f979b;

        public static final class a implements FlowCollector<T> {

            public final FlowCollector f980a;

            public final C1676c1 f981b;

            @DebugMetadata(m1304c = "com.hp.bgp.util.BroadcastingMap$flow$$inlined$map$1$2", m1305f = "BroadcastingMap.kt", m1306i = {}, m1307l = {137}, m1308m = "emit", m1309n = {}, m1310s = {})
            public static final class C3359a extends ContinuationImpl {

                public Object f982a;

                public int f983b;

                public C3359a(Continuation continuation) {
                    super(continuation);
                }

                @Override
                public final Object invokeSuspend(Object obj) {
                    this.f982a = obj;
                    this.f983b |= Integer.MIN_VALUE;
                    return a.this.emit(null, this);
                }
            }

            public a(FlowCollector flowCollector, C1676c1 c1676c1) {
                this.f980a = flowCollector;
                this.f981b = c1676c1;
            }

            @Override
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public java.lang.Object emit(java.lang.Object r5, kotlin.coroutines.Continuation r6) throws java.lang.Throwable {
                /*
                    r4 = this;
                    boolean r0 = r6 instanceof com.p020hp.printsdk.C1676c1.b.a.C3359a
                    if (r0 == 0) goto L13
                    r0 = r6
                    com.hp.printsdk.c1$b$a$a r0 = (com.p020hp.printsdk.C1676c1.b.a.C3359a) r0
                    int r1 = r0.f983b
                    r2 = -2147483648(0xffffffff80000000, float:-0.0)
                    r3 = r1 & r2
                    if (r3 == 0) goto L13
                    int r1 = r1 - r2
                    r0.f983b = r1
                    goto L18
                L13:
                    com.hp.printsdk.c1$b$a$a r0 = new com.hp.printsdk.c1$b$a$a
                    r0.<init>(r6)
                L18:
                    java.lang.Object r6 = r0.f982a
                    java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                    int r2 = r0.f983b
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
                    kotlinx.coroutines.flow.FlowCollector r6 = r4.f980a
                    com.hp.printsdk.c1 r2 = r4.f981b
                    java.util.Map<T, U> r2 = r2.f969a
                    java.lang.Object r5 = r2.get(r5)
                    r0.f983b = r3
                    java.lang.Object r5 = r6.emit(r5, r0)
                    if (r5 != r1) goto L47
                    return r1
                L47:
                    kotlin.Unit r5 = kotlin.Unit.INSTANCE
                    return r5
                */
                throw new UnsupportedOperationException("Method not decompiled: com.p020hp.printsdk.C1676c1.b.a.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
            }
        }

        public b(Flow flow, C1676c1 c1676c1) {
            this.f978a = flow;
            this.f979b = c1676c1;
        }

        @Override
        public Object collect(FlowCollector flowCollector, Continuation continuation) {
            Object objCollect = this.f978a.collect(new a(flowCollector, this.f979b), continuation);
            return objCollect == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objCollect : Unit.INSTANCE;
        }
    }

    @DebugMetadata(m1304c = "com.hp.bgp.util.BroadcastingMap", m1305f = "BroadcastingMap.kt", m1306i = {0, 0}, m1307l = {77, 77}, m1308m = "getOrPut", m1309n = {"key", "$this$getOrPut_u24lambda_u2d6"}, m1310s = {"L$0", "L$1"})
    public static final class c extends ContinuationImpl {

        public Object f985a;

        public Object f986b;

        public Object f987c;

        public final C1676c1<T, U> f988d;

        public int f989e;

        public c(C1676c1<T, U> c1676c1, Continuation<? super c> continuation) {
            super(continuation);
            this.f988d = c1676c1;
        }

        @Override
        public final Object invokeSuspend(Object obj) {
            this.f987c = obj;
            this.f989e |= Integer.MIN_VALUE;
            return this.f988d.m479a((Object) null, (Function1) null, (Continuation) this);
        }
    }

    public final Object m477a(T t, U u, Continuation<? super Unit> continuation) {
        if (Intrinsics.areEqual(this.f969a.get(t), u)) {
            return Unit.INSTANCE;
        }
        Map<T, U> map = this.f969a;
        Intrinsics.checkNotNullExpressionValue(map, "map");
        map.put(t, u);
        Object objSend = this.f970b.send(t, continuation);
        return objSend == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objSend : Unit.INSTANCE;
    }

    public final Object m478a(T t, Continuation<? super Unit> continuation) {
        this.f969a.remove(t);
        Object objSend = this.f970b.send(t, continuation);
        return objSend == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objSend : Unit.INSTANCE;
    }

    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object m479a(T r6, kotlin.jvm.functions.Function1<? super kotlin.coroutines.Continuation<? super U>, ? extends java.lang.Object> r7, kotlin.coroutines.Continuation<? super U> r8) {
        /*
            r5 = this;
            boolean r0 = r8 instanceof com.p020hp.printsdk.C1676c1.c
            if (r0 == 0) goto L13
            r0 = r8
            com.hp.printsdk.c1$c r0 = (com.p020hp.printsdk.C1676c1.c) r0
            int r1 = r0.f989e
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.f989e = r1
            goto L18
        L13:
            com.hp.printsdk.c1$c r0 = new com.hp.printsdk.c1$c
            r0.<init>(r5, r8)
        L18:
            java.lang.Object r8 = r0.f987c
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.f989e
            r3 = 2
            r4 = 1
            if (r2 == 0) goto L40
            if (r2 == r4) goto L36
            if (r2 != r3) goto L2e
            java.lang.Object r6 = r0.f985a
            kotlin.ResultKt.throwOnFailure(r8)
            goto L69
        L2e:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L36:
            java.lang.Object r6 = r0.f986b
            com.hp.printsdk.c1 r6 = (com.p020hp.printsdk.C1676c1) r6
            java.lang.Object r7 = r0.f985a
            kotlin.ResultKt.throwOnFailure(r8)
            goto L5a
        L40:
            kotlin.ResultKt.throwOnFailure(r8)
            java.util.Map<T, U> r8 = r5.f969a
            java.lang.Object r8 = r8.get(r6)
            if (r8 != 0) goto L68
            r0.f985a = r6
            r0.f986b = r5
            r0.f989e = r4
            java.lang.Object r8 = r7.invoke(r0)
            if (r8 != r1) goto L58
            return r1
        L58:
            r7 = r6
            r6 = r5
        L5a:
            r0.f985a = r8
            r2 = 0
            r0.f986b = r2
            r0.f989e = r3
            java.lang.Object r6 = r6.m477a(r7, r8, r0)
            if (r6 != r1) goto L68
            return r1
        L68:
            r6 = r8
        L69:
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.p020hp.printsdk.C1676c1.m479a(java.lang.Object, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public final List<T> m480a() {
        List<T> list;
        Map<T, U> map = this.f969a;
        Intrinsics.checkNotNullExpressionValue(map, "map");
        synchronized (map) {
            list = CollectionsKt.toList(this.f969a.keySet());
        }
        return list;
    }

    public final Flow<U> m481a(T key) {
        Intrinsics.checkNotNullParameter(key, "key");
        return FlowKt.distinctUntilChanged(new b(new a(FlowKt.flow(new C1670b1(this, null)), key), this));
    }

    public final List<U> m482b() {
        List<U> list;
        Map<T, U> map = this.f969a;
        Intrinsics.checkNotNullExpressionValue(map, "map");
        synchronized (map) {
            list = CollectionsKt.toList(this.f969a.values());
        }
        return list;
    }
}
