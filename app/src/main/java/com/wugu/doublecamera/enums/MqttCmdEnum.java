package com.wugu.doublecamera.enums;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class MqttCmdEnum {
    public static final String C2S_DEVICE_CONNECT = "r1";
    public static final String C2S_DEVICE_DISCONNECT = "e1";
    public static final String C2S_HEART = "a1";
    public static final String C2S_PRINTER_ERROR = "printer";
    public static final String C2S_SW_STARTUP = "startup";
    public static final String S2C_APK_UPDATE = "b1";
    public static final String S2C_CANCEL_SHUTDOWN = "s10";
    public static final String S2C_CHANGE_EASY_BEAUTY_LEVEL = "change_easy_beauty_level";
    public static final String S2C_CLEAR_GLIDE_CACHE = "clear_glide_cache";
    public static final String S2C_CLEAR_PIC_VIDEO = "clear_all_video_pic";
    public static final String S2C_CLEAR_TEMP = "cc";
    public static final String S2C_CLEAR_UI_POS = "s11";
    public static final String S2C_CLOSE_APP = "s5";
    public static final String S2C_CUSTOM_HTTP = "custom_http";
    public static final String S2C_CUSTOM_PE_DATA_FORMAT = "custom_easy_data_format";
    public static final String S2C_DNP_DEFAULT_COLOR = "dnp_default_color";
    public static final String S2C_EXIT_APP = "b7";
    public static final String S2C_HEART_REPLY = "a2";
    public static final String S2C_IGNORE_PRINTER_WARNING = "ignore_printer_warning";
    public static final String S2C_INCREASE_VOLUME = "b9";
    public static final String S2C_LOCK_OFF = "lockOff";
    public static final String S2C_LOCK_ON = "lockOn";
    public static final String S2C_LOWER_VOLUME = "b8";
    public static final String S2C_MUTE = "b10";
    public static final String S2C_PARAM_UPDATE = "b13";
    public static final String S2C_PAY_SUCCESS = "b3";
    public static final String S2C_PAY_SUCCESS2 = "b4|";
    public static final String S2C_POWER_OFF = "b6";
    public static final String S2C_PRINT_ORDER_PHOTO = "pa1;";
    public static final String S2C_REBOOT = "b5";
    public static final String S2C_REQUEST_LOG_FILE = "b11";
    public static final String S2C_RESET_ADMIN_PWD = "rea3";
    public static final String S2C_SET_LOCK_PWD = "rea_lock";
    public static final String S2C_SET_PARAM = "s4";
    public static final String S2C_SET_SHUTDOWN_TIME = "s9";
    public static final String S2C_START_BUSINESS = "c2";
    public static final String S2C_STOP_BUSINESS = "c1";
    public static final String S2C_SYNC_FRAMES = "s12";
    public static final String S2C_SYNC_VOICE = "s13";
    public static final String S2C_TEST_INPUT_COIN = "hhy_input_test";
    public static final String S2C_TEST_PRINT = "c3";
    public static final String S2C_UPDATE_APK = "up_new_apk";
    public static final String S2C_UPLOAD_MEDIA = "upload_media_res";
    public static final String S2C_UP_PRINT_ERROR = "up2,";
    public static final String S2C_UP_PRINT_START = "up1,";
    public static final String S2C_VERSION = "version";
    public static final String S2C_VIDEO_ZOOM = "custom_video_zoom";

    @Retention(RetentionPolicy.SOURCE)
    public @interface MqttCmd {
    }
}
