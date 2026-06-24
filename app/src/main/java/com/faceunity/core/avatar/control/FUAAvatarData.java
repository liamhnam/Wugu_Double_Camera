package com.faceunity.core.avatar.control;

import com.faceunity.core.entity.FUAnimationData;
import com.faceunity.core.entity.FUBundleData;
import com.p020hp.jipp.model.PrinterServiceType;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001Bs\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0018\b\u0002\u0010\u0004\u001a\u0012\u0012\u0004\u0012\u00020\u00060\u0005j\b\u0012\u0004\u0012\u00020\u0006`\u0007\u0012\u0018\b\u0002\u0010\b\u001a\u0012\u0012\u0004\u0012\u00020\t0\u0005j\b\u0012\u0004\u0012\u00020\t`\u0007\u00120\b\u0002\u0010\n\u001a*\u0012\u0004\u0012\u00020\f\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\r0\u000bj\u0014\u0012\u0004\u0012\u00020\f\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\r`\u000f¢\u0006\u0002\u0010\u0010J\t\u0010\u0018\u001a\u00020\u0003HÆ\u0003J\u0019\u0010\u0019\u001a\u0012\u0012\u0004\u0012\u00020\u00060\u0005j\b\u0012\u0004\u0012\u00020\u0006`\u0007HÆ\u0003J\u0019\u0010\u001a\u001a\u0012\u0012\u0004\u0012\u00020\t0\u0005j\b\u0012\u0004\u0012\u00020\t`\u0007HÆ\u0003J1\u0010\u001b\u001a*\u0012\u0004\u0012\u00020\f\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\r0\u000bj\u0014\u0012\u0004\u0012\u00020\f\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\r`\u000fHÆ\u0003Jy\u0010\u001c\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u0018\b\u0002\u0010\u0004\u001a\u0012\u0012\u0004\u0012\u00020\u00060\u0005j\b\u0012\u0004\u0012\u00020\u0006`\u00072\u0018\b\u0002\u0010\b\u001a\u0012\u0012\u0004\u0012\u00020\t0\u0005j\b\u0012\u0004\u0012\u00020\t`\u000720\b\u0002\u0010\n\u001a*\u0012\u0004\u0012\u00020\f\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\r0\u000bj\u0014\u0012\u0004\u0012\u00020\f\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\r`\u000fHÆ\u0001J\u0013\u0010\u001d\u001a\u00020\u001e2\b\u0010\u001f\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010 \u001a\u00020!HÖ\u0001J\t\u0010\"\u001a\u00020\fHÖ\u0001R!\u0010\b\u001a\u0012\u0012\u0004\u0012\u00020\t0\u0005j\b\u0012\u0004\u0012\u00020\t`\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R!\u0010\u0004\u001a\u0012\u0012\u0004\u0012\u00020\u00060\u0005j\b\u0012\u0004\u0012\u00020\u0006`\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0012R9\u0010\n\u001a*\u0012\u0004\u0012\u00020\f\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\r0\u000bj\u0014\u0012\u0004\u0012\u00020\f\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\r`\u000f¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017¨\u0006#"}, m1293d2 = {"Lcom/faceunity/core/avatar/control/FUAAvatarData;", "", "id", "", "itemBundles", "Ljava/util/ArrayList;", "Lcom/faceunity/core/entity/FUBundleData;", "Lkotlin/collections/ArrayList;", "animationData", "Lcom/faceunity/core/entity/FUAnimationData;", "param", "Ljava/util/LinkedHashMap;", "", "Lkotlin/Function0;", "", "Lkotlin/collections/LinkedHashMap;", "(JLjava/util/ArrayList;Ljava/util/ArrayList;Ljava/util/LinkedHashMap;)V", "getAnimationData", "()Ljava/util/ArrayList;", "getId", "()J", "getItemBundles", "getParam", "()Ljava/util/LinkedHashMap;", "component1", "component2", "component3", "component4", PrinterServiceType.copy, "equals", "", "other", "hashCode", "", "toString", "fu_core_all_featureRelease"}, m1294k = 1, m1295mv = {1, 1, 16})
public final class FUAAvatarData {
    private final ArrayList<FUAnimationData> animationData;
    private final long id;
    private final ArrayList<FUBundleData> itemBundles;
    private final LinkedHashMap<String, Function0<Unit>> param;

