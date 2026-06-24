package com.tom_roush.pdfbox.pdmodel.documentinterchange.taggedpdf;

import com.tom_roush.pdfbox.cos.COSDictionary;

public class PDTableAttributeObject extends PDStandardAttributeObject {
    protected static final String COL_SPAN = "ColSpan";
    protected static final String HEADERS = "Headers";
    public static final String OWNER_TABLE = "Table";
    protected static final String ROW_SPAN = "RowSpan";
    protected static final String SCOPE = "Scope";
    public static final String SCOPE_BOTH = "Both";
    public static final String SCOPE_COLUMN = "Column";
    public static final String SCOPE_ROW = "Row";
    protected static final String SUMMARY = "Summary";

    public PDTableAttributeObject() {
        setOwner("Table");
    }

    public PDTableAttributeObject(COSDictionary cOSDictionary) {
        super(cOSDictionary);
    }

    public int getRowSpan() {
        return getInteger(ROW_SPAN, 1);
    }

    public void setRowSpan(int i) {
        setInteger(ROW_SPAN, i);
    }

    public int getColSpan() {
        return getInteger(COL_SPAN, 1);
    }

    public void setColSpan(int i) {
        setInteger(COL_SPAN, i);
    }

    public String[] getHeaders() {
        return getArrayOfString(HEADERS);
    }

    public void setHeaders(String[] strArr) {
        setArrayOfString(HEADERS, strArr);
    }

    public String getScope() {
        return getName(SCOPE);
    }

    public void setScope(String str) {
        setName(SCOPE, str);
    }

    public String getSummary() {
        return getString(SUMMARY);
    }

    public void setSummary(String str) {
        setString(SUMMARY, str);
    }

    @Override
    public String toString() {
        StringBuilder sbAppend = new StringBuilder().append(super.toString());
        if (isSpecified(ROW_SPAN)) {
            sbAppend.append(", RowSpan=").append(String.valueOf(getRowSpan()));
        }
        if (isSpecified(COL_SPAN)) {
            sbAppend.append(", ColSpan=").append(String.valueOf(getColSpan()));
        }
        if (isSpecified(HEADERS)) {
            sbAppend.append(", Headers=").append(arrayToString(getHeaders()));
        }
        if (isSpecified(SCOPE)) {
            sbAppend.append(", Scope=").append(getScope());
        }
        if (isSpecified(SUMMARY)) {
            sbAppend.append(", Summary=").append(getSummary());
        }
        return sbAppend.toString();
    }
}
