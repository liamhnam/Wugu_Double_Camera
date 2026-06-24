package com.tom_roush.pdfbox.p022io;

import com.tom_roush.pdfbox.pdmodel.common.PDPageLabelRange;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.UByte;

public class RandomAccessBufferedFileInputStream extends InputStream implements RandomAccessRead {
    private static final String TMP_FILE_PREFIX = "tmpPDFBox";
    private byte[] curPage;
    private long curPageOffset;
    private final long fileLength;
    private long fileOffset;
    private boolean isClosed;
    private byte[] lastRemovedCachePage;
    private int maxCachedPages;
    private int offsetWithinPage;
    private final Map<Long, byte[]> pageCache;
    private long pageOffsetMask;
    private int pageSize;
    private int pageSizeShift;
    private final RandomAccessFile raFile;
    private File tempFile;

    public RandomAccessBufferedFileInputStream(String str) throws IOException {
        this(new File(str));
    }

    public RandomAccessBufferedFileInputStream(File file) throws IOException {
        this.pageSizeShift = 12;
        this.pageSize = 1 << 12;
        this.pageOffsetMask = (-1) << 12;
        this.maxCachedPages = 1000;
        this.lastRemovedCachePage = null;
        this.pageCache = new LinkedHashMap<Long, byte[]>(this.maxCachedPages, 0.75f, true) {
            private static final long serialVersionUID = -6302488539257741101L;

            @Override
            protected boolean removeEldestEntry(Map.Entry<Long, byte[]> entry) {
                boolean z = size() > RandomAccessBufferedFileInputStream.this.maxCachedPages;
                if (z) {
                    RandomAccessBufferedFileInputStream.this.lastRemovedCachePage = entry.getValue();
                }
                return z;
            }
        };
        this.curPageOffset = -1L;
        this.curPage = new byte[this.pageSize];
        this.offsetWithinPage = 0;
        this.fileOffset = 0L;
        this.raFile = new RandomAccessFile(file, PDPageLabelRange.STYLE_ROMAN_LOWER);
        this.fileLength = file.length();
        seek(0L);
    }

    public RandomAccessBufferedFileInputStream(InputStream inputStream) throws Throwable {
        this.pageSizeShift = 12;
        this.pageSize = 1 << 12;
        this.pageOffsetMask = (-1) << 12;
        this.maxCachedPages = 1000;
        this.lastRemovedCachePage = null;
        this.pageCache = new LinkedHashMap<Long, byte[]>(this.maxCachedPages, 0.75f, true) {
            private static final long serialVersionUID = -6302488539257741101L;

            @Override
            protected boolean removeEldestEntry(Map.Entry<Long, byte[]> entry) {
                boolean z = size() > RandomAccessBufferedFileInputStream.this.maxCachedPages;
                if (z) {
                    RandomAccessBufferedFileInputStream.this.lastRemovedCachePage = entry.getValue();
                }
                return z;
            }
        };
        this.curPageOffset = -1L;
        this.curPage = new byte[this.pageSize];
        this.offsetWithinPage = 0;
        this.fileOffset = 0L;
        File fileCreateTmpFile = createTmpFile(inputStream);
        this.tempFile = fileCreateTmpFile;
        this.fileLength = fileCreateTmpFile.length();
        this.raFile = new RandomAccessFile(this.tempFile, PDPageLabelRange.STYLE_ROMAN_LOWER);
        seek(0L);
    }

    private File createTmpFile(InputStream inputStream) throws Throwable {
        FileOutputStream fileOutputStream;
        Throwable th;
        try {
            File fileCreateTempFile = File.createTempFile(TMP_FILE_PREFIX, ".pdf");
            fileOutputStream = new FileOutputStream(fileCreateTempFile);
            try {
                IOUtils.copy(inputStream, fileOutputStream);
                IOUtils.closeQuietly(inputStream);
                IOUtils.closeQuietly(fileOutputStream);
                return fileCreateTempFile;
            } catch (Throwable th2) {
                th = th2;
                IOUtils.closeQuietly(inputStream);
                IOUtils.closeQuietly(fileOutputStream);
                throw th;
            }
        } catch (Throwable th3) {
            fileOutputStream = null;
            th = th3;
        }
    }

    private void deleteTempFile() {
        File file = this.tempFile;
        if (file != null) {
            file.delete();
        }
    }

    @Override
    public long getPosition() {
        return this.fileOffset;
    }

