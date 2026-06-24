package com.tom_roush.pdfbox.pdmodel.interactive.form;

import com.tom_roush.pdfbox.contentstream.operator.Operator;
import com.tom_roush.pdfbox.cos.COSArray;
import com.tom_roush.pdfbox.cos.COSBase;
import com.tom_roush.pdfbox.cos.COSName;
import com.tom_roush.pdfbox.cos.COSNumber;
import com.tom_roush.pdfbox.cos.COSObject;
import com.tom_roush.pdfbox.cos.COSString;
import com.tom_roush.pdfbox.pdfparser.PDFStreamParser;
import com.tom_roush.pdfbox.pdmodel.PDPageContentStream;
import com.tom_roush.pdfbox.pdmodel.PDResources;
import com.tom_roush.pdfbox.pdmodel.font.PDFont;
import com.tom_roush.pdfbox.pdmodel.graphics.color.PDColor;
import com.tom_roush.pdfbox.pdmodel.graphics.color.PDDeviceColorSpace;
import com.tom_roush.pdfbox.pdmodel.graphics.color.PDDeviceRGB;
import com.tom_roush.pdfbox.pdmodel.interactive.annotation.PDAppearanceStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class PDDefaultAppearanceString {
    private static final float DEFAULT_FONT_SIZE = 12.0f;
    private final PDResources defaultResources;
    private PDFont font;
    private PDColor fontColor;
    private COSName fontName;
    private float fontSize = DEFAULT_FONT_SIZE;

    PDDefaultAppearanceString(COSString cOSString, PDResources pDResources) throws IOException {
        if (cOSString == null) {
            throw new IllegalArgumentException("/DA is a required entry");
        }
        if (pDResources == null) {
            throw new IllegalArgumentException("/DR is a required entry");
        }
        this.defaultResources = pDResources;
        processAppearanceStringOperators(cOSString.getBytes());
    }

    private void processAppearanceStringOperators(byte[] bArr) throws IOException {
        ArrayList arrayList = new ArrayList();
        PDFStreamParser pDFStreamParser = new PDFStreamParser(bArr);
        for (Object nextToken = pDFStreamParser.parseNextToken(); nextToken != null; nextToken = pDFStreamParser.parseNextToken()) {
            if (nextToken instanceof COSObject) {
                arrayList.add(((COSObject) nextToken).getObject());
            } else if (nextToken instanceof Operator) {
                processOperator((Operator) nextToken, arrayList);
                arrayList = new ArrayList();
            } else {
                arrayList.add((COSBase) nextToken);
            }
        }
    }

    private void processOperator(Operator operator, List<COSBase> list) throws IOException {
        String name = operator.getName();
        if ("Tf".equals(name)) {
            processSetFont(list);
        } else if ("rg".equals(name)) {
            processSetFontColor(list);
        }
    }

    private void processSetFont(List<COSBase> list) throws IOException {
        if (list.size() < 2) {
            throw new IOException("Missing operands for set font operator " + Arrays.toString(list.toArray()));
        }
        COSBase cOSBase = list.get(0);
        COSBase cOSBase2 = list.get(1);
        if ((cOSBase instanceof COSName) && (cOSBase2 instanceof COSNumber)) {
            COSName cOSName = (COSName) cOSBase;
            PDFont font = this.defaultResources.getFont(cOSName);
            float fFloatValue = ((COSNumber) cOSBase2).floatValue();
            if (font == null) {
                throw new IOException("Could not find font: /" + cOSName.getName());
            }
            setFontName(cOSName);
            setFont(font);
            setFontSize(fFloatValue);
        }
    }

    private void processSetFontColor(List<COSBase> list) throws IOException {
        PDDeviceRGB pDDeviceRGB = PDDeviceRGB.INSTANCE;
        if ((pDDeviceRGB instanceof PDDeviceColorSpace) && list.size() < pDDeviceRGB.getNumberOfComponents()) {
            throw new IOException("Missing operands for set non stroking color operator " + Arrays.toString(list.toArray()));
        }
        COSArray cOSArray = new COSArray();
        cOSArray.addAll(list);
        setFontColor(new PDColor(cOSArray, pDDeviceRGB));
    }

    COSName getFontName() {
        return this.fontName;
    }

    void setFontName(COSName cOSName) {
        this.fontName = cOSName;
    }

    PDFont getFont() throws IOException {
        return this.font;
    }

    void setFont(PDFont pDFont) {
        this.font = pDFont;
    }

    public float getFontSize() {
        return this.fontSize;
    }

    void setFontSize(float f) {
        this.fontSize = f;
    }

    PDColor getFontColor() {
        return this.fontColor;
    }

    void setFontColor(PDColor pDColor) {
        this.fontColor = pDColor;
    }

    void writeTo(PDPageContentStream pDPageContentStream, float f) throws IOException {
        float fontSize = getFontSize();
        if (fontSize != 0.0f) {
            f = fontSize;
        }
        pDPageContentStream.setFont(getFont(), f);
        if (getFontColor() != null) {
            pDPageContentStream.setNonStrokingColor(getFontColor());
        }
    }

    void copyNeededResourcesTo(PDAppearanceStream pDAppearanceStream) throws IOException {
        PDResources resources = pDAppearanceStream.getResources();
        if (resources == null) {
            resources = new PDResources();
            pDAppearanceStream.setResources(resources);
        }
        if (resources.getFont(getFontName()) == null) {
            resources.put(this.fontName, getFont());
        }
    }
}
