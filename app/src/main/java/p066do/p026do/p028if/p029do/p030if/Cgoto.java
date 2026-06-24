package p066do.p026do.p028if.p029do.p030if;

import android.hardware.usb.UsbDevice;
import android.net.nsd.NsdServiceInfo;
import androidx.lifecycle.Observer;
import com.p020hp.mobile.common.browsing.NsAttributeKey;
import com.p020hp.mobile.common.browsing.ServiceType;
import com.p020hp.mobile.common.usb.IppUsbSocketFactory;
import com.p020hp.mobile.common.usb.UsbBgService;
import com.p020hp.mobile.common.utils.Logger;
import com.p020hp.mobile.common.utils.LoggerKt;
import com.wugu.doublecamera.enums.UiPosIndexEnum;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import javax.net.SocketFactory;
import jp.p036co.dnp.photoprintlib.DNPPhotoPrint;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.JobKt__JobKt;
import kotlinx.coroutines.sync.Mutex;
import kotlinx.coroutines.sync.MutexKt;
import p066do.p026do.p028if.p029do.p068case.Celse;

public final class Cgoto implements CoroutineScope, InterfaceC2073a {

    public final List<C2080do> f3973break;

    public final List<String> f3974case;

    public final Mutex f3975else;

    public final Function2<NsdServiceInfo, Boolean, Unit> f3976for;

    public final Mutex f3977goto;

    public final CoroutineContext f2534if;

    public final Logger f3978new;

    public final Observer<UsbBgService.UsbDiscoveryEvent> f3979this;

    public UsbBgService.Connection f3980try;

    public static final class C2080do {

        public final UsbDevice f2535do;

        public final List<NsdServiceInfo> f3984for;

        public final Celse f2536if;

        public static final Cdo f3982new = new Cdo();

        public static final AtomicInteger f3983try = new AtomicInteger(2);

        public static final Map<String, Integer> f3981case = new LinkedHashMap();

        public static final class Cdo {
        }

        public C2080do(UsbDevice usbDevice, Celse usbPrinter, List<NsdServiceInfo> serviceInfos) {
            Intrinsics.checkNotNullParameter(usbDevice, "usbDevice");
            Intrinsics.checkNotNullParameter(usbPrinter, "usbPrinter");
            Intrinsics.checkNotNullParameter(serviceInfos, "serviceInfos");
            this.f2535do = usbDevice;
            this.f2536if = usbPrinter;
            this.f3984for = serviceInfos;
        }
    }

    @DebugMetadata(m1304c = "com.hp.mobile.common.browsing.ServiceDiscoveryUSB", m1305f = "ServiceDiscoveryUSB.kt", m1306i = {0}, m1307l = {268}, m1308m = "findServicesOn", m1309n = {"this"}, m1310s = {"L$0"})
    public static final class Cfor extends ContinuationImpl {

        public int f3985case;

        public Object f3986for;

        public Object f2537if;

        public Object f3987new;

        public Cfor(Continuation<? super Cfor> continuation) {
            super(continuation);
        }

        @Override
        public final Object invokeSuspend(Object obj) {
            this.f3987new = obj;
            this.f3985case |= Integer.MIN_VALUE;
            return Cgoto.m1241do(Cgoto.this, (List) null, this);
        }
    }

    @DebugMetadata(m1304c = "com.hp.mobile.common.browsing.ServiceDiscoveryUSB", m1305f = "ServiceDiscoveryUSB.kt", m1306i = {0, 0, 0, 0, 1, 1, 1}, m1307l = {UiPosIndexEnum.PAYMENT_KEYBOARD_POS, 293}, m1308m = "findServiceOn", m1309n = {"this", "serviceType", "usbBundles", "$this$withLock_u24default$iv", "this", "serviceType", "$this$withLock_u24default$iv"}, m1310s = {"L$0", "L$1", "L$2", "L$3", "L$0", "L$1", "L$2"})
    public static final class C2081if extends ContinuationImpl {

