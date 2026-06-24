package com.p020hp.printsdk;

import android.content.Context;
import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbDeviceConnection;
import android.hardware.usb.UsbEndpoint;
import android.hardware.usb.UsbInterface;
import com.p020hp.mobile.common.utils.Logger;
import com.p020hp.mobile.common.utils.LoggerKt;
import java.io.Closeable;
import java.io.IOException;
import kotlin.jvm.internal.Intrinsics;

public final class C1679c4 implements Closeable {

    public static final Logger f1134d = LoggerKt.logger("UsbPrinter");

    public final UsbDevice f1135a;

    public final Context f1136b;

    public boolean f1137c;

    public static final class a implements Closeable {

        public final C1679c4 f1138a;

        public final UsbDeviceConnection f1139b;

        public final UsbInterface f1140c;

        public final UsbEndpoint f1141d;

        public final Logger f1142e = LoggerKt.logger(this);

        public boolean f1143f;

        public a(C1679c4 c1679c4, UsbDeviceConnection usbDeviceConnection, UsbInterface usbInterface, UsbEndpoint usbEndpoint, UsbEndpoint usbEndpoint2) {
            this.f1138a = c1679c4;
            this.f1139b = usbDeviceConnection;
            this.f1140c = usbInterface;
            this.f1141d = usbEndpoint2;
        }

        public final void m483a(byte[] bytes, int i, int i2) throws IOException {
            Intrinsics.checkNotNullParameter(bytes, "bytes");
            if (this.f1138a.f1137c) {
                throw new IOException(this.f1138a + " is closed");
            }
            if (this.f1143f) {
                throw new IOException(this + " is closed");
            }
            this.f1142e.m446d(this + " write bytes(" + bytes.length + "), offset(" + i + "), len(" + i2 + "), result(" + this.f1139b.bulkTransfer(this.f1141d, bytes, i, i2, 0) + ')');
        }

        @Override
        public void close() {
            this.f1142e.m446d("Close " + this);
            this.f1143f = true;
            this.f1139b.releaseInterface(this.f1140c);
            this.f1139b.close();
        }

        public String toString() {
            return "ConnectionWrapper[connection(" + this.f1139b.hashCode() + "), interface(" + this.f1140c.hashCode() + ")]";
        }
    }

    public static final class b {

        public final UsbInterface f1144a;

        public final UsbEndpoint f1145b;

        public final UsbEndpoint f1146c;

        public b(UsbInterface usbInterface, UsbEndpoint enIn, UsbEndpoint enOut) {
            Intrinsics.checkNotNullParameter(usbInterface, "usbInterface");
            Intrinsics.checkNotNullParameter(enIn, "enIn");
            Intrinsics.checkNotNullParameter(enOut, "enOut");
            this.f1144a = usbInterface;
            this.f1145b = enIn;
            this.f1146c = enOut;
        }

        public String toString() {
            return "Interface(" + this.f1144a.hashCode() + ')';
        }
    }

    public C1679c4(UsbDevice device, Context context) {
        Intrinsics.checkNotNullParameter(device, "device");
        Intrinsics.checkNotNullParameter(context, "context");
        this.f1135a = device;
        this.f1136b = context;
    }

    @Override
    public void close() {
        f1134d.m446d("Close " + this);
        this.f1137c = true;
    }

    public String toString() {
        return "UsbPrinter(name: " + this.f1135a.getProductName() + ", hashCode: 0x" + Integer.toHexString(hashCode()) + ')';
    }
}
