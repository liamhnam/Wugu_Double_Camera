package com.tom_roush.pdfbox.pdmodel.graphics.shading;

import com.tom_roush.pdfbox.cos.COSArray;
import com.tom_roush.pdfbox.cos.COSBase;
import com.tom_roush.pdfbox.cos.COSDictionary;
import com.tom_roush.pdfbox.cos.COSFloat;
import com.tom_roush.pdfbox.cos.COSName;
import com.tom_roush.pdfbox.util.Matrix;

public class PDShadingType1 extends PDShading {
    private COSArray domain;

    @Override
    public int getShadingType() {
        return 1;
    }

    public PDShadingType1(COSDictionary cOSDictionary) {
        super(cOSDictionary);
        this.domain = null;
    }

    public Matrix getMatrix() {
        COSArray cOSArray = (COSArray) getCOSObject().getDictionaryObject(COSName.MATRIX);
        if (cOSArray != null) {
            return new Matrix(cOSArray);
        }
        return new Matrix();
    }

    public void setMatrix(android.graphics.Matrix matrix) {
        COSArray cOSArray = new COSArray();
        float[] fArr = new float[9];
        matrix.getValues(fArr);
        for (int i = 0; i < 9; i++) {
            cOSArray.add((COSBase) new COSFloat(fArr[i]));
        }
        getCOSObject().setItem(COSName.MATRIX, (COSBase) cOSArray);
    }

    public COSArray getDomain() {
        if (this.domain == null) {
            this.domain = (COSArray) getCOSObject().getDictionaryObject(COSName.DOMAIN);
        }
        return this.domain;
    }

    public void setDomain(COSArray cOSArray) {
        this.domain = cOSArray;
        getCOSObject().setItem(COSName.DOMAIN, (COSBase) cOSArray);
    }
}
