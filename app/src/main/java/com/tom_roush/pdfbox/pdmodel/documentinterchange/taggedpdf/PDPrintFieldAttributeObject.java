package com.tom_roush.pdfbox.pdmodel.documentinterchange.taggedpdf;

import com.tom_roush.pdfbox.cos.COSDictionary;

public class PDPrintFieldAttributeObject extends PDStandardAttributeObject {
    private static final String CHECKED = "checked";
    public static final String CHECKED_STATE_NEUTRAL = "neutral";
    public static final String CHECKED_STATE_OFF = "off";
    public static final String CHECKED_STATE_ON = "on";
    private static final String DESC = "Desc";
    public static final String OWNER_PRINT_FIELD = "PrintField";
    private static final String ROLE = "Role";
    public static final String ROLE_CB = "cb";
    public static final String ROLE_PB = "pb";
    public static final String ROLE_RB = "rb";
    public static final String ROLE_TV = "tv";

    public PDPrintFieldAttributeObject() {
        setOwner(OWNER_PRINT_FIELD);
    }

    public PDPrintFieldAttributeObject(COSDictionary cOSDictionary) {
        super(cOSDictionary);
    }

    public String getRole() {
        return getName(ROLE);
    }

    public void setRole(String str) {
        setName(ROLE, str);
    }

    public String getCheckedState() {
        return getName(CHECKED, "off");
    }

    public void setCheckedState(String str) {
        setName(CHECKED, str);
    }

    public String getAlternateName() {
        return getString(DESC);
    }

    public void setAlternateName(String str) {
        setString(DESC, str);
    }

    @Override
    public String toString() {
        StringBuilder sbAppend = new StringBuilder().append(super.toString());
        if (isSpecified(ROLE)) {
            sbAppend.append(", Role=").append(getRole());
        }
        if (isSpecified(CHECKED)) {
            sbAppend.append(", Checked=").append(getCheckedState());
        }
        if (isSpecified(DESC)) {
            sbAppend.append(", Desc=").append(getAlternateName());
        }
        return sbAppend.toString();
    }
}
