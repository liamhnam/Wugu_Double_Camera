package com.tom_roush.harmony.javax.imageio.stream;

import java.io.DataOutput;
import java.io.IOException;

public interface ImageOutputStream extends DataOutput, ImageInputStream {
    @Override
    void flushBefore(long j) throws IOException;

    @Override
    void write(int i) throws IOException;

    @Override
    void write(byte[] bArr) throws IOException;

    @Override
    void write(byte[] bArr, int i, int i2) throws IOException;

    void writeBit(int i) throws IOException;

    void writeBits(long j, int i) throws IOException;

    @Override
    void writeBoolean(boolean z) throws IOException;

    @Override
    void writeByte(int i) throws IOException;

    @Override
    void writeBytes(String str) throws IOException;

    @Override
    void writeChar(int i) throws IOException;

    @Override
    void writeChars(String str) throws IOException;

    void writeChars(char[] cArr, int i, int i2) throws IOException;

    @Override
    void writeDouble(double d) throws IOException;

    void writeDoubles(double[] dArr, int i, int i2) throws IOException;

    @Override
    void writeFloat(float f) throws IOException;

    void writeFloats(float[] fArr, int i, int i2) throws IOException;

    @Override
    void writeInt(int i) throws IOException;

    void writeInts(int[] iArr, int i, int i2) throws IOException;

    @Override
    void writeLong(long j) throws IOException;

    void writeLongs(long[] jArr, int i, int i2) throws IOException;

    @Override
    void writeShort(int i) throws IOException;

    void writeShorts(short[] sArr, int i, int i2) throws IOException;

    @Override
    void writeUTF(String str) throws IOException;
}
