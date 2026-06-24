package com.p020hp.printsdk;

import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

@DebugMetadata(m1304c = "com.hp.bgp.ipp.JobEntry$withResponse$1$1$1", m1305f = "JobEntry.kt", m1306i = {}, m1307l = {277}, m1308m = "invokeSuspend", m1309n = {}, m1310s = {})
public final class C1769u0 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {

    public int f1844a;

    public final C1759s0 f1845b;

    public C1769u0(C1759s0 c1759s0, Continuation<? super C1769u0> continuation) {
        super(2, continuation);
        this.f1845b = c1759s0;
    }

    @Override
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new C1769u0(this.f1845b, continuation);
    }

    @Override
    public Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return new C1769u0(this.f1845b, continuation).invokeSuspend(Unit.INSTANCE);
    }

    @Override
    public final Object invokeSuspend(Object obj) throws Throwable {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.f1844a;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            C1759s0 c1759s0 = this.f1845b;
            this.f1844a = 1;
            if (c1759s0.m649a(this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        return Unit.INSTANCE;
    }
}
