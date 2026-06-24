package com.faceunity.core.avatar.avatar;

import com.faceunity.core.avatar.base.BaseAvatarAttribute;
import com.faceunity.core.avatar.control.AvatarController;
import com.faceunity.core.avatar.model.Avatar;
import com.faceunity.core.entity.FUBundleData;
import com.faceunity.core.entity.FUColorRGBData;
import com.faceunity.core.utils.FULogger;
import com.tom_roush.fontbox.ttf.NamingTable;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

@Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\n\u0018\u0000 \"2\u00020\u0001:\u0001\"B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u000e\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0000Jm\u0010\u0015\u001a\u00020\u00132.\u0010\u0016\u001a*\u0012\u0004\u0012\u00020\u0007\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00130\u00180\u0017j\u0014\u0012\u0004\u0012\u00020\u0007\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00130\u0018`\u00192.\u0010\u001a\u001a*\u0012\u0004\u0012\u00020\u0007\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00130\u00180\u0017j\u0014\u0012\u0004\u0012\u00020\u0007\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00130\u0018`\u0019H\u0000¢\u0006\u0002\b\u001bJ\u0016\u0010\u001c\u001a\u00020\u00132\u0006\u0010\u001d\u001a\u00020\u00072\u0006\u0010\u0014\u001a\u00020\bJ\u0016\u0010\u001e\u001a\u00020\u00132\u0006\u0010\u001d\u001a\u00020\u00072\u0006\u0010\u001f\u001a\u00020\rJ\u0016\u0010 \u001a\u00020\u00132\u0006\u0010\u001d\u001a\u00020\u00072\u0006\u0010\u0014\u001a\u00020\bJ\u0016\u0010!\u001a\u00020\u00132\u0006\u0010\u001d\u001a\u00020\u00072\u0006\u0010\u0014\u001a\u00020\bR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R-\u0010\u0005\u001a\u001e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u0006j\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b`\t¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR-\u0010\f\u001a\u001e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\r0\u0006j\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\r`\t¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000bR-\u0010\u000f\u001a\u001e\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\b0\u0006j\u000e\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\b`\t¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u000b¨\u0006#"}, m1293d2 = {"Lcom/faceunity/core/avatar/avatar/Color;", "Lcom/faceunity/core/avatar/base/BaseAvatarAttribute;", "avatar", "Lcom/faceunity/core/avatar/model/Avatar;", "(Lcom/faceunity/core/avatar/model/Avatar;)V", "colorCache", "Ljava/util/HashMap;", "", "Lcom/faceunity/core/entity/FUColorRGBData;", "Lkotlin/collections/HashMap;", "getColorCache", "()Ljava/util/HashMap;", "colorIntensityCache", "", "getColorIntensityCache", "componentColorCache", "Lcom/faceunity/core/entity/FUBundleData;", "getComponentColorCache", "clone", "", "color", "loadParams", "params", "Ljava/util/LinkedHashMap;", "Lkotlin/Function0;", "Lkotlin/collections/LinkedHashMap;", "initParams", "loadParams$fu_core_all_featureRelease", "setColor", NamingTable.TAG, "setColorIntensity", "level", "setComponentColorByName", "setComponentColorByNameGL", "Companion", "fu_core_all_featureRelease"}, m1294k = 1, m1295mv = {1, 1, 16})
public final class Color extends BaseAvatarAttribute {
    public static final String Beard = "beard_color";
    public static final String Eyebrow = "eyebrow_color";
    public static final String Glass = "glass_color";
    public static final String GlassFrame = "glass_frame_color";
    public static final String Hair = "hair_color";
    public static final String Hat = "hat_color";
    public static final String Iris = "iris_color";
    public static final String Skin = "skin_color";
    private final Avatar avatar;
    private final HashMap<String, FUColorRGBData> colorCache;
    private final HashMap<String, Float> colorIntensityCache;
    private final HashMap<FUBundleData, FUColorRGBData> componentColorCache;

    public Color(Avatar avatar) {
        Intrinsics.checkParameterIsNotNull(avatar, "avatar");
        this.avatar = avatar;
        this.colorCache = new HashMap<>();
        this.colorIntensityCache = new HashMap<>();
        this.componentColorCache = new HashMap<>();
    }

    public final HashMap<String, FUColorRGBData> getColorCache() {
        return this.colorCache;
    }

    public final HashMap<String, Float> getColorIntensityCache() {
        return this.colorIntensityCache;
    }

    public final HashMap<FUBundleData, FUColorRGBData> getComponentColorCache() {
        return this.componentColorCache;
    }

