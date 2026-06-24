package com.tom_roush.pdfbox.pdmodel.interactive.action;

import com.tom_roush.pdfbox.cos.COSDictionary;
import com.tom_roush.pdfbox.cos.COSName;
import com.tom_roush.pdfbox.pdmodel.common.COSObjectable;

public class PDFormFieldAdditionalActions implements COSObjectable {
    private final COSDictionary actions;

    public PDFormFieldAdditionalActions() {
        this.actions = new COSDictionary();
    }

    public PDFormFieldAdditionalActions(COSDictionary cOSDictionary) {
        this.actions = cOSDictionary;
    }

    @Override
    public COSDictionary getCOSObject() {
        return this.actions;
    }

    public PDAction getK() {
        COSDictionary cOSDictionary = (COSDictionary) this.actions.getDictionaryObject(COSName.f2274K);
        if (cOSDictionary != null) {
            return PDActionFactory.createAction(cOSDictionary);
        }
        return null;
    }

    public void setK(PDAction pDAction) {
        this.actions.setItem(COSName.f2274K, pDAction);
    }

    public PDAction getF() {
        COSDictionary cOSDictionary = (COSDictionary) this.actions.getDictionaryObject(COSName.f2260F);
        if (cOSDictionary != null) {
            return PDActionFactory.createAction(cOSDictionary);
        }
        return null;
    }

    public void setF(PDAction pDAction) {
        this.actions.setItem(COSName.f2260F, pDAction);
    }

    public PDAction getV() {
        COSDictionary cOSDictionary = (COSDictionary) this.actions.getDictionaryObject(COSName.f2320V);
        if (cOSDictionary != null) {
            return PDActionFactory.createAction(cOSDictionary);
        }
        return null;
    }

    public void setV(PDAction pDAction) {
        this.actions.setItem(COSName.f2320V, pDAction);
    }

    public PDAction getC() {
        COSDictionary cOSDictionary = (COSDictionary) this.actions.getDictionaryObject(COSName.f2238C);
        if (cOSDictionary != null) {
            return PDActionFactory.createAction(cOSDictionary);
        }
        return null;
    }

    public void setC(PDAction pDAction) {
        this.actions.setItem(COSName.f2238C, pDAction);
    }
}
