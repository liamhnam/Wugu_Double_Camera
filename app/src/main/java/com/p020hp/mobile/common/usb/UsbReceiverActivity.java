package com.p020hp.mobile.common.usb;

import android.app.Activity;
import android.content.Intent;
import android.hardware.usb.UsbDevice;
import android.os.Build;
import android.os.Bundle;
import com.p020hp.mobile.common.utils.Logger;
import com.p020hp.mobile.common.utils.LoggerKt;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(m1292d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0014J\u0017\u0010\t\u001a\u0004\u0018\u00010\n2\u0006\u0010\u000b\u001a\u00020\fH\u0000¢\u0006\u0002\b\rR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000e"}, m1293d2 = {"Lcom/hp/mobile/common/usb/UsbReceiverActivity;", "Landroid/app/Activity;", "()V", "log", "Lcom/hp/mobile/common/utils/Logger;", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "startUsbBgService", "Landroid/content/ComponentName;", "it", "Landroid/hardware/usb/UsbDevice;", "startUsbBgService$common_lib_release", "common-lib_release"}, m1294k = 1, m1295mv = {1, 6, 0}, m1297xi = 48)
public final class UsbReceiverActivity extends Activity {

    public final Logger f772if = LoggerKt.logger(LoggerKt.toTag("UsbReceiverActivity"));

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Object parcelableExtra;
        UsbDevice it;
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= 33) {
            Intent intent = getIntent();
            if (intent != null) {
                parcelableExtra = intent.getParcelableExtra("device", UsbDevice.class);
                it = (UsbDevice) parcelableExtra;
            }
            it = null;
        } else {
            Intent intent2 = getIntent();
            if (intent2 != null) {
                parcelableExtra = intent2.getParcelableExtra("device");
                it = (UsbDevice) parcelableExtra;
            }
            it = null;
        }
        if (it != null) {
            Intrinsics.checkNotNullParameter(it, "it");
            this.f772if.m449i("usb receiver activity received an intent including " + it);
            Intent intent3 = new Intent(this, (Class<?>) UsbBgService.class);
            intent3.putExtra("device", it);
            intent3.setPackage("com.hp.mobile");
            startService(intent3);
        }
        finish();
    }
}
