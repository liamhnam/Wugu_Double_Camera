package com.brother.sdk.common.util;

public class MemoryUtil {
    private static final int SIZE_1KB = 1024;
    private static final int SIZE_1MB = 1048576;

    public static boolean mayProcessImage(double d, double d2, double d3, double d4) {
        try {
            Runtime runtime = Runtime.getRuntime();
            return ((double) (runtime.totalMemory() - runtime.freeMemory())) / 1048576.0d > (((d2 * d) * d3) / 1048576.0d) + d4;
        } catch (Exception unused) {
            return false;
        }
    }
}
