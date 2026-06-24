package com.tom_roush.pdfbox.pdmodel;

import android.graphics.Path;
import android.util.Log;
import com.p020hp.jipp.model.MaterialAmountUnit;
import com.p020hp.jipp.model.Media;
import com.tom_roush.harmony.awt.AWTColor;
import com.tom_roush.harmony.awt.geom.AffineTransform;
import com.tom_roush.pdfbox.cos.COSArray;
import com.tom_roush.pdfbox.cos.COSBase;
import com.tom_roush.pdfbox.cos.COSName;
import com.tom_roush.pdfbox.cos.COSNumber;
import com.tom_roush.pdfbox.pdfwriter.COSWriter;
import com.tom_roush.pdfbox.pdmodel.common.PDStream;
import com.tom_roush.pdfbox.pdmodel.documentinterchange.markedcontent.PDPropertyList;
import com.tom_roush.pdfbox.pdmodel.documentinterchange.taggedpdf.StandardStructureTypes;
import com.tom_roush.pdfbox.pdmodel.font.PDFont;
import com.tom_roush.pdfbox.pdmodel.graphics.PDXObject;
import com.tom_roush.pdfbox.pdmodel.graphics.color.PDColor;
import com.tom_roush.pdfbox.pdmodel.graphics.color.PDColorSpace;
import com.tom_roush.pdfbox.pdmodel.graphics.color.PDDeviceGray;
import com.tom_roush.pdfbox.pdmodel.graphics.color.PDDeviceRGB;
import com.tom_roush.pdfbox.pdmodel.graphics.form.PDFormXObject;
import com.tom_roush.pdfbox.pdmodel.graphics.image.PDImageXObject;
import com.tom_roush.pdfbox.pdmodel.graphics.image.PDInlineImage;
import com.tom_roush.pdfbox.pdmodel.graphics.shading.PDShading;
import com.tom_roush.pdfbox.pdmodel.graphics.state.PDExtendedGraphicsState;
import com.tom_roush.pdfbox.pdmodel.interactive.annotation.PDAppearanceStream;
import com.tom_roush.pdfbox.util.Charsets;
import com.tom_roush.pdfbox.util.Matrix;
import java.io.Closeable;
import java.io.IOException;
import java.io.OutputStream;
import java.text.NumberFormat;
import java.util.Iterator;
import java.util.Locale;
import java.util.Stack;

public final class PDPageContentStream implements Closeable {
    private final PDDocument document;
    private final Stack<PDFont> fontStack;
    private final NumberFormat formatDecimal;
    private boolean inTextMode;
    private final Stack<PDColorSpace> nonStrokingColorSpaceStack;
    private OutputStream output;
    private PDResources resources;
    private final Stack<PDColorSpace> strokingColorSpaceStack;

    private boolean isOutside255Interval(int i) {
        return i < 0 || i > 255;
    }

    private boolean isOutsideOneInterval(double d) {
        return d < 0.0d || d > 1.0d;
    }

    public enum AppendMode {
        OVERWRITE,
        APPEND,
        PREPEND;

        public boolean isOverwrite() {
            return this == OVERWRITE;
        }

        public boolean isPrepend() {
            return this == PREPEND;
        }
    }

    public PDPageContentStream(PDDocument pDDocument, PDPage pDPage) throws IOException {
        this(pDDocument, pDPage, AppendMode.OVERWRITE, true, false);
    }

    @Deprecated
    public PDPageContentStream(PDDocument pDDocument, PDPage pDPage, boolean z, boolean z2) throws IOException {
        this(pDDocument, pDPage, z, z2, false);
    }

    public PDPageContentStream(PDDocument pDDocument, PDPage pDPage, AppendMode appendMode, boolean z) throws IOException {
        this(pDDocument, pDPage, appendMode, z, false);
    }

    @Deprecated
    public PDPageContentStream(PDDocument pDDocument, PDPage pDPage, boolean z, boolean z2, boolean z3) throws IOException {
        this(pDDocument, pDPage, z ? AppendMode.APPEND : AppendMode.OVERWRITE, z2, z3);
    }

