package com.tom_roush.pdfbox.pdmodel.interactive.form;

import com.tom_roush.pdfbox.cos.COSArray;
import com.tom_roush.pdfbox.cos.COSBase;
import com.tom_roush.pdfbox.cos.COSDictionary;
import com.tom_roush.pdfbox.cos.COSName;
import com.tom_roush.pdfbox.cos.COSString;
import com.tom_roush.pdfbox.pdmodel.common.COSArrayList;
import com.tom_roush.pdfbox.pdmodel.interactive.annotation.PDAnnotationWidget;
import com.tom_roush.pdfbox.pdmodel.interactive.annotation.PDAppearanceDictionary;
import com.tom_roush.pdfbox.pdmodel.interactive.annotation.PDAppearanceEntry;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public abstract class PDButton extends PDTerminalField {
    static final int FLAG_PUSHBUTTON = 65536;
    static final int FLAG_RADIO = 32768;
    static final int FLAG_RADIOS_IN_UNISON = 33554432;

    public PDButton(PDAcroForm pDAcroForm) {
        super(pDAcroForm);
        getCOSObject().setItem(COSName.f2264FT, (COSBase) COSName.BTN);
    }

    PDButton(PDAcroForm pDAcroForm, COSDictionary cOSDictionary, PDNonTerminalField pDNonTerminalField) {
        super(pDAcroForm, cOSDictionary, pDNonTerminalField);
    }

    public boolean isPushButton() {
        return getCOSObject().getFlag(COSName.f2262FF, 65536);
    }

    public void setPushButton(boolean z) {
        getCOSObject().setFlag(COSName.f2262FF, 65536, z);
    }

    public boolean isRadioButton() {
        return getCOSObject().getFlag(COSName.f2262FF, 32768);
    }

    public void setRadioButton(boolean z) {
        getCOSObject().setFlag(COSName.f2262FF, 32768, z);
    }

    public String getValue() {
        COSBase inheritableAttribute = getInheritableAttribute(COSName.f2320V);
        return inheritableAttribute instanceof COSName ? ((COSName) inheritableAttribute).getName() : "";
    }

    @Override
    public void setValue(String str) throws IOException {
        checkValue(str);
        getCOSObject().setName(COSName.f2320V, str);
        for (PDAnnotationWidget pDAnnotationWidget : getWidgets()) {
            if (((COSDictionary) pDAnnotationWidget.getAppearance().getNormalAppearance().getCOSObject()).containsKey(str)) {
                pDAnnotationWidget.getCOSObject().setName(COSName.f2231AS, str);
            } else {
                pDAnnotationWidget.getCOSObject().setItem(COSName.f2231AS, (COSBase) COSName.Off);
            }
        }
        applyChange();
    }

    public String getDefaultValue() {
        COSBase inheritableAttribute = getInheritableAttribute(COSName.f2256DV);
        return inheritableAttribute instanceof COSName ? ((COSName) inheritableAttribute).getName() : "";
    }

    public void setDefaultValue(String str) {
        checkValue(str);
        getCOSObject().setName(COSName.f2256DV, str);
    }

    @Override
    public String getValueAsString() {
        return getValue();
    }

    public List<String> getExportValues() {
        COSBase inheritableAttribute = getInheritableAttribute(COSName.OPT);
        if (inheritableAttribute instanceof COSString) {
            ArrayList arrayList = new ArrayList();
            arrayList.add(((COSString) inheritableAttribute).getString());
            return arrayList;
        }
        if (inheritableAttribute instanceof COSArray) {
            return COSArrayList.convertCOSStringCOSArrayToList((COSArray) inheritableAttribute);
        }
        return Collections.emptyList();
    }

    public void setExportValues(List<String> list) {
        if (list != null && !list.isEmpty()) {
            getCOSObject().setItem(COSName.OPT, (COSBase) COSArrayList.convertStringListToCOSStringCOSArray(list));
        } else {
            getCOSObject().removeItem(COSName.OPT);
        }
    }

    @Override
    void constructAppearances() throws IOException {
        for (PDAnnotationWidget pDAnnotationWidget : getWidgets()) {
            PDAppearanceDictionary appearance = pDAnnotationWidget.getAppearance();
            if (appearance == null || appearance.getNormalAppearance() == null) {
                throw new UnsupportedOperationException("Appearance generation is not implemented yet, see PDFBOX-2849");
            }
            PDAppearanceEntry normalAppearance = pDAnnotationWidget.getAppearance().getNormalAppearance();
            String value = getValue();
            if (((COSDictionary) normalAppearance.getCOSObject()).containsKey(value)) {
                pDAnnotationWidget.getCOSObject().setName(COSName.f2231AS, value);
            } else {
                pDAnnotationWidget.getCOSObject().setItem(COSName.f2231AS, (COSBase) COSName.Off);
            }
        }
    }

    public Set<String> getOnValues() {
        PDAppearanceEntry normalAppearance;
        HashSet hashSet = new HashSet();
        Iterator<PDAnnotationWidget> it = getWidgets().iterator();
        while (it.hasNext()) {
            PDAppearanceDictionary appearance = it.next().getAppearance();
            if (appearance != null && (normalAppearance = appearance.getNormalAppearance()) != null) {
                for (COSName cOSName : normalAppearance.getSubDictionary().keySet()) {
                    if (COSName.Off.compareTo(cOSName) != 0) {
                        hashSet.add(cOSName.getName());
                    }
                }
            }
        }
        return hashSet;
    }

    void checkValue(String str) throws IllegalArgumentException {
        Set<String> onValues = getOnValues();
        if (COSName.Off.getName().compareTo(str) != 0 && !onValues.contains(str)) {
            throw new IllegalArgumentException("value '" + str + "' is not a valid option for the field " + getFullyQualifiedName() + ", valid values are: " + onValues + " and " + COSName.Off.getName());
        }
    }
}