    @Override
    public void seek(long j) throws IOException {
        long j2 = this.pageOffsetMask & j;
        if (j2 != this.curPageOffset) {
            byte[] page = this.pageCache.get(Long.valueOf(j2));
            if (page == null) {
                this.raFile.seek(j2);
                page = readPage();
                this.pageCache.put(Long.valueOf(j2), page);
            }
            this.curPageOffset = j2;
            this.curPage = page;
        }
        this.offsetWithinPage = (int) (j - this.curPageOffset);
        this.fileOffset = j;
    }

    private byte[] readPage() throws IOException {
        int i;
        byte[] bArr = this.lastRemovedCachePage;
        if (bArr != null) {
            this.lastRemovedCachePage = null;
        } else {
            bArr = new byte[this.pageSize];
        }
        int i2 = 0;
        while (true) {
            int i3 = this.pageSize;
            if (i2 >= i3 || (i = this.raFile.read(bArr, i2, i3 - i2)) < 0) {
                break;
            }
            i2 += i;
        }
        return bArr;
    }

    @Override
    public int read() throws IOException {
        long j = this.fileOffset;
        if (j >= this.fileLength) {
            return -1;
        }
        if (this.offsetWithinPage == this.pageSize) {
            seek(j);
        }
        this.fileOffset++;
        byte[] bArr = this.curPage;
        int i = this.offsetWithinPage;
        this.offsetWithinPage = i + 1;
        return bArr[i] & UByte.MAX_VALUE;
    }

    @Override
    public int read(byte[] bArr) throws IOException {
        return read(bArr, 0, bArr.length);
    }

    @Override
    public int read(byte[] bArr, int i, int i2) throws IOException {
        long j = this.fileOffset;
        if (j >= this.fileLength) {
            return -1;
        }
        if (this.offsetWithinPage == this.pageSize) {
            seek(j);
        }
        int iMin = Math.min(this.pageSize - this.offsetWithinPage, i2);
        long j2 = this.fileLength;
        long j3 = this.fileOffset;
        if (j2 - j3 < this.pageSize) {
            iMin = Math.min(iMin, (int) (j2 - j3));
        }
        System.arraycopy(this.curPage, this.offsetWithinPage, bArr, i, iMin);
        this.offsetWithinPage += iMin;
        this.fileOffset += (long) iMin;
        return iMin;
    }

    @Override
    public int available() throws IOException {
        return (int) Math.min(this.fileLength - this.fileOffset, 2147483647L);
    }

    @Override
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public long skip(long r9) throws java.io.IOException {
        /*
            r8 = this;
            long r0 = r8.fileLength
            long r2 = r8.fileOffset
            long r4 = r0 - r2
            int r4 = (r4 > r9 ? 1 : (r4 == r9 ? 0 : -1))
            if (r4 >= 0) goto Lc
            long r9 = r0 - r2
        Lc:
            int r0 = r8.pageSize
            long r4 = (long) r0
            int r1 = (r9 > r4 ? 1 : (r9 == r4 ? 0 : -1))
            if (r1 >= 0) goto L25
            int r1 = r8.offsetWithinPage
            long r4 = (long) r1
            long r4 = r4 + r9
            long r6 = (long) r0
            int r0 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r0 > 0) goto L25
            long r0 = (long) r1
            long r0 = r0 + r9
            int r0 = (int) r0
            r8.offsetWithinPage = r0
            long r2 = r2 + r9
            r8.fileOffset = r2
            goto L29
        L25:
            long r2 = r2 + r9
            r8.seek(r2)
        L29:
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tom_roush.pdfbox.p022io.RandomAccessBufferedFileInputStream.skip(long):long");
    }

    @Override
    public long length() throws IOException {
        return this.fileLength;
    }

    @Override
    public void close() throws IOException {
        this.raFile.close();
        deleteTempFile();
        this.pageCache.clear();
        this.isClosed = true;
    }

    @Override
    public boolean isClosed() {
        return this.isClosed;
    }

    @Override
    public int peek() throws IOException {
        int i = read();
        if (i != -1) {
            rewind(1);
        }
        return i;
    }

    @Override
    public void rewind(int i) throws IOException {
        seek(getPosition() - ((long) i));
    }

    @Override
    public byte[] readFully(int i) throws IOException {
        byte[] bArr = new byte[i];
        int i2 = read(bArr);
        while (i2 < i) {
            i2 += read(bArr, i2, i - i2);
        }
        return bArr;
    }

    @Override
    public boolean isEOF() throws IOException {
        return peek() == -1;
    }
}
