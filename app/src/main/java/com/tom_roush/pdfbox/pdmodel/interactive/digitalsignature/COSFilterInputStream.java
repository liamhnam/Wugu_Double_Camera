package com.tom_roush.pdfbox.pdmodel.interactive.digitalsignature;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

public class COSFilterInputStream extends FilterInputStream {
    private final int[] byteRange;
    private long position;

    public COSFilterInputStream(InputStream inputStream, int[] iArr) {
        super(inputStream);
        this.position = 0L;
        this.byteRange = iArr;
    }

    public COSFilterInputStream(byte[] bArr, int[] iArr) {
        super(new ByteArrayInputStream(bArr));
        this.position = 0L;
        this.byteRange = iArr;
    }

    @Override
    public int read() throws IOException {
        nextAvailable();
        int i = super.read();
        if (i > -1) {
            this.position++;
        }
        return i;
    }

    @Override
    public int read(byte[] bArr) throws IOException {
        return read(bArr, 0, bArr.length);
    }

    @Override
    public int read(byte[] bArr, int i, int i2) throws IOException {
        if (i2 == 0) {
            return 0;
        }
        int i3 = read();
        if (i3 == -1) {
            return -1;
        }
        bArr[i] = (byte) i3;
        int i4 = 1;
        while (i4 < i2) {
            try {
                int i5 = read();
                if (i5 == -1) {
                    break;
                }
                bArr[i + i4] = (byte) i5;
                i4++;
            } catch (IOException unused) {
            }
        }
        return i4;
    }

    private boolean inRange() throws IOException {
        long j = this.position;
        int i = 0;
        while (true) {
            int[] iArr = this.byteRange;
            if (i >= iArr.length / 2) {
                return false;
            }
            if (iArr[i * 2] <= j && r6 + iArr[r5 + 1] > j) {
                return true;
            }
            i++;
        }
    }

    private void nextAvailable() throws IOException {
        while (!inRange()) {
            this.position++;
            if (super.read() < 0) {
                return;
            }
        }
    }

    public byte[] toByteArray() throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bArr = new byte[1024];
        while (true) {
            int i = read(bArr);
            if (i != -1) {
                byteArrayOutputStream.write(bArr, 0, i);
            } else {
                return byteArrayOutputStream.toByteArray();
            }
        }
    }
}
