package com.faceunity.core.model.prop.animoji;

import com.faceunity.core.controller.prop.PropParam;
import com.faceunity.core.entity.FUBundleData;
import com.faceunity.core.model.prop.Prop;
import java.util.LinkedHashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J)\u0010\f\u001a\u001e\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u000f0\rj\u000e\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u000f`\u0010H\u0010¢\u0006\u0002\b\u0011R$\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0006@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000b¨\u0006\u0012"}, m1293d2 = {"Lcom/faceunity/core/model/prop/animoji/Animoji;", "Lcom/faceunity/core/model/prop/Prop;", "controlBundle", "Lcom/faceunity/core/entity/FUBundleData;", "(Lcom/faceunity/core/entity/FUBundleData;)V", "value", "", "enableFaceFollow", "getEnableFaceFollow", "()Z", "setEnableFaceFollow", "(Z)V", "buildParams", "Ljava/util/LinkedHashMap;", "", "", "Lkotlin/collections/LinkedHashMap;", "buildParams$fu_core_all_featureRelease", "fu_core_all_featureRelease"}, m1294k = 1, m1295mv = {1, 1, 16})
public final class Animoji extends Prop {
    private boolean enableFaceFollow;

    public Animoji(FUBundleData controlBundle) {
        super(controlBundle);
        Intrinsics.checkParameterIsNotNull(controlBundle, "controlBundle");
        this.enableFaceFollow = true;
    }

    public final boolean getEnableFaceFollow() {
        return this.enableFaceFollow;
    }

    public final void setEnableFaceFollow(boolean z) {
        this.enableFaceFollow = z;
        updateAttributesGL(PropParam.FACE_FOLLOW, Double.valueOf(z ? 1.0d : 0.0d));
        updateAttributesGL(PropParam.IS_FIX_X, Double.valueOf(z ? 0.0d : 1.0d));
        updateAttributesGL(PropParam.IS_FIX_Y, Double.valueOf(z ? 0.0d : 1.0d));
        updateAttributesGL(PropParam.IS_FIX_Z, Double.valueOf(z ? 0.0d : 1.0d));
        updateAttributesGL(PropParam.FIX_ROTATION, Double.valueOf(z ? 0.0d : 1.0d));
    }

    @Override
    public LinkedHashMap<String, Object> buildParams$fu_core_all_featureRelease() {
        LinkedHashMap<String, Object> linkedHashMap = new LinkedHashMap<>();
        linkedHashMap.put(PropParam.FACE_FOLLOW, Boolean.valueOf(this.enableFaceFollow));
        return linkedHashMap;
    }
}
