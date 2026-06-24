package com.faceunity.core.avatar.scene;

import com.faceunity.core.avatar.base.BaseSceneAttribute;
import com.faceunity.core.avatar.control.AvatarController;
import java.util.LinkedHashMap;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

@Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010\u0007\n\u0002\b\u0010\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J=\u0010\u001b\u001a\u00020\u001c2.\u0010\u001d\u001a*\u0012\u0004\u0012\u00020\u001f\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001c0 0\u001ej\u0014\u0012\u0004\u0012\u00020\u001f\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001c0 `!H\u0000¢\u0006\u0002\b\"R*\u0010\u0005\u001a\u0004\u0018\u00010\u00042\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004@FX\u0086\u000e¢\u0006\u0010\n\u0002\u0010\n\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR*\u0010\f\u001a\u0004\u0018\u00010\u000b2\b\u0010\u0003\u001a\u0004\u0018\u00010\u000b@FX\u0086\u000e¢\u0006\u0010\n\u0002\u0010\u0011\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R*\u0010\u0012\u001a\u0004\u0018\u00010\u000b2\b\u0010\u0003\u001a\u0004\u0018\u00010\u000b@FX\u0086\u000e¢\u0006\u0010\n\u0002\u0010\u0011\u001a\u0004\b\u0013\u0010\u000e\"\u0004\b\u0014\u0010\u0010R*\u0010\u0015\u001a\u0004\u0018\u00010\u000b2\b\u0010\u0003\u001a\u0004\u0018\u00010\u000b@FX\u0086\u000e¢\u0006\u0010\n\u0002\u0010\u0011\u001a\u0004\b\u0016\u0010\u000e\"\u0004\b\u0017\u0010\u0010R*\u0010\u0018\u001a\u0004\u0018\u00010\u000b2\b\u0010\u0003\u001a\u0004\u0018\u00010\u000b@FX\u0086\u000e¢\u0006\u0010\n\u0002\u0010\u0011\u001a\u0004\b\u0019\u0010\u000e\"\u0004\b\u001a\u0010\u0010¨\u0006#"}, m1293d2 = {"Lcom/faceunity/core/avatar/scene/Camera;", "Lcom/faceunity/core/avatar/base/BaseSceneAttribute;", "()V", "value", "", "enableRenderCamera", "getEnableRenderCamera", "()Ljava/lang/Boolean;", "setEnableRenderCamera", "(Ljava/lang/Boolean;)V", "Ljava/lang/Boolean;", "", "renderFov", "getRenderFov", "()Ljava/lang/Float;", "setRenderFov", "(Ljava/lang/Float;)V", "Ljava/lang/Float;", "renderOrthSize", "getRenderOrthSize", "setRenderOrthSize", "zfar", "getZfar", "setZfar", "znear", "getZnear", "setZnear", "loadParams", "", "params", "Ljava/util/LinkedHashMap;", "", "Lkotlin/Function0;", "Lkotlin/collections/LinkedHashMap;", "loadParams$fu_core_all_featureRelease", "fu_core_all_featureRelease"}, m1294k = 1, m1295mv = {1, 1, 16})
public final class Camera extends BaseSceneAttribute {
    private Boolean enableRenderCamera;
    private Float renderFov;
    private Float renderOrthSize;
    private Float zfar;
    private Float znear;

    public final Boolean getEnableRenderCamera() {
        return this.enableRenderCamera;
    }

    public final void setEnableRenderCamera(Boolean bool) {
        if (bool != null) {
            boolean zBooleanValue = bool.booleanValue();
            if (getHasLoaded()) {
                AvatarController.enableRenderCamera$default(getMAvatarController$fu_core_all_featureRelease(), getSceneId(), zBooleanValue, false, 4, null);
            }
        }
        this.enableRenderCamera = bool;
    }

    public final Float getRenderFov() {
        return this.renderFov;
    }

    public final void setRenderFov(Float f) {
        if (f != null) {
            float fFloatValue = f.floatValue();
            if (getHasLoaded()) {
                AvatarController.setProjectionMatrixFov$default(getMAvatarController$fu_core_all_featureRelease(), getSceneId(), fFloatValue, false, 4, null);
            }
        }
        this.renderFov = f;
    }

    public final Float getRenderOrthSize() {
        return this.renderOrthSize;
    }

    public final void setRenderOrthSize(Float f) {
        if (f != null) {
            float fFloatValue = f.floatValue();
            if (getHasLoaded()) {
                AvatarController.setProjectionMatrixOrthoSize$default(getMAvatarController$fu_core_all_featureRelease(), getSceneId(), fFloatValue, false, 4, null);
            }
        }
        this.renderOrthSize = f;
    }

    public final Float getZnear() {
        return this.znear;
    }

    public final void setZnear(Float f) {
        if (f != null) {
            float fFloatValue = f.floatValue();
            if (getHasLoaded()) {
                AvatarController.setProjectionMatrixZnear$default(getMAvatarController$fu_core_all_featureRelease(), getSceneId(), fFloatValue, false, 4, null);
            }
        }
        this.znear = f;
    }

    public final Float getZfar() {
        return this.zfar;
    }

    public final void setZfar(Float f) {
        if (f != null) {
            float fFloatValue = f.floatValue();
            if (getHasLoaded()) {
                AvatarController.setProjectionMatrixZfar$default(getMAvatarController$fu_core_all_featureRelease(), getSceneId(), fFloatValue, false, 4, null);
            }
        }
        this.zfar = f;
    }

    public final void loadParams$fu_core_all_featureRelease(final LinkedHashMap<String, Function0<Unit>> params) {
        Intrinsics.checkParameterIsNotNull(params, "params");
        Boolean bool = this.enableRenderCamera;
        if (bool != null) {
            final boolean zBooleanValue = bool.booleanValue();
            params.put("enableRenderCamera", new Function0<Unit>() {
                {
                    super(0);
                }

                @Override
                public Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }

                public final void invoke2() {
                    this.getMAvatarController$fu_core_all_featureRelease().enableRenderCamera(this.getSceneId(), zBooleanValue, false);
                }
            });
        }
        Float f = this.renderFov;
        if (f != null) {
            final float fFloatValue = f.floatValue();
            params.put("setProjectionMatrixFov", new Function0<Unit>() {
                {
                    super(0);
                }

                @Override
                public Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }

                public final void invoke2() {
                    this.getMAvatarController$fu_core_all_featureRelease().setProjectionMatrixFov(this.getSceneId(), fFloatValue, false);
                }
            });
        }
        Float f2 = this.renderOrthSize;
        if (f2 != null) {
            final float fFloatValue2 = f2.floatValue();
            params.put("setProjectionMatrixFov", new Function0<Unit>() {
                {
                    super(0);
                }

                @Override
                public Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }

                public final void invoke2() {
                    this.getMAvatarController$fu_core_all_featureRelease().setProjectionMatrixOrthoSize(this.getSceneId(), fFloatValue2, false);
                }
            });
        }
        Float f3 = this.znear;
        if (f3 != null) {
            final float fFloatValue3 = f3.floatValue();
            params.put("setProjectionMatrixFov", new Function0<Unit>() {
                {
                    super(0);
                }

                @Override
                public Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }

                public final void invoke2() {
                    this.getMAvatarController$fu_core_all_featureRelease().setProjectionMatrixZnear(this.getSceneId(), fFloatValue3, false);
                }
            });
        }
        Float f4 = this.zfar;
        if (f4 != null) {
            final float fFloatValue4 = f4.floatValue();
            params.put("setProjectionMatrixFov", new Function0<Unit>() {
                {
                    super(0);
                }

                @Override
                public Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }

                public final void invoke2() {
                    this.getMAvatarController$fu_core_all_featureRelease().setProjectionMatrixZfar(this.getSceneId(), fFloatValue4, false);
                }
            });
        }
        setHasLoaded(true);
    }
}
