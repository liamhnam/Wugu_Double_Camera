package com.p020hp.printsdk;

import java.util.UUID;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

@DebugMetadata(m1304c = "com.hp.printsdk.base.BasePrintService$cancelPrintJob$1", m1305f = "BasePrintService.kt", m1306i = {}, m1307l = {103, 103}, m1308m = "invokeSuspend", m1309n = {}, m1310s = {})
public final class C1712i2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {

    public int f1338a;

    public Object f1339b;

    public final AbstractC1717j2 f1340c;

    public final UUID f1341d;

    public C1712i2(AbstractC1717j2 abstractC1717j2, UUID uuid, Continuation<? super C1712i2> continuation) {
        super(2, continuation);
        this.f1340c = abstractC1717j2;
        this.f1341d = uuid;
    }

    @Override
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        C1712i2 c1712i2 = new C1712i2(this.f1340c, this.f1341d, continuation);
        c1712i2.f1339b = obj;
        return c1712i2;
    }

    @Override
    public Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        C1712i2 c1712i2 = new C1712i2(this.f1340c, this.f1341d, continuation);
        c1712i2.f1339b = coroutineScope;
        return c1712i2.invokeSuspend(Unit.INSTANCE);
    }

    @Override
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r6) throws java.lang.Throwable {
        /*
            r5 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r5.f1338a
            r2 = 2
            r3 = 1
            if (r1 == 0) goto L22
            if (r1 == r3) goto L1a
            if (r1 != r2) goto L12
            kotlin.ResultKt.throwOnFailure(r6)     // Catch: java.lang.Throwable -> L4d
            goto L46
        L12:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r0)
            throw r6
        L1a:
            java.lang.Object r1 = r5.f1339b
            java.util.UUID r1 = (java.util.UUID) r1
            kotlin.ResultKt.throwOnFailure(r6)     // Catch: java.lang.Throwable -> L4d
            goto L38
        L22:
            kotlin.ResultKt.throwOnFailure(r6)
            java.lang.Object r6 = r5.f1339b
            kotlinx.coroutines.CoroutineScope r6 = (kotlinx.coroutines.CoroutineScope) r6
            com.hp.printsdk.j2 r6 = r5.f1340c
            java.util.UUID r1 = r5.f1341d
            r5.f1339b = r1     // Catch: java.lang.Throwable -> L4d
            r5.f1338a = r3     // Catch: java.lang.Throwable -> L4d
            java.lang.Object r6 = r6.mo561a(r5)     // Catch: java.lang.Throwable -> L4d
            if (r6 != r0) goto L38
            return r0
        L38:
            com.hp.printsdk.g r6 = (com.p020hp.printsdk.InterfaceC1699g) r6     // Catch: java.lang.Throwable -> L4d
            r3 = 0
            r5.f1339b = r3     // Catch: java.lang.Throwable -> L4d
            r5.f1338a = r2     // Catch: java.lang.Throwable -> L4d
            java.lang.Object r6 = r6.mo520b(r1, r5)     // Catch: java.lang.Throwable -> L4d
            if (r6 != r0) goto L46
            return r0
        L46:
            kotlin.Unit r6 = kotlin.Unit.INSTANCE     // Catch: java.lang.Throwable -> L4d
            java.lang.Object r6 = kotlin.Result.m1772constructorimpl(r6)     // Catch: java.lang.Throwable -> L4d
            goto L56
        L4d:
            r6 = move-exception
            java.lang.Object r6 = kotlin.ResultKt.createFailure(r6)
            java.lang.Object r6 = kotlin.Result.m1772constructorimpl(r6)
        L56:
            com.hp.printsdk.j2 r0 = r5.f1340c
            java.util.UUID r1 = r5.f1341d
            java.lang.Throwable r2 = kotlin.Result.m1775exceptionOrNullimpl(r6)
            if (r2 == 0) goto L74
            com.hp.mobile.common.utils.Logger r0 = r0.f1367c
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            java.lang.String r4 = "Failed to cancel the job with uuid "
            r3.<init>(r4)
            java.lang.StringBuilder r1 = r3.append(r1)
            java.lang.String r1 = r1.toString()
            r0.m448e(r1, r2)
        L74:
            com.hp.printsdk.j2 r0 = r5.f1340c
            java.util.UUID r1 = r5.f1341d
            boolean r2 = kotlin.Result.m1779isSuccessimpl(r6)
            if (r2 == 0) goto L9a
            kotlin.Unit r6 = (kotlin.Unit) r6
            com.hp.mobile.common.utils.Logger r6 = r0.f1367c
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r2 = "Cancel the job with uuid "
            r0.<init>(r2)
            java.lang.StringBuilder r0 = r0.append(r1)
            java.lang.String r1 = " successfully"
            java.lang.StringBuilder r0 = r0.append(r1)
            java.lang.String r0 = r0.toString()
            r6.invoke(r0)
        L9a:
            kotlin.Unit r6 = kotlin.Unit.INSTANCE
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.p020hp.printsdk.C1712i2.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
