package com.p020hp.printsdk;

import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.channels.Channel;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.MutableStateFlow;

@DebugMetadata(m1304c = "com.hp.bgp.ipp.JobQueue$print$1", m1305f = "JobQueue.kt", m1306i = {}, m1307l = {57, 133}, m1308m = "invokeSuspend", m1309n = {}, m1310s = {})
public final class C1784x0 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {

    public int f1954a;

    public final C1680d f1955b;

    public final AbstractC1774v0 f1956c;

    public final C1759s0 f1957d;

    public static final class a implements FlowCollector<C1668b> {

        public final AbstractC1774v0 f1958a;

        public a(AbstractC1774v0 abstractC1774v0) {
            this.f1958a = abstractC1774v0;
        }

        @Override
        public Object emit(C1668b c1668b, Continuation<? super Unit> continuation) {
            C1668b c1668b2 = c1668b;
            Object objM477a = this.f1958a.f1888d.m477a(c1668b2.f891a, c1668b2, continuation);
            return objM477a == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objM477a : Unit.INSTANCE;
        }
    }

    public C1784x0(C1680d c1680d, AbstractC1774v0 abstractC1774v0, C1759s0 c1759s0, Continuation<? super C1784x0> continuation) {
        super(2, continuation);
        this.f1955b = c1680d;
        this.f1956c = abstractC1774v0;
        this.f1957d = c1759s0;
    }

    @Override
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new C1784x0(this.f1955b, this.f1956c, this.f1957d, continuation);
    }

    @Override
    public Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return new C1784x0(this.f1955b, this.f1956c, this.f1957d, continuation).invokeSuspend(Unit.INSTANCE);
    }

    @Override
    public final Object invokeSuspend(Object obj) throws Throwable {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.f1954a;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            AbstractC1774v0.f1883h.invoke("print() " + this.f1955b);
            this.f1956c.f1889e.add(this.f1957d);
            Channel<Boolean> channel = this.f1956c.f1890f;
            Boolean boolBoxBoolean = Boxing.boxBoolean(true);
            this.f1954a = 1;
            if (channel.send(boolBoxBoolean, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                if (i != 2) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
                return Unit.INSTANCE;
            }
            ResultKt.throwOnFailure(obj);
        }
        MutableStateFlow<C1668b> mutableStateFlow = this.f1957d.f1721i;
        a aVar = new a(this.f1956c);
        this.f1954a = 2;
        if (mutableStateFlow.collect(aVar, this) == coroutine_suspended) {
            return coroutine_suspended;
        }
        return Unit.INSTANCE;
    }
}
