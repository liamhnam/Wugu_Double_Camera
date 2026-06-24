package com.faceunity.core.avatar.avatar;

import com.faceunity.core.avatar.base.BaseAvatarAttribute;
import com.faceunity.core.avatar.control.AvatarController;
import com.faceunity.core.entity.FUCoordinate3DData;
import com.faceunity.core.entity.FUTranslationScale;
import java.util.LinkedHashMap;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

@Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0007\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u000e\u0010&\u001a\u00020'2\u0006\u0010(\u001a\u00020\u0000J=\u0010)\u001a\u00020'2.\u0010*\u001a*\u0012\u0004\u0012\u00020,\u0012\n\u0012\b\u0012\u0004\u0012\u00020'0-0+j\u0014\u0012\u0004\u0012\u00020,\u0012\n\u0012\b\u0012\u0004\u0012\u00020'0-`.H\u0000¢\u0006\u0002\b/J\u0010\u00100\u001a\u00020'2\b\u00101\u001a\u0004\u0018\u00010\u0013J\u000e\u00102\u001a\u00020'2\u0006\u00103\u001a\u00020\u0019J\u000e\u00104\u001a\u00020'2\u0006\u00103\u001a\u00020\u0019J\u000e\u00105\u001a\u00020'2\u0006\u00103\u001a\u00020\u0019R$\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0004@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR$\u0010\u000b\u001a\u00020\n2\u0006\u0010\u0003\u001a\u00020\n@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR$\u0010\u0010\u001a\u00020\n2\u0006\u0010\u0003\u001a\u00020\n@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\r\"\u0004\b\u0012\u0010\u000fR(\u0010\u0014\u001a\u0004\u0018\u00010\u00132\b\u0010\u0003\u001a\u0004\u0018\u00010\u0013@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018R*\u0010\u001a\u001a\u0004\u0018\u00010\u00192\b\u0010\u0003\u001a\u0004\u0018\u00010\u0019@FX\u0086\u000e¢\u0006\u0010\n\u0002\u0010\u001f\u001a\u0004\b\u001b\u0010\u001c\"\u0004\b\u001d\u0010\u001eR$\u0010!\u001a\u00020 2\u0006\u0010\u0003\u001a\u00020 @FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\"\u0010#\"\u0004\b$\u0010%¨\u00066"}, m1293d2 = {"Lcom/faceunity/core/avatar/avatar/TransForm;", "Lcom/faceunity/core/avatar/base/BaseAvatarAttribute;", "()V", "value", "", "avatarFollowMode", "getAvatarFollowMode", "()I", "setAvatarFollowMode", "(I)V", "", "enableHumanFollowMode", "getEnableHumanFollowMode", "()Z", "setEnableHumanFollowMode", "(Z)V", "instanceEnableHumanAnimDriver", "getInstanceEnableHumanAnimDriver", "setInstanceEnableHumanAnimDriver", "Lcom/faceunity/core/entity/FUCoordinate3DData;", "position", "getPosition", "()Lcom/faceunity/core/entity/FUCoordinate3DData;", "setPosition", "(Lcom/faceunity/core/entity/FUCoordinate3DData;)V", "", "rotate", "getRotate", "()Ljava/lang/Float;", "setRotate", "(Ljava/lang/Float;)V", "Ljava/lang/Float;", "Lcom/faceunity/core/entity/FUTranslationScale;", "translationScale", "getTranslationScale", "()Lcom/faceunity/core/entity/FUTranslationScale;", "setTranslationScale", "(Lcom/faceunity/core/entity/FUTranslationScale;)V", "clone", "", "transForm", "loadParams", "params", "Ljava/util/LinkedHashMap;", "", "Lkotlin/Function0;", "Lkotlin/collections/LinkedHashMap;", "loadParams$fu_core_all_featureRelease", "setPositionGL", "data", "setRotDelta", "delta", "setScaleDelta", "setTranslateDelta", "fu_core_all_featureRelease"}, m1294k = 1, m1295mv = {1, 1, 16})
public final class TransForm extends BaseAvatarAttribute {
    private int avatarFollowMode;
    private boolean enableHumanFollowMode;
    private FUCoordinate3DData position;
    private Float rotate;
    private FUTranslationScale translationScale = new FUTranslationScale(0.0f, 0.0f, 0.0f);
    private boolean instanceEnableHumanAnimDriver = true;

    public final FUCoordinate3DData getPosition() {
        return this.position;
    }

    public final void setPosition(FUCoordinate3DData fUCoordinate3DData) {
        this.position = fUCoordinate3DData;
        if (fUCoordinate3DData == null || !getHasLoaded()) {
            return;
        }
        AvatarController.setInstanceTargetPosition$default(getMAvatarController$fu_core_all_featureRelease(), getAvatarId(), fUCoordinate3DData, false, 4, null);
    }

    public final void setPositionGL(FUCoordinate3DData data) {
        if (data != null) {
            FUCoordinate3DData fUCoordinate3DData = this.position;
            if (fUCoordinate3DData != null) {
                fUCoordinate3DData.setPositionX(data.getPositionX());
            }
            FUCoordinate3DData fUCoordinate3DData2 = this.position;
            if (fUCoordinate3DData2 != null) {
                fUCoordinate3DData2.setPositionY(data.getPositionY());
            }
            FUCoordinate3DData fUCoordinate3DData3 = this.position;
            if (fUCoordinate3DData3 != null) {
                fUCoordinate3DData3.setPositionZ(data.getPositionZ());
            }
            if (getHasLoaded()) {
                getMAvatarController$fu_core_all_featureRelease().setInstanceTargetPosition(getAvatarId(), data, false);
            }
        }
    }

    public final Float getRotate() {
        return this.rotate;
    }

    public final void setRotate(Float f) {
        this.rotate = f;
        if (f != null) {
            float fFloatValue = f.floatValue();
            if (getHasLoaded()) {
                AvatarController.setInstanceTargetAngle$default(getMAvatarController$fu_core_all_featureRelease(), getAvatarId(), fFloatValue, false, 4, null);
            }
        }
    }

    public final void setRotDelta(float delta) {
        AvatarController.setInstanceRotDelta$default(getMAvatarController$fu_core_all_featureRelease(), getAvatarId(), delta, false, 4, null);
    }

    public final void setScaleDelta(float delta) {
        AvatarController.setInstanceScaleDelta$default(getMAvatarController$fu_core_all_featureRelease(), getAvatarId(), delta, false, 4, null);
    }

    public final void setTranslateDelta(float delta) {
        AvatarController.setInstanceTranslateDelta$default(getMAvatarController$fu_core_all_featureRelease(), getAvatarId(), delta, false, 4, null);
    }

    public final FUTranslationScale getTranslationScale() {
        return this.translationScale;
    }

    public final void setTranslationScale(FUTranslationScale value) {
        Intrinsics.checkParameterIsNotNull(value, "value");
        this.translationScale = value;
        getMAvatarController$fu_core_all_featureRelease().setInstanceRiggingRetargeterAvatarFixModeTransScale(getAvatarId(), this.translationScale.getX(), this.translationScale.getY(), this.translationScale.getZ());
    }

    public final boolean getEnableHumanFollowMode() {
        return this.enableHumanFollowMode;
    }

    public final void setEnableHumanFollowMode(boolean z) {
        this.enableHumanFollowMode = z;
        getMAvatarController$fu_core_all_featureRelease().enableHumanFollowMode(getAvatarId(), z);
    }

    public final int getAvatarFollowMode() {
        return this.avatarFollowMode;
    }

    public final void setAvatarFollowMode(int i) {
        this.avatarFollowMode = i;
        getMAvatarController$fu_core_all_featureRelease().fuSetInstanceRiggingRetargeterAvatarFollowMode(getAvatarId(), i);
    }

    public final boolean getInstanceEnableHumanAnimDriver() {
        return this.instanceEnableHumanAnimDriver;
    }

    public final void setInstanceEnableHumanAnimDriver(boolean z) {
        this.instanceEnableHumanAnimDriver = z;
        getMAvatarController$fu_core_all_featureRelease().fuSetInstanceEnableHumanAnimDriver(getAvatarId(), z);
    }

    public final void loadParams$fu_core_all_featureRelease(final LinkedHashMap<String, Function0<Unit>> params) {
        Intrinsics.checkParameterIsNotNull(params, "params");
        final FUCoordinate3DData fUCoordinate3DData = this.position;
        if (fUCoordinate3DData != null) {
            params.put("setInstanceTargetPosition", new Function0<Unit>() {
                {
                    super(0);
                }

                @Override
                public Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }

                public final void invoke2() {
                    this.getMAvatarController$fu_core_all_featureRelease().setInstanceTargetPosition(this.getAvatarId(), fUCoordinate3DData, false);
                }
            });
        }
        Float f = this.rotate;
        if (f != null) {
            final float fFloatValue = f.floatValue();
            params.put("setInstanceTargetAngle", new Function0<Unit>() {
                {
                    super(0);
                }

                @Override
                public Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }

                public final void invoke2() {
                    this.getMAvatarController$fu_core_all_featureRelease().setInstanceTargetAngle(this.getAvatarId(), fFloatValue, false);
                }
            });
        }
        LinkedHashMap<String, Function0<Unit>> linkedHashMap = params;
        linkedHashMap.put("fuSetInstanceRiggingRetargeterAvatarFollowMode", new Function0<Unit>() {
            {
                super(0);
            }

            @Override
            public Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            public final void invoke2() {
                this.this$0.getMAvatarController$fu_core_all_featureRelease().fuSetInstanceRiggingRetargeterAvatarFollowMode(this.this$0.getAvatarId(), this.this$0.getAvatarFollowMode());
            }
        });
        linkedHashMap.put("instanceEnableHumanAnimDriver", new Function0<Unit>() {
            {
                super(0);
            }

            @Override
            public Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            public final void invoke2() {
                this.this$0.getMAvatarController$fu_core_all_featureRelease().fuSetInstanceEnableHumanAnimDriver(this.this$0.getAvatarId(), this.this$0.getInstanceEnableHumanAnimDriver());
            }
        });
        setHasLoaded(true);
    }

    public final void clone(TransForm transForm) {
        Intrinsics.checkParameterIsNotNull(transForm, "transForm");
        setPosition(transForm.position);
        setRotate(transForm.rotate);
    }
}
