package com.epson.isv.eprinterdriver.Ctrl;

import android.graphics.Rect;

class DrawInfo {
    private int printableHeight;
    private int printableWidth;
    private short totalPages = 0;
    private short curPage = 0;
    private Rect drawBandRect = new Rect();

    public DrawInfo(int i, int i2) {
        this.printableWidth = i;
        this.printableHeight = i2;
    }

    public void setTotalPages(short s) {
        this.totalPages = s;
    }

    public void setCurPage(short s) {
        this.curPage = s;
    }

    public void setDrawBandRect(Rect rect) {
        this.drawBandRect.set(rect);
    }

    public Rect getDrawBandRect() {
        return this.drawBandRect;
    }

    public short getTotalPages() {
        return this.totalPages;
    }

    public short getCurPage() {
        return this.curPage;
    }

    public int getPrintableHeight() {
        return this.printableHeight;
    }

    public int getPrintableWidth() {
        return this.printableWidth;
    }
}
