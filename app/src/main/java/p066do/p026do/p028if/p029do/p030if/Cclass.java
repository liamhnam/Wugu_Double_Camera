package p066do.p026do.p028if.p029do.p030if;

import android.net.nsd.NsdServiceInfo;
import com.p020hp.mobile.common.browsing.ServicesBrowser;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

public final class Cclass extends Lambda implements Function2<NsdServiceInfo, Boolean, Unit> {

    public final ServicesBrowser f2516if;

    public Cclass(ServicesBrowser servicesBrowser) {
        super(2);
        this.f2516if = servicesBrowser;
    }

    @Override
    public Unit invoke(NsdServiceInfo nsdServiceInfo, Boolean bool) {
        NsdServiceInfo info = nsdServiceInfo;
        boolean zBooleanValue = bool.booleanValue();
        Intrinsics.checkNotNullParameter(info, "info");
        this.f2516if.handleServiceInfo(info, zBooleanValue);
        return Unit.INSTANCE;
    }
}
