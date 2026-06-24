package com.faceunity.wrapper;

import android.graphics.Bitmap;
import java.io.File;

public class faceunity {
    public static final int FUAIHUMAN_SEG_COMMON = 1;
    public static final int FUAIHUMAN_SEG_MEETING = 0;
    public static final int FUAITYPE_BACKGROUNDSEGMENTATION = 2;
    public static final int FUAITYPE_BACKGROUNDSEGMENTATION_GREEN = 128;
    public static final int FUAITYPE_FACELANDMARKS209 = 16384;
    public static final int FUAITYPE_FACELANDMARKS239 = 32768;
    public static final int FUAITYPE_FACELANDMARKS75 = 8192;
    public static final int FUAITYPE_FACEPROCESSOR = 256;
    public static final long FUAITYPE_FACEPROCESSOR_DELSPOT = 1099511627776L;
    public static final int FUAITYPE_FACEPROCESSOR_DISNEYGAN = 67108864;
    public static final int FUAITYPE_FACEPROCESSOR_EMOTION_RECOGNIZER = 33554432;
    public static final int FUAITYPE_FACEPROCESSOR_EXPRESSION_RECOGNIZER = 16777216;
    public static final int FUAITYPE_FACEPROCESSOR_FACECAPTURE = 1048576;
    public static final int FUAITYPE_FACEPROCESSOR_FACECAPTURE_TONGUETRACKING = 2097152;
    public static final int FUAITYPE_FACEPROCESSOR_FACEID = 134217728;
    public static final long FUAITYPE_FACEPROCESSOR_FACEOCCUSEGMENT = 274877906944L;
    public static final int FUAITYPE_FACEPROCESSOR_HAIRSEGMENTATION = 4194304;
    public static final int FUAITYPE_FACEPROCESSOR_HEADSEGMENTATION = 8388608;
    public static final int FUAITYPE_FACEPROCESSOR_IMAGE_BEAUTY = 65536;
    public static final long FUAITYPE_FACEPROCESSOR_LIPSOCCUSEGMENT = 137438953472L;
    public static final long FUAITYPE_FACEPROCESSOR_SKINSEGMENT = 549755813888L;
    public static final int FUAITYPE_FACE_ATTRIBUTE_PROCESSOR = 4096;
    public static final int FUAITYPE_FACE_RECOGNIZER = 1024;
    public static final int FUAITYPE_HAIRSEGMENTATION = 4;
    public static final int FUAITYPE_HANDGESTURE = 8;
    public static final int FUAITYPE_HANDPROCESSOR = 16;
    public static final int FUAITYPE_HUMANPOSE2D = 64;
    public static final int FUAITYPE_HUMAN_PROCESSOR = 512;
    public static final long FUAITYPE_HUMAN_PROCESSOR_2D_DANCE = 2147483648L;
    public static final long FUAITYPE_HUMAN_PROCESSOR_2D_IMGSLIM = 34359738368L;
    public static final int FUAITYPE_HUMAN_PROCESSOR_2D_SELFIE = 1073741824;
    public static final long FUAITYPE_HUMAN_PROCESSOR_2D_SLIM = 4294967296L;
    public static final long FUAITYPE_HUMAN_PROCESSOR_3D_DANCE = 17179869184L;
    public static final long FUAITYPE_HUMAN_PROCESSOR_3D_SELFIE = 8589934592L;
    public static final int FUAITYPE_HUMAN_PROCESSOR_DETECT = 268435456;
    public static final int FUAITYPE_HUMAN_PROCESSOR_IMAGE_BEAUTY = 131072;
    public static final int FUAITYPE_HUMAN_PROCESSOR_SEGMENTATION = 536870912;
    public static final int FUAITYPE_IMAGE_BEAUTY = 2048;
    public static final long FUAITYPE_IMAGE_BEAUTY_UNKNOW = 68719476736L;
    public static final int FUAITYPE_TONGUETRACKING = 32;
    public static final int FUAI_FACE_ALGORITHMCONFIG_DISABLE_ARMESHV2 = 8;
    public static final int FUAI_FACE_ALGORITHMCONFIG_DISABLE_DEL_SPOT = 4;
    public static final int FUAI_FACE_ALGORITHMCONFIG_DISABLE_FACE_OCCU = 1;
    public static final int FUAI_FACE_ALGORITHMCONFIG_DISABLE_LANDMARK_HP_OCCU = 32;
    public static final int FUAI_FACE_ALGORITHMCONFIG_DISABLE_RACE = 16;
    public static final int FUAI_FACE_ALGORITHMCONFIG_DISABLE_SKIN_SEG = 2;
    public static final int FUAI_FACE_ALGORITHMCONFIG_ENABLE_ALL = 0;
    public static final int FUAI_FACE_MODELCONFIG_ALL_DEFAULT = -1;
    public static final int FUAI_HUMAN_ALGORITHMCONFIG_DISABLE_HUMAN_SEG = 1;
    public static final int FUAI_HUMAN_ALGORITHMCONFIG_ENABLE_ALL = 0;
    public static final int FUAI_HUMAN_MODELCONFIG_SEG_CPU_COMM = 0;
    public static final int FUAI_HUMAN_MODELCONFIG_SEG_GPU_COMM = 1;
    public static final int FUAI_HUMAN_MODELCONFIG_SEG_GPU_MEET = 2;
    public static final int FUAI_HUMAN_SEG_CPU_COMMON = 0;
    public static final int FUAI_HUMAN_SEG_GPU_COMMON = 1;
    public static final int FUAI_HUMAN_SEG_GPU_MEETING = 2;
    public static final int FU_ADM_FLAG_ENABLE_READBACK = 2;
    public static final int FU_ADM_FLAG_EXTERNAL_OES_TEXTURE = 1;
    public static final int FU_ADM_FLAG_FLIP_X = 32;
    public static final int FU_ADM_FLAG_FLIP_Y = 64;
    public static final int FU_ADM_FLAG_I420_BUFFER = 16;
    public static final int FU_ADM_FLAG_I420_TEXTURE = 8;
    public static final int FU_ADM_FLAG_NV12_BUFFER = 262144;
    public static final int FU_ADM_FLAG_NV12_TEXTURE = 131072;
    public static final int FU_ADM_FLAG_NV21_TEXTURE = 4;
    public static final int FU_ADM_FLAG_RGBA_BUFFER = 128;
    public static final int FU_ADM_FLAG_TEXTURE_AND_READBACK_BUFFER_OPPOSITE_X = 256;
    public static final int FU_ADM_FLAG_TEXTURE_AND_READBACK_BUFFER_OPPOSITE_Y = 512;
    public static final int FU_ADM_FLAG_TEXTURE_AND_READBACK_BUFFER_ROTATE_180 = 2048;
    public static final int FU_ADM_FLAG_TEXTURE_AND_READBACK_BUFFER_ROTATE_270 = 4096;
    public static final int FU_ADM_FLAG_TEXTURE_AND_READBACK_BUFFER_ROTATE_90 = 1024;
    public static final int FU_ADM_FLAG_TEXTURE_ROTATE_180 = 16384;
    public static final int FU_ADM_FLAG_TEXTURE_ROTATE_270 = 32768;
    public static final int FU_ADM_FLAG_TEXTURE_ROTATE_90 = 8192;
    public static final int FU_FORMAT_GL_CURRENT_FRAMEBUFFER = 3;
    public static final int FU_FORMAT_I420_BUFFER = 13;
    public static final int FU_FORMAT_NV12_BUFFER = 8;
    public static final int FU_FORMAT_NV21_BUFFER = 2;
    public static final int FU_FORMAT_RGBA_BUFFER = 4;
    public static final int FU_FORMAT_RGBA_TEXTURE = 1;
    public static final int FU_IMAGE_BEAUTY_MODE_ACENCANCEL = 128;
    public static final int FU_IMAGE_BEAUTY_MODE_AUTO = 8;
    public static final int FU_IMAGE_BEAUTY_MODE_AUTO_WITHOUT_ACEN_AVER = 524288;
    public static final int FU_IMAGE_BEAUTY_MODE_AVER = 256;
    public static final int FU_IMAGE_BEAUTY_MODE_BLUR = 64;
    public static final int FU_IMAGE_BEAUTY_MODE_BODY_SLIM = 4;
    public static final int FU_IMAGE_BEAUTY_MODE_DARKCIRCLE = 131072;
    public static final int FU_IMAGE_BEAUTY_MODE_DECREEPATTERN = 262144;
    public static final int FU_IMAGE_BEAUTY_MODE_FACE_BEAUTY = 1;
    public static final int FU_IMAGE_BEAUTY_MODE_FACE_BEAUTY_PREPROCESS = 32;
    public static final int FU_IMAGE_BEAUTY_MODE_FACE_WARP = 2;
    public static final int FU_IMAGE_BEAUTY_MODE_JILI = 65536;
    public static final int FU_IMAGE_BEAUTY_MODE_LIGHTEYE = 8192;
    public static final int FU_IMAGE_BEAUTY_MODE_RED = 4096;
    public static final int FU_IMAGE_BEAUTY_MODE_SHARPENBROWN = 16384;
    public static final int FU_IMAGE_BEAUTY_MODE_THREED = 32768;
    public static final int FU_IMAGE_BEAUTY_MODE_UNKNOWN = 0;
    public static final int FU_IMAGE_BEAUTY_MODE_UPLOAD = 16;
    public static final int FU_IMAGE_BEAUTY_MODE_WARMCOLD = 2048;
    public static final int FU_IMAGE_BEAUTY_MODE_WHITEBLACK = 1024;
    public static final int FU_IMAGE_BEAUTY_MODE_WRINKLE = 512;
    public static final int FU_IMAGE_UNDOREDO_MODE_GAP_EQUAL_TO_ZERO = 1;
    public static final int FU_IMAGE_UNDOREDO_MODE_GAP_GREATER_THAN_ZERO = 0;
    public static final int FU_NOCLEAR_CURRENT_FRAMEBUFFER = 65536;
    public static final int FU_ROTATION_MODE_0 = 0;
    public static final int FU_ROTATION_MODE_180 = 2;
    public static final int FU_ROTATION_MODE_270 = 3;
    public static final int FU_ROTATION_MODE_90 = 1;
    public static final int FU_SETUP_TYPE_OFFLINE_GET_ANDROID_ID_IN_JAVA = 2;
    public static final int FU_SETUP_TYPE_OFFLINE_GET_SERIAL_ID_IN_JNI = 0;
    public static final int FU_SETUP_TYPE_OFFLINE_GET_SERIAL_ID_IN_NATIVE = 1;

