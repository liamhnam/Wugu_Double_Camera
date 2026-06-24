package com.brother.sdk.print.pdl;

import com.brother.sdk.common.IReadStream;
import com.brother.sdk.common.IUnknown;
import java.io.IOException;
import java.io.InputStream;

public abstract class PrintDataBlock implements IReadStream {
    private InputStream mStream;

    protected abstract InputStream init();

    protected abstract InputStream next();

    protected void finalize() throws Throwable {
        try {
            InputStream inputStream = this.mStream;
            if (inputStream != null) {
                inputStream.close();
            }
        } finally {
            super.finalize();
        }
    }

    @Override
    public int read(byte[] bArr, int i, int i2) throws IOException {
        if (this.mStream == null) {
            this.mStream = init();
        }
        int i3 = this.mStream.read(bArr, i, i2);
        if (i3 >= 0) {
            return i3;
        }
        this.mStream.close();
        InputStream next = next();
        this.mStream = next;
        return next != null ? next.read(bArr, i, i2) : i3;
    }

    @Override
    public void reset() {
        InputStream inputStream = this.mStream;
        if (inputStream != null) {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            this.mStream = null;
        }
    }

    @Override
    public IUnknown queryInterface(String str) {
        if (IUnknown.f479ID.equals(str) || IReadStream.f478ID.equals(str)) {
            return this;
        }
        return null;
    }
}
