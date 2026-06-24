package com.tom_roush.pdfbox.pdmodel.encryption;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

class RC4Cipher {

    private int f2385b;

    private int f2386c;
    private final int[] salt = new int[256];

    private static int fixByte(byte b) {
        return b < 0 ? b + 256 : b;
    }

    RC4Cipher() {
    }

    public void setKey(byte[] bArr) {
        this.f2385b = 0;
        this.f2386c = 0;
        if (bArr.length < 1 || bArr.length > 32) {
            throw new IllegalArgumentException("number of bytes must be between 1 and 32");
        }
        int i = 0;
        while (true) {
            int[] iArr = this.salt;
            if (i >= iArr.length) {
                break;
            }
            iArr[i] = i;
            i++;
        }
        int length = 0;
        int i2 = 0;
        for (int i3 = 0; i3 < this.salt.length; i3++) {
            int iFixByte = fixByte(bArr[length]);
            int[] iArr2 = this.salt;
            i2 = ((iFixByte + iArr2[i3]) + i2) % 256;
            swap(iArr2, i3, i2);
            length = (length + 1) % bArr.length;
        }
    }

    private static void swap(int[] iArr, int i, int i2) {
        int i3 = iArr[i];
        iArr[i] = iArr[i2];
        iArr[i2] = i3;
    }

    public void write(byte b, OutputStream outputStream) throws IOException {
        int i = (this.f2385b + 1) % 256;
        this.f2385b = i;
        int[] iArr = this.salt;
        int i2 = (iArr[i] + this.f2386c) % 256;
        this.f2386c = i2;
        swap(iArr, i, i2);
        int[] iArr2 = this.salt;
        outputStream.write(b ^ ((byte) iArr2[(iArr2[this.f2385b] + iArr2[this.f2386c]) % 256]));
    }

    public void write(byte[] bArr, OutputStream outputStream) throws IOException {
        for (byte b : bArr) {
            write(b, outputStream);
        }
    }

    public void write(InputStream inputStream, OutputStream outputStream) throws IOException {
        byte[] bArr = new byte[1024];
        while (true) {
            int i = inputStream.read(bArr);
            if (i == -1) {
                return;
            } else {
                write(bArr, 0, i, outputStream);
            }
        }
    }

    public void write(byte[] bArr, int i, int i2, OutputStream outputStream) throws IOException {
        for (int i3 = i; i3 < i + i2; i3++) {
            write(bArr[i3], outputStream);
        }
    }
}
