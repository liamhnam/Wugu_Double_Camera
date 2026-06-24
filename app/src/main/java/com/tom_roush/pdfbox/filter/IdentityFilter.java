package com.tom_roush.pdfbox.filter;

import com.tom_roush.pdfbox.cos.COSDictionary;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

final class IdentityFilter extends Filter {
    private static final int BUFFER_SIZE = 1024;

    IdentityFilter() {
    }

    @Override
    public DecodeResult decode(InputStream inputStream, OutputStream outputStream, COSDictionary cOSDictionary, int i) throws IOException {
        byte[] bArr = new byte[1024];
        while (true) {
            int i2 = inputStream.read(bArr, 0, 1024);
            if (i2 != -1) {
                outputStream.write(bArr, 0, i2);
            } else {
                outputStream.flush();
                return new DecodeResult(cOSDictionary);
            }
        }
    }

    @Override
    protected void encode(InputStream inputStream, OutputStream outputStream, COSDictionary cOSDictionary) throws IOException {
        byte[] bArr = new byte[1024];
        while (true) {
            int i = inputStream.read(bArr, 0, 1024);
            if (i != -1) {
                outputStream.write(bArr, 0, i);
            } else {
                outputStream.flush();
                return;
            }
        }
    }
}
