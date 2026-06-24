package com.brother.sdk.print.pdl;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

class PrintDataBlockPage extends PrintDataBlock {
    private File mPageData;
    private byte[] mPageEndData;
    private byte[] mPageStartData;
    private PageState mState = PageState.PageStart;

    private enum PageState {
        PageStart,
        PageData,
        PageEnd
    }

    public PrintDataBlockPage(byte[] bArr, byte[] bArr2, File file) {
        this.mPageStartData = bArr;
        this.mPageEndData = bArr2;
        this.mPageData = file;
    }

    @Override
    public int length() {
        return ((int) (((long) this.mPageStartData.length) + this.mPageData.length())) + this.mPageEndData.length;
    }

    @Override
    protected InputStream init() {
        this.mState = PageState.PageStart;
        return new ByteArrayInputStream(this.mPageStartData);
    }

    @Override
    protected InputStream next() {
        if (this.mState == PageState.PageStart) {
            this.mState = PageState.PageData;
            try {
                return new FileInputStream(this.mPageData);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                return null;
            }
        }
        if (this.mState != PageState.PageData) {
            return null;
        }
        this.mState = PageState.PageEnd;
        return new ByteArrayInputStream(this.mPageEndData);
    }
}
