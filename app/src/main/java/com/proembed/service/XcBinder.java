package com.proembed.service;

import android.content.Context;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import android.os.ServiceManager;

public class XcBinder {
    private static final String DESCRIPTOR = "XcBinder";
    private static final String TAG = "XcBinder";
    private static final int TRANSACTION_GET_4G_MODULE = 8;
    private static final int TRANSACTION_GET_4G_MODULE_LIST = 38;
    private static final int TRANSACTION_GET_APP_BOOT = 35;
    private static final int TRANSACTION_GET_APP_LISTEN = 28;
    private static final int TRANSACTION_GET_APP_XML = 36;
    private static final int TRANSACTION_GET_AUDIO_IN_TYPE = 46;
    private static final int TRANSACTION_GET_AUDIO_OUT_TYPE = 24;
    private static final int TRANSACTION_GET_BOOTINFO = 10;
    private static final int TRANSACTION_GET_BRIGHTNESS = 14;
    private static final int TRANSACTION_GET_CAMERA_IMAGE = 37;
    private static final int TRANSACTION_GET_CAMERA_ROTATION = 5;
    private static final int TRANSACTION_GET_CUR_DISPLAY = 32;
    private static final int TRANSACTION_GET_DEBUG_UART = 33;
    private static final int TRANSACTION_GET_DISPLAY_LIST = 31;
    private static final int TRANSACTION_GET_DOORLOCK = 43;
    private static final int TRANSACTION_GET_DPI = 6;
    private static final int TRANSACTION_GET_GSM_TO_ETH = 26;
    private static final int TRANSACTION_GET_HIDEBARSTATUS = 15;
    private static final int TRANSACTION_GET_HWROTATION = 47;
    private static final int TRANSACTION_GET_MAINSCREEN_TOUCH = 44;
    private static final int TRANSACTION_GET_NETWORK_MODE = 41;
    private static final int TRANSACTION_GET_NETWORK_SCORE = 45;
    private static final int TRANSACTION_GET_OTG_MODE = 27;
    private static final int TRANSACTION_GET_PLATFORMINFO = 12;
    private static final int TRANSACTION_GET_RUN_STATUS = 39;
    private static final int TRANSACTION_GET_SAVELOG = 34;
    private static final int TRANSACTION_GET_SCREEN_ROTATION = 4;
    private static final int TRANSACTION_GET_SERVICE_VERSION = 23;
    private static final int TRANSACTION_GET_SLIDEBARSTATUS = 16;
    private static final int TRANSACTION_GET_SUB_SCREEN_ROTATION = 18;
    private static final int TRANSACTION_GET_SWITCH = 40;
    private static final int TRANSACTION_GET_TIMER_POWEROFF = 21;
    private static final int TRANSACTION_GET_TIMER_POWERON = 20;
    private static final int TRANSACTION_GET_TIMER_POWER_STATUS = 19;
    private static final int TRANSACTION_GET_TIMER_POWER_WEEK = 22;
    private static final int TRANSACTION_GET_TIMER_REBOOT = 30;
    private static final int TRANSACTION_GET_TIMER_REBOOT_STATUS = 29;
    private static final int TRANSACTION_GET_TOUCH_STATUS = 25;
    private static final int TRANSACTION_SET_4G_MODULE = 9;
    private static final int TRANSACTION_SET_BOOTINFO = 11;
    private static final int TRANSACTION_SET_BRIGHTNESS = 13;
    private static final int TRANSACTION_SET_CAMERA_ROTATION = 3;
    private static final int TRANSACTION_SET_DOORLOCK = 42;
    private static final int TRANSACTION_SET_DPI = 7;
    private static final int TRANSACTION_SET_SCREEN_ROTATION = 2;
    private static final int TRANSACTION_SET_SUB_SCREEN_ROTATION = 17;
    private String current_func;
    private IBinder mBinder = ServiceManager.getService("XcBinder");
    private Context mContext;

    public XcBinder(Context context) {
        this.mContext = context;
    }

    private String _FUNC_() {
        return new Exception().getStackTrace()[1].getMethodName();
    }

