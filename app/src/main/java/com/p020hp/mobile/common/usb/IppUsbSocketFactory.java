package com.p020hp.mobile.common.usb;

import java.net.InetAddress;
import java.net.Socket;
import java.util.List;
import javax.net.SocketFactory;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.apache.http.cookie.ClientCookie;
import p066do.p026do.p028if.p029do.p030if.Cgoto;
import p066do.p026do.p028if.p029do.p068case.Cfor;

@Metadata(m1292d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u0001B\u0013\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0002\u0010\u0005J\b\u0010\u0006\u001a\u00020\u0007H\u0016J\u0018\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0016J(\u0010\u0006\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\r\u001a\u00020\t2\u0006\u0010\u000e\u001a\u00020\u000bH\u0016J\u0018\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u000f2\u0006\u0010\n\u001a\u00020\u000bH\u0016J(\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u000f2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0010\u001a\u00020\t2\u0006\u0010\u000e\u001a\u00020\u000bH\u0016R\u0014\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0011"}, m1293d2 = {"Lcom/hp/mobile/common/usb/IppUsbSocketFactory;", "Ljavax/net/SocketFactory;", "usbServicesBundles", "", "Lcom/hp/mobile/common/browsing/ServiceDiscoveryUSB$UsbServicesBundle;", "(Ljava/util/List;)V", "createSocket", "Ljava/net/Socket;", "host", "Ljava/net/InetAddress;", ClientCookie.PORT_ATTR, "", "address", "localAddress", "localPort", "", "localHost", "common-lib_release"}, m1294k = 1, m1295mv = {1, 6, 0}, m1297xi = 48)
public final class IppUsbSocketFactory extends SocketFactory {
    public final List<Cgoto.C2080do> usbServicesBundles;

    public IppUsbSocketFactory(List<Cgoto.C2080do> usbServicesBundles) {
        Intrinsics.checkNotNullParameter(usbServicesBundles, "usbServicesBundles");
        this.usbServicesBundles = usbServicesBundles;
    }

    @Override
    public Socket createSocket() {
        return new Cfor(this.usbServicesBundles);
    }

    @Override
    public Socket createSocket(String host, int port) {
        Intrinsics.checkNotNullParameter(host, "host");
        return createSocket();
    }

    @Override
    public Socket createSocket(String host, int port, InetAddress localHost, int localPort) {
        Intrinsics.checkNotNullParameter(host, "host");
        Intrinsics.checkNotNullParameter(localHost, "localHost");
        return createSocket();
    }

    @Override
    public Socket createSocket(InetAddress host, int port) {
        Intrinsics.checkNotNullParameter(host, "host");
        return createSocket();
    }

    @Override
    public Socket createSocket(InetAddress address, int port, InetAddress localAddress, int localPort) {
        Intrinsics.checkNotNullParameter(address, "address");
        Intrinsics.checkNotNullParameter(localAddress, "localAddress");
        return createSocket();
    }
}
