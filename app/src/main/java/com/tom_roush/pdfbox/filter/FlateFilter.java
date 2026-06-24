package com.tom_roush.pdfbox.filter;

import android.util.Log;
import com.tom_roush.pdfbox.cos.COSDictionary;
import com.tom_roush.pdfbox.cos.COSName;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.zip.DataFormatException;
import java.util.zip.DeflaterOutputStream;
import java.util.zip.Inflater;

final class FlateFilter extends Filter {
    private static final int BUFFER_SIZE = 16348;

    FlateFilter() {
    }

    @Override
    public DecodeResult decode(InputStream inputStream, OutputStream outputStream, COSDictionary cOSDictionary, int i) throws IOException {
        COSDictionary decodeParams = getDecodeParams(cOSDictionary, i);
        int i2 = decodeParams != null ? decodeParams.getInt(COSName.PREDICTOR) : -1;
        try {
            if (i2 > 1) {
                int iMin = Math.min(decodeParams.getInt(COSName.COLORS, 1), 32);
                int i3 = decodeParams.getInt(COSName.BITS_PER_COMPONENT, 8);
                int i4 = decodeParams.getInt(COSName.COLUMNS, 1);
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                decompress(inputStream, byteArrayOutputStream);
                ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
                Predictor.decodePredictor(i2, iMin, i3, i4, byteArrayInputStream, outputStream);
                outputStream.flush();
                byteArrayOutputStream.reset();
                byteArrayInputStream.reset();
            } else {
                decompress(inputStream, outputStream);
            }
            return new DecodeResult(cOSDictionary);
        } catch (DataFormatException e) {
            Log.e("PdfBox-Android", "FlateFilter: stop reading corrupt stream due to a DataFormatException");
            throw new IOException(e);
        }
    }

    private void decompress(InputStream inputStream, OutputStream outputStream) throws DataFormatException, IOException {
        byte[] bArr = new byte[2048];
        inputStream.read(bArr, 0, 2);
        int i = inputStream.read(bArr);
        if (i > 0) {
            Inflater inflater = new Inflater(true);
            inflater.setInput(bArr, 0, i);
            byte[] bArr2 = new byte[2048];
            boolean z = false;
            while (true) {
                try {
                    int iInflate = inflater.inflate(bArr2);
                    if (iInflate != 0) {
                        outputStream.write(bArr2, 0, iInflate);
                        z = true;
                    } else if (inflater.finished() || inflater.needsDictionary() || inputStream.available() == 0) {
                        break;
                    } else {
                        inflater.setInput(bArr, 0, inputStream.read(bArr));
                    }
                } catch (DataFormatException e) {
                    if (z) {
                        Log.w("PdfBox-Android", "FlateFilter: premature end of stream due to a DataFormatException");
                    } else {
                        throw e;
                    }
                }
            }
            inflater.end();
        }
        outputStream.flush();
    }

    @Override
    protected void encode(InputStream inputStream, OutputStream outputStream, COSDictionary cOSDictionary) throws IOException {
        DeflaterOutputStream deflaterOutputStream = new DeflaterOutputStream(outputStream);
        int iAvailable = inputStream.available();
        if (iAvailable > 0) {
            byte[] bArr = new byte[Math.min(iAvailable, BUFFER_SIZE)];
            while (true) {
                int i = inputStream.read(bArr, 0, Math.min(iAvailable, BUFFER_SIZE));
                if (i == -1) {
                    break;
                } else {
                    deflaterOutputStream.write(bArr, 0, i);
                }
            }
        }
        deflaterOutputStream.close();
        outputStream.flush();
    }
}
