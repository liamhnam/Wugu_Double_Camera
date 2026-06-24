package com.brother.sdk.print.pdl;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

class PrintDataBlockJob extends PrintDataBlock {
    private byte[] mJobData;

    @Override
    protected InputStream next() {
        return null;
    }

    public PrintDataBlockJob(byte[] bArr) {
        this.mJobData = bArr;
    }

    @Override
    public int length() {
        return this.mJobData.length;
    }

    @Override
    protected InputStream init() {
        return new ByteArrayInputStream(this.mJobData);
    }
}
