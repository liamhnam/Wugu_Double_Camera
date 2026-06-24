package com.proembed.service;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.util.Log;
import java.lang.reflect.InvocationTargetException;

public class NetUtils {
    public static String staticIP;

    public static void setEthMAC(String str) {
        try {
            Class<?> cls = Class.forName("android.os.Custom");
            cls.getDeclaredMethod("setMac", String.class).invoke(cls, str);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e2) {
            e2.printStackTrace();
        } catch (InvocationTargetException e3) {
            e3.printStackTrace();
        } catch (Exception e4) {
            e4.printStackTrace();
        }
    }

    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.lang.String getEthMAC() {
        /*
            java.lang.String r0 = ""
            r1 = 0
            java.lang.Runtime r2 = java.lang.Runtime.getRuntime()     // Catch: java.io.IOException -> L29
            java.lang.String r3 = "cat /sys/class/net/eth0/address "
            java.lang.Process r2 = r2.exec(r3)     // Catch: java.io.IOException -> L29
            java.io.InputStreamReader r3 = new java.io.InputStreamReader     // Catch: java.io.IOException -> L29
            java.io.InputStream r2 = r2.getInputStream()     // Catch: java.io.IOException -> L29
            r3.<init>(r2)     // Catch: java.io.IOException -> L29
            java.io.LineNumberReader r2 = new java.io.LineNumberReader     // Catch: java.io.IOException -> L29
            r2.<init>(r3)     // Catch: java.io.IOException -> L29
            r3 = r0
        L1c:
            if (r3 == 0) goto L2d
            java.lang.String r3 = r2.readLine()     // Catch: java.io.IOException -> L29
            if (r3 == 0) goto L1c
            java.lang.String r1 = r3.trim()     // Catch: java.io.IOException -> L29
            goto L2d
        L29:
            r2 = move-exception
            r2.printStackTrace()
        L2d:
            if (r1 == 0) goto L41
            boolean r2 = r1.equals(r0)
            if (r2 != 0) goto L41
            int r2 = r1.length()
            r3 = 17
            if (r2 != r3) goto L41
            java.lang.String r0 = r1.toUpperCase()
        L41:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.proembed.service.NetUtils.getEthMAC():java.lang.String");
    }

    public static boolean isValidMac(String str) {
        return (str == null || str.equals("") || !str.matches("([A-Fa-f0-9]{2}[-,:]){5}[A-Fa-f0-9]{2}")) ? false : true;
    }

    public static String getDynamicEthIPAddress(Context context) {
        return ((VersionUtils.getAndroidModle().equals("rk3288") && Build.VERSION.SDK.equals("25")) || (VersionUtils.getAndroidModle().equals("rk3368") && Build.VERSION.SDK.equals("25")) || ((VersionUtils.getAndroidModle().equals("rk3128") && Build.VERSION.SDK.equals("25")) || VersionUtils.getAndroidModle().equals("rk3399"))) ? Utils.getEthernet(context) : Utils.getValueFromProp(Constant.ETH_IP_ADDRESS_PROP);
    }

    public static void setStaticIP(Context context, String str, String str2, String str3, String str4, String str5) {
        Log.d("MyManager", "setStaticIP 发送修改IP广播");
        Intent intent = new Intent(Constant.ETH_STATIC_IP_ACTION);
        intent.setPackage("com.ys.ys_receiver");
        intent.putExtra("useStaticIP", 1);
        intent.putExtra(Constant.ETH_SET_IP, str);
        intent.putExtra(Constant.ETH_SET_GATEWAY, str2);
        intent.putExtra(Constant.ETH_SET_MASK, str3);
        intent.putExtra(Constant.ETH_DNS1, str4);
        intent.putExtra(Constant.ETH_DNS2, str5);
        context.sendBroadcast(intent);
    }

    public static int getNetWorkType(Context context) {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
        if (activeNetworkInfo == null || !activeNetworkInfo.isConnected()) {
            return -100;
        }
        return activeNetworkInfo.getType();
    }

    public static void setEthernetEnabled(Context context, boolean z) {
        Log.d("Ethernet", "发送Ethernet开关广播");
        Intent intent = new Intent(Constant.SET_ETH_ENABLE_ACTION);
        intent.putExtra(Constant.ETH_MODE, z);
        context.sendBroadcast(intent);
    }

    public static int getEthStatus() {
        try {
            Class<?> cls = Class.forName("android.net.EthernetManager");
            return ((Integer) cls.getDeclaredMethod("getEthernetIfaceState", String.class).invoke(cls, new Object[0])).intValue();
        } catch (Exception unused) {
            return -2;
        }
    }
}
