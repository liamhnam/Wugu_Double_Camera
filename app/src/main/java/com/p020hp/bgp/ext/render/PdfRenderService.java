package com.p020hp.bgp.ext.render;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.ParcelFileDescriptor;
import androidx.lifecycle.LifecycleOwnerKt;
import androidx.lifecycle.LifecycleService;
import com.p020hp.mobile.common.utils.Logger;
import com.p020hp.mobile.common.utils.LoggerKt;
import com.p020hp.printsdk.BinderC1778w;
import com.p020hp.printsdk.C1663a0;
import com.p020hp.printsdk.InterfaceC1783x;
import com.p020hp.printsdk.InterfaceC1788y;
import kotlin.Metadata;
import kotlin.NotImplementedError;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.SafeContinuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;

@Metadata(m1292d1 = {"\u00009\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002*\u0001\u0005\u0018\u0000 \u00122\u00020\u00012\u00020\u0002:\u0001\u0012B\u0005¢\u0006\u0002\u0010\u0003J\b\u0010\u0007\u001a\u00020\bH\u0016J\u0010\u0010\t\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\u000bH\u0016J\u0010\u0010\f\u001a\u00020\r2\u0006\u0010\n\u001a\u00020\u000bH\u0016J\u0012\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\u0006\u0010\u0010\u001a\u00020\u0011H\u0016R\u0010\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0006¨\u0006\u0013"}, m1293d2 = {"Lcom/hp/bgp/ext/render/PdfRenderService;", "Landroidx/lifecycle/LifecycleService;", "Lcom/hp/bgp/ext/render/IPdfRender;", "()V", "instance", "com/hp/bgp/ext/render/PdfRenderService$instance$1", "Lcom/hp/bgp/ext/render/PdfRenderService$instance$1;", "asBinder", "Landroid/os/IBinder;", "onBind", "intent", "Landroid/content/Intent;", "onUnbind", "", "openDocument", "Lcom/hp/bgp/ext/render/IDocument;", "pfd", "Landroid/os/ParcelFileDescriptor;", "Companion", "print-core_thirdPartyRelease"}, m1294k = 1, m1295mv = {1, 6, 0}, m1297xi = 48)
public final class PdfRenderService extends LifecycleService implements InterfaceC1788y {

    public static final C1604a f692b;

    public static final Logger f693c;

    public final BinderC1605b f694a = new BinderC1605b();

    public static final class C1604a {

        public static final class a implements ServiceConnection {

            public final Ref.BooleanRef f695a;

            public final Continuation<C1663a0> f696b;

            public final Context f697c;

            public a(Ref.BooleanRef booleanRef, Continuation<? super C1663a0> continuation, Context context) {
                this.f695a = booleanRef;
                this.f696b = continuation;
                this.f697c = context;
            }

            @Override
            public void onServiceConnected(ComponentName name, IBinder binder) {
                Intrinsics.checkNotNullParameter(name, "name");
                Intrinsics.checkNotNullParameter(binder, "binder");
                Ref.BooleanRef booleanRef = this.f695a;
                if (booleanRef.element) {
                    PdfRenderService.f693c.m447e(name + " is already resumed.");
                    return;
                }
                booleanRef.element = true;
                Continuation<C1663a0> continuation = this.f696b;
                Context context = this.f697c;
                InterfaceC1788y interfaceC1788yM686a = InterfaceC1788y.a.m686a(binder);
                Intrinsics.checkNotNullExpressionValue(interfaceC1788yM686a, "asInterface(binder)");
                continuation.resumeWith(Result.m1772constructorimpl(new C1663a0(context, this, interfaceC1788yM686a)));
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {
                Intrinsics.checkNotNullParameter(name, "name");
                PdfRenderService.f693c.invoke(name + " disconnected");
            }
        }

        public final Object m406a(Context context, Continuation<? super C1663a0> continuation) {
            SafeContinuation safeContinuation = new SafeContinuation(IntrinsicsKt.intercepted(continuation));
            PdfRenderService.f693c.invoke("connect pdf render service");
            context.bindService(new Intent(context, (Class<?>) PdfRenderService.class), new a(new Ref.BooleanRef(), safeContinuation, context), 1);
            Object orThrow = safeContinuation.getOrThrow();
            if (orThrow == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                DebugProbesKt.probeCoroutineSuspended(continuation);
            }
            return orThrow;
        }
    }

    public static final class BinderC1605b extends InterfaceC1788y.a implements InterfaceC1788y {
        public BinderC1605b() {
        }

        @Override
        public InterfaceC1783x mo405a(ParcelFileDescriptor parcelFileDescriptor) {
            PdfRenderService pdfRenderService = PdfRenderService.this;
            Intrinsics.checkNotNullExpressionValue(parcelFileDescriptor, "openDocument(...)");
            return pdfRenderService.mo405a(parcelFileDescriptor);
        }

        @Override
        public IBinder asBinder() {
            Intrinsics.checkNotNullExpressionValue(this, "super.asBinder()");
            return this;
        }
    }

    static {
        C1604a c1604a = new C1604a();
        f692b = c1604a;
        f693c = LoggerKt.logger(c1604a);
    }

    @Override
    public InterfaceC1783x mo405a(ParcelFileDescriptor pfd) {
        Object objM1772constructorimpl;
        Intrinsics.checkNotNullParameter(pfd, "pfd");
        try {
            f693c.invoke("openDocument()");
            objM1772constructorimpl = Result.m1772constructorimpl(new BinderC1778w(LifecycleOwnerKt.getLifecycleScope(this), pfd));
        } catch (Throwable th) {
            objM1772constructorimpl = Result.m1772constructorimpl(ResultKt.createFailure(th));
        }
        Throwable thM1775exceptionOrNullimpl = Result.m1775exceptionOrNullimpl(objM1772constructorimpl);
        if (thM1775exceptionOrNullimpl != null) {
            f693c.m448e("Could not open file descriptor for rendering", thM1775exceptionOrNullimpl);
        }
        if (Result.m1778isFailureimpl(objM1772constructorimpl)) {
            objM1772constructorimpl = null;
        }
        return (InterfaceC1783x) objM1772constructorimpl;
    }

    @Override
    public IBinder asBinder() {
        throw new NotImplementedError(null, 1, null);
    }

    @Override
    public IBinder onBind(Intent intent) {
        Intrinsics.checkNotNullParameter(intent, "intent");
        f693c.invoke("on pdf render service Bind");
        super.onBind(intent);
        return this.f694a;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Intrinsics.checkNotNullParameter(intent, "intent");
        f693c.invoke("on pdf render service Unbind");
        return super.onUnbind(intent);
    }
}
