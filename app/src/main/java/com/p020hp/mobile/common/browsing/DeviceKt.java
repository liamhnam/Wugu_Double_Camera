package com.p020hp.mobile.common.browsing;

import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(m1292d1 = {"\u0000\u001a\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\u001a\u0010\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u0003\u001a\u0010\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u0003\u001a\n\u0010\u0005\u001a\u00020\u0006*\u00020\u0003\u001a\n\u0010\u0007\u001a\u00020\u0006*\u00020\u0003\u001a\n\u0010\b\u001a\u00020\u0006*\u00020\u0003¨\u0006\t"}, m1293d2 = {"getPrintServices", "", "Lcom/hp/mobile/common/browsing/ServiceInfo;", "Lcom/hp/mobile/common/browsing/Device;", "getScanServices", "hasPrinter", "", "hasScanner", "isEmpty", "common-lib_release"}, m1294k = 2, m1295mv = {1, 6, 0}, m1297xi = 48)
public final class DeviceKt {
    public static final List<ServiceInfo> getPrintServices(Device device) {
        Intrinsics.checkNotNullParameter(device, "<this>");
        ArrayList arrayList = new ArrayList();
        ServiceInfo service = device.getService(ServiceType.IPP_SECURE);
        if (service != null) {
            arrayList.add(service);
        }
        ServiceInfo service2 = device.getService(ServiceType.IPP);
        if (service2 != null) {
            arrayList.add(service2);
        }
        return arrayList;
    }

    public static final List<ServiceInfo> getScanServices(Device device) {
        Intrinsics.checkNotNullParameter(device, "<this>");
        ArrayList arrayList = new ArrayList();
        ServiceInfo service = device.getService(ServiceType.ESCL_SECURE);
        if (service != null) {
            arrayList.add(service);
        }
        ServiceInfo service2 = device.getService(ServiceType.ESCL);
        if (service2 != null) {
            arrayList.add(service2);
        }
        return arrayList;
    }

    public static final boolean hasPrinter(Device device) {
        Intrinsics.checkNotNullParameter(device, "<this>");
        ServiceInfo service = device.getService(ServiceType.IPP_SECURE);
        if (service == null) {
            service = device.getService(ServiceType.IPP);
        }
        return service != null;
    }

    public static final boolean hasScanner(Device device) {
        Intrinsics.checkNotNullParameter(device, "<this>");
        ServiceInfo service = device.getService(ServiceType.ESCL_SECURE);
        if (service == null) {
            service = device.getService(ServiceType.ESCL);
        }
        return service != null;
    }

    public static final boolean isEmpty(Device device) {
        Intrinsics.checkNotNullParameter(device, "<this>");
        return device.cachedServices$common_lib_release().isEmpty();
    }
}
