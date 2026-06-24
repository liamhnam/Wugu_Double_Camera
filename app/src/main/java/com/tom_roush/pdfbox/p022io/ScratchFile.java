package com.tom_roush.pdfbox.p022io;

import android.util.Log;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.util.BitSet;

public class ScratchFile implements Closeable {
    private static final int ENLARGE_PAGE_COUNT = 16;
    private static final int INIT_UNRESTRICTED_MAINMEM_PAGECOUNT = 100000;
    private static final int PAGE_SIZE = 4096;
    private File file;
    private final BitSet freePages;
    private final int inMemoryMaxPageCount;
    private volatile byte[][] inMemoryPages;
    private final Object ioLock;
    private volatile boolean isClosed;
    private final boolean maxMainMemoryIsRestricted;
    private final int maxPageCount;
    private volatile int pageCount;
    private RandomAccessFile raf;
    private final File scratchFileDirectory;
    private final boolean useScratchFile;

    int getPageSize() {
        return 4096;
    }

    public ScratchFile(File file) throws IOException {
        this(MemoryUsageSetting.setupTempFileOnly().setTempDir(file));
    }

    public ScratchFile(MemoryUsageSetting memoryUsageSetting) throws IOException {
        this.ioLock = new Object();
        this.pageCount = 0;
        BitSet bitSet = new BitSet();
        this.freePages = bitSet;
        this.isClosed = false;
        boolean z = !memoryUsageSetting.useMainMemory() || memoryUsageSetting.isMainMemoryRestricted();
        this.maxMainMemoryIsRestricted = z;
        boolean zUseTempFile = z ? memoryUsageSetting.useTempFile() : false;
        this.useScratchFile = zUseTempFile;
        File tempDir = zUseTempFile ? memoryUsageSetting.getTempDir() : null;
        this.scratchFileDirectory = tempDir;
        if (tempDir != null && !tempDir.isDirectory()) {
            throw new IOException("Scratch file directory does not exist: " + tempDir);
        }
        int iMin = Integer.MAX_VALUE;
        this.maxPageCount = memoryUsageSetting.isStorageRestricted() ? (int) Math.min(2147483647L, memoryUsageSetting.getMaxStorageBytes() / 4096) : Integer.MAX_VALUE;
        if (!memoryUsageSetting.useMainMemory()) {
            iMin = 0;
        } else if (memoryUsageSetting.isMainMemoryRestricted()) {
            iMin = (int) Math.min(2147483647L, memoryUsageSetting.getMaxMainMemoryBytes() / 4096);
        }
        this.inMemoryMaxPageCount = iMin;
        this.inMemoryPages = new byte[z ? iMin : INIT_UNRESTRICTED_MAINMEM_PAGECOUNT][];
        bitSet.set(0, this.inMemoryPages.length);
    }

    public static ScratchFile getMainMemoryOnlyInstance() {
        try {
            return new ScratchFile(MemoryUsageSetting.setupMainMemoryOnly());
        } catch (IOException e) {
            Log.e("PdfBox-Android", "Unexpected exception occurred creating main memory scratch file instance: " + e.getMessage());
            return null;
        }
    }

    int getNewPage() throws IOException {
        int iNextSetBit;
        synchronized (this.freePages) {
            iNextSetBit = this.freePages.nextSetBit(0);
            if (iNextSetBit < 0) {
                enlarge();
                iNextSetBit = this.freePages.nextSetBit(0);
                if (iNextSetBit < 0) {
                    throw new IOException("Maximum allowed scratch file memory exceeded.");
                }
            }
            this.freePages.clear(iNextSetBit);
            if (iNextSetBit >= this.pageCount) {
                this.pageCount = iNextSetBit + 1;
            }
        }
        return iNextSetBit;
    }

    private void enlarge() throws IOException {
        synchronized (this.ioLock) {
            checkClosed();
            if (this.pageCount >= this.maxPageCount) {
                return;
            }
            if (this.useScratchFile) {
                if (this.raf == null) {
                    this.file = File.createTempFile("PDFBox", ".tmp", this.scratchFileDirectory);
                    try {
                        this.raf = new RandomAccessFile(this.file, "rw");
                    } catch (IOException e) {
                        if (!this.file.delete()) {
                            Log.w("PdfBox-Android", "Error deleting scratch file: " + this.file.getAbsolutePath());
                        }
                        throw e;
                    }
                }
                long length = this.raf.length();
                long j = (((long) this.pageCount) - ((long) this.inMemoryMaxPageCount)) * 4096;
                if (j != length) {
                    throw new IOException("Expected scratch file size of " + j + " but found " + length);
                }
                if (this.pageCount + 16 > this.pageCount) {
                    this.raf.setLength(length + 65536);
                    this.freePages.set(this.pageCount, this.pageCount + 16);
                }
            } else if (!this.maxMainMemoryIsRestricted) {
                int length2 = this.inMemoryPages.length;
                int iMin = (int) Math.min(((long) length2) * 2, 2147483647L);
                if (iMin > length2) {
                    byte[][] bArr = new byte[iMin][];
                    System.arraycopy(this.inMemoryPages, 0, bArr, 0, length2);
                    this.inMemoryPages = bArr;
                    this.freePages.set(length2, iMin);
                }
            }
        }
    }

