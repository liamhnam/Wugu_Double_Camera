package com.faceunity.core.model.bgSegGreen;

import com.arthenica.ffmpegkit.StreamInformation;
import com.faceunity.core.controller.bgSegGreen.BgSegGreenController;
import com.faceunity.core.controller.bgSegGreen.BgSegGreenParam;
import com.faceunity.core.controller.bgSegGreen.BgSegGreenRemark;
import com.faceunity.core.entity.FUBundleData;
import com.faceunity.core.entity.FUColorRGBData;
import com.faceunity.core.entity.FUCoordinate2DData;
import com.faceunity.core.entity.FUFeaturesData;
import com.faceunity.core.model.BaseSingleModel;
import com.faceunity.core.support.FURenderBridge;
import com.p020hp.jipp.model.MediaType;
import java.util.LinkedHashMap;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

@Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u0000d\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0006\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\r\u0010*\u001a\u00020+H\u0010¢\u0006\u0002\b,J\u0014\u0010-\u001a\u000e\u0012\u0004\u0012\u00020/\u0012\u0004\u0012\u0002000.H\u0014J\u001e\u00101\u001a\u0002022\u0006\u00103\u001a\u0002042\u0006\u00105\u001a\u0002062\u0006\u00107\u001a\u000206J\u001e\u00108\u001a\u0002022\u0006\u00103\u001a\u0002042\u0006\u00105\u001a\u0002062\u0006\u00107\u001a\u000206J\b\u00109\u001a\u00020\u001dH\u0014J\u0006\u0010:\u001a\u000202J\u0006\u0010;\u001a\u000202R$\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0006@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR$\u0010\r\u001a\u00020\f2\u0006\u0010\u0005\u001a\u00020\f@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R$\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0005\u001a\u00020\u0012@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R$\u0010\u0018\u001a\u00020\u00172\u0006\u0010\u0005\u001a\u00020\u0017@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001bR\u000e\u0010\u001c\u001a\u00020\u001dX\u0082\u0004¢\u0006\u0002\n\u0000R$\u0010\u001e\u001a\u00020\u00172\u0006\u0010\u0005\u001a\u00020\u0017@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010\u0019\"\u0004\b \u0010\u001bR$\u0010!\u001a\u00020\u00172\u0006\u0010\u0005\u001a\u00020\u0017@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\"\u0010\u0019\"\u0004\b#\u0010\u001bR$\u0010$\u001a\u00020\u00172\u0006\u0010\u0005\u001a\u00020\u0017@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b%\u0010\u0019\"\u0004\b&\u0010\u001bR$\u0010'\u001a\u00020\u00172\u0006\u0010\u0005\u001a\u00020\u0017@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b(\u0010\u0019\"\u0004\b)\u0010\u001b¨\u0006<"}, m1293d2 = {"Lcom/faceunity/core/model/bgSegGreen/BgSegGreen;", "Lcom/faceunity/core/model/BaseSingleModel;", "controlBundle", "Lcom/faceunity/core/entity/FUBundleData;", "(Lcom/faceunity/core/entity/FUBundleData;)V", "value", "Lcom/faceunity/core/entity/FUCoordinate2DData;", "centerPoint", "getCenterPoint", "()Lcom/faceunity/core/entity/FUCoordinate2DData;", "setCenterPoint", "(Lcom/faceunity/core/entity/FUCoordinate2DData;)V", "Lcom/faceunity/core/entity/FUColorRGBData;", "colorRGB", "getColorRGB", "()Lcom/faceunity/core/entity/FUColorRGBData;", "setColorRGB", "(Lcom/faceunity/core/entity/FUColorRGBData;)V", "", "isBGRA", "()Z", "setBGRA", "(Z)V", "", "isUseTemplate", "()D", "setUseTemplate", "(D)V", "mBgSegGreenController", "Lcom/faceunity/core/controller/bgSegGreen/BgSegGreenController;", "similarity", "getSimilarity", "setSimilarity", "smoothness", "getSmoothness", "setSmoothness", MediaType.transparency, "getTransparency", "setTransparency", "zoom", "getZoom", "setZoom", "buildFUFeaturesData", "Lcom/faceunity/core/entity/FUFeaturesData;", "buildFUFeaturesData$fu_core_all_featureRelease", "buildParams", "Ljava/util/LinkedHashMap;", "", "", "createBgSegment", "", "rgba", "", StreamInformation.KEY_WIDTH, "", StreamInformation.KEY_HEIGHT, "createSafeAreaSegment", "getModelController", "removeBgSegment", "removeSafeAreaSegment", "fu_core_all_featureRelease"}, m1294k = 1, m1295mv = {1, 1, 16})
public final class BgSegGreen extends BaseSingleModel {
    private FUCoordinate2DData centerPoint;
    private FUColorRGBData colorRGB;
    private boolean isBGRA;
    private double isUseTemplate;
    private final BgSegGreenController mBgSegGreenController;
    private double similarity;
    private double smoothness;
    private double transparency;
    private double zoom;

