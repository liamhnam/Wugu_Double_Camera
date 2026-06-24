package com.tom_roush.pdfbox.rendering;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import com.tom_roush.pdfbox.pdmodel.PDDocument;
import com.tom_roush.pdfbox.pdmodel.PDPage;
import com.tom_roush.pdfbox.pdmodel.common.PDRectangle;
import java.io.IOException;

public class PDFRenderer {
    protected final PDDocument document;

    public PDFRenderer(PDDocument pDDocument) {
        this.document = pDDocument;
    }

    public Bitmap renderImage(int i) throws IOException {
        return renderImage(i, 1.0f);
    }

    public Bitmap renderImage(int i, float f) throws IOException {
        return renderImage(i, f, ImageType.RGB);
    }

    public Bitmap renderImageWithDPI(int i, float f) throws IOException {
        return renderImage(i, f / 72.0f, ImageType.RGB);
    }

    public Bitmap renderImageWithDPI(int i, float f, ImageType imageType) throws IOException {
        return renderImage(i, f / 72.0f, imageType);
    }

    public Bitmap renderImage(int i, float f, ImageType imageType) throws IOException {
        Bitmap bitmapCreateBitmap;
        PDPage page = this.document.getPage(i);
        PDRectangle cropBox = page.getCropBox();
        float width = cropBox.getWidth();
        float height = cropBox.getHeight();
        int iRound = Math.round(width * f);
        int iRound2 = Math.round(height * f);
        int rotation = page.getRotation();
        if (rotation == 90 || rotation == 270) {
            bitmapCreateBitmap = Bitmap.createBitmap(iRound2, iRound, imageType.toBitmapConfig());
        } else {
            bitmapCreateBitmap = Bitmap.createBitmap(iRound, iRound2, imageType.toBitmapConfig());
        }
        Paint paint = new Paint();
        Canvas canvas = new Canvas(bitmapCreateBitmap);
        if (imageType == ImageType.ARGB) {
            paint.setColor(0);
        } else {
            paint.setColor(-1);
        }
        paint.setStyle(Paint.Style.FILL);
        canvas.drawRect(0.0f, 0.0f, bitmapCreateBitmap.getWidth(), bitmapCreateBitmap.getHeight(), paint);
        paint.reset();
        renderPage(page, paint, canvas, bitmapCreateBitmap.getWidth(), bitmapCreateBitmap.getHeight(), f, f);
        return bitmapCreateBitmap;
    }

    public void renderPageToGraphics(int i, Paint paint, Canvas canvas, float f) throws IOException {
        PDPage page = this.document.getPage(i);
        PDRectangle cropBox = page.getCropBox();
        renderPage(page, paint, canvas, (int) cropBox.getWidth(), (int) cropBox.getHeight(), f, f);
    }

    private void renderPage(PDPage pDPage, Paint paint, Canvas canvas, int i, int i2, float f, float f2) throws IOException {
        float height;
        canvas.scale(f, f2);
        PDRectangle cropBox = pDPage.getCropBox();
        int rotation = pDPage.getRotation();
        if (rotation != 0) {
            float height2 = 0.0f;
            if (rotation == 90) {
                height2 = cropBox.getHeight();
                height = 0.0f;
            } else if (rotation != 180) {
                height = rotation != 270 ? 0.0f : cropBox.getWidth();
            } else {
                height2 = cropBox.getWidth();
                height = cropBox.getHeight();
            }
            canvas.translate(height2, height);
            canvas.rotate((float) Math.toRadians(rotation));
        }
        createPageDrawer(new PageDrawerParameters(this, pDPage)).drawPage(paint, canvas, cropBox);
    }

    protected PageDrawer createPageDrawer(PageDrawerParameters pageDrawerParameters) throws IOException {
        return new PageDrawer(pageDrawerParameters);
    }
}