    public interface OnHandGestureListener {
        void OnHandGestureDetect(int i);
    }

    public interface OnItemTriggerListener {
        void OnItemTrigger(int i, int i2);
    }

    public static native int fuAuthCountWithAPIName(String str);

    @Deprecated
    public static native int fuAvatarBindItems(int i, int[] iArr, int[] iArr2);

    public static native int fuAvatarToCurrentFBO(float[] fArr, float[] fArr2, float[] fArr3, float[] fArr4, float[] fArr5, int i, int i2, int i3, int i4, int[] iArr, int i5, int i6);

    public static native int fuAvatarToImage(float[] fArr, float[] fArr2, float[] fArr3, float[] fArr4, int i, int i2, int i3, int i4, int[] iArr, int i5, int i6, int i7, byte[] bArr);

    public static native int fuAvatarToImage(float[] fArr, float[] fArr2, float[] fArr3, float[] fArr4, float[] fArr5, int i, int i2, int i3, int i4, int[] iArr, int i5, int i6, int i7, byte[] bArr);

    public static native int fuAvatarToTexture(float[] fArr, float[] fArr2, float[] fArr3, float[] fArr4, int i, int i2, int i3, int i4, int[] iArr, int i5);

    public static native int fuAvatarToTexture(float[] fArr, float[] fArr2, float[] fArr3, float[] fArr4, float[] fArr5, int i, int i2, int i3, int i4, int[] iArr, int i5);

