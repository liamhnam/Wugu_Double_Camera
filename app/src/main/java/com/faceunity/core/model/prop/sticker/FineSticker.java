package com.faceunity.core.model.prop.sticker;

import com.faceunity.core.controller.prop.PropParam;
import com.faceunity.core.entity.FUBundleData;
import com.faceunity.core.model.prop.Prop;
import java.util.LinkedHashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\b\u0016\u0018\u00002\u00020\u0001B-\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0005¢\u0006\u0002\u0010\bJ\u0019\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020\u00140\u0012H\u0010¢\u0006\u0002\b\u0015J\u0006\u0010\u0016\u001a\u00020\u0017R$\u0010\u000b\u001a\u00020\n2\u0006\u0010\t\u001a\u00020\n@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0010R\u0011\u0010\u0007\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\u0010R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0004\u0010\u0010¨\u0006\u0018"}, m1293d2 = {"Lcom/faceunity/core/model/prop/sticker/FineSticker;", "Lcom/faceunity/core/model/prop/Prop;", "controlBundle", "Lcom/faceunity/core/entity/FUBundleData;", "isFlipPoints", "", "is3DFlipH", "isClick", "(Lcom/faceunity/core/entity/FUBundleData;ZZZ)V", "value", "", "forcePortrait", "getForcePortrait", "()I", "setForcePortrait", "(I)V", "()Z", "buildRemark", "Ljava/util/LinkedHashMap;", "", "", "buildRemark$fu_core_all_featureRelease", "onClick", "", "fu_core_all_featureRelease"}, m1294k = 1, m1295mv = {1, 1, 16})
public class FineSticker extends Prop {
    private int forcePortrait;
    private final boolean is3DFlipH;
    private final boolean isClick;
    private final boolean isFlipPoints;

    public FineSticker(FUBundleData fUBundleData) {
        this(fUBundleData, false, false, false, 14, null);
    }

    public FineSticker(FUBundleData fUBundleData, boolean z) {
        this(fUBundleData, z, false, false, 12, null);
    }

    public FineSticker(FUBundleData fUBundleData, boolean z, boolean z2) {
        this(fUBundleData, z, z2, false, 8, null);
    }

    public final boolean getIsFlipPoints() {
        return this.isFlipPoints;
    }

    public FineSticker(FUBundleData fUBundleData, boolean z, boolean z2, boolean z3, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(fUBundleData, (i & 2) != 0 ? false : z, (i & 4) != 0 ? false : z2, (i & 8) != 0 ? false : z3);
    }

    public final boolean getIs3DFlipH() {
        return this.is3DFlipH;
    }

    public final boolean getIsClick() {
        return this.isClick;
    }

    public FineSticker(FUBundleData controlBundle, boolean z, boolean z2, boolean z3) {
        super(controlBundle);
        Intrinsics.checkParameterIsNotNull(controlBundle, "controlBundle");
        this.isFlipPoints = z;
        this.is3DFlipH = z2;
        this.isClick = z3;
    }

    public final void onClick() {
        if (this.isClick) {
            updateAttributes(PropParam.MOUSE_DOWN, Double.valueOf(1.0d));
        }
    }

    public final int getForcePortrait() {
        return this.forcePortrait;
    }

    public final void setForcePortrait(int i) {
        this.forcePortrait = i;
        updateAttributes(PropParam.FORCE_PORTRAIT, Integer.valueOf(i));
    }

    @Override
    public LinkedHashMap<String, Object> buildRemark$fu_core_all_featureRelease() {
        LinkedHashMap<String, Object> linkedHashMapBuildRemark$fu_core_all_featureRelease = super.buildRemark$fu_core_all_featureRelease();
        if (this.isFlipPoints) {
            linkedHashMapBuildRemark$fu_core_all_featureRelease.put("is_flip_points", 1);
        }
        if (this.is3DFlipH) {
            linkedHashMapBuildRemark$fu_core_all_featureRelease.put("is3DFlipH", 1);
        }
        linkedHashMapBuildRemark$fu_core_all_featureRelease.put(PropParam.FORCE_PORTRAIT, Integer.valueOf(this.forcePortrait));
        return linkedHashMapBuildRemark$fu_core_all_featureRelease;
    }
}
