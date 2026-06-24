package com.tom_roush.pdfbox.pdmodel.documentinterchange.taggedpdf;

import com.tom_roush.pdfbox.cos.COSArray;
import com.tom_roush.pdfbox.cos.COSBase;
import com.tom_roush.pdfbox.cos.COSNull;
import com.tom_roush.pdfbox.pdmodel.common.COSObjectable;
import com.tom_roush.pdfbox.pdmodel.graphics.color.PDGamma;

public class PDFourColours implements COSObjectable {
    private final COSArray array;

    public PDFourColours() {
        COSArray cOSArray = new COSArray();
        this.array = cOSArray;
        cOSArray.add((COSBase) COSNull.NULL);
        cOSArray.add((COSBase) COSNull.NULL);
        cOSArray.add((COSBase) COSNull.NULL);
        cOSArray.add((COSBase) COSNull.NULL);
    }

    public PDFourColours(COSArray cOSArray) {
        this.array = cOSArray;
        if (cOSArray.size() < 4) {
            for (int size = cOSArray.size() - 1; size < 4; size++) {
                this.array.add((COSBase) COSNull.NULL);
            }
        }
    }

    public PDGamma getBeforeColour() {
        return getColourByIndex(0);
    }

    public void setBeforeColour(PDGamma pDGamma) {
        setColourByIndex(0, pDGamma);
    }

    public PDGamma getAfterColour() {
        return getColourByIndex(1);
    }

    public void setAfterColour(PDGamma pDGamma) {
        setColourByIndex(1, pDGamma);
    }

    public PDGamma getStartColour() {
        return getColourByIndex(2);
    }

    public void setStartColour(PDGamma pDGamma) {
        setColourByIndex(2, pDGamma);
    }

    public PDGamma getEndColour() {
        return getColourByIndex(3);
    }

    public void setEndColour(PDGamma pDGamma) {
        setColourByIndex(3, pDGamma);
    }

    @Override
    public COSBase getCOSObject() {
        return this.array;
    }

    private PDGamma getColourByIndex(int i) {
        COSBase object = this.array.getObject(i);
        if (object instanceof COSArray) {
            return new PDGamma((COSArray) object);
        }
        return null;
    }

    private void setColourByIndex(int i, PDGamma pDGamma) {
        COSBase cOSArray;
        if (pDGamma == null) {
            cOSArray = COSNull.NULL;
        } else {
            cOSArray = pDGamma.getCOSArray();
        }
        this.array.set(i, cOSArray);
    }
}
