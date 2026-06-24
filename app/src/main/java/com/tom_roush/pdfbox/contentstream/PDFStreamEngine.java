package com.tom_roush.pdfbox.contentstream;

import android.graphics.PointF;
import android.graphics.RectF;
import android.util.Log;
import com.tom_roush.pdfbox.contentstream.operator.MissingOperandException;
import com.tom_roush.pdfbox.contentstream.operator.Operator;
import com.tom_roush.pdfbox.contentstream.operator.OperatorProcessor;
import com.tom_roush.pdfbox.contentstream.operator.state.EmptyGraphicsStackException;
import com.tom_roush.pdfbox.cos.COSArray;
import com.tom_roush.pdfbox.cos.COSBase;
import com.tom_roush.pdfbox.cos.COSNumber;
import com.tom_roush.pdfbox.cos.COSObject;
import com.tom_roush.pdfbox.cos.COSString;
import com.tom_roush.pdfbox.filter.MissingImageReaderException;
import com.tom_roush.pdfbox.pdfparser.PDFStreamParser;
import com.tom_roush.pdfbox.pdmodel.MissingResourceException;
import com.tom_roush.pdfbox.pdmodel.PDPage;
import com.tom_roush.pdfbox.pdmodel.PDResources;
import com.tom_roush.pdfbox.pdmodel.common.PDRectangle;
import com.tom_roush.pdfbox.pdmodel.font.PDFont;
import com.tom_roush.pdfbox.pdmodel.font.PDFontFactory;
import com.tom_roush.pdfbox.pdmodel.font.PDType3CharProc;
import com.tom_roush.pdfbox.pdmodel.font.PDType3Font;
import com.tom_roush.pdfbox.pdmodel.graphics.PDLineDashPattern;
import com.tom_roush.pdfbox.pdmodel.graphics.color.PDColor;
import com.tom_roush.pdfbox.pdmodel.graphics.color.PDColorSpace;
import com.tom_roush.pdfbox.pdmodel.graphics.form.PDFormXObject;
import com.tom_roush.pdfbox.pdmodel.graphics.form.PDTransparencyGroup;
import com.tom_roush.pdfbox.pdmodel.graphics.pattern.PDTilingPattern;
import com.tom_roush.pdfbox.pdmodel.graphics.state.PDGraphicsState;
import com.tom_roush.pdfbox.pdmodel.graphics.state.PDTextState;
import com.tom_roush.pdfbox.pdmodel.interactive.annotation.PDAnnotation;
import com.tom_roush.pdfbox.pdmodel.interactive.annotation.PDAppearanceStream;
import com.tom_roush.pdfbox.util.Matrix;
import com.tom_roush.pdfbox.util.Vector;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

public abstract class PDFStreamEngine {
    private PDPage currentPage;
    private Matrix initialMatrix;
    private boolean isProcessingPage;
    private PDResources resources;
    private Matrix textLineMatrix;
    private Matrix textMatrix;
    private final Map<String, OperatorProcessor> operators = new HashMap(80);
    private Stack<PDGraphicsState> graphicsStack = new Stack<>();

    public void beginText() throws IOException {
    }

    public void endText() throws IOException {
    }

    protected void showFontGlyph(Matrix matrix, PDFont pDFont, int i, String str, Vector vector) throws IOException {
    }

    protected void unsupportedOperator(Operator operator, List<COSBase> list) throws IOException {
    }

    protected PDFStreamEngine() {
    }

    @Deprecated
    public void registerOperatorProcessor(String str, OperatorProcessor operatorProcessor) {
        operatorProcessor.setContext(this);
        this.operators.put(str, operatorProcessor);
    }

    public final void addOperator(OperatorProcessor operatorProcessor) {
        operatorProcessor.setContext(this);
        this.operators.put(operatorProcessor.getName(), operatorProcessor);
    }

    private void initPage(PDPage pDPage) {
        if (pDPage == null) {
            throw new IllegalArgumentException("Page cannot be null");
        }
        this.currentPage = pDPage;
        this.graphicsStack.clear();
        this.graphicsStack.push(new PDGraphicsState(pDPage.getCropBox()));
        this.textMatrix = null;
        this.textLineMatrix = null;
        this.resources = null;
        this.initialMatrix = pDPage.getMatrix();
    }

