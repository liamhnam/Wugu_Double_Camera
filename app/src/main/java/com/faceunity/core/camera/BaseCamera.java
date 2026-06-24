package com.faceunity.core.camera;

import android.graphics.SurfaceTexture;
import com.faceunity.core.enumeration.CameraFacingEnum;
import com.p020hp.jipp.model.Status;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0017\n\u0002\u0010\u000b\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0010\u0007\n\u0002\b\u0016\b&\u0018\u0000 S2\u00020\u0001:\u0001SB\u0005¢\u0006\u0002\u0010\u0002J\u001d\u00106\u001a\u0002072\u0006\u00108\u001a\u00020\u00042\u0006\u00109\u001a\u00020\u0004H ¢\u0006\u0002\b:J\r\u0010;\u001a\u000207H ¢\u0006\u0002\b<J\r\u0010=\u001a\u00020>H ¢\u0006\u0002\b?J\r\u0010@\u001a\u00020>H ¢\u0006\u0002\bAJ5\u0010B\u001a\u0002072\u0006\u0010C\u001a\u00020\u00042\u0006\u0010D\u001a\u00020\u00042\u0006\u0010E\u001a\u00020>2\u0006\u0010F\u001a\u00020>2\u0006\u0010G\u001a\u00020\u0004H ¢\u0006\u0002\bHJ\r\u0010I\u001a\u000207H ¢\u0006\u0002\bJJ\b\u0010K\u001a\u000207H&J\u0015\u0010L\u001a\u0002072\u0006\u0010M\u001a\u00020>H ¢\u0006\u0002\bNJ\u0015\u0010O\u001a\u0002072\u0006\u0010M\u001a\u00020>H ¢\u0006\u0002\bPJ\b\u0010Q\u001a\u000207H&J\u0006\u0010R\u001a\u000207R\u001a\u0010\u0003\u001a\u00020\u0004X\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\u0004X\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u0006\"\u0004\b\u000b\u0010\bR\u001a\u0010\f\u001a\u00020\rX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u001a\u0010\u0012\u001a\u00020\u0004X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0006\"\u0004\b\u0014\u0010\bR\u001a\u0010\u0015\u001a\u00020\u0004X\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0006\"\u0004\b\u0017\u0010\bR\u001a\u0010\u0018\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u0006\"\u0004\b\u001a\u0010\bR\u001a\u0010\u001b\u001a\u00020\u0004X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u0006\"\u0004\b\u001d\u0010\bR\u001a\u0010\u001e\u001a\u00020\u0004X\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010\u0006\"\u0004\b \u0010\bR\u001a\u0010!\u001a\u00020\u0004X\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\"\u0010\u0006\"\u0004\b#\u0010\bR\u001a\u0010$\u001a\u00020%X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b&\u0010'\"\u0004\b(\u0010)R\u001a\u0010*\u001a\u00020%X\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b+\u0010'\"\u0004\b,\u0010)R\u001a\u0010-\u001a\u00020%X\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b.\u0010'\"\u0004\b/\u0010)R\u001c\u00100\u001a\u0004\u0018\u000101X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b2\u00103\"\u0004\b4\u00105¨\u0006T"}, m1293d2 = {"Lcom/faceunity/core/camera/BaseCamera;", "", "()V", "mBackCameraId", "", "getMBackCameraId", "()I", "setMBackCameraId", "(I)V", "mBackCameraOrientation", "getMBackCameraOrientation", "setMBackCameraOrientation", "mCameraFacing", "Lcom/faceunity/core/enumeration/CameraFacingEnum;", "getMCameraFacing$fu_core_all_featureRelease", "()Lcom/faceunity/core/enumeration/CameraFacingEnum;", "setMCameraFacing$fu_core_all_featureRelease", "(Lcom/faceunity/core/enumeration/CameraFacingEnum;)V", "mCameraHeight", "getMCameraHeight$fu_core_all_featureRelease", "setMCameraHeight$fu_core_all_featureRelease", "mCameraOrientation", "getMCameraOrientation", "setMCameraOrientation", "mCameraTexId", "getMCameraTexId", "setMCameraTexId", "mCameraWidth", "getMCameraWidth$fu_core_all_featureRelease", "setMCameraWidth$fu_core_all_featureRelease", "mFrontCameraId", "getMFrontCameraId", "setMFrontCameraId", "mFrontCameraOrientation", "getMFrontCameraOrientation", "setMFrontCameraOrientation", "mIsHighestRate", "", "getMIsHighestRate$fu_core_all_featureRelease", "()Z", "setMIsHighestRate$fu_core_all_featureRelease", "(Z)V", "mIsPreviewing", "getMIsPreviewing", "setMIsPreviewing", "mIsStopPreview", "getMIsStopPreview", "setMIsStopPreview", "mSurfaceTexture", "Landroid/graphics/SurfaceTexture;", "getMSurfaceTexture", "()Landroid/graphics/SurfaceTexture;", "setMSurfaceTexture", "(Landroid/graphics/SurfaceTexture;)V", "changeResolution", "", "cameraWidth", "cameraHeight", "changeResolution$fu_core_all_featureRelease", "closeCamera", "closeCamera$fu_core_all_featureRelease", "getExposureCompensation", "", "getExposureCompensation$fu_core_all_featureRelease", "getZoom", "getZoom$fu_core_all_featureRelease", "handleFocus", "viewWidth", "viewHeight", "rawX", "rawY", "areaSize", "handleFocus$fu_core_all_featureRelease", "initCameraInfo", "initCameraInfo$fu_core_all_featureRelease", "openCamera", "setExposureCompensation", "value", "setExposureCompensation$fu_core_all_featureRelease", "setZoom", "setZoom$fu_core_all_featureRelease", "startPreview", "switchCamera", "Companion", "fu_core_all_featureRelease"}, m1294k = 1, m1295mv = {1, 1, 16})
public abstract class BaseCamera {
    public static final int BACK_CAMERA_ORIENTATION = 90;
    public static final int FRONT_CAMERA_ORIENTATION = 270;
    public static final int PREVIEW_BUFFER_SIZE = 3;
    public static final String TAG = "KIT_BaseCamera";
    private int mBackCameraId;
    private int mFrontCameraId;
    private boolean mIsHighestRate;
    private boolean mIsPreviewing;
    private boolean mIsStopPreview;
    private SurfaceTexture mSurfaceTexture;
    private CameraFacingEnum mCameraFacing = CameraFacingEnum.CAMERA_FRONT;
    private int mCameraWidth = Status.Code.serverErrorInternalError;
    private int mCameraHeight = 720;
    private int mCameraOrientation = 270;
    private int mBackCameraOrientation = 90;
    private int mFrontCameraOrientation = 270;
    private int mCameraTexId = 100;

