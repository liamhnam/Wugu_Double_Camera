package com.tom_roush.pdfbox.pdmodel.common.function;

import com.tom_roush.pdfbox.cos.COSArray;
import com.tom_roush.pdfbox.cos.COSBase;
import com.tom_roush.pdfbox.cos.COSDictionary;
import com.tom_roush.pdfbox.cos.COSName;
import com.tom_roush.pdfbox.cos.COSObject;
import com.tom_roush.pdfbox.cos.COSStream;
import com.tom_roush.pdfbox.pdmodel.common.COSObjectable;
import com.tom_roush.pdfbox.pdmodel.common.PDRange;
import com.tom_roush.pdfbox.pdmodel.common.PDStream;
import java.io.IOException;

public abstract class PDFunction implements COSObjectable {
    private COSDictionary functionDictionary;
    private PDStream functionStream;
    private COSArray domain = null;
    private COSArray range = null;
    private int numberOfInputValues = -1;
    private int numberOfOutputValues = -1;

    protected float clipToRange(float f, float f2, float f3) {
        return f < f2 ? f2 : f > f3 ? f3 : f;
    }

    public abstract float[] eval(float[] fArr) throws IOException;

    public abstract int getFunctionType();

    protected float interpolate(float f, float f2, float f3, float f4, float f5) {
        return f4 + (((f - f2) * (f5 - f4)) / (f3 - f2));
    }

    public PDFunction(COSBase cOSBase) {
        this.functionStream = null;
        this.functionDictionary = null;
        if (cOSBase instanceof COSStream) {
            PDStream pDStream = new PDStream((COSStream) cOSBase);
            this.functionStream = pDStream;
            pDStream.getCOSObject().setItem(COSName.TYPE, (COSBase) COSName.FUNCTION);
        } else if (cOSBase instanceof COSDictionary) {
            this.functionDictionary = (COSDictionary) cOSBase;
        }
    }

    @Override
    public COSDictionary getCOSObject() {
        PDStream pDStream = this.functionStream;
        if (pDStream != null) {
            return pDStream.getCOSObject();
        }
        return this.functionDictionary;
    }

    protected PDStream getPDStream() {
        return this.functionStream;
    }

    public static PDFunction create(COSBase cOSBase) throws IOException {
        if (cOSBase == COSName.IDENTITY) {
            return new PDFunctionTypeIdentity(null);
        }
        if (cOSBase instanceof COSObject) {
            cOSBase = ((COSObject) cOSBase).getObject();
        }
        COSDictionary cOSDictionary = (COSDictionary) cOSBase;
        int i = cOSDictionary.getInt(COSName.FUNCTION_TYPE);
        if (i == 0) {
            return new PDFunctionType0(cOSDictionary);
        }
        if (i == 2) {
            return new PDFunctionType2(cOSDictionary);
        }
        if (i == 3) {
            return new PDFunctionType3(cOSDictionary);
        }
        if (i == 4) {
            return new PDFunctionType4(cOSDictionary);
        }
        throw new IOException("Error: Unknown function type " + i);
    }

    public int getNumberOfOutputParameters() {
        if (this.numberOfOutputValues == -1) {
            this.numberOfOutputValues = getRangeValues().size() / 2;
        }
        return this.numberOfOutputValues;
    }

    public PDRange getRangeForOutput(int i) {
        return new PDRange(getRangeValues(), i);
    }

    public void setRangeValues(COSArray cOSArray) {
        this.range = cOSArray;
        getCOSObject().setItem(COSName.RANGE, (COSBase) cOSArray);
    }

    public int getNumberOfInputParameters() {
        if (this.numberOfInputValues == -1) {
            this.numberOfInputValues = getDomainValues().size() / 2;
        }
        return this.numberOfInputValues;
    }

    public PDRange getDomainForInput(int i) {
        return new PDRange(getDomainValues(), i);
    }

    public void setDomainValues(COSArray cOSArray) {
        this.domain = cOSArray;
        getCOSObject().setItem(COSName.DOMAIN, (COSBase) cOSArray);
    }

    public COSArray eval(COSArray cOSArray) throws IOException {
        float[] fArrEval = eval(cOSArray.toFloatArray());
        COSArray cOSArray2 = new COSArray();
        cOSArray2.setFloatArray(fArrEval);
        return cOSArray2;
    }

    protected COSArray getRangeValues() {
        if (this.range == null) {
            this.range = (COSArray) getCOSObject().getDictionaryObject(COSName.RANGE);
        }
        return this.range;
    }

    private COSArray getDomainValues() {
        if (this.domain == null) {
            this.domain = (COSArray) getCOSObject().getDictionaryObject(COSName.DOMAIN);
        }
        return this.domain;
    }

    protected float[] clipToRange(float[] fArr) {
        COSArray rangeValues = getRangeValues();
        if (rangeValues == null) {
            return fArr;
        }
        float[] floatArray = rangeValues.toFloatArray();
        int length = floatArray.length / 2;
        float[] fArr2 = new float[length];
        for (int i = 0; i < length; i++) {
            int i2 = i << 1;
            fArr2[i] = clipToRange(fArr[i], floatArray[i2], floatArray[i2 + 1]);
        }
        return fArr2;
    }

    public String toString() {
        return "FunctionType" + getFunctionType();
    }
}
