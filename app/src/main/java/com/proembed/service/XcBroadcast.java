package com.proembed.service;

import android.content.Context;
import android.content.Intent;
import androidx.constraintlayout.motion.widget.Key;
import androidx.core.app.NotificationCompat;
import com.p020hp.jipp.cups.Cups;
import com.p020hp.jipp.model.SystemTimeSource;
import com.p020hp.jipp.model.TrimmingType;
import com.tom_roush.fontbox.ttf.NamingTable;

public class XcBroadcast {
    public static final String ACTION_BACKLIGHT_SET = "android.intent.myservice.set_backlight";
    public static final String ACTION_BLUETOOTH_EN = "android.intent.myservice.bluetooth_en";
    public static final String ACTION_BLUETOOTH_NAME_SET = "android.intent.myservice.set_bluetooth_name";
    public static final String ACTION_CAMERA_IMAGE = "android.intent.myservice.camera_image";
    public static final String ACTION_ETHERNET_SET = "android.intent.myservice.set_ethernet";
    public static final String ACTION_GSM_LISTEN = "android.intent.myservice.gsm_listen";
    public static final String ACTION_HIDE_NAV = "android.intent.myservice.hide_navigagation_bar";
    public static final String ACTION_NETWORK_MODE = "android.intent.myservice.network_mode";
    public static final String ACTION_NETWORK_SCORE = "android.intent.myservice.network_score";
    public static final String ACTION_POWEROFF = "android.intent.myservice.poweroff";
    public static final String ACTION_PRINT_FILE = "android.intent.myservice.print_file";
    public static final String ACTION_PUSHNETLOG = "android.intent.myservice.push_net_log";
    public static final String ACTION_REBOOT = "android.intent.myservice.reboot";
    public static final String ACTION_RUN_OTA = "android.intent.myservice.run_ota";
    public static final String ACTION_RUN_SYSTEM_CMD = "android.intent.myservice.run_shellcmd";
    public static final String ACTION_SERIAL_SET = "android.intent.myservice.set_serial";
    public static final String ACTION_SET_APP_BOOT = "android.intent.myservice.app_boot";
    public static final String ACTION_SET_APP_LISTEN = "android.intent.myservice.app_listen";
    public static final String ACTION_SET_APP_XML = "android.intent.myservice.app_xml";
    public static final String ACTION_SET_AUDIO_IN = "android.intent.myservice.set_audio_in_type";
    public static final String ACTION_SET_AUDIO_OUT = "android.intent.myservice.set_audio_out_type";
    public static final String ACTION_SET_AUDIO_VOLUME = "android.intent.myservice.set_audio_vol";
    public static final String ACTION_SET_DEBUG_UART = "android.intent.myservice.set_debug_uart";
    public static final String ACTION_SET_DISPLAY_HWROTATION = "android.intent.myservice.hwrotation";
    public static final String ACTION_SET_ETH_IPROUTE = "android.intent.myservice.eth_iproute";
    public static final String ACTION_SET_GSM_TO_ETH = "android.intent.myservice.set_gsm_to_eth";
    public static final String ACTION_SET_KEY = "android.intent.myservice.key";
    public static final String ACTION_SET_LVDS_ODD = "android.intent.myservice.lvds_odd";
    public static final String ACTION_SET_MAINSCREEN_TOUCH = "android.intent.myservice.set_mainscreen_touch";
    public static final String ACTION_SET_SAVE_LOG = "android.intent.myservice.set_savelog";
    public static final String ACTION_SET_SLIDE_NAV = "android.intent.myservice.set_slide_naviagation_bar";
    public static final String ACTION_SET_TIMER_POWER = "android.intent.myservice.set_timer_power";
    public static final String ACTION_SET_TIMER_POWER_CLEAR = "android.intent.myservice.clear_timer_power";
    public static final String ACTION_SET_TIMER_POWER_REBOOT = "android.intent.myservice.set_timer_reboot";
    public static final String ACTION_SET_TOUCH_STATUS = "android.intent.myservice.set_touch_status";
    public static final String ACTION_SHOW_NAV = "android.intent.myservice.show_naviagation_bar";
    public static final String ACTION_SUB_ETHERNET_SET = "android.intent.myservice.set_sub_ethernet";
    public static final String ACTION_SWITCH = "android.intent.myservice.swtich";
    public static final String ACTION_SWITCH_USB_OTG_MODE = "android.intent.myservice.usb_otg_mode";
    public static final String ACTION_SYSTEMTIME_AUTO_SET = "android.intent.myservice.auto_system_time";
    public static final String ACTION_SYSTEMTIME_SET = "android.intent.myservice.system_time";
    public static final String ACTION_UPDATE_SYSTEM = "android.intent.myservice.update_system";
    public static final String ACTION_WIFI_AP_SET = "android.intent.myservice.set_wifi_ap";
    public static final String ACTION_WIFI_STA_SET = "android.intent.myservice.set_wifi_sta";
    public static final String ACTION_XCDZ_RETURN = "android.intent.myservice.xcdz_return";
    public static final String MANAGE_INSTALL_SILENT = "android.intent.myservice.manage_install_silent";
    public static final String MANAGE_UN_INSTALL_SILENT = "android.intent.myservice.manage_uninstall_silent";
    public static final String RECEIVER_PACKAGE_NAME = "com.android.rk.service";
    private Context mContext;

