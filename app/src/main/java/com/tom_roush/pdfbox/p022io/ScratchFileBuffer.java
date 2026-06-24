package com.tom_roush.pdfbox.p022io;

import android.util.Log;
import java.io.EOFException;
import java.io.IOException;
import kotlin.UByte;

class ScratchFileBuffer implements RandomAccess {
    private byte[] currentPage;
    private long currentPageOffset;
    private int currentPagePositionInPageIndexes;
    private ScratchFile pageHandler;
    private final int pageSize;
    private int positionInPage;
    private long size = 0;
    private boolean currentPageContentChanged = false;
    private int[] pageIndexes = new int[16];
    private int pageCount = 0;

    ScratchFileBuffer(ScratchFile scratchFile) throws IOException {
        scratchFile.checkClosed();
        this.pageHandler = scratchFile;
        this.pageSize = scratchFile.getPageSize();
        addPage();
    }

    private void checkClosed() throws IOException {
        ScratchFile scratchFile = this.pageHandler;
        if (scratchFile == null) {
            throw new IOException("Buffer already closed");
        }
        scratchFile.checkClosed();
    }

    private void addPage() throws IOException {
        int i = this.pageCount;
        int i2 = i + 1;
        int[] iArr = this.pageIndexes;
        if (i2 >= iArr.length) {
            int length = iArr.length * 2;
            if (length < iArr.length) {
                if (iArr.length == Integer.MAX_VALUE) {
                    throw new IOException("Maximum buffer size reached.");
                }
                length = Integer.MAX_VALUE;
            }
            int[] iArr2 = new int[length];
            System.arraycopy(iArr, 0, iArr2, 0, i);
            this.pageIndexes = iArr2;
        }
        int newPage = this.pageHandler.getNewPage();
        int[] iArr3 = this.pageIndexes;
        int i3 = this.pageCount;
        iArr3[i3] = newPage;
        this.currentPagePositionInPageIndexes = i3;
        int i4 = this.pageSize;
        this.currentPageOffset = ((long) i3) * ((long) i4);
        this.pageCount = i3 + 1;
        this.currentPage = new byte[i4];
        this.positionInPage = 0;
    }

    @Override
    public long length() throws IOException {
        return this.size;
    }

    private boolean ensureAvailableBytesInPage(boolean z) throws IOException {
        if (this.positionInPage >= this.pageSize) {
            if (this.currentPageContentChanged) {
                this.pageHandler.writePage(this.pageIndexes[this.currentPagePositionInPageIndexes], this.currentPage);
                this.currentPageContentChanged = false;
            }
            int i = this.currentPagePositionInPageIndexes;
            if (i + 1 < this.pageCount) {
                ScratchFile scratchFile = this.pageHandler;
                int[] iArr = this.pageIndexes;
                int i2 = i + 1;
                this.currentPagePositionInPageIndexes = i2;
                this.currentPage = scratchFile.readPage(iArr[i2]);
                this.currentPageOffset = ((long) this.currentPagePositionInPageIndexes) * ((long) this.pageSize);
                this.positionInPage = 0;
            } else {
                if (!z) {
                    return false;
                }
                addPage();
            }
        }
        return true;
    }

    @Override
    public void write(int i) throws IOException {
        checkClosed();
        ensureAvailableBytesInPage(true);
        byte[] bArr = this.currentPage;
        int i2 = this.positionInPage;
        int i3 = i2 + 1;
        this.positionInPage = i3;
        bArr[i2] = (byte) i;
        this.currentPageContentChanged = true;
        long j = this.currentPageOffset;
        if (((long) i3) + j > this.size) {
            this.size = j + ((long) i3);
        }
    }

    @Override
    public void write(byte[] bArr) throws IOException {
        write(bArr, 0, bArr.length);
    }

    @Override
    public void write(byte[] bArr, int i, int i2) throws IOException {
        checkClosed();
        while (i2 > 0) {
            ensureAvailableBytesInPage(true);
            int iMin = Math.min(i2, this.pageSize - this.positionInPage);
            System.arraycopy(bArr, i, this.currentPage, this.positionInPage, iMin);
            this.positionInPage += iMin;
            this.currentPageContentChanged = true;
            i += iMin;
            i2 -= iMin;
        }
        long j = this.currentPageOffset;
        int i3 = this.positionInPage;
        if (((long) i3) + j > this.size) {
            this.size = j + ((long) i3);
        }
    }

