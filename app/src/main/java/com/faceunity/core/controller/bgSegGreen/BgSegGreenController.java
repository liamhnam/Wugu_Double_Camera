package com.faceunity.core.controller.bgSegGreen;

import com.arthenica.ffmpegkit.StreamInformation;
import com.faceunity.core.controller.BaseSingleController;
import com.faceunity.core.entity.FUFeaturesData;
import com.faceunity.core.enumeration.FUExternalInputEnum;
import com.faceunity.core.utils.FULogger;
import java.util.LinkedHashMap;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

@Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0014J-\u0010\u000b\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0011H\u0000¢\u0006\u0002\b\u0013J-\u0010\u0014\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0011H\u0000¢\u0006\u0002\b\u0015J\u001d\u0010\u0016\u001a\u00020\b2\u000e\u0010\u0017\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u0018H\u0010¢\u0006\u0002\b\u0019J\u0015\u0010\u001a\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\rH\u0000¢\u0006\u0002\b\u001bJ\u0015\u0010\u001c\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\rH\u0000¢\u0006\u0002\b\u001dJ2\u0010\u001e\u001a\u00020\b2(\b\u0002\u0010\u001f\u001a\"\u0012\u0004\u0012\u00020!\u0012\u0004\u0012\u00020\"\u0018\u00010 j\u0010\u0012\u0004\u0012\u00020!\u0012\u0004\u0012\u00020\"\u0018\u0001`#H\u0002J-\u0010$\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u0006\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0004H\u0000¢\u0006\u0002\b%J\r\u0010&\u001a\u00020\bH\u0000¢\u0006\u0002\b'J\r\u0010(\u001a\u00020\bH\u0000¢\u0006\u0002\b)J\b\u0010*\u001a\u00020\bH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006+"}, m1293d2 = {"Lcom/faceunity/core/controller/bgSegGreen/BgSegGreenController;", "Lcom/faceunity/core/controller/BaseSingleController;", "()V", "centerX", "", "centerY", "zoom", "applyControllerBundle", "", "featuresData", "Lcom/faceunity/core/entity/FUFeaturesData;", "createBgSegment", "sign", "", "rgba", "", StreamInformation.KEY_WIDTH, "", StreamInformation.KEY_HEIGHT, "createBgSegment$fu_core_all_featureRelease", "createSafeAreaSegment", "createSafeAreaSegment$fu_core_all_featureRelease", "release", "unit", "Lkotlin/Function0;", "release$fu_core_all_featureRelease", "removeBgSegment", "removeBgSegment$fu_core_all_featureRelease", "removeSafeAreaSegment", "removeSafeAreaSegment$fu_core_all_featureRelease", "setBgSegGreenParams", "params", "Ljava/util/LinkedHashMap;", "", "", "Lkotlin/collections/LinkedHashMap;", "setScale", "setScale$fu_core_all_featureRelease", "updateFlipMode", "updateFlipMode$fu_core_all_featureRelease", "updateRotationMode", "updateRotationMode$fu_core_all_featureRelease", "updateScale", "fu_core_all_featureRelease"}, m1294k = 1, m1295mv = {1, 1, 16})
public final class BgSegGreenController extends BaseSingleController {
    private double zoom = 1.0d;
    private double centerX = 0.5d;
    private double centerY = 0.5d;

    @Override
    protected void applyControllerBundle(FUFeaturesData featuresData) {
        Intrinsics.checkParameterIsNotNull(featuresData, "featuresData");
        BaseSingleController.applyControllerBundleAction$default(this, featuresData.getBundle(), featuresData.getEnable(), null, 4, null);
        Object remark = featuresData.getRemark();
        if (remark == null) {
            Intrinsics.throwNpe();
        }
        if (remark == null) {
            throw new TypeCastException("null cannot be cast to non-null type com.faceunity.core.controller.bgSegGreen.BgSegGreenRemark");
        }
        BgSegGreenRemark bgSegGreenRemark = (BgSegGreenRemark) remark;
        this.zoom = bgSegGreenRemark.getZoom();
        this.centerX = bgSegGreenRemark.getCenterX();
        this.centerY = bgSegGreenRemark.getCenterY();
        setBgSegGreenParams(featuresData.getParam());
    }