    public PDPageContentStream(PDDocument pDDocument, PDPage pDPage, AppendMode appendMode, boolean z, boolean z2) throws IOException {
        COSArray cOSArray;
        this.inTextMode = false;
        this.fontStack = new Stack<>();
        this.nonStrokingColorSpaceStack = new Stack<>();
        this.strokingColorSpaceStack = new Stack<>();
        NumberFormat numberInstance = NumberFormat.getNumberInstance(Locale.US);
        this.formatDecimal = numberInstance;
        this.document = pDDocument;
        COSName cOSName = z ? COSName.FLATE_DECODE : null;
        if (!appendMode.isOverwrite() && pDPage.hasContents()) {
            PDStream pDStream = new PDStream(pDDocument);
            COSBase dictionaryObject = pDPage.getCOSObject().getDictionaryObject(COSName.CONTENTS);
            if (dictionaryObject instanceof COSArray) {
                cOSArray = (COSArray) dictionaryObject;
            } else {
                COSArray cOSArray2 = new COSArray();
                cOSArray2.add(dictionaryObject);
                cOSArray = cOSArray2;
            }
            if (appendMode.isPrepend()) {
                cOSArray.add(0, pDStream.getCOSObject());
            } else {
                cOSArray.add(pDStream);
            }
            if (z2) {
                PDStream pDStream2 = new PDStream(pDDocument);
                this.output = pDStream2.createOutputStream(cOSName);
                saveGraphicsState();
                close();
                cOSArray.add(0, pDStream2.getCOSObject());
            }
            pDPage.getCOSObject().setItem(COSName.CONTENTS, (COSBase) cOSArray);
            this.output = pDStream.createOutputStream(cOSName);
            if (z2) {
                restoreGraphicsState();
            }
        } else {
            if (pDPage.hasContents()) {
                Log.w("PdfBox-Android", "You are overwriting an existing content, you should use the append mode");
            }
            PDStream pDStream3 = new PDStream(pDDocument);
            pDPage.setContents(pDStream3);
            this.output = pDStream3.createOutputStream(cOSName);
        }
        PDResources resources = pDPage.getResources();
        this.resources = resources;
        if (resources == null) {
            PDResources pDResources = new PDResources();
            this.resources = pDResources;
            pDPage.setResources(pDResources);
        }
        numberInstance.setMaximumFractionDigits(10);
        numberInstance.setGroupingUsed(false);
    }

    public PDPageContentStream(PDDocument pDDocument, PDAppearanceStream pDAppearanceStream) throws IOException {
        this(pDDocument, pDAppearanceStream, pDAppearanceStream.getStream().createOutputStream());
    }

    public PDPageContentStream(PDDocument pDDocument, PDAppearanceStream pDAppearanceStream, OutputStream outputStream) throws IOException {
        this.inTextMode = false;
        this.fontStack = new Stack<>();
        this.nonStrokingColorSpaceStack = new Stack<>();
        this.strokingColorSpaceStack = new Stack<>();
        NumberFormat numberInstance = NumberFormat.getNumberInstance(Locale.US);
        this.formatDecimal = numberInstance;
        this.document = pDDocument;
        this.output = outputStream;
        this.resources = pDAppearanceStream.getResources();
        numberInstance.setMaximumFractionDigits(4);
        numberInstance.setGroupingUsed(false);
    }

    public void beginText() throws IOException {
        if (this.inTextMode) {
            throw new IllegalStateException("Error: Nested beginText() calls are not allowed.");
        }
        writeOperator("BT");
        this.inTextMode = true;
    }

    public void endText() throws IOException {
        if (!this.inTextMode) {
            throw new IllegalStateException("Error: You must call beginText() before calling endText.");
        }
        writeOperator("ET");
        this.inTextMode = false;
    }

    public void setFont(PDFont pDFont, float f) throws IOException {
        if (this.fontStack.isEmpty()) {
            this.fontStack.add(pDFont);
        } else {
            this.fontStack.setElementAt(pDFont, r0.size() - 1);
        }
        if (pDFont.willBeSubset()) {
            this.document.getFontsToSubset().add(pDFont);
        }
        writeOperand(this.resources.add(pDFont));
        writeOperand(f);
        writeOperator("Tf");
    }

    @Deprecated
    public void drawString(String str) throws IOException {
        showText(str);
    }

    public void showText(String str) throws IOException {
        if (!this.inTextMode) {
            throw new IllegalStateException("Must call beginText() before showText()");
        }
        if (this.fontStack.isEmpty()) {
            throw new IllegalStateException("Must call setFont() before showText()");
        }
        PDFont pDFontPeek = this.fontStack.peek();
        if (pDFontPeek.willBeSubset()) {
            int iCharCount = 0;
            while (iCharCount < str.length()) {
                int iCodePointAt = str.codePointAt(iCharCount);
                pDFontPeek.addToSubset(iCodePointAt);
                iCharCount += Character.charCount(iCodePointAt);
            }
        }
        COSWriter.writeString(pDFontPeek.encode(str), this.output);
        write(" ");
        writeOperator("Tj");
    }

    public void setLeading(double d) throws IOException {
        writeOperand((float) d);
        writeOperator("TL");
    }

    public void newLine() throws IOException {
        if (!this.inTextMode) {
            throw new IllegalStateException("Must call beginText() before newLine()");
        }
        writeOperator("T*");
    }