    public final void setColor(String name, FUColorRGBData color) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        Intrinsics.checkParameterIsNotNull(color, "color");
        this.colorCache.put(name, color);
        AvatarController.setInstanceColor$default(getMAvatarController$fu_core_all_featureRelease(), getAvatarId(), name, color, false, 8, null);
    }

    public final void setColorIntensity(String name, float level) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        this.colorIntensityCache.put(name, Float.valueOf(level));
        AvatarController.setInstanceColorIntensity$default(getMAvatarController$fu_core_all_featureRelease(), getAvatarId(), name, level, false, 8, null);
    }

    public final void setComponentColorByName(String name, FUColorRGBData color) {
        FUBundleData fUBundleData;
        Intrinsics.checkParameterIsNotNull(name, "name");
        Intrinsics.checkParameterIsNotNull(color, "color");
        fUBundleData = null;
        loop0: while (true) {
            fUBundleData = fUBundleData;
            for (FUBundleData fUBundleData2 : this.avatar.components) {
                if (Intrinsics.areEqual(fUBundleData2.getName(), name)) {
                    break;
                }
            }
        }
        if (fUBundleData != null) {
            this.componentColorCache.put(fUBundleData, color);
            AvatarController.fuSetInstanceFaceBeautyColor$default(getMAvatarController$fu_core_all_featureRelease(), getAvatarId(), fUBundleData, color, false, 8, null);
        } else {
            FULogger.m294e("KIT-Avatar-Color", "has not loaded component which name is " + name);
        }
    }

    public final void setComponentColorByNameGL(String name, FUColorRGBData color) {
        FUBundleData fUBundleData;
        Intrinsics.checkParameterIsNotNull(name, "name");
        Intrinsics.checkParameterIsNotNull(color, "color");
        fUBundleData = null;
        loop0: while (true) {
            fUBundleData = fUBundleData;
            for (FUBundleData fUBundleData2 : this.avatar.components) {
                if (Intrinsics.areEqual(fUBundleData2.getName(), name)) {
                    break;
                }
            }
        }
        if (fUBundleData != null) {
            this.componentColorCache.put(fUBundleData, color);
            getMAvatarController$fu_core_all_featureRelease().fuSetInstanceFaceBeautyColor(getAvatarId(), fUBundleData, color, false);
        } else {
            FULogger.m294e("KIT-Avatar-Color", "has not loaded component which name is " + name);
        }
    }

    public final void loadParams$fu_core_all_featureRelease(LinkedHashMap<String, Function0<Unit>> params, LinkedHashMap<String, Function0<Unit>> initParams) {
        Intrinsics.checkParameterIsNotNull(params, "params");
        Intrinsics.checkParameterIsNotNull(initParams, "initParams");
        if (this.colorCache.size() > 0) {
            initParams.put("setInstanceColor", new Function0<Unit>() {
                {
                    super(0);
                }

                @Override
                public Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }

                public final void invoke2() {
                    for (Map.Entry<String, FUColorRGBData> entry : this.this$0.getColorCache().entrySet()) {
                        this.this$0.getMAvatarController$fu_core_all_featureRelease().setInstanceColor(this.this$0.getAvatarId(), entry.getKey(), entry.getValue(), false);
                    }
                }
            });
        }
        if (this.colorIntensityCache.size() > 0) {
            initParams.put("setInstanceColorIntensity", new Function0<Unit>() {
                {
                    super(0);
                }

                @Override
                public Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }

                public final void invoke2() {
                    for (Map.Entry<String, Float> entry : this.this$0.getColorIntensityCache().entrySet()) {
                        this.this$0.getMAvatarController$fu_core_all_featureRelease().setInstanceColorIntensity(this.this$0.getAvatarId(), entry.getKey(), entry.getValue().floatValue(), false);
                    }
                }
            });
        }
        if (this.componentColorCache.size() > 0) {
            params.put("fuSetInstanceFaceBeautyColor", new Function0<Unit>() {
                {
                    super(0);
                }

                @Override
                public Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }

                public final void invoke2() {
                    for (Map.Entry<FUBundleData, FUColorRGBData> entry : this.this$0.getComponentColorCache().entrySet()) {
                        this.this$0.getMAvatarController$fu_core_all_featureRelease().fuSetInstanceFaceBeautyColor(this.this$0.getAvatarId(), entry.getKey(), entry.getValue(), false);
                    }
                }
            });
        }
        setHasLoaded(true);
    }

    public final void clone(Color color) {
        Intrinsics.checkParameterIsNotNull(color, "color");
        for (Map.Entry<String, FUColorRGBData> entry : color.colorCache.entrySet()) {
            String key = entry.getKey();
            FUColorRGBData value = entry.getValue();
            this.colorCache.put(key, new FUColorRGBData(value.getRed(), value.getGreen(), value.getBlue(), value.getAlpha()));
        }
        for (Map.Entry<String, Float> entry2 : color.colorIntensityCache.entrySet()) {
            this.colorIntensityCache.put(entry2.getKey(), Float.valueOf(entry2.getValue().floatValue()));
        }
        for (Map.Entry<FUBundleData, FUColorRGBData> entry3 : color.componentColorCache.entrySet()) {
            this.componentColorCache.put(entry3.getKey().clone(), entry3.getValue().clone());
        }
    }
}
