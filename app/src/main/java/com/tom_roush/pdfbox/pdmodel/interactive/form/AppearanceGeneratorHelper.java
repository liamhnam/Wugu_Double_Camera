package com.tom_roush.pdfbox.pdmodel.interactive.form;

import cc.uling.usdk.constants.BoardConst;
import com.tom_roush.harmony.awt.geom.AffineTransform;
import com.tom_roush.pdfbox.contentstream.operator.Operator;
import com.tom_roush.pdfbox.cos.COSName;
import com.tom_roush.pdfbox.pdfparser.PDFStreamParser;
import com.tom_roush.pdfbox.pdfwriter.ContentStreamWriter;
import com.tom_roush.pdfbox.pdmodel.PDPageContentStream;
import com.tom_roush.pdfbox.pdmodel.common.PDRectangle;
import com.tom_roush.pdfbox.pdmodel.font.PDFont;
import com.tom_roush.pdfbox.pdmodel.graphics.color.PDColor;
import com.tom_roush.pdfbox.pdmodel.interactive.action.PDFormFieldAdditionalActions;
import com.tom_roush.pdfbox.pdmodel.interactive.annotation.PDAnnotationWidget;
import com.tom_roush.pdfbox.pdmodel.interactive.annotation.PDAppearanceCharacteristicsDictionary;
import com.tom_roush.pdfbox.pdmodel.interactive.annotation.PDAppearanceDictionary;
import com.tom_roush.pdfbox.pdmodel.interactive.annotation.PDAppearanceEntry;
import com.tom_roush.pdfbox.pdmodel.interactive.annotation.PDAppearanceStream;
import com.tom_roush.pdfbox.pdmodel.interactive.annotation.PDBorderStyleDictionary;
import com.tom_roush.pdfbox.pdmodel.interactive.form.PlainTextFormatter;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

class AppearanceGeneratorHelper {
    private static final float DEFAULT_FONT_SIZE = 12.0f;
    private static final float DEFAULT_PADDING = 0.5f;
    private static final int FONTSCALE = 1000;
    private final PDDefaultAppearanceString defaultAppearance;
    private final PDVariableText field;
    private String value;
    private static final Operator BMC = Operator.getOperator("BMC");
    private static final Operator EMC = Operator.getOperator("EMC");
    private static final int[] HIGHLIGHT_COLOR = {153, 193, BoardConst.CODE_BOARD_FAULT};

    AppearanceGeneratorHelper(PDVariableText pDVariableText) throws IOException {
        this.field = pDVariableText;
        this.defaultAppearance = pDVariableText.getDefaultAppearanceString();
    }

    public void setAppearanceValue(String str) throws IOException {
        PDAppearanceStream appearanceStream;
        this.value = str;
        for (PDAnnotationWidget pDAnnotationWidget : this.field.getWidgets()) {
            PDFormFieldAdditionalActions actions = this.field.getActions();
            if (actions == null || actions.getF() == null || pDAnnotationWidget.getCOSObject().getDictionaryObject(COSName.f2230AP) != null) {
                PDAppearanceDictionary appearance = pDAnnotationWidget.getAppearance();
                if (appearance == null) {
                    appearance = new PDAppearanceDictionary();
                    pDAnnotationWidget.setAppearance(appearance);
                }
                PDAppearanceEntry normalAppearance = appearance.getNormalAppearance();
                if (normalAppearance.isStream()) {
                    appearanceStream = normalAppearance.getAppearanceStream();
                } else {
                    PDAppearanceStream pDAppearanceStream = new PDAppearanceStream(this.field.getAcroForm().getDocument());
                    pDAppearanceStream.setBBox(pDAnnotationWidget.getRectangle().createRetranslatedRectangle());
                    appearance.setNormalAppearance(pDAppearanceStream);
                    appearanceStream = pDAppearanceStream;
                }
                if (pDAnnotationWidget.getAppearanceCharacteristics() != null || appearanceStream.getContentStream().getLength() == 0) {
                    initializeAppearanceContent(pDAnnotationWidget, appearanceStream);
                }
                setAppearanceContent(pDAnnotationWidget, appearanceStream);
            }
        }
    }

