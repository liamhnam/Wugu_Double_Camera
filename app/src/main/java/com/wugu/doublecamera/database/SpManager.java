package com.wugu.doublecamera.database;

import android.content.SharedPreferences;
import android.text.TextUtils;
import com.wugu.doublecamera.App;
import com.wugu.doublecamera.AppConfig;
import com.wugu.doublecamera.enums.MqttCmdEnum;

public class SpManager {
    public static final String SOUND_BGM = "background_music";
    private final String ADMIN_PWD;
    private final String ADMIN_TIME;
    private final String AI_ADD_PRICE;
    private final String AI_PRICE;
    private final String AI_TYPE_AND_TOPIC;
    private final String APP_ID;
    private final String APP_UPLOAD_KEY;
    private final String BURST_SELECT_TIME;
    private final String CAMERA_EXPOSURE;
    private final String CAMERA_MIRROR;
    private final String CAMERA_ZOOM;
    private final String CASH_BASE_VALUE;
    private final String CHOOSE_FRAME_TIME;
    private final String COIN_BASE_VALUE;
    private final String CPU_SERIAL;
    private final String CURRENT_ORDER_FRAME;
    private final String CUSTOM_HTTP_HOST;
    private final String CUSTOM_PE_DATA_FORMAT;
    private final String DEFAULT_BEAUTY_LEVEL;
    private final String DNP_DEFAULT_COLOR_PARAMS;
    private final String ERROR_REBOOT_TIME;
    private final String IGNORE_PRINTER_WARNING;
    private final String IS_LOCK_SCREEN;
    private final String IS_PE_SHOW_DATE;
    private final String LOCK_PWD;
    private final String MACHINE_NAME;
    private final String MACHINE_NUM;
    private final String MACHINE_PHONE;
    private final String MODEL_SELECT_TIME;
    private final String MQTT_LAST_REBOOT_TIME;
    private final String PAYMENT_METHOD;
    private final String PAYMENT_TIME;
    private final String PHOTO_CONFIRM_TIME;
    private final String PHOTO_PREVIEW_TIME;
    private final String PHOTO_READY_TIME;
    private final String PRICE_TEXT_COLOR;
    private final String PRINTER_MODEL;
    private final String PRINT_MAX_COUNT;
    private final String PRINT_START_TIME;
    private final String REMAIN_PRINTER_SHEET;
    private final String REMAIN_PRINT_COUNT;
    private final String REPLACE_BG_ADD_PRICE;
    private final String REPLACE_BG_PRICE;
    private final String REPLACE_BG_TIME;
    private final String RETAKE_COUNT;
    private final String SYSTEM_MODE;
    private final String TEST_SERVER_SELECT;
    private final String UPLOAD_PRINT_PRICE;
    private final String UPLOAD_PRINT_PRICE_EXT;
    private final String UPLOAD_PRINT_TIME;
    private final String VIDEO_ZOOM;
    private final String WECHAT_NUM;
    private final SharedPreferences mSP;

