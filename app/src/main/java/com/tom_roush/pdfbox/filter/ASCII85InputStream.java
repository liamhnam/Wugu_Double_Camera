package com.tom_roush.pdfbox.filter;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

final class ASCII85InputStream extends FilterInputStream {
    private static final char NEWLINE = '\n';
    private static final char OFFSET = '!';
    private static final char PADDING_U = 'u';
    private static final char RETURN = '\r';
    private static final char SPACE = ' ';
    private static final char TERMINATOR = '~';

    private static final char f2323Z = 'z';
    private byte[] ascii;

    private byte[] f2324b;
    private boolean eof;
    private int index;

    private int f2325n;

    @Override
    public int available() {
        return 0;
    }

    @Override
    public void mark(int i) {
    }

    @Override
    public boolean markSupported() {
        return false;
    }

    @Override
    public long skip(long j) {
        return 0L;
    }

    ASCII85InputStream(InputStream inputStream) {
        super(inputStream);
        this.index = 0;
        this.f2325n = 0;
        this.eof = false;
        this.ascii = new byte[5];
        this.f2324b = new byte[4];
    }

    @Override
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public int read() throws java.io.IOException {
        /*
            Method dump skipped, instruction units count: 216
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tom_roush.pdfbox.filter.ASCII85InputStream.read():int");
    }

    @Override
    public int read(byte[] bArr, int i, int i2) throws IOException {
        if (this.eof && this.index >= this.f2325n) {
            return -1;
        }
        for (int i3 = 0; i3 < i2; i3++) {
            int i4 = this.index;
            if (i4 < this.f2325n) {
                byte[] bArr2 = this.f2324b;
                this.index = i4 + 1;
                bArr[i3 + i] = bArr2[i4];
            } else {
                int i5 = read();
                if (i5 == -1) {
                    return i3;
                }
                bArr[i3 + i] = (byte) i5;
            }
        }
        return i2;
    }

    @Override
    public void close() throws IOException {
        this.ascii = null;
        this.eof = true;
        this.f2324b = null;
        super.close();
    }

    @Override
    public void reset() throws IOException {
        throw new IOException("Reset is not supported");
    }
}
