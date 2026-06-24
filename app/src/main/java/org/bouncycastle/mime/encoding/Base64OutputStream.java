package org.bouncycastle.mime.encoding;

import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import org.bouncycastle.util.encoders.Base64Encoder;

public class Base64OutputStream extends FilterOutputStream {
    private static final Base64Encoder ENCODER = new Base64Encoder();
    private static final int INBUF_SIZE = 54;
    private static final int OUTBUF_SIZE = 74;
    private final byte[] inBuf;
    private int inPos;
    private final byte[] outBuf;

    public Base64OutputStream(OutputStream outputStream) {
        super(outputStream);
        this.inBuf = new byte[54];
        byte[] bArr = new byte[74];
        this.outBuf = bArr;
        this.inPos = 0;
        bArr[72] = 13;
        bArr[73] = 10;
    }

    private void encodeBlock(byte[] bArr, int i) throws IOException {
        ENCODER.encode(bArr, i, 54, this.outBuf, 0);
        this.out.write(this.outBuf, 0, 74);
    }

    @Override
    public void close() throws IOException {
        int i = this.inPos;
        if (i > 0) {
            int iEncode = ENCODER.encode(this.inBuf, 0, i, this.outBuf, 0);
            this.inPos = 0;
            byte[] bArr = this.outBuf;
            int i2 = iEncode + 1;
            bArr[iEncode] = 13;
            bArr[i2] = 10;
            this.out.write(this.outBuf, 0, i2 + 1);
        }
        this.out.close();
    }

    @Override
    public void write(int i) throws IOException {
        byte[] bArr = this.inBuf;
        int i2 = this.inPos;
        int i3 = i2 + 1;
        this.inPos = i3;
        bArr[i2] = (byte) i;
        if (i3 == 54) {
            encodeBlock(bArr, 0);
            this.inPos = 0;
        }
    }

    @Override
    public void write(byte[] bArr) throws IOException {
        write(bArr, 0, bArr.length);
    }

    @Override
    public void write(byte[] bArr, int i, int i2) throws IOException {
        int i3;
        int i4 = this.inPos;
        int i5 = 54 - i4;
        if (i2 < i5) {
            System.arraycopy(bArr, i, this.inBuf, i4, i2);
            this.inPos += i2;
            return;
        }
        if (i4 > 0) {
            System.arraycopy(bArr, i, this.inBuf, i4, i5);
            i3 = i5 + 0;
            encodeBlock(this.inBuf, 0);
        } else {
            i3 = 0;
        }
        while (true) {
            int i6 = i2 - i3;
            if (i6 < 54) {
                System.arraycopy(bArr, i + i3, this.inBuf, 0, i6);
                this.inPos = i6;
                return;
            } else {
                encodeBlock(bArr, i + i3);
                i3 += 54;
            }
        }
    }
}
