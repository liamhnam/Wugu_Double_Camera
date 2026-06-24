package p000a.p001a.p002a.p003a;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.hardware.usb.UsbDevice;
import jp.p036co.dnp.photoprintlib.DNPPhotoPrint;

public class C0004c extends BroadcastReceiver {

    public final DNPPhotoPrint f44a;

    public C0004c(DNPPhotoPrint dNPPhotoPrint) {
        this.f44a = dNPPhotoPrint;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        UsbDevice usbDevice;
        C0002a c0002a;
        if (!intent.getAction().equals("android.hardware.usb.action.USB_DEVICE_DETACHED") || (c0002a = this.f44a.f2686d.get((usbDevice = (UsbDevice) intent.getParcelableExtra("device")))) == null) {
            return;
        }
        this.f44a.f2686d.remove(usbDevice);
        c0002a.m4a();
    }
}
