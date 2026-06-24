package com.tom_roush.harmony.javax.imageio.stream;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.nio.ByteOrder;
import kotlin.UByte;
import kotlin.UShort;
import org.bouncycastle.asn1.cmc.BodyPartID;

public abstract class ImageInputStreamImpl implements ImageInputStream {
    int currentByte;
    private final PositionStack offsetStack;
    private final PositionStack posStack;
    protected ByteOrder byteOrder = ByteOrder.BIG_ENDIAN;
    protected long streamPos = 0;
    protected long flushedPos = 0;
    protected int bitOffset = 0;
    private boolean closed = false;
    private final byte[] buff = new byte[8];

    @Override
    public boolean isCached() {
        return false;
    }

    @Override
    public boolean isCachedFile() {
        return false;
    }

    @Override
    public boolean isCachedMemory() {
        return false;
    }

    @Override
    public long length() {
        return -1L;
    }

    @Override
    public abstract int read() throws IOException;

    @Override
    public abstract int read(byte[] bArr, int i, int i2) throws IOException;

    public ImageInputStreamImpl() {
        this.posStack = new PositionStack();
        this.offsetStack = new PositionStack();
    }

    protected final void checkClosed() throws IOException {
        if (this.closed) {
            throw new IOException("stream is closed");
        }
    }

    @Override
    public void setByteOrder(ByteOrder byteOrder) {
        this.byteOrder = byteOrder;
    }

    @Override
    public ByteOrder getByteOrder() {
        return this.byteOrder;
    }

    @Override
    public int read(byte[] bArr) throws IOException {
        return read(bArr, 0, bArr.length);
    }

    @Override
    public void readBytes(IIOByteBuffer iIOByteBuffer, int i) throws IOException {
        if (iIOByteBuffer == null) {
            throw new NullPointerException("buffer is NULL");
        }
        byte[] bArr = new byte[i];
        int i2 = read(bArr, 0, i);
        iIOByteBuffer.setData(bArr);
        iIOByteBuffer.setOffset(0);
        iIOByteBuffer.setLength(i2);
    }

    @Override
    public boolean readBoolean() throws IOException {
        int i = read();
        if (i >= 0) {
            return i != 0;
        }
        throw new EOFException("EOF reached");
    }

    @Override
    public byte readByte() throws IOException {
        int i = read();
        if (i >= 0) {
            return (byte) i;
        }
        throw new EOFException("EOF reached");
    }

    @Override
    public int readUnsignedByte() throws IOException {
        int i = read();
        if (i >= 0) {
            return i;
        }
        throw new EOFException("EOF reached");
    }

    @Override
    public short readShort() throws IOException {
        int i;
        byte b;
        if (read(this.buff, 0, 2) < 0) {
            throw new EOFException();
        }
        if (this.byteOrder == ByteOrder.BIG_ENDIAN) {
            byte[] bArr = this.buff;
            i = bArr[0] << 8;
            b = bArr[1];
        } else {
            byte[] bArr2 = this.buff;
            i = bArr2[1] << 8;
            b = bArr2[0];
        }
        return (short) ((b & UByte.MAX_VALUE) | i);
    }

    @Override
    public int readUnsignedShort() throws IOException {
        return readShort() & UShort.MAX_VALUE;
    }

    @Override
    public char readChar() throws IOException {
        return (char) readShort();
    }

    @Override
    public int readInt() throws IOException {
        int i;
        byte b;
        if (read(this.buff, 0, 4) < 0) {
            throw new EOFException();
        }
        if (this.byteOrder == ByteOrder.BIG_ENDIAN) {
            byte[] bArr = this.buff;
            i = ((bArr[0] & UByte.MAX_VALUE) << 24) | ((bArr[1] & UByte.MAX_VALUE) << 16) | ((bArr[2] & UByte.MAX_VALUE) << 8);
            b = bArr[3];
        } else {
            byte[] bArr2 = this.buff;
            i = ((bArr2[3] & UByte.MAX_VALUE) << 24) | ((bArr2[2] & UByte.MAX_VALUE) << 16) | ((bArr2[1] & UByte.MAX_VALUE) << 8);
            b = bArr2[0];
        }
        return (b & UByte.MAX_VALUE) | i;
    }

    @Override
    public long readUnsignedInt() throws IOException {
        return ((long) readInt()) & BodyPartID.bodyIdMax;
    }

