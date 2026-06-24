package com.faceunity.core.support;

import com.arthenica.ffmpegkit.StreamInformation;
import com.brother.sdk.print.pdl.PrintImageUtil;
import com.faceunity.core.avatar.control.AvatarController;
import com.faceunity.core.bundle.BundleManager;
import com.faceunity.core.controller.action.ActionRecognitionController;
import com.faceunity.core.controller.animationFilter.AnimationFilterController;
import com.faceunity.core.controller.antialiasing.AntialiasingController;
import com.faceunity.core.controller.bgSegGreen.BgSegGreenController;
import com.faceunity.core.controller.bodyBeauty.BodyBeautyController;
import com.faceunity.core.controller.facebeauty.FaceBeautyController;
import com.faceunity.core.controller.hairBeauty.HairBeautyController;
import com.faceunity.core.controller.littleMakeup.LightMakeupController;
import com.faceunity.core.controller.makeup.MakeupController;
import com.faceunity.core.controller.musicFilter.MusicFilterController;
import com.faceunity.core.controller.poster.PosterController;
import com.faceunity.core.controller.prop.PropContainerController;
import com.faceunity.core.entity.FURenderInputData;
import com.faceunity.core.entity.FURenderOutputData;
import com.faceunity.core.enumeration.CameraFacingEnum;
import com.faceunity.core.enumeration.FUExternalInputEnum;
import com.faceunity.core.enumeration.FUInputBufferEnum;
import com.faceunity.core.enumeration.FUInputTextureEnum;
import com.faceunity.core.enumeration.FUTransformMatrixEnum;
import com.faceunity.core.faceunity.FURenderKit;
import com.faceunity.core.utils.BitmapUtils;
import com.faceunity.core.utils.DecimalUtils;
import com.faceunity.core.utils.FULogger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u0000Ð\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0014\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0012\n\u0002\b%\u0018\u0000 §\u00012\u00020\u0001:\u0002§\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010n\u001a\u00020\nJ\r\u0010o\u001a\u00020\nH\u0000¢\u0006\u0002\bpJ\b\u0010q\u001a\u00020\nH\u0002J\r\u0010r\u001a\u00020FH\u0000¢\u0006\u0002\bsJ\u001b\u0010t\u001a\u00020F2\f\u0010u\u001a\b\u0012\u0004\u0012\u00020F0EH\u0000¢\u0006\u0002\bvJ\u0018\u0010w\u001a\u00020x2\u0006\u0010y\u001a\u00020z2\u0006\u0010{\u001a\u00020\nH\u0002J1\u0010|\u001a\u00020x2\u0006\u0010}\u001a\u00020\n2\u0006\u0010~\u001a\u00020\n2\u0006\u0010\u007f\u001a\u00020\n2\u0006\u0010\u0015\u001a\u00020\n2\u0007\u0010\u0080\u0001\u001a\u00020\u0018H\u0002JW\u0010\u0081\u0001\u001a\u00020x2\u0006\u0010}\u001a\u00020\n2\u0006\u0010~\u001a\u00020\n2\u0006\u0010\u007f\u001a\u00020\n2\u0006\u0010\u0015\u001a\u00020\n2\b\u0010\u0082\u0001\u001a\u00030\u0083\u00012\u0007\u0010\u0084\u0001\u001a\u00020\n2\u0007\u0010\u0085\u0001\u001a\u00020\u00182\u0007\u0010\u0080\u0001\u001a\u00020\u00182\u0007\u0010\u0086\u0001\u001a\u00020\u0018H\u0002J<\u0010\u0087\u0001\u001a\u00020x2\u0006\u0010}\u001a\u00020\n2\u0006\u0010~\u001a\u00020\n2\u0006\u0010\u007f\u001a\u00020\n2\u0006\u0010\u0015\u001a\u00020\n2\b\u0010\u0082\u0001\u001a\u00030\u0083\u00012\u0007\u0010\u0084\u0001\u001a\u00020\nH\u0002JI\u0010\u0088\u0001\u001a\u00020x2\u0006\u0010}\u001a\u00020\n2\u0006\u0010~\u001a\u00020\n2\b\u0010\u0082\u0001\u001a\u00030\u0083\u00012\u0007\u0010\u0084\u0001\u001a\u00020\n2\t\b\u0002\u0010\u0085\u0001\u001a\u00020\u00182\u0007\u0010\u0080\u0001\u001a\u00020\u00182\u0007\u0010\u0086\u0001\u001a\u00020\u0018H\u0002J2\u0010\u0089\u0001\u001a\u00020x2\u0006\u0010}\u001a\u00020\n2\u0006\u0010~\u001a\u00020\n2\u0006\u0010\u007f\u001a\u00020\n2\u0006\u0010\u0015\u001a\u00020\n2\u0007\u0010\u0080\u0001\u001a\u00020\u0018H\u0002JX\u0010\u008a\u0001\u001a\u00020x2\u0006\u0010}\u001a\u00020\n2\u0006\u0010~\u001a\u00020\n2\n\u0010\u008b\u0001\u001a\u0005\u0018\u00010\u0083\u00012\n\u0010\u008c\u0001\u001a\u0005\u0018\u00010\u0083\u00012\n\u0010\u008d\u0001\u001a\u0005\u0018\u00010\u0083\u00012\u0007\u0010\u0085\u0001\u001a\u00020\u00182\u0007\u0010\u0080\u0001\u001a\u00020\u00182\u0007\u0010\u0086\u0001\u001a\u00020\u0018H\u0002J\u0018\u0010\u008e\u0001\u001a\u00020F2\u0007\u0010\u008f\u0001\u001a\u00020\u0018H\u0000¢\u0006\u0003\b\u0090\u0001J\u0019\u0010\u0091\u0001\u001a\u00020\n2\u0006\u0010\u007f\u001a\u00020\n2\u0006\u0010\u0015\u001a\u00020\nH\u0002J\u0012\u0010\u0092\u0001\u001a\u00020\u00182\u0007\u0010\u0093\u0001\u001a\u00020\u0012H\u0002J\u0010\u0010\u0094\u0001\u001a\u00020F2\u0007\u0010\u0095\u0001\u001a\u00020\u0018J\t\u0010\u0096\u0001\u001a\u00020FH\u0002J\u001a\u0010\u0097\u0001\u001a\u00020x2\u0007\u0010\u0098\u0001\u001a\u00020z2\b\b\u0002\u0010{\u001a\u00020\nJ\u0018\u0010\u0099\u0001\u001a\u00020F2\u0007\u0010\u008f\u0001\u001a\u00020\u0018H\u0000¢\u0006\u0003\b\u009a\u0001J\u0018\u0010\u009b\u0001\u001a\u00020\n2\u0007\u0010\u009c\u0001\u001a\u00020\u0018H\u0000¢\u0006\u0003\b\u009d\u0001J!\u0010\u009e\u0001\u001a\u00020\n2\u0007\u0010\u009f\u0001\u001a\u00020\u00182\u0007\u0010 \u0001\u001a\u00020\u0018H\u0000¢\u0006\u0003\b¡\u0001J\u0018\u0010¢\u0001\u001a\u00020\n2\u0007\u0010\u009c\u0001\u001a\u00020\u0018H\u0000¢\u0006\u0003\b£\u0001J\t\u0010¤\u0001\u001a\u00020FH\u0002J\u0012\u0010¥\u0001\u001a\u00020F2\u0007\u0010\u0098\u0001\u001a\u00020zH\u0002J\t\u0010¦\u0001\u001a\u00020FH\u0002R\u001c\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u000e\u0010\t\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u001c\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u0010\u0010\u0011\u001a\u0004\u0018\u00010\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0014\u001a\u0004\u0018\u00010\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0015\u001a\u0004\u0018\u00010\u0016X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0018X\u0082\u000e¢\u0006\u0002\n\u0000R\u001b\u0010\u0019\u001a\u00020\u001a8@X\u0080\u0084\u0002¢\u0006\f\n\u0004\b\u001d\u0010\u001e\u001a\u0004\b\u001b\u0010\u001cR\u001b\u0010\u001f\u001a\u00020 8@X\u0080\u0084\u0002¢\u0006\f\n\u0004\b#\u0010\u001e\u001a\u0004\b!\u0010\"R\u001b\u0010$\u001a\u00020%8@X\u0080\u0084\u0002¢\u0006\f\n\u0004\b(\u0010\u001e\u001a\u0004\b&\u0010'R\u001b\u0010)\u001a\u00020*8@X\u0080\u0084\u0002¢\u0006\f\n\u0004\b-\u0010\u001e\u001a\u0004\b+\u0010,R\u001b\u0010.\u001a\u00020/8@X\u0080\u0084\u0002¢\u0006\f\n\u0004\b2\u0010\u001e\u001a\u0004\b0\u00101R\u001b\u00103\u001a\u0002048@X\u0080\u0084\u0002¢\u0006\f\n\u0004\b7\u0010\u001e\u001a\u0004\b5\u00106R\u001b\u00108\u001a\u0002098BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b<\u0010\u001e\u001a\u0004\b:\u0010;R\u001b\u0010=\u001a\u00020>8@X\u0080\u0084\u0002¢\u0006\f\n\u0004\bA\u0010\u001e\u001a\u0004\b?\u0010@R\u000e\u0010B\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010C\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020F0E0DX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010G\u001a\u00020HX\u0082\u000e¢\u0006\u0002\n\u0000R\u001b\u0010I\u001a\u00020J8@X\u0080\u0084\u0002¢\u0006\f\n\u0004\bM\u0010\u001e\u001a\u0004\bK\u0010LR\u001b\u0010N\u001a\u00020O8@X\u0080\u0084\u0002¢\u0006\f\n\u0004\bR\u0010\u001e\u001a\u0004\bP\u0010QR\u001b\u0010S\u001a\u00020T8@X\u0080\u0084\u0002¢\u0006\f\n\u0004\bW\u0010\u001e\u001a\u0004\bU\u0010VR\u001b\u0010X\u001a\u00020Y8@X\u0080\u0084\u0002¢\u0006\f\n\u0004\b\\\u0010\u001e\u001a\u0004\bZ\u0010[R\u001b\u0010]\u001a\u00020^8@X\u0080\u0084\u0002¢\u0006\f\n\u0004\ba\u0010\u001e\u001a\u0004\b_\u0010`R\u001b\u0010b\u001a\u00020c8@X\u0080\u0084\u0002¢\u0006\f\n\u0004\bf\u0010\u001e\u001a\u0004\bd\u0010eR\u001a\u0010g\u001a\u00020\nX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bh\u0010i\"\u0004\bj\u0010kR\u0010\u0010l\u001a\u0004\u0018\u00010\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010m\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006¨\u0001"}, m1293d2 = {"Lcom/faceunity/core/support/FURenderBridge;", "", "()V", "cameraFacing", "Lcom/faceunity/core/enumeration/CameraFacingEnum;", "getCameraFacing$fu_core_all_featureRelease", "()Lcom/faceunity/core/enumeration/CameraFacingEnum;", "setCameraFacing$fu_core_all_featureRelease", "(Lcom/faceunity/core/enumeration/CameraFacingEnum;)V", "deviceOrientation", "", "externalInputType", "Lcom/faceunity/core/enumeration/FUExternalInputEnum;", "getExternalInputType$fu_core_all_featureRelease", "()Lcom/faceunity/core/enumeration/FUExternalInputEnum;", "setExternalInputType$fu_core_all_featureRelease", "(Lcom/faceunity/core/enumeration/FUExternalInputEnum;)V", "inputBufferMatrix", "Lcom/faceunity/core/enumeration/FUTransformMatrixEnum;", "inputOrientation", "inputTextureMatrix", "inputTextureType", "Lcom/faceunity/core/enumeration/FUInputTextureEnum;", "lastFrameRenderTexture", "", "mActionRecognitionController", "Lcom/faceunity/core/controller/action/ActionRecognitionController;", "getMActionRecognitionController$fu_core_all_featureRelease", "()Lcom/faceunity/core/controller/action/ActionRecognitionController;", "mActionRecognitionController$delegate", "Lkotlin/Lazy;", "mAnimationFilterController", "Lcom/faceunity/core/controller/animationFilter/AnimationFilterController;", "getMAnimationFilterController$fu_core_all_featureRelease", "()Lcom/faceunity/core/controller/animationFilter/AnimationFilterController;", "mAnimationFilterController$delegate", "mAntialiasingController", "Lcom/faceunity/core/controller/antialiasing/AntialiasingController;", "getMAntialiasingController$fu_core_all_featureRelease", "()Lcom/faceunity/core/controller/antialiasing/AntialiasingController;", "mAntialiasingController$delegate", "mAvatarController", "Lcom/faceunity/core/avatar/control/AvatarController;", "getMAvatarController$fu_core_all_featureRelease", "()Lcom/faceunity/core/avatar/control/AvatarController;", "mAvatarController$delegate", "mBgSegGreenController", "Lcom/faceunity/core/controller/bgSegGreen/BgSegGreenController;", "getMBgSegGreenController$fu_core_all_featureRelease", "()Lcom/faceunity/core/controller/bgSegGreen/BgSegGreenController;", "mBgSegGreenController$delegate", "mBodyBeautyController", "Lcom/faceunity/core/controller/bodyBeauty/BodyBeautyController;", "getMBodyBeautyController$fu_core_all_featureRelease", "()Lcom/faceunity/core/controller/bodyBeauty/BodyBeautyController;", "mBodyBeautyController$delegate", "mFURenderKit", "Lcom/faceunity/core/faceunity/FURenderKit;", "getMFURenderKit", "()Lcom/faceunity/core/faceunity/FURenderKit;", "mFURenderKit$delegate", "mFaceBeautyController", "Lcom/faceunity/core/controller/facebeauty/FaceBeautyController;", "getMFaceBeautyController$fu_core_all_featureRelease", "()Lcom/faceunity/core/controller/facebeauty/FaceBeautyController;", "mFaceBeautyController$delegate", "mFrameId", "mGLEventQueue", "", "Lkotlin/Function0;", "", "mGLThreadId", "", "mHairBeautyController", "Lcom/faceunity/core/controller/hairBeauty/HairBeautyController;", "getMHairBeautyController$fu_core_all_featureRelease", "()Lcom/faceunity/core/controller/hairBeauty/HairBeautyController;", "mHairBeautyController$delegate", "mLightMakeupController", "Lcom/faceunity/core/controller/littleMakeup/LightMakeupController;", "getMLightMakeupController$fu_core_all_featureRelease", "()Lcom/faceunity/core/controller/littleMakeup/LightMakeupController;", "mLightMakeupController$delegate", "mMakeupController", "Lcom/faceunity/core/controller/makeup/MakeupController;", "getMMakeupController$fu_core_all_featureRelease", "()Lcom/faceunity/core/controller/makeup/MakeupController;", "mMakeupController$delegate", "mMusicFilterController", "Lcom/faceunity/core/controller/musicFilter/MusicFilterController;", "getMMusicFilterController$fu_core_all_featureRelease", "()Lcom/faceunity/core/controller/musicFilter/MusicFilterController;", "mMusicFilterController$delegate", "mPosterController", "Lcom/faceunity/core/controller/poster/PosterController;", "getMPosterController$fu_core_all_featureRelease", "()Lcom/faceunity/core/controller/poster/PosterController;", "mPosterController$delegate", "mPropContainerController", "Lcom/faceunity/core/controller/prop/PropContainerController;", "getMPropContainerController$fu_core_all_featureRelease", "()Lcom/faceunity/core/controller/prop/PropContainerController;", "mPropContainerController$delegate", "mRotationMode", "getMRotationMode$fu_core_all_featureRelease", "()I", "setMRotationMode$fu_core_all_featureRelease", "(I)V", "outputMatrix", "renderLock", "calculateOrientationMode", "calculateRotModeLagacy", "calculateRotModeLagacy$fu_core_all_featureRelease", "calculateRotationMode", "clearCacheResource", "clearCacheResource$fu_core_all_featureRelease", "doGLThreadAction", "unit", "doGLThreadAction$fu_core_all_featureRelease", "drawFrame", "Lcom/faceunity/core/entity/FURenderOutputData;", "data", "Lcom/faceunity/core/entity/FURenderInputData;", "type", "drawFrameBeautify", StreamInformation.KEY_WIDTH, StreamInformation.KEY_HEIGHT, "texId", "needChangedTexture", "drawFrameDualInput", "buffer", "", "imgType", "needReadBack", "needChangedBuffer", "drawFrameForPoster", "drawFrameImg", "drawFrameTexture", "drawFrameYUV", "y_buffer", "u_buffer", "v_buffer", "fuSetARMeshV2", "enable", "fuSetARMeshV2$fu_core_all_featureRelease", "getRenderFlags", "needChangeWithAndHeight", "matrix", "onDestroy", "isSafe", "prepareDrawFrame", "renderWithInput", "input", "setDynamicQualityControl", "setDynamicQualityControl$fu_core_all_featureRelease", "setUseAsyncAIInference", "isUse", "setUseAsyncAIInference$fu_core_all_featureRelease", "setUseMultiBuffer", "isUseMultiGPUTexture", "isUseMultiCPUBuffer", "setUseMultiBuffer$fu_core_all_featureRelease", "setUseTexAsync", "setUseTexAsync$fu_core_all_featureRelease", "updateFlipMode", "updateRenderEnvironment", "updateRotationMode", "Companion", "fu_core_all_featureRelease"}, m1294k = 1, m1295mv = {1, 1, 16})
public final class FURenderBridge {

    public static final Companion INSTANCE = new Companion(null);
    private static volatile FURenderBridge INSTANCE = null;
    public static final String TAG = "KIT_FURenderBridge";
    private CameraFacingEnum cameraFacing;
    private int deviceOrientation;
    private FUExternalInputEnum externalInputType;
    private FUTransformMatrixEnum inputBufferMatrix;
    private int inputOrientation;
    private FUTransformMatrixEnum inputTextureMatrix;
    private FUInputTextureEnum inputTextureType;
    private boolean lastFrameRenderTexture;

    private final Lazy mActionRecognitionController;

    private final Lazy mAnimationFilterController;

    private final Lazy mAntialiasingController;

    private final Lazy mAvatarController;

    private final Lazy mBgSegGreenController;

    private final Lazy mBodyBeautyController;

    private final Lazy mFURenderKit;

    private final Lazy mFaceBeautyController;
    private int mFrameId;
    private List<Function0<Unit>> mGLEventQueue;
    private long mGLThreadId;

    private final Lazy mHairBeautyController;

    private final Lazy mLightMakeupController;

    private final Lazy mMakeupController;

    private final Lazy mMusicFilterController;

    private final Lazy mPosterController;

    private final Lazy mPropContainerController;
    private int mRotationMode;
    private FUTransformMatrixEnum outputMatrix;
    private final Object renderLock;

    @Metadata(m1291bv = {1, 0, 3}, m1294k = 3, m1295mv = {1, 1, 16})
    public final class WhenMappings {
        public static final int[] $EnumSwitchMapping$0;
        public static final int[] $EnumSwitchMapping$1;

        static {
            int[] iArr = new int[FUExternalInputEnum.values().length];
            $EnumSwitchMapping$0 = iArr;
            iArr[FUExternalInputEnum.EXTERNAL_INPUT_TYPE_IMAGE.ordinal()] = 1;
            iArr[FUExternalInputEnum.EXTERNAL_INPUT_TYPE_VIDEO.ordinal()] = 2;
            int[] iArr2 = new int[FUExternalInputEnum.values().length];
            $EnumSwitchMapping$1 = iArr2;
            iArr2[FUExternalInputEnum.EXTERNAL_INPUT_TYPE_IMAGE.ordinal()] = 1;
            iArr2[FUExternalInputEnum.EXTERNAL_INPUT_TYPE_VIDEO.ordinal()] = 2;
        }
    }

    private final FURenderKit getMFURenderKit() {
        return (FURenderKit) this.mFURenderKit.getValue();
    }

    private final int getRenderFlags(int texId, int inputTextureType) {
        if (texId > 0) {
            return inputTextureType;
        }
        return 0;
    }

    public final ActionRecognitionController getMActionRecognitionController$fu_core_all_featureRelease() {
        return (ActionRecognitionController) this.mActionRecognitionController.getValue();
    }

    public final AnimationFilterController getMAnimationFilterController$fu_core_all_featureRelease() {
        return (AnimationFilterController) this.mAnimationFilterController.getValue();
    }

    public final AntialiasingController getMAntialiasingController$fu_core_all_featureRelease() {
        return (AntialiasingController) this.mAntialiasingController.getValue();
    }

    public final AvatarController getMAvatarController$fu_core_all_featureRelease() {
        return (AvatarController) this.mAvatarController.getValue();
    }

    public final BgSegGreenController getMBgSegGreenController$fu_core_all_featureRelease() {
        return (BgSegGreenController) this.mBgSegGreenController.getValue();
    }

    public final BodyBeautyController getMBodyBeautyController$fu_core_all_featureRelease() {
        return (BodyBeautyController) this.mBodyBeautyController.getValue();
    }

    public final FaceBeautyController getMFaceBeautyController$fu_core_all_featureRelease() {
        return (FaceBeautyController) this.mFaceBeautyController.getValue();
    }

    public final HairBeautyController getMHairBeautyController$fu_core_all_featureRelease() {
        return (HairBeautyController) this.mHairBeautyController.getValue();
    }

    public final LightMakeupController getMLightMakeupController$fu_core_all_featureRelease() {
        return (LightMakeupController) this.mLightMakeupController.getValue();
    }

    public final MakeupController getMMakeupController$fu_core_all_featureRelease() {
        return (MakeupController) this.mMakeupController.getValue();
    }

    public final MusicFilterController getMMusicFilterController$fu_core_all_featureRelease() {
        return (MusicFilterController) this.mMusicFilterController.getValue();
    }

    public final PosterController getMPosterController$fu_core_all_featureRelease() {
        return (PosterController) this.mPosterController.getValue();
    }

    public final PropContainerController getMPropContainerController$fu_core_all_featureRelease() {
        return (PropContainerController) this.mPropContainerController.getValue();
    }

    private FURenderBridge() {
        this.renderLock = new Object();
        this.mFURenderKit = LazyKt.lazy(new Function0<FURenderKit>() {
            @Override
            public final FURenderKit invoke() {
                return FURenderKit.INSTANCE.getInstance();
            }
        });
        this.mRotationMode = -1;
        this.inputOrientation = -1;
        this.deviceOrientation = -1;
        this.mFaceBeautyController = LazyKt.lazy(new Function0<FaceBeautyController>() {
            @Override
            public final FaceBeautyController invoke() {
                return new FaceBeautyController();
            }
        });
        this.mMakeupController = LazyKt.lazy(new Function0<MakeupController>() {
            @Override
            public final MakeupController invoke() {
                return new MakeupController();
            }
        });
        this.mActionRecognitionController = LazyKt.lazy(new Function0<ActionRecognitionController>() {
            @Override
            public final ActionRecognitionController invoke() {
                return new ActionRecognitionController();
            }
        });
        this.mAnimationFilterController = LazyKt.lazy(new Function0<AnimationFilterController>() {
            @Override
            public final AnimationFilterController invoke() {
                return new AnimationFilterController();
            }
        });
        this.mAntialiasingController = LazyKt.lazy(new Function0<AntialiasingController>() {
            @Override
            public final AntialiasingController invoke() {
                return new AntialiasingController();
            }
        });
        this.mBgSegGreenController = LazyKt.lazy(new Function0<BgSegGreenController>() {
            @Override
            public final BgSegGreenController invoke() {
                return new BgSegGreenController();
            }
        });
        this.mBodyBeautyController = LazyKt.lazy(new Function0<BodyBeautyController>() {
            @Override
            public final BodyBeautyController invoke() {
                return new BodyBeautyController();
            }
        });
        this.mHairBeautyController = LazyKt.lazy(new Function0<HairBeautyController>() {
            @Override
            public final HairBeautyController invoke() {
                return new HairBeautyController();
            }
        });
        this.mLightMakeupController = LazyKt.lazy(new Function0<LightMakeupController>() {
            @Override
            public final LightMakeupController invoke() {
                return new LightMakeupController();
            }
        });
        this.mMusicFilterController = LazyKt.lazy(new Function0<MusicFilterController>() {
            @Override
            public final MusicFilterController invoke() {
                return new MusicFilterController();
            }
        });
        this.mPropContainerController = LazyKt.lazy(new Function0<PropContainerController>() {
            @Override
            public final PropContainerController invoke() {
                return new PropContainerController();
            }
        });
        this.mPosterController = LazyKt.lazy(new Function0<PosterController>() {
            @Override
            public final PosterController invoke() {
                return new PosterController();
            }
        });
        this.mAvatarController = LazyKt.lazy(new Function0<AvatarController>() {
            @Override
            public final AvatarController invoke() {
                return new AvatarController();
            }
        });
        List<Function0<Unit>> listSynchronizedList = Collections.synchronizedList(new ArrayList(16));
        Intrinsics.checkExpressionValueIsNotNull(listSynchronizedList, "Collections.synchronized…rrayList<() -> Unit>(16))");
        this.mGLEventQueue = listSynchronizedList;
        this.mGLThreadId = -1L;
    }

    public FURenderBridge(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    @Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\r\u0010\u0007\u001a\u00020\u0004H\u0001¢\u0006\u0002\b\bR\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000¨\u0006\t"}, m1293d2 = {"Lcom/faceunity/core/support/FURenderBridge$Companion;", "", "()V", "INSTANCE", "Lcom/faceunity/core/support/FURenderBridge;", "TAG", "", "getInstance", "getInstance$fu_core_all_featureRelease", "fu_core_all_featureRelease"}, m1294k = 1, m1295mv = {1, 1, 16})
    public static final class Companion {
        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        public final FURenderBridge getInstance$fu_core_all_featureRelease() {
            if (FURenderBridge.INSTANCE == null) {
                synchronized (this) {
                    if (FURenderBridge.INSTANCE == null) {
                        FURenderBridge.INSTANCE = new FURenderBridge(null);
                    }
                    Unit unit = Unit.INSTANCE;
                }
            }
            FURenderBridge fURenderBridge = FURenderBridge.INSTANCE;
            if (fURenderBridge == null) {
                Intrinsics.throwNpe();
            }
            return fURenderBridge;
        }
    }

    public final void onDestroy(boolean isSafe) {
        synchronized (this.renderLock) {
            this.mRotationMode = -1;
            this.externalInputType = null;
            this.cameraFacing = null;
            this.inputOrientation = -1;
            this.deviceOrientation = -1;
            this.inputTextureType = null;
            this.inputTextureMatrix = null;
            this.inputBufferMatrix = null;
            this.outputMatrix = null;
            this.mGLThreadId = -1L;
            this.mFrameId = 0;
            BundleManager.INSTANCE.getInstance$fu_core_all_featureRelease().release$fu_core_all_featureRelease();
            this.mGLEventQueue.clear();
            SDKController.INSTANCE.onCameraChange$fu_core_all_featureRelease();
            SDKController.INSTANCE.humanProcessorReset$fu_core_all_featureRelease();
            SDKController.INSTANCE.done$fu_core_all_featureRelease();
            if (isSafe) {
                SDKController.INSTANCE.onDeviceLostSafe$fu_core_all_featureRelease();
            } else {
                SDKController.INSTANCE.onDeviceLost$fu_core_all_featureRelease();
            }
            Unit unit = Unit.INSTANCE;
        }
    }

    public final int getMRotationMode() {
        return this.mRotationMode;
    }

    public final void setMRotationMode$fu_core_all_featureRelease(int i) {
        this.mRotationMode = i;
    }

    public final CameraFacingEnum getCameraFacing() {
        return this.cameraFacing;
    }

    public final void setCameraFacing$fu_core_all_featureRelease(CameraFacingEnum cameraFacingEnum) {
        this.cameraFacing = cameraFacingEnum;
    }

    public final FUExternalInputEnum getExternalInputType() {
        return this.externalInputType;
    }

    public final void setExternalInputType$fu_core_all_featureRelease(FUExternalInputEnum fUExternalInputEnum) {
        this.externalInputType = fUExternalInputEnum;
    }

    public static FURenderOutputData renderWithInput$default(FURenderBridge fURenderBridge, FURenderInputData fURenderInputData, int i, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = 0;
        }
        return fURenderBridge.renderWithInput(fURenderInputData, i);
    }

    public final FURenderOutputData renderWithInput(FURenderInputData input, int type) {
        FURenderOutputData fURenderOutputDataDrawFrame;
        Intrinsics.checkParameterIsNotNull(input, "input");
        synchronized (this.renderLock) {
            updateRenderEnvironment(input);
            Thread threadCurrentThread = Thread.currentThread();
            Intrinsics.checkExpressionValueIsNotNull(threadCurrentThread, "Thread.currentThread()");
            this.mGLThreadId = threadCurrentThread.getId();
            fURenderOutputDataDrawFrame = drawFrame(input, type);
        }
        return fURenderOutputDataDrawFrame;
    }

    private final FURenderOutputData drawFrame(FURenderInputData data, int type) {
        prepareDrawFrame();
        FURenderInputData.FUTexture texture = data.getTexture();
        int texId = texture != null ? texture.getTexId() : 0;
        FURenderInputData.FUTexture texture2 = data.getTexture();
        FUInputTextureEnum inputTextureType = texture2 != null ? texture2.getInputTextureType() : null;
        FURenderInputData.FUImageBuffer imageBuffer = data.getImageBuffer();
        byte[] buffer = imageBuffer != null ? imageBuffer.getBuffer() : null;
        FURenderInputData.FUImageBuffer imageBuffer2 = data.getImageBuffer();
        FUInputBufferEnum inputBufferType = imageBuffer2 != null ? imageBuffer2.getInputBufferType() : null;
        boolean isNeedBufferReturn = data.getRenderConfig().getIsNeedBufferReturn();
        if (data.getWidth() <= 0 || data.getHeight() <= 0) {
            FULogger.m294e(TAG, "renderInput data is illegal   width:" + data.getWidth() + "  height:" + data.getHeight() + "  ");
            return new FURenderOutputData(null, null, 3, null);
        }
        FUTransformMatrixEnum fUTransformMatrixEnum = this.inputBufferMatrix;
        if (fUTransformMatrixEnum == null) {
            fUTransformMatrixEnum = FUTransformMatrixEnum.CCROT0;
        }
        boolean zNeedChangeWithAndHeight = needChangeWithAndHeight(fUTransformMatrixEnum);
        FUTransformMatrixEnum fUTransformMatrixEnum2 = this.inputTextureMatrix;
        if (fUTransformMatrixEnum2 == null) {
            fUTransformMatrixEnum2 = FUTransformMatrixEnum.CCROT0;
        }
        boolean zNeedChangeWithAndHeight2 = needChangeWithAndHeight(fUTransformMatrixEnum2);
        FUTransformMatrixEnum fUTransformMatrixEnum3 = this.outputMatrix;
        if (fUTransformMatrixEnum3 == null) {
            fUTransformMatrixEnum3 = FUTransformMatrixEnum.CCROT0;
        }
        boolean zNeedChangeWithAndHeight3 = needChangeWithAndHeight(fUTransformMatrixEnum3);
        boolean z = (zNeedChangeWithAndHeight && !zNeedChangeWithAndHeight3) || (!zNeedChangeWithAndHeight && zNeedChangeWithAndHeight3);
        boolean z2 = (zNeedChangeWithAndHeight2 && !zNeedChangeWithAndHeight3) || (!zNeedChangeWithAndHeight2 && zNeedChangeWithAndHeight3);
        if (data.getRenderConfig().getIsRenderFaceBeautyOnly() && texId >= 0 && inputTextureType != null) {
            this.lastFrameRenderTexture = false;
            return drawFrameBeautify(data.getWidth(), data.getHeight(), texId, inputTextureType.getType(), z2);
        }
        if (inputBufferType == FUInputBufferEnum.FU_FORMAT_YUV_BUFFER) {
            this.lastFrameRenderTexture = false;
            int width = data.getWidth();
            int height = data.getHeight();
            FURenderInputData.FUImageBuffer imageBuffer3 = data.getImageBuffer();
            byte[] buffer2 = imageBuffer3 != null ? imageBuffer3.getBuffer() : null;
            FURenderInputData.FUImageBuffer imageBuffer4 = data.getImageBuffer();
            byte[] buffer1 = imageBuffer4 != null ? imageBuffer4.getBuffer1() : null;
            FURenderInputData.FUImageBuffer imageBuffer5 = data.getImageBuffer();
            return drawFrameYUV(width, height, buffer2, buffer1, imageBuffer5 != null ? imageBuffer5.getBuffer2() : null, isNeedBufferReturn, z2, z);
        }
        if (texId > 0 && inputTextureType != null && buffer != null && inputBufferType != null) {
            if (type == 1) {
                this.lastFrameRenderTexture = false;
                return drawFrameForPoster(data.getWidth(), data.getHeight(), texId, inputTextureType.getType(), buffer, inputBufferType.getType());
            }
            this.lastFrameRenderTexture = false;
            return drawFrameDualInput(data.getWidth(), data.getHeight(), texId, inputTextureType.getType(), buffer, inputBufferType.getType(), isNeedBufferReturn, z2, z);
        }
        if (texId > 0 && inputTextureType != null) {
            if (!this.lastFrameRenderTexture) {
                this.lastFrameRenderTexture = true;
                clearCacheResource$fu_core_all_featureRelease();
            }
            return drawFrameTexture(data.getWidth(), data.getHeight(), texId, inputTextureType.getType(), z2);
        }
        if (buffer != null && inputBufferType != null) {
            this.lastFrameRenderTexture = false;
            return drawFrameImg(data.getWidth(), data.getHeight(), buffer, inputBufferType.getType(), isNeedBufferReturn, z2, z);
        }
        return new FURenderOutputData(null, null, 3, null);
    }

    private final boolean needChangeWithAndHeight(FUTransformMatrixEnum matrix) {
        return matrix == FUTransformMatrixEnum.CCROT90 || matrix == FUTransformMatrixEnum.CCROT270 || matrix == FUTransformMatrixEnum.CCROT90_FLIPVERTICAL || matrix == FUTransformMatrixEnum.CCROT90_FLIPHORIZONTAL;
    }

    private final FURenderOutputData drawFrameForPoster(int width, int height, int texId, int inputTextureType, byte[] buffer, int imgType) {
        if (texId <= 0) {
            FULogger.m294e(TAG, "drawFrameForPoster data is illegal  texId:" + texId);
            return new FURenderOutputData(null, null, 3, null);
        }
        int renderFlags = getRenderFlags(texId, inputTextureType);
        SDKController sDKController = SDKController.INSTANCE;
        int i = this.mFrameId;
        this.mFrameId = i + 1;
        int iFuRenderDualInput$fu_core_all_featureRelease$default = SDKController.fuRenderDualInput$fu_core_all_featureRelease$default(sDKController, width, height, i, new int[]{getMPosterController$fu_core_all_featureRelease().getMControllerBundleHandle()}, texId, renderFlags, buffer, imgType, 0, 0, null, 1792, null);
        if (iFuRenderDualInput$fu_core_all_featureRelease$default <= 0) {
            SDKController.INSTANCE.callBackSystemError$fu_core_all_featureRelease();
        }
        return new FURenderOutputData(new FURenderOutputData.FUTexture(iFuRenderDualInput$fu_core_all_featureRelease$default, width, height), null, 2, null);
    }

    private final FURenderOutputData drawFrameBeautify(int width, int height, int texId, int inputTextureType, boolean needChangedTexture) {
        int renderFlags = getRenderFlags(texId, inputTextureType);
        SDKController sDKController = SDKController.INSTANCE;
        int i = this.mFrameId;
        this.mFrameId = i + 1;
        int iFuRenderBeautifyOnly$fu_core_all_featureRelease = sDKController.fuRenderBeautifyOnly$fu_core_all_featureRelease(width, height, i, BundleManager.INSTANCE.getInstance$fu_core_all_featureRelease().getRenderBindBundles$fu_core_all_featureRelease(), renderFlags, texId);
        if (iFuRenderBeautifyOnly$fu_core_all_featureRelease <= 0) {
            SDKController.INSTANCE.callBackSystemError$fu_core_all_featureRelease();
        }
        int i2 = needChangedTexture ? width : height;
        if (needChangedTexture) {
            width = height;
        }
        return new FURenderOutputData(new FURenderOutputData.FUTexture(iFuRenderBeautifyOnly$fu_core_all_featureRelease, width, i2), null, 2, null);
    }

    private final FURenderOutputData drawFrameYUV(int width, int height, byte[] y_buffer, byte[] u_buffer, byte[] v_buffer, boolean needReadBack, boolean needChangedTexture, boolean needChangedBuffer) {
        if (y_buffer == null || u_buffer == null || v_buffer == null) {
            FULogger.m294e(TAG, "drawFrameYUV data is illegal  y_buffer:" + (y_buffer == null) + "  u_buffer:" + (u_buffer == null) + " v_buffer:" + (v_buffer == null) + " width:" + width + "  height:" + height + "  ");
            return new FURenderOutputData(null, null, 3, null);
        }
        int i = needChangedTexture ? width : height;
        int i2 = needChangedTexture ? height : width;
        int i3 = needChangedBuffer ? width : height;
        int i4 = needChangedBuffer ? height : width;
        int i5 = i4 >> 1;
        int renderFlags = getRenderFlags(0, 0);
        byte[] bArrYUVTOVN21 = BitmapUtils.INSTANCE.YUVTOVN21(y_buffer, u_buffer, v_buffer);
        byte[] bArr = needReadBack ? new byte[bArrYUVTOVN21.length] : null;
        SDKController sDKController = SDKController.INSTANCE;
        int i6 = this.mFrameId;
        this.mFrameId = i6 + 1;
        int iFuRenderImg$fu_core_all_featureRelease = sDKController.fuRenderImg$fu_core_all_featureRelease(width, height, i6, BundleManager.INSTANCE.getInstance$fu_core_all_featureRelease().getRenderBindBundles$fu_core_all_featureRelease(), renderFlags, bArrYUVTOVN21, FUInputBufferEnum.FU_FORMAT_NV21_BUFFER.getType(), i4, i3, bArr);
        if (iFuRenderImg$fu_core_all_featureRelease <= 0) {
            SDKController.INSTANCE.callBackSystemError$fu_core_all_featureRelease();
        }
        if (needReadBack) {
            byte[] bArr2 = new byte[y_buffer.length];
            byte[] bArr3 = new byte[u_buffer.length];
            byte[] bArr4 = new byte[v_buffer.length];
            BitmapUtils bitmapUtils = BitmapUtils.INSTANCE;
            if (bArr == null) {
                Intrinsics.throwNpe();
            }
            bitmapUtils.NV21ToYUV(bArr, bArr2, bArr3, bArr4);
            return new FURenderOutputData(new FURenderOutputData.FUTexture(iFuRenderImg$fu_core_all_featureRelease, i2, i), new FURenderOutputData.FUImageBuffer(i4, i3, DecimalUtils.copyArray(bArr2), DecimalUtils.copyArray(bArr3), DecimalUtils.copyArray(bArr4), i4, i5, i5));
        }
        return new FURenderOutputData(new FURenderOutputData.FUTexture(iFuRenderImg$fu_core_all_featureRelease, i2, i), null, 2, null);
    }

    private final FURenderOutputData drawFrameDualInput(int width, int height, int texId, int inputTextureType, byte[] buffer, int imgType, boolean needReadBack, boolean needChangedTexture, boolean needChangedBuffer) {
        int renderFlags = getRenderFlags(texId, inputTextureType);
        int i = needChangedTexture ? width : height;
        int i2 = needChangedTexture ? height : width;
        int i3 = needChangedBuffer ? width : height;
        int i4 = needChangedBuffer ? height : width;
        byte[] bArr = needReadBack ? new byte[buffer.length] : null;
        SDKController sDKController = SDKController.INSTANCE;
        int i5 = this.mFrameId;
        this.mFrameId = i5 + 1;
        int iFuRenderDualInput$fu_core_all_featureRelease = sDKController.fuRenderDualInput$fu_core_all_featureRelease(width, height, i5, BundleManager.INSTANCE.getInstance$fu_core_all_featureRelease().getRenderBindBundles$fu_core_all_featureRelease(), texId, renderFlags, buffer, imgType, i4, i3, bArr);
        if (iFuRenderDualInput$fu_core_all_featureRelease <= 0) {
            SDKController.INSTANCE.callBackSystemError$fu_core_all_featureRelease();
        }
        if (needReadBack) {
            return new FURenderOutputData(new FURenderOutputData.FUTexture(iFuRenderDualInput$fu_core_all_featureRelease, i2, i), new FURenderOutputData.FUImageBuffer(i4, i3, bArr, null, null, 0, 0, 0, 248, null));
        }
        return new FURenderOutputData(new FURenderOutputData.FUTexture(iFuRenderDualInput$fu_core_all_featureRelease, i2, i), null, 2, null);
    }

    private final FURenderOutputData drawFrameTexture(int width, int height, int texId, int inputTextureType, boolean needChangedTexture) {
        int i = needChangedTexture ? width : height;
        int i2 = needChangedTexture ? height : width;
        int renderFlags = getRenderFlags(texId, inputTextureType);
        SDKController sDKController = SDKController.INSTANCE;
        int i3 = this.mFrameId;
        this.mFrameId = i3 + 1;
        int iFuRenderTexture$fu_core_all_featureRelease = sDKController.fuRenderTexture$fu_core_all_featureRelease(width, height, i3, BundleManager.INSTANCE.getInstance$fu_core_all_featureRelease().getRenderBindBundles$fu_core_all_featureRelease(), texId, renderFlags);
        if (iFuRenderTexture$fu_core_all_featureRelease <= 0) {
            SDKController.INSTANCE.callBackSystemError$fu_core_all_featureRelease();
        }
        return new FURenderOutputData(new FURenderOutputData.FUTexture(iFuRenderTexture$fu_core_all_featureRelease, i2, i), null, 2, null);
    }

    public final FURenderOutputData drawFrameImg(int width, int height, byte[] buffer, int imgType, boolean needReadBack, boolean needChangedTexture, boolean needChangedBuffer) {
        int i = needChangedTexture ? width : height;
        int i2 = needChangedTexture ? height : width;
        int i3 = needChangedBuffer ? width : height;
        int i4 = needChangedBuffer ? height : width;
        byte[] bArr = needReadBack ? new byte[buffer.length] : null;
        int renderFlags = getRenderFlags(0, 0);
        SDKController sDKController = SDKController.INSTANCE;
        int i5 = this.mFrameId;
        this.mFrameId = i5 + 1;
        int iFuRenderImg$fu_core_all_featureRelease = sDKController.fuRenderImg$fu_core_all_featureRelease(width, height, i5, BundleManager.INSTANCE.getInstance$fu_core_all_featureRelease().getRenderBindBundles$fu_core_all_featureRelease(), renderFlags, buffer, imgType, i4, i3, bArr);
        if (iFuRenderImg$fu_core_all_featureRelease <= 0) {
            SDKController.INSTANCE.callBackSystemError$fu_core_all_featureRelease();
        }
        if (needReadBack) {
            return new FURenderOutputData(new FURenderOutputData.FUTexture(iFuRenderImg$fu_core_all_featureRelease, i2, i), new FURenderOutputData.FUImageBuffer(i4, i3, bArr, null, null, 0, 0, 0, 248, null));
        }
        return new FURenderOutputData(new FURenderOutputData.FUTexture(iFuRenderImg$fu_core_all_featureRelease, i2, i), null, 2, null);
    }

    private final void updateRenderEnvironment(FURenderInputData input) {
        boolean z;
        FURenderInputData.FURenderConfig renderConfig = input.getRenderConfig();
        boolean z2 = true;
        if (this.externalInputType == renderConfig.getExternalInputType() && this.inputOrientation == renderConfig.getInputOrientation() && this.deviceOrientation == renderConfig.getDeviceOrientation()) {
            z = false;
        } else {
            this.externalInputType = renderConfig.getExternalInputType();
            this.inputOrientation = renderConfig.getInputOrientation();
            this.deviceOrientation = renderConfig.getDeviceOrientation();
            z = true;
        }
        if (this.cameraFacing != renderConfig.getCameraFacing()) {
            SDKController.INSTANCE.clearCacheResource$fu_core_all_featureRelease();
            this.cameraFacing = renderConfig.getCameraFacing();
        } else {
            z2 = false;
        }
        if (z2) {
            updateFlipMode();
        } else if (z) {
            updateRotationMode();
        }
        if (renderConfig.getInputTextureMatrix() != this.inputTextureMatrix) {
            this.inputTextureMatrix = renderConfig.getInputTextureMatrix();
            SDKController.INSTANCE.setInputCameraTextureMatrix$fu_core_all_featureRelease(renderConfig.getInputTextureMatrix().getIndex());
        }
        if (renderConfig.getInputBufferMatrix() != this.inputBufferMatrix) {
            this.inputBufferMatrix = renderConfig.getInputBufferMatrix();
            SDKController.INSTANCE.setInputCameraBufferMatrix$fu_core_all_featureRelease(renderConfig.getInputBufferMatrix().getIndex());
        }
        if (renderConfig.getOutputMatrix() != this.outputMatrix) {
            this.outputMatrix = renderConfig.getOutputMatrix();
            if (renderConfig.getOutputMatrixEnable()) {
                SDKController.INSTANCE.setOutputMatrix$fu_core_all_featureRelease(renderConfig.getOutputMatrix().getIndex());
            }
        }
    }

    private final void prepareDrawFrame() {
        while (!this.mGLEventQueue.isEmpty()) {
            this.mGLEventQueue.remove(0).invoke();
        }
    }

    private final void updateRotationMode() {
        int iCalculateRotationMode = calculateRotationMode();
        if (this.mRotationMode == iCalculateRotationMode) {
            return;
        }
        this.mRotationMode = iCalculateRotationMode;
        SDKController.INSTANCE.onCameraChange$fu_core_all_featureRelease();
        SDKController.INSTANCE.humanProcessorReset$fu_core_all_featureRelease();
        SDKController.INSTANCE.setDefaultRotationMode$fu_core_all_featureRelease(this.mRotationMode);
        if (getMFURenderKit().getBgSegGreen() != null) {
            getMBgSegGreenController$fu_core_all_featureRelease().updateRotationMode$fu_core_all_featureRelease();
        }
        getMPropContainerController$fu_core_all_featureRelease().updateRotationMode$fu_core_all_featureRelease();
    }

    private final void updateFlipMode() {
        int iCalculateRotationMode = calculateRotationMode();
        if (this.mRotationMode != iCalculateRotationMode) {
            this.mRotationMode = iCalculateRotationMode;
            SDKController.INSTANCE.onCameraChange$fu_core_all_featureRelease();
            SDKController.INSTANCE.humanProcessorReset$fu_core_all_featureRelease();
            SDKController.INSTANCE.setDefaultRotationMode$fu_core_all_featureRelease(this.mRotationMode);
        }
        if (getMFURenderKit().getBgSegGreen() != null) {
            getMBgSegGreenController$fu_core_all_featureRelease().updateFlipMode$fu_core_all_featureRelease();
        }
        if (getMFURenderKit().getMakeup() != null) {
            getMMakeupController$fu_core_all_featureRelease().updateFlipMode$fu_core_all_featureRelease();
        }
        getMPropContainerController$fu_core_all_featureRelease().updateFlipMode$fu_core_all_featureRelease();
    }

    private final int calculateRotationMode() {
        FUExternalInputEnum fUExternalInputEnum = this.externalInputType;
        if (fUExternalInputEnum != null) {
            int i = WhenMappings.$EnumSwitchMapping$0[fUExternalInputEnum.ordinal()];
            if (i == 1) {
                return 0;
            }
            if (i == 2) {
                int i2 = this.inputOrientation;
                if (i2 == 90) {
                    return 3;
                }
                if (i2 != 180) {
                    return i2 != 270 ? 0 : 1;
                }
                return 2;
            }
        }
        if (this.cameraFacing == CameraFacingEnum.CAMERA_FRONT) {
            return (((this.inputOrientation + this.deviceOrientation) + 90) % PrintImageUtil.ROUND_ROTATE) / 90;
        }
        return (((this.inputOrientation - this.deviceOrientation) + 270) % PrintImageUtil.ROUND_ROTATE) / 90;
    }

    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final int calculateOrientationMode() {
        /*
            r8 = this;
            com.faceunity.core.enumeration.FUExternalInputEnum r0 = r8.externalInputType
            r1 = 3
            r2 = 180(0xb4, float:2.52E-43)
            r3 = 90
            r4 = 0
            r5 = 2
            r6 = 1
            if (r0 != 0) goto Ld
            goto L19
        Ld:
            int[] r7 = com.faceunity.core.support.FURenderBridge.WhenMappings.$EnumSwitchMapping$1
            int r0 = r0.ordinal()
            r0 = r7[r0]
            if (r0 == r6) goto L40
            if (r0 == r5) goto L31
        L19:
            com.faceunity.core.enumeration.CameraFacingEnum r0 = r8.cameraFacing
            com.faceunity.core.enumeration.CameraFacingEnum r7 = com.faceunity.core.enumeration.CameraFacingEnum.CAMERA_FRONT
            if (r0 != r7) goto L28
            int r0 = r8.deviceOrientation
            if (r0 == 0) goto L3c
            if (r0 == r3) goto L40
            if (r0 == r2) goto L41
            goto L3e
        L28:
            int r0 = r8.deviceOrientation
            if (r0 == 0) goto L41
            if (r0 == r3) goto L40
            if (r0 == r2) goto L3c
            goto L3e
        L31:
            int r0 = r8.inputOrientation
            if (r0 == r3) goto L41
            if (r0 == r2) goto L3e
            r1 = 270(0x10e, float:3.78E-43)
            if (r0 == r1) goto L3c
            goto L40
        L3c:
            r1 = r6
            goto L41
        L3e:
            r1 = r5
            goto L41
        L40:
            r1 = r4
        L41:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.faceunity.core.support.FURenderBridge.calculateOrientationMode():int");
    }

    public final int calculateRotModeLagacy$fu_core_all_featureRelease() {
        if (this.inputOrientation == 270) {
            if (this.cameraFacing == CameraFacingEnum.CAMERA_FRONT) {
                return this.deviceOrientation / 90;
            }
            return (this.deviceOrientation - 180) / 90;
        }
        if (this.cameraFacing == CameraFacingEnum.CAMERA_FRONT) {
            return (this.deviceOrientation + 180) / 90;
        }
        return this.deviceOrientation / 90;
    }

    public final int setUseAsyncAIInference$fu_core_all_featureRelease(boolean isUse) {
        return SDKController.INSTANCE.setUseAsyncAIInference$fu_core_all_featureRelease(isUse ? 1 : 0);
    }

    public final int setUseMultiBuffer$fu_core_all_featureRelease(boolean isUseMultiGPUTexture, boolean isUseMultiCPUBuffer) {
        return SDKController.INSTANCE.setUseMultiBuffer$fu_core_all_featureRelease(isUseMultiGPUTexture ? 1 : 0, isUseMultiCPUBuffer ? 1 : 0);
    }

    public final int setUseTexAsync$fu_core_all_featureRelease(boolean isUse) {
        return SDKController.INSTANCE.setUseTexAsync$fu_core_all_featureRelease(isUse ? 1 : 0);
    }

    public final void setDynamicQualityControl$fu_core_all_featureRelease(boolean enable) {
        SDKController.INSTANCE.fuSetDynamicQualityControl$fu_core_all_featureRelease(enable);
    }

    public final void fuSetARMeshV2$fu_core_all_featureRelease(boolean enable) {
        SDKController.INSTANCE.fuSetARMeshV2$fu_core_all_featureRelease(enable);
    }

    public final void clearCacheResource$fu_core_all_featureRelease() {
        SDKController.INSTANCE.clearCacheResource$fu_core_all_featureRelease();
    }

    public final void doGLThreadAction$fu_core_all_featureRelease(Function0<Unit> unit) {
        Intrinsics.checkParameterIsNotNull(unit, "unit");
        Thread threadCurrentThread = Thread.currentThread();
        Intrinsics.checkExpressionValueIsNotNull(threadCurrentThread, "Thread.currentThread()");
        if (threadCurrentThread.getId() == this.mGLThreadId) {
            unit.invoke();
        } else {
            this.mGLEventQueue.add(unit);
        }
    }
}
