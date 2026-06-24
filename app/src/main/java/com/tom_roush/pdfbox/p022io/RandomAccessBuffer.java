package com.tom_roush.pdfbox.p022io;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import kotlin.UByte;

public class RandomAccessBuffer implements RandomAccess, Cloneable {
    private static final int DEFAULT_CHUNK_SIZE = 1024;
    private List<byte[]> bufferList;
    private int bufferListIndex;
    private int bufferListMaxIndex;
    private int chunkSize;
    private byte[] currentBuffer;
    private int currentBufferPointer;
    private long pointer;
    private long size;

    public RandomAccessBuffer() {
        this(1024);
    }

    private RandomAccessBuffer(int i) {
        this.chunkSize = 1024;
        this.bufferList = null;
        ArrayList arrayList = new ArrayList();
        this.bufferList = arrayList;
        this.chunkSize = i;
        byte[] bArr = new byte[i];
        this.currentBuffer = bArr;
        arrayList.add(bArr);
        this.pointer = 0L;
        this.currentBufferPointer = 0;
        this.size = 0L;
        this.bufferListIndex = 0;
        this.bufferListMaxIndex = 0;
    }

    public RandomAccessBuffer(byte[] bArr) {
        this.chunkSize = 1024;
        this.bufferList = null;
        ArrayList arrayList = new ArrayList(1);
        this.bufferList = arrayList;
        this.chunkSize = bArr.length;
        this.currentBuffer = bArr;
        arrayList.add(bArr);
        this.pointer = 0L;
        this.currentBufferPointer = 0;
        this.size = this.chunkSize;
        this.bufferListIndex = 0;
        this.bufferListMaxIndex = 0;
    }

    public RandomAccessBuffer(InputStream inputStream) throws IOException {
        this();
        byte[] bArr = new byte[8192];
        while (true) {
            int i = inputStream.read(bArr);
            if (i > -1) {
                write(bArr, 0, i);
            } else {
                seek(0L);
                return;
            }
        }
    }

    public RandomAccessBuffer m1572clone() {
        RandomAccessBuffer randomAccessBuffer = new RandomAccessBuffer(this.chunkSize);
        randomAccessBuffer.bufferList = new ArrayList(this.bufferList.size());
        for (byte[] bArr : this.bufferList) {
            byte[] bArr2 = new byte[bArr.length];
            System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
            randomAccessBuffer.bufferList.add(bArr2);
        }
        if (this.currentBuffer != null) {
            randomAccessBuffer.currentBuffer = randomAccessBuffer.bufferList.get(r1.size() - 1);
        } else {
            randomAccessBuffer.currentBuffer = null;
        }
        randomAccessBuffer.pointer = this.pointer;
        randomAccessBuffer.currentBufferPointer = this.currentBufferPointer;
        randomAccessBuffer.size = this.size;
        randomAccessBuffer.bufferListIndex = this.bufferListIndex;
        randomAccessBuffer.bufferListMaxIndex = this.bufferListMaxIndex;
        return randomAccessBuffer;
    }

    @Override
    public void close() throws IOException {
        this.currentBuffer = null;
        this.bufferList.clear();
        this.pointer = 0L;
        this.currentBufferPointer = 0;
        this.size = 0L;
        this.bufferListIndex = 0;
    }

    @Override
    public void clear() {
        this.bufferList.clear();
        byte[] bArr = new byte[this.chunkSize];
        this.currentBuffer = bArr;
        this.bufferList.add(bArr);
        this.pointer = 0L;
        this.currentBufferPointer = 0;
        this.size = 0L;
        this.bufferListIndex = 0;
        this.bufferListMaxIndex = 0;
    }

    @Override
    public void seek(long j) throws IOException {
        checkClosed();
        if (j < 0) {
            throw new IOException("Invalid position " + j);
        }
        this.pointer = j;
        if (j < this.size) {
            int i = this.chunkSize;
            int i2 = (int) (j / ((long) i));
            this.bufferListIndex = i2;
            this.currentBufferPointer = (int) (j % ((long) i));
            this.currentBuffer = this.bufferList.get(i2);
            return;
        }
        int i3 = this.bufferListMaxIndex;
        this.bufferListIndex = i3;
        this.currentBuffer = this.bufferList.get(i3);
        this.currentBufferPointer = (int) (this.size % ((long) this.chunkSize));
    }

    @Override
    public long getPosition() throws IOException {
        checkClosed();
        return this.pointer;
    }

    @Override
    public int read() throws IOException {
        checkClosed();
        if (this.pointer >= this.size) {
            return -1;
        }
        if (this.currentBufferPointer >= this.chunkSize) {
            int i = this.bufferListIndex;
            if (i >= this.bufferListMaxIndex) {
                return -1;
            }
            List<byte[]> list = this.bufferList;
            int i2 = i + 1;
            this.bufferListIndex = i2;
            this.currentBuffer = list.get(i2);
            this.currentBufferPointer = 0;
        }
        this.pointer++;
        byte[] bArr = this.currentBuffer;
        int i3 = this.currentBufferPointer;
        this.currentBufferPointer = i3 + 1;
        return bArr[i3] & UByte.MAX_VALUE;
    }

    @Override
    public int read(byte[] bArr, int i, int i2) throws IOException {
        checkClosed();
        if (this.pointer >= this.size) {
            return 0;
        }
        int remainingBytes = readRemainingBytes(bArr, i, i2);
        while (remainingBytes < i2 && available() > 0) {
            remainingBytes += readRemainingBytes(bArr, i + remainingBytes, i2 - remainingBytes);
            if (this.currentBufferPointer == this.chunkSize) {
                nextBuffer();
            }
        }
        return remainingBytes;
    }

