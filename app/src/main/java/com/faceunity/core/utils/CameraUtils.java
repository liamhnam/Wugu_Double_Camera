package com.faceunity.core.utils;

import android.content.Context;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.RectF;
import android.hardware.Camera;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraManager;
import android.media.Image;
import android.text.TextUtils;
import android.util.Log;
import android.util.Size;
import android.view.Display;
import android.view.WindowManager;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.arthenica.ffmpegkit.StreamInformation;
import com.brother.sdk.print.pdl.PrintImageUtil;
import com.google.android.exoplayer2.text.ttml.TtmlNode;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

@Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u0000\u0088\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0007\n\u0002\u0010\u0015\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010$\n\u0002\b\f\n\u0002\u0010 \n\u0002\b\f\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0016\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fJ8\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0018\u001a\u00020\u00162\u0006\u0010\u0019\u001a\u00020\u0016H\u0002J\u001a\u0010\u001a\u001a\u00020\u000b2\n\u0010\u001b\u001a\u00060\u001cR\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u0004JC\u0010\u001f\u001a\u00020 2\f\u0010!\u001a\b\u0012\u0004\u0012\u00020 0\"2\u0006\u0010#\u001a\u00020\u00162\u0006\u0010$\u001a\u00020\u00162\u0006\u0010%\u001a\u00020\u00162\u0006\u0010&\u001a\u00020\u00162\u0006\u0010'\u001a\u00020 H\u0007¢\u0006\u0002\u0010(J\"\u0010)\u001a\u00020*2\n\u0010\u001b\u001a\u00060\u001cR\u00020\u001d2\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0016J\u0010\u0010+\u001a\u00020\u00162\u0006\u0010\u0012\u001a\u00020\u0016H\u0002J&\u0010,\u001a\n\u0012\u0004\u0012\u00020\u0016\u0018\u00010-2\u0006\u0010.\u001a\u00020/2\u0006\u00100\u001a\u00020\b2\u0006\u0010\u001e\u001a\u00020\u0004J\u000e\u00101\u001a\u00020\u00162\u0006\u0010\u0019\u001a\u00020\u0016J\u0010\u00102\u001a\u00020\u00132\b\u00103\u001a\u0004\u0018\u00010\u001dJ\u001a\u00104\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\b052\u0006\u00103\u001a\u00020\u001dJ\u0010\u00106\u001a\u00020\u00132\b\u00107\u001a\u0004\u0018\u00010\u001dJP\u00108\u001a\u00020\u000b2\b\u00103\u001a\u0004\u0018\u00010\u001d2\u0006\u00109\u001a\u00020\u00132\u0006\u0010:\u001a\u00020\u00132\u0006\u0010;\u001a\u00020\u00162\u0006\u0010<\u001a\u00020\u00162\u0006\u0010=\u001a\u00020\u00162\u0006\u0010>\u001a\u00020\u00162\u0006\u0010\u0018\u001a\u00020\u00162\u0006\u0010\u0019\u001a\u00020\u0016J\u0010\u0010?\u001a\u00020\u00042\b\u0010.\u001a\u0004\u0018\u00010/J\u001e\u0010@\u001a\u00020\u00162\f\u0010A\u001a\b\u0012\u0004\u0012\u00020\u00160B2\u0006\u0010C\u001a\u00020\u0013H\u0002J\u0018\u0010D\u001a\u00020\u000b2\u0006\u00103\u001a\u00020\u001d2\u0006\u0010E\u001a\u00020\bH\u0002J\u001e\u0010F\u001a\u00020\u000b2\u0006\u0010.\u001a\u00020/2\u0006\u00100\u001a\u00020\u00162\u0006\u00103\u001a\u00020\u001dJ\u0018\u0010G\u001a\u00020\u000b2\b\u00103\u001a\u0004\u0018\u00010\u001d2\u0006\u0010H\u001a\u00020\u0013J\u0012\u0010I\u001a\u00020\u000b2\n\u0010\u001b\u001a\u00060\u001cR\u00020\u001dJ\u001e\u0010J\u001a\u00020\u000b2\b\u00103\u001a\u0004\u0018\u00010\u001d2\f\u0010\u001b\u001a\b\u0018\u00010\u001cR\u00020\u001dJ\u0012\u0010K\u001a\u00020\u000b2\n\u0010\u001b\u001a\u00060\u001cR\u00020\u001dJ\u0018\u0010L\u001a\u00020\u000b2\b\u00107\u001a\u0004\u0018\u00010\u001d2\u0006\u0010M\u001a\u00020\u0013R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000R\u0016\u0010\u0007\u001a\n \t*\u0004\u0018\u00010\b0\bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006N"}, m1293d2 = {"Lcom/faceunity/core/utils/CameraUtils;", "", "()V", "DEBUG", "", "FOCUS_TIME", "", "TAG", "", "kotlin.jvm.PlatformType", "YUV420ToNV21", "", TtmlNode.TAG_IMAGE, "Landroid/media/Image;", "yuvDataBuffer", "", "calculateTapArea", "Landroid/graphics/Rect;", "x", "", "y", StreamInformation.KEY_WIDTH, "", StreamInformation.KEY_HEIGHT, "areaSize", "cameraFacing", "chooseFrameRate", "parameters", "Landroid/hardware/Camera$Parameters;", "Landroid/hardware/Camera;", "isHighestRate", "chooseOptimalSize", "Landroid/util/Size;", "choices", "", "textureViewWidth", "textureViewHeight", "maxWidth", "maxHeight", "aspectRatio", "([Landroid/util/Size;IIIILandroid/util/Size;)Landroid/util/Size;", "choosePreviewSize", "", "clamp", "getBestRange", "Landroid/util/Range;", "context", "Landroid/content/Context;", "cameraId", "getCameraOrientation", "getExposureCompensation", "camera", "getFullCameraParameters", "", "getMaxZoomScale", "mCamera", "handleFocusMetering", "rawX", "rawY", "viewWidth", "viewHeight", "cameraWidth", "cameraHeight", "hasCamera2", "indexByBinary", "ints", "", TypedValues.AttributesType.S_TARGET, "resetFocus", "focusMode", "setCameraDisplayOrientation", "setExposureCompensation", "value", "setFocusModes", "setParameters", "setVideoStabilization", "setZoom", "targetRatio", "fu_core_all_featureRelease"}, m1294k = 1, m1295mv = {1, 1, 16})
public final class CameraUtils {
    public static final boolean DEBUG = false;
    public static final long FOCUS_TIME = 2000;
    public static final CameraUtils INSTANCE = new CameraUtils();
    private static final String TAG = "CameraUtils";

