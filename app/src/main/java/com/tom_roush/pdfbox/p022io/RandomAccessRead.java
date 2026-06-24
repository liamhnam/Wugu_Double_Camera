package com.tom_roush.pdfbox.p022io;

import java.io.Closeable;
import java.io.IOException;

public interface RandomAccessRead extends Closeable {
    int available() throws IOException;

    long getPosition() throws IOException;

    boolean isClosed();

    boolean isEOF() throws IOException;

    long length() throws IOException;

    int peek() throws IOException;

    int read() throws IOException;

    int read(byte[] bArr) throws IOException;

    int read(byte[] bArr, int i, int i2) throws IOException;

    byte[] readFully(int i) throws IOException;

    void rewind(int i) throws IOException;

    void seek(long j) throws IOException;
}
