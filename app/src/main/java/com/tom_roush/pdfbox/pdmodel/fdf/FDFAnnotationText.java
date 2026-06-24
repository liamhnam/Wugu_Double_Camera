package com.tom_roush.pdfbox.pdmodel.fdf;

import com.tom_roush.pdfbox.cos.COSDictionary;
import com.tom_roush.pdfbox.cos.COSName;
import java.io.IOException;
import org.w3c.dom.Element;

public class FDFAnnotationText extends FDFAnnotation {
    public static final String SUBTYPE = "Text";

    public FDFAnnotationText() {
        this.annot.setName(COSName.SUBTYPE, "Text");
    }

    public FDFAnnotationText(COSDictionary cOSDictionary) {
        super(cOSDictionary);
    }

    public FDFAnnotationText(Element element) throws IOException {
        String attribute;
        super(element);
        this.annot.setName(COSName.SUBTYPE, "Text");
        String attribute2 = element.getAttribute("icon");
        if (attribute2 != null && !attribute2.isEmpty()) {
            setIcon(element.getAttribute("icon"));
        }
        String attribute3 = element.getAttribute("state");
        if (attribute3 == null || attribute3.isEmpty() || (attribute = element.getAttribute("statemodel")) == null || attribute.isEmpty()) {
            return;
        }
        setState(element.getAttribute("state"));
        setStateModel(element.getAttribute("statemodel"));
    }

    public void setIcon(String str) {
        this.annot.setName(COSName.NAME, str);
    }

    public String getIcon() {
        return this.annot.getNameAsString(COSName.NAME, "Note");
    }

    public String getState() {
        return this.annot.getString(COSName.STATE);
    }

    public void setState(String str) {
        this.annot.setString(COSName.STATE, str);
    }

    public String getStateModel() {
        return this.annot.getString(COSName.STATE_MODEL);
    }

    public void setStateModel(String str) {
        this.annot.setString(COSName.STATE_MODEL, str);
    }
}
