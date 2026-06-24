package com.tom_roush.pdfbox.pdmodel.documentinterchange.markedcontent;

import com.tom_roush.pdfbox.cos.COSDictionary;
import com.tom_roush.pdfbox.cos.COSName;
import com.tom_roush.pdfbox.pdmodel.documentinterchange.taggedpdf.PDArtifactMarkedContent;
import com.tom_roush.pdfbox.pdmodel.graphics.PDXObject;
import com.tom_roush.pdfbox.text.TextPosition;
import java.util.ArrayList;
import java.util.List;

public class PDMarkedContent {
    private final List<Object> contents;
    private final COSDictionary properties;
    private final String tag;

    public static PDMarkedContent create(COSName cOSName, COSDictionary cOSDictionary) {
        if (COSName.ARTIFACT.equals(cOSName)) {
            return new PDArtifactMarkedContent(cOSDictionary);
        }
        return new PDMarkedContent(cOSName, cOSDictionary);
    }

    public PDMarkedContent(COSName cOSName, COSDictionary cOSDictionary) {
        this.tag = cOSName == null ? null : cOSName.getName();
        this.properties = cOSDictionary;
        this.contents = new ArrayList();
    }

    public String getTag() {
        return this.tag;
    }

    public COSDictionary getProperties() {
        return this.properties;
    }

    public int getMCID() {
        if (getProperties() == null) {
            return -1;
        }
        return getProperties().getInt(COSName.MCID);
    }

    public String getLanguage() {
        if (getProperties() == null) {
            return null;
        }
        return getProperties().getNameAsString(COSName.LANG);
    }

    public String getActualText() {
        if (getProperties() == null) {
            return null;
        }
        return getProperties().getString(COSName.ACTUAL_TEXT);
    }

    public String getAlternateDescription() {
        if (getProperties() == null) {
            return null;
        }
        return getProperties().getString(COSName.ALT);
    }

    public String getExpandedForm() {
        if (getProperties() == null) {
            return null;
        }
        return getProperties().getString(COSName.f2258E);
    }

    public List<Object> getContents() {
        return this.contents;
    }

    public void addText(TextPosition textPosition) {
        getContents().add(textPosition);
    }

    public void addMarkedContent(PDMarkedContent pDMarkedContent) {
        getContents().add(pDMarkedContent);
    }

    public void addXObject(PDXObject pDXObject) {
        getContents().add(pDXObject);
    }

    public String toString() {
        StringBuilder sbAppend = new StringBuilder("tag=").append(this.tag).append(", properties=").append(this.properties);
        sbAppend.append(", contents=").append(this.contents);
        return sbAppend.toString();
    }
}
