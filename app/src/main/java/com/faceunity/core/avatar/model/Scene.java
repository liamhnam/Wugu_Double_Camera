package com.faceunity.core.avatar.model;

import com.faceunity.core.avatar.base.BaseSceneAttribute;
import com.faceunity.core.avatar.control.AvatarController;
import com.faceunity.core.avatar.control.FUASceneData;
import com.faceunity.core.avatar.scene.Camera;
import com.faceunity.core.avatar.scene.CameraAnimation;
import com.faceunity.core.avatar.scene.ProcessorConfig;
import com.faceunity.core.entity.FUAnimationData;
import com.faceunity.core.entity.FUBundleData;
import com.faceunity.core.entity.FUColorRGBData;
import com.faceunity.core.utils.FULogger;
import com.google.android.exoplayer2.text.ttml.TtmlNode;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

@Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\t\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005J\u000e\u00100\u001a\u0002012\u0006\u00102\u001a\u00020\bJ\u000e\u00103\u001a\u0002012\u0006\u00102\u001a\u00020\bJ\r\u00104\u001a\u000205H\u0000¢\u0006\u0002\b6J\u0016\u00107\u001a\u0012\u0012\u0004\u0012\u00020\b0\u0007j\b\u0012\u0004\u0012\u00020\b`\tJ\u000e\u00108\u001a\u0002012\u0006\u00102\u001a\u00020\bJ\u000e\u00109\u001a\u0002012\u0006\u00102\u001a\u00020\bJ\u001a\u0010:\u001a\u0002012\b\u0010;\u001a\u0004\u0018\u00010\b2\b\u0010<\u001a\u0004\u0018\u00010\bJ\u001a\u0010=\u001a\u0002012\b\u0010;\u001a\u0004\u0018\u00010\b2\b\u0010<\u001a\u0004\u0018\u00010\bR\u000e\u0010\u0004\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\u0006\u001a\u0012\u0012\u0004\u0012\u00020\b0\u0007j\b\u0012\u0004\u0012\u00020\b`\tX\u0082\u0004¢\u0006\u0002\n\u0000R(\u0010\u000b\u001a\u0004\u0018\u00010\u00032\b\u0010\n\u001a\u0004\u0018\u00010\u0003@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR(\u0010\u0011\u001a\u0004\u0018\u00010\u00102\b\u0010\n\u001a\u0004\u0018\u00010\u0010@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R\u0010\u0010\u0016\u001a\u00020\u00178\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0018\u001a\u00020\u00198\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R*\u0010\u001b\u001a\u0004\u0018\u00010\u001a2\b\u0010\n\u001a\u0004\u0018\u00010\u001a@FX\u0086\u000e¢\u0006\u0010\n\u0002\u0010 \u001a\u0004\b\u001c\u0010\u001d\"\u0004\b\u001e\u0010\u001fR*\u0010!\u001a\u0004\u0018\u00010\u001a2\b\u0010\n\u001a\u0004\u0018\u00010\u001a@FX\u0086\u000e¢\u0006\u0010\n\u0002\u0010 \u001a\u0004\b\"\u0010\u001d\"\u0004\b#\u0010\u001fR(\u0010$\u001a\u0004\u0018\u00010\u00032\b\u0010\n\u001a\u0004\u0018\u00010\u0003@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b%\u0010\r\"\u0004\b&\u0010\u000fR\u0010\u0010'\u001a\u00020(8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R*\u0010*\u001a\u0004\u0018\u00010)2\b\u0010\n\u001a\u0004\u0018\u00010)@FX\u0086\u000e¢\u0006\u0010\n\u0002\u0010/\u001a\u0004\b+\u0010,\"\u0004\b-\u0010.¨\u0006>"}, m1293d2 = {"Lcom/faceunity/core/avatar/model/Scene;", "Lcom/faceunity/core/avatar/base/BaseSceneAttribute;", "controlBundle", "Lcom/faceunity/core/entity/FUBundleData;", "avatarConfig", "(Lcom/faceunity/core/entity/FUBundleData;Lcom/faceunity/core/entity/FUBundleData;)V", "avatars", "Ljava/util/ArrayList;", "Lcom/faceunity/core/avatar/model/Avatar;", "Lkotlin/collections/ArrayList;", "value", "backgroundBundle", "getBackgroundBundle", "()Lcom/faceunity/core/entity/FUBundleData;", "setBackgroundBundle", "(Lcom/faceunity/core/entity/FUBundleData;)V", "Lcom/faceunity/core/entity/FUColorRGBData;", TtmlNode.ATTR_TTS_BACKGROUND_COLOR, "getBackgroundColor", "()Lcom/faceunity/core/entity/FUColorRGBData;", "setBackgroundColor", "(Lcom/faceunity/core/entity/FUColorRGBData;)V", "camera", "Lcom/faceunity/core/avatar/scene/Camera;", "cameraAnimation", "Lcom/faceunity/core/avatar/scene/CameraAnimation;", "", "enableLowQualityLighting", "getEnableLowQualityLighting", "()Ljava/lang/Boolean;", "setEnableLowQualityLighting", "(Ljava/lang/Boolean;)V", "Ljava/lang/Boolean;", "enableShadow", "getEnableShadow", "setEnableShadow", "lightingBundle", "getLightingBundle", "setLightingBundle", "processorConfig", "Lcom/faceunity/core/avatar/scene/ProcessorConfig;", "", "shadowPCFLevel", "getShadowPCFLevel", "()Ljava/lang/Integer;", "setShadowPCFLevel", "(Ljava/lang/Integer;)V", "Ljava/lang/Integer;", "addAvatar", "", "avatar", "addAvatarGL", "buildFUASceneData", "Lcom/faceunity/core/avatar/control/FUASceneData;", "buildFUASceneData$fu_core_all_featureRelease", "getAvatars", "removeAvatar", "removeAvatarGL", "replaceAvatar", "oldAvatar", "newAvatar", "replaceAvatarGL", "fu_core_all_featureRelease"}, m1294k = 1, m1295mv = {1, 1, 16})
public final class Scene extends BaseSceneAttribute {
    private final FUBundleData avatarConfig;
    private final ArrayList<Avatar> avatars;
    private FUBundleData backgroundBundle;
    private FUColorRGBData backgroundColor;
    public final Camera camera;
    public final CameraAnimation cameraAnimation;
    private final FUBundleData controlBundle;
    private Boolean enableLowQualityLighting;
    private Boolean enableShadow;
    private FUBundleData lightingBundle;
    public final ProcessorConfig processorConfig;
    private Integer shadowPCFLevel;