    private void sendBinder(int i, String str) {
        if (this.mBinder == null) {
            this.mBinder = ServiceManager.getService("XcBinder");
            return;
        }
        this.current_func = _FUNC_();
        Parcel parcelObtain = Parcel.obtain();
        Parcel parcelObtain2 = Parcel.obtain();
        parcelObtain.writeInterfaceToken("XcBinder");
        parcelObtain.writeString(str);
        try {
            try {
                this.mBinder.transact(i, parcelObtain, parcelObtain2, 0);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        } finally {
            parcelObtain.recycle();
            parcelObtain2.recycle();
        }
    }

    private void sendBinder(int i, int i2) {
        if (this.mBinder == null) {
            this.mBinder = ServiceManager.getService("XcBinder");
            return;
        }
        Parcel parcelObtain = Parcel.obtain();
        Parcel parcelObtain2 = Parcel.obtain();
        parcelObtain.writeInterfaceToken("XcBinder");
        parcelObtain.writeInt(i2);
        try {
            try {
                this.mBinder.transact(i, parcelObtain, parcelObtain2, 0);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        } finally {
            parcelObtain.recycle();
            parcelObtain2.recycle();
        }
    }

    private int getBinder_int(int i) {
        if (this.mBinder == null) {
            this.mBinder = ServiceManager.getService("XcBinder");
            return -1;
        }
        Parcel parcelObtain = Parcel.obtain();
        Parcel parcelObtain2 = Parcel.obtain();
        parcelObtain.writeInterfaceToken("XcBinder");
        int i2 = 0;
        try {
            try {
                this.mBinder.transact(i, parcelObtain, parcelObtain2, 0);
                i2 = parcelObtain2.readInt();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
            return i2;
        } finally {
            parcelObtain.recycle();
            parcelObtain2.recycle();
        }
    }

    private int getBinder_int(int i, String str) {
        int i2 = -1;
        if (this.mBinder == null) {
            this.mBinder = ServiceManager.getService("XcBinder");
            return -1;
        }
        Parcel parcelObtain = Parcel.obtain();
        Parcel parcelObtain2 = Parcel.obtain();
        parcelObtain.writeInterfaceToken("XcBinder");
        if (str != null) {
            parcelObtain.writeString(str);
        }
        try {
            try {
                this.mBinder.transact(i, parcelObtain, parcelObtain2, 0);
                i2 = parcelObtain2.readInt();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
            return i2;
        } finally {
            parcelObtain.recycle();
            parcelObtain2.recycle();
        }
    }

    private String getBinder_string(int i, String str) {
        String string;
        if (this.mBinder == null) {
            this.mBinder = ServiceManager.getService("XcBinder");
            return "";
        }
        Parcel parcelObtain = Parcel.obtain();
        Parcel parcelObtain2 = Parcel.obtain();
        parcelObtain.writeInterfaceToken("XcBinder");
        if (str != null) {
            parcelObtain.writeString(str);
        }
        try {
            try {
                this.mBinder.transact(i, parcelObtain, parcelObtain2, 0);
                string = parcelObtain2.readString();
            } catch (RemoteException e) {
                e.printStackTrace();
                parcelObtain.recycle();
                parcelObtain2.recycle();
                string = "";
            }
            return string == null ? "" : string;
        } finally {
            parcelObtain.recycle();
            parcelObtain2.recycle();
        }
    }

    private String getBinder_string(int i) {
        String string;
        if (this.mBinder == null) {
            this.mBinder = ServiceManager.getService("XcBinder");
            return "";
        }
        Parcel parcelObtain = Parcel.obtain();
        Parcel parcelObtain2 = Parcel.obtain();
        parcelObtain.writeInterfaceToken("XcBinder");
        try {
            try {
                this.mBinder.transact(i, parcelObtain, parcelObtain2, 0);
                string = parcelObtain2.readString();
            } catch (RemoteException e) {
                e.printStackTrace();
                parcelObtain.recycle();
                parcelObtain2.recycle();
                string = "";
            }
            return string == null ? "" : string;
        } finally {
            parcelObtain.recycle();
            parcelObtain2.recycle();
        }
    }

    public void setMainScreenRotation(int i) {
        sendBinder(2, i);
    }

    public int getMainScreenRotation() {
        return getBinder_int(4);
    }

    public void setSubScreenRotation(int i) {
        sendBinder(17, i);
    }

    public int getSubScreenRotation() {
        return getBinder_int(18);
    }

    public void setCameraRotation(int i) {
        sendBinder(3, i);
    }

    public int getCameraRotation() {
        return getBinder_int(5);
    }

    public void setDisplayDensity(int i) {
        sendBinder(7, i);
    }

    public int getDisplayDensity() {
        return getBinder_int(6);
    }

    public void setGsmModule(String str) {
        sendBinder(9, str);
    }

    public String getGsmModule() {
        return getBinder_string(8);
    }

    public String getGsmModuleList() {
        return getBinder_string(38);
    }

    public void setBootInfo(String str, String str2) {
        sendBinder(11, new String(str + "=" + str2));
    }

    public String getBootInfo(String str) {
        return getBinder_string(10, str);
    }

    public String getPlatformInfo() {
        return getBinder_string(12, null);
    }

    public void setDisplayType(String str) {
        setBootInfo("display_type", str);
    }

    public String getDisplayType() {
        return getBootInfo("display_type");
    }

    public void setSerial(String str) {
        setBootInfo("serial", str);
    }

    public String getSerial() {
        return getBootInfo("serial");
    }

    public void setBrightness(int i) {
        sendBinder(13, i);
    }

    public int getBrightness() {
        return getBinder_int(14);
    }

    public boolean getHideBarStatus() {
        return getBinder_int(15) == 1;
    }

    public boolean getSlideBarStatus() {
        return getBinder_int(16) == 1;
    }

    public boolean getTimerPowerStatus() {
        return getBinder_int(19) == 1;
    }

    public String getTimerPowerOn() {
        return getBinder_string(20, null);
    }

    public String getTimerPowerOff() {
        return getBinder_string(21, null);
    }

    public String getTimerPowerWeek() {
        return getBinder_string(22, null);
    }

    public String getServiceVersion() {
        return getBinder_string(23);
    }

    public String getAudioOutType() {
        return getBinder_string(24);
    }

    public String getAudioInType() {
        return getBinder_string(46);
    }

    public String getTouchStatus() {
        return getBinder_string(25);
    }

    public int getGsmToEth() {
        return getBinder_int(26);
    }

    public int getOtgMode() {
        return getBinder_int(27);
    }

    public String getAppListen() {
        return getBinder_string(28);
    }

    public boolean getTimerRebootStatus() {
        return getBinder_int(29) == 1;
    }

    public String getTimerReboot() {
        return getBinder_string(30, null);
    }

    public String getDisplayList() {
        return getBinder_string(31);
    }

    public String getCurDisplay() {
        return getBinder_string(32);
    }

    public String getDebugUart() {
        return getBinder_string(33);
    }

    public boolean getSaveLogEn() {
        return "true".equals(getBinder_string(34));
    }

    public String getAppBoot() {
        return getBinder_string(35);
    }

    public String getAppXml(String str) {
        return getBinder_string(36, str);
    }

    public String getCameraImages() {
        return getBinder_string(37);
    }

    public String getRunStatus(String str) {
        return getBinder_string(39, str);
    }

    public boolean getAutoGsmModule() {
        return "true".equals(getBinder_string(40, "gsm_auto_set"));
    }

    public boolean getGsmListen() {
        return "true".equals(getBinder_string(40, "gsm_listen"));
    }

    public boolean getSwitch(String str) {
        return "true".equals(getBinder_string(40, str));
    }

    public String getNetworkMode() {
        return getBinder_string(41);
    }

    public boolean openDoorLock(String str) {
        return getBinder_int(42, str) == 1;
    }

    public String getDoorLockStatus() {
        return getBinder_string(43);
    }

    public String getMainScreenTouch() {
        return getBinder_string(44);
    }

    public String getMainNetwork() {
        return getBinder_string(45);
    }

    public int getHWRotation() {
        return getBinder_int(47);
    }
}
