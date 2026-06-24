package com.tom_roush.pdfbox.pdmodel.graphics.color;

import com.tom_roush.pdfbox.cos.COSArray;
import com.tom_roush.pdfbox.cos.COSBase;
import com.tom_roush.pdfbox.cos.COSName;
import com.tom_roush.pdfbox.cos.COSNumber;
import java.io.IOException;
import java.util.Arrays;

public final class PDColor {
    private final PDColorSpace colorSpace;
    private final float[] components;
    private final COSName patternName;

    public PDColor(COSArray cOSArray, PDColorSpace pDColorSpace) {
        int i = 0;
        if (cOSArray.size() > 0 && (cOSArray.get(cOSArray.size() - 1) instanceof COSName)) {
            this.components = new float[cOSArray.size() - 1];
            while (i < cOSArray.size() - 1) {
                this.components[i] = ((COSNumber) cOSArray.get(i)).floatValue();
                i++;
            }
            this.patternName = (COSName) cOSArray.get(cOSArray.size() - 1);
        } else {
            this.components = new float[cOSArray.size()];
            while (i < cOSArray.size()) {
                this.components[i] = ((COSNumber) cOSArray.get(i)).floatValue();
                i++;
            }
            this.patternName = null;
        }
        this.colorSpace = pDColorSpace;
    }

    public PDColor(float[] fArr, PDColorSpace pDColorSpace) {
        this.components = (float[]) fArr.clone();
        this.patternName = null;
        this.colorSpace = pDColorSpace;
    }

    public PDColor(COSName cOSName, PDColorSpace pDColorSpace) {
        this.components = new float[0];
        this.patternName = cOSName;
        this.colorSpace = pDColorSpace;
    }

    public PDColor(float[] fArr, COSName cOSName, PDColorSpace pDColorSpace) {
        this.components = (float[]) fArr.clone();
        this.patternName = cOSName;
        this.colorSpace = pDColorSpace;
    }

    public float[] getComponents() {
        return (float[]) this.components.clone();
    }

    public COSName getPatternName() {
        return this.patternName;
    }

    public boolean isPattern() {
        return this.patternName != null;
    }

    public int toRGB() throws IOException {
        float[] rgb = this.colorSpace.toRGB(this.components);
        int iRound = Math.round(rgb[0] * 255.0f);
        return (((iRound << 8) + Math.round(rgb[1] * 255.0f)) << 8) + Math.round(rgb[2] * 255.0f);
    }

    public COSArray toCOSArray() {
        COSArray cOSArray = new COSArray();
        cOSArray.setFloatArray(this.components);
        COSName cOSName = this.patternName;
        if (cOSName != null) {
            cOSArray.add((COSBase) cOSName);
        }
        return cOSArray;
    }

    public PDColorSpace getColorSpace() {
        return this.colorSpace;
    }

    public String toString() {
        return "PDColor{components=" + Arrays.toString(this.components) + ", patternName=" + this.patternName + "}";
    }
}
