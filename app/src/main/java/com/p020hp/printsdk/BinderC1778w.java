package com.p020hp.printsdk;

import android.os.ParcelFileDescriptor;
import com.p020hp.mobile.common.utils.Logger;
import com.p020hp.mobile.common.utils.LoggerKt;
import com.p020hp.open.printsdk.CoreManager;
import com.p020hp.printsdk.InterfaceC1783x;
import com.shockwave.pdfium.PdfDocument;
import com.shockwave.pdfium.PdfiumCore;
import java.io.BufferedOutputStream;
import java.io.IOException;
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
import kotlin.p037io.CloseableKt;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.BuildersKt__BuildersKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.JobKt;
import kotlinx.coroutines.MainCoroutineDispatcher;

public final class BinderC1778w extends InterfaceC1783x.b {

    public static final a f1930f;

    public static final Logger f1931g;

    public final CoroutineScope f1932a;

    public final PdfiumCore f1933b;

    public PdfDocument f1934c;

    public Job f1935d;

    public C1793z f1936e;

    public static final class a {
    }

    @DebugMetadata(m1304c = "com.hp.bgp.ext.render.DocumentRenderer$closePage$1", m1305f = "DocumentRenderer.kt", m1306i = {}, m1307l = {84}, m1308m = "invokeSuspend", m1309n = {}, m1310s = {})
    public static final class b extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {

        public int f1937a;

        public b(Continuation<? super b> continuation) {
            super(2, continuation);
        }

        @Override
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return BinderC1778w.this.new b(continuation);
        }

