package com.tom_roush.pdfbox.p022io;

import java.io.Closeable;
import java.io.IOException;

public interface SequentialRead extends Closeable {
    int read() throws IOException;

    int read(byte[] bArr) throws IOException;

    int read(byte[] bArr, int i, int i2) throws IOException;
}