    private SpManager() {
        this.MACHINE_NUM = "machine_num";
        this.MACHINE_PHONE = "machine_phone";
        this.WECHAT_NUM = "wechat_num";
        this.MACHINE_NAME = "machine_name";
        this.ADMIN_PWD = "admin_pwd";
        this.LOCK_PWD = "lock_pwd";
        this.CPU_SERIAL = "cpu_serial";
        this.APP_ID = "app_id";
        this.APP_UPLOAD_KEY = "upload_key";
        this.IS_LOCK_SCREEN = "lock_screen";
        this.PRINTER_MODEL = "printer_mode";
        this.REMAIN_PRINT_COUNT = "remain_print_count";
        this.REMAIN_PRINTER_SHEET = "remain_printer_sheet";
        this.PAYMENT_METHOD = "payment_method";
        this.CASH_BASE_VALUE = "cash_base_value";
        this.COIN_BASE_VALUE = "coin_base_value";
        this.CURRENT_ORDER_FRAME = "current_order_frame";
        this.MODEL_SELECT_TIME = "model_select_time";
        this.CHOOSE_FRAME_TIME = "choose_frame_time";
        this.UPLOAD_PRINT_TIME = "upload_print_time";
        this.PAYMENT_TIME = "payment_time";
        this.PHOTO_PREVIEW_TIME = "photo_preview_time";
        this.PHOTO_READY_TIME = "photo_ready_time";
        this.PHOTO_CONFIRM_TIME = "photo_confirm_time";
        this.BURST_SELECT_TIME = "burst_select_time";
        this.PRINT_START_TIME = "print_start_time";
        this.REPLACE_BG_TIME = "replace_bg_time";
        this.ADMIN_TIME = "admin_time";
        this.SYSTEM_MODE = "system_mode";
        this.CAMERA_MIRROR = "camera_mirror";
        this.RETAKE_COUNT = "retake_count";
        this.CAMERA_EXPOSURE = "camera_exposure";
        this.CAMERA_ZOOM = "camera_zoom";
        this.VIDEO_ZOOM = "video_zoom";
        this.UPLOAD_PRINT_PRICE = "upload_print_price";
        this.UPLOAD_PRINT_PRICE_EXT = "upload_print_price_ext";
        this.REPLACE_BG_PRICE = "replace_bg_price";
        this.REPLACE_BG_ADD_PRICE = "replace_bg_add_price";
        this.AI_PRICE = "ai_price";
        this.AI_ADD_PRICE = "ai_add_price";
        this.PRINT_MAX_COUNT = "print_max_count";
        this.PRICE_TEXT_COLOR = "price_text_color";
        this.TEST_SERVER_SELECT = "test_server_select";
        this.DNP_DEFAULT_COLOR_PARAMS = "dnp_default_color_params";
        this.DEFAULT_BEAUTY_LEVEL = "default_beauty_level";
        this.AI_TYPE_AND_TOPIC = "ai_type_and_topic";
        this.MQTT_LAST_REBOOT_TIME = "mqtt_last_reboot_time";
        this.IS_PE_SHOW_DATE = "is_pe_show_date";
        this.CUSTOM_HTTP_HOST = "custom_http_host";
        this.CUSTOM_PE_DATA_FORMAT = "custom_pe_data_format";
        this.IGNORE_PRINTER_WARNING = MqttCmdEnum.S2C_IGNORE_PRINTER_WARNING;
        this.ERROR_REBOOT_TIME = "error_reboot_time";
        this.mSP = App.getInstance().getSharedPreferences(AppConfig.SP_NAME, 0);
    }

    private static class InstanceHolder {
        private static final SpManager instance = new SpManager();

        private InstanceHolder() {
        }
    }

    public static SpManager getInstance() {
        return InstanceHolder.instance;
    }

    public void setMachineNum(String str) {
        putString("machine_num", str);
    }

    public String getMachineNum() {
        return getString("machine_num", null);
    }

    public void setMachinePhone(String str) {
        putString("machine_phone", str);
    }

    public String getMachinePhone() {
        return getString("machine_phone", "123");
    }

    public void setWechatNum(String str) {
        putString("wechat_num", str);
    }

    public String getWechatNum() {
        return getString("wechat_num", null);
    }

    public void setMachineName(String str) {
        putString("machine_name", str);
    }

    public String getMachineName() {
        return getString("machine_name", "大头贴");
    }

    public void setAdminPwd(String str) {
        putString("admin_pwd", str);
    }

    public String getAdminPwd() {
        return getString("admin_pwd", "");
    }

    public void setLockPwd(String str) {
        putString("lock_pwd", str);
    }

    public String getLockPwd() {
        return getString("lock_pwd", "980437");
    }

    public void setCpuSerial(String str) {
        putString("cpu_serial", str);
    }

    public String getCpuSerial() {
        return getString("cpu_serial", null);
    }

    public void setIsLockScreen(boolean z) {
        putBoolean("lock_screen", z);
    }

    public boolean getIsLockScreen() {
        return getBoolean("lock_screen", true);
    }

    public void setAppId(String str) {
        putString("app_id", str);
    }

    public String getAppId() {
        return getString("app_id", "");
    }

    public void setAppUploadKey(String str) {
        putString("upload_key", str);
    }

    public String getAppUploadKey() {
        return getString("upload_key", "");
    }

    public void setPrinterModel(int i) {
        putInt("printer_mode", i);
    }

    public int getPrinterModel() {
        return getInt("printer_mode", 1);
    }

    public void setCoinBaseValue(float f) {
        putFloat("coin_base_value", f);
    }

    public float getCoinBaseValue() {
        return getFloat("coin_base_value", 1.0f);
    }

    public void setPaymentMethod(String str) {
        putString("payment_method", str);
    }

