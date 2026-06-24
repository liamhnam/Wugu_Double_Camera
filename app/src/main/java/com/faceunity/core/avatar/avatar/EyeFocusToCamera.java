package com.faceunity.core.avatar.avatar;

import com.faceunity.core.avatar.base.BaseAvatarAttribute;
import com.faceunity.core.avatar.control.AvatarController;
import java.util.LinkedHashMap;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

@Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u000e\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u0000J=\u0010\u000e\u001a\u00020\f2.\u0010\u000f\u001a*\u0012\u0004\u0012\u00020\u0011\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u00120\u0010j\u0014\u0012\u0004\u0012\u00020\u0011\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u0012`\u0013H\u0000¢\u0006\u0002\b\u0014R*\u0010\u0005\u001a\u0004\u0018\u00010\u00042\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004@FX\u0086\u000e¢\u0006\u0010\n\u0002\u0010\n\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\t¨\u0006\u0015"}, m1293d2 = {"Lcom/faceunity/core/avatar/avatar/EyeFocusToCamera;", "Lcom/faceunity/core/avatar/base/BaseAvatarAttribute;", "()V", "value", "", "enableEyeFocusToCamera", "getEnableEyeFocusToCamera", "()Ljava/lang/Boolean;", "setEnableEyeFocusToCamera", "(Ljava/lang/Boolean;)V", "Ljava/lang/Boolean;", "clone", "", "eyeFocusToCamera", "loadParams", "params", "Ljava/util/LinkedHashMap;", "", "Lkotlin/Function0;", "Lkotlin/collections/LinkedHashMap;", "loadParams$fu_core_all_featureRelease", "fu_core_all_featureRelease"}, m1294k = 1, m1295mv = {1, 1, 16})
public final class EyeFocusToCamera extends BaseAvatarAttribute {
    private Boolean enableEyeFocusToCamera;

    public final Boolean getEnableEyeFocusToCamera() {
        return this.enableEyeFocusToCamera;
    }

    public final void setEnableEyeFocusToCamera(Boolean bool) {
        this.enableEyeFocusToCamera = bool;
        if (bool != null) {
            boolean zBooleanValue = bool.booleanValue();
            if (getHasLoaded()) {
                AvatarController.enableInstanceFocusEyeToCamera$default(getMAvatarController$fu_core_all_featureRelease(), getAvatarId(), zBooleanValue, false, 4, null);
            }
        }
    }

    public final void loadParams$fu_core_all_featureRelease(final LinkedHashMap<String, Function0<Unit>> params) {
        Intrinsics.checkParameterIsNotNull(params, "params");
        Boolean bool = this.enableEyeFocusToCamera;
        if (bool != null) {
            final boolean zBooleanValue = bool.booleanValue();
            params.put("enableInstanceFocusEyeToCamera", new Function0<Unit>() {
                {
                    super(0);
                }

                @Override
                public Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }

                public final void invoke2() {
                    this.getMAvatarController$fu_core_all_featureRelease().enableInstanceFocusEyeToCamera(this.getAvatarId(), zBooleanValue, false);
                }
            });
        }
        setHasLoaded(true);
    }

    public final void clone(EyeFocusToCamera eyeFocusToCamera) {
        Intrinsics.checkParameterIsNotNull(eyeFocusToCamera, "eyeFocusToCamera");
        setEnableEyeFocusToCamera(eyeFocusToCamera.enableEyeFocusToCamera);
    }
}
