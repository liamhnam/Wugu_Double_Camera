package com.tom_roush.pdfbox.pdmodel.interactive.form;

import com.tom_roush.pdfbox.cos.COSBase;
import com.tom_roush.pdfbox.cos.COSDictionary;
import com.tom_roush.pdfbox.cos.COSName;
import com.tom_roush.pdfbox.cos.COSNumber;
import com.tom_roush.pdfbox.cos.COSStream;
import com.tom_roush.pdfbox.cos.COSString;
import java.io.IOException;

public abstract class PDVariableText extends PDTerminalField {
    static final int QUADDING_CENTERED = 1;
    static final int QUADDING_LEFT = 0;
    static final int QUADDING_RIGHT = 2;

    PDVariableText(PDAcroForm pDAcroForm) {
        super(pDAcroForm);
    }

    PDVariableText(PDAcroForm pDAcroForm, COSDictionary cOSDictionary, PDNonTerminalField pDNonTerminalField) {
        super(pDAcroForm, cOSDictionary, pDNonTerminalField);
    }

    public String getDefaultAppearance() {
        return ((COSString) getInheritableAttribute(COSName.f2249DA)).getString();
    }

    PDDefaultAppearanceString getDefaultAppearanceString() throws IOException {
        return new PDDefaultAppearanceString((COSString) getInheritableAttribute(COSName.f2249DA), getAcroForm().getDefaultResources());
    }

    public void setDefaultAppearance(String str) {
        getCOSObject().setString(COSName.f2249DA, str);
    }

    public String getDefaultStyleString() {
        return ((COSString) getCOSObject().getDictionaryObject(COSName.f2255DS)).getString();
    }

    public void setDefaultStyleString(String str) {
        if (str != null) {
            getCOSObject().setItem(COSName.f2255DS, (COSBase) new COSString(str));
        } else {
            getCOSObject().removeItem(COSName.f2255DS);
        }
    }

    public int getQ() {
        COSNumber cOSNumber = (COSNumber) getInheritableAttribute(COSName.f2295Q);
        if (cOSNumber != null) {
            return cOSNumber.intValue();
        }
        return 0;
    }

    public void setQ(int i) {
        getCOSObject().setInt(COSName.f2295Q, i);
    }

    public String getRichTextValue() throws IOException {
        return getStringOrStream(getInheritableAttribute(COSName.f2300RV));
    }

    public void setRichTextValue(String str) {
        if (str != null) {
            getCOSObject().setItem(COSName.f2300RV, (COSBase) new COSString(str));
        } else {
            getCOSObject().removeItem(COSName.f2300RV);
        }
    }

    protected String getStringOrStream(COSBase cOSBase) {
        if (cOSBase == null) {
            return "";
        }
        if (cOSBase instanceof COSString) {
            return ((COSString) cOSBase).getString();
        }
        return cOSBase instanceof COSStream ? ((COSStream) cOSBase).toTextString() : "";
    }
}
