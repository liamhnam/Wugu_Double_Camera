package com.faceunity.core.callback;

import android.net.Uri;
import kotlin.Metadata;
import org.eclipse.paho.android.service.MqttServiceConstants;

@Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0010\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\bH&J\u0012\u0010\t\u001a\u00020\u00032\b\u0010\n\u001a\u0004\u0018\u00010\u000bH&¨\u0006\f"}, m1293d2 = {"Lcom/faceunity/core/callback/OnRecordingVideoCallback;", "", "onError", "", MqttServiceConstants.TRACE_ERROR, "", "onProcess", "time", "", "onRecordFinish", "uri", "Landroid/net/Uri;", "fu_core_all_featureRelease"}, m1294k = 1, m1295mv = {1, 1, 16})
public interface OnRecordingVideoCallback {
    void onError(String error);

    void onProcess(long time);

    void onRecordFinish(Uri uri);
}
