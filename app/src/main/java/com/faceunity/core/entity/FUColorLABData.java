package com.faceunity.core.entity;

import com.faceunity.core.controller.hairBeauty.HairBeautyParam;
import com.p020hp.jipp.model.PrinterServiceType;
import java.util.LinkedHashMap;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;

@Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\f\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J'\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0003HÆ\u0001J2\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\"\u0010\u0013\u001a\u001e\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\u00010\u0014j\u000e\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\u0001`\u0015J\u0013\u0010\u0016\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u0001H\u0096\u0002J\b\u0010\u0019\u001a\u00020\u0003H\u0016J\t\u0010\u001a\u001a\u00020\u0012HÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\b¨\u0006\u001b"}, m1293d2 = {"Lcom/faceunity/core/entity/FUColorLABData;", "", HairBeautyParam.Col_L, "", HairBeautyParam.Col_A, "Col_B", "(III)V", "getCol_A", "()I", "getCol_B", "getCol_L", "component1", "component2", "component3", PrinterServiceType.copy, "coverLABParam", "", "key", "", "params", "Ljava/util/LinkedHashMap;", "Lkotlin/collections/LinkedHashMap;", "equals", "", "other", "hashCode", "toString", "fu_core_all_featureRelease"}, m1294k = 1, m1295mv = {1, 1, 16})
public final class FUColorLABData {
    private final int Col_A;
    private final int Col_B;
    private final int Col_L;

    public static FUColorLABData copy$default(FUColorLABData fUColorLABData, int i, int i2, int i3, int i4, Object obj) {
        if ((i4 & 1) != 0) {
            i = fUColorLABData.Col_L;
        }
        if ((i4 & 2) != 0) {
            i2 = fUColorLABData.Col_A;
        }
        if ((i4 & 4) != 0) {
            i3 = fUColorLABData.Col_B;
        }
        return fUColorLABData.copy(i, i2, i3);
    }

    public final int getCol_L() {
        return this.Col_L;
    }

    public final int getCol_A() {
        return this.Col_A;
    }

    public final int getCol_B() {
        return this.Col_B;
    }

    public final FUColorLABData copy(int Col_L, int Col_A, int Col_B) {
        return new FUColorLABData(Col_L, Col_A, Col_B);
    }

    public String toString() {
        return "FUColorLABData(Col_L=" + this.Col_L + ", Col_A=" + this.Col_A + ", Col_B=" + this.Col_B + ")";
    }

    public FUColorLABData(int i, int i2, int i3) {
        this.Col_L = i;
        this.Col_A = i2;
        this.Col_B = i3;
    }

    public final int getCol_A() {
        return this.Col_A;
    }

    public final int getCol_B() {
        return this.Col_B;
    }

    public final int getCol_L() {
        return this.Col_L;
    }

    public final void coverLABParam(String key, LinkedHashMap<String, Object> params) {
        Intrinsics.checkParameterIsNotNull(key, "key");
        Intrinsics.checkParameterIsNotNull(params, "params");
        LinkedHashMap<String, Object> linkedHashMap = params;
        linkedHashMap.put(key + "_L", Double.valueOf(((double) this.Col_L) / 100.0d));
        linkedHashMap.put(key + "_A", Double.valueOf(((double) (this.Col_A + 128)) / 255.0d));
        linkedHashMap.put(key + "_B", Double.valueOf(((double) (this.Col_B + 128)) / 255.0d));
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!Intrinsics.areEqual(getClass(), other != null ? other.getClass() : null)) {
            return false;
        }
        if (other != null) {
            FUColorLABData fUColorLABData = (FUColorLABData) other;
            return this.Col_L == fUColorLABData.Col_L && this.Col_A == fUColorLABData.Col_A && this.Col_B == fUColorLABData.Col_B;
        }
        throw new TypeCastException("null cannot be cast to non-null type com.faceunity.core.entity.FUColorLABData");
    }

    public int hashCode() {
        return (((this.Col_L * 31) + this.Col_A) * 31) + this.Col_B;
    }
}
