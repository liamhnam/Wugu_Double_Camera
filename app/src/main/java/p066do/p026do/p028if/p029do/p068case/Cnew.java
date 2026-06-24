package p066do.p026do.p028if.p029do.p068case;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbManager;
import android.os.Build;
import com.p020hp.mobile.common.utils.Logger;
import com.p020hp.mobile.common.utils.LoggerKt;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Pair;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

public final class Cnew {

    public InterfaceC2068do f3912case;

    public final Context f2495do;

    public final C2069if f3913else;

    public final Lazy f3914for;

    public final Logger f2496if;

    public final ArrayList<UsbDevice> f3915new;

    public final Lazy f3916try;

    public interface InterfaceC2068do {
        void mo444do(UsbDevice usbDevice, boolean z);
    }

    public static final class Cfor extends Lambda implements Function0<UsbManager> {
        public Cfor() {
            super(0);
        }

        @Override
        public UsbManager invoke() {
            Object systemService = Cnew.this.f2495do.getSystemService("usb");
            if (systemService != null) {
                return (UsbManager) systemService;
            }
            throw new NullPointerException("null cannot be cast to non-null type android.hardware.usb.UsbManager");
        }
    }

    public static final class C2069if extends BroadcastReceiver {
        public C2069if() {
        }

        @Override
        public void onReceive(Context context, Intent intent) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(intent, "intent");
            if (Intrinsics.areEqual("android.hardware.usb.action.USB_DEVICE_DETACHED", intent.getAction())) {
                UsbDevice lostDevice = (UsbDevice) (Build.VERSION.SDK_INT >= 33 ? intent.getParcelableExtra("device", UsbDevice.class) : intent.getParcelableExtra("device"));
                Cnew.this.f2496if.invoke("usb device detached: " + (lostDevice != null ? lostDevice.getDeviceName() : null));
                if (lostDevice != null) {
                    Cnew cnew = Cnew.this;
                    Ccase ccaseM1755for = cnew.m1755for();
                    ccaseM1755for.getClass();
                    Intrinsics.checkNotNullParameter(lostDevice, "lostDevice");
                    if (ccaseM1755for.f3897try.contains(lostDevice)) {
                        if (Intrinsics.areEqual(ccaseM1755for.f3894case, lostDevice)) {
                            ccaseM1755for.f3894case = null;
                        } else {
                            ccaseM1755for.f3895for.invoke("remove() - lost device " + ccaseM1755for.m1200if(lostDevice));
                            ccaseM1755for.m1198do();
                            if (Intrinsics.areEqual(lostDevice, ccaseM1755for.f3897try.peek())) {
                                ccaseM1755for.f3895for.invoke("remove() - not removed as this is the head of queue. In queue = " + ccaseM1755for.f3897try.size());
                            } else {
                                ccaseM1755for.f3895for.invoke("remove() - isRemoved = " + ccaseM1755for.f3897try.remove(lostDevice) + ". In queue = " + ccaseM1755for.f3897try.size());
                            }
                        }
                    }
                    cnew.f2496if.invoke("device: \n" + lostDevice.getProductName());
                    cnew.f3915new.remove(lostDevice);
                    InterfaceC2068do interfaceC2068do = cnew.f3912case;
                    if (interfaceC2068do != null) {
                        interfaceC2068do.mo444do(lostDevice, false);
                    }
                }
            }
        }
    }

    public static final class C3376new extends Lambda implements Function0<Ccase> {
        public C3376new() {
            super(0);
        }

        @Override
        public Ccase invoke() {
            Cnew cnew = Cnew.this;
            return new Ccase(cnew.f2495do, new Ctry(cnew));
        }
    }

    public Cnew(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.f2495do = context;
        this.f2496if = LoggerKt.logger(LoggerKt.toTag("UsbConnectionManager"));
        this.f3914for = LazyKt.lazy(new Cfor());
        this.f3915new = new ArrayList<>();
        this.f3916try = LazyKt.lazy(new C3376new());
        this.f3913else = new C2069if();
    }

    public final Pair<List<UsbDevice>, List<UsbDevice>> m1206do() {
        StringBuilder sb = new StringBuilder("enumerateAllUsbDevices():\n");
        Collection<UsbDevice> collectionValues = m1208if().getDeviceList().values();
        Intrinsics.checkNotNullExpressionValue(collectionValues, "usbManager.deviceList.values");
        ArrayList arrayList = new ArrayList();
        for (Object obj : collectionValues) {
            UsbDevice it = (UsbDevice) obj;
            Intrinsics.checkNotNullExpressionValue(it, "it");
            boolean zM1207do = m1207do(it);
            sb.append(it.getProductName() + " | " + it.getDeviceName() + " isHPDevice = " + zM1207do + '\n');
            if (zM1207do) {
                arrayList.add(obj);
            }
        }
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        for (Object obj2 : arrayList) {
            UsbDevice usbDevice = (UsbDevice) obj2;
            boolean zHasPermission = m1208if().hasPermission(usbDevice);
            sb.append(usbDevice.getProductName() + " | " + usbDevice.getDeviceName() + " hasPermission = " + zHasPermission + '\n');
            if (zHasPermission) {
                arrayList2.add(obj2);
            } else {
                arrayList3.add(obj2);
            }
        }
        Pair<List<UsbDevice>, List<UsbDevice>> pair = new Pair<>(arrayList2, arrayList3);
        this.f2496if.m446d(sb.toString());
        return pair;
    }

    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean m1207do(android.hardware.usb.UsbDevice r6) {
        /*
            r5 = this;
            int r0 = r6.getVendorId()
            r1 = 1008(0x3f0, float:1.413E-42)
            r2 = 0
            if (r0 != r1) goto L49
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            int r1 = r6.getInterfaceCount()
            r3 = r2
        L13:
            if (r3 >= r1) goto L1f
            android.hardware.usb.UsbInterface r4 = r6.getInterface(r3)
            r0.add(r4)
            int r3 = r3 + 1
            goto L13
        L1f:
            boolean r1 = r0.isEmpty()
            r3 = 1
            if (r1 == 0) goto L27
            goto L45
        L27:
            java.util.Iterator r0 = r0.iterator()
        L2b:
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto L45
            java.lang.Object r1 = r0.next()
            android.hardware.usb.UsbInterface r1 = (android.hardware.usb.UsbInterface) r1
            int r1 = r1.getInterfaceClass()
            r4 = 7
            if (r1 != r4) goto L40
            r1 = r3
            goto L41
        L40:
            r1 = r2
        L41:
            if (r1 == 0) goto L2b
            r0 = r3
            goto L46
        L45:
            r0 = r2
        L46:
            if (r0 == 0) goto L49
            r2 = r3
        L49:
            com.hp.mobile.common.utils.Logger r0 = r5.f2496if
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r3 = "checkIfHpDevice("
            r1.<init>(r3)
            java.lang.String r6 = r6.getDeviceName()
            java.lang.StringBuilder r6 = r1.append(r6)
            java.lang.String r1 = "): "
            java.lang.StringBuilder r6 = r6.append(r1)
            java.lang.StringBuilder r6 = r6.append(r2)
            java.lang.String r6 = r6.toString()
            r0.invoke(r6)
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: p066do.p026do.p028if.p029do.p068case.Cnew.m1207do(android.hardware.usb.UsbDevice):boolean");
    }

    public final Ccase m1755for() {
        return (Ccase) this.f3916try.getValue();
    }

    public final UsbManager m1208if() {
        return (UsbManager) this.f3914for.getValue();
    }

    public final void m1209if(UsbDevice usbDevice) {
        this.f2496if.invoke("proceedWithPermission() " + (usbDevice != null ? usbDevice.getProductName() : null) + " | " + (usbDevice != null ? usbDevice.getDeviceName() : null));
        if (usbDevice != null) {
            this.f3915new.add(usbDevice);
            InterfaceC2068do interfaceC2068do = this.f3912case;
            if (interfaceC2068do != null) {
                interfaceC2068do.mo444do(usbDevice, true);
            }
        }
    }

    public final void m1756new() {
        Pair<List<UsbDevice>, List<UsbDevice>> pairM1206do = m1206do();
        this.f2496if.invoke("processAllUsbDevices() " + pairM1206do.getFirst().size() + " devices with permission, " + pairM1206do.getSecond().size() + " devices with NO permission");
        for (UsbDevice usbDevice : pairM1206do.getSecond()) {
            this.f2496if.invoke("processAllUsbDevices() put in permission queue " + usbDevice.getProductName() + " | " + usbDevice.getDeviceName() + ' ');
            m1755for().m1199do(usbDevice);
        }
        for (UsbDevice usbDevice2 : pairM1206do.getFirst()) {
            this.f2496if.invoke("processAllUsbDevices() proceed with permission " + usbDevice2.getProductName() + " | " + usbDevice2.getDeviceName());
            m1209if(usbDevice2);
        }
    }
}