    public static FUAAvatarData copy$default(FUAAvatarData fUAAvatarData, long j, ArrayList arrayList, ArrayList arrayList2, LinkedHashMap linkedHashMap, int i, Object obj) {
        if ((i & 1) != 0) {
            j = fUAAvatarData.id;
        }
        long j2 = j;
        if ((i & 2) != 0) {
            arrayList = fUAAvatarData.itemBundles;
        }
        ArrayList arrayList3 = arrayList;
        if ((i & 4) != 0) {
            arrayList2 = fUAAvatarData.animationData;
        }
        ArrayList arrayList4 = arrayList2;
        if ((i & 8) != 0) {
            linkedHashMap = fUAAvatarData.param;
        }
        return fUAAvatarData.copy(j2, arrayList3, arrayList4, linkedHashMap);
    }

    public final long getId() {
        return this.id;
    }

    public final ArrayList<FUBundleData> component2() {
        return this.itemBundles;
    }

    public final ArrayList<FUAnimationData> component3() {
        return this.animationData;
    }

    public final LinkedHashMap<String, Function0<Unit>> component4() {
        return this.param;
    }

    public final FUAAvatarData copy(long id, ArrayList<FUBundleData> itemBundles, ArrayList<FUAnimationData> animationData, LinkedHashMap<String, Function0<Unit>> param) {
        Intrinsics.checkParameterIsNotNull(itemBundles, "itemBundles");
        Intrinsics.checkParameterIsNotNull(animationData, "animationData");
        Intrinsics.checkParameterIsNotNull(param, "param");
        return new FUAAvatarData(id, itemBundles, animationData, param);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof FUAAvatarData)) {
            return false;
        }
        FUAAvatarData fUAAvatarData = (FUAAvatarData) other;
        return this.id == fUAAvatarData.id && Intrinsics.areEqual(this.itemBundles, fUAAvatarData.itemBundles) && Intrinsics.areEqual(this.animationData, fUAAvatarData.animationData) && Intrinsics.areEqual(this.param, fUAAvatarData.param);
    }

    public int hashCode() {
        int iHashCode = Long.hashCode(this.id) * 31;
        ArrayList<FUBundleData> arrayList = this.itemBundles;
        int iHashCode2 = (iHashCode + (arrayList != null ? arrayList.hashCode() : 0)) * 31;
        ArrayList<FUAnimationData> arrayList2 = this.animationData;
        int iHashCode3 = (iHashCode2 + (arrayList2 != null ? arrayList2.hashCode() : 0)) * 31;
        LinkedHashMap<String, Function0<Unit>> linkedHashMap = this.param;
        return iHashCode3 + (linkedHashMap != null ? linkedHashMap.hashCode() : 0);
    }

    public String toString() {
        return "FUAAvatarData(id=" + this.id + ", itemBundles=" + this.itemBundles + ", animationData=" + this.animationData + ", param=" + this.param + ")";
    }

    public FUAAvatarData(long j, ArrayList<FUBundleData> itemBundles, ArrayList<FUAnimationData> animationData, LinkedHashMap<String, Function0<Unit>> param) {
        Intrinsics.checkParameterIsNotNull(itemBundles, "itemBundles");
        Intrinsics.checkParameterIsNotNull(animationData, "animationData");
        Intrinsics.checkParameterIsNotNull(param, "param");
        this.id = j;
        this.itemBundles = itemBundles;
        this.animationData = animationData;
        this.param = param;
    }

    public final long getId() {
        return this.id;
    }

    public FUAAvatarData(long j, ArrayList arrayList, ArrayList arrayList2, LinkedHashMap linkedHashMap, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(j, (i & 2) != 0 ? new ArrayList() : arrayList, (i & 4) != 0 ? new ArrayList() : arrayList2, (i & 8) != 0 ? new LinkedHashMap() : linkedHashMap);
    }

    public final ArrayList<FUBundleData> getItemBundles() {
        return this.itemBundles;
    }

    public final ArrayList<FUAnimationData> getAnimationData() {
        return this.animationData;
    }

    public final LinkedHashMap<String, Function0<Unit>> getParam() {
        return this.param;
    }
}
