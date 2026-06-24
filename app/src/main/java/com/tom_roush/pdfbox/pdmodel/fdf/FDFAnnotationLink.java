package com.tom_roush.pdfbox.pdmodel.fdf;

import com.tom_roush.pdfbox.cos.COSDictionary;
import com.tom_roush.pdfbox.cos.COSName;
import java.io.IOException;
import org.w3c.dom.Element;

public class FDFAnnotationLink extends FDFAnnotation {
    public static final String SUBTYPE = "Link";

    public FDFAnnotationLink() {
        this.annot.setName(COSName.SUBTYPE, "Link");
    }

    public FDFAnnotationLink(COSDictionary cOSDictionary) {
        super(cOSDictionary);
    }

    public FDFAnnotationLink(Element element) throws IOException {
        super(element);
        this.annot.setName(COSName.SUBTYPE, "Link");
    }
}
