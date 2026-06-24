package com.faceunity.core.callback;

import androidx.core.app.NotificationCompat;
import com.arthenica.ffmpegkit.StreamInformation;
import com.faceunity.core.enumeration.FUAIProcessorEnum;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J(\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\bH\u0016J\u0018\u0010\n\u001a\u00020\u00032\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u0005H\u0016¨\u0006\u000e"}, m1293d2 = {"Lcom/faceunity/core/callback/OnRenderKitCallback;", "", "onBenchmarkChanged", "", StreamInformation.KEY_WIDTH, "", StreamInformation.KEY_HEIGHT, "fps", "", "renderTime", "onTrackStatusChanged", "process", "Lcom/faceunity/core/enumeration/FUAIProcessorEnum;", NotificationCompat.CATEGORY_STATUS, "fu_core_all_featureRelease"}, m1294k = 1, m1295mv = {1, 1, 16})
public interface OnRenderKitCallback {

    @Metadata(m1291bv = {1, 0, 3}, m1294k = 3, m1295mv = {1, 1, 16})
    public static final class DefaultImpls {
        public static void onBenchmarkChanged(OnRenderKitCallback onRenderKitCallback, int i, int i2, double d, double d2) {
        }

        public static void onTrackStatusChanged(OnRenderKitCallback onRenderKitCallback, FUAIProcessorEnum process, int i) {
            Intrinsics.checkParameterIsNotNull(process, "process");
        }
    }

    void onBenchmarkChanged(int width, int height, double fps, double renderTime);

    void onTrackStatusChanged(FUAIProcessorEnum process, int status);
}
