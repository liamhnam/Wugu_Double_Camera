package com.faceunity.core.avatar.avatar;

import com.faceunity.core.avatar.base.BaseAvatarAttribute;
import com.faceunity.core.avatar.control.AvatarController;
import java.util.LinkedHashMap;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

@Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010\u0014\n\u0002\b\t\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0000J=\u0010\u0017\u001a\u00020\u00152.\u0010\u0018\u001a*\u0012\u0004\u0012\u00020\u001a\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00150\u001b0\u0019j\u0014\u0012\u0004\u0012\u00020\u001a\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00150\u001b`\u001cH\u0000¢\u0006\u0002\b\u001dJ\u000e\u0010\u001e\u001a\u00020\u00152\u0006\u0010\u001f\u001a\u00020\u000bR*\u0010\u0005\u001a\u0004\u0018\u00010\u00042\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004@FX\u0086\u000e¢\u0006\u0010\n\u0002\u0010\n\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR(\u0010\f\u001a\u0004\u0018\u00010\u000b2\b\u0010\u0003\u001a\u0004\u0018\u00010\u000b@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R(\u0010\u0011\u001a\u0004\u0018\u00010\u000b2\b\u0010\u0003\u001a\u0004\u0018\u00010\u000b@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u000e\"\u0004\b\u0013\u0010\u0010¨\u0006 "}, m1293d2 = {"Lcom/faceunity/core/avatar/avatar/BlendShape;", "Lcom/faceunity/core/avatar/base/BaseAvatarAttribute;", "()V", "value", "", "enableExpressionBlend", "getEnableExpressionBlend", "()Ljava/lang/Boolean;", "setEnableExpressionBlend", "(Ljava/lang/Boolean;)V", "Ljava/lang/Boolean;", "", "inputBlendShapeWeight", "getInputBlendShapeWeight", "()[F", "setInputBlendShapeWeight", "([F)V", "systemBlendShapeWeight", "getSystemBlendShapeWeight", "setSystemBlendShapeWeight", "clone", "", "blendShape", "loadParams", "params", "Ljava/util/LinkedHashMap;", "", "Lkotlin/Function0;", "Lkotlin/collections/LinkedHashMap;", "loadParams$fu_core_all_featureRelease", "updateInputBlendShape", "expression", "fu_core_all_featureRelease"}, m1294k = 1, m1295mv = {1, 1, 16})
public final class BlendShape extends BaseAvatarAttribute {
    private Boolean enableExpressionBlend;
    private float[] inputBlendShapeWeight;
    private float[] systemBlendShapeWeight;

    public final Boolean getEnableExpressionBlend() {
        return this.enableExpressionBlend;
    }

    public final void setEnableExpressionBlend(Boolean bool) {
        if (bool != null) {
            boolean zBooleanValue = bool.booleanValue();
            if (getHasLoaded()) {
                AvatarController.enableInstanceExpressionBlend$default(getMAvatarController$fu_core_all_featureRelease(), getAvatarId(), zBooleanValue, false, 4, null);
            }
        }
        this.enableExpressionBlend = bool;
    }

    public final float[] getInputBlendShapeWeight() {
        return this.inputBlendShapeWeight;
    }

    public final void setInputBlendShapeWeight(float[] fArr) {
        if (fArr != null && getHasLoaded()) {
            AvatarController.setInstanceExpressionWeight0$default(getMAvatarController$fu_core_all_featureRelease(), getAvatarId(), fArr, false, 4, null);
        }
        this.inputBlendShapeWeight = fArr;
    }

    public final float[] getSystemBlendShapeWeight() {
        return this.systemBlendShapeWeight;
    }

    public final void setSystemBlendShapeWeight(float[] fArr) {
        if (fArr != null && getHasLoaded()) {
            AvatarController.setInstanceExpressionWeight1$default(getMAvatarController$fu_core_all_featureRelease(), getAvatarId(), fArr, false, 4, null);
        }
        this.systemBlendShapeWeight = fArr;
    }

    public final void updateInputBlendShape(float[] expression) {
        Intrinsics.checkParameterIsNotNull(expression, "expression");
        AvatarController.setInstanceBlendExpression$default(getMAvatarController$fu_core_all_featureRelease(), getAvatarId(), expression, false, 4, null);
    }

    public final void loadParams$fu_core_all_featureRelease(final LinkedHashMap<String, Function0<Unit>> params) {
        Intrinsics.checkParameterIsNotNull(params, "params");
        Boolean bool = this.enableExpressionBlend;
        if (bool != null) {
            final boolean zBooleanValue = bool.booleanValue();
            params.put("enableInstanceExpressionBlend", new Function0<Unit>() {
                {
                    super(0);
                }

                @Override
                public Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }

                public final void invoke2() {
                    this.getMAvatarController$fu_core_all_featureRelease().enableInstanceExpressionBlend(this.getAvatarId(), zBooleanValue, false);
                }
            });
        }
        final float[] fArr = this.inputBlendShapeWeight;
        if (fArr != null) {
            params.put("setInstanceExpressionWeight0", new Function0<Unit>() {
                {
                    super(0);
                }

                @Override
                public Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }

                public final void invoke2() {
                    this.getMAvatarController$fu_core_all_featureRelease().setInstanceExpressionWeight0(this.getAvatarId(), fArr, false);
                }
            });
        }
        final float[] fArr2 = this.systemBlendShapeWeight;
        if (fArr2 != null) {
            params.put("setInstanceExpressionWeight1", new Function0<Unit>() {
                {
                    super(0);
                }

                @Override
                public Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }

                public final void invoke2() {
                    this.getMAvatarController$fu_core_all_featureRelease().setInstanceExpressionWeight1(this.getAvatarId(), fArr2, false);
                }
            });
        }
        setHasLoaded(true);
    }

    public final void clone(BlendShape blendShape) {
        Intrinsics.checkParameterIsNotNull(blendShape, "blendShape");
        setEnableExpressionBlend(blendShape.enableExpressionBlend);
        setInputBlendShapeWeight(blendShape.inputBlendShapeWeight);
        setSystemBlendShapeWeight(blendShape.systemBlendShapeWeight);
    }
}
