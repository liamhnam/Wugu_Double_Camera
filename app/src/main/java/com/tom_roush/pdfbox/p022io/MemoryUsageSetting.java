package com.tom_roush.pdfbox.p022io;

import java.io.File;

public final class MemoryUsageSetting {
    private final long maxMainMemoryBytes;
    private final long maxStorageBytes;
    private File tempDir;
    private final boolean useMainMemory;
    private final boolean useTempFile;

    private MemoryUsageSetting(boolean z, boolean z2, long j, long j2) {
        boolean z3 = z2 ? z : true;
        j = z ? j : -1L;
        j2 = j2 <= 0 ? -1L : j2;
        j = j < -1 ? -1L : j;
        if (z3 && j == 0) {
            if (z2) {
                z3 = false;
            } else {
                j = j2;
            }
        }
        if (z3 && j2 > -1 && (j == -1 || j > j2)) {
            j2 = j;
        }
        this.useMainMemory = z3;
        this.useTempFile = z2;
        this.maxMainMemoryBytes = j;
        this.maxStorageBytes = j2;
    }

    public static MemoryUsageSetting setupMainMemoryOnly() {
        return setupMainMemoryOnly(-1L);
    }

    public static MemoryUsageSetting setupMainMemoryOnly(long j) {
        return new MemoryUsageSetting(true, false, j, j);
    }

    public static MemoryUsageSetting setupTempFileOnly() {
        return setupTempFileOnly(-1L);
    }

    public static MemoryUsageSetting setupTempFileOnly(long j) {
        return new MemoryUsageSetting(false, true, 0L, j);
    }

    public static MemoryUsageSetting setupMixed(long j) {
        return setupMixed(j, -1L);
    }

    public MemoryUsageSetting getPartitionedCopy(int i) {
        long j = this.maxMainMemoryBytes;
        if (j > 0) {
            j /= (long) i;
        }
        long j2 = j;
        long j3 = this.maxStorageBytes;
        if (j3 > 0) {
            j3 /= (long) i;
        }
        MemoryUsageSetting memoryUsageSetting = new MemoryUsageSetting(this.useMainMemory, this.useTempFile, j2, j3);
        memoryUsageSetting.tempDir = this.tempDir;
        return memoryUsageSetting;
    }

    public static MemoryUsageSetting setupMixed(long j, long j2) {
        return new MemoryUsageSetting(true, true, j, j2);
    }

    public MemoryUsageSetting setTempDir(File file) {
        this.tempDir = file;
        return this;
    }

    public boolean useMainMemory() {
        return this.useMainMemory;
    }

    public boolean useTempFile() {
        return this.useTempFile;
    }

    public boolean isMainMemoryRestricted() {
        return this.maxMainMemoryBytes >= 0;
    }

    public boolean isStorageRestricted() {
        return this.maxStorageBytes > 0;
    }

    public long getMaxMainMemoryBytes() {
        return this.maxMainMemoryBytes;
    }

    public long getMaxStorageBytes() {
        return this.maxStorageBytes;
    }

    public File getTempDir() {
        return this.tempDir;
    }

    public String toString() {
        if (!this.useMainMemory) {
            return isStorageRestricted() ? "Scratch file only with max. of " + this.maxStorageBytes + " bytes" : "Scratch file only with no size restriction";
        }
        if (this.useTempFile) {
            return "Mixed mode with max. of " + this.maxMainMemoryBytes + " main memory bytes" + (isStorageRestricted() ? " and max. of " + this.maxStorageBytes + " storage bytes" : " and unrestricted scratch file size");
        }
        return isMainMemoryRestricted() ? "Main memory only with max. of " + this.maxMainMemoryBytes + " bytes" : "Main memory only with no size restriction";
    }
}
