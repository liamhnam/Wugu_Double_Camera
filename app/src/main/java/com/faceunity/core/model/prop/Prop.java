package com.faceunity.core.model.prop;

import com.arthenica.ffmpegkit.StreamInformation;
import com.faceunity.core.controller.prop.PropContainerController;
import com.faceunity.core.controller.prop.PropParam;
import com.faceunity.core.entity.FUBundleData;
import com.faceunity.core.entity.FUFeaturesData;
import com.faceunity.core.model.prop.animoji.Animoji;
import com.faceunity.core.model.prop.arMask.ARMask;
import com.faceunity.core.model.prop.bgSegCustom.BgSegCustom;
import com.faceunity.core.model.prop.bigHead.BigHead;
import com.faceunity.core.model.prop.expression.ExpressionRecognition;
import com.faceunity.core.model.prop.faceWarp.FaceWarp;
import com.faceunity.core.model.prop.gesture.GestureRecognition;
import com.faceunity.core.model.prop.humanOutline.HumanOutline;
import com.faceunity.core.model.prop.portraitSegment.PortraitSegment;
import com.faceunity.core.model.prop.sticker.FineSticker;
import com.faceunity.core.model.prop.sticker.Sticker;
import com.faceunity.core.support.FURenderBridge;
import com.tom_roush.fontbox.ttf.NamingTable;
import java.util.LinkedHashMap;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

@Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u0000n\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u0006\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u0013\n\u0000\n\u0002\u0010\u0014\n\u0002\b\u0005\b&\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\r\u0010\u001e\u001a\u00020\u001fH\u0000¢\u0006\u0002\b J)\u0010!\u001a\u001e\u0012\u0004\u0012\u00020#\u0012\u0004\u0012\u00020\u00010\"j\u000e\u0012\u0004\u0012\u00020#\u0012\u0004\u0012\u00020\u0001`$H\u0010¢\u0006\u0002\b%J)\u0010&\u001a\u001e\u0012\u0004\u0012\u00020#\u0012\u0004\u0012\u00020\u00010\"j\u000e\u0012\u0004\u0012\u00020#\u0012\u0004\u0012\u00020\u0001`$H\u0010¢\u0006\u0002\b'J(\u0010(\u001a\u00020)2\u0006\u0010*\u001a\u00020#2\u0006\u0010+\u001a\u00020,2\u0006\u0010-\u001a\u00020.2\u0006\u0010/\u001a\u00020.H\u0004J\u0010\u00100\u001a\u00020)2\u0006\u0010*\u001a\u00020#H\u0004J\u000e\u00101\u001a\u00020\u000e2\u0006\u00102\u001a\u00020#J\u0010\u00103\u001a\u0004\u0018\u0001042\u0006\u00102\u001a\u00020#J\u0010\u00105\u001a\u0004\u0018\u0001062\u0006\u00102\u001a\u00020#J\u0010\u00107\u001a\u0004\u0018\u00010#2\u0006\u00102\u001a\u00020#J\u0016\u00108\u001a\u00020)2\u0006\u00102\u001a\u00020#2\u0006\u0010\u0007\u001a\u00020\u0001J\u0018\u00109\u001a\u00020)2\u0006\u00102\u001a\u00020#2\u0006\u0010\u0007\u001a\u00020\u0001H\u0004J\u0018\u0010:\u001a\u00020)2\u0006\u00102\u001a\u00020#2\u0006\u0010\u0007\u001a\u00020\u0001H\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R$\u0010\t\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\b@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR$\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0007\u001a\u00020\u000e@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\u001b\u0010\u0014\u001a\u00020\u00158BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0018\u0010\u0019\u001a\u0004\b\u0016\u0010\u0017R\u0011\u0010\u001a\u001a\u00020\u001b¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001d¨\u0006;"}, m1293d2 = {"Lcom/faceunity/core/model/prop/Prop;", "", "controlBundle", "Lcom/faceunity/core/entity/FUBundleData;", "(Lcom/faceunity/core/entity/FUBundleData;)V", "getControlBundle", "()Lcom/faceunity/core/entity/FUBundleData;", "value", "", "enable", "getEnable", "()Z", "setEnable", "(Z)V", "", "flipAction", "getFlipAction", "()D", "setFlipAction", "(D)V", "mPropController", "Lcom/faceunity/core/controller/prop/PropContainerController;", "getMPropController", "()Lcom/faceunity/core/controller/prop/PropContainerController;", "mPropController$delegate", "Lkotlin/Lazy;", "propId", "", "getPropId", "()J", "buildFUFeaturesData", "Lcom/faceunity/core/entity/FUFeaturesData;", "buildFUFeaturesData$fu_core_all_featureRelease", "buildParams", "Ljava/util/LinkedHashMap;", "", "Lkotlin/collections/LinkedHashMap;", "buildParams$fu_core_all_featureRelease", "buildRemark", "buildRemark$fu_core_all_featureRelease", "createTexForItem", "", NamingTable.TAG, "rgba", "", StreamInformation.KEY_WIDTH, "", StreamInformation.KEY_HEIGHT, "deleteTexForItem", "getParamDouble", "key", "getParamDoubleArray", "", "getParamFloatArray", "", "getParamString", "setParam", "updateAttributes", "updateAttributesGL", "fu_core_all_featureRelease"}, m1294k = 1, m1295mv = {1, 1, 16})
public abstract class Prop {
    private final FUBundleData controlBundle;
    private boolean enable;
    private double flipAction;