    public static native int fuAvatarToTextureWithTrans(float[] fArr, float[] fArr2, float[] fArr3, float[] fArr4, float[] fArr5, int i, int i2, int i3, int i4, int[] iArr, int i5);

    @Deprecated
    public static native int fuAvatarUnbindItems(int i, int[] iArr);

    public static native int fuBeautifyImage(int i, int i2, int i3, int i4, int i5, int[] iArr);

    public static native int fuBindItems(int i, int[] iArr);

    public static native int fuBindItemsToInstance(int i, int[] iArr);

    public static native int fuBindItemsToScene(int i, int[] iArr);

    public static native int fuCheckDebugItem(byte[] bArr);

    public static native int fuCheckGLError();

    public static native void fuClearCacheResource();

    public static native void fuClearItemTriggerListener();

    public static native int fuClearReadbackRelated();

    public static native void fuCreateEGLContext();

    public static native int fuCreateInstance(int i);

    public static native int fuCreateItemFromPackage(byte[] bArr);

    public static native int fuCreateScene();

    public static native int fuCreateTexForItem(int i, String str, byte[] bArr, int i2, int i3);

    public static native int fuDeleteTexForItem(int i, String str);

    public static native void fuDestroyAllItems();

    public static native int fuDestroyInstance(int i);

    public static native void fuDestroyItem(int i);

    public static native void fuDestroyLibData();

    public static native int fuDestroyScene(int i);

    public static native void fuDisableBoostWithEGLImage();

    public static native void fuDone();

    public static native void fuDualInputToFBO(byte[] bArr, int i, int i2, int i3, int i4, int i5, int[] iArr, int i6);

    public static native int fuDualInputToTexture(long j, int i, int i2, int i3, int i4, int i5, int[] iArr);

    public static native int fuDualInputToTexture(byte[] bArr, int i, int i2, int i3, int i4, int i5, int[] iArr);

    public static native int fuDualInputToTexture(byte[] bArr, int i, int i2, int i3, int i4, int i5, int[] iArr, int i6, int i7, byte[] bArr2);

    public static native int fuDualInputToTexture(byte[] bArr, int i, int i2, int i3, int i4, int i5, int[] iArr, int i6, int i7, byte[] bArr2, int i8, int i9);

    public static native int fuDualInputToTextureMasked(byte[] bArr, int i, int i2, int i3, int i4, int i5, int[] iArr, int[] iArr2);

    public static native int fuEnableARMode(int i, boolean z);

    public static native int fuEnableBackgroundAnimationLoop(int i, int i2, boolean z);

    public static native int fuEnableBackgroundColor(int i, boolean z);

    public static native int fuEnableBinaryShaderProgram(boolean z);

    public static native int fuEnableBloom(int i, boolean z);

    public static native int fuEnableCameraAnimation(int i, boolean z);

    public static native int fuEnableCameraAnimationInternalLerp(int i, boolean z);

    public static native int fuEnableControlTimeUpdate(int i, boolean z);

    public static native int fuEnableDynamicBone(int i, boolean z);

    public static native int fuEnableFaceProcessor(int i, boolean z);

    public static native int fuEnableGroundReflection(int i, boolean z);

    public static native int fuEnableHDRRGBA16F(int i, boolean z);

    public static native int fuEnableHandDetetor(int i, boolean z);

    public static native int fuEnableHumanProcessor(int i, boolean z);

    public static native int fuEnableInstanceAnimationInternalLerp(int i, boolean z);

    public static native int fuEnableInstanceDynamicBoneRootRotationSpeedLimitMode(int i, boolean z);

    public static native int fuEnableInstanceDynamicBoneRootTranslationSpeedLimitMode(int i, boolean z);

    public static native int fuEnableInstanceDynamicBoneTeleportMode(int i, boolean z);

    public static native int fuEnableInstanceExpressionBlend(int i, boolean z);

    public static native int fuEnableInstanceFaceProcessorRotateHead(int i, boolean z);

    public static native int fuEnableInstanceFacepupMode(int i, boolean z);

    public static native int fuEnableInstanceFocusEyeToCamera(int i, boolean z);

    public static native int fuEnableInstanceHideNeck(int i, boolean z);

    public static native int fuEnableInstanceModelMatToBone(int i, boolean z);

    public static native int fuEnableInstanceRotateWithoutAnimationTranslation(int i, int i2);

    public static native int fuEnableInstanceSelfCollision(int i, int i2);

    public static native int fuEnableInstanceSingleDynamicBone(int i, int i2, boolean z);

    public static native int fuEnableInstanceSingleMeshVisible(int i, int i2, boolean z);

    public static native int fuEnableInstanceUseFaceBeautyOrder(int i, boolean z);

    public static native int fuEnableInstanceVisible(int i, int i2);

    public static native int fuEnableInstanceVisible(int i, boolean z);

    public static native int fuEnableLowQualityLighting(int i, boolean z);

    public static native int fuEnableLowResolutionTexture(int i, boolean z);

    public static native int fuEnableOrthogonalProjection(int i, boolean z);

    public static native int fuEnableOuterMVPMatrix(int i, boolean z);

    public static native int fuEnableRender(int i, boolean z);

    public static native int fuEnableRenderCamera(int i, boolean z);

    public static native int fuEnableRiggingBVHInputProcessor(int i, boolean z);

    public static native int fuEnableShadow(int i, boolean z);

    public static native float fuFaceProcessorGetConfidenceScore(int i);

    public static native int fuFaceProcessorGetNumResults();

    public static native int fuFaceProcessorGetResultFaceOcclusion(int i);

    public static native int fuFaceProcessorGetResultHairMask(int i, float[] fArr);

