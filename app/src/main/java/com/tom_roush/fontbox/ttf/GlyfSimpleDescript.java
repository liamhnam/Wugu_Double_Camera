package com.tom_roush.fontbox.ttf;

import java.io.IOException;

public class GlyfSimpleDescript extends GlyfDescript {
    private int[] endPtsOfContours;
    private byte[] flags;
    private final int pointCount;
    private short[] xCoordinates;
    private short[] yCoordinates;

    @Override
    public boolean isComposite() {
        return false;
    }

    public GlyfSimpleDescript(short s, TTFDataStream tTFDataStream, short s2) throws IOException {
        super(s, tTFDataStream);
        if (s == 0) {
            this.pointCount = 0;
            return;
        }
        int[] unsignedShortArray = tTFDataStream.readUnsignedShortArray(s);
        this.endPtsOfContours = unsignedShortArray;
        int i = unsignedShortArray[s - 1];
        if (s == 1 && i == 65535) {
            this.pointCount = 0;
            return;
        }
        int i2 = i + 1;
        this.pointCount = i2;
        this.flags = new byte[i2];
        this.xCoordinates = new short[i2];
        this.yCoordinates = new short[i2];
        readInstructions(tTFDataStream, tTFDataStream.readUnsignedShort());
        readFlags(i2, tTFDataStream);
        readCoords(i2, tTFDataStream, s2);
    }

    @Override
    public int getEndPtOfContours(int i) {
        return this.endPtsOfContours[i];
    }

    @Override
    public byte getFlags(int i) {
        return this.flags[i];
    }

    @Override
    public short getXCoordinate(int i) {
        return this.xCoordinates[i];
    }

    @Override
    public short getYCoordinate(int i) {
        return this.yCoordinates[i];
    }

    @Override
    public int getPointCount() {
        return this.pointCount;
    }

    private void readCoords(int i, TTFDataStream tTFDataStream, short s) throws IOException {
        short signedShort;
        int unsignedByte;
        short signedShort2;
        int unsignedByte2;
        for (int i2 = 0; i2 < i; i2++) {
            byte b = this.flags[i2];
            if ((b & 16) != 0) {
                if ((b & 2) != 0) {
                    unsignedByte2 = tTFDataStream.readUnsignedByte();
                } else {
                    this.xCoordinates[i2] = s;
                }
            } else if ((b & 2) != 0) {
                unsignedByte2 = -((short) tTFDataStream.readUnsignedByte());
            } else {
                signedShort2 = tTFDataStream.readSignedShort();
                s = (short) (s + signedShort2);
                this.xCoordinates[i2] = s;
            }
            signedShort2 = (short) unsignedByte2;
            s = (short) (s + signedShort2);
            this.xCoordinates[i2] = s;
        }
        short s2 = 0;
        for (int i3 = 0; i3 < i; i3++) {
            byte b2 = this.flags[i3];
            if ((b2 & 32) != 0) {
                if ((b2 & 4) != 0) {
                    unsignedByte = tTFDataStream.readUnsignedByte();
                } else {
                    this.yCoordinates[i3] = s2;
                }
            } else if ((b2 & 4) != 0) {
                unsignedByte = -((short) tTFDataStream.readUnsignedByte());
            } else {
                signedShort = tTFDataStream.readSignedShort();
                s2 = (short) (s2 + signedShort);
                this.yCoordinates[i3] = s2;
            }
            signedShort = (short) unsignedByte;
            s2 = (short) (s2 + signedShort);
            this.yCoordinates[i3] = s2;
        }
    }

    private void readFlags(int i, TTFDataStream tTFDataStream) throws IOException {
        int i2 = 0;
        while (i2 < i) {
            this.flags[i2] = (byte) tTFDataStream.readUnsignedByte();
            if ((this.flags[i2] & 8) != 0) {
                int unsignedByte = tTFDataStream.readUnsignedByte();
                for (int i3 = 1; i3 <= unsignedByte; i3++) {
                    byte[] bArr = this.flags;
                    bArr[i2 + i3] = bArr[i2];
                }
                i2 += unsignedByte;
            }
            i2++;
        }
    }
}
