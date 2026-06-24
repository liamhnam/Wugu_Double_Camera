package com.tom_roush.pdfbox.pdmodel.fdf;

import android.util.Log;
import com.tom_roush.harmony.awt.AWTColor;
import com.tom_roush.pdfbox.cos.COSArray;
import com.tom_roush.pdfbox.cos.COSBase;
import com.tom_roush.pdfbox.cos.COSDictionary;
import com.tom_roush.pdfbox.cos.COSName;
import java.io.IOException;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Element;

public class FDFAnnotationPolyline extends FDFAnnotation {
    public static final String SUBTYPE = "Polyline";

    public FDFAnnotationPolyline() {
        this.annot.setName(COSName.SUBTYPE, SUBTYPE);
    }

    public FDFAnnotationPolyline(COSDictionary cOSDictionary) {
        super(cOSDictionary);
    }

    public FDFAnnotationPolyline(Element element) throws IOException {
        super(element);
        this.annot.setName(COSName.SUBTYPE, SUBTYPE);
        initVertices(element);
        initStyles(element);
    }

    private void initVertices(Element element) throws IOException {
        try {
            String strEvaluate = XPathFactory.newInstance().newXPath().evaluate("vertices[1]", element);
            if (strEvaluate == null || strEvaluate.isEmpty()) {
                throw new IOException("Error: missing element 'vertices'");
            }
            String[] strArrSplit = strEvaluate.split(",|;");
            float[] fArr = new float[strArrSplit.length];
            for (int i = 0; i < strArrSplit.length; i++) {
                fArr[i] = Float.parseFloat(strArrSplit[i]);
            }
            setVertices(fArr);
        } catch (XPathExpressionException unused) {
            Log.d("PdfBox-Android", "Error while evaluating XPath expression for polyline vertices");
        }
    }

    private void initStyles(Element element) {
        String attribute = element.getAttribute("head");
        if (attribute != null && !attribute.isEmpty()) {
            setStartPointEndingStyle(attribute);
        }
        String attribute2 = element.getAttribute("tail");
        if (attribute2 != null && !attribute2.isEmpty()) {
            setEndPointEndingStyle(attribute2);
        }
        String attribute3 = element.getAttribute("interior-color");
        if (attribute3 != null && attribute3.length() == 7 && attribute3.charAt(0) == '#') {
            setInteriorColor(new AWTColor(Integer.parseInt(attribute3.substring(1, 7), 16)));
        }
    }

    public void setVertices(float[] fArr) {
        COSArray cOSArray = new COSArray();
        cOSArray.setFloatArray(fArr);
        this.annot.setItem(COSName.VERTICES, (COSBase) cOSArray);
    }

    public float[] getVertices() {
        COSArray cOSArray = (COSArray) this.annot.getDictionaryObject(COSName.VERTICES);
        if (cOSArray != null) {
            return cOSArray.toFloatArray();
        }
        return null;
    }

    public void setStartPointEndingStyle(String str) {
        if (str == null) {
            str = "None";
        }
        COSArray cOSArray = (COSArray) this.annot.getDictionaryObject(COSName.f2277LE);
        if (cOSArray == null) {
            COSArray cOSArray2 = new COSArray();
            cOSArray2.add((COSBase) COSName.getPDFName(str));
            cOSArray2.add((COSBase) COSName.getPDFName("None"));
            this.annot.setItem(COSName.f2277LE, (COSBase) cOSArray2);
            return;
        }
        cOSArray.setName(0, str);
    }

    public String getStartPointEndingStyle() {
        COSArray cOSArray = (COSArray) this.annot.getDictionaryObject(COSName.f2277LE);
        return cOSArray != null ? cOSArray.getName(0) : "None";
    }

    public void setEndPointEndingStyle(String str) {
        if (str == null) {
            str = "None";
        }
        COSArray cOSArray = (COSArray) this.annot.getDictionaryObject(COSName.f2277LE);
        if (cOSArray == null) {
            COSArray cOSArray2 = new COSArray();
            cOSArray2.add((COSBase) COSName.getPDFName("None"));
            cOSArray2.add((COSBase) COSName.getPDFName(str));
            this.annot.setItem(COSName.f2277LE, (COSBase) cOSArray2);
            return;
        }
        cOSArray.setName(1, str);
    }

    public String getEndPointEndingStyle() {
        COSArray cOSArray = (COSArray) this.annot.getDictionaryObject(COSName.f2277LE);
        return cOSArray != null ? cOSArray.getName(1) : "None";
    }

    public void setInteriorColor(AWTColor aWTColor) {
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
}
