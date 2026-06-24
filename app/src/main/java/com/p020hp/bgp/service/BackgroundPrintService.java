package com.p020hp.bgp.service;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.net.wifi.WifiManager;
import android.os.Binder;
import android.os.Build;
import android.os.IBinder;
import androidx.core.app.NotificationCompat;
import androidx.lifecycle.LifecycleOwnerKt;
import androidx.lifecycle.LifecycleService;
import com.p020hp.mobile.common.utils.Logger;
import com.p020hp.mobile.common.utils.LoggerKt;
import com.p020hp.printsdk.AbstractC1750q0;
import com.p020hp.printsdk.C1664a1;
import com.p020hp.printsdk.C1705h0;
import com.p020hp.printsdk.C1709i;
import com.p020hp.printsdk.C1745p0;
import com.p020hp.printsdk.ServiceConnectionC1794z0;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.SafeContinuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(m1292d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\u0018\u0000 \u001d2\u00020\u0001:\u0003\u001d\u001e\u001fB\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0019H\u0016J\b\u0010\u001a\u001a\u00020\u001bH\u0016J\b\u0010\u001c\u001a\u00020\u001bH\u0016R\u0012\u0010\u0003\u001a\u00060\u0004R\u00020\u0000X\u0082\u0004¢\u0006\u0002\n\u0000R\u001b\u0010\u0005\u001a\u00020\u00068FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u0007\u0010\bR\u001b\u0010\u000b\u001a\u00020\f8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u000f\u0010\n\u001a\u0004\b\r\u0010\u000eR\u0012\u0010\u0010\u001a\u00060\u0011R\u00020\u0012X\u0082.¢\u0006\u0002\n\u0000R\u001b\u0010\u0013\u001a\u00020\f8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0015\u0010\n\u001a\u0004\b\u0014\u0010\u000e¨\u0006 "}, m1293d2 = {"Lcom/hp/bgp/service/BackgroundPrintService;", "Landroidx/lifecycle/LifecycleService;", "()V", "binder", "Lcom/hp/bgp/service/BackgroundPrintService$LocalBinder;", "converter", "Lcom/hp/bgp/ipp/FormatConverter;", "getConverter", "()Lcom/hp/bgp/ipp/FormatConverter;", "converter$delegate", "Lkotlin/Lazy;", "ippPrinters", "Lcom/hp/bgp/Printers;", "getIppPrinters", "()Lcom/hp/bgp/Printers;", "ippPrinters$delegate", "multicastLock", "Landroid/net/wifi/WifiManager$MulticastLock;", "Landroid/net/wifi/WifiManager;", "usbPrinters", "getUsbPrinters", "usbPrinters$delegate", "onBind", "Landroid/os/IBinder;", "intent", "Landroid/content/Intent;", "onCreate", "", "onDestroy", "Companion", "Connection", "LocalBinder", "print-core_thirdPartyRelease"}, m1294k = 1, m1295mv = {1, 6, 0}, m1297xi = 48)
public final class BackgroundPrintService extends LifecycleService {

    public static final C1606a f699e;

    public static final Logger f700f;

    public WifiManager.MulticastLock f702b;

    public final BinderC1608c f701a = new BinderC1608c();

    public final Lazy f703c = LazyKt.lazy(new C1609d());

    public final Lazy f704d = LazyKt.lazy(new C1610e());

    public static final class C1606a {

        public static final class a extends Lambda implements Function1<C1607b, Unit> {

            public final Continuation<C1607b> f705a;

            public a(Continuation<? super C1607b> continuation) {
                super(1);
                this.f705a = continuation;
            }

            @Override
            public Unit invoke(C1607b c1607b) {
                C1607b it = c1607b;
                Intrinsics.checkNotNullParameter(it, "it");
                this.f705a.resumeWith(Result.m1772constructorimpl(it));
                return Unit.INSTANCE;
            }
        }

        public final Object m407a(Context context, Continuation<? super C1607b> continuation) throws Throwable {
            SafeContinuation safeContinuation = new SafeContinuation(IntrinsicsKt.intercepted(continuation));
            C1606a c1606a = BackgroundPrintService.f699e;
            context.bindService(new Intent(context, (Class<?>) BackgroundPrintService.class), new ServiceConnectionC1794z0(new a(safeContinuation), context), 1);
            Object orThrow = safeContinuation.getOrThrow();
            if (orThrow == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                DebugProbesKt.probeCoroutineSuspended(continuation);
            }
            return orThrow;
        }
    }

    public static final class C1607b implements AutoCloseable {

        public final Context f706a;

        public final ServiceConnection f707b;

        public final BackgroundPrintService f708c;

        public C1607b(Context context, ServiceConnection connection, BackgroundPrintService service) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(connection, "connection");
            Intrinsics.checkNotNullParameter(service, "service");
            this.f706a = context;
            this.f707b = connection;
            this.f708c = service;
        }

        @Override
        public void close() {
            BackgroundPrintService.f700f.invoke("bgp service Connection.close()");
            this.f706a.unbindService(this.f707b);
        }
    }

    public final class BinderC1608c extends Binder {

        public final BackgroundPrintService f709a;

        public BinderC1608c() {
            this.f709a = BackgroundPrintService.this;
        }
    }

    public static final class C1609d extends Lambda implements Function0<C1705h0> {
        public C1609d() {
            super(0);
        }

        @Override
        public C1705h0 invoke() {
            return new C1705h0(LifecycleOwnerKt.getLifecycleScope(BackgroundPrintService.this).getCoroutineContext(), new C1664a1(BackgroundPrintService.this, null));
        }
    }

    public static final class C1610e extends Lambda implements Function0<AbstractC1750q0> {
        public C1610e() {
            super(0);
        }

        @Override
        public AbstractC1750q0 invoke() {
            AbstractC1750q0.a aVar = AbstractC1750q0.f1585d;
            CoroutineContext context = LifecycleOwnerKt.getLifecycleScope(BackgroundPrintService.this).getCoroutineContext();
            C1709i printerTransport = new C1709i(null, 1);
            C1705h0 formatter = (C1705h0) BackgroundPrintService.this.f703c.getValue();
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(printerTransport, "printerTransport");
            Intrinsics.checkNotNullParameter(formatter, "formatter");
            return new C1745p0(printerTransport, context, formatter);
        }
    }

    public static final class C1611f extends Lambda implements Function0<AbstractC1750q0> {
        public C1611f() {
            super(0);
        }

        @Override
        public AbstractC1750q0 invoke() {
            AbstractC1750q0.a aVar = AbstractC1750q0.f1585d;
            CoroutineContext context = LifecycleOwnerKt.getLifecycleScope(BackgroundPrintService.this).getCoroutineContext();
            C1709i printerTransport = new C1709i(null, 1);
            C1705h0 formatter = (C1705h0) BackgroundPrintService.this.f703c.getValue();
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(printerTransport, "printerTransport");
            Intrinsics.checkNotNullParameter(formatter, "formatter");
            return new C1745p0(printerTransport, context, formatter);
        }
    }

    static {
        C1606a c1606a = new C1606a();
        f699e = c1606a;
        f700f = LoggerKt.logger(c1606a);
    }

    public BackgroundPrintService() {
        LazyKt.lazy(new C1611f());
    }

    @Override
    public IBinder onBind(Intent intent) {
        Intrinsics.checkNotNullParameter(intent, "intent");
        super.onBind(intent);
        f700f.invoke("on bgp service Bind");
        Object systemService = getSystemService("notification");
        if (systemService == null) {
            throw new NullPointerException("null cannot be cast to non-null type android.app.NotificationManager");
        }
        NotificationManager notificationManager = (NotificationManager) systemService;
        NotificationCompat.Builder autoCancel = new NotificationCompat.Builder(this).setStyle(new NotificationCompat.DecoratedCustomViewStyle()).setVisibility(1).setAutoCancel(true);
        if (Build.VERSION.SDK_INT >= 26) {
            NotificationChannel notificationChannel = new NotificationChannel("HP_SDK_PRINT_CHANNEL_ID", "HP_SDK_PRINT_CHANNEL_ID", 3);
            notificationManager.createNotificationChannel(notificationChannel);
            notificationChannel.setShowBadge(true);
            autoCancel.setChannelId("HP_SDK_PRINT_CHANNEL_ID");
        }
        startForeground(2, autoCancel.build());
        return this.f701a;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        f700f.invoke("on bgp service Create");
        Object systemService = getApplicationContext().getSystemService("wifi");
        if (systemService == null) {
            throw new NullPointerException("null cannot be cast to non-null type android.net.wifi.WifiManager");
        }
        WifiManager.MulticastLock multicastLockCreateMulticastLock = ((WifiManager) systemService).createMulticastLock(BackgroundPrintService.class.getName());
        multicastLockCreateMulticastLock.acquire();
        Intrinsics.checkNotNullExpressionValue(multicastLockCreateMulticastLock, "applicationContext.getSy…me).also { it.acquire() }");
        this.f702b = multicastLockCreateMulticastLock;
    }

    @Override
    public void onDestroy() {
        f700f.invoke("on bgp service Destroy");
        WifiManager.MulticastLock multicastLock = this.f702b;
        if (multicastLock == null) {
            Intrinsics.throwUninitializedPropertyAccessException("multicastLock");
            multicastLock = null;
        }
        multicastLock.release();
        stopForeground(true);
        super.onDestroy();
    }
}