    public XcBroadcast(Context context) {
        this.mContext = context;
    }

    private void sendBroadcast(Intent intent) {
        intent.setPackage(RECEIVER_PACKAGE_NAME);
        this.mContext.sendBroadcast(intent);
    }

    public void setStaticEthIPAddress(String str, String str2, String str3, String str4, String str5) {
        Intent intent = new Intent(ACTION_ETHERNET_SET);
        intent.putExtra("ipaddr", str);
        intent.putExtra("mask", str3);
        intent.putExtra(Constant.ETH_SET_GATEWAY, str2);
        intent.putExtra("dns", str4);
        intent.putExtra(Constant.ETH_DNS1, str5);
        intent.putExtra(SystemTimeSource.dhcp, false);
        sendBroadcast(intent);
    }

    public void setDHCPEthIPAddress() {
        Intent intent = new Intent(ACTION_ETHERNET_SET);
        intent.putExtra(SystemTimeSource.dhcp, true);
        sendBroadcast(intent);
    }

    public void ethEnabled(boolean z) {
        Intent intent = new Intent(ACTION_ETHERNET_SET);
        intent.putExtra("enable", z);
        sendBroadcast(intent);
    }

    public void setEthMac(String str) {
        Intent intent = new Intent(ACTION_ETHERNET_SET);
        intent.putExtra("mac", str);
        sendBroadcast(intent);
    }

    public void rebootSystem() {
        sendBroadcast(new Intent(ACTION_REBOOT));
    }

    public void silentInstallApk(String str, String str2, boolean z) {
        Intent intent = new Intent(MANAGE_INSTALL_SILENT);
        intent.putExtra("apkpath", str);
        intent.putExtra("package", str2);
        intent.putExtra("isopen", z);
        sendBroadcast(intent);
    }

    public void silentUnInstallApk(String str) {
        Intent intent = new Intent(MANAGE_UN_INSTALL_SILENT);
        intent.putExtra("package", str);
        sendBroadcast(intent);
    }

    public void hideNavBar(boolean z) {
        sendBroadcast(new Intent(!z ? ACTION_SHOW_NAV : ACTION_HIDE_NAV));
    }

    public void setSystemTimeMillis(long j) {
        Intent intent = new Intent(ACTION_SYSTEMTIME_SET);
        intent.putExtra("time", j);
        sendBroadcast(intent);
    }

    public void shutdownSystem() {
        sendBroadcast(new Intent(ACTION_POWEROFF));
    }

    public void switchAutoTime(boolean z) {
        Intent intent = new Intent(ACTION_SYSTEMTIME_AUTO_SET);
        intent.putExtra("enable", z);
        sendBroadcast(intent);
    }

    public void setTimerPower(int[] iArr, int[] iArr2, int[] iArr3) {
        Intent intent = new Intent(ACTION_SET_TIMER_POWER);
        intent.putExtra("timeon", iArr);
        intent.putExtra("timeoff", iArr2);
        intent.putExtra("wkdays", iArr3);
        intent.putExtra("enable", true);
        sendBroadcast(intent);
    }

    public void setTimerPower(int[] iArr, int[] iArr2, int i) {
        Intent intent = new Intent(ACTION_SET_TIMER_POWER);
        intent.putExtra("timeon", iArr);
        intent.putExtra("timeoff", iArr2);
        intent.putExtra("week", i);
        intent.putExtra("enable", true);
        sendBroadcast(intent);
    }

    public void clearTimerPower() {
        sendBroadcast(new Intent(ACTION_SET_TIMER_POWER_CLEAR));
    }

