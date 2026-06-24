package com.p020hp.printsdk;

import com.p020hp.open.printsdk.HpPrintJob;
import com.p020hp.open.printsdk.HpPrintRequest;
import com.p020hp.open.printsdk.HpPrinter;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import org.apache.log4j.net.SyslogAppender;

@DebugMetadata(m1304c = "com.hp.printsdk.db.DbHelper$savePrinterWithJobs$1$onChanged$2", m1305f = "DbHelper.kt", m1306i = {}, m1307l = {SyslogAppender.LOG_LOCAL5, SyslogAppender.LOG_LOCAL5}, m1308m = "invokeSuspend", m1309n = {}, m1310s = {})
public final class C1761s2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {

    public int f1759a;

    public final HpPrinter f1760b;

    public final HpPrintJob f1761c;

    public final HpPrintRequest f1762d;

    public C1761s2(HpPrinter hpPrinter, HpPrintJob hpPrintJob, HpPrintRequest hpPrintRequest, Continuation<? super C1761s2> continuation) {
        super(2, continuation);
        this.f1760b = hpPrinter;
        this.f1761c = hpPrintJob;
        this.f1762d = hpPrintRequest;
    }

    @Override
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new C1761s2(this.f1760b, this.f1761c, this.f1762d, continuation);
    }

    @Override
    public Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return new C1761s2(this.f1760b, this.f1761c, this.f1762d, continuation).invokeSuspend(Unit.INSTANCE);
    }

    @Override
    public final Object invokeSuspend(Object obj) throws Throwable {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.f1759a;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            C1752q2 c1752q2 = C1752q2.f1624a;
            this.f1759a = 1;
            obj = c1752q2.m620a(this);
            if (obj == coroutine_suspended) {
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
        HpPrinter hpPrinter = this.f1760b;
        HpPrintJob hpPrintJob = this.f1761c;
        HpPrintRequest hpPrintRequest = this.f1762d;
        String jobName = hpPrintJob.getJobName();
        this.f1759a = 2;
        if (((AbstractC1771u2) obj).m666a(hpPrinter, hpPrintJob, hpPrintRequest, jobName, this) == coroutine_suspended) {
            return coroutine_suspended;
        }
        return Unit.INSTANCE;
    }
}
