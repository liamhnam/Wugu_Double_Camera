package com.tom_roush.pdfbox.pdmodel.interactive.form;

import com.tom_roush.pdfbox.cos.COSDictionary;
import com.tom_roush.pdfbox.cos.COSName;
import java.io.IOException;

public final class PDListBox extends PDChoice {
    public PDListBox(PDAcroForm pDAcroForm) {
        super(pDAcroForm);
    }

    PDListBox(PDAcroForm pDAcroForm, COSDictionary cOSDictionary, PDNonTerminalField pDNonTerminalField) {
        super(pDAcroForm, cOSDictionary, pDNonTerminalField);
    }

    public int getTopIndex() {
        return getCOSObject().getInt(COSName.f2311TI, 0);
    }

    public void setTopIndex(Integer num) {
        if (num != null) {
            getCOSObject().setInt(COSName.f2311TI, num.intValue());
        } else {
            getCOSObject().removeItem(COSName.f2311TI);
        }
    }

    @Override
    void constructAppearances() throws IOException {
        new AppearanceGeneratorHelper(this).setAppearanceValue("");
    }
}
