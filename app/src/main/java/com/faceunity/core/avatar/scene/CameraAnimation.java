package com.faceunity.core.avatar.scene;

import com.faceunity.core.avatar.base.BaseSceneAttribute;
import com.faceunity.core.avatar.control.AvatarController;
import com.faceunity.core.entity.FUAnimationData;
import com.faceunity.core.utils.FULogger;
import com.tom_roush.fontbox.ttf.NamingTable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

@Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\n\n\u0002\u0010\u0002\n\u0002\b\r\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\f\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0018\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\r2\b\b\u0002\u0010\u001d\u001a\u00020\u0010J+\u0010\u001e\u001a\u00020\u001b2\u0006\u0010\u001f\u001a\u00020\r2\n\b\u0002\u0010 \u001a\u0004\u0018\u00010\u00102\b\b\u0002\u0010\u001d\u001a\u00020\u0010H\u0002¢\u0006\u0002\u0010!J\u0018\u0010\"\u001a\u00020\u001b2\u0006\u0010\u001f\u001a\u00020\r2\u0006\u0010\u001d\u001a\u00020\u0010H\u0002J \u0010#\u001a\u00020\u001b2\u0006\u0010\u001f\u001a\u00020\r2\u0006\u0010$\u001a\u00020\r2\u0006\u0010\u001d\u001a\u00020\u0010H\u0002J\u0018\u0010%\u001a\u00020\u001b2\u0006\u0010\u001f\u001a\u00020\r2\u0006\u0010&\u001a\u00020\u0010H\u0002J\u0012\u0010'\u001a\u0004\u0018\u00010\r2\u0006\u0010(\u001a\u00020)H\u0007J\u000e\u0010*\u001a\u00020+2\u0006\u0010\u001f\u001a\u00020\rJ\u000e\u0010,\u001a\u00020\u00042\u0006\u0010\u001f\u001a\u00020\rJ\u0018\u0010-\u001a\u0012\u0012\u0004\u0012\u00020\r0\fj\b\u0012\u0004\u0012\u00020\r`\u000eH\u0007J\n\u0010.\u001a\u0004\u0018\u00010\rH\u0007J\u0006\u0010/\u001a\u00020\u0004JU\u00100\u001a\u00020\u001b2.\u00101\u001a*\u0012\u0004\u0012\u00020)\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001b0302j\u0014\u0012\u0004\u0012\u00020)\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001b03`42\u0016\u00105\u001a\u0012\u0012\u0004\u0012\u00020\r0\fj\b\u0012\u0004\u0012\u00020\r`\u000eH\u0000¢\u0006\u0002\b6J\u0006\u00107\u001a\u00020\u001bJ\u0016\u00108\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\r2\u0006\u0010 \u001a\u00020\u0010J\u0016\u00108\u001a\u00020\u001b2\u0006\u0010(\u001a\u00020)2\u0006\u0010 \u001a\u00020\u0010J\u0006\u00109\u001a\u00020\u001bJ\u0018\u0010:\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\r2\b\b\u0002\u0010\u001d\u001a\u00020\u0010J\u0018\u0010:\u001a\u00020\u001b2\u0006\u0010(\u001a\u00020)2\b\b\u0002\u0010\u001d\u001a\u00020\u0010J&\u0010;\u001a\u00020\u001b2\b\u0010<\u001a\u0004\u0018\u00010\r2\b\u0010=\u001a\u0004\u0018\u00010\r2\b\b\u0002\u0010\u001d\u001a\u00020\u0010H\u0007J \u0010;\u001a\u00020\u001b2\u0006\u0010(\u001a\u00020)2\u0006\u0010=\u001a\u00020\r2\b\b\u0002\u0010\u001d\u001a\u00020\u0010J\u0006\u0010>\u001a\u00020\u001bJ\u0006\u0010?\u001a\u00020\u001bR*\u0010\u0005\u001a\u0004\u0018\u00010\u00042\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004@FX\u0086\u000e¢\u0006\u0010\n\u0002\u0010\n\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u001e\u0010\u000b\u001a\u0012\u0012\u0004\u0012\u00020\r0\fj\b\u0012\u0004\u0012\u00020\r`\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u000f\u001a\u0004\u0018\u00010\rX\u0082\u000e¢\u0006\u0002\n\u0000R*\u0010\u0011\u001a\u0004\u0018\u00010\u00102\b\u0010\u0003\u001a\u0004\u0018\u00010\u0010@FX\u0086\u000e¢\u0006\u0010\n\u0002\u0010\u0016\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R*\u0010\u0017\u001a\u0004\u0018\u00010\u00102\b\u0010\u0003\u001a\u0004\u0018\u00010\u0010@FX\u0086\u000e¢\u0006\u0010\n\u0002\u0010\u0016\u001a\u0004\b\u0018\u0010\u0013\"\u0004\b\u0019\u0010\u0015¨\u0006@"}, m1293d2 = {"Lcom/faceunity/core/avatar/scene/CameraAnimation;", "Lcom/faceunity/core/avatar/base/BaseSceneAttribute;", "()V", "value", "", "animationTransitionTime", "getAnimationTransitionTime", "()Ljava/lang/Float;", "setAnimationTransitionTime", "(Ljava/lang/Float;)V", "Ljava/lang/Float;", "animations", "Ljava/util/ArrayList;", "Lcom/faceunity/core/entity/FUAnimationData;", "Lkotlin/collections/ArrayList;", "currentAnimation", "", "enableAnimation", "getEnableAnimation", "()Ljava/lang/Boolean;", "setEnableAnimation", "(Ljava/lang/Boolean;)V", "Ljava/lang/Boolean;", "enableInternalLerp", "getEnableInternalLerp", "setEnableInternalLerp", "addAnimation", "", "bundle", "needBackgroundThread", "doCameraAnimationLoad", "data", "isLoop", "(Lcom/faceunity/core/entity/FUAnimationData;Ljava/lang/Boolean;Z)V", "doCameraAnimationRemove", "doCameraAnimationReplace", "targetData", "doPlayAnimation", "isLooper", "getAnimation", NamingTable.TAG, "", "getAnimationFrameNumber", "", "getAnimationProgress", "getAnimations", "getCurrentAnimation", "getCurrentAnimationTransitionProgress", "loadParams", "params", "Ljava/util/LinkedHashMap;", "Lkotlin/Function0;", "Lkotlin/collections/LinkedHashMap;", "bundles", "loadParams$fu_core_all_featureRelease", "pauseCurrentAnimation", "playAnimation", "removeAllAnimations", "removeAnimation", "replaceAnimation", "animation", "targetAnimation", "resetCurrentAnimation", "startCurrentAnimation", "fu_core_all_featureRelease"}, m1294k = 1, m1295mv = {1, 1, 16})
public final class CameraAnimation extends BaseSceneAttribute {
    private Float animationTransitionTime;
    private final ArrayList<FUAnimationData> animations = new ArrayList<>();
    private FUAnimationData currentAnimation;
    private Boolean enableAnimation;
    private Boolean enableInternalLerp;

