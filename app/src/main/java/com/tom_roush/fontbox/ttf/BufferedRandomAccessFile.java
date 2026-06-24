package com.tom_roush.fontbox.ttf;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class BufferedRandomAccessFile extends RandomAccessFile {
    private final int BUFSIZE;
    private int bufend;
    private final byte[] buffer;
    private int bufpos;
    private long realpos;

    public BufferedRandomAccessFile(String str, String str2, int i) throws FileNotFoundException {
        super(str, str2);
        this.bufend = 0;
        this.bufpos = 0;
        this.realpos = 0L;
        this.BUFSIZE = i;
        this.buffer = new byte[i];
    }

    public BufferedRandomAccessFile(File file, String str, int i) throws FileNotFoundException {
        super(file, str);
        this.bufend = 0;
        this.bufpos = 0;
        this.realpos = 0L;
        this.BUFSIZE = i;
        this.buffer = new byte[i];
    }

    @Override
    public final int read() throws IOException {
        if ((this.bufpos >= this.bufend && fillBuffer() < 0) || this.bufend == 0) {
            return -1;
        }
        byte[] bArr = this.buffer;
        int i = this.bufpos;
        this.bufpos = i + 1;
        return (bArr[i] + 256) & 255;
    }

    private int fillBuffer() throws IOException {
        int i = super.read(this.buffer, 0, this.BUFSIZE);
        if (i >= 0) {
            this.realpos += (long) i;
            this.bufend = i;
            this.bufpos = 0;
        }
        return i;
    }

    private void invalidate() throws IOException {
        this.bufend = 0;
        this.bufpos = 0;
        this.realpos = super.getFilePointer();
    }

    @Override
    public int read(byte[] bArr, int i, int i2) throws IOException {
        int i3;
        int i4 = this.bufend;
        int i5 = this.bufpos;
        int i6 = i4 - i5;
        if (i2 <= i6) {
            System.arraycopy(this.buffer, i5, bArr, i, i2);
            this.bufpos += i2;
            return i2;
        }
        System.arraycopy(this.buffer, i5, bArr, i, i6);
        this.bufpos += i6;
        return (fillBuffer() <= 0 || (i3 = read(bArr, i + i6, i2 - i6)) <= 0) ? i6 : i6 + i3;
    }

    @Override
    public long getFilePointer() throws IOException {
        return (this.realpos - ((long) this.bufend)) + ((long) this.bufpos);
    }

    @Override
    public void seek(long j) throws IOException {
        int i;
        int i2 = (int) (this.realpos - j);
        if (i2 >= 0 && i2 <= (i = this.bufend)) {
            this.bufpos = i - i2;
        } else {
            super.seek(j);
            invalidate();
        }
    }
}
