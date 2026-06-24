package com.brother.sdk.print.pdl;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

class PrintDataBlockLastPage extends PrintDataBlock {
    private byte[] mLastPage;

    @Override
    protected InputStream next() {
        return null;
    }

    public PrintDataBlockLastPage(byte[] bArr) {
        this.mLastPage = bArr;
    }

    @Override
    public int length() {
        return this.mLastPage.length;
    }

    @Override
    protected InputStream init() {
        return new ByteArrayInputStream(this.mLastPage);
    }
}
