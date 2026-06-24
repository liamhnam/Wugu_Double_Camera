package com.mqtt.util;

import android.content.Context;
import android.os.Build;
import android.provider.Settings;
import android.text.TextUtils;
import com.mqtt.params.ApiConstant;

public class MQTTUtils {
    private static final String DEVICE_DEFAULT_ID = "a5d77e0fab764983";

    public static final String getAPKTopic(Context context) {
        return ApiConstant.TOPIC_APK + getAndroidId(context);
    }

    public static final String getServerTopic(Context context) {
        return ApiConstant.TOPIC_SERVER + getAndroidId(context);
    }

    public static String getAndroidId(Context context) {
        String string = Settings.System.getString(context.getContentResolver(), "android_id");
        return TextUtils.isEmpty(string) ? Build.SERIAL : string;
    }
}