    public void processPage(PDPage pDPage) throws IOException {
        initPage(pDPage);
        if (pDPage.hasContents()) {
            this.isProcessingPage = true;
            processStream(pDPage);
            this.isProcessingPage = false;
        }
    }

    public void showTransparencyGroup(PDTransparencyGroup pDTransparencyGroup) throws IOException {
        processTransparencyGroup(pDTransparencyGroup);
    }

    public void showForm(PDFormXObject pDFormXObject) throws IOException {
        if (this.currentPage == null) {
            throw new IllegalStateException("No current page, call #processChildStream(PDContentStream, PDPage) instead");
        }
        processStream(pDFormXObject);
    }

    public void processSoftMask(PDTransparencyGroup pDTransparencyGroup) throws IOException {
        saveGraphicsState();
        getGraphicsState().setSoftMask(null);
        processTransparencyGroup(pDTransparencyGroup);
        restoreGraphicsState();
    }

    public void processTransparencyGroup(PDTransparencyGroup pDTransparencyGroup) throws IOException {
        if (this.currentPage == null) {
            throw new IllegalStateException("No current page, call #processChildStream(PDContentStream, PDPage) instead");
        }
        PDResources pDResourcesPushResources = pushResources(pDTransparencyGroup);
        Stack<PDGraphicsState> stackSaveGraphicsStack = saveGraphicsStack();
        getGraphicsState().getCurrentTransformationMatrix().concatenate(pDTransparencyGroup.getMatrix());
        clipToRect(pDTransparencyGroup.getBBox());
        processStreamOperators(pDTransparencyGroup);
        restoreGraphicsStack(stackSaveGraphicsStack);
        popResources(pDResourcesPushResources);
    }

    protected void processType3Stream(PDType3CharProc pDType3CharProc, Matrix matrix) throws IOException {
        if (this.currentPage == null) {
            throw new IllegalStateException("No current page, call #processChildStream(PDContentStream, PDPage) instead");
        }
        PDResources pDResourcesPushResources = pushResources(pDType3CharProc);
        Stack<PDGraphicsState> stackSaveGraphicsStack = saveGraphicsStack();
        getGraphicsState().setCurrentTransformationMatrix(matrix);
        getGraphicsState().getCurrentTransformationMatrix().concatenate(pDType3CharProc.getMatrix());
        Matrix matrix2 = this.textMatrix;
        this.textMatrix = new Matrix();
        Matrix matrix3 = this.textLineMatrix;
        this.textLineMatrix = new Matrix();
        processStreamOperators(pDType3CharProc);
        this.textMatrix = matrix2;
        this.textLineMatrix = matrix3;
        restoreGraphicsStack(stackSaveGraphicsStack);
        popResources(pDResourcesPushResources);
    }

    protected void processAnnotation(PDAnnotation pDAnnotation, PDAppearanceStream pDAppearanceStream) throws IOException {
        PDResources pDResourcesPushResources = pushResources(pDAppearanceStream);
        Stack<PDGraphicsState> stackSaveGraphicsStack = saveGraphicsStack();
        PDRectangle bBox = pDAppearanceStream.getBBox();
        PDRectangle rectangle = pDAnnotation.getRectangle();
        Matrix matrix = pDAppearanceStream.getMatrix();
        if (rectangle != null && rectangle.getWidth() > 0.0f && rectangle.getHeight() > 0.0f && bBox != null) {
            RectF rectF = new RectF();
            bBox.transform(matrix).computeBounds(rectF, true);
            Matrix translateInstance = Matrix.getTranslateInstance(rectangle.getLowerLeftX(), rectangle.getLowerLeftY());
            translateInstance.concatenate(Matrix.getScaleInstance(rectangle.getWidth() / rectF.width(), rectangle.getHeight() / rectF.height()));
            translateInstance.concatenate(Matrix.getTranslateInstance(-rectF.left, -rectF.top));
            getGraphicsState().setCurrentTransformationMatrix(Matrix.concatenate(translateInstance, matrix));
            clipToRect(bBox);
            processStreamOperators(pDAppearanceStream);
        }
        restoreGraphicsStack(stackSaveGraphicsStack);
        popResources(pDResourcesPushResources);
    }

