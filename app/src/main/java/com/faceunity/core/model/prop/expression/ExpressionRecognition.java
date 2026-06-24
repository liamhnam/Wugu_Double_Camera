package com.faceunity.core.model.prop.expression;

import com.faceunity.core.controller.prop.PropParam;
import com.faceunity.core.entity.FUBundleData;
import com.faceunity.core.enumeration.FUAITypeEnum;
import com.faceunity.core.model.prop.Prop;
import java.util.LinkedHashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J)\u0010\u000f\u001a\u001e\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u00120\u0010j\u000e\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u0012`\u0013H\u0010¢\u0006\u0002\b\u0014R(\u0010\u0007\u001a\u0004\u0018\u00010\u00062\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR(\u0010\f\u001a\u0004\u0018\u00010\u00062\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\t\"\u0004\b\u000e\u0010\u000b¨\u0006\u0015"}, m1293d2 = {"Lcom/faceunity/core/model/prop/expression/ExpressionRecognition;", "Lcom/faceunity/core/model/prop/Prop;", "controlBundle", "Lcom/faceunity/core/entity/FUBundleData;", "(Lcom/faceunity/core/entity/FUBundleData;)V", "value", "Lcom/faceunity/core/enumeration/FUAITypeEnum;", "aiType", "getAiType", "()Lcom/faceunity/core/enumeration/FUAITypeEnum;", "setAiType", "(Lcom/faceunity/core/enumeration/FUAITypeEnum;)V", "landmarksType", "getLandmarksType", "setLandmarksType", "buildParams", "Ljava/util/LinkedHashMap;", "", "", "Lkotlin/collections/LinkedHashMap;", "buildParams$fu_core_all_featureRelease", "fu_core_all_featureRelease"}, m1294k = 1, m1295mv = {1, 1, 16})
public final class ExpressionRecognition extends Prop {
    private FUAITypeEnum aiType;
    private FUAITypeEnum landmarksType;

    public ExpressionRecognition(FUBundleData controlBundle) {
        super(controlBundle);
        Intrinsics.checkParameterIsNotNull(controlBundle, "controlBundle");
    }

    public final FUAITypeEnum getAiType() {
        return this.aiType;
    }

    public final void setAiType(FUAITypeEnum fUAITypeEnum) {
        this.aiType = fUAITypeEnum;
        if (fUAITypeEnum != null) {
            updateAttributes(PropParam.KEY_AI_TYPE, Integer.valueOf(fUAITypeEnum.getType()));
        }
    }

    public final FUAITypeEnum getLandmarksType() {
        return this.landmarksType;
    }

    public final void setLandmarksType(FUAITypeEnum fUAITypeEnum) {
        this.landmarksType = fUAITypeEnum;
        if (fUAITypeEnum != null) {
            updateAttributes(PropParam.KEY_LANDMARKS_TYPE, Integer.valueOf(fUAITypeEnum.getType()));
        }
    }

    @Override
    public LinkedHashMap<String, Object> buildParams$fu_core_all_featureRelease() {
        LinkedHashMap<String, Object> linkedHashMap = new LinkedHashMap<>();
        FUAITypeEnum fUAITypeEnum = this.aiType;
        if (fUAITypeEnum != null) {
            linkedHashMap.put(PropParam.KEY_AI_TYPE, Integer.valueOf(fUAITypeEnum.getType()));
        }
        FUAITypeEnum fUAITypeEnum2 = this.landmarksType;
        if (fUAITypeEnum2 != null) {
            linkedHashMap.put(PropParam.KEY_LANDMARKS_TYPE, Integer.valueOf(fUAITypeEnum2.getType()));
        }
        return linkedHashMap;
    }
}