    private int readRemainingBytes(byte[] bArr, int i, int i2) throws IOException {
        long j = this.pointer;
        long j2 = this.size;
        if (j >= j2) {
            return 0;
        }
        int iMin = (int) Math.min(i2, j2 - j);
        int i3 = this.chunkSize;
        int i4 = this.currentBufferPointer;
        int i5 = i3 - i4;
        if (i5 == 0) {
            return 0;
        }
        if (iMin >= i5) {
            System.arraycopy(this.currentBuffer, i4, bArr, i, i5);
            this.currentBufferPointer += i5;
            this.pointer += (long) i5;
            return i5;
        }
        System.arraycopy(this.currentBuffer, i4, bArr, i, iMin);
        this.currentBufferPointer += iMin;
        this.pointer += (long) iMin;
        return iMin;
    }

    @Override
    public long length() throws IOException {
        checkClosed();
        return this.size;
    }

    @Override
    public void write(int i) throws IOException {
        checkClosed();
        int i2 = this.currentBufferPointer;
        int i3 = this.chunkSize;
        if (i2 >= i3) {
            if (this.pointer + ((long) i3) >= 2147483647L) {
                throw new IOException("RandomAccessBuffer overflow");
            }
            expandBuffer();
        }
        byte[] bArr = this.currentBuffer;
        int i4 = this.currentBufferPointer;
        int i5 = i4 + 1;
        this.currentBufferPointer = i5;
        bArr[i4] = (byte) i;
        long j = this.pointer + 1;
        this.pointer = j;
        if (j > this.size) {
            this.size = j;
        }
        int i6 = this.chunkSize;
        if (i5 >= i6) {
            if (j + ((long) i6) >= 2147483647L) {
                throw new IOException("RandomAccessBuffer overflow");
            }
            expandBuffer();
        }
    }

    @Override
    public void write(byte[] bArr) throws IOException {
        write(bArr, 0, bArr.length);
    }

    @Override
    public void write(byte[] bArr, int i, int i2) throws IOException {
        checkClosed();
        long j = i2;
        long j2 = this.pointer + j;
        int i3 = this.chunkSize;
        int i4 = this.currentBufferPointer;
        int i5 = i3 - i4;
        if (i2 >= i5) {
            if (j2 > 2147483647L) {
                throw new IOException("RandomAccessBuffer overflow");
            }
            System.arraycopy(bArr, i, this.currentBuffer, i4, i5);
            int i6 = i + i5;
            long j3 = i2 - i5;
            int i7 = ((int) j3) / this.chunkSize;
            for (int i8 = 0; i8 < i7; i8++) {
                expandBuffer();
                System.arraycopy(bArr, i6, this.currentBuffer, this.currentBufferPointer, this.chunkSize);
                i6 += this.chunkSize;
            }
            long j4 = j3 - (((long) i7) * ((long) this.chunkSize));
            if (j4 >= 0) {
                expandBuffer();
                if (j4 > 0) {
                    System.arraycopy(bArr, i6, this.currentBuffer, this.currentBufferPointer, (int) j4);
                }
                this.currentBufferPointer = (int) j4;
            }
        } else {
            System.arraycopy(bArr, i, this.currentBuffer, i4, i2);
            this.currentBufferPointer += i2;
        }
        long j5 = this.pointer + j;
        this.pointer = j5;
        if (j5 > this.size) {
            this.size = j5;
        }
    }

    private void expandBuffer() throws IOException {
        if (this.bufferListMaxIndex > this.bufferListIndex) {
            nextBuffer();
            return;
        }
        byte[] bArr = new byte[this.chunkSize];
        this.currentBuffer = bArr;
        this.bufferList.add(bArr);
        this.currentBufferPointer = 0;
        this.bufferListMaxIndex++;
        this.bufferListIndex++;
    }

    private void nextBuffer() throws IOException {
        int i = this.bufferListIndex;
        if (i == this.bufferListMaxIndex) {
            throw new IOException("No more chunks available, end of buffer reached");
        }
        this.currentBufferPointer = 0;
        List<byte[]> list = this.bufferList;
        int i2 = i + 1;
        this.bufferListIndex = i2;
        this.currentBuffer = list.get(i2);
    }

    private void checkClosed() throws IOException {
        if (this.currentBuffer == null) {
            throw new IOException("RandomAccessBuffer already closed");
        }
    }

    @Override
    public boolean isClosed() {
        return this.currentBuffer == null;
    }

    @Override
    public boolean isEOF() throws IOException {
        checkClosed();
        return this.pointer >= this.size;
    }

    @Override
    public int available() throws IOException {
        return (int) Math.min(length() - getPosition(), 2147483647L);
    }

    @Override
    public int peek() throws IOException {
        int i = read();
        if (i != -1) {
            rewind(1);
        }
        return i;
    }

    @Override
    public void rewind(int i) throws IOException {
        checkClosed();
        seek(getPosition() - ((long) i));
    }

    @Override
    public byte[] readFully(int i) throws IOException {
        byte[] bArr = new byte[i];
        int i2 = read(bArr);
        while (i2 < i) {
            i2 += read(bArr, i2, i - i2);
        }
        return bArr;
    }

    @Override
    public int read(byte[] bArr) throws IOException {
        return read(bArr, 0, bArr.length);
    }
}
