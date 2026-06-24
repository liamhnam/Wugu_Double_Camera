package ZtlApi;

import android.app.usage.StorageStatsManager;
import android.content.Intent;
import android.os.Build;
import android.os.storage.StorageManager;
import android.util.Log;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ZtlManager3399_11 extends ZtlManager {
    private String TAG = "ZtlManager3568";

    @Override
    public void setScreenMode(String str) {
    }

    ZtlManager3399_11() {
        this.DEBUG_ZTL = getSystemProperty("persist.sys.ztl.debug", "false").equals("true");
    }

    @Override
    public long getTotalInternalMemorySize() {
        if (Build.VERSION.SDK_INT < 26) {
            return -1L;
        }
        StorageStatsManager storageStatsManager = (StorageStatsManager) this.mContext.getSystemService("storagestats");
        try {
            long totalBytes = storageStatsManager.getTotalBytes(StorageManager.UUID_DEFAULT);
            storageStatsManager.getFreeBytes(StorageManager.UUID_DEFAULT);
            return totalBytes;
        } catch (IOException e) {
            e.printStackTrace();
            return -1L;
        }
    }

    @Override
    public String[] getHDMIResolutions() {
        try {
            return loadFileAsString("/sys/class/drm/card0-HDMI-A-1/modes").split("\n");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public String getFreq(String str) {
        return String.valueOf(execRootCmd(String.format("cat /sys/devices/system/cpu/cpufreq/policy%s/scaling_available_frequencies", str)));
    }

    @Override
    public String getNowFreq(String str) {
        return String.valueOf(execRootCmd(String.format("cat /sys/devices/system/cpu/cpufreq/policy%s/cpuinfo_cur_freq", str)));
    }

    @Override
    public void setNowFreq(String str, String str2) {
        execRootCmdSilent("echo userspace > /sys/devices/system/cpu/cpufreq/policy" + str + "/scaling_governor");
        execRootCmdSilent("echo  " + str2 + " > /sys/devices/system/cpu/cpufreq/policy" + str + "/scaling_setspeed");
    }

    @Override
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void setUSBtoPC(boolean r7) {
        /*
            r6 = this;
            android.content.Context r0 = r6.mContext
            if (r0 != 0) goto Ld
            java.lang.String r7 = r6.TAG
            java.lang.String r0 = "上下文为空,不执行"
            android.util.Log.e(r7, r0)
            return
        Ld:
            java.lang.String r0 = getDeviceVersion()
            java.lang.String r1 = "3588"
            boolean r0 = r0.contains(r1)
            java.lang.String r1 = "1"
            java.lang.String r2 = "host"
            java.lang.String r3 = "2"
            if (r0 == 0) goto L28
            java.lang.String r0 = "/sys/kernel/debug/usb/fc000000.usb/mode"
            if (r7 == 0) goto L26
            java.lang.String r2 = "device"
            goto L54
        L26:
            r1 = r3
            goto L54
        L28:
            java.lang.String r0 = getDeviceVersion()
            java.lang.String r4 = "3399"
            boolean r0 = r0.contains(r4)
            java.lang.String r4 = "otg"
            if (r0 == 0) goto L3d
            java.lang.String r0 = "/sys/devices/platform/ff770000.syscon/ff770000.syscon:usb2-phy@e450/otg_mode"
            if (r7 == 0) goto L54
        L3a:
            r1 = r3
        L3b:
            r2 = r4
            goto L54
        L3d:
            java.lang.String r0 = getDeviceVersion()
            java.lang.String r5 = "3288"
            boolean r0 = r0.contains(r5)
            if (r0 == 0) goto L4f
            java.lang.String r0 = "sys/devices/platform/ff770000.syscon/ff770000.syscon:usbphy/phy/phy-ff770000.syscon:usbphy.1/otg_mode"
            if (r7 == 0) goto L54
            goto L3a
        L4f:
            java.lang.String r0 = "/sys/devices/platform/fe8a0000.usb2-phy/otg_mode"
            if (r7 == 0) goto L26
            goto L3b
        L54:
            java.lang.String r3 = "persist.usb.mode"
            r6.setSystemProperty(r3, r1)
            java.lang.String r1 = ""
            if (r7 == 0) goto L64
            java.lang.String r7 = "ro.ztl.otgcmd"
            java.lang.String r7 = r6.getSystemProperty(r7, r1)
            goto L6a
        L64:
            java.lang.String r7 = "ro.ztl.hostcmd"
            java.lang.String r7 = r6.getSystemProperty(r7, r1)
        L6a:
            int r1 = r7.length()
            if (r1 <= 0) goto L79
            java.io.PrintStream r0 = java.lang.System.out
            r0.println(r7)
            r6.execRootCmdSilent(r7)
            return
        L79:
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            java.lang.String r1 = "echo "
            r7.<init>(r1)
            java.lang.StringBuilder r7 = r7.append(r2)
            java.lang.String r1 = " > "
            java.lang.StringBuilder r7 = r7.append(r1)
            java.lang.StringBuilder r7 = r7.append(r0)
            java.lang.String r7 = r7.toString()
            r6.execRootCmdSilent(r7)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: ZtlApi.ZtlManager3399_11.setUSBtoPC(boolean):void");
    }

    @Override
    public int getGpioValue(int i) {
        getAndroidVersion().contains("3568");
        return -1;
    }

    @Override
    public List<String> getUSBDisks() {
        String str = (getAndroidVersion().contains("5.1.1") || getAndroidVersion().contains("4.4")) ? "/mnt/usb_storage/" : "/storage/";
        ArrayList arrayList = new ArrayList();
        File file = new File(str);
        try {
            if (!file.exists() || !file.isDirectory()) {
                return arrayList;
            }
            File[] fileArrListFiles = file.listFiles();
            if (fileArrListFiles == null && Build.VERSION.SDK_INT > 28) {
                ZtlManager.GetInstance();
                String[] strArrSplit = ZtlManager.execRootCmd("ls /storage/").split("\n");
                for (int i = 0; i < strArrSplit.length; i++) {
                    String str2 = "/storage/" + strArrSplit[i];
                    if (!str2.equals("/storage/emulated") && !str2.equals("/storage/self") && !str2.equals(getAppRootOfSdCardRemovable())) {
                        File file2 = new File(str2);
                        if (file2.exists() && file2.isDirectory()) {
                            if (getAndroidVersion().contains("5.1") || getAndroidVersion().contains("4.4")) {
                                arrayList.add(file2.listFiles()[i].getPath());
                            } else {
                                arrayList.add(str2);
                            }
                        }
                    }
                }
                return arrayList.size() > 0 ? Collections.singletonList(arrayList.get(0)) : arrayList;
            }
            if (fileArrListFiles.length <= 0) {
                return arrayList;
            }
            for (int i2 = 0; i2 < fileArrListFiles.length; i2++) {
                String absolutePath = fileArrListFiles[i2].getAbsolutePath();
                if (!absolutePath.equals("/storage/emulated") && !absolutePath.equals("/storage/self") && !absolutePath.equals(getAppRootOfSdCardRemovable())) {
                    File file3 = new File(absolutePath);
                    if (file3.exists() && file3.isDirectory()) {
                        if (getAndroidVersion().contains("5.1") || getAndroidVersion().contains("4.4")) {
                            arrayList.add(file3.listFiles()[i2].getPath());
                        } else {
                            arrayList.add(absolutePath);
                        }
                    }
                }
            }
            return arrayList.size() > 0 ? Collections.singletonList(arrayList.get(0)) : arrayList;
        } catch (Exception e) {
            e.printStackTrace();
            return arrayList;
        }
    }

    @Override
    public boolean getUSBtoPC() {
        try {
            if (getDeviceVersion().contains("3588")) {
                String strLoadFileAsString = loadFileAsString("/sys/kernel/debug/usb/fc000000.usb/mode");
                System.out.println("3588 to PC:" + strLoadFileAsString);
                if (strLoadFileAsString.contains("device")) {
                    return true;
                }
            } else if (getDeviceVersion().contains("3399")) {
                String strLoadFileAsString2 = loadFileAsString("/sys/devices/platform/ff770000.syscon/ff770000.syscon:usb2-phy@e450/otg_mode");
                System.out.println("3399 to PC:" + strLoadFileAsString2);
                if (strLoadFileAsString2.contains("otg") || strLoadFileAsString2.contains("peripheral")) {
                    return true;
                }
            } else if (getDeviceVersion().contains("3288")) {
                String strLoadFileAsString3 = loadFileAsString("sys/devices/platform/ff770000.syscon/ff770000.syscon:usbphy/phy/phy-ff770000.syscon:usbphy.1/otg_mode");
                System.out.println("3399 to PC:" + strLoadFileAsString3);
                if (strLoadFileAsString3.contains("otg")) {
                    return true;
                }
            } else {
                String strLoadFileAsString4 = loadFileAsString("/sys/devices/platform/fe8a0000.usb2-phy/otg_mode");
                System.out.println("3568 to PC:" + strLoadFileAsString4);
                if (strLoadFileAsString4.contains("otg")) {
                    return true;
                }
            }
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public void setDisplayOrientation(int i) {
        setSystemProperty("persist.ztl.hwrotation", Integer.toString(i));
        reboot(0);
    }

    @Override
    public void openSystemBar(boolean z) {
        if (this.mContext == null) {
            Log.e(this.TAG, "上下文为空,不执行");
            return;
        }
        Intent intent = new Intent("com.ztl.action.systembar");
        intent.setPackage("com.android.systemui");
        intent.putExtra("skip_permission", true);
        intent.putExtra("enable", z);
        this.mContext.sendBroadcast(intent);
    }

    @Override
    public boolean isSystemBarOpen() {
        return Boolean.valueOf(getSystemProperty("persist.ztl.systembar", "true")).booleanValue();
    }

    @Override
    public int setBuildSerial(String str) {
        if (str == null) {
            return 0;
        }
        setSystemProperty("persist.ztl.ztlsn", str);
        setSystemProperty("persist.sys.ztlsn", str);
        return 0;
    }
}
