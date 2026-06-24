package com.tom_roush.pdfbox.pdmodel.common.function;

import com.tom_roush.pdfbox.cos.COSArray;
import com.tom_roush.pdfbox.cos.COSBase;
import com.tom_roush.pdfbox.cos.COSFloat;
import com.tom_roush.pdfbox.cos.COSName;
import com.tom_roush.pdfbox.cos.COSNumber;
import java.io.IOException;

public class PDFunctionType2 extends PDFunction {

    private final COSArray f2352c0;

    private final COSArray f2353c1;
    private final float exponent;

    @Override
    public int getFunctionType() {
        return 2;
    }

    public PDFunctionType2(COSBase cOSBase) {
        super(cOSBase);
        if (getCOSObject().getDictionaryObject(COSName.f2239C0) == null) {
            COSArray cOSArray = new COSArray();
            this.f2352c0 = cOSArray;
            cOSArray.add((COSBase) new COSFloat(0.0f));
        } else {
            this.f2352c0 = (COSArray) getCOSObject().getDictionaryObject(COSName.f2239C0);
        }
        if (getCOSObject().getDictionaryObject(COSName.f2240C1) == null) {
            COSArray cOSArray2 = new COSArray();
            this.f2353c1 = cOSArray2;
            cOSArray2.add((COSBase) new COSFloat(1.0f));
        } else {
            this.f2353c1 = (COSArray) getCOSObject().getDictionaryObject(COSName.f2240C1);
        }
        this.exponent = getCOSObject().getFloat(COSName.f2284N);
    }

    @Override
    public float[] eval(float[] fArr) throws IOException {
        float fPow = (float) Math.pow(fArr[0], this.exponent);
        int size = this.f2352c0.size();
        float[] fArr2 = new float[size];
        for (int i = 0; i < size; i++) {
            float fFloatValue = ((COSNumber) this.f2352c0.get(i)).floatValue();
            fArr2[i] = fFloatValue + ((((COSNumber) this.f2353c1.get(i)).floatValue() - fFloatValue) * fPow);
        }
        return clipToRange(fArr2);
    }

    public COSArray getC0() {
        return this.f2352c0;
    }

    public COSArray getC1() {
        return this.f2353c1;
    }

    public float getN() {
        return this.exponent;
    }

    @Override
    public String toString() {
        return "FunctionType2{C0: " + getC0() + " C1: " + getC1() + " N: " + getN() + "}";
    }
}
