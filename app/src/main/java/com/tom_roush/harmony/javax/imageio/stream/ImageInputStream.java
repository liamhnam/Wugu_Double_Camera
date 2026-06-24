package com.tom_roush.harmony.javax.imageio.stream;

import java.io.DataInput;
import java.io.IOException;
import java.nio.ByteOrder;

public interface ImageInputStream extends DataInput {
    void close() throws IOException;

    void flush() throws IOException;

    void flushBefore(long j) throws IOException;

    int getBitOffset() throws IOException;

    ByteOrder getByteOrder();

    long getFlushedPosition();

    long getStreamPosition() throws IOException;

    boolean isCached();

    boolean isCachedFile();

    boolean isCachedMemory();

    long length() throws IOException;

    void mark();

    int read() throws IOException;

    int read(byte[] bArr) throws IOException;

    int read(byte[] bArr, int i, int i2) throws IOException;

    int readBit() throws IOException;

    long readBits(int i) throws IOException;

    @Override
    boolean readBoolean() throws IOException;

    @Override
    byte readByte() throws IOException;

    void readBytes(IIOByteBuffer iIOByteBuffer, int i) throws IOException;

    @Override
    char readChar() throws IOException;

    @Override
    double readDouble() throws IOException;

    @Override
    float readFloat() throws IOException;

    @Override
    void readFully(byte[] bArr) throws IOException;

    @Override
    void readFully(byte[] bArr, int i, int i2) throws IOException;

    void readFully(char[] cArr, int i, int i2) throws IOException;

    void readFully(double[] dArr, int i, int i2) throws IOException;

    void readFully(float[] fArr, int i, int i2) throws IOException;

    void readFully(int[] iArr, int i, int i2) throws IOException;

    void readFully(long[] jArr, int i, int i2) throws IOException;

    void readFully(short[] sArr, int i, int i2) throws IOException;

    @Override
    int readInt() throws IOException;

    @Override
    String readLine() throws IOException;

    @Override
    long readLong() throws IOException;

    @Override
    short readShort() throws IOException;

    @Override
    String readUTF() throws IOException;

    @Override
    int readUnsignedByte() throws IOException;

    long readUnsignedInt() throws IOException;

    @Override
    int readUnsignedShort() throws IOException;

    void reset() throws IOException;

    void seek(long j) throws IOException;

    void setBitOffset(int i) throws IOException;

    void setByteOrder(ByteOrder byteOrder);

    @Override
    int skipBytes(int i) throws IOException;

    long skipBytes(long j) throws IOException;
}
