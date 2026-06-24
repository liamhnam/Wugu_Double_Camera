package com.faceunity.core.model.makeup;

import com.faceunity.core.controller.makeup.MakeupController;
import com.faceunity.core.controller.makeup.MakeupParam;
import com.faceunity.core.entity.FUBundleData;
import com.faceunity.core.model.BaseSingleModel;
import com.faceunity.core.support.FURenderBridge;
import java.util.LinkedHashMap;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

@Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0006\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\t\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0016\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020\u0003H\u0002J$\u0010%\u001a\u001e\u0012\u0004\u0012\u00020'\u0012\u0004\u0012\u00020(0&j\u000e\u0012\u0004\u0012\u00020'\u0012\u0004\u0012\u00020(`)H\u0014J\b\u0010*\u001a\u0004\u0018\u00010\u0003J\b\u0010+\u001a\u00020\u0014H\u0014J\u0012\u0010,\u001a\u00020#2\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003H\u0016J\u001a\u0010-\u001a\u00020#2\u0006\u0010.\u001a\u00020'2\b\u0010$\u001a\u0004\u0018\u00010\u0003H\u0004R(\u0010\u0006\u001a\u0004\u0018\u00010\u00032\b\u0010\u0005\u001a\u0004\u0018\u00010\u0003@DX\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\u0004R\u001a\u0010\n\u001a\u00020\u000bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR$\u0010\u0010\u001a\u00020\u000b2\u0006\u0010\u0005\u001a\u00020\u000b@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\r\"\u0004\b\u0012\u0010\u000fR\u001b\u0010\u0013\u001a\u00020\u00148DX\u0084\u0084\u0002¢\u0006\f\n\u0004\b\u0017\u0010\u0018\u001a\u0004\b\u0015\u0010\u0016R$\u0010\u001a\u001a\u00020\u00192\u0006\u0010\u0005\u001a\u00020\u0019@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u001c\"\u0004\b\u001d\u0010\u001eR$\u0010\u001f\u001a\u00020\u000b2\u0006\u0010\u0005\u001a\u00020\u000b@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b \u0010\r\"\u0004\b!\u0010\u000f¨\u0006/"}, m1293d2 = {"Lcom/faceunity/core/model/makeup/SimpleMakeup;", "Lcom/faceunity/core/model/BaseSingleModel;", "controlBundle", "Lcom/faceunity/core/entity/FUBundleData;", "(Lcom/faceunity/core/entity/FUBundleData;)V", "value", "combined", "getCombined", "()Lcom/faceunity/core/entity/FUBundleData;", "setCombined", "currentFilterScale", "", "getCurrentFilterScale", "()D", "setCurrentFilterScale", "(D)V", "filterIntensity", "getFilterIntensity", "setFilterIntensity", "mMakeupController", "Lcom/faceunity/core/controller/makeup/MakeupController;", "getMMakeupController", "()Lcom/faceunity/core/controller/makeup/MakeupController;", "mMakeupController$delegate", "Lkotlin/Lazy;", "", "machineLevel", "getMachineLevel", "()Z", "setMachineLevel", "(Z)V", "makeupIntensity", "getMakeupIntensity", "setMakeupIntensity", "applyAddProp", "", "bundle", "buildParams", "Ljava/util/LinkedHashMap;", "", "", "Lkotlin/collections/LinkedHashMap;", "getCombinedConfig", "getModelController", "setCombinedConfig", "updateMakeupBundle", "key", "fu_core_all_featureRelease"}, m1294k = 1, m1295mv = {1, 1, 16})
public class SimpleMakeup extends BaseSingleModel {
    private FUBundleData combined;
    private double currentFilterScale;
    private double filterIntensity;

    private final Lazy mMakeupController;
    private boolean machineLevel;
    private double makeupIntensity;

    protected final MakeupController getMMakeupController() {
        return (MakeupController) this.mMakeupController.getValue();
    }

    public SimpleMakeup(FUBundleData controlBundle) {
        super(controlBundle);
        Intrinsics.checkParameterIsNotNull(controlBundle, "controlBundle");
        this.mMakeupController = LazyKt.lazy(new Function0<MakeupController>() {
            @Override
            public final MakeupController invoke() {
                return FURenderBridge.INSTANCE.getInstance$fu_core_all_featureRelease().getMMakeupController$fu_core_all_featureRelease();
            }
        });
        this.makeupIntensity = 1.0d;
        this.currentFilterScale = 1.0d;
    }

    @Override
    public MakeupController getModelController() {
        return getMMakeupController();
    }

    protected final FUBundleData getCombined() {
        return this.combined;
    }

    protected final void setCombined(FUBundleData fUBundleData) {
        this.combined = fUBundleData;
        updateMakeupBundle(MakeupParam.COMBINATION, fUBundleData);
    }

    public final double getMakeupIntensity() {
        return this.makeupIntensity;
    }

    public final void setMakeupIntensity(double d) {
        this.makeupIntensity = d;
        updateAttributesBackground("makeup_intensity", Double.valueOf(d));
    }

    public final double getFilterIntensity() {
        return this.filterIntensity;
    }

    public final void setFilterIntensity(double d) {
        double d2 = d * this.currentFilterScale;
        this.filterIntensity = d2;
        updateAttributes("filter_level", Double.valueOf(d2));
    }

    public final boolean getMachineLevel() {
        return this.machineLevel;
    }

    public final void setMachineLevel(boolean z) {
        this.machineLevel = z;
        updateAttributesBackground(MakeupParam.MAKEUP_MACHINE_LEVEL, Double.valueOf(z ? 1.0d : 0.0d));
    }

    public final double getCurrentFilterScale() {
        return this.currentFilterScale;
    }

    public final void setCurrentFilterScale(double d) {
        this.currentFilterScale = d;
    }

    public void setCombinedConfig(FUBundleData controlBundle) {
        setCombined(controlBundle);
    }

    public final FUBundleData getCombinedConfig() {
        return this.combined;
    }

    protected final void updateMakeupBundle(final String key, final FUBundleData bundle) {
        Intrinsics.checkParameterIsNotNull(key, "key");
        updateCustomUnit(key, new Function0<Unit>() {
            {
                super(0);
            }

            @Override
            public Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            public final void invoke2() {
                SimpleMakeup.this.getMMakeupController().updateItemBundle$fu_core_all_featureRelease(SimpleMakeup.this.getMSign(), key, bundle);
            }
        });
    }

    private final void applyAddProp(FUBundleData bundle) {
        getMMakeupController().applyAddProp(bundle);
    }

    @Override
    protected LinkedHashMap<String, Object> buildParams() {
        LinkedHashMap<String, Object> linkedHashMap = new LinkedHashMap<>();
        FUBundleData fUBundleData = this.combined;
        if (fUBundleData != null) {
            linkedHashMap.put(MakeupParam.COMBINATION, fUBundleData);
        }
        LinkedHashMap<String, Object> linkedHashMap2 = linkedHashMap;
        linkedHashMap2.put("makeup_intensity", Double.valueOf(this.makeupIntensity));
        linkedHashMap2.put("filter_level", Double.valueOf(this.filterIntensity));
        linkedHashMap2.put(MakeupParam.MAKEUP_MACHINE_LEVEL, Double.valueOf(this.machineLevel ? 1.0d : 0.0d));
        return linkedHashMap;
    }
}
