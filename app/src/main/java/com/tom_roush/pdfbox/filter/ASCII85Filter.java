package com.tom_roush.pdfbox.filter;

import com.tom_roush.pdfbox.cos.COSDictionary;
import com.tom_roush.pdfbox.p022io.IOUtils;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

final class ASCII85Filter extends Filter {
    ASCII85Filter() {
    }

    @Override
    public DecodeResult decode(InputStream inputStream, OutputStream outputStream, COSDictionary cOSDictionary, int i) throws Throwable {
        ASCII85InputStream aSCII85InputStream = null;
        try {
            ASCII85InputStream aSCII85InputStream2 = new ASCII85InputStream(inputStream);
            try {
                byte[] bArr = new byte[1024];
                while (true) {
                    int i2 = aSCII85InputStream2.read(bArr, 0, 1024);
                    if (i2 != -1) {
                        outputStream.write(bArr, 0, i2);
                    } else {
                        outputStream.flush();
                        IOUtils.closeQuietly(aSCII85InputStream2);
                        return new DecodeResult(cOSDictionary);
                    }
                }
            } catch (Throwable th) {
                th = th;
                aSCII85InputStream = aSCII85InputStream2;
                IOUtils.closeQuietly(aSCII85InputStream);
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    @Override
    protected void encode(InputStream inputStream, OutputStream outputStream, COSDictionary cOSDictionary) throws IOException {
        ASCII85OutputStream aSCII85OutputStream = new ASCII85OutputStream(outputStream);
        byte[] bArr = new byte[1024];
        while (true) {
            int i = inputStream.read(bArr, 0, 1024);
            if (i != -1) {
                aSCII85OutputStream.write(bArr, 0, i);
            } else {
                aSCII85OutputStream.close();
                outputStream.flush();
                return;
            }
        }
    }
}