    private final Lazy mPropController;
    private final long propId;

    private final PropContainerController getMPropController() {
        return (PropContainerController) this.mPropController.getValue();
    }

    public Prop(FUBundleData controlBundle) {
        Intrinsics.checkParameterIsNotNull(controlBundle, "controlBundle");
        this.controlBundle = controlBundle;
        this.mPropController = LazyKt.lazy(new Function0<PropContainerController>() {
            @Override
            public final PropContainerController invoke() {
                return FURenderBridge.Companion.getInstance$fu_core_all_featureRelease().getMPropContainerController$fu_core_all_featureRelease();
            }
        });
        this.propId = System.nanoTime();
        this.enable = true;
    }

    public final FUBundleData getControlBundle() {
        return this.controlBundle;
    }

    public final long getPropId() {
        return this.propId;
    }

    public final boolean getEnable() {
        return this.enable;
    }

    public final void setEnable(boolean z) {
        if (z == this.enable) {
            return;
        }
        this.enable = z;
        getMPropController().setBundleEnable$fu_core_all_featureRelease(this.propId, this.enable);
    }

    public final double getFlipAction() {
        return this.flipAction;
    }

    public final void setFlipAction(double d) {
        this.flipAction = d;
        updateAttributes(PropParam.FLIP_ACTION, Double.valueOf(d));
    }

    public final double getParamDouble(String key) {
        Intrinsics.checkParameterIsNotNull(key, "key");
        return getMPropController().itemGetParamDouble$fu_core_all_featureRelease(this.propId, key);
    }

    public final double[] getParamDoubleArray(String key) {
        Intrinsics.checkParameterIsNotNull(key, "key");
        return getMPropController().itemGetParamDoubleArray$fu_core_all_featureRelease(this.propId, key);
    }

    public final String getParamString(String key) {
        Intrinsics.checkParameterIsNotNull(key, "key");
        return getMPropController().itemGetParamString$fu_core_all_featureRelease(this.propId, key);
    }

    public final float[] getParamFloatArray(String key) {
        Intrinsics.checkParameterIsNotNull(key, "key");
        return getMPropController().itemGetParamFloatArray$fu_core_all_featureRelease(this.propId, key);
    }

    public final void setParam(String key, Object value) {
        Intrinsics.checkParameterIsNotNull(key, "key");
        Intrinsics.checkParameterIsNotNull(value, "value");
        updateAttributes(key, value);
    }

    public LinkedHashMap<String, Object> buildParams$fu_core_all_featureRelease() {
        return new LinkedHashMap<>();
    }

    public final FUFeaturesData buildFUFeaturesData$fu_core_all_featureRelease() {
        return new FUFeaturesData(this.controlBundle, buildParams$fu_core_all_featureRelease(), this.enable, buildRemark$fu_core_all_featureRelease(), this.propId);
    }

    public LinkedHashMap<String, Object> buildRemark$fu_core_all_featureRelease() {
        LinkedHashMap<String, Object> linkedHashMap = new LinkedHashMap<>();
        int i = 0;
        if (!(this instanceof Sticker)) {
            if (this instanceof Animoji) {
                i = 1;
            } else if (this instanceof ARMask) {
                i = 2;
            } else if (this instanceof HumanOutline) {
                i = 4;
            } else if (this instanceof PortraitSegment) {
                i = 3;
            } else if (this instanceof BgSegCustom) {
                i = 5;
            } else if (this instanceof BigHead) {
                i = 6;
            } else if (this instanceof ExpressionRecognition) {
                i = 7;
            } else if (this instanceof FaceWarp) {
                i = 8;
            } else if (this instanceof GestureRecognition) {
                i = 9;
            } else if (this instanceof FineSticker) {
                i = 10;
            }
        }
        linkedHashMap.put(PropParam.PROP_TYPE, Integer.valueOf(i));
        return linkedHashMap;
    }

    protected final void updateAttributesGL(String key, Object value) {
        Intrinsics.checkParameterIsNotNull(key, "key");
        Intrinsics.checkParameterIsNotNull(value, "value");
        getMPropController().setItemParamGL$fu_core_all_featureRelease(this.propId, key, value);
    }

    protected final void updateAttributes(String key, Object value) {
        Intrinsics.checkParameterIsNotNull(key, "key");
        Intrinsics.checkParameterIsNotNull(value, "value");
        getMPropController().setItemParam$fu_core_all_featureRelease(this.propId, key, value);
    }

    protected final void createTexForItem(String name, byte[] rgba, int width, int height) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        Intrinsics.checkParameterIsNotNull(rgba, "rgba");
        getMPropController().createTexForItem$fu_core_all_featureRelease(this.propId, name, rgba, width, height);
    }

    protected final void deleteTexForItem(String name) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        getMPropController().deleteTexForItem$fu_core_all_featureRelease(this.propId, name);
    }
}