    @Deprecated
    public void moveTextPositionByAmount(float f, float f2) throws IOException {
        newLineAtOffset(f, f2);
    }

    public void newLineAtOffset(float f, float f2) throws IOException {
        if (!this.inTextMode) {
            throw new IllegalStateException("Error: must call beginText() before newLineAtOffset()");
        }
        writeOperand(f);
        writeOperand(f2);
        writeOperator("Td");
    }

    @Deprecated
    public void setTextMatrix(double d, double d2, double d3, double d4, double d5, double d6) throws IOException {
        setTextMatrix(new Matrix((float) d, (float) d2, (float) d3, (float) d4, (float) d5, (float) d6));
    }

    @Deprecated
    public void setTextMatrix(AffineTransform affineTransform) throws IOException {
        setTextMatrix(new Matrix(affineTransform));
    }

    public void setTextMatrix(Matrix matrix) throws IOException {
        if (!this.inTextMode) {
            throw new IllegalStateException("Error: must call beginText() before setTextMatrix");
        }
        writeAffineTransform(matrix.createAffineTransform());
        writeOperator("Tm");
    }

    @Deprecated
    public void setTextScaling(double d, double d2, double d3, double d4) throws IOException {
        setTextMatrix(new Matrix((float) d, 0.0f, 0.0f, (float) d2, (float) d3, (float) d4));
    }

    @Deprecated
    public void setTextTranslation(double d, double d2) throws IOException {
        setTextMatrix(Matrix.getTranslateInstance((float) d, (float) d2));
    }

    @Deprecated
    public void setTextRotation(double d, double d2, double d3) throws IOException {
        setTextMatrix(Matrix.getRotateInstance(d, (float) d2, (float) d3));
    }

    public void drawImage(PDImageXObject pDImageXObject, float f, float f2) throws IOException {
        drawImage(pDImageXObject, f, f2, pDImageXObject.getWidth(), pDImageXObject.getHeight());
    }

    public void drawImage(PDImageXObject pDImageXObject, float f, float f2, float f3, float f4) throws IOException {
        if (this.inTextMode) {
            throw new IllegalStateException("Error: drawImage is not allowed within a text block.");
        }
        saveGraphicsState();
        transform(new Matrix(new AffineTransform(f3, 0.0f, 0.0f, f4, f, f2)));
        writeOperand(this.resources.add(pDImageXObject));
        writeOperator("Do");
        restoreGraphicsState();
    }

    @Deprecated
    public void drawInlineImage(PDInlineImage pDInlineImage, float f, float f2) throws IOException {
        drawImage(pDInlineImage, f, f2, pDInlineImage.getWidth(), pDInlineImage.getHeight());
    }

    public void drawImage(PDInlineImage pDInlineImage, float f, float f2) throws IOException {
        drawImage(pDInlineImage, f, f2, pDInlineImage.getWidth(), pDInlineImage.getHeight());
    }

    @Deprecated
    public void drawInlineImage(PDInlineImage pDInlineImage, float f, float f2, float f3, float f4) throws IOException {
        drawImage(pDInlineImage, f, f2, f3, f4);
    }

    public void drawImage(PDInlineImage pDInlineImage, float f, float f2, float f3, float f4) throws IOException {
        if (this.inTextMode) {
            throw new IllegalStateException("Error: drawImage is not allowed within a text block.");
        }
        saveGraphicsState();
        transform(new Matrix(f3, 0.0f, 0.0f, f4, f, f2));
        StringBuilder sb = new StringBuilder("BI\n /W ");
        sb.append(pDInlineImage.getWidth());
        sb.append("\n /H ");
        sb.append(pDInlineImage.getHeight());
        sb.append("\n /CS /");
        sb.append(pDInlineImage.getColorSpace().getName());
        if (pDInlineImage.getDecode() != null && pDInlineImage.getDecode().size() > 0) {
            sb.append("\n /D [");
            Iterator<COSBase> it = pDInlineImage.getDecode().iterator();
            while (it.hasNext()) {
                sb.append(((COSNumber) it.next()).intValue());
                sb.append(" ");
            }
            sb.append("]");
        }
        if (pDInlineImage.isStencil()) {
            sb.append("\n /IM true");
        }
        sb.append("\n /BPC ");
        sb.append(pDInlineImage.getBitsPerComponent());
        write(sb.toString());
        writeLine();
        writeOperator("ID");
        writeBytes(pDInlineImage.getData());
        writeLine();
        writeOperator("EI");
        restoreGraphicsState();
    }

    @Deprecated
    public void drawXObject(PDXObject pDXObject, float f, float f2, float f3, float f4) throws IOException {
        drawXObject(pDXObject, new AffineTransform(f3, 0.0f, 0.0f, f4, f, f2));
    }

