package ZtlApi;

import android.app.usage.StorageStatsManager;
import android.content.Intent;
import android.os.Build;
import android.os.storage.StorageManager;
import android.util.Log;
import androidx.exifinterface.media.ExifInterface;
import com.google.android.exoplayer2.metadata.icy.IcyHeaders;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ZtlManager3576 extends ZtlManager {
    private String TAG = "ZtlManager3576";

    @Override
    public void setScreenMode(String str) {
    }

    ZtlManager3576() {
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
    public void setUSBtoPC(boolean z) {
        if (this.mContext == null) {
            Log.e(this.TAG, "上下文为空,不执行");
            return;
        }
        String str = z ? IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE : ExifInterface.GPS_MEASUREMENT_2D;
        setSystemProperty("persist.usb.mode", str);
        writeMethod("/sys/kernel/debug/usb/23000000.usb/mode", str);
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
            String strLoadFileAsString = loadFileAsString("/sys/kernel/debug/usb/23000000.usb/mode");
            System.out.println("3576 to PC:" + strLoadFileAsString);
            if (strLoadFileAsString.contains("device")) {
                return true;
            }
            return strLoadFileAsString.contains("otg");
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
