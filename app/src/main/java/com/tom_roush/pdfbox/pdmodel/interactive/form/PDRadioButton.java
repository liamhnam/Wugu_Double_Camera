package com.tom_roush.pdfbox.pdmodel.interactive.form;

import com.faceunity.wrapper.faceunity;
import com.tom_roush.pdfbox.cos.COSDictionary;
import com.tom_roush.pdfbox.cos.COSName;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public final class PDRadioButton extends PDButton {
    private static final int FLAG_NO_TOGGLE_TO_OFF = 16384;

    public PDRadioButton(PDAcroForm pDAcroForm) {
        super(pDAcroForm);
        setRadioButton(true);
    }

    PDRadioButton(PDAcroForm pDAcroForm, COSDictionary cOSDictionary, PDNonTerminalField pDNonTerminalField) {
        super(pDAcroForm, cOSDictionary, pDNonTerminalField);
    }

    public void setRadiosInUnison(boolean z) {
        getCOSObject().setFlag(COSName.f2262FF, faceunity.FUAITYPE_FACEPROCESSOR_EMOTION_RECOGNIZER, z);
    }

    public boolean isRadiosInUnison() {
        return getCOSObject().getFlag(COSName.f2262FF, faceunity.FUAITYPE_FACEPROCESSOR_EMOTION_RECOGNIZER);
    }

    public List<String> getSelectedExportValues() throws IOException {
        Set<String> onValues = getOnValues();
        List<String> exportValues = getExportValues();
        ArrayList arrayList = new ArrayList();
        if (exportValues.isEmpty()) {
            arrayList.add(getValue());
            return arrayList;
        }
        String value = getValue();
        Iterator<String> it = onValues.iterator();
        while (it.hasNext()) {
            if (it.next().compareTo(value) == 0) {
                arrayList.add(exportValues.get(0));
            }
        }
        return arrayList;
    }
}
