package com.hiti.usb.printer;

import android.graphics.Bitmap;

public class PrintPara {
    public Bitmap bmp;
    public Bitmap[] cardBmp;
    public PhotoSettings pSettings;

    public static class CardSettings {
        private ColorSettings cattr;
        private short copies = 1;

        public CardSettings() {
            ColorSettings colorSettings = new ColorSettings();
            this.cattr = colorSettings;
            colorSettings.shBlue = (short) 0;
            colorSettings.shGreen = (short) 0;
            colorSettings.shRed = (short) 0;
            colorSettings.shSaturation = (short) 0;
            colorSettings.shContrast = (short) 0;
            colorSettings.shBrightness = (short) 0;
            colorSettings.shSharpness = (short) 50;
            colorSettings.shGamma = (short) 100;
        }
    }

    public static class ColorSettings {
        short shBlue;
        short shBrightness;
        short shContrast;
        short shGamma;
        short shGreen;
        short shRed;
        short shSaturation;
        short shSharpness;
    }

    public enum PaperSize {
        PAPER_SIZE_6X4_PHOTO(0),
        PAPER_SIZE_6X8_PHOTO(6),
        PAPER_SIZE_6X9_PHOTO(12),
        PAPER_SIZE_6X9_SPLIT_2UP(14),
        PAPER_SIZE_5X7_PHOTO(4),
        PAPER_SIZE_6X4_SPLIT_2UP(17),
        PAPER_SIZE_5X7_SPLIT_2UP(19);

        private short paperSize;

        PaperSize(int i) {
            this.paperSize = (short) i;
        }

        public short getValue() {
            return this.paperSize;
        }
    }

    public static class PhotoSettings {
        private short applyMatte;
        private short autoPowerOffseconds;
        private ColorSettings cattr;
        private short copies = 1;
        private byte format;
        private String m_strTablesRoot;
        private long objectId;
        private short paperSize;
        private short printMode;
        private short ribbonVendor;
        private long storageId;

        public PhotoSettings() {
            ColorSettings colorSettings = new ColorSettings();
            this.cattr = colorSettings;
            colorSettings.shBlue = (short) 0;
            colorSettings.shGreen = (short) 0;
            colorSettings.shRed = (short) 0;
            colorSettings.shSaturation = (short) 0;
            colorSettings.shContrast = (short) 0;
            colorSettings.shBrightness = (short) 0;
            colorSettings.shSharpness = (short) 50;
            colorSettings.shGamma = (short) 100;
        }
    }

    private PrintPara(long j, byte b, long j2) {
        this.bmp = null;
        this.cardBmp = null;
        PhotoSettings photoSettings = new PhotoSettings();
        this.pSettings = photoSettings;
        photoSettings.storageId = j;
        this.pSettings.format = b;
        this.pSettings.objectId = j2;
    }

    private PrintPara(Bitmap bitmap, int i, PaperSize paperSize) {
        this.cardBmp = null;
        this.bmp = bitmap;
        PhotoSettings photoSettings = new PhotoSettings();
        this.pSettings = photoSettings;
        photoSettings.copies = (short) i;
        this.pSettings.paperSize = paperSize.getValue();
    }

    private PrintPara(Bitmap bitmap, Bitmap bitmap2, Bitmap bitmap3, Bitmap bitmap4, Bitmap bitmap5, Bitmap bitmap6) {
        this.bmp = null;
        this.cardBmp = new Bitmap[]{bitmap, bitmap2, bitmap3, bitmap4, bitmap5, bitmap6};
        PhotoSettings photoSettings = new PhotoSettings();
        this.pSettings = photoSettings;
        photoSettings.copies = (short) 1;
    }

    private PrintPara(Bitmap bitmap, short s, short s2, short s3, PaperSize paperSize, String str) {
        this.cardBmp = null;
        this.bmp = bitmap;
        PhotoSettings photoSettings = new PhotoSettings();
        this.pSettings = photoSettings;
        photoSettings.copies = s;
        this.pSettings.applyMatte = s2;
        this.pSettings.printMode = s3;
        this.pSettings.paperSize = paperSize.getValue();
        this.pSettings.m_strTablesRoot = str;
    }

