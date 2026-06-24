package com.tom_roush.pdfbox.pdmodel.graphics.color;

import com.tom_roush.pdfbox.cos.COSArray;
import com.tom_roush.pdfbox.cos.COSBase;
import com.tom_roush.pdfbox.cos.COSFloat;
import com.tom_roush.pdfbox.cos.COSNumber;
import com.tom_roush.pdfbox.pdmodel.common.COSObjectable;

public final class PDGamma implements COSObjectable {
    private COSArray values;

    public PDGamma() {
        this.values = null;
        COSArray cOSArray = new COSArray();
        this.values = cOSArray;
        cOSArray.add((COSBase) new COSFloat(0.0f));
        this.values.add((COSBase) new COSFloat(0.0f));
        this.values.add((COSBase) new COSFloat(0.0f));
    }

    public PDGamma(COSArray cOSArray) {
        this.values = cOSArray;
    }

    @Override
    public COSBase getCOSObject() {
        return this.values;
    }

    public COSArray getCOSArray() {
        return this.values;
    }

    public float getR() {
        return ((COSNumber) this.values.get(0)).floatValue();
    }

    public void setR(float f) {
        this.values.set(0, (COSBase) new COSFloat(f));
    }

    public float getG() {
        return ((COSNumber) this.values.get(1)).floatValue();
    }

    public void setG(float f) {
        this.values.set(1, (COSBase) new COSFloat(f));
    }

    public float getB() {
        return ((COSNumber) this.values.get(2)).floatValue();
    }

    public void setB(float f) {
        this.values.set(2, (COSBase) new COSFloat(f));
    }
}
