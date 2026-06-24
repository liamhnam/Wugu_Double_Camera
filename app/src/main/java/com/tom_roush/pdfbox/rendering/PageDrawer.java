package com.tom_roush.pdfbox.rendering;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.RectF;
import android.graphics.Region;
import android.util.Log;
import androidx.core.view.ViewCompat;
import com.tom_roush.harmony.awt.geom.AffineTransform;
import com.tom_roush.pdfbox.contentstream.PDFGraphicsStreamEngine;
import com.tom_roush.pdfbox.cos.COSArray;
import com.tom_roush.pdfbox.cos.COSBase;
import com.tom_roush.pdfbox.cos.COSDictionary;
import com.tom_roush.pdfbox.cos.COSName;
import com.tom_roush.pdfbox.cos.COSNumber;
import com.tom_roush.pdfbox.pdmodel.common.PDRectangle;
import com.tom_roush.pdfbox.pdmodel.common.function.PDFunction;
import com.tom_roush.pdfbox.pdmodel.font.PDCIDFontType0;
import com.tom_roush.pdfbox.pdmodel.font.PDCIDFontType2;
import com.tom_roush.pdfbox.pdmodel.font.PDFont;
import com.tom_roush.pdfbox.pdmodel.font.PDTrueTypeFont;
import com.tom_roush.pdfbox.pdmodel.font.PDType0Font;
import com.tom_roush.pdfbox.pdmodel.font.PDType1CFont;
import com.tom_roush.pdfbox.pdmodel.font.PDType1Font;
import com.tom_roush.pdfbox.pdmodel.graphics.PDLineDashPattern;
import com.tom_roush.pdfbox.pdmodel.graphics.color.PDColor;
import com.tom_roush.pdfbox.pdmodel.graphics.color.PDDeviceGray;
import com.tom_roush.pdfbox.pdmodel.graphics.form.PDTransparencyGroup;
import com.tom_roush.pdfbox.pdmodel.graphics.image.PDImage;
import com.tom_roush.pdfbox.pdmodel.graphics.state.PDGraphicsState;
import com.tom_roush.pdfbox.pdmodel.graphics.state.RenderingMode;
import com.tom_roush.pdfbox.pdmodel.interactive.annotation.PDAnnotation;
import com.tom_roush.pdfbox.pdmodel.interactive.annotation.PDAnnotationLink;
import com.tom_roush.pdfbox.pdmodel.interactive.annotation.PDAnnotationMarkup;
import com.tom_roush.pdfbox.pdmodel.interactive.annotation.PDBorderStyleDictionary;
import com.tom_roush.pdfbox.util.Matrix;
import com.tom_roush.pdfbox.util.Vector;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class PageDrawer extends PDFGraphicsStreamEngine {
    Canvas canvas;
    private Path.FillType clipWindingRule;
    private PointF currentPoint;
    private final Map<PDFont, Glyph2D> fontGlyph2D;
    private Region lastClip;
    private Path linePath;
    private PDRectangle pageSize;
    Paint paint;
    private final PDFRenderer renderer;
    private Region textClippingArea;
    private AffineTransform xform;

    public PageDrawer(PageDrawerParameters pageDrawerParameters) throws IOException {
        super(pageDrawerParameters.getPage());
        this.clipWindingRule = null;
        this.linePath = new Path();
        this.fontGlyph2D = new HashMap();
        this.currentPoint = new PointF();
        this.renderer = pageDrawerParameters.getRenderer();
    }

    public final PDFRenderer getRenderer() {
        return this.renderer;
    }

    protected final Canvas getCanvas() {
        return this.canvas;
    }

    protected final Path getLinePath() {
        return this.linePath;
    }

    private void setRenderingHints() {
        this.paint.setAntiAlias(true);
    }

    public void drawPage(Paint paint, Canvas canvas, PDRectangle pDRectangle) throws IOException {
        this.paint = paint;
        this.canvas = canvas;
        this.xform = new AffineTransform(this.canvas.getMatrix());
        this.pageSize = pDRectangle;
        setRenderingHints();
        this.canvas.translate(0.0f, pDRectangle.getHeight());
        this.canvas.scale(1.0f, -1.0f);
        this.paint.setStrokeCap(Paint.Cap.BUTT);
        this.paint.setStrokeJoin(Paint.Join.MITER);
        this.paint.setStrokeWidth(1.0f);
        this.canvas.translate(-pDRectangle.getLowerLeftX(), -pDRectangle.getLowerLeftY());
        this.canvas.save();
        processPage(getPage());
        Iterator<PDAnnotation> it = getPage().getAnnotations().iterator();
        while (it.hasNext()) {
            showAnnotation(it.next());
        }
    }

    private int getColor(PDColor pDColor) throws IOException {
        float[] rgb = pDColor.getColorSpace().toRGB(pDColor.getComponents());
        return Color.rgb(Math.round(rgb[0] * 255.0f), Math.round(rgb[1] * 255.0f), Math.round(rgb[2] * 255.0f));
    }

    private void setClip() {
        Region currentClippingPath = getGraphicsState().getCurrentClippingPath();
        if (currentClippingPath != this.lastClip) {
            this.canvas.clipPath(currentClippingPath.getBoundaryPath());
            this.lastClip = currentClippingPath;
        }
    }

    @Override
    public void beginText() throws IOException {
        setClip();
        beginTextClip();
    }

    @Override
    public void endText() throws IOException {
        endTextClip();
    }

    private void beginTextClip() {
        this.textClippingArea = new Region();
    }

    private void endTextClip() {
        PDGraphicsState graphicsState = getGraphicsState();
        if (!graphicsState.getTextState().getRenderingMode().isClip() || this.textClippingArea.isEmpty()) {
            return;
        }
        graphicsState.intersectClippingPath(this.textClippingArea);
        this.textClippingArea = null;
    }

    @Override
    protected void showFontGlyph(Matrix matrix, PDFont pDFont, int i, String str, Vector vector) throws IOException {
        AffineTransform affineTransformCreateAffineTransform = matrix.createAffineTransform();
        affineTransformCreateAffineTransform.concatenate(pDFont.getFontMatrix().createAffineTransform());
        drawGlyph2D(createGlyph2D(pDFont), pDFont, i, vector, affineTransformCreateAffineTransform);
    }

    private void drawGlyph2D(Glyph2D glyph2D, PDFont pDFont, int i, Vector vector, AffineTransform affineTransform) throws IOException {
        RenderingMode renderingMode = getGraphicsState().getTextState().getRenderingMode();
        Path pathForCharacterCode = glyph2D.getPathForCharacterCode(i);
        if (pathForCharacterCode != null) {
            if (!pDFont.isEmbedded()) {
                if (pDFont.getWidthFromFont(i) > 0.0f && Math.abs(r8 - (vector.getX() * 1000.0f)) > 1.0E-4d) {
                    affineTransform.scale((vector.getX() * 1000.0f) / r8, 1.0d);
                }
            }
            pathForCharacterCode.transform(affineTransform.toMatrix());
            if (renderingMode.isFill()) {
                this.paint.setColor(getNonStrokingColor());
                setClip();
                this.paint.setStyle(Paint.Style.FILL);
                this.canvas.drawPath(pathForCharacterCode, this.paint);
            }
            if (renderingMode.isStroke()) {
                this.paint.setColor(getStrokingColor());
                setClip();
                this.paint.setStyle(Paint.Style.STROKE);
                this.canvas.drawPath(pathForCharacterCode, this.paint);
            }
            renderingMode.isClip();
        }
    }

    private Glyph2D createGlyph2D(PDFont pDFont) throws IOException {
        Glyph2D type1Glyph2D;
        Glyph2D cIDType0Glyph2D = this.fontGlyph2D.get(pDFont);
        if (cIDType0Glyph2D != null) {
            return cIDType0Glyph2D;
        }
        if (pDFont instanceof PDTrueTypeFont) {
            type1Glyph2D = new TTFGlyph2D((PDTrueTypeFont) pDFont);
        } else if (pDFont instanceof PDType1Font) {
            type1Glyph2D = new Type1Glyph2D((PDType1Font) pDFont);
        } else if (pDFont instanceof PDType1CFont) {
            type1Glyph2D = new Type1Glyph2D((PDType1CFont) pDFont);
        } else if (pDFont instanceof PDType0Font) {
            PDType0Font pDType0Font = (PDType0Font) pDFont;
            if (pDType0Font.getDescendantFont() instanceof PDCIDFontType2) {
                cIDType0Glyph2D = new TTFGlyph2D(pDType0Font);
            } else if (pDType0Font.getDescendantFont() instanceof PDCIDFontType0) {
                cIDType0Glyph2D = new CIDType0Glyph2D((PDCIDFontType0) pDType0Font.getDescendantFont());
            }
            type1Glyph2D = cIDType0Glyph2D;
        } else {
            throw new IllegalStateException("Bad font type: " + pDFont.getClass().getSimpleName());
        }
        if (type1Glyph2D != null) {
            this.fontGlyph2D.put(pDFont, type1Glyph2D);
        }
        if (type1Glyph2D != null) {
            return type1Glyph2D;
        }
        throw new UnsupportedOperationException("No font for " + pDFont.getName());
    }

    @Override
    public void appendRectangle(PointF pointF, PointF pointF2, PointF pointF3, PointF pointF4) {
        this.linePath.moveTo(pointF.x, pointF.y);
        this.linePath.lineTo(pointF2.x, pointF2.y);
        this.linePath.lineTo(pointF3.x, pointF3.y);
        this.linePath.lineTo(pointF4.x, pointF4.y);
        this.linePath.close();
    }

    private int getStrokingColor() throws IOException {
        return getColor(getGraphicsState().getStrokingColor());
    }

    private int getNonStrokingColor() throws IOException {
        return getColor(getGraphicsState().getNonStrokingColor());
    }

    private void setStroke() {
        PDGraphicsState graphicsState = getGraphicsState();
        float fTransformWidth = transformWidth(graphicsState.getLineWidth());
        if (fTransformWidth < 0.25d) {
            fTransformWidth = 0.25f;
        }
        PDLineDashPattern lineDashPattern = graphicsState.getLineDashPattern();
        int phase = lineDashPattern.getPhase();
        float[] dashArray = lineDashPattern.getDashArray();
        if (dashArray != null) {
            for (int i = 0; i < dashArray.length; i++) {
                float fTransformWidth2 = transformWidth(dashArray[i]);
                if (fTransformWidth2 != 0.0f) {
                    dashArray[i] = Math.max(fTransformWidth2, 0.035f);
                }
            }
            phase = (int) transformWidth(phase);
            if (dashArray.length == 0) {
                dashArray = null;
            }
        }
        this.paint.setStrokeWidth(fTransformWidth);
        this.paint.setStrokeCap(graphicsState.getLineCap());
        this.paint.setStrokeJoin(graphicsState.getLineJoin());
        if (dashArray != null) {
            this.paint.setPathEffect(new DashPathEffect(dashArray, phase));
        }
    }

    @Override
    public void strokePath() throws IOException {
        setStroke();
        setClip();
        this.paint.setARGB(255, 0, 0, 0);
        this.paint.setStyle(Paint.Style.STROKE);
        this.paint.setColor(getStrokingColor());
        setClip();
        this.canvas.drawPath(this.linePath, this.paint);
        this.linePath.reset();
    }

    @Override
    public void fillPath(Path.FillType fillType) throws IOException {
        this.paint.setColor(getNonStrokingColor());
        setClip();
        this.linePath.setFillType(fillType);
        this.linePath.computeBounds(new RectF(), true);
        this.paint.setStyle(Paint.Style.FILL);
        this.canvas.drawPath(this.linePath, this.paint);
        this.linePath.reset();
    }

    private boolean isRectangular(Path path) {
        return path.isRect(null);
    }

    @Override
    public void fillAndStrokePath(Path.FillType fillType) throws IOException {
        Path path = new Path(this.linePath);
        fillPath(fillType);
        this.linePath = path;
        strokePath();
    }

    @Override
    public void clip(Path.FillType fillType) {
        this.clipWindingRule = fillType;
    }

    @Override
    public void moveTo(float f, float f2) {
        this.currentPoint.x = f;
        this.currentPoint.y = f2;
        this.linePath.moveTo(f, f2);
    }

    @Override
    public void lineTo(float f, float f2) {
        this.currentPoint.x = f;
        this.currentPoint.y = f2;
        this.linePath.lineTo(f, f2);
    }

    @Override
    public void curveTo(float f, float f2, float f3, float f4, float f5, float f6) {
        this.currentPoint.x = f5;
        this.currentPoint.y = f6;
        this.linePath.cubicTo(f, f2, f3, f4, f5, f6);
    }

    @Override
    public PointF getCurrentPoint() {
        return this.currentPoint;
    }

    @Override
    public void closePath() {
        this.linePath.close();
    }

    @Override
    public void endPath() {
        this.linePath.reset();
    }

    @Override
    public void drawImage(PDImage pDImage) throws IOException {
        AffineTransform affineTransformCreateAffineTransform = getGraphicsState().getCurrentTransformationMatrix().createAffineTransform();
        if (!pDImage.getInterpolate()) {
            if (!(((long) pDImage.getWidth()) < Math.round(affineTransformCreateAffineTransform.getScaleX()) || ((long) pDImage.getHeight()) < Math.round(affineTransformCreateAffineTransform.getScaleY()))) {
                pDImage.isStencil();
            }
        }
        if (!pDImage.isStencil()) {
            drawBitmap(pDImage.getImage(), affineTransformCreateAffineTransform);
        }
        if (pDImage.getInterpolate()) {
            return;
        }
        setRenderingHints();
    }

    private void drawBitmap(Bitmap bitmap, AffineTransform affineTransform) throws IOException {
        setClip();
        if (getGraphicsState().getSoftMask() != null) {
            AffineTransform affineTransform2 = new AffineTransform(affineTransform);
            affineTransform2.scale(1.0d, -1.0d);
            affineTransform2.translate(0.0d, -1.0d);
            new RectF(0.0f, 0.0f, 1.0f, 1.0f);
            return;
        }
        COSBase transfer = getGraphicsState().getTransfer();
        if ((transfer instanceof COSArray) || (transfer instanceof COSDictionary)) {
            bitmap = applyTransferFunction(bitmap, transfer);
        }
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        AffineTransform affineTransform3 = new AffineTransform(affineTransform);
        affineTransform3.scale(1.0f / width, (-1.0f) / height);
        affineTransform3.translate(0.0d, -height);
        this.canvas.drawBitmap(bitmap, affineTransform3.toMatrix(), this.paint);
    }

    private Bitmap applyTransferFunction(Bitmap bitmap, COSBase cOSBase) throws IOException {
        PDFunction pDFunctionCreate;
        Integer[] numArr;
        PDFunction pDFunctionCreate2;
        PDFunction pDFunctionCreate3;
        Integer[] numArr2;
        Integer[] numArr3;
        int iIntValue;
        int iIntValue2;
        int iIntValue3;
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        if (cOSBase instanceof COSArray) {
            COSArray cOSArray = (COSArray) cOSBase;
            pDFunctionCreate = PDFunction.create(cOSArray.getObject(0));
            pDFunctionCreate3 = PDFunction.create(cOSArray.getObject(1));
            pDFunctionCreate2 = PDFunction.create(cOSArray.getObject(2));
            numArr = new Integer[256];
            numArr3 = new Integer[256];
            numArr2 = new Integer[256];
        } else {
            pDFunctionCreate = PDFunction.create(cOSBase);
            numArr = new Integer[256];
            pDFunctionCreate2 = pDFunctionCreate;
            pDFunctionCreate3 = pDFunctionCreate2;
            numArr2 = numArr;
            numArr3 = numArr2;
        }
        float[] fArr = new float[1];
        int[] iArr = new int[bitmap.getWidth() * bitmap.getHeight()];
        bitmap.getPixels(iArr, 0, bitmap.getWidth(), 0, 0, bitmap.getWidth(), bitmap.getHeight());
        for (int i = 0; i < bitmap.getWidth() * bitmap.getHeight(); i++) {
            int i2 = iArr[i];
            int i3 = (i2 >> 16) & 255;
            int i4 = (i2 >> 8) & 255;
            int i5 = i2 & 255;
            Integer num = numArr[i3];
            if (num != null) {
                iIntValue = num.intValue();
            } else {
                fArr[0] = (i3 & 255) / 255.0f;
                int i6 = (int) (pDFunctionCreate.eval(fArr)[0] * 255.0f);
                numArr[i3] = Integer.valueOf(i6);
                iIntValue = i6;
            }
            Integer num2 = numArr3[i4];
            if (num2 != null) {
                iIntValue2 = num2.intValue();
            } else {
                fArr[0] = (i4 & 255) / 255.0f;
                int i7 = (int) (pDFunctionCreate3.eval(fArr)[0] * 255.0f);
                numArr3[i4] = Integer.valueOf(i7);
                iIntValue2 = i7;
            }
            Integer num3 = numArr2[i5];
            if (num3 != null) {
                iIntValue3 = num3.intValue();
            } else {
                fArr[0] = (i5 & 255) / 255.0f;
                int i8 = (int) (pDFunctionCreate2.eval(fArr)[0] * 255.0f);
                numArr2[i5] = Integer.valueOf(i8);
                iIntValue3 = i8;
            }
            iArr[i] = (i2 & ViewCompat.MEASURED_STATE_MASK) | (iIntValue << 16) | (iIntValue2 << 8) | iIntValue3;
        }
        bitmapCreateBitmap.setPixels(iArr, 0, bitmap.getWidth(), 0, 0, bitmap.getWidth(), bitmap.getHeight());
        return bitmapCreateBitmap;
    }

    @Override
    public void shadingFill(COSName cOSName) throws IOException {
        getResources().getShading(cOSName);
        getGraphicsState().getCurrentTransformationMatrix();
    }

    @Override
    public void showAnnotation(PDAnnotation pDAnnotation) throws IOException {
        this.lastClip = null;
        if (pDAnnotation.isNoView() || pDAnnotation.isHidden()) {
            return;
        }
        super.showAnnotation(pDAnnotation);
        if (pDAnnotation.getAppearance() == null) {
            if (pDAnnotation instanceof PDAnnotationLink) {
                drawAnnotationLinkBorder((PDAnnotationLink) pDAnnotation);
            }
            if ((pDAnnotation instanceof PDAnnotationMarkup) && pDAnnotation.getSubtype().equals("Ink")) {
                drawAnnotationInk((PDAnnotationMarkup) pDAnnotation);
            }
        }
    }

    private static class AnnotationBorder {
        private PDColor color;
        private float[] dashArray;
        private boolean underline;
        private float width;

        private AnnotationBorder() {
            this.dashArray = null;
            this.underline = false;
            this.width = 0.0f;
        }
    }

    private AnnotationBorder getAnnotationBorder(PDAnnotation pDAnnotation, PDBorderStyleDictionary pDBorderStyleDictionary) {
        AnnotationBorder annotationBorder = new AnnotationBorder();
        COSArray border = pDAnnotation.getBorder();
        boolean z = true;
        if (pDBorderStyleDictionary != null) {
            annotationBorder.width = pDBorderStyleDictionary.getWidth();
            if (pDBorderStyleDictionary.getStyle().equals("D")) {
                annotationBorder.dashArray = pDBorderStyleDictionary.getDashStyle().getDashArray();
            }
            if (pDBorderStyleDictionary.getStyle().equals(PDBorderStyleDictionary.STYLE_UNDERLINE)) {
                annotationBorder.underline = true;
            }
        } else {
            if (border.get(2) instanceof COSNumber) {
                annotationBorder.width = ((COSNumber) border.getObject(2)).floatValue();
            }
            if (border.size() > 3) {
                COSBase object = border.getObject(3);
                if (object instanceof COSArray) {
                    annotationBorder.dashArray = ((COSArray) object).toFloatArray();
                }
            }
        }
        annotationBorder.color = pDAnnotation.getColor();
        if (annotationBorder.color == null) {
            annotationBorder.color = new PDColor(new float[]{0.0f}, PDDeviceGray.INSTANCE);
        }
        if (annotationBorder.dashArray != null) {
            float[] fArr = annotationBorder.dashArray;
            int length = fArr.length;
            int i = 0;
            while (true) {
                if (i >= length) {
                    break;
                }
                if (fArr[i] != 0.0f) {
                    z = false;
                    break;
                }
                i++;
            }
            if (z) {
                annotationBorder.dashArray = null;
            }
        }
        return annotationBorder;
    }

    private void drawAnnotationLinkBorder(PDAnnotationLink pDAnnotationLink) throws IOException {
        Log.e("PdfBox-Android", "Hey! We drew an annotation link border!");
        AnnotationBorder annotationBorder = getAnnotationBorder(pDAnnotationLink, pDAnnotationLink.getBorderStyle());
        if (annotationBorder.width == 0.0f) {
            return;
        }
        PDRectangle rectangle = pDAnnotationLink.getRectangle();
        Paint paint = new Paint(this.paint);
        paint.setColor(getColor(annotationBorder.color));
        setStroke(paint, annotationBorder.width, Paint.Cap.BUTT, Paint.Join.MITER, 10.0f, annotationBorder.dashArray, 0.0f);
        this.canvas.restore();
        if (annotationBorder.underline) {
            this.canvas.drawLine(rectangle.getLowerLeftX(), rectangle.getLowerLeftY(), rectangle.getWidth() + rectangle.getLowerLeftX(), rectangle.getLowerLeftY(), paint);
            return;
        }
        this.canvas.drawRect(rectangle.getLowerLeftX(), rectangle.getLowerLeftY(), rectangle.getWidth(), rectangle.getHeight(), paint);
    }

    private void drawAnnotationInk(PDAnnotationMarkup pDAnnotationMarkup) throws IOException {
        Log.e("PdfBox-Android", "Hey! We drew an annotation ink!");
        if (pDAnnotationMarkup.getCOSObject().containsKey(COSName.INKLIST)) {
            COSBase dictionaryObject = pDAnnotationMarkup.getCOSObject().getDictionaryObject(COSName.INKLIST);
            if (dictionaryObject instanceof COSArray) {
                AnnotationBorder annotationBorder = getAnnotationBorder(pDAnnotationMarkup, pDAnnotationMarkup.getBorderStyle());
                if (annotationBorder.width == 0.0f) {
                    return;
                }
                Paint paint = new Paint(this.paint);
                paint.setColor(getColor(annotationBorder.color));
                setStroke(paint, annotationBorder.width, Paint.Cap.BUTT, Paint.Join.MITER, 10.0f, annotationBorder.dashArray, 0.0f);
                this.canvas.restore();
                Iterator<T> it = ((COSArray) dictionaryObject).toList().iterator();
                while (it.hasNext()) {
                    COSBase cOSBase = (COSBase) it.next();
                    if (cOSBase instanceof COSArray) {
                        COSArray cOSArray = (COSArray) cOSBase;
                        int size = cOSArray.size() / 2;
                        Path path = new Path();
                        for (int i = 0; i < size; i++) {
                            int i2 = i * 2;
                            COSBase object = cOSArray.getObject(i2);
                            COSBase object2 = cOSArray.getObject(i2 + 1);
                            if ((object instanceof COSNumber) && (object2 instanceof COSNumber)) {
                                float fFloatValue = ((COSNumber) object).floatValue();
                                float fFloatValue2 = ((COSNumber) object2).floatValue();
                                if (i == 0) {
                                    path.moveTo(fFloatValue, fFloatValue2);
                                } else {
                                    path.lineTo(fFloatValue, fFloatValue2);
                                }
                            }
                        }
                        this.canvas.drawPath(path, paint);
                    }
                }
            }
        }
    }

    public void setStroke(Paint paint, float f, Paint.Cap cap, Paint.Join join, float f2, float[] fArr, float f3) {
        paint.setStrokeWidth(f);
        paint.setStrokeCap(cap);
        paint.setStrokeJoin(join);
        paint.setStrokeMiter(f2);
        paint.setPathEffect(new DashPathEffect(fArr, f3));
    }

    @Override
    public void showTransparencyGroup(PDTransparencyGroup pDTransparencyGroup) throws IOException {
        new TransparencyGroup(pDTransparencyGroup, false);
        setClip();
        getGraphicsState().getSoftMask();
    }

    private final class TransparencyGroup {
        private TransparencyGroup(PDTransparencyGroup pDTransparencyGroup, boolean z) throws IOException {
            pDTransparencyGroup.getBBox().transform(Matrix.concatenate(PageDrawer.this.getGraphicsState().getCurrentTransformationMatrix(), pDTransparencyGroup.getMatrix()));
            if (z) {
                PageDrawer.this.processSoftMask(pDTransparencyGroup);
            } else {
                PageDrawer.this.processTransparencyGroup(pDTransparencyGroup);
            }
        }
    }
}
