package com.faceunity.core.avatar.avatar;

import com.faceunity.core.avatar.base.BaseAvatarAttribute;
import com.faceunity.core.avatar.control.AvatarController;
import java.util.LinkedHashMap;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

@Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0013\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u0000J=\u0010\u001a\u001a\u00020\u00182.\u0010\u001b\u001a*\u0012\u0004\u0012\u00020\u001d\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00180\u001e0\u001cj\u0014\u0012\u0004\u0012\u00020\u001d\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00180\u001e`\u001fH\u0000¢\u0006\u0002\b J\u0012\u0010!\u001a\u00020\u00182\b\b\u0002\u0010\"\u001a\u00020\u0004H\u0007J\u0012\u0010#\u001a\u00020\u00182\b\b\u0002\u0010\"\u001a\u00020\u0004H\u0007R*\u0010\u0005\u001a\u0004\u0018\u00010\u00042\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004@FX\u0086\u000e¢\u0006\u0010\n\u0002\u0010\n\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR*\u0010\u000b\u001a\u0004\u0018\u00010\u00042\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004@FX\u0086\u000e¢\u0006\u0010\n\u0002\u0010\n\u001a\u0004\b\f\u0010\u0007\"\u0004\b\r\u0010\tR*\u0010\u000e\u001a\u0004\u0018\u00010\u00042\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004@FX\u0086\u000e¢\u0006\u0010\n\u0002\u0010\n\u001a\u0004\b\u000f\u0010\u0007\"\u0004\b\u0010\u0010\tR*\u0010\u0011\u001a\u0004\u0018\u00010\u00042\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004@FX\u0086\u000e¢\u0006\u0010\n\u0002\u0010\n\u001a\u0004\b\u0012\u0010\u0007\"\u0004\b\u0013\u0010\tR*\u0010\u0014\u001a\u0004\u0018\u00010\u00042\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004@FX\u0086\u000e¢\u0006\u0010\n\u0002\u0010\n\u001a\u0004\b\u0015\u0010\u0007\"\u0004\b\u0016\u0010\t¨\u0006$"}, m1293d2 = {"Lcom/faceunity/core/avatar/avatar/DynamicBone;", "Lcom/faceunity/core/avatar/base/BaseAvatarAttribute;", "()V", "value", "", "enableDynamicBone", "getEnableDynamicBone", "()Ljava/lang/Boolean;", "setEnableDynamicBone", "(Ljava/lang/Boolean;)V", "Ljava/lang/Boolean;", "enableModelMatToBone", "getEnableModelMatToBone", "setEnableModelMatToBone", "enableRootRotateSpeedLimitMode", "getEnableRootRotateSpeedLimitMode", "setEnableRootRotateSpeedLimitMode", "enableRootTranslateSpeedLimitMode", "getEnableRootTranslateSpeedLimitMode", "setEnableRootTranslateSpeedLimitMode", "enableTeleportMode", "getEnableTeleportMode", "setEnableTeleportMode", "clone", "", "dynamicBone", "loadParams", "params", "Ljava/util/LinkedHashMap;", "", "Lkotlin/Function0;", "Lkotlin/collections/LinkedHashMap;", "loadParams$fu_core_all_featureRelease", "refresh", "isImmediate", "reset", "fu_core_all_featureRelease"}, m1294k = 1, m1295mv = {1, 1, 16})
public final class DynamicBone extends BaseAvatarAttribute {
    private Boolean enableDynamicBone;
    private Boolean enableModelMatToBone;
    private Boolean enableRootRotateSpeedLimitMode;
    private Boolean enableRootTranslateSpeedLimitMode;
    private Boolean enableTeleportMode;

    public final void refresh() {
        refresh$default(this, false, 1, null);
    }

    public final void reset() {
        reset$default(this, false, 1, null);
    }

    public final Boolean getEnableDynamicBone() {
        return this.enableDynamicBone;
    }

    public final void setEnableDynamicBone(Boolean bool) {
        this.enableDynamicBone = bool;
        if (bool != null) {
            boolean zBooleanValue = bool.booleanValue();
            if (getHasLoaded()) {
                AvatarController.enableInstanceDynamicBone$default(getMAvatarController$fu_core_all_featureRelease(), getAvatarId(), zBooleanValue, false, 4, null);
            }
        }
    }

    public final Boolean getEnableModelMatToBone() {
        return this.enableModelMatToBone;
    }

    public final void setEnableModelMatToBone(Boolean bool) {
        this.enableModelMatToBone = bool;
        if (bool != null) {
            boolean zBooleanValue = bool.booleanValue();
            if (getHasLoaded()) {
                AvatarController.enableInstanceModelMatToBone$default(getMAvatarController$fu_core_all_featureRelease(), getAvatarId(), zBooleanValue, false, 4, null);
            }
        }
    }

    public final Boolean getEnableTeleportMode() {
        return this.enableTeleportMode;
    }

    public final void setEnableTeleportMode(Boolean bool) {
        this.enableTeleportMode = bool;
        if (bool != null) {
            boolean zBooleanValue = bool.booleanValue();
            if (getHasLoaded()) {
                AvatarController.enableInstanceDynamicBoneTeleportMode$default(getMAvatarController$fu_core_all_featureRelease(), getAvatarId(), zBooleanValue, false, 4, null);
            }
        }
    }

    public final Boolean getEnableRootTranslateSpeedLimitMode() {
        return this.enableRootTranslateSpeedLimitMode;
    }

    public final void setEnableRootTranslateSpeedLimitMode(Boolean bool) {
        this.enableRootTranslateSpeedLimitMode = bool;
        if (bool != null) {
            boolean zBooleanValue = bool.booleanValue();
            if (getHasLoaded()) {
                AvatarController.enableInstanceDynamicBoneRootTranslationSpeedLimitMode$default(getMAvatarController$fu_core_all_featureRelease(), getAvatarId(), zBooleanValue, false, 4, null);
            }
        }
    }

    public final Boolean getEnableRootRotateSpeedLimitMode() {
        return this.enableRootRotateSpeedLimitMode;
    }

    public final void setEnableRootRotateSpeedLimitMode(Boolean bool) {
        this.enableRootRotateSpeedLimitMode = bool;
        if (bool != null) {
            boolean zBooleanValue = bool.booleanValue();
            if (getHasLoaded()) {
                AvatarController.enableInstanceDynamicBoneRootRotationSpeedLimitMode$default(getMAvatarController$fu_core_all_featureRelease(), getAvatarId(), zBooleanValue, false, 4, null);
            }
        }
    }

    public static void refresh$default(DynamicBone dynamicBone, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            z = true;
        }
        dynamicBone.refresh(z);
    }

    public final void refresh(boolean isImmediate) {
        AvatarController.refreshInstanceDynamicBone$default(getMAvatarController$fu_core_all_featureRelease(), getAvatarId(), isImmediate, false, 4, null);
    }

    public static void reset$default(DynamicBone dynamicBone, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            z = true;
        }
        dynamicBone.reset(z);
    }

    public final void reset(boolean isImmediate) {
        AvatarController.resetInstanceDynamicBone$default(getMAvatarController$fu_core_all_featureRelease(), getAvatarId(), isImmediate, false, 4, null);
    }

    public final void loadParams$fu_core_all_featureRelease(final LinkedHashMap<String, Function0<Unit>> params) {
        Intrinsics.checkParameterIsNotNull(params, "params");
        Boolean bool = this.enableDynamicBone;
        if (bool != null) {
            final boolean zBooleanValue = bool.booleanValue();
            params.put("enableInstanceDynamicBone", new Function0<Unit>() {
                {
                    super(0);
                }

                @Override
                public Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }

                public final void invoke2() {
                    this.getMAvatarController$fu_core_all_featureRelease().enableInstanceDynamicBone(this.getAvatarId(), zBooleanValue, false);
                }
            });
        }
        Boolean bool2 = this.enableModelMatToBone;
        if (bool2 != null) {
            final boolean zBooleanValue2 = bool2.booleanValue();
            params.put("enableInstanceModelMatToBone", new Function0<Unit>() {
                {
                    super(0);
                }

                @Override
                public Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }

                public final void invoke2() {
                    this.getMAvatarController$fu_core_all_featureRelease().enableInstanceModelMatToBone(this.getAvatarId(), zBooleanValue2, false);
                }
            });
        }
        Boolean bool3 = this.enableTeleportMode;
        if (bool3 != null) {
            final boolean zBooleanValue3 = bool3.booleanValue();
            params.put("enableInstanceDynamicBoneTeleportMode", new Function0<Unit>() {
                {
                    super(0);
                }

                @Override
                public Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }

                public final void invoke2() {
                    this.getMAvatarController$fu_core_all_featureRelease().enableInstanceDynamicBoneTeleportMode(this.getAvatarId(), zBooleanValue3, false);
                }
            });
        }
        Boolean bool4 = this.enableRootTranslateSpeedLimitMode;
        if (bool4 != null) {
            final boolean zBooleanValue4 = bool4.booleanValue();
            params.put("enableInstanceDynamicBoneRootTranslationSpeedLimitMode", new Function0<Unit>() {
                {
                    super(0);
                }

                @Override
                public Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }

                public final void invoke2() {
                    this.getMAvatarController$fu_core_all_featureRelease().enableInstanceDynamicBoneRootTranslationSpeedLimitMode(this.getAvatarId(), zBooleanValue4, false);
                }
            });
        }
        Boolean bool5 = this.enableRootRotateSpeedLimitMode;
        if (bool5 != null) {
            final boolean zBooleanValue5 = bool5.booleanValue();
            params.put("enableInstanceDynamicBoneRootRotationSpeedLimitMode", new Function0<Unit>() {
                {
                    super(0);
                }

                @Override
                public Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }

                public final void invoke2() {
                    this.getMAvatarController$fu_core_all_featureRelease().enableInstanceDynamicBoneRootRotationSpeedLimitMode(this.getAvatarId(), zBooleanValue5, false);
                }
            });
        }
        setHasLoaded(true);
    }

    public final void clone(DynamicBone dynamicBone) {
        Intrinsics.checkParameterIsNotNull(dynamicBone, "dynamicBone");
        setEnableDynamicBone(dynamicBone.enableDynamicBone);
        setEnableModelMatToBone(dynamicBone.enableModelMatToBone);
        setEnableTeleportMode(dynamicBone.enableTeleportMode);
        setEnableRootTranslateSpeedLimitMode(dynamicBone.enableRootTranslateSpeedLimitMode);
        setEnableRootRotateSpeedLimitMode(dynamicBone.enableRootRotateSpeedLimitMode);
    }
}