    protected final void processTilingPattern(PDTilingPattern pDTilingPattern, PDColor pDColor, PDColorSpace pDColorSpace) throws IOException {
        processTilingPattern(pDTilingPattern, pDColor, pDColorSpace, pDTilingPattern.getMatrix());
    }

    protected final void processTilingPattern(PDTilingPattern pDTilingPattern, PDColor pDColor, PDColorSpace pDColorSpace, Matrix matrix) throws IOException {
        PDResources pDResourcesPushResources = pushResources(pDTilingPattern);
        Matrix matrix2 = this.initialMatrix;
        this.initialMatrix = Matrix.concatenate(matrix2, matrix);
        Stack<PDGraphicsState> stackSaveGraphicsStack = saveGraphicsStack();
        RectF rectF = new RectF();
        pDTilingPattern.getBBox().transform(matrix).computeBounds(rectF, true);
        this.graphicsStack.push(new PDGraphicsState(new PDRectangle(rectF.left, rectF.top, rectF.width(), rectF.height())));
        if (pDColorSpace != null) {
            PDColor pDColor2 = new PDColor(pDColor.getComponents(), pDColorSpace);
            getGraphicsState().setNonStrokingColorSpace(pDColorSpace);
            getGraphicsState().setNonStrokingColor(pDColor2);
            getGraphicsState().setStrokingColorSpace(pDColorSpace);
            getGraphicsState().setStrokingColor(pDColor2);
        }
        getGraphicsState().getCurrentTransformationMatrix().concatenate(matrix);
        clipToRect(pDTilingPattern.getBBox());
        processStreamOperators(pDTilingPattern);
        this.initialMatrix = matrix2;
        restoreGraphicsStack(stackSaveGraphicsStack);
        popResources(pDResourcesPushResources);
    }

    public void showAnnotation(PDAnnotation pDAnnotation) throws IOException {
        PDAppearanceStream appearance = getAppearance(pDAnnotation);
        if (appearance != null) {
            processAnnotation(pDAnnotation, appearance);
        }
    }

    public PDAppearanceStream getAppearance(PDAnnotation pDAnnotation) {
        return pDAnnotation.getNormalAppearanceStream();
    }

    protected void processChildStream(PDContentStream pDContentStream, PDPage pDPage) throws IOException {
        if (this.isProcessingPage) {
            throw new IllegalStateException("Current page has already been set via  #processPage(PDPage) call #processChildStream(PDContentStream) instead");
        }
        initPage(pDPage);
        processStream(pDContentStream);
        this.currentPage = null;
    }

    private void processStream(PDContentStream pDContentStream) throws IOException {
        PDResources pDResourcesPushResources = pushResources(pDContentStream);
        Stack<PDGraphicsState> stackSaveGraphicsStack = saveGraphicsStack();
        Matrix matrix = this.initialMatrix;
        getGraphicsState().getCurrentTransformationMatrix().concatenate(pDContentStream.getMatrix());
        this.initialMatrix = getGraphicsState().getCurrentTransformationMatrix().m1575clone();
        clipToRect(pDContentStream.getBBox());
        processStreamOperators(pDContentStream);
        this.initialMatrix = matrix;
        restoreGraphicsStack(stackSaveGraphicsStack);
        popResources(pDResourcesPushResources);
    }

