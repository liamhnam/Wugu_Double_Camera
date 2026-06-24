package com.faceunity.core.model.bodyBeauty;

import com.faceunity.core.controller.bodyBeauty.BodyBeautyController;
import com.faceunity.core.controller.bodyBeauty.BodyBeautyParam;
import com.faceunity.core.entity.FUBundleData;
import com.faceunity.core.model.BaseSingleModel;
import com.faceunity.core.support.FURenderBridge;
import java.util.LinkedHashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0014\u0010$\u001a\u000e\u0012\u0004\u0012\u00020&\u0012\u0004\u0012\u00020'0%H\u0014J\b\u0010(\u001a\u00020)H\u0014R$\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0006@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR$\u0010\r\u001a\u00020\f2\u0006\u0010\u0005\u001a\u00020\f@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R$\u0010\u0012\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0006@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\t\"\u0004\b\u0014\u0010\u000bR$\u0010\u0015\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0006@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\t\"\u0004\b\u0017\u0010\u000bR$\u0010\u0018\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0006@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\t\"\u0004\b\u001a\u0010\u000bR$\u0010\u001b\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0006@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\t\"\u0004\b\u001d\u0010\u000bR$\u0010\u001e\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0006@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010\t\"\u0004\b \u0010\u000bR$\u0010!\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0006@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\"\u0010\t\"\u0004\b#\u0010\u000b¨\u0006*"}, m1293d2 = {"Lcom/faceunity/core/model/bodyBeauty/BodyBeauty;", "Lcom/faceunity/core/model/BaseSingleModel;", "controlBundle", "Lcom/faceunity/core/entity/FUBundleData;", "(Lcom/faceunity/core/entity/FUBundleData;)V", "value", "", "bodySlimIntensity", "getBodySlimIntensity", "()D", "setBodySlimIntensity", "(D)V", "", "enableDebug", "getEnableDebug", "()Z", "setEnableDebug", "(Z)V", "headSlimIntensity", "getHeadSlimIntensity", "setHeadSlimIntensity", "hipSlimIntensity", "getHipSlimIntensity", "setHipSlimIntensity", "legSlimIntensity", "getLegSlimIntensity", "setLegSlimIntensity", "legStretchIntensity", "getLegStretchIntensity", "setLegStretchIntensity", "shoulderSlimIntensity", "getShoulderSlimIntensity", "setShoulderSlimIntensity", "waistSlimIntensity", "getWaistSlimIntensity", "setWaistSlimIntensity", "buildParams", "Ljava/util/LinkedHashMap;", "", "", "getModelController", "Lcom/faceunity/core/controller/bodyBeauty/BodyBeautyController;", "fu_core_all_featureRelease"}, m1294k = 1, m1295mv = {1, 1, 16})
public final class BodyBeauty extends BaseSingleModel {
    private double bodySlimIntensity;
    private boolean enableDebug;
    private double headSlimIntensity;
    private double hipSlimIntensity;
    private double legSlimIntensity;
    private double legStretchIntensity;
    private double shoulderSlimIntensity;
    private double waistSlimIntensity;

    public BodyBeauty(FUBundleData controlBundle) {
        super(controlBundle);
        Intrinsics.checkParameterIsNotNull(controlBundle, "controlBundle");
        this.shoulderSlimIntensity = 0.5d;
    }

    @Override
    public BodyBeautyController getMBgSegGreenController() {
        return FURenderBridge.INSTANCE.getInstance$fu_core_all_featureRelease().getMBodyBeautyController$fu_core_all_featureRelease();
    }

    public final boolean getEnableDebug() {
        return this.enableDebug;
    }

    public final void setEnableDebug(boolean z) {
        this.enableDebug = z;
        updateAttributes(BodyBeautyParam.IS_DEBUG, Integer.valueOf(z ? 1 : 0));
    }

    public final double getBodySlimIntensity() {
        return this.bodySlimIntensity;
    }

    public final void setBodySlimIntensity(double d) {
        this.bodySlimIntensity = d;
        updateAttributes(BodyBeautyParam.BODY_SLIM_INTENSITY, Double.valueOf(d));
    }

    public final double getLegStretchIntensity() {
        return this.legStretchIntensity;
    }

    public final void setLegStretchIntensity(double d) {
        this.legStretchIntensity = d;
        updateAttributes(BodyBeautyParam.LEG_STRETCH_INTENSITY, Double.valueOf(d));
    }

    public final double getWaistSlimIntensity() {
        return this.waistSlimIntensity;
    }

    public final void setWaistSlimIntensity(double d) {
        this.waistSlimIntensity = d;
        updateAttributes(BodyBeautyParam.WAIST_SLIM_INTENSITY, Double.valueOf(d));
    }

    public final double getShoulderSlimIntensity() {
        return this.shoulderSlimIntensity;
    }

    public final void setShoulderSlimIntensity(double d) {
        this.shoulderSlimIntensity = d;
        updateAttributes(BodyBeautyParam.SHOULDER_SLIM_INTENSITY, Double.valueOf(d));
    }

    public final double getHipSlimIntensity() {
        return this.hipSlimIntensity;
    }

    public final void setHipSlimIntensity(double d) {
        this.hipSlimIntensity = d;
        updateAttributes(BodyBeautyParam.HIP_SLIM_INTENSITY, Double.valueOf(d));
    }

    public final double getHeadSlimIntensity() {
        return this.headSlimIntensity;
    }

    public final void setHeadSlimIntensity(double d) {
        this.headSlimIntensity = d;
        updateAttributes(BodyBeautyParam.HEAD_SLIM_INTENSITY, Double.valueOf(d));
    }

    public final double getLegSlimIntensity() {
        return this.legSlimIntensity;
    }

    public final void setLegSlimIntensity(double d) {
        this.legSlimIntensity = d;
        updateAttributes(BodyBeautyParam.LEG_SLIM_INTENSITY, Double.valueOf(d));
    }

    @Override
    protected LinkedHashMap<String, Object> buildParams() {
        LinkedHashMap<String, Object> linkedHashMap = new LinkedHashMap<>();
        LinkedHashMap<String, Object> linkedHashMap2 = linkedHashMap;
        linkedHashMap2.put(BodyBeautyParam.CLEAR_SLIM, 1);
        linkedHashMap2.put(BodyBeautyParam.IS_DEBUG, Integer.valueOf(this.enableDebug ? 1 : 0));
        linkedHashMap2.put(BodyBeautyParam.BODY_SLIM_INTENSITY, Double.valueOf(this.bodySlimIntensity));
        linkedHashMap2.put(BodyBeautyParam.LEG_STRETCH_INTENSITY, Double.valueOf(this.legStretchIntensity));
        linkedHashMap2.put(BodyBeautyParam.WAIST_SLIM_INTENSITY, Double.valueOf(this.waistSlimIntensity));
        linkedHashMap2.put(BodyBeautyParam.SHOULDER_SLIM_INTENSITY, Double.valueOf(this.shoulderSlimIntensity));
        linkedHashMap2.put(BodyBeautyParam.HIP_SLIM_INTENSITY, Double.valueOf(this.hipSlimIntensity));
        linkedHashMap2.put(BodyBeautyParam.HEAD_SLIM_INTENSITY, Double.valueOf(this.headSlimIntensity));
        linkedHashMap2.put(BodyBeautyParam.LEG_SLIM_INTENSITY, Double.valueOf(this.legSlimIntensity));
        return linkedHashMap;
    }
}
