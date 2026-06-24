package com.faceunity.core.avatar.avatar;

import com.faceunity.core.avatar.base.BaseAvatarAttribute;
import com.faceunity.core.avatar.control.AvatarController;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

@Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0010\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u000e\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u0000J=\u0010\r\u001a\u00020\u000b2.\u0010\u000e\u001a*\u0012\u0004\u0012\u00020\u0005\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\u00100\u000fj\u0014\u0012\u0004\u0012\u00020\u0005\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\u0010`\u0011H\u0000¢\u0006\u0002\b\u0012J\u0016\u0010\u0013\u001a\u00020\u000b2\u0006\u0010\u0014\u001a\u00020\u00052\u0006\u0010\u0015\u001a\u00020\u0006J\u0016\u0010\u0016\u001a\u00020\u000b2\u0006\u0010\u0014\u001a\u00020\u00052\u0006\u0010\u0015\u001a\u00020\u0006R-\u0010\u0003\u001a\u001e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004j\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0006`\u0007¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t¨\u0006\u0017"}, m1293d2 = {"Lcom/faceunity/core/avatar/avatar/Deformation;", "Lcom/faceunity/core/avatar/base/BaseAvatarAttribute;", "()V", "deformationCache", "Ljava/util/HashMap;", "", "", "Lkotlin/collections/HashMap;", "getDeformationCache", "()Ljava/util/HashMap;", "clone", "", "deformation", "loadParams", "params", "Ljava/util/LinkedHashMap;", "Lkotlin/Function0;", "Lkotlin/collections/LinkedHashMap;", "loadParams$fu_core_all_featureRelease", "setDeformation", "key", "intensity", "setDeformationGL", "fu_core_all_featureRelease"}, m1294k = 1, m1295mv = {1, 1, 16})
public final class Deformation extends BaseAvatarAttribute {
    private final HashMap<String, Float> deformationCache = new HashMap<>();

    public final HashMap<String, Float> getDeformationCache() {
        return this.deformationCache;
    }

    public final void setDeformation(String key, float intensity) {
        Intrinsics.checkParameterIsNotNull(key, "key");
        this.deformationCache.put(key, Float.valueOf(intensity));
        AvatarController.setInstanceDeformation$default(getMAvatarController$fu_core_all_featureRelease(), getAvatarId(), key, intensity, false, 8, null);
    }

    public final void setDeformationGL(String key, float intensity) {
        Intrinsics.checkParameterIsNotNull(key, "key");
        this.deformationCache.put(key, Float.valueOf(intensity));
        getMAvatarController$fu_core_all_featureRelease().setInstanceDeformation(getAvatarId(), key, intensity, false);
    }

    public final void loadParams$fu_core_all_featureRelease(LinkedHashMap<String, Function0<Unit>> params) {
        Intrinsics.checkParameterIsNotNull(params, "params");
        if (!this.deformationCache.isEmpty()) {
            params.put("setInstanceDeformation", new Function0<Unit>() {
                {
                    super(0);
                }

                @Override
                public Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }

                public final void invoke2() {
                    for (Map.Entry<String, Float> entry : this.this$0.getDeformationCache().entrySet()) {
                        this.this$0.getMAvatarController$fu_core_all_featureRelease().setInstanceDeformation(this.this$0.getAvatarId(), entry.getKey(), entry.getValue().floatValue(), false);
                    }
                }
            });
        }
        setHasLoaded(true);
    }

    public final void clone(Deformation deformation) {
        Intrinsics.checkParameterIsNotNull(deformation, "deformation");
        for (Map.Entry<String, Float> entry : deformation.deformationCache.entrySet()) {
            this.deformationCache.put(entry.getKey(), Float.valueOf(entry.getValue().floatValue()));
        }
    }
}
