package com.p020hp.printsdk;

import com.p020hp.bgp.ext.render.PdfRenderService;
import com.p020hp.bgp.service.BackgroundPrintService;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;

@DebugMetadata(m1304c = "com.hp.bgp.service.BackgroundPrintService$converter$2$1", m1305f = "BackgroundPrintService.kt", m1306i = {}, m1307l = {39}, m1308m = "invokeSuspend", m1309n = {}, m1310s = {})
public final class C1664a1 extends SuspendLambda implements Function1<Continuation<? super C1663a0>, Object> {

    public int f881a;

    public final BackgroundPrintService f882b;

    public C1664a1(BackgroundPrintService backgroundPrintService, Continuation<? super C1664a1> continuation) {
        super(1, continuation);
        this.f882b = backgroundPrintService;
    }

    @Override
    public final Continuation<Unit> create(Continuation<?> continuation) {
        return new C1664a1(this.f882b, continuation);
    }

    @Override
    public Object invoke(Continuation<? super C1663a0> continuation) {
        return new C1664a1(this.f882b, continuation).invokeSuspend(Unit.INSTANCE);
    }

    @Override
    public final Object invokeSuspend(Object obj) throws Throwable {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.f881a;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            PdfRenderService.C1604a c1604a = PdfRenderService.f692b;
            BackgroundPrintService backgroundPrintService = this.f882b;
            this.f881a = 1;
            obj = c1604a.m406a(backgroundPrintService, this);
            if (obj == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        return obj;
    }
}
