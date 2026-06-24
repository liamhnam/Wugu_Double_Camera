package com.tom_roush.pdfbox.pdmodel.fdf;

import com.tom_roush.pdfbox.cos.COSArray;
import com.tom_roush.pdfbox.cos.COSBase;
import com.tom_roush.pdfbox.cos.COSDictionary;
import com.tom_roush.pdfbox.cos.COSName;
import java.io.IOException;
import org.w3c.dom.Element;

public abstract class FDFAnnotationTextMarkup extends FDFAnnotation {
    public FDFAnnotationTextMarkup() {
    }

    public FDFAnnotationTextMarkup(COSDictionary cOSDictionary) {
        super(cOSDictionary);
    }

    public FDFAnnotationTextMarkup(Element element) throws IOException {
        super(element);
        String attribute = element.getAttribute("coords");
        if (attribute == null || attribute.isEmpty()) {
            throw new IOException("Error: missing attribute 'coords'");
        }
        String[] strArrSplit = attribute.split(",");
        if (strArrSplit.length < 8) {
            throw new IOException("Error: too little numbers in attribute 'coords'");
        }
        float[] fArr = new float[strArrSplit.length];
        for (int i = 0; i < strArrSplit.length; i++) {
            fArr[i] = Float.parseFloat(strArrSplit[i]);
        }
        setCoords(fArr);
    }

    public void setCoords(float[] fArr) {
        COSArray cOSArray = new COSArray();
        cOSArray.setFloatArray(fArr);
        this.annot.setItem(COSName.QUADPOINTS, (COSBase) cOSArray);
    }

    public float[] getCoords() {
        COSArray cOSArray = (COSArray) this.annot.getItem(COSName.QUADPOINTS);
        if (cOSArray != null) {
            return cOSArray.toFloatArray();
        }
        return null;
    }
}
