package com.tom_roush.fontbox.cff;

import com.tom_roush.fontbox.util.Charsets;
import java.io.EOFException;
import java.io.IOException;
import kotlin.UByte;

public class DataInput {
    private int bufferPosition = 0;
    private byte[] inputBuffer;

    public DataInput(byte[] bArr) {
        this.inputBuffer = bArr;
    }

    public boolean hasRemaining() {
        return this.bufferPosition < this.inputBuffer.length;
    }

    public int getPosition() {
        return this.bufferPosition;
    }

    public void setPosition(int i) {
        this.bufferPosition = i;
    }

    public String getString() throws IOException {
        return new String(this.inputBuffer, Charsets.ISO_8859_1);
    }

    public byte readByte() throws IOException {
        try {
            byte[] bArr = this.inputBuffer;
            int i = this.bufferPosition;
            byte b = bArr[i];
            this.bufferPosition = i + 1;
            return b;
        } catch (RuntimeException unused) {
            return (byte) -1;
        }
    }

    public int readUnsignedByte() throws IOException {
        int i = read();
        if (i >= 0) {
            return i;
        }
        throw new EOFException();
    }

    public int peekUnsignedByte(int i) throws IOException {
        int iPeek = peek(i);
        if (iPeek >= 0) {
            return iPeek;
        }
        throw new EOFException();
    }

    public short readShort() throws IOException {
        return (short) readUnsignedShort();
    }

    public int readUnsignedShort() throws IOException {
        int i = read();
        int i2 = read();
        if ((i | i2) >= 0) {
            return (i << 8) | i2;
        }
        throw new EOFException();
    }

    public int readInt() throws IOException {
        int i = read();
        int i2 = read();
        int i3 = read();
        int i4 = read();
        if ((i | i2 | i3 | i4) >= 0) {
            return (i << 24) | (i2 << 16) | (i3 << 8) | i4;
        }
        throw new EOFException();
    }

    public byte[] readBytes(int i) throws IOException {
        byte[] bArr = this.inputBuffer;
        int length = bArr.length;
        int i2 = this.bufferPosition;
        if (length - i2 < i) {
            throw new EOFException();
        }
        byte[] bArr2 = new byte[i];
        System.arraycopy(bArr, i2, bArr2, 0, i);
        this.bufferPosition += i;
        return bArr2;
    }

    private int read() {
        try {
            byte[] bArr = this.inputBuffer;
            int i = this.bufferPosition;
            int i2 = bArr[i] & UByte.MAX_VALUE;
            this.bufferPosition = i + 1;
            return i2;
        } catch (RuntimeException unused) {
            return -1;
        }
    }

    private int peek(int i) {
        try {
            return this.inputBuffer[this.bufferPosition + i] & UByte.MAX_VALUE;
        } catch (RuntimeException unused) {
            return -1;
        }
    }

    public int length() {
        return this.inputBuffer.length;
    }
}
