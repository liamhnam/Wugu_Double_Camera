package com.faceunity.core.faceunity;

import androidx.constraintlayout.motion.widget.Key;
import com.arthenica.ffmpegkit.MediaInformation;
import com.arthenica.ffmpegkit.StreamInformation;
import com.faceunity.core.callback.OperateCallback;
import com.faceunity.core.controller.action.ActionRecognitionParam;
import com.faceunity.core.enumeration.FUAITypeEnum;
import com.faceunity.core.enumeration.FUFaceProcessorDetectModeEnum;
import com.faceunity.core.enumeration.FUHumanProcessorDetectModeEnum;
import com.faceunity.core.enumeration.FUInputBufferEnum;
import com.faceunity.core.enumeration.FUPortraitSegmentationEnum;
import com.faceunity.core.support.SDKController;
import com.faceunity.core.utils.FULogger;
import com.faceunity.core.utils.FileUtils;
import com.tom_roush.fontbox.ttf.NamingTable;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

@Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u0000p\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\n\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u0014\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0007\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\u0010\u0015\n\u0002\b\u0015\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u0000 ^2\u00020\u0001:\u0001^B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u000f\u001a\u00020\u0010J\u0006\u0010\u0011\u001a\u00020\u0012J\u0016\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0014\u001a\u00020\u00052\u0006\u0010\u0015\u001a\u00020\u0016J\u0016\u0010\u0017\u001a\u00020\u00122\u0006\u0010\u0014\u001a\u00020\u00052\u0006\u0010\u0015\u001a\u00020\u0016J\u000e\u0010\u0018\u001a\u00020\u00122\u0006\u0010\u0019\u001a\u00020\u001aJ\u000e\u0010\u001b\u001a\u00020\u00122\u0006\u0010\u001c\u001a\u00020\u0005J\u000e\u0010\u001d\u001a\u00020\u00122\u0006\u0010\u001e\u001a\u00020\u001fJ\u000e\u0010 \u001a\u00020\u00122\u0006\u0010\u0007\u001a\u00020\u0005J\u000e\u0010!\u001a\u00020\u00122\u0006\u0010\u001c\u001a\u00020\u001fJ\u000e\u0010\"\u001a\u00020\u00122\u0006\u0010#\u001a\u00020\u0010J\u000e\u0010$\u001a\u00020\u00122\u0006\u0010#\u001a\u00020\u0005J\u000e\u0010%\u001a\u00020\u00122\u0006\u0010\u0006\u001a\u00020\u0005J\u000e\u0010&\u001a\u00020\u00122\u0006\u0010\u0006\u001a\u00020\u0005J\u000e\u0010'\u001a\u00020\u00122\u0006\u0010\u0006\u001a\u00020\u0005J\u000e\u0010(\u001a\u00020\u00122\u0006\u0010\u0006\u001a\u00020\u0005J\u000e\u0010)\u001a\u00020\u00122\u0006\u0010*\u001a\u00020+J\u0006\u0010,\u001a\u00020\u0012J\u0016\u0010-\u001a\u00020\u00122\u0006\u0010.\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0016J\u001e\u0010-\u001a\u00020\u00122\u0006\u0010.\u001a\u00020\u00052\u0006\u0010/\u001a\u0002002\u0006\u0010\u0006\u001a\u00020\u0016J\u001e\u0010-\u001a\u00020\u00122\u0006\u0010.\u001a\u00020\u00052\u0006\u0010/\u001a\u0002002\u0006\u0010\u0006\u001a\u000201J\u000e\u00102\u001a\u00020\u001f2\u0006\u0010\u0014\u001a\u00020\u0005J\u000e\u00103\u001a\u00020\u00052\u0006\u0010\u0014\u001a\u00020\u0005J\u0016\u00104\u001a\u00020\u00052\u0006\u0010\u0014\u001a\u00020\u00052\u0006\u00105\u001a\u00020\u0016J\u000e\u00106\u001a\u00020\u001f2\u0006\u0010\u0014\u001a\u00020\u0005J\u0006\u00107\u001a\u00020\u0005J\u0006\u00108\u001a\u00020\u001fJ\u0006\u00109\u001a\u00020\u0005J\u000e\u0010:\u001a\u00020\u001f2\u0006\u0010\u0014\u001a\u00020\u0005J\u000e\u0010;\u001a\u00020\u00052\u0006\u0010\u0014\u001a\u00020\u0005J\u0016\u0010<\u001a\u00020\u00052\u0006\u0010\u0014\u001a\u00020\u00052\u0006\u0010\u0015\u001a\u00020\u0016J\u0016\u0010=\u001a\u00020\u00122\u0006\u0010\u0014\u001a\u00020\u00052\u0006\u0010>\u001a\u00020\u0016J\u0016\u0010?\u001a\u00020\u00122\u0006\u0010\u0014\u001a\u00020\u00052\u0006\u0010>\u001a\u00020\u0016J\u0016\u0010@\u001a\u00020\u00122\u0006\u0010\u0014\u001a\u00020\u00052\u0006\u00105\u001a\u00020\u0016J\u000e\u0010A\u001a\u00020\u00052\u0006\u0010\u0014\u001a\u00020\u0005J\u0006\u0010B\u001a\u00020\u0012J\u000e\u0010C\u001a\u00020\u00122\u0006\u0010\u001e\u001a\u00020\u001fJ\u000e\u0010D\u001a\u00020\u00122\u0006\u0010\f\u001a\u00020\u0005J\u000e\u0010E\u001a\u00020\u00102\u0006\u0010F\u001a\u00020GJ\u0006\u0010H\u001a\u00020\u0005J\u0016\u0010I\u001a\u00020\u00122\u0006\u0010J\u001a\u0002002\u0006\u0010F\u001a\u00020GJ\u0016\u0010K\u001a\u00020\u00122\u0006\u0010J\u001a\u0002002\u0006\u0010F\u001a\u00020GJ\u000e\u0010L\u001a\u00020\u00122\u0006\u0010F\u001a\u00020GJ\u0006\u0010M\u001a\u00020\u0012J\u000e\u0010N\u001a\u00020\u00122\u0006\u0010#\u001a\u00020\u0010J\u000e\u0010O\u001a\u00020\u00122\u0006\u0010P\u001a\u00020\u0005J\u000e\u0010Q\u001a\u00020\u00122\u0006\u0010P\u001a\u00020\u0005J\u000e\u0010R\u001a\u00020\u00122\u0006\u0010P\u001a\u00020\u0005J\u000e\u0010S\u001a\u00020\u00122\u0006\u0010\u0019\u001a\u00020TJ\u000e\u0010U\u001a\u00020\u00122\u0006\u0010F\u001a\u00020GJ&\u0010V\u001a\u00020\u00052\u0006\u0010W\u001a\u00020X2\u0006\u0010Y\u001a\u00020Z2\u0006\u0010[\u001a\u00020\u00052\u0006\u0010\\\u001a\u00020\u0005J.\u0010V\u001a\u00020\u00052\u0006\u0010W\u001a\u00020X2\u0006\u0010Y\u001a\u00020Z2\u0006\u0010[\u001a\u00020\u00052\u0006\u0010\\\u001a\u00020\u00052\u0006\u0010]\u001a\u00020\u0005R\u001a\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R$\u0010\u0007\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0005@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR$\u0010\f\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0005@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\t\"\u0004\b\u000e\u0010\u000b¨\u0006_"}, m1293d2 = {"Lcom/faceunity/core/faceunity/FUAIKit;", "", "()V", "hasLoadAIProcessor", "Ljava/util/concurrent/ConcurrentHashMap;", "", "value", "maxFaces", "getMaxFaces", "()I", "setMaxFaces", "(I)V", "maxHumans", "getMaxHumans", "setMaxHumans", "checkRotation", "", "clearCameraCache", "", "faceProcessorGetResultHairMask", StreamInformation.KEY_INDEX, "mask", "", "faceProcessorGetResultHeadMask", "faceProcessorSetDetectMode", "mode", "Lcom/faceunity/core/enumeration/FUFaceProcessorDetectModeEnum;", "faceProcessorSetFaceLandmarkQuality", "ratio", "faceProcessorSetFov", "fov", "", "faceProcessorSetMaxFaces", "faceProcessorSetMinFaceRatio", "fuFaceProcessorSetDetectSmallFace", "enable", "fuFaceProcessorSetFaceLandmarkHpOccu", "fuSetFaceAlgorithmConfig", "fuSetFaceModelConfig", "fuSetHumanAlgorithmConfig", "fuSetHumanModelConfig", "fuSetHumanSegMode", "fuPortraitSegmentationEnum", "Lcom/faceunity/core/enumeration/FUPortraitSegmentationEnum;", "fuSetModelToCPU", "getFaceInfo", "faceId", NamingTable.TAG, "", "", "getFaceProcessorGetConfidenceScore", "handDetectorGetResultGestureType", "handDetectorGetResultHandRect", "rect", "handDetectorGetResultHandScore", "handProcessorGetNumResults", "humanProcessorGetFov", "humanProcessorGetNumResults", "humanProcessorGetResultActionScore", "humanProcessorGetResultActionType", "humanProcessorGetResultHumanMask", "humanProcessorGetResultJoint2ds", "joint2ds", "humanProcessorGetResultJoint3ds", "humanProcessorGetResultRect", "humanProcessorGetResultTrackId", "humanProcessorReset", "humanProcessorSetFov", "humanProcessorSetMaxHumans", "isAIProcessorLoaded", "aiType", "Lcom/faceunity/core/enumeration/FUAITypeEnum;", "isTracking", "loadAIProcessor", "path", "preLoadAIProcessor", "releaseAIProcessor", "releaseAllAIProcessor", "setFaceDelayLeaveEnable", "setFaceProcessorDetectEveryNFramesWhenFace", "frameN", "setFaceProcessorDetectEveryNFramesWhenNoFace", "setHandDetectEveryNFramesWhenNoHand", "setHumanProcessorDetectMode", "Lcom/faceunity/core/enumeration/FUHumanProcessorDetectModeEnum;", "setTrackFaceAIType", "trackFace", "imgBuffer", "", MediaInformation.KEY_FORMAT_PROPERTIES, "Lcom/faceunity/core/enumeration/FUInputBufferEnum;", StreamInformation.KEY_WIDTH, StreamInformation.KEY_HEIGHT, ActionRecognitionParam.ROT_MODE, "Companion", "fu_core_all_featureRelease"}, m1294k = 1, m1295mv = {1, 1, 16})
public final class FUAIKit {

