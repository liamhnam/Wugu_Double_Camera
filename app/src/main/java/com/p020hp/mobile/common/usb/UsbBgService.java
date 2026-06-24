package com.p020hp.mobile.common.usb;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.hardware.usb.UsbDevice;
import android.os.Binder;
import android.os.Build;
import android.os.IBinder;
import androidx.core.app.NotificationCompat;
import androidx.lifecycle.LifecycleService;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.p020hp.mobile.common.CommonLibKt;
import com.p020hp.mobile.common.utils.Logger;
import com.p020hp.mobile.common.utils.LoggerKt;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Result;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.SafeContinuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.eclipse.paho.android.service.MqttServiceConstants;
import p066do.p026do.p028if.p029do.p068case.Ccase;
import p066do.p026do.p028if.p029do.p068case.Ccase.C2062do;
import p066do.p026do.p028if.p029do.p068case.Cnew;

@Metadata(m1292d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0005\b\u0000\u0018\u0000 %2\u00020\u0001:\u0004%&'(B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0018H\u0016J\b\u0010\u0019\u001a\u00020\u001aH\u0016J\b\u0010\u001b\u001a\u00020\u001aH\u0016J\"\u0010\u001c\u001a\u00020\u001d2\b\u0010\u0017\u001a\u0004\u0018\u00010\u00182\u0006\u0010\u001e\u001a\u00020\u001d2\u0006\u0010\u001f\u001a\u00020\u001dH\u0016J\u0006\u0010 \u001a\u00020\u001aJ\u0018\u0010!\u001a\u00020\u001a2\u0006\u0010\"\u001a\u00020\u00072\u0006\u0010#\u001a\u00020$H\u0002R\u0012\u0010\u0003\u001a\u00060\u0004R\u00020\u0000X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u001b\u0010\b\u001a\u00020\t8@X\u0080\u0084\u0002¢\u0006\f\n\u0004\b\f\u0010\r\u001a\u0004\b\n\u0010\u000bR\u0017\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000f¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0014\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00100\u0014X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006)"}, m1293d2 = {"Lcom/hp/mobile/common/usb/UsbBgService;", "Landroidx/lifecycle/LifecycleService;", "()V", "binder", "Lcom/hp/mobile/common/usb/UsbBgService$LocalBinder;", "cache", "", "Landroid/hardware/usb/UsbDevice;", "connections", "Lcom/hp/mobile/common/usb/UsbConnectionManager;", "getConnections$common_lib_release", "()Lcom/hp/mobile/common/usb/UsbConnectionManager;", "connections$delegate", "Lkotlin/Lazy;", "liveDataForUsbDevice", "Landroidx/lifecycle/LiveData;", "Lcom/hp/mobile/common/usb/UsbBgService$UsbDiscoveryEvent;", "getLiveDataForUsbDevice", "()Landroidx/lifecycle/LiveData;", "mLiveDataForUsbDevices", "Landroidx/lifecycle/MutableLiveData;", "onBind", "Landroid/os/IBinder;", "intent", "Landroid/content/Intent;", "onCreate", "", "onDestroy", "onStartCommand", "", "flags", "startId", "reCheckAllUsbDevices", "updateCache", "usbDevice", "isConnected", "", "Companion", "Connection", "LocalBinder", "UsbDiscoveryEvent", "common-lib_release"}, m1294k = 1, m1295mv = {1, 6, 0}, m1297xi = 48)
public final class UsbBgService extends LifecycleService {

    public static final Companion INSTANCE = new Companion(null);
    public static final Logger log = LoggerKt.logger(LoggerKt.toTag("UsbBgService"));
    public final List<UsbDevice> cache;
    public final LiveData<UsbDiscoveryEvent> liveDataForUsbDevice;
    public final MutableLiveData<UsbDiscoveryEvent> mLiveDataForUsbDevices;

    public final Lazy connections = LazyKt.lazy(C1660if.f771if);
    public final BinderC1659do binder = new BinderC1659do();

    @Metadata(m1292d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0019\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\tJ\"\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0007\u001a\u00020\b2\u0012\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u000b0\rR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u000e"}, m1293d2 = {"Lcom/hp/mobile/common/usb/UsbBgService$Companion;", "", "()V", "log", "Lcom/hp/mobile/common/utils/Logger;", MqttServiceConstants.CONNECT_ACTION, "Lcom/hp/mobile/common/usb/UsbBgService$Connection;", "context", "Landroid/content/Context;", "(Landroid/content/Context;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "connectAsync", "", "callback", "Lkotlin/Function1;", "common-lib_release"}, m1294k = 1, m1295mv = {1, 6, 0}, m1297xi = 48)
    public static final class Companion {

        public static final class C1657do extends Lambda implements Function1<Connection, Unit> {

            public final Continuation<Connection> f765if;

            public C1657do(Continuation<? super Connection> continuation) {
                super(1);
                this.f765if = continuation;
            }

            @Override
            public Unit invoke(Connection connection) {
                Connection it = connection;
                Intrinsics.checkNotNullParameter(it, "it");
                this.f765if.resumeWith(Result.m1772constructorimpl(it));
                return Unit.INSTANCE;
            }
        }

        public static final class ServiceConnectionC1658if implements ServiceConnection {

            public final Function1<Connection, Unit> f766do;

            public final Context f767if;

            public ServiceConnectionC1658if(Function1<? super Connection, Unit> function1, Context context) {
                this.f766do = function1;
                this.f767if = context;
            }

            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                Intrinsics.checkNotNullParameter(name, "name");
                Intrinsics.checkNotNullParameter(service, "service");
                UsbBgService.log.invoke("onServiceConnected(), componentName: " + name);
                this.f766do.invoke(new Connection(this.f767if, this, ((BinderC1659do) service).f768do));
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {
                Intrinsics.checkNotNullParameter(name, "name");
                UsbBgService.log.m447e("onServiceDisconnected(), componentName: " + name);
            }
        }

        public Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final Object connect(Context context, Continuation<? super Connection> continuation) throws Throwable {
            SafeContinuation safeContinuation = new SafeContinuation(IntrinsicsKt.intercepted(continuation));
            UsbBgService.INSTANCE.connectAsync(context, new C1657do(safeContinuation));
            Object orThrow = safeContinuation.getOrThrow();
            if (orThrow == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                DebugProbesKt.probeCoroutineSuspended(continuation);
            }
            return orThrow;
        }

        public final void connectAsync(Context context, Function1<? super Connection, Unit> callback) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(callback, "callback");
            context.bindService(new Intent(context, (Class<?>) UsbBgService.class), new ServiceConnectionC1658if(callback, context), 1);
        }
    }

    @Metadata(m1292d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\b\u0010\u000b\u001a\u00020\fH\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\r"}, m1293d2 = {"Lcom/hp/mobile/common/usb/UsbBgService$Connection;", "Ljava/lang/AutoCloseable;", "context", "Landroid/content/Context;", "connection", "Landroid/content/ServiceConnection;", NotificationCompat.CATEGORY_SERVICE, "Lcom/hp/mobile/common/usb/UsbBgService;", "(Landroid/content/Context;Landroid/content/ServiceConnection;Lcom/hp/mobile/common/usb/UsbBgService;)V", "getService", "()Lcom/hp/mobile/common/usb/UsbBgService;", "close", "", "common-lib_release"}, m1294k = 1, m1295mv = {1, 6, 0}, m1297xi = 48)
    public static final class Connection implements AutoCloseable {
        public final ServiceConnection connection;
        public final Context context;
        public final UsbBgService service;

        public Connection(Context context, ServiceConnection connection, UsbBgService service) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(connection, "connection");
            Intrinsics.checkNotNullParameter(service, "service");
            this.context = context;
            this.connection = connection;
            this.service = service;
        }

        @Override
        public void close() {
            UsbBgService.log.invoke("Usb.Connection -> close()");
            this.context.unbindService(this.connection);
        }

        public final UsbBgService getService() {
            return this.service;
        }
    }

    @Metadata(m1292d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0007\u0018\u00002\u00020\u0001B+\u0012\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0004\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bR\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\r¨\u0006\u000e"}, m1293d2 = {"Lcom/hp/mobile/common/usb/UsbBgService$UsbDiscoveryEvent;", "", "cache", "", "Landroid/hardware/usb/UsbDevice;", "device", "isConnected", "", "(Ljava/util/List;Landroid/hardware/usb/UsbDevice;Z)V", "getCache", "()Ljava/util/List;", "getDevice", "()Landroid/hardware/usb/UsbDevice;", "()Z", "common-lib_release"}, m1294k = 1, m1295mv = {1, 6, 0}, m1297xi = 48)
    public static final class UsbDiscoveryEvent {
        public final List<UsbDevice> cache;
        public final UsbDevice device;
        public final boolean isConnected;

        public UsbDiscoveryEvent() {
            this(null, null, false, 7, null);
        }

        public UsbDiscoveryEvent(List<? extends UsbDevice> cache, UsbDevice usbDevice, boolean z) {
            Intrinsics.checkNotNullParameter(cache, "cache");
            this.cache = cache;
            this.device = usbDevice;
            this.isConnected = z;
        }

        public UsbDiscoveryEvent(List list, UsbDevice usbDevice, boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this((i & 1) != 0 ? CollectionsKt.emptyList() : list, (i & 2) != 0 ? null : usbDevice, (i & 4) != 0 ? false : z);
        }

        public final List<UsbDevice> getCache() {
            return this.cache;
        }

        public final UsbDevice getDevice() {
            return this.device;
        }

        public final boolean getIsConnected() {
            return this.isConnected;
        }
    }

    public final class BinderC1659do extends Binder {

        public final UsbBgService f768do;

        public BinderC1659do() {
            this.f768do = UsbBgService.this;
        }
    }

    public static final class Cfor implements Cnew.InterfaceC2068do {
        public Cfor() {
        }

        @Override
        public void mo444do(UsbDevice usbDevice, boolean z) {
            Intrinsics.checkNotNullParameter(usbDevice, "usbDevice");
            UsbBgService.log.invoke("Usb -> onUsbDeviceConnected(" + usbDevice.getProductName() + ", isConnected = " + z + ')');
            UsbBgService.this.updateCache(usbDevice, z);
        }
    }

    public static final class C1660if extends Lambda implements Function0<Cnew> {

        public static final C1660if f771if = new C1660if();

        public C1660if() {
            super(0);
        }

        @Override
        public Cnew invoke() {
            return new Cnew(CommonLibKt.context());
        }
    }

    public UsbBgService() {
        MutableLiveData<UsbDiscoveryEvent> mutableLiveData = new MutableLiveData<>();
        this.mLiveDataForUsbDevices = mutableLiveData;
        this.liveDataForUsbDevice = mutableLiveData;
        this.cache = new ArrayList();
    }

    public final void updateCache(UsbDevice usbDevice, boolean isConnected) {
        log.invoke("Usb -> updateCache() usbDevice = " + usbDevice.getDeviceName() + ' ');
        List<UsbDevice> list = this.cache;
        if (isConnected) {
            list.add(usbDevice);
        } else {
            list.remove(usbDevice);
        }
        this.mLiveDataForUsbDevices.setValue(new UsbDiscoveryEvent(CollectionsKt.toList(this.cache), usbDevice, isConnected));
    }

    public final Cnew getConnections$common_lib_release() {
        return (Cnew) this.connections.getValue();
    }

    public final LiveData<UsbDiscoveryEvent> getLiveDataForUsbDevice() {
        return this.liveDataForUsbDevice;
    }

    @Override
    public IBinder onBind(Intent intent) {
        Intrinsics.checkNotNullParameter(intent, "intent");
        log.invoke("Usb -> onBind()");
        super.onBind(intent);
        return this.binder;
    }

    @Override
    public void onDestroy() {
        log.invoke("Usb -> onDestroy()");
        super.onDestroy();
        this.cache.clear();
        this.mLiveDataForUsbDevices.setValue(new UsbDiscoveryEvent(null, null, false, 7, null));
        Cnew connections$common_lib_release = getConnections$common_lib_release();
        connections$common_lib_release.f3912case = null;
        connections$common_lib_release.f2496if.invoke("usb connection manager end");
        connections$common_lib_release.f2495do.unregisterReceiver(connections$common_lib_release.f3913else);
        Ccase ccaseM1755for = connections$common_lib_release.m1755for();
        Ccase.C2062do c2062do = ccaseM1755for.f3896new;
        if (c2062do != null) {
            ccaseM1755for.f2483do.unregisterReceiver(c2062do);
            ccaseM1755for.f3896new = null;
        }
        ccaseM1755for.f3897try.clear();
        ccaseM1755for.f3895for.m446d("UsbPermissionQueue stopped");
        connections$common_lib_release.f3915new.clear();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Object parcelableExtra;
        UsbDevice usbDevice;
        Logger logger = log;
        logger.invoke("Usb -> onStartCommand()");
        if (Build.VERSION.SDK_INT >= 33) {
            if (intent != null) {
                parcelableExtra = intent.getParcelableExtra("device", UsbDevice.class);
                usbDevice = (UsbDevice) parcelableExtra;
            }
            usbDevice = null;
        } else {
            if (intent != null) {
                parcelableExtra = intent.getParcelableExtra("device");
                usbDevice = (UsbDevice) parcelableExtra;
            }
            usbDevice = null;
        }
        if (usbDevice != null) {
            logger.invoke("Usb -> onStartCommand() -> connection.addUsbDevice()");
            Cnew connections$common_lib_release = getConnections$common_lib_release();
            connections$common_lib_release.getClass();
            Intrinsics.checkNotNullParameter(usbDevice, "usbDevice");
            connections$common_lib_release.f2496if.invoke("addUsbDevice(), " + usbDevice.getDeviceName() + ", number of devices before add " + connections$common_lib_release.f3915new.size());
            if (connections$common_lib_release.f3915new.isEmpty()) {
                connections$common_lib_release.m1756new();
            } else if (!connections$common_lib_release.f3915new.contains(usbDevice)) {
                connections$common_lib_release.f2496if.invoke("addUsbDevice(), " + usbDevice.getDeviceName());
                if (connections$common_lib_release.m1208if().hasPermission(usbDevice)) {
                    connections$common_lib_release.m1209if(usbDevice);
                } else {
                    connections$common_lib_release.m1755for().m1199do(usbDevice);
                }
            }
        }
        return super.onStartCommand(intent, flags, startId);
    }

    public final void reCheckAllUsbDevices() {
        Object next;
        Cnew connections$common_lib_release = getConnections$common_lib_release();
        Pair<List<UsbDevice>, List<UsbDevice>> pairM1206do = connections$common_lib_release.m1206do();
        List<UsbDevice> first = pairM1206do.getFirst();
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        for (Object obj : first) {
            UsbDevice usbDevice = (UsbDevice) obj;
            Iterator<T> it = connections$common_lib_release.f3915new.iterator();
            while (true) {
                if (it.hasNext()) {
                    next = it.next();
                    if (Intrinsics.areEqual(((UsbDevice) next).getDeviceName(), usbDevice.getDeviceName())) {
                        break;
                    }
                } else {
                    next = null;
                    break;
                }
            }
            if (next != null) {
                arrayList.add(obj);
            } else {
                arrayList2.add(obj);
            }
        }
        Iterator it2 = ((Iterable) new Pair(arrayList, arrayList2).getSecond()).iterator();
        while (it2.hasNext()) {
            connections$common_lib_release.m1209if((UsbDevice) it2.next());
        }
        Iterator<T> it3 = pairM1206do.getSecond().iterator();
        while (it3.hasNext()) {
            connections$common_lib_release.m1755for().m1199do((UsbDevice) it3.next());
        }
    }

    @Override
    public void onCreate() {
        log.invoke("Usb -> onCreate()");
        super.onCreate();
        Cnew connections$common_lib_release = getConnections$common_lib_release();
        Cfor listener = new Cfor();
        connections$common_lib_release.getClass();
        Intrinsics.checkNotNullParameter(listener, "listener");
        if (connections$common_lib_release.f3912case != null) {
            return;
        }
        connections$common_lib_release.f3912case = listener;
        connections$common_lib_release.f2496if.invoke("usb connection manager start");
        Ccase ccaseM1755for = connections$common_lib_release.m1755for();
        ccaseM1755for.getClass();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("USB_PERMISSION");
        Ccase.C2062do c2062do = ccaseM1755for.new C2062do();
        ccaseM1755for.f3896new = c2062do;
        int i = Build.VERSION.SDK_INT;
        if (i >= 34) {
            ccaseM1755for.f2483do.registerReceiver(c2062do, intentFilter, 2);
        } else if (i >= 33) {
            ccaseM1755for.f2483do.registerReceiver(c2062do, intentFilter, 4);
        } else {
            ccaseM1755for.f2483do.registerReceiver(c2062do, intentFilter);
        }
        ccaseM1755for.f3895for.m446d("UsbPermissionQueue started");
        Context context = connections$common_lib_release.f2495do;
        connections$common_lib_release.f2496if.invoke("register usb event receiver");
        IntentFilter intentFilter2 = new IntentFilter();
        intentFilter2.addAction("android.hardware.usb.action.USB_DEVICE_DETACHED");
        intentFilter2.addAction("USB_PERMISSION");
        if (Build.VERSION.SDK_INT >= 33) {
            context.registerReceiver(connections$common_lib_release.f3913else, intentFilter2, 4);
        } else {
            context.registerReceiver(connections$common_lib_release.f3913else, intentFilter2);
        }
        connections$common_lib_release.m1756new();
    }
}
