package com.faceunity.core.camera;

import com.arthenica.ffmpegkit.StreamInformation;
import com.faceunity.core.enumeration.CameraFacingEnum;
import com.p020hp.jipp.model.PrinterServiceType;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0012\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B-\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0007\u0012\u0006\u0010\t\u001a\u00020\u0007¢\u0006\u0002\u0010\nJ\t\u0010\u0013\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0007HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0007HÆ\u0003J\t\u0010\u0017\u001a\u00020\u0007HÆ\u0003J;\u0010\u0018\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u00072\b\b\u0002\u0010\t\u001a\u00020\u0007HÆ\u0001J\u0013\u0010\u0019\u001a\u00020\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001c\u001a\u00020\u0007HÖ\u0001J\t\u0010\u001d\u001a\u00020\u001eHÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\t\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0010R\u0011\u0010\b\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0010¨\u0006\u001f"}, m1293d2 = {"Lcom/faceunity/core/camera/FUCameraPreviewData;", "", "buffer", "", "cameraFacing", "Lcom/faceunity/core/enumeration/CameraFacingEnum;", "cameraOrientation", "", StreamInformation.KEY_WIDTH, StreamInformation.KEY_HEIGHT, "([BLcom/faceunity/core/enumeration/CameraFacingEnum;III)V", "getBuffer", "()[B", "getCameraFacing", "()Lcom/faceunity/core/enumeration/CameraFacingEnum;", "getCameraOrientation", "()I", "getHeight", "getWidth", "component1", "component2", "component3", "component4", "component5", PrinterServiceType.copy, "equals", "", "other", "hashCode", "toString", "", "fu_core_all_featureRelease"}, m1294k = 1, m1295mv = {1, 1, 16})
public final class FUCameraPreviewData {
    private final byte[] buffer;
    private final CameraFacingEnum cameraFacing;
    private final int cameraOrientation;
    private final int height;
    private final int width;

    public static FUCameraPreviewData copy$default(FUCameraPreviewData fUCameraPreviewData, byte[] bArr, CameraFacingEnum cameraFacingEnum, int i, int i2, int i3, int i4, Object obj) {
        if ((i4 & 1) != 0) {
            bArr = fUCameraPreviewData.buffer;
        }
        if ((i4 & 2) != 0) {
            cameraFacingEnum = fUCameraPreviewData.cameraFacing;
        }
        CameraFacingEnum cameraFacingEnum2 = cameraFacingEnum;
        if ((i4 & 4) != 0) {
            i = fUCameraPreviewData.cameraOrientation;
        }
        int i5 = i;
        if ((i4 & 8) != 0) {
            i2 = fUCameraPreviewData.width;
        }
        int i6 = i2;
        if ((i4 & 16) != 0) {
            i3 = fUCameraPreviewData.height;
        }
        return fUCameraPreviewData.copy(bArr, cameraFacingEnum2, i5, i6, i3);
    }

    public final byte[] getBuffer() {
        return this.buffer;
    }

    public final CameraFacingEnum getCameraFacing() {
        return this.cameraFacing;
    }

    public final int getCameraOrientation() {
        return this.cameraOrientation;
    }

    public final int getWidth() {
        return this.width;
    }

    public final int getHeight() {
        return this.height;
    }

    public final FUCameraPreviewData copy(byte[] buffer, CameraFacingEnum cameraFacing, int cameraOrientation, int width, int height) {
        Intrinsics.checkParameterIsNotNull(buffer, "buffer");
        Intrinsics.checkParameterIsNotNull(cameraFacing, "cameraFacing");
        return new FUCameraPreviewData(buffer, cameraFacing, cameraOrientation, width, height);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof FUCameraPreviewData)) {
            return false;
        }
        FUCameraPreviewData fUCameraPreviewData = (FUCameraPreviewData) other;
        return Intrinsics.areEqual(this.buffer, fUCameraPreviewData.buffer) && Intrinsics.areEqual(this.cameraFacing, fUCameraPreviewData.cameraFacing) && this.cameraOrientation == fUCameraPreviewData.cameraOrientation && this.width == fUCameraPreviewData.width && this.height == fUCameraPreviewData.height;
    }

    public int hashCode() {
        byte[] bArr = this.buffer;
        int iHashCode = (bArr != null ? Arrays.hashCode(bArr) : 0) * 31;
        CameraFacingEnum cameraFacingEnum = this.cameraFacing;
        return ((((((iHashCode + (cameraFacingEnum != null ? cameraFacingEnum.hashCode() : 0)) * 31) + Integer.hashCode(this.cameraOrientation)) * 31) + Integer.hashCode(this.width)) * 31) + Integer.hashCode(this.height);
    }

    public String toString() {
        return "FUCameraPreviewData(buffer=" + Arrays.toString(this.buffer) + ", cameraFacing=" + this.cameraFacing + ", cameraOrientation=" + this.cameraOrientation + ", width=" + this.width + ", height=" + this.height + ")";
    }

    public FUCameraPreviewData(byte[] buffer, CameraFacingEnum cameraFacing, int i, int i2, int i3) {
        Intrinsics.checkParameterIsNotNull(buffer, "buffer");
        Intrinsics.checkParameterIsNotNull(cameraFacing, "cameraFacing");
        this.buffer = buffer;
        this.cameraFacing = cameraFacing;
        this.cameraOrientation = i;
        this.width = i2;
        this.height = i3;
    }

    public final byte[] getBuffer() {
        return this.buffer;
    }

    public final CameraFacingEnum getCameraFacing() {
        return this.cameraFacing;
    }

    public final int getCameraOrientation() {
        return this.cameraOrientation;
    }

    public final int getHeight() {
        return this.height;
    }

    public final int getWidth() {
        return this.width;
    }
}
