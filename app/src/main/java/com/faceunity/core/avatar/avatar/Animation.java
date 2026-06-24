package com.faceunity.core.avatar.avatar;

import com.faceunity.core.avatar.base.BaseAvatarAttribute;
import com.faceunity.core.avatar.control.AvatarController;
import com.faceunity.core.entity.FUAnimationData;
import com.faceunity.core.entity.FUAvatarAnimFilterParams;
import com.faceunity.core.utils.FULogger;
import com.tom_roush.fontbox.ttf.NamingTable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

@Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u000f\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u000e\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0005J\u0015\u0010\u0019\u001a\u00020\u00172\u0006\u0010\u001a\u001a\u00020\u0000H\u0000¢\u0006\u0002\b\u001bJ!\u0010\u001c\u001a\u00020\u00172\u0006\u0010\u001d\u001a\u00020\u00052\n\b\u0002\u0010\u001e\u001a\u0004\u0018\u00010\tH\u0002¢\u0006\u0002\u0010\u001fJ\u0010\u0010 \u001a\u00020\u00172\u0006\u0010\u001d\u001a\u00020\u0005H\u0002J\u0018\u0010!\u001a\u00020\u00172\u0006\u0010\u001d\u001a\u00020\u00052\u0006\u0010\"\u001a\u00020\u0005H\u0002J\u0018\u0010#\u001a\u00020\u00172\u0006\u0010\u001d\u001a\u00020\u00052\u0006\u0010$\u001a\u00020\tH\u0002J\u0012\u0010%\u001a\u0004\u0018\u00010\u00052\u0006\u0010&\u001a\u00020'H\u0007J\u000e\u0010(\u001a\u00020)2\u0006\u0010\u001d\u001a\u00020\u0005J\u000e\u0010*\u001a\u00020+2\u0006\u0010\u001d\u001a\u00020\u0005J\u000e\u0010,\u001a\u00020+2\u0006\u0010\u001d\u001a\u00020\u0005J\u0018\u0010-\u001a\u0012\u0012\u0004\u0012\u00020\u00050\u0004j\b\u0012\u0004\u0012\u00020\u0005`\u0006H\u0007J\n\u0010.\u001a\u0004\u0018\u00010\u0005H\u0007JU\u0010/\u001a\u00020\u00172.\u00100\u001a*\u0012\u0004\u0012\u00020'\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00170201j\u0014\u0012\u0004\u0012\u00020'\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001702`32\u0016\u00104\u001a\u0012\u0012\u0004\u0012\u00020\u00050\u0004j\b\u0012\u0004\u0012\u00020\u0005`\u0006H\u0000¢\u0006\u0002\b5J\u0006\u00106\u001a\u00020\u0017J\u0016\u00107\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00052\u0006\u0010\u001e\u001a\u00020\tJ\u0016\u00107\u001a\u00020\u00172\u0006\u0010&\u001a\u00020'2\u0006\u0010\u001e\u001a\u00020\tJ\u0006\u00108\u001a\u00020\u0017J\u000e\u00109\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0005J\u000e\u00109\u001a\u00020\u00172\u0006\u0010&\u001a\u00020'J\u001a\u0010:\u001a\u00020\u00172\b\u0010\u001a\u001a\u0004\u0018\u00010\u00052\b\u0010;\u001a\u0004\u0018\u00010\u0005J\u0016\u0010:\u001a\u00020\u00172\u0006\u0010&\u001a\u00020'2\u0006\u0010;\u001a\u00020\u0005J\u0006\u0010<\u001a\u00020\u0017J\u000e\u0010=\u001a\u00020\u00172\u0006\u0010>\u001a\u00020+J\u0006\u0010?\u001a\u00020\u0017J\u0006\u0010@\u001a\u00020\u0017R\u001e\u0010\u0003\u001a\u0012\u0012\u0004\u0012\u00020\u00050\u0004j\b\u0012\u0004\u0012\u00020\u0005`\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R*\u0010\n\u001a\u0004\u0018\u00010\t2\b\u0010\b\u001a\u0004\u0018\u00010\t@FX\u0086\u000e¢\u0006\u0010\n\u0002\u0010\u000f\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR(\u0010\u0011\u001a\u0004\u0018\u00010\u00102\b\u0010\b\u001a\u0004\u0018\u00010\u0010@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015¨\u0006A"}, m1293d2 = {"Lcom/faceunity/core/avatar/avatar/Animation;", "Lcom/faceunity/core/avatar/base/BaseAvatarAttribute;", "()V", "animations", "Ljava/util/ArrayList;", "Lcom/faceunity/core/entity/FUAnimationData;", "Lkotlin/collections/ArrayList;", "currentAnimation", "value", "", "enableInternalLerp", "getEnableInternalLerp", "()Ljava/lang/Boolean;", "setEnableInternalLerp", "(Ljava/lang/Boolean;)V", "Ljava/lang/Boolean;", "Lcom/faceunity/core/entity/FUAvatarAnimFilterParams;", "humanProcessorSetAvatarAnimFilterParams", "getHumanProcessorSetAvatarAnimFilterParams", "()Lcom/faceunity/core/entity/FUAvatarAnimFilterParams;", "setHumanProcessorSetAvatarAnimFilterParams", "(Lcom/faceunity/core/entity/FUAvatarAnimFilterParams;)V", "addAnimation", "", "bundle", "clone", "animation", "clone$fu_core_all_featureRelease", "doAvatarAnimationLoad", "data", "isLoop", "(Lcom/faceunity/core/entity/FUAnimationData;Ljava/lang/Boolean;)V", "doAvatarAnimationRemove", "doAvatarAnimationReplace", "targetData", "doPlayAnimation", "isLooper", "getAnimation", NamingTable.TAG, "", "getAnimationFrameNumber", "", "getAnimationProgress", "", "getAnimationTransitionProgress", "getAnimations", "getCurrentAnimation", "loadParams", "params", "Ljava/util/LinkedHashMap;", "Lkotlin/Function0;", "Lkotlin/collections/LinkedHashMap;", "bundles", "loadParams$fu_core_all_featureRelease", "pauseCurrentAnimation", "playAnimation", "removeAllAnimations", "removeAnimation", "replaceAnimation", "targetAnimation", "resetCurrentAnimation", "setAnimationTransitionTime", "time", "startCurrentAnimation", "stopCurrentAnimation", "fu_core_all_featureRelease"}, m1294k = 1, m1295mv = {1, 1, 16})
public final class Animation extends BaseAvatarAttribute {
    private final ArrayList<FUAnimationData> animations = new ArrayList<>();
    private FUAnimationData currentAnimation;
    private Boolean enableInternalLerp;
    private FUAvatarAnimFilterParams humanProcessorSetAvatarAnimFilterParams;

