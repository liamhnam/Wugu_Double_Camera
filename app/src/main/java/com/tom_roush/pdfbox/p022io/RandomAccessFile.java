package com.tom_roush.pdfbox.p022io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class RandomAccessFile implements RandomAccess {
    private boolean isClosed;
    private final java.io.RandomAccessFile ras;

    public RandomAccessFile(File file, String str) throws FileNotFoundException {
        this.ras = new java.io.RandomAccessFile(file, str);
    }

    @Override
    public void close() throws IOException {
        this.ras.close();
        this.isClosed = true;
    }

    @Override
    public void clear() throws IOException {
        checkClosed();
        this.ras.seek(0L);
        this.ras.setLength(0L);
    }

    @Override
    public void seek(long j) throws IOException {
        checkClosed();
        this.ras.seek(j);
    }

    @Override
    public long getPosition() throws IOException {
        checkClosed();
        return this.ras.getFilePointer();
    }

    @Override
    public int read() throws IOException {
        return this.ras.read();
    }

    @Override
    public int read(byte[] bArr) throws IOException {
        checkClosed();
        return this.ras.read(bArr);
    }

    @Override
    public int read(byte[] bArr, int i, int i2) throws IOException {
        checkClosed();
        return this.ras.read(bArr, i, i2);
    }

    @Override
    public long length() throws IOException {
        checkClosed();
        return this.ras.length();
    }

    private void checkClosed() throws IOException {
        if (this.isClosed) {
            throw new IOException("RandomAccessFile already closed");
        }
    }

    @Override
    public boolean isClosed() {
        return this.isClosed;
    }

    @Override
    public void write(byte[] bArr, int i, int i2) throws IOException {
        checkClosed();
        this.ras.write(bArr, i, i2);
    }

    @Override
    public void write(byte[] bArr) throws IOException {
        write(bArr, 0, bArr.length);
    }

    @Override
    public void write(int i) throws IOException {
        checkClosed();
        this.ras.write(i);
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
        checkClosed();
        java.io.RandomAccessFile randomAccessFile = this.ras;
        randomAccessFile.seek(randomAccessFile.getFilePointer() - ((long) i));
    }

    @Override
    public byte[] readFully(int i) throws IOException {
        checkClosed();
        byte[] bArr = new byte[i];
        this.ras.readFully(bArr);
        return bArr;
    }

    @Override
    public boolean isEOF() throws IOException {
        return peek() == -1;
    }

    @Override
    public int available() throws IOException {
        checkClosed();
        return (int) Math.min(this.ras.length() - getPosition(), 2147483647L);
    }
}