    public static final Companion INSTANCE = new Companion(null);
    private static volatile FUAIKit INSTANCE = null;
    public static final String TAG = "KIT_FUAIController";
    private final ConcurrentHashMap<Integer, Integer> hasLoadAIProcessor;
    private int maxFaces;
    private int maxHumans;

    @Metadata(m1291bv = {1, 0, 3}, m1294k = 3, m1295mv = {1, 1, 16})
    public final class WhenMappings {
        public static final int[] $EnumSwitchMapping$0;
        public static final int[] $EnumSwitchMapping$1;

        static {
            int[] iArr = new int[FUAITypeEnum.values().length];
            $EnumSwitchMapping$0 = iArr;
            iArr[FUAITypeEnum.FUAITYPE_FACEPROCESSOR.ordinal()] = 1;
            iArr[FUAITypeEnum.FUAITYPE_HUMAN_PROCESSOR.ordinal()] = 2;
            int[] iArr2 = new int[FUAITypeEnum.values().length];
            $EnumSwitchMapping$1 = iArr2;
            iArr2[FUAITypeEnum.FUAITYPE_FACEPROCESSOR.ordinal()] = 1;
            iArr2[FUAITypeEnum.FUAITYPE_HUMAN_PROCESSOR.ordinal()] = 2;
        }
    }

