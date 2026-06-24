package com.tom_roush.fontbox.ttf;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import org.bouncycastle.asn1.cmc.BodyPartID;

class MemoryTTFDataStream extends TTFDataStream {
    private int currentPosition = 0;
    private byte[] data;

    MemoryTTFDataStream(InputStream inputStream) throws IOException {
        this.data = null;
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(inputStream.available());
            byte[] bArr = new byte[1024];
            while (true) {
                int i = inputStream.read(bArr);
                if (i == -1) {
                    break;
                } else {
                    byteArrayOutputStream.write(bArr, 0, i);
                }
            }
            this.data = byteArrayOutputStream.toByteArray();
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
        }
    }

    @Override
    public long readLong() throws IOException {
        return (((long) readSignedInt()) << 32) + (((long) readSignedInt()) & BodyPartID.bodyIdMax);
    }

    public int readSignedInt() throws IOException {
        int i = read();
        int i2 = read();
        int i3 = read();
        int i4 = read();
        if ((i | i2 | i3 | i4) >= 0) {
            return (i << 24) + (i2 << 16) + (i3 << 8) + (i4 << 0);
        }
        throw new EOFException();
    }

    @Override
    public int read() throws IOException {
        int i = this.currentPosition;
        byte[] bArr = this.data;
        if (i >= bArr.length) {
            return -1;
        }
        byte b = bArr[i];
        this.currentPosition = i + 1;
        return (b + 256) % 256;
    }

    @Override
    public int readUnsignedShort() throws IOException {
        int i = read();
        int i2 = read();
        if ((i | i2) >= 0) {
            return (i << 8) + (i2 << 0);
        }
        throw new EOFException();
    }

    @Override
    public short readSignedShort() throws IOException {
        int i = read();
        int i2 = read();
        if ((i | i2) >= 0) {
            return (short) ((i << 8) + (i2 << 0));
        }
        throw new EOFException();
    }

    @Override
    public void close() throws IOException {
        this.data = null;
    }

    @Override
    public void seek(long j) throws IOException {
        this.currentPosition = (int) j;
    }

    @Override
    public int read(byte[] bArr, int i, int i2) throws IOException {
        int i3 = this.currentPosition;
        byte[] bArr2 = this.data;
        if (i3 >= bArr2.length) {
            return -1;
        }
        int iMin = Math.min(i2, bArr2.length - i3);
        System.arraycopy(this.data, this.currentPosition, bArr, i, iMin);
        this.currentPosition += iMin;
        return iMin;
    }

    @Override
    public long getCurrentPosition() throws IOException {
        return this.currentPosition;
    }

    @Override
    public InputStream getOriginalData() throws IOException {
        return new ByteArrayInputStream(this.data);
    }
}
