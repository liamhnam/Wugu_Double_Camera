package com.p020hp.mobile.common.browsing;

import com.p020hp.mobile.common.usb.UsbHost;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(m1292d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u000b\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\n\u0010\u0000\u001a\u00020\u0001*\u00020\u0002\u001a\n\u0010\u0003\u001a\u00020\u0004*\u00020\u0002¨\u0006\u0005"}, m1293d2 = {"isUsbHost", "", "", "toConnectionType", "Lcom/hp/mobile/common/browsing/ConnectionType;", "common-lib_release"}, m1294k = 2, m1295mv = {1, 6, 0}, m1297xi = 48)
public final class ConnectionTypeKt {
    public static final boolean isUsbHost(String str) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        return Intrinsics.areEqual(str, UsbHost.UsbDevice.INSTANCE.getHost()) || Intrinsics.areEqual(str, UsbHost.UsbDevice.INSTANCE.getAddress()) || Intrinsics.areEqual(str, "[::]");
    }

    public static final ConnectionType toConnectionType(String str) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        return isUsbHost(str) ? ConnectionType.USB : ConnectionType.WIFI;
    }
}
