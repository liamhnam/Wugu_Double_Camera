package com.epson.isv.eprinterdriver.Ctrl;

class EpsBandHeader {
    private int bandHeight;
    private byte bpType;
    private int imageHeight;
    private int imageWidth;

    public static final class BandPixelType {
        public static final byte BAND_PIXEL_ARGB8888 = 1;
        public static final byte BAND_PIXEL_RGB565 = 0;
    }

    public EpsBandHeader(int i, int i2, int i3, byte b) {
        this.imageWidth = i;
        this.imageHeight = i2;
        this.bandHeight = i3;
        this.bpType = b;
    }

    public int getImageWidth() {
        return this.imageWidth;
    }

    public int getImageHeight() {
        return this.imageHeight;
    }

    public int getBandHeight() {
        return this.bandHeight;
    }

    public byte getBpType() {
        return this.bpType;
    }
}
