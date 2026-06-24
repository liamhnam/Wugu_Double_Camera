package p066do.p026do.p028if.p029do.p068case;

import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbDeviceConnection;
import android.hardware.usb.UsbEndpoint;
import android.hardware.usb.UsbInterface;
import com.p020hp.mobile.common.CommonLib;
import com.p020hp.mobile.common.usb.Interface;
import com.p020hp.mobile.common.usb.IppUsbInterfaceMapping;
import com.p020hp.mobile.common.utils.Logger;
import com.p020hp.mobile.common.utils.LoggerKt;
import java.io.Closeable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.collections.CollectionsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

public final class Celse implements Closeable {

    public static final Logger f3899try = LoggerKt.logger(LoggerKt.toTag("UsbPrinter"));

    public boolean f3900for;

    public final UsbDevice f2487if;

    public final Lazy f3901new;

    public static final class C2064do implements Closeable {

        public final UsbEndpoint f3902case;

        public final Logger f3903else = LoggerKt.logger(this);

        public final UsbDeviceConnection f3904for;

        public boolean f3905goto;

        public final Celse f2488if;

        public final UsbInterface f3906new;

        public final UsbEndpoint f3907try;

        public C2064do(Celse celse, UsbDeviceConnection usbDeviceConnection, UsbInterface usbInterface, UsbEndpoint usbEndpoint, UsbEndpoint usbEndpoint2) {
            this.f2488if = celse;
            this.f3904for = usbDeviceConnection;
            this.f3906new = usbInterface;
            this.f3907try = usbEndpoint;
            this.f3902case = usbEndpoint2;
        }

        @Override
        public void close() {
            this.f3903else.m446d("Close " + this);
            this.f3905goto = true;
            this.f3904for.releaseInterface(this.f3906new);
            this.f3904for.close();
        }

        public final void m1202do() throws IOException {
            if (this.f2488if.f3900for) {
                throw new IOException(this.f2488if + " is closed");
            }
            if (this.f3905goto) {
                throw new IOException(this + " is closed");
            }
        }

        public String toString() {
            return "ConnectionWrapper[connection(" + this.f3904for.hashCode() + "), interface(" + this.f3906new.hashCode() + ")]";
        }
    }

    public static final class Cfor<T> implements Comparator {
        @Override
        public final int compare(T t, T t2) {
            return ComparisonsKt.compareValues(Integer.valueOf(((UsbInterface) t).getId()), Integer.valueOf(((UsbInterface) t2).getId()));
        }
    }

    public static final class C2065if {

        public final UsbInterface f2489do;

        public final UsbEndpoint f3908for;

        public final UsbEndpoint f2490if;

        public C2065if(UsbInterface usbInterface, UsbEndpoint enIn, UsbEndpoint enOut) {
            Intrinsics.checkNotNullParameter(usbInterface, "usbInterface");
            Intrinsics.checkNotNullParameter(enIn, "enIn");
            Intrinsics.checkNotNullParameter(enOut, "enOut");
            this.f2489do = usbInterface;
            this.f2490if = enIn;
            this.f3908for = enOut;
        }

        public final UsbEndpoint m1203do() {
            return this.f2490if;
        }

        public final UsbInterface m1754for() {
            return this.f2489do;
        }

        public final UsbEndpoint m1204if() {
            return this.f3908for;
        }

        public String toString() {
            return "Interface(" + this.f2489do.hashCode() + ')';
        }
    }

    public Celse(UsbDevice device) {
        Intrinsics.checkNotNullParameter(device, "device");
        this.f2487if = device;
        this.f3901new = LazyKt.lazy(new Cnew());
    }

    @Override
    public void close() {
        f3899try.m446d("Close " + this);
        this.f3900for = true;
    }

