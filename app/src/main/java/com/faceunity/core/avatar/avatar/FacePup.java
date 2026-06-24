package com.faceunity.core.avatar.avatar;

import com.faceunity.core.avatar.base.BaseAvatarAttribute;
import com.faceunity.core.avatar.control.AvatarController;
import com.tom_roush.fontbox.ttf.NamingTable;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

@Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0010\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010\u0014\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0000J\u0006\u0010\u0014\u001a\u00020\u0012J\u000e\u0010\u0015\u001a\u00020\u00062\u0006\u0010\u0016\u001a\u00020\u0005J\u0006\u0010\u0017\u001a\u00020\u0018J=\u0010\u0019\u001a\u00020\u00122.\u0010\u001a\u001a*\u0012\u0004\u0012\u00020\u0005\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00120\u001c0\u001bj\u0014\u0012\u0004\u0012\u00020\u0005\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00120\u001c`\u001dH\u0000¢\u0006\u0002\b\u001eJ\u0006\u0010\u001f\u001a\u00020\u0012J\u0016\u0010 \u001a\u00020\u00122\u0006\u0010\u0016\u001a\u00020\u00052\u0006\u0010!\u001a\u00020\u0006J\u0016\u0010\"\u001a\u00020\u00122\u0006\u0010\u0016\u001a\u00020\u00052\u0006\u0010!\u001a\u00020\u0006R-\u0010\u0003\u001a\u001e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004j\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0006`\u0007¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u001e\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0080\u000e¢\u0006\u0010\n\u0002\u0010\u0010\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000f¨\u0006#"}, m1293d2 = {"Lcom/faceunity/core/avatar/avatar/FacePup;", "Lcom/faceunity/core/avatar/base/BaseAvatarAttribute;", "()V", "facePupCache", "Ljava/util/HashMap;", "", "", "Lkotlin/collections/HashMap;", "getFacePupCache", "()Ljava/util/HashMap;", "facePupMode", "", "getFacePupMode$fu_core_all_featureRelease", "()Ljava/lang/Boolean;", "setFacePupMode$fu_core_all_featureRelease", "(Ljava/lang/Boolean;)V", "Ljava/lang/Boolean;", "clone", "", "facePup", "enterFacePupMode", "getFacePupParam", NamingTable.TAG, "getInstanceFaceUpArray", "", "loadParams", "params", "Ljava/util/LinkedHashMap;", "Lkotlin/Function0;", "Lkotlin/collections/LinkedHashMap;", "loadParams$fu_core_all_featureRelease", "quitFacePupMode", "setFacePupParam", "value", "setFacePupParamGL", "fu_core_all_featureRelease"}, m1294k = 1, m1295mv = {1, 1, 16})
public final class FacePup extends BaseAvatarAttribute {
    private final HashMap<String, Float> facePupCache = new HashMap<>();
    private Boolean facePupMode;

    public final HashMap<String, Float> getFacePupCache() {
        return this.facePupCache;
    }

    public final Boolean getFacePupMode() {
        return this.facePupMode;
    }

    public final void setFacePupMode$fu_core_all_featureRelease(Boolean bool) {
        this.facePupMode = bool;
    }

    public final void enterFacePupMode() {
        this.facePupMode = true;
        AvatarController.enableInstanceFaceUpMode$default(getMAvatarController$fu_core_all_featureRelease(), getAvatarId(), true, false, 4, null);
    }

    public final void quitFacePupMode() {
        this.facePupMode = false;
        AvatarController.enableInstanceFaceUpMode$default(getMAvatarController$fu_core_all_featureRelease(), getAvatarId(), false, false, 4, null);
    }

    public final void setFacePupParam(String name, float value) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        this.facePupCache.put(name, Float.valueOf(value));
        AvatarController.setInstanceFaceUp$default(getMAvatarController$fu_core_all_featureRelease(), getAvatarId(), name, value, false, 8, null);
    }

    public final void setFacePupParamGL(String name, float value) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        this.facePupCache.put(name, Float.valueOf(value));
        getMAvatarController$fu_core_all_featureRelease().setInstanceFaceUp(getAvatarId(), name, value, false);
    }

    public final float getFacePupParam(String name) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        return getMAvatarController$fu_core_all_featureRelease().getInstanceFaceUpOriginalValue(getAvatarId(), name);
    }

    public final float[] getInstanceFaceUpArray() {
        float[] fArr = new float[100];
        getMAvatarController$fu_core_all_featureRelease().getInstanceFaceUpArray(getAvatarId(), fArr);
        return fArr;
    }

    public final void loadParams$fu_core_all_featureRelease(final LinkedHashMap<String, Function0<Unit>> params) {
        Intrinsics.checkParameterIsNotNull(params, "params");
        Boolean bool = this.facePupMode;
        if (bool != null) {
            final boolean zBooleanValue = bool.booleanValue();
            params.put("enableInstanceFaceUpMode", new Function0<Unit>() {
                {
                    super(0);
                }

                @Override
                public Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }

                public final void invoke2() {
                    this.getMAvatarController$fu_core_all_featureRelease().enableInstanceFaceUpMode(this.getAvatarId(), zBooleanValue, false);
                }
            });
        }
        if (!this.facePupCache.isEmpty()) {
            params.put("setInstanceFaceUp", new Function0<Unit>() {
                {
                    super(0);
                }

                @Override
                public Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }

                public final void invoke2() {
                    for (Map.Entry<String, Float> entry : this.this$0.getFacePupCache().entrySet()) {
                        this.this$0.getMAvatarController$fu_core_all_featureRelease().setInstanceFaceUp(this.this$0.getAvatarId(), entry.getKey(), entry.getValue().floatValue(), false);
                    }
                }
            });
        }
        setHasLoaded(true);
    }

    public final void clone(FacePup facePup) {
        Intrinsics.checkParameterIsNotNull(facePup, "facePup");
        this.facePupMode = facePup.facePupMode;
        for (Map.Entry<String, Float> entry : facePup.facePupCache.entrySet()) {
            this.facePupCache.put(entry.getKey(), Float.valueOf(entry.getValue().floatValue()));
        }
    }
}
