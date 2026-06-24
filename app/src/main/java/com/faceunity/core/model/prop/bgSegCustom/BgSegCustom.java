package com.faceunity.core.model.prop.bgSegCustom;

import com.arthenica.ffmpegkit.StreamInformation;
import com.faceunity.core.controller.prop.PropParam;
import com.faceunity.core.entity.FUBundleData;
import com.faceunity.core.model.prop.Prop;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u001e\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\nJ\u0006\u0010\f\u001a\u00020\u0006¨\u0006\r"}, m1293d2 = {"Lcom/faceunity/core/model/prop/bgSegCustom/BgSegCustom;", "Lcom/faceunity/core/model/prop/Prop;", "controlBundle", "Lcom/faceunity/core/entity/FUBundleData;", "(Lcom/faceunity/core/entity/FUBundleData;)V", "createBgSegment", "", "rgba", "", StreamInformation.KEY_WIDTH, "", StreamInformation.KEY_HEIGHT, "removeBgSegment", "fu_core_all_featureRelease"}, m1294k = 1, m1295mv = {1, 1, 16})
public final class BgSegCustom extends Prop {
    public BgSegCustom(FUBundleData controlBundle) {
        super(controlBundle);
        Intrinsics.checkParameterIsNotNull(controlBundle, "controlBundle");
    }

    public final void createBgSegment(byte[] rgba, int width, int height) {
        Intrinsics.checkParameterIsNotNull(rgba, "rgba");
        createTexForItem(PropParam.TAX_BG, rgba, width, height);
    }

    public final void removeBgSegment() {
        deleteTexForItem(PropParam.TAX_BG);
    }
}