    @JvmStatic
    public static final FUAIKit getInstance() {
        return INSTANCE.getInstance();
    }

    private FUAIKit() {
        this.hasLoadAIProcessor = new ConcurrentHashMap<>();
        this.maxFaces = 4;
        this.maxHumans = 1;
    }

    public FUAIKit(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    @Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\u0007\u001a\u00020\u0004H\u0007R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000¨\u0006\b"}, m1293d2 = {"Lcom/faceunity/core/faceunity/FUAIKit$Companion;", "", "()V", "INSTANCE", "Lcom/faceunity/core/faceunity/FUAIKit;", "TAG", "", "getInstance", "fu_core_all_featureRelease"}, m1294k = 1, m1295mv = {1, 1, 16})
    public static final class Companion {
        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        public final FUAIKit getInstance() {
            if (FUAIKit.INSTANCE == null) {
                synchronized (this) {
                    if (FUAIKit.INSTANCE == null) {
                        FUAIKit.INSTANCE = new FUAIKit(null);
                    }
                    Unit unit = Unit.INSTANCE;
                }
            }
            FUAIKit fUAIKit = FUAIKit.INSTANCE;
            if (fUAIKit == null) {
                Intrinsics.throwNpe();
            }
            return fUAIKit;
        }
    }

    public final void loadAIProcessor(String path, FUAITypeEnum aiType) {
        Intrinsics.checkParameterIsNotNull(path, "path");
        Intrinsics.checkParameterIsNotNull(aiType, "aiType");
        if (isAIProcessorLoaded(aiType)) {
            int i = WhenMappings.$EnumSwitchMapping$0[aiType.ordinal()];
            if (i == 1) {
                faceProcessorSetMaxFaces(this.maxFaces);
                return;
            } else {
                if (i != 2) {
                    return;
                }
                humanProcessorSetMaxHumans(this.maxHumans);
                return;
            }
        }
        if (StringsKt.isBlank(path)) {
            FULogger.m294e(TAG, "loadAIProcessor failed   type=" + aiType.getType() + "  bundle path isBlank");
            return;
        }
        byte[] bArrLoadBundleFromLocal = FileUtils.loadBundleFromLocal(FURenderManager.INSTANCE.getMContext$fu_core_all_featureRelease(), path);
        if (bArrLoadBundleFromLocal == null) {
            FULogger.m294e(TAG, "loadAIProcessor failed  file not found: " + path);
            OperateCallback mOperateCallback$fu_core_all_featureRelease = FURenderManager.INSTANCE.getMOperateCallback$fu_core_all_featureRelease();
            if (mOperateCallback$fu_core_all_featureRelease != null) {
                mOperateCallback$fu_core_all_featureRelease.onFail(FURenderConfig.OPERATE_FAILED_FILE_NOT_FOUND, "file not found: " + path);
                return;
            }
            return;
        }
        if (aiType == FUAITypeEnum.FUAITYPE_TONGUETRACKING) {
            if (SDKController.INSTANCE.loadTongueModel$fu_core_all_featureRelease(bArrLoadBundleFromLocal, path)) {
                this.hasLoadAIProcessor.put(Integer.valueOf(aiType.getType()), Integer.valueOf(aiType.getType()));
                return;
            }
            return;
        }
        if (SDKController.INSTANCE.loadAIModelFromPackage$fu_core_all_featureRelease(bArrLoadBundleFromLocal, aiType.getType(), path)) {
            OperateCallback mOperateCallback$fu_core_all_featureRelease2 = FURenderManager.INSTANCE.getMOperateCallback$fu_core_all_featureRelease();
            if (mOperateCallback$fu_core_all_featureRelease2 != null) {
                mOperateCallback$fu_core_all_featureRelease2.onSuccess(201, "loadAIModel success path: " + path);
            }
            int i2 = WhenMappings.$EnumSwitchMapping$1[aiType.ordinal()];
            if (i2 == 1) {
                faceProcessorSetMaxFaces(this.maxFaces);
            } else if (i2 == 2) {
                humanProcessorSetMaxHumans(this.maxHumans);
            }
            this.hasLoadAIProcessor.put(Integer.valueOf(aiType.getType()), Integer.valueOf(aiType.getType()));
            return;
        }
        OperateCallback mOperateCallback$fu_core_all_featureRelease3 = FURenderManager.INSTANCE.getMOperateCallback$fu_core_all_featureRelease();
        if (mOperateCallback$fu_core_all_featureRelease3 != null) {
            mOperateCallback$fu_core_all_featureRelease3.onFail(FURenderConfig.OPERATE_FAILED_LOAD_AI_MODEL, "loadAIModel failed path: " + path);
        }
        FULogger.m294e(TAG, "loadAIProcessor failed  path: " + path + "  type: " + aiType.getType());
    }

    public final void preLoadAIProcessor(String path, FUAITypeEnum aiType) {
        Intrinsics.checkParameterIsNotNull(path, "path");
        Intrinsics.checkParameterIsNotNull(aiType, "aiType");
        if (StringsKt.isBlank(path)) {
            FULogger.m294e(TAG, "preLoadAIProcessor failed   type=" + aiType.getType() + "  bundle path isBlank");
            return;
        }
        byte[] bArrLoadBundleFromLocal = FileUtils.loadBundleFromLocal(FURenderManager.INSTANCE.getMContext$fu_core_all_featureRelease(), path);
        if (bArrLoadBundleFromLocal == null) {
            FULogger.m294e(TAG, "preLoadAIProcessor failed  file not found: " + path);
            OperateCallback mOperateCallback$fu_core_all_featureRelease = FURenderManager.INSTANCE.getMOperateCallback$fu_core_all_featureRelease();
            if (mOperateCallback$fu_core_all_featureRelease != null) {
                mOperateCallback$fu_core_all_featureRelease.onFail(FURenderConfig.OPERATE_FAILED_FILE_NOT_FOUND, "file not found: " + path);
                return;
            }
            return;
        }
        SDKController.INSTANCE.preProcessAIModelFromPackage$fu_core_all_featureRelease(bArrLoadBundleFromLocal, aiType.getType());
    }

    public final boolean isAIProcessorLoaded(FUAITypeEnum aiType) {
        Intrinsics.checkParameterIsNotNull(aiType, "aiType");
        return SDKController.INSTANCE.isAIModelLoaded$fu_core_all_featureRelease(aiType.getType());
    }

    public final void releaseAIProcessor(FUAITypeEnum aiType) {
        Intrinsics.checkParameterIsNotNull(aiType, "aiType");
        SDKController.INSTANCE.releaseAIModel$fu_core_all_featureRelease(aiType.getType());
        this.hasLoadAIProcessor.remove(Integer.valueOf(aiType.getType()));
    }

    public final void releaseAllAIProcessor() {
        for (Map.Entry<Integer, Integer> entry : this.hasLoadAIProcessor.entrySet()) {
            Intrinsics.checkExpressionValueIsNotNull(entry, "entries.next()");
            SDKController sDKController = SDKController.INSTANCE;
            Integer key = entry.getKey();
            Intrinsics.checkExpressionValueIsNotNull(key, "entry.key");
            sDKController.releaseAIModel$fu_core_all_featureRelease(key.intValue());
        }
        this.hasLoadAIProcessor.clear();
    }

    public final int trackFace(byte[] imgBuffer, FUInputBufferEnum format, int width, int height) {
        Intrinsics.checkParameterIsNotNull(imgBuffer, "imgBuffer");
        Intrinsics.checkParameterIsNotNull(format, "format");
        return trackFace(imgBuffer, format, width, height, -1);
    }

    public final int trackFace(byte[] imgBuffer, FUInputBufferEnum format, int width, int height, int rotMode) {
        Intrinsics.checkParameterIsNotNull(imgBuffer, "imgBuffer");
        Intrinsics.checkParameterIsNotNull(format, "format");
        if (width <= 0 || height <= 0) {
            return 0;
        }
        int currentRotationMode = SDKController.INSTANCE.getCurrentRotationMode();
        if (rotMode >= 0 && rotMode != currentRotationMode) {
            SDKController.INSTANCE.setDefaultRotationMode$fu_core_all_featureRelease(rotMode);
        }
        SDKController.INSTANCE.trackFace$fu_core_all_featureRelease(imgBuffer, format.getType(), width, height);
        int iIsTracking$fu_core_all_featureRelease = SDKController.INSTANCE.isTracking$fu_core_all_featureRelease();
        if (rotMode >= 0 && rotMode != currentRotationMode) {
            SDKController.INSTANCE.setDefaultRotationMode$fu_core_all_featureRelease(currentRotationMode);
        }
        return iIsTracking$fu_core_all_featureRelease;
    }

    public final int isTracking() {
        return SDKController.INSTANCE.isTracking$fu_core_all_featureRelease();
    }

    public final float getFaceProcessorGetConfidenceScore(int index) {
        return SDKController.INSTANCE.getFaceProcessorGetConfidenceScore$fu_core_all_featureRelease(index);
    }

    public final void getFaceInfo(int faceId, float[] value) {
        Intrinsics.checkParameterIsNotNull(value, "value");
        SDKController.INSTANCE.getFaceInfo$fu_core_all_featureRelease(faceId, "face_rect", value);
    }

    public final void getFaceInfo(int faceId, String name, float[] value) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        Intrinsics.checkParameterIsNotNull(value, "value");
        SDKController.INSTANCE.getFaceInfo$fu_core_all_featureRelease(faceId, name, value);
    }