    @Deprecated
    public void drawXObject(PDXObject pDXObject, AffineTransform affineTransform) throws IOException {
        if (this.inTextMode) {
            throw new IllegalStateException("Error: drawXObject is not allowed within a text block.");
        }
        COSName cOSNameAdd = this.resources.add(pDXObject, pDXObject instanceof PDImageXObject ? "Im" : StandardStructureTypes.FORM);
        saveGraphicsState();
        transform(new Matrix(affineTransform));
        writeOperand(cOSNameAdd);
        writeOperator("Do");
        restoreGraphicsState();
    }

    public void drawForm(PDFormXObject pDFormXObject) throws IOException {
        if (this.inTextMode) {
            throw new IllegalStateException("Error: drawForm is not allowed within a text block.");
        }
        writeOperand(this.resources.add(pDFormXObject));
        writeOperator("Do");
    }

    @Deprecated
    public void concatenate2CTM(double d, double d2, double d3, double d4, double d5, double d6) throws IOException {
        transform(new Matrix((float) d, (float) d2, (float) d3, (float) d4, (float) d5, (float) d6));
    }

    @Deprecated
    public void concatenate2CTM(AffineTransform affineTransform) throws IOException {
        transform(new Matrix(affineTransform));
    }

    public void transform(Matrix matrix) throws IOException {
        writeAffineTransform(matrix.createAffineTransform());
        writeOperator("cm");
    }

    public void saveGraphicsState() throws IOException {
        if (!this.fontStack.isEmpty()) {
            Stack<PDFont> stack = this.fontStack;
            stack.push(stack.peek());
        }
        if (!this.strokingColorSpaceStack.isEmpty()) {
            Stack<PDColorSpace> stack2 = this.strokingColorSpaceStack;
            stack2.push(stack2.peek());
        }
        if (!this.nonStrokingColorSpaceStack.isEmpty()) {
            Stack<PDColorSpace> stack3 = this.nonStrokingColorSpaceStack;
            stack3.push(stack3.peek());
        }
        writeOperator("q");
    }

    public void restoreGraphicsState() throws IOException {
        if (!this.fontStack.isEmpty()) {
            this.fontStack.pop();
        }
        if (!this.strokingColorSpaceStack.isEmpty()) {
            this.strokingColorSpaceStack.pop();
        }
        if (!this.nonStrokingColorSpaceStack.isEmpty()) {
            this.nonStrokingColorSpaceStack.pop();
        }
        writeOperator("Q");
    }

    @Deprecated
    public void setStrokingColorSpace(PDColorSpace pDColorSpace) throws IOException {
        setStrokingColorSpaceStack(pDColorSpace);
        writeOperand(getName(pDColorSpace));
        writeOperator("CS");
    }

    @Deprecated
    public void setNonStrokingColorSpace(PDColorSpace pDColorSpace) throws IOException {
        setNonStrokingColorSpaceStack(pDColorSpace);
        writeOperand(getName(pDColorSpace));
        writeOperator("cs");
    }

    private COSName getName(PDColorSpace pDColorSpace) throws IOException {
        if ((pDColorSpace instanceof PDDeviceGray) || (pDColorSpace instanceof PDDeviceRGB)) {
            return COSName.getPDFName(pDColorSpace.getName());
        }
        return this.resources.add(pDColorSpace);
    }

    public void setStrokingColor(PDColor pDColor) throws IOException {
        if (this.strokingColorSpaceStack.isEmpty() || this.strokingColorSpaceStack.peek() != pDColor.getColorSpace()) {
            writeOperand(getName(pDColor.getColorSpace()));
            writeOperator("CS");
            setStrokingColorSpaceStack(pDColor.getColorSpace());
        }
        for (float f : pDColor.getComponents()) {
            writeOperand(f);
        }
        writeOperator("SC");
    }

    public void setStrokingColor(AWTColor aWTColor) throws IOException {
        setStrokingColor(new PDColor(new float[]{aWTColor.getRed() / 255.0f, aWTColor.getGreen() / 255.0f, aWTColor.getBlue() / 255.0f}, PDDeviceRGB.INSTANCE));
    }

    @Deprecated
    public void setStrokingColor(float[] fArr) throws IOException {
        if (this.strokingColorSpaceStack.isEmpty()) {
            throw new IllegalStateException("The color space must be set before setting a color");
        }
        for (float f : fArr) {
            writeOperand(f);
        }
        this.strokingColorSpaceStack.peek();
        writeOperator("SC");
    }