    public static native int fuFaceProcessorGetResultHairMaskHeight(int i);

    public static native int fuFaceProcessorGetResultHairMaskWidth(int i);

    public static native int fuFaceProcessorGetResultHeadMask(int i, float[] fArr);

    public static native int fuFaceProcessorGetResultHeadMaskHeight(int i);

    public static native int fuFaceProcessorGetResultHeadMaskWidth(int i);

    public static native void fuFaceProcessorSetDetectSmallFace(int i);

    public static native void fuFaceProcessorSetFaceLandmarkHpOccu(int i);

    public static native void fuFaceProcessorSetFaceLandmarkQuality(int i);

    public static native void fuFaceProcessorSetMinFaceRatio(float f);

    public static native int fuFaceProcessorSetUseCaptureEyeLookCam(int i);

    public static native void fuForceSetInputPbo(boolean z);

    public static native int fuGetCameraAnimationFrameNumber(int i);

    public static native int fuGetCameraAnimationFrameNumber(int i, int i2);

    public static native float fuGetCameraAnimationProgress(int i, int i2);

    public static native float fuGetCameraAnimationTransitionProgress(int i);

    public static native String fuGetCommitTime();

    public static native int fuGetCurrentRotationMode();

    public static native float fuGetDynamicQuality();

    public static native int fuGetFaceIdentifier(int i);

    public static native int fuGetFaceInfo(int i, String str, float[] fArr);

    public static native int fuGetFaceInfo(int i, String str, int[] iArr);

    public static native int fuGetFaceInfoRotated(int i, String str, float[] fArr);

    public static native int fuGetFaceInfoRotated(int i, String str, int[] iArr);

    public static native float fuGetFaceProcessorFov();

    public static native int fuGetFaceTransferTexID();

    public static native int fuGetGetProjectionMatrixZfar(int i);

    public static native int fuGetInstanceAnimationFrameNumber(int i, int i2);

    public static native float fuGetInstanceAnimationProgress(int i, int i2);

    public static native float fuGetInstanceAnimationTransitionProgress(int i, int i2);

    public static native int fuGetInstanceBoneScreenCoordinate(int i, String str, float[] fArr);

    public static native int fuGetInstanceBoundingBoxScreenCoordinate(int i, float[] fArr);

    public static native int fuGetInstanceBoundingBoxScreenCoordinateWithOffset(int i, float[] fArr, float f, float f2, float f3, float f4, float f5, float f6);

    public static native int fuGetInstanceFaceVertexScreenCoordinate(int i, int i2, float[] fArr);

    public static native int fuGetInstanceFacepupArray(int i, float[] fArr);

    public static native float fuGetInstanceFacepupOriginalValue(int i, String str);

    public static native int fuGetInstanceHeadCenterScreenCoordinate(int i, float[] fArr);

    public static native int fuGetInstanceLocalBoundingBox(int i, float[] fArr);

    public static native int fuGetInstancePosition(int i, float[] fArr);

    public static native int fuGetInstanceSkinColorIndex(int i);

    public static native int fuGetLogLevel();

    public static native int fuGetModuleCode(int i);

    public static native int fuGetSystemError();

    public static native String fuGetSystemErrorString(int i);

    public static native String fuGetVersion();

    public static native int fuHandDetectorGetResultGestureType(int i);

    public static native int fuHandDetectorGetResultHandRect(int i, float[] fArr);

    public static native float fuHandDetectorGetResultHandScore(int i);

    public static native int fuHandDetectorGetResultNumHands();

    public static native int fuHasFace();

    public static native void fuHexagonInitWithPath(String str);

    public static native void fuHexagonTearDown();

    public static native float fuHumanActionMatchDistance(float[] fArr, float[] fArr2);

    public static native float fuHumanActionMatchLeftRightHandDistance(float[] fArr, float[] fArr2, boolean z);

    public static native float fuHumanProcessorGetFov();

    public static native int fuHumanProcessorGetNumResults();

    public static native float fuHumanProcessorGetResultActionScore(int i);

    public static native int fuHumanProcessorGetResultActionType(int i);

    public static native int fuHumanProcessorGetResultBVHMotionFrameOutput(int i, float[] fArr);

    public static native int fuHumanProcessorGetResultHumanMask(int i, float[] fArr);

    public static native int fuHumanProcessorGetResultHumanMaskHeight(int i);

    public static native int fuHumanProcessorGetResultHumanMaskWidth(int i);

    public static native int fuHumanProcessorGetResultJoint2ds(int i, float[] fArr);

    public static native int fuHumanProcessorGetResultJoint3ds(int i, float[] fArr);

    public static native int fuHumanProcessorGetResultPofJoint2dScores(int i, float[] fArr);

    public static native int fuHumanProcessorGetResultPofJoint2ds(int i, float[] fArr);

    public static native int fuHumanProcessorGetResultRect(int i, float[] fArr);

    public static native int fuHumanProcessorGetResultTrackId(int i);

    public static native void fuHumanProcessorReset();

    public static native int fuHumanProcessorSet3DScene(int i, int i2);

    public static native void fuHumanProcessorSetAvatarAnimFilterParams(int i, float f, float f2);

    public static native void fuHumanProcessorSetBVHInPlaneMirrorType(int i, int i2);

    public static native void fuHumanProcessorSetBVHInPlaneRotation(int i, int i2);

    public static native void fuHumanProcessorSetEnableBVHOutput(int i, boolean z);

    public static native void fuHumanProcessorSetFov(float f);

    public static native void fuHumanProcessorSetMaxHumans(int i);

    public static native Bitmap fuImageBeautyBitmap(int i, int i2);

    public static native int fuImageBeautyClearMemory(int[] iArr);

    public static native int fuImageBeautyConvertRGBA2NV21(int i, int i2, byte[] bArr, int i3, int i4, byte[] bArr2);

    public static native int fuImageBeautyConvertRGBA2NV21WithBitmap(Bitmap bitmap, int i, int i2, byte[] bArr);

