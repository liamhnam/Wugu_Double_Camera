package com.tom_roush.pdfbox.pdmodel.fdf;

import com.tom_roush.pdfbox.cos.COSDictionary;
import com.tom_roush.pdfbox.cos.COSName;
import java.io.IOException;
import org.w3c.dom.Element;

public class FDFAnnotationHighlight extends FDFAnnotationTextMarkup {
    public static final String SUBTYPE = "Highlight";

    public FDFAnnotationHighlight() {
        this.annot.setName(COSName.SUBTYPE, "Highlight");
    }

    public FDFAnnotationHighlight(COSDictionary cOSDictionary) {
        super(cOSDictionary);
    }

    public FDFAnnotationHighlight(Element element) throws IOException {
        super(element);
        this.annot.setName(COSName.SUBTYPE, "Highlight");
    }
}
