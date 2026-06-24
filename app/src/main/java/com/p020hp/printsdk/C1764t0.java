package com.p020hp.printsdk;

import cc.uling.usdk.constants.BoardConst;
import com.p020hp.jipp.model.PowerState;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

@DebugMetadata(m1304c = "com.hp.bgp.ipp.JobEntry$pollForStatus$1", m1305f = "JobEntry.kt", m1306i = {0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2}, m1307l = {PowerState.Code.noChange, BoardConst.CODE_NOT_OPENED, 220}, m1308m = "invokeSuspend", m1309n = {"$this$invokeSuspend_u24lambda_u2d5", "toRequest", "lastTimestamp", "maxTimeOut", "jobId", "$this$invokeSuspend_u24lambda_u2d5", "toRequest", "lastTimestamp", "maxTimeOut", "jobId", "$this$invokeSuspend_u24lambda_u2d5", "toRequest", "lastTimestamp", "maxTimeOut", "jobId"}, m1310s = {"L$1", "L$2", "L$3", "I$0", "I$1", "L$1", "L$2", "L$3", "I$0", "I$1", "L$1", "L$2", "L$3", "I$0", "I$1"})
public final class C1764t0 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {

    public Object f1785a;

    public Object f1786b;

    public Object f1787c;

    public Object f1788d;

    public int f1789e;

    public int f1790f;

    public int f1791g;

    public Object f1792h;

    public final C1759s0 f1793i;

    public C1764t0(C1759s0 c1759s0, Continuation<? super C1764t0> continuation) {
        super(2, continuation);
        this.f1793i = c1759s0;
    }

    @Override
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        C1764t0 c1764t0 = new C1764t0(this.f1793i, continuation);
        c1764t0.f1792h = obj;
        return c1764t0;
    }

    @Override
    public Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        C1764t0 c1764t0 = new C1764t0(this.f1793i, continuation);
        c1764t0.f1792h = coroutineScope;
        return c1764t0.invokeSuspend(Unit.INSTANCE);
    }

    @Override
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r23) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 676
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.p020hp.printsdk.C1764t0.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
