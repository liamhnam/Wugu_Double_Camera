package com.tom_roush.pdfbox.pdmodel.interactive.pagenavigation;

import com.tom_roush.pdfbox.cos.COSBase;
import com.tom_roush.pdfbox.cos.COSBoolean;
import com.tom_roush.pdfbox.cos.COSDictionary;
import com.tom_roush.pdfbox.cos.COSFloat;
import com.tom_roush.pdfbox.cos.COSInteger;
import com.tom_roush.pdfbox.cos.COSName;
import com.tom_roush.pdfbox.pdmodel.common.PDDictionaryWrapper;

public final class PDTransition extends PDDictionaryWrapper {
    public PDTransition() {
        this(PDTransitionStyle.R);
    }

    public PDTransition(PDTransitionStyle pDTransitionStyle) {
        getCOSObject().setName(COSName.TYPE, COSName.TRANS.getName());
        getCOSObject().setName(COSName.f2301S, pDTransitionStyle.name());
    }

    public PDTransition(COSDictionary cOSDictionary) {
        super(cOSDictionary);
    }

    public String getStyle() {
        return getCOSObject().getNameAsString(COSName.f2301S, PDTransitionStyle.R.name());
    }

    public String getDimension() {
        return getCOSObject().getNameAsString(COSName.f2252DM, PDTransitionDimension.H.name());
    }

    public void setDimension(PDTransitionDimension pDTransitionDimension) {
        getCOSObject().setName(COSName.f2252DM, pDTransitionDimension.name());
    }

    public String getMotion() {
        return getCOSObject().getNameAsString(COSName.f2281M, PDTransitionMotion.I.name());
    }

    public void setMotion(PDTransitionMotion pDTransitionMotion) {
        getCOSObject().setName(COSName.f2281M, pDTransitionMotion.name());
    }

    public COSBase getDirection() {
        COSBase item = getCOSObject().getItem(COSName.f2250DI);
        return item == null ? COSInteger.ZERO : item;
    }

    public void setDirection(PDTransitionDirection pDTransitionDirection) {
        getCOSObject().setItem(COSName.f2250DI, pDTransitionDirection.getCOSBase());
    }

    public float getDuration() {
        return getCOSObject().getFloat(COSName.f2248D, 1.0f);
    }

    public void setDuration(float f) {
        getCOSObject().setItem(COSName.f2248D, (COSBase) new COSFloat(f));
    }

    public float getFlyScale() {
        return getCOSObject().getFloat(COSName.f2305SS, 1.0f);
    }

    public void setFlyScale(float f) {
        getCOSObject().setItem(COSName.f2305SS, (COSBase) new COSFloat(f));
    }

    public boolean isFlyAreaOpaque() {
        return getCOSObject().getBoolean(COSName.f2232B, false);
    }

    public void setFlyAreaOpaque(boolean z) {
        getCOSObject().setItem(COSName.f2232B, (COSBase) COSBoolean.getBoolean(z));
    }
}
