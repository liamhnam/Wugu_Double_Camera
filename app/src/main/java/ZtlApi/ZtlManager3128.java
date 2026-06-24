package ZtlApi;

import android.content.Intent;
import android.media.AudioManager;
import android.os.SystemProperties;
import android.util.Log;
import com.google.android.exoplayer2.metadata.icy.IcyHeaders;
import com.google.android.exoplayer2.util.MimeTypes;
import com.proembed.service.Constant;
import com.tom_roush.pdfbox.pdmodel.documentinterchange.taggedpdf.PDLayoutAttributeObject;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import org.eclipse.paho.client.mqttv3.MqttTopic;

public class ZtlManager3128 extends ZtlManager {
    static final String SYSTEM_BAR_HIDE = "hide";
    static final String SYSTEM_BAR_SHOW = "show";
    static final String SYSTEM_BAR_STATE = "persist.sys.systemBar";
    private static final String SYS_NODE_VGA_MODE = "/sys/devices/platform/display-subsystem/drm/card0/card0-VGA-1/mode";
    private static final String SYS_NODE_VGA_MODES = "/sys/devices/platform/display-subsystem/drm/card0/card0-VGA-1/modes";
    private String TAG = "Arctan";

    private List<String> readStrListFromFile(String str) throws IOException {
        ArrayList arrayList = new ArrayList();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(new File(str))));
        while (true) {
            String line = bufferedReader.readLine();
            if (line != null) {
                arrayList.add(line);
            } else {
                Log.d(this.TAG, "readStrListFromFile - " + arrayList.toString());
                return arrayList;
            }
        }
    }

    private String readStrFromFile(String str) throws IOException {
        Log.d(this.TAG, "readStrFromFile - " + str);
        return new BufferedReader(new InputStreamReader(new FileInputStream(new File(str)))).readLine();
    }

    public void LwlTest(int i) {
        Log.d(this.TAG, "22LLLLL ----> " + i);
        try {
            readStrListFromFile(SYS_NODE_VGA_MODES);
            readStrFromFile(SYS_NODE_VGA_MODE);
            Log.d(this.TAG, getDisplayMode());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    ZtlManager3128() {
        this.DEBUG_ZTL = getSystemProperty("persist.sys.ztl.debug", "false").equals("true");
    }

    @Override
    public void setSystemDate(int i, int i2, int i3) {
        if (this.mContext == null) {
            Log.e("上下文为空，不执行", "请检查是否已调用setContext()");
            return;
        }
        LOGD("set system Date " + i + MqttTopic.TOPIC_LEVEL_SEPARATOR + i2 + MqttTopic.TOPIC_LEVEL_SEPARATOR + i3);
        Intent intent = new Intent("com.ztl.action.setdate");
        intent.putExtra(Constant.POWER_ON_YEAR, i);
        intent.putExtra(Constant.POWER_ON_Month, i2);
        intent.putExtra(Constant.POWER_ON_DAY, i3);
        this.mContext.sendBroadcast(intent);
    }

    @Override
    public int setBuildSerial(String str) {
        if (str != null) {
            setSystemProperty("persist.sys.ztlsn", str);
        }
        execRootCmd("ztlenv sn " + str);
        return 0;
    }

    @Override
    public void setAutoDateTime(boolean z) {
        if (this.mContext == null) {
            Log.e("上下文为空，不执行", "请检查是否已调用setContext()");
            return;
        }
        Intent intent = new Intent("com.ztl.action.autodatetime");
        intent.putExtra("checked", z ? 1 : 0);
        this.mContext.sendBroadcast(intent);
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
        if (z) {
            execRootCmdSilent("ztlenv od");
        } else {
            execRootCmdSilent("ztlenv cd");
        }
        if (z2) {
            reboot(0);
        }
    }

    @Override
    public int getGpioValue(int i) {
        getAndroidVersion().contains("3128");
        return -1;
    }

    @Override
    public int setAutoDateTime(int i) {
        if (this.mContext == null) {
            Log.e("上下文为空，不执行", "请检查是否已调用setContext()");
            return -1;
        }
        Intent intent = new Intent("com.ztl.action.autodatetime");
        intent.putExtra("checked", i);
        this.mContext.sendBroadcast(intent);
        return 0;
    }

    @Override
    public int setRaiseSystemVolume() {
        try {
            ((AudioManager) this.mContext.getSystemService(MimeTypes.BASE_TYPE_AUDIO)).adjustStreamVolume(3, 1, 4);
            return 0;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public int setLowerSystemVolume() {
        try {
            ((AudioManager) this.mContext.getSystemService(MimeTypes.BASE_TYPE_AUDIO)).adjustStreamVolume(3, -1, 4);
            return 0;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public int setSystemBrightness(int i) {
        if (this.mContext == null) {
            Log.e("上下文为空，不执行", "请检查是否已调用setContext()");
            return -1;
        }
        Log.d("Arctan", "ztl enter set ");
        try {
            if (i >= 0 && i <= 255) {
                try {
                    Log.d("Arctan", "before send brodcast");
                    Intent intent = new Intent("ZTL.ACTION.SET.SYSTEMBRIGHTNESS");
                    intent.putExtra("ztl_brightness", i);
                    this.mContext.sendBroadcast(intent);
                    return 0;
                } catch (Exception e) {
                    e.printStackTrace();
                    return 0;
                }
            }
            LOGD("brightness index 0~255 , please check it");
            return -1;
        } catch (Exception e2) {
            e2.printStackTrace();
            return -1;
        }
    }

    @Override
    public int getDisplayOrientation() {
        return Integer.valueOf(getSystemProperty("persist.ztl.hwrotation", PDLayoutAttributeObject.GLYPH_ORIENTATION_VERTICAL_ZERO_DEGREES)).intValue();
    }

    @Override
    public int getDisplayOrientation(int i) {
        if (i < 0) {
            return -1;
        }
        if (i == 0) {
            return Integer.valueOf(getSystemProperty("persist.ztl.hwrotation", PDLayoutAttributeObject.GLYPH_ORIENTATION_VERTICAL_ZERO_DEGREES)).intValue();
        }
        String str = "persist.ztl.extend.rotation";
        if (i > 1) {
            str = "persist.ztl.extend.rotation" + (i - 1);
        }
        System.out.println("extend:" + str);
        try {
            return Integer.parseInt(ZtlManager.GetInstance().getSystemProperty(str, "-1"));
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return -1;
        }
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
        if (str.equals(SYSTEM_BAR_SHOW)) {
            return true;
        }
        str.equals(SYSTEM_BAR_HIDE);
        return false;
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
    public String[] getScreenModes() {
        return getSystemProperty("persist.sys.displaymdoes", "").split(",");
    }

    @Override
    public String getHDMIState() {
        return execRootCmd("cat /sys/devices/virtual/switch/hdmi/state");
    }

    @Override
    public void setHDMIEnable(boolean z) {
        if (z) {
            execRootCmdSilent("echo 1 > /sys/devices/virtual/display/HDMI/enable");
        } else {
            execRootCmdSilent("echo 0 > /sys/devices/virtual/display/HDMI/enable");
        }
    }

    @Override
    public void setScreenMode(String str) {
        if (this.mContext == null) {
            Log.e("上下文为空，不执行", "请检查是否已调用setContext()");
            return;
        }
        Intent intent = new Intent("android.ztl.action.SET_SCREEN_MODE");
        intent.putExtra("mode", str);
        this.mContext.sendBroadcast(intent);
    }
}
