package com.brother.sdk.jni.print.image;

public class NativePrintImageUtil {
    public static native int[] calculateSizeOfImageFile(String str);

    public static native int compressImageFileWithMode1030(String str, String str2, int i);

    public static native int[] createImageFileFilledWithImageRect(String str, String str2, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8);

    public static native int rotateImageFile(String str, int i, int i2, String str2, int i3);

    public static native int scaleImageFile(String str, int i, int i2, String str2);

    static {
        System.loadLibrary("nativeprint");
    }
}
