package com.faceunity.core.entity;

import com.faceunity.core.enumeration.CameraFacingEnum;
import com.faceunity.core.enumeration.CameraTypeEnum;
import com.p020hp.jipp.model.Status;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0007\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u000e\u0010\r\u001a\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u0004J\u000e\u0010\u000e\u001a\u00020\u00002\u0006\u0010\u0005\u001a\u00020\u0006J\u000e\u0010\u000f\u001a\u00020\u00002\u0006\u0010\u0007\u001a\u00020\u0004J\u000e\u0010\u0010\u001a\u00020\u00002\u0006\u0010\b\u001a\u00020\tJ\u000e\u0010\u0011\u001a\u00020\u00002\u0006\u0010\n\u001a\u00020\u0004J\u000e\u0010\u0012\u001a\u00020\u00002\u0006\u0010\u000b\u001a\u00020\fR\u0012\u0010\u0003\u001a\u00020\u00048\u0006@\u0006X\u0087\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u0005\u001a\u00020\u00068\u0006@\u0006X\u0087\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u0007\u001a\u00020\u00048\u0006@\u0006X\u0087\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\b\u001a\u00020\t8\u0006@\u0006X\u0087\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\n\u001a\u00020\u00048\u0006@\u0006X\u0087\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u000b\u001a\u00020\f8\u0006@\u0006X\u0087\u000e¢\u0006\u0002\n\u0000¨\u0006\u0013"}, m1293d2 = {"Lcom/faceunity/core/entity/FUCameraConfig;", "", "()V", "cameraFPS", "", "cameraFacing", "Lcom/faceunity/core/enumeration/CameraFacingEnum;", "cameraHeight", "cameraType", "Lcom/faceunity/core/enumeration/CameraTypeEnum;", "cameraWidth", "isHighestRate", "", "setCameraFPS", "setCameraFacing", "setCameraHeight", "setCameraType", "setCameraWidth", "setHighestRate", "fu_core_all_featureRelease"}, m1294k = 1, m1295mv = {1, 1, 16})
public final class FUCameraConfig {
    public boolean isHighestRate;
    public CameraTypeEnum cameraType = CameraTypeEnum.CAMERA1;
    public CameraFacingEnum cameraFacing = CameraFacingEnum.CAMERA_FRONT;
    public int cameraFPS = -1;
    public int cameraWidth = Status.Code.serverErrorInternalError;
    public int cameraHeight = 720;

    public final FUCameraConfig setCameraType(CameraTypeEnum cameraType) {
        Intrinsics.checkParameterIsNotNull(cameraType, "cameraType");
        this.cameraType = cameraType;
        return this;
    }

    public final FUCameraConfig setCameraFacing(CameraFacingEnum cameraFacing) {
        Intrinsics.checkParameterIsNotNull(cameraFacing, "cameraFacing");
        this.cameraFacing = cameraFacing;
        return this;
    }

    public final FUCameraConfig setCameraFPS(int cameraFPS) {
        this.cameraFPS = cameraFPS;
        return this;
    }

    public final FUCameraConfig setCameraHeight(int cameraHeight) {
        this.cameraHeight = cameraHeight;
        return this;
    }

    public final FUCameraConfig setCameraWidth(int cameraWidth) {
        this.cameraWidth = cameraWidth;
        return this;
    }

    public final FUCameraConfig setHighestRate(boolean isHighestRate) {
        this.isHighestRate = isHighestRate;
        return this;
    }
}
