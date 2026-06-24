package org.apache.log4j.spi;

import java.io.Writer;

class NullWriter extends Writer {
    @Override
    public void close() {
    }

    @Override
    public void flush() {
    }

    @Override
    public void write(char[] cArr, int i, int i2) {
    }

    NullWriter() {
    }
}
