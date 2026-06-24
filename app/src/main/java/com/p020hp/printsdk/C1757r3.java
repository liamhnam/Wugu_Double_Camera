package com.p020hp.printsdk;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.pdf.PdfDocument;
import android.net.Uri;
import android.print.PrintAttributes;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.p020hp.bgp.ext.render.PdfRenderService;
import com.p020hp.mobile.common.utils.Logger;
import com.p020hp.mobile.common.utils.LoggerKt;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Deferred;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;

public final class C1757r3 implements CoroutineScope {

    public static final a f1676l;

    public static final Logger f1677m;

    public final CoroutineContext f1678a;

    public final Context f1679b;

    public final Uri f1680c;

    public final MutableLiveData<C1700g0> f1681d;

    public final MutableLiveData<Integer> f1682e;

    public final LiveData<Integer> f1683f;

    public String f1684g;

    public String f1685h;

    public final Deferred<C1663a0> f1686i;

    public File f1687j;

    public final Deferred<File> f1688k;

    public static final class a {
    }

    @DebugMetadata(m1304c = "com.hp.printsdk.ipp.DocumentManager", m1305f = "DocumentManager.kt", m1306i = {0, 0, 1}, m1307l = {109, 110, 154}, m1308m = "analyzeContent", m1309n = {"this", "cacheFile", "this"}, m1310s = {"L$0", "L$1", "L$0"})
    public static final class b extends ContinuationImpl {

        public Object f1689a;

        public Object f1690b;

        public Object f1691c;

        public int f1693e;

        public b(Continuation<? super b> continuation) {
            super(continuation);
        }

        @Override
        public final Object invokeSuspend(Object obj) {
            this.f1691c = obj;
            this.f1693e |= Integer.MIN_VALUE;
            return C1757r3.m634a(C1757r3.this, null, this);
        }
    }

    @DebugMetadata(m1304c = "com.hp.printsdk.ipp.DocumentManager$analyzeContent$2$1", m1305f = "DocumentManager.kt", m1306i = {}, m1307l = {}, m1308m = "invokeSuspend", m1309n = {}, m1310s = {})
    public static final class c extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {

        public final InterfaceC1783x f1695b;

        public c(InterfaceC1783x interfaceC1783x, Continuation<? super c> continuation) {
            super(2, continuation);
            this.f1695b = interfaceC1783x;
        }

        @Override
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return C1757r3.this.new c(this.f1695b, continuation);
        }

