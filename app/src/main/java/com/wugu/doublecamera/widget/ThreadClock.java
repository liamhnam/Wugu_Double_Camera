package com.wugu.doublecamera.widget;

import android.os.SystemClock;

public final class ThreadClock {
    private ThreadClock() {
    }

    private static void sleep(long j, boolean z) {
        long jUptimeMillis = uptimeMillis();
        boolean z2 = false;
        long jUptimeMillis2 = j;
        do {
            try {
                Thread.sleep(jUptimeMillis2);
            } catch (InterruptedException unused) {
                z2 = true;
            }
            jUptimeMillis2 = (jUptimeMillis + j) - uptimeMillis();
            if (!z) {
                break;
            }
        } while (jUptimeMillis2 > 0);
        if (z2) {
            Thread.currentThread().interrupt();
        }
    }

    public static void sleep(long j) {
        sleep(j, false);
    }

    public static void sleepUntil(long j) {
        sleep(j, true);
    }

    private static long uptimeMillis() {
        return SystemClock.uptimeMillis();
    }
}
