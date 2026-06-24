package com.faceunity.core.model.hairBeauty;

import com.faceunity.core.controller.hairBeauty.HairBeautyController;
import com.faceunity.core.controller.hairBeauty.HairBeautyParam;
import com.faceunity.core.entity.FUBundleData;
import com.faceunity.core.entity.FUColorLABData;
import com.faceunity.core.model.BaseSingleModel;
import com.faceunity.core.support.FURenderBridge;
import java.util.LinkedHashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u0006\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0016\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0014\u0010\u001b\u001a\u000e\u0012\u0004\u0012\u00020\u001d\u0012\u0004\u0012\u00020\u001e0\u001cH\u0014J\b\u0010\u001f\u001a\u00020 H\u0014R(\u0010\u0007\u001a\u0004\u0018\u00010\u00062\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR$\u0010\r\u001a\u00020\f2\u0006\u0010\u0005\u001a\u00020\f@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R$\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0005\u001a\u00020\u0012@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R$\u0010\u0018\u001a\u00020\u00122\u0006\u0010\u0005\u001a\u00020\u0012@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u0015\"\u0004\b\u001a\u0010\u0017¨\u0006!"}, m1293d2 = {"Lcom/faceunity/core/model/hairBeauty/HairBeautyNormal;", "Lcom/faceunity/core/model/BaseSingleModel;", "controlBundle", "Lcom/faceunity/core/entity/FUBundleData;", "(Lcom/faceunity/core/entity/FUBundleData;)V", "value", "Lcom/faceunity/core/entity/FUColorLABData;", "hairColorLABData", "getHairColorLABData", "()Lcom/faceunity/core/entity/FUColorLABData;", "setHairColorLABData", "(Lcom/faceunity/core/entity/FUColorLABData;)V", "", "hairIndex", "getHairIndex", "()I", "setHairIndex", "(I)V", "", "hairIntensity", "getHairIntensity", "()D", "setHairIntensity", "(D)V", "hairShine", "getHairShine", "setHairShine", "buildParams", "Ljava/util/LinkedHashMap;", "", "", "getModelController", "Lcom/faceunity/core/controller/hairBeauty/HairBeautyController;", "fu_core_all_featureRelease"}, m1294k = 1, m1295mv = {1, 1, 16})
public class HairBeautyNormal extends BaseSingleModel {
    private FUColorLABData hairColorLABData;
    private int hairIndex;
    private double hairIntensity;
    private double hairShine;

    public HairBeautyNormal(FUBundleData controlBundle) {
        super(controlBundle);
        Intrinsics.checkParameterIsNotNull(controlBundle, "controlBundle");
        this.hairIntensity = 1.0d;
    }

    @Override
    public HairBeautyController getModelController() {
        return FURenderBridge.INSTANCE.getInstance$fu_core_all_featureRelease().getMHairBeautyController$fu_core_all_featureRelease();
    }

    public final int getHairIndex() {
        return this.hairIndex;
    }

    public final void setHairIndex(int i) {
        this.hairIndex = i;
        updateAttributes("Index", Integer.valueOf(i));
    }

    public final double getHairIntensity() {
        return this.hairIntensity;
    }

    public final void setHairIntensity(double d) {
        this.hairIntensity = d;
        updateAttributes(HairBeautyParam.INTENSITY, Double.valueOf(d));
    }

    public final double getHairShine() {
        return this.hairShine;
    }

    public final void setHairShine(double d) {
        this.hairShine = d;
        updateAttributes(HairBeautyParam.SHINE, Double.valueOf(d));
    }

    public final FUColorLABData getHairColorLABData() {
        return this.hairColorLABData;
    }

    public final void setHairColorLABData(FUColorLABData fUColorLABData) {
        if (fUColorLABData == null) {
            return;
        }
        this.hairColorLABData = fUColorLABData;
        LinkedHashMap<String, Object> linkedHashMap = new LinkedHashMap<>();
        fUColorLABData.coverLABParam("Col", linkedHashMap);
        updateAttributes("Col", linkedHashMap);
    }

    @Override
    protected LinkedHashMap<String, Object> buildParams() {
        LinkedHashMap<String, Object> linkedHashMap = new LinkedHashMap<>();
        LinkedHashMap<String, Object> linkedHashMap2 = linkedHashMap;
        linkedHashMap2.put("Index", Integer.valueOf(this.hairIndex));
        linkedHashMap2.put(HairBeautyParam.INTENSITY, Double.valueOf(this.hairIntensity));
        linkedHashMap2.put(HairBeautyParam.SHINE, Double.valueOf(this.hairShine));
        FUColorLABData fUColorLABData = this.hairColorLABData;
        if (fUColorLABData != null) {
            fUColorLABData.coverLABParam("Col", linkedHashMap);
        }
        return linkedHashMap;
    }
}
