package com.tom_roush.fontbox.ttf;

import java.io.IOException;
import java.io.InputStream;

class TTCDataStream extends TTFDataStream {
    private final TTFDataStream stream;

    @Override
    public void close() throws IOException {
    }

    TTCDataStream(TTFDataStream tTFDataStream) {
        this.stream = tTFDataStream;
    }

    @Override
    public int read() throws IOException {
        return this.stream.read();
    }

    @Override
    public long readLong() throws IOException {
        return this.stream.readLong();
    }

    @Override
    public int readUnsignedShort() throws IOException {
        return this.stream.readUnsignedShort();
    }

    @Override
    public short readSignedShort() throws IOException {
        return this.stream.readSignedShort();
    }

    @Override
    public void seek(long j) throws IOException {
        this.stream.seek(j);
    }

    @Override
    public int read(byte[] bArr, int i, int i2) throws IOException {
        return this.stream.read(bArr, i, i2);
    }

    @Override
    public long getCurrentPosition() throws IOException {
        return this.stream.getCurrentPosition();
    }

    @Override
    public InputStream getOriginalData() throws IOException {
        return this.stream.getOriginalData();
    }
}
