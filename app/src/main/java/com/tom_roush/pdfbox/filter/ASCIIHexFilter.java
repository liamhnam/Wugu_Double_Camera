package com.tom_roush.pdfbox.filter;

import android.util.Log;
import com.tom_roush.pdfbox.cos.COSDictionary;
import com.tom_roush.pdfbox.util.Hex;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

final class ASCIIHexFilter extends Filter {
    private static final int[] REVERSE_HEX = {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, -1, -1, -1, -1, -1, -1, -1, 10, 11, 12, 13, 14, 15, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 10, 11, 12, 13, 14, 15};

    private boolean isEOD(int i) {
        return i == 62;
    }

    private boolean isWhitespace(int i) {
        return i == 0 || i == 9 || i == 10 || i == 12 || i == 13 || i == 32;
    }

    ASCIIHexFilter() {
    }

    @Override
    public DecodeResult decode(InputStream inputStream, OutputStream outputStream, COSDictionary cOSDictionary, int i) throws IOException {
        int i2;
        while (true) {
            int i3 = inputStream.read();
            if (i3 == -1) {
                break;
            }
            while (isWhitespace(i3)) {
                i3 = inputStream.read();
            }
            if (i3 == -1 || isEOD(i3)) {
                break;
            }
            int[] iArr = REVERSE_HEX;
            if (iArr[i3] == -1) {
                Log.e("PdfBox-Android", "Invalid hex, int: " + i3 + " char: " + ((char) i3));
            }
            i2 = iArr[i3] * 16;
            int i4 = inputStream.read();
            if (i4 == -1 || isEOD(i4)) {
                break;
            }
            if (i4 >= 0) {
                if (iArr[i4] == -1) {
                    Log.e("PdfBox-Android", "Invalid hex, int: " + i4 + " char: " + ((char) i4));
                }
                i2 += iArr[i4];
            }
            outputStream.write(i2);
        }
        outputStream.write(i2);
        outputStream.flush();
        return new DecodeResult(cOSDictionary);
    }

    @Override
    public void encode(InputStream inputStream, OutputStream outputStream, COSDictionary cOSDictionary) throws IOException {
        while (true) {
            int i = inputStream.read();
            if (i != -1) {
                outputStream.write(Hex.getBytes((byte) i));
            } else {
                outputStream.flush();
                return;
            }
        }
    }
}
