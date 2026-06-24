package com.tom_roush.pdfbox.pdmodel.fdf;

import com.tom_roush.harmony.awt.AWTColor;
import com.tom_roush.pdfbox.cos.COSArray;
import com.tom_roush.pdfbox.cos.COSBase;
import com.tom_roush.pdfbox.cos.COSDictionary;
import com.tom_roush.pdfbox.cos.COSName;
import com.tom_roush.pdfbox.pdmodel.common.PDRectangle;
import java.io.IOException;
import org.w3c.dom.Element;

public class FDFAnnotationCircle extends FDFAnnotation {
    public static final String SUBTYPE = "Circle";

    public FDFAnnotationCircle() {
        this.annot.setName(COSName.SUBTYPE, "Circle");
    }

    public FDFAnnotationCircle(COSDictionary cOSDictionary) {
        super(cOSDictionary);
    }

    public FDFAnnotationCircle(Element element) throws IOException {
        super(element);
        this.annot.setName(COSName.SUBTYPE, "Circle");
        String attribute = element.getAttribute("interior-color");
        if (attribute != null && attribute.length() == 7 && attribute.charAt(0) == '#') {
            setInteriorColor(new AWTColor(Integer.parseInt(attribute.substring(1, 7), 16)));
        }
        initFringe(element);
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

    public final void setInteriorColor(AWTColor aWTColor) {
        COSArray cOSArray = null;
        if (aWTColor != null) {
            float[] rGBColorComponents = aWTColor.getRGBColorComponents(null);
            cOSArray = new COSArray();
            cOSArray.setFloatArray(rGBColorComponents);
        }
        this.annot.setItem(COSName.f2268IC, (COSBase) cOSArray);
    }

    public AWTColor getInteriorColor() {
        COSArray cOSArray = (COSArray) this.annot.getDictionaryObject(COSName.f2268IC);
        if (cOSArray != null) {
            float[] floatArray = cOSArray.toFloatArray();
            if (floatArray.length >= 3) {
                return new AWTColor(floatArray[0], floatArray[1], floatArray[2]);
            }
        }
        return null;
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
}
