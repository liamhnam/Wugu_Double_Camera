package com.tom_roush.pdfbox.pdmodel.interactive.form;

import com.tom_roush.pdfbox.cos.COSDictionary;
import com.tom_roush.pdfbox.cos.COSName;

final class PDFieldFactory {
    private static final String FIELD_TYPE_BUTTON = "Btn";
    private static final String FIELD_TYPE_CHOICE = "Ch";
    private static final String FIELD_TYPE_SIGNATURE = "Sig";
    private static final String FIELD_TYPE_TEXT = "Tx";

    private PDFieldFactory() {
    }

    static PDField createField(PDAcroForm pDAcroForm, COSDictionary cOSDictionary, PDNonTerminalField pDNonTerminalField) {
        String strFindFieldType = findFieldType(cOSDictionary);
        if (FIELD_TYPE_CHOICE.equals(strFindFieldType)) {
            return createChoiceSubType(pDAcroForm, cOSDictionary, pDNonTerminalField);
        }
        if (FIELD_TYPE_TEXT.equals(strFindFieldType)) {
            return new PDTextField(pDAcroForm, cOSDictionary, pDNonTerminalField);
        }
        if (FIELD_TYPE_SIGNATURE.equals(strFindFieldType)) {
            return new PDSignatureField(pDAcroForm, cOSDictionary, pDNonTerminalField);
        }
        if (FIELD_TYPE_BUTTON.equals(strFindFieldType)) {
            return createButtonSubType(pDAcroForm, cOSDictionary, pDNonTerminalField);
        }
        if (cOSDictionary.containsKey(COSName.KIDS)) {
            return new PDNonTerminalField(pDAcroForm, cOSDictionary, pDNonTerminalField);
        }
        return null;
    }

    private static PDField createChoiceSubType(PDAcroForm pDAcroForm, COSDictionary cOSDictionary, PDNonTerminalField pDNonTerminalField) {
        if ((cOSDictionary.getInt(COSName.f2262FF, 0) & 131072) != 0) {
            return new PDComboBox(pDAcroForm, cOSDictionary, pDNonTerminalField);
        }
        return new PDListBox(pDAcroForm, cOSDictionary, pDNonTerminalField);
    }

    private static PDField createButtonSubType(PDAcroForm pDAcroForm, COSDictionary cOSDictionary, PDNonTerminalField pDNonTerminalField) {
        int i = cOSDictionary.getInt(COSName.f2262FF, 0);
        if ((32768 & i) != 0) {
            return new PDRadioButton(pDAcroForm, cOSDictionary, pDNonTerminalField);
        }
        if ((i & 65536) != 0) {
            return new PDPushButton(pDAcroForm, cOSDictionary, pDNonTerminalField);
        }
        return new PDCheckBox(pDAcroForm, cOSDictionary, pDNonTerminalField);
    }

    private static String findFieldType(COSDictionary cOSDictionary) {
        COSDictionary cOSDictionary2;
        String nameAsString = cOSDictionary.getNameAsString(COSName.f2264FT);
        return (nameAsString != null || (cOSDictionary2 = (COSDictionary) cOSDictionary.getDictionaryObject(COSName.PARENT, COSName.f2292P)) == null) ? nameAsString : findFieldType(cOSDictionary2);
    }
}
