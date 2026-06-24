package com.faceunity.core.model;

import com.faceunity.core.callback.OnControllerBundleLoadCallback;
import com.faceunity.core.controller.BaseSingleController;
import com.faceunity.core.entity.FUBundleData;
import com.faceunity.core.entity.FUFeaturesData;
import com.tom_roush.fontbox.ttf.NamingTable;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

@Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u0000m\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\b\u0003\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\u0003\n\u0002\u0010\u0013\n\u0000\n\u0002\u0010\u0014\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\r*\u0001\u0010\b&\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0006\u0010\u0019\u001a\u00020\u0018J\r\u0010\u001a\u001a\u00020\u001bH\u0010¢\u0006\u0002\b\u001cJ$\u0010\u001d\u001a\u001e\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u00020\u00010\u001ej\u000e\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u00020\u0001`\u001fH$J\u0006\u0010 \u001a\u00020\u0018J\u0017\u0010!\u001a\u0004\u0018\u00010\"2\u0006\u0010#\u001a\u00020\u0016H\u0004¢\u0006\u0002\u0010$J\u0012\u0010%\u001a\u0004\u0018\u00010&2\u0006\u0010#\u001a\u00020\u0016H\u0004J\u0012\u0010'\u001a\u0004\u0018\u00010(2\u0006\u0010#\u001a\u00020\u0016H\u0004J\u0012\u0010)\u001a\u0004\u0018\u00010\u00162\u0006\u0010#\u001a\u00020\u0016H\u0004J\r\u0010*\u001a\u00020\u0013H\u0000¢\u0006\u0002\b+J\b\u0010,\u001a\u00020-H$J\r\u0010.\u001a\u00020\u0018H\u0000¢\u0006\u0002\b/J\u0016\u00100\u001a\u00020\u00182\u0006\u0010#\u001a\u00020\u00162\u0006\u0010\u0007\u001a\u00020\u0001J\u0018\u00101\u001a\u00020\u00182\u0006\u0010#\u001a\u00020\u00162\u0006\u0010\u0007\u001a\u00020\u0001H\u0004J4\u00101\u001a\u00020\u00182\u0006\u0010#\u001a\u00020\u00162\"\u00102\u001a\u001e\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u00020\u00010\u001ej\u000e\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u00020\u0001`\u001fH\u0004J\u0018\u00103\u001a\u00020\u00182\u0006\u0010#\u001a\u00020\u00162\u0006\u0010\u0007\u001a\u00020\u0001H\u0004J4\u00103\u001a\u00020\u00182\u0006\u0010#\u001a\u00020\u00162\"\u00102\u001a\u001e\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u00020\u00010\u001ej\u000e\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u00020\u0001`\u001fH\u0004J\u0018\u00104\u001a\u00020\u00182\u0006\u0010#\u001a\u00020\u00162\u0006\u0010\u0007\u001a\u00020\u0001H\u0004J\u001e\u00105\u001a\u00020\u00182\u0006\u0010#\u001a\u00020\u00162\f\u00106\u001a\b\u0012\u0004\u0012\u00020\u00180\u0017H\u0004J\u001a\u00107\u001a\u00020\u00182\u0006\u00108\u001a\u00020\u00162\b\u00109\u001a\u0004\u0018\u00010\u0016H\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R$\u0010\t\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\b@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR\u000e\u0010\u000e\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000f\u001a\u00020\u0010X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0011R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u000e¢\u0006\u0002\n\u0000R \u0010\u0014\u001a\u0014\u0012\u0004\u0012\u00020\u0016\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00180\u00170\u0015X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006:"}, m1293d2 = {"Lcom/faceunity/core/model/BaseSingleModel;", "", "controlBundle", "Lcom/faceunity/core/entity/FUBundleData;", "(Lcom/faceunity/core/entity/FUBundleData;)V", "getControlBundle", "()Lcom/faceunity/core/entity/FUBundleData;", "value", "", "enable", "getEnable", "()Z", "setEnable", "(Z)V", "isControllerBundleLoading", "mLoadCallback", "com/faceunity/core/model/BaseSingleModel$mLoadCallback$1", "Lcom/faceunity/core/model/BaseSingleModel$mLoadCallback$1;", "mSign", "", "modelUnitCache", "Ljava/util/concurrent/ConcurrentHashMap;", "", "Lkotlin/Function0;", "", "beginCacheAction", "buildFUFeaturesData", "Lcom/faceunity/core/entity/FUFeaturesData;", "buildFUFeaturesData$fu_core_all_featureRelease", "buildParams", "Ljava/util/LinkedHashMap;", "Lkotlin/collections/LinkedHashMap;", "doingUnitCache", "getAttributesDouble", "", "key", "(Ljava/lang/String;)Ljava/lang/Double;", "getAttributesDoubleArray", "", "getAttributesFloatArray", "", "getAttributesString", "getCurrentSign", "getCurrentSign$fu_core_all_featureRelease", "getModelController", "Lcom/faceunity/core/controller/BaseSingleController;", "loadToRenderKit", "loadToRenderKit$fu_core_all_featureRelease", "setParam", "updateAttributes", "param", "updateAttributesBackground", "updateAttributesGL", "updateCustomUnit", "unity", "updateItemTex", NamingTable.TAG, "path", "fu_core_all_featureRelease"}, m1294k = 1, m1295mv = {1, 1, 16})
public abstract class BaseSingleModel {
    private final FUBundleData controlBundle;
    private boolean enable;
    private volatile boolean isControllerBundleLoading;
    private final BaseSingleModel$mLoadCallback$1 mLoadCallback;
    private long mSign;
    private final ConcurrentHashMap<String, Function0<Unit>> modelUnitCache;

