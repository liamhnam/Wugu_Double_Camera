package com.tom_roush.pdfbox.filter;

import android.util.Log;
import com.tom_roush.pdfbox.cos.COSArray;
import com.tom_roush.pdfbox.cos.COSBase;
import com.tom_roush.pdfbox.cos.COSDictionary;
import com.tom_roush.pdfbox.cos.COSName;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public abstract class Filter {
    public abstract DecodeResult decode(InputStream inputStream, OutputStream outputStream, COSDictionary cOSDictionary, int i) throws IOException;

    protected abstract void encode(InputStream inputStream, OutputStream outputStream, COSDictionary cOSDictionary) throws IOException;

    protected Filter() {
    }

    public final void encode(InputStream inputStream, OutputStream outputStream, COSDictionary cOSDictionary, int i) throws IOException {
        encode(inputStream, outputStream, cOSDictionary.asUnmodifiableDictionary());
    }

    protected COSDictionary getDecodeParams(COSDictionary cOSDictionary, int i) {
        COSBase dictionaryObject = cOSDictionary.getDictionaryObject(COSName.DECODE_PARMS, COSName.f2253DP);
        if (dictionaryObject instanceof COSDictionary) {
            return (COSDictionary) dictionaryObject;
        }
        if (dictionaryObject instanceof COSArray) {
            COSArray cOSArray = (COSArray) dictionaryObject;
            if (i < cOSArray.size()) {
                return (COSDictionary) cOSArray.getObject(i);
            }
        } else if (dictionaryObject != null) {
            Log.e("PdfBox-Android", "Expected DecodeParams to be an Array or Dictionary but found " + dictionaryObject.getClass().getName());
        }
        return new COSDictionary();
    }
}
