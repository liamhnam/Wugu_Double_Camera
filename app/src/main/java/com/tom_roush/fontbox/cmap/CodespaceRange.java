package com.tom_roush.fontbox.cmap;

public class CodespaceRange {
    private int codeLength = 0;
    private byte[] end;
    private int endInt;
    private byte[] start;
    private int startInt;

    public int getCodeLength() {
        return this.codeLength;
    }

    public byte[] getEnd() {
        return this.end;
    }

    void setEnd(byte[] bArr) {
        this.end = bArr;
        this.endInt = toInt(bArr, bArr.length);
    }

    public byte[] getStart() {
        return this.start;
    }

    void setStart(byte[] bArr) {
        this.start = bArr;
        this.codeLength = bArr.length;
        this.startInt = toInt(bArr, bArr.length);
    }

    public boolean matches(byte[] bArr) {
        return isFullMatch(bArr, bArr.length);
    }

    private int toInt(byte[] bArr, int i) {
        int i2 = 0;
        for (int i3 = 0; i3 < i; i3++) {
            i2 = (i2 << 8) | ((bArr[i3] + 256) % 256);
        }
        return i2;
    }

    public boolean isFullMatch(byte[] bArr, int i) {
        int i2;
        return i == this.codeLength && (i2 = toInt(bArr, i)) >= this.startInt && i2 <= this.endInt;
    }
}
