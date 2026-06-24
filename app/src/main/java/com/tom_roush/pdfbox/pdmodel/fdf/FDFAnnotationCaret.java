package com.tom_roush.pdfbox.pdmodel.fdf;

import com.tom_roush.pdfbox.cos.COSArray;
import com.tom_roush.pdfbox.cos.COSDictionary;
import com.tom_roush.pdfbox.cos.COSName;
import com.tom_roush.pdfbox.pdmodel.common.PDRectangle;
import java.io.IOException;
import org.w3c.dom.Element;

public class FDFAnnotationCaret extends FDFAnnotation {
    public static final String SUBTYPE = "Caret";

    public FDFAnnotationCaret() {
        this.annot.setName(COSName.SUBTYPE, "Caret");
    }

    public FDFAnnotationCaret(COSDictionary cOSDictionary) {
        super(cOSDictionary);
    }

    public FDFAnnotationCaret(Element element) throws IOException {
        super(element);
        this.annot.setName(COSName.SUBTYPE, "Caret");
        initFringe(element);
        String attribute = element.getAttribute("symbol");
        if (attribute == null || attribute.isEmpty()) {
            return;
        }
        setSymbol(element.getAttribute("symbol"));
    }

    private void initFringe(Element element) throws IOException {
        String attribute = element.getAttribute("fringe");
        if (attribute == null || attribute.isEmpty()) {
            return;
        }
        String[] strArrSplit = attribute.split(",");
        if (strArrSplit.length != 4) {
            throw new IOException("Error: wrong amount of numbers in attribute 'fringe'");
        }
        PDRectangle pDRectangle = new PDRectangle();
        pDRectangle.setLowerLeftX(Float.parseFloat(strArrSplit[0]));
        pDRectangle.setLowerLeftY(Float.parseFloat(strArrSplit[1]));
        pDRectangle.setUpperRightX(Float.parseFloat(strArrSplit[2]));
        pDRectangle.setUpperRightY(Float.parseFloat(strArrSplit[3]));
        setFringe(pDRectangle);
    }

    public final void setFringe(PDRectangle pDRectangle) {
        this.annot.setItem(COSName.f2298RD, pDRectangle);
    }

    public PDRectangle getFringe() {
        COSArray cOSArray = (COSArray) this.annot.getDictionaryObject(COSName.f2298RD);
        if (cOSArray != null) {
            return new PDRectangle(cOSArray);
        }
        return null;
    }

    public final void setSymbol(String str) {
        this.annot.setString(COSName.f2309SY, "paragraph".equals(str) ? "P" : "None");
    }

    public String getSymbol() {
        return this.annot.getString(COSName.f2309SY);
    }
}
