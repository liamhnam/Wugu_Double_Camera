package com.faceunity.core.entity;

import com.tom_roush.fontbox.ttf.NamingTable;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0002\b\u0016\u0018\u00002\u00020\u0001B\u0019\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\b\u0010\u000b\u001a\u00020\u0000H\u0016J\u0010\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u0000H\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u000f"}, m1293d2 = {"Lcom/faceunity/core/entity/FUAnimationData;", "", "animation", "Lcom/faceunity/core/entity/FUBundleData;", NamingTable.TAG, "", "(Lcom/faceunity/core/entity/FUBundleData;Ljava/lang/String;)V", "getAnimation", "()Lcom/faceunity/core/entity/FUBundleData;", "getName", "()Ljava/lang/String;", "clone", "isEqual", "", "data", "fu_core_all_featureRelease"}, m1294k = 1, m1295mv = {1, 1, 16})
public class FUAnimationData {
    private final FUBundleData animation;
    private final String name;

    public FUAnimationData(FUBundleData fUBundleData) {
        this(fUBundleData, null, 2, 0 == true ? 1 : 0);
    }

    public FUAnimationData(FUBundleData animation, String name) {
        Intrinsics.checkParameterIsNotNull(animation, "animation");
        Intrinsics.checkParameterIsNotNull(name, "name");
        this.animation = animation;
        this.name = name;
    }

    public FUAnimationData(FUBundleData fUBundleData, String str, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(fUBundleData, (i & 2) != 0 ? fUBundleData.getName() : str);
    }

    public final FUBundleData getAnimation() {
        return this.animation;
    }

    public final String getName() {
        return this.name;
    }

    public FUAnimationData clone() {
        return new FUAnimationData(this.animation.clone(), null, 2, 0 == true ? 1 : 0);
    }

    public boolean isEqual(FUAnimationData data) {
        Intrinsics.checkParameterIsNotNull(data, "data");
        return Intrinsics.areEqual(data.animation.getPath(), this.animation.getPath()) && Intrinsics.areEqual(this.name, data.name);
    }
}
