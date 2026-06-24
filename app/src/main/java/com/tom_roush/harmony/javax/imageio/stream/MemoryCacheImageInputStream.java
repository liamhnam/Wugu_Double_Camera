package com.tom_roush.harmony.javax.imageio.stream;

import java.io.IOException;
import java.io.InputStream;

public class MemoryCacheImageInputStream extends ImageInputStreamImpl {

    private InputStream f2226is;
    private RandomAccessMemoryCache ramc = new RandomAccessMemoryCache();

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

    public MemoryCacheImageInputStream(InputStream inputStream) {
        if (inputStream == null) {
            throw new IllegalArgumentException("stream == null!");
        }
        this.f2226is = inputStream;
    }

    @Override
    public int read() throws IOException {
        this.bitOffset = 0;
        if (this.streamPos >= this.ramc.length()) {
            int length = (int) ((this.streamPos - this.ramc.length()) + 1);
            if (this.ramc.appendData(this.f2226is, length) < length) {
                return -1;
            }
        }
        int data = this.ramc.getData(this.streamPos);
        if (data >= 0) {
            this.streamPos++;
        }
        return data;
    }

    @Override
    public int read(byte[] bArr, int i, int i2) throws IOException {
        this.bitOffset = 0;
        if (this.streamPos >= this.ramc.length()) {
            this.ramc.appendData(this.f2226is, (int) ((this.streamPos - this.ramc.length()) + ((long) i2)));
        }
        int data = this.ramc.getData(bArr, i, i2, this.streamPos);
        if (data > 0) {
            this.streamPos += (long) data;
        }
        return data;
    }

    @Override
    public void close() throws IOException {
        super.close();
        this.ramc.close();
    }

    @Override
    public void flushBefore(long j) throws IOException {
        super.flushBefore(j);
        this.ramc.freeBefore(getFlushedPosition());
    }
}
