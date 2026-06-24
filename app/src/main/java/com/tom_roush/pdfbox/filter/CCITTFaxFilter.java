package com.tom_roush.pdfbox.filter;

import com.tom_roush.pdfbox.cos.COSDictionary;
import com.tom_roush.pdfbox.cos.COSName;
import com.tom_roush.pdfbox.filter.ccitt.CCITTFaxG31DDecodeInputStream;
import com.tom_roush.pdfbox.filter.ccitt.FillOrderChangeInputStream;
import com.tom_roush.pdfbox.filter.ccitt.TIFFFaxDecoder;
import com.tom_roush.pdfbox.p022io.IOUtils;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

final class CCITTFaxFilter extends Filter {
    CCITTFaxFilter() {
    }

    @Override
    public DecodeResult decode(InputStream inputStream, OutputStream outputStream, COSDictionary cOSDictionary, int i) throws IOException {
        int iMax;
        byte[] byteArray;
        DecodeResult decodeResult = new DecodeResult(new COSDictionary());
        decodeResult.getParameters().addAll(cOSDictionary);
        COSDictionary decodeParams = getDecodeParams(cOSDictionary, i);
        int i2 = decodeParams.getInt(COSName.COLUMNS, 1728);
        int i3 = decodeParams.getInt(COSName.ROWS, 0);
        int i4 = cOSDictionary.getInt(COSName.HEIGHT, COSName.f2266H, 0);
        if (i3 > 0 && i4 > 0) {
            iMax = Math.min(i3, i4);
        } else {
            iMax = Math.max(i3, i4);
        }
        int i5 = iMax;
        int i6 = decodeParams.getInt(COSName.f2274K, 0);
        boolean z = decodeParams.getBoolean(COSName.ENCODED_BYTE_ALIGN, false);
        int i7 = ((i2 + 7) / 8) * i5;
        if (i6 == 0) {
            FillOrderChangeInputStream fillOrderChangeInputStream = new FillOrderChangeInputStream(new CCITTFaxG31DDecodeInputStream(inputStream, i2, i5, z));
            byteArray = IOUtils.toByteArray(fillOrderChangeInputStream);
            fillOrderChangeInputStream.close();
        } else {
            TIFFFaxDecoder tIFFFaxDecoder = new TIFFFaxDecoder(1, i2, i5);
            byte[] byteArray2 = IOUtils.toByteArray(inputStream);
            byte[] bArr = new byte[i7];
            if (i6 > 0) {
                tIFFFaxDecoder.decode2D(bArr, byteArray2, 0, i5, 0L);
            } else {
                tIFFFaxDecoder.decodeT6(bArr, byteArray2, 0, i5, 0L, z);
            }
            byteArray = bArr;
        }
        if (!decodeParams.getBoolean(COSName.BLACK_IS_1, false)) {
            invertBitmap(byteArray);
        }
        if (!cOSDictionary.containsKey(COSName.COLORSPACE)) {
            decodeResult.getParameters().setName(COSName.COLORSPACE, COSName.DEVICEGRAY.getName());
        }
        outputStream.write(byteArray);
        return new DecodeResult(cOSDictionary);
    }

    private void invertBitmap(byte[] bArr) {
        int length = bArr.length;
        for (int i = 0; i < length; i++) {
            bArr[i] = (byte) ((~bArr[i]) & 255);
        }
    }

    @Override
    protected void encode(InputStream inputStream, OutputStream outputStream, COSDictionary cOSDictionary) throws IOException {
        throw new UnsupportedEncodingException("CCITTFaxDecode encoding is not implemented, use the CCITTFactory methods instead.");
    }
}
