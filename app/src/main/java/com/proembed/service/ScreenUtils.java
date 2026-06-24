package com.proembed.service;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.WindowManager;
import java.io.File;

public class ScreenUtils {
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static void rotateScreen(java.lang.String r5) throws java.lang.Throwable {
        /*
            java.lang.String r0 = "setprop persist.sys.displayrot "
            r1 = 0
            java.lang.Runtime r2 = java.lang.Runtime.getRuntime()     // Catch: java.lang.Throwable -> L6c java.lang.Exception -> L70
            java.lang.String r3 = "su"
            java.lang.Process r2 = r2.exec(r3)     // Catch: java.lang.Throwable -> L6c java.lang.Exception -> L70
            java.io.DataOutputStream r3 = new java.io.DataOutputStream     // Catch: java.lang.Throwable -> L66 java.lang.Exception -> L69
            java.io.OutputStream r4 = r2.getOutputStream()     // Catch: java.lang.Throwable -> L66 java.lang.Exception -> L69
            r3.<init>(r4)     // Catch: java.lang.Throwable -> L66 java.lang.Exception -> L69
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L5e java.lang.Exception -> L62
            r4.<init>(r0)     // Catch: java.lang.Throwable -> L5e java.lang.Exception -> L62
            java.lang.StringBuilder r5 = r4.append(r5)     // Catch: java.lang.Throwable -> L5e java.lang.Exception -> L62
            java.lang.String r0 = " \n"
            java.lang.StringBuilder r5 = r5.append(r0)     // Catch: java.lang.Throwable -> L5e java.lang.Exception -> L62
            java.lang.String r5 = r5.toString()     // Catch: java.lang.Throwable -> L5e java.lang.Exception -> L62
            r3.writeBytes(r5)     // Catch: java.lang.Throwable -> L5e java.lang.Exception -> L62
            java.lang.String r5 = "exit\n"
            r3.writeBytes(r5)     // Catch: java.lang.Throwable -> L5e java.lang.Exception -> L62
            r3.flush()     // Catch: java.lang.Throwable -> L5e java.lang.Exception -> L62
            r2.waitFor()     // Catch: java.lang.Throwable -> L5e java.lang.Exception -> L62
            java.io.DataInputStream r5 = new java.io.DataInputStream     // Catch: java.lang.Throwable -> L5e java.lang.Exception -> L62
            java.io.InputStream r0 = r2.getInputStream()     // Catch: java.lang.Throwable -> L5e java.lang.Exception -> L62
            r5.<init>(r0)     // Catch: java.lang.Throwable -> L5e java.lang.Exception -> L62
            int r0 = r5.available()     // Catch: java.lang.Throwable -> L5a java.lang.Exception -> L5c
            byte[] r0 = new byte[r0]     // Catch: java.lang.Throwable -> L5a java.lang.Exception -> L5c
            r5.read(r0)     // Catch: java.lang.Throwable -> L5a java.lang.Exception -> L5c
            java.lang.String r1 = new java.lang.String     // Catch: java.lang.Throwable -> L5a java.lang.Exception -> L5c
            r1.<init>(r0)     // Catch: java.lang.Throwable -> L5a java.lang.Exception -> L5c
            r3.close()     // Catch: java.lang.Exception -> L7e
        L53:
            r5.close()     // Catch: java.lang.Exception -> L7e
        L56:
            r2.destroy()     // Catch: java.lang.Exception -> L7e
            goto L7e
        L5a:
            r0 = move-exception
            goto L60
        L5c:
            r0 = move-exception
            goto L64
        L5e:
            r0 = move-exception
            r5 = r1
        L60:
            r1 = r3
            goto L80
        L62:
            r0 = move-exception
            r5 = r1
        L64:
            r1 = r3
            goto L73
        L66:
            r0 = move-exception
            r5 = r1
            goto L80
        L69:
            r0 = move-exception
            r5 = r1
            goto L73
        L6c:
            r0 = move-exception
            r5 = r1
            r2 = r5
            goto L80
        L70:
            r0 = move-exception
            r5 = r1
            r2 = r5
        L73:
            r0.printStackTrace()     // Catch: java.lang.Throwable -> L7f
            if (r1 == 0) goto L7b
            r1.close()     // Catch: java.lang.Exception -> L7e
        L7b:
            if (r5 == 0) goto L56
            goto L53
        L7e:
            return
        L7f:
            r0 = move-exception
        L80:
            if (r1 == 0) goto L85
            r1.close()     // Catch: java.lang.Exception -> L8d
        L85:
            if (r5 == 0) goto L8a
            r5.close()     // Catch: java.lang.Exception -> L8d
        L8a:
            r2.destroy()     // Catch: java.lang.Exception -> L8d
        L8d:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.proembed.service.ScreenUtils.rotateScreen(java.lang.String):void");
    }

    public static void rotationScreen(String str, String str2) throws Throwable {
        Utils.setValueToProp("persist.sys.displayrot", str2);
        Log.i("yuanhang", Utils.getValueFromProp("persist.sys.displayrot"));
        int i = Integer.parseInt(str2);
        File file = new File(str);
        if (file.exists()) {
            GPIOUtils.writeStringFileFor7(file, (i / 90) + "");
        }
    }

    public static void rotationScreen(Context context, int i) {
        Log.d("HHHHH", "rotationScreen");
        Intent intent = new Intent();
        intent.setAction(Constant.ROTATION_ACTION);
        intent.putExtra(Constant.ROTATION_INDEX, i);
        context.sendBroadcast(intent);
    }

    public static int getDisplayWith(Context context) {
        WindowManager windowManager = ((Activity) context).getWindowManager();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.widthPixels;
    }

    public static int getDisplayHeight(Context context) {
        WindowManager windowManager = ((Activity) context).getWindowManager();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.heightPixels;
    }
}