    private final int clamp(int x) {
        int i = 1000;
        if (x <= 1000) {
            i = -1000;
            if (x >= -1000) {
                return x;
            }
        }
        return i;
    }

    private CameraUtils() {
    }

    public final boolean hasCamera2(Context context) {
        if (context == null) {
            return false;
        }
        try {
            Object systemService = context.getSystemService("camera");
            if (systemService == null) {
                throw new TypeCastException("null cannot be cast to non-null type android.hardware.camera2.CameraManager");
            }
            CameraManager cameraManager = (CameraManager) systemService;
            String[] cameraIdList = cameraManager.getCameraIdList();
            if (cameraIdList.length == 0) {
                return false;
            }
            int length = cameraIdList.length;
            int i = 0;
            while (true) {
                boolean z = true;
                if (i >= length) {
                    return true;
                }
                String str = cameraIdList[i];
                if (str == null) {
                    return false;
                }
                String str2 = str;
                int length2 = str2.length() - 1;
                int i2 = 0;
                boolean z2 = false;
                while (i2 <= length2) {
                    boolean z3 = str2.charAt(!z2 ? i2 : length2) <= ' ';
                    if (z2) {
                        if (!z3) {
                            break;
                        }
                        length2--;
                    } else if (z3) {
                        i2++;
                    } else {
                        z2 = true;
                    }
                }
                if (str2.subSequence(i2, length2 + 1).toString().length() != 0) {
                    z = false;
                }
                if (z) {
                    return false;
                }
                Integer num = (Integer) cameraManager.getCameraCharacteristics(str).get(CameraCharacteristics.INFO_SUPPORTED_HARDWARE_LEVEL);
                if (num != null && num.intValue() == 2) {
                    return false;
                }
                i++;
            }
        } catch (Exception unused) {
            return false;
        }
    }

