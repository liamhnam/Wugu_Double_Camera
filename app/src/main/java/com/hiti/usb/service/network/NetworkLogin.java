package com.hiti.usb.service.network;

import android.content.Context;
import android.util.Log;
import com.hiti.jni.hello.C1588a;
import com.hiti.usb.utility.UserInfo;

public class NetworkLogin {
    private static final boolean localLOG = false;
    private static final String tag = "NetworkLogin";

    public static void FakeLogin(Context context) {
        String strM402b = C1588a.m402b(context, 1014);
        String strM401a = C1588a.m401a(context, 1014);
        boolean z = localLOG;
        if (z) {
            Log.e(tag, "getPackageName: " + String.valueOf(context.getPackageName()));
        }
        if (z) {
            Log.e(tag, "FakeLogin-strU: " + String.valueOf(strM402b));
        }
        if (z) {
            Log.e(tag, "strP: " + String.valueOf(strM401a));
        }
        UserInfo.FakeUserLogin(context, strM402b, strM401a);
    }
}
