package com.faceunity.core.faceunity;

import com.faceunity.core.avatar.control.BaseAvatarController;
import com.faceunity.core.controller.BaseSingleController;
import com.faceunity.core.controller.prop.BasePropController;
import com.faceunity.core.entity.FURenderInputData;
import com.faceunity.core.entity.FURenderOutputData;
import com.faceunity.core.model.action.ActionRecognition;
import com.faceunity.core.model.animationFilter.AnimationFilter;
import com.faceunity.core.model.antialiasing.Antialiasing;
import com.faceunity.core.model.bgSegGreen.BgSegGreen;
import com.faceunity.core.model.bodyBeauty.BodyBeauty;
import com.faceunity.core.model.facebeauty.FaceBeauty;
import com.faceunity.core.model.hairBeauty.HairBeautyNormal;
import com.faceunity.core.model.littleMakeup.LightMakeup;
import com.faceunity.core.model.makeup.SimpleMakeup;
import com.faceunity.core.model.musicFilter.MusicFilter;
import com.faceunity.core.model.prop.PropContainer;
import com.faceunity.core.support.FURenderBridge;
import com.faceunity.core.support.SDKController;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u0000°\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0010\u0018\u0000 }2\u00020\u0001:\u0001}B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010U\u001a\u00020V2\u0006\u0010W\u001a\u00020XJ\u0006\u0010Y\u001a\u00020VJ\u0006\u0010Z\u001a\u00020VJ\u0010\u0010[\u001a\u00020V2\u0006\u0010\\\u001a\u00020]H\u0002J\u000e\u0010^\u001a\u00020V2\u0006\u0010W\u001a\u00020XJ\u000e\u0010_\u001a\u00020V2\u0006\u0010`\u001a\u00020]J\u000e\u0010a\u001a\u00020V2\u0006\u0010b\u001a\u00020]J\u000e\u0010c\u001a\u00020d2\u0006\u0010e\u001a\u00020dJ\u0006\u0010f\u001a\u00020gJ\u0006\u0010h\u001a\u00020VJ\u0006\u0010i\u001a\u00020VJ\u0006\u0010j\u001a\u00020VJ\u000e\u0010k\u001a\u00020l2\u0006\u0010m\u001a\u00020nJ\u000e\u0010o\u001a\u00020V2\u0006\u0010p\u001a\u00020gJ\u000e\u0010q\u001a\u00020V2\u0006\u0010b\u001a\u00020]J\u000e\u0010r\u001a\u00020V2\u0006\u0010s\u001a\u00020dJ\u000e\u0010t\u001a\u00020V2\u0006\u0010u\u001a\u00020]J\u000e\u0010v\u001a\u00020V2\u0006\u0010b\u001a\u00020]J\u000e\u0010w\u001a\u00020d2\u0006\u0010x\u001a\u00020]J\u0016\u0010y\u001a\u00020d2\u0006\u0010z\u001a\u00020]2\u0006\u0010{\u001a\u00020]J\u000e\u0010|\u001a\u00020d2\u0006\u0010x\u001a\u00020]R\u001b\u0010\u0003\u001a\u00020\u00048FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\u0005\u0010\u0006R(\u0010\u000b\u001a\u0004\u0018\u00010\n2\b\u0010\t\u001a\u0004\u0018\u00010\n@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR(\u0010\u0011\u001a\u0004\u0018\u00010\u00102\b\u0010\t\u001a\u0004\u0018\u00010\u0010@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R(\u0010\u0017\u001a\u0004\u0018\u00010\u00162\b\u0010\t\u001a\u0004\u0018\u00010\u0016@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001bR(\u0010\u001d\u001a\u0004\u0018\u00010\u001c2\b\u0010\t\u001a\u0004\u0018\u00010\u001c@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\u001f\"\u0004\b \u0010!R(\u0010#\u001a\u0004\u0018\u00010\"2\b\u0010\t\u001a\u0004\u0018\u00010\"@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b$\u0010%\"\u0004\b&\u0010'R(\u0010)\u001a\u0004\u0018\u00010(2\b\u0010\t\u001a\u0004\u0018\u00010(@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b*\u0010+\"\u0004\b,\u0010-R(\u0010/\u001a\u0004\u0018\u00010.2\b\u0010\t\u001a\u0004\u0018\u00010.@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b0\u00101\"\u0004\b2\u00103R(\u00105\u001a\u0004\u0018\u0001042\b\u0010\t\u001a\u0004\u0018\u000104@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b6\u00107\"\u0004\b8\u00109R\u001b\u0010:\u001a\u00020;8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b>\u0010\b\u001a\u0004\b<\u0010=R(\u0010@\u001a\u0004\u0018\u00010?2\b\u0010\t\u001a\u0004\u0018\u00010?@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bA\u0010B\"\u0004\bC\u0010DR(\u0010F\u001a\u0004\u0018\u00010E2\b\u0010\t\u001a\u0004\u0018\u00010E@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bG\u0010H\"\u0004\bI\u0010JR\u001b\u0010K\u001a\u00020L8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\bO\u0010\b\u001a\u0004\bM\u0010NR\u001b\u0010P\u001a\u00020Q8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\bT\u0010\b\u001a\u0004\bR\u0010S¨\u0006~"}, m1293d2 = {"Lcom/faceunity/core/faceunity/FURenderKit;", "", "()V", "FUAIController", "Lcom/faceunity/core/faceunity/FUAIKit;", "getFUAIController", "()Lcom/faceunity/core/faceunity/FUAIKit;", "FUAIController$delegate", "Lkotlin/Lazy;", "value", "Lcom/faceunity/core/model/action/ActionRecognition;", "actionRecognition", "getActionRecognition", "()Lcom/faceunity/core/model/action/ActionRecognition;", "setActionRecognition", "(Lcom/faceunity/core/model/action/ActionRecognition;)V", "Lcom/faceunity/core/model/animationFilter/AnimationFilter;", "animationFilter", "getAnimationFilter", "()Lcom/faceunity/core/model/animationFilter/AnimationFilter;", "setAnimationFilter", "(Lcom/faceunity/core/model/animationFilter/AnimationFilter;)V", "Lcom/faceunity/core/model/antialiasing/Antialiasing;", "antialiasing", "getAntialiasing", "()Lcom/faceunity/core/model/antialiasing/Antialiasing;", "setAntialiasing", "(Lcom/faceunity/core/model/antialiasing/Antialiasing;)V", "Lcom/faceunity/core/model/bgSegGreen/BgSegGreen;", "bgSegGreen", "getBgSegGreen", "()Lcom/faceunity/core/model/bgSegGreen/BgSegGreen;", "setBgSegGreen", "(Lcom/faceunity/core/model/bgSegGreen/BgSegGreen;)V", "Lcom/faceunity/core/model/bodyBeauty/BodyBeauty;", "bodyBeauty", "getBodyBeauty", "()Lcom/faceunity/core/model/bodyBeauty/BodyBeauty;", "setBodyBeauty", "(Lcom/faceunity/core/model/bodyBeauty/BodyBeauty;)V", "Lcom/faceunity/core/model/facebeauty/FaceBeauty;", "faceBeauty", "getFaceBeauty", "()Lcom/faceunity/core/model/facebeauty/FaceBeauty;", "setFaceBeauty", "(Lcom/faceunity/core/model/facebeauty/FaceBeauty;)V", "Lcom/faceunity/core/model/hairBeauty/HairBeautyNormal;", "hairBeauty", "getHairBeauty", "()Lcom/faceunity/core/model/hairBeauty/HairBeautyNormal;", "setHairBeauty", "(Lcom/faceunity/core/model/hairBeauty/HairBeautyNormal;)V", "Lcom/faceunity/core/model/littleMakeup/LightMakeup;", "lightMakeup", "getLightMakeup", "()Lcom/faceunity/core/model/littleMakeup/LightMakeup;", "setLightMakeup", "(Lcom/faceunity/core/model/littleMakeup/LightMakeup;)V", "mFURenderBridge", "Lcom/faceunity/core/support/FURenderBridge;", "getMFURenderBridge", "()Lcom/faceunity/core/support/FURenderBridge;", "mFURenderBridge$delegate", "Lcom/faceunity/core/model/makeup/SimpleMakeup;", "makeup", "getMakeup", "()Lcom/faceunity/core/model/makeup/SimpleMakeup;", "setMakeup", "(Lcom/faceunity/core/model/makeup/SimpleMakeup;)V", "Lcom/faceunity/core/model/musicFilter/MusicFilter;", "musicFilter", "getMusicFilter", "()Lcom/faceunity/core/model/musicFilter/MusicFilter;", "setMusicFilter", "(Lcom/faceunity/core/model/musicFilter/MusicFilter;)V", "propContainer", "Lcom/faceunity/core/model/prop/PropContainer;", "getPropContainer", "()Lcom/faceunity/core/model/prop/PropContainer;", "propContainer$delegate", "sceneManager", "Lcom/faceunity/core/faceunity/FUSceneKit;", "getSceneManager", "()Lcom/faceunity/core/faceunity/FUSceneKit;", "sceneManager$delegate", "addMakeupLoadListener", "", "runnable", "Ljava/lang/Runnable;", "clearCacheResource", "createEGLContext", "destroy", "isSafe", "", "doGLThreadAction", "forceSetInputPbo", "force", "fuSetARMeshV2", "enable", "getModuleCode", "", "code", "getVersion", "", "release", "releaseEGLContext", "releaseSafe", "renderWithInput", "Lcom/faceunity/core/entity/FURenderOutputData;", "input", "Lcom/faceunity/core/entity/FURenderInputData;", "setCacheDirectory", "path", "setDynamicQualityControl", "setMachineType", "type", "setMakeupCoverResource", "coverResource", "setReadBackSync", "setUseAsyncAIInference", "isUse", "setUseMultiBuffer", "isUseMultiGPUTexture", "isUseMultiCPUBuffer", "setUseTexAsync", "Companion", "fu_core_all_featureRelease"}, m1294k = 1, m1295mv = {1, 1, 16})
public final class FURenderKit {

    public static final Companion INSTANCE = new Companion(null);
    private static volatile FURenderKit INSTANCE = null;
    public static final String TAG = "KIT_FURenderKit";

    private final Lazy FUAIController;
    private ActionRecognition actionRecognition;
    private AnimationFilter animationFilter;
    private Antialiasing antialiasing;
    private BgSegGreen bgSegGreen;
    private BodyBeauty bodyBeauty;
    private FaceBeauty faceBeauty;
    private HairBeautyNormal hairBeauty;
    private LightMakeup lightMakeup;

    private final Lazy mFURenderBridge;
    private SimpleMakeup makeup;
    private MusicFilter musicFilter;

    private final Lazy propContainer;

    private final Lazy sceneManager;

    @JvmStatic
    public static final FURenderKit getInstance() {
        return INSTANCE.getInstance();
    }

    private final FURenderBridge getMFURenderBridge() {
        return (FURenderBridge) this.mFURenderBridge.getValue();
    }

    public final FUAIKit getFUAIController() {
        return (FUAIKit) this.FUAIController.getValue();
    }

    public final PropContainer getPropContainer() {
        return (PropContainer) this.propContainer.getValue();
    }

    public final FUSceneKit getSceneManager() {
        return (FUSceneKit) this.sceneManager.getValue();
    }

    private FURenderKit() {
        this.mFURenderBridge = LazyKt.lazy(new Function0<FURenderBridge>() {
            @Override
            public final FURenderBridge invoke() {
                return FURenderBridge.INSTANCE.getInstance$fu_core_all_featureRelease();
            }
        });
        this.FUAIController = LazyKt.lazy(new Function0<FUAIKit>() {
            @Override
            public final FUAIKit invoke() {
                return FUAIKit.INSTANCE.getInstance();
            }
        });
        this.propContainer = LazyKt.lazy(new Function0<PropContainer>() {
            @Override
            public final PropContainer invoke() {
                return PropContainer.INSTANCE.getInstance$fu_core_all_featureRelease();
            }
        });
        this.sceneManager = LazyKt.lazy(new Function0<FUSceneKit>() {
            @Override
            public final FUSceneKit invoke() {
                return FUSceneKit.INSTANCE.getInstance();
            }
        });
    }

    public FURenderKit(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    @Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\u0007\u001a\u00020\u0004H\u0007R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000¨\u0006\b"}, m1293d2 = {"Lcom/faceunity/core/faceunity/FURenderKit$Companion;", "", "()V", "INSTANCE", "Lcom/faceunity/core/faceunity/FURenderKit;", "TAG", "", "getInstance", "fu_core_all_featureRelease"}, m1294k = 1, m1295mv = {1, 1, 16})
    public static final class Companion {
        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        public final FURenderKit getInstance() {
            if (FURenderKit.INSTANCE == null) {
                synchronized (this) {
                    if (FURenderKit.INSTANCE == null) {
                        FURenderKit.INSTANCE = new FURenderKit(null);
                    }
                    Unit unit = Unit.INSTANCE;
                }
            }
            FURenderKit fURenderKit = FURenderKit.INSTANCE;
            if (fURenderKit == null) {
                Intrinsics.throwNpe();
            }
            return fURenderKit;
        }
    }

    public final void release() {
        destroy(false);
    }

    public final void releaseSafe() throws InterruptedException {
        destroy(true);
    }

    private final void destroy(boolean isSafe) throws InterruptedException {
        if (this.faceBeauty != null) {
            setFaceBeauty(null);
        }
        if (this.makeup != null) {
            setMakeup(null);
        }
        if (this.animationFilter != null) {
            setAnimationFilter(null);
        }
        if (this.antialiasing != null) {
            setAntialiasing(null);
        }
        if (this.bgSegGreen != null) {
            setBgSegGreen(null);
        }
        if (this.bodyBeauty != null) {
            setBodyBeauty(null);
        }
        if (this.hairBeauty != null) {
            setHairBeauty(null);
        }
        if (this.lightMakeup != null) {
            setLightMakeup(null);
        }
        if (this.musicFilter != null) {
            setMusicFilter(null);
        }
        if (this.actionRecognition != null) {
            setActionRecognition(null);
        }
        if (!getPropContainer().getAllProp().isEmpty()) {
            getPropContainer().removeAllProp();
        }
        BasePropController.release$fu_core_all_featureRelease$default(getMFURenderBridge().getMPropContainerController$fu_core_all_featureRelease(), null, 1, null);
        if (!getSceneManager().getAllScene().isEmpty()) {
            getSceneManager().removeAllScene();
            BaseAvatarController.release$fu_core_all_featureRelease$default(getMFURenderBridge().getMAvatarController$fu_core_all_featureRelease(), null, 1, null);
        }
        getMFURenderBridge().onDestroy(isSafe);
    }

    public final FaceBeauty getFaceBeauty() {
        return this.faceBeauty;
    }

    public final void setFaceBeauty(FaceBeauty faceBeauty) {
        if (Intrinsics.areEqual(this.faceBeauty, faceBeauty)) {
            return;
        }
        this.faceBeauty = faceBeauty;
        if (faceBeauty != null) {
            faceBeauty.loadToRenderKit$fu_core_all_featureRelease();
        } else {
            BaseSingleController.release$fu_core_all_featureRelease$default(getMFURenderBridge().getMFaceBeautyController$fu_core_all_featureRelease(), null, 1, null);
        }
    }

    public final SimpleMakeup getMakeup() {
        return this.makeup;
    }

    public final void setMakeup(SimpleMakeup simpleMakeup) {
        if (Intrinsics.areEqual(this.makeup, simpleMakeup)) {
            return;
        }
        this.makeup = simpleMakeup;
        if (simpleMakeup != null) {
            simpleMakeup.loadToRenderKit$fu_core_all_featureRelease();
        } else {
            BaseSingleController.release$fu_core_all_featureRelease$default(getMFURenderBridge().getMMakeupController$fu_core_all_featureRelease(), null, 1, null);
        }
    }

    public final AnimationFilter getAnimationFilter() {
        return this.animationFilter;
    }

    public final void setAnimationFilter(AnimationFilter animationFilter) {
        if (Intrinsics.areEqual(this.animationFilter, animationFilter)) {
            return;
        }
        this.animationFilter = animationFilter;
        if (animationFilter != null) {
            animationFilter.loadToRenderKit$fu_core_all_featureRelease();
        } else {
            BaseSingleController.release$fu_core_all_featureRelease$default(getMFURenderBridge().getMAnimationFilterController$fu_core_all_featureRelease(), null, 1, null);
        }
    }

    public final Antialiasing getAntialiasing() {
        return this.antialiasing;
    }

    public final void setAntialiasing(Antialiasing antialiasing) throws InterruptedException {
        if (Intrinsics.areEqual(this.antialiasing, antialiasing)) {
            return;
        }
        this.antialiasing = antialiasing;
        if (antialiasing != null) {
            antialiasing.loadToRenderKit$fu_core_all_featureRelease();
        } else {
            BaseSingleController.release$fu_core_all_featureRelease$default(getMFURenderBridge().getMAntialiasingController$fu_core_all_featureRelease(), null, 1, null);
        }
    }

    public final BgSegGreen getBgSegGreen() {
        return this.bgSegGreen;
    }

    public final void setBgSegGreen(BgSegGreen bgSegGreen) throws InterruptedException {
        if (Intrinsics.areEqual(this.bgSegGreen, bgSegGreen)) {
            return;
        }
        this.bgSegGreen = bgSegGreen;
        if (bgSegGreen != null) {
            bgSegGreen.loadToRenderKit$fu_core_all_featureRelease();
        } else {
            BaseSingleController.release$fu_core_all_featureRelease$default(getMFURenderBridge().getMBgSegGreenController$fu_core_all_featureRelease(), null, 1, null);
        }
    }

    public final BodyBeauty getBodyBeauty() {
        return this.bodyBeauty;
    }

    public final void setBodyBeauty(BodyBeauty bodyBeauty) throws InterruptedException {
        if (Intrinsics.areEqual(this.bodyBeauty, bodyBeauty)) {
            return;
        }
        this.bodyBeauty = bodyBeauty;
        if (bodyBeauty != null) {
            bodyBeauty.loadToRenderKit$fu_core_all_featureRelease();
        } else {
            BaseSingleController.release$fu_core_all_featureRelease$default(getMFURenderBridge().getMBodyBeautyController$fu_core_all_featureRelease(), null, 1, null);
        }
    }

    public final HairBeautyNormal getHairBeauty() {
        return this.hairBeauty;
    }

    public final void setHairBeauty(HairBeautyNormal hairBeautyNormal) throws InterruptedException {
        if (Intrinsics.areEqual(this.hairBeauty, hairBeautyNormal)) {
            return;
        }
        this.hairBeauty = hairBeautyNormal;
        if (hairBeautyNormal != null) {
            hairBeautyNormal.loadToRenderKit$fu_core_all_featureRelease();
        } else {
            BaseSingleController.release$fu_core_all_featureRelease$default(getMFURenderBridge().getMHairBeautyController$fu_core_all_featureRelease(), null, 1, null);
        }
    }

    public final LightMakeup getLightMakeup() {
        return this.lightMakeup;
    }

    public final void setLightMakeup(LightMakeup lightMakeup) throws InterruptedException {
        if (Intrinsics.areEqual(this.lightMakeup, lightMakeup)) {
            return;
        }
        this.lightMakeup = lightMakeup;
        if (lightMakeup != null) {
            lightMakeup.loadToRenderKit$fu_core_all_featureRelease();
        } else {
            BaseSingleController.release$fu_core_all_featureRelease$default(getMFURenderBridge().getMLightMakeupController$fu_core_all_featureRelease(), null, 1, null);
        }
    }

    public final MusicFilter getMusicFilter() {
        return this.musicFilter;
    }

    public final void setMusicFilter(MusicFilter musicFilter) throws InterruptedException {
        if (Intrinsics.areEqual(this.musicFilter, musicFilter)) {
            return;
        }
        this.musicFilter = musicFilter;
        if (musicFilter != null) {
            musicFilter.loadToRenderKit$fu_core_all_featureRelease();
        } else {
            BaseSingleController.release$fu_core_all_featureRelease$default(getMFURenderBridge().getMMusicFilterController$fu_core_all_featureRelease(), null, 1, null);
        }
    }

    public final ActionRecognition getActionRecognition() {
        return this.actionRecognition;
    }

    public final void setActionRecognition(ActionRecognition actionRecognition) throws InterruptedException {
        if (Intrinsics.areEqual(this.actionRecognition, actionRecognition)) {
            return;
        }
        this.actionRecognition = actionRecognition;
        if (actionRecognition != null) {
            actionRecognition.loadToRenderKit$fu_core_all_featureRelease();
        } else {
            BaseSingleController.release$fu_core_all_featureRelease$default(getMFURenderBridge().getMActionRecognitionController$fu_core_all_featureRelease(), null, 1, null);
        }
    }

    public final FURenderOutputData renderWithInput(FURenderInputData input) {
        Intrinsics.checkParameterIsNotNull(input, "input");
        return FURenderBridge.renderWithInput$default(getMFURenderBridge(), input, 0, 2, null);
    }

    public final int setUseAsyncAIInference(boolean isUse) {
        return getMFURenderBridge().setUseAsyncAIInference$fu_core_all_featureRelease(isUse);
    }

    public final int setUseMultiBuffer(boolean isUseMultiGPUTexture, boolean isUseMultiCPUBuffer) {
        return getMFURenderBridge().setUseMultiBuffer$fu_core_all_featureRelease(isUseMultiGPUTexture, isUseMultiCPUBuffer);
    }

    public final int setUseTexAsync(boolean isUse) {
        return getMFURenderBridge().setUseTexAsync$fu_core_all_featureRelease(isUse);
    }

    public final void setDynamicQualityControl(boolean enable) {
        getMFURenderBridge().setDynamicQualityControl$fu_core_all_featureRelease(enable);
    }

    public final void fuSetARMeshV2(boolean enable) {
        getMFURenderBridge().fuSetARMeshV2$fu_core_all_featureRelease(enable);
    }

    public final void clearCacheResource() {
        getMFURenderBridge().clearCacheResource$fu_core_all_featureRelease();
    }

    public final String getVersion() {
        return SDKController.INSTANCE.getVersion$fu_core_all_featureRelease();
    }

    public final int getModuleCode(int code) {
        return SDKController.INSTANCE.getModuleCode$fu_core_all_featureRelease(code);
    }

    public final void createEGLContext() {
        SDKController.INSTANCE.createEGLContext$fu_core_all_featureRelease();
    }

    public final void releaseEGLContext() {
        SDKController.INSTANCE.releaseEGLContext$fu_core_all_featureRelease();
    }

    public final void setReadBackSync(boolean enable) {
        SDKController.INSTANCE.setReadbackSync$fu_core_all_featureRelease(enable);
    }

    public final void setCacheDirectory(String path) {
        Intrinsics.checkParameterIsNotNull(path, "path");
        SDKController.INSTANCE.fuSetCacheDirectory$fu_core_all_featureRelease(path);
    }

    public final void setMachineType(int type) {
        SDKController.INSTANCE.fuSetMachineType$fu_core_all_featureRelease(type);
    }

    public final void forceSetInputPbo(boolean force) {
        SDKController.INSTANCE.fuForceSetInputPbo$fu_core_all_featureRelease(force);
    }

    public final void setMakeupCoverResource(boolean coverResource) {
        SDKController.INSTANCE.fuSetMakeupCoverResource$fu_core_all_featureRelease(coverResource);
    }

    public final void addMakeupLoadListener(final Runnable runnable) {
        Intrinsics.checkParameterIsNotNull(runnable, "runnable");
        getMFURenderBridge().getMMakeupController$fu_core_all_featureRelease().addModelUnitCache$fu_core_all_featureRelease(new Function0<Unit>() {
            {
                super(0);
            }

            @Override
            public Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            public final void invoke2() {
                runnable.run();
            }
        });
    }

    public final void doGLThreadAction(final Runnable runnable) {
        Intrinsics.checkParameterIsNotNull(runnable, "runnable");
        getMFURenderBridge().doGLThreadAction$fu_core_all_featureRelease(new Function0<Unit>() {
            {
                super(0);
            }

            @Override
            public Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            public final void invoke2() {
                runnable.run();
            }
        });
    }
}
