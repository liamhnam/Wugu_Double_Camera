package com.faceunity.core.entity;

import com.p020hp.jipp.model.PrinterServiceType;
import java.util.LinkedHashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0014\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001BW\b\u0007\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012$\b\u0002\u0010\u0004\u001a\u001e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00010\u0005j\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0001`\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\t\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u0001\u0012\b\b\u0002\u0010\u000b\u001a\u00020\f¢\u0006\u0002\u0010\rJ\u000b\u0010\u0018\u001a\u0004\u0018\u00010\u0003HÆ\u0003J%\u0010\u0019\u001a\u001e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00010\u0005j\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0001`\u0007HÆ\u0003J\t\u0010\u001a\u001a\u00020\tHÆ\u0003J\u000b\u0010\u001b\u001a\u0004\u0018\u00010\u0001HÆ\u0003J\t\u0010\u001c\u001a\u00020\fHÆ\u0003J[\u0010\u001d\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032$\b\u0002\u0010\u0004\u001a\u001e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00010\u0005j\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0001`\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u00012\b\b\u0002\u0010\u000b\u001a\u00020\fHÆ\u0001J\u0013\u0010\u001e\u001a\u00020\t2\b\u0010\u001f\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010 \u001a\u00020!HÖ\u0001J\t\u0010\"\u001a\u00020\u0006HÖ\u0001R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\u000b\u001a\u00020\f¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R-\u0010\u0004\u001a\u001e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00010\u0005j\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0001`\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0013\u0010\n\u001a\u0004\u0018\u00010\u0001¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017¨\u0006#"}, m1293d2 = {"Lcom/faceunity/core/entity/FUFeaturesData;", "", "bundle", "Lcom/faceunity/core/entity/FUBundleData;", "param", "Ljava/util/LinkedHashMap;", "", "Lkotlin/collections/LinkedHashMap;", "enable", "", "remark", "id", "", "(Lcom/faceunity/core/entity/FUBundleData;Ljava/util/LinkedHashMap;ZLjava/lang/Object;J)V", "getBundle", "()Lcom/faceunity/core/entity/FUBundleData;", "getEnable", "()Z", "getId", "()J", "getParam", "()Ljava/util/LinkedHashMap;", "getRemark", "()Ljava/lang/Object;", "component1", "component2", "component3", "component4", "component5", PrinterServiceType.copy, "equals", "other", "hashCode", "", "toString", "fu_core_all_featureRelease"}, m1294k = 1, m1295mv = {1, 1, 16})
public final class FUFeaturesData {
    private final FUBundleData bundle;
    private final boolean enable;
    private final long id;
    private final LinkedHashMap<String, Object> param;
    private final Object remark;

    public FUFeaturesData(FUBundleData fUBundleData) {
        this(fUBundleData, null, false, null, 0L, 30, null);
    }

    public FUFeaturesData(FUBundleData fUBundleData, LinkedHashMap<String, Object> linkedHashMap) {
        this(fUBundleData, linkedHashMap, false, null, 0L, 28, null);
    }

    public FUFeaturesData(FUBundleData fUBundleData, LinkedHashMap<String, Object> linkedHashMap, boolean z) {
        this(fUBundleData, linkedHashMap, z, null, 0L, 24, null);
    }

    public FUFeaturesData(FUBundleData fUBundleData, LinkedHashMap<String, Object> linkedHashMap, boolean z, Object obj) {
        this(fUBundleData, linkedHashMap, z, obj, 0L, 16, null);
    }

    public static FUFeaturesData copy$default(FUFeaturesData fUFeaturesData, FUBundleData fUBundleData, LinkedHashMap linkedHashMap, boolean z, Object obj, long j, int i, Object obj2) {
        if ((i & 1) != 0) {
            fUBundleData = fUFeaturesData.bundle;
        }
        if ((i & 2) != 0) {
            linkedHashMap = fUFeaturesData.param;
        }
        LinkedHashMap linkedHashMap2 = linkedHashMap;
        if ((i & 4) != 0) {
            z = fUFeaturesData.enable;
        }
        boolean z2 = z;
        if ((i & 8) != 0) {
            obj = fUFeaturesData.remark;
        }
        Object obj3 = obj;
        if ((i & 16) != 0) {
            j = fUFeaturesData.id;
        }
        return fUFeaturesData.copy(fUBundleData, linkedHashMap2, z2, obj3, j);
    }

    public final FUBundleData getBundle() {
        return this.bundle;
    }

    public final LinkedHashMap<String, Object> component2() {
        return this.param;
    }

    public final boolean getEnable() {
        return this.enable;
    }

    public final Object getRemark() {
        return this.remark;
    }

    public final long getId() {
        return this.id;
    }

    public final FUFeaturesData copy(FUBundleData bundle, LinkedHashMap<String, Object> param, boolean enable, Object remark, long id) {
        Intrinsics.checkParameterIsNotNull(param, "param");
        return new FUFeaturesData(bundle, param, enable, remark, id);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof FUFeaturesData)) {
            return false;
        }
        FUFeaturesData fUFeaturesData = (FUFeaturesData) other;
        return Intrinsics.areEqual(this.bundle, fUFeaturesData.bundle) && Intrinsics.areEqual(this.param, fUFeaturesData.param) && this.enable == fUFeaturesData.enable && Intrinsics.areEqual(this.remark, fUFeaturesData.remark) && this.id == fUFeaturesData.id;
    }

    public int hashCode() {
        FUBundleData fUBundleData = this.bundle;
        int iHashCode = (fUBundleData != null ? fUBundleData.hashCode() : 0) * 31;
        LinkedHashMap<String, Object> linkedHashMap = this.param;
        int iHashCode2 = (iHashCode + (linkedHashMap != null ? linkedHashMap.hashCode() : 0)) * 31;
        boolean z = this.enable;
        ?? r2 = z;
        if (z) {
            r2 = 1;
        }
        int i = (iHashCode2 + r2) * 31;
        Object obj = this.remark;
        return ((i + (obj != null ? obj.hashCode() : 0)) * 31) + Long.hashCode(this.id);
    }

    public String toString() {
        return "FUFeaturesData(bundle=" + this.bundle + ", param=" + this.param + ", enable=" + this.enable + ", remark=" + this.remark + ", id=" + this.id + ")";
    }

    public FUFeaturesData(FUBundleData fUBundleData, LinkedHashMap<String, Object> param, boolean z, Object obj, long j) {
        Intrinsics.checkParameterIsNotNull(param, "param");
        this.bundle = fUBundleData;
        this.param = param;
        this.enable = z;
        this.remark = obj;
        this.id = j;
    }

    public final FUBundleData getBundle() {
        return this.bundle;
    }

    public FUFeaturesData(FUBundleData fUBundleData, LinkedHashMap linkedHashMap, boolean z, Object obj, long j, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(fUBundleData, (i & 2) != 0 ? new LinkedHashMap() : linkedHashMap, (i & 4) != 0 ? true : z, (i & 8) != 0 ? null : obj, (i & 16) != 0 ? 0L : j);
    }

    public final LinkedHashMap<String, Object> getParam() {
        return this.param;
    }

    public final boolean getEnable() {
        return this.enable;
    }

    public final Object getRemark() {
        return this.remark;
    }

    public final long getId() {
        return this.id;
    }
}
