package com.p020hp.mobile.common.browsing;

import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.MapsKt;
import kotlin.ranges.RangesKt;

@Metadata(m1292d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u000b\b\u0086\u0001\u0018\u0000 \r2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\rB\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\f¨\u0006\u000e"}, m1293d2 = {"Lcom/hp/mobile/common/browsing/ServiceType;", "", "type", "", "(Ljava/lang/String;ILjava/lang/String;)V", "getType", "()Ljava/lang/String;", "ESCL", "ESCL_SECURE", "LEDM", "CDM", "IPP", "IPP_SECURE", "Companion", "common-lib_release"}, m1294k = 1, m1295mv = {1, 6, 0}, m1297xi = 48)
public enum ServiceType {
    ESCL("_uscan._tcp"),
    ESCL_SECURE("_uscans._tcp"),
    LEDM("_http-alt._tcp"),
    CDM("_cdm._tcp"),
    IPP("_ipp._tcp"),
    IPP_SECURE("_ipps._tcp");


    public static final Companion INSTANCE = new Companion();
    public static final Map<String, ServiceType> map;
    public final String type;

    public static final class Companion {
    }

    static {
        ServiceType[] serviceTypeArrValues = values();
        LinkedHashMap linkedHashMap = new LinkedHashMap(RangesKt.coerceAtLeast(MapsKt.mapCapacity(serviceTypeArrValues.length), 16));
        for (ServiceType serviceType : serviceTypeArrValues) {
            linkedHashMap.put(serviceType.type, serviceType);
        }
        map = linkedHashMap;
    }

    ServiceType(String str) {
        this.type = str;
    }

    public final String getType() {
        return this.type;
    }
}