    protected abstract LinkedHashMap<String, Object> buildParams();

    protected abstract BaseSingleController getModelController();

    public BaseSingleModel(FUBundleData controlBundle) {
        Intrinsics.checkParameterIsNotNull(controlBundle, "controlBundle");
        this.controlBundle = controlBundle;
        this.mSign = -1L;
        this.modelUnitCache = new ConcurrentHashMap<>();
        this.mLoadCallback = new OnControllerBundleLoadCallback() {
            @Override
            public void onLoadSuccess(long sign) {
                this.this$0.mSign = sign;
                for (Object obj : this.this$0.modelUnitCache.entrySet()) {
                    Intrinsics.checkExpressionValueIsNotNull(obj, "iterator.next()");
                    Map.Entry entry = (Map.Entry) obj;
                    this.this$0.modelUnitCache.remove(entry.getKey());
                    ((Function0) entry.getValue()).invoke();
                }
                this.this$0.isControllerBundleLoading = false;
            }
        };
        this.enable = true;
    }

    public final FUBundleData getControlBundle() {
        return this.controlBundle;
    }

    public FUFeaturesData buildFUFeaturesData$fu_core_all_featureRelease() {
        return new FUFeaturesData(this.controlBundle, buildParams(), this.enable, null, 0L, 24, null);
    }

    public final void loadToRenderKit$fu_core_all_featureRelease() {
        this.isControllerBundleLoading = true;
        getModelController().loadControllerBundle$fu_core_all_featureRelease(buildFUFeaturesData$fu_core_all_featureRelease(), this.mLoadCallback);
    }

    public final long getMSign() {
        return this.mSign;
    }

    public final boolean getEnable() {
        return this.enable;
    }

