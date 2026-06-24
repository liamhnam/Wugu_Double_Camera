package com.faceunity.core.callback;

import androidx.core.app.NotificationCompat;
import kotlin.Metadata;

@Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\bf\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H&J\u0018\u0010\b\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\u00052\u0006\u0010\n\u001a\u00020\u0007H&¨\u0006\u000b"}, m1293d2 = {"Lcom/faceunity/core/callback/OperateCallback;", "", "onFail", "", "errCode", "", "errMsg", "", "onSuccess", "code", NotificationCompat.CATEGORY_MESSAGE, "fu_core_all_featureRelease"}, m1294k = 1, m1295mv = {1, 1, 16})
public interface OperateCallback {
    void onFail(int errCode, String errMsg);

    void onSuccess(int code, String msg);
}
