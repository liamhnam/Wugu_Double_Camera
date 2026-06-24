package com.faceunity.core.utils;

public class DecimalUtils {
    private static final float THRESHOLD = 0.001f;

    private DecimalUtils() {
    }

    public static boolean floatEquals(float f, float f2) {
        return Math.abs(f - f2) < THRESHOLD;
    }

    public static boolean doubleEquals(double d, double d2) {
        return Math.abs(d - d2) < 0.0010000000474974513d;
    }

    public static boolean floatArrayEquals(float[] fArr, float[] fArr2) {
        if (fArr == null && fArr2 == null) {
            return true;
        }
        if (fArr == null || fArr2 == null || fArr.length != fArr2.length) {
            return false;
        }
        for (int i = 0; i < fArr.length; i++) {
            if (!floatEquals(fArr[i], fArr2[i])) {
                return false;
            }
        }
        return true;
    }

    public static boolean doubleArrayEquals(double[] dArr, double[] dArr2) {
        if (dArr == null && dArr2 == null) {
            return true;
        }
        if (dArr == null || dArr2 == null || dArr.length != dArr2.length) {
            return false;
        }
        for (int i = 0; i < dArr.length; i++) {
            if (!doubleEquals(dArr[i], dArr2[i])) {
                return false;
            }
        }
        return true;
    }

    public static float[] copyArray(float[] fArr) {
        float[] fArr2 = new float[fArr.length];
        System.arraycopy(fArr, 0, fArr2, 0, fArr.length);
        return fArr2;
    }

    public static byte[] copyArray(byte[] bArr) {
        byte[] bArr2 = new byte[bArr.length];
        System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
        return bArr2;
    }
}
