package com.faceunity.core.controller.poster;

import androidx.constraintlayout.motion.widget.Key;
import com.faceunity.core.controller.BaseSingleController;
import com.faceunity.core.controller.action.ActionRecognitionParam;
import com.faceunity.core.controller.bgSegGreen.BgSegGreenParam;
import com.faceunity.core.entity.FUBundleData;
import com.faceunity.core.entity.FUFeaturesData;
import com.faceunity.core.support.SDKController;
import com.faceunity.core.utils.FULogger;
import com.faceunity.wrapper.faceunity;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

@Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0010\u0013\n\u0000\n\u0002\u0010\u0014\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\n\n\u0002\u0010\u0012\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0014J\r\u0010\u0007\u001a\u00020\bH\u0000¢\u0006\u0002\b\tJ\u0015\u0010\n\u001a\u00020\u00042\u0006\u0010\u000b\u001a\u00020\fH\u0000¢\u0006\u0002\b\rJ\u0010\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011H\u0002J\u001d\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0014H\u0000¢\u0006\u0002\b\u0016J\u001d\u0010\u0017\u001a\u00020\u00042\u0006\u0010\u0018\u001a\u00020\u00142\u0006\u0010\u0019\u001a\u00020\u0011H\u0000¢\u0006\u0002\b\u001aJ\b\u0010\u001b\u001a\u00020\u0011H\u0002J-\u0010\u001c\u001a\u00020\u00042\u0006\u0010\u001d\u001a\u00020\u00142\u0006\u0010\u001e\u001a\u00020\u00142\u0006\u0010\u0010\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020\u0011H\u0000¢\u0006\u0002\b!J-\u0010\"\u001a\u00020\u00042\u0006\u0010\u001d\u001a\u00020\u00142\u0006\u0010\u001e\u001a\u00020\u00142\u0006\u0010\u0010\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020\u0011H\u0000¢\u0006\u0002\b#J\u001d\u0010$\u001a\u00020\u00042\u000e\u0010%\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010&H\u0010¢\u0006\u0002\b'¨\u0006("}, m1293d2 = {"Lcom/faceunity/core/controller/poster/PosterController;", "Lcom/faceunity/core/controller/BaseSingleController;", "()V", "applyControllerBundle", "", "featuresData", "Lcom/faceunity/core/entity/FUFeaturesData;", "checkRotation", "", "checkRotation$fu_core_all_featureRelease", "fixPosterFaceParam", "value", "", "fixPosterFaceParam$fu_core_all_featureRelease", "floatArrayToDoubleArray", "", "input", "", "getFaceRectData", "i", "", ActionRecognitionParam.ROT_MODE, "getFaceRectData$fu_core_all_featureRelease", "getLandmarksData", "faceId", "landmarks", "getLandmarksData$fu_core_all_featureRelease", "getRotationData", "loadPosterPhoto", "inputWidth", "inputHeight", "", "landmark", "loadPosterPhoto$fu_core_all_featureRelease", "loadPosterTemplate", "loadPosterTemplate$fu_core_all_featureRelease", "release", "unit", "Lkotlin/Function0;", "release$fu_core_all_featureRelease", "fu_core_all_featureRelease"}, m1294k = 1, m1295mv = {1, 1, 16})
public final class PosterController extends BaseSingleController {
    @Override
    protected void applyControllerBundle(FUFeaturesData featuresData) {
        Intrinsics.checkParameterIsNotNull(featuresData, "featuresData");
        FUBundleData bundle = featuresData.getBundle();
        int iLoadBundleFile = bundle != null ? getMBundleManager().loadBundleFile(bundle.getName(), bundle.getPath()) : 0;
        if (iLoadBundleFile <= 0) {
            getMBundleManager().destroyControllerBundle(getMControllerBundleHandle());
            setMControllerBundleHandle$fu_core_all_featureRelease(-1);
            String tag = getTAG();
            StringBuilder sbAppend = new StringBuilder("loadControllerBundle failed handle:").append(iLoadBundleFile).append("  path:");
            FUBundleData bundle2 = featuresData.getBundle();
            FULogger.m294e(tag, sbAppend.append(bundle2 != null ? bundle2.getPath() : null).toString());
            return;
        }
        if (getMControllerBundleHandle() != iLoadBundleFile) {
            getMBundleManager().destroyControllerBundle(getMControllerBundleHandle());
        }
        setMControllerBundleHandle$fu_core_all_featureRelease(iLoadBundleFile);
    }

