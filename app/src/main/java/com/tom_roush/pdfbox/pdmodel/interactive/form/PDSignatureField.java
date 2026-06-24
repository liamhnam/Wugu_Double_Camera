package com.tom_roush.pdfbox.pdmodel.interactive.form;

import com.tom_roush.pdfbox.cos.COSBase;
import com.tom_roush.pdfbox.cos.COSDictionary;
import com.tom_roush.pdfbox.cos.COSName;
import com.tom_roush.pdfbox.pdmodel.interactive.annotation.PDAnnotationWidget;
import com.tom_roush.pdfbox.pdmodel.interactive.digitalsignature.PDSeedValue;
import com.tom_roush.pdfbox.pdmodel.interactive.digitalsignature.PDSignature;
import java.io.IOException;
import java.util.HashSet;

public class PDSignatureField extends PDTerminalField {
    public PDSignatureField(PDAcroForm pDAcroForm) throws IOException {
        super(pDAcroForm);
        getCOSObject().setItem(COSName.f2264FT, (COSBase) COSName.SIG);
        getWidgets().get(0).setLocked(true);
        getWidgets().get(0).setPrinted(true);
        setPartialName(generatePartialName());
    }

    PDSignatureField(PDAcroForm pDAcroForm, COSDictionary cOSDictionary, PDNonTerminalField pDNonTerminalField) {
        super(pDAcroForm, cOSDictionary, pDNonTerminalField);
    }

    private String generatePartialName() {
        HashSet hashSet = new HashSet();
        for (PDField pDField : getAcroForm().getFields()) {
            if (pDField instanceof PDSignatureField) {
                hashSet.add(pDField.getPartialName());
            }
        }
        int i = 1;
        while (hashSet.contains("Signature" + i)) {
            i++;
        }
        return "Signature" + i;
    }

    @Deprecated
    public void setSignature(PDSignature pDSignature) throws IOException {
        setValue(pDSignature);
    }

    public PDSignature getSignature() {
        return getValue();
    }

    public void setValue(PDSignature pDSignature) throws IOException {
        getCOSObject().setItem(COSName.f2320V, pDSignature);
        applyChange();
    }

    @Override
    public void setValue(String str) throws UnsupportedOperationException {
        throw new UnsupportedOperationException("Signature fields don't support setting the value as String - use setValue(PDSignature value) instead");
    }

    public void setDefaultValue(PDSignature pDSignature) throws IOException {
        getCOSObject().setItem(COSName.f2256DV, pDSignature);
    }

    public PDSignature getValue() {
        COSBase dictionaryObject = getCOSObject().getDictionaryObject(COSName.f2320V);
        if (dictionaryObject == null) {
            return null;
        }
        return new PDSignature((COSDictionary) dictionaryObject);
    }

    public PDSignature getDefaultValue() {
        COSBase dictionaryObject = getCOSObject().getDictionaryObject(COSName.f2256DV);
        if (dictionaryObject == null) {
            return null;
        }
        return new PDSignature((COSDictionary) dictionaryObject);
    }

    @Override
    public String getValueAsString() {
        PDSignature value = getValue();
        return value != null ? value.toString() : "";
    }

    public PDSeedValue getSeedValue() {
        COSDictionary cOSDictionary = (COSDictionary) getCOSObject().getDictionaryObject(COSName.f2307SV);
        if (cOSDictionary != null) {
            return new PDSeedValue(cOSDictionary);
        }
        return null;
    }

    public void setSeedValue(PDSeedValue pDSeedValue) {
        if (pDSeedValue != null) {
            getCOSObject().setItem(COSName.f2307SV, pDSeedValue);
        }
    }

    @Override
    void constructAppearances() throws IOException {
        PDAnnotationWidget pDAnnotationWidget = getWidgets().get(0);
        if (pDAnnotationWidget == null || pDAnnotationWidget.getRectangle() == null) {
            return;
        }
        if ((pDAnnotationWidget.getRectangle().getHeight() != 0.0f || pDAnnotationWidget.getRectangle().getWidth() != 0.0f) && !pDAnnotationWidget.isNoView() && !pDAnnotationWidget.isHidden()) {
            throw new UnsupportedOperationException("not implemented");
        }
    }
}
