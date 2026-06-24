package ZtlApi;

import android.content.Intent;
import android.media.AudioManager;
import android.os.SystemProperties;
import android.util.Log;
import com.google.android.exoplayer2.extractor.p018ts.TsExtractor;
import com.google.android.exoplayer2.metadata.icy.IcyHeaders;
import com.google.android.exoplayer2.util.MimeTypes;
import com.tom_roush.fontbox.ttf.NamingTable;
import com.tom_roush.pdfbox.pdmodel.documentinterchange.taggedpdf.PDLayoutAttributeObject;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

public class ZtlManagerA33_A64 extends ZtlManager {
    private boolean DEBUG_ZTL;
    private String TAG = "ZtlManagerA33_A64";
    Map<String, Integer> gpios = new HashMap();

    @Override
    public String[] getCPUFreq() {
        return null;
    }

    public ZtlManagerA33_A64() {
        this.DEBUG_ZTL = false;
        init_gpiomap();
        this.DEBUG_ZTL = SystemProperties.get("persist.sys.ztl.debug", "false").equals("true");
    }

    @Override
    void LOGD(String str) {
        if (this.DEBUG_ZTL) {
            Log.d(this.TAG, str);
        }
    }

    @Override
    public String getUsbStoragePath() {
        try {
            return getSystemProperty("persist.sys.usbDisk", "unKnown");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public String getDeviceID() {
        File file = new File("/sys/class/android_usb/android0/iSerial");
        if (!file.exists()) {
            return null;
        }
        try {
            return new BufferedReader(new FileReader(file)).readLine();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public int setRaiseSystemVolume() {
        if (this.mContext == null) {
            Log.e("上下文为空，不执行", "请检查是否已调用setContext()");
            return -1;
        }
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
        if (this.mContext == null) {
            Log.e("上下文为空，不执行", "请检查是否已调用setContext()");
            return -1;
        }
        try {
            ((AudioManager) this.mContext.getSystemService(MimeTypes.BASE_TYPE_AUDIO)).adjustStreamVolume(3, -1, 4);
            return 0;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public void setBootPackageActivity(String str, String str2) {
        if (str != null && str2 != null) {
            execRootCmdSilent("setprop persist.sys.bootPkgName " + str);
            execRootCmdSilent("setprop persist.sys.bootPkgActivity " + str2);
        } else {
            Log.e(this.TAG, "pkgName (" + str + ") or pkgActivity (" + str2 + ") err");
        }
    }

    public int setAppKey(String str) {
        if (str != null) {
            Intent intent = new Intent("com.ztl.key");
            intent.putExtra("enable", str);
            this.mContext.sendBroadcast(intent);
            return 0;
        }
        Log.e(this.TAG, "设置APP加密密钥值错误");
        return -1;
    }

    @Override
    public int getDisplayOrientation() {
        Log.e(this.TAG, "等待后续系统开发此功能");
        return 0;
    }

    @Override
    public void setDisplayOrientation(int i) {
        if (this.mContext == null) {
            Log.e("上下文为空，不执行", "请检查是否已调用setContext()");
            return;
        }
        Intent intent = new Intent("com.ztl.rotation");
        intent.putExtra("enable", i);
        this.mContext.sendBroadcast(intent);
    }

    @Override
    public void openSystemBar(boolean z) {
        if (this.mContext == null) {
            Log.e("上下文为空，不执行", "请检查是否已调用setContext()");
            return;
        }
        String str = z ? PDLayoutAttributeObject.GLYPH_ORIENTATION_VERTICAL_ZERO_DEGREES : IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE;
        Intent intent = new Intent("com.ztl.systembar");
        intent.putExtra("enable", str);
        this.mContext.sendBroadcast(intent);
    }

    @Override
    public void setOpenSystemBar() {
        if (this.mContext == null) {
            Log.e("上下文为空，不执行", "请检查是否已调用setContext()");
            return;
        }
        Intent intent = new Intent("com.ztl.systembar");
        intent.putExtra("enable", IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE);
        this.mContext.sendBroadcast(intent);
    }

    @Override
    public void setCloseSystemBar() {
        if (this.mContext == null) {
            Log.e("上下文为空，不执行", "请检查是否已调用setContext()");
            return;
        }
        Intent intent = new Intent("com.ztl.systembar");
        intent.putExtra("enable", PDLayoutAttributeObject.GLYPH_ORIENTATION_VERTICAL_ZERO_DEGREES);
        this.mContext.sendBroadcast(intent);
    }

    @Override
    public void setScreenMode(String str) {
        if (this.mContext == null) {
            Log.e("上下文为空，不执行", "请检查是否已调用setContext()");
            return;
        }
        Intent intent = new Intent("com.ztl.vga");
        intent.putExtra("enable", str);
        this.mContext.sendBroadcast(intent);
    }

    @Override
    public int getUsbDebugState() {
        Log.e(this.TAG, "等待后续系统开发此功能");
        return 0;
    }

    @Override
    public int getSystemBarState() {
        Log.e(this.TAG, "等待后续系统开发此功能");
        return 0;
    }

    @Override
    public void setGpioValue(String str, int i) {
        if (!str.contains("PE")) {
            Log.e(this.TAG, "传入参数错误,请传入PE1之类的，实际以规格书为准");
            return;
        }
        Gpio gpio = new Gpio();
        if (gpio.open(str)) {
            gpio.setValue("out", i);
        }
    }

    @Override
    public int getGpioValue(int i) {
        if (!getAndroidVersion().contains("A64") || !getAndroidVersion().contains("A33")) {
            return -1;
        }
        Gpio gpio = new Gpio();
        gpio.open(new String[]{"", "PE7", "PE4", "PE3", "PE2", "PE1"}[i]);
        return gpio.getValue();
    }

    @Override
    public int getGpioValue(String str) {
        if (!str.contains("PE")) {
            Log.e(this.TAG, "传入参数错误,请传入PE1之类的，实际以规格书为准");
            return -1;
        }
        Gpio gpio = new Gpio();
        if (gpio.open(str)) {
            return gpio.getValue();
        }
        return -1;
    }

    @Override
    public void setGpioDirection(String str, String str2) {
        if (!str.contains("PE")) {
            Log.e(this.TAG, "传入参数错误,请传入PE1之类的，实际以规格书为准");
            return;
        }
        Gpio gpio = new Gpio();
        if (gpio.open(str)) {
            gpio.setDirection(str2);
        }
    }

    @Override
    public String getGpioDirection(String str) {
        if (!str.contains("PE")) {
            Log.e(this.TAG, "传入参数错误,请传入PE1之类的，实际以规格书为准");
            return null;
        }
        Gpio gpio = new Gpio();
        if (gpio.open(str)) {
            return gpio.getDirection();
        }
        return null;
    }

    void init_gpiomap() {
        this.gpios.put("PE1", 129);
        this.gpios.put("PE2", 130);
        this.gpios.put("PE3", 131);
        this.gpios.put("PE4", 132);
        this.gpios.put("PE7", Integer.valueOf(TsExtractor.TS_STREAM_TYPE_E_AC3));
    }

    @Override
    public int gpioStringToInt(String str) {
        if (!this.gpios.containsKey(str)) {
            return -1;
        }
        if (this.gpios.get(str) == null) {
            Log.e("gpio", NamingTable.TAG + str + "缺乏映射，请联系管理员添加");
        }
        return this.gpios.get(str).intValue();
    }
}
