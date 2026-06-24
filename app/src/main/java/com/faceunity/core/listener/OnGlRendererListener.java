package com.faceunity.core.listener;

import com.arthenica.ffmpegkit.StreamInformation;
import com.faceunity.core.entity.FURenderFrameData;
import com.faceunity.core.entity.FURenderInputData;
import com.faceunity.core.entity.FURenderOutputData;
import kotlin.Metadata;

@Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\u0018\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH&J\u0012\u0010\t\u001a\u00020\u00032\b\u0010\n\u001a\u0004\u0018\u00010\u000bH&J\u0018\u0010\f\u001a\u00020\u00032\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u000eH&J\b\u0010\u0010\u001a\u00020\u0003H&J\b\u0010\u0011\u001a\u00020\u0003H&¨\u0006\u0012"}, m1293d2 = {"Lcom/faceunity/core/listener/OnGlRendererListener;", "", "onDrawFrameAfter", "", "onRenderAfter", "outputData", "Lcom/faceunity/core/entity/FURenderOutputData;", "frameData", "Lcom/faceunity/core/entity/FURenderFrameData;", "onRenderBefore", "inputData", "Lcom/faceunity/core/entity/FURenderInputData;", "onSurfaceChanged", StreamInformation.KEY_WIDTH, "", StreamInformation.KEY_HEIGHT, "onSurfaceCreated", "onSurfaceDestroy", "fu_core_all_featureRelease"}, m1294k = 1, m1295mv = {1, 1, 16})
public interface OnGlRendererListener {
    void onDrawFrameAfter();

    void onRenderAfter(FURenderOutputData outputData, FURenderFrameData frameData);

    void onRenderBefore(FURenderInputData inputData);

    void onSurfaceChanged(int width, int height);

    void onSurfaceCreated();

    void onSurfaceDestroy();
}