    public final int getCameraOrientation(int cameraFacing) {
        try {
            Camera.CameraInfo cameraInfo = new Camera.CameraInfo();
            int numberOfCameras = Camera.getNumberOfCameras();
            int i = 0;
            while (true) {
                if (i >= numberOfCameras) {
                    i = -1;
                    break;
                }
                Camera.getCameraInfo(i, cameraInfo);
                if (cameraInfo.facing == cameraFacing) {
                    break;
                }
                i++;
            }
            if (i < 0) {
                return 90;
            }
            return cameraInfo.orientation;
        } catch (Exception unused) {
            return 90;
        }
    }

    public final void setCameraDisplayOrientation(Context context, int cameraId, Camera camera) {
        int i;
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(camera, "camera");
        Camera.CameraInfo cameraInfo = new Camera.CameraInfo();
        Camera.getCameraInfo(cameraId, cameraInfo);
        Object systemService = context.getSystemService("window");
        if (systemService == null) {
            throw new TypeCastException("null cannot be cast to non-null type android.view.WindowManager");
        }
        Display defaultDisplay = ((WindowManager) systemService).getDefaultDisplay();
        Intrinsics.checkExpressionValueIsNotNull(defaultDisplay, "windowManager.defaultDisplay");
        int rotation = defaultDisplay.getRotation();
        int i2 = 0;
        if (rotation != 0) {
            if (rotation == 1) {
                i2 = 90;
            } else if (rotation == 2) {
                i2 = 180;
            } else if (rotation == 3) {
                i2 = 270;
            }
        }
        if (cameraInfo.facing == 1) {
            i = (360 - ((cameraInfo.orientation + i2) % PrintImageUtil.ROUND_ROTATE)) % PrintImageUtil.ROUND_ROTATE;
        } else {
            i = ((cameraInfo.orientation - i2) + PrintImageUtil.ROUND_ROTATE) % PrintImageUtil.ROUND_ROTATE;
        }
        camera.setDisplayOrientation(i);
    }

    public final void setFocusModes(Camera.Parameters parameters) {
        Intrinsics.checkParameterIsNotNull(parameters, "parameters");
        List<String> supportedFocusModes = parameters.getSupportedFocusModes();
        if (supportedFocusModes.contains("continuous-video")) {
            parameters.setFocusMode("continuous-video");
        } else if (supportedFocusModes.contains("continuous-picture")) {
            parameters.setFocusMode("continuous-picture");
        } else if (supportedFocusModes.contains("auto")) {
            parameters.setFocusMode("auto");
        }
    }

    public final int[] choosePreviewSize(Camera.Parameters parameters, int width, int height) {
        Intrinsics.checkParameterIsNotNull(parameters, "parameters");
        for (Camera.Size size : parameters.getSupportedPreviewSizes()) {
            if (size.width == width && size.height == height) {
                parameters.setPreviewSize(width, height);
                return new int[]{width, height};
            }
        }
        Camera.Size preferredPreviewSizeForVideo = parameters.getPreferredPreviewSizeForVideo();
        if (preferredPreviewSizeForVideo != null) {
            parameters.setPreviewSize(preferredPreviewSizeForVideo.width, preferredPreviewSizeForVideo.height);
            return new int[]{preferredPreviewSizeForVideo.width, preferredPreviewSizeForVideo.height};
        }
        return new int[]{0, 0};
    }

