package com.faceunity.core.entity;

import com.p020hp.jipp.model.PrinterServiceType;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;

@Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0014\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001H\u0096\u0002J\b\u0010\u0012\u001a\u00020\u0013H\u0016J\t\u0010\u0014\u001a\u00020\u0015HÖ\u0001R\u001a\u0010\u0004\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u0007\"\u0004\b\u000b\u0010\t¨\u0006\u0016"}, m1293d2 = {"Lcom/faceunity/core/entity/FURenderFrameData;", "", "texMatrix", "", "mvpMatrix", "([F[F)V", "getMvpMatrix", "()[F", "setMvpMatrix", "([F)V", "getTexMatrix", "setTexMatrix", "component1", "component2", PrinterServiceType.copy, "equals", "", "other", "hashCode", "", "toString", "", "fu_core_all_featureRelease"}, m1294k = 1, m1295mv = {1, 1, 16})
public final class FURenderFrameData {
    private float[] mvpMatrix;
    private float[] texMatrix;

    public static FURenderFrameData copy$default(FURenderFrameData fURenderFrameData, float[] fArr, float[] fArr2, int i, Object obj) {
        if ((i & 1) != 0) {
            fArr = fURenderFrameData.texMatrix;
        }
        if ((i & 2) != 0) {
            fArr2 = fURenderFrameData.mvpMatrix;
        }
        return fURenderFrameData.copy(fArr, fArr2);
    }

    public final float[] getTexMatrix() {
        return this.texMatrix;
    }

    public final float[] getMvpMatrix() {
        return this.mvpMatrix;
    }

    public final FURenderFrameData copy(float[] texMatrix, float[] mvpMatrix) {
        Intrinsics.checkParameterIsNotNull(texMatrix, "texMatrix");
        Intrinsics.checkParameterIsNotNull(mvpMatrix, "mvpMatrix");
        return new FURenderFrameData(texMatrix, mvpMatrix);
    }

    public String toString() {
        return "FURenderFrameData(texMatrix=" + Arrays.toString(this.texMatrix) + ", mvpMatrix=" + Arrays.toString(this.mvpMatrix) + ")";
    }

    public FURenderFrameData(float[] texMatrix, float[] mvpMatrix) {
        Intrinsics.checkParameterIsNotNull(texMatrix, "texMatrix");
        Intrinsics.checkParameterIsNotNull(mvpMatrix, "mvpMatrix");
        this.texMatrix = texMatrix;
        this.mvpMatrix = mvpMatrix;
    }

    public final float[] getMvpMatrix() {
        return this.mvpMatrix;
    }

    public final float[] getTexMatrix() {
        return this.texMatrix;
    }

    public final void setMvpMatrix(float[] fArr) {
        Intrinsics.checkParameterIsNotNull(fArr, "<set-?>");
        this.mvpMatrix = fArr;
    }

    public final void setTexMatrix(float[] fArr) {
        Intrinsics.checkParameterIsNotNull(fArr, "<set-?>");
        this.texMatrix = fArr;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!Intrinsics.areEqual(getClass(), other != null ? other.getClass() : null)) {
            return false;
        }
        if (other != null) {
            FURenderFrameData fURenderFrameData = (FURenderFrameData) other;
            return Arrays.equals(this.texMatrix, fURenderFrameData.texMatrix) && Arrays.equals(this.mvpMatrix, fURenderFrameData.mvpMatrix);
        }
        throw new TypeCastException("null cannot be cast to non-null type com.faceunity.core.entity.FURenderFrameData");
    }

    public int hashCode() {
        return (Arrays.hashCode(this.texMatrix) * 31) + Arrays.hashCode(this.mvpMatrix);
    }
}
