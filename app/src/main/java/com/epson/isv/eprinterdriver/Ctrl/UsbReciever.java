package com.epson.isv.eprinterdriver.Ctrl;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.hardware.usb.UsbDevice;
import com.epson.isv.eprinterdriver.Common.CommonDefine;
import com.epson.isv.eprinterdriver.Common.EPLog;
import java.util.Iterator;

public class UsbReciever extends Activity {
    public static final String ACTION_USB_PERMISSION = "com.epson.otg.USB_PERMISSION";
    private static UsbPermissionReceiverCallback permissionGrantedcallback;

    public interface UsbPermissionReceiverCallback {
        void permissionGranted(Context context, boolean z);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Intent intent = getIntent();
        String action = intent.getAction();
        if ("android.hardware.usb.action.USB_DEVICE_ATTACHED".equals(action)) {
            EPLog.logInfo("Tagandroid.hardware.usb.action.USB_DEVICE_ATTACHED");
            UsbDevice usbDevice = (UsbDevice) intent.getParcelableExtra("device");
            if (usbDevice != null) {
                Iterator<UsbPrinter> it = UsbPrintDriver.getInstance(this).findPrinters(false, CommonDefine.EPSON_VENDERID).iterator();
                while (it.hasNext()) {
                    if (it.next().getUsbDevice().getDeviceId() == usbDevice.getDeviceId()) {
                        EPLog.logInfo("TagEPSON Printer ATTACHED");
                        getPermission(usbDevice, new UsbPermissionReceiverCallback() {
                            @Override
                            public void permissionGranted(Context context, boolean z) {
                                if (z) {
                                    String className = context.getPackageManager().getLaunchIntentForPackage(context.getPackageName()).getComponent().getClassName();
                                    Intent intent2 = new Intent("android.intent.action.MAIN");
                                    intent2.setFlags(268435456);
                                    intent2.addCategory("android.intent.category.LAUNCHER");
                                    intent2.setClassName(context, className);
                                    try {
                                        UsbReciever.this.startActivity(intent2);
                                        EPLog.logInfo("EPSONUsbReciever ACTION_MAIN");
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }
                                EPLog.logInfo("EPSONUsbReciever finish()");
                                UsbReciever.this.finish();
                            }
                        });
                        return;
                    }
                }
                return;
            }
            return;
        }
        if (ACTION_USB_PERMISSION.equals(action)) {
            synchronized (this) {
                UsbDevice usbDevice2 = (UsbDevice) intent.getParcelableExtra("device");
                if (intent.getBooleanExtra("permission", false) && usbDevice2 != null) {
                    EPLog.logInfo("EPSONHAS PERMISSION");
                    UsbPermissionReceiverCallback usbPermissionReceiverCallback = permissionGrantedcallback;
                    if (usbPermissionReceiverCallback != null) {
                        usbPermissionReceiverCallback.permissionGranted(this, true);
                    }
                } else {
                    EPLog.logInfo("EPSONpermission denied for device");
                    UsbPermissionReceiverCallback usbPermissionReceiverCallback2 = permissionGrantedcallback;
                    if (usbPermissionReceiverCallback2 != null) {
                        usbPermissionReceiverCallback2.permissionGranted(this, false);
                    }
                }
                permissionGrantedcallback = null;
            }
        }
    }

    public static class UsbReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            if ("android.hardware.usb.action.USB_DEVICE_DETACHED".equals(intent.getAction())) {
                EPLog.logInfo("Tagandroid.hardware.usb.action.USB_DEVICE_DETACHED");
                UsbDevice usbDevice = (UsbDevice) intent.getParcelableExtra("device");
                if (usbDevice != null) {
                    UsbPrintDriver.getInstance(context).deletePrinterPermmited(usbDevice);
                }
            }
        }
    }

    public boolean getPermission(UsbDevice usbDevice, UsbPermissionReceiverCallback usbPermissionReceiverCallback) {
        boolean zHasPermission = UsbPrintDriver.getInstance(this).getUsbManager().hasPermission(usbDevice);
        if (!zHasPermission) {
            UsbPrintDriver.getInstance(this).getUsbManager().requestPermission(usbDevice, PendingIntent.getBroadcast(this, 0, new Intent(ACTION_USB_PERMISSION), 0));
            permissionGrantedcallback = usbPermissionReceiverCallback;
        } else {
            EPLog.logInfo("EPSONHAS PERMISSION");
            if (usbPermissionReceiverCallback != null) {
                usbPermissionReceiverCallback.permissionGranted(this, true);
            }
        }
        return zHasPermission;
    }
}