    public final void loadPosterPhoto$fu_core_all_featureRelease(int inputWidth, int inputHeight, byte[] input, float[] landmark) {
        Intrinsics.checkParameterIsNotNull(input, "input");
        Intrinsics.checkParameterIsNotNull(landmark, "landmark");
        double[] dArrFloatArrayToDoubleArray = floatArrayToDoubleArray(landmark);
        itemSetParam("input_width", Integer.valueOf(inputWidth));
        itemSetParam("input_height", Integer.valueOf(inputHeight));
        itemSetParam("input_face_points", dArrFloatArrayToDoubleArray);
        SDKController.INSTANCE.createTexForItem$fu_core_all_featureRelease(getMControllerBundleHandle(), "tex_input", input, inputWidth, inputHeight);
    }

    public final void loadPosterTemplate$fu_core_all_featureRelease(int inputWidth, int inputHeight, byte[] input, float[] landmark) {
        Intrinsics.checkParameterIsNotNull(input, "input");
        Intrinsics.checkParameterIsNotNull(landmark, "landmark");
        double[] dArrFloatArrayToDoubleArray = floatArrayToDoubleArray(landmark);
        itemSetParam("template_width", Integer.valueOf(inputWidth));
        itemSetParam("template_height", Integer.valueOf(inputHeight));
        itemSetParam("template_face_points", dArrFloatArrayToDoubleArray);
        SDKController.INSTANCE.createTexForItem$fu_core_all_featureRelease(getMControllerBundleHandle(), BgSegGreenParam.TEX_TEMPLATE, input, inputWidth, inputHeight);
    }

    public final void fixPosterFaceParam$fu_core_all_featureRelease(double value) {
        FULogger.m295i(getTAG(), "fixPosterFaceParam value:" + value);
        itemSetParam("warp_intensity", Double.valueOf(value));
    }

    public final boolean checkRotation$fu_core_all_featureRelease() {
        float[] rotationData = getRotationData();
        double d = rotationData[0];
        double d2 = rotationData[1];
        double d3 = rotationData[2];
        double d4 = rotationData[3];
        double d5 = 2;
        double d6 = 1;
        double d7 = d2 * d2;
        double d8 = 180;
        double dAtan2 = (Math.atan2(((d4 * d) + (d2 * d3)) * d5, d6 - (((d * d) + d7) * d5)) / 3.141592653589793d) * d8;
        double dAsin = (Math.asin(((d4 * d2) - (d3 * d)) * d5) / 3.141592653589793d) * d8;
        Math.atan2(((d4 * d3) + (d * d2)) * d5, d6 - (d5 * (d7 + (d3 * d3))));
        return dAtan2 > ((double) 30) || dAtan2 < ((double) (-30)) || dAsin > ((double) 15) || dAsin < ((double) (-15));
    }

    private final float[] getRotationData() {
        float[] fArr = new float[4];
        SDKController.INSTANCE.getFaceInfo$fu_core_all_featureRelease(0, Key.ROTATION, fArr);
        return fArr;
    }

    public final void getLandmarksData$fu_core_all_featureRelease(int faceId, float[] landmarks) {
        Intrinsics.checkParameterIsNotNull(landmarks, "landmarks");
        if (faceunity.fuIsTracking() > 0) {
            SDKController.INSTANCE.getFaceInfo$fu_core_all_featureRelease(faceId, "landmarks_origin", landmarks);
        }
    }

    public final float[] getFaceRectData$fu_core_all_featureRelease(int i, int rotMode) {
        float[] fArr = new float[4];
        SDKController.INSTANCE.getFaceInfo$fu_core_all_featureRelease(i, "face_rect_origin", fArr);
        return fArr;
    }

    @Override
    public void release$fu_core_all_featureRelease(Function0<Unit> unit) throws InterruptedException {
        super.release$fu_core_all_featureRelease(new Function0<Unit>() {
            {
                super(0);
            }

            @Override
            public Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            public final void invoke2() {
                this.this$0.deleteItemTex(BgSegGreenParam.TEX_TEMPLATE);
                this.this$0.deleteItemTex("tex_input");
            }
        });
    }

    private final double[] floatArrayToDoubleArray(float[] input) {
        double[] dArr = new double[input.length];
        int length = input.length;
        for (int i = 0; i < length; i++) {
            dArr[i] = input[i];
        }
        return dArr;
    }
}