    public static native int fuImageBeautyCreateTexture(int i, int i2, int i3, byte[] bArr, int i4, byte[] bArr2, int i5, byte[] bArr3, int i6, float f);

    public static native int fuImageBeautyCreateTextureCoverPreview(int i, int i2, int i3, byte[] bArr, int i4, byte[] bArr2, int i5, byte[] bArr3, int i6, float f);

    public static native int fuImageBeautyCreateTextureWithBitmap(Bitmap bitmap, float f);

    public static native int fuImageBeautyCreateTextureWithBitmapCoverPreview(Bitmap bitmap, float f);

    public static native int fuImageBeautyGetInfo(int i, String str, float[] fArr);

    public static native int fuImageBeautyGetLastResultTexture();

    public static native int fuImageBeautyGetOriginTexture();

    public static native String fuImageBeautyGetParam(int[] iArr, String str);

    public static native int fuImageBeautyGetResult(int i, int i2, int i3, int i4, byte[] bArr, int i5, byte[] bArr2, int i6, byte[] bArr3, int i7, int[] iArr);

    public static native Bitmap fuImageBeautyGetResultBitmap(int i, int i2, int[] iArr);

    public static native int fuImageBeautyGetResultWithBitmap(Bitmap bitmap, int[] iArr);

    public static native Bitmap fuImageBeautyLoadBitmapFromPath(String str, int i, int i2, int i3);

    public static native int fuImageBeautyLoadCache(int[] iArr);

    public static native void fuImageBeautyNewPic();

    public static native int fuImageBeautyPreProcess(int i, int i2, int i3, int i4, byte[] bArr, int i5, byte[] bArr2, int i6, byte[] bArr3, int i7, int[] iArr, int i8, int i9, int i10, float f);

    public static native void fuImageBeautyPreProcessForImageInfo(int i, int i2, int i3, int i4, byte[] bArr, int i5, byte[] bArr2, int i6, byte[] bArr3, int i7, float f);

    public static native void fuImageBeautyPreProcessForImageInfoWithBitmap(Bitmap bitmap, float f);

    public static native int fuImageBeautyPreProcessWithBitmap(Bitmap bitmap, int[] iArr, int i, int i2, int i3, float f);

    public static native int fuImageBeautyPreview(int[] iArr);

    public static native void fuImageBeautyResetPic(byte[] bArr, int i, int i2, int i3, byte[] bArr2, int i4, int i5);

    public static native int fuImageBeautySaveBitmapToPath(Bitmap bitmap, String str, int i);

    public static native int fuImageBeautySaveCache(int[] iArr);

    public static native int fuImageBeautySaveResultToPath(String str, int[] iArr);

    public static native int fuImageBeautySetAttributePath(String str);

    public static native int fuImageBeautySetCacheDir(String str);

    public static native int fuImageBeautySetFaceBeautyPath(String str);

    public static native int fuImageBeautySetParam(int[] iArr, String str, String str2);

    public static native int fuImageBeautySetUndoRedoMode(int[] iArr, int i);

    public static native int fuIsAIModelLoaded(int i);

    public static native int fuIsGLPrepared(int[] iArr);

    public static native int fuIsLibraryInit();

    public static native int fuIsTracking();

    public static native double fuItemGetParam(int i, String str);

    public static native String fuItemGetParamString(int i, String str);

    public static native double[] fuItemGetParamdv(int i, String str);

    public static native float[] fuItemGetParamfv(int i, String str);

    public static native byte[] fuItemGetParamu8v(int i, String str);

    public static native int fuItemSetParam(int i, String str, double d);

    public static native int fuItemSetParam(int i, String str, String str2);

    public static native int fuItemSetParam(int i, String str, double[] dArr);

    public static native int fuItemSetParamPtr(int i, String str, float[] fArr);

    public static native int fuItemSetParamu64(int i, String str, long j);

    public static native int fuItemSetParamu8v(int i, String str, byte[] bArr, int i2);

    public static native int fuLoadAIModelFromPackage(byte[] bArr, int i);

    public static native int fuLoadTongueModel(byte[] bArr);

    public static native void fuOnCameraChange();

    public static native void fuOnDeviceLost();

    public static native void fuOnDeviceLostSafe();

    public static native int fuOpenFileLog(String str, int i, int i2);

    public static native int fuPauseCameraAnimation(int i);

    public static native int fuPauseInstanceAnimation(int i);

    public static native int fuPauseTimeUpdate(int i, boolean z);

    public static native int fuPlayCameraAnimation(int i, int i2);

    public static native int fuPlayCameraAnimationOnce(int i, int i2);

    public static native int fuPlayInstanceAnimation(int i, int i2);

    public static native int fuPlayInstanceAnimationOnce(int i, int i2);

    public static native void fuPrepareGLResource(int[] iArr);

    public static native int fuPreprocessAIModelFromPackage(byte[] bArr, int i);

    public static native void fuProcessorChangeModel(int i);

    public static native int fuProfileGetNumTimers();

    public static native long fuProfileGetTimerAverage(int i);

    public static native long fuProfileGetTimerCount(int i);

    public static native long fuProfileGetTimerMax(int i);

    public static native long fuProfileGetTimerMin(int i);

    public static native String fuProfileGetTimerName(int i);

    public static native int fuProfileResetAllTimers();

    public static native void fuReadPixelsRGBA2NV21(int i, int i2, int i3, int i4, byte[] bArr);

    public static native int fuRecordMemoryUsage(String str);

    public static native int fuRefreshInstanceDynamicBone(int i, int i2);

    public static native int fuReleaseAIModel(int i);

    public static native void fuReleaseEGLContext();

    public static native void fuReleaseGLResources();

    public static native void fuReleaseGLResourcesSafe();

    public static native int fuRenderBundles(AvatarInfo avatarInfo, int i, int i2, int i3, int i4, int[] iArr);