    public final void setEnable(boolean z) {
        if (z == this.enable) {
            return;
        }
        this.enable = z;
        if (this.isControllerBundleLoading) {
            this.modelUnitCache.put("enable", new Function0<Unit>() {
                {
                    super(0);
                }

                @Override
                public Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }

                public final void invoke2() {
                    this.this$0.getModelController().setBundleEnable$fu_core_all_featureRelease(this.this$0.getMSign(), this.this$0.enable);
                }
            });
        } else {
            getModelController().setBundleEnable$fu_core_all_featureRelease(getMSign(), this.enable);
        }
    }

    public final void setParam(String key, Object value) {
        Intrinsics.checkParameterIsNotNull(key, "key");
        Intrinsics.checkParameterIsNotNull(value, "value");
        updateAttributes(key, value);
    }

    protected final Double getAttributesDouble(String key) {
        Intrinsics.checkParameterIsNotNull(key, "key");
        return getModelController().getItemParamDouble$fu_core_all_featureRelease(getMSign(), key);
    }

    protected final double[] getAttributesDoubleArray(String key) {
        Intrinsics.checkParameterIsNotNull(key, "key");
        return getModelController().getItemParamDoubleArray$fu_core_all_featureRelease(getMSign(), key);
    }

    protected final String getAttributesString(String key) {
        Intrinsics.checkParameterIsNotNull(key, "key");
        return getModelController().getItemParamString$fu_core_all_featureRelease(getMSign(), key);
    }

    protected final float[] getAttributesFloatArray(String key) {
        Intrinsics.checkParameterIsNotNull(key, "key");
        return getModelController().getItemParamFloatArray$fu_core_all_featureRelease(getMSign(), key);
    }

    protected final void updateAttributes(final String key, final Object value) {
        Intrinsics.checkParameterIsNotNull(key, "key");
        Intrinsics.checkParameterIsNotNull(value, "value");
        if (this.isControllerBundleLoading) {
            this.modelUnitCache.put(key, new Function0<Unit>() {
                {
                    super(0);
                }

                @Override
                public Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }

                public final void invoke2() {
                    BaseSingleModel.this.getModelController().setItemParam$fu_core_all_featureRelease(BaseSingleModel.this.getMSign(), key, value);
                }
            });
        } else {
            getModelController().setItemParam$fu_core_all_featureRelease(getMSign(), key, value);
        }
    }

    protected final void updateAttributesGL(final String key, final Object value) {
        Intrinsics.checkParameterIsNotNull(key, "key");
        Intrinsics.checkParameterIsNotNull(value, "value");
        if (this.isControllerBundleLoading) {
            this.modelUnitCache.put(key, new Function0<Unit>() {
                {
                    super(0);
                }

                @Override
                public Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }

                public final void invoke2() {
                    BaseSingleModel.this.getModelController().setItemParamGL$fu_core_all_featureRelease(BaseSingleModel.this.getMSign(), key, value);
                }
            });
        } else {
            getModelController().setItemParamGL$fu_core_all_featureRelease(getMSign(), key, value);
        }
    }

    protected final void updateAttributesBackground(final String key, final Object value) {
        Intrinsics.checkParameterIsNotNull(key, "key");
        Intrinsics.checkParameterIsNotNull(value, "value");
        if (this.isControllerBundleLoading) {
            this.modelUnitCache.put(key, new Function0<Unit>() {
                {
                    super(0);
                }

                @Override
                public Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }

                public final void invoke2() {
                    BaseSingleModel.this.getModelController().setItemParamBackground$fu_core_all_featureRelease(BaseSingleModel.this.getMSign(), key, value);
                }
            });
        } else {
            getModelController().setItemParamBackground$fu_core_all_featureRelease(getMSign(), key, value);
        }
    }

    protected final void updateAttributesBackground(String key, final LinkedHashMap<String, Object> param) {
        Intrinsics.checkParameterIsNotNull(key, "key");
        Intrinsics.checkParameterIsNotNull(param, "param");
        if (this.isControllerBundleLoading) {
            this.modelUnitCache.put(key, new Function0<Unit>() {
                {
                    super(0);
                }

                @Override
                public Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }

                public final void invoke2() {
                    BaseSingleModel.this.getModelController().setItemParamBackground$fu_core_all_featureRelease(BaseSingleModel.this.getMSign(), param);
                }
            });
        } else {
            getModelController().setItemParamBackground$fu_core_all_featureRelease(getMSign(), param);
        }
    }

    protected final void updateAttributes(String key, final LinkedHashMap<String, Object> param) {
        Intrinsics.checkParameterIsNotNull(key, "key");
        Intrinsics.checkParameterIsNotNull(param, "param");
        if (this.isControllerBundleLoading) {
            this.modelUnitCache.put(key, new Function0<Unit>() {
                {
                    super(0);
                }

                @Override
                public Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }

                public final void invoke2() {
                    BaseSingleModel.this.getModelController().setItemParam$fu_core_all_featureRelease(BaseSingleModel.this.getMSign(), param);
                }
            });
        } else {
            getModelController().setItemParam$fu_core_all_featureRelease(getMSign(), param);
        }
    }

    protected final void updateItemTex(final String name, final String path) throws IOException {
        Intrinsics.checkParameterIsNotNull(name, "name");
        if (this.isControllerBundleLoading) {
            if (path == null) {
                this.modelUnitCache.put(name, new Function0<Unit>() {
                    {
                        super(0);
                    }

                    @Override
                    public Unit invoke() {
                        invoke2();
                        return Unit.INSTANCE;
                    }

                    public final void invoke2() {
                        BaseSingleModel.this.getModelController().deleteItemTex$fu_core_all_featureRelease(BaseSingleModel.this.getMSign(), name);
                    }
                });
                return;
            } else {
                this.modelUnitCache.put(name, new Function0<Unit>() {
                    {
                        super(0);
                    }

                    @Override
                    public Unit invoke() throws IOException {
                        invoke2();
                        return Unit.INSTANCE;
                    }

                    public final void invoke2() throws IOException {
                        BaseSingleModel.this.getModelController().createItemTex$fu_core_all_featureRelease(BaseSingleModel.this.getMSign(), name, path);
                    }
                });
                return;
            }
        }
        if (path == null) {
            getModelController().deleteItemTex$fu_core_all_featureRelease(getMSign(), name);
        } else {
            getModelController().createItemTex$fu_core_all_featureRelease(getMSign(), name, path);
        }
    }

    protected final void updateCustomUnit(String key, Function0<Unit> unity) {
        Intrinsics.checkParameterIsNotNull(key, "key");
        Intrinsics.checkParameterIsNotNull(unity, "unity");
        if (this.isControllerBundleLoading) {
            this.modelUnitCache.put(key, unity);
        } else {
            unity.invoke();
        }
    }

    public final void beginCacheAction() {
        this.isControllerBundleLoading = true;
    }

    public final void doingUnitCache() {
        for (Map.Entry<String, Function0<Unit>> entry : this.modelUnitCache.entrySet()) {
            Intrinsics.checkExpressionValueIsNotNull(entry, "iterator.next()");
            Map.Entry<String, Function0<Unit>> entry2 = entry;
            this.modelUnitCache.remove(entry2.getKey());
            entry2.getValue().invoke();
        }
        this.isControllerBundleLoading = false;
    }
}
