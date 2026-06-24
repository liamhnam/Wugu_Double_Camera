package com.faceunity.core.entity;

import com.faceunity.core.utils.DecimalUtils;
import com.p020hp.jipp.model.PrinterServiceType;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;

@Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0010\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0014\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003¢\u0006\u0002\u0010\u0006J\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J'\u0010\u0012\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0001H\u0096\u0002J\b\u0010\u0016\u001a\u00020\u0017H\u0016J\u0006\u0010\u0018\u001a\u00020\u0019J\t\u0010\u001a\u001a\u00020\u001bHÖ\u0001R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001a\u0010\u0004\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\b\"\u0004\b\f\u0010\nR\u001a\u0010\u0005\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\b\"\u0004\b\u000e\u0010\n¨\u0006\u001c"}, m1293d2 = {"Lcom/faceunity/core/entity/FUAvatarOffset;", "", "offsetX", "", "offsetY", "offsetZ", "(FFF)V", "getOffsetX", "()F", "setOffsetX", "(F)V", "getOffsetY", "setOffsetY", "getOffsetZ", "setOffsetZ", "component1", "component2", "component3", PrinterServiceType.copy, "equals", "", "other", "hashCode", "", "toDataArray", "", "toString", "", "fu_core_all_featureRelease"}, m1294k = 1, m1295mv = {1, 1, 16})
public final class FUAvatarOffset {
    private float offsetX;
    private float offsetY;
    private float offsetZ;

    public static FUAvatarOffset copy$default(FUAvatarOffset fUAvatarOffset, float f, float f2, float f3, int i, Object obj) {
        if ((i & 1) != 0) {
            f = fUAvatarOffset.offsetX;
        }
        if ((i & 2) != 0) {
            f2 = fUAvatarOffset.offsetY;
        }
        if ((i & 4) != 0) {
            f3 = fUAvatarOffset.offsetZ;
        }
        return fUAvatarOffset.copy(f, f2, f3);
    }

    public final float getOffsetX() {
        return this.offsetX;
    }

    public final float getOffsetY() {
        return this.offsetY;
    }

    public final float getOffsetZ() {
        return this.offsetZ;
    }

    public final FUAvatarOffset copy(float offsetX, float offsetY, float offsetZ) {
        return new FUAvatarOffset(offsetX, offsetY, offsetZ);
    }

    public String toString() {
        return "FUAvatarOffset(offsetX=" + this.offsetX + ", offsetY=" + this.offsetY + ", offsetZ=" + this.offsetZ + ")";
    }

    public FUAvatarOffset(float f, float f2, float f3) {
        this.offsetX = f;
        this.offsetY = f2;
        this.offsetZ = f3;
    }

    public final float getOffsetX() {
        return this.offsetX;
    }

    public final float getOffsetY() {
        return this.offsetY;
    }

    public final float getOffsetZ() {
        return this.offsetZ;
    }

    public final void setOffsetX(float f) {
        this.offsetX = f;
    }

    public final void setOffsetY(float f) {
        this.offsetY = f;
    }

    public final void setOffsetZ(float f) {
        this.offsetZ = f;
    }

    public final float[] toDataArray() {
        return new float[]{this.offsetX, this.offsetY, this.offsetZ};
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!Intrinsics.areEqual(getClass(), other != null ? other.getClass() : null)) {
            return false;
        }
        if (other != null) {
            FUAvatarOffset fUAvatarOffset = (FUAvatarOffset) other;
            return DecimalUtils.floatEquals(fUAvatarOffset.offsetX, this.offsetX) && DecimalUtils.floatEquals(fUAvatarOffset.offsetY, this.offsetY) && DecimalUtils.floatEquals(fUAvatarOffset.offsetZ, this.offsetZ);
        }
        throw new TypeCastException("null cannot be cast to non-null type com.faceunity.core.entity.FUAvatarOffset");
    }

    public int hashCode() {
        return (((Float.hashCode(this.offsetX) * 31) + Float.hashCode(this.offsetY)) * 31) + Float.hashCode(this.offsetZ);
    }
}
