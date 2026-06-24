package com.tom_roush.pdfbox.pdmodel.graphics;

import com.tom_roush.pdfbox.cos.COSArray;
import com.tom_roush.pdfbox.cos.COSBase;
import com.tom_roush.pdfbox.cos.COSInteger;
import com.tom_roush.pdfbox.pdmodel.common.COSArrayList;
import com.tom_roush.pdfbox.pdmodel.common.COSObjectable;
import java.util.Arrays;

public final class PDLineDashPattern implements COSObjectable {
    private final float[] array;
    private final int phase;

    public PDLineDashPattern() {
        this.array = new float[0];
        this.phase = 0;
    }

    public PDLineDashPattern(COSArray cOSArray, int i) {
        this.array = cOSArray.toFloatArray();
        this.phase = i;
    }

    @Override
    public COSBase getCOSObject() {
        COSArray cOSArray = new COSArray();
        cOSArray.add((COSBase) COSArrayList.converterToCOSArray(Arrays.asList(this.array)));
        cOSArray.add((COSBase) COSInteger.get(this.phase));
        return cOSArray;
    }

    public int getPhase() {
        return this.phase;
    }

    public float[] getDashArray() {
        return (float[]) this.array.clone();
    }
}
