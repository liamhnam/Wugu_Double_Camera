package com.wugu.doublecamera.device.camera.camera2;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.SurfaceTexture;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraManager;
import android.hardware.camera2.params.StreamConfigurationMap;
import android.util.Size;
import androidx.core.app.ActivityCompat;
import com.hjq.permissions.Permission;
import com.wugu.doublecamera.custom.AutoSizeGLSurface;
import com.wugu.doublecamera.database.SpManager;
import com.wugu.doublecamera.listener.IBitmapListener;
import com.wugu.doublecamera.listener.ICaptureListener;
import com.wugu.doublecamera.utils.AppUtil;
import com.wugu.doublecamera.utils.LoggerUtil;
import com.wugu.facebeauty.ITakePhotoListener;
import com.wugu.facebeauty.SurfaceRender;
import com.wugu.facebeauty.bean.BeautifyItem;

public class Camera2Handler implements ITakePhotoListener {
    private static final int REQUEST_CAMERA_PERMISSION = 200;
    private final Activity activity;
    private final BeautifyItem beautyItem;
    private final IBitmapListener bitmapListener;
    private final ICaptureListener captureListener;
    private int frameHeight;
    private int frameWidth;
    private final AutoSizeGLSurface glSurfaceView;
    private Size previewSize;
    private SurfaceRender surfaceRender;

    @Override
    public void onSurfaceCreated() {
    }

    public Camera2Handler(Activity activity, AutoSizeGLSurface autoSizeGLSurface, BeautifyItem beautifyItem, ICaptureListener iCaptureListener, IBitmapListener iBitmapListener) {
        this.activity = activity;
        this.glSurfaceView = autoSizeGLSurface;
        this.beautyItem = beautifyItem;
        this.captureListener = iCaptureListener;
        this.bitmapListener = iBitmapListener;
        openCamera();
    }

    public void close() {
        this.surfaceRender.onPause();
        this.surfaceRender.onDestroy();
    }

    public void stopCamera() {
        this.surfaceRender.closeCamera();
    }

    public void reopenCamera() {
        this.surfaceRender.reopenCamera();
    }

    public void reconnect() {
        openCamera();
    }

    public void setBeautyFace(BeautifyItem beautifyItem) {
        this.surfaceRender.setBeautyLevel(beautifyItem);
    }

    public void setBitmapRect(int i, int i2) {
        this.frameWidth = i;
        this.frameHeight = i2;
        this.glSurfaceView.setAspectRation(i, i2);
    }

    public void setEffectItem(int i, String str) {
        this.surfaceRender.setEffect(i, str);
    }

    public void startRecord() {
        this.surfaceRender.startRecord();
    }

    public void capture() {
        this.surfaceRender.takePhoto();
    }

    public void setExposure(float f) {
        this.surfaceRender.setExposure(f);
    }

    public void setZoom(float f) {
        this.surfaceRender.setZoom(f);
    }

    @Override
    public void captureFinish(final Bitmap bitmap) {
        AppUtil.runOnUI(new Runnable() {
            @Override
            public final void run() {
                this.f$0.m873xb3bc4a8c(bitmap);
            }
        });
    }

    void m873xb3bc4a8c(Bitmap bitmap) {
        IBitmapListener iBitmapListener = this.bitmapListener;
        if (iBitmapListener != null) {
            iBitmapListener.onBitmapResult(getRectBitmap(bitmap, this.frameWidth, this.frameHeight), 0);
        }
        bitmap.recycle();
        this.captureListener.finishCapture(true);
    }

    @Override
    public void recordFinish(String str) {
        ICaptureListener iCaptureListener = this.captureListener;
        if (iCaptureListener != null) {
            iCaptureListener.recordEnd(str);
        }
    }

    private void openCamera() {
        StreamConfigurationMap streamConfigurationMap;
        CameraManager cameraManager = (CameraManager) this.activity.getSystemService("camera");
        try {
            String[] cameraIdList = cameraManager.getCameraIdList();
            if (cameraIdList.length == 0 || (streamConfigurationMap = (StreamConfigurationMap) cameraManager.getCameraCharacteristics(cameraIdList[0]).get(CameraCharacteristics.SCALER_STREAM_CONFIGURATION_MAP)) == null) {
                return;
            }
            for (Size size : streamConfigurationMap.getOutputSizes(SurfaceTexture.class)) {
                if (size.getWidth() < 1920 && size.getHeight() < 1920) {
                }
                this.previewSize = size;
                LoggerUtil.m1181d("camera2", "previewSize: " + this.previewSize.getWidth() + ", " + this.previewSize.getHeight());
            }
            if (this.previewSize == null) {
                this.previewSize = streamConfigurationMap.getOutputSizes(SurfaceTexture.class)[0];
            }
            if (ActivityCompat.checkSelfPermission(this.activity, Permission.CAMERA) != 0) {
                ActivityCompat.requestPermissions(this.activity, new String[]{Permission.CAMERA}, 200);
                LoggerUtil.m1182e("camera2", "error: REQUEST_CAMERA_PERMISSION");
            } else {
                int videoZoom = SpManager.getInstance().getVideoZoom();
                LoggerUtil.m1181d("camera2", "previewSize " + this.previewSize.getWidth() + "," + this.previewSize.getHeight() + " compressRatio " + videoZoom);
                this.surfaceRender = new SurfaceRender(this.glSurfaceView, this.previewSize.getWidth(), this.previewSize.getHeight(), this.beautyItem, SpManager.getInstance().getCameraMirror(), videoZoom, this);
            }
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
    }

    private Bitmap getRectBitmap(Bitmap bitmap, int i, int i2) {
        int height;
        int i3;
        if (i == 0 || i2 == 0) {
            return bitmap;
        }
        float f = i / i2;
        if (bitmap.getWidth() / bitmap.getHeight() > f) {
            height = bitmap.getHeight();
            i3 = (int) (height * f);
        } else {
            int width = bitmap.getWidth();
            height = (int) (width / f);
            i3 = width;
        }
        return Bitmap.createBitmap(bitmap, (bitmap.getWidth() / 2) - (i3 / 2), (bitmap.getHeight() / 2) - (height / 2), i3, height);
    }
}