    public final Size chooseOptimalSize(Size[] choices, int textureViewWidth, int textureViewHeight, int maxWidth, int maxHeight, Size aspectRatio) {
        Size size;
        Intrinsics.checkParameterIsNotNull(choices, "choices");
        Intrinsics.checkParameterIsNotNull(aspectRatio, "aspectRatio");
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        int width = aspectRatio.getWidth();
        int height = aspectRatio.getHeight();
        for (Size size2 : choices) {
            if (size2.getWidth() <= maxWidth && size2.getHeight() <= maxHeight && size2.getHeight() == (size2.getWidth() * height) / width) {
                if (size2.getWidth() >= textureViewWidth && size2.getHeight() >= textureViewHeight) {
                    arrayList.add(size2);
                } else {
                    arrayList2.add(size2);
                }
            }
        }
        CameraUtils$chooseOptimalSize$comparator$1 cameraUtils$chooseOptimalSize$comparator$1 = new Comparator<Size>() {
            @Override
            public final int compare(Size lhs, Size rhs) {
                Intrinsics.checkExpressionValueIsNotNull(lhs, "lhs");
                long width2 = ((long) lhs.getWidth()) * ((long) lhs.getHeight());
                Intrinsics.checkExpressionValueIsNotNull(rhs, "rhs");
                return Long.signum(width2 - (((long) rhs.getWidth()) * ((long) rhs.getHeight())));
            }
        };
        if (arrayList.size() > 0) {
            Object objMin = Collections.min(arrayList, cameraUtils$chooseOptimalSize$comparator$1);
            Intrinsics.checkExpressionValueIsNotNull(objMin, "Collections.min(bigEnough, comparator)");
            return (Size) objMin;
        }
        if (arrayList2.size() > 0) {
            size = (Size) Collections.max(arrayList2, cameraUtils$chooseOptimalSize$comparator$1);
        } else {
            Log.e(TAG, "Couldn't find any suitable preview size");
            size = choices[0];
        }
        Intrinsics.checkExpressionValueIsNotNull(size, "if (notBigEnough.size > …     choices[0]\n        }");
        return size;
    }

    public final void setVideoStabilization(Camera.Parameters parameters) {
        Intrinsics.checkParameterIsNotNull(parameters, "parameters");
        if (!parameters.isVideoStabilizationSupported() || parameters.getVideoStabilization()) {
            return;
        }
        parameters.setVideoStabilization(true);
    }

    public final float getExposureCompensation(Camera camera) {
        if (camera == null) {
            return 0.0f;
        }
        try {
            Camera.Parameters parameters = camera.getParameters();
            Intrinsics.checkExpressionValueIsNotNull(parameters, "parameters");
            float exposureCompensation = parameters.getExposureCompensation();
            float minExposureCompensation = parameters.getMinExposureCompensation();
            return (exposureCompensation - minExposureCompensation) / (parameters.getMaxExposureCompensation() - minExposureCompensation);
        } catch (Exception unused) {
            return 0.0f;
        }
    }

    public final void setExposureCompensation(Camera camera, float value) {
        if (camera == null) {
            return;
        }
        try {
            Camera.Parameters parameters = camera.getParameters();
            Intrinsics.checkExpressionValueIsNotNull(parameters, "parameters");
            float minExposureCompensation = parameters.getMinExposureCompensation();
            parameters.setExposureCompensation((int) ((value * (parameters.getMaxExposureCompensation() - minExposureCompensation)) + minExposureCompensation));
            camera.setParameters(parameters);
        } catch (Exception unused) {
        }
    }

    public final void setParameters(Camera camera, Camera.Parameters parameters) {
        if (camera == null || parameters == null) {
            return;
        }
        try {
            camera.setParameters(parameters);
        } catch (Exception unused) {
        }
    }

