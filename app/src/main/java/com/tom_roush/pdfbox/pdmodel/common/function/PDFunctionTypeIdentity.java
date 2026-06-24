package com.tom_roush.pdfbox.pdmodel.common.function;

import com.tom_roush.pdfbox.cos.COSBase;
import java.io.IOException;

public class PDFunctionTypeIdentity extends PDFunction {
    @Override
    public float[] eval(float[] fArr) throws IOException {
        return fArr;
    }

    @Override
    public String toString() {
        return "FunctionTypeIdentity";
    }

    public PDFunctionTypeIdentity(COSBase cOSBase) {
        super(null);
    }

    @Override
    public int getFunctionType() {
        throw new UnsupportedOperationException();
    }
}
