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

@Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u001c\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u009f\u0001\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0018\b\u0002\u0010\u0006\u001a\u0012\u0012\u0004\u0012\u00020\u00050\u0007j\b\u0012\u0004\u0012\u00020\u0005`\b\u0012\u0018\b\u0002\u0010\t\u001a\u0012\u0012\u0004\u0012\u00020\n0\u0007j\b\u0012\u0004\u0012\u00020\n`\b\u0012\u0018\b\u0002\u0010\u000b\u001a\u0012\u0012\u0004\u0012\u00020\f0\u0007j\b\u0012\u0004\u0012\u00020\f`\b\u00120\b\u0002\u0010\r\u001a*\u0012\u0004\u0012\u00020\u000f\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00110\u00100\u000ej\u0014\u0012\u0004\u0012\u00020\u000f\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00110\u0010`\u0012\u0012\b\b\u0002\u0010\u0013\u001a\u00020\u0014¢\u0006\u0002\u0010\u0015J\t\u0010&\u001a\u00020\u0003HÆ\u0003J\t\u0010'\u001a\u00020\u0005HÆ\u0003J\u0019\u0010(\u001a\u0012\u0012\u0004\u0012\u00020\u00050\u0007j\b\u0012\u0004\u0012\u00020\u0005`\bHÆ\u0003J\u0019\u0010)\u001a\u0012\u0012\u0004\u0012\u00020\n0\u0007j\b\u0012\u0004\u0012\u00020\n`\bHÆ\u0003J\u0019\u0010*\u001a\u0012\u0012\u0004\u0012\u00020\f0\u0007j\b\u0012\u0004\u0012\u00020\f`\bHÆ\u0003J1\u0010+\u001a*\u0012\u0004\u0012\u00020\u000f\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00110\u00100\u000ej\u0014\u0012\u0004\u0012\u00020\u000f\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00110\u0010`\u0012HÆ\u0003J\t\u0010,\u001a\u00020\u0014HÆ\u0003J§\u0001\u0010-\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\u0018\b\u0002\u0010\u0006\u001a\u0012\u0012\u0004\u0012\u00020\u00050\u0007j\b\u0012\u0004\u0012\u00020\u0005`\b2\u0018\b\u0002\u0010\t\u001a\u0012\u0012\u0004\u0012\u00020\n0\u0007j\b\u0012\u0004\u0012\u00020\n`\b2\u0018\b\u0002\u0010\u000b\u001a\u0012\u0012\u0004\u0012\u00020\f0\u0007j\b\u0012\u0004\u0012\u00020\f`\b20\b\u0002\u0010\r\u001a*\u0012\u0004\u0012\u00020\u000f\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00110\u00100\u000ej\u0014\u0012\u0004\u0012\u00020\u000f\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00110\u0010`\u00122\b\b\u0002\u0010\u0013\u001a\u00020\u0014HÆ\u0001J\u0013\u0010.\u001a\u00020\u00142\b\u0010/\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u00100\u001a\u000201HÖ\u0001J\t\u00102\u001a\u00020\u000fHÖ\u0001R!\u0010\t\u001a\u0012\u0012\u0004\u0012\u00020\n0\u0007j\b\u0012\u0004\u0012\u00020\n`\b¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R!\u0010\u000b\u001a\u0012\u0012\u0004\u0012\u00020\f0\u0007j\b\u0012\u0004\u0012\u00020\f`\b¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0017R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u001a\u0010\u0013\u001a\u00020\u0014X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u001c\"\u0004\b\u001d\u0010\u001eR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010 R!\u0010\u0006\u001a\u0012\u0012\u0004\u0012\u00020\u00050\u0007j\b\u0012\u0004\u0012\u00020\u0005`\b¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\u0017RB\u0010\r\u001a*\u0012\u0004\u0012\u00020\u000f\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00110\u00100\u000ej\u0014\u0012\u0004\u0012\u00020\u000f\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00110\u0010`\u0012X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\"\u0010#\"\u0004\b$\u0010%¨\u00063"}, m1293d2 = {"Lcom/faceunity/core/avatar/control/FUASceneData;", "", "id", "", "controller", "Lcom/faceunity/core/entity/FUBundleData;", "itemBundles", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "animationData", "Lcom/faceunity/core/entity/FUAnimationData;", "avatars", "Lcom/faceunity/core/avatar/control/FUAAvatarData;", "params", "Ljava/util/LinkedHashMap;", "", "Lkotlin/Function0;", "", "Lkotlin/collections/LinkedHashMap;", "enable", "", "(JLcom/faceunity/core/entity/FUBundleData;Ljava/util/ArrayList;Ljava/util/ArrayList;Ljava/util/ArrayList;Ljava/util/LinkedHashMap;Z)V", "getAnimationData", "()Ljava/util/ArrayList;", "getAvatars", "getController", "()Lcom/faceunity/core/entity/FUBundleData;", "getEnable", "()Z", "setEnable", "(Z)V", "getId", "()J", "getItemBundles", "getParams", "()Ljava/util/LinkedHashMap;", "setParams", "(Ljava/util/LinkedHashMap;)V", "component1", "component2", "component3", "component4", "component5", "component6", "component7", PrinterServiceType.copy, "equals", "other", "hashCode", "", "toString", "fu_core_all_featureRelease"}, m1294k = 1, m1295mv = {1, 1, 16})
public final class FUASceneData {
    private final ArrayList<FUAnimationData> animationData;
    private final ArrayList<FUAAvatarData> avatars;
    private final FUBundleData controller;
    private boolean enable;
    private final long id;
    private final ArrayList<FUBundleData> itemBundles;
    private LinkedHashMap<String, Function0<Unit>> params;