    public final void handleFocusMetering(Camera camera, float rawX, float rawY, int viewWidth, int viewHeight, int cameraWidth, int cameraHeight, int areaSize, int cameraFacing) {
        if (camera == null) {
            return;
        }
        try {
            Camera.Parameters parameters = camera.getParameters();
            Rect rectCalculateTapArea = calculateTapArea(cameraHeight * (rawX / viewWidth), cameraWidth * (rawY / viewHeight), cameraHeight, cameraWidth, areaSize, cameraFacing);
            Intrinsics.checkExpressionValueIsNotNull(parameters, "parameters");
            final String focusMode = parameters.getFocusMode();
            ArrayList arrayList = new ArrayList();
            arrayList.add(new Camera.Area(rectCalculateTapArea, 1000));
            ArrayList arrayList2 = new ArrayList();
            arrayList2.add(new Camera.Area(new Rect(rectCalculateTapArea), 1000));
            if (parameters.getMaxNumFocusAreas() > 0 && (Intrinsics.areEqual(focusMode, "auto") || Intrinsics.areEqual(focusMode, "macro") || Intrinsics.areEqual(focusMode, "continuous-picture") || Intrinsics.areEqual(focusMode, "continuous-video"))) {
                parameters.setFocusMode("auto");
                parameters.setFocusAreas(arrayList);
                if (parameters.getMaxNumMeteringAreas() > 0) {
                    parameters.setMeteringAreas(arrayList2);
                }
                camera.cancelAutoFocus();
                setParameters(camera, parameters);
                camera.autoFocus(new Camera.AutoFocusCallback() {
                    @Override
                    public final void onAutoFocus(boolean z, Camera camera2) {
                        CameraUtils cameraUtils = CameraUtils.INSTANCE;
                        Intrinsics.checkExpressionValueIsNotNull(camera2, "camera");
                        cameraUtils.resetFocus(camera2, focusMode);
                    }
                });
                return;
            }
            if (parameters.getMaxNumMeteringAreas() > 0) {
                if (!parameters.getSupportedFocusModes().contains("auto")) {
                    Log.w(TAG, "handleFocusMetering: not support focus");
                }
                parameters.setMeteringAreas(arrayList2);
                camera.cancelAutoFocus();
                setParameters(camera, parameters);
                camera.autoFocus(new Camera.AutoFocusCallback() {
                    @Override
                    public final void onAutoFocus(boolean z, Camera camera2) {
                        CameraUtils cameraUtils = CameraUtils.INSTANCE;
                        Intrinsics.checkExpressionValueIsNotNull(camera2, "camera");
                        String focusMode2 = focusMode;
                        Intrinsics.checkExpressionValueIsNotNull(focusMode2, "focusMode");
                        cameraUtils.resetFocus(camera2, focusMode2);
                    }
                });
                return;
            }
            camera.autoFocus(null);
        } catch (Exception e) {
            Log.e(TAG, "handleFocusMetering: ", e);
        }
    }

    public final void resetFocus(final Camera camera, final String focusMode) {
        ThreadHelper.getInstance().removeUiAllTasks();
        ThreadHelper.getInstance().runOnUiPostDelayed(new Runnable() {
            @Override
            public final void run() {
                try {
                    camera.cancelAutoFocus();
                    Camera.Parameters parameter = camera.getParameters();
                    Intrinsics.checkExpressionValueIsNotNull(parameter, "parameter");
                    parameter.setFocusMode(focusMode);
                    parameter.setFocusAreas(null);
                    parameter.setMeteringAreas(null);
                    CameraUtils.INSTANCE.setParameters(camera, parameter);
                } catch (Exception unused) {
                }
            }
        }, 2000L);
    }

    private final Rect calculateTapArea(float x, float y, int width, int height, int areaSize, int cameraFacing) {
        float f = x / width;
        float f2 = 2000;
        float f3 = 1000;
        int i = (int) ((f * f2) - f3);
        int i2 = (int) (((y / height) * f2) - f3);
        int i3 = areaSize / 2;
        int iClamp = clamp(i - i3);
        int iClamp2 = clamp(iClamp + areaSize);
        RectF rectF = new RectF(clamp(i2 - i3), iClamp, clamp(areaSize + r3), iClamp2);
        Matrix matrix = new Matrix();
        matrix.setScale(cameraFacing == 1 ? -1 : 1, -1.0f);
        matrix.mapRect(rectF);
        return new Rect(Math.round(rectF.left), Math.round(rectF.top), Math.round(rectF.right), Math.round(rectF.bottom));
    }

