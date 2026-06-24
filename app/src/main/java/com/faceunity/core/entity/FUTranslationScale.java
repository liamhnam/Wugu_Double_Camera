package com.faceunity.core.entity;

import com.faceunity.core.utils.DecimalUtils;
import com.p020hp.jipp.model.PrinterServiceType;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;

@Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0010\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0014\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003¢\u0006\u0002\u0010\u0006J\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J'\u0010\u0012\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0001H\u0096\u0002J\b\u0010\u0016\u001a\u00020\u0017H\u0016J\u0006\u0010\u0018\u001a\u00020\u0019J\t\u0010\u001a\u001a\u00020\u001bHÖ\u0001R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001a\u0010\u0004\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\b\"\u0004\b\f\u0010\nR\u001a\u0010\u0005\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\b\"\u0004\b\u000e\u0010\n¨\u0006\u001c"}, m1293d2 = {"Lcom/faceunity/core/entity/FUTranslationScale;", "", "x", "", "y", "z", "(FFF)V", "getX", "()F", "setX", "(F)V", "getY", "setY", "getZ", "setZ", "component1", "component2", "component3", PrinterServiceType.copy, "equals", "", "other", "hashCode", "", "toDataArray", "", "toString", "", "fu_core_all_featureRelease"}, m1294k = 1, m1295mv = {1, 1, 16})
public final class FUTranslationScale {
    private float x;
    private float y;
    private float z;

    public static FUTranslationScale copy$default(FUTranslationScale fUTranslationScale, float f, float f2, float f3, int i, Object obj) {
        if ((i & 1) != 0) {
            f = fUTranslationScale.x;
        }
        if ((i & 2) != 0) {
            f2 = fUTranslationScale.y;
        }
        if ((i & 4) != 0) {
            f3 = fUTranslationScale.z;
        }
        return fUTranslationScale.copy(f, f2, f3);
    }

    public final float getX() {
        return this.x;
    }

    public final float getY() {
        return this.y;
    }

    public final float getZ() {
        return this.z;
    }

    public final FUTranslationScale copy(float x, float y, float z) {
        return new FUTranslationScale(x, y, z);
    }

    public String toString() {
        return "FUTranslationScale(x=" + this.x + ", y=" + this.y + ", z=" + this.z + ")";
    }

    public FUTranslationScale(float f, float f2, float f3) {
        this.x = f;
        this.y = f2;
        this.z = f3;
    }

    public final float getX() {
        return this.x;
    }

    public final float getY() {
        return this.y;
    }

    public final float getZ() {
        return this.z;
    }

    public final void setX(float f) {
        this.x = f;
    }

    public final void setY(float f) {
        this.y = f;
    }

    public final void setZ(float f) {
        this.z = f;
    }

    public final float[] toDataArray() {
        return new float[]{this.x, this.y, this.z};
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!Intrinsics.areEqual(getClass(), other != null ? other.getClass() : null)) {
            return false;
        }
        if (other != null) {
            FUTranslationScale fUTranslationScale = (FUTranslationScale) other;
            return DecimalUtils.floatEquals(fUTranslationScale.x, this.x) && DecimalUtils.floatEquals(fUTranslationScale.y, this.y) && DecimalUtils.floatEquals(fUTranslationScale.z, this.z);
        }
        throw new TypeCastException("null cannot be cast to non-null type com.faceunity.core.entity.FUTranslationScale");
    }

    public int hashCode() {
        return (((Float.hashCode(this.x) * 31) + Float.hashCode(this.y)) * 31) + Float.hashCode(this.z);
    }
}
