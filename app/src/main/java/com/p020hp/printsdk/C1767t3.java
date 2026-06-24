package com.p020hp.printsdk;

import java.io.File;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.JobKt;

@DebugMetadata(m1304c = "com.hp.printsdk.ipp.DocumentPreview$pdf$2$1", m1305f = "DocumentPreview.kt", m1306i = {}, m1307l = {}, m1308m = "invokeSuspend", m1309n = {}, m1310s = {})
public final class C1767t3 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super C1729m>, Object> {

    public final C1663a0 f1816a;

    public final File f1817b;

    public final C1762s3 f1818c;

    public static final class a extends Lambda implements Function0<Boolean> {

        public final C1762s3 f1819a;

        public a(C1762s3 c1762s3) {
            super(0);
            this.f1819a = c1762s3;
        }

        @Override
        public Boolean invoke() {
            return Boolean.valueOf(JobKt.isActive(this.f1819a.f1766b));
        }
    }

    public C1767t3(C1663a0 c1663a0, File file, C1762s3 c1762s3, Continuation<? super C1767t3> continuation) {
        super(2, continuation);
        this.f1816a = c1663a0;
        this.f1817b = file;
        this.f1818c = c1762s3;
    }

    @Override
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new C1767t3(this.f1816a, this.f1817b, this.f1818c, continuation);
    }

    @Override
    public Object invoke(CoroutineScope coroutineScope, Continuation<? super C1729m> continuation) {
        return new C1767t3(this.f1816a, this.f1817b, this.f1818c, continuation).invokeSuspend(Unit.INSTANCE);
    }

    @Override
    public final Object invokeSuspend(Object obj) throws Throwable {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        ResultKt.throwOnFailure(obj);
        C1663a0 pdfRender = this.f1816a;
        File input = this.f1817b;
        C1762s3 c1762s3 = this.f1818c;
        C1714j options = c1762s3.f1770f;
        a isActive = new a(c1762s3);
        Intrinsics.checkNotNullParameter(pdfRender, "pdfRender");
        Intrinsics.checkNotNullParameter(input, "input");
        Intrinsics.checkNotNullParameter(options, "options");
        Intrinsics.checkNotNullParameter(isActive, "isActive");
        C1729m c1729m = new C1729m(pdfRender, options, isActive);
        c1729m.f1486h = input;
        return c1729m;
    }
}
