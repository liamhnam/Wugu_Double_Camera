package com.wugu.doublecamera.utils;

public class BsPatchUtil {
    public static native void bsPatch(String str, String str2, String str3);

    static {
        System.loadLibrary("native-lib");
    }
}
