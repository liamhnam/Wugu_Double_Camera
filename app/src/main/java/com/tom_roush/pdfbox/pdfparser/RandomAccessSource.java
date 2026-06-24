package com.tom_roush.pdfbox.pdfparser;

import com.tom_roush.pdfbox.p022io.RandomAccessRead;
import java.io.IOException;

final class RandomAccessSource implements SequentialSource {
    private final RandomAccessRead reader;

    RandomAccessSource(RandomAccessRead randomAccessRead) {
        this.reader = randomAccessRead;
    }

    @Override
    public int read() throws IOException {
        return this.reader.read();
    }

    @Override
    public int read(byte[] bArr) throws IOException {
        return this.reader.read(bArr);
    }

    @Override
    public int read(byte[] bArr, int i, int i2) throws IOException {
        return this.reader.read(bArr, i, i2);
    }

    @Override
    public long getPosition() throws IOException {
        return this.reader.getPosition();
    }

    @Override
    public int peek() throws IOException {
        return this.reader.peek();
    }

    @Override
    public void unread(int i) throws IOException {
        this.reader.rewind(1);
    }

    @Override
    public void unread(byte[] bArr) throws IOException {
        this.reader.rewind(bArr.length);
    }

    @Override
    public byte[] readFully(int i) throws IOException {
        return this.reader.readFully(i);
    }

    @Override
    public boolean isEOF() throws IOException {
        return this.reader.isEOF();
    }

    @Override
    public void close() throws IOException {
        this.reader.close();
    }
}
