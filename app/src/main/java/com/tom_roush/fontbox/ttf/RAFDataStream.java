package com.tom_roush.fontbox.ttf;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;

class RAFDataStream extends TTFDataStream {
    private static final int BUFFERSIZE = 16834;
    private RandomAccessFile raf;
    private File ttfFile;

    RAFDataStream(String str, String str2) throws IOException {
        this(new File(str), str2);
    }

    RAFDataStream(File file, String str) throws IOException {
        this.raf = null;
        this.ttfFile = null;
        this.raf = new BufferedRandomAccessFile(file, str, BUFFERSIZE);
        this.ttfFile = file;
    }

    @Override
    public short readSignedShort() throws IOException {
        return this.raf.readShort();
    }

    @Override
    public long getCurrentPosition() throws IOException {
        return this.raf.getFilePointer();
    }

    @Override
    public void close() throws IOException {
        this.raf.close();
        this.raf = null;
    }

    @Override
    public int read() throws IOException {
        return this.raf.read();
    }

    @Override
    public int readUnsignedShort() throws IOException {
        return this.raf.readUnsignedShort();
    }

    @Override
    public long readLong() throws IOException {
        return this.raf.readLong();
    }

    @Override
    public void seek(long j) throws IOException {
        this.raf.seek(j);
    }

    @Override
    public int read(byte[] bArr, int i, int i2) throws IOException {
        return this.raf.read(bArr, i, i2);
    }

    @Override
    public InputStream getOriginalData() throws IOException {
        return new FileInputStream(this.ttfFile);
    }
}