    private PrintPara(Bitmap bitmap, short s, short s2, short s3, PaperSize paperSize, short s4, String str) {
        this.cardBmp = null;
        this.bmp = bitmap;
        PhotoSettings photoSettings = new PhotoSettings();
        this.pSettings = photoSettings;
        photoSettings.copies = s;
        this.pSettings.applyMatte = s2;
        this.pSettings.printMode = s3;
        this.pSettings.paperSize = paperSize.getValue();
        this.pSettings.ribbonVendor = s4;
        this.pSettings.m_strTablesRoot = str;
    }

    public PrintPara(short s) {
        this.bmp = null;
        this.cardBmp = null;
        PhotoSettings photoSettings = new PhotoSettings();
        this.pSettings = photoSettings;
        photoSettings.autoPowerOffseconds = s;
    }

    public static PrintPara getGetObjectValue(long j, byte b, long j2) {
        return new PrintPara(j, b, j2);
    }

    public static PrintPara getPrintCardPara(Bitmap bitmap, Bitmap bitmap2, Bitmap bitmap3, Bitmap bitmap4, Bitmap bitmap5, Bitmap bitmap6) {
        return new PrintPara(bitmap, bitmap2, bitmap3, bitmap4, bitmap5, bitmap6);
    }

    public static PrintPara getPrintPhotoPara(Bitmap bitmap) {
        return new PrintPara(bitmap, 1, PaperSize.PAPER_SIZE_6X4_PHOTO);
    }

    public static PrintPara getPrintPhotoPara(Bitmap bitmap, short s, short s2, short s3, PaperSize paperSize, String str) {
        return new PrintPara(bitmap, s, s2, s3, paperSize, str);
    }

    public static PrintPara getPrintPhotoPara(Bitmap bitmap, short s, short s2, short s3, PaperSize paperSize, short s4, String str) {
        return new PrintPara(bitmap, s, s2, s3, paperSize, s4, str);
    }

    public static PrintPara getSetCommandPara(short s) {
        return new PrintPara(s);
    }

    public static boolean isValidCard(PrintPara printPara) {
        Bitmap[] bitmapArr = printPara.cardBmp;
        return (bitmapArr == null || (bitmapArr[0] == null && bitmapArr[1] == null && bitmapArr[3] == null && bitmapArr[4] == null && (printPara.pSettings == null || printPara.getCopies() <= 0))) ? false : true;
    }

    public static boolean isValidPhoto(PrintPara printPara) {
        return (printPara.bmp == null || printPara.pSettings == null || printPara.getCopies() <= 0) ? false : true;
    }

    public short getApplyMatte() {
        return this.pSettings.applyMatte;
    }

    public short getAutoPowerOffSeconds() {
        return this.pSettings.autoPowerOffseconds;
    }

    public short getBlue() {
        return this.pSettings.cattr.shBlue;
    }

    public short getBrightness() {
        return this.pSettings.cattr.shBrightness;
    }

    public short getContrast() {
        return this.pSettings.cattr.shContrast;
    }

    public short getCopies() {
        return this.pSettings.copies;
    }

    public byte getFormat() {
        return this.pSettings.format;
    }

    public short getGamma() {
        return this.pSettings.cattr.shGamma;
    }

    public short getGreen() {
        return this.pSettings.cattr.shGreen;
    }

    public long getObjectId() {
        return this.pSettings.objectId;
    }

    public short getPaperSize() {
        return this.pSettings.paperSize;
    }

    public short getPrintMode() {
        return this.pSettings.printMode;
    }

    public short getRed() {
        return this.pSettings.cattr.shRed;
    }

    public short getRibbonVendor() {
        return this.pSettings.ribbonVendor;
    }

    public short getSaturation() {
        return this.pSettings.cattr.shSaturation;
    }

    public short getSharpness() {
        return this.pSettings.cattr.shSharpness;
    }

    public long getStorageId() {
        return this.pSettings.storageId;
    }

    public String getTableRoot() {
        return this.pSettings.m_strTablesRoot;
    }
}
