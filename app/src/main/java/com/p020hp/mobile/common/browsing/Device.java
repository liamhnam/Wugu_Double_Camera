package com.p020hp.mobile.common.browsing;

import java.io.Serializable;
import java.net.InetAddress;
import java.net.URL;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.apache.http.cookie.ClientCookie;

@Metadata(m1292d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0006\u0018\u0000 $2\u00020\u00012\u00020\u0002:\u0001$B\u0007\b\u0000¢\u0006\u0002\u0010\u0003J\u0015\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u0007H\u0000¢\u0006\u0002\b\u000fJ\u0013\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00070\u0011H\u0000¢\u0006\u0002\b\u0012J\n\u0010\u0013\u001a\u0004\u0018\u00010\u0014H\u0016J\u0010\u0010\u0015\u001a\u0004\u0018\u00010\u00072\u0006\u0010\u0016\u001a\u00020\u0017J\n\u0010\u0018\u001a\u0004\u0018\u00010\u0006H\u0016J\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aJ\b\u0010\u001b\u001a\u00020\u001cH\u0016J\b\u0010\u001d\u001a\u00020\u001cH\u0016J\u000f\u0010\u001e\u001a\u0004\u0018\u00010\u001fH\u0016¢\u0006\u0002\u0010 J\u0017\u0010!\u001a\u0004\u0018\u00010\u00072\u0006\u0010\"\u001a\u00020\u0006H\u0000¢\u0006\u0002\b#R\u001a\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R$\u0010\t\u001a\u0004\u0018\u00010\u00062\b\u0010\b\u001a\u0004\u0018\u00010\u00068V@RX\u0096\u000e¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006%"}, m1293d2 = {"Lcom/hp/mobile/common/browsing/Device;", "Ljava/io/Serializable;", "Lcom/hp/mobile/common/browsing/IDevice;", "()V", "_services", "", "", "Lcom/hp/mobile/common/browsing/ServiceInfo;", "<set-?>", "makeAndModel", "getMakeAndModel", "()Ljava/lang/String;", "addService", "", "serviceInfo", "addService$common_lib_release", "cachedServices", "", "cachedServices$common_lib_release", "connectionType", "Lcom/hp/mobile/common/browsing/ConnectionType;", "getService", "serviceType", "Lcom/hp/mobile/common/browsing/ServiceType;", "hostAddress", "icon", "Ljava/net/URL;", "isCDM", "", "isLEDM", ClientCookie.PORT_ATTR, "", "()Ljava/lang/Integer;", "removeService", "identifier", "removeService$common_lib_release", "Companion", "common-lib_release"}, m1294k = 1, m1295mv = {1, 6, 0}, m1297xi = 48)
public final class Device implements Serializable, IDevice {
    public static final long serialVersionUID = -5065988568320609153L;
    public final Map<String, ServiceInfo> _services = new LinkedHashMap();
    public String makeAndModel;

    public final void addService$common_lib_release(ServiceInfo serviceInfo) {
        Intrinsics.checkNotNullParameter(serviceInfo, "serviceInfo");
        this._services.put(ServiceInfoKt.identifier(serviceInfo), serviceInfo);
    }

    public final List<ServiceInfo> cachedServices$common_lib_release() {
        return CollectionsKt.toList(this._services.values());
    }

    @Override
    public ConnectionType connectionType() {
        ServiceInfo serviceInfo = (ServiceInfo) CollectionsKt.firstOrNull(this._services.values());
        if (serviceInfo != null) {
            return ServiceInfoKt.connectionType(serviceInfo);
        }
        return null;
    }

    @Override
    public String getMakeAndModel() {
        Object next;
        String str = this.makeAndModel;
        if (str == null) {
            Iterator<T> it = this._services.values().iterator();
            while (true) {
                if (!it.hasNext()) {
                    next = null;
                    break;
                }
                next = it.next();
                String str2 = ServiceInfoKt.get((ServiceInfo) next, NsAttributeKey.MAKE_AND_MODEL.getKey());
                if (str2 != null && str2.length() > 0) {
                    break;
                }
            }
            ServiceInfo serviceInfo = (ServiceInfo) next;
            str = serviceInfo != null ? ServiceInfoKt.get(serviceInfo, NsAttributeKey.MAKE_AND_MODEL.getKey()) : null;
            this.makeAndModel = str;
        }
        return str;
    }

    public final ServiceInfo getService(ServiceType serviceType) {
        Object next;
        Intrinsics.checkNotNullParameter(serviceType, "serviceType");
        Iterator<T> it = this._services.values().iterator();
        while (true) {
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
            if (Intrinsics.areEqual(((ServiceInfo) next).getServiceType(), serviceType.getType())) {
                break;
            }
        }
        return (ServiceInfo) next;
    }

    @Override
    public String hostAddress() {
        InetAddress host;
        ServiceInfo serviceInfo = (ServiceInfo) CollectionsKt.firstOrNull(this._services.values());
        if (serviceInfo == null || (host = serviceInfo.getHost()) == null) {
            return null;
        }
        return host.getHostAddress();
    }

    public final URL icon() {
        return ServiceInfoKt.findIcon(this._services.values());
    }

    @Override
    public boolean isCDM() {
        return getService(ServiceType.CDM) != null;
    }

    @Override
    public boolean isLEDM() {
        return getService(ServiceType.LEDM) != null;
    }

    @Override
    public Integer port() {
        ServiceInfo serviceInfo = (ServiceInfo) CollectionsKt.firstOrNull((List) cachedServices$common_lib_release());
        if (serviceInfo != null) {
            return Integer.valueOf(serviceInfo.getPort());
        }
        return null;
    }

    public final ServiceInfo removeService$common_lib_release(String identifier) {
        Intrinsics.checkNotNullParameter(identifier, "identifier");
        return this._services.remove(identifier);
    }
}