        public Object f3989case;

        public Object f3991for;

        public int f3992goto;

        public Object f2538if;

        public Object f3993new;

        public Object f3994try;

        public C2081if(Continuation<? super C2081if> continuation) {
            super(continuation);
        }

        @Override
        public final Object invokeSuspend(Object obj) {
            this.f3989case = obj;
            this.f3992goto |= Integer.MIN_VALUE;
            return Cgoto.m1240do(Cgoto.this, null, null, this);
        }
    }

    @DebugMetadata(m1304c = "com.hp.mobile.common.browsing.ServiceDiscoveryUSB$notify$2", m1305f = "ServiceDiscoveryUSB.kt", m1306i = {}, m1307l = {}, m1308m = "invokeSuspend", m1309n = {}, m1310s = {})
    public static final class Cnew extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {

        public final C2080do f3995for;

        public final boolean f3996new;

        public Cnew(C2080do c2080do, boolean z, Continuation<? super Cnew> continuation) {
            super(2, continuation);
            this.f3995for = c2080do;
            this.f3996new = z;
        }

        @Override
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return Cgoto.this.new Cnew(this.f3995for, this.f3996new, continuation);
        }

        @Override
        public Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return Cgoto.this.new Cnew(this.f3995for, this.f3996new, continuation).invokeSuspend(Unit.INSTANCE);
        }

        @Override
        public final Object invokeSuspend(Object obj) throws Throwable {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            ResultKt.throwOnFailure(obj);
            Cgoto.this.f3978new.m446d("notify() for " + this.f3995for.f2535do.getDeviceName());
            String serialNumber = this.f3996new ? this.f3995for.f2535do.getSerialNumber() : null;
            List<NsdServiceInfo> list = this.f3995for.f3984for;
            Cgoto cgoto = Cgoto.this;
            boolean z = this.f3996new;
            for (NsdServiceInfo nsdServiceInfo : list) {
                cgoto.f3978new.m446d("notify() " + C2076do.m1758new(nsdServiceInfo) + " isConnected = " + z);
                nsdServiceInfo.setAttribute(NsAttributeKey.SERIAL_NUMBER.getKey(), serialNumber);
                cgoto.f3976for.invoke(nsdServiceInfo, Boxing.boxBoolean(z));
            }
            return Unit.INSTANCE;
        }
    }

    @DebugMetadata(m1304c = "com.hp.mobile.common.browsing.ServiceDiscoveryUSB$observer$1$1", m1305f = "ServiceDiscoveryUSB.kt", m1306i = {0, 1, 2, 3}, m1307l = {UiPosIndexEnum.PAYMENT_KEYBOARD_POS, 114, DNPPhotoPrint.OVERCOAT_FINISH_PMATTE12, 131}, m1308m = "invokeSuspend", m1309n = {"$this$withLock_u24default$iv", "$this$withLock_u24default$iv", "$this$withLock_u24default$iv", "$this$withLock_u24default$iv"}, m1310s = {"L$0", "L$0", "L$0", "L$0"})
    public static final class Ctry extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {

        public final UsbBgService.UsbDiscoveryEvent f3998else;

        public Object f3999for;

        public Object f2540if;

        public Object f4000new;

        public int f4001try;

        public Ctry(UsbBgService.UsbDiscoveryEvent usbDiscoveryEvent, Continuation<? super Ctry> continuation) {
            super(2, continuation);
            this.f3998else = usbDiscoveryEvent;
        }

        @Override
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return Cgoto.this.new Ctry(this.f3998else, continuation);
        }

        @Override
        public Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return Cgoto.this.new Ctry(this.f3998else, continuation).invokeSuspend(Unit.INSTANCE);
        }

        @Override
        public final Object invokeSuspend(Object obj) throws Throwable {
            Cgoto cgoto;
            Mutex mutex;
            UsbBgService.UsbDiscoveryEvent event;
            Mutex mutex2;
            Object next;
            Cgoto cgoto2;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.f4001try;
            try {
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    cgoto = Cgoto.this;
                    Mutex mutex3 = cgoto.f3977goto;
                    UsbBgService.UsbDiscoveryEvent usbDiscoveryEvent = this.f3998else;
                    this.f2540if = mutex3;
                    this.f3999for = cgoto;
                    this.f4000new = usbDiscoveryEvent;
                    this.f4001try = 1;
                    if (mutex3.lock(null, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    mutex = mutex3;
                    event = usbDiscoveryEvent;
                } else {
                    if (i != 1) {
                        if (i != 2 && i != 3 && i != 4) {
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                        cgoto2 = (Cgoto) this.f3999for;
                        mutex2 = (Mutex) this.f2540if;
                        try {
                            ResultKt.throwOnFailure(obj);
                            cgoto2.f3978new.m446d("cache after USB connection event handling");
                            Cgoto.m1242do(cgoto2);
                            mutex2.unlock(null);
                            return Unit.INSTANCE;
                        } catch (Throwable th) {
                            th = th;
                            mutex2.unlock(null);
                            throw th;
                        }
                    }
                    event = (UsbBgService.UsbDiscoveryEvent) this.f4000new;
                    Cgoto cgoto3 = (Cgoto) this.f3999for;
                    mutex = (Mutex) this.f2540if;
                    ResultKt.throwOnFailure(obj);
                    cgoto = cgoto3;
                }
                cgoto.f3978new.m446d("cache before USB connection event handling");
                Cgoto.m1242do(cgoto);
                Intrinsics.checkNotNullExpressionValue(event, "event");
                Cgoto.m1243do(cgoto, event);
                if (cgoto.f3973break.isEmpty() && event.getIsConnected()) {
                    cgoto.f3978new.m446d("USB connection initial event");
                    List<UsbDevice> cache = event.getCache();
                    this.f2540if = mutex;
                    this.f3999for = cgoto;
                    this.f4000new = null;
                    this.f4001try = 2;
                    if (cgoto.m1248do(cache, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                } else {
                    UsbDevice device = event.getDevice();
                    if (device != null) {
                        if (event.getIsConnected()) {
                            cgoto.f3978new.m446d("USB connection event: connected");
                            C2080do c2080doM1245do = cgoto.m1245do(device);
                            if (c2080doM1245do != null) {
                                cgoto.f3973break.add(c2080doM1245do);
                                this.f2540if = mutex;
                                this.f3999for = cgoto;
                                this.f4000new = null;
                                this.f4001try = 3;
                                if (cgoto.m1247do(c2080doM1245do, true, (Continuation<? super Unit>) this) == coroutine_suspended) {
                                    return coroutine_suspended;
                                }
                            }
                        } else {
                            cgoto.f3978new.m446d("USB connection event: disconnected");
                            Iterator<T> it = cgoto.f3973break.iterator();
                            while (true) {
                                if (!it.hasNext()) {
                                    next = null;
                                    break;
                                }
                                next = it.next();
                                C2080do c2080do = (C2080do) next;
                                cgoto.f3978new.m446d("USB connection event: looking for " + device.getDeviceName() + " and have " + c2080do.f2535do.getDeviceName());
                                if (Intrinsics.areEqual(c2080do.f2535do.getDeviceName(), device.getDeviceName())) {
                                    break;
                                }
                            }
                            C2080do c2080do2 = (C2080do) next;
                            if (c2080do2 != null) {
                                cgoto.f3978new.m446d("USB connection event: " + c2080do2.f2535do.getDeviceName() + " removed (" + cgoto.f3973break.remove(c2080do2) + ") from cache");
                                this.f2540if = mutex;
                                this.f3999for = cgoto;
                                this.f4000new = null;
                                this.f4001try = 4;
                                if (cgoto.m1247do(c2080do2, false, (Continuation<? super Unit>) this) == coroutine_suspended) {
                                    return coroutine_suspended;
                                }
                            }
                        }
                    }
                }
                cgoto2 = cgoto;
                mutex2 = mutex;
                cgoto2.f3978new.m446d("cache after USB connection event handling");
                Cgoto.m1242do(cgoto2);
                mutex2.unlock(null);
                return Unit.INSTANCE;
            } catch (Throwable th2) {
                th = th2;
                mutex2 = mutex;
                mutex2.unlock(null);
                throw th;
            }
        }
    }

    public Cgoto(CoroutineContext scope, Function2<? super NsdServiceInfo, ? super Boolean, Unit> onAvailable) {
        Intrinsics.checkNotNullParameter(scope, "scope");
        Intrinsics.checkNotNullParameter(onAvailable, "onAvailable");
        this.f2534if = scope;
        this.f3976for = onAvailable;
        this.f3978new = LoggerKt.logger(LoggerKt.toTag("ServiceDiscoveryUSB"));
        this.f3974case = new ArrayList();
        this.f3975else = MutexKt.Mutex$default(false, 1, null);
        this.f3977goto = MutexKt.Mutex$default(false, 1, null);
        this.f3979this = new Observer() {
            @Override
            public final void onChanged(Object obj) {
                Cgoto.m1244if(this.f$0, (UsbBgService.UsbDiscoveryEvent) obj);
            }
        };
        this.f3973break = new ArrayList();
    }

    public static final Object m1240do(Cgoto cgoto, ServiceType serviceType, List list, Continuation continuation) {
        return cgoto.m1246do((ServiceType) null, (List<C2080do>) null, (Continuation<? super Unit>) continuation);
    }

    public static final Object m1241do(Cgoto cgoto, List list, Continuation continuation) {
        return cgoto.m1248do((List<? extends UsbDevice>) null, (Continuation<? super Unit>) continuation);
    }

    public static final void m1242do(Cgoto cgoto) {
        cgoto.f3978new.m446d("===============================================");
        cgoto.f3978new.m446d("USB services cache have " + cgoto.f3973break.size() + " bundles of ");
        Iterator<T> it = cgoto.f3973break.iterator();
        while (it.hasNext()) {
            cgoto.f3978new.m446d(((C2080do) it.next()).f2535do.getDeviceName());
        }
        cgoto.f3978new.m446d("===============================================");
    }

    public static final void m1244if(Cgoto this$0, UsbBgService.UsbDiscoveryEvent usbDiscoveryEvent) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        BuildersKt__Builders_commonKt.launch$default(this$0, Dispatchers.getIO(), null, this$0.new Ctry(usbDiscoveryEvent, null), 2, null);
    }

    public final Object m1247do(C2080do c2080do, boolean z, Continuation<? super Unit> continuation) throws Throwable {
        Object objWithContext = BuildersKt.withContext(Dispatchers.getMain(), new Cnew(c2080do, z, null), continuation);
        return objWithContext == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objWithContext : Unit.INSTANCE;
    }

    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object m1248do(java.util.List<? extends android.hardware.usb.UsbDevice> r6, kotlin.coroutines.Continuation<? super kotlin.Unit> r7) throws java.lang.Throwable {
        /*
            r5 = this;
            boolean r0 = r7 instanceof p066do.p026do.p028if.p029do.p030if.Cgoto.Cfor
            if (r0 == 0) goto L13
            r0 = r7
            do.do.if.do.if.goto$for r0 = (p066do.p026do.p028if.p029do.p030if.Cgoto.Cfor) r0
            int r1 = r0.f3985case
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.f3985case = r1
            goto L18
        L13:
            do.do.if.do.if.goto$for r0 = new do.do.if.do.if.goto$for
            r0.<init>(r7)
        L18:
            java.lang.Object r7 = r0.f3987new
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.f3985case
            r3 = 1
            if (r2 == 0) goto L39
            if (r2 != r3) goto L31
            java.lang.Object r6 = r0.f3986for
            java.util.Iterator r6 = (java.util.Iterator) r6
            java.lang.Object r2 = r0.f2537if
            do.do.if.do.if.goto r2 = (p066do.p026do.p028if.p029do.p030if.Cgoto) r2
            kotlin.ResultKt.throwOnFailure(r7)
            goto L41
        L31:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L39:
            kotlin.ResultKt.throwOnFailure(r7)
            java.util.Iterator r6 = r6.iterator()
            r2 = r5
        L41:
            boolean r7 = r6.hasNext()
            if (r7 == 0) goto L65
            java.lang.Object r7 = r6.next()
            android.hardware.usb.UsbDevice r7 = (android.hardware.usb.UsbDevice) r7
            do.do.if.do.if.goto$do r7 = r2.m1245do(r7)
            if (r7 == 0) goto L41
            java.util.List<do.do.if.do.if.goto$do> r4 = r2.f3973break
            r4.add(r7)
            r0.f2537if = r2
            r0.f3986for = r6
            r0.f3985case = r3
            java.lang.Object r7 = r2.m1247do(r7, r3, r0)
            if (r7 != r1) goto L41
            return r1
        L65:
            kotlin.Unit r6 = kotlin.Unit.INSTANCE
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: p066do.p026do.p028if.p029do.p030if.Cgoto.m1248do(java.util.List, kotlin.coroutines.Continuation):java.lang.Object");
    }

    @Override
    public SocketFactory mo1213do() {
        return new IppUsbSocketFactory(this.f3973break);
    }

    @Override
    public CoroutineContext getCoroutineContext() {
        return this.f2534if.plus(JobKt__JobKt.Job$default((Job) null, 1, (Object) null));
    }

    public static final void m1243do(Cgoto cgoto, UsbBgService.UsbDiscoveryEvent usbDiscoveryEvent) {
        cgoto.f3978new.m446d("===============================================");
        Logger logger = cgoto.f3978new;
        StringBuilder sb = new StringBuilder("USB connection event:\n");
        UsbDevice device = usbDiscoveryEvent.getDevice();
        StringBuilder sbAppend = sb.append(device != null ? device.getDeviceName() : null).append(" | ");
        UsbDevice device2 = usbDiscoveryEvent.getDevice();
        logger.m446d(sbAppend.append(device2 != null ? device2.getProductName() : null).append(" is ").append(usbDiscoveryEvent.getIsConnected() ? "connected" : "disconnected").toString());
        cgoto.f3978new.m446d("Cache:\nTotal " + usbDiscoveryEvent.getCache().size() + " device(s) connected\n");
        for (UsbDevice usbDevice : usbDiscoveryEvent.getCache()) {
            cgoto.f3978new.m446d(usbDevice.getDeviceName() + " | " + usbDevice.getProductName());
        }
        cgoto.f3978new.m446d("===============================================");
    }

    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object m1246do(com.p020hp.mobile.common.browsing.ServiceType r13, java.util.List<p066do.p026do.p028if.p029do.p030if.Cgoto.C2080do> r14, kotlin.coroutines.Continuation<? super kotlin.Unit> r15) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 353
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: p066do.p026do.p028if.p029do.p030if.Cgoto.m1246do(com.hp.mobile.common.browsing.ServiceType, java.util.List, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final p066do.p026do.p028if.p029do.p030if.Cgoto.C2080do m1245do(android.hardware.usb.UsbDevice r15) {
        /*
            Method dump skipped, instruction units count: 678
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: p066do.p026do.p028if.p029do.p030if.Cgoto.m1245do(android.hardware.usb.UsbDevice):do.do.if.do.if.goto$do");
    }

    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.String m1249do(p066do.p026do.p028if.p029do.p030if.Cgoto.C2080do r9) {
        /*
            Method dump skipped, instruction units count: 303
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: p066do.p026do.p028if.p029do.p030if.Cgoto.m1249do(do.do.if.do.if.goto$do):java.lang.String");
    }
}
