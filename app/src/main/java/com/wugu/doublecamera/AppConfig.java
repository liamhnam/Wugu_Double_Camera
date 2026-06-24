package com.wugu.doublecamera;

import android.text.TextUtils;
import com.wugu.doublecamera.database.SpManager;
import com.wugu.doublecamera.utils.AppUtil;
import com.wugu.doublecamera.utils.StringUtil;

public class AppConfig {
    public static final String APK_DIR;
    public static final String AUTO_KEY = "L8A07V(CAMERA&)G3L08T";
    public static final String BASE_FOLDER;
    public static final String BG_FRAME_DIR;
    public static final int CODE_HTTP_OK = 0;
    public static final String CRASH_LOG_DIR;
    public static final String DB_NAME = "wg_double_camera.db";
    public static final String EFFECT_DIR;
    public static final String FFMPEG_DIR;
    public static final String FILTER_DIR;
    public static final String FRAME_DIR;
    public static String HTTP_HOST = null;
    public static final boolean IS_PAYMENT_TEST = false;
    public static final String LOGGER_DIR;
    public static String MACHINE_KEY = null;
    public static final String MAKEUP_DIR;
    public static String MQTT_HOST = null;
    public static String MQTT_KEY = null;
    public static String MQTT_NAME = null;
    public static String MQTT_PWD = null;
    public static final String MUSIC_DIR;
    public static String PHOTO_PRE_URL = null;
    public static final String PRINTER_PARAM_DIR;
    public static final String PWD_SALT = "0bziEOjjldXLDYld4Dck";
    public static final String SP_NAME = "app_share";
    public static final String STICKER_BTN;
    public static final String STICKER_DIR;
    public static final String TEMP_DIR;
    public static final String TEST_PRINT_FILE_PATH;
    public static final String THEME_BG_DIR;
    public static final String THEME_BTN_DIR;
    public static final String UART_CASH = "/dev/ttyXRUSB2";
    public static final String UART_COIN = "/dev/ttyXRUSB3";
    public static final String UART_CREDIT = "/dev/ttyXRUSB1";
    public static final String UART_SCANNER = "/dev/ttyXRUSB0";
    public static final String UI_DIR;

    static {
        String str = AppUtil.getInternalSDCardPath() + "/WgDCamera/";
        BASE_FOLDER = str;
        LOGGER_DIR = str + "logger";
        CRASH_LOG_DIR = str + "crash";
        THEME_BG_DIR = str + "theme_bg";
        THEME_BTN_DIR = str + "theme_btn";
        FRAME_DIR = str + "frames";
        STICKER_DIR = str + "stickers";
        STICKER_BTN = str + "sticker_btn";
        FILTER_DIR = str + "filter";
        MAKEUP_DIR = str + "makeup";
        EFFECT_DIR = str + "effects";
        BG_FRAME_DIR = str + "bg_frames";
        APK_DIR = str + "updates";
        UI_DIR = str + "uis";
        PRINTER_PARAM_DIR = str + "printerParam";
        MUSIC_DIR = str + "music";
        TEMP_DIR = str + "temp";
        FFMPEG_DIR = str + "ffmpeg";
        TEST_PRINT_FILE_PATH = str + "test_print.png";
        MACHINE_KEY = getSnKey();
        MQTT_KEY = getMqttKey();
        MQTT_HOST = getMqttHost();
        HTTP_HOST = getHttpHost();
        MQTT_NAME = getMqttName();
        MQTT_PWD = getMqttPwd();
        PHOTO_PRE_URL = getPhotoPreUrl();
    }

    public static String getMp4Name() {
        if (TextUtils.isEmpty(App.mCPUSerial)) {
            return AppUtil.getSystemTime("yyyyMMdd_HHmmss") + StringUtil.getRandomNumber(4) + ".mp4";
        }
        return App.mCPUSerial + "_" + AppUtil.getSystemTime("yyyyMMdd_HHmmss") + ".mp4";
    }

