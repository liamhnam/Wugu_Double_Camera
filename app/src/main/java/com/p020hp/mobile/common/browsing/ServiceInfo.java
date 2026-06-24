package com.p020hp.mobile.common.browsing;

import java.io.Serializable;
import java.net.InetAddress;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.apache.http.cookie.ClientCookie;

@Metadata(m1292d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\u0010\u0012\n\u0002\b\f\u0018\u0000 \u00162\u00020\u0001:\u0001\u0016B9\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0003\u0012\u0012\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u000b0\n¢\u0006\u0002\u0010\fR\u001d\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u000b0\n¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0014¨\u0006\u0017"}, m1293d2 = {"Lcom/hp/mobile/common/browsing/ServiceInfo;", "Ljava/io/Serializable;", "serviceType", "", "host", "Ljava/net/InetAddress;", ClientCookie.PORT_ATTR, "", "serviceName", "attributes", "", "", "(Ljava/lang/String;Ljava/net/InetAddress;ILjava/lang/String;Ljava/util/Map;)V", "getAttributes", "()Ljava/util/Map;", "getHost", "()Ljava/net/InetAddress;", "getPort", "()I", "getServiceName", "()Ljava/lang/String;", "getServiceType", "Companion", "common-lib_release"}, m1294k = 1, m1295mv = {1, 6, 0}, m1297xi = 48)
public final class ServiceInfo implements Serializable {
    public static final long serialVersionUID = 4646010824471850000L;
    public final Map<String, byte[]> attributes;
    public final InetAddress host;
    public final int port;
    public final String serviceName;
    public final String serviceType;

    public ServiceInfo(String serviceType, InetAddress host, int i, String serviceName, Map<String, byte[]> attributes) {
        Intrinsics.checkNotNullParameter(serviceType, "serviceType");
        Intrinsics.checkNotNullParameter(host, "host");
        Intrinsics.checkNotNullParameter(serviceName, "serviceName");
        Intrinsics.checkNotNullParameter(attributes, "attributes");
        this.serviceType = serviceType;
        this.host = host;
        this.port = i;
        this.serviceName = serviceName;
        this.attributes = attributes;
    }

    public final Map<String, byte[]> getAttributes() {
        return this.attributes;
    }

    public final InetAddress getHost() {
        return this.host;
    }

    public final int getPort() {
        return this.port;
    }

    public final String getServiceName() {
        return this.serviceName;
    }

    public final String getServiceType() {
        return this.serviceType;
    }
}