    public void setSlideNavBar(boolean z) {
        Intent intent = new Intent(ACTION_SET_SLIDE_NAV);
        intent.putExtra("enable", z);
        sendBroadcast(intent);
    }

    public void setAudioOutType(String str) {
        Intent intent = new Intent(ACTION_SET_AUDIO_OUT);
        intent.putExtra("type", str);
        sendBroadcast(intent);
    }

    public void setAudioInType(String str) {
        Intent intent = new Intent(ACTION_SET_AUDIO_IN);
        intent.putExtra("type", str);
        sendBroadcast(intent);
    }

    public void setTouchStatus(String str) {
        Intent intent = new Intent(ACTION_SET_TOUCH_STATUS);
        intent.putExtra(NotificationCompat.CATEGORY_STATUS, str);
        sendBroadcast(intent);
    }

    public void setGsmToEth(boolean z) {
        Intent intent = new Intent(ACTION_SET_GSM_TO_ETH);
        intent.putExtra("enable", z);
        sendBroadcast(intent);
    }

    public void setOtgMode(boolean z) {
        Intent intent = new Intent(ACTION_SWITCH_USB_OTG_MODE);
        intent.putExtra("enable", z);
        sendBroadcast(intent);
    }

    public void setAppListen(String str) {
        Intent intent = new Intent(ACTION_SET_APP_LISTEN);
        if (str == null) {
            intent.putExtra("enable", false);
        } else {
            intent.putExtra("enable", true);
            intent.putExtra("pkgname", str);
        }
        sendBroadcast(intent);
    }

    public void setAppListen(String str, int i) {
        Intent intent = new Intent(ACTION_SET_APP_LISTEN);
        if (str == null) {
            intent.putExtra("enable", false);
        } else {
            intent.putExtra("enable", true);
            intent.putExtra("pkgname", str);
            intent.putExtra("timeout", i);
        }
        sendBroadcast(intent);
    }

    public void setWifiAP_Open(String str, String str2) {
        Intent intent = new Intent(ACTION_WIFI_AP_SET);
        intent.putExtra("enable", true);
        intent.putExtra("ssid", str);
        intent.putExtra(Cups.AuthInfoRequired.password, str2);
        sendBroadcast(intent);
    }

    public void setWifiAP_Close() {
        Intent intent = new Intent(ACTION_WIFI_AP_SET);
        intent.putExtra("enable", false);
        sendBroadcast(intent);
    }

    public void setWifiStaDhcp_Open(String str, String str2) {
        Intent intent = new Intent(ACTION_WIFI_STA_SET);
        intent.putExtra("enable", true);
        intent.putExtra("ssid", str);
        intent.putExtra(Cups.AuthInfoRequired.password, str2);
        sendBroadcast(intent);
    }

    public void setWifiStaStatic_Open(String str, String str2, String str3, String str4, String str5, String str6) {
        Intent intent = new Intent(ACTION_WIFI_STA_SET);
        intent.putExtra("enable", true);
        intent.putExtra("ssid", str);
        intent.putExtra(Cups.AuthInfoRequired.password, str2);
        intent.putExtra("ipaddr", str3);
        intent.putExtra(Constant.ETH_SET_GATEWAY, str4);
        intent.putExtra(Constant.ETH_SET_MASK, str5);
        intent.putExtra("dns", str6);
        sendBroadcast(intent);
    }

    public void setWifiSta_Close() {
        Intent intent = new Intent(ACTION_WIFI_STA_SET);
        intent.putExtra("enable", false);
        sendBroadcast(intent);
    }

    public void setBluetoothEnable(boolean z) {
        Intent intent = new Intent(ACTION_BLUETOOTH_EN);
        intent.putExtra("enable", z);
        sendBroadcast(intent);
    }

    public void setBluetoothName(String str) {
        Intent intent = new Intent(ACTION_BLUETOOTH_NAME_SET);
        intent.putExtra(NamingTable.TAG, str);
        sendBroadcast(intent);
    }

    public void setAudioVolume(int i) {
        Intent intent = new Intent(ACTION_SET_AUDIO_VOLUME);
        intent.putExtra("volume", i);
        sendBroadcast(intent);
    }

    public void setTimerReboot(int[] iArr) {
        Intent intent = new Intent(ACTION_SET_TIMER_POWER_REBOOT);
        intent.putExtra("reboottime", iArr);
        intent.putExtra("enable", true);
        sendBroadcast(intent);
    }

