package com.faceunity.core.listener;

import com.faceunity.core.camera.FUCameraPreviewData;
import kotlin.Metadata;

@Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&¨\u0006\u0006"}, m1293d2 = {"Lcom/faceunity/core/listener/OnFUCameraListener;", "", "onPreviewFrame", "", "previewData", "Lcom/faceunity/core/camera/FUCameraPreviewData;", "fu_core_all_featureRelease"}, m1294k = 1, m1295mv = {1, 1, 16})
public interface OnFUCameraListener {
    void onPreviewFrame(FUCameraPreviewData previewData);
}
