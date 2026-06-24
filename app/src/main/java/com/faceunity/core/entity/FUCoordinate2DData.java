package com.faceunity.core.entity;

import com.faceunity.core.utils.DecimalUtils;
import com.p020hp.jipp.model.PrinterServiceType;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;

@Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0006\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0013\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001H\u0096\u0002J\b\u0010\u0012\u001a\u00020\u0013H\u0016J\u0006\u0010\u0014\u001a\u00020\u0015J\t\u0010\u0016\u001a\u00020\u0017HÖ\u0001R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u001a\u0010\u0004\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u0007\"\u0004\b\u000b\u0010\t¨\u0006\u0018"}, m1293d2 = {"Lcom/faceunity/core/entity/FUCoordinate2DData;", "", "positionX", "", "positionY", "(DD)V", "getPositionX", "()D", "setPositionX", "(D)V", "getPositionY", "setPositionY", "component1", "component2", PrinterServiceType.copy, "equals", "", "other", "hashCode", "", "toDataArray", "", "toString", "", "fu_core_all_featureRelease"}, m1294k = 1, m1295mv = {1, 1, 16})
public final class FUCoordinate2DData {
    private double positionX;
    private double positionY;

    public static FUCoordinate2DData copy$default(FUCoordinate2DData fUCoordinate2DData, double d, double d2, int i, Object obj) {
        if ((i & 1) != 0) {
            d = fUCoordinate2DData.positionX;
        }
        if ((i & 2) != 0) {
            d2 = fUCoordinate2DData.positionY;
        }
        return fUCoordinate2DData.copy(d, d2);
    }

    public final double getPositionX() {
        return this.positionX;
    }

    public final double getPositionY() {
        return this.positionY;
    }

    public final FUCoordinate2DData copy(double positionX, double positionY) {
        return new FUCoordinate2DData(positionX, positionY);
    }

    public String toString() {
        return "FUCoordinate2DData(positionX=" + this.positionX + ", positionY=" + this.positionY + ")";
    }

    public FUCoordinate2DData(double d, double d2) {
        this.positionX = d;
        this.positionY = d2;
    }

    public final double getPositionX() {
        return this.positionX;
    }

    public final double getPositionY() {
        return this.positionY;
    }

    public final void setPositionX(double d) {
        this.positionX = d;
    }

    public final void setPositionY(double d) {
        this.positionY = d;
    }

    public final double[] toDataArray() {
        return new double[]{this.positionX, this.positionY};
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!Intrinsics.areEqual(getClass(), other != null ? other.getClass() : null)) {
            return false;
        }
        if (other != null) {
            FUCoordinate2DData fUCoordinate2DData = (FUCoordinate2DData) other;
            return DecimalUtils.doubleEquals(fUCoordinate2DData.positionX, this.positionX) && DecimalUtils.doubleEquals(fUCoordinate2DData.positionY, this.positionY);
        }
        throw new TypeCastException("null cannot be cast to non-null type com.faceunity.core.entity.FUCoordinate2DData");
    }

    public int hashCode() {
        return (Double.hashCode(this.positionX) * 31) + Double.hashCode(this.positionY);
    }
}