        @Override
        public Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return C1757r3.this.new c(this.f1695b, continuation).invokeSuspend(Unit.INSTANCE);
        }

        @Override
        public final Object invokeSuspend(Object obj) throws Throwable {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            ResultKt.throwOnFailure(obj);
            C1757r3.this.f1682e.setValue(Boxing.boxInt(this.f1695b.getPageCount()));
            C1757r3.this.f1681d.setValue(this.f1695b.mo462a(0));
            return Unit.INSTANCE;
        }
    }

    @DebugMetadata(m1304c = "com.hp.printsdk.ipp.DocumentManager$analyzeContent$3$1$1", m1305f = "DocumentManager.kt", m1306i = {}, m1307l = {}, m1308m = "invokeSuspend", m1309n = {}, m1310s = {})
    public static final class d extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public d(Continuation<? super d> continuation) {
            super(2, continuation);
        }

        @Override
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return C1757r3.this.new d(continuation);
        }

        @Override
        public Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return C1757r3.this.new d(continuation).invokeSuspend(Unit.INSTANCE);
        }

        @Override
        public final Object invokeSuspend(Object obj) throws Throwable {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            ResultKt.throwOnFailure(obj);
            C1757r3.this.f1682e.setValue(Boxing.boxInt(1));
            return Unit.INSTANCE;
        }
    }

    @DebugMetadata(m1304c = "com.hp.printsdk.ipp.DocumentManager$content$1", m1305f = "DocumentManager.kt", m1306i = {}, m1307l = {67}, m1308m = "invokeSuspend", m1309n = {}, m1310s = {})
    public static final class e extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super File>, Object> {

        public int f1697a;

        public Object f1698b;

        public e(Continuation<? super e> continuation) {
            super(2, continuation);
        }

        @Override
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            e eVar = C1757r3.this.new e(continuation);
            eVar.f1698b = obj;
            return eVar;
        }

        @Override
        public Object invoke(CoroutineScope coroutineScope, Continuation<? super File> continuation) {
            e eVar = C1757r3.this.new e(continuation);
            eVar.f1698b = coroutineScope;
            return eVar.invokeSuspend(Unit.INSTANCE);
        }

        @Override
        public final Object invokeSuspend(Object obj) throws Throwable {
            Object objM1772constructorimpl;
            C1757r3 c1757r3;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.f1697a;
            try {
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    C1757r3 c1757r32 = C1757r3.this;
                    File file = new File(c1757r32.f1679b.getCacheDir(), "print");
                    file.deleteOnExit();
                    c1757r32.f1687j = file;
                    this.f1698b = c1757r32;
                    this.f1697a = 1;
                    if (c1757r32.m637a(this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    c1757r3 = c1757r32;
                } else {
                    if (i != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    c1757r3 = (C1757r3) this.f1698b;
                    ResultKt.throwOnFailure(obj);
                }
                File file2 = c1757r3.f1687j;
                if (file2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("tempFile");
                    file2 = null;
                }
                objM1772constructorimpl = Result.m1772constructorimpl(file2);
            } catch (Throwable th) {
                objM1772constructorimpl = Result.m1772constructorimpl(ResultKt.createFailure(th));
            }
            ResultKt.throwOnFailure(objM1772constructorimpl);
            return objM1772constructorimpl;
        }
    }

    @DebugMetadata(m1304c = "com.hp.printsdk.ipp.DocumentManager$service$1", m1305f = "DocumentManager.kt", m1306i = {}, m1307l = {52}, m1308m = "invokeSuspend", m1309n = {}, m1310s = {})
    public static final class f extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super C1663a0>, Object> {

        public int f1700a;

        public static final class a extends Lambda implements Function1<Throwable, Unit> {

            public final C1663a0 f1702a;

            public a(C1663a0 c1663a0) {
                super(1);
                this.f1702a = c1663a0;
            }

            @Override
            public Unit invoke(Throwable th) {
                C1757r3.f1677m.invoke("Closing connection to PdfRenderService");
                this.f1702a.close();
                return Unit.INSTANCE;
            }
        }

        public f(Continuation<? super f> continuation) {
            super(2, continuation);
        }

        @Override
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return C1757r3.this.new f(continuation);
        }

        @Override
        public Object invoke(CoroutineScope coroutineScope, Continuation<? super C1663a0> continuation) {
            return C1757r3.this.new f(continuation).invokeSuspend(Unit.INSTANCE);
        }

        @Override
        public final Object invokeSuspend(Object obj) throws Throwable {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.f1700a;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                PdfRenderService.C1604a c1604a = PdfRenderService.f692b;
                Context context = C1757r3.this.f1679b;
                this.f1700a = 1;
                obj = c1604a.m406a(context, this);
                if (obj == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            C1663a0 c1663a0 = (C1663a0) obj;
            Job job = (Job) C1757r3.this.f1678a.get(Job.INSTANCE);
            if (job != null) {
                job.invokeOnCompletion(new a(c1663a0));
            }
            return obj;
        }
    }

    static {
        a aVar = new a();
        f1676l = aVar;
        f1677m = LoggerKt.logger(aVar);
    }

    public C1757r3(CoroutineContext coroutineContext, Context context, Uri data) {
        Intrinsics.checkNotNullParameter(coroutineContext, "coroutineContext");
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(data, "data");
        this.f1678a = coroutineContext;
        this.f1679b = context;
        this.f1680c = data;
        this.f1681d = new MutableLiveData<>();
        MutableLiveData<Integer> mutableLiveData = new MutableLiveData<>();
        this.f1682e = mutableLiveData;
        this.f1683f = mutableLiveData;
        this.f1686i = BuildersKt__Builders_commonKt.async$default(this, null, null, new f(null), 3, null);
        this.f1688k = BuildersKt__Builders_commonKt.async$default(this, Dispatchers.getIO(), null, new e(null), 2, null);
    }

    public static final Object m634a(C1757r3 c1757r3, File file, Continuation continuation) {
        return c1757r3.m636a(null, continuation);
    }

    public final File m635a(Bitmap bitmap) throws IOException {
        Throwable th;
        FileOutputStream fileOutputStream;
        PdfDocument pdfDocument = new PdfDocument();
        int widthMils = (PrintAttributes.MediaSize.ISO_A4.getWidthMils() * 72) / 1000;
        int height = (int) (bitmap.getHeight() * (widthMils / bitmap.getWidth()));
        PdfDocument.Page pageStartPage = pdfDocument.startPage(new PdfDocument.PageInfo.Builder(widthMils, height, 0).create());
        Canvas canvas = pageStartPage.getCanvas();
        Intrinsics.checkNotNullExpressionValue(canvas, "page.canvas");
        canvas.drawBitmap(Bitmap.createScaledBitmap(bitmap, widthMils, height, true), new Matrix(), null);
        pdfDocument.finishPage(pageStartPage);
        File file = new File(this.f1679b.getCacheDir(), "print");
        file.deleteOnExit();
        try {
            fileOutputStream = new FileOutputStream(file);
            try {
                pdfDocument.writeTo(fileOutputStream);
                Result.m1772constructorimpl(Unit.INSTANCE);
            } catch (Throwable th2) {
                th = th2;
                Result.m1772constructorimpl(ResultKt.createFailure(th));
            }
        } catch (Throwable th3) {
            th = th3;
            fileOutputStream = null;
        }
        pdfDocument.close();
        if (fileOutputStream != null) {
            fileOutputStream.close();
        }
        return file;
    }

    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object m636a(java.io.File r19, kotlin.coroutines.Continuation<? super kotlin.Unit> r20) throws java.lang.Exception {
        /*
            Method dump skipped, instruction units count: 532
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.p020hp.printsdk.C1757r3.m636a(java.io.File, kotlin.coroutines.Continuation):java.lang.Object");
    }

    @Override
    public CoroutineContext getCoroutineContext() {
        return this.f1678a;
    }

    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object m637a(kotlin.coroutines.Continuation<? super kotlin.Unit> r13) throws java.lang.Exception {
        /*
            Method dump skipped, instruction units count: 342
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.p020hp.printsdk.C1757r3.m637a(kotlin.coroutines.Continuation):java.lang.Object");
    }
}