    public void setStrokingColor(int i, int i2, int i3) throws IOException {
        if (isOutside255Interval(i) || isOutside255Interval(i2) || isOutside255Interval(i3)) {
            throw new IllegalArgumentException("Parameters must be within 0..255, but are " + String.format("(%d,%d,%d)", Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(i3)));
        }
        writeOperand(i / 255.0f);
        writeOperand(i2 / 255.0f);
        writeOperand(i3 / 255.0f);
        writeOperator("RG");
        setStrokingColorSpaceStack(PDDeviceRGB.INSTANCE);
    }

    @Deprecated
    public void setStrokingColor(int i, int i2, int i3, int i4) throws IOException {
        if (isOutside255Interval(i) || isOutside255Interval(i2) || isOutside255Interval(i3) || isOutside255Interval(i4)) {
            throw new IllegalArgumentException("Parameters must be within 0..255, but are " + String.format("(%d,%d,%d,%d)", Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(i3), Integer.valueOf(i4)));
        }
        setStrokingColor(i / 255.0f, i2 / 255.0f, i3 / 255.0f, i4 / 255.0f);
    }

    public void setStrokingColor(float f, float f2, float f3, float f4) throws IOException {
        if (isOutsideOneInterval(f) || isOutsideOneInterval(f2) || isOutsideOneInterval(f3) || isOutsideOneInterval(f4)) {
            throw new IllegalArgumentException("Parameters must be within 0..1, but are " + String.format("(%.2f,%.2f,%.2f,%.2f)", Float.valueOf(f), Float.valueOf(f2), Float.valueOf(f3), Float.valueOf(f4)));
        }
        writeOperand(f);
        writeOperand(f2);
        writeOperand(f3);
        writeOperand(f4);
        writeOperator("K");
    }

    @Deprecated
    public void setStrokingColor(int i) throws IOException {
        if (isOutside255Interval(i)) {
            throw new IllegalArgumentException("Parameter must be within 0..255, but is " + i);
        }
        setStrokingColor(i / 255.0f);
    }

    public void setStrokingColor(double d) throws IOException {
        if (isOutsideOneInterval(d)) {
            throw new IllegalArgumentException("Parameter must be within 0..1, but is " + d);
        }
        writeOperand((float) d);
        writeOperator("G");
    }

    public void setNonStrokingColor(PDColor pDColor) throws IOException {
        if (this.nonStrokingColorSpaceStack.isEmpty() || this.nonStrokingColorSpaceStack.peek() != pDColor.getColorSpace()) {
            writeOperand(getName(pDColor.getColorSpace()));
            writeOperator("cs");
            setNonStrokingColorSpaceStack(pDColor.getColorSpace());
        }
        for (float f : pDColor.getComponents()) {
            writeOperand(f);
        }
        writeOperator("sc");
    }

    public void setNonStrokingColor(AWTColor aWTColor) throws IOException {
        setNonStrokingColor(new PDColor(new float[]{aWTColor.getRed() / 255.0f, aWTColor.getGreen() / 255.0f, aWTColor.getBlue() / 255.0f}, PDDeviceRGB.INSTANCE));
    }

    @Deprecated
    public void setNonStrokingColor(float[] fArr) throws IOException {
        if (this.nonStrokingColorSpaceStack.isEmpty()) {
            throw new IllegalStateException("The color space must be set before setting a color");
        }
        for (float f : fArr) {
            writeOperand(f);
        }
        this.nonStrokingColorSpaceStack.peek();
        writeOperator("sc");
    }

    public void setNonStrokingColor(int i, int i2, int i3) throws IOException {
        if (isOutside255Interval(i) || isOutside255Interval(i2) || isOutside255Interval(i3)) {
            throw new IllegalArgumentException("Parameters must be within 0..255, but are " + String.format("(%d,%d,%d)", Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(i3)));
        }
        writeOperand(i / 255.0f);
        writeOperand(i2 / 255.0f);
        writeOperand(i3 / 255.0f);
        writeOperator("rg");
        setNonStrokingColorSpaceStack(PDDeviceRGB.INSTANCE);
    }

    public void setNonStrokingColor(int i, int i2, int i3, int i4) throws IOException {
        if (isOutside255Interval(i) || isOutside255Interval(i2) || isOutside255Interval(i3) || isOutside255Interval(i4)) {
            throw new IllegalArgumentException("Parameters must be within 0..255, but are " + String.format("(%d,%d,%d,%d)", Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(i3), Integer.valueOf(i4)));
        }
        setNonStrokingColor(i / 255.0f, i2 / 255.0f, i3 / 255.0f, i4 / 255.0f);
    }

    public void setNonStrokingColor(double d, double d2, double d3, double d4) throws IOException {
        if (isOutsideOneInterval(d) || isOutsideOneInterval(d2) || isOutsideOneInterval(d3) || isOutsideOneInterval(d4)) {
            throw new IllegalArgumentException("Parameters must be within 0..1, but are " + String.format("(%.2f,%.2f,%.2f,%.2f)", Double.valueOf(d), Double.valueOf(d2), Double.valueOf(d3), Double.valueOf(d4)));
        }
        writeOperand((float) d);
        writeOperand((float) d2);
        writeOperand((float) d3);
        writeOperand((float) d4);
        writeOperator("k");
    }

