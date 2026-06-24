package com.p020hp.mobile.common.browsing;

import android.content.Context;
import android.net.nsd.NsdManager;
import android.net.nsd.NsdServiceInfo;
import androidx.core.app.NotificationCompat;
import androidx.lifecycle.LiveData;
import com.p020hp.mobile.common.CommonLibKt;
import com.p020hp.mobile.common.filter.DeviceFilter;
import com.p020hp.mobile.common.identity.DeviceIdentity;
import com.p020hp.mobile.common.usb.IppUsbSocketFactory;
import com.p020hp.mobile.common.usb.UsbBgService;
import com.p020hp.mobile.common.utils.ExpirationChecker;
import com.p020hp.mobile.common.utils.Logger;
import com.p020hp.mobile.common.utils.LoggerKt;
import com.proembed.service.Constant;
import com.tom_roush.fontbox.ttf.NamingTable;
import com.wugu.doublecamera.enums.UiPosIndexEnum;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CancellationException;
import javax.net.SocketFactory;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.JobKt__JobKt;
import kotlinx.coroutines.sync.Mutex;
import kotlinx.coroutines.sync.MutexKt;
import p066do.p026do.p028if.p029do.p030if.C2076do;
import p066do.p026do.p028if.p029do.p030if.C2082if;
import p066do.p026do.p028if.p029do.p030if.Celse;