    private void processStreamOperators(PDContentStream pDContentStream) throws IOException {
        ArrayList arrayList = new ArrayList();
        PDFStreamParser pDFStreamParser = new PDFStreamParser(pDContentStream);
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

    private PDResources pushResources(PDContentStream pDContentStream) {
        PDResources pDResources = this.resources;
        PDResources resources = pDContentStream.getResources();
        if (resources != null) {
            this.resources = resources;
        } else if (this.resources == null) {
            this.resources = this.currentPage.getResources();
        }
        if (this.resources == null) {
            this.resources = new PDResources();
        }
        return pDResources;
    }

    private void popResources(PDResources pDResources) {
        this.resources = pDResources;
    }

    private void clipToRect(PDRectangle pDRectangle) {
        if (pDRectangle != null) {
            getGraphicsState().intersectClippingPath(pDRectangle.transform(getGraphicsState().getCurrentTransformationMatrix()));
        }
    }

    public void showTextString(byte[] bArr) throws IOException {
        showText(bArr);
    }

    public void showTextStrings(COSArray cOSArray) throws IOException {
        float f;
        PDTextState textState = getGraphicsState().getTextState();
        float fontSize = textState.getFontSize();
        float horizontalScaling = textState.getHorizontalScaling() / 100.0f;
        PDFont font = textState.getFont();
        boolean zIsVertical = font != null ? font.isVertical() : false;
        for (COSBase cOSBase : cOSArray) {
            if (cOSBase instanceof COSNumber) {
                float fFloatValue = ((COSNumber) cOSBase).floatValue();
                float f2 = 0.0f;
                if (zIsVertical) {
                    f = ((-fFloatValue) / 1000.0f) * fontSize;
                } else {
                    f2 = ((-fFloatValue) / 1000.0f) * fontSize * horizontalScaling;
                    f = 0.0f;
                }
                applyTextAdjustment(f2, f);
            } else if (cOSBase instanceof COSString) {
                showText(((COSString) cOSBase).getBytes());
            } else {
                throw new IOException("Unknown type in array for TJ operation:" + cOSBase);
            }
        }
    }

    protected void applyTextAdjustment(float f, float f2) throws IOException {
        this.textMatrix.concatenate(Matrix.getTranslateInstance(f, f2));
    }

    protected void showText(byte[] bArr) throws IOException {
        float y;
        PDGraphicsState graphicsState = getGraphicsState();
        PDTextState textState = graphicsState.getTextState();
        PDFont font = textState.getFont();
        if (font == null) {
            Log.w("PdfBox-Android", "No current font, will use default");
            font = PDFontFactory.createDefaultFont();
        }
        PDFont pDFont = font;
        float fontSize = textState.getFontSize();
        float horizontalScaling = textState.getHorizontalScaling() / 100.0f;
        float characterSpacing = textState.getCharacterSpacing();
        Matrix matrix = new Matrix(fontSize * horizontalScaling, 0.0f, 0.0f, fontSize, 0.0f, textState.getRise());
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bArr);
        while (byteArrayInputStream.available() > 0) {
            int iAvailable = byteArrayInputStream.available();
            int code = pDFont.readCode(byteArrayInputStream);
            int iAvailable2 = iAvailable - byteArrayInputStream.available();
            String unicode = pDFont.toUnicode(code);
            float x = 0.0f;
            float wordSpacing = (iAvailable2 == 1 && code == 32) ? textState.getWordSpacing() + 0.0f : 0.0f;
            Matrix matrixMultiply = matrix.multiply(this.textMatrix).multiply(graphicsState.getCurrentTransformationMatrix());
            if (pDFont.isVertical()) {
                matrixMultiply.translate(pDFont.getPositionVector(code));
            }
            Vector displacement = pDFont.getDisplacement(code);
            saveGraphicsState();
            Matrix matrix2 = this.textMatrix;
            Matrix matrix3 = this.textLineMatrix;
            Matrix matrix4 = matrix;
            showGlyph(matrixMultiply, pDFont, code, unicode, displacement);
            this.textMatrix = matrix2;
            this.textLineMatrix = matrix3;
            restoreGraphicsState();
            if (pDFont.isVertical()) {
                y = (displacement.getY() * fontSize) + characterSpacing + wordSpacing;
            } else {
                x = ((displacement.getX() * fontSize) + characterSpacing + wordSpacing) * horizontalScaling;
                y = 0.0f;
            }
            this.textMatrix.concatenate(Matrix.getTranslateInstance(x, y));
            matrix = matrix4;
        }
    }

    protected void showGlyph(Matrix matrix, PDFont pDFont, int i, String str, Vector vector) throws IOException {
        if (pDFont instanceof PDType3Font) {
            showType3Glyph(matrix, (PDType3Font) pDFont, i, str, vector);
        } else {
            showFontGlyph(matrix, pDFont, i, str, vector);
        }
    }

