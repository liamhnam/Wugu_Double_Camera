package com.brother.sdk.print.pdl;

import com.brother.sdk.common.CancelToken;
import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class PrintDataBlockCancel extends PrintDataBlock implements CancelToken {
    private boolean mCancel = false;
    private byte[] mCancelData;

    @Override
    protected InputStream next() {
        return null;
    }

    public PrintDataBlockCancel(byte[] bArr) {
        this.mCancelData = bArr;
    }

    @Override
    public int length() {
        return this.mCancelData.length;
    }

    @Override
    protected InputStream init() {
        return new ByteArrayInputStream(this.mCancelData);
    }

    @Override
    public boolean isCancelled() {
        return this.mCancel;
    }

    void cancel() {
        this.mCancel = true;
    }
}
