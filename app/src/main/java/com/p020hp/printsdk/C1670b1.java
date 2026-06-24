package com.p020hp.printsdk;

import java.util.Iterator;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.channels.ReceiveChannel;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;

@DebugMetadata(m1304c = "com.hp.bgp.util.BroadcastingMap$changes$1", m1305f = "BroadcastingMap.kt", m1306i = {0}, m1307l = {70, 71}, m1308m = "invokeSuspend", m1309n = {"$this$flow"}, m1310s = {"L$0"})
public final class C1670b1<T> extends SuspendLambda implements Function2<FlowCollector<? super T>, Continuation<? super Unit>, Object> {

    public Object f906a;

    public int f907b;

    public Object f908c;

    public final C1676c1<T, U> f909d;

    public C1670b1(C1676c1<T, U> c1676c1, Continuation<? super C1670b1> continuation) {
        super(2, continuation);
        this.f909d = c1676c1;
    }

    @Override
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        C1670b1 c1670b1 = new C1670b1(this.f909d, continuation);
        c1670b1.f908c = obj;
        return c1670b1;
    }

    @Override
    public Object invoke(Object obj, Continuation<? super Unit> continuation) {
        C1670b1 c1670b1 = new C1670b1(this.f909d, continuation);
        c1670b1.f908c = (FlowCollector) obj;
        return c1670b1.invokeSuspend(Unit.INSTANCE);
    }

    @Override
    public final Object invokeSuspend(Object obj) throws Throwable {
        FlowCollector flowCollector;
        Iterator<T> it;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.f907b;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            flowCollector = (FlowCollector) this.f908c;
            it = this.f909d.m480a().iterator();
        } else {
            if (i != 1) {
                if (i != 2) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
                return Unit.INSTANCE;
            }
            it = (Iterator) this.f906a;
            flowCollector = (FlowCollector) this.f908c;
            ResultKt.throwOnFailure(obj);
        }
        while (it.hasNext()) {
            T next = it.next();
            this.f908c = flowCollector;
            this.f906a = it;
            this.f907b = 1;
            if (flowCollector.emit(next, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        }
        ReceiveChannel<T> receiveChannelOpenSubscription = this.f909d.f970b.openSubscription();
        this.f908c = null;
        this.f906a = null;
        this.f907b = 2;
        if (FlowKt.emitAll(flowCollector, receiveChannelOpenSubscription, this) == coroutine_suspended) {
            return coroutine_suspended;
        }
        return Unit.INSTANCE;
    }
}
