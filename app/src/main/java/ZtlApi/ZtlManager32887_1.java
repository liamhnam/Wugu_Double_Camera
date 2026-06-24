package ZtlApi;

import android.content.Intent;
import android.os.SystemProperties;
import android.util.Log;
import com.google.android.exoplayer2.metadata.icy.IcyHeaders;
import com.tom_roush.pdfbox.pdmodel.documentinterchange.taggedpdf.PDLayoutAttributeObject;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ZtlManager32887_1 extends ZtlManager {
    static final String SYSTEM_BAR_HIDE = "hide";
    static final String SYSTEM_BAR_SHOW = "show";
    static final String SYSTEM_BAR_STATE = "persist.sys.systemBar";
    private static final String SYS_NODE_VGA_MODE = "/sys/devices/platform/display-subsystem/drm/card0/card0-VGA-1/mode";
    private static final String SYS_NODE_VGA_MODES = "/sys/devices/platform/display-subsystem/drm/card0/card0-VGA-1/modes";
    Map<String, Integer> gpios = new HashMap();

    ZtlManager32887_1() {
        init_gpiomap();
    }

    private List<String> readStrListFromFile(String str) throws IOException {
        ArrayList arrayList = new ArrayList();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(new File(str))));
        while (true) {
            String line = bufferedReader.readLine();
            if (line != null) {
                arrayList.add(line);
            } else {
                Log.d(ZtlManager.TAG, "readStrListFromFile - " + arrayList.toString());
                return arrayList;
            }
        }
    }

    private String readStrFromFile(String str) throws IOException {
        Log.d(ZtlManager.TAG, "readStrFromFile - " + str);
        return new BufferedReader(new InputStreamReader(new FileInputStream(new File(str)))).readLine();
    }

    public void LwlTest(int i) {
        Log.d(ZtlManager.TAG, "LLLLL ----> " + i);
        try {
            readStrListFromFile(SYS_NODE_VGA_MODES);
            readStrFromFile(SYS_NODE_VGA_MODE);
            Log.d(ZtlManager.TAG, getDisplayMode());
        } catch (Exception e) {
            e.printStackTrace();
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
    public int getGpioValue(int i) {
        if (!getAndroidVersion().contains("3288") || !getAndroidVersion().contains("7.1")) {
            return -1;
        }
        Gpio gpio = new Gpio();
        gpio.open(new String[]{"", "GPIO0_C2", "GPIO7_B5", "GPIO8_B0", "GPIO7_B4", "GPIO7_C5", "GPIO7_B3", "GPIO8_A2", "GPIO7_A6", "GPIO8_A1", "GPIO7_A5"}[i]);
        return gpio.getValue();
    }

    @Override
    public int getDisplayOrientation() {
        return Integer.valueOf(getSystemProperty("persist.sys.orientation", PDLayoutAttributeObject.GLYPH_ORIENTATION_VERTICAL_ZERO_DEGREES)).intValue();
    }

    @Override
    public void openSystemBar(boolean z) {
        if (this.mContext == null) {
            Log.e("上下文为空，不执行", "请检查是否已调用setContext()");
        } else if (z) {
            this.mContext.sendBroadcast(new Intent("ZTL.ACTION.OPEN.SYSTEMBAR"));
        } else {
            this.mContext.sendBroadcast(new Intent("ZTL.ACTION.CLOSE.SYSTEMBAR"));
        }
    }

    @Override
    public void setOpenSystemBar() {
        if (this.mContext == null) {
            Log.e("上下文为空，不执行", "请检查是否已调用setContext()");
        } else {
            this.mContext.sendBroadcast(new Intent("ZTL.ACTION.OPEN.SYSTEMBAR"));
        }
    }

    @Override
    public void setCloseSystemBar() {
        if (this.mContext == null) {
            Log.e("上下文为空，不执行", "请检查是否已调用setContext()");
        } else {
            this.mContext.sendBroadcast(new Intent("ZTL.ACTION.CLOSE.SYSTEMBAR"));
        }
    }

    @Override
    public boolean isSystemBarOpen() {
        String str = SystemProperties.get(SYSTEM_BAR_STATE);
        return str.equals(SYSTEM_BAR_SHOW) || !str.equals(SYSTEM_BAR_HIDE);
    }

    @Override
    public int getSystemBarState() {
        String str = SystemProperties.get(SYSTEM_BAR_STATE);
        if (str.equals(SYSTEM_BAR_SHOW)) {
            return 1;
        }
        return str.equals(SYSTEM_BAR_HIDE) ? 0 : -1;
    }

    @Override
    public void setBlackLight(boolean z) {
        ZtlManager.GetInstance().execRootCmdSilent(String.format("echo %s > /proc/bl_root/bl_entry", z ? IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE : PDLayoutAttributeObject.GLYPH_ORIENTATION_VERTICAL_ZERO_DEGREES));
    }

    @Override
    public void setScreenMode(String str) {
        setSystemProperty("persist.sys.screenmode", str);
        setSystemProperty("ztl.Screen", "Set");
        setDisplayResolution(str);
    }

    @Override
    public String getHDMIState() {
        return execRootCmd("cat /sys/class/drm/card0-HDMI-A-1/status");
    }

    @Override
    public void setHDMIEnable(boolean z) {
        if (z) {
            execRootCmdSilent("echo on > /sys/class/drm/card0-HDMI-A-1/status");
        } else {
            execRootCmdSilent("echo off > /sys/class/drm/card0-HDMI-A-1/status");
        }
    }

    void init_gpiomap() {
        this.gpios.put("GPIO0_A0", 0);
        this.gpios.put("GPIO0_A1", 1);
        this.gpios.put("GPIO0_A2", 2);
        this.gpios.put("GPIO0_A3", 3);
        this.gpios.put("GPIO0_A4", 4);
        this.gpios.put("GPIO0_A5", 5);
        this.gpios.put("GPIO0_A6", 6);
        this.gpios.put("GPIO0_A7", 7);
        this.gpios.put("GPIO0_B0", 8);
        this.gpios.put("GPIO0_B1", 9);
        this.gpios.put("GPIO0_B2", 10);
        this.gpios.put("GPIO0_B3", 11);
        this.gpios.put("GPIO0_B4", 12);
        this.gpios.put("GPIO0_B5", 13);
        this.gpios.put("GPIO0_B6", 14);
        this.gpios.put("GPIO0_B7", 15);
        this.gpios.put("GPIO0_C0", 16);
        this.gpios.put("GPIO0_C1", 17);
        this.gpios.put("GPIO0_C2", 18);
        this.gpios.put("GPIO0_C3", 19);
        this.gpios.put("GPIO0_C4", 20);
        this.gpios.put("GPIO0_C5", 21);
        this.gpios.put("GPIO0_C6", 22);
        this.gpios.put("GPIO0_C7", 23);
        this.gpios.put("GPIO0_D0", 24);
    }

    @Override
    public int gpioStringToInt(String str) {
        if (this.gpios.get(str) != null) {
            return super.gpioStringToInt(str);
        }
        return super.gpioStringToInt(str) - 8;
    }

    @Override
    public void setGPUMode(String str) {
        execRootCmdSilent(String.format("echo " + str + " >/sys/bus/platform/devices/ffa30000.gpu/devfreq/ffa30000.gpu/governor", new Object[0]));
    }

    @Override
    public int getCPUTemp() {
        String onelinevalue = getOnelinevalue("/sys/class/thermal/thermal_zone0/temp");
        if (onelinevalue != null) {
            return Integer.valueOf(onelinevalue).intValue() / 1000;
        }
        return -1;
    }
}
