package com.tom_roush.pdfbox.pdmodel.interactive.action;

import com.tom_roush.pdfbox.cos.COSBase;
import com.tom_roush.pdfbox.cos.COSDictionary;
import com.tom_roush.pdfbox.cos.COSName;
import com.tom_roush.pdfbox.cos.COSStream;
import com.tom_roush.pdfbox.cos.COSString;

public class PDActionJavaScript extends PDAction {
    public static final String SUB_TYPE = "JavaScript";

    public PDActionJavaScript() {
        setSubType(SUB_TYPE);
    }

    public PDActionJavaScript(String str) {
        this();
        setAction(str);
    }

    public PDActionJavaScript(COSDictionary cOSDictionary) {
        super(cOSDictionary);
    }

    public final void setAction(String str) {
        this.action.setString(COSName.f2273JS, str);
    }

    public String getAction() {
        COSBase dictionaryObject = this.action.getDictionaryObject(COSName.f2273JS);
        if (dictionaryObject instanceof COSString) {
            return ((COSString) dictionaryObject).getString();
        }
        if (dictionaryObject instanceof COSStream) {
            return ((COSStream) dictionaryObject).toTextString();
        }
        return null;
    }
}