    public Scene(FUBundleData controlBundle, FUBundleData avatarConfig) {
        Intrinsics.checkParameterIsNotNull(controlBundle, "controlBundle");
        Intrinsics.checkParameterIsNotNull(avatarConfig, "avatarConfig");
        this.controlBundle = controlBundle;
        this.avatarConfig = avatarConfig;
        this.avatars = new ArrayList<>();
        Camera camera = new Camera();
        this.camera = camera;
        CameraAnimation cameraAnimation = new CameraAnimation();
        this.cameraAnimation = cameraAnimation;
        ProcessorConfig processorConfig = new ProcessorConfig();
        this.processorConfig = processorConfig;
        setSceneId$fu_core_all_featureRelease(System.nanoTime());
        camera.setSceneId$fu_core_all_featureRelease(getSceneId());
        cameraAnimation.setSceneId$fu_core_all_featureRelease(getSceneId());
        processorConfig.setSceneId$fu_core_all_featureRelease(getSceneId());
    }

    public final FUBundleData getBackgroundBundle() {
        return this.backgroundBundle;
    }

    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void setBackgroundBundle(com.faceunity.core.entity.FUBundleData r5) {
        /*
            r4 = this;
            boolean r0 = r4.getHasLoaded()
            if (r0 == 0) goto L5c
            com.faceunity.core.entity.FUBundleData r0 = r4.backgroundBundle
            if (r0 != 0) goto L18
            if (r5 == 0) goto L18
            com.faceunity.core.avatar.control.AvatarController r0 = r4.getMAvatarController$fu_core_all_featureRelease()
            long r1 = r4.getSceneId()
            r0.loadSceneItemBundle(r1, r5)
            goto L5c
        L18:
            if (r0 == 0) goto L44
            if (r5 == 0) goto L44
            if (r0 != 0) goto L21
            kotlin.jvm.internal.Intrinsics.throwNpe()
        L21:
            java.lang.String r0 = r0.getPath()
            java.lang.String r1 = r5.getPath()
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r1)
            r0 = r0 ^ 1
            if (r0 == 0) goto L44
            com.faceunity.core.avatar.control.AvatarController r0 = r4.getMAvatarController$fu_core_all_featureRelease()
            long r1 = r4.getSceneId()
            com.faceunity.core.entity.FUBundleData r3 = r4.backgroundBundle
            if (r3 != 0) goto L40
            kotlin.jvm.internal.Intrinsics.throwNpe()
        L40:
            r0.replaceSceneItemBundle(r1, r3, r5)
            goto L5c
        L44:
            com.faceunity.core.entity.FUBundleData r0 = r4.backgroundBundle
            if (r0 == 0) goto L5c
            if (r5 != 0) goto L5c
            com.faceunity.core.avatar.control.AvatarController r0 = r4.getMAvatarController$fu_core_all_featureRelease()
            long r1 = r4.getSceneId()
            com.faceunity.core.entity.FUBundleData r3 = r4.backgroundBundle
            if (r3 != 0) goto L59
            kotlin.jvm.internal.Intrinsics.throwNpe()
        L59:
            r0.removeSceneItemBundle(r1, r3)
        L5c:
            r4.backgroundBundle = r5
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.faceunity.core.avatar.model.Scene.setBackgroundBundle(com.faceunity.core.entity.FUBundleData):void");
    }

    public final FUColorRGBData getBackgroundColor() {
        return this.backgroundColor;
    }

    public final void setBackgroundColor(FUColorRGBData fUColorRGBData) {
        this.backgroundColor = fUColorRGBData;
        if (getHasLoaded()) {
            if (fUColorRGBData != null) {
                AvatarController.enableBackgroundColor$default(getMAvatarController$fu_core_all_featureRelease(), getSceneId(), true, false, 4, null);
                AvatarController.setBackgroundColor$default(getMAvatarController$fu_core_all_featureRelease(), getSceneId(), fUColorRGBData, false, 4, null);
            } else {
                AvatarController.enableBackgroundColor$default(getMAvatarController$fu_core_all_featureRelease(), getSceneId(), false, false, 4, null);
            }
        }
    }

    public final Boolean getEnableShadow() {
        return this.enableShadow;
    }

    public final void setEnableShadow(Boolean bool) {
        this.enableShadow = bool;
        if (!getHasLoaded() || bool == null) {
            return;
        }
        AvatarController.enableShadow$default(getMAvatarController$fu_core_all_featureRelease(), getSceneId(), bool.booleanValue(), false, 4, null);
    }

    public final Integer getShadowPCFLevel() {
        return this.shadowPCFLevel;
    }

    public final void setShadowPCFLevel(Integer num) {
        this.shadowPCFLevel = num;
        if (!getHasLoaded() || num == null) {
            return;
        }
        AvatarController.setInstanceShadowPCFLevel$default(getMAvatarController$fu_core_all_featureRelease(), getSceneId(), num.intValue(), false, 4, null);
    }

    public final Boolean getEnableLowQualityLighting() {
        return this.enableLowQualityLighting;
    }

    public final void setEnableLowQualityLighting(Boolean bool) {
        this.enableLowQualityLighting = bool;
        if (!getHasLoaded() || bool == null) {
            return;
        }
        AvatarController.enableLowQualityLighting$default(getMAvatarController$fu_core_all_featureRelease(), getSceneId(), bool.booleanValue(), false, 4, null);
    }

    public final FUBundleData getLightingBundle() {
        return this.lightingBundle;
    }

    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void setLightingBundle(com.faceunity.core.entity.FUBundleData r5) {
        /*
            r4 = this;
            boolean r0 = r4.getHasLoaded()
            if (r0 == 0) goto L5c
            com.faceunity.core.entity.FUBundleData r0 = r4.lightingBundle
            if (r0 != 0) goto L18
            if (r5 == 0) goto L18
            com.faceunity.core.avatar.control.AvatarController r0 = r4.getMAvatarController$fu_core_all_featureRelease()
            long r1 = r4.getSceneId()
            r0.loadSceneItemBundle(r1, r5)
            goto L5c
        L18:
            if (r0 == 0) goto L44
            if (r5 == 0) goto L44
            if (r0 != 0) goto L21
            kotlin.jvm.internal.Intrinsics.throwNpe()
        L21:
            java.lang.String r0 = r0.getPath()
            java.lang.String r1 = r5.getPath()
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r1)
            r0 = r0 ^ 1
            if (r0 == 0) goto L44
            com.faceunity.core.avatar.control.AvatarController r0 = r4.getMAvatarController$fu_core_all_featureRelease()
            long r1 = r4.getSceneId()
            com.faceunity.core.entity.FUBundleData r3 = r4.lightingBundle
            if (r3 != 0) goto L40
            kotlin.jvm.internal.Intrinsics.throwNpe()
        L40:
            r0.replaceSceneItemBundle(r1, r3, r5)
            goto L5c
        L44:
            com.faceunity.core.entity.FUBundleData r0 = r4.lightingBundle
            if (r0 == 0) goto L5c
            if (r5 != 0) goto L5c
            com.faceunity.core.avatar.control.AvatarController r0 = r4.getMAvatarController$fu_core_all_featureRelease()
            long r1 = r4.getSceneId()
            com.faceunity.core.entity.FUBundleData r3 = r4.lightingBundle
            if (r3 != 0) goto L59
            kotlin.jvm.internal.Intrinsics.throwNpe()
        L59:
            r0.removeSceneItemBundle(r1, r3)
        L5c:
            r4.lightingBundle = r5
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.faceunity.core.avatar.model.Scene.setLightingBundle(com.faceunity.core.entity.FUBundleData):void");
    }

    public final ArrayList<Avatar> getAvatars() {
        return this.avatars;
    }

    public final void addAvatar(Avatar avatar) {
        Intrinsics.checkParameterIsNotNull(avatar, "avatar");
        if (this.avatars.contains(avatar)) {
            FULogger.m294e(BaseSceneAttribute.INSTANCE.getTAG(), "has loaded this FaceUnityAvatarModel");
            return;
        }
        this.avatars.add(avatar);
        if (getHasLoaded()) {
            getMAvatarController$fu_core_all_featureRelease().doAddAvatar$fu_core_all_featureRelease(getSceneId(), avatar.buildFUAAvatarData$fu_core_all_featureRelease());
        }
    }

    public final void addAvatarGL(Avatar avatar) {
        Intrinsics.checkParameterIsNotNull(avatar, "avatar");
        if (this.avatars.contains(avatar)) {
            FULogger.m294e(BaseSceneAttribute.INSTANCE.getTAG(), "has loaded this FaceUnityAvatarModel");
            return;
        }
        this.avatars.add(avatar);
        if (getHasLoaded()) {
            getMAvatarController$fu_core_all_featureRelease().doAddAvatarGL$fu_core_all_featureRelease(getSceneId(), avatar.buildFUAAvatarData$fu_core_all_featureRelease());
        }
    }

    public final void removeAvatar(Avatar avatar) {
        Intrinsics.checkParameterIsNotNull(avatar, "avatar");
        if (!this.avatars.contains(avatar)) {
            FULogger.m294e(BaseSceneAttribute.INSTANCE.getTAG(), "has not loaded this FaceUnityAvatarModel");
            return;
        }
        this.avatars.remove(avatar);
        if (getHasLoaded()) {
            getMAvatarController$fu_core_all_featureRelease().doRemoveAvatar$fu_core_all_featureRelease(getSceneId(), avatar.buildFUAAvatarData$fu_core_all_featureRelease());
        }
    }

    public final void removeAvatarGL(Avatar avatar) {
        Intrinsics.checkParameterIsNotNull(avatar, "avatar");
        if (!this.avatars.contains(avatar)) {
            FULogger.m294e(BaseSceneAttribute.INSTANCE.getTAG(), "has not loaded this FaceUnityAvatarModel");
            return;
        }
        this.avatars.remove(avatar);
        if (getHasLoaded()) {
            getMAvatarController$fu_core_all_featureRelease().doRemoveAvatarGL$fu_core_all_featureRelease(getSceneId(), avatar.buildFUAAvatarData$fu_core_all_featureRelease());
        }
    }

    public final void replaceAvatar(Avatar oldAvatar, Avatar newAvatar) {
        if (oldAvatar == null && newAvatar == null) {
            FULogger.m297w(BaseSceneAttribute.INSTANCE.getTAG(), "oldAvatar and newAvatar is null");
            return;
        }
        if (oldAvatar == null && newAvatar != null) {
            addAvatar(newAvatar);
            return;
        }
        if (oldAvatar != null && newAvatar == null) {
            removeAvatar(oldAvatar);
            return;
        }
        if (oldAvatar == null || newAvatar == null) {
            return;
        }
        if (!this.avatars.contains(oldAvatar)) {
            FULogger.m294e(BaseSceneAttribute.INSTANCE.getTAG(), "has not loaded this FaceUnityAvatarModel");
            addAvatar(newAvatar);
            return;
        }
        if (this.avatars.contains(newAvatar)) {
            if (Intrinsics.areEqual(oldAvatar, newAvatar)) {
                FULogger.m297w(BaseSceneAttribute.INSTANCE.getTAG(), "oldAvatar and newAvatar  is same");
                return;
            } else {
                FULogger.m294e(BaseSceneAttribute.INSTANCE.getTAG(), "same newAvatar  already exists");
                removeAvatar(oldAvatar);
                return;
            }
        }
        this.avatars.remove(oldAvatar);
        this.avatars.add(newAvatar);
        if (getHasLoaded()) {
            getMAvatarController$fu_core_all_featureRelease().doReplaceAvatar$fu_core_all_featureRelease(getSceneId(), oldAvatar.buildFUAAvatarData$fu_core_all_featureRelease(), newAvatar.buildFUAAvatarData$fu_core_all_featureRelease());
        }
    }

    public final void replaceAvatarGL(Avatar oldAvatar, Avatar newAvatar) {
        if (oldAvatar == null && newAvatar == null) {
            FULogger.m297w(BaseSceneAttribute.INSTANCE.getTAG(), "oldAvatar and newAvatar is null");
            return;
        }
        if (oldAvatar == null && newAvatar != null) {
            addAvatarGL(newAvatar);
            return;
        }
        if (oldAvatar != null && newAvatar == null) {
            removeAvatarGL(oldAvatar);
            return;
        }
        if (oldAvatar == null || newAvatar == null) {
            return;
        }
        if (!this.avatars.contains(oldAvatar)) {
            FULogger.m294e(BaseSceneAttribute.INSTANCE.getTAG(), "has not loaded this FaceUnityAvatarModel");
            addAvatarGL(newAvatar);
            return;
        }
        if (this.avatars.contains(newAvatar)) {
            if (Intrinsics.areEqual(oldAvatar, newAvatar)) {
                FULogger.m297w(BaseSceneAttribute.INSTANCE.getTAG(), "oldAvatar and newAvatar  is same");
                return;
            } else {
                FULogger.m294e(BaseSceneAttribute.INSTANCE.getTAG(), "same newAvatar  already exists");
                removeAvatarGL(oldAvatar);
                return;
            }
        }
        this.avatars.remove(oldAvatar);
        this.avatars.add(newAvatar);
        if (getHasLoaded()) {
            getMAvatarController$fu_core_all_featureRelease().doReplaceAvatarGL$fu_core_all_featureRelease(getSceneId(), oldAvatar.buildFUAAvatarData$fu_core_all_featureRelease(), newAvatar.buildFUAAvatarData$fu_core_all_featureRelease());
        }
    }

    public final FUASceneData buildFUASceneData$fu_core_all_featureRelease() {
        final LinkedHashMap<String, Function0<Unit>> linkedHashMap = new LinkedHashMap<>();
        ArrayList arrayList = new ArrayList();
        ArrayList<FUAnimationData> arrayList2 = new ArrayList<>();
        ArrayList arrayList3 = new ArrayList();
        arrayList.add(this.avatarConfig);
        FUBundleData fUBundleData = this.backgroundBundle;
        if (fUBundleData != null) {
            arrayList.add(fUBundleData);
        }
        final FUColorRGBData fUColorRGBData = this.backgroundColor;
        if (fUColorRGBData != null) {
            LinkedHashMap<String, Function0<Unit>> linkedHashMap2 = linkedHashMap;
            linkedHashMap2.put("enableBackgroundColor", new Function0<Unit>() {
                {
                    super(0);
                }

                @Override
                public Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }

                public final void invoke2() {
                    this.this$0.getMAvatarController$fu_core_all_featureRelease().enableBackgroundColor(this.this$0.getSceneId(), true, false);
                }
            });
            linkedHashMap2.put("setBackgroundColor", new Function0<Unit>() {
                {
                    super(0);
                }

                @Override
                public Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }

                public final void invoke2() {
                    this.getMAvatarController$fu_core_all_featureRelease().setBackgroundColor(this.getSceneId(), fUColorRGBData, false);
                }
            });
        }
        this.camera.loadParams$fu_core_all_featureRelease(linkedHashMap);
        this.cameraAnimation.loadParams$fu_core_all_featureRelease(linkedHashMap, arrayList2);
        Boolean bool = this.enableShadow;
        if (bool != null) {
            final boolean zBooleanValue = bool.booleanValue();
            linkedHashMap.put("enableShadow", new Function0<Unit>() {
                {
                    super(0);
                }

                @Override
                public Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }

                public final void invoke2() {
                    this.getMAvatarController$fu_core_all_featureRelease().enableShadow(this.getSceneId(), zBooleanValue, false);
                }
            });
        }
        Integer num = this.shadowPCFLevel;
        if (num != null) {
            final int iIntValue = num.intValue();
            linkedHashMap.put("setInstanceShadowPCFLevel", new Function0<Unit>() {
                {
                    super(0);
                }

                @Override
                public Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }

                public final void invoke2() {
                    this.getMAvatarController$fu_core_all_featureRelease().setInstanceShadowPCFLevel(this.getSceneId(), iIntValue, false);
                }
            });
        }
        Boolean bool2 = this.enableLowQualityLighting;
        if (bool2 != null) {
            final boolean zBooleanValue2 = bool2.booleanValue();
            linkedHashMap.put("enableLowQualityLighting", new Function0<Unit>() {
                {
                    super(0);
                }

                @Override
                public Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }

                public final void invoke2() {
                    AvatarController.enableLowQualityLighting$default(this.getMAvatarController$fu_core_all_featureRelease(), this.getSceneId(), zBooleanValue2, false, 4, null);
                }
            });
        }
        FUBundleData fUBundleData2 = this.lightingBundle;
        if (fUBundleData2 != null) {
            arrayList.add(fUBundleData2);
        }
        this.processorConfig.loadParams$fu_core_all_featureRelease(linkedHashMap);
        Iterator<T> it = this.avatars.iterator();
        while (it.hasNext()) {
            arrayList3.add(((Avatar) it.next()).buildFUAAvatarData$fu_core_all_featureRelease());
        }
        setHasLoaded(true);
        return new FUASceneData(getSceneId(), this.controlBundle, arrayList, arrayList2, arrayList3, linkedHashMap, false, 64, null);
    }
}
