package com.faceunity.core.model.hairBeauty;

import com.faceunity.core.controller.hairBeauty.HairBeautyParam;
import com.faceunity.core.entity.FUBundleData;
import com.faceunity.core.entity.FUColorLABData;
import java.util.LinkedHashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0006\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J$\u0010\u0012\u001a\u001e\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\u00150\u0013j\u000e\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\u0015`\u0016H\u0014R(\u0010\u0007\u001a\u0004\u0018\u00010\u00062\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR$\u0010\r\u001a\u00020\f2\u0006\u0010\u0005\u001a\u00020\f@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011¨\u0006\u0017"}, m1293d2 = {"Lcom/faceunity/core/model/hairBeauty/HairBeautyGradient;", "Lcom/faceunity/core/model/hairBeauty/HairBeautyNormal;", "controlBundle", "Lcom/faceunity/core/entity/FUBundleData;", "(Lcom/faceunity/core/entity/FUBundleData;)V", "value", "Lcom/faceunity/core/entity/FUColorLABData;", "hairColorLABData2", "getHairColorLABData2", "()Lcom/faceunity/core/entity/FUColorLABData;", "setHairColorLABData2", "(Lcom/faceunity/core/entity/FUColorLABData;)V", "", "hairShine2", "getHairShine2", "()D", "setHairShine2", "(D)V", "buildParams", "Ljava/util/LinkedHashMap;", "", "", "Lkotlin/collections/LinkedHashMap;", "fu_core_all_featureRelease"}, m1294k = 1, m1295mv = {1, 1, 16})
public final class HairBeautyGradient extends HairBeautyNormal {
    private FUColorLABData hairColorLABData2;
    private double hairShine2;

    public HairBeautyGradient(FUBundleData controlBundle) {
        super(controlBundle);
        Intrinsics.checkParameterIsNotNull(controlBundle, "controlBundle");
    }

    public final double getHairShine2() {
        return this.hairShine2;
    }

    public final void setHairShine2(double d) {
        this.hairShine2 = d;
        updateAttributes(HairBeautyParam.SHINE_1, Double.valueOf(d));
    }

    public final FUColorLABData getHairColorLABData2() {
        return this.hairColorLABData2;
    }

    public final void setHairColorLABData2(FUColorLABData fUColorLABData) {
        if (fUColorLABData == null) {
            return;
        }
        this.hairColorLABData2 = fUColorLABData;
        LinkedHashMap<String, Object> linkedHashMap = new LinkedHashMap<>();
        fUColorLABData.coverLABParam("Col1", linkedHashMap);
        updateAttributes("Col1", linkedHashMap);
    }

    @Override
    protected LinkedHashMap<String, Object> buildParams() {
        LinkedHashMap<String, Object> linkedHashMap = new LinkedHashMap<>();
        LinkedHashMap<String, Object> linkedHashMap2 = linkedHashMap;
        linkedHashMap2.put("Index", Integer.valueOf(getHairIndex()));
        linkedHashMap2.put(HairBeautyParam.INTENSITY, Double.valueOf(getHairIntensity()));
        linkedHashMap2.put(HairBeautyParam.SHINE_0, Double.valueOf(getHairShine()));
        linkedHashMap2.put(HairBeautyParam.SHINE_1, Double.valueOf(this.hairShine2));
        FUColorLABData hairColorLABData = getHairColorLABData();
        if (hairColorLABData != null) {
            hairColorLABData.coverLABParam("Col0", linkedHashMap);
        }
        FUColorLABData fUColorLABData = this.hairColorLABData2;
        if (fUColorLABData != null) {
            fUColorLABData.coverLABParam("Col1", linkedHashMap);
        }
        return linkedHashMap;
    }
}
