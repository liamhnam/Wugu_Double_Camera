package com.tom_roush.pdfbox.pdmodel.documentinterchange.taggedpdf;

import com.tom_roush.pdfbox.cos.COSDictionary;

public class PDListAttributeObject extends PDStandardAttributeObject {
    protected static final String LIST_NUMBERING = "ListNumbering";
    public static final String LIST_NUMBERING_CIRCLE = "Circle";
    public static final String LIST_NUMBERING_DECIMAL = "Decimal";
    public static final String LIST_NUMBERING_DISC = "Disc";
    public static final String LIST_NUMBERING_LOWER_ALPHA = "LowerAlpha";
    public static final String LIST_NUMBERING_LOWER_ROMAN = "LowerRoman";
    public static final String LIST_NUMBERING_NONE = "None";
    public static final String LIST_NUMBERING_SQUARE = "Square";
    public static final String LIST_NUMBERING_UPPER_ALPHA = "UpperAlpha";
    public static final String LIST_NUMBERING_UPPER_ROMAN = "UpperRoman";
    public static final String OWNER_LIST = "List";

    public PDListAttributeObject() {
        setOwner(OWNER_LIST);
    }

    public PDListAttributeObject(COSDictionary cOSDictionary) {
        super(cOSDictionary);
    }

    public String getListNumbering() {
        return getName(LIST_NUMBERING, "None");
    }

    public void setListNumbering(String str) {
        setName(LIST_NUMBERING, str);
    }

    @Override
    public String toString() {
        StringBuilder sbAppend = new StringBuilder().append(super.toString());
        if (isSpecified(LIST_NUMBERING)) {
            sbAppend.append(", ListNumbering=").append(getListNumbering());
        }
        return sbAppend.toString();
    }
}