@Metadata(m1292d1 = {"\u0000´\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010 \n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B!\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u0016\u00103\u001a\b\u0012\u0004\u0012\u00020\u000b042\u0006\u00105\u001a\u000206H\u0002J3\u00107\u001a\u0002082\u0006\u00109\u001a\u0002062#\u0010:\u001a\u001f\u0012\u0015\u0012\u0013\u0018\u00010<¢\u0006\f\b=\u0012\b\b>\u0012\u0004\b\b(?\u0012\u0004\u0012\u0002080;J\u001f\u0010@\u001a\b\u0012\u0004\u0012\u00020A042\u0006\u0010B\u001a\u00020AH\u0082@ø\u0001\u0000¢\u0006\u0002\u0010CJ\u0012\u0010D\u001a\u0004\u0018\u00010\u000b2\u0006\u0010E\u001a\u00020FH\u0002J\u0018\u0010G\u001a\u0002082\u0006\u0010B\u001a\u00020A2\u0006\u0010H\u001a\u00020IH\u0002J\u001b\u0010J\u001a\u0004\u0018\u00010<2\u0006\u0010B\u001a\u00020AH\u0083@ø\u0001\u0000¢\u0006\u0002\u0010CJ\u0019\u0010K\u001a\u0002082\u0006\u0010B\u001a\u00020AH\u0082@ø\u0001\u0000¢\u0006\u0002\u0010CJ\u0010\u0010L\u001a\u0002082\u0006\u0010M\u001a\u00020\u000bH\u0002J\u0006\u0010N\u001a\u000208J\u0006\u0010O\u001a\u000208J\u0010\u0010P\u001a\u0002082\u0006\u0010Q\u001a\u00020RH\u0016J\u001c\u0010P\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020<040S2\u0006\u0010E\u001a\u00020FH\u0016J\u0010\u0010T\u001a\u0002082\u0006\u0010Q\u001a\u00020RH\u0002J\u0010\u0010U\u001a\u0002082\u0006\u0010Q\u001a\u00020RH\u0016J\u0012\u0010U\u001a\u0002082\b\u0010E\u001a\u0004\u0018\u00010FH\u0016J\u0010\u0010V\u001a\u0002082\u0006\u0010M\u001a\u00020\u000bH\u0002J\u0006\u0010W\u001a\u00020XR\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u001b\u0010\u0010\u001a\u00020\u00118BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0014\u0010\u0015\u001a\u0004\b\u0012\u0010\u0013R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0017X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0019X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u0019X\u0082\u0004¢\u0006\u0002\n\u0000R\u001b\u0010\u001b\u001a\u00020\u001c8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u001f\u0010\u0015\u001a\u0004\b\u001d\u0010\u001eR\u001b\u0010 \u001a\u00020!8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b$\u0010\u0015\u001a\u0004\b\"\u0010#R\u001b\u0010%\u001a\u00020&8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b)\u0010\u0015\u001a\u0004\b'\u0010(R\u001b\u0010*\u001a\u00020+8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b.\u0010\u0015\u001a\u0004\b,\u0010-R\u001b\u0010/\u001a\u00020\u00198BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b2\u0010\u0015\u001a\u0004\b0\u00101\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006Y"}, m1293d2 = {"Lcom/hp/mobile/common/browsing/ServicesBrowser;", "Lcom/hp/mobile/common/browsing/Browser;", "context", "Landroid/content/Context;", "nsdManager", "Landroid/net/nsd/NsdManager;", "ledc", "Lcom/hp/mobile/common/utils/ExpirationChecker;", "(Landroid/content/Context;Landroid/net/nsd/NsdManager;Lcom/hp/mobile/common/utils/ExpirationChecker;)V", "adapterHolders", "", "Lcom/hp/mobile/common/browsing/ServiceAdapterHolder;", "browserScope", "Lkotlinx/coroutines/CoroutineScope;", "getContext", "()Landroid/content/Context;", "deviceFilter", "Lcom/hp/mobile/common/filter/DeviceFilter;", "getDeviceFilter", "()Lcom/hp/mobile/common/filter/DeviceFilter;", "deviceFilter$delegate", "Lkotlin/Lazy;", "log", "Lcom/hp/mobile/common/utils/Logger;", "mutex", "Lkotlinx/coroutines/sync/Mutex;", "resolverMutex", "serviceBundler", "Lcom/hp/mobile/common/browsing/ServiceBundler;", "getServiceBundler", "()Lcom/hp/mobile/common/browsing/ServiceBundler;", "serviceBundler$delegate", "serviceDiscoveryIP", "Lcom/hp/mobile/common/browsing/ServiceDiscoveryIP;", "getServiceDiscoveryIP", "()Lcom/hp/mobile/common/browsing/ServiceDiscoveryIP;", "serviceDiscoveryIP$delegate", "serviceDiscoveryNSD", "Lcom/hp/mobile/common/browsing/ServiceDiscoveryNSD;", "getServiceDiscoveryNSD", "()Lcom/hp/mobile/common/browsing/ServiceDiscoveryNSD;", "serviceDiscoveryNSD$delegate", "serviceDiscoveryUSB", "Lcom/hp/mobile/common/browsing/ServiceDiscoveryUSB;", "getServiceDiscoveryUSB", "()Lcom/hp/mobile/common/browsing/ServiceDiscoveryUSB;", "serviceDiscoveryUSB$delegate", "usbMutex", "getUsbMutex", "()Lkotlinx/coroutines/sync/Mutex;", "usbMutex$delegate", "adapterHoldersByServiceType", "", "serviceType", "", "addDeviceByIP", "", Constant.ETH_SET_IP, "callback", "Lkotlin/Function1;", "Lcom/hp/mobile/common/browsing/Device;", "Lkotlin/ParameterName;", NamingTable.TAG, "nsdServiceInfo", "checkIfCanReport", "Landroid/net/nsd/NsdServiceInfo;", "info", "(Landroid/net/nsd/NsdServiceInfo;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "find", "serviceGroup", "Lcom/hp/mobile/common/browsing/ServicesGroup;", "handleServiceInfo", "isFound", "", "notifyFound", "notifyLost", "notifyWithCachedServices", "holder", "reCheckAllUSBDevices", "refreshDevices", "start", "serviceAdapter", "Lcom/hp/mobile/common/browsing/ServiceAdapter;", "Landroidx/lifecycle/LiveData;", "startBrowsingFor", "stop", "stopBrowsingFor", "usbSocketFactory", "Ljavax/net/SocketFactory;", "common-lib_release"}, m1294k = 1, m1295mv = {1, 6, 0}, m1297xi = 48)
public final class ServicesBrowser implements Browser {
    public final List<C2082if> adapterHolders;
    public final CoroutineScope browserScope;
    public final Context context;

    public final Lazy deviceFilter;
    public final ExpirationChecker ledc;
    public final Logger log;
    public final Mutex mutex;
    public final NsdManager nsdManager;
    public final Mutex resolverMutex;

    public final Lazy serviceBundler;

    public final Lazy serviceDiscoveryIP;

    public final Lazy serviceDiscoveryNSD;

    public final Lazy serviceDiscoveryUSB;

    public final Lazy usbMutex;

    public static final class C1648a extends Lambda implements Function0<Mutex> {

        public static final C1648a f741if = new C1648a();

        public C1648a() {
            super(0);
        }

        @Override
        public Mutex invoke() {
            return MutexKt.Mutex$default(false, 1, null);
        }
    }

    public static final class Cbreak extends Lambda implements Function0<p066do.p026do.p028if.p029do.p030if.Ccase> {
        public Cbreak() {
            super(0);
        }

        @Override
        public p066do.p026do.p028if.p029do.p030if.Ccase invoke() {
            return new p066do.p026do.p028if.p029do.p030if.Ccase(CommonLibKt.CommonLib().getContext(), ServicesBrowser.this.browserScope.getCoroutineContext(), new p066do.p026do.p028if.p029do.p030if.Cclass(ServicesBrowser.this));
        }
    }

    @DebugMetadata(m1304c = "com.hp.mobile.common.browsing.ServicesBrowser", m1305f = "ServicesBrowser.kt", m1306i = {0, 0, 1, 1, 1}, m1307l = {287, 290}, m1308m = "notifyFound", m1309n = {"this", "result", "this", "result", "nsdServiceInfo"}, m1310s = {"L$0", "L$1", "L$0", "L$1", "L$3"})
    public static final class Ccase extends ContinuationImpl {

        public int f3809break;

        public Object f3810case;

        public Object f3811else;

        public Object f3812for;

        public Object f3813goto;

        public Object f743if;

        public Object f3814new;

        public Object f3816try;

        public Ccase(Continuation<? super Ccase> continuation) {
            super(continuation);
        }

        @Override
        public final Object invokeSuspend(Object obj) {
            this.f3813goto = obj;
            this.f3809break |= Integer.MIN_VALUE;
            return ServicesBrowser.this.notifyFound(null, this);
        }
    }

    public static final class Ccatch extends Lambda implements Function0<p066do.p026do.p028if.p029do.p030if.Celse> {
        public Ccatch() {
            super(0);
        }

        @Override
        public p066do.p026do.p028if.p029do.p030if.Celse invoke() {
            return new p066do.p026do.p028if.p029do.p030if.Celse(CommonLibKt.CommonLib().getContext(), ServicesBrowser.this.browserScope.getCoroutineContext(), ServicesBrowser.this.resolverMutex, ServicesBrowser.this.nsdManager, new p066do.p026do.p028if.p029do.p030if.Cconst(ServicesBrowser.this));
        }
    }

    public static final class Cclass extends Lambda implements Function0<p066do.p026do.p028if.p029do.p030if.Cgoto> {
        public Cclass() {
            super(0);
        }

        @Override
        public p066do.p026do.p028if.p029do.p030if.Cgoto invoke() {
            return new p066do.p026do.p028if.p029do.p030if.Cgoto(ServicesBrowser.this.browserScope.getCoroutineContext(), new p066do.p026do.p028if.p029do.p030if.Cfinal(ServicesBrowser.this));
        }
    }

    @DebugMetadata(m1304c = "com.hp.mobile.common.browsing.ServicesBrowser$start$1", m1305f = "ServicesBrowser.kt", m1306i = {}, m1307l = {109}, m1308m = "invokeSuspend", m1309n = {}, m1310s = {})
    public static final class Cconst extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {

        public final ServiceAdapter f3817for;

        public int f746if;

        public Cconst(ServiceAdapter serviceAdapter, Continuation<? super Cconst> continuation) {
            super(2, continuation);
            this.f3817for = serviceAdapter;
        }

        @Override
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new Cconst(this.f3817for, continuation);
        }

        @Override
        public Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return new Cconst(this.f3817for, continuation).invokeSuspend(Unit.INSTANCE);
        }

        @Override
        public final Object invokeSuspend(Object obj) throws Throwable {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.f746if;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                ServiceAdapter serviceAdapter = this.f3817for;
                this.f746if = 1;
                if (serviceAdapter.notifyClear$common_lib_release(this) == coroutine_suspended) {
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

    @DebugMetadata(m1304c = "com.hp.mobile.common.browsing.ServicesBrowser$addDeviceByIP$1$1", m1305f = "ServicesBrowser.kt", m1306i = {1, 1, 2, 2, 2}, m1307l = {357, 399, 362}, m1308m = "invokeSuspend", m1309n = {"services", "$this$withLock_u24default$iv", "$this$withLock_u24default$iv", "device", NotificationCompat.CATEGORY_SERVICE}, m1310s = {"L$4", "L$5", "L$4", "L$5", "L$7"})
    public static final class C1649do extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {

        public int f3818break;

        public Object f3819case;

        public final InetAddress f3821class;

        public final String f3822const;

        public Object f3823else;

        public final Function1<Device, Unit> f3824final;

        public Object f3825for;

        public Object f3826goto;

        public Object f747if;

        public Object f3827new;

        public Object f3828this;

        public Object f3829try;

        public C1649do(InetAddress inetAddress, String str, Function1<? super Device, Unit> function1, Continuation<? super C1649do> continuation) {
            super(2, continuation);
            this.f3821class = inetAddress;
            this.f3822const = str;
            this.f3824final = function1;
        }

        @Override
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return ServicesBrowser.this.new C1649do(this.f3821class, this.f3822const, this.f3824final, continuation);
        }

        @Override
        public Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ServicesBrowser.this.new C1649do(this.f3821class, this.f3822const, this.f3824final, continuation).invokeSuspend(Unit.INSTANCE);
        }

        @Override
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r18) throws java.lang.Throwable {
            /*
                Method dump skipped, instruction units count: 436
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.p020hp.mobile.common.browsing.ServicesBrowser.C1649do.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    @DebugMetadata(m1304c = "com.hp.mobile.common.browsing.ServicesBrowser$notifyLost$2", m1305f = "ServicesBrowser.kt", m1306i = {}, m1307l = {UiPosIndexEnum.PAYMENT_XIE_CHENG_IC}, m1308m = "invokeSuspend", m1309n = {}, m1310s = {})
    public static final class Celse extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {

        public final NsdServiceInfo f3830case;

        public final ServicesBrowser f3831else;

        public Object f3832for;

        public Object f748if;

        public Object f3833new;

        public int f3834try;

        public Celse(NsdServiceInfo nsdServiceInfo, ServicesBrowser servicesBrowser, Continuation<? super Celse> continuation) {
            super(2, continuation);
            this.f3830case = nsdServiceInfo;
            this.f3831else = servicesBrowser;
        }

        @Override
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new Celse(this.f3830case, this.f3831else, continuation);
        }

        @Override
        public Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return new Celse(this.f3830case, this.f3831else, continuation).invokeSuspend(Unit.INSTANCE);
        }

        @Override
        public final Object invokeSuspend(Object obj) throws Throwable {
            String type;
            ServicesBrowser servicesBrowser;
            NsdServiceInfo nsdServiceInfo;
            Iterator it;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.f3834try;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                String serviceType = this.f3830case.getServiceType();
                Intrinsics.checkNotNullExpressionValue(serviceType, "info.serviceType");
                ServiceType serviceTypeM1220do = C2076do.m1220do(serviceType);
                if (serviceTypeM1220do == null || (type = serviceTypeM1220do.getType()) == null) {
                    return null;
                }
                servicesBrowser = this.f3831else;
                nsdServiceInfo = this.f3830case;
                it = servicesBrowser.adapterHoldersByServiceType(type).iterator();
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                it = (Iterator) this.f3833new;
                nsdServiceInfo = (NsdServiceInfo) this.f3832for;
                servicesBrowser = (ServicesBrowser) this.f748if;
                ResultKt.throwOnFailure(obj);
            }
            while (it.hasNext()) {
                C2082if c2082if = (C2082if) it.next();
                servicesBrowser.log.m446d("notifyLost() - " + C2076do.m1758new(nsdServiceInfo));
                this.f748if = servicesBrowser;
                this.f3832for = nsdServiceInfo;
                this.f3833new = it;
                this.f3834try = 1;
                if (c2082if.m1254if(nsdServiceInfo, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            }
            return Unit.INSTANCE;
        }
    }

    public static final class Cfinal extends ServiceAdapter {

        public final ServicesGroup f749do;

        public Cfinal(ServicesGroup servicesGroup) {
            this.f749do = servicesGroup;
        }

        @Override
        public ServicesGroup serviceGroup() {
            return this.f749do;
        }
    }

    @DebugMetadata(m1304c = "com.hp.mobile.common.browsing.ServicesBrowser$checkIfCanReport$discovered$1", m1305f = "ServicesBrowser.kt", m1306i = {}, m1307l = {305}, m1308m = "invokeSuspend", m1309n = {}, m1310s = {})
    public static final class Cfor extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Pair<? extends DeviceIdentity, ? extends List<? extends NsdServiceInfo>>>, Object> {

        public int f750if;

        public final NsdServiceInfo f3836new;

        public Cfor(NsdServiceInfo nsdServiceInfo, Continuation<? super Cfor> continuation) {
            super(2, continuation);
            this.f3836new = nsdServiceInfo;
        }

        @Override
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return ServicesBrowser.this.new Cfor(this.f3836new, continuation);
        }

        @Override
        public Object invoke(CoroutineScope coroutineScope, Continuation<? super Pair<? extends DeviceIdentity, ? extends List<? extends NsdServiceInfo>>> continuation) {
            return ServicesBrowser.this.new Cfor(this.f3836new, continuation).invokeSuspend(Unit.INSTANCE);
        }

        @Override
        public final Object invokeSuspend(Object obj) throws Throwable {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.f750if;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                p066do.p026do.p028if.p029do.p030if.Cnew serviceBundler = ServicesBrowser.this.getServiceBundler();
                NsdServiceInfo nsdServiceInfo = this.f3836new;
                this.f750if = 1;
                obj = serviceBundler.m1255do(nsdServiceInfo, this);
                if (obj == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            return obj;
        }
    }

    @DebugMetadata(m1304c = "com.hp.mobile.common.browsing.ServicesBrowser$notifyWithCachedServices$1", m1305f = "ServicesBrowser.kt", m1306i = {0, 1}, m1307l = {399, 94}, m1308m = "invokeSuspend", m1309n = {"$this$withLock_u24default$iv", "$this$withLock_u24default$iv"}, m1310s = {"L$0", "L$0"})
    public static final class Cgoto extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {

        public final C2082if f3838else;

        public Object f3839for;

        public Object f751if;

        public Object f3840new;

        public int f3841try;

        public Cgoto(C2082if c2082if, Continuation<? super Cgoto> continuation) {
            super(2, continuation);
            this.f3838else = c2082if;
        }

        @Override
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return ServicesBrowser.this.new Cgoto(this.f3838else, continuation);
        }

        @Override
        public Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ServicesBrowser.this.new Cgoto(this.f3838else, continuation).invokeSuspend(Unit.INSTANCE);
        }

        @Override
        public final Object invokeSuspend(Object obj) throws Throwable {
            Mutex mutex;
            ServicesBrowser servicesBrowser;
            C2082if c2082if;
            Mutex mutex2;
            Throwable th;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.f3841try;
            try {
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    mutex = ServicesBrowser.this.mutex;
                    ServicesBrowser servicesBrowser2 = ServicesBrowser.this;
                    C2082if c2082if2 = this.f3838else;
                    this.f751if = mutex;
                    this.f3839for = servicesBrowser2;
                    this.f3840new = c2082if2;
                    this.f3841try = 1;
                    if (mutex.lock(null, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    servicesBrowser = servicesBrowser2;
                    c2082if = c2082if2;
                } else {
                    if (i != 1) {
                        if (i != 2) {
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                        mutex2 = (Mutex) this.f751if;
                        try {
                            ResultKt.throwOnFailure(obj);
                            mutex2.unlock(null);
                            return Unit.INSTANCE;
                        } catch (Throwable th2) {
                            th = th2;
                            mutex2.unlock(null);
                            throw th;
                        }
                    }
                    c2082if = (C2082if) this.f3840new;
                    servicesBrowser = (ServicesBrowser) this.f3839for;
                    Mutex mutex3 = (Mutex) this.f751if;
                    ResultKt.throwOnFailure(obj);
                    mutex = mutex3;
                }
                ArrayList arrayList = new ArrayList();
                Iterator it = servicesBrowser.adapterHolders.iterator();
                while (it.hasNext()) {
                    List<ServiceInfo> listM1253do = ((C2082if) it.next()).m1253do(c2082if.m1252do());
                    HashSet hashSet = new HashSet();
                    ArrayList arrayList2 = new ArrayList();
                    for (Object obj2 : listM1253do) {
                        if (hashSet.add(ServiceInfoKt.identifier((ServiceInfo) obj2))) {
                            arrayList2.add(obj2);
                        }
                    }
                    arrayList.addAll(arrayList2);
                }
                servicesBrowser.log.m446d("notifyWithCachedServices() " + arrayList);
                this.f751if = mutex;
                this.f3839for = null;
                this.f3840new = null;
                this.f3841try = 2;
                if (c2082if.m1251do(arrayList, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                mutex2 = mutex;
                mutex2.unlock(null);
                return Unit.INSTANCE;
            } catch (Throwable th3) {
                mutex2 = mutex;
                th = th3;
                mutex2.unlock(null);
                throw th;
            }
        }
    }

    @DebugMetadata(m1304c = "com.hp.mobile.common.browsing.ServicesBrowser", m1305f = "ServicesBrowser.kt", m1306i = {0}, m1307l = {304}, m1308m = "checkIfCanReport", m1309n = {"this"}, m1310s = {"L$0"})
    public static final class C1650if extends ContinuationImpl {

        public Object f3842for;

        public Object f752if;

        public int f3844try;

        public C1650if(Continuation<? super C1650if> continuation) {
            super(continuation);
        }

        @Override
        public final Object invokeSuspend(Object obj) {
            this.f3842for = obj;
            this.f3844try |= Integer.MIN_VALUE;
            return ServicesBrowser.this.checkIfCanReport(null, this);
        }
    }

    public static final class Cnew extends Lambda implements Function0<DeviceFilter> {

        public static final Cnew f753if = new Cnew();

        public Cnew() {
            super(0);
        }

        @Override
        public DeviceFilter invoke() {
            return new DeviceFilter(null, 0 == true ? 1 : 0, 3, 0 == true ? 1 : 0);
        }
    }

    public static final class Cthis extends Lambda implements Function0<p066do.p026do.p028if.p029do.p030if.Cnew> {
        public Cthis() {
            super(0);
        }

        @Override
        public p066do.p026do.p028if.p029do.p030if.Cnew invoke() {
            return new p066do.p026do.p028if.p029do.p030if.Cnew(ServicesBrowser.this.getServiceDiscoveryUSB());
        }
    }

    @DebugMetadata(m1304c = "com.hp.mobile.common.browsing.ServicesBrowser$handleServiceInfo$1$1", m1305f = "ServicesBrowser.kt", m1306i = {0, 1, 2}, m1307l = {399, 268, 270}, m1308m = "invokeSuspend", m1309n = {"$this$withLock_u24default$iv", "$this$withLock_u24default$iv", "$this$withLock_u24default$iv"}, m1310s = {"L$0", "L$0", "L$0"})
    public static final class Ctry extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {

        public int f3845case;

        public final NsdServiceInfo f3846else;

        public Object f3847for;

        public final ServicesBrowser f3848goto;

        public Object f755if;

        public Object f3849new;

        public final boolean f3850this;

        public boolean f3851try;

        public Ctry(NsdServiceInfo nsdServiceInfo, ServicesBrowser servicesBrowser, boolean z, Continuation<? super Ctry> continuation) {
            super(2, continuation);
            this.f3846else = nsdServiceInfo;
            this.f3848goto = servicesBrowser;
            this.f3850this = z;
        }

        @Override
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new Ctry(this.f3846else, this.f3848goto, this.f3850this, continuation);
        }

        @Override
        public Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return new Ctry(this.f3846else, this.f3848goto, this.f3850this, continuation).invokeSuspend(Unit.INSTANCE);
        }

        @Override
        public final Object invokeSuspend(Object obj) throws Throwable {
            Mutex usbMutex;
            boolean z;
            ServicesBrowser servicesBrowser;
            NsdServiceInfo nsdServiceInfo;
            Mutex mutex;
            Throwable th;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.f3845case;
            try {
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    usbMutex = C2076do.m1219do(this.f3846else) == ConnectionType.USB ? this.f3848goto.getUsbMutex() : this.f3848goto.mutex;
                    z = this.f3850this;
                    servicesBrowser = this.f3848goto;
                    NsdServiceInfo nsdServiceInfo2 = this.f3846else;
                    this.f755if = usbMutex;
                    this.f3847for = servicesBrowser;
                    this.f3849new = nsdServiceInfo2;
                    this.f3851try = z;
                    this.f3845case = 1;
                    if (usbMutex.lock(null, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    nsdServiceInfo = nsdServiceInfo2;
                } else {
                    if (i != 1) {
                        if (i != 2 && i != 3) {
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                        mutex = (Mutex) this.f755if;
                        try {
                            ResultKt.throwOnFailure(obj);
                            mutex.unlock(null);
                            return Unit.INSTANCE;
                        } catch (Throwable th2) {
                            th = th2;
                            mutex.unlock(null);
                            throw th;
                        }
                    }
                    z = this.f3851try;
                    nsdServiceInfo = (NsdServiceInfo) this.f3849new;
                    servicesBrowser = (ServicesBrowser) this.f3847for;
                    Mutex mutex2 = (Mutex) this.f755if;
                    ResultKt.throwOnFailure(obj);
                    usbMutex = mutex2;
                }
                if (z) {
                    this.f755if = usbMutex;
                    this.f3847for = null;
                    this.f3849new = null;
                    this.f3845case = 2;
                    if (servicesBrowser.notifyFound(nsdServiceInfo, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                } else {
                    this.f755if = usbMutex;
                    this.f3847for = null;
                    this.f3849new = null;
                    this.f3845case = 3;
                    if (servicesBrowser.notifyLost(nsdServiceInfo, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                }
                mutex = usbMutex;
                mutex.unlock(null);
                return Unit.INSTANCE;
            } catch (Throwable th3) {
                mutex = usbMutex;
                th = th3;
                mutex.unlock(null);
                throw th;
            }
        }
    }

    public ServicesBrowser(Context context, NsdManager nsdManager, ExpirationChecker ledc) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(nsdManager, "nsdManager");
        Intrinsics.checkNotNullParameter(ledc, "ledc");
        this.context = context;
        this.nsdManager = nsdManager;
        this.ledc = ledc;
        this.log = LoggerKt.logger(LoggerKt.toTag("ServicesBrowser"));
        this.browserScope = CoroutineScopeKt.CoroutineScope(JobKt__JobKt.Job$default((Job) null, 1, (Object) null).plus(Dispatchers.getIO()));
        this.resolverMutex = MutexKt.Mutex$default(false, 1, null);
        this.adapterHolders = new ArrayList();
        this.serviceBundler = LazyKt.lazy(new Cthis());
        this.deviceFilter = LazyKt.lazy(Cnew.f753if);
        this.serviceDiscoveryNSD = LazyKt.lazy(new Ccatch());
        this.serviceDiscoveryIP = LazyKt.lazy(new Cbreak());
        this.serviceDiscoveryUSB = LazyKt.lazy(new Cclass());
        this.mutex = MutexKt.Mutex$default(false, 1, null);
        this.usbMutex = LazyKt.lazy(C1648a.f741if);
    }

    public ServicesBrowser(Context context, NsdManager nsdManager, ExpirationChecker expirationChecker, int i, DefaultConstructorMarker defaultConstructorMarker) {
        if ((i & 2) != 0) {
            Object systemService = context.getSystemService("servicediscovery");
            if (systemService == null) {
                throw new NullPointerException("null cannot be cast to non-null type android.net.nsd.NsdManager");
            }
            nsdManager = (NsdManager) systemService;
        }
        this(context, nsdManager, (i & 4) != 0 ? CommonLibKt.CommonLib().getExpChecker() : expirationChecker);
    }

    public final synchronized List<C2082if> adapterHoldersByServiceType(String serviceType) {
        ArrayList arrayList;
        List<C2082if> list = this.adapterHolders;
        arrayList = new ArrayList();
        for (Object obj : list) {
            if (((C2082if) obj).m1252do().contains(serviceType)) {
                arrayList.add(obj);
            }
        }
        return arrayList;
    }

    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object checkIfCanReport(android.net.nsd.NsdServiceInfo r6, kotlin.coroutines.Continuation<? super java.util.List<android.net.nsd.NsdServiceInfo>> r7) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 223
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.p020hp.mobile.common.browsing.ServicesBrowser.checkIfCanReport(android.net.nsd.NsdServiceInfo, kotlin.coroutines.Continuation):java.lang.Object");
    }

    private final C2082if find(ServicesGroup servicesGroup) {
        Object next;
        Iterator<T> it = this.adapterHolders.iterator();
        while (true) {
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
            if (((C2082if) next).f2541do.serviceGroup() == servicesGroup) {
                break;
            }
        }
        return (C2082if) next;
    }

    private final DeviceFilter getDeviceFilter() {
        return (DeviceFilter) this.deviceFilter.getValue();
    }

    public final p066do.p026do.p028if.p029do.p030if.Cnew getServiceBundler() {
        return (p066do.p026do.p028if.p029do.p030if.Cnew) this.serviceBundler.getValue();
    }

    public final p066do.p026do.p028if.p029do.p030if.Ccase getServiceDiscoveryIP() {
        return (p066do.p026do.p028if.p029do.p030if.Ccase) this.serviceDiscoveryIP.getValue();
    }

    private final p066do.p026do.p028if.p029do.p030if.Celse getServiceDiscoveryNSD() {
        return (p066do.p026do.p028if.p029do.p030if.Celse) this.serviceDiscoveryNSD.getValue();
    }

    public final p066do.p026do.p028if.p029do.p030if.Cgoto getServiceDiscoveryUSB() {
        return (p066do.p026do.p028if.p029do.p030if.Cgoto) this.serviceDiscoveryUSB.getValue();
    }

    public final Mutex getUsbMutex() {
        return (Mutex) this.usbMutex.getValue();
    }

    public final void handleServiceInfo(NsdServiceInfo info, boolean isFound) {
        String serviceType = info.getServiceType();
        Intrinsics.checkNotNullExpressionValue(serviceType, "info.serviceType");
        if (C2076do.m1220do(serviceType) != null) {
            this.log.m446d("handleServiceInfo() - handle " + C2076do.m1758new(info) + '.');
            if (BuildersKt__Builders_commonKt.launch$default(this.browserScope, Dispatchers.getMain(), null, new Ctry(info, this, isFound, null), 2, null) != null) {
                return;
            }
        }
        this.log.m447e("handleServiceInfo() Failed to read serviceType.");
    }

    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object notifyFound(android.net.nsd.NsdServiceInfo r10, kotlin.coroutines.Continuation<? super com.p020hp.mobile.common.browsing.Device> r11) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 216
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.p020hp.mobile.common.browsing.ServicesBrowser.notifyFound(android.net.nsd.NsdServiceInfo, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public final Object notifyLost(NsdServiceInfo nsdServiceInfo, Continuation<? super Unit> continuation) {
        return getServiceBundler().m1256do(nsdServiceInfo) ? BuildersKt.withContext(Dispatchers.getMain(), new Celse(nsdServiceInfo, this, null), continuation) : Unit.INSTANCE;
    }

    private final void notifyWithCachedServices(C2082if c2082if) {
        this.log.m446d("notifyWithCachedServices() for " + c2082if);
        BuildersKt__Builders_commonKt.launch$default(this.browserScope, Dispatchers.getMain(), null, new Cgoto(c2082if, null), 2, null);
    }

    private final void startBrowsingFor(ServiceAdapter serviceAdapter) {
        Object objM1772constructorimpl;
        C2082if c2082if = new C2082if(serviceAdapter);
        StringBuilder sb = new StringBuilder("startBrowsingFor() [ ");
        for (String str : serviceAdapter.serviceGroup().getServiceTypes()) {
            sb.append(str + ' ');
        }
        this.log.m446d(sb.append("]").toString());
        notifyWithCachedServices(c2082if);
        this.adapterHolders.add(c2082if);
        p066do.p026do.p028if.p029do.p030if.Celse serviceDiscoveryNSD = getServiceDiscoveryNSD();
        List<String> serviceTypes = c2082if.m1252do();
        synchronized (serviceDiscoveryNSD) {
            Intrinsics.checkNotNullParameter(serviceTypes, "serviceTypes");
            serviceDiscoveryNSD.f3940else.clear();
            Iterator<T> it = serviceTypes.iterator();
            while (true) {
                Object obj = null;
                if (!it.hasNext()) {
                    break;
                }
                String serviceType = (String) it.next();
                Intrinsics.checkNotNullParameter(serviceType, "serviceType");
                Iterator<T> it2 = serviceDiscoveryNSD.f3942goto.iterator();
                while (true) {
                    if (!it2.hasNext()) {
                        break;
                    }
                    Object next = it2.next();
                    if (Intrinsics.areEqual(((Celse.C2077do) next).f2520do, serviceType)) {
                        obj = next;
                        break;
                    }
                }
                if (!(obj != null)) {
                    Celse.C2077do c2077do = new Celse.C2077do(serviceDiscoveryNSD, serviceType);
                    serviceDiscoveryNSD.f3942goto.add(c2077do);
                    if (!c2077do.f2521if) {
                        c2077do.f2521if = true;
                        c2077do.f3945for.f3939case.m446d("DISCO-FLOW Discoverer.start() " + c2077do.f2520do);
                        try {
                            c2077do.f3945for.f3943new.discoverServices(c2077do.f2520do, 1, c2077do);
                            objM1772constructorimpl = Result.m1772constructorimpl(Unit.INSTANCE);
                        } catch (Throwable th) {
                            objM1772constructorimpl = Result.m1772constructorimpl(ResultKt.createFailure(th));
                        }
                        p066do.p026do.p028if.p029do.p030if.Celse celse = c2077do.f3945for;
                        Throwable thM1775exceptionOrNullimpl = Result.m1775exceptionOrNullimpl(objM1772constructorimpl);
                        if (thM1775exceptionOrNullimpl != null) {
                            celse.f3939case.m446d("DISCO-FLOW Discoverer.start() fail - " + thM1775exceptionOrNullimpl.getLocalizedMessage());
                        }
                    }
                }
            }
        }
        p066do.p026do.p028if.p029do.p030if.Cgoto serviceDiscoveryUSB = getServiceDiscoveryUSB();
        List<String> serviceTypes2 = c2082if.m1252do();
        synchronized (serviceDiscoveryUSB) {
            Intrinsics.checkNotNullParameter(serviceTypes2, "serviceTypes");
            serviceDiscoveryUSB.f3973break.clear();
            for (String serviceType2 : serviceTypes2) {
                Intrinsics.checkNotNullParameter(serviceType2, "serviceType");
                if (serviceDiscoveryUSB.f3974case.contains(serviceType2)) {
                    serviceDiscoveryUSB.f3978new.m446d("start() already started for serviceType " + serviceTypes2);
                } else {
                    ServiceType serviceTypeM1220do = C2076do.m1220do(serviceType2);
                    if (serviceTypeM1220do != null) {
                        serviceDiscoveryUSB.f3978new.m446d("start() discovery for " + serviceTypeM1220do.getType());
                        serviceDiscoveryUSB.f3974case.add(serviceTypeM1220do.getType());
                        BuildersKt__Builders_commonKt.launch$default(serviceDiscoveryUSB, null, null, new p066do.p026do.p028if.p029do.p030if.Cthis(serviceDiscoveryUSB, serviceTypeM1220do, null), 3, null);
                    }
                }
            }
            if (!serviceDiscoveryUSB.f3974case.isEmpty()) {
                BuildersKt__Builders_commonKt.launch$default(serviceDiscoveryUSB, null, null, new p066do.p026do.p028if.p029do.p030if.Cbreak(serviceDiscoveryUSB, null), 3, null);
            }
        }
    }

    private final void stopBrowsingFor(C2082if c2082if) {
        Object next;
        Object objM1772constructorimpl;
        List<String> serviceTypes = c2082if.m1252do();
        this.log.m446d("stopBrowsingFor() serviceAdapter " + c2082if + ' ' + serviceTypes);
        if (!serviceTypes.isEmpty()) {
            p066do.p026do.p028if.p029do.p030if.Celse serviceDiscoveryNSD = getServiceDiscoveryNSD();
            synchronized (serviceDiscoveryNSD) {
                Intrinsics.checkNotNullParameter(serviceTypes, "serviceTypes");
                for (String str : serviceTypes) {
                    Iterator<T> it = serviceDiscoveryNSD.f3942goto.iterator();
                    while (true) {
                        if (it.hasNext()) {
                            next = it.next();
                            if (Intrinsics.areEqual(((Celse.C2077do) next).f2520do, str)) {
                                break;
                            }
                        } else {
                            next = null;
                            break;
                        }
                    }
                    Celse.C2077do c2077do = (Celse.C2077do) next;
                    if (c2077do != null) {
                        if (c2077do.f2521if) {
                            c2077do.f2521if = false;
                            p066do.p026do.p028if.p029do.p030if.Celse celse = c2077do.f3945for;
                            try {
                                JobKt__JobKt.cancelChildren$default(celse.getCoroutineContext(), (CancellationException) null, 1, (Object) null);
                                celse.f3939case.m446d("DISCO-FLOW Discoverer.stop() - " + c2077do.f2520do);
                                celse.f3943new.stopServiceDiscovery(c2077do);
                                objM1772constructorimpl = Result.m1772constructorimpl(Unit.INSTANCE);
                            } catch (Throwable th) {
                                objM1772constructorimpl = Result.m1772constructorimpl(ResultKt.createFailure(th));
                            }
                            p066do.p026do.p028if.p029do.p030if.Celse celse2 = c2077do.f3945for;
                            Throwable thM1775exceptionOrNullimpl = Result.m1775exceptionOrNullimpl(objM1772constructorimpl);
                            if (thM1775exceptionOrNullimpl != null) {
                                celse2.f3939case.m446d("DISCO-FLOW Discoverer.stop() fail - " + thM1775exceptionOrNullimpl.getLocalizedMessage());
                            }
                        }
                        serviceDiscoveryNSD.f3942goto.remove(c2077do);
                    }
                }
            }
            p066do.p026do.p028if.p029do.p030if.Cgoto serviceDiscoveryUSB = getServiceDiscoveryUSB();
            synchronized (serviceDiscoveryUSB) {
                Intrinsics.checkNotNullParameter(serviceTypes, "serviceTypes");
                for (String str2 : serviceTypes) {
                    serviceDiscoveryUSB.f3978new.m446d("stop() for " + str2);
                    serviceDiscoveryUSB.f3974case.remove(str2);
                }
                serviceDiscoveryUSB.f3978new.m446d("stop() remaining services to discover " + serviceDiscoveryUSB.f3974case.size());
                if (serviceDiscoveryUSB.f3974case.isEmpty()) {
                    UsbBgService.Connection connection = serviceDiscoveryUSB.f3980try;
                    if (connection != null) {
                        BuildersKt__Builders_commonKt.launch$default(serviceDiscoveryUSB, null, null, new p066do.p026do.p028if.p029do.p030if.Ccatch(connection, serviceDiscoveryUSB, null), 3, null);
                    }
                    serviceDiscoveryUSB.f3980try = null;
                }
            }
        }
    }

    public final void addDeviceByIP(String ip, Function1<? super Device, Unit> callback) {
        Object objM1772constructorimpl;
        Intrinsics.checkNotNullParameter(ip, "ip");
        Intrinsics.checkNotNullParameter(callback, "callback");
        try {
            objM1772constructorimpl = Result.m1772constructorimpl(BuildersKt__Builders_commonKt.launch$default(this.browserScope, Dispatchers.getMain(), null, new C1649do(InetAddress.getByName(ip), ip, callback, null), 2, null));
        } catch (Throwable th) {
            objM1772constructorimpl = Result.m1772constructorimpl(ResultKt.createFailure(th));
        }
        if (Result.m1775exceptionOrNullimpl(objM1772constructorimpl) != null) {
            callback.invoke(null);
        }
    }

    public final Context getContext() {
        return this.context;
    }

    public final void reCheckAllUSBDevices() {
        UsbBgService.Connection connection = getServiceDiscoveryUSB().f3980try;
        if (connection != null) {
            connection.getService().reCheckAllUsbDevices();
        }
    }

    public final void refreshDevices() {
        List<C2082if> list = this.adapterHolders;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
        Iterator<T> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(((C2082if) it.next()).f2541do);
        }
        Iterator it2 = arrayList.iterator();
        while (it2.hasNext()) {
            stop((ServiceAdapter) it2.next());
        }
        Iterator it3 = arrayList.iterator();
        while (it3.hasNext()) {
            start((ServiceAdapter) it3.next());
        }
    }

    public LiveData<List<Device>> start(ServicesGroup serviceGroup) {
        Intrinsics.checkNotNullParameter(serviceGroup, "serviceGroup");
        Cfinal cfinal = new Cfinal(serviceGroup);
        start(cfinal);
        return cfinal.asLiveData();
    }

    public synchronized void start(ServiceAdapter serviceAdapter) {
        Intrinsics.checkNotNullParameter(serviceAdapter, "serviceAdapter");
        if (ExpirationChecker.hasExpired$default(this.ledc, null, null, 3, null)) {
            return;
        }
        BuildersKt__Builders_commonKt.launch$default(this.browserScope, Dispatchers.getMain(), null, new Cconst(serviceAdapter, null), 2, null);
        if (find(serviceAdapter.serviceGroup()) != null) {
            this.log.m446d("start() skipped for serviceAdapter " + serviceAdapter + " as it is already started");
        } else {
            this.log.m446d(">>> start() for serviceAdapter " + serviceAdapter);
            startBrowsingFor(serviceAdapter);
        }
    }

    public void stop(ServiceAdapter serviceAdapter) {
        Intrinsics.checkNotNullParameter(serviceAdapter, "serviceAdapter");
        stop(serviceAdapter.serviceGroup());
    }

    public synchronized void stop(ServicesGroup serviceGroup) {
        Unit unit;
        if (serviceGroup == null) {
            this.log.m446d("stop() all serviceGroups");
            Iterator<T> it = this.adapterHolders.iterator();
            while (it.hasNext()) {
                stopBrowsingFor((C2082if) it.next());
            }
            this.adapterHolders.clear();
        } else {
            C2082if c2082ifFind = find(serviceGroup);
            if (c2082ifFind != null) {
                if (this.adapterHolders.remove(c2082ifFind)) {
                    this.log.m446d("<<< stop() for serviceGroup " + serviceGroup);
                    stopBrowsingFor(c2082ifFind);
                }
                unit = Unit.INSTANCE;
            } else {
                unit = null;
            }
            if (unit == null) {
                this.log.m446d("stop() skipped as ServicesBrowser.start() was not invoked for serviceGroup " + serviceGroup);
            }
        }
        if (this.adapterHolders.isEmpty()) {
            getServiceBundler().f4012for.clear();
            JobKt__JobKt.cancelChildren$default(this.browserScope.getCoroutineContext(), (CancellationException) null, 1, (Object) null);
            this.log.m446d("stop() browserScope.coroutineContext.cancelChildren()");
        }
    }

    public final SocketFactory usbSocketFactory() {
        p066do.p026do.p028if.p029do.p030if.Cgoto serviceDiscoveryUSB = getServiceDiscoveryUSB();
        serviceDiscoveryUSB.getClass();
        return new IppUsbSocketFactory(serviceDiscoveryUSB.f3973break);
    }
}
