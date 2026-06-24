package com.tom_roush.pdfbox.pdmodel.graphics.state;

import android.graphics.Paint;
import com.tom_roush.pdfbox.cos.COSArray;
import com.tom_roush.pdfbox.cos.COSBase;
import com.tom_roush.pdfbox.cos.COSDictionary;
import com.tom_roush.pdfbox.cos.COSFloat;
import com.tom_roush.pdfbox.cos.COSName;
import com.tom_roush.pdfbox.cos.COSNumber;
import com.tom_roush.pdfbox.pdmodel.common.COSObjectable;
import com.tom_roush.pdfbox.pdmodel.graphics.PDFontSetting;
import com.tom_roush.pdfbox.pdmodel.graphics.PDLineDashPattern;
import com.tom_roush.pdfbox.pdmodel.graphics.blend.BlendMode;
import java.io.IOException;

public class PDExtendedGraphicsState implements COSObjectable {
    private final COSDictionary dict;

    public PDExtendedGraphicsState() {
        COSDictionary cOSDictionary = new COSDictionary();
        this.dict = cOSDictionary;
        cOSDictionary.setItem(COSName.TYPE, (COSBase) COSName.EXT_G_STATE);
    }

    public PDExtendedGraphicsState(COSDictionary cOSDictionary) {
        this.dict = cOSDictionary;
    }

    public void copyIntoGraphicsState(PDGraphicsState pDGraphicsState) throws IOException {
        for (COSName cOSName : this.dict.keySet()) {
            if (cOSName.equals(COSName.f2280LW)) {
                pDGraphicsState.setLineWidth(getLineWidth().floatValue());
            } else if (cOSName.equals(COSName.f2276LC)) {
                pDGraphicsState.setLineCap(getLineCapStyle());
            } else if (cOSName.equals(COSName.f2278LJ)) {
                pDGraphicsState.setLineJoin(getLineJoinStyle());
            } else if (cOSName.equals(COSName.f2283ML)) {
                pDGraphicsState.setMiterLimit(getMiterLimit().floatValue());
            } else if (cOSName.equals(COSName.f2248D)) {
                pDGraphicsState.setLineDashPattern(getLineDashPattern());
            } else if (cOSName.equals(COSName.f2299RI)) {
                pDGraphicsState.setRenderingIntent(getRenderingIntent());
            } else if (cOSName.equals(COSName.OPM)) {
                pDGraphicsState.setOverprintMode(getOverprintMode().doubleValue());
            } else if (cOSName.equals(COSName.FONT)) {
                PDFontSetting fontSetting = getFontSetting();
                if (fontSetting != null) {
                    pDGraphicsState.getTextState().setFont(fontSetting.getFont());
                    pDGraphicsState.getTextState().setFontSize(fontSetting.getFontSize());
                }
            } else if (cOSName.equals(COSName.f2263FL)) {
                pDGraphicsState.setFlatness(getFlatnessTolerance().floatValue());
            } else if (cOSName.equals(COSName.f2304SM)) {
                pDGraphicsState.setSmoothness(getSmoothnessTolerance().floatValue());
            } else if (cOSName.equals(COSName.f2302SA)) {
                pDGraphicsState.setStrokeAdjustment(getAutomaticStrokeAdjustment());
            } else if (cOSName.equals(COSName.f2241CA)) {
                pDGraphicsState.setAlphaConstant(getStrokingAlphaConstant().floatValue());
            } else if (cOSName.equals(COSName.CA_NS)) {
                pDGraphicsState.setNonStrokeAlphaConstant(getNonStrokingAlphaConstant().floatValue());
            } else if (cOSName.equals(COSName.AIS)) {
                pDGraphicsState.setAlphaSource(getAlphaSourceFlag());
            } else if (cOSName.equals(COSName.f2312TK)) {
                pDGraphicsState.getTextState().setKnockoutFlag(getTextKnockoutFlag());
            } else if (cOSName.equals(COSName.SMASK)) {
                pDGraphicsState.setSoftMask(getSoftMask());
            } else if (cOSName.equals(COSName.f2236BM)) {
                pDGraphicsState.setBlendMode(getBlendMode());
            } else if (cOSName.equals(COSName.f2314TR)) {
                if (!this.dict.containsKey(COSName.TR2)) {
                    pDGraphicsState.setTransfer(getTransfer());
                }
            } else if (cOSName.equals(COSName.TR2)) {
                pDGraphicsState.setTransfer(getTransfer2());
            }
        }
    }

    @Override
    public COSDictionary getCOSObject() {
        return this.dict;
    }

    public Float getLineWidth() {
        return getFloatItem(COSName.f2280LW);
    }

    public void setLineWidth(Float f) {
        setFloatItem(COSName.f2280LW, f);
    }

    public Paint.Cap getLineCapStyle() {
        int i = this.dict.getInt(COSName.f2276LC);
        if (i == 0) {
            return Paint.Cap.BUTT;
        }
        if (i == 1) {
            return Paint.Cap.ROUND;
        }
        if (i != 2) {
            return null;
        }
        return Paint.Cap.SQUARE;
    }

    public void setLineCapStyle(int i) {
        this.dict.setInt(COSName.f2276LC, i);
    }

    public Paint.Join getLineJoinStyle() {
        int i = this.dict.getInt(COSName.f2278LJ);
        if (i == 0) {
            return Paint.Join.MITER;
        }
        if (i == 1) {
            return Paint.Join.ROUND;
        }
        if (i != 2) {
            return null;
        }
        return Paint.Join.BEVEL;
    }

    public void setLineJoinStyle(int i) {
        this.dict.setInt(COSName.f2278LJ, i);
    }

