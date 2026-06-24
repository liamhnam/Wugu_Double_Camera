package com.tom_roush.pdfbox.cos;

import com.tom_roush.pdfbox.filter.Filter;
import com.tom_roush.pdfbox.p022io.ScratchFile;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

public final class COSOutputStream extends FilterOutputStream {
    private ByteArrayOutputStream buffer;
    private final List<Filter> filters;
    private final COSDictionary parameters;

    @Override
    public void flush() throws IOException {
    }

    COSOutputStream(List<Filter> list, COSDictionary cOSDictionary, OutputStream outputStream, ScratchFile scratchFile) {
        super(outputStream);
        this.buffer = new ByteArrayOutputStream();
        this.filters = list;
        this.parameters = cOSDictionary;
    }

    @Override
    public void write(byte[] bArr) throws IOException {
        this.buffer.write(bArr);
    }

    @Override
    public void write(byte[] bArr, int i, int i2) throws IOException {
        this.buffer.write(bArr, i, i2);
    }

    @Override
    public void write(int i) throws IOException {
        this.buffer.write(i);
    }

    @Override
    public void close() throws IOException {
        for (int size = this.filters.size() - 1; size >= 0; size--) {
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(this.buffer.toByteArray());
            this.buffer = new ByteArrayOutputStream();
            this.filters.get(size).encode(byteArrayInputStream, this.buffer, this.parameters, size);
        }
        this.out.write(this.buffer.toByteArray());
        super.close();
    }
}
