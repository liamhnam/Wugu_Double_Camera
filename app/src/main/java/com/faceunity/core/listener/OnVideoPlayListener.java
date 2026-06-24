package com.faceunity.core.listener;

import kotlin.Metadata;
import org.eclipse.paho.android.service.MqttServiceConstants;

@Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\b\u0010\u0006\u001a\u00020\u0003H&¨\u0006\u0007"}, m1293d2 = {"Lcom/faceunity/core/listener/OnVideoPlayListener;", "", "onError", "", MqttServiceConstants.TRACE_ERROR, "", "onPlayFinish", "fu_core_all_featureRelease"}, m1294k = 1, m1295mv = {1, 1, 16})
public interface OnVideoPlayListener {
    void onError(String error);

    void onPlayFinish();
}