    public void setNonStrokingColor(int i) throws IOException {
        if (isOutside255Interval(i)) {
            throw new IllegalArgumentException("Parameter must be within 0..255, but is " + i);
        }
        setNonStrokingColor(i / 255.0f);
    }

    public void setNonStrokingColor(double d) throws IOException {
        if (isOutsideOneInterval(d)) {
            throw new IllegalArgumentException("Parameter must be within 0..1, but is " + d);
        }
        writeOperand((float) d);
        writeOperator(MaterialAmountUnit.f719g);
        setNonStrokingColorSpaceStack(PDDeviceGray.INSTANCE);
    }

    public void addRect(float f, float f2, float f3, float f4) throws IOException {
        if (this.inTextMode) {
            throw new IllegalStateException("Error: addRect is not allowed within a text block.");
        }
        writeOperand(f);
        writeOperand(f2);
        writeOperand(f3);
        writeOperand(f4);
        writeOperator("re");
    }

    @Deprecated
    public void fillRect(float f, float f2, float f3, float f4) throws IOException {
        if (this.inTextMode) {
            throw new IllegalStateException("Error: fillRect is not allowed within a text block.");
        }
        addRect(f, f2, f3, f4);
        fill();
    }

    @Deprecated
    public void addBezier312(float f, float f2, float f3, float f4, float f5, float f6) throws IOException {
        curveTo(f, f2, f3, f4, f5, f6);
    }

    public void curveTo(float f, float f2, float f3, float f4, float f5, float f6) throws IOException {
        if (this.inTextMode) {
            throw new IllegalStateException("Error: curveTo is not allowed within a text block.");
        }
        writeOperand(f);
        writeOperand(f2);
        writeOperand(f3);
        writeOperand(f4);
        writeOperand(f5);
        writeOperand(f6);
        writeOperator(Media.f727c);
    }

    @Deprecated
    public void addBezier32(float f, float f2, float f3, float f4) throws IOException {
        curveTo2(f, f2, f3, f4);
    }

    public void curveTo2(float f, float f2, float f3, float f4) throws IOException {
        if (this.inTextMode) {
            throw new IllegalStateException("Error: curveTo2 is not allowed within a text block.");
        }
        writeOperand(f);
        writeOperand(f2);
        writeOperand(f3);
        writeOperand(f4);
        writeOperator("v");
    }

    @Deprecated
    public void addBezier31(float f, float f2, float f3, float f4) throws IOException {
        curveTo1(f, f2, f3, f4);
    }

    public void curveTo1(float f, float f2, float f3, float f4) throws IOException {
        if (this.inTextMode) {
            throw new IllegalStateException("Error: curveTo1 is not allowed within a text block.");
        }
        writeOperand(f);
        writeOperand(f2);
        writeOperand(f3);
        writeOperand(f4);
        writeOperator("y");
    }

    public void moveTo(float f, float f2) throws IOException {
        if (this.inTextMode) {
            throw new IllegalStateException("Error: moveTo is not allowed within a text block.");
        }
        writeOperand(f);
        writeOperand(f2);
        writeOperator(MaterialAmountUnit.f722m);
    }

    public void lineTo(float f, float f2) throws IOException {
        if (this.inTextMode) {
            throw new IllegalStateException("Error: lineTo is not allowed within a text block.");
        }
        writeOperand(f);
        writeOperand(f2);
        writeOperator(MaterialAmountUnit.f721l);
    }

    @Deprecated
    public void addLine(float f, float f2, float f3, float f4) throws IOException {
        if (this.inTextMode) {
            throw new IllegalStateException("Error: addLine is not allowed within a text block.");
        }
        moveTo(f, f2);
        lineTo(f3, f4);
    }

    @Deprecated
    public void drawLine(float f, float f2, float f3, float f4) throws IOException {
        if (this.inTextMode) {
            throw new IllegalStateException("Error: drawLine is not allowed within a text block.");
        }
        moveTo(f, f2);
        lineTo(f3, f4);
        stroke();
    }

    @Deprecated
    public void addPolygon(float[] fArr, float[] fArr2) throws IOException {
        if (this.inTextMode) {
            throw new IllegalStateException("Error: addPolygon is not allowed within a text block.");
        }
        if (fArr.length != fArr2.length) {
            throw new IllegalArgumentException("Error: some points are missing coordinate");
        }
        for (int i = 0; i < fArr.length; i++) {
            if (i == 0) {
                moveTo(fArr[i], fArr2[i]);
            } else {
                lineTo(fArr[i], fArr2[i]);
            }
        }
        closeSubPath();
    }