    @Override
    public final void clear() throws IOException {
        checkClosed();
        this.pageHandler.markPagesAsFree(this.pageIndexes, 1, this.pageCount - 1);
        this.pageCount = 1;
        if (this.currentPagePositionInPageIndexes > 0) {
            this.currentPage = this.pageHandler.readPage(this.pageIndexes[0]);
            this.currentPagePositionInPageIndexes = 0;
            this.currentPageOffset = 0L;
        }
        this.positionInPage = 0;
        this.size = 0L;
        this.currentPageContentChanged = false;
    }

    @Override
    public long getPosition() throws IOException {
        checkClosed();
        return this.currentPageOffset + ((long) this.positionInPage);
    }

    @Override
    public void seek(long j) throws IOException {
        checkClosed();
        if (j > this.size) {
            throw new EOFException();
        }
        if (j < 0) {
            throw new IOException("Negative seek offset: " + j);
        }
        long j2 = this.currentPageOffset;
        if (j >= j2 && j <= ((long) this.pageSize) + j2) {
            this.positionInPage = (int) (j - j2);
            return;
        }
        if (this.currentPageContentChanged) {
            this.pageHandler.writePage(this.pageIndexes[this.currentPagePositionInPageIndexes], this.currentPage);
            this.currentPageContentChanged = false;
        }
        int i = (int) (j / ((long) this.pageSize));
        this.currentPage = this.pageHandler.readPage(this.pageIndexes[i]);
        this.currentPagePositionInPageIndexes = i;
        long j3 = ((long) i) * ((long) this.pageSize);
        this.currentPageOffset = j3;
        this.positionInPage = (int) (j - j3);
    }

    @Override
    public boolean isClosed() {
        return this.pageHandler == null;
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
        seek((this.currentPageOffset + ((long) this.positionInPage)) - ((long) i));
    }

    @Override
    public byte[] readFully(int i) throws IOException {
        byte[] bArr = new byte[i];
        int i2 = 0;
        do {
            int i3 = read(bArr, i2, i - i2);
            if (i3 < 0) {
                throw new EOFException();
            }
            i2 += i3;
        } while (i2 < i);
        return bArr;
    }

    @Override
    public boolean isEOF() throws IOException {
        checkClosed();
        return this.currentPageOffset + ((long) this.positionInPage) >= this.size;
    }

    @Override
    public int available() throws IOException {
        checkClosed();
        return (int) Math.min(this.size - (this.currentPageOffset + ((long) this.positionInPage)), 2147483647L);
    }

    @Override
    public int read() throws IOException {
        checkClosed();
        if (this.currentPageOffset + ((long) this.positionInPage) >= this.size) {
            return -1;
        }
        if (!ensureAvailableBytesInPage(false)) {
            throw new IOException("Unexpectedly no bytes available for read in buffer.");
        }
        byte[] bArr = this.currentPage;
        int i = this.positionInPage;
        this.positionInPage = i + 1;
        return bArr[i] & UByte.MAX_VALUE;
    }

    @Override
    public int read(byte[] bArr) throws IOException {
        return read(bArr, 0, bArr.length);
    }

    @Override
    public int read(byte[] bArr, int i, int i2) throws IOException {
        checkClosed();
        long j = this.currentPageOffset;
        int i3 = this.positionInPage;
        long j2 = ((long) i3) + j;
        long j3 = this.size;
        if (j2 >= j3) {
            return -1;
        }
        int iMin = (int) Math.min(i2, j3 - (j + ((long) i3)));
        int i4 = 0;
        while (iMin > 0) {
            if (!ensureAvailableBytesInPage(false)) {
                throw new IOException("Unexpectedly no bytes available for read in buffer.");
            }
            int iMin2 = Math.min(iMin, this.pageSize - this.positionInPage);
            System.arraycopy(this.currentPage, this.positionInPage, bArr, i, iMin2);
            this.positionInPage += iMin2;
            i4 += iMin2;
            i += iMin2;
            iMin -= iMin2;
        }
        return i4;
    }

    @Override
    public void close() throws IOException {
        ScratchFile scratchFile = this.pageHandler;
        if (scratchFile != null) {
            scratchFile.markPagesAsFree(this.pageIndexes, 0, this.pageCount);
            this.pageHandler = null;
            this.pageIndexes = null;
            this.currentPage = null;
            this.currentPageOffset = 0L;
            this.currentPagePositionInPageIndexes = -1;
            this.positionInPage = 0;
            this.size = 0L;
        }
    }

    protected void finalize() throws Throwable {
        try {
            if (this.pageHandler != null) {
                Log.d("PdfBox-Android", "ScratchFileBuffer not closed!");
            }
            close();
        } finally {
            super.finalize();
        }
    }
}
