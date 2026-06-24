package com.tom_roush.pdfbox.pdmodel.interactive.form;

import com.tom_roush.pdfbox.cos.COSDictionary;
import java.util.Collections;
import java.util.List;

public class PDPushButton extends PDButton {
    @Override
    public String getDefaultValue() {
        return "";
    }

    @Override
    public String getValue() {
        return "";
    }

    public PDPushButton(PDAcroForm pDAcroForm) {
        super(pDAcroForm);
        setPushButton(true);
    }

    PDPushButton(PDAcroForm pDAcroForm, COSDictionary cOSDictionary, PDNonTerminalField pDNonTerminalField) {
        super(pDAcroForm, cOSDictionary, pDNonTerminalField);
    }

    @Override
    public List<String> getExportValues() {
        return Collections.emptyList();
    }

    @Override
    public void setExportValues(List<String> list) {
        if (list != null && !list.isEmpty()) {
            throw new IllegalArgumentException("A PDPushButton shall not use the Opt entry in the field dictionary");
        }
    }

    @Override
    public String getValueAsString() {
        return getValue();
    }
}