    public final void setScale$fu_core_all_featureRelease(long sign, double zoom, double centerX, double centerY) {
        FULogger.m295i(getTAG(), "setItemParam sign:" + (sign == getModelSign()) + "  zoom:" + zoom + "   centerX:" + centerX + "   centerY:" + centerY);
        if (sign != getModelSign()) {
            return;
        }
        this.zoom = zoom;
        this.centerX = centerX;
        this.centerY = centerY;
        updateScale();
    }

    public final void updateRotationMode$fu_core_all_featureRelease() {
        if (getMControllerBundleHandle() <= 0) {
            return;
        }
        itemSetParam("rotation_mode", Double.valueOf(getMFURenderBridge().getMRotationMode()));
    }

    public final void updateFlipMode$fu_core_all_featureRelease() {
        if (getMControllerBundleHandle() <= 0) {
            return;
        }
        itemSetParam("rotation_mode", Double.valueOf(getMFURenderBridge().getMRotationMode()));
        updateScale();
    }

    public final void createSafeAreaSegment$fu_core_all_featureRelease(long sign, byte[] rgba, int width, int height) {
        Intrinsics.checkParameterIsNotNull(rgba, "rgba");
        if (sign != getModelSign()) {
            return;
        }
        FULogger.m295i(getTAG(), "createSafeAreaSegment ");
        deleteItemTex(BgSegGreenParam.TEX_TEMPLATE);
        createItemTex(BgSegGreenParam.TEX_TEMPLATE, rgba, width, height);
    }

    public final void removeSafeAreaSegment$fu_core_all_featureRelease(long sign) {
        if (sign != getModelSign()) {
            return;
        }
        FULogger.m295i(getTAG(), "removeSafeAreaSegment ");
        deleteItemTex(BgSegGreenParam.TEX_TEMPLATE);
    }

    public final void createBgSegment$fu_core_all_featureRelease(long sign, byte[] rgba, int width, int height) {
        Intrinsics.checkParameterIsNotNull(rgba, "rgba");
        if (sign != getModelSign()) {
            return;
        }
        FULogger.m295i(getTAG(), "createBgSegment ");
        createItemTex(BgSegGreenParam.TAX_BG, rgba, width, height);
    }

    public final void removeBgSegment$fu_core_all_featureRelease(long sign) {
        if (sign != getModelSign()) {
            return;
        }
        FULogger.m295i(getTAG(), "removeBgSegment ");
        deleteItemTex(BgSegGreenParam.TAX_BG);
    }

    @Override
    public void release$fu_core_all_featureRelease(Function0<Unit> unit) throws InterruptedException {
        super.release$fu_core_all_featureRelease(new Function0<Unit>() {
            {
                super(0);
            }

            @Override
            public Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            public final void invoke2() {
                this.this$0.deleteItemTex(BgSegGreenParam.TAX_BG);
            }
        });
    }

    private final void updateScale() {
        double dSqrt = Math.sqrt(this.zoom);
        double d = this.centerX;
        double d2 = this.centerY;
        if (getMFURenderBridge().getExternalInputType() == FUExternalInputEnum.EXTERNAL_INPUT_TYPE_VIDEO) {
            int mRotationMode = getMFURenderBridge().getMRotationMode();
            if (mRotationMode == 1) {
                d2 = this.centerX;
                d = ((double) 1) - this.centerY;
            } else if (mRotationMode == 2) {
                double d3 = 1;
                double d4 = d3 - this.centerX;
                double d5 = d3 - this.centerY;
                d = d4;
                d2 = d5;
            } else if (mRotationMode == 3) {
                d = this.centerY;
                d2 = ((double) 1) - this.centerX;
            }
        }
        double d6 = dSqrt * 0.5d;
        itemSetParam(BgSegGreenParam.START_X, Double.valueOf(d - d6));
        itemSetParam(BgSegGreenParam.START_Y, Double.valueOf(d2 - d6));
        itemSetParam(BgSegGreenParam.END_X, Double.valueOf(d + d6));
        itemSetParam(BgSegGreenParam.END_Y, Double.valueOf(d2 + d6));
    }

    static void setBgSegGreenParams$default(BgSegGreenController bgSegGreenController, LinkedHashMap linkedHashMap, int i, Object obj) {
        if ((i & 1) != 0) {
            linkedHashMap = null;
        }
        bgSegGreenController.setBgSegGreenParams(linkedHashMap);
    }

    private final void setBgSegGreenParams(LinkedHashMap<String, Object> params) {
        itemSetParam("rotation_mode", Double.valueOf(getMFURenderBridge().getMRotationMode()));
        updateScale();
        if (params != null) {
            itemSetParam(params);
        }
    }
}
