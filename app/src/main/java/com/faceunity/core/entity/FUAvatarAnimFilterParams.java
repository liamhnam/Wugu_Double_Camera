package com.faceunity.core.entity;

import com.faceunity.core.utils.DecimalUtils;
import com.p020hp.jipp.model.PrinterServiceType;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;

@Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0011\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005¢\u0006\u0002\u0010\u0007J\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0005HÆ\u0003J'\u0010\u0015\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u0016\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u0001H\u0096\u0002J\b\u0010\u0019\u001a\u00020\u0003H\u0016J\t\u0010\u001a\u001a\u00020\u001bHÖ\u0001R\u001a\u0010\u0006\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\t\"\u0004\b\u0011\u0010\u000b¨\u0006\u001c"}, m1293d2 = {"Lcom/faceunity/core/entity/FUAvatarAnimFilterParams;", "", "nBufferFrames", "", "pos", "", "angle", "(IFF)V", "getAngle", "()F", "setAngle", "(F)V", "getNBufferFrames", "()I", "setNBufferFrames", "(I)V", "getPos", "setPos", "component1", "component2", "component3", PrinterServiceType.copy, "equals", "", "other", "hashCode", "toString", "", "fu_core_all_featureRelease"}, m1294k = 1, m1295mv = {1, 1, 16})
public final class FUAvatarAnimFilterParams {
    private float angle;
    private int nBufferFrames;
    private float pos;

    public static FUAvatarAnimFilterParams copy$default(FUAvatarAnimFilterParams fUAvatarAnimFilterParams, int i, float f, float f2, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = fUAvatarAnimFilterParams.nBufferFrames;
        }
        if ((i2 & 2) != 0) {
            f = fUAvatarAnimFilterParams.pos;
        }
        if ((i2 & 4) != 0) {
            f2 = fUAvatarAnimFilterParams.angle;
        }
        return fUAvatarAnimFilterParams.copy(i, f, f2);
    }

    public final int getNBufferFrames() {
        return this.nBufferFrames;
    }

    public final float getPos() {
        return this.pos;
    }

    public final float getAngle() {
        return this.angle;
    }

    public final FUAvatarAnimFilterParams copy(int nBufferFrames, float pos, float angle) {
        return new FUAvatarAnimFilterParams(nBufferFrames, pos, angle);
    }

    public String toString() {
        return "FUAvatarAnimFilterParams(nBufferFrames=" + this.nBufferFrames + ", pos=" + this.pos + ", angle=" + this.angle + ")";
    }

    public FUAvatarAnimFilterParams(int i, float f, float f2) {
        this.nBufferFrames = i;
        this.pos = f;
        this.angle = f2;
    }

    public final float getAngle() {
        return this.angle;
    }

    public final int getNBufferFrames() {
        return this.nBufferFrames;
    }

    public final float getPos() {
        return this.pos;
    }

    public final void setAngle(float f) {
        this.angle = f;
    }

    public final void setNBufferFrames(int i) {
        this.nBufferFrames = i;
    }

    public final void setPos(float f) {
        this.pos = f;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!Intrinsics.areEqual(getClass(), other != null ? other.getClass() : null)) {
            return false;
        }
        if (other != null) {
            FUAvatarAnimFilterParams fUAvatarAnimFilterParams = (FUAvatarAnimFilterParams) other;
            return DecimalUtils.floatEquals((float) fUAvatarAnimFilterParams.nBufferFrames, (float) this.nBufferFrames) && DecimalUtils.floatEquals(fUAvatarAnimFilterParams.pos, this.pos) && DecimalUtils.floatEquals(fUAvatarAnimFilterParams.angle, this.angle);
        }
        throw new TypeCastException("null cannot be cast to non-null type com.faceunity.core.entity.FUAvatarAnimFilterParams");
    }

    public int hashCode() {
        return (((Integer.hashCode(this.nBufferFrames) * 31) + Float.hashCode(this.pos)) * 31) + Float.hashCode(this.angle);
    }
}
