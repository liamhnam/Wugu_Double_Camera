package com.faceunity.core.model.action;

import com.faceunity.core.controller.action.ActionRecognitionController;
import com.faceunity.core.entity.FUBundleData;
import com.faceunity.core.model.BaseSingleModel;
import com.faceunity.core.support.FURenderBridge;
import java.util.LinkedHashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J$\u0010\u0005\u001a\u001e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u0006j\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b`\tH\u0014J\b\u0010\n\u001a\u00020\u000bH\u0014¨\u0006\f"}, m1293d2 = {"Lcom/faceunity/core/model/action/ActionRecognition;", "Lcom/faceunity/core/model/BaseSingleModel;", "controlBundle", "Lcom/faceunity/core/entity/FUBundleData;", "(Lcom/faceunity/core/entity/FUBundleData;)V", "buildParams", "Ljava/util/LinkedHashMap;", "", "", "Lkotlin/collections/LinkedHashMap;", "getModelController", "Lcom/faceunity/core/controller/action/ActionRecognitionController;", "fu_core_all_featureRelease"}, m1294k = 1, m1295mv = {1, 1, 16})
public final class ActionRecognition extends BaseSingleModel {
    public ActionRecognition(FUBundleData controlBundle) {
        super(controlBundle);
        Intrinsics.checkParameterIsNotNull(controlBundle, "controlBundle");
    }

    @Override
    public ActionRecognitionController getMBgSegGreenController() {
        return FURenderBridge.INSTANCE.getInstance$fu_core_all_featureRelease().getMActionRecognitionController$fu_core_all_featureRelease();
    }

    @Override
    protected LinkedHashMap<String, Object> buildParams() {
        return new LinkedHashMap<>();
    }
}
