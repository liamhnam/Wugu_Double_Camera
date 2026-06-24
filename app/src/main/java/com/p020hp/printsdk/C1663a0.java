package com.p020hp.printsdk;

import android.content.Context;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.ParcelFileDescriptor;
import com.p020hp.mobile.common.utils.Logger;
import com.p020hp.mobile.common.utils.LoggerKt;
import java.io.File;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

public final class C1663a0 implements AutoCloseable {

    public static final b f874d;

    public static final Logger f875e;

    public final Context f876a;

    public final ServiceConnection f877b;

    public final InterfaceC1788y f878c;

    public static final class a implements InterfaceC1783x {

        public final InterfaceC1783x f879a;

        public final Function0<Unit> f880b;

        public a(InterfaceC1783x document, Function0<Unit> onClose) {
            Intrinsics.checkNotNullParameter(document, "document");
            Intrinsics.checkNotNullParameter(onClose, "onClose");
            this.f879a = document;
            this.f880b = onClose;
        }

        @Override
        public ParcelFileDescriptor mo461a(C1773v c1773v, C1694f0 c1694f0) {
            return this.f879a.mo461a(c1773v, c1694f0);
        }

        @Override
        public C1700g0 mo462a(int i) {
            return this.f879a.mo462a(i);
        }

        @Override
        public IBinder asBinder() {
            return this.f879a.asBinder();
        }

        @Override
        public void close() {
            this.f879a.close();
            this.f880b.invoke();
        }

        @Override
        public int getPageCount() {
            return this.f879a.getPageCount();
        }
    }

    public static final class b {
    }

    static {
        b bVar = new b();
        f874d = bVar;
        f875e = LoggerKt.logger(bVar);
    }

    public C1663a0(Context context, ServiceConnection serviceConnection, InterfaceC1788y service) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(serviceConnection, "serviceConnection");
        Intrinsics.checkNotNullParameter(service, "service");
        this.f876a = context;
        this.f877b = serviceConnection;
        this.f878c = service;
    }

    public final InterfaceC1783x m460a(File file) {
        Object objM1772constructorimpl;
        Intrinsics.checkNotNullParameter(file, "file");
        try {
            f875e.invoke("open(): sending " + file);
            objM1772constructorimpl = Result.m1772constructorimpl(this.f878c.mo405a(ParcelFileDescriptor.open(file, 268435456)));
        } catch (Throwable th) {
            objM1772constructorimpl = Result.m1772constructorimpl(ResultKt.createFailure(th));
        }
        Throwable thM1775exceptionOrNullimpl = Result.m1775exceptionOrNullimpl(objM1772constructorimpl);
        if (thM1775exceptionOrNullimpl != null) {
            f875e.m448e("Failed to open input", thM1775exceptionOrNullimpl);
        }
        if (Result.m1778isFailureimpl(objM1772constructorimpl)) {
            objM1772constructorimpl = null;
        }
        return (InterfaceC1783x) objM1772constructorimpl;
    }

    @Override
    public void close() {
        f875e.invoke("Connection.close()");
        this.f876a.unbindService(this.f877b);
    }
}
