package com.tom_roush.pdfbox.pdfparser;

import java.io.Closeable;
import java.io.IOException;

interface SequentialSource extends Closeable {
    long getPosition() throws IOException;

    boolean isEOF() throws IOException;

    int peek() throws IOException;

    int read() throws IOException;

    int read(byte[] bArr) throws IOException;

    int read(byte[] bArr, int i, int i2) throws IOException;

    byte[] readFully(int i) throws IOException;

    void unread(int i) throws IOException;

    void unread(byte[] bArr) throws IOException;
}
