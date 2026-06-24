package org.bouncycastle.mime.encoding;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import kotlin.UByte;
import okio.Utf8;

public class Base64InputStream extends InputStream {
    private static final byte[] decodingTable = new byte[128];

    InputStream f3628in;
    int[] outBuf = new int[3];
    int bufPtr = 3;

    static {
        for (int i = 65; i <= 90; i++) {
            decodingTable[i] = (byte) (i - 65);
        }
        for (int i2 = 97; i2 <= 122; i2++) {
            decodingTable[i2] = (byte) ((i2 - 97) + 26);
        }
        for (int i3 = 48; i3 <= 57; i3++) {
            decodingTable[i3] = (byte) ((i3 - 48) + 52);
        }
        byte[] bArr = decodingTable;
        bArr[43] = 62;
        bArr[47] = Utf8.REPLACEMENT_BYTE;
    }

    public Base64InputStream(InputStream inputStream) {
        this.f3628in = inputStream;
    }

    private int decode(int i, int i2, int i3, int i4, int[] iArr) throws EOFException {
        if (i4 < 0) {
            throw new EOFException("unexpected end of file in armored stream.");
        }
        if (i3 == 61) {
            byte[] bArr = decodingTable;
            iArr[2] = (((bArr[i] & UByte.MAX_VALUE) << 2) | ((bArr[i2] & UByte.MAX_VALUE) >> 4)) & 255;
            return 2;
        }
        if (i4 == 61) {
            byte[] bArr2 = decodingTable;
            byte b = bArr2[i];
            byte b2 = bArr2[i2];
            byte b3 = bArr2[i3];
            iArr[1] = ((b << 2) | (b2 >> 4)) & 255;
            iArr[2] = ((b2 << 4) | (b3 >> 2)) & 255;
            return 1;
        }
        byte[] bArr3 = decodingTable;
        byte b4 = bArr3[i];
        byte b5 = bArr3[i2];
        byte b6 = bArr3[i3];
        byte b7 = bArr3[i4];
        iArr[0] = ((b4 << 2) | (b5 >> 4)) & 255;
        iArr[1] = ((b5 << 4) | (b6 >> 2)) & 255;
        iArr[2] = ((b6 << 6) | b7) & 255;
        return 0;
    }

    private int readIgnoreSpace() throws IOException {
        while (true) {
            int i = this.f3628in.read();
            if (i != 9 && i != 32) {
                return i;
            }
        }
    }

    private int readIgnoreSpaceFirst() throws IOException {
        while (true) {
            int i = this.f3628in.read();
            if (i != 9 && i != 10 && i != 13 && i != 32) {
                return i;
            }
        }
    }

    @Override
    public int available() throws IOException {
        return 0;
    }

    @Override
    public void close() throws IOException {
        this.f3628in.close();
    }

    @Override
    public int read() throws IOException {
        if (this.bufPtr > 2) {
            int ignoreSpaceFirst = readIgnoreSpaceFirst();
            if (ignoreSpaceFirst < 0) {
                return -1;
            }
            this.bufPtr = decode(ignoreSpaceFirst, readIgnoreSpace(), readIgnoreSpace(), readIgnoreSpace(), this.outBuf);
        }
        int[] iArr = this.outBuf;
        int i = this.bufPtr;
        this.bufPtr = i + 1;
        return iArr[i];
    }
}
