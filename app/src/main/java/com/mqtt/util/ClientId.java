package com.mqtt.util;

import android.os.Build;
import java.util.UUID;

public class ClientId {
    private static String CLIENT_ID;

    public static String m703id() {
        if (CLIENT_ID == null) {
            String str = "35" + (Build.BOARD.length() % 10) + (Build.BRAND.length() % 10) + (Build.CPU_ABI.length() % 10) + (Build.DEVICE.length() % 10) + (Build.DISPLAY.length() % 10) + (Build.HOST.length() % 10) + (Build.ID.length() % 10) + (Build.MANUFACTURER.length() % 10) + (Build.MODEL.length() % 10) + (Build.PRODUCT.length() % 10) + (Build.TAGS.length() % 10) + (Build.TYPE.length() % 10) + (Build.USER.length() % 10);
            try {
                CLIENT_ID = new UUID(str.hashCode(), Build.class.getField("SERIAL").get(null).toString().hashCode()).toString();
            } catch (Exception unused) {
                CLIENT_ID = new UUID(str.hashCode(), -905839116).toString();
            }
        }
        return CLIENT_ID;
    }
}
