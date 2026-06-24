package com.brother.sdk.print.pdl;

import com.brother.sdk.common.IReadStream;
import com.brother.sdk.common.IUnknown;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

public class PDLStream implements IReadStream {
    private PrintDataBlockCancel mCancelBlock;
    private PrintDataBlock mCurrentBlock;
    private List<PrintDataBlock> mData;
    private int mIndex;

    public PDLStream(List<PrintDataBlock> list, PrintDataBlockCancel printDataBlockCancel) {
        this.mData = list;
        this.mCancelBlock = printDataBlockCancel;
        reset();
    }

    @Override
    public int length() {
        Iterator<PrintDataBlock> it = this.mData.iterator();
        int length = 0;
        while (it.hasNext()) {
            length += it.next().length();
        }
        return length;
    }

    @Override
    public int read(byte[] bArr, int i, int i2) throws IOException {
        PrintDataBlockCancel printDataBlockCancel = this.mCancelBlock;
        if (printDataBlockCancel != null && printDataBlockCancel.isCancelled()) {
            return this.mCancelBlock.read(bArr, i, i2);
        }
        int i3 = this.mCurrentBlock.read(bArr, i, i2);
        if (i3 >= 0) {
            return i3;
        }
        int i4 = this.mIndex + 1;
        this.mIndex = i4;
        if (i4 >= this.mData.size()) {
            return i3;
        }
        PrintDataBlock printDataBlock = this.mData.get(this.mIndex);
        this.mCurrentBlock = printDataBlock;
        return printDataBlock.read(bArr, i, i2);
    }

    @Override
    public void reset() {
        this.mIndex = 0;
        this.mCurrentBlock = this.mData.get(0);
    }

    @Override
    public IUnknown queryInterface(String str) {
        if (IUnknown.f479ID.equals(str) || IReadStream.f478ID.equals(str)) {
            return this;
        }
        return null;
    }

    public void cancel() {
        PrintDataBlockCancel printDataBlockCancel = this.mCancelBlock;
        if (printDataBlockCancel != null) {
            printDataBlockCancel.cancel();
        }
    }
}