    public final ArrayList<UsbInterface> m1201do() {
        f3899try.m446d("Find interface according to default configuration");
        ArrayList arrayList = new ArrayList();
        int interfaceCount = this.f2487if.getInterfaceCount();
        for (int i = 0; i < interfaceCount; i++) {
            arrayList.add(this.f2487if.getInterface(i));
        }
        if (arrayList.size() > 1) {
            CollectionsKt.sortWith(arrayList, new Cfor());
        }
        ArrayList<UsbInterface> arrayList2 = new ArrayList<>();
        int i2 = 0;
        int i3 = 0;
        for (Object obj : arrayList) {
            int i4 = i2 + 1;
            if (i2 < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            UsbInterface usbInterface = (UsbInterface) obj;
            boolean z = usbInterface.getInterfaceClass() == 7 && usbInterface.getInterfaceSubclass() == 1 && usbInterface.getInterfaceProtocol() == 4;
            boolean zAreEqual = Intrinsics.areEqual("IPP Printer", usbInterface.getName());
            if ((z || zAreEqual) && (i3 = i3 + 1) <= 3) {
                f3899try.m446d("Found a usbInterface(" + usbInterface.hashCode() + ')');
                arrayList2.add(usbInterface);
            }
            i2 = i4;
        }
        return arrayList2;
    }

    public String toString() {
        return "UsbPrinter(name: " + this.f2487if.getProductName() + ", hashCode: 0x" + Integer.toHexString(hashCode()) + ')';
    }

    public static final class Cnew extends Lambda implements Function0<List<? extends C2065if>> {

        public static final class C2066do<T> implements Comparator {
            @Override
            public final int compare(T t, T t2) {
                return ComparisonsKt.compareValues(Integer.valueOf(((UsbInterface) t).getId()), Integer.valueOf(((UsbInterface) t2).getId()));
            }
        }

        public Cnew() {
            super(0);
        }

        @Override
        public final List<C2065if> invoke() {
            Object next;
            C2065if c2065if;
            List<Interface> interfaceWhiteList;
            Celse celse = Celse.this;
            celse.getClass();
            List<IppUsbInterfaceMapping> usbInterfaceWhiteList = CommonLib.INSTANCE.getUsbInterfaceWhiteList();
            Celse.f3899try.invoke("Find interface according white list: " + usbInterfaceWhiteList);
            ArrayList<UsbInterface> arrayList = new ArrayList<>();
            Iterator<T> it = usbInterfaceWhiteList.iterator();
            while (true) {
                if (!it.hasNext()) {
                    next = null;
                    break;
                }
                next = it.next();
                if (Intrinsics.areEqual(((IppUsbInterfaceMapping) next).getMProductName(), celse.f2487if.getProductName())) {
                    break;
                }
            }
            IppUsbInterfaceMapping ippUsbInterfaceMapping = (IppUsbInterfaceMapping) next;
            if (ippUsbInterfaceMapping != null && (interfaceWhiteList = ippUsbInterfaceMapping.getInterfaceWhiteList()) != null) {
                int interfaceCount = celse.f2487if.getInterfaceCount();
                for (Interface r6 : interfaceWhiteList) {
                    for (int i = 0; i < interfaceCount; i++) {
                        UsbInterface usbInterface = celse.f2487if.getInterface(i);
                        Intrinsics.checkNotNullExpressionValue(usbInterface, "device.getInterface(i)");
                        if (usbInterface.getId() == r6.getMId() && usbInterface.getInterfaceClass() == r6.getMClass() && usbInterface.getInterfaceSubclass() == r6.getMSubClass() && usbInterface.getInterfaceProtocol() == r6.getMProtocol()) {
                            Celse.f3899try.m446d(new Cgoto(usbInterface));
                            arrayList.add(usbInterface);
                        }
                    }
                }
            }
            Celse celse2 = Celse.this;
            if (arrayList.isEmpty()) {
                arrayList = celse2.m1201do();
            }
            List<UsbInterface> listSortedWith = CollectionsKt.sortedWith(arrayList, new C2066do());
            Celse celse3 = Celse.this;
            ArrayList arrayList2 = new ArrayList();
            for (UsbInterface usbInterface2 : listSortedWith) {
                celse3.getClass();
                int endpointCount = usbInterface2.getEndpointCount();
                UsbEndpoint usbEndpoint = null;
                UsbEndpoint usbEndpoint2 = null;
                for (int i2 = 0; i2 < endpointCount; i2++) {
                    UsbEndpoint endpoint = usbInterface2.getEndpoint(i2);
                    if (endpoint.getType() == 2) {
                        if (endpoint.getDirection() == 0) {
                            usbEndpoint = endpoint;
                        } else {
                            usbEndpoint2 = endpoint;
                        }
                    }
                }
                if (usbEndpoint == null || usbEndpoint2 == null) {
                    Celse.f3899try.m447e("Interface(" + usbInterface2.hashCode() + ")'s endpoint is not found");
                    c2065if = null;
                } else {
                    Celse.f3899try.m446d("Interface(" + usbInterface2.hashCode() + ")'s endpoint is found");
                    c2065if = new C2065if(usbInterface2, usbEndpoint2, usbEndpoint);
                }
                if (c2065if != null) {
                    arrayList2.add(c2065if);
                }
            }
            Celse.f3899try.m446d("Found usable bulk interface list: " + arrayList2);
            return arrayList2;
        }
    }
}
