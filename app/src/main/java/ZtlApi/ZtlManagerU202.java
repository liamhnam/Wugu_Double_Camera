package ZtlApi;

import android.app.usage.StorageStatsManager;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Build;
import android.os.storage.StorageManager;
import android.util.Log;
import androidx.constraintlayout.motion.widget.Key;
import com.google.android.exoplayer2.metadata.icy.IcyHeaders;
import com.p020hp.jipp.model.IdentifyAction;
import com.tom_roush.pdfbox.pdmodel.documentinterchange.taggedpdf.PDLayoutAttributeObject;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.apache.http.HttpStatus;
import org.apache.log4j.spi.Configurator;
import org.eclipse.paho.client.mqttv3.MqttTopic;

public class ZtlManagerU202 extends ZtlManager {
    private String TAG = "ZtlManagerU202";
    HashMap<String, s905d3_gpio> s905d3_gpios = new HashMap<>();

    ZtlManagerU202() {
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
    public void startScreenShot(String str, String str2) {
        ComponentName componentName = new ComponentName("com.yian.yiansettings", "com.yian.yiansettings.ZTLService");
        Intent intent = new Intent();
        intent.setComponent(componentName);
        intent.putExtra("cmd", "snapshot");
        intent.putExtra("filepath", str + MqttTopic.TOPIC_LEVEL_SEPARATOR + str2);
        this.mContext.startService(intent);
    }

    @Override
    public void setScreenMode(String str) {
        ComponentName componentName = new ComponentName("com.droidlogic.tv.settings", "com.droidlogic.tv.settings.ZTLService");
        Intent intent = new Intent();
        intent.setComponent(componentName);
        intent.putExtra("cmd", "set_hdmiresolution");
        intent.putExtra("mode", str);
        this.mContext.startService(intent);
    }

    @Override
    public int getDisplayCount() {
        String strExecRootCmd = execRootCmd("cat /sys/class/display/mode");
        String strExecRootCmd2 = execRootCmd("cat /sys/class/display2/mode");
        int i = !strExecRootCmd.equals(Configurator.NULL) ? 1 : 0;
        return !strExecRootCmd2.equals(Configurator.NULL) ? i + 1 : i;
    }

    class s905d3_gpio {
        boolean is_ao;
        String name;
        int offset;

        public s905d3_gpio(String str, boolean z, int i) {
            this.name = str;
            this.is_ao = z;
            this.offset = i;
        }

        int getValue() {
            if (this.is_ao) {
                return this.offset + 496;
            }
            return this.offset + HttpStatus.SC_GONE;
        }
    }

    int get905d3GpioValue(String str) {
        if (this.s905d3_gpios.size() <= 0) {
            this.s905d3_gpios.put("GPIOAO_0", new s905d3_gpio("GPIOAO_0", true, 0));
            this.s905d3_gpios.put("GPIOAO_1", new s905d3_gpio("GPIOAO_1", true, 1));
            this.s905d3_gpios.put("GPIOAO_2", new s905d3_gpio("GPIOAO_2", true, 2));
            this.s905d3_gpios.put("GPIOAO_3", new s905d3_gpio("GPIOAO_3", true, 3));
            this.s905d3_gpios.put("GPIOAO_4", new s905d3_gpio("GPIOAO_4", true, 4));
            this.s905d3_gpios.put("GPIOAO_5", new s905d3_gpio("GPIOAO_5", true, 5));
            this.s905d3_gpios.put("GPIOAO_6", new s905d3_gpio("GPIOAO_6", true, 6));
            this.s905d3_gpios.put("GPIOAO_7", new s905d3_gpio("GPIOAO_7", true, 7));
            this.s905d3_gpios.put("GPIOAO_8", new s905d3_gpio("GPIOAO_8", true, 8));
            this.s905d3_gpios.put("GPIOAO_9", new s905d3_gpio("GPIOAO_9", true, 9));
            this.s905d3_gpios.put("GPIOAO_10", new s905d3_gpio("GPIOAO_10", true, 10));
            this.s905d3_gpios.put("GPIOAO_11", new s905d3_gpio("GPIOAO_11", true, 11));
            this.s905d3_gpios.put("GPIOE_0", new s905d3_gpio("GPIOE_0", true, 12));
            this.s905d3_gpios.put("GPIOE_1", new s905d3_gpio("GPIOE_1", true, 13));
            this.s905d3_gpios.put("GPIOE_2", new s905d3_gpio("GPIOE_2", true, 14));
            this.s905d3_gpios.put("GPIO_TEST_N", new s905d3_gpio("GPIO_TEST_N", true, 15));
            this.s905d3_gpios.put("GPIOV_0", new s905d3_gpio("GPIOV_0", false, 0));
            this.s905d3_gpios.put("GPIOZ_0", new s905d3_gpio("GPIOZ_0", false, 1));
            this.s905d3_gpios.put("GPIOZ_1", new s905d3_gpio("GPIOZ_1", false, 2));
            this.s905d3_gpios.put("GPIOZ_2", new s905d3_gpio("GPIOZ_2", false, 3));
            this.s905d3_gpios.put("GPIOZ_3", new s905d3_gpio("GPIOZ_3", false, 4));
            this.s905d3_gpios.put("GPIOZ_4", new s905d3_gpio("GPIOZ_4", false, 5));
            this.s905d3_gpios.put("GPIOZ_5", new s905d3_gpio("GPIOZ_5", false, 6));
            this.s905d3_gpios.put("GPIOZ_6", new s905d3_gpio("GPIOZ_6", false, 7));
            this.s905d3_gpios.put("GPIOZ_7", new s905d3_gpio("GPIOZ_7", false, 8));
            this.s905d3_gpios.put("GPIOZ_8", new s905d3_gpio("GPIOZ_8", false, 9));
            this.s905d3_gpios.put("GPIOZ_9", new s905d3_gpio("GPIOZ_9", false, 10));
            this.s905d3_gpios.put("GPIOZ_10", new s905d3_gpio("GPIOZ_10", false, 11));
            this.s905d3_gpios.put("GPIOZ_11", new s905d3_gpio("GPIOZ_11", false, 12));
            this.s905d3_gpios.put("GPIOZ_12", new s905d3_gpio("GPIOZ_12", false, 13));
            this.s905d3_gpios.put("GPIOZ_13", new s905d3_gpio("GPIOZ_13", false, 14));
            this.s905d3_gpios.put("GPIOZ_14", new s905d3_gpio("GPIOZ_14", false, 15));
            this.s905d3_gpios.put("GPIOZ_15", new s905d3_gpio("GPIOZ_15", false, 16));
            this.s905d3_gpios.put("GPIOH_0", new s905d3_gpio("GPIOH_0", false, 17));
            this.s905d3_gpios.put("GPIOH_1", new s905d3_gpio("GPIOH_1", false, 18));
            this.s905d3_gpios.put("GPIOH_2", new s905d3_gpio("GPIOH_2", false, 19));
            this.s905d3_gpios.put("GPIOH_3", new s905d3_gpio("GPIOH_3", false, 20));
            this.s905d3_gpios.put("GPIOH_4", new s905d3_gpio("GPIOH_4", false, 21));
            this.s905d3_gpios.put("GPIOH_5", new s905d3_gpio("GPIOH_5", false, 22));
            this.s905d3_gpios.put("GPIOH_6", new s905d3_gpio("GPIOH_6", false, 23));
            this.s905d3_gpios.put("GPIOH_7", new s905d3_gpio("GPIOH_7", false, 24));
            this.s905d3_gpios.put("GPIOH_8", new s905d3_gpio("GPIOH_8", false, 25));
            this.s905d3_gpios.put("BOOT_0", new s905d3_gpio("BOOT_0", false, 26));
            this.s905d3_gpios.put("BOOT_1", new s905d3_gpio("BOOT_1", false, 27));
            this.s905d3_gpios.put("BOOT_2", new s905d3_gpio("BOOT_2", false, 28));
            this.s905d3_gpios.put("BOOT_3", new s905d3_gpio("BOOT_3", false, 29));
            this.s905d3_gpios.put("BOOT_4", new s905d3_gpio("BOOT_4", false, 30));
            this.s905d3_gpios.put("BOOT_5", new s905d3_gpio("BOOT_5", false, 31));
            this.s905d3_gpios.put("BOOT_6", new s905d3_gpio("BOOT_6", false, 32));
            this.s905d3_gpios.put("BOOT_7", new s905d3_gpio("BOOT_7", false, 33));
            this.s905d3_gpios.put("BOOT_8", new s905d3_gpio("BOOT_8", false, 34));
            this.s905d3_gpios.put("BOOT_9", new s905d3_gpio("BOOT_9", false, 35));
            this.s905d3_gpios.put("BOOT_10", new s905d3_gpio("BOOT_10", false, 36));
            this.s905d3_gpios.put("BOOT_11", new s905d3_gpio("BOOT_11", false, 37));
            this.s905d3_gpios.put("BOOT_12", new s905d3_gpio("BOOT_12", false, 38));
            this.s905d3_gpios.put("BOOT_13", new s905d3_gpio("BOOT_13", false, 39));
            this.s905d3_gpios.put("BOOT_14", new s905d3_gpio("BOOT_14", false, 40));
            this.s905d3_gpios.put("BOOT_15", new s905d3_gpio("BOOT_15", false, 41));
            this.s905d3_gpios.put("GPIOC_0", new s905d3_gpio("GPIOC_0", false, 42));
            this.s905d3_gpios.put("GPIOC_1", new s905d3_gpio("GPIOC_1", false, 43));
            this.s905d3_gpios.put("GPIOC_2", new s905d3_gpio("GPIOC_2", false, 44));
            this.s905d3_gpios.put("GPIOC_3", new s905d3_gpio("GPIOC_3", false, 45));
            this.s905d3_gpios.put("GPIOC_4", new s905d3_gpio("GPIOC_4", false, 46));
            this.s905d3_gpios.put("GPIOC_5", new s905d3_gpio("GPIOC_5", false, 47));
            this.s905d3_gpios.put("GPIOC_6", new s905d3_gpio("GPIOC_6", false, 48));
            this.s905d3_gpios.put("GPIOC_7", new s905d3_gpio("GPIOC_7", false, 49));
            this.s905d3_gpios.put("GPIOA_0", new s905d3_gpio("GPIOA_0", false, 50));
            this.s905d3_gpios.put("GPIOA_1", new s905d3_gpio("GPIOA_1", false, 51));
            this.s905d3_gpios.put("GPIOA_2", new s905d3_gpio("GPIOA_2", false, 52));
            this.s905d3_gpios.put("GPIOA_3", new s905d3_gpio("GPIOA_3", false, 53));
            this.s905d3_gpios.put("GPIOA_4", new s905d3_gpio("GPIOA_4", false, 54));
            this.s905d3_gpios.put("GPIOA_5", new s905d3_gpio("GPIOA_5", false, 55));
            this.s905d3_gpios.put("GPIOA_6", new s905d3_gpio("GPIOA_6", false, 56));
            this.s905d3_gpios.put("GPIOA_7", new s905d3_gpio("GPIOA_7", false, 57));
            this.s905d3_gpios.put("GPIOA_8", new s905d3_gpio("GPIOA_8", false, 58));
            this.s905d3_gpios.put("GPIOA_9", new s905d3_gpio("GPIOA_9", false, 59));
            this.s905d3_gpios.put("GPIOA_10", new s905d3_gpio("GPIOA_10", false, 60));
            this.s905d3_gpios.put("GPIOA_11", new s905d3_gpio("GPIOA_11", false, 61));
            this.s905d3_gpios.put("GPIOA_12", new s905d3_gpio("GPIOA_12", false, 62));
            this.s905d3_gpios.put("GPIOA_13", new s905d3_gpio("GPIOA_13", false, 63));
            this.s905d3_gpios.put("GPIOA_14", new s905d3_gpio("GPIOA_14", false, 64));
            this.s905d3_gpios.put("GPIOA_15", new s905d3_gpio("GPIOA_15", false, 65));
            this.s905d3_gpios.put("GPIOX_0", new s905d3_gpio("GPIOX_0", false, 66));
            this.s905d3_gpios.put("GPIOX_1", new s905d3_gpio("GPIOX_1", false, 67));
            this.s905d3_gpios.put("GPIOX_2", new s905d3_gpio("GPIOX_2", false, 68));
            this.s905d3_gpios.put("GPIOX_3", new s905d3_gpio("GPIOX_3", false, 69));
            this.s905d3_gpios.put("GPIOX_4", new s905d3_gpio("GPIOX_4", false, 70));
            this.s905d3_gpios.put("GPIOX_5", new s905d3_gpio("GPIOX_5", false, 71));
            this.s905d3_gpios.put("GPIOX_6", new s905d3_gpio("GPIOX_6", false, 72));
            this.s905d3_gpios.put("GPIOX_7", new s905d3_gpio("GPIOX_7", false, 73));
            this.s905d3_gpios.put("GPIOX_8", new s905d3_gpio("GPIOX_8", false, 74));
            this.s905d3_gpios.put("GPIOX_9", new s905d3_gpio("GPIOX_9", false, 75));
            this.s905d3_gpios.put("GPIOX_10", new s905d3_gpio("GPIOX_10", false, 76));
            this.s905d3_gpios.put("GPIOX_11", new s905d3_gpio("GPIOX_11", false, 77));
            this.s905d3_gpios.put("GPIOX_12", new s905d3_gpio("GPIOX_12", false, 78));
            this.s905d3_gpios.put("GPIOX_13", new s905d3_gpio("GPIOX_13", false, 79));
            this.s905d3_gpios.put("GPIOX_14", new s905d3_gpio("GPIOX_14", false, 80));
            this.s905d3_gpios.put("GPIOX_15", new s905d3_gpio("GPIOX_15", false, 81));
            this.s905d3_gpios.put("GPIOX_16", new s905d3_gpio("GPIOX_16", false, 82));
            this.s905d3_gpios.put("GPIOX_17", new s905d3_gpio("GPIOX_17", false, 83));
            this.s905d3_gpios.put("GPIOX_18", new s905d3_gpio("GPIOX_18", false, 84));
            this.s905d3_gpios.put("GPIOX_19", new s905d3_gpio("GPIOX_19", false, 85));
        }
        if (this.s905d3_gpios.containsKey(str)) {
            return this.s905d3_gpios.get(str).getValue();
        }
        return 0;
    }

    @Override
    public int gpioStringToInt(String str) {
        return get905d3GpioValue(str);
    }

    protected static class rotaionString implements Comparable {
        public String res;
        public String number = "";
        public String tary = "";

        @Override
        public int compareTo(Object obj) {
            return -1;
        }

        public rotaionString(String str) {
            this.res = str;
            if (str != null) {
                handled();
            }
        }

        protected void handled() {
            for (int i = 0; i < this.res.length(); i++) {
                if (this.res.charAt(i) <= '9' && this.res.charAt(i) >= '0') {
                    this.number += this.res.charAt(i);
                } else {
                    this.tary += this.res.charAt(i);
                }
            }
        }
    }

    @Override
    public String[] getHDMIResolutions() {
        new ArrayList();
        return new String[]{"1080p60hz", "1080p50hz", "1080p30hz", "1440x900p60hz", "1366x768p60hz", "720p60hz", "1024x600p60hz"};
    }

    @Override
    public void setHDMIResolution(String str) {
        ZtlManager.GetInstance().execRootCmdSilent("setbootenv ubootenv.var.is.bestmode false");
        ZtlManager.GetInstance().execRootCmdSilent("setbootenv ubootenv.var.hdmimode " + str);
        ZtlManager.GetInstance().execRootCmdSilent("setbootenv ubootenv.var.outputmode " + str);
        if (ZtlManager.GetInstance().getSystemProperty("ro.ztl.board.type", "").contains("mbox")) {
            if (str.contains("1080p")) {
                ZtlManager.GetInstance().execRootCmdSilent("setbootenv ubootenv.var.resolutionmain 1920x1080");
                ZtlManager.GetInstance().execRootCmdSilent("setbootenv ubootenv.var.fb_width 1920");
                ZtlManager.GetInstance().execRootCmdSilent("setbootenv ubootenv.var.fb_height 1080");
                return;
            }
            if (str.contains("1440x900p")) {
                ZtlManager.GetInstance().execRootCmdSilent("setbootenv ubootenv.var.resolutionmain 1440x900");
                ZtlManager.GetInstance().execRootCmdSilent("setbootenv ubootenv.var.fb_width 1440");
                ZtlManager.GetInstance().execRootCmdSilent("setbootenv ubootenv.var.fb_height 900");
                return;
            }
            if (str.contains("1366x768p")) {
                ZtlManager.GetInstance().execRootCmdSilent("setbootenv ubootenv.var.resolutionmain 1366x768");
                ZtlManager.GetInstance().execRootCmdSilent("setbootenv ubootenv.var.fb_width 1366");
                ZtlManager.GetInstance().execRootCmdSilent("setbootenv ubootenv.var.fb_height 768");
            } else if (str.contains("720p")) {
                ZtlManager.GetInstance().execRootCmdSilent("setbootenv ubootenv.var.resolutionmain 1280x720");
                ZtlManager.GetInstance().execRootCmdSilent("setbootenv ubootenv.var.fb_width 1280");
                ZtlManager.GetInstance().execRootCmdSilent("setbootenv ubootenv.var.fb_height 720");
            } else if (str.contains("1024x600p")) {
                ZtlManager.GetInstance().execRootCmdSilent("setbootenv ubootenv.var.resolutionmain 1024x600");
                ZtlManager.GetInstance().execRootCmdSilent("setbootenv ubootenv.var.fb_width 1024");
                ZtlManager.GetInstance().execRootCmdSilent("setbootenv ubootenv.var.fb_height 600");
            }
        }
    }

    @Override
    public String getDisplayMode() {
        try {
            return loadFileAsString("sys/class/display/mode");
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public int setBuildSerial(String str) {
        execRootCmdSilent("echo usid > /sys/class/unifykeys/name");
        execRootCmdSilent("echo \"" + str + "\" > /sys/class/unifykeys/write");
        return 0;
    }

    @Override
    public void openUsbDebug(boolean z) {
        if (this.mContext == null) {
            Log.e("上下文为空，不执行", "请检查是否已调用setContext()");
            return;
        }
        String str = z ? IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE : PDLayoutAttributeObject.GLYPH_ORIENTATION_VERTICAL_ZERO_DEGREES;
        Intent intent = new Intent("com.ding.adbsetting");
        intent.putExtra("enable", str);
        intent.setPackage("com.yian.yiansettings");
        intent.putExtra("skip_permission", true);
        this.mContext.sendBroadcast(intent);
    }

    @Override
    public void setUSBtoPC(boolean z) {
        String str = z ? IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE : PDLayoutAttributeObject.GLYPH_ORIENTATION_VERTICAL_ZERO_DEGREES;
        setSystemProperty("persist.usb.mode", str);
        writeMethod("/sys/devices/platform/ffe09080.usb3phy/ztl_usb_ctrl/ztl_usb_ctrl", str);
        ComponentName componentName = new ComponentName("com.droidlogic.tv.settings", "com.droidlogic.tv.settings.ZTLService");
        Intent intent = new Intent();
        intent.setComponent(componentName);
        intent.putExtra("cmd", "set_otg");
        intent.putExtra("isotg", z);
        this.mContext.startService(intent);
    }

    @Override
    public boolean getUSBtoPC() {
        try {
            return loadFileAsString("/sys/devices/platform/ffe09080.usb3phy/ztl_usb_ctrl/ztl_usb_ctrl").contains(IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean isDebugSerialEnable() {
        String systemProperty = getSystemProperty("ro.ztl.debugSerialState", "-1");
        if (systemProperty.equals(IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE)) {
            return true;
        }
        if (systemProperty.equals(PDLayoutAttributeObject.GLYPH_ORIENTATION_VERTICAL_ZERO_DEGREES)) {
            return false;
        }
        Log.e(this.TAG, "系统不支持，请更新");
        return true;
    }

    @Override
    public void enableDebugSerial(boolean z, boolean z2) {
        ComponentName componentName = new ComponentName("com.droidlogic.tv.settings", "com.droidlogic.tv.settings.ZTLService");
        Intent intent = new Intent();
        intent.setComponent(componentName);
        intent.setPackage("com.droidlogic.tv.settings");
        intent.putExtra("cmd", "setDebugSerial");
        intent.putExtra("enable", z);
        this.mContext.startService(intent);
        if (z2) {
            reboot(0);
        }
    }

    @Override
    public void setDisplayOrientation(int i) {
        if (i == getDisplayOrientation()) {
            Log.e("当前方向", "与旋转方向一致，不执行");
        } else {
            setPrimaryDisplayOrientation(i);
            reboot(1);
        }
    }

    @Override
    public int setDisplayOrientation(int i, int i2) {
        if (i == 0) {
            setPrimaryDisplayOrientation(i2);
            reboot(1);
            return 0;
        }
        if (i == 1) {
            setExtendDisplayOrientation(i2);
            reboot(1);
            return 0;
        }
        if (i != -1) {
            return 0;
        }
        setPrimaryDisplayOrientation(i2);
        setExtendDisplayOrientation(i2);
        reboot(1);
        return 0;
    }

    @Override
    public void setPrimaryDisplayOrientation(int i) {
        ComponentName componentName = new ComponentName("com.droidlogic.tv.settings", "com.droidlogic.tv.settings.ZTLService");
        if (i == getDisplayOrientation(0)) {
            Log.e("当前方向", "与旋转方向一致，不执行");
            return;
        }
        Intent intent = new Intent();
        intent.setComponent(componentName);
        intent.putExtra("cmd", "set_display_rotation");
        intent.putExtra(Key.ROTATION, i);
        intent.putExtra(IdentifyAction.display, "primary");
        this.mContext.startService(intent);
    }

    @Override
    public List<String> getUSBDisks() {
        String str = (getAndroidVersion().contains("5.1.1") || getAndroidVersion().contains("4.4")) ? "/mnt/usb_storage/" : "/storage/";
        ArrayList arrayList = new ArrayList();
        File file = new File(str);
        try {
            if (file.exists() && file.isDirectory()) {
                File[] fileArrListFiles = file.listFiles();
                if (fileArrListFiles.length > 0) {
                    for (int i = 0; i < fileArrListFiles.length; i++) {
                        String absolutePath = fileArrListFiles[i].getAbsolutePath();
                        if (!absolutePath.equals("/storage/emulated") && !absolutePath.equals("/storage/self") && !absolutePath.equals(getAppRootOfSdCardRemovable())) {
                            File file2 = new File(absolutePath);
                            if (file2.exists() && file2.isDirectory()) {
                                if (getAndroidVersion().contains("5.1") || getAndroidVersion().contains("4.4")) {
                                    arrayList.add(file2.listFiles()[i].getPath());
                                } else {
                                    arrayList.add(absolutePath);
                                }
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return arrayList;
    }

    @Override
    public void setExtendDisplayOrientation(int i) {
        ComponentName componentName = new ComponentName("com.droidlogic.tv.settings", "com.droidlogic.tv.settings.ZTLService");
        if (i == getDisplayOrientation(1)) {
            Log.e("当前方向", "与旋转方向一致，不执行");
            return;
        }
        Intent intent = new Intent();
        intent.setComponent(componentName);
        intent.putExtra("cmd", "set_display_rotation");
        intent.putExtra(Key.ROTATION, i);
        intent.putExtra(IdentifyAction.display, "extend");
        this.mContext.startService(intent);
    }

    @Override
    public void setCloseSystemBar() {
        openSystemBar(false);
    }

    @Override
    public void openSystemBar(boolean z) {
        if (this.mContext == null) {
            Log.e(this.TAG, "上下文为空,不执行");
            return;
        }
        Intent intent = new Intent("com.ztl.action.systembar");
        intent.putExtra("enable", z);
        intent.putExtra("skip_permission", true);
        this.mContext.sendBroadcast(intent);
    }

    @Override
    public boolean isSystemBarOpen() {
        return Boolean.valueOf(getSystemProperty("persist.ztl.systembar", "true")).booleanValue();
    }
}