    public BgSegGreen(FUBundleData controlBundle) {
        super(controlBundle);
        Intrinsics.checkParameterIsNotNull(controlBundle, "controlBundle");
        this.mBgSegGreenController = FURenderBridge.INSTANCE.getInstance$fu_core_all_featureRelease().getMBgSegGreenController$fu_core_all_featureRelease();
        this.colorRGB = new FUColorRGBData(0.0d, 255.0d, 0.0d, 0.0d, 8, null);
        this.similarity = 0.5d;
        this.smoothness = 0.3d;
        this.transparency = 0.66d;
        this.centerPoint = new FUCoordinate2DData(0.5d, 0.5d);
        this.zoom = 1.0d;
    }

    @Override
    public BgSegGreenController getMBgSegGreenController() {
        return this.mBgSegGreenController;
    }

    public final boolean getIsBGRA() {
        return this.isBGRA;
    }

    public final void setBGRA(boolean z) {
        this.isBGRA = z;
        updateAttributes(BgSegGreenParam.IS_BGRA, Double.valueOf(z ? 1.0d : 0.0d));
    }

    public final FUColorRGBData getColorRGB() {
        return this.colorRGB;
    }

    public final void setColorRGB(FUColorRGBData value) {
        Intrinsics.checkParameterIsNotNull(value, "value");
        this.colorRGB = value;
        updateAttributes(BgSegGreenParam.RGB_COLOR, value.toColorArray());
        Double attributesDouble = getAttributesDouble(BgSegGreenParam.SIMILARITY);
        if (attributesDouble != null) {
            setSimilarity(attributesDouble.doubleValue());
        }
        Double attributesDouble2 = getAttributesDouble(BgSegGreenParam.SMOOTHNESS);
        if (attributesDouble2 != null) {
            setSmoothness(attributesDouble2.doubleValue());
        }
        Double attributesDouble3 = getAttributesDouble(BgSegGreenParam.TRANSPARENCY);
        if (attributesDouble3 != null) {
            setTransparency(attributesDouble3.doubleValue());
        }
    }

    public final double getSimilarity() {
        return this.similarity;
    }

    public final void setSimilarity(double d) {
        this.similarity = d;
        updateAttributes(BgSegGreenParam.SIMILARITY, Double.valueOf(d));
    }

    public final double getSmoothness() {
        return this.smoothness;
    }

    public final void setSmoothness(double d) {
        this.smoothness = d;
        updateAttributes(BgSegGreenParam.SMOOTHNESS, Double.valueOf(d));
    }

    public final double getTransparency() {
        return this.transparency;
    }

    public final void setTransparency(double d) {
        this.transparency = d;
        updateAttributes(BgSegGreenParam.TRANSPARENCY, Double.valueOf(d));
    }

    public final double getIsUseTemplate() {
        return this.isUseTemplate;
    }

    public final void setUseTemplate(double d) {
        this.isUseTemplate = d;
        updateAttributes(BgSegGreenParam.IS_USE_TEMPLATE, Double.valueOf(d));
    }

