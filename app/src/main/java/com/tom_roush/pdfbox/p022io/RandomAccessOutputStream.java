package com.tom_roush.pdfbox.p022io;

import java.io.IOException;
import java.io.OutputStream;

public class RandomAccessOutputStream extends OutputStream {
    private final RandomAccessWrite writer;

    public RandomAccessOutputStream(RandomAccessWrite randomAccessWrite) {
        this.writer = randomAccessWrite;
    }

    @Override
    public void write(byte[] bArr, int i, int i2) throws IOException {
        this.writer.write(bArr, i, i2);
    }

    @Override
    public void write(byte[] bArr) throws IOException {
        this.writer.write(bArr);
    }

    @Override
    public void write(int i) throws IOException {
        this.writer.write(i);
    }
}