    public final ArrayList<FUAnimationData> getAnimations() {
        return this.animations;
    }

    public final FUAnimationData getCurrentAnimation() {
        return this.currentAnimation;
    }

    public final FUAnimationData getAnimation(String name) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        for (FUAnimationData fUAnimationData : this.animations) {
            if (Intrinsics.areEqual(fUAnimationData.getName(), name)) {
                return fUAnimationData;
            }
        }
        FULogger.m297w(BaseAvatarAttribute.INSTANCE.getTAG(), "animation  has not find name=" + name);
        return null;
    }

    public final void addAnimation(FUAnimationData bundle) {
        Intrinsics.checkParameterIsNotNull(bundle, "bundle");
        Iterator<T> it = this.animations.iterator();
        while (it.hasNext()) {
            if (((FUAnimationData) it.next()).isEqual(bundle)) {
                FULogger.m297w(BaseAvatarAttribute.INSTANCE.getTAG(), "animation  has added");
                return;
            }
        }
        doAvatarAnimationLoad$default(this, bundle, null, 2, null);
    }

    public final void removeAnimation(FUAnimationData bundle) {
        Intrinsics.checkParameterIsNotNull(bundle, "bundle");
        Iterator<T> it = this.animations.iterator();
        while (it.hasNext()) {
            if (((FUAnimationData) it.next()).isEqual(bundle)) {
                doAvatarAnimationRemove(bundle);
                return;
            }
        }
        FULogger.m297w(BaseAvatarAttribute.INSTANCE.getTAG(), "animation has not find name=" + bundle.getName());
    }

    public final void removeAnimation(String name) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        for (FUAnimationData fUAnimationData : this.animations) {
            if (Intrinsics.areEqual(fUAnimationData.getName(), name)) {
                doAvatarAnimationRemove(fUAnimationData);
                return;
            }
        }
        FULogger.m297w(BaseAvatarAttribute.INSTANCE.getTAG(), "animation bundle has not find  name=" + name);
    }

    public final void removeAllAnimations() {
        Iterator<T> it = this.animations.iterator();
        while (it.hasNext()) {
            doAvatarAnimationRemove((FUAnimationData) it.next());
        }
        this.animations.clear();
    }

    public final void replaceAnimation(String name, FUAnimationData targetAnimation) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        Intrinsics.checkParameterIsNotNull(targetAnimation, "targetAnimation");
        FUAnimationData fUAnimationData = null;
        for (FUAnimationData fUAnimationData2 : this.animations) {
            if (Intrinsics.areEqual(fUAnimationData2.getName(), name)) {
                fUAnimationData = fUAnimationData2;
            }
        }
        replaceAnimation(fUAnimationData, targetAnimation);
    }

    public final void replaceAnimation(FUAnimationData animation, FUAnimationData targetAnimation) {
        if (animation == null && targetAnimation == null) {
            FULogger.m297w(BaseAvatarAttribute.INSTANCE.getTAG(), "animation and targetAnimation is null");
            return;
        }
        if (animation == null && targetAnimation != null) {
            addAnimation(targetAnimation);
            return;
        }
        if (animation != null && targetAnimation == null) {
            removeAnimation(animation);
            return;
        }
        if (animation == null || targetAnimation == null) {
            return;
        }
        if (animation.isEqual(targetAnimation)) {
            FULogger.m297w(BaseAvatarAttribute.INSTANCE.getTAG(), "animation and targetAnimation  is same");
        } else {
            doAvatarAnimationReplace(animation, targetAnimation);
        }
    }

    public final void playAnimation(String name, boolean isLoop) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        FUAnimationData animation = getAnimation(name);
        if (animation != null) {
            doPlayAnimation(animation, isLoop);
        } else {
            FULogger.m297w(BaseAvatarAttribute.INSTANCE.getTAG(), "animation bundle has not find name=" + name);
        }
    }

    public final void playAnimation(FUAnimationData bundle, boolean isLoop) {
        Intrinsics.checkParameterIsNotNull(bundle, "bundle");
        Iterator<T> it = this.animations.iterator();
        boolean z = false;
        while (it.hasNext()) {
            if (((FUAnimationData) it.next()).isEqual(bundle)) {
                z = true;
            }
        }
        if (!z) {
            doAvatarAnimationLoad(bundle, Boolean.valueOf(isLoop));
        } else {
            doPlayAnimation(bundle, isLoop);
        }
        this.currentAnimation = bundle;
    }

    public final void startCurrentAnimation() {
        AvatarController.startInstanceAnimation$default(getMAvatarController$fu_core_all_featureRelease(), getAvatarId(), false, 2, null);
    }

    public final void pauseCurrentAnimation() {
        AvatarController.pauseInstanceAnimation$default(getMAvatarController$fu_core_all_featureRelease(), getAvatarId(), false, 2, null);
    }

    public final void stopCurrentAnimation() {
        AvatarController.stopInstanceAnimation$default(getMAvatarController$fu_core_all_featureRelease(), getAvatarId(), false, 2, null);
    }

    public final void resetCurrentAnimation() {
        AvatarController.resetInstanceAnimation$default(getMAvatarController$fu_core_all_featureRelease(), getAvatarId(), false, 2, null);
    }

    public final void setAnimationTransitionTime(float time) {
        AvatarController.setInstanceAnimationTransitionTime$default(getMAvatarController$fu_core_all_featureRelease(), getAvatarId(), time, false, 4, null);
    }

    public final Boolean getEnableInternalLerp() {
        return this.enableInternalLerp;
    }

    public final void setEnableInternalLerp(Boolean bool) {
        if (bool != null) {
            boolean zBooleanValue = bool.booleanValue();
            if (getHasLoaded()) {
                AvatarController.enableInstanceAnimationInternalLerp$default(getMAvatarController$fu_core_all_featureRelease(), getAvatarId(), zBooleanValue, false, 4, null);
            }
        }
        this.enableInternalLerp = bool;
    }

    public final int getAnimationFrameNumber(FUAnimationData data) {
        Intrinsics.checkParameterIsNotNull(data, "data");
        return getMAvatarController$fu_core_all_featureRelease().getInstanceAnimationFrameNumber(getAvatarId(), data.getAnimation());
    }

    public final float getAnimationProgress(FUAnimationData data) {
        Intrinsics.checkParameterIsNotNull(data, "data");
        return getMAvatarController$fu_core_all_featureRelease().getInstanceAnimationProgress(getAvatarId(), data.getAnimation());
    }

    public final float getAnimationTransitionProgress(FUAnimationData data) {
        Intrinsics.checkParameterIsNotNull(data, "data");
        return getMAvatarController$fu_core_all_featureRelease().getInstanceAnimationTransitionProgress(getAvatarId(), data.getAnimation());
    }

    public final FUAvatarAnimFilterParams getHumanProcessorSetAvatarAnimFilterParams() {
        return this.humanProcessorSetAvatarAnimFilterParams;
    }

    public final void setHumanProcessorSetAvatarAnimFilterParams(FUAvatarAnimFilterParams fUAvatarAnimFilterParams) {
        this.humanProcessorSetAvatarAnimFilterParams = fUAvatarAnimFilterParams;
        if (fUAvatarAnimFilterParams != null) {
            getMAvatarController$fu_core_all_featureRelease().humanProcessorSetAvatarAnimFilterParams(fUAvatarAnimFilterParams.getNBufferFrames(), fUAvatarAnimFilterParams.getPos(), fUAvatarAnimFilterParams.getAngle());
        }
    }

    public final void loadParams$fu_core_all_featureRelease(final LinkedHashMap<String, Function0<Unit>> params, ArrayList<FUAnimationData> bundles) {
        Intrinsics.checkParameterIsNotNull(params, "params");
        Intrinsics.checkParameterIsNotNull(bundles, "bundles");
        Boolean bool = this.enableInternalLerp;
        if (bool != null) {
            final boolean zBooleanValue = bool.booleanValue();
            params.put("enableInternalLerp", new Function0<Unit>() {
                {
                    super(0);
                }

                @Override
                public Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }

                public final void invoke2() {
                    this.getMAvatarController$fu_core_all_featureRelease().enableInstanceAnimationInternalLerp(this.getAvatarId(), zBooleanValue, false);
                }
            });
        }
        final FUAvatarAnimFilterParams fUAvatarAnimFilterParams = this.humanProcessorSetAvatarAnimFilterParams;
        if (fUAvatarAnimFilterParams != null) {
            params.put("humanProcessorSetAvatarAnimFilterParams", new Function0<Unit>() {
                {
                    super(0);
                }

                @Override
                public Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }

                public final void invoke2() {
                    this.getMAvatarController$fu_core_all_featureRelease().humanProcessorSetAvatarAnimFilterParams(fUAvatarAnimFilterParams.getNBufferFrames(), fUAvatarAnimFilterParams.getPos(), fUAvatarAnimFilterParams.getAngle());
                }
            });
        }
        bundles.addAll(this.animations);
        setHasLoaded(true);
    }

    public final void clone$fu_core_all_featureRelease(Animation animation) {
        Intrinsics.checkParameterIsNotNull(animation, "animation");
        Iterator<T> it = animation.getAnimations().iterator();
        while (it.hasNext()) {
            this.animations.add(((FUAnimationData) it.next()).clone());
        }
        setEnableInternalLerp(animation.enableInternalLerp);
    }

    static void doAvatarAnimationLoad$default(Animation animation, FUAnimationData fUAnimationData, Boolean bool, int i, Object obj) {
        if ((i & 2) != 0) {
            bool = null;
        }
        animation.doAvatarAnimationLoad(fUAnimationData, bool);
    }

    private final void doAvatarAnimationLoad(FUAnimationData data, Boolean isLoop) {
        this.animations.add(data);
        if (getHasLoaded()) {
            getMAvatarController$fu_core_all_featureRelease().loadAvatarAnimationData(getAvatarId(), data, isLoop);
        }
    }

    private final void doAvatarAnimationRemove(FUAnimationData data) {
        this.animations.remove(data);
        if (getHasLoaded()) {
            getMAvatarController$fu_core_all_featureRelease().removeAvatarAnimationData(getAvatarId(), data);
        }
    }

    private final void doAvatarAnimationReplace(FUAnimationData data, FUAnimationData targetData) {
        this.animations.remove(data);
        this.animations.add(targetData);
        if (getHasLoaded()) {
            getMAvatarController$fu_core_all_featureRelease().replaceAvatarAnimationData(getAvatarId(), data, targetData);
        }
    }

    private final void doPlayAnimation(FUAnimationData data, boolean isLooper) {
        if (getHasLoaded()) {
            AvatarController.playInstanceAnimation$default(getMAvatarController$fu_core_all_featureRelease(), getAvatarId(), data, isLooper, false, 8, null);
        }
    }
}
