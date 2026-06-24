package com.liulishuo.okdownload;

import android.os.SystemClock;
import com.liulishuo.okdownload.core.Util;

public class SpeedCalculator {
    long allIncreaseBytes;
    long beginTimestamp;
    long bytesPerSecond;
    long endTimestamp;
    long increaseBytes;
    long timestamp;

    public void reset() {
        this.timestamp = 0L;
        this.increaseBytes = 0L;
        this.bytesPerSecond = 0L;
        this.beginTimestamp = 0L;
        this.endTimestamp = 0L;
        this.allIncreaseBytes = 0L;
    }

    long nowMillis() {
        return SystemClock.uptimeMillis();
    }

    public synchronized void downloading(long j) {
        if (this.timestamp == 0) {
            long jNowMillis = nowMillis();
            this.timestamp = jNowMillis;
            this.beginTimestamp = jNowMillis;
        }
        this.increaseBytes += j;
        this.allIncreaseBytes += j;
    }

    public synchronized void flush() {
        long jNowMillis = nowMillis();
        long j = this.increaseBytes;
        long jMax = Math.max(1L, jNowMillis - this.timestamp);
        this.increaseBytes = 0L;
        this.timestamp = jNowMillis;
        this.bytesPerSecond = (long) ((j / jMax) * 1000.0f);
    }

    public long getInstantBytesPerSecondAndFlush() {
        flush();
        return this.bytesPerSecond;
    }

    public long getBytesPerSecondAndFlush() {
        long jNowMillis = nowMillis() - this.timestamp;
        if (jNowMillis < 1000) {
            long j = this.bytesPerSecond;
            if (j != 0) {
                return j;
            }
        }
        if (this.bytesPerSecond != 0 || jNowMillis >= 500) {
            return getInstantBytesPerSecondAndFlush();
        }
        return 0L;
    }

    public synchronized long getBytesPerSecondFromBegin() {
        long jNowMillis;
        jNowMillis = this.endTimestamp;
        if (jNowMillis == 0) {
            jNowMillis = nowMillis();
        }
        return (long) ((this.allIncreaseBytes / Math.max(1L, jNowMillis - this.beginTimestamp)) * 1000.0f);
    }

    public void endTask() {
        this.endTimestamp = nowMillis();
    }

    public String instantSpeed() {
        return getSpeedWithSIAndFlush();
    }

    public String speed() {
        return humanReadableSpeed(getBytesPerSecondAndFlush(), true);
    }

    public String lastSpeed() {
        return humanReadableSpeed(this.bytesPerSecond, true);
    }

    public long getInstantSpeedDurationMillis() {
        return nowMillis() - this.timestamp;
    }

    public String getSpeedWithBinaryAndFlush() {
        return humanReadableSpeed(getInstantBytesPerSecondAndFlush(), false);
    }

    public String getSpeedWithSIAndFlush() {
        return humanReadableSpeed(getInstantBytesPerSecondAndFlush(), true);
    }

    public String averageSpeed() {
        return speedFromBegin();
    }

    public String speedFromBegin() {
        return humanReadableSpeed(getBytesPerSecondFromBegin(), true);
    }

    private static String humanReadableSpeed(long j, boolean z) {
        return Util.humanReadableBytes(j, z) + "/s";
    }
}
