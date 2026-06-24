package p066do.p026do.p028if.p029do.p068case;

import android.hardware.usb.UsbDeviceConnection;
import android.hardware.usb.UsbEndpoint;
import android.hardware.usb.UsbManager;
import android.net.nsd.NsdServiceInfo;
import com.p020hp.mobile.common.CommonLibKt;
import com.p020hp.mobile.common.utils.Logger;
import com.p020hp.mobile.common.utils.LoggerKt;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.Iterator;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import p066do.p026do.p028if.p029do.p030if.Cgoto;
import p066do.p026do.p028if.p029do.p068case.Celse;

public final class Cfor extends Socket {

    public final Logger f3909for;

    public final List<Cgoto.C2080do> f2492if;

    public Celse.C2064do f3910new;

    public Cfor(List<Cgoto.C2080do> usbServicesBundles) {
        Intrinsics.checkNotNullParameter(usbServicesBundles, "usbServicesBundles");
        this.f2492if = usbServicesBundles;
        this.f3909for = LoggerKt.logger(LoggerKt.toTag("IppUsbSocket"));
    }

    @Override
    public void close() {
        this.f3909for.m446d("Socket(" + hashCode() + ") disconnected to " + this.f3910new);
        Celse.C2064do c2064do = this.f3910new;
        if (c2064do != null) {
            c2064do.close();
        }
        this.f3910new = null;
    }

    @Override
    public void connect(SocketAddress socketAddress, int i) throws InterruptedException, IOException {
        Object next;
        Celse device;
        Object next2;
        Celse.C2064do c2064do;
        Object next3;
        this.f3909for.m446d("connect() - endpoint " + socketAddress);
        if (socketAddress == null) {
            throw new IllegalArgumentException("connect: The address can't be null".toString());
        }
        if (!(i >= 0)) {
            throw new IllegalArgumentException("connect: timeout can't be negative".toString());
        }
        InetSocketAddress inetSocketAddress = (InetSocketAddress) socketAddress;
        String hostName = inetSocketAddress.getHostName();
        Intrinsics.checkNotNullExpressionValue(hostName, "address.hostName");
        int port = inetSocketAddress.getPort();
        this.f3909for.m446d("Usb connect: host(" + hostName + "), port(" + port + ')');
        Cgoto.C2080do.Cdo cdo = Cgoto.C2080do.f3982new;
        List<Cgoto.C2080do> list = this.f2492if;
        Intrinsics.checkNotNullParameter(list, "<this>");
        Iterator<T> it = list.iterator();
        while (true) {
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
            Iterator<T> it2 = ((Cgoto.C2080do) next).f3984for.iterator();
            while (true) {
                if (!it2.hasNext()) {
                    next3 = null;
                    break;
                } else {
                    next3 = it2.next();
                    if (((NsdServiceInfo) next3).getPort() == port) {
                        break;
                    }
                }
            }
            if (next3 != null) {
                break;
            }
        }
        Cgoto.C2080do c2080do = (Cgoto.C2080do) next;
        if (c2080do == null || (device = c2080do.f2536if) == null) {
            throw new IOException("The connection for port " + port + " is not found");
        }
        while (true) {
            Celse.f3899try.m446d("Opening device: " + device);
            Object systemService = CommonLibKt.context().getSystemService("usb");
            UsbManager usbManager = systemService instanceof UsbManager ? (UsbManager) systemService : null;
            if (usbManager == null) {
                throw new IOException("Could not get UsbManager instance.");
            }
            UsbDeviceConnection connection = usbManager.openDevice(device.f2487if);
            if (connection == null) {
                throw new IOException("Open usb device failed.");
            }
            Celse.f3899try.m446d("Creating ConnectionWrapper for UsbDeviceConnection(" + connection.hashCode() + ')');
            Iterator it3 = ((List) device.f3901new.getValue()).iterator();
            while (true) {
                if (!it3.hasNext()) {
                    next2 = null;
                    break;
                } else {
                    next2 = it3.next();
                    if (connection.claimInterface(((Celse.C2065if) next2).f2489do, false)) {
                        break;
                    }
                }
            }
            Celse.C2065if wrapper = (Celse.C2065if) next2;
            if (wrapper != null) {
                UsbEndpoint endPoint = wrapper.f2490if;
                Intrinsics.checkNotNullParameter(connection, "connection");
                Intrinsics.checkNotNullParameter(endPoint, "endPoint");
                Celse.f3899try.m446d("EndPointSanitizer.sanitize()");
                int maxPacketSize = endPoint.getMaxPacketSize();
                byte[] bArr = new byte[maxPacketSize];
                while (true) {
                    int iBulkTransfer = connection.bulkTransfer(endPoint, bArr, maxPacketSize, 40);
                    if (iBulkTransfer <= 0) {
                        break;
                    }
                    Thread.sleep(20L);
                    Celse.f3899try.m446d("Blowed " + iBulkTransfer + " bytes");
                }
                Intrinsics.checkNotNullParameter(device, "device");
                Intrinsics.checkNotNullParameter(connection, "connection");
                Intrinsics.checkNotNullParameter(wrapper, "wrapper");
                c2064do = new Celse.C2064do(device, connection, wrapper.m1754for(), wrapper.m1203do(), wrapper.m1204if());
                Celse.f3899try.m446d("Create " + c2064do + " done.");
            } else {
                Celse.f3899try.m446d("Create ConnectionWrapper failed, could retry it later.");
                connection.close();
                c2064do = null;
            }
            this.f3910new = c2064do;
            if (c2064do != null) {
                this.f3909for.m446d("Socket(" + hashCode() + ") connected to " + this.f3910new);
                return;
            } else {
                this.f3909for.m446d("Device " + device + " is busy, will retry it later.");
                Thread.sleep(50L);
            }
        }
    }

    @Override
    public InputStream getInputStream() throws IOException {
        Celse.C2064do c2064do = this.f3910new;
        if (c2064do != null) {
            return new C2063do(c2064do);
        }
        throw new IOException("Input stream is null");
    }

    @Override
    public OutputStream getOutputStream() throws IOException {
        Celse.C2064do c2064do = this.f3910new;
        if (c2064do != null) {
            return new C2067if(c2064do);
        }
        throw new IOException("Output stream is null");
    }
}