    @Deprecated
    public void drawPolygon(float[] fArr, float[] fArr2) throws IOException {
        if (this.inTextMode) {
            throw new IllegalStateException("Error: drawPolygon is not allowed within a text block.");
        }
        addPolygon(fArr, fArr2);
        stroke();
    }

    @Deprecated
    public void fillPolygon(float[] fArr, float[] fArr2) throws IOException {
        if (this.inTextMode) {
            throw new IllegalStateException("Error: fillPolygon is not allowed within a text block.");
        }
        addPolygon(fArr, fArr2);
        fill();
    }

    public void stroke() throws IOException {
        if (this.inTextMode) {
            throw new IllegalStateException("Error: stroke is not allowed within a text block.");
        }
        writeOperator("S");
    }

    public void closeAndStroke() throws IOException {
        if (this.inTextMode) {
            throw new IllegalStateException("Error: closeAndStroke is not allowed within a text block.");
        }
        writeOperator("s");
    }

    @Deprecated
    public void fill(Path.FillType fillType) throws IOException {
        if (fillType == Path.FillType.WINDING) {
            fill();
        } else {
            if (fillType == Path.FillType.EVEN_ODD) {
                fillEvenOdd();
                return;
            }
            throw new IllegalArgumentException("Error: unknown value for winding rule");
        }
    }

    public void fill() throws IOException {
        if (this.inTextMode) {
            throw new IllegalStateException("Error: fill is not allowed within a text block.");
        }
        writeOperator(Media.f730f);
    }

    public void fillEvenOdd() throws IOException {
        if (this.inTextMode) {
            throw new IllegalStateException("Error: fillEvenOdd is not allowed within a text block.");
        }
        writeOperator("f*");
    }

    public void fillAndStroke() throws IOException {
        if (this.inTextMode) {
            throw new IllegalStateException("Error: fillAndStroke is not allowed within a text block.");
        }
        writeOperator("B");
    }

    public void fillAndStrokeEvenOdd() throws IOException {
        if (this.inTextMode) {
            throw new IllegalStateException("Error: fillAndStrokeEvenOdd is not allowed within a text block.");
        }
        writeOperator("B*");
    }

    public void closeAndFillAndStroke() throws IOException {
        if (this.inTextMode) {
            throw new IllegalStateException("Error: closeAndFillAndStroke is not allowed within a text block.");
        }
        writeOperator(Media.f726b);
    }

    public void closeAndFillAndStrokeEvenOdd() throws IOException {
        if (this.inTextMode) {
            throw new IllegalStateException("Error: closeAndFillAndStrokeEvenOdd is not allowed within a text block.");
        }
        writeOperator("b*");
    }

    public void shadingFill(PDShading pDShading) throws IOException {
        if (this.inTextMode) {
            throw new IllegalStateException("Error: shadingFill is not allowed within a text block.");
        }
        writeOperand(this.resources.add(pDShading));
        writeOperator("sh");
    }

    @Deprecated
    public void closeSubPath() throws IOException {
        closePath();
    }

    public void closePath() throws IOException {
        if (this.inTextMode) {
            throw new IllegalStateException("Error: closePath is not allowed within a text block.");
        }
        writeOperator("h");
    }

    @Deprecated
    public void clipPath(Path.FillType fillType) throws IOException {
        if (this.inTextMode) {
            throw new IllegalStateException("Error: clipPath is not allowed within a text block.");
        }
        if (fillType == Path.FillType.WINDING || fillType == Path.FillType.EVEN_ODD) {
            writeOperator("W");
            writeOperator("n");
            return;
        }
        throw new IllegalArgumentException("Error: unknown value for winding rule");
    }

    public void clip() throws IOException {
        if (this.inTextMode) {
            throw new IllegalStateException("Error: clip is not allowed within a text block.");
        }
        writeOperator("W");
        writeOperator("n");
    }

    public void clipEvenOdd() throws IOException {
        if (this.inTextMode) {
            throw new IllegalStateException("Error: clipEvenOdd is not allowed within a text block.");
        }
        writeOperator("W*");
        writeOperator("n");
    }

    public void setLineWidth(float f) throws IOException {
        if (this.inTextMode) {
            throw new IllegalStateException("Error: setLineWidth is not allowed within a text block.");
        }
        writeOperand(f);
        writeOperator("w");
    }

    public void setLineJoinStyle(int i) throws IOException {
        if (this.inTextMode) {
            throw new IllegalStateException("Error: setLineJoinStyle is not allowed within a text block.");
        }
        if (i >= 0 && i <= 2) {
            writeOperand(i);
            writeOperator("j");
            return;
        }
        throw new IllegalArgumentException("Error: unknown value for line join style");
    }