    public final FUCoordinate2DData getCenterPoint() {
        return this.centerPoint;
    }

    public final void setCenterPoint(final FUCoordinate2DData value) {
        Intrinsics.checkParameterIsNotNull(value, "value");
        this.centerPoint = value;
        updateCustomUnit("coordinate", new Function0<Unit>() {
            {
                super(0);
            }

            @Override
            public Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            public final void invoke2() {
                this.this$0.mBgSegGreenController.setScale$fu_core_all_featureRelease(this.this$0.getMSign(), this.this$0.getZoom(), value.getPositionX(), value.getPositionY());
            }
        });
    }

    public final double getZoom() {
        return this.zoom;
    }

    public final void setZoom(final double d) {
        this.zoom = d;
        updateCustomUnit("coordinate", new Function0<Unit>() {
            {
                super(0);
            }

            @Override
            public Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            public final void invoke2() {
                this.this$0.mBgSegGreenController.setScale$fu_core_all_featureRelease(this.this$0.getMSign(), d, this.this$0.getCenterPoint().getPositionX(), this.this$0.getCenterPoint().getPositionY());
            }
        });
    }

    public final void createSafeAreaSegment(final byte[] rgba, final int width, final int height) {
        Intrinsics.checkParameterIsNotNull(rgba, "rgba");
        setUseTemplate(1.0d);
        updateCustomUnit("createSafeAreaSegment", new Function0<Unit>() {
            {
                super(0);
            }

            @Override
            public Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            public final void invoke2() {
                BgSegGreen.this.mBgSegGreenController.createSafeAreaSegment$fu_core_all_featureRelease(BgSegGreen.this.getMSign(), rgba, width, height);
            }
        });
    }

    public final void removeSafeAreaSegment() {
        setUseTemplate(0.0d);
        updateCustomUnit("removeSafeAreaSegment", new Function0<Unit>() {
            {
                super(0);
            }

            @Override
            public Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            public final void invoke2() {
                BgSegGreen.this.mBgSegGreenController.removeSafeAreaSegment$fu_core_all_featureRelease(BgSegGreen.this.getMSign());
            }
        });
    }

    public final void createBgSegment(final byte[] rgba, final int width, final int height) {
        Intrinsics.checkParameterIsNotNull(rgba, "rgba");
        updateCustomUnit("createBgSegment", new Function0<Unit>() {
            {
                super(0);
            }

            @Override
            public Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            public final void invoke2() {
                BgSegGreen.this.mBgSegGreenController.createBgSegment$fu_core_all_featureRelease(BgSegGreen.this.getMSign(), rgba, width, height);
            }
        });
    }

    public final void removeBgSegment() {
        updateCustomUnit("removeBgSegment", new Function0<Unit>() {
            {
                super(0);
            }

            @Override
            public Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            public final void invoke2() {
                BgSegGreen.this.mBgSegGreenController.removeBgSegment$fu_core_all_featureRelease(BgSegGreen.this.getMSign());
            }
        });
    }

    @Override
    protected LinkedHashMap<String, Object> buildParams() {
        LinkedHashMap<String, Object> linkedHashMap = new LinkedHashMap<>();
        LinkedHashMap<String, Object> linkedHashMap2 = linkedHashMap;
        linkedHashMap2.put(BgSegGreenParam.RGB_COLOR, this.colorRGB.toColorArray());
        linkedHashMap2.put(BgSegGreenParam.SIMILARITY, Double.valueOf(this.similarity));
        linkedHashMap2.put(BgSegGreenParam.SMOOTHNESS, Double.valueOf(this.smoothness));
        linkedHashMap2.put(BgSegGreenParam.TRANSPARENCY, Double.valueOf(this.transparency));
        return linkedHashMap;
    }

    @Override
    public FUFeaturesData buildFUFeaturesData$fu_core_all_featureRelease() {
        return new FUFeaturesData(getControlBundle(), buildParams(), getEnable(), new BgSegGreenRemark(this.zoom, this.centerPoint.getPositionX(), this.centerPoint.getPositionY()), 0L, 16, null);
    }
}