    public void clearTimerReboot() {
        Intent intent = new Intent(ACTION_SET_TIMER_POWER_REBOOT);
        intent.putExtra("enable", false);
        sendBroadcast(intent);
    }

    public void setDebugUart(boolean z) {
        Intent intent = new Intent(ACTION_SET_DEBUG_UART);
        intent.putExtra("enable", z);
        sendBroadcast(intent);
    }

    public void setSaveLogEn(boolean z) {
        Intent intent = new Intent(ACTION_SET_SAVE_LOG);
        intent.putExtra("enable", z);
        sendBroadcast(intent);
    }

    public void setAppBoot(String str) {
        Intent intent = new Intent(ACTION_SET_APP_BOOT);
        if (str == null) {
            str = "";
        }
        intent.putExtra("pkgname", str);
        sendBroadcast(intent);
    }

    public void setAppXml(String str, String str2) {
        Intent intent = new Intent(ACTION_SET_APP_XML);
        intent.putExtra("key", str);
        intent.putExtra("val", str2);
        sendBroadcast(intent);
    }

    public void setCameraImages(String str) {
        Intent intent = new Intent(ACTION_CAMERA_IMAGE);
        intent.putExtra(NotificationCompat.CATEGORY_STATUS, str);
        sendBroadcast(intent);
    }

    public void startPrint(String str) {
        Intent intent = new Intent(ACTION_PRINT_FILE);
        intent.putExtra("file", str);
        sendBroadcast(intent);
    }

    public void setAutoGsmModule(boolean z) {
        Intent intent = new Intent(ACTION_GSM_LISTEN);
        intent.putExtra("key", "gsm_auto_set");
        intent.putExtra("enable", z);
        sendBroadcast(intent);
    }

    public void setGsmListen(boolean z) {
        Intent intent = new Intent(ACTION_GSM_LISTEN);
        intent.putExtra("key", "gsm_listen");
        intent.putExtra("enable", z);
        sendBroadcast(intent);
    }

    public void systemUpdate(String str) {
        Intent intent = new Intent(ACTION_UPDATE_SYSTEM);
        intent.putExtra("file", str);
        sendBroadcast(intent);
    }

    public void setSwitch(String str, boolean z) {
        Intent intent = new Intent(ACTION_SWITCH);
        intent.putExtra(NotificationCompat.CATEGORY_STATUS, str);
        intent.putExtra("enable", z);
        sendBroadcast(intent);
    }

    public void setNetworkMode(String str) {
        Intent intent = new Intent(ACTION_NETWORK_MODE);
        intent.putExtra("mode", str);
        sendBroadcast(intent);
    }

    public void setSubNetwork(String str, String str2, boolean z) {
        Intent intent = new Intent(ACTION_SUB_ETHERNET_SET);
        intent.putExtra("ipaddr", str);
        intent.putExtra(Constant.ETH_SET_MASK, str2);
        intent.putExtra(SystemTimeSource.dhcp, z);
        sendBroadcast(intent);
    }

    public void setMainScreenTouch(String str) {
        Intent intent = new Intent(ACTION_SET_MAINSCREEN_TOUCH);
        intent.putExtra("type", str);
        sendBroadcast(intent);
    }

    public void setMainNetwork(String str) {
        Intent intent = new Intent(ACTION_NETWORK_SCORE);
        intent.putExtra("type", str);
        intent.putExtra(TrimmingType.score, 70);
        sendBroadcast(intent);
    }

    public void pushLog(String str, String str2, String str3) {
        Intent intent = new Intent(ACTION_PUSHNETLOG);
        intent.putExtra("url", str);
        intent.putExtra("user", str2);
        intent.putExtra("passwd", str3);
        sendBroadcast(intent);
    }

    public void setEthIpRoute(String str) {
        Intent intent = new Intent(ACTION_SET_ETH_IPROUTE);
        intent.putExtra("ipaddr", str);
        sendBroadcast(intent);
    }

    public void setHWRotation(int i) {
        Intent intent = new Intent(ACTION_SET_DISPLAY_HWROTATION);
        intent.putExtra(Key.ROTATION, i);
        sendBroadcast(intent);
    }

    public void setKeyVal(String str, String str2) {
        Intent intent = new Intent(ACTION_SET_KEY);
        intent.putExtra("key", str);
        intent.putExtra("value", str2);
        sendBroadcast(intent);
    }

    public void setBluetoothCmd(String str) {
        Intent intent = new Intent("android.intent.mybluetooth.cmd");
        intent.putExtra("cmd", str);
        sendBroadcast(intent);
    }
}
