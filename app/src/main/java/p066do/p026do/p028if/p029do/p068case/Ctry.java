package p066do.p026do.p028if.p029do.p068case;

import android.hardware.usb.UsbDevice;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

public final class Ctry extends Lambda implements Function1<UsbDevice, Unit> {

    public final Cnew f2500if;

    public Ctry(Cnew cnew) {
        super(1);
        this.f2500if = cnew;
    }

    @Override
    public Unit invoke(UsbDevice usbDevice) {
        UsbDevice usbDevice2 = usbDevice;
        Intrinsics.checkNotNullParameter(usbDevice2, "usbDevice");
        this.f2500if.m1209if(usbDevice2);
        return Unit.INSTANCE;
    }
}