    public static String getFrameName() {
        if (TextUtils.isEmpty(App.mCPUSerial)) {
            return "frame_" + AppUtil.getSystemTime("yyyyMMdd_HHmmss_") + StringUtil.getRandomNumber(4) + ".jpg";
        }
        return "frame_" + App.mCPUSerial + "_" + AppUtil.getSystemTime("yyyyMMdd_HHmmss") + ".jpg";
    }

    public static String getFrameName(String str) {
        if (TextUtils.isEmpty(App.mCPUSerial)) {
            return "frame_" + AppUtil.getSystemTime("yyyyMMdd_HHmmss_") + StringUtil.getRandomNumber(4) + str + ".jpg";
        }
        return "frame_" + App.mCPUSerial + "_" + AppUtil.getSystemTime("yyyyMMdd_HHmmss") + str + ".jpg";
    }

    public static String getAiFrameName(String str) {
        if (TextUtils.isEmpty(App.mCPUSerial)) {
            return "ai_" + AppUtil.getSystemTime("yyyyMMdd_HHmmss_") + StringUtil.getRandomNumber(4) + str;
        }
        return "ai_" + App.mCPUSerial + "_" + AppUtil.getSystemTime("yyyyMMdd_HHmmss") + str;
    }

    public static String getSubFrameName() {
        if (TextUtils.isEmpty(App.mCPUSerial)) {
            return AppUtil.getSystemTime("yyyyMMdd_HHmmss_") + StringUtil.getRandomNumber(4) + ".jpg";
        }
        return App.mCPUSerial + "_" + AppUtil.getSystemTime("yyyyMMdd_HHmmss") + ".jpg";
    }

    private static String getHttpHost() {
        int testServerSelect = SpManager.getInstance().getTestServerSelect();
        String str = BuildConfig.HTTP_URL;
        if (testServerSelect != 0) {
            if (testServerSelect == 1) {
                str = "http://api.pipiphoto.com/";
            } else if (testServerSelect == 2) {
                str = "http://picfapi.pipiphoto.com/";
            }
        }
        String customHttpHost = SpManager.getInstance().getCustomHttpHost();
        return !TextUtils.isEmpty(customHttpHost) ? customHttpHost : str;
    }

    public static String getMqttName() {
        return SpManager.getInstance().getTestServerSelect() != 1 ? BuildConfig.MqttName : "Admin";
    }

    public static String getMqttPwd() {
        int testServerSelect = SpManager.getInstance().getTestServerSelect();
        return testServerSelect != 1 ? testServerSelect != 2 ? BuildConfig.MqttPwd : "OgIuXsAxtqnxJEX7" : "Admin";
    }

    public static String getSnKey() {
        return SpManager.getInstance().getTestServerSelect() != 1 ? BuildConfig.SNKey : "iRU3GC8hEZK1uTJs3onxU4WYshkkD5V4";
    }

    public static String getMqttKey() {
        return SpManager.getInstance().getTestServerSelect() != 1 ? BuildConfig.MqttKey : "3JVqE4Bfhd9B863O9G6AK64mGbC1irCH";
    }

    public static String getMqttHost() {
        int testServerSelect = SpManager.getInstance().getTestServerSelect();
        return testServerSelect != 1 ? testServerSelect != 2 ? BuildConfig.MQTT_URL : "tcp://47.99.166.5:19960" : "tcp://8.134.128.42:19960";
    }

    public static String getPhotoPreUrl() {
        int testServerSelect = SpManager.getInstance().getTestServerSelect();
        return testServerSelect != 1 ? testServerSelect != 2 ? BuildConfig.PhotoUrl : "https://pic.pipiphoto.com/#/pages/Order/CodeResult/CodeResult?id=" : "http://test.fongtop.com/#/pages/Order/CodeResult/CodeResult?id=";
    }

    public static boolean isBelongHeadSys() {
        return App.mSystemMode == 1 || App.mSystemMode == 4;
    }
}
