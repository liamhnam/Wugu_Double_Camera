package com.tom_roush.pdfbox.pdmodel.graphics.color;

import com.tom_roush.pdfbox.cos.COSBase;
import com.tom_roush.pdfbox.cos.COSName;

public abstract class PDDeviceColorSpace extends PDColorSpace {
    public String toString() {
        return getName();
    }

    @Override
    public COSBase getCOSObject() {
        return COSName.getPDFName(getName());
    }
}
