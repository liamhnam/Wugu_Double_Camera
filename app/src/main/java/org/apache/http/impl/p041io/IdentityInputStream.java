package org.apache.http.impl.p041io;

import java.io.InputStream;
import org.apache.http.p042io.BufferInfo;
import org.apache.http.p042io.SessionInputBuffer;
import org.apache.http.util.Args;

public class IdentityInputStream extends InputStream {
    private boolean closed = false;

    private final SessionInputBuffer f2776in;

    public IdentityInputStream(SessionInputBuffer sessionInputBuffer) {
        this.f2776in = (SessionInputBuffer) Args.notNull(sessionInputBuffer, "Session input buffer");
    }

    @Override
    public int available() {
        SessionInputBuffer sessionInputBuffer = this.f2776in;
        if (sessionInputBuffer instanceof BufferInfo) {
            return ((BufferInfo) sessionInputBuffer).length();
        }
        return 0;
    }

    @Override
    public void close() {
        this.closed = true;
    }

    @Override
    public int read() {
        if (this.closed) {
            return -1;
        }
        return this.f2776in.read();
    }

    @Override
    public int read(byte[] bArr, int i, int i2) {
        if (this.closed) {
            return -1;
        }
        return this.f2776in.read(bArr, i, i2);
    }
}