    protected void showType3Glyph(Matrix matrix, PDType3Font pDType3Font, int i, String str, Vector vector) throws IOException {
        PDType3CharProc charProc = pDType3Font.getCharProc(i);
        if (charProc != null) {
            processType3Stream(charProc, matrix);
        }
    }

    public void processOperator(String str, List<COSBase> list) throws IOException {
        processOperator(Operator.getOperator(str), list);
    }

    protected void processOperator(Operator operator, List<COSBase> list) throws IOException {
        OperatorProcessor operatorProcessor = this.operators.get(operator.getName());
        if (operatorProcessor != null) {
            operatorProcessor.setContext(this);
            try {
                operatorProcessor.process(operator, list);
                return;
            } catch (IOException e) {
                operatorException(operator, list, e);
                return;
            }
        }
        unsupportedOperator(operator, list);
    }

    protected void operatorException(Operator operator, List<COSBase> list, IOException iOException) throws IOException {
        if ((iOException instanceof MissingOperandException) || (iOException instanceof MissingResourceException) || (iOException instanceof MissingImageReaderException)) {
            Log.e("PdfBox-Android", iOException.getMessage());
        } else if (iOException instanceof EmptyGraphicsStackException) {
            Log.w("PdfBox-Android", iOException.getMessage());
        } else {
            if (operator.getName().equals("Do")) {
                Log.w("PdfBox-Android", iOException.getMessage());
                return;
            }
            throw iOException;
        }
    }

    public void saveGraphicsState() {
        Stack<PDGraphicsState> stack = this.graphicsStack;
        stack.push(stack.peek().m1573clone());
    }

    public void restoreGraphicsState() {
        this.graphicsStack.pop();
    }

    protected final Stack<PDGraphicsState> saveGraphicsStack() {
        Stack<PDGraphicsState> stack = this.graphicsStack;
        Stack<PDGraphicsState> stack2 = new Stack<>();
        this.graphicsStack = stack2;
        stack2.add(stack.peek().m1573clone());
        return stack;
    }

    protected final void restoreGraphicsStack(Stack<PDGraphicsState> stack) {
        this.graphicsStack = stack;
    }

    public int getGraphicsStackSize() {
        return this.graphicsStack.size();
    }

    public PDGraphicsState getGraphicsState() {
        return this.graphicsStack.peek();
    }

    public Matrix getTextLineMatrix() {
        return this.textLineMatrix;
    }

    public void setTextLineMatrix(Matrix matrix) {
        this.textLineMatrix = matrix;
    }

    public Matrix getTextMatrix() {
        return this.textMatrix;
    }

    public void setTextMatrix(Matrix matrix) {
        this.textMatrix = matrix;
    }

    public void setLineDashPattern(COSArray cOSArray, int i) {
        if (i < 0) {
            Log.w("PdfBox-Android", "Dash phase has negative value " + i + ", set to 0");
            i = 0;
        }
        getGraphicsState().setLineDashPattern(new PDLineDashPattern(cOSArray, i));
    }

    public PDResources getResources() {
        return this.resources;
    }

    public PDPage getCurrentPage() {
        return this.currentPage;
    }

    public Matrix getInitialMatrix() {
        return this.initialMatrix;
    }

    public PointF transformedPoint(float f, float f2) {
        float[] fArr = {f, f2};
        getGraphicsState().getCurrentTransformationMatrix().createAffineTransform().transform(fArr, 0, fArr, 0, 1);
        return new PointF(fArr[0], fArr[1]);
    }

    protected float transformWidth(float f) {
        Matrix currentTransformationMatrix = getGraphicsState().getCurrentTransformationMatrix();
        float scaleX = currentTransformationMatrix.getScaleX() + currentTransformationMatrix.getShearX();
        float scaleY = currentTransformationMatrix.getScaleY() + currentTransformationMatrix.getShearY();
        return f * ((float) Math.sqrt(((double) ((scaleX * scaleX) + (scaleY * scaleY))) * 0.5d));
    }
}
