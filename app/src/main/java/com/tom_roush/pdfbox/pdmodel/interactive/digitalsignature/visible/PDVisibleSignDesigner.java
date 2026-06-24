package com.tom_roush.pdfbox.pdmodel.interactive.digitalsignature.visible;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import com.tom_roush.pdfbox.pdmodel.PDDocument;
import com.tom_roush.pdfbox.pdmodel.common.PDRectangle;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class PDVisibleSignDesigner {
    private byte[] AffineTransformParams;
    private byte[] formatterRectangleParams;
    private Bitmap image;
    private Float imageHeight;
    private float imageSizeInPercents;
    private Float imageWidth;
    private float pageHeight;
    private float pageWidth;
    private String signatureFieldName;
    private float xAxis;
    private float yAxis;

    public PDVisibleSignDesigner(String str, InputStream inputStream, int i) throws IOException {
        this(new FileInputStream(str), inputStream, i);
    }

    public PDVisibleSignDesigner(InputStream inputStream, InputStream inputStream2, int i) throws IOException {
        this.signatureFieldName = "sig";
        this.formatterRectangleParams = new byte[]{0, 0, 100, 50};
        this.AffineTransformParams = new byte[]{1, 0, 0, 1, 0, 0};
        readImageStream(inputStream2);
        PDDocument pDDocumentLoad = PDDocument.load(inputStream);
        calculatePageSize(pDDocumentLoad, i);
        pDDocumentLoad.close();
    }

    public PDVisibleSignDesigner(PDDocument pDDocument, InputStream inputStream, int i) throws IOException {
        this.signatureFieldName = "sig";
        this.formatterRectangleParams = new byte[]{0, 0, 100, 50};
        this.AffineTransformParams = new byte[]{1, 0, 0, 1, 0, 0};
        readImageStream(inputStream);
        calculatePageSize(pDDocument, i);
    }

    public PDVisibleSignDesigner(String str, Bitmap bitmap, int i) throws IOException {
        this(new FileInputStream(str), bitmap, i);
    }

    public PDVisibleSignDesigner(InputStream inputStream, Bitmap bitmap, int i) throws IOException {
        this.signatureFieldName = "sig";
        this.formatterRectangleParams = new byte[]{0, 0, 100, 50};
        this.AffineTransformParams = new byte[]{1, 0, 0, 1, 0, 0};
        setImage(bitmap);
        PDDocument pDDocumentLoad = PDDocument.load(inputStream);
        calculatePageSize(pDDocumentLoad, i);
        pDDocumentLoad.close();
    }

    public PDVisibleSignDesigner(PDDocument pDDocument, Bitmap bitmap, int i) {
        this.signatureFieldName = "sig";
        this.formatterRectangleParams = new byte[]{0, 0, 100, 50};
        this.AffineTransformParams = new byte[]{1, 0, 0, 1, 0, 0};
        setImage(bitmap);
        calculatePageSize(pDDocument, i);
    }

    private void calculatePageSize(PDDocument pDDocument, int i) {
        if (i < 1) {
            throw new IllegalArgumentException("First page of pdf is 1, not " + i);
        }
        PDRectangle mediaBox = pDDocument.getPage(i - 1).getMediaBox();
        pageHeight(mediaBox.getHeight());
        float width = mediaBox.getWidth();
        this.pageWidth = width + 0.0f;
        this.imageSizeInPercents = 100.0f - (0.0f / (width + 0.0f));
    }

    public PDVisibleSignDesigner signatureImage(String str) throws IOException {
        readImageStream(new FileInputStream(str));
        return this;
    }

    public PDVisibleSignDesigner zoom(float f) {
        this.imageHeight = Float.valueOf(this.imageHeight.floatValue() + ((this.imageHeight.floatValue() * f) / 100.0f));
        this.imageWidth = Float.valueOf(this.imageWidth.floatValue() + ((this.imageWidth.floatValue() * f) / 100.0f));
        return this;
    }

    public PDVisibleSignDesigner coordinates(float f, float f2) {
        xAxis(f);
        yAxis(f2);
        return this;
    }

    public float getxAxis() {
        return this.xAxis;
    }

    public PDVisibleSignDesigner xAxis(float f) {
        this.xAxis = f;
        return this;
    }

    public float getyAxis() {
        return this.yAxis;
    }

    public PDVisibleSignDesigner yAxis(float f) {
        this.yAxis = f;
        return this;
    }

    public float getWidth() {
        return this.imageWidth.floatValue();
    }

    public PDVisibleSignDesigner width(float f) {
        this.imageWidth = Float.valueOf(f);
        return this;
    }

    public float getHeight() {
        return this.imageHeight.floatValue();
    }

    public PDVisibleSignDesigner height(float f) {
        this.imageHeight = Float.valueOf(f);
        return this;
    }

    protected float getTemplateHeight() {
        return getPageHeight();
    }

    private PDVisibleSignDesigner pageHeight(float f) {
        this.pageHeight = f;
        return this;
    }

    public String getSignatureFieldName() {
        return this.signatureFieldName;
    }

    public PDVisibleSignDesigner signatureFieldName(String str) {
        this.signatureFieldName = str;
        return this;
    }

    public Bitmap getImage() {
        return this.image;
    }

    private void readImageStream(InputStream inputStream) throws IOException {
        setImage(BitmapFactory.decodeStream(inputStream));
    }

    private void setImage(Bitmap bitmap) {
        this.image = bitmap;
        this.imageHeight = Float.valueOf(bitmap.getHeight());
        this.imageWidth = Float.valueOf(bitmap.getWidth());
    }

    public byte[] getAffineTransformParams() {
        return this.AffineTransformParams;
    }

    public PDVisibleSignDesigner affineTransformParams(byte[] bArr) {
        this.AffineTransformParams = bArr;
        return this;
    }

    public byte[] getFormatterRectangleParams() {
        return this.formatterRectangleParams;
    }

    public PDVisibleSignDesigner formatterRectangleParams(byte[] bArr) {
        this.formatterRectangleParams = bArr;
        return this;
    }

    public float getPageWidth() {
        return this.pageWidth;
    }

    public PDVisibleSignDesigner pageWidth(float f) {
        this.pageWidth = f;
        return this;
    }

    public float getPageHeight() {
        return this.pageHeight;
    }

    public float getImageSizeInPercents() {
        return this.imageSizeInPercents;
    }

    public void imageSizeInPercents(float f) {
        this.imageSizeInPercents = f;
    }

    public String getSignatureText() {
        throw new UnsupportedOperationException("That method is not yet implemented");
    }

    public PDVisibleSignDesigner signatureText(String str) {
        throw new UnsupportedOperationException("That method is not yet implemented");
    }
}