    private void initializeAppearanceContent(PDAnnotationWidget pDAnnotationWidget, PDAppearanceStream pDAppearanceStream) throws IOException {
        float width;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PDPageContentStream pDPageContentStream = new PDPageContentStream(this.field.getAcroForm().getDocument(), pDAppearanceStream, byteArrayOutputStream);
        PDAppearanceCharacteristicsDictionary appearanceCharacteristics = pDAnnotationWidget.getAppearanceCharacteristics();
        if (appearanceCharacteristics != null) {
            PDColor borderColour = appearanceCharacteristics.getBorderColour();
            if (borderColour != null) {
                pDPageContentStream.setNonStrokingColor(borderColour);
                width = 1.0f;
            } else {
                width = 0.0f;
            }
            PDBorderStyleDictionary borderStyle = pDAnnotationWidget.getBorderStyle();
            if (borderStyle != null && borderStyle.getWidth() > 0.0f) {
                width = borderStyle.getWidth();
            }
            if (width > 0.0f) {
                pDPageContentStream.setLineWidth(width);
                PDRectangle pDRectangleApplyPadding = applyPadding(resolveBoundingBox(pDAnnotationWidget, pDAppearanceStream), Math.max(0.5f, width / 2.0f));
                pDPageContentStream.addRect(pDRectangleApplyPadding.getLowerLeftX(), pDRectangleApplyPadding.getLowerLeftY(), pDRectangleApplyPadding.getWidth(), pDRectangleApplyPadding.getHeight());
                pDPageContentStream.closeAndStroke();
            }
        }
        pDPageContentStream.close();
        byteArrayOutputStream.close();
        writeToStream(byteArrayOutputStream.toByteArray(), pDAppearanceStream);
    }

    private List<Object> tokenize(PDAppearanceStream pDAppearanceStream) throws IOException {
        PDFStreamParser pDFStreamParser = new PDFStreamParser(pDAppearanceStream);
        pDFStreamParser.parse();
        return pDFStreamParser.getTokens();
    }

    private void setAppearanceContent(PDAnnotationWidget pDAnnotationWidget, PDAppearanceStream pDAppearanceStream) throws IOException {
        this.defaultAppearance.copyNeededResourcesTo(pDAppearanceStream);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ContentStreamWriter contentStreamWriter = new ContentStreamWriter(byteArrayOutputStream);
        List<Object> list = tokenize(pDAppearanceStream);
        Operator operator = BMC;
        int iIndexOf = list.indexOf(operator);
        if (iIndexOf == -1) {
            contentStreamWriter.writeTokens(list);
            contentStreamWriter.writeTokens(COSName.f2316TX, operator);
        } else {
            contentStreamWriter.writeTokens(list.subList(0, iIndexOf + 1));
        }
        insertGeneratedAppearance(pDAnnotationWidget, pDAppearanceStream, byteArrayOutputStream);
        Operator operator2 = EMC;
        int iIndexOf2 = list.indexOf(operator2);
        if (iIndexOf2 == -1) {
            contentStreamWriter.writeTokens(operator2);
        } else {
            contentStreamWriter.writeTokens(list.subList(iIndexOf2, list.size()));
        }
        byteArrayOutputStream.close();
        writeToStream(byteArrayOutputStream.toByteArray(), pDAppearanceStream);
    }