    @Override
    public long readLong() throws IOException {
        if (read(this.buff, 0, 8) < 0) {
            throw new EOFException();
        }
        if (this.byteOrder == ByteOrder.BIG_ENDIAN) {
            byte[] bArr = this.buff;
            return ((((long) (((((bArr[0] & UByte.MAX_VALUE) << 24) | ((bArr[1] & UByte.MAX_VALUE) << 16)) | ((bArr[2] & UByte.MAX_VALUE) << 8)) | (bArr[3] & UByte.MAX_VALUE))) & BodyPartID.bodyIdMax) << 32) | (((long) ((bArr[7] & UByte.MAX_VALUE) | ((bArr[6] & UByte.MAX_VALUE) << 8) | ((bArr[4] & UByte.MAX_VALUE) << 24) | ((bArr[5] & UByte.MAX_VALUE) << 16))) & BodyPartID.bodyIdMax);
        }
        byte[] bArr2 = this.buff;
        int i = (bArr2[0] & UByte.MAX_VALUE) | ((bArr2[3] & UByte.MAX_VALUE) << 24) | ((bArr2[2] & UByte.MAX_VALUE) << 16) | ((bArr2[1] & UByte.MAX_VALUE) << 8);
        return (((long) i) & BodyPartID.bodyIdMax) | ((((long) ((bArr2[4] & UByte.MAX_VALUE) | (((bArr2[5] & UByte.MAX_VALUE) << 8) | (((bArr2[7] & UByte.MAX_VALUE) << 24) | ((bArr2[6] & UByte.MAX_VALUE) << 16))))) & BodyPartID.bodyIdMax) << 32);
    }

    @Override
    public float readFloat() throws IOException {
        return Float.intBitsToFloat(readInt());
    }

    @Override
    public double readDouble() throws IOException {
        return Double.longBitsToDouble(readLong());
    }

    @Override
    public String readLine() throws IOException {
        StringBuilder sb = new StringBuilder(80);
        boolean z = true;
        while (true) {
            int i = read();
            if (i != -1) {
                if (i == 10) {
                    break;
                }
                if (i == 13) {
                    int i2 = read();
                    if (i2 != 10 && i2 != -1) {
                        seek(getStreamPosition() - 1);
                    }
                } else {
                    sb.append((char) i);
                    z = false;
                }
            } else {
                break;
            }
        }
        z = false;
        if (z) {
            return null;
        }
        return sb.toString();
    }

    @Override
    public String readUTF() throws IOException {
        ByteOrder byteOrder = getByteOrder();
        setByteOrder(ByteOrder.BIG_ENDIAN);
        int unsignedShort = readUnsignedShort();
        char[] cArr = new char[unsignedShort];
        readFully(new byte[unsignedShort], 0, unsignedShort);
        setByteOrder(byteOrder);
        return new DataInputStream(new ByteArrayInputStream(this.buff)).readUTF();
    }

    @Override
    public void readFully(byte[] bArr, int i, int i2) throws IOException {
        if (i < 0 || i2 < 0 || i + i2 > bArr.length) {
            throw new IndexOutOfBoundsException();
        }
        while (i2 > 0) {
            int i3 = read(bArr, i, i2);
            if (i3 == -1) {
                throw new EOFException();
            }
            i += i3;
            i2 -= i3;
        }
    }

    @Override
    public void readFully(byte[] bArr) throws IOException {
        readFully(bArr, 0, bArr.length);
    }

    @Override
    public void readFully(short[] sArr, int i, int i2) throws IOException {
        if (i < 0 || i2 < 0 || i + i2 > sArr.length) {
            throw new IndexOutOfBoundsException();
        }
        for (int i3 = 0; i3 < i2; i3++) {
            sArr[i + i3] = readShort();
        }
    }

    @Override
    public void readFully(char[] cArr, int i, int i2) throws IOException {
        if (i < 0 || i2 < 0 || i + i2 > cArr.length) {
            throw new IndexOutOfBoundsException();
        }
        for (int i3 = 0; i3 < i2; i3++) {
            cArr[i + i3] = readChar();
        }
    }

    @Override
    public void readFully(int[] iArr, int i, int i2) throws IOException {
        if (i < 0 || i2 < 0 || i + i2 > iArr.length) {
            throw new IndexOutOfBoundsException();
        }
        for (int i3 = 0; i3 < i2; i3++) {
            iArr[i + i3] = readInt();
        }
    }

    @Override
    public void readFully(long[] jArr, int i, int i2) throws IOException {
        if (i < 0 || i2 < 0 || i + i2 > jArr.length) {
            throw new IndexOutOfBoundsException();
        }
        for (int i3 = 0; i3 < i2; i3++) {
            jArr[i + i3] = readLong();
        }
    }