    public void setLineCapStyle(int i) throws IOException {
        if (this.inTextMode) {
            throw new IllegalStateException("Error: setLineCapStyle is not allowed within a text block.");
        }
        if (i >= 0 && i <= 2) {
            writeOperand(i);
            writeOperator("J");
            return;
        }
        throw new IllegalArgumentException("Error: unknown value for line cap style");
    }

    public void setLineDashPattern(float[] fArr, float f) throws IOException {
        if (this.inTextMode) {
            throw new IllegalStateException("Error: setLineDashPattern is not allowed within a text block.");
        }
        write("[");
        for (float f2 : fArr) {
            writeOperand(f2);
        }
        write("] ");
        writeOperand(f);
        writeOperator(Media.f728d);
    }

    @Deprecated
    public void beginMarkedContentSequence(COSName cOSName) throws IOException {
        beginMarkedContent(cOSName);
    }

    public void beginMarkedContent(COSName cOSName) throws IOException {
        writeOperand(cOSName);
        writeOperator("BMC");
    }

    @Deprecated
    public void beginMarkedContentSequence(COSName cOSName, COSName cOSName2) throws IOException {
        writeOperand(cOSName);
        writeOperand(cOSName2);
        writeOperator("BDC");
    }

    public void beginMarkedContent(COSName cOSName, PDPropertyList pDPropertyList) throws IOException {
        writeOperand(cOSName);
        writeOperand(this.resources.add(pDPropertyList));
        writeOperator("BDC");
    }

    @Deprecated
    public void endMarkedContentSequence() throws IOException {
        endMarkedContent();
    }

    public void endMarkedContent() throws IOException {
        writeOperator("EMC");
    }

    @Deprecated
    public void appendRawCommands(String str) throws IOException {
        this.output.write(str.getBytes(Charsets.US_ASCII));
    }

    @Deprecated
    public void appendRawCommands(byte[] bArr) throws IOException {
        this.output.write(bArr);
    }

    @Deprecated
    public void appendRawCommands(int i) throws IOException {
        this.output.write(i);
    }

    @Deprecated
    public void appendRawCommands(double d) throws IOException {
        this.output.write(this.formatDecimal.format(d).getBytes(Charsets.US_ASCII));
    }

    @Deprecated
    public void appendRawCommands(float f) throws IOException {
        this.output.write(this.formatDecimal.format(f).getBytes(Charsets.US_ASCII));
    }

    @Deprecated
    public void appendCOSName(COSName cOSName) throws IOException {
        cOSName.writePDF(this.output);
    }

    public void setGraphicsStateParameters(PDExtendedGraphicsState pDExtendedGraphicsState) throws IOException {
        writeOperand(this.resources.add(pDExtendedGraphicsState));
        writeOperator("gs");
    }

    private void writeOperand(float f) throws IOException {
        write(this.formatDecimal.format(f));
        this.output.write(32);
    }

    private void writeOperand(int i) throws IOException {
        write(this.formatDecimal.format(i));
        this.output.write(32);
    }

    private void writeOperand(COSName cOSName) throws IOException {
        cOSName.writePDF(this.output);
        this.output.write(32);
    }

    private void writeOperator(String str) throws IOException {
        this.output.write(str.getBytes(Charsets.US_ASCII));
        this.output.write(10);
    }

    private void write(String str) throws IOException {
        this.output.write(str.getBytes(Charsets.US_ASCII));
    }

    private void writeLine() throws IOException {
        this.output.write(10);
    }

    private void writeBytes(byte[] bArr) throws IOException {
        this.output.write(bArr);
    }

    private void writeAffineTransform(AffineTransform affineTransform) throws IOException {
        double[] dArr = new double[6];
        affineTransform.getMatrix(dArr);
        for (int i = 0; i < 6; i++) {
            writeOperand((float) dArr[i]);
        }
    }

    @Override
    public void close() throws IOException {
        this.output.close();
    }

    private void setStrokingColorSpaceStack(PDColorSpace pDColorSpace) {
        if (this.strokingColorSpaceStack.isEmpty()) {
            this.strokingColorSpaceStack.add(pDColorSpace);
        } else {
            this.strokingColorSpaceStack.setElementAt(pDColorSpace, r0.size() - 1);
        }
    }

    private void setNonStrokingColorSpaceStack(PDColorSpace pDColorSpace) {
        if (this.nonStrokingColorSpaceStack.isEmpty()) {
            this.nonStrokingColorSpaceStack.add(pDColorSpace);
        } else {
            this.nonStrokingColorSpaceStack.setElementAt(pDColorSpace, r0.size() - 1);
        }
    }
}
