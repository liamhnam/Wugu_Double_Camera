package com.tom_roush.pdfbox.pdmodel.font.encoding;

import com.tom_roush.pdfbox.cos.COSArray;
import com.tom_roush.pdfbox.cos.COSBase;
import com.tom_roush.pdfbox.cos.COSDictionary;
import com.tom_roush.pdfbox.cos.COSName;
import com.tom_roush.pdfbox.cos.COSNumber;
import java.util.HashMap;
import java.util.Map;

public class DictionaryEncoding extends Encoding {
    private final Encoding baseEncoding;
    private final Map<Integer, String> differences;
    private final COSDictionary encoding;

    public DictionaryEncoding(COSName cOSName, COSArray cOSArray) {
        this.differences = new HashMap();
        COSDictionary cOSDictionary = new COSDictionary();
        this.encoding = cOSDictionary;
        cOSDictionary.setItem(COSName.NAME, (COSBase) COSName.ENCODING);
        cOSDictionary.setItem(COSName.DIFFERENCES, (COSBase) cOSArray);
        if (cOSName != COSName.STANDARD_ENCODING) {
            cOSDictionary.setItem(COSName.BASE_ENCODING, (COSBase) cOSName);
            this.baseEncoding = Encoding.getInstance(cOSName);
        } else {
            this.baseEncoding = Encoding.getInstance(cOSName);
        }
        if (this.baseEncoding == null) {
            throw new IllegalArgumentException("Invalid encoding: " + cOSName);
        }
        this.codeToName.putAll(this.baseEncoding.codeToName);
        this.inverted.putAll(this.baseEncoding.inverted);
        applyDifferences();
    }

    public DictionaryEncoding(COSDictionary cOSDictionary) {
        this.differences = new HashMap();
        this.encoding = cOSDictionary;
        this.baseEncoding = null;
        applyDifferences();
    }

    public DictionaryEncoding(COSDictionary cOSDictionary, boolean z, Encoding encoding) {
        this.differences = new HashMap();
        this.encoding = cOSDictionary;
        Encoding encoding2 = cOSDictionary.containsKey(COSName.BASE_ENCODING) ? Encoding.getInstance(cOSDictionary.getCOSName(COSName.BASE_ENCODING)) : null;
        if (encoding2 != null) {
            encoding = encoding2;
        } else if (z) {
            encoding = StandardEncoding.INSTANCE;
        } else if (encoding == null) {
            throw new IllegalArgumentException("Symbolic fonts must have a built-in encoding");
        }
        this.baseEncoding = encoding;
        this.codeToName.putAll(encoding.codeToName);
        this.inverted.putAll(encoding.inverted);
        applyDifferences();
    }

    private void applyDifferences() {
        COSArray cOSArray = (COSArray) this.encoding.getDictionaryObject(COSName.DIFFERENCES);
        int iIntValue = -1;
        for (int i = 0; cOSArray != null && i < cOSArray.size(); i++) {
            COSBase object = cOSArray.getObject(i);
            if (object instanceof COSNumber) {
                iIntValue = ((COSNumber) object).intValue();
            } else if (object instanceof COSName) {
                COSName cOSName = (COSName) object;
                add(iIntValue, cOSName.getName());
                this.differences.put(Integer.valueOf(iIntValue), cOSName.getName());
                iIntValue++;
            }
        }
    }

    public Encoding getBaseEncoding() {
        return this.baseEncoding;
    }

    public Map<Integer, String> getDifferences() {
        return this.differences;
    }

    @Override
    public COSBase getCOSObject() {
        return this.encoding;
    }

    @Override
    public String getEncodingName() {
        return this.baseEncoding.getEncodingName() + " with differences";
    }
}
