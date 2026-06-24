package com.tom_roush.pdfbox.pdfparser;

import java.io.IOException;
import java.io.InputStream;
import java.io.PushbackInputStream;

final class InputStreamSource implements SequentialSource {
    private final PushbackInputStream input;
    private int position = 0;

    InputStreamSource(InputStream inputStream) {
        this.input = new PushbackInputStream(inputStream, 32767);
    }

    @Override
    public int read() throws IOException {
        int i = this.input.read();
        this.position++;
        return i;
    }

    @Override
    public int read(byte[] bArr) throws IOException {
        int i = this.input.read(bArr);
        this.position += i;
        return i;
    }

    @Override
    public int read(byte[] bArr, int i, int i2) throws IOException {
        int i3 = this.input.read(bArr, i, i2);
        this.position += i3;
        return i3;
    }

    @Override
    public long getPosition() throws IOException {
        return this.position;
    }

    @Override
    public int peek() throws IOException {
        int i = this.input.read();
        if (i != -1) {
            this.input.unread(i);
        }
        return i;
    }

    @Override
    public void unread(int i) throws IOException {
        this.input.unread(i);
        this.position--;
    }

    @Override
    public void unread(byte[] bArr) throws IOException {
        this.input.unread(bArr);
        this.position -= bArr.length;
    }

    @Override
    public byte[] readFully(int i) throws IOException {
        byte[] bArr = new byte[i];
        int i2 = 0;
        while (i > 0) {
            int i3 = read(bArr, i2, i);
            i2 += i3;
            i -= i3;
            this.position += i3;
        }
        return bArr;
    }

    @Override
    public boolean isEOF() throws IOException {
        return peek() == -1;
    }

    @Override
    public void close() throws IOException {
        this.input.close();
    }
}
