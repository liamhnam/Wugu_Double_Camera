package com.wugu.doublecamera.device.camera;

import com.faceunity.core.media.video.VideoRecordHelper;
import com.wugu.doublecamera.device.camera.camera2.Camera2Handler;
import com.wugu.doublecamera.device.camera.camerausb.AUsbCamera;
import com.wugu.doublecamera.utils.LoggerUtil;
import com.wugu.facebeauty.bean.BeautifyItem;

public class CameraHandler {
    private final AUsbCamera cameraCanon;
    private final Camera2Handler cameraSystem;

    public CameraHandler(Camera2Handler camera2Handler, AUsbCamera aUsbCamera) {
        this.cameraSystem = camera2Handler;
        this.cameraCanon = aUsbCamera;
    }

    public void reconnect() {
        AUsbCamera aUsbCamera = this.cameraCanon;
        if (aUsbCamera != null) {
            aUsbCamera.init(true);
        } else {
            this.cameraSystem.reconnect();
        }
    }

    public void setBeautyFace(BeautifyItem beautifyItem) {
        AUsbCamera aUsbCamera = this.cameraCanon;
        if (aUsbCamera != null) {
            aUsbCamera.setBeautyFace(beautifyItem);
        } else {
            this.cameraSystem.setBeautyFace(beautifyItem);
        }
    }

    public void setEffectUrl(int i, String str) {
        AUsbCamera aUsbCamera = this.cameraCanon;
        if (aUsbCamera != null) {
            aUsbCamera.setEffectItem(i, str);
        } else {
            this.cameraSystem.setEffectItem(i, str);
        }
    }

    public void setBitmapRect(int i, int i2) {
        if (i == 0 || i2 == 0) {
            return;
        }
        AUsbCamera aUsbCamera = this.cameraCanon;
        if (aUsbCamera != null) {
            aUsbCamera.setBitmapRect(i, i2);
        } else {
            this.cameraSystem.setBitmapRect(i, i2);
        }
    }

    public void setFrameWH(int i, int i2) {
        AUsbCamera aUsbCamera = this.cameraCanon;
        if (aUsbCamera != null) {
            aUsbCamera.setFrameWH(i, i2);
        }
    }

    public void setZoom(int i) {
        Camera2Handler camera2Handler = this.cameraSystem;
        if (camera2Handler != null) {
            camera2Handler.setZoom(i / 10.0f);
        }
    }

    public void setExposure(int i) {
        Camera2Handler camera2Handler = this.cameraSystem;
        if (camera2Handler != null) {
            camera2Handler.setExposure(i / 100.0f);
        }
    }

    public void startRecord() {
        Camera2Handler camera2Handler = this.cameraSystem;
        if (camera2Handler != null) {
            camera2Handler.startRecord();
        } else {
            this.cameraCanon.startRecord();
        }
    }

    public boolean isSystemCamera() {
        return this.cameraSystem != null;
    }

    public int[] getPreviewSize() {
        if (isSystemCamera()) {
            return new int[]{VideoRecordHelper.MAX_VIDEO_LENGTH, 1080};
        }
        return new int[]{2976, 1984};
    }

    public void capture() {
        LoggerUtil.m1181d("Camera", "capture");
        AUsbCamera aUsbCamera = this.cameraCanon;
        if (aUsbCamera != null) {
            aUsbCamera.capture();
        } else {
            this.cameraSystem.capture();
        }
    }

    public void preview() {
        AUsbCamera aUsbCamera = this.cameraCanon;
        if (aUsbCamera != null) {
            aUsbCamera.preview();
        }
    }

    public void close() {
        AUsbCamera aUsbCamera = this.cameraCanon;
        if (aUsbCamera != null) {
            aUsbCamera.close();
        } else {
            this.cameraSystem.close();
        }
    }

    public void stopCamera() {
        Camera2Handler camera2Handler = this.cameraSystem;
        if (camera2Handler != null) {
            camera2Handler.stopCamera();
        }
    }

    public void reopen() {
        Camera2Handler camera2Handler = this.cameraSystem;
        if (camera2Handler != null) {
            camera2Handler.reopenCamera();
        }
    }
}
