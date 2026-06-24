package com.tom_roush.harmony.javax.imageio.stream;

public class IIOByteBuffer {
    private byte[] data;
    private int length;
    private int offset;

    public IIOByteBuffer(byte[] bArr, int i, int i2) {
        this.data = bArr;
        this.offset = i;
        this.length = i2;
    }

    public byte[] getData() {
        return this.data;
    }

    public int getLength() {
        return this.length;
    }

    public int getOffset() {
        return this.offset;
    }

    public void setData(byte[] bArr) {
        this.data = bArr;
    }

    public void setLength(int i) {
        this.length = i;
    }

    public void setOffset(int i) {
        this.offset = i;
    }
}
