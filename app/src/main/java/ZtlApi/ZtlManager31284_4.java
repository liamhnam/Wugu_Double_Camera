package ZtlApi;

import android.util.DisplayMetrics;
import android.util.Log;
import com.tom_roush.pdfbox.pdmodel.documentinterchange.taggedpdf.PDLayoutAttributeObject;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import org.eclipse.paho.client.mqttv3.MqttTopic;

public class ZtlManager31284_4 extends ZtlManager {
    static final String SYSTEM_BAR_HIDE = "hide";
    static final String SYSTEM_BAR_SHOW = "show";
    static final String SYSTEM_BAR_STATE = "persist.sys.systemBar";
    private static final String SYS_NODE_VGA_MODE = "/sys/devices/platform/display-subsystem/drm/card0/card0-VGA-1/mode";
    private static final String SYS_NODE_VGA_MODES = "/sys/devices/platform/display-subsystem/drm/card0/card0-VGA-1/modes";
    private String TAG = "ZTS_3128_44";

    @Override
    public String[] getCPUFreq() {
        return null;
    }

    @Override
    public void setDesktop(String str) {
    }

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

    @Override
    public int getGpioValue(int i) {
        if (!getAndroidVersion().contains("3128")) {
            return -1;
        }
        getAndroidVersion().contains("4.4");
        return -1;
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

    @Override
    public long getTotalInternalMemorySize() {
        long j = readLong("/sys/block/mmcblk0/size") * 512;
        if (j == 0) {
            return getDeviceCapacity();
        }
        return roundStorageSize(j);
    }

    private long readLong(String str) {
        try {
            return Long.parseLong(new BufferedReader(new InputStreamReader(new FileInputStream(str))).readLine());
        } catch (Exception unused) {
            return 0L;
        }
    }

    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public long getDeviceCapacity() throws java.lang.Throwable {
        /*
            r7 = this;
            java.lang.String r0 = "Device Capacity:"
            java.io.File r1 = new java.io.File
            java.lang.String r2 = "/proc/rknand"
            r1.<init>(r2)
            boolean r1 = r1.exists()
            java.lang.String r3 = "TAG"
            java.lang.String r4 = "2048"
            if (r1 == 0) goto L70
            r1 = 0
            java.io.FileReader r5 = new java.io.FileReader     // Catch: java.lang.Throwable -> L63 java.io.IOException -> L6a
            r5.<init>(r2)     // Catch: java.lang.Throwable -> L63 java.io.IOException -> L6a
            java.io.BufferedReader r2 = new java.io.BufferedReader     // Catch: java.lang.Throwable -> L63 java.io.IOException -> L6a
            r6 = 4096(0x1000, float:5.74E-42)
            r2.<init>(r5, r6)     // Catch: java.lang.Throwable -> L63 java.io.IOException -> L6a
        L20:
            java.lang.String r1 = r2.readLine()     // Catch: java.lang.Throwable -> L5e java.io.IOException -> L61
            if (r1 == 0) goto L5a
            int r5 = r1.indexOf(r0)     // Catch: java.lang.Throwable -> L5e java.io.IOException -> L61
            r6 = -1
            if (r5 == r6) goto L20
            int r0 = r1.indexOf(r0)     // Catch: java.lang.Throwable -> L5e java.io.IOException -> L61
            java.lang.String r0 = r1.substring(r0)     // Catch: java.lang.Throwable -> L5e java.io.IOException -> L61
            java.lang.String r1 = "\\D+"
            java.lang.String r5 = ""
            java.lang.String r0 = r0.replaceAll(r1, r5)     // Catch: java.lang.Throwable -> L5e java.io.IOException -> L61
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L5e java.io.IOException -> L61
            r1.<init>()     // Catch: java.lang.Throwable -> L5e java.io.IOException -> L61
            java.lang.String r5 = "get device capacity: "
            java.lang.StringBuilder r1 = r1.append(r5)     // Catch: java.lang.Throwable -> L5e java.io.IOException -> L61
            java.lang.StringBuilder r1 = r1.append(r0)     // Catch: java.lang.Throwable -> L5e java.io.IOException -> L61
            java.lang.String r5 = " MB"
            java.lang.StringBuilder r1 = r1.append(r5)     // Catch: java.lang.Throwable -> L5e java.io.IOException -> L61
            java.lang.String r1 = r1.toString()     // Catch: java.lang.Throwable -> L5e java.io.IOException -> L61
            android.util.Log.i(r3, r1)     // Catch: java.lang.Throwable -> L5e java.io.IOException -> L61
            r4 = r0
        L5a:
            r2.close()     // Catch: java.io.IOException -> L75
            goto L75
        L5e:
            r0 = move-exception
            r1 = r2
            goto L64
        L61:
            r1 = r2
            goto L6a
        L63:
            r0 = move-exception
        L64:
            if (r1 == 0) goto L69
            r1.close()     // Catch: java.io.IOException -> L69
        L69:
            throw r0
        L6a:
            if (r1 == 0) goto L75
            r1.close()     // Catch: java.io.IOException -> L75
            goto L75
        L70:
            java.lang.String r0 = "/proc/rknand not exist, storage capacity defaults to 4GB."
            android.util.Log.w(r3, r0)
        L75:
            java.lang.Integer r0 = java.lang.Integer.valueOf(r4)
            int r0 = r0.intValue()
            long r0 = (long) r0
            r2 = 1024(0x400, double:5.06E-321)
            long r0 = r0 * r2
            long r0 = r0 * r2
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r3 = "capacity"
            r2.<init>(r3)
            java.lang.StringBuilder r2 = r2.append(r4)
            java.lang.String r2 = r2.toString()
            android.util.Log.e(r3, r2)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: ZtlApi.ZtlManager31284_4.getDeviceCapacity():long");
    }

    ZtlManager31284_4() {
        this.DEBUG_ZTL = getSystemProperty("persist.sys.ztl.debug", "false").equals("true");
    }

    @Override
    public String getUsbStoragePath() {
        for (int i = 0; i < 100; i++) {
            try {
                if (new File("/mnt/usb_storage/USB_DISK1/udisk" + i + MqttTopic.TOPIC_LEVEL_SEPARATOR).exists()) {
                    return "/mnt/usb_storage/USB_DISK1/udisk" + i + MqttTopic.TOPIC_LEVEL_SEPARATOR;
                }
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
        return null;
    }

    public int Getcurrentgpio(int i) {
        String strFromFile;
        execRootCmdSilent("cat /sys/class/gpio/gpio" + i + "/value");
        try {
            strFromFile = readStrFromFile("/sys/class/gpio/gpio" + i + "/value");
        } catch (Exception e) {
            e.printStackTrace();
            strFromFile = null;
        }
        return Integer.parseInt(strFromFile);
    }

    @Override
    public void setDisplayOrientation(int i) {
        if (this.mContext == null) {
            Log.e("上下文为空", "不执行");
            return;
        }
        if (i == getDisplayOrientation()) {
            Log.e("当前方向", "与旋转方向一致，不执行");
            return;
        }
        try {
            setSystemProperty("persist.ztl.hwrotation", i + "");
            execRootCmdSilent("reboot");
        } catch (Exception unused) {
            Log.e(this.TAG, "set rotation err!");
        }
    }

    @Override
    public void setTouchOrientation(int i, boolean z) {
        try {
            setSystemProperty(this.TP_ORIENTATION_PROP, i + "");
            if (z) {
                execRootCmdSilent("reboot");
            }
        } catch (Exception unused) {
        }
    }

    @Override
    public int getTouchOrientation() {
        return Integer.valueOf(getSystemProperty(this.TP_ORIENTATION_PROP, PDLayoutAttributeObject.GLYPH_ORIENTATION_VERTICAL_ZERO_DEGREES)).intValue();
    }

    @Override
    public String getDisplayMode() {
        if (this.mContext == null) {
            Log.e("上下文为空", "不执行");
            return null;
        }
        try {
            new DisplayMetrics();
            DisplayMetrics displayMetrics = this.mContext.getResources().getDisplayMetrics();
            return displayMetrics.widthPixels + "x" + displayMetrics.heightPixels;
        } catch (Exception unused) {
            Log.e(this.TAG, "getDisplayMode err!");
            return "1920x1080";
        }
    }

    @Override
    public String[] getScreenModes() {
        return new String[]{"1920x1080", "1280x720"};
    }

    @Override
    public void setScreenMode(String str) {
        if (str.contains("1920x1080")) {
            execRootCmdSilent("/system/xbin/resolution 1080 1920 60");
            reboot(0);
        } else if (str.contains("1280x720")) {
            execRootCmdSilent("/system/xbin/resolution 720  1280 60");
            reboot(0);
        }
    }
}