    public String getPaymentMethod() {
        return getString("payment_method", "3-4-7");
    }

    public void setCashBaseValue(String str) {
        putString("cash_base_value", str);
    }

    public String getCashBaseValue() {
        return getString("cash_base_value", "100-200-500-1000-2000");
    }

    public void setRemainPrintCount(int i) {
        putInt("remain_print_count", i);
    }

    public int getRemainPrintCount() {
        return getInt("remain_print_count", 700);
    }

    public void setRemainPrinterSheet(int i) {
        putInt("remain_printer_sheet", i);
    }

    public int getRemainPrinterSheet() {
        return getInt("remain_printer_sheet", 50);
    }

    public void setCurrentOrderFrame(String str, String str2, String str3) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2) || TextUtils.isEmpty(str3)) {
            putString("current_order_frame", null);
        } else {
            putString("current_order_frame", str + "," + str2 + "," + str3);
        }
    }

    public String getCurrentOrderFrame() {
        return getString("current_order_frame", null);
    }

    public void setModelSelectTime(int i) {
        putInt("model_select_time", i);
    }

    public int getModelSelectTime() {
        return getInt("model_select_time", 60);
    }

    public void setChooseFrameTime(int i) {
        putInt("choose_frame_time", i);
    }

    public int getChooseFrameTime() {
        return getInt("choose_frame_time", 60);
    }

    public void setUploadPrintTime(int i) {
        putInt("upload_print_time", i);
    }

    public int getUploadPrintTime() {
        return getInt("upload_print_time", 60);
    }

    public void setPaymentTime(int i) {
        putInt("payment_time", i);
    }

    public int getPaymentTime() {
        return getInt("payment_time", 60);
    }

    public void setPhotoPreviewTime(int i) {
        putInt("photo_preview_time", i);
    }

    public int getPhotoPreviewTime() {
        return getInt("photo_preview_time", 60);
    }

    public void setPhotoReadyTime(int i) {
        putInt("photo_ready_time", i);
    }

    public int getPhotoReadyTime() {
        return getInt("photo_ready_time", 8);
    }

    public void setPhotoConfirmTime(int i) {
        putInt("photo_confirm_time", i);
    }

    public int getPhotoConfirmTime() {
        return getInt("photo_confirm_time", 20);
    }

    public void setPrintStartTime(int i) {
        putInt("print_start_time", i);
    }

    public int getPrintStartTime() {
        return getInt("print_start_time", 120);
    }

    public void setReplaceBgTime(int i) {
        putInt("replace_bg_time", i);
    }

    public int getReplaceBgTime() {
        return getInt("replace_bg_time", 90);
    }

    public void setBurstSelectTime(int i) {
        putInt("burst_select_time", i);
    }

    public int getBurstSelectTime() {
        return getInt("burst_select_time", 90);
    }

    public void setAdminTime(int i) {
        putInt("admin_time", i);
    }

    public int getAdminTime() {
        return getInt("admin_time", 60);
    }

    public void setSystemMode(int i) {
        putInt("system_mode", i);
    }

    public int getSystemMode() {
        return getInt("system_mode", 1);
    }

    public void setBgmPaths(String str) {
        putString(SOUND_BGM, str);
    }

    public String getBgmPaths() {
        return getString(SOUND_BGM, null);
    }

    public void setCameraMirror(boolean z) {
        putBoolean("camera_mirror", z);
    }

    public boolean getCameraMirror() {
        return getBoolean("camera_mirror", true);
    }

    public void setRetakeCount(int i) {
        putInt("retake_count", i);
    }

    public int getRetakeCount() {
        return getInt("retake_count", 2);
    }

    public void setCameraExposure(int i) {
        putInt("camera_exposure", i);
    }

    public int getCameraExposure() {
        return getInt("camera_exposure", 50);
    }

    public void setCameraZoom(int i) {
        putInt("camera_zoom", i);
    }

    public int getCameraZoom() {
        return getInt("camera_zoom", 10);
    }

    public void setVideoZoom(int i) {
        putInt("video_zoom", i);
    }

    public int getVideoZoom() {
        return getInt("video_zoom", 4);
    }

    public void setUploadPrintPrice(int i) {
        putInt("upload_print_price", i);
    }

    public int getUploadPrintPrice() {
        return getInt("upload_print_price", 2000);
    }

    public void setUploadPrintAddPrice(int i) {
        putInt("upload_print_price_ext", i);
    }

    public int getUploadPrintAddPrice() {
        return getInt("upload_print_price_ext", 500);
    }

    public void setReplaceBgPrice(int i) {
        putInt("replace_bg_price", i);
    }

    public int getReplaceBgPrice() {
        return getInt("replace_bg_price", 500);
    }

    public void setReplaceBgAddPrice(int i) {
        putInt("replace_bg_add_price", i);
    }

    public int getReplaceBgAddPrice() {
        return getInt("replace_bg_add_price", 100);
    }

    public void setAiPrice(int i) {
        putInt("ai_price", i);
    }

    public int getAiPrice() {
        return getInt("ai_price", 1000);
    }

    public void setAiAddPrice(int i) {
        putInt("ai_add_price", i);
    }

    public int getAiAddPrice() {
        return getInt("ai_add_price", 500);
    }

    public void setPrintMaxCount(int i) {
        if (i < 1) {
            i = 5;
        }
        putInt("print_max_count", i);
    }

    public int getPrintMaxCount() {
        return getInt("print_max_count", 20);
    }

    public void setPriceTextColor(String str) {
        putString("price_text_color", str);
    }

    public String getPriceTextColor() {
        return getString("price_text_color", "#663226");
    }

    public void setTestServerSelect(int i) {
        putInt("test_server_select", i);
    }

    public int getTestServerSelect() {
        return getInt("test_server_select", 0);
    }

    public void setDnpDefaultColorParams(boolean z) {
        putBoolean("dnp_default_color_params", z);
    }

    public boolean getDnpDefaultColorParams() {
        return getBoolean("dnp_default_color_params", true);
    }

    public void setDefaultBeautyLevel(int i) {
        putInt("default_beauty_level", i);
    }

    public int getDefaultBeautyLevel(int i) {
        return getInt("default_beauty_level", i);
    }

    public void setAiTypeAndTopic(String str, String str2) {
        putString("ai_type_and_topic", str + "---" + str2);
    }

    public String getAiTypeAndTopic() {
        return getString("ai_type_and_topic", null);
    }

    public void setMqttLastRebootTime(long j) {
        putLong("mqtt_last_reboot_time", j);
    }

    public long getMqttLastRebootTime() {
        return getLong("mqtt_last_reboot_time", 0L);
    }

    public void setIsPeShowDate(boolean z) {
        putBoolean("is_pe_show_date", z);
    }

    public boolean getIsPeShowDate() {
        return getBoolean("is_pe_show_date", true);
    }

    public void setCustomHttpHost(String str) {
        putString("custom_http_host", str);
    }

    public String getCustomHttpHost() {
        return getString("custom_http_host", null);
    }

    public void setCustomPeDataFormat(String str) {
        putString("custom_pe_data_format", str);
    }

    public String getCustomPeDataFormat() {
        return getString("custom_pe_data_format", null);
    }

    public void setIgnorePrinterWarning(boolean z) {
        putBoolean(MqttCmdEnum.S2C_IGNORE_PRINTER_WARNING, z);
    }

    public boolean getIgnorePrinterWarning() {
        return getBoolean(MqttCmdEnum.S2C_IGNORE_PRINTER_WARNING, false);
    }

    public long getErrorRebootTime() {
        return getLong("error_reboot_time", 0L);
    }

    public void setErrorRebootTime(long j) {
        putLong("error_reboot_time", j);
    }

    private void putInt(String str, int i) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        this.mSP.edit().putInt(str, i).apply();
    }

    private int getInt(String str, int i) {
        return this.mSP.getInt(str, i);
    }

    private void putLong(String str, long j) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        this.mSP.edit().putLong(str, j).apply();
    }

    private long getLong(String str, long j) {
        return this.mSP.getLong(str, j);
    }

    private void putFloat(String str, float f) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        this.mSP.edit().putFloat(str, f).apply();
    }

    private float getFloat(String str, float f) {
        return this.mSP.getFloat(str, f);
    }

    public void putString(String str, String str2) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        this.mSP.edit().putString(str, str2).apply();
    }

    private String getString(String str, String str2) {
        return this.mSP.getString(str, str2);
    }

    private void putBoolean(String str, boolean z) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        this.mSP.edit().putBoolean(str, z).apply();
    }

    private boolean getBoolean(String str, boolean z) {
        return this.mSP.getBoolean(str, z);
    }
}