    private void insertGeneratedAppearance(PDAnnotationWidget pDAnnotationWidget, PDAppearanceStream pDAppearanceStream, OutputStream outputStream) throws IOException {
        float fMin;
        PDPageContentStream pDPageContentStream = new PDPageContentStream(this.field.getAcroForm().getDocument(), pDAppearanceStream, outputStream);
        pDAppearanceStream.setMatrix(new AffineTransform());
        pDAppearanceStream.setFormType(1);
        float width = pDAnnotationWidget.getBorderStyle() != null ? pDAnnotationWidget.getBorderStyle().getWidth() : 0.0f;
        PDRectangle pDRectangleApplyPadding = applyPadding(resolveBoundingBox(pDAnnotationWidget, pDAppearanceStream), Math.max(1.0f, width));
        PDRectangle pDRectangleApplyPadding2 = applyPadding(pDRectangleApplyPadding, Math.max(1.0f, width));
        pDPageContentStream.saveGraphicsState();
        pDPageContentStream.addRect(pDRectangleApplyPadding.getLowerLeftX(), pDRectangleApplyPadding.getLowerLeftY(), pDRectangleApplyPadding.getWidth(), pDRectangleApplyPadding.getHeight());
        pDPageContentStream.clip();
        PDFont font = this.field.getDefaultAppearanceString().getFont();
        float fCalculateFontSize = calculateFontSize(font, pDRectangleApplyPadding2);
        if (this.field instanceof PDListBox) {
            insertGeneratedSelectionHighlight(pDPageContentStream, pDAppearanceStream, font, fCalculateFontSize);
        }
        pDPageContentStream.beginText();
        this.field.getDefaultAppearanceString().writeTo(pDPageContentStream, fCalculateFontSize);
        float f = fCalculateFontSize / 1000.0f;
        float height = font.getBoundingBox().getHeight() * f;
        float capHeight = font.getFontDescriptor().getCapHeight() * f;
        float descent = font.getFontDescriptor().getDescent() * f;
        PDVariableText pDVariableText = this.field;
        if ((pDVariableText instanceof PDTextField) && ((PDTextField) pDVariableText).isMultiline()) {
            fMin = pDRectangleApplyPadding2.getUpperRightY() - height;
        } else if (capHeight > pDRectangleApplyPadding.getHeight()) {
            fMin = pDRectangleApplyPadding.getLowerLeftY() + (-descent);
        } else {
            float lowerLeftY = pDRectangleApplyPadding.getLowerLeftY() + ((pDRectangleApplyPadding.getHeight() - capHeight) / 2.0f);
            float f2 = -descent;
            fMin = lowerLeftY - pDRectangleApplyPadding.getLowerLeftY() < f2 ? Math.min(f2 + pDRectangleApplyPadding2.getLowerLeftY(), Math.max(lowerLeftY, (pDRectangleApplyPadding2.getHeight() - pDRectangleApplyPadding2.getLowerLeftY()) - capHeight)) : lowerLeftY;
        }
        float lowerLeftX = pDRectangleApplyPadding2.getLowerLeftX();
        if (shallComb()) {
            insertGeneratedCombAppearance(pDPageContentStream, pDAppearanceStream, font, fCalculateFontSize);
        } else if (this.field instanceof PDListBox) {
            insertGeneratedListboxAppearance(pDPageContentStream, pDAppearanceStream, pDRectangleApplyPadding2, font, fCalculateFontSize);
        } else {
            PlainText plainText = new PlainText(this.value);
            AppearanceStyle appearanceStyle = new AppearanceStyle();
            appearanceStyle.setFont(font);
            appearanceStyle.setFontSize(fCalculateFontSize);
            appearanceStyle.setLeading(font.getBoundingBox().getHeight() * f);
            new PlainTextFormatter.Builder(pDPageContentStream).style(appearanceStyle).text(plainText).width(pDRectangleApplyPadding2.getWidth()).wrapLines(isMultiLine()).initialOffset(lowerLeftX, fMin).textAlign(this.field.getQ()).build().format();
        }
        pDPageContentStream.endText();
        pDPageContentStream.restoreGraphicsState();
        pDPageContentStream.close();
    }

    private boolean isMultiLine() {
        PDVariableText pDVariableText = this.field;
        return (pDVariableText instanceof PDTextField) && ((PDTextField) pDVariableText).isMultiline();
    }

    private boolean shallComb() {
        PDVariableText pDVariableText = this.field;
        return (!(pDVariableText instanceof PDTextField) || !((PDTextField) pDVariableText).isComb() || ((PDTextField) this.field).isMultiline() || ((PDTextField) this.field).isPassword() || ((PDTextField) this.field).isFileSelect()) ? false : true;
    }

    private void insertGeneratedCombAppearance(PDPageContentStream pDPageContentStream, PDAppearanceStream pDAppearanceStream, PDFont pDFont, float f) throws IOException {
        int maxLen = ((PDTextField) this.field).getMaxLen();
        int iMin = Math.min(this.value.length(), maxLen);
        PDRectangle pDRectangleApplyPadding = applyPadding(pDAppearanceStream.getBBox(), 1.0f);
        float width = pDAppearanceStream.getBBox().getWidth() / maxLen;
        float lowerLeftY = pDRectangleApplyPadding.getLowerLeftY() + ((pDAppearanceStream.getBBox().getHeight() - ((pDFont.getFontDescriptor().getAscent() / 1000.0f) * f)) / 2.0f);
        float f2 = width / 2.0f;
        int i = 0;
        float f3 = 0.0f;
        while (i < iMin) {
            int i2 = i + 1;
            String strSubstring = this.value.substring(i, i2);
            float stringWidth = ((pDFont.getStringWidth(strSubstring) / 1000.0f) * f) / 2.0f;
            pDPageContentStream.newLineAtOffset((f2 + (f3 / 2.0f)) - (stringWidth / 2.0f), lowerLeftY);
            pDPageContentStream.showText(strSubstring);
            f2 = width;
            lowerLeftY = 0.0f;
            f3 = stringWidth;
            i = i2;
        }
    }

