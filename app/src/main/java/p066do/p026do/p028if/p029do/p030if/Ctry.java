package p066do.p026do.p028if.p029do.p030if;

import android.net.nsd.NsdServiceInfo;
import androidx.recyclerview.widget.ItemTouchHelper;
import com.p020hp.mobile.common.browsing.ConnectionType;
import com.p020hp.mobile.common.browsing.NsAttributeKey;
import com.p020hp.mobile.common.browsing.ServiceType;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.sync.Mutex;

@DebugMetadata(m1304c = "com.hp.mobile.common.browsing.ServiceDiscoveryIP$startFindByIp$2", m1305f = "ServiceDiscoveryIP.kt", m1306i = {0}, m1307l = {ItemTouchHelper.Callback.DEFAULT_SWIPE_ANIMATION_DURATION}, m1308m = "invokeSuspend", m1309n = {"$this$withLock_u24default$iv"}, m1310s = {"L$0"})
public final class Ctry extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super List<? extends NsdServiceInfo>>, Object> {

    public int f4018case;

    public final Ccase f4019else;

    public Object f4020for;

    public final String f4021goto;

    public Object f2550if;

    public Object f4022new;

    public final boolean f4023this;

    public boolean f4024try;

    public Ctry(Ccase ccase, String str, boolean z, Continuation<? super Ctry> continuation) {
        super(2, continuation);
        this.f4019else = ccase;
        this.f4021goto = str;
        this.f4023this = z;
    }

    @Override
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new Ctry(this.f4019else, this.f4021goto, this.f4023this, continuation);
    }

    @Override
    public Object invoke(CoroutineScope coroutineScope, Continuation<? super List<? extends NsdServiceInfo>> continuation) {
        return new Ctry(this.f4019else, this.f4021goto, this.f4023this, continuation).invokeSuspend(Unit.INSTANCE);
    }

    @Override
    public final Object invokeSuspend(Object obj) throws Throwable {
        Mutex mutex;
        String str;
        boolean z;
        Ccase ccase;
        String strM1216do;
        String strM1216do2;
        String strM1216do3;
        String strM1216do4;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.f4018case;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            Ccase ccase2 = this.f4019else;
            mutex = ccase2.f3932case;
            str = this.f4021goto;
            boolean z2 = this.f4023this;
            this.f2550if = mutex;
            this.f4020for = str;
            this.f4022new = ccase2;
            this.f4024try = z2;
            this.f4018case = 1;
            if (mutex.lock(null, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
            z = z2;
            ccase = ccase2;
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            z = this.f4024try;
            Ccase ccase3 = (Ccase) this.f4022new;
            str = (String) this.f4020for;
            mutex = (Mutex) this.f2550if;
            ResultKt.throwOnFailure(obj);
            ccase = ccase3;
        }
        try {
            InetAddress internetAddress = InetAddress.getByName(str);
            Object arrayList = new ArrayList();
            Intrinsics.checkNotNullExpressionValue(internetAddress, "internetAddress");
            ccase.getClass();
            int i2 = -1;
            NsdServiceInfo nsdServiceInfoM1218do = C2076do.m1218do((String) null, ServiceType.LEDM, internetAddress, -1, 1);
            String key = NsAttributeKey.UUID.getKey();
            String hostAddress = internetAddress.getHostAddress();
            if (hostAddress != null) {
                Intrinsics.checkNotNullExpressionValue(hostAddress, "hostAddress");
                strM1216do = ccase.m1216do(hostAddress);
            } else {
                strM1216do = null;
            }
            nsdServiceInfoM1218do.setAttribute(key, strM1216do);
            arrayList.add(nsdServiceInfoM1218do);
            NsdServiceInfo nsdServiceInfoM1218do2 = C2076do.m1218do((String) null, ServiceType.CDM, internetAddress, 443, 1);
            String key2 = NsAttributeKey.UUID.getKey();
            String hostAddress2 = internetAddress.getHostAddress();
            if (hostAddress2 != null) {
                Intrinsics.checkNotNullExpressionValue(hostAddress2, "hostAddress");
                strM1216do2 = ccase.m1216do(hostAddress2);
            } else {
                strM1216do2 = null;
            }
            nsdServiceInfoM1218do2.setAttribute(key2, strM1216do2);
            arrayList.add(nsdServiceInfoM1218do2);
            ccase.f3934new.m446d("startFindByIp() - probing " + ServiceType.IPP_SECURE.getType());
            NsdServiceInfo nsdServiceInfoM1218do3 = C2076do.m1218do((String) null, ServiceType.IPP_SECURE, internetAddress, 631, 1);
            Intrinsics.checkNotNullParameter(nsdServiceInfoM1218do3, "<this>");
            nsdServiceInfoM1218do3.setAttribute(NsAttributeKey.IPP_PATH.getKey(), "ipp/print");
            C2076do.m1228do(nsdServiceInfoM1218do3, ConnectionType.IP);
            String key3 = NsAttributeKey.UUID.getKey();
            String hostAddress3 = internetAddress.getHostAddress();
            if (hostAddress3 != null) {
                Intrinsics.checkNotNullExpressionValue(hostAddress3, "hostAddress");
                strM1216do3 = ccase.m1216do(hostAddress3);
            } else {
                strM1216do3 = null;
            }
            nsdServiceInfoM1218do3.setAttribute(key3, strM1216do3);
            arrayList.add(nsdServiceInfoM1218do3);
            ccase.f3934new.m446d("startFindByIp() - probing " + ServiceType.ESCL_SECURE.getType());
            ServiceType serviceType = ServiceType.ESCL_SECURE;
            if (serviceType != ServiceType.ESCL) {
                i2 = 443;
            }
            NsdServiceInfo nsdServiceInfoM1218do4 = C2076do.m1218do((String) null, serviceType, internetAddress, i2, 1);
            C2076do.m1228do(nsdServiceInfoM1218do4, ConnectionType.IP);
            String key4 = NsAttributeKey.UUID.getKey();
            String hostAddress4 = internetAddress.getHostAddress();
            if (hostAddress4 != null) {
                Intrinsics.checkNotNullExpressionValue(hostAddress4, "hostAddress");
                strM1216do4 = ccase.m1216do(hostAddress4);
            } else {
                strM1216do4 = null;
            }
            nsdServiceInfoM1218do4.setAttribute(key4, strM1216do4);
            arrayList.add(nsdServiceInfoM1218do4);
            if (ccase.m1217do((List<NsdServiceInfo>) arrayList)) {
                for (NsdServiceInfo nsdServiceInfo : arrayList) {
                    ccase.f3935try.put(C2076do.m1758new(nsdServiceInfo), nsdServiceInfo);
                    if (z) {
                        ccase.f3933for.invoke(nsdServiceInfo, Boxing.boxBoolean(true));
                    }
                }
            } else {
                arrayList = CollectionsKt.emptyList();
            }
            return arrayList;
        } finally {
            mutex.unlock(null);
        }
    }
}
