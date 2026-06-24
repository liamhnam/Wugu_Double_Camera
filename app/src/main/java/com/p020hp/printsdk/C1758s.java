package com.p020hp.printsdk;

import android.os.ParcelFileDescriptor;
import com.p020hp.mobile.common.utils.Logger;
import com.p020hp.mobile.common.utils.LoggerKt;
import com.p020hp.open.printsdk.CoreManager;
import com.shockwave.pdfium.PdfDocument;
import com.shockwave.pdfium.PdfiumCore;
import java.io.File;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.JobKt;

public final class C1758s implements InterfaceC1749q {

    public static final a f1703e;

    public static final Logger f1704f;

    public final PdfiumCore f1705a;

    public PdfDocument f1706b;

    public Job f1707c;

    public C1793z f1708d;

    public static final class a {
    }

    @DebugMetadata(m1304c = "com.hp.bgp.ext.local.LocalDocument$closePage$1", m1305f = "LocalDocument.kt", m1306i = {}, m1307l = {75}, m1308m = "invokeSuspend", m1309n = {}, m1310s = {})
    public static final class b extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {

        public int f1709a;

        public b(Continuation<? super b> continuation) {
            super(2, continuation);
        }

        @Override
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return C1758s.this.new b(continuation);
        }

        @Override
        public Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return C1758s.this.new b(continuation).invokeSuspend(Unit.INSTANCE);
        }

        @Override
        public final Object invokeSuspend(Object obj) throws Throwable {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.f1709a;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                Job job = C1758s.this.f1707c;
                if (job != null) {
                    this.f1709a = 1;
                    if (JobKt.cancelAndJoin(job, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            C1758s.this.f1707c = null;
            return Unit.INSTANCE;
        }
    }

    static {
        a aVar = new a();
        f1703e = aVar;
        f1704f = LoggerKt.logger(aVar);
    }

    public C1758s(File pdfFile) {
        Intrinsics.checkNotNullParameter(pdfFile, "pdfFile");
        PdfiumCore pdfiumCore$print_core_thirdPartyRelease = CoreManager.INSTANCE.getPdfiumCore$print_core_thirdPartyRelease();
        this.f1705a = pdfiumCore$print_core_thirdPartyRelease;
        this.f1706b = pdfiumCore$print_core_thirdPartyRelease.newDocument(ParcelFileDescriptor.open(pdfFile, 268435456));
    }

    @Override
    public C1700g0 mo610a(int i) throws InterruptedException {
        if (m639b(i) != null) {
            return new C1700g0(r6.f2047d, r6.f2048e);
        }
        return null;
    }

    public final void m638a() throws InterruptedException {
        BuildersKt__BuildersKt.runBlocking$default(null, new b(null), 1, null);
        C1793z c1793z = this.f1708d;
        if (c1793z != null) {
            c1793z.close();
            this.f1708d = null;
        }
    }

    public final C1793z m639b(int i) throws InterruptedException {
        C1793z c1793z = this.f1708d;
        if (!(c1793z != null && c1793z.f2046c == i)) {
            m638a();
            f1704f.invoke("openPage(" + i + ')');
            this.f1705a.openPage(this.f1706b, i);
            PdfiumCore pdfiumCore = this.f1705a;
            PdfDocument pdfDocument = this.f1706b;
            Intrinsics.checkNotNull(pdfDocument);
            this.f1708d = new C1793z(pdfiumCore, pdfDocument, i);
        }
        return this.f1708d;
    }

    @Override
    public void close() throws InterruptedException {
        m638a();
        this.f1705a.closeDocument(this.f1706b);
        this.f1706b = null;
    }

    @Override
    public int getPageCount() {
        return this.f1705a.getPageCount(this.f1706b);
    }
}