    public final void replaceAnimation(FUAnimationData fUAnimationData, FUAnimationData fUAnimationData2) {
        replaceAnimation$default(this, fUAnimationData, fUAnimationData2, false, 4, (Object) null);
    }

    public final Boolean getEnableAnimation() {
        return this.enableAnimation;
    }

    public final void setEnableAnimation(Boolean bool) {
        this.enableAnimation = bool;
        if (bool != null) {
            boolean zBooleanValue = bool.booleanValue();
            if (getHasLoaded()) {
                AvatarController.enableCameraAnimation$default(getMAvatarController$fu_core_all_featureRelease(), getSceneId(), zBooleanValue, false, 4, null);
            }
        }
    }

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
        FULogger.m297w(BaseSceneAttribute.INSTANCE.getTAG(), "animation has not find name=" + name);
        return null;
    }

    public static void addAnimation$default(CameraAnimation cameraAnimation, FUAnimationData fUAnimationData, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = true;
        }
        cameraAnimation.addAnimation(fUAnimationData, z);
    }

    public final void addAnimation(FUAnimationData bundle, boolean needBackgroundThread) {
        Intrinsics.checkParameterIsNotNull(bundle, "bundle");
        Iterator<T> it = this.animations.iterator();
        while (it.hasNext()) {
            if (((FUAnimationData) it.next()).isEqual(bundle)) {
                FULogger.m297w(BaseSceneAttribute.INSTANCE.getTAG(), "animation bundle has added");
                return;
            }
        }
        doCameraAnimationLoad(bundle, null, needBackgroundThread);
    }

    public static void removeAnimation$default(CameraAnimation cameraAnimation, FUAnimationData fUAnimationData, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = true;
        }
        cameraAnimation.removeAnimation(fUAnimationData, z);
    }

    public final void removeAnimation(FUAnimationData bundle, boolean needBackgroundThread) {
        Intrinsics.checkParameterIsNotNull(bundle, "bundle");
        Iterator<T> it = this.animations.iterator();
        while (it.hasNext()) {
            if (((FUAnimationData) it.next()).isEqual(bundle)) {
                doCameraAnimationRemove(bundle, needBackgroundThread);
                return;
            }
        }
        FULogger.m297w(BaseSceneAttribute.INSTANCE.getTAG(), "animation  has not find name=" + bundle.getName());
    }

    public static void removeAnimation$default(CameraAnimation cameraAnimation, String str, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = true;
        }
        cameraAnimation.removeAnimation(str, z);
    }

    public final void removeAnimation(String name, boolean needBackgroundThread) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        for (FUAnimationData fUAnimationData : this.animations) {
            if (Intrinsics.areEqual(fUAnimationData.getName(), name)) {
                doCameraAnimationRemove(fUAnimationData, needBackgroundThread);
                return;
            }
        }
        FULogger.m297w(BaseSceneAttribute.INSTANCE.getTAG(), "animation bundle has not find  name=" + name);
    }

    public final void removeAllAnimations() {
        Iterator<T> it = this.animations.iterator();
        while (it.hasNext()) {
            doCameraAnimationRemove((FUAnimationData) it.next(), true);
        }
        this.animations.clear();
    }

    public static void replaceAnimation$default(CameraAnimation cameraAnimation, String str, FUAnimationData fUAnimationData, boolean z, int i, Object obj) {
        if ((i & 4) != 0) {
            z = true;
        }
        cameraAnimation.replaceAnimation(str, fUAnimationData, z);
    }

    public final void replaceAnimation(String name, FUAnimationData targetAnimation, boolean needBackgroundThread) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        Intrinsics.checkParameterIsNotNull(targetAnimation, "targetAnimation");
        FUAnimationData fUAnimationData = null;
        for (FUAnimationData fUAnimationData2 : this.animations) {
            if (Intrinsics.areEqual(fUAnimationData2.getName(), name)) {
                fUAnimationData = fUAnimationData2;
            }
        }
        replaceAnimation(fUAnimationData, targetAnimation, needBackgroundThread);
    }

    public static void replaceAnimation$default(CameraAnimation cameraAnimation, FUAnimationData fUAnimationData, FUAnimationData fUAnimationData2, boolean z, int i, Object obj) {
        if ((i & 4) != 0) {
            z = true;
        }
        cameraAnimation.replaceAnimation(fUAnimationData, fUAnimationData2, z);
    }

    public final void replaceAnimation(FUAnimationData animation, FUAnimationData targetAnimation, boolean needBackgroundThread) {
        if (animation == null && targetAnimation == null) {
            FULogger.m297w(BaseSceneAttribute.INSTANCE.getTAG(), "animation and targetAnimation is null");
            return;
        }
        if (animation == null && targetAnimation != null) {
            addAnimation$default(this, targetAnimation, false, 2, null);
            return;
        }
        if (animation != null && targetAnimation == null) {
            removeAnimation$default(this, animation, false, 2, (Object) null);
            return;
        }
        if (animation == null || targetAnimation == null) {
            return;
        }
        if (animation.isEqual(targetAnimation)) {
            FULogger.m297w(BaseSceneAttribute.INSTANCE.getTAG(), "animation and targetAnimation  is same");
        } else {
            doCameraAnimationReplace(animation, targetAnimation, needBackgroundThread);
        }
    }

    public final void playAnimation(String name, boolean isLoop) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        FUAnimationData animation = getAnimation(name);
        if (animation != null) {
            doPlayAnimation(animation, isLoop);
        } else {
            FULogger.m297w(BaseSceneAttribute.INSTANCE.getTAG(), "animation bundle has not find name=" + name);
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
            doCameraAnimationLoad(bundle, Boolean.valueOf(isLoop), true);
        } else {
            doPlayAnimation(bundle, isLoop);
        }
        this.currentAnimation = bundle;
    }

    public final void startCurrentAnimation() {
        AvatarController.startCameraAnimation$default(getMAvatarController$fu_core_all_featureRelease(), getSceneId(), false, 2, null);
    }

    public final void pauseCurrentAnimation() {
        AvatarController.pauseCameraAnimation$default(getMAvatarController$fu_core_all_featureRelease(), getSceneId(), false, 2, null);
    }

    public final void resetCurrentAnimation() {
        AvatarController.resetCameraAnimation$default(getMAvatarController$fu_core_all_featureRelease(), getSceneId(), false, 2, null);
    }

    public final Float getAnimationTransitionTime() {
        return this.animationTransitionTime;
    }

    public final void setAnimationTransitionTime(Float f) {
        if (f != null) {
            float fFloatValue = f.floatValue();
            if (getHasLoaded()) {
                AvatarController.setCameraAnimationTransitionTime$default(getMAvatarController$fu_core_all_featureRelease(), getSceneId(), fFloatValue, false, 4, null);
            }
        }
        this.animationTransitionTime = f;
    }

    public final Boolean getEnableInternalLerp() {
        return this.enableInternalLerp;
    }

    public final void setEnableInternalLerp(Boolean bool) {
        if (bool != null) {
            boolean zBooleanValue = bool.booleanValue();
            if (getHasLoaded()) {
                AvatarController.enableCameraAnimationInternalLerp$default(getMAvatarController$fu_core_all_featureRelease(), getSceneId(), zBooleanValue, false, 4, null);
            }
        }
        this.enableInternalLerp = bool;
    }

    public final int getAnimationFrameNumber(FUAnimationData data) {
        Intrinsics.checkParameterIsNotNull(data, "data");
        return getMAvatarController$fu_core_all_featureRelease().getCameraAnimationFrameNumber(getSceneId(), data.getAnimation());
    }

    public final float getAnimationProgress(FUAnimationData data) {
        Intrinsics.checkParameterIsNotNull(data, "data");
        return getMAvatarController$fu_core_all_featureRelease().getCameraAnimationProgress(getSceneId(), data.getAnimation());
    }

    public final float getCurrentAnimationTransitionProgress() {
        return getMAvatarController$fu_core_all_featureRelease().getCameraAnimationTransitionProgress(getSceneId());
    }

    public final void loadParams$fu_core_all_featureRelease(final LinkedHashMap<String, Function0<Unit>> params, ArrayList<FUAnimationData> bundles) {
        Intrinsics.checkParameterIsNotNull(params, "params");
        Intrinsics.checkParameterIsNotNull(bundles, "bundles");
        Boolean bool = this.enableAnimation;
        if (bool != null) {
            final boolean zBooleanValue = bool.booleanValue();
            params.put("enableCameraAnimation", new Function0<Unit>() {
                {
                    super(0);
                }

                @Override
                public Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }

                public final void invoke2() {
                    this.getMAvatarController$fu_core_all_featureRelease().enableCameraAnimation(this.getSceneId(), zBooleanValue, false);
                }
            });
        }
        Boolean bool2 = this.enableInternalLerp;
        if (bool2 != null) {
            final boolean zBooleanValue2 = bool2.booleanValue();
            params.put("enableCameraAnimationInternalLerp", new Function0<Unit>() {
                {
                    super(0);
                }

                @Override
                public Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }

                public final void invoke2() {
                    this.getMAvatarController$fu_core_all_featureRelease().enableCameraAnimationInternalLerp(this.getSceneId(), zBooleanValue2, false);
                }
            });
        }
        Float f = this.animationTransitionTime;
        if (f != null) {
            final float fFloatValue = f.floatValue();
            params.put("setCameraAnimationTransitionTime", new Function0<Unit>() {
                {
                    super(0);
                }

                @Override
                public Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }

                public final void invoke2() {
                    this.getMAvatarController$fu_core_all_featureRelease().setCameraAnimationTransitionTime(this.getSceneId(), fFloatValue, false);
                }
            });
        }
        bundles.addAll(this.animations);
        setHasLoaded(true);
    }

    static void doCameraAnimationLoad$default(CameraAnimation cameraAnimation, FUAnimationData fUAnimationData, Boolean bool, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            bool = null;
        }
        if ((i & 4) != 0) {
            z = true;
        }
        cameraAnimation.doCameraAnimationLoad(fUAnimationData, bool, z);
    }

    private final void doCameraAnimationLoad(FUAnimationData data, Boolean isLoop, boolean needBackgroundThread) {
        this.animations.add(data);
        if (getHasLoaded()) {
            getMAvatarController$fu_core_all_featureRelease().loadCameraAnimationData(getSceneId(), data, isLoop, needBackgroundThread);
        }
    }

    private final void doCameraAnimationRemove(FUAnimationData data, boolean needBackgroundThread) {
        this.animations.remove(data);
        if (getHasLoaded()) {
            getMAvatarController$fu_core_all_featureRelease().removeCameraAnimationData(getSceneId(), data, needBackgroundThread);
        }
    }

    private final void doCameraAnimationReplace(FUAnimationData data, FUAnimationData targetData, boolean needBackgroundThread) {
        this.animations.remove(data);
        this.animations.add(targetData);
        if (getHasLoaded()) {
            getMAvatarController$fu_core_all_featureRelease().replaceCameraAnimationData(getSceneId(), data, targetData, needBackgroundThread);
        }
    }

    private final void doPlayAnimation(FUAnimationData data, boolean isLooper) {
        if (getHasLoaded()) {
            AvatarController.playCameraAnimation$default(getMAvatarController$fu_core_all_featureRelease(), getSceneId(), data, isLooper, false, 8, null);
        }
    }
}
