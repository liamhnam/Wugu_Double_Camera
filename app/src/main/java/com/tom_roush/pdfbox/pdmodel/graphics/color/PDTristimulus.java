package com.tom_roush.pdfbox.pdmodel.graphics.color;

import com.tom_roush.pdfbox.cos.COSArray;
import com.tom_roush.pdfbox.cos.COSBase;
import com.tom_roush.pdfbox.cos.COSFloat;
import com.tom_roush.pdfbox.cos.COSNumber;
import com.tom_roush.pdfbox.pdmodel.common.COSObjectable;

public final class PDTristimulus implements COSObjectable {
    private COSArray values;

    public PDTristimulus() {
        this.values = null;
        COSArray cOSArray = new COSArray();
        this.values = cOSArray;
        cOSArray.add((COSBase) new COSFloat(0.0f));
        this.values.add((COSBase) new COSFloat(0.0f));
        this.values.add((COSBase) new COSFloat(0.0f));
    }

    public PDTristimulus(COSArray cOSArray) {
        this.values = cOSArray;
    }

    public PDTristimulus(float[] fArr) {
        this.values = null;
        this.values = new COSArray();
        for (int i = 0; i < fArr.length && i < 3; i++) {
            this.values.add((COSBase) new COSFloat(fArr[i]));
        }
    }

    @Override
    public COSBase getCOSObject() {
        return this.values;
    }

    public float getX() {
        return ((COSNumber) this.values.get(0)).floatValue();
    }

    public void setX(float f) {
        this.values.set(0, (COSBase) new COSFloat(f));
    }

    public float getY() {
        return ((COSNumber) this.values.get(1)).floatValue();
    }

    public void setY(float f) {
        this.values.set(1, (COSBase) new COSFloat(f));
    }

    public float getZ() {
        return ((COSNumber) this.values.get(2)).floatValue();
    }

    public void setZ(float f) {
        this.values.set(2, (COSBase) new COSFloat(f));
    }
}
