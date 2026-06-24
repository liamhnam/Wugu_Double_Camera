package p066do.p026do.p028if.p029do.p068case;

import android.hardware.usb.UsbInterface;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

public final class Cgoto extends Lambda implements Function0<String> {

    public final UsbInterface f2493if;

    public Cgoto(UsbInterface usbInterface) {
        super(0);
        this.f2493if = usbInterface;
    }

    @Override
    public String invoke() {
        return "Found a usbInterface(" + this.f2493if.hashCode() + ')';
    }
}
