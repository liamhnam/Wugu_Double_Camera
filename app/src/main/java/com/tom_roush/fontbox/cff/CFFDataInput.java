package com.tom_roush.fontbox.cff;

import java.io.IOException;

public class CFFDataInput extends DataInput {
    public CFFDataInput(byte[] bArr) {
        super(bArr);
    }

    public int readCard8() throws IOException {
        return readUnsignedByte();
    }

    public int readCard16() throws IOException {
        return readUnsignedShort();
    }

    public int readOffset(int i) throws IOException {
        int unsignedByte = 0;
        for (int i2 = 0; i2 < i; i2++) {
            unsignedByte = (unsignedByte << 8) | readUnsignedByte();
        }
        return unsignedByte;
    }

    public int readOffSize() throws IOException {
        return readUnsignedByte();
    }

    public int readSID() throws IOException {
        return readUnsignedShort();
    }
}
