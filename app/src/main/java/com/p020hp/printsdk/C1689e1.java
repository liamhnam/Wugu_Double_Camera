package com.p020hp.printsdk;

import java.io.EOFException;
import java.io.FilterInputStream;
import java.io.InputStream;
import java.nio.ByteBuffer;
import kotlin.jvm.internal.Intrinsics;

public final class C1689e1 extends FilterInputStream {

    public final byte[] f1188a;

    public final ByteBuffer f1189b;

    public C1689e1(InputStream inputStream) {
        super(inputStream);
        byte[] bArr = new byte[8];
        this.f1188a = bArr;
        this.f1189b = ByteBuffer.wrap(bArr);
    }

    public final short m509b(int i) throws EOFException {
        byte[] b = this.f1188a;
        Intrinsics.checkNotNullParameter(b, "b");
        if (read(b, 0, i) != i) {
            throw new EOFException();
        }
        this.f1189b.rewind();
        return this.f1189b.getShort();
    }

    @Override
    public int read() {
        return ((FilterInputStream) this).in.read();
    }

    @Override
    public int read(byte[] b) {
        Intrinsics.checkNotNullParameter(b, "b");
        return ((FilterInputStream) this).in.read(b);
    }

    @Override
    public int read(byte[] b, int i, int i2) {
        Intrinsics.checkNotNullParameter(b, "b");
        return ((FilterInputStream) this).in.read(b, i, i2);
    }

    @Override
    public long skip(long j) {
        return ((FilterInputStream) this).in.skip(j);
    }
}
