package com.tom_roush.pdfbox.pdfwriter;

import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class COSStandardOutputStream extends FilterOutputStream {
    private boolean onNewLine;
    private long position;
    public static final byte[] CRLF = {13, 10};

    public static final byte[] f2342LF = {10};
    public static final byte[] EOL = {10};

    public COSStandardOutputStream(OutputStream outputStream) {
        super(outputStream);
        this.position = 0L;
        this.onNewLine = false;
    }

    public COSStandardOutputStream(OutputStream outputStream, int i) {
        super(outputStream);
        this.onNewLine = false;
        this.position = i;
    }

    public long getPos() {
        return this.position;
    }

    public boolean isOnNewLine() {
        return this.onNewLine;
    }

    public void setOnNewLine(boolean z) {
        this.onNewLine = z;
    }

    @Override
    public void write(byte[] bArr, int i, int i2) throws IOException {
        setOnNewLine(false);
        this.out.write(bArr, i, i2);
        this.position += (long) i2;
    }

    @Override
    public void write(int i) throws IOException {
        setOnNewLine(false);
        this.out.write(i);
        this.position++;
    }

    public void writeCRLF() throws IOException {
        write(CRLF);
    }

    public void writeEOL() throws IOException {
        if (isOnNewLine()) {
            return;
        }
        write(EOL);
        setOnNewLine(true);
    }

    public void writeLF() throws IOException {
        write(f2342LF);
    }
}
