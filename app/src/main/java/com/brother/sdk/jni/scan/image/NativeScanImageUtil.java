package com.brother.sdk.jni.scan.image;

public class NativeScanImageUtil {
    public static final int IMAGE_IS_BLANK = 0;
    public static final int IMAGE_IS_NOBLANK = 1;
    public static final int NATIVE_PROCESS_FAILURE = 0;
    public static final int NATIVE_PROCESS_SUCCESS = 1;

    public static native int adjustImageWithBlankAndDeskewCheck(String str, String str2, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10, boolean z, boolean z2);

    public static native int convertJpegToBmp(String str, String str2, int i, int i2);

    static {
        System.loadLibrary("nativescan");
    }
}
