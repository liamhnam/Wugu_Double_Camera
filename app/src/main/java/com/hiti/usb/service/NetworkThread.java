package com.hiti.usb.service;

import android.content.Context;
import android.util.Log;
import com.hiti.usb.jni.JniData;
import com.hiti.usb.jni.UsbCommand;
import com.hiti.usb.service.network.FirmwareLoader;
import com.hiti.usb.service.network.INet;
import com.hiti.usb.service.network.NetworkLogin;
import com.hiti.usb.service.network.Uploader;
import hiti.p035a.EnumC2094b;
import java.util.HashMap;
import p000a.p001a.p002a.p005c.AbstractRunnableC0008a;
import p000a.p001a.p002a.p006d.C0015f;

class NetworkThread extends AbstractRunnableC0008a {
    private static final boolean localLOG = true;
    private static final String tag = "NetworkThread";
    private INet.IUpload listener = null;
    private PrinterService service;

    static class C15921 {
        static final int[] $SwitchMap$com$hiti$usb$service$network$INet$Result;

        static {
            int[] iArr = new int[INet.Result.values().length];
            $SwitchMap$com$hiti$usb$service$network$INet$Result = iArr;
            try {
                iArr[INet.Result.Uploaded.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$hiti$usb$service$network$INet$Result[INet.Result.VerifyFail.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    class LFirmwareLoader extends FirmwareLoader {
        public LFirmwareLoader(Context context, EnumC2094b enumC2094b) {
            super(context, enumC2094b);
        }

        @Override
        public void AfterCheckFirmwareDone(String str, String str2) {
            Log.v(NetworkThread.tag, "AfterCheckFirmwareDone(): version= " + str + " ,binFile= " + str2);
            if (NetworkThread.this.listener != null) {
                NetworkThread.this.listener.downloadFirmwareDone(NetworkThread.this.service, str, str2);
            }
        }
    }

    class LUploader extends Uploader {
        public LUploader(Context context, EnumC2094b enumC2094b) {
            super(context, enumC2094b);
        }

        @Override
        public boolean beforeUpload() {
            if (this.productId == null) {
                Log.v(NetworkThread.tag, "beforeUpload return");
                return false;
            }
            C0015f c0015f = new C0015f(NetworkThread.this.service.getApplicationContext(), this.productId.name());
            c0015f.m70f();
            JniData jniDataCallJniUsbCommand = NetworkThread.this.service.usbPrinter.callJniUsbCommand(UsbCommand.Function.HITI_GET_DEVICE_INFO, UsbCommand.SubFunc.HITI_DEVINFO_MFG_SERIAL, null);
            if (jniDataCallJniUsbCommand != null && !ErrorCode.HITI_ERROR(jniDataCallJniUsbCommand.getErrorCode())) {
                c0015f.m65a((String) jniDataCallJniUsbCommand.getRetData());
            }
            JniData jniDataCallJniUsbCommand2 = NetworkThread.this.service.usbPrinter.callJniUsbCommand(UsbCommand.Function.HITI_GET_DEVICE_INFO, UsbCommand.SubFunc.HITI_DEVINFO_PRINT_COUNT, null);
            if (jniDataCallJniUsbCommand2 != null && !ErrorCode.HITI_ERROR(jniDataCallJniUsbCommand2.getErrorCode())) {
                JniData.IntArray intArray = (JniData.IntArray) jniDataCallJniUsbCommand2.getRetData();
                HashMap<String, String> map = new HashMap<>();
                map.put("4x6", String.valueOf(intArray.get(1)));
                map.put("5x7", String.valueOf(intArray.get(2)));
                map.put("6x8", String.valueOf(intArray.get(3)));
                c0015f.m66a(map);
            }
            c0015f.m71g();
            return true;
        }

        @Override
        public void uploaded(INet.Result result, String str) {
            if (NetworkThread.this.listener != null) {
                int i = C15921.$SwitchMap$com$hiti$usb$service$network$INet$Result[result.ordinal()];
                if (i == 1) {
                    NetworkThread.this.listener.uploadDataDone(str);
                } else {
                    if (i != 2) {
                        return;
                    }
                    NetworkThread.this.listener.connectFail(result);
                }
            }
        }
    }

    public NetworkThread(PrinterService printerService) {
        this.service = printerService;
        NetworkLogin.FakeLogin(printerService.getApplicationContext());
    }

    @Override
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void run() {
        /*
            Method dump skipped, instruction units count: 289
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.hiti.usb.service.NetworkThread.run():void");
    }

    public void setListener(INet.IUpload iUpload) {
        this.listener = iUpload;
    }
}