    public final Map<String, String> getFullCameraParameters(Camera camera) {
        Intrinsics.checkParameterIsNotNull(camera, "camera");
        HashMap map = new HashMap(64);
        try {
            Method getNativeParams = camera.getClass().getDeclaredMethod("native_getParameters", new Class[0]);
            Intrinsics.checkExpressionValueIsNotNull(getNativeParams, "getNativeParams");
            getNativeParams.setAccessible(true);
            Object objInvoke = getNativeParams.invoke(camera, new Object[0]);
            if (objInvoke == null) {
                throw new TypeCastException("null cannot be cast to non-null type kotlin.String");
            }
            TextUtils.SimpleStringSplitter<String> simpleStringSplitter = new TextUtils.SimpleStringSplitter(';');
            simpleStringSplitter.setString((String) objInvoke);
            for (String kv : simpleStringSplitter) {
                Intrinsics.checkExpressionValueIsNotNull(kv, "kv");
                int iIndexOf$default = StringsKt.indexOf$default((CharSequence) kv, '=', 0, false, 6, (Object) null);
                if (iIndexOf$default != -1) {
                    String strSubstring = kv.substring(0, iIndexOf$default);
                    Intrinsics.checkExpressionValueIsNotNull(strSubstring, "(this as java.lang.Strin…ing(startIndex, endIndex)");
                    String strSubstring2 = kv.substring(iIndexOf$default + 1);
                    Intrinsics.checkExpressionValueIsNotNull(strSubstring2, "(this as java.lang.String).substring(startIndex)");
                    map.put(strSubstring, strSubstring2);
                }
            }
            return map;
        } catch (Exception e) {
            String str = TAG;
            Log.e(str, "ex:", e);
            Log.e(str, "Unable to retrieve parameters from Camera.");
            return map;
        }
    }

