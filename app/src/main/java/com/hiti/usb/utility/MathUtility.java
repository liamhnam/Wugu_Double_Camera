package com.hiti.usb.utility;

public class MathUtility {
    public static float Diagonal(float f, float f2) {
        return (float) Math.sqrt((f * f) + (f2 * f2));
    }

    public static boolean IsZero(float f) {
        return new Float("0.0").compareTo(Float.valueOf(f)) == 0;
    }

    public static boolean IsZero(float f, float f2) {
        return ((double) (Math.abs(f - f2) / Math.max(Math.abs(f), Math.abs(f2)))) < 1.0E-6d;
    }

    public static float Spacing(float f, float f2, float f3, float f4) {
        float f5 = f - f3;
        float f6 = f2 - f4;
        return (float) Math.sqrt((f5 * f5) + (f6 * f6));
    }
}