    public Float getMiterLimit() {
        return getFloatItem(COSName.f2283ML);
    }

    public void setMiterLimit(Float f) {
        setFloatItem(COSName.f2283ML, f);
    }

    public PDLineDashPattern getLineDashPattern() {
        COSArray cOSArray = (COSArray) this.dict.getDictionaryObject(COSName.f2248D);
        if (cOSArray == null) {
            return null;
        }
        COSArray cOSArray2 = new COSArray();
        cOSArray.addAll(cOSArray);
        cOSArray.remove(cOSArray.size() - 1);
        return new PDLineDashPattern(cOSArray2, cOSArray.getInt(cOSArray.size() - 1));
    }

    public void setLineDashPattern(PDLineDashPattern pDLineDashPattern) {
        this.dict.setItem(COSName.f2248D, pDLineDashPattern.getCOSObject());
    }

    public RenderingIntent getRenderingIntent() {
        String nameAsString = this.dict.getNameAsString("RI");
        if (nameAsString != null) {
            return RenderingIntent.fromString(nameAsString);
        }
        return null;
    }

    public void setRenderingIntent(String str) {
        this.dict.setName("RI", str);
    }

    public boolean getStrokingOverprintControl() {
        return this.dict.getBoolean(COSName.f2290OP, false);
    }

    public void setStrokingOverprintControl(boolean z) {
        this.dict.setBoolean(COSName.f2290OP, z);
    }

    public boolean getNonStrokingOverprintControl() {
        return this.dict.getBoolean(COSName.OP_NS, getStrokingOverprintControl());
    }

    public void setNonStrokingOverprintControl(boolean z) {
        this.dict.setBoolean(COSName.OP_NS, z);
    }

    public Float getOverprintMode() {
        return getFloatItem(COSName.OPM);
    }

    public void setOverprintMode(Float f) {
        setFloatItem(COSName.OPM, f);
    }

    public PDFontSetting getFontSetting() {
        COSBase dictionaryObject = this.dict.getDictionaryObject(COSName.FONT);
        if (dictionaryObject instanceof COSArray) {
            return new PDFontSetting((COSArray) dictionaryObject);
        }
        return null;
    }

    public void setFontSetting(PDFontSetting pDFontSetting) {
        this.dict.setItem(COSName.FONT, pDFontSetting);
    }

    public Float getFlatnessTolerance() {
        return getFloatItem(COSName.f2263FL);
    }

    public void setFlatnessTolerance(Float f) {
        setFloatItem(COSName.f2263FL, f);
    }

    public Float getSmoothnessTolerance() {
        return getFloatItem(COSName.f2304SM);
    }

    public void setSmoothnessTolerance(Float f) {
        setFloatItem(COSName.f2304SM, f);
    }

    public boolean getAutomaticStrokeAdjustment() {
        return this.dict.getBoolean(COSName.f2302SA, false);
    }

    public void setAutomaticStrokeAdjustment(boolean z) {
        this.dict.setBoolean(COSName.f2302SA, z);
    }

    public Float getStrokingAlphaConstant() {
        return getFloatItem(COSName.f2241CA);
    }

    public void setStrokingAlphaConstant(Float f) {
        setFloatItem(COSName.f2241CA, f);
    }

    public Float getNonStrokingAlphaConstant() {
        return getFloatItem(COSName.CA_NS);
    }

    public void setNonStrokingAlphaConstant(Float f) {
        setFloatItem(COSName.CA_NS, f);
    }

    public boolean getAlphaSourceFlag() {
        return this.dict.getBoolean(COSName.AIS, false);
    }

    public void setAlphaSourceFlag(boolean z) {
        this.dict.setBoolean(COSName.AIS, z);
    }

    public BlendMode getBlendMode() {
        return BlendMode.getInstance(this.dict.getDictionaryObject(COSName.f2236BM));
    }

    public PDSoftMask getSoftMask() {
        return PDSoftMask.create(this.dict.getDictionaryObject(COSName.SMASK));
    }

    public boolean getTextKnockoutFlag() {
        return this.dict.getBoolean(COSName.f2312TK, true);
    }

    public void setTextKnockoutFlag(boolean z) {
        this.dict.setBoolean(COSName.f2312TK, z);
    }

    private Float getFloatItem(COSName cOSName) {
        COSNumber cOSNumber = (COSNumber) this.dict.getDictionaryObject(cOSName);
        if (cOSNumber != null) {
            return Float.valueOf(cOSNumber.floatValue());
        }
        return null;
    }

    private void setFloatItem(COSName cOSName, Float f) {
        if (f == null) {
            this.dict.removeItem(cOSName);
        } else {
            this.dict.setItem(cOSName, (COSBase) new COSFloat(f.floatValue()));
        }
    }

    public COSBase getTransfer() {
        COSBase dictionaryObject = this.dict.getDictionaryObject(COSName.f2314TR);
        if (!(dictionaryObject instanceof COSArray) || ((COSArray) dictionaryObject).size() == 4) {
            return dictionaryObject;
        }
        return null;
    }

    public void setTransfer(COSBase cOSBase) {
        this.dict.setItem(COSName.f2314TR, cOSBase);
    }

    public COSBase getTransfer2() {
        COSBase dictionaryObject = this.dict.getDictionaryObject(COSName.TR2);
        if (!(dictionaryObject instanceof COSArray) || ((COSArray) dictionaryObject).size() == 4) {
            return dictionaryObject;
        }
        return null;
    }

    public void setTransfer2(COSBase cOSBase) {
        this.dict.setItem(COSName.TR2, cOSBase);
    }
}
