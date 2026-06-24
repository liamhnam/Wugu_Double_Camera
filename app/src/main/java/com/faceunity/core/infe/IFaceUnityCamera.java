package com.faceunity.core.infe;

import android.graphics.SurfaceTexture;
import com.arthenica.ffmpegkit.StreamInformation;
import com.faceunity.core.camera.BaseCamera;
import com.faceunity.core.camera.FUCameraPreviewData;
import com.faceunity.core.entity.FUCameraConfig;
import com.faceunity.core.enumeration.CameraFacingEnum;
import com.faceunity.core.listener.OnFUCameraListener;
import kotlin.Metadata;

@Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\bf\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0005H&J\b\u0010\u0007\u001a\u00020\u0003H&J\n\u0010\b\u001a\u0004\u0018\u00010\tH&J\n\u0010\n\u001a\u0004\u0018\u00010\u000bH&J\b\u0010\f\u001a\u00020\u0005H&J\b\u0010\r\u001a\u00020\u0005H&J\b\u0010\u000e\u001a\u00020\u000fH&J\n\u0010\u0010\u001a\u0004\u0018\u00010\u0011H&J\n\u0010\u0012\u001a\u0004\u0018\u00010\u0013H&J0\u0010\u0014\u001a\u00020\u00032\u0006\u0010\u0015\u001a\u00020\u00052\u0006\u0010\u0016\u001a\u00020\u00052\u0006\u0010\u0017\u001a\u00020\u000f2\u0006\u0010\u0018\u001a\u00020\u000f2\u0006\u0010\u0019\u001a\u00020\u0005H&J\"\u0010\u001a\u001a\u00020\u00032\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u00052\b\u0010\u001e\u001a\u0004\u0018\u00010\u001fH&J\b\u0010 \u001a\u00020\u0003H&J\u0010\u0010!\u001a\u00020\u00032\u0006\u0010\"\u001a\u00020\u000fH&J\u0010\u0010#\u001a\u00020\u00032\u0006\u0010\"\u001a\u00020\u000fH&J\b\u0010$\u001a\u00020\u0003H&¨\u0006%"}, m1293d2 = {"Lcom/faceunity/core/infe/IFaceUnityCamera;", "", "changeResolution", "", StreamInformation.KEY_WIDTH, "", StreamInformation.KEY_HEIGHT, "closeCamera", "getCameraByte", "Lcom/faceunity/core/camera/FUCameraPreviewData;", "getCameraFacing", "Lcom/faceunity/core/enumeration/CameraFacingEnum;", "getCameraHeight", "getCameraWidth", "getExposureCompensation", "", "getFaceUnityCamera", "Lcom/faceunity/core/camera/BaseCamera;", "getSurfaceTexture", "Landroid/graphics/SurfaceTexture;", "handleFocus", "viewWidth", "viewHeight", "rawX", "rawY", "areaSize", "openCamera", "config", "Lcom/faceunity/core/entity/FUCameraConfig;", "texId", "onCameraListener", "Lcom/faceunity/core/listener/OnFUCameraListener;", "releaseCamera", "setExposureCompensation", "value", "setZoomValue", "switchCamera", "fu_core_all_featureRelease"}, m1294k = 1, m1295mv = {1, 1, 16})
public interface IFaceUnityCamera {
    void changeResolution(int width, int height);

    void closeCamera();

    FUCameraPreviewData getCameraByte();

    CameraFacingEnum getCameraFacing();

    int getCameraHeight();

    int getCameraWidth();

    float getExposureCompensation();

    BaseCamera getFaceUnityCamera();

    SurfaceTexture getSurfaceTexture();

    void handleFocus(int viewWidth, int viewHeight, float rawX, float rawY, int areaSize);

    void openCamera(FUCameraConfig config, int texId, OnFUCameraListener onCameraListener);

    void releaseCamera();

    void setExposureCompensation(float value);

    void setZoomValue(float value);

    void switchCamera();
}
