package p066do.p026do.p028if.p029do.p030if;

import android.content.Context;
import android.net.nsd.NsdServiceInfo;
import com.p020hp.mobile.common.browsing.ServiceType;
import com.p020hp.mobile.common.utils.Logger;
import com.p020hp.mobile.common.utils.LoggerKt;
import java.net.InetAddress;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.JobKt__JobKt;
import kotlinx.coroutines.sync.Mutex;
import kotlinx.coroutines.sync.MutexKt;

public final class Ccase implements CoroutineScope {

    public final Mutex f3932case;

    public final Function2<NsdServiceInfo, Boolean, Unit> f3933for;

    public final CoroutineContext f2513if;

    public final Logger f3934new;

    public final Map<String, NsdServiceInfo> f3935try;

    public Ccase(Context context, CoroutineContext scope, Function2<? super NsdServiceInfo, ? super Boolean, Unit> onAvailable) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(scope, "scope");
        Intrinsics.checkNotNullParameter(onAvailable, "onAvailable");
        this.f2513if = scope;
        this.f3933for = onAvailable;
        this.f3934new = LoggerKt.logger(LoggerKt.toTag("ServiceDiscoveryIP"));
        this.f3935try = new LinkedHashMap();
        this.f3932case = MutexKt.Mutex$default(false, 1, null);
    }

    public final Object m1215do(InetAddress inetAddress, Continuation<? super List<NsdServiceInfo>> continuation) {
        this.f3934new.m446d("addByIp() - host:" + inetAddress.getHostAddress());
        String hostAddress = inetAddress.getHostAddress();
        if (hostAddress != null) {
            return BuildersKt.withContext(Dispatchers.getIO(), new Ctry(this, hostAddress, false, null), continuation);
        }
        this.f3934new.m446d("addByIp() - ignored due to no host address");
        return CollectionsKt.emptyList();
    }

    public final String m1216do(String str) {
        String string = "00000000-0000-0000-0000-000000000000";
        int i = 0;
        for (Object obj : StringsKt.split$default((CharSequence) str, new String[]{"."}, false, 0, 6, (Object) null)) {
            int i2 = i + 1;
            if (i < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            String str2 = (String) obj;
            int i3 = i == 0 ? 0 : ((i - 1) * 5) + 9;
            string = StringsKt.replaceRange((CharSequence) string, i3, str2.length() + i3, (CharSequence) str2).toString();
            i = i2;
        }
        return string;
    }

    public final boolean m1217do(List<NsdServiceInfo> list) {
        Object next;
        Iterator<T> it = list.iterator();
        while (true) {
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
            NsdServiceInfo nsdServiceInfo = (NsdServiceInfo) next;
            if (Intrinsics.areEqual(nsdServiceInfo.getServiceType(), ServiceType.IPP.getType()) || Intrinsics.areEqual(nsdServiceInfo.getServiceType(), ServiceType.IPP_SECURE.getType()) || Intrinsics.areEqual(nsdServiceInfo.getServiceType(), ServiceType.ESCL.getType()) || Intrinsics.areEqual(nsdServiceInfo.getServiceType(), ServiceType.ESCL_SECURE.getType())) {
                break;
            }
        }
        NsdServiceInfo nsdServiceInfo2 = (NsdServiceInfo) next;
        if (nsdServiceInfo2 != null) {
            StringBuilder sbAppend = new StringBuilder().append(nsdServiceInfo2.getServiceName()).append(" [");
            String string = nsdServiceInfo2.getHost().toString();
            Intrinsics.checkNotNullExpressionValue(string, "it.host.toString()");
            String string2 = sbAppend.append(StringsKt.trim(string, '/')).append(']').toString();
            if (string2 != null) {
                this.f3934new.m446d("normalizedServiceName = " + string2);
                for (NsdServiceInfo nsdServiceInfo3 : list) {
                    this.f3934new.m446d("normalizing service name for " + nsdServiceInfo3.getServiceType());
                    nsdServiceInfo3.setServiceName(string2);
                }
                return true;
            }
        }
        return false;
    }

    @Override
    public CoroutineContext getCoroutineContext() {
        return this.f2513if.plus(JobKt__JobKt.Job$default((Job) null, 1, (Object) null));
    }
}
