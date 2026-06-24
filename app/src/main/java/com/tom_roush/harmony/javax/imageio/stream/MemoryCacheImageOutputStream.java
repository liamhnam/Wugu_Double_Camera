package com.tom_roush.harmony.javax.imageio.stream;

import java.io.IOException;
import java.io.OutputStream;

public class MemoryCacheImageOutputStream extends ImageOutputStreamImpl {

    OutputStream f2227os;
    RandomAccessMemoryCache ramc = new RandomAccessMemoryCache();

    @Override
    public boolean isCached() {
        return true;
    }

    @Override
    public boolean isCachedFile() {
        return false;
    }

    @Override
    public boolean isCachedMemory() {
        return true;
    }

    public MemoryCacheImageOutputStream(OutputStream outputStream) {
        if (outputStream == null) {
            throw new IllegalArgumentException("stream == null!");
        }
        this.f2227os = outputStream;
    }

    @Override
    public void write(int i) throws IOException {
        flushBits();
        this.ramc.putData(i, this.streamPos);
        this.streamPos++;
    }

    @Override
    public void write(byte[] bArr, int i, int i2) throws IOException {
        flushBits();
        this.ramc.putData(bArr, i, i2, this.streamPos);
        this.streamPos += (long) i2;
    }

    @Override
    public int read() throws IOException {
        this.bitOffset = 0;
        int data = this.ramc.getData(this.streamPos);
        if (data >= 0) {
            this.streamPos++;
        }
        return data;
    }

    @Override
    public int read(byte[] bArr, int i, int i2) throws IOException {
        this.bitOffset = 0;
        int data = this.ramc.getData(bArr, i, i2, this.streamPos);
        if (data > 0) {
            this.streamPos += (long) data;
        }
        return data;
    }

    @Override
    public long length() {
        return this.ramc.length();
    }

    @Override
    public void close() throws IOException {
        long length = length();
        seek(length);
        flushBefore(length);
        super.close();
        this.ramc.close();
    }

    @Override
    public void flushBefore(long j) throws IOException {
        long flushedPosition = getFlushedPosition();
        super.flushBefore(j);
        long flushedPosition2 = getFlushedPosition();
        this.ramc.getData(this.f2227os, (int) (flushedPosition2 - flushedPosition), flushedPosition);
        this.ramc.freeBefore(flushedPosition2);
        this.f2227os.flush();
    }
}