    @Override
    public void readFully(float[] fArr, int i, int i2) throws IOException {
        if (i < 0 || i2 < 0 || i + i2 > fArr.length) {
            throw new IndexOutOfBoundsException();
        }
        for (int i3 = 0; i3 < i2; i3++) {
            fArr[i + i3] = readFloat();
        }
    }

    @Override
    public void readFully(double[] dArr, int i, int i2) throws IOException {
        if (i < 0 || i2 < 0 || i + i2 > dArr.length) {
            throw new IndexOutOfBoundsException();
        }
        for (int i3 = 0; i3 < i2; i3++) {
            dArr[i + i3] = readFloat();
        }
    }

    @Override
    public long getStreamPosition() throws IOException {
        checkClosed();
        return this.streamPos;
    }

    @Override
    public int getBitOffset() throws IOException {
        checkClosed();
        return this.bitOffset;
    }

    @Override
    public void setBitOffset(int i) throws IOException {
        checkClosed();
        if (i < 0 || i > 7) {
            throw new IllegalArgumentException();
        }
        this.bitOffset = i;
    }

    @Override
    public int readBit() throws IOException {
        checkClosed();
        int i = this.bitOffset;
        int i2 = read();
        if (i2 == -1) {
            throw new EOFException();
        }
        int i3 = (i + 1) & 7;
        if (i3 != 0) {
            i2 >>= 8 - i3;
            seek(getStreamPosition() - 1);
        }
        this.bitOffset = i3;
        return i2 & 1;
    }

    @Override
    public long readBits(int i) throws IOException {
        checkClosed();
        if (i < 0 || i > 64) {
            throw new IllegalArgumentException();
        }
        long bit = 0;
        for (int i2 = 0; i2 < i; i2++) {
            bit = (bit << 1) | ((long) readBit());
        }
        return bit;
    }

    @Override
    public int skipBytes(int i) throws IOException {
        return (int) skipBytes(i);
    }

    @Override
    public long skipBytes(long j) throws IOException {
        seek(getStreamPosition() + j);
        return j;
    }

    @Override
    public void seek(long j) throws IOException {
        checkClosed();
        if (j < getFlushedPosition()) {
            throw new IllegalArgumentException("trying to seek before flushed pos");
        }
        this.bitOffset = 0;
        this.streamPos = j;
    }

    @Override
    public void mark() {
        try {
            this.posStack.push(getStreamPosition());
            this.offsetStack.push(getBitOffset());
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Stream marking error");
        }
    }

    @Override
    public void reset() throws IOException {
        if (this.posStack.isEmpty() || this.offsetStack.isEmpty()) {
            return;
        }
        long jPop = this.posStack.pop();
        if (jPop < this.flushedPos) {
            throw new IOException("marked position lies in the flushed portion of the stream");
        }
        seek(jPop);
        setBitOffset((int) this.offsetStack.pop());
    }

    @Override
    public void flushBefore(long j) throws IOException {
        if (j > getStreamPosition()) {
            throw new IndexOutOfBoundsException("Trying to flush outside of current position");
        }
        if (j < this.flushedPos) {
            throw new IndexOutOfBoundsException("Trying to flush within already flushed portion");
        }
        this.flushedPos = j;
    }

    @Override
    public void flush() throws IOException {
        flushBefore(getStreamPosition());
    }

    @Override
    public long getFlushedPosition() {
        return this.flushedPos;
    }

    @Override
    public void close() throws IOException {
        checkClosed();
        this.closed = true;
    }

    protected void finalize() throws Throwable {
        if (this.closed) {
            return;
        }
        try {
            close();
        } finally {
            super.finalize();
        }
    }

    private static class PositionStack {
        private static final int SIZE = 10;
        private int pos;
        private long[] values;

        private PositionStack() {
            this.values = new long[10];
            this.pos = 0;
        }

        void push(long j) {
            int i = this.pos;
            if (i >= this.values.length) {
                ensure(i + 1);
            }
            long[] jArr = this.values;
            int i2 = this.pos;
            this.pos = i2 + 1;
            jArr[i2] = j;
        }

        long pop() {
            long[] jArr = this.values;
            int i = this.pos - 1;
            this.pos = i;
            return jArr[i];
        }

        boolean isEmpty() {
            return this.pos == 0;
        }

        private void ensure(int i) {
            long[] jArr = new long[Math.max(this.values.length * 2, i)];
            long[] jArr2 = this.values;
            System.arraycopy(jArr2, 0, jArr, 0, jArr2.length);
            this.values = jArr;
        }
    }
}