    public final void YUV420ToNV21(Image image, byte[] yuvDataBuffer) {
        int i;
        Intrinsics.checkParameterIsNotNull(image, "image");
        Intrinsics.checkParameterIsNotNull(yuvDataBuffer, "yuvDataBuffer");
        Rect cropRect = image.getCropRect();
        int iWidth = cropRect.width();
        int iHeight = cropRect.height();
        Image.Plane[] planes = image.getPlanes();
        int i2 = 0;
        Image.Plane plane = planes[0];
        Intrinsics.checkExpressionValueIsNotNull(plane, "planes[0]");
        byte[] bArr = new byte[plane.getRowStride()];
        Intrinsics.checkExpressionValueIsNotNull(planes, "planes");
        int length = planes.length;
        int i3 = 1;
        int i4 = 0;
        int i5 = 0;
        int i6 = 1;
        while (i4 < length) {
            if (i4 != 0) {
                if (i4 == i3) {
                    i5 = (iWidth * iHeight) + i3;
                } else if (i4 == 2) {
                    i5 = iWidth * iHeight;
                }
                i6 = 2;
            } else {
                i5 = i2;
                i6 = i3;
            }
            Image.Plane plane2 = planes[i4];
            Intrinsics.checkExpressionValueIsNotNull(plane2, "planes[i]");
            ByteBuffer buffer = plane2.getBuffer();
            Image.Plane plane3 = planes[i4];
            Intrinsics.checkExpressionValueIsNotNull(plane3, "planes[i]");
            int rowStride = plane3.getRowStride();
            Image.Plane plane4 = planes[i4];
            Intrinsics.checkExpressionValueIsNotNull(plane4, "planes[i]");
            int pixelStride = plane4.getPixelStride();
            int i7 = i4 == 0 ? i2 : i3;
            int i8 = iWidth >> i7;
            int i9 = iHeight >> i7;
            Image.Plane[] planeArr = planes;
            int i10 = iWidth;
            buffer.position(((cropRect.top >> i7) * rowStride) + ((cropRect.left >> i7) * pixelStride));
            for (int i11 = 0; i11 < i9; i11++) {
                if (pixelStride == 1 && i6 == 1) {
                    buffer.get(yuvDataBuffer, i5, i8);
                    i5 += i8;
                    i = i8;
                } else {
                    i = ((i8 - 1) * pixelStride) + 1;
                    buffer.get(bArr, 0, i);
                    for (int i12 = 0; i12 < i8; i12++) {
                        yuvDataBuffer[i5] = bArr[i12 * pixelStride];
                        i5 += i6;
                    }
                }
                if (i11 < i9 - 1) {
                    buffer.position((buffer.position() + rowStride) - i);
                }
            }
            i4++;
            planes = planeArr;
            iWidth = i10;
            i2 = 0;
            i3 = 1;
        }
    }

    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final android.util.Range<java.lang.Integer> getBestRange(android.content.Context r9, java.lang.String r10, boolean r11) {
        /*
            Method dump skipped, instruction units count: 314
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.faceunity.core.utils.CameraUtils.getBestRange(android.content.Context, java.lang.String, boolean):android.util.Range");
    }

    public final void chooseFrameRate(Camera.Parameters parameters, boolean isHighestRate) {
        Intrinsics.checkParameterIsNotNull(parameters, "parameters");
        List<int[]> supportedPreviewFpsRange = parameters.getSupportedPreviewFpsRange();
        int[] iArr = supportedPreviewFpsRange.get(0);
        if (isHighestRate) {
            for (int[] iArr2 : supportedPreviewFpsRange) {
                int i = iArr2[0];
                int i2 = iArr2[1];
                int i3 = iArr[1];
                if (i2 > i3 || (i2 == i3 && i > iArr[0])) {
                    iArr = iArr2;
                }
            }
        } else {
            for (int[] iArr3 : supportedPreviewFpsRange) {
                int i4 = iArr3[0];
                int i5 = iArr3[1];
                if (i4 >= 7000 && i4 <= 15000 && i5 - i4 > iArr[1] - iArr[0]) {
                    iArr = iArr3;
                }
            }
        }
        parameters.setPreviewFpsRange(iArr[0], iArr[1]);
    }

    public final void setZoom(Camera mCamera, float targetRatio) {
        List<Integer> zoomRatios;
        int iIndexByBinary;
        if (mCamera == null) {
            return;
        }
        Camera.Parameters parameters = mCamera.getParameters();
        Intrinsics.checkExpressionValueIsNotNull(parameters, "mCamera.parameters");
        if (!parameters.isZoomSupported() || (zoomRatios = parameters.getZoomRatios()) == null || zoomRatios.isEmpty() || (iIndexByBinary = indexByBinary(zoomRatios, targetRatio * 100)) == parameters.getZoom()) {
            return;
        }
        parameters.setZoom(iIndexByBinary);
        mCamera.setParameters(parameters);
    }

    public final float getMaxZoomScale(Camera mCamera) {
        List<Integer> zoomRatios;
        if (mCamera == null) {
            return 1.0f;
        }
        Camera.Parameters parameters = mCamera.getParameters();
        Intrinsics.checkExpressionValueIsNotNull(parameters, "mCamera.parameters");
        if (!parameters.isZoomSupported() || (zoomRatios = parameters.getZoomRatios()) == null || zoomRatios.size() != parameters.getMaxZoom() + 1) {
            return 1.0f;
        }
        Integer minZoom = zoomRatios.get(0);
        float fIntValue = zoomRatios.get(zoomRatios.size() - 1).intValue();
        Intrinsics.checkExpressionValueIsNotNull(minZoom, "minZoom");
        return fIntValue / minZoom.intValue();
    }

    private final int indexByBinary(List<Integer> ints, float target) {
        int size = ints.size() - 1;
        int i = 0;
        if (ints.size() == 1 || target <= ints.get(0).floatValue()) {
            return 0;
        }
        if (target >= ints.get(size).floatValue()) {
            return size;
        }
        int i2 = 0;
        while (i <= size) {
            i2 = (i + size) / 2;
            int i3 = i2 + 1;
            if (Math.abs(ints.get(i3).floatValue() - target) > Math.abs(ints.get(i2).floatValue() - target)) {
                size = i2 - 1;
            } else {
                i = i3;
            }
        }
        int i4 = i2 + 1;
        return Math.abs(ints.get(i4).floatValue() - target) > Math.abs(ints.get(i2).floatValue() - target) ? i2 : i4;
    }
}
