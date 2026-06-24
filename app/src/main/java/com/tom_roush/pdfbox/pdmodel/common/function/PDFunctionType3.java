package com.tom_roush.pdfbox.pdmodel.common.function;

import com.tom_roush.pdfbox.cos.COSArray;
import com.tom_roush.pdfbox.cos.COSBase;
import com.tom_roush.pdfbox.cos.COSName;
import com.tom_roush.pdfbox.pdmodel.common.PDRange;
import java.io.IOException;

public class PDFunctionType3 extends PDFunction {
    private COSArray bounds;
    private COSArray encode;
    private COSArray functions;

    @Override
    public int getFunctionType() {
        return 3;
    }

    public PDFunctionType3(COSBase cOSBase) {
        super(cOSBase);
        this.functions = null;
        this.encode = null;
        this.bounds = null;
    }

    @Override
    public float[] eval(float[] fArr) throws IOException {
        PDFunction pDFunctionCreate;
        float f = fArr[0];
        PDRange domainForInput = getDomainForInput(0);
        float fClipToRange = clipToRange(f, domainForInput.getMin(), domainForInput.getMax());
        COSArray functions = getFunctions();
        if (functions.size() == 1) {
            pDFunctionCreate = PDFunction.create(functions.get(0));
            PDRange encodeForParameter = getEncodeForParameter(0);
            fClipToRange = interpolate(fClipToRange, domainForInput.getMin(), domainForInput.getMax(), encodeForParameter.getMin(), encodeForParameter.getMax());
        } else {
            float[] floatArray = getBounds().toFloatArray();
            int length = floatArray.length;
            int i = length + 2;
            float[] fArr2 = new float[i];
            fArr2[0] = domainForInput.getMin();
            int i2 = i - 1;
            fArr2[i2] = domainForInput.getMax();
            System.arraycopy(floatArray, 0, fArr2, 1, length);
            for (int i3 = 0; i3 < i2; i3++) {
                if (fClipToRange >= fArr2[i3]) {
                    int i4 = i3 + 1;
                    float f2 = fArr2[i4];
                    if (fClipToRange < f2 || (i3 == i - 2 && fClipToRange == f2)) {
                        pDFunctionCreate = PDFunction.create(functions.get(i3));
                        PDRange encodeForParameter2 = getEncodeForParameter(i3);
                        fClipToRange = interpolate(fClipToRange, fArr2[i3], fArr2[i4], encodeForParameter2.getMin(), encodeForParameter2.getMax());
                        break;
                    }
                }
            }
            pDFunctionCreate = null;
        }
        if (pDFunctionCreate == null) {
            throw new IOException("partition not found in type 3 function");
        }
        return clipToRange(pDFunctionCreate.eval(new float[]{fClipToRange}));
    }

    public COSArray getFunctions() {
        if (this.functions == null) {
            this.functions = (COSArray) getCOSObject().getDictionaryObject(COSName.FUNCTIONS);
        }
        return this.functions;
    }

    public COSArray getBounds() {
        if (this.bounds == null) {
            this.bounds = (COSArray) getCOSObject().getDictionaryObject(COSName.BOUNDS);
        }
        return this.bounds;
    }

    public COSArray getEncode() {
        if (this.encode == null) {
            this.encode = (COSArray) getCOSObject().getDictionaryObject(COSName.ENCODE);
        }
        return this.encode;
    }

    private PDRange getEncodeForParameter(int i) {
        return new PDRange(getEncode(), i);
    }
}
