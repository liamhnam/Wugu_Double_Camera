package com.tom_roush.pdfbox.pdmodel.graphics.state;

import android.util.Log;
import com.tom_roush.pdfbox.cos.COSArray;
import com.tom_roush.pdfbox.cos.COSBase;
import com.tom_roush.pdfbox.cos.COSDictionary;
import com.tom_roush.pdfbox.cos.COSName;
import com.tom_roush.pdfbox.pdmodel.common.COSObjectable;
import com.tom_roush.pdfbox.pdmodel.common.function.PDFunction;
import com.tom_roush.pdfbox.pdmodel.graphics.PDXObject;
import com.tom_roush.pdfbox.pdmodel.graphics.form.PDTransparencyGroup;
import java.io.IOException;

public final class PDSoftMask implements COSObjectable {
    private final COSDictionary dictionary;
    private COSName subType = null;
    private PDTransparencyGroup group = null;
    private COSArray backdropColor = null;
    private PDFunction transferFunction = null;

    public static PDSoftMask create(COSBase cOSBase) {
        if (cOSBase instanceof COSName) {
            if (COSName.NONE.equals(cOSBase)) {
                return null;
            }
            Log.w("PdfBox-Android", "Invalid SMask " + cOSBase);
            return null;
        }
        if (cOSBase instanceof COSDictionary) {
            return new PDSoftMask((COSDictionary) cOSBase);
        }
        Log.w("PdfBox-Android", "Invalid SMask " + cOSBase);
        return null;
    }

    public PDSoftMask(COSDictionary cOSDictionary) {
        this.dictionary = cOSDictionary;
    }

    @Override
    public COSDictionary getCOSObject() {
        return this.dictionary;
    }

    public COSName getSubType() {
        if (this.subType == null) {
            this.subType = (COSName) getCOSObject().getDictionaryObject(COSName.f2301S);
        }
        return this.subType;
    }

    public PDTransparencyGroup getGroup() throws IOException {
        COSBase dictionaryObject;
        if (this.group == null && (dictionaryObject = getCOSObject().getDictionaryObject(COSName.f2265G)) != null) {
            this.group = (PDTransparencyGroup) PDXObject.createXObject(dictionaryObject, null);
        }
        return this.group;
    }

    public COSArray getBackdropColor() {
        if (this.backdropColor == null) {
            this.backdropColor = (COSArray) getCOSObject().getDictionaryObject(COSName.f2233BC);
        }
        return this.backdropColor;
    }

    public PDFunction getTransferFunction() throws IOException {
        COSBase dictionaryObject;
        if (this.transferFunction == null && (dictionaryObject = getCOSObject().getDictionaryObject(COSName.f2314TR)) != null) {
            this.transferFunction = PDFunction.create(dictionaryObject);
        }
        return this.transferFunction;
    }
}
