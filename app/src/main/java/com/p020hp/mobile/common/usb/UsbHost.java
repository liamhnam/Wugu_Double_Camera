package com.p020hp.mobile.common.usb;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

@Metadata(m1292d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\b0\u0018\u00002\u00020\u0001:\u0001\tB\u0017\b\u0004\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007\u0082\u0001\u0001\n¨\u0006\u000b"}, m1293d2 = {"Lcom/hp/mobile/common/usb/UsbHost;", "", "host", "", "address", "(Ljava/lang/String;Ljava/lang/String;)V", "getAddress", "()Ljava/lang/String;", "getHost", "UsbDevice", "Lcom/hp/mobile/common/usb/UsbHost$UsbDevice;", "common-lib_release"}, m1294k = 1, m1295mv = {1, 6, 0}, m1297xi = 48)
public abstract class UsbHost {
    public final String address;
    public final String host;

    @Metadata(m1292d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, m1293d2 = {"Lcom/hp/mobile/common/usb/UsbHost$UsbDevice;", "Lcom/hp/mobile/common/usb/UsbHost;", "()V", "common-lib_release"}, m1294k = 1, m1295mv = {1, 6, 0}, m1297xi = 48)
    public static final class UsbDevice extends UsbHost {
        public static final UsbDevice INSTANCE = new UsbDevice();

        public UsbDevice() {
            super("localhost", "127.0.0.1", null);
        }
    }

    public UsbHost(String str, String str2) {
        this.host = str;
        this.address = str2;
    }

    public UsbHost(String str, String str2, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2);
    }

    public final String getAddress() {
        return this.address;
    }

    public final String getHost() {
        return this.host;
    }
}
