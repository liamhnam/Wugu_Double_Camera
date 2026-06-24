package com.faceunity.core.entity;

import com.faceunity.core.utils.DecimalUtils;
import com.p020hp.jipp.model.PrinterServiceType;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;

@Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0010\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0013\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003¢\u0006\u0002\u0010\u0006J\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J'\u0010\u0012\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0001H\u0096\u0002J\b\u0010\u0016\u001a\u00020\u0017H\u0016J\u0006\u0010\u0018\u001a\u00020\u0019J\t\u0010\u001a\u001a\u00020\u001bHÖ\u0001R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001a\u0010\u0004\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\b\"\u0004\b\f\u0010\nR\u001a\u0010\u0005\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\b\"\u0004\b\u000e\u0010\n¨\u0006\u001c"}, m1293d2 = {"Lcom/faceunity/core/entity/FUCoordinate3DData;", "", "positionX", "", "positionY", "positionZ", "(DDD)V", "getPositionX", "()D", "setPositionX", "(D)V", "getPositionY", "setPositionY", "getPositionZ", "setPositionZ", "component1", "component2", "component3", PrinterServiceType.copy, "equals", "", "other", "hashCode", "", "toDataArray", "", "toString", "", "fu_core_all_featureRelease"}, m1294k = 1, m1295mv = {1, 1, 16})
public final class FUCoordinate3DData {
    private double positionX;
    private double positionY;
    private double positionZ;

    public static FUCoordinate3DData copy$default(FUCoordinate3DData fUCoordinate3DData, double d, double d2, double d3, int i, Object obj) {
        if ((i & 1) != 0) {
            d = fUCoordinate3DData.positionX;
        }
        double d4 = d;
        if ((i & 2) != 0) {
            d2 = fUCoordinate3DData.positionY;
        }
        double d5 = d2;
        if ((i & 4) != 0) {
            d3 = fUCoordinate3DData.positionZ;
        }
        return fUCoordinate3DData.copy(d4, d5, d3);
    }

    public final double getPositionX() {
        return this.positionX;
    }

    public final double getPositionY() {
        return this.positionY;
    }

    public final double getPositionZ() {
        return this.positionZ;
    }

    public final FUCoordinate3DData copy(double positionX, double positionY, double positionZ) {
        return new FUCoordinate3DData(positionX, positionY, positionZ);
    }

    public String toString() {
        return "FUCoordinate3DData(positionX=" + this.positionX + ", positionY=" + this.positionY + ", positionZ=" + this.positionZ + ")";
    }

    public FUCoordinate3DData(double d, double d2, double d3) {
        this.positionX = d;
        this.positionY = d2;
        this.positionZ = d3;
    }

    public final double getPositionX() {
        return this.positionX;
    }

    public final double getPositionY() {
        return this.positionY;
    }

    public final double getPositionZ() {
        return this.positionZ;
    }

    public final void setPositionX(double d) {
        this.positionX = d;
    }

    public final void setPositionY(double d) {
        this.positionY = d;
    }

    public final void setPositionZ(double d) {
        this.positionZ = d;
    }

    public final double[] toDataArray() {
        return new double[]{this.positionX, this.positionY, this.positionZ};
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!Intrinsics.areEqual(getClass(), other != null ? other.getClass() : null)) {
            return false;
        }
        if (other != null) {
            FUCoordinate3DData fUCoordinate3DData = (FUCoordinate3DData) other;
            return DecimalUtils.doubleEquals(fUCoordinate3DData.positionX, this.positionX) && DecimalUtils.doubleEquals(fUCoordinate3DData.positionY, this.positionY) && DecimalUtils.doubleEquals(fUCoordinate3DData.positionZ, this.positionZ);
        }
        throw new TypeCastException("null cannot be cast to non-null type com.faceunity.core.entity.FUCoordinate3DData");
    }

    public int hashCode() {
        return (((Double.hashCode(this.positionX) * 31) + Double.hashCode(this.positionY)) * 31) + Double.hashCode(this.positionZ);
    }
}