    private void insertGeneratedSelectionHighlight(PDPageContentStream pDPageContentStream, PDAppearanceStream pDAppearanceStream, PDFont pDFont, float f) throws IOException {
        int iIndexOf;
        List<Integer> selectedOptionsIndex = ((PDListBox) this.field).getSelectedOptionsIndex();
        List<String> value = ((PDListBox) this.field).getValue();
        List<String> optionsExportValues = ((PDListBox) this.field).getOptionsExportValues();
        if (value.isEmpty() || optionsExportValues.isEmpty()) {
            iIndexOf = 0;
        } else if (!selectedOptionsIndex.isEmpty()) {
            iIndexOf = selectedOptionsIndex.get(0).intValue();
        } else {
            iIndexOf = optionsExportValues.indexOf(value.get(0));
        }
        int topIndex = ((PDListBox) this.field).getTopIndex();
        float height = ((pDFont.getBoundingBox().getHeight() * f) / 1000.0f) - 2.0f;
        PDRectangle pDRectangleApplyPadding = applyPadding(pDAppearanceStream.getBBox(), 1.0f);
        int[] iArr = HIGHLIGHT_COLOR;
        pDPageContentStream.setNonStrokingColor(iArr[0], iArr[1], iArr[2]);
        pDPageContentStream.addRect(pDRectangleApplyPadding.getLowerLeftX(), pDRectangleApplyPadding.getUpperRightY() - (((iIndexOf - topIndex) + 1) * height), pDRectangleApplyPadding.getWidth(), height);
        pDPageContentStream.fill();
        pDPageContentStream.setNonStrokingColor(0);
    }

    private void insertGeneratedListboxAppearance(PDPageContentStream pDPageContentStream, PDAppearanceStream pDAppearanceStream, PDRectangle pDRectangle, PDFont pDFont, float f) throws IOException {
        pDPageContentStream.setNonStrokingColor(0);
        int q = this.field.getQ();
        if (q == 1 || q == 2) {
            float width = (pDAppearanceStream.getBBox().getWidth() - ((pDFont.getStringWidth(this.value) / 1000.0f) * f)) - 4.0f;
            if (q == 1) {
                width /= 2.0f;
            }
            pDPageContentStream.newLineAtOffset(width, 0.0f);
        } else if (q != 0) {
            throw new IOException("Error: Unknown justification value:" + q);
        }
        List<String> optionsDisplayValues = ((PDListBox) this.field).getOptionsDisplayValues();
        int size = optionsDisplayValues.size();
        float upperRightY = pDRectangle.getUpperRightY();
        int topIndex = ((PDListBox) this.field).getTopIndex();
        for (int i = topIndex; i < size; i++) {
            if (i == topIndex) {
                upperRightY -= (pDFont.getFontDescriptor().getAscent() / 1000.0f) * f;
            } else {
                upperRightY -= (pDFont.getBoundingBox().getHeight() / 1000.0f) * f;
                pDPageContentStream.beginText();
            }
            pDPageContentStream.newLineAtOffset(pDRectangle.getLowerLeftX(), upperRightY);
            pDPageContentStream.showText(optionsDisplayValues.get(i));
            if (i - topIndex != size - 1) {
                pDPageContentStream.endText();
            }
        }
    }

    private void writeToStream(byte[] bArr, PDAppearanceStream pDAppearanceStream) throws IOException {
        OutputStream outputStreamCreateOutputStream = pDAppearanceStream.getCOSObject().createOutputStream();
        outputStreamCreateOutputStream.write(bArr);
        outputStreamCreateOutputStream.close();
    }

    private float calculateFontSize(PDFont pDFont, PDRectangle pDRectangle) throws IOException {
        float fontSize = this.defaultAppearance.getFontSize();
        if (fontSize != 0.0f) {
            return fontSize;
        }
        if (isMultiLine()) {
            return DEFAULT_FONT_SIZE;
        }
        float scaleY = pDFont.getFontMatrix().getScaleY() * 1000.0f;
        float width = (pDRectangle.getWidth() / (pDFont.getStringWidth(this.value) * pDFont.getFontMatrix().getScaleX())) * pDFont.getFontMatrix().getScaleX() * 1000.0f;
        float capHeight = (pDFont.getFontDescriptor().getCapHeight() + (-pDFont.getFontDescriptor().getDescent())) * pDFont.getFontMatrix().getScaleY();
        if (capHeight <= 0.0f) {
            capHeight = pDFont.getBoundingBox().getHeight() * pDFont.getFontMatrix().getScaleY();
        }
        return Math.min((pDRectangle.getHeight() / capHeight) * scaleY, width);
    }

    private PDRectangle resolveBoundingBox(PDAnnotationWidget pDAnnotationWidget, PDAppearanceStream pDAppearanceStream) {
        PDRectangle bBox = pDAppearanceStream.getBBox();
        return bBox == null ? pDAnnotationWidget.getRectangle().createRetranslatedRectangle() : bBox;
    }

    private PDRectangle applyPadding(PDRectangle pDRectangle, float f) {
        float lowerLeftX = pDRectangle.getLowerLeftX() + f;
        float lowerLeftY = pDRectangle.getLowerLeftY() + f;
        float f2 = f * 2.0f;
        return new PDRectangle(lowerLeftX, lowerLeftY, pDRectangle.getWidth() - f2, pDRectangle.getHeight() - f2);
    }
}
