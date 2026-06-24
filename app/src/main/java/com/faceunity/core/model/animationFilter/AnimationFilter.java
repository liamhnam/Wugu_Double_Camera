package com.faceunity.core.model.animationFilter;

import com.faceunity.core.controller.animationFilter.AnimationFilterController;
import com.faceunity.core.entity.FUBundleData;
import com.faceunity.core.model.BaseSingleModel;
import com.faceunity.core.support.FURenderBridge;
import java.util.LinkedHashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0014\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u000f0\rH\u0014J\b\u0010\u0010\u001a\u00020\u0011H\u0014R$\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0006@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000b¨\u0006\u0012"}, m1293d2 = {"Lcom/faceunity/core/model/animationFilter/AnimationFilter;", "Lcom/faceunity/core/model/BaseSingleModel;", "controlBundle", "Lcom/faceunity/core/entity/FUBundleData;", "(Lcom/faceunity/core/entity/FUBundleData;)V", "value", "", "style", "getStyle", "()I", "setStyle", "(I)V", "buildParams", "Ljava/util/LinkedHashMap;", "", "", "getModelController", "Lcom/faceunity/core/controller/animationFilter/AnimationFilterController;", "fu_core_all_featureRelease"}, m1294k = 1, m1295mv = {1, 1, 16})
public final class AnimationFilter extends BaseSingleModel {
    private int style;

    public AnimationFilter(FUBundleData controlBundle) {
        super(controlBundle);
        Intrinsics.checkParameterIsNotNull(controlBundle, "controlBundle");
        this.style = -1;
    }

    @Override
    public AnimationFilterController getMBgSegGreenController() {
        return FURenderBridge.INSTANCE.getInstance$fu_core_all_featureRelease().getMAnimationFilterController$fu_core_all_featureRelease();
    }

    public final int getStyle() {
        return this.style;
    }

    public final void setStyle(int i) {
        this.style = i;
        updateAttributes("style", Integer.valueOf(i));
    }

    @Override
    protected LinkedHashMap<String, Object> buildParams() {
        LinkedHashMap<String, Object> linkedHashMap = new LinkedHashMap<>();
        linkedHashMap.put("style", Integer.valueOf(this.style));
        return linkedHashMap;
    }
}
