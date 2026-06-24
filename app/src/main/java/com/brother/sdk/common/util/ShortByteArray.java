package com.brother.sdk.common.util;

public class ShortByteArray {
    private static final int BASE_SIZE = 64;
    private byte[] mBuffer = new byte[64];
    private int mLength;

    public void put(byte[] bArr) {
        if (bArr == null || bArr.length < 1) {
            return;
        }
        int length = bArr.length;
        int i = this.mLength + length;
        if (this.mBuffer.length < i) {
            expand(i);
        }
        System.arraycopy(bArr, 0, this.mBuffer, this.mLength, length);
        this.mLength += length;
    }

    public void put(byte b) {
        int i = this.mLength + 1;
        if (this.mBuffer.length < i) {
            expand(i);
        }
        byte[] bArr = this.mBuffer;
        int i2 = this.mLength;
        bArr[i2] = b;
        this.mLength = i2 + 1;
    }

    public byte[] toArray() {
        int i = this.mLength;
        byte[] bArr = new byte[i];
        System.arraycopy(this.mBuffer, 0, bArr, 0, i);
        return bArr;
    }

    public int length() {
        return this.mLength;
    }

    private int expand(int i) {
        byte[] bArr = this.mBuffer;
        int length = bArr.length;
        if (i < length) {
            return length;
        }
        int i2 = (((i + 64) - 1) / 64) * 64;
        byte[] bArr2 = new byte[i2];
        this.mBuffer = bArr2;
        System.arraycopy(bArr, 0, bArr2, 0, this.mLength);
        return i2;
    }
}