        @Override
        public Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return BinderC1778w.this.new b(continuation).invokeSuspend(Unit.INSTANCE);
        }

        @Override
        public final Object invokeSuspend(Object obj) throws Throwable {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.f1937a;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                Job job = BinderC1778w.this.f1935d;
                if (job != null) {
                    this.f1937a = 1;
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
            BinderC1778w.this.f1935d = null;
            return Unit.INSTANCE;
        }
    }

    public static final class c extends Lambda implements Function0<String> {

        public static final c f1939a = new c();

        public c() {
            super(0);
        }

        @Override
        public String invoke() {
            return " renderPageStripe";
        }
    }

    @DebugMetadata(m1304c = "com.hp.bgp.ext.render.DocumentRenderer$renderPageStripe$2$1", m1305f = "DocumentRenderer.kt", m1306i = {}, m1307l = {53}, m1308m = "invokeSuspend", m1309n = {}, m1310s = {})
    public static final class d extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {

        public int f1940a;

        public final ParcelFileDescriptor[] f1942c;

        public final C1773v f1943d;

        public final C1694f0 f1944e;

        @DebugMetadata(m1304c = "com.hp.bgp.ext.render.DocumentRenderer$renderPageStripe$2$1$1", m1305f = "DocumentRenderer.kt", m1306i = {}, m1307l = {}, m1308m = "invokeSuspend", m1309n = {}, m1310s = {})
        public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {

            public final BinderC1778w f1945a;

            public a(BinderC1778w binderC1778w, Continuation<? super a> continuation) {
                super(2, continuation);
                this.f1945a = binderC1778w;
            }

            @Override
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                return new a(this.f1945a, continuation);
            }

            @Override
            public Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                return new a(this.f1945a, continuation).invokeSuspend(Unit.INSTANCE);
            }

            @Override
            public final Object invokeSuspend(Object obj) throws Throwable {
                IntrinsicsKt.getCOROUTINE_SUSPENDED();
                ResultKt.throwOnFailure(obj);
                this.f1945a.f1935d = null;
                return Unit.INSTANCE;
            }
        }

        public d(ParcelFileDescriptor[] parcelFileDescriptorArr, C1773v c1773v, C1694f0 c1694f0, Continuation<? super d> continuation) {
            super(2, continuation);
            this.f1942c = parcelFileDescriptorArr;
            this.f1943d = c1773v;
            this.f1944e = c1694f0;
        }

        @Override
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return BinderC1778w.this.new d(this.f1942c, this.f1943d, this.f1944e, continuation);
        }

        @Override
        public Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return BinderC1778w.this.new d(this.f1942c, this.f1943d, this.f1944e, continuation).invokeSuspend(Unit.INSTANCE);
        }

        @Override
        public final Object invokeSuspend(Object obj) throws Throwable {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.f1940a;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                C1793z c1793z = BinderC1778w.this.f1936e;
                if (c1793z != null) {
                    ParcelFileDescriptor output = this.f1942c[1];
                    Intrinsics.checkNotNullExpressionValue(output, "pipes[1]");
                    C1773v area = this.f1943d;
                    C1694f0 renderSettings = this.f1944e;
                    Intrinsics.checkNotNullParameter(output, "output");
                    Intrinsics.checkNotNullParameter(area, "area");
                    Intrinsics.checkNotNullParameter(renderSettings, "renderSettings");
                    BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new ParcelFileDescriptor.AutoCloseOutputStream(output));
                    try {
                        c1793z.m693a(bufferedOutputStream, area, renderSettings);
                        CloseableKt.closeFinally(bufferedOutputStream, null);
                    } finally {
                    }
                }
                MainCoroutineDispatcher main = Dispatchers.getMain();
                a aVar = new a(BinderC1778w.this, null);
                this.f1940a = 1;
                if (BuildersKt.withContext(main, aVar, this) == coroutine_suspended) {
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

    static {
        a aVar = new a();
        f1930f = aVar;
        f1931g = LoggerKt.logger(aVar);
    }

    public BinderC1778w(CoroutineScope scope, ParcelFileDescriptor pfd) {
        Intrinsics.checkNotNullParameter(scope, "scope");
        Intrinsics.checkNotNullParameter(pfd, "pfd");
        this.f1932a = scope;
        PdfiumCore pdfiumCore$print_core_thirdPartyRelease = CoreManager.INSTANCE.getPdfiumCore$print_core_thirdPartyRelease();
        this.f1933b = pdfiumCore$print_core_thirdPartyRelease;
        this.f1934c = pdfiumCore$print_core_thirdPartyRelease.newDocument(pfd);
    }

    @Override
    public C1700g0 mo462a(int i) throws InterruptedException {
        if (m678b(i) != null) {
            return new C1700g0(r6.f2047d, r6.f2048e);
        }
        return null;
    }

    public final void m677a() throws InterruptedException {
        BuildersKt__BuildersKt.runBlocking$default(null, new b(null), 1, null);
        C1793z c1793z = this.f1936e;
        if (c1793z != null) {
            c1793z.close();
            this.f1936e = null;
        }
    }

    public final C1793z m678b(int i) throws InterruptedException {
        C1793z c1793z = this.f1936e;
        if (!(c1793z != null && c1793z.f2046c == i)) {
            m677a();
            f1931g.invoke("openPage(" + i + ')');
            this.f1933b.openPage(this.f1934c, i);
            PdfiumCore pdfiumCore = this.f1933b;
            PdfDocument pdfDocument = this.f1934c;
            Intrinsics.checkNotNull(pdfDocument);
            this.f1936e = new C1793z(pdfiumCore, pdfDocument, i);
        }
        return this.f1936e;
    }

    @Override
    public void close() throws InterruptedException {
        m677a();
        this.f1933b.closeDocument(this.f1934c);
        this.f1934c = null;
    }

    @Override
    public int getPageCount() {
        return this.f1933b.getPageCount(this.f1934c);
    }

    @Override
    public ParcelFileDescriptor mo461a(C1773v area, C1694f0 renderSettings) {
        Intrinsics.checkNotNullParameter(area, "area");
        Intrinsics.checkNotNullParameter(renderSettings, "renderSettings");
        f1931g.m446d(c.f1939a);
        if (m678b(area.f1875a) == null) {
            return null;
        }
        try {
            ParcelFileDescriptor[] parcelFileDescriptorArrCreatePipe = ParcelFileDescriptor.createPipe();
            this.f1935d = BuildersKt__Builders_commonKt.launch$default(this.f1932a, Dispatchers.getIO(), null, new d(parcelFileDescriptorArrCreatePipe, area, renderSettings, null), 2, null);
            return parcelFileDescriptorArrCreatePipe[0];
        } catch (IOException e) {
            f1931g.m448e("Couldn't create pipe", e);
            return null;
        }
    }
}
