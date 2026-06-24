package com.faceunity.core.model.prop.bigHead;

import com.faceunity.core.entity.FUBundleData;
import com.faceunity.core.model.prop.Prop;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004¨\u0006\u0005"}, m1293d2 = {"Lcom/faceunity/core/model/prop/bigHead/BigHead;", "Lcom/faceunity/core/model/prop/Prop;", "controlBundle", "Lcom/faceunity/core/entity/FUBundleData;", "(Lcom/faceunity/core/entity/FUBundleData;)V", "fu_core_all_featureRelease"}, m1294k = 1, m1295mv = {1, 1, 16})
public final class BigHead extends Prop {
    public BigHead(FUBundleData controlBundle) {
        super(controlBundle);
        Intrinsics.checkParameterIsNotNull(controlBundle, "controlBundle");
    }
}
