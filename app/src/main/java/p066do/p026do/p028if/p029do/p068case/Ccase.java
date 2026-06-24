package p066do.p026do.p028if.p029do.p068case;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbManager;
import android.os.Build;
import com.faceunity.wrapper.faceunity;
import com.p020hp.mobile.common.utils.Logger;
import com.p020hp.mobile.common.utils.LoggerKt;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

public final class Ccase {

    public UsbDevice f3894case;

    public final Context f2483do;

    public final Logger f3895for;

    public final Function1<UsbDevice, Unit> f2484if;

    public C2062do f3896new;

    public final Queue<UsbDevice> f3897try;

    public final class C2062do extends BroadcastReceiver {
        public C2062do() {
        }

        @Override
        public void onReceive(Context context, Intent intent) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(intent, "intent");
            String action = intent.getAction();
            Ccase.this.f3895for.m446d("onReceive intent: " + action + ' ');
            if (Intrinsics.areEqual("USB_PERMISSION", action)) {
                UsbDevice usbDevice = (UsbDevice) (Build.VERSION.SDK_INT >= 33 ? intent.getParcelableExtra("device", UsbDevice.class) : intent.getParcelableExtra("device"));
                if (!intent.getBooleanExtra("permission", false)) {
                    Ccase.this.f3895for.m446d("onReceive() - DENIED for " + Ccase.this.m1200if(usbDevice) + ". calling next()");
                    Ccase.m1197do(Ccase.this, usbDevice, false);
                } else if (usbDevice != null) {
                    Ccase.this.f2484if.invoke(usbDevice);
                    Ccase.this.f3895for.m446d("onReceive() - GRANTED for " + Ccase.this.m1200if(usbDevice) + ". calling next() ");
                    Ccase.m1197do(Ccase.this, usbDevice, true);
                }
            }
        }
    }

    public Ccase(Context context, Function1<? super UsbDevice, Unit> listener) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.f2483do = context;
        this.f2484if = listener;
        this.f3895for = LoggerKt.logger(LoggerKt.toTag("UsbPermissionQueue"));
        this.f3897try = new LinkedList();
    }

    public static final void m1197do(Ccase ccase, UsbDevice usbDevice, boolean z) {
        UsbDevice usbDevice2;
        ccase.f3895for.m446d("next() - begin");
        ccase.f3895for.m446d("next() - processed " + ccase.m1200if(usbDevice) + " isGranted = " + z);
        if (usbDevice == null && (usbDevice2 = ccase.f3894case) != null) {
            ccase.f3894case = null;
            Object systemService = ccase.f2483do.getSystemService("usb");
            if (systemService == null) {
                throw new NullPointerException("null cannot be cast to non-null type android.hardware.usb.UsbManager");
            }
            if (((UsbManager) systemService).hasPermission(usbDevice2)) {
                ccase.f2484if.invoke(usbDevice2);
            }
        } else if (ccase.f3894case == null) {
            return;
        }
        ccase.f3895for.m446d("next() - remove head");
        ccase.f3897try.poll();
        UsbDevice usbDevicePeek = ccase.f3897try.peek();
        if (usbDevicePeek != null) {
            ccase.f3895for.invoke("next() - next device " + ccase.m1200if(usbDevicePeek));
            ccase.m1753for(usbDevicePeek);
        } else {
            ccase.f3895for.invoke("next() - Queue is empty.");
        }
        ccase.m1198do();
        ccase.f3895for.m446d("next() - end");
    }

    public final void m1198do() {
        StringBuilder sb = new StringBuilder("\n====PERMISSION QUEUE STATE===========\n");
        Iterator<UsbDevice> it = this.f3897try.iterator();
        while (it.hasNext()) {
            sb.append(m1200if(it.next())).append("\n");
        }
        sb.append("=====================================\n");
        this.f3895for.m446d("sb: " + ((Object) sb));
    }

    public final void m1199do(UsbDevice device) {
        Intrinsics.checkNotNullParameter(device, "device");
        String strM1200if = m1200if(device);
        this.f3895for.m446d("add() - Try to add " + strM1200if);
        m1198do();
        if (this.f3897try.contains(device)) {
            this.f3895for.invoke("add() - " + strM1200if + " already in the queue. In queue = " + this.f3897try.size());
            return;
        }
        this.f3897try.add(device);
        this.f3895for.invoke("add() - added " + strM1200if + ". In queue = " + this.f3897try.size());
        m1198do();
        if (this.f3897try.size() == 1) {
            m1753for(device);
        }
    }

    public final void m1753for(UsbDevice device) {
        Intrinsics.checkNotNullParameter(device, "device");
        this.f3895for.invoke("requestPermission() - for " + m1200if(device));
        this.f3894case = device;
        Object systemService = this.f2483do.getSystemService("usb");
        if (systemService == null) {
            throw new NullPointerException("null cannot be cast to non-null type android.hardware.usb.UsbManager");
        }
        ((UsbManager) systemService).requestPermission(device, PendingIntent.getBroadcast(this.f2483do, 0, new Intent("USB_PERMISSION"), Build.VERSION.SDK_INT >= 34 ? 50331648 : faceunity.FUAITYPE_FACEPROCESSOR_EMOTION_RECOGNIZER));
    }

    public final String m1200if(UsbDevice usbDevice) {
        StringBuilder sb = new StringBuilder();
        if (usbDevice == null) {
            sb.append("NULL [NULL]");
        } else {
            sb.append(usbDevice.getProductName()).append(" [").append(usbDevice.getDeviceName()).append("]");
        }
        String string = sb.toString();
        Intrinsics.checkNotNullExpressionValue(string, "sb.toString()");
        return string;
    }
}
