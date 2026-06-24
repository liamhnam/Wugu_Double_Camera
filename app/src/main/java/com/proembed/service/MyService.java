package com.proembed.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Point;
import android.p011rk.RKWdt;
import com.tom_roush.fontbox.ttf.NamingTable;
import java.io.File;

public class MyService {
    private XcBroadcast mBroadcast;
    private MyCallback mCallback;
    private Context mContext;
    private String mPlatform;
    private final BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if ("android.intent.mybluetooth.request".equals(intent.getAction()) && intent.hasExtra(NamingTable.TAG)) {
                String stringExtra = intent.getStringExtra(NamingTable.TAG);
                if (MyService.this.mCallback != null) {
                    MyService.this.mCallback.callback("bluetooth_request", stringExtra);
                }
            }
        }
    };
    private String mServiceVersion;
    private SocketClient mSocketClient;
    private XcBinder mXcBinder;
    private int sdkVersion;

    public String getApiVersion() {
        return "V1.3-20210225-" + getPlatformInfo();
    }

    public MyService(Context context) {
        this.mPlatform = "";
        this.mServiceVersion = "";
        this.mContext = context;
        this.mBroadcast = new XcBroadcast(this.mContext);
        XcBinder xcBinder = new XcBinder(this.mContext);
        this.mXcBinder = xcBinder;
        String platformInfo = xcBinder.getPlatformInfo();
        this.mPlatform = platformInfo;
        if ("".equals(platformInfo)) {
            this.mPlatform = Utils.readGpioPG("/proc/proembed/board_info");
        }
        String serviceVersion = this.mXcBinder.getServiceVersion();
        this.mServiceVersion = serviceVersion;
        this.sdkVersion = Utils.str2int(serviceVersion.split(" ")[0]);
    }

    public void setStaticEthIPAddress(String str, String str2, String str3, String str4, String str5) {
        this.mBroadcast.setStaticEthIPAddress(str, str2, str3, str4, str5);
    }

    public void setDHCPEthIPAddress() {
        this.mBroadcast.setDHCPEthIPAddress();
    }

    public void ethEnabled(boolean z) {
        this.mBroadcast.ethEnabled(z);
    }

    public void setSubNetwork(String str, String str2, boolean z) {
        this.mBroadcast.setSubNetwork(str, str2, z);
    }

    public void setEthMac(String str) {
        this.mBroadcast.setEthMac(str);
    }

    public void rebootSystem() {
        this.mBroadcast.rebootSystem();
    }

    public void silentInstallApk(String str, String str2, boolean z) {
        this.mBroadcast.silentInstallApk(str, str2, z);
    }

    public void silentInstallApk(String str) throws Throwable {
        SilentInstallUtils.install(str);
    }

    public void silentUnInstallApk(String str) {
        this.mBroadcast.silentUnInstallApk(str);
    }

    public void setHideNavBar(boolean z) {
        this.mBroadcast.hideNavBar(z);
    }

    public boolean getHideNavBar() {
        return this.mXcBinder.getHideBarStatus();
    }

    public void setSlideNavBar(boolean z) {
        this.mBroadcast.setSlideNavBar(z);
    }

    public boolean getSlideNavBar() {
        return this.mXcBinder.getSlideBarStatus();
    }

    public void setTouchStatus(String str) {
        this.mBroadcast.setTouchStatus(str);
    }

    public String getTouchStatus() {
        return this.mXcBinder.getTouchStatus();
    }

    public void setSystemTimeMillis(long j) {
        this.mBroadcast.setSystemTimeMillis(j);
    }

    public void setSystemTime(int i, int i2, int i3, int i4, int i5, int i6) {
        this.mBroadcast.setSystemTimeMillis(TimeUtils.getTimeMills(i, i2, i3, i4, i5, i6));
    }

    public void switchAutoTime(boolean z) {
        this.mBroadcast.switchAutoTime(z);
    }

    public void shutdownSystem() {
        this.mBroadcast.shutdownSystem();
    }

    public void setTimerPower(int[] iArr, int[] iArr2, int[] iArr3) {
        this.mBroadcast.setTimerPower(iArr, iArr2, iArr3);
    }

    public void setTimerPower(int[] iArr, int[] iArr2, int i) {
        this.mBroadcast.setTimerPower(iArr, iArr2, i);
    }

    public boolean getTimerPowerStatus() {
        return this.mXcBinder.getTimerPowerStatus();
    }

    public int[] getTimerPowerOn() {
        String[] strArrSplit = this.mXcBinder.getTimerPowerOn().split(":");
        if (strArrSplit.length == 5) {
            return new int[]{Integer.parseInt(strArrSplit[0]), Integer.parseInt(strArrSplit[1]), Integer.parseInt(strArrSplit[2]), Integer.parseInt(strArrSplit[3]), Integer.parseInt(strArrSplit[4])};
        }
        return strArrSplit.length == 2 ? new int[]{Integer.parseInt(strArrSplit[0]), Integer.parseInt(strArrSplit[1])} : new int[2];
    }

    public int[] getTimerPowerOff() {
        String[] strArrSplit = this.mXcBinder.getTimerPowerOff().split(":");
        if (strArrSplit.length == 5) {
            return new int[]{Integer.parseInt(strArrSplit[0]), Integer.parseInt(strArrSplit[1]), Integer.parseInt(strArrSplit[2]), Integer.parseInt(strArrSplit[3]), Integer.parseInt(strArrSplit[4])};
        }
        return strArrSplit.length == 2 ? new int[]{Integer.parseInt(strArrSplit[0]), Integer.parseInt(strArrSplit[1])} : new int[2];
    }

    public int[] getTimerPowerWeek() {
        int[] iArr = new int[7];
        String[] strArrSplit = this.mXcBinder.getTimerPowerWeek().split(",");
        if (strArrSplit.length == 7) {
            for (int i = 0; i < strArrSplit.length; i++) {
                iArr[i] = Integer.parseInt(strArrSplit[i]);
            }
        }
        return iArr;
    }

    public void clearTimerPower() {
        this.mBroadcast.clearTimerPower();
    }

    public void setAudioOutType(String str) {
        this.mBroadcast.setAudioOutType(str);
    }

    public String getAudioOutType() {
        return this.mXcBinder.getAudioOutType();
    }

    public void setAudioInType(String str) {
        this.mBroadcast.setAudioInType(str);
    }

    public String getAudioInType() {
        return this.mXcBinder.getAudioInType();
    }

    public void setWifiAP_Open(String str, String str2) {
        this.mBroadcast.setWifiAP_Open(str, str2);
    }

    public void setWifiAP_Close() {
        this.mBroadcast.setWifiAP_Close();
    }

    public void setWifiStaDhcp_Open(String str, String str2) {
        this.mBroadcast.setWifiStaDhcp_Open(str, str2);
    }

    public void setWifiStaStatic_Open(String str, String str2, String str3, String str4, String str5, String str6) {
        this.mBroadcast.setWifiStaStatic_Open(str, str2, str3, str4, str5, str6);
    }

    public void setWifiSta_Close() {
        this.mBroadcast.setWifiSta_Close();
    }

    public void setBluetoothEnable(boolean z) {
        this.mBroadcast.setBluetoothEnable(z);
    }

    public void setBluetoothName(String str) {
        this.mBroadcast.setBluetoothName(str);
    }

    public void setAudioVolume(int i) {
        this.mBroadcast.setAudioVolume(i);
    }

    public void setTimerReboot(int[] iArr) {
        this.mBroadcast.setTimerReboot(iArr);
    }

    public void clearTimerReboot() {
        this.mBroadcast.clearTimerReboot();
    }

    public void setMainScreenRotation(int i) {
        this.mXcBinder.setMainScreenRotation(i);
    }

    public int getMainScreenRotation() {
        return this.mXcBinder.getMainScreenRotation();
    }

    public void setSubScreenRotation(int i) {
        this.mXcBinder.setSubScreenRotation(i);
    }

    public int getSubScreenRotation() {
        return this.mXcBinder.getSubScreenRotation();
    }

    public void setHWRotation(int i) {
        this.mBroadcast.setHWRotation(i);
    }

    public int getHWRotation() {
        return this.mXcBinder.getHWRotation();
    }

    public void setCameraRotation(int i) {
        this.mXcBinder.setCameraRotation(i);
    }

    public int getCameraRotation() {
        return this.mXcBinder.getCameraRotation();
    }

    public void setCameraImages(String str) {
        this.mBroadcast.setCameraImages(str);
    }

    public String getCameraImages() {
        return this.mXcBinder.getCameraImages();
    }

    public void setDisplayDensity(int i) {
        this.mXcBinder.setDisplayDensity(i);
    }

    public int getDisplayDensity() {
        return this.mXcBinder.getDisplayDensity();
    }

    public void setGsmModule(String str) {
        this.mXcBinder.setGsmModule(str);
    }

    public String getGsmModule() {
        return this.mXcBinder.getGsmModule();
    }

    public String getGsmModuleList() {
        return this.mXcBinder.getGsmModuleList();
    }

    public String getPlatformInfo() {
        return this.mPlatform;
    }

    public void setDisplayType(String str) {
        this.mXcBinder.setDisplayType(str);
    }

    public String getDisplayType() {
        return this.mXcBinder.getDisplayType();
    }

    public String getDisplayList() {
        return this.mXcBinder.getDisplayList();
    }

    public String getCurDisplay() {
        return this.mXcBinder.getCurDisplay();
    }

    public void setBrightness(int i) {
        this.mXcBinder.setBrightness(i);
    }

    public int getBrightness() {
        return this.mXcBinder.getBrightness();
    }

    public void setSerial(String str) {
        this.mXcBinder.setSerial(str);
    }

    public String getSerial() {
        return this.mXcBinder.getSerial();
    }

    public String getServiceVersion() {
        return this.mServiceVersion;
    }

    public void setBootInfo(String str, String str2) {
        this.mXcBinder.setBootInfo(str, str2);
    }

    public String getBootInfo(String str) {
        return this.mXcBinder.getBootInfo(str);
    }

    public void setGsmToEth(boolean z) {
        this.mBroadcast.setGsmToEth(z);
    }

    public int getGsmToEth() {
        return this.mXcBinder.getGsmToEth();
    }

    public void setNetworkMode(String str) {
        this.mBroadcast.setNetworkMode(str);
    }

    public String getNetworkMode() {
        return this.mXcBinder.getNetworkMode();
    }

    public void setOtgMode(boolean z) {
        this.mBroadcast.setOtgMode(z);
    }

    public int getOtgMode() {
        return this.mXcBinder.getOtgMode();
    }

    public void setDebugUart(boolean z) {
        this.mBroadcast.setDebugUart(z);
    }

    public String getDebugUart() {
        return this.mXcBinder.getDebugUart();
    }

    public void setAppListen(String str) {
        this.mBroadcast.setAppListen(str);
    }

    public void setAppListen(String str, int i) {
        this.mBroadcast.setAppListen(str, i);
    }

    public String getAppListen() {
        return this.mXcBinder.getAppListen();
    }

    public void setSaveLogEn(boolean z) {
        this.mBroadcast.setSaveLogEn(z);
    }

    public boolean getSaveLogEn() {
        return this.mXcBinder.getSaveLogEn();
    }

    public void setAppBoot(String str) {
        this.mBroadcast.setAppBoot(str);
    }

    public String getAppBoot() {
        return this.mXcBinder.getAppBoot();
    }

    public void setAppInstallPasswd(String str) {
        this.mBroadcast.setKeyVal("app_installpasswd", str);
    }

    public String getAppInstallPasswd() {
        return this.mXcBinder.getRunStatus("app_installpasswd");
    }

    public void setNtpServer(String str) {
        this.mBroadcast.setKeyVal("ntp_server", str);
    }

    public String getNtpServer() {
        return this.mXcBinder.getRunStatus("ntp_server");
    }

    public void setKeyVal(String str, String str2) {
        this.mBroadcast.setKeyVal(str, str2);
    }

    public String getKeyVal(String str) {
        return this.mXcBinder.getRunStatus(str);
    }

    public void setAppXml(String str, String str2) {
        this.mBroadcast.setAppXml(str, str2);
    }

    public String getAppXml(String str) {
        return this.mXcBinder.getAppXml(str);
    }

    public void startPrint(String str) {
        this.mBroadcast.startPrint(str);
    }

    public void setAutoGsmModule(boolean z) {
        this.mBroadcast.setAutoGsmModule(z);
    }

    public boolean getAutoGsmModule() {
        return this.mXcBinder.getAutoGsmModule();
    }

    public void setGsmListen(boolean z) {
        this.mBroadcast.setGsmListen(z);
    }

    public boolean getGsmListen() {
        return this.mXcBinder.getGsmListen();
    }

    public void systemUpdate(String str) {
        this.mBroadcast.systemUpdate(str);
    }

    public void setMainScreenTouch(String str) {
        this.mBroadcast.setMainScreenTouch(str);
    }

    public String getMainScreenTouch() {
        return this.mXcBinder.getMainScreenTouch();
    }

    public void setMainNetwork(String str) {
        this.mBroadcast.setMainNetwork(str);
    }

    public String getMainNetwork() {
        return this.mXcBinder.getMainNetwork();
    }

    public void setSwitch(String str, boolean z) {
        this.mBroadcast.setSwitch(str, z);
    }

    public boolean getSwitch(String str) {
        return this.mXcBinder.getSwitch(str);
    }

    public void setTouchWakeup(boolean z) {
        setSwitch("TouchWakeup", z);
    }

    public boolean getTouchWakeup() {
        return getSwitch("TouchWakeup");
    }

    public void setDisplayEnable(boolean z) {
        setSwitch("OpenDisplay", z);
    }

    public boolean getDisplayEnable() {
        return getSwitch("OpenDisplay");
    }

    public void pushLog(String str, String str2, String str3) {
        this.mBroadcast.pushLog(str, str2, str3);
    }

    public void setEthIpRoute(String str) {
        this.mBroadcast.setEthIpRoute(str);
    }

    public int getDisplayWidth() {
        Point point = new Point();
        Utils.findActivity(this.mContext).getWindowManager().getDefaultDisplay().getRealSize(point);
        return point.x;
    }

    public int getDisplayHeight() {
        Point point = new Point();
        Utils.findActivity(this.mContext).getWindowManager().getDefaultDisplay().getRealSize(point);
        return point.y;
    }

    public int getCPUTemperature() {
        return XcFactory.getRK().getCPUTemperature();
    }

    public void execSuCmd(String str) throws Throwable {
        Utils.execFor7(str);
    }

    public void getAndroidLogcat(String str) {
        LogUtils.startLog(str);
    }

    public void stopAndroidLogcat() {
        LogUtils.stopLog();
    }

    public String getRunStatus(String str) {
        return this.mXcBinder.getRunStatus(str);
    }

    public void replaceBootanimation(String str) throws Throwable {
        String[] strArr = {"mount -o rw,remount -t ext4 /system", "rm -rf system/media/bootanimation.zip", "cp  " + str + " system/media/bootanimation.zip", "chmod 644 system/media/bootanimation.zip", "sync", "mount -o ro,remount -t ext4 /system"};
        for (int i = 0; i < 6; i++) {
            Utils.execFor7(strArr[i]);
        }
    }

    public void setBuildProp(String str, String str2) throws Throwable {
        String[] strArr = {"mount -o rw,remount -t ext4 /system", "sed -i '/^" + str + "/d' /system/build.prop", "echo \"" + str + "=" + str2 + "\" >> /system/build.prop", "sync", "mount -o ro,remount -t ext4 /system"};
        for (int i = 0; i < 5; i++) {
            Utils.execFor7(strArr[i]);
        }
    }

    public void setDoor2Lock(boolean z) throws Throwable {
        if ("rk3288hw1.x 7.1.2".equals(this.mPlatform)) {
            setGpioValue_force(169, !z ? 1 : 0);
        } else if ("rk3288hw1.x 5.1.1".equals(this.mPlatform)) {
            setGpioValue_force(177, !z ? 1 : 0);
        } else if (this.mPlatform.contains("3128")) {
            setGpioValue_force(73, !z ? 1 : 0);
        }
    }

    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean getDoor2Lock() {
        /*
            r4 = this;
            java.lang.String r0 = "rk3288hw1.x 7.1.2"
            java.lang.String r1 = r4.mPlatform
            boolean r0 = r0.equals(r1)
            r1 = 1
            r2 = 0
            if (r0 == 0) goto L19
            r0 = 226(0xe2, float:3.17E-43)
            int r0 = r4.getGpioValue_force(r0)
            if (r0 != 0) goto L16
            goto L17
        L16:
            r1 = r2
        L17:
            r2 = r1
            goto L40
        L19:
            java.lang.String r0 = "rk3288hw1.x 5.1.1"
            java.lang.String r3 = r4.mPlatform
            boolean r0 = r0.equals(r3)
            if (r0 == 0) goto L2d
            r0 = 234(0xea, float:3.28E-43)
            int r0 = r4.getGpioValue_force(r0)
            if (r0 != 0) goto L16
            goto L17
        L2d:
            java.lang.String r0 = r4.mPlatform
            java.lang.String r3 = "3128"
            boolean r0 = r0.contains(r3)
            if (r0 == 0) goto L40
            r0 = 40
            int r0 = r4.getGpioValue_force(r0)
            if (r0 != 0) goto L16
            goto L17
        L40:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.proembed.service.MyService.getDoor2Lock():boolean");
    }

    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean getDoor2Mag() {
        /*
            r4 = this;
            java.lang.String r0 = "rk3288hw1.x 7.1.2"
            java.lang.String r1 = r4.mPlatform
            boolean r0 = r0.equals(r1)
            r1 = 1
            r2 = 0
            if (r0 == 0) goto L19
            r0 = 257(0x101, float:3.6E-43)
            int r0 = r4.getGpioValue_force(r0)
            if (r1 != r0) goto L16
            goto L17
        L16:
            r1 = r2
        L17:
            r2 = r1
            goto L2d
        L19:
            java.lang.String r0 = "rk3288hw1.x 5.1.1"
            java.lang.String r3 = r4.mPlatform
            boolean r0 = r0.equals(r3)
            if (r0 == 0) goto L2d
            r0 = 265(0x109, float:3.71E-43)
            int r0 = r4.getGpioValue_force(r0)
            if (r1 != r0) goto L16
            goto L17
        L2d:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.proembed.service.MyService.getDoor2Mag():boolean");
    }

    public void setDoorLock(boolean z) throws Throwable {
        if ("rk3288hw1.x 7.1.2".equals(this.mPlatform)) {
            setGpioValue_force(254, !z ? 1 : 0);
        } else if ("rk3288hw1.x 5.1.1".equals(this.mPlatform)) {
            setGpioValue_force(262, !z ? 1 : 0);
        }
    }

    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean getDoorLock() {
        /*
            r4 = this;
            java.lang.String r0 = "rk3288hw1.x 7.1.2"
            java.lang.String r1 = r4.mPlatform
            boolean r0 = r0.equals(r1)
            r1 = 1
            r2 = 0
            if (r0 == 0) goto L19
            r0 = 251(0xfb, float:3.52E-43)
            int r0 = r4.getGpioValue_force(r0)
            if (r0 != 0) goto L16
            goto L17
        L16:
            r1 = r2
        L17:
            r2 = r1
            goto L2d
        L19:
            java.lang.String r0 = "rk3288hw1.x 5.1.1"
            java.lang.String r3 = r4.mPlatform
            boolean r0 = r0.equals(r3)
            if (r0 == 0) goto L2d
            r0 = 259(0x103, float:3.63E-43)
            int r0 = r4.getGpioValue_force(r0)
            if (r0 != 0) goto L16
            goto L17
        L2d:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.proembed.service.MyService.getDoorLock():boolean");
    }

    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean getDoorMag() {
        /*
            r4 = this;
            java.lang.String r0 = "rk3288hw1.x 7.1.2"
            java.lang.String r1 = r4.mPlatform
            boolean r0 = r0.equals(r1)
            r1 = 1
            r2 = 0
            if (r0 == 0) goto L19
            r0 = 255(0xff, float:3.57E-43)
            int r0 = r4.getGpioValue_force(r0)
            if (r0 != 0) goto L16
            goto L17
        L16:
            r1 = r2
        L17:
            r2 = r1
            goto L2d
        L19:
            java.lang.String r0 = "rk3288hw1.x 5.1.1"
            java.lang.String r3 = r4.mPlatform
            boolean r0 = r0.equals(r3)
            if (r0 == 0) goto L2d
            r0 = 263(0x107, float:3.69E-43)
            int r0 = r4.getGpioValue_force(r0)
            if (r0 != 0) goto L16
            goto L17
        L2d:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.proembed.service.MyService.getDoorMag():boolean");
    }

    public void setGpioValue_force(int i, int i2) throws Throwable {
        if (Utils.checkfile("/proc/proembed/gpio" + i + "_dir")) {
            Utils.writeStringFileFor7(new File("/proc/proembed/gpio" + i + "_dir"), i2 != 1 ? "low" : "high");
        } else if (Utils.checkfile("/proc/proembed/gpio_export")) {
            Utils.writeStringFileFor7(new File("/proc/proembed/gpio_export"), (i2 != 1 ? "low" : "high") + " " + i);
        } else if (Utils.checkfile("/proc/proembed/gpio")) {
            Utils.writeStringFileFor7(new File("/proc/proembed/gpio"), i + " " + i2);
        }
    }

    public int getGpioValue_force(int i) throws Throwable {
        if (Utils.checkfile("/proc/proembed/gpio" + i + "_dir")) {
            if (!"in".equals(Utils.readGpioPG("/proc/proembed/gpio" + i + "_dir"))) {
                Utils.writeStringFileFor7(new File("/proc/proembed/gpio" + i + "_dir"), "in");
            }
            return Utils.str2int(Utils.readGpioValue("/proc/proembed/gpio" + i + "_value"));
        }
        if (Utils.checkfile("/proc/proembed/gpio_export")) {
            Utils.writeStringFileFor7(new File("/proc/proembed/gpio_export"), "in " + i);
            return Utils.str2int(Utils.readGpioValue("/proc/proembed/gpio" + i + "_value"));
        }
        if (!Utils.checkfile("/proc/proembed/gpio")) {
            return 0;
        }
        Utils.writeStringFileFor7(new File("/proc/proembed/gpio"), "r " + i);
        return Utils.str2int(Utils.readGpioValue("/proc/proembed/gpio"));
    }

    public int EnableWtd(int i) {
        if (this.sdkVersion < 20201208) {
            return -1;
        }
        return RKWdt.EnableWtd(i);
    }

    public int FeedWdt() {
        if (this.sdkVersion < 20201208) {
            return -1;
        }
        return RKWdt.FeedWdt();
    }

    public int DisableWdt() {
        if (this.sdkVersion < 20201208) {
            return -1;
        }
        return RKWdt.DisableWdt();
    }

    public int getTimeoutWdt() {
        if (this.sdkVersion < 20201208) {
            return -1;
        }
        return RKWdt.getTimeoutWdt();
    }

    public void setGpioDirection(int i, String str) {
        GPIOUtils.setGpioDirection(i, str);
    }

    public String getGpioDirection(int i) {
        return GPIOUtils.getGpioDirection(i);
    }

    public void setGpioValue(int i, int i2) {
        GPIOUtils.writeGpioValue(i, i2);
    }

    public int getGpioValue(int i) {
        return Integer.parseInt(GPIOUtils.getGpioValue(i));
    }

    public boolean exportGpio(int i) {
        return GPIOUtils.exportGpio(i);
    }

    public String getUSBStoragePath() {
        return Utils.getValueFromProp("sys.udisk.path");
    }

    public String getPropValue(String str) {
        return Utils.getValueFromProp(str);
    }

    public void setPropValue(String str, String str2) {
        Utils.setValueToProp(str, str2);
    }

    public String getRelayStatus() {
        return Utils.checkfile("/sys/class/custom_class/custom_dev/relay") ? Utils.readGpioValue("/sys/class/custom_class/custom_dev/relay") : "";
    }

    public void setRelayStatus(int i) throws Throwable {
        if (Utils.checkfile("/sys/class/custom_class/custom_dev/relay")) {
            Utils.writeStringFileFor7(new File("/sys/class/custom_class/custom_dev/relay"), String.valueOf(i));
        }
    }

    public String getBGDStatus(int i) {
        return i == 1 ? Utils.checkfile("/sys/class/custom_class/custom_dev/white_led") ? Utils.readGpioValue("/sys/class/custom_class/custom_dev/white_led") : "" : i == 2 ? Utils.checkfile("/sys/class/custom_class/custom_dev/green_led") ? Utils.readGpioValue("/sys/class/custom_class/custom_dev/green_led") : "" : (i == 3 && Utils.checkfile("/sys/class/custom_class/custom_dev/red_led")) ? Utils.readGpioValue("/sys/class/custom_class/custom_dev/red_led") : "";
    }

    public void setBGDStatus(int i, int i2) throws Throwable {
        if (i == 1) {
            if (Utils.checkfile("/sys/class/custom_class/custom_dev/white_led")) {
                Utils.writeStringFileFor7(new File("/sys/class/custom_class/custom_dev/white_led"), String.valueOf(i2));
            }
        } else if (i == 2) {
            if (Utils.checkfile("/sys/class/custom_class/custom_dev/green_led")) {
                Utils.writeStringFileFor7(new File("/sys/class/custom_class/custom_dev/green_led"), String.valueOf(i2));
            }
        } else if (i == 3 && Utils.checkfile("/sys/class/custom_class/custom_dev/red_led")) {
            Utils.writeStringFileFor7(new File("/sys/class/custom_class/custom_dev/red_led"), String.valueOf(i2));
        }
    }

    public String HDInit(int i, String str, String str2) {
        if (this.mSocketClient == null) {
            this.mSocketClient = new SocketClient();
        }
        if (!this.mSocketClient.isConnect()) {
            this.mSocketClient = new SocketClient();
        }
        if (!this.mSocketClient.isConnect()) {
            return "noconnect";
        }
        this.mSocketClient.setKeyVal("HDInit", String.valueOf(i) + "," + str + "," + str2);
        return this.mSocketClient.read();
    }

    public String HDOpenSpring(int i) {
        if (!this.mSocketClient.isConnect()) {
            return "noconnect";
        }
        this.mSocketClient.setKeyVal("HDOpenSpring", String.valueOf(i));
        return this.mSocketClient.read();
    }

    public void setBluetoothCmd(String str) {
        Intent intent = new Intent("android.intent.mybluetooth.cmd");
        intent.putExtra("cmd", str);
        this.mContext.sendBroadcast(intent);
    }

    public void setListener(MyCallback myCallback) {
        if (myCallback == null) {
            return;
        }
        this.mCallback = myCallback;
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.mybluetooth.request");
        this.mContext.registerReceiver(this.mReceiver, intentFilter);
    }

    public int getAdcValue(int i) {
        if (this.mPlatform.contains("3128")) {
            return Utils.str2int(Utils.getDatafromFile("/sys/bus/platform/drivers/rockchip-adc/2006c000.adc/iio:device0/in_voltage" + i + "_raw", 16));
        }
        return 0;
    }
}
