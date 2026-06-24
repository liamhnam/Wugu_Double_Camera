package org.apache.http.impl.conn;

import java.io.IOException;
import java.io.InputStream;

class LoggingInputStream extends InputStream {

    private final InputStream f2770in;
    private final Wire wire;

    public LoggingInputStream(InputStream inputStream, Wire wire) {
        this.f2770in = inputStream;
        this.wire = wire;
    }

    @Override
    public int available() {
        return this.f2770in.available();
    }

    @Override
    public void close() throws IOException {
        this.f2770in.close();
    }

    @Override
    public synchronized void mark(int i) {
        super.mark(i);
    }

    @Override
    public boolean markSupported() {
        return false;
    }

    @Override
    public int read() throws IOException {
        int i = this.f2770in.read();
        if (i != -1) {
            this.wire.input(i);
        }
        return i;
    }

    @Override
    public int read(byte[] bArr) throws IOException {
        int i = this.f2770in.read(bArr);
        if (i != -1) {
            this.wire.input(bArr, 0, i);
        }
        return i;
    }

    @Override
    public int read(byte[] bArr, int i, int i2) throws IOException {
        int i3 = this.f2770in.read(bArr, i, i2);
        if (i3 != -1) {
            this.wire.input(bArr, i, i3);
        }
        return i3;
    }

    @Override
    public synchronized void reset() {
        super.reset();
    }

    @Override
    public long skip(long j) {
        return super.skip(j);
    }
}