    public abstract void changeResolution$fu_core_all_featureRelease(int cameraWidth, int cameraHeight);

    public abstract void closeCamera$fu_core_all_featureRelease();

    public abstract float getExposureCompensation$fu_core_all_featureRelease();

    public abstract float getZoom$fu_core_all_featureRelease();

    public abstract void handleFocus$fu_core_all_featureRelease(int viewWidth, int viewHeight, float rawX, float rawY, int areaSize);

    public abstract void initCameraInfo$fu_core_all_featureRelease();

    public abstract void openCamera();

    public abstract void setExposureCompensation$fu_core_all_featureRelease(float value);

    public abstract void setZoom$fu_core_all_featureRelease(float value);

    public abstract void startPreview();

    public final boolean getMIsHighestRate() {
        return this.mIsHighestRate;
    }

    public final void setMIsHighestRate$fu_core_all_featureRelease(boolean z) {
        this.mIsHighestRate = z;
    }

    protected final int getMFrontCameraId() {
        return this.mFrontCameraId;
    }

    protected final void setMFrontCameraId(int i) {
        this.mFrontCameraId = i;
    }

    protected final int getMBackCameraId() {
        return this.mBackCameraId;
    }

    protected final void setMBackCameraId(int i) {
        this.mBackCameraId = i;
    }

    protected final boolean getMIsPreviewing() {
        return this.mIsPreviewing;
    }

    protected final void setMIsPreviewing(boolean z) {
        this.mIsPreviewing = z;
    }

    protected final boolean getMIsStopPreview() {
        return this.mIsStopPreview;
    }

    protected final void setMIsStopPreview(boolean z) {
        this.mIsStopPreview = z;
    }

    public final CameraFacingEnum getMCameraFacing() {
        return this.mCameraFacing;
    }

    public final void setMCameraFacing$fu_core_all_featureRelease(CameraFacingEnum cameraFacingEnum) {
        Intrinsics.checkParameterIsNotNull(cameraFacingEnum, "<set-?>");
        this.mCameraFacing = cameraFacingEnum;
    }

    public final int getMCameraWidth() {
        return this.mCameraWidth;
    }

    public final void setMCameraWidth$fu_core_all_featureRelease(int i) {
        this.mCameraWidth = i;
    }

    public final int getMCameraHeight() {
        return this.mCameraHeight;
    }

    public final void setMCameraHeight$fu_core_all_featureRelease(int i) {
        this.mCameraHeight = i;
    }

    protected final int getMCameraOrientation() {
        return this.mCameraOrientation;
    }

    protected final void setMCameraOrientation(int i) {
        this.mCameraOrientation = i;
    }

    protected final int getMBackCameraOrientation() {
        return this.mBackCameraOrientation;
    }

    protected final void setMBackCameraOrientation(int i) {
        this.mBackCameraOrientation = i;
    }

    protected final int getMFrontCameraOrientation() {
        return this.mFrontCameraOrientation;
    }

    protected final void setMFrontCameraOrientation(int i) {
        this.mFrontCameraOrientation = i;
    }

    public final int getMCameraTexId() {
        return this.mCameraTexId;
    }

    public final void setMCameraTexId(int i) {
        this.mCameraTexId = i;
    }

    public final SurfaceTexture getMSurfaceTexture() {
        return this.mSurfaceTexture;
    }

    public final void setMSurfaceTexture(SurfaceTexture surfaceTexture) {
        this.mSurfaceTexture = surfaceTexture;
    }

    public final void switchCamera() {
        this.mIsStopPreview = true;
        CameraFacingEnum cameraFacingEnum = this.mCameraFacing == CameraFacingEnum.CAMERA_FRONT ? CameraFacingEnum.CAMERA_BACK : CameraFacingEnum.CAMERA_FRONT;
        this.mCameraFacing = cameraFacingEnum;
        this.mCameraOrientation = cameraFacingEnum == CameraFacingEnum.CAMERA_FRONT ? this.mFrontCameraOrientation : this.mBackCameraOrientation;
        closeCamera$fu_core_all_featureRelease();
        openCamera();
        this.mIsStopPreview = false;
    }
}
