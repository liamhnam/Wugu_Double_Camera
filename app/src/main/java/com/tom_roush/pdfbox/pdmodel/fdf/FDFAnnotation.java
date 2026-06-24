package com.tom_roush.pdfbox.pdmodel.fdf;

import android.util.Log;
import com.arthenica.ffmpegkit.StreamInformation;
import com.google.android.exoplayer2.offline.DownloadRequest;
import com.google.android.exoplayer2.text.ttml.TtmlNode;
import com.tom_roush.fontbox.ttf.NamingTable;
import com.tom_roush.harmony.awt.AWTColor;
import com.tom_roush.pdfbox.cos.COSArray;
import com.tom_roush.pdfbox.cos.COSBase;
import com.tom_roush.pdfbox.cos.COSDictionary;
import com.tom_roush.pdfbox.cos.COSName;
import com.tom_roush.pdfbox.cos.COSNumber;
import com.tom_roush.pdfbox.cos.COSStream;
import com.tom_roush.pdfbox.cos.COSString;
import com.tom_roush.pdfbox.pdmodel.common.COSObjectable;
import com.tom_roush.pdfbox.pdmodel.common.PDRectangle;
import com.tom_roush.pdfbox.pdmodel.interactive.annotation.PDBorderEffectDictionary;
import com.tom_roush.pdfbox.pdmodel.interactive.annotation.PDBorderStyleDictionary;
import com.tom_roush.pdfbox.util.DateConverter;
import java.io.IOException;
import java.util.Calendar;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import org.bouncycastle.i18n.MessageBundle;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public abstract class FDFAnnotation implements COSObjectable {
    private static final int FLAG_HIDDEN = 2;
    private static final int FLAG_INVISIBLE = 1;
    private static final int FLAG_LOCKED = 128;
    private static final int FLAG_NO_ROTATE = 16;
    private static final int FLAG_NO_VIEW = 32;
    private static final int FLAG_NO_ZOOM = 8;
    private static final int FLAG_PRINTED = 4;
    private static final int FLAG_READ_ONLY = 64;
    private static final int FLAG_TOGGLE_NO_VIEW = 256;
    protected COSDictionary annot;

    public FDFAnnotation() {
        COSDictionary cOSDictionary = new COSDictionary();
        this.annot = cOSDictionary;
        cOSDictionary.setItem(COSName.TYPE, (COSBase) COSName.ANNOT);
    }

    public FDFAnnotation(COSDictionary cOSDictionary) {
        this.annot = cOSDictionary;
    }

    public FDFAnnotation(Element element) throws IOException {
        this();
        String attribute = element.getAttribute("page");
        if (attribute == null || attribute.isEmpty()) {
            throw new IOException("Error: missing required attribute 'page'");
        }
        setPage(Integer.parseInt(attribute));
        String attribute2 = element.getAttribute("color");
        if (attribute2 != null && attribute2.length() == 7 && attribute2.charAt(0) == '#') {
            setColor(new AWTColor(Integer.parseInt(attribute2.substring(1, 7), 16)));
        }
        setDate(element.getAttribute("date"));
        String attribute3 = element.getAttribute("flags");
        if (attribute3 != null) {
            for (String str : attribute3.split(",")) {
                if (str.equals("invisible")) {
                    setInvisible(true);
                } else if (str.equals("hidden")) {
                    setHidden(true);
                } else if (str.equals("print")) {
                    setPrinted(true);
                } else if (str.equals("nozoom")) {
                    setNoZoom(true);
                } else if (str.equals("norotate")) {
                    setNoRotate(true);
                } else if (str.equals("noview")) {
                    setNoView(true);
                } else if (str.equals("readonly")) {
                    setReadOnly(true);
                } else if (str.equals("locked")) {
                    setLocked(true);
                } else if (str.equals("togglenoview")) {
                    setToggleNoView(true);
                }
            }
        }
        setName(element.getAttribute(NamingTable.TAG));
        String attribute4 = element.getAttribute("rect");
        if (attribute4 == null) {
            throw new IOException("Error: missing attribute 'rect'");
        }
        String[] strArrSplit = attribute4.split(",");
        if (strArrSplit.length != 4) {
            throw new IOException("Error: wrong amount of numbers in attribute 'rect'");
        }
        float[] fArr = new float[4];
        for (int i = 0; i < 4; i++) {
            fArr[i] = Float.parseFloat(strArrSplit[i]);
        }
        COSArray cOSArray = new COSArray();
        cOSArray.setFloatArray(fArr);
        setRectangle(new PDRectangle(cOSArray));
        setTitle(element.getAttribute(MessageBundle.TITLE_ENTRY));
        setCreationDate(DateConverter.toCalendar(element.getAttribute("creationdate")));
        String attribute5 = element.getAttribute("opacity");
        if (attribute5 != null && !attribute5.isEmpty()) {
            setOpacity(Float.parseFloat(attribute5));
        }
        setSubject(element.getAttribute("subject"));
        String attribute6 = element.getAttribute("intent");
        setIntent(attribute6.isEmpty() ? element.getAttribute("IT") : attribute6);
        XPath xPathNewXPath = XPathFactory.newInstance().newXPath();
        try {
            setContents(xPathNewXPath.evaluate("contents[1]", element));
        } catch (XPathExpressionException unused) {
            Log.d("PdfBox-Android", "Error while evaluating XPath expression for richtext contents");
        }
        try {
            Node node = (Node) xPathNewXPath.evaluate("contents-richtext[1]", element, XPathConstants.NODE);
            if (node != null) {
                setRichContents(richContentsToString(node, true));
                setContents(node.getTextContent().trim());
            }
        } catch (XPathExpressionException unused2) {
            Log.d("PdfBox-Android", "Error while evaluating XPath expression for richtext contents");
        }
        PDBorderStyleDictionary pDBorderStyleDictionary = new PDBorderStyleDictionary();
        String attribute7 = element.getAttribute(StreamInformation.KEY_WIDTH);
        if (attribute7 != null && !attribute7.isEmpty()) {
            pDBorderStyleDictionary.setWidth(Float.parseFloat(attribute7));
        }
        if (pDBorderStyleDictionary.getWidth() > 0.0f) {
            String attribute8 = element.getAttribute("style");
            if (attribute8 != null && !attribute8.isEmpty()) {
                if (attribute8.equals(DownloadRequest.TYPE_DASH)) {
                    pDBorderStyleDictionary.setStyle("D");
                } else if (attribute8.equals("bevelled")) {
                    pDBorderStyleDictionary.setStyle("B");
                } else if (attribute8.equals("inset")) {
                    pDBorderStyleDictionary.setStyle("I");
                } else if (attribute8.equals(TtmlNode.UNDERLINE)) {
                    pDBorderStyleDictionary.setStyle(PDBorderStyleDictionary.STYLE_UNDERLINE);
                } else if (attribute8.equals("cloudy")) {
                    pDBorderStyleDictionary.setStyle("S");
                    PDBorderEffectDictionary pDBorderEffectDictionary = new PDBorderEffectDictionary();
                    pDBorderEffectDictionary.setStyle("C");
                    String attribute9 = element.getAttribute("intensity");
                    if (attribute9 != null && !attribute9.isEmpty()) {
                        pDBorderEffectDictionary.setIntensity(Float.parseFloat(element.getAttribute("intensity")));
                    }
                    setBorderEffect(pDBorderEffectDictionary);
                } else {
                    pDBorderStyleDictionary.setStyle("S");
                }
            }
            String attribute10 = element.getAttribute("dashes");
            if (attribute10 != null && !attribute10.isEmpty()) {
                String[] strArrSplit2 = attribute10.split(",");
                COSArray cOSArray2 = new COSArray();
                for (String str2 : strArrSplit2) {
                    cOSArray2.add((COSBase) COSNumber.get(str2));
                }
                pDBorderStyleDictionary.setDashStyle(cOSArray2);
            }
            setBorderStyle(pDBorderStyleDictionary);
        }
    }

    public static FDFAnnotation create(COSDictionary cOSDictionary) throws IOException {
        if (cOSDictionary != null) {
            if ("Text".equals(cOSDictionary.getNameAsString(COSName.SUBTYPE))) {
                return new FDFAnnotationText(cOSDictionary);
            }
            if ("Caret".equals(cOSDictionary.getNameAsString(COSName.SUBTYPE))) {
                return new FDFAnnotationCaret(cOSDictionary);
            }
            if ("FreeText".equals(cOSDictionary.getNameAsString(COSName.SUBTYPE))) {
                return new FDFAnnotationFreeText(cOSDictionary);
            }
            if ("FileAttachment".equals(cOSDictionary.getNameAsString(COSName.SUBTYPE))) {
                return new FDFAnnotationFileAttachment(cOSDictionary);
            }
            if ("Highlight".equals(cOSDictionary.getNameAsString(COSName.SUBTYPE))) {
                return new FDFAnnotationHighlight(cOSDictionary);
            }
            if ("Ink".equals(cOSDictionary.getNameAsString(COSName.SUBTYPE))) {
                return new FDFAnnotationInk(cOSDictionary);
            }
            if ("Line".equals(cOSDictionary.getNameAsString(COSName.SUBTYPE))) {
                return new FDFAnnotationLine(cOSDictionary);
            }
            if ("Link".equals(cOSDictionary.getNameAsString(COSName.SUBTYPE))) {
                return new FDFAnnotationLink(cOSDictionary);
            }
            if ("Circle".equals(cOSDictionary.getNameAsString(COSName.SUBTYPE))) {
                return new FDFAnnotationCircle(cOSDictionary);
            }
            if ("Square".equals(cOSDictionary.getNameAsString(COSName.SUBTYPE))) {
                return new FDFAnnotationSquare(cOSDictionary);
            }
            if ("Polygon".equals(cOSDictionary.getNameAsString(COSName.SUBTYPE))) {
                return new FDFAnnotationPolygon(cOSDictionary);
            }
            if (FDFAnnotationPolyline.SUBTYPE.equals(cOSDictionary.getNameAsString(COSName.SUBTYPE))) {
                return new FDFAnnotationPolyline(cOSDictionary);
            }
            if ("Sound".equals(cOSDictionary.getNameAsString(COSName.SUBTYPE))) {
                return new FDFAnnotationSound(cOSDictionary);
            }
            if ("Squiggly".equals(cOSDictionary.getNameAsString(COSName.SUBTYPE))) {
                return new FDFAnnotationSquiggly(cOSDictionary);
            }
            if ("Stamp".equals(cOSDictionary.getNameAsString(COSName.SUBTYPE))) {
                return new FDFAnnotationStamp(cOSDictionary);
            }
            if ("StrikeOut".equals(cOSDictionary.getNameAsString(COSName.SUBTYPE))) {
                return new FDFAnnotationStrikeOut(cOSDictionary);
            }
            if ("Underline".equals(cOSDictionary.getNameAsString(COSName.SUBTYPE))) {
                return new FDFAnnotationUnderline(cOSDictionary);
            }
            Log.w("PdfBox-Android", "Unknown or unsupported annotation type '" + cOSDictionary.getNameAsString(COSName.SUBTYPE) + "'");
        }
        return null;
    }

    @Override
    public COSDictionary getCOSObject() {
        return this.annot;
    }

    public Integer getPage() {
        COSNumber cOSNumber = (COSNumber) this.annot.getDictionaryObject(COSName.PAGE);
        if (cOSNumber != null) {
            return Integer.valueOf(cOSNumber.intValue());
        }
        return null;
    }

    public final void setPage(int i) {
        this.annot.setInt(COSName.PAGE, i);
    }

    public AWTColor getColor() {
        COSArray cOSArray = (COSArray) this.annot.getDictionaryObject(COSName.f2238C);
        if (cOSArray != null) {
            float[] floatArray = cOSArray.toFloatArray();
            if (floatArray.length >= 3) {
                return new AWTColor(floatArray[0], floatArray[1], floatArray[2]);
            }
        }
        return null;
    }

    public final void setColor(AWTColor aWTColor) {
        COSArray cOSArray = null;
        if (aWTColor != null) {
            float[] rGBColorComponents = aWTColor.getRGBColorComponents(null);
            cOSArray = new COSArray();
            cOSArray.setFloatArray(rGBColorComponents);
        }
        this.annot.setItem(COSName.f2238C, (COSBase) cOSArray);
    }

    public String getDate() {
        return this.annot.getString(COSName.f2281M);
    }

    public final void setDate(String str) {
        this.annot.setString(COSName.f2281M, str);
    }

    public boolean isInvisible() {
        return this.annot.getFlag(COSName.f2260F, 1);
    }

    public final void setInvisible(boolean z) {
        this.annot.setFlag(COSName.f2260F, 1, z);
    }

    public boolean isHidden() {
        return this.annot.getFlag(COSName.f2260F, 2);
    }

    public final void setHidden(boolean z) {
        this.annot.setFlag(COSName.f2260F, 2, z);
    }

    public boolean isPrinted() {
        return this.annot.getFlag(COSName.f2260F, 4);
    }

    public final void setPrinted(boolean z) {
        this.annot.setFlag(COSName.f2260F, 4, z);
    }

    public boolean isNoZoom() {
        return this.annot.getFlag(COSName.f2260F, 8);
    }

    public final void setNoZoom(boolean z) {
        this.annot.setFlag(COSName.f2260F, 8, z);
    }

    public boolean isNoRotate() {
        return this.annot.getFlag(COSName.f2260F, 16);
    }

    public final void setNoRotate(boolean z) {
        this.annot.setFlag(COSName.f2260F, 16, z);
    }

    public boolean isNoView() {
        return this.annot.getFlag(COSName.f2260F, 32);
    }

    public final void setNoView(boolean z) {
        this.annot.setFlag(COSName.f2260F, 32, z);
    }

    public boolean isReadOnly() {
        return this.annot.getFlag(COSName.f2260F, 64);
    }

    public final void setReadOnly(boolean z) {
        this.annot.setFlag(COSName.f2260F, 64, z);
    }

    public boolean isLocked() {
        return this.annot.getFlag(COSName.f2260F, 128);
    }

    public final void setLocked(boolean z) {
        this.annot.setFlag(COSName.f2260F, 128, z);
    }

    public boolean isToggleNoView() {
        return this.annot.getFlag(COSName.f2260F, 256);
    }

    public final void setToggleNoView(boolean z) {
        this.annot.setFlag(COSName.f2260F, 256, z);
    }

    public final void setName(String str) {
        this.annot.setString(COSName.f2285NM, str);
    }

    public String getName() {
        return this.annot.getString(COSName.f2285NM);
    }

    public final void setRectangle(PDRectangle pDRectangle) {
        this.annot.setItem(COSName.RECT, pDRectangle);
    }

    public PDRectangle getRectangle() {
        COSArray cOSArray = (COSArray) this.annot.getDictionaryObject(COSName.RECT);
        if (cOSArray != null) {
            return new PDRectangle(cOSArray);
        }
        return null;
    }

    public final void setContents(String str) {
        this.annot.setString(COSName.CONTENTS, str);
    }

    public String getContents() {
        return this.annot.getString(COSName.CONTENTS);
    }

    public final void setTitle(String str) {
        this.annot.setString(COSName.f2310T, str);
    }

    public String getTitle() {
        return this.annot.getString(COSName.f2310T);
    }

    public Calendar getCreationDate() throws IOException {
        return this.annot.getDate(COSName.CREATION_DATE);
    }

    public final void setCreationDate(Calendar calendar) {
        this.annot.setDate(COSName.CREATION_DATE, calendar);
    }

    public final void setOpacity(float f) {
        this.annot.setFloat(COSName.f2241CA, f);
    }

    public float getOpacity() {
        return this.annot.getFloat(COSName.f2241CA, 1.0f);
    }

    public final void setSubject(String str) {
        this.annot.setString(COSName.SUBJ, str);
    }

    public String getSubject() {
        return this.annot.getString(COSName.SUBJ);
    }

    public final void setIntent(String str) {
        this.annot.setName(COSName.f2272IT, str);
    }

    public String getIntent() {
        return this.annot.getNameAsString(COSName.f2272IT);
    }

    public String getRichContents() {
        return getStringOrStream(this.annot.getDictionaryObject(COSName.f2297RC));
    }

    public final void setRichContents(String str) {
        this.annot.setItem(COSName.f2297RC, (COSBase) new COSString(str));
    }

    public final void setBorderStyle(PDBorderStyleDictionary pDBorderStyleDictionary) {
        this.annot.setItem(COSName.f2237BS, pDBorderStyleDictionary);
    }

    public PDBorderStyleDictionary getBorderStyle() {
        COSDictionary cOSDictionary = (COSDictionary) this.annot.getDictionaryObject(COSName.f2237BS);
        if (cOSDictionary != null) {
            return new PDBorderStyleDictionary(cOSDictionary);
        }
        return null;
    }

    public final void setBorderEffect(PDBorderEffectDictionary pDBorderEffectDictionary) {
        this.annot.setItem(COSName.f2234BE, pDBorderEffectDictionary);
    }

    public PDBorderEffectDictionary getBorderEffect() {
        COSDictionary cOSDictionary = (COSDictionary) this.annot.getDictionaryObject(COSName.f2234BE);
        if (cOSDictionary != null) {
            return new PDBorderEffectDictionary(cOSDictionary);
        }
        return null;
    }

    protected final String getStringOrStream(COSBase cOSBase) {
        if (cOSBase == null) {
            return "";
        }
        if (cOSBase instanceof COSString) {
            return ((COSString) cOSBase).getString();
        }
        return cOSBase instanceof COSStream ? ((COSStream) cOSBase).toTextString() : "";
    }

    private String richContentsToString(Node node, boolean z) {
        try {
            NodeList nodeList = (NodeList) XPathFactory.newInstance().newXPath().evaluate("*", node, XPathConstants.NODESET);
            String nodeValue = nodeList.getLength() == 0 ? node.getFirstChild().getNodeValue() : "";
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node nodeItem = nodeList.item(i);
                if (nodeItem instanceof Element) {
                    nodeValue = nodeValue + richContentsToString(nodeItem, false);
                }
            }
            NamedNodeMap attributes = node.getAttributes();
            StringBuilder sb = new StringBuilder();
            for (int i2 = 0; i2 < attributes.getLength(); i2++) {
                Node nodeItem2 = attributes.item(i2);
                sb.append(String.format(" %s=\"%s\"", nodeItem2.getNodeName(), nodeItem2.getNodeValue()));
            }
            return z ? nodeValue : String.format("<%s%s>%s</%s>", node.getNodeName(), sb.toString(), nodeValue, node.getNodeName());
        } catch (XPathExpressionException unused) {
            Log.d("PdfBox-Android", "Error while evaluating XPath expression for richtext contents");
            return "";
        }
    }
}
