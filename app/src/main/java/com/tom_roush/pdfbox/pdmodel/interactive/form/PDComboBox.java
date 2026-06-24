package com.tom_roush.pdfbox.pdmodel.interactive.form;

import com.tom_roush.pdfbox.cos.COSDictionary;
import com.tom_roush.pdfbox.cos.COSName;
import java.io.IOException;
import java.util.List;

public final class PDComboBox extends PDChoice {
    private static final int FLAG_EDIT = 262144;

    public PDComboBox(PDAcroForm pDAcroForm) {
        super(pDAcroForm);
        setCombo(true);
    }

    PDComboBox(PDAcroForm pDAcroForm, COSDictionary cOSDictionary, PDNonTerminalField pDNonTerminalField) {
        super(pDAcroForm, cOSDictionary, pDNonTerminalField);
    }

    public boolean isEdit() {
        return getCOSObject().getFlag(COSName.f2262FF, 262144);
    }

    public void setEdit(boolean z) {
        getCOSObject().setFlag(COSName.f2262FF, 262144, z);
    }

    @Override
    void constructAppearances() throws IOException {
        AppearanceGeneratorHelper appearanceGeneratorHelper = new AppearanceGeneratorHelper(this);
        List<String> value = getValue();
        if (!value.isEmpty()) {
            appearanceGeneratorHelper.setAppearanceValue(value.get(0));
        } else {
            appearanceGeneratorHelper.setAppearanceValue("");
        }
    }
}
