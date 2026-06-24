package com.tom_roush.pdfbox.p022io;

import java.io.IOException;
import java.io.InputStream;

public class RandomAccessInputStream extends InputStream {
    private final RandomAccessRead input;
    private long position = 0;

    public RandomAccessInputStream(RandomAccessRead randomAccessRead) {
        this.input = randomAccessRead;
    }

    void restorePosition() throws IOException {
        this.input.seek(this.position);
    }

    @Override
    public int available() throws IOException {
        restorePosition();
        long length = this.input.length() - this.input.getPosition();
        if (length > 2147483647L) {
            return Integer.MAX_VALUE;
        }
        return (int) length;
    }

    @Override
    public int read() throws IOException {
        restorePosition();
        if (this.input.isEOF()) {
            return -1;
        }
        int i = this.input.read();
        this.position++;
        return i;
    }

    @Override
    public int read(byte[] bArr, int i, int i2) throws IOException {
        restorePosition();
        if (this.input.isEOF()) {
            return -1;
        }
        int i3 = this.input.read(bArr, i, i2);
        this.position += (long) i3;
        return i3;
    }

    @Override
    public long skip(long j) throws IOException {
        restorePosition();
        this.input.seek(this.position + j);
        this.position += j;
        return j;
    }
}
