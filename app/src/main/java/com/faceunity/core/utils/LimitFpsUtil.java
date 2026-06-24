package com.faceunity.core.utils;

import android.os.SystemClock;

public final class LimitFpsUtil {
    public static final int DEFAULT_FPS = 30;
    private static final String TAG = "KIT_LimitFpsUtil";
    private static long expectedFrameTimeMs = 33;
    private static long frameStartTimeMs;
    private static long startTimeMs;

    private LimitFpsUtil() {
    }

    public static void setTargetFps(int i) {
        expectedFrameTimeMs = i > 0 ? 1000 / i : 0L;
        frameStartTimeMs = 0L;
        startTimeMs = 0L;
    }

    public static void limitFrameRate() {
        long jElapsedRealtime = expectedFrameTimeMs - (SystemClock.elapsedRealtime() - frameStartTimeMs);
        if (jElapsedRealtime > 0) {
            SystemClock.sleep(jElapsedRealtime);
        }
        frameStartTimeMs = SystemClock.elapsedRealtime();
    }

    public static double averageFrameRate(int i) {
        double dElapsedRealtime = (((double) i) * 1000.0d) / (SystemClock.elapsedRealtime() - startTimeMs);
        startTimeMs = SystemClock.elapsedRealtime();
        return dElapsedRealtime;
    }
}