    byte[] readPage(int i) throws IOException {
        byte[] bArr;
        if (i < 0 || i >= this.pageCount) {
            checkClosed();
            throw new IOException("Page index out of range: " + i + ". Max value: " + (this.pageCount - 1));
        }
        if (i < this.inMemoryMaxPageCount) {
            byte[] bArr2 = this.inMemoryPages[i];
            if (bArr2 != null) {
                return bArr2;
            }
            checkClosed();
            throw new IOException("Requested page with index " + i + " was not written before.");
        }
        synchronized (this.ioLock) {
            RandomAccessFile randomAccessFile = this.raf;
            if (randomAccessFile == null) {
                checkClosed();
                throw new IOException("Missing scratch file to read page with index " + i + " from.");
            }
            bArr = new byte[4096];
            randomAccessFile.seek((((long) i) - ((long) this.inMemoryMaxPageCount)) * 4096);
            this.raf.readFully(bArr);
        }
        return bArr;
    }

    void writePage(int i, byte[] bArr) throws IOException {
        if (i < 0 || i >= this.pageCount) {
            checkClosed();
            throw new IOException("Page index out of range: " + i + ". Max value: " + (this.pageCount - 1));
        }
        if (bArr.length != 4096) {
            throw new IOException("Wrong page size to write: " + bArr.length + ". Expected: 4096");
        }
        if (i < this.inMemoryMaxPageCount) {
            if (this.maxMainMemoryIsRestricted) {
                this.inMemoryPages[i] = bArr;
            } else {
                synchronized (this.ioLock) {
                    this.inMemoryPages[i] = bArr;
                }
            }
            checkClosed();
            return;
        }
        synchronized (this.ioLock) {
            checkClosed();
            this.raf.seek((((long) i) - ((long) this.inMemoryMaxPageCount)) * 4096);
            this.raf.write(bArr);
        }
    }

    void checkClosed() throws IOException {
        if (this.isClosed) {
            throw new IOException("Scratch file already closed");
        }
    }

    public RandomAccess createBuffer() throws IOException {
        return new ScratchFileBuffer(this);
    }

    public RandomAccess createBuffer(InputStream inputStream) throws IOException {
        ScratchFileBuffer scratchFileBuffer = new ScratchFileBuffer(this);
        byte[] bArr = new byte[8192];
        while (true) {
            int i = inputStream.read(bArr);
            if (i > -1) {
                scratchFileBuffer.write(bArr, 0, i);
            } else {
                scratchFileBuffer.seek(0L);
                return scratchFileBuffer;
            }
        }
    }

    void markPagesAsFree(int[] iArr, int i, int i2) {
        synchronized (this.freePages) {
            while (i < i2) {
                int i3 = iArr[i];
                if (i3 >= 0 && i3 < this.pageCount && !this.freePages.get(i3)) {
                    this.freePages.set(i3);
                    if (i3 < this.inMemoryMaxPageCount) {
                        this.inMemoryPages[i3] = null;
                    }
                }
                i++;
            }
        }
    }

    @Override
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void close() throws java.io.IOException {
        /*
            r4 = this;
            java.lang.String r0 = "Error deleting scratch file: "
            boolean r1 = r4.isClosed
            if (r1 == 0) goto L7
            return
        L7:
            r1 = 1
            r4.isClosed = r1
            java.lang.Object r1 = r4.ioLock
            monitor-enter(r1)
            java.io.RandomAccessFile r2 = r4.raf     // Catch: java.lang.Throwable -> L58
            if (r2 == 0) goto L17
            r2.close()     // Catch: java.io.IOException -> L15 java.lang.Throwable -> L58
            goto L17
        L15:
            r2 = move-exception
            goto L18
        L17:
            r2 = 0
        L18:
            java.io.File r3 = r4.file     // Catch: java.lang.Throwable -> L58
            if (r3 == 0) goto L44
            boolean r3 = r3.delete()     // Catch: java.lang.Throwable -> L58
            if (r3 != 0) goto L44
            java.io.File r3 = r4.file     // Catch: java.lang.Throwable -> L58
            boolean r3 = r3.exists()     // Catch: java.lang.Throwable -> L58
            if (r3 == 0) goto L44
            if (r2 != 0) goto L44
            java.io.IOException r2 = new java.io.IOException     // Catch: java.lang.Throwable -> L58
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L58
            r3.<init>(r0)     // Catch: java.lang.Throwable -> L58
            java.io.File r0 = r4.file     // Catch: java.lang.Throwable -> L58
            java.lang.String r0 = r0.getAbsolutePath()     // Catch: java.lang.Throwable -> L58
            java.lang.StringBuilder r0 = r3.append(r0)     // Catch: java.lang.Throwable -> L58
            java.lang.String r0 = r0.toString()     // Catch: java.lang.Throwable -> L58
            r2.<init>(r0)     // Catch: java.lang.Throwable -> L58
        L44:
            java.util.BitSet r0 = r4.freePages     // Catch: java.lang.Throwable -> L58
            monitor-enter(r0)     // Catch: java.lang.Throwable -> L58
            java.util.BitSet r3 = r4.freePages     // Catch: java.lang.Throwable -> L55
            r3.clear()     // Catch: java.lang.Throwable -> L55
            r3 = 0
            r4.pageCount = r3     // Catch: java.lang.Throwable -> L55
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L55
            if (r2 != 0) goto L54
            monitor-exit(r1)     // Catch: java.lang.Throwable -> L58
            return
        L54:
            throw r2     // Catch: java.lang.Throwable -> L58
        L55:
            r2 = move-exception
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L55
            throw r2     // Catch: java.lang.Throwable -> L58
        L58:
            r0 = move-exception
            monitor-exit(r1)     // Catch: java.lang.Throwable -> L58
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tom_roush.pdfbox.p022io.ScratchFile.close():void");
    }
}