    public static native int fuRenderBundlesSplitView(AvatarInfo avatarInfo, int i, int i2, int i3, int i4, int[] iArr, SplitViewInfo splitViewInfo);

    public static native int fuRenderBundlesToCurrentFBO(AvatarInfo avatarInfo, int i, int i2, int i3, int i4, int[] iArr, int i5);

    public static native int fuRenderBundlesWithCamera(byte[] bArr, int i, int i2, int i3, int i4, int i5, int[] iArr);

    public static native int fuRenderBundlesWithCamera(byte[] bArr, int i, int i2, int i3, int[] iArr);

    public static native int fuRenderBundlesWithCamera(byte[] bArr, int i, int i2, int i3, int[] iArr, byte[] bArr2);

    public static native int fuRenderBundlesWithCameraToCurrentFBO(byte[] bArr, int i, int i2, int i3, int i4, int i5, int[] iArr, int i6);

    public static native int fuRenderBundlesWithCameraToCurrentFBO(byte[] bArr, int i, int i2, int i3, int i4, int[] iArr, int i5);

    public static native int fuRenderDualInput(int i, int i2, int i3, int[] iArr, int i4, int i5, byte[] bArr, int i6, int i7, int i8, byte[] bArr2);

    public static native int fuRenderI420ImageToTexture(byte[] bArr, int i, int i2, int i3, int[] iArr);

    public static native int fuRenderImg(int i, int i2, int i3, int[] iArr, int i4, byte[] bArr, int i5, int i6, int i7, byte[] bArr2);

    public static native int fuRenderNV21ImageToTexture(byte[] bArr, int i, int i2, int i3, int[] iArr);

    public static native int fuRenderTexture(int i, int i2, int i3, int[] iArr, int i4, int i5);

    public static native int fuRenderToI420Image(long j, int i, int i2, int i3, int[] iArr, int i4);

    public static native int fuRenderToI420Image(byte[] bArr, int i, int i2, int i3, int[] iArr);

    public static native int fuRenderToI420Image(byte[] bArr, int i, int i2, int i3, int[] iArr, int i4);

    public static native int fuRenderToI420Image(byte[] bArr, int i, int i2, int i3, int[] iArr, int i4, int i5, int i6, byte[] bArr2);

    public static native int fuRenderToI420Image(byte[] bArr, int i, int i2, int i3, int[] iArr, int i4, int i5, int i6, byte[] bArr2, int i7, int i8);

    public static native int fuRenderToI420ImageMasked(byte[] bArr, int i, int i2, int i3, int[] iArr, int[] iArr2);

    public static native int fuRenderToNV21Image(long j, int i, int i2, int i3, int[] iArr, int i4);

    public static native int fuRenderToNV21Image(byte[] bArr, int i, int i2, int i3, int[] iArr);

    public static native int fuRenderToNV21Image(byte[] bArr, int i, int i2, int i3, int[] iArr, int i4);

    public static native int fuRenderToNV21Image(byte[] bArr, int i, int i2, int i3, int[] iArr, int i4, int i5, int i6, byte[] bArr2);

    public static native int fuRenderToNV21Image(byte[] bArr, int i, int i2, int i3, int[] iArr, int i4, int i5, int i6, byte[] bArr2, int i7, int i8);

    public static native int fuRenderToNV21ImageMasked(byte[] bArr, int i, int i2, int i3, int[] iArr, int[] iArr2);

    public static native int fuRenderToRgbaImage(long j, int i, int i2, int i3, int[] iArr, int i4);

    public static native int fuRenderToRgbaImage(byte[] bArr, int i, int i2, int i3, int[] iArr);

    public static native int fuRenderToRgbaImage(byte[] bArr, int i, int i2, int i3, int[] iArr, int i4);

    public static native int fuRenderToRgbaImage(byte[] bArr, int i, int i2, int i3, int[] iArr, int i4, int i5, int i6, byte[] bArr2);

    public static native int fuRenderToRgbaImage(byte[] bArr, int i, int i2, int i3, int[] iArr, int i4, int i5, int i6, byte[] bArr2, int i7, int i8);

    public static native int fuRenderToTexture(int i, int i2, int i3, int i4, int[] iArr, int i5);

    public static native int fuRenderToTexture(int i, int i2, int i3, int i4, int[] iArr, int i5, byte[] bArr, int i6, int i7);

    public static native int fuRenderToTexture(int i, int i2, int i3, int i4, int[] iArr, int i5, byte[] bArr, int i6, int i7, int i8, int i9);

    public static native int fuRenderToYUVImage(long j, long j2, long j3, int i, int i2, int i3, int i4, int i5, int i6, int[] iArr);

    public static native int fuRenderToYUVImage(byte[] bArr, byte[] bArr2, byte[] bArr3, int i, int i2, int i3, int i4, int i5, int i6, int[] iArr);

    public static native int fuRenderToYUVImage(byte[] bArr, byte[] bArr2, byte[] bArr3, int i, int i2, int i3, int i4, int i5, int i6, int[] iArr, int i7);

    public static native int fuRenderYUV(int i, int i2, int i3, int[] iArr, int i4, byte[] bArr, byte[] bArr2, byte[] bArr3, int i5, int i6, int i7, boolean z);

    public static native int fuRenderYUV2(int i, int i2, int i3, int[] iArr, int i4, byte[] bArr, byte[] bArr2, byte[] bArr3, int i5, int i6, int i7, boolean z, byte[] bArr4, byte[] bArr5, byte[] bArr6);

    public static native int fuResetBackgroundAnimation(int i, int i2);

    public static native int fuResetCameraAnimation(int i);

    public static native int fuResetInstanceAnimation(int i);

    public static native int fuResetInstanceDynamicBone(int i, int i2);

    public static native int fuResetInstanceFaceProcessorFilter(int i);

    public static native int fuResetInstanceHead(int i);

    public static native int fuResetLightAnimation(int i);

    public static native int fuRiggingBVHInputProcessorFeedMotionFrame(int i, float[] fArr);

