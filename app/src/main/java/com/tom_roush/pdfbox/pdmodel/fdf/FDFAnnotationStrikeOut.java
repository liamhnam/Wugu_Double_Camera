package com.tom_roush.pdfbox.pdmodel.fdf;

import com.tom_roush.pdfbox.cos.COSDictionary;
import com.tom_roush.pdfbox.cos.COSName;
import java.io.IOException;
import org.w3c.dom.Element;

public class FDFAnnotationStrikeOut extends FDFAnnotationTextMarkup {
    public static final String SUBTYPE = "StrikeOut";

    public FDFAnnotationStrikeOut() {
        this.annot.setName(COSName.SUBTYPE, "StrikeOut");
    }

    public FDFAnnotationStrikeOut(COSDictionary cOSDictionary) {
        super(cOSDictionary);
    }

    public FDFAnnotationStrikeOut(Element element) throws IOException {
        super(element);
        this.annot.setName(COSName.SUBTYPE, "StrikeOut");
    }
}