    public final void getFaceInfo(int faceId, String name, int[] value) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        Intrinsics.checkParameterIsNotNull(value, "value");
        SDKController.INSTANCE.getFaceInfo$fu_core_all_featureRelease(faceId, name, value);
    }

    public final boolean checkRotation() {
        float[] fArr = new float[4];
        getFaceInfo(0, Key.ROTATION, fArr);
        double d = fArr[0];
        double d2 = fArr[1];
        double d3 = fArr[2];
        double d4 = fArr[3];
        double d5 = 2;
        double d6 = 1;
        double d7 = d2 * d2;
        double d8 = 180;
        double dAtan2 = (Math.atan2(d5 * ((d4 * d) + (d2 * d3)), d6 - (((d * d) + d7) * d5)) / 3.141592653589793d) * d8;
        double dAsin = (Math.asin(((d4 * d2) - (d3 * d)) * d5) / 3.141592653589793d) * d8;
        Math.atan2(((d4 * d3) + (d * d2)) * d5, d6 - (d5 * (d7 + (d3 * d3))));
        return dAtan2 > ((double) 30) || dAtan2 < ((double) (-30)) || dAsin > ((double) 15) || dAsin < ((double) (-15));
    }

    public final void clearCameraCache() {
        SDKController.INSTANCE.onCameraChange$fu_core_all_featureRelease();
    }

    public final void setTrackFaceAIType(FUAITypeEnum aiType) {
        Intrinsics.checkParameterIsNotNull(aiType, "aiType");
        SDKController.INSTANCE.setTrackFaceAIType$fu_core_all_featureRelease(aiType.getType());
    }

    public final int getMaxFaces() {
        return this.maxFaces;
    }

    public final void setMaxFaces(int i) {
        if (i != this.maxFaces) {
            this.maxFaces = i;
        }
        faceProcessorSetMaxFaces(i);
    }

    public final void faceProcessorSetMaxFaces(int maxFaces) {
        SDKController.INSTANCE.setMaxFaces$fu_core_all_featureRelease(maxFaces);
    }

    public final void setFaceProcessorDetectEveryNFramesWhenFace(int frameN) {
        SDKController.INSTANCE.m289xe12af11d(frameN);
    }

    public final void setFaceProcessorDetectEveryNFramesWhenNoFace(int frameN) {
        SDKController.INSTANCE.m290x3908915c(frameN);
    }

    public final void setHandDetectEveryNFramesWhenNoHand(int frameN) {
        SDKController.INSTANCE.setHandDetectEveryNFramesWhenNoHand$fu_core_all_featureRelease(frameN);
    }

    public final void fuFaceProcessorSetDetectSmallFace(boolean enable) {
        SDKController.INSTANCE.fuFaceProcessorSetDetectSmallFace$fu_core_all_featureRelease(enable ? 1 : 0);
    }

    public final void setFaceDelayLeaveEnable(boolean enable) {
        SDKController.INSTANCE.fuSetFaceDelayLeaveEnable$fu_core_all_featureRelease(enable);
    }

    public final void fuSetHumanSegMode(FUPortraitSegmentationEnum fuPortraitSegmentationEnum) {
        Intrinsics.checkParameterIsNotNull(fuPortraitSegmentationEnum, "fuPortraitSegmentationEnum");
        SDKController.INSTANCE.fuSetHumanSegMode$fu_core_all_featureRelease(fuPortraitSegmentationEnum.getIndex());
    }

    public final void fuSetFaceAlgorithmConfig(int value) {
        SDKController.INSTANCE.fuSetFaceAlgorithmConfig$fu_core_all_featureRelease(value);
    }

    public final void fuSetFaceModelConfig(int value) {
        SDKController.INSTANCE.fuSetFaceModelConfig$fu_core_all_featureRelease(value);
    }

    public final void fuSetHumanModelConfig(int value) {
        SDKController.INSTANCE.fuSetHumanModelConfig$fu_core_all_featureRelease(value);
    }

    public final void fuSetHumanAlgorithmConfig(int value) {
        SDKController.INSTANCE.fuSetHumanAlgorithmConfig$fu_core_all_featureRelease(value);
    }

    public final void fuSetModelToCPU() {
        SDKController.INSTANCE.fuSetModelToCPU$fu_core_all_featureRelease();
    }

    public final void faceProcessorSetFaceLandmarkQuality(int ratio) {
        SDKController.INSTANCE.faceProcessorSetFaceLandmarkQuality$fu_core_all_featureRelease(ratio);
    }

    public final void fuFaceProcessorSetFaceLandmarkHpOccu(int enable) {
        SDKController.INSTANCE.fuFaceProcessorSetFaceLandmarkHpOccu$fu_core_all_featureRelease(enable);
    }

    public final void faceProcessorSetMinFaceRatio(float ratio) {
        SDKController.INSTANCE.faceProcessorSetMinFaceRatio$fu_core_all_featureRelease(ratio);
    }

    public final void faceProcessorSetFov(float fov) {
        SDKController.INSTANCE.setFaceProcessorFov$fu_core_all_featureRelease(fov);
    }

    public final void faceProcessorGetResultHairMask(int index, float[] mask) {
        Intrinsics.checkParameterIsNotNull(mask, "mask");
        SDKController.INSTANCE.faceProcessorGetResultHairMask$fu_core_all_featureRelease(index, mask);
    }

    public final void faceProcessorGetResultHeadMask(int index, float[] mask) {
        Intrinsics.checkParameterIsNotNull(mask, "mask");
        SDKController.INSTANCE.faceProcessorGetResultHeadMask$fu_core_all_featureRelease(index, mask);
    }

    public final void faceProcessorSetDetectMode(FUFaceProcessorDetectModeEnum mode) {
        Intrinsics.checkParameterIsNotNull(mode, "mode");
        SDKController.INSTANCE.setFaceProcessorDetectMode$fu_core_all_featureRelease(mode.getType());
    }

    public final void setHumanProcessorDetectMode(FUHumanProcessorDetectModeEnum mode) {
        Intrinsics.checkParameterIsNotNull(mode, "mode");
        SDKController.INSTANCE.setHumanProcessorDetectMode$fu_core_all_featureRelease(mode.getType());
    }

    public final int getMaxHumans() {
        return this.maxHumans;
    }

    public final void setMaxHumans(int i) {
        if (i != this.maxHumans) {
            this.maxHumans = i;
        }
        humanProcessorSetMaxHumans(i);
    }

    public final void humanProcessorReset() {
        SDKController.INSTANCE.humanProcessorReset$fu_core_all_featureRelease();
    }

    public final void humanProcessorSetMaxHumans(int maxHumans) {
        SDKController.INSTANCE.humanProcessorSetMaxHumans$fu_core_all_featureRelease(maxHumans);
    }

    public final int humanProcessorGetNumResults() {
        return SDKController.INSTANCE.humanProcessorGetNumResults$fu_core_all_featureRelease();
    }

    public final int humanProcessorGetResultTrackId(int index) {
        return SDKController.INSTANCE.humanProcessorGetResultTrackId$fu_core_all_featureRelease(index);
    }

    public final void humanProcessorGetResultRect(int index, float[] rect) {
        Intrinsics.checkParameterIsNotNull(rect, "rect");
        SDKController.INSTANCE.humanProcessorGetResultRect$fu_core_all_featureRelease(index, rect);
    }

    public final void humanProcessorGetResultJoint2ds(int index, float[] joint2ds) {
        Intrinsics.checkParameterIsNotNull(joint2ds, "joint2ds");
        SDKController.INSTANCE.humanProcessorGetResultJoint2ds$fu_core_all_featureRelease(index, joint2ds);
    }

    public final void humanProcessorGetResultJoint3ds(int index, float[] joint2ds) {
        Intrinsics.checkParameterIsNotNull(joint2ds, "joint2ds");
        SDKController.INSTANCE.humanProcessorGetResultJoint3ds$fu_core_all_featureRelease(index, joint2ds);
    }

    public final float humanProcessorGetFov() {
        return SDKController.INSTANCE.humanProcessorGetFov$fu_core_all_featureRelease();
    }

    public final void humanProcessorSetFov(float fov) {
        SDKController.INSTANCE.humanProcessorSetFov$fu_core_all_featureRelease(fov);
    }

    public final int humanProcessorGetResultHumanMask(int index, float[] mask) {
        Intrinsics.checkParameterIsNotNull(mask, "mask");
        return SDKController.INSTANCE.humanProcessorGetResultHumanMask$fu_core_all_featureRelease(index, mask);
    }

    public final int humanProcessorGetResultActionType(int index) {
        return SDKController.INSTANCE.humanProcessorGetResultActionType$fu_core_all_featureRelease(index);
    }

    public final float humanProcessorGetResultActionScore(int index) {
        return SDKController.INSTANCE.humanProcessorGetResultActionScore$fu_core_all_featureRelease(index);
    }

    public final int handProcessorGetNumResults() {
        return SDKController.INSTANCE.handDetectorGetResultNumHands$fu_core_all_featureRelease();
    }

    public final int handDetectorGetResultHandRect(int index, float[] rect) {
        Intrinsics.checkParameterIsNotNull(rect, "rect");
        return SDKController.INSTANCE.handDetectorGetResultHandRect$fu_core_all_featureRelease(index, rect);
    }

    public final int handDetectorGetResultGestureType(int index) {
        return SDKController.INSTANCE.handDetectorGetResultGestureType$fu_core_all_featureRelease(index);
    }

    public final float handDetectorGetResultHandScore(int index) {
        return SDKController.INSTANCE.handDetectorGetResultHandScore$fu_core_all_featureRelease(index);
    }
}