    public static native int fuRiggingBVHInputProcessorSetConfig(int i, byte[] bArr, byte[] bArr2);

    public static native int fuRotateImage(RotatedImage rotatedImage, byte[] bArr, int i, int i2, int i3, int i4, int i5, int i6);

    public static native void fuRunCache();

    public static native void fuSetARMeshV2(boolean z);

    @Deprecated
    public static native int fuSetAsyncTrackFace(int i);

    public static native int fuSetBackgroundColor(int i, int i2, int i3, int i4, int i5);

    public static native int fuSetBackgroundParams(int i, int i2, float f, float f2, float f3, float f4, boolean z, int i3);

    public static native int fuSetBinaryShaderProgramDirectory(String str);

    public static native void fuSetCacheDirectory(String str);

    public static native int fuSetCameraAnimationTransitionTime(int i, float f);

    public static native int fuSetCropFreePixel(int i, int i2, int i3, int i4);

    public static native int fuSetCropState(int i);

    public static native int fuSetCurrentScene(int i);

    public static native int fuSetCurrentTime(int i, float f);

    public static native void fuSetDefaultRotationMode(int i);

    public static native void fuSetDeviceOrientation(int i);

    public static native void fuSetDynamicQuality(float f);

    public static native void fuSetDynamicQualityControl(boolean z);

    public static native void fuSetDynamicQualityParams(float f, float f2, float f3);

    public static native void fuSetFaceAlgorithmConfig(int i);

    public static native void fuSetFaceDelayLeaveEnable(boolean z);

    public static native void fuSetFaceDelayLeaveFrameNum(int i);

    @Deprecated
    public static native int fuSetFaceDetParam(String str, float f);

    public static native void fuSetFaceModelConfig(int i);

    public static native void fuSetFaceProcessorDetectEveryNFramesWhenFace(int i);

    public static native void fuSetFaceProcessorDetectEveryNFramesWhenNoFace(int i);

    public static native int fuSetFaceProcessorDetectMode(int i);

    public static native int fuSetFaceProcessorFov(float f);

    @Deprecated
    public static native int fuSetFaceTrackParam(String str, float f);

    public static native int fuSetForceUseGL2(int i);

    public static native int fuSetGroundReflectionPrameters(int i, float f, float f2);

    public static native void fuSetHandDetectEveryNFramesWhenNoHand(int i);

    public static native void fuSetHandGestrueListener(OnHandGestureListener onHandGestureListener);

    public static native void fuSetHumanAlgorithmConfig(int i);

    public static native void fuSetHumanModelConfig(int i);

    public static native int fuSetHumanProcessorDetectMode(int i);

    public static native void fuSetHumanSegMode(int i);

    public static native void fuSetHumanSegScene(int i);

    public static native void fuSetInputBufferMatrix(int i);

    public static native void fuSetInputCameraBufferMatrixState(int i);

    public static native void fuSetInputCameraMatrix(int i, int i2, int i3);

    public static native void fuSetInputCameraTextureMatrixState(int i);

    public static native void fuSetInputTextureMatrix(int i);

    public static native int fuSetInstanceAnimationTransitionTime(int i, float f);

    public static native int fuSetInstanceBlendExpression(int i, float[] fArr);

    public static native int fuSetInstanceBodyInvisibleList(int i, int[] iArr);

    public static native int fuSetInstanceBodyVisibleList(int i, int[] iArr);

    public static native int fuSetInstanceColor(int i, String str, int i2, int i3, int i4);

    public static native int fuSetInstanceColorIntensity(int i, String str, float f);

    public static native int fuSetInstanceDeformation(int i, String str, float f);

    public static native int fuSetInstanceEnableHumanAnimDriver(int i, boolean z);

    public static native int fuSetInstanceExpressionWeight0(int i, float[] fArr);

    public static native int fuSetInstanceExpressionWeight1(int i, float[] fArr);

    public static native int fuSetInstanceEyeRotationDeltaX(int i, float f);

    public static native int fuSetInstanceFaceBeautyOrder(int i, int[] iArr);

    public static native int fuSetInstanceFaceProcessorFaceId(int i, int i2);

    public static native int fuSetInstanceFaceProcessorFilterSize(int i, int i2, int i3, int i4);

    public static native int fuSetInstanceFaceProcessorOuterResultPtr(int i, float[] fArr);

    public static native int fuSetInstanceFaceProcessorType(int i, int i2);

    public static native int fuSetInstanceFacebeautyColor(int i, int i2, int i3, int i4, int i5);

    public static native int fuSetInstanceFacepup(int i, String str, float f);

    public static native int fuSetInstanceFocusEyeToCameraParams(int i, float f, float f2, float f3);

    public static native int fuSetInstanceHeadRotationDeltaX(int i, float f);

    public static native int fuSetInstanceHeadRotationZRange(int i, float f, float f2);

    public static native int fuSetInstanceHumanProcessorType(int i, int i2);

    public static native int fuSetInstanceInputCameraBufferMatrix(int i, int i2);

    public static native int fuSetInstanceRiggingRetargeterAvatarFixModeTransScale(int i, float f, float f2, float f3);

    public static native int fuSetInstanceRiggingRetargeterAvatarFollowMode(int i, int i2);

    public static native int fuSetInstanceRotDelta(int i, float f);

    public static native int fuSetInstanceScale(int i, float f, float f2, float f3, float f4);

    public static native int fuSetInstanceScaleDelta(int i, float f);

    public static native int fuSetInstanceShadowPCFLevel(int i, int i2);

    public static native int fuSetInstanceShadowSampleOffset(int i, int i2);

    public static native int fuSetInstanceTargetAngle(int i, float f);

    public static native int fuSetInstanceTargetAngleGradually(int i, float f, int i2);

    public static native int fuSetInstanceTargetPosition(int i, float f, float f2, float f3);

    public static native int fuSetInstanceTargetPositionGradually(int i, float f, float f2, float f3, int i2);

