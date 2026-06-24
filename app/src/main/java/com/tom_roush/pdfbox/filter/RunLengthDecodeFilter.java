package com.tom_roush.pdfbox.filter;

import android.util.Log;
import com.tom_roush.pdfbox.cos.COSDictionary;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

final class RunLengthDecodeFilter extends Filter {
    private static final int RUN_LENGTH_EOD = 128;

    RunLengthDecodeFilter() {
    }

    @Override
    public DecodeResult decode(InputStream inputStream, OutputStream outputStream, COSDictionary cOSDictionary, int i) throws IOException {
        byte[] bArr = new byte[128];
        while (true) {
            int i2 = inputStream.read();
            if (i2 == -1 || i2 == 128) {
                break;
            }
            if (i2 <= 127) {
                int i3 = i2 + 1;
                while (i3 > 0) {
                    int i4 = inputStream.read(bArr, 0, i3);
                    outputStream.write(bArr, 0, i4);
                    i3 -= i4;
                }
            } else {
                int i5 = inputStream.read();
                for (int i6 = 0; i6 < 257 - i2; i6++) {
                    outputStream.write(i5);
                }
            }
        }
        return new DecodeResult(cOSDictionary);
    }

    @Override
    protected void encode(InputStream inputStream, OutputStream outputStream, COSDictionary cOSDictionary) throws IOException {
        Log.w("PdfBox-Android", "RunLengthDecodeFilter.encode is not implemented yet, skipping this stream.");
    }
}
