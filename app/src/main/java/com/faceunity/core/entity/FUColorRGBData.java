package com.faceunity.core.entity;

import com.faceunity.core.utils.DecimalUtils;
import com.p020hp.jipp.model.MediaColor;
import com.p020hp.jipp.model.PrinterServiceType;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0010\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0013\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B)\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0003¢\u0006\u0002\u0010\u0007J\u0006\u0010\r\u001a\u00020\u0000J\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J1\u0010\u0012\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0001H\u0096\u0002J\b\u0010\u0016\u001a\u00020\u0017H\u0016J\u0006\u0010\u0018\u001a\u00020\u0019J\u0006\u0010\u001a\u001a\u00020\u0019J\t\u0010\u001b\u001a\u00020\u001cHÖ\u0001R\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\tR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\t¨\u0006\u001d"}, m1293d2 = {"Lcom/faceunity/core/entity/FUColorRGBData;", "", MediaColor.red, "", MediaColor.green, MediaColor.blue, "alpha", "(DDDD)V", "getAlpha", "()D", "getBlue", "getGreen", "getRed", "clone", "component1", "component2", "component3", "component4", PrinterServiceType.copy, "equals", "", "other", "hashCode", "", "toColorArray", "", "toScaleColorArray", "toString", "", "fu_core_all_featureRelease"}, m1294k = 1, m1295mv = {1, 1, 16})
public final class FUColorRGBData {
    private final double alpha;
    private final double blue;
    private final double green;
    private final double red;

    public FUColorRGBData(double d, double d2, double d3) {
        this(d, d2, d3, 0.0d, 8, null);
    }

    public final double getRed() {
        return this.red;
    }

    public final double getGreen() {
        return this.green;
    }

    public final double getBlue() {
        return this.blue;
    }

    public final double getAlpha() {
        return this.alpha;
    }

    public final FUColorRGBData copy(double red, double green, double blue, double alpha) {
        return new FUColorRGBData(red, green, blue, alpha);
    }

    public String toString() {
        return "FUColorRGBData(red=" + this.red + ", green=" + this.green + ", blue=" + this.blue + ", alpha=" + this.alpha + ")";
    }

    public FUColorRGBData(double d, double d2, double d3, double d4) {
        this.red = d;
        this.green = d2;
        this.blue = d3;
        this.alpha = d4;
    }

    public FUColorRGBData(double d, double d2, double d3, double d4, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(d, d2, d3, (i & 8) != 0 ? -1.0d : d4);
    }

    public final double getAlpha() {
        return this.alpha;
    }

    public final double getBlue() {
        return this.blue;
    }

    public final double getGreen() {
        return this.green;
    }

    public final double getRed() {
        return this.red;
    }

    public final double[] toColorArray() {
        double d = this.alpha;
        return d < ((double) 0) ? new double[]{this.red, this.green, this.blue} : new double[]{this.red, this.green, this.blue, d};
    }

    public final double[] toScaleColorArray() {
        double d = this.alpha;
        if (d < 0) {
            double d2 = 255;
            return new double[]{this.red / d2, this.green / d2, this.blue / d2};
        }
        double d3 = 255;
        return new double[]{this.red / d3, this.green / d3, this.blue / d3, d / d3};
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!Intrinsics.areEqual(getClass(), other != null ? other.getClass() : null)) {
            return false;
        }
        if (other != null) {
            FUColorRGBData fUColorRGBData = (FUColorRGBData) other;
            return DecimalUtils.doubleEquals(fUColorRGBData.alpha, this.alpha) && DecimalUtils.doubleEquals(fUColorRGBData.blue, this.blue) && DecimalUtils.doubleEquals(fUColorRGBData.green, this.green) && DecimalUtils.doubleEquals(fUColorRGBData.red, this.red);
        }
        throw new TypeCastException("null cannot be cast to non-null type com.faceunity.core.entity.FUColorRGBData");
    }

    public int hashCode() {
        return (((((Double.hashCode(this.red) * 31) + Double.hashCode(this.green)) * 31) + Double.hashCode(this.blue)) * 31) + Double.hashCode(this.alpha);
    }

    public final FUColorRGBData clone() {
        return new FUColorRGBData(this.red, this.green, this.blue, this.alpha);
    }
}