    public static native int fuSetInstanceTargetPositionRange(int i, float f, float f2, float f3, float f4, float f5, float f6);

    public static native int fuSetInstanceTranslateDelta(int i, float f);

    public static native int fuSetInstanceUVAnimArray(int i, int[] iArr);

    public static native void fuSetItemTriggerListener(int i, OnItemTriggerListener onItemTriggerListener);

    public static native int fuSetLoadQuality(int i);

    public static native int fuSetLogLevel(int i);

    public static native void fuSetMachineType(int i);

    public static native void fuSetMakeupCoverResource(boolean z);

    public static native int fuSetMaxFaces(int i);

    public static native void fuSetModelToCPU();

    public static native int fuSetMultiSamples(int i);

    public static native int fuSetOuterModelMatrix(int i, float[] fArr);

    public static native int fuSetOuterProjectionMatrix(int i, float[] fArr);

    public static native int fuSetOuterViewMatrix(int i, float[] fArr);

    public static native void fuSetOutputMatrix(int i);

    public static native void fuSetOutputMatrixState(int i);

    public static native void fuSetOutputResolution(int i, int i2);

    public static native int fuSetProjectionMatrixFov(int i, float f);

    public static native int fuSetProjectionMatrixOrthoSize(int i, float f);

    public static native int fuSetProjectionMatrixZfar(int i, float f);

    public static native int fuSetProjectionMatrixZnear(int i, float f);

    @Deprecated
    public static native void fuSetQualityTradeoff(float f);

    public static native void fuSetReadbackSync(boolean z);

    public static native void fuSetRenderPauseState(boolean z);

    public static native void fuSetRttCacheState(int i);

    public static native int fuSetShadowBias(int i, float f, float f2);

    public static native int fuSetShadowMapSize(int i, int i2);

    @Deprecated
    public static native void fuSetStrictTracking(int i);

    public static native int fuSetTongueTracking(int i);

    public static native void fuSetTrackFaceAIType(int i);

    public static native int fuSetUseAsyncAIInference(int i);

    public static native int fuSetUseMultiBuffer(int i, int i2);

    public static native int fuSetUseTexAsync(int i);

    public static native int fuSetup(byte[] bArr, byte[] bArr2);

    @Deprecated
    public static native int fuSetup(byte[] bArr, byte[] bArr2, byte[] bArr3);

    public static native byte[] fuSetupDeviceLocal(byte[] bArr, byte[] bArr2, byte[] bArr3);

    public static native int fuSetupInternalCheck(byte[] bArr, byte[] bArr2);

    public static native int fuSetupInternalCheckEx(byte[] bArr, byte[] bArr2, byte[] bArr3);

    public static native int fuSetupInternalCheckPackageBind(byte[] bArr, byte[] bArr2);

    public static native byte[] fuSetupLocal(byte[] bArr, byte[] bArr2, byte[] bArr3);

    public static native byte[] fuSetupOffline(int i, byte[] bArr, byte[] bArr2, byte[] bArr3);

    public static native int fuStartCameraAnimation(int i);

    public static native int fuStartInstanceAnimation(int i);

    public static native int fuStopInstanceAnimation(int i);

    public static native int fuTrackFace(byte[] bArr, int i, int i2, int i3);

    public static native int fuTrackFaceWithTongue(byte[] bArr, int i, int i2, int i3);

    public static native int fuUnBindItems(int i, int[] iArr);

    public static native int fuUnbindAllItems(int i);

    public static native int fuUnbindItemsFromInstance(int i, int[] iArr);

    public static native int fuUnbindItemsFromScene(int i, int[] iArr);

    public static native int fuUpdateBackgroundTexture(int i, int i2, byte[] bArr, int i3, int i4);

    public static class LoadConfig {
        private static boolean sLoadFUAI = true;
        private static boolean sLoadedLibrary = false;

        private LoadConfig() {
        }

        public static void disableLoadFUAI(boolean z) {
            sLoadFUAI = z;
        }

        public static void loadLibrary(String str) {
            if (sLoadedLibrary || str == null) {
                return;
            }
            if (sLoadFUAI) {
                System.load(new File(str, "libfuai.so").getAbsolutePath());
            }
            System.load(new File(str, "libCNamaSDK.so").getAbsolutePath());
            sLoadedLibrary = true;
        }

        public static void loadLibraryWithPostfix(String str) {
            if (sLoadedLibrary || str == null) {
                return;
            }
            if (sLoadFUAI) {
                System.loadLibrary("fuai" + str);
            }
            System.loadLibrary("CNamaSDK" + str);
            sLoadedLibrary = true;
        }
    }

    static {
        if (LoadConfig.sLoadedLibrary) {
            return;
        }
        if (LoadConfig.sLoadFUAI) {
            System.loadLibrary("fuai");
        }
        System.loadLibrary("CNamaSDK");
    }

    public static class AvatarInfo {
        public float[] mExpression;
        public boolean mIsValid;
        public float[] mPupilPos;
        public float[] mRotation;
        public float[] mRotationMode;
        public float[] mTranslation;

        private native void initJniFiledIDs();

        static {
            new AvatarInfo().initJniFiledIDs();
        }
    }

    public static class SplitViewInfo {
        public float mCropRatioTop;
        public byte[] mImage;
        public boolean mIsImageFirst;
        public boolean mIsVertical;
        public int mMarginInPixel;
        public int mOutH;
        public int mOutW;
        public int mRotationModeBeforeCrop;
        public int mTex;
        public int mUseBlackEdge;
        public float mView0Ratio;

        private native void initJniFiledIDs();

        static {
            new SplitViewInfo().initJniFiledIDs();
        }
    }

    public static class RotatedImage {
        public byte[] mData = null;
        public int mWidth = 0;
        public int mHeight = 0;
        private byte[] mData1 = null;
        private byte[] mData2 = null;

        private native void initJniFiledIDs();

        static {
            new RotatedImage().initJniFiledIDs();
        }
    }
}