    public final long getId() {
        return this.id;
    }

    public final FUBundleData getController() {
        return this.controller;
    }

    public final ArrayList<FUBundleData> component3() {
        return this.itemBundles;
    }

    public final ArrayList<FUAnimationData> component4() {
        return this.animationData;
    }

    public final ArrayList<FUAAvatarData> component5() {
        return this.avatars;
    }

    public final LinkedHashMap<String, Function0<Unit>> component6() {
        return this.params;
    }

    public final boolean getEnable() {
        return this.enable;
    }

    public final FUASceneData copy(long id, FUBundleData controller, ArrayList<FUBundleData> itemBundles, ArrayList<FUAnimationData> animationData, ArrayList<FUAAvatarData> avatars, LinkedHashMap<String, Function0<Unit>> params, boolean enable) {
        Intrinsics.checkParameterIsNotNull(controller, "controller");
        Intrinsics.checkParameterIsNotNull(itemBundles, "itemBundles");
        Intrinsics.checkParameterIsNotNull(animationData, "animationData");
        Intrinsics.checkParameterIsNotNull(avatars, "avatars");
        Intrinsics.checkParameterIsNotNull(params, "params");
        return new FUASceneData(id, controller, itemBundles, animationData, avatars, params, enable);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof FUASceneData)) {
            return false;
        }
        FUASceneData fUASceneData = (FUASceneData) other;
        return this.id == fUASceneData.id && Intrinsics.areEqual(this.controller, fUASceneData.controller) && Intrinsics.areEqual(this.itemBundles, fUASceneData.itemBundles) && Intrinsics.areEqual(this.animationData, fUASceneData.animationData) && Intrinsics.areEqual(this.avatars, fUASceneData.avatars) && Intrinsics.areEqual(this.params, fUASceneData.params) && this.enable == fUASceneData.enable;
    }

    public int hashCode() {
        int iHashCode = Long.hashCode(this.id) * 31;
        FUBundleData fUBundleData = this.controller;
        int iHashCode2 = (iHashCode + (fUBundleData != null ? fUBundleData.hashCode() : 0)) * 31;
        ArrayList<FUBundleData> arrayList = this.itemBundles;
        int iHashCode3 = (iHashCode2 + (arrayList != null ? arrayList.hashCode() : 0)) * 31;
        ArrayList<FUAnimationData> arrayList2 = this.animationData;
        int iHashCode4 = (iHashCode3 + (arrayList2 != null ? arrayList2.hashCode() : 0)) * 31;
        ArrayList<FUAAvatarData> arrayList3 = this.avatars;
        int iHashCode5 = (iHashCode4 + (arrayList3 != null ? arrayList3.hashCode() : 0)) * 31;
        LinkedHashMap<String, Function0<Unit>> linkedHashMap = this.params;
        int iHashCode6 = (iHashCode5 + (linkedHashMap != null ? linkedHashMap.hashCode() : 0)) * 31;
        boolean z = this.enable;
        ?? r1 = z;
        if (z) {
            r1 = 1;
        }
        return iHashCode6 + r1;
    }

    public String toString() {
        return "FUASceneData(id=" + this.id + ", controller=" + this.controller + ", itemBundles=" + this.itemBundles + ", animationData=" + this.animationData + ", avatars=" + this.avatars + ", params=" + this.params + ", enable=" + this.enable + ")";
    }

    public FUASceneData(long j, FUBundleData controller, ArrayList<FUBundleData> itemBundles, ArrayList<FUAnimationData> animationData, ArrayList<FUAAvatarData> avatars, LinkedHashMap<String, Function0<Unit>> params, boolean z) {
        Intrinsics.checkParameterIsNotNull(controller, "controller");
        Intrinsics.checkParameterIsNotNull(itemBundles, "itemBundles");
        Intrinsics.checkParameterIsNotNull(animationData, "animationData");
        Intrinsics.checkParameterIsNotNull(avatars, "avatars");
        Intrinsics.checkParameterIsNotNull(params, "params");
        this.id = j;
        this.controller = controller;
        this.itemBundles = itemBundles;
        this.animationData = animationData;
        this.avatars = avatars;
        this.params = params;
        this.enable = z;
    }

    public final long getId() {
        return this.id;
    }

    public final FUBundleData getController() {
        return this.controller;
    }

    public FUASceneData(long j, FUBundleData fUBundleData, ArrayList arrayList, ArrayList arrayList2, ArrayList arrayList3, LinkedHashMap linkedHashMap, boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(j, fUBundleData, (i & 4) != 0 ? new ArrayList() : arrayList, (i & 8) != 0 ? new ArrayList() : arrayList2, (i & 16) != 0 ? new ArrayList() : arrayList3, (i & 32) != 0 ? new LinkedHashMap() : linkedHashMap, (i & 64) != 0 ? true : z);
    }

    public final ArrayList<FUBundleData> getItemBundles() {
        return this.itemBundles;
    }

    public final ArrayList<FUAnimationData> getAnimationData() {
        return this.animationData;
    }

    public final ArrayList<FUAAvatarData> getAvatars() {
        return this.avatars;
    }

    public final LinkedHashMap<String, Function0<Unit>> getParams() {
        return this.params;
    }

    public final void setParams(LinkedHashMap<String, Function0<Unit>> linkedHashMap) {
        Intrinsics.checkParameterIsNotNull(linkedHashMap, "<set-?>");
        this.params = linkedHashMap;
    }

    public final boolean getEnable() {
        return this.enable;
    }

    public final void setEnable(boolean z) {
        this.enable = z;
    }
}
