package com.tom_roush.pdfbox.pdmodel.font;

import com.tom_roush.fontbox.util.BoundingBox;
import com.tom_roush.pdfbox.cos.COSArray;
import com.tom_roush.pdfbox.cos.COSBase;
import com.tom_roush.pdfbox.cos.COSDictionary;
import com.tom_roush.pdfbox.cos.COSName;
import com.tom_roush.pdfbox.cos.COSNumber;
import com.tom_roush.pdfbox.pdmodel.common.COSObjectable;
import com.tom_roush.pdfbox.util.Matrix;
import com.tom_roush.pdfbox.util.Vector;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public abstract class PDCIDFont implements COSObjectable, PDFontLike, PDVectorFont {
    private float averageWidth;
    private float defaultWidth;
    protected final COSDictionary dict;
    private float[] dw2;
    private PDFontDescriptor fontDescriptor;
    protected final PDType0Font parent;
    private Map<Integer, Float> widths;
    private final Map<Integer, Float> verticalDisplacementY = new HashMap();
    private final Map<Integer, Vector> positionVectors = new HashMap();

    public abstract int codeToCID(int i);

    public abstract int codeToGID(int i) throws IOException;

    protected abstract byte[] encode(int i) throws IOException;

    @Override
    public abstract BoundingBox getBoundingBox() throws IOException;

    @Override
    public abstract Matrix getFontMatrix();

    @Override
    public abstract float getHeight(int i) throws IOException;

    @Override
    public abstract float getWidthFromFont(int i) throws IOException;

    @Override
    public abstract boolean isEmbedded();

    PDCIDFont(COSDictionary cOSDictionary, PDType0Font pDType0Font) throws IOException {
        this.dict = cOSDictionary;
        this.parent = pDType0Font;
        readWidths();
        readVerticalDisplacements();
    }

    private void readWidths() {
        this.widths = new HashMap();
        COSArray cOSArray = (COSArray) this.dict.getDictionaryObject(COSName.f2321W);
        if (cOSArray != null) {
            int size = cOSArray.size();
            int i = 0;
            while (i < size) {
                int i2 = i + 1;
                COSNumber cOSNumber = (COSNumber) cOSArray.getObject(i);
                int i3 = i2 + 1;
                COSBase object = cOSArray.getObject(i2);
                if (object instanceof COSArray) {
                    COSArray cOSArray2 = (COSArray) object;
                    int iIntValue = cOSNumber.intValue();
                    int size2 = cOSArray2.size();
                    for (int i4 = 0; i4 < size2; i4++) {
                        this.widths.put(Integer.valueOf(iIntValue + i4), Float.valueOf(((COSNumber) cOSArray2.get(i4)).floatValue()));
                    }
                    i = i3;
                } else {
                    int i5 = i3 + 1;
                    COSNumber cOSNumber2 = (COSNumber) cOSArray.getObject(i3);
                    int iIntValue2 = ((COSNumber) object).intValue();
                    float fFloatValue = cOSNumber2.floatValue();
                    for (int iIntValue3 = cOSNumber.intValue(); iIntValue3 <= iIntValue2; iIntValue3++) {
                        this.widths.put(Integer.valueOf(iIntValue3), Float.valueOf(fFloatValue));
                    }
                    i = i5;
                }
            }
        }
    }

    private void readVerticalDisplacements() {
        COSArray cOSArray = (COSArray) this.dict.getDictionaryObject(COSName.DW2);
        if (cOSArray != null) {
            this.dw2 = new float[]{((COSNumber) cOSArray.get(0)).floatValue(), 0.0f};
            this.dw2[1] = ((COSNumber) cOSArray.get(1)).floatValue();
        } else {
            this.dw2 = new float[]{880.0f, -1000.0f};
        }
        COSArray cOSArray2 = (COSArray) this.dict.getDictionaryObject(COSName.f2322W2);
        if (cOSArray2 != null) {
            int i = 0;
            while (i < cOSArray2.size()) {
                COSNumber cOSNumber = (COSNumber) cOSArray2.get(i);
                int i2 = i + 1;
                COSBase cOSBase = cOSArray2.get(i2);
                if (cOSBase instanceof COSArray) {
                    COSArray cOSArray3 = (COSArray) cOSBase;
                    int i3 = 0;
                    while (i3 < cOSArray3.size()) {
                        int iIntValue = cOSNumber.intValue() + i3;
                        COSNumber cOSNumber2 = (COSNumber) cOSArray3.get(i3);
                        int i4 = i3 + 1;
                        COSNumber cOSNumber3 = (COSNumber) cOSArray3.get(i4);
                        int i5 = i4 + 1;
                        COSNumber cOSNumber4 = (COSNumber) cOSArray3.get(i5);
                        this.verticalDisplacementY.put(Integer.valueOf(iIntValue), Float.valueOf(cOSNumber2.floatValue()));
                        this.positionVectors.put(Integer.valueOf(iIntValue), new Vector(cOSNumber3.floatValue(), cOSNumber4.floatValue()));
                        i3 = i5 + 1;
                    }
                } else {
                    int iIntValue2 = ((COSNumber) cOSBase).intValue();
                    int i6 = i2 + 1;
                    COSNumber cOSNumber5 = (COSNumber) cOSArray2.get(i6);
                    int i7 = i6 + 1;
                    COSNumber cOSNumber6 = (COSNumber) cOSArray2.get(i7);
                    i2 = i7 + 1;
                    COSNumber cOSNumber7 = (COSNumber) cOSArray2.get(i2);
                    for (int iIntValue3 = cOSNumber.intValue(); iIntValue3 <= iIntValue2; iIntValue3++) {
                        this.verticalDisplacementY.put(Integer.valueOf(iIntValue3), Float.valueOf(cOSNumber5.floatValue()));
                        this.positionVectors.put(Integer.valueOf(iIntValue3), new Vector(cOSNumber6.floatValue(), cOSNumber7.floatValue()));
                    }
                }
                i = i2 + 1;
            }
        }
    }

    @Override
    public COSDictionary getCOSObject() {
        return this.dict;
    }

    public String getBaseFont() {
        return this.dict.getNameAsString(COSName.BASE_FONT);
    }

    @Override
    public String getName() {
        return getBaseFont();
    }

    @Override
    public PDFontDescriptor getFontDescriptor() {
        COSDictionary cOSDictionary;
        if (this.fontDescriptor == null && (cOSDictionary = (COSDictionary) this.dict.getDictionaryObject(COSName.FONT_DESC)) != null) {
            this.fontDescriptor = new PDFontDescriptor(cOSDictionary);
        }
        return this.fontDescriptor;
    }

    public final PDType0Font getParent() {
        return this.parent;
    }

    private float getDefaultWidth() {
        if (this.defaultWidth == 0.0f) {
            COSNumber cOSNumber = (COSNumber) this.dict.getDictionaryObject(COSName.f2257DW);
            if (cOSNumber != null) {
                this.defaultWidth = cOSNumber.floatValue();
            } else {
                this.defaultWidth = 1000.0f;
            }
        }
        return this.defaultWidth;
    }

    private Vector getDefaultPositionVector(int i) {
        return new Vector(getWidthForCID(i) / 2.0f, this.dw2[0]);
    }

    private float getWidthForCID(int i) {
        Float fValueOf = this.widths.get(Integer.valueOf(i));
        if (fValueOf == null) {
            fValueOf = Float.valueOf(getDefaultWidth());
        }
        return fValueOf.floatValue();
    }

    @Override
    public Vector getPositionVector(int i) {
        int iCodeToCID = codeToCID(i);
        Vector vector = this.positionVectors.get(Integer.valueOf(iCodeToCID));
        return vector == null ? getDefaultPositionVector(iCodeToCID) : vector;
    }

    public float getVerticalDisplacementVectorY(int i) {
        Float f = this.verticalDisplacementY.get(Integer.valueOf(codeToCID(i)));
        if (f == null) {
            return this.dw2[1];
        }
        return f.floatValue();
    }

    @Override
    public float getWidth(int i) throws IOException {
        return getWidthForCID(codeToCID(i));
    }

    @Override
    public float getAverageFontWidth() {
        float size;
        float fFloatValue;
        if (this.averageWidth == 0.0f) {
            Map<Integer, Float> map = this.widths;
            if (map != null) {
                size = map.size();
                Iterator<Float> it = this.widths.values().iterator();
                fFloatValue = 0.0f;
                while (it.hasNext()) {
                    fFloatValue += it.next().floatValue();
                }
            } else {
                size = 0.0f;
                fFloatValue = 0.0f;
            }
            float f = fFloatValue / size;
            if (f <= 0.0f || Float.isNaN(f)) {
                getDefaultWidth();
            }
        }
        return this.averageWidth;
    }

    public PDCIDSystemInfo getCIDSystemInfo() {
        COSDictionary cOSDictionary = (COSDictionary) this.dict.getDictionaryObject(COSName.CIDSYSTEMINFO);
        if (cOSDictionary != null) {
            return new PDCIDSystemInfo(cOSDictionary);
        }
        return null;
    }
}
