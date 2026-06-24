package com.faceunity.core.controller.bgSegGreen;

import com.p020hp.jipp.model.PrinterServiceType;
import kotlin.Metadata;

@Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0006\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J'\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001J\t\u0010\u0014\u001a\u00020\u0015HÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\b¨\u0006\u0016"}, m1293d2 = {"Lcom/faceunity/core/controller/bgSegGreen/BgSegGreenRemark;", "", "zoom", "", "centerX", "centerY", "(DDD)V", "getCenterX", "()D", "getCenterY", "getZoom", "component1", "component2", "component3", PrinterServiceType.copy, "equals", "", "other", "hashCode", "", "toString", "", "fu_core_all_featureRelease"}, m1294k = 1, m1295mv = {1, 1, 16})
public final class BgSegGreenRemark {
    private final double centerX;
    private final double centerY;
    private final double zoom;

    public static BgSegGreenRemark copy$default(BgSegGreenRemark bgSegGreenRemark, double d, double d2, double d3, int i, Object obj) {
        if ((i & 1) != 0) {
            d = bgSegGreenRemark.zoom;
        }
        double d4 = d;
        if ((i & 2) != 0) {
            d2 = bgSegGreenRemark.centerX;
        }
        double d5 = d2;
        if ((i & 4) != 0) {
            d3 = bgSegGreenRemark.centerY;
        }
        return bgSegGreenRemark.copy(d4, d5, d3);
    }

    public final double getZoom() {
        return this.zoom;
    }

    public final double getCenterX() {
        return this.centerX;
    }

    public final double getCenterY() {
        return this.centerY;
    }

    public final BgSegGreenRemark copy(double zoom, double centerX, double centerY) {
        return new BgSegGreenRemark(zoom, centerX, centerY);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof BgSegGreenRemark)) {
            return false;
        }
        BgSegGreenRemark bgSegGreenRemark = (BgSegGreenRemark) other;
        return Double.compare(this.zoom, bgSegGreenRemark.zoom) == 0 && Double.compare(this.centerX, bgSegGreenRemark.centerX) == 0 && Double.compare(this.centerY, bgSegGreenRemark.centerY) == 0;
    }

    public int hashCode() {
        return (((Double.hashCode(this.zoom) * 31) + Double.hashCode(this.centerX)) * 31) + Double.hashCode(this.centerY);
    }

    public String toString() {
        return "BgSegGreenRemark(zoom=" + this.zoom + ", centerX=" + this.centerX + ", centerY=" + this.centerY + ")";
    }

    public BgSegGreenRemark(double d, double d2, double d3) {
        this.zoom = d;
        this.centerX = d2;
        this.centerY = d3;
    }

    public final double getCenterX() {
        return this.centerX;
    }

    public final double getCenterY() {
        return this.centerY;
    }

    public final double getZoom() {
        return this.zoom;
    }
}
