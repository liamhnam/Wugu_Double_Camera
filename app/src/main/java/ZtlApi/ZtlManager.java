package ZtlApi;

import ZtlApi.CpuInfo;
import android.app.ActivityManager;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.display.DisplayManager;
import android.media.AudioManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.PowerManager;
import android.os.StatFs;
import android.os.SystemClock;
import android.os.storage.StorageManager;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.constraintlayout.motion.widget.Key;
import androidx.core.app.NotificationCompat;
import androidx.exifinterface.media.ExifInterface;
import com.google.android.exoplayer2.metadata.icy.IcyHeaders;
import com.google.android.exoplayer2.util.MimeTypes;
import com.p020hp.jipp.cups.Cups;
import com.p020hp.jipp.model.IdentifyAction;
import com.p020hp.jipp.model.Media;
import com.p020hp.jipp.model.TimeoutPredicate;
import com.proembed.service.Constant;
import com.tom_roush.fontbox.ttf.NamingTable;
import com.tom_roush.pdfbox.pdmodel.documentinterchange.taggedpdf.PDLayoutAttributeObject;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import java.util.Objects;
import okhttp3.internal.p040ws.RealWebSocket;
import org.eclipse.paho.android.service.MqttServiceConstants;
import org.eclipse.paho.client.mqttv3.MqttTopic;

public class ZtlManager {
    static ZtlManager Instance = null;
    static String TAG = "ZtlManager";
    private static String devType = null;
    private static boolean isOpenWatchDog = false;
    boolean DEBUG_ZTL;
    private CpuInfo cpuInfo;
    protected Context mContext;
    private ZtlI2C ztlI2C;
    String BlFile = "/proc/bl_root/bl_entry";
    String HdmiEnableFile = Constant.HDMI_STATUS_3288;
    String BlOn = IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE;
    String BlOff = PDLayoutAttributeObject.GLYPH_ORIENTATION_VERTICAL_ZERO_DEGREES;
    String POWER_ON_TIME = Constant.PERSIST_SYS_POWERONTIME;
    String IS_OPEN_ALARM = "persist.sys.isopenalarm";
    String ALARM_ON = IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE;
    String ALARM_OFF = PDLayoutAttributeObject.GLYPH_ORIENTATION_VERTICAL_ZERO_DEGREES;
    String LCD_DENSITY_PROP = "persist.sys.ztl_density";
    String CAMERA_ORIENTATION_PROP = "persist.sys.cameraOrientation";
    String TP_ORIENTATION_PROP = "persist.sys.tp.orientation";
    Thread watchDogThread = null;

    public static long roundStorageSize(long j) {
        long j2 = 1;
        long j3 = 1;
        while (true) {
            long j4 = j2 * j3;
            if (j4 >= j) {
                return j4;
            }
            j2 <<= 1;
            if (j2 > 512) {
                j3 *= RealWebSocket.DEFAULT_MINIMUM_DEFLATE_SIZE;
                j2 = 1;
            }
        }
    }

    private static native int setScreenResolution(String str);

    public static native boolean ztl_check();

    public String getFreq(String str) {
        return "当前仅3588可用";
    }

    public String getJARVersion() {
        return "6.3";
    }

    public String getNowFreq(String str) {
        return "当前仅3588可用";
    }

    public int getSystemMaxBrightness() {
        return 255;
    }

    public void setBlackLight(boolean z) {
    }

    public void setGPUMode(String str) {
    }

    public void setNowFreq(String str, String str2) {
    }

    public void setPrimaryDisplayOrientation(int i) {
    }

    public void setResolution(String str) {
    }

    public static ZtlManager GetInstance() {
        if (Instance == null) {
            String deviceVersion = getDeviceVersion();
            devType = deviceVersion;
            if (deviceVersion.contains("3288") && getAndroidVersion().contains("5.1")) {
                Instance = new ZtlManager();
            } else if (devType.contains("dolphin")) {
                Instance = new ZtlManagerH3();
            } else if (devType.contains("3399pro")) {
                Instance = new ZtlManager3399Pro();
            } else if (devType.contains("3399")) {
                if (getAndroidVersion().contains("7.1")) {
                    Instance = new ZtlManager33997_1();
                } else if (getAndroidVersion().contains("11")) {
                    Instance = new ZtlManager3399_11();
                }
            } else if (devType.contains("3288") && getAndroidVersion().contains("7.1")) {
                Instance = new ZtlManager32887_1();
            } else if (devType.contains("3328")) {
                Instance = new ZtlManager33287_1();
            } else if (devType.contains("3368")) {
                Instance = new ZtlManager3368();
            } else if (devType.contains("3126") || devType.contains("3128")) {
                if (getAndroidVersion().contains("7.1")) {
                    Instance = new ZtlManager3128();
                } else if (getAndroidVersion().contains("4.4")) {
                    Instance = new ZtlManager31284_4();
                }
            } else if (devType.contains("A64") || devType.contains("A33")) {
                Instance = new ZtlManagerA33_A64();
            } else if (devType.contains("A40")) {
                Instance = new ZtlManagerA40i();
            } else if (devType.contains("u202") || devType.contains("w400")) {
                Instance = new ZtlManagerU202();
            } else if (devType.contains("3568") || devType.contains("3566") || devType.contains("3588")) {
                Instance = new ZtlManager3568();
            } else if (devType.contains("kt11")) {
                Instance = new ZtlManagerkt11_32();
            } else if (devType.contains("Hi3751V350")) {
                Instance = new ZtlManagerHisi();
            } else if (devType.contains("A133")) {
                Instance = new ZtlManagerA133();
            } else if (devType.contains("rk3576")) {
                Instance = new ZtlManager3576();
            } else if (devType.contains("A527")) {
                Instance = new ZtlManagerA527();
            }
            if (Instance == null) {
                Instance = new ZtlManager();
            }
        }
        return Instance;
    }

    public void setContext(Context context) {
        if (context == null) {
            Log.e("context ", "设置为空");
        }
        this.mContext = context;
    }

    ZtlManager() {
        this.DEBUG_ZTL = false;
        this.DEBUG_ZTL = getSystemProperty("persist.sys.ztl.debug", "false").equals("true");
    }

    void LOGD(String str) {
        if (this.DEBUG_ZTL) {
            Log.d(TAG, str);
        }
    }

    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.lang.String getDeviceVersion() {
        /*
            java.lang.String r0 = "get"
            java.lang.String r1 = "android.os.SystemProperties"
            java.lang.String r2 = ""
            r3 = 0
            r4 = 1
            java.lang.String r5 = "ro.ztl.model"
            java.lang.Class r6 = java.lang.Class.forName(r1)     // Catch: java.lang.Exception -> L2d
            java.lang.Class[] r7 = new java.lang.Class[r4]     // Catch: java.lang.Exception -> L2d
            java.lang.Class<java.lang.String> r8 = java.lang.String.class
            r7[r3] = r8     // Catch: java.lang.Exception -> L2d
            java.lang.reflect.Method r7 = r6.getDeclaredMethod(r0, r7)     // Catch: java.lang.Exception -> L2d
            java.lang.Object r6 = r6.newInstance()     // Catch: java.lang.Exception -> L2d
            java.lang.Object[] r5 = new java.lang.Object[]{r5}     // Catch: java.lang.Exception -> L2d
            java.lang.Object r5 = r7.invoke(r6, r5)     // Catch: java.lang.Exception -> L2d
            java.lang.String r5 = (java.lang.String) r5     // Catch: java.lang.Exception -> L2d
            boolean r6 = android.text.TextUtils.isEmpty(r5)     // Catch: java.lang.Exception -> L2d
            if (r6 != 0) goto L3a
            goto L3b
        L2d:
            java.lang.String r5 = "getprop ro.ztl.model"
            java.lang.String r5 = execRootCmd(r5)     // Catch: java.lang.Exception -> L46
            boolean r6 = r5.isEmpty()     // Catch: java.lang.Exception -> L46
            if (r6 != 0) goto L3a
            goto L3b
        L3a:
            r5 = r2
        L3b:
            java.lang.String r6 = r5.trim()     // Catch: java.lang.Exception -> L46
            boolean r6 = r6.equals(r2)     // Catch: java.lang.Exception -> L46
            if (r6 != 0) goto L4a
            return r5
        L46:
            r5 = move-exception
            r5.printStackTrace()
        L4a:
            java.lang.String r5 = "ro.product.system.model"
            java.lang.Class r1 = java.lang.Class.forName(r1)     // Catch: java.lang.Exception -> L6f
            java.lang.Class[] r4 = new java.lang.Class[r4]     // Catch: java.lang.Exception -> L6f
            java.lang.Class<java.lang.String> r6 = java.lang.String.class
            r4[r3] = r6     // Catch: java.lang.Exception -> L6f
            java.lang.reflect.Method r0 = r1.getDeclaredMethod(r0, r4)     // Catch: java.lang.Exception -> L6f
            java.lang.Object r1 = r1.newInstance()     // Catch: java.lang.Exception -> L6f
            java.lang.Object[] r3 = new java.lang.Object[]{r5}     // Catch: java.lang.Exception -> L6f
            java.lang.Object r0 = r0.invoke(r1, r3)     // Catch: java.lang.Exception -> L6f
            java.lang.String r0 = (java.lang.String) r0     // Catch: java.lang.Exception -> L6f
            boolean r1 = android.text.TextUtils.isEmpty(r0)     // Catch: java.lang.Exception -> L6f
            if (r1 != 0) goto L7c
            goto L7d
        L6f:
            java.lang.String r0 = "getprop ro.product.system.model"
            java.lang.String r0 = execRootCmd(r0)     // Catch: java.lang.Exception -> L88
            boolean r1 = r0.isEmpty()     // Catch: java.lang.Exception -> L88
            if (r1 != 0) goto L7c
            goto L7d
        L7c:
            r0 = r2
        L7d:
            java.lang.String r1 = r0.trim()     // Catch: java.lang.Exception -> L88
            boolean r1 = r1.equals(r2)     // Catch: java.lang.Exception -> L88
            if (r1 != 0) goto L8c
            return r0
        L88:
            r0 = move-exception
            r0.printStackTrace()
        L8c:
            java.lang.String r0 = android.os.Build.MODEL
            java.lang.String r1 = "IC2"
            boolean r1 = r0.equals(r1)
            if (r1 == 0) goto L98
            java.lang.String r0 = "rk3288"
        L98:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: ZtlApi.ZtlManager.getDeviceVersion():java.lang.String");
    }

    public static String getAndroidVersion() {
        return Build.VERSION.RELEASE;
    }

    public String getFirmwareVersion() {
        return getSystemProperty("ro.build.display.id", "");
    }

    public String getBoardType() {
        String systemProperty = getSystemProperty("persist.ztl.board.type", "");
        if (!Objects.equals(systemProperty, "")) {
            return systemProperty;
        }
        Log.e("ZTLLib", "请检查是否设置了persist.ztl.board.type属性");
        return "";
    }

    public int getSDKVersion() {
        return Build.VERSION.SDK_INT;
    }

    public String getDeviceID() {
        BufferedReader bufferedReader;
        String line;
        File file = new File("/proc/cpuinfo");
        if (!file.exists()) {
            LOGD("/proc/cpuinfo not found!");
            return null;
        }
        try {
            bufferedReader = new BufferedReader(new FileReader(file));
        } catch (Exception e) {
            e.printStackTrace();
        }
        do {
            line = bufferedReader.readLine();
            if (line != null) {
            }
            return null;
        } while (!line.contains("Serial"));
        LOGD(line.length() + line);
        String strSubstring = line.substring(line.indexOf(":") + 2);
        LOGD(strSubstring);
        return strSubstring;
    }

    public long getTotalInternalMemorySize() {
        return ((roundStorageSize(Environment.getDataDirectory().getTotalSpace() + Environment.getRootDirectory().getTotalSpace()) / RealWebSocket.DEFAULT_MINIMUM_DEFLATE_SIZE) / RealWebSocket.DEFAULT_MINIMUM_DEFLATE_SIZE) / RealWebSocket.DEFAULT_MINIMUM_DEFLATE_SIZE;
    }

    public long getFreeMemory() {
        StatFs statFs = new StatFs(Environment.getDataDirectory().getPath());
        return ((long) statFs.getFreeBlocks()) * ((long) statFs.getBlockSize());
    }

    public long getFreeMemorySize() {
        if (this.mContext == null) {
            Log.e("上下文为空，不执行", "请检查是否已调用setContext()");
            return -1L;
        }
        ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
        ((ActivityManager) this.mContext.getSystemService(TimeoutPredicate.activity)).getMemoryInfo(memoryInfo);
        return memoryInfo.availMem;
    }

    public String getInternalSDCardPath() {
        return System.getenv("EXTERNAL_STORAGE");
    }

    public String getExternalSDCardPath() {
        return getAppRootOfSdCardRemovable();
    }

    String getAppRootOfSdCardRemovable() {
        GetInstance();
        if (getAndroidVersion().contains("5.1")) {
            String[] list = new File(Constant.SD_PATH).list();
            if (list == null || list.length <= 0) {
                return null;
            }
            return Constant.SD_PATH;
        }
        StorageManager storageManager = (StorageManager) this.mContext.getSystemService("storage");
        try {
            Class<?> cls = Class.forName("android.os.storage.StorageVolume");
            Method method = storageManager.getClass().getMethod("getVolumeList", new Class[0]);
            Method method2 = cls.getMethod("getPath", new Class[0]);
            Method method3 = cls.getMethod("isRemovable", new Class[0]);
            Method method4 = cls.getMethod("getDescription", Context.class);
            Object objInvoke = method.invoke(storageManager, new Object[0]);
            int length = Array.getLength(objInvoke);
            for (int i = 0; i < length; i++) {
                Object obj = Array.get(objInvoke, i);
                String str = (String) method2.invoke(obj, new Object[0]);
                String str2 = (String) method4.invoke(obj, this.mContext);
                if (true == ((Boolean) method3.invoke(obj, new Object[0])).booleanValue() && (str2.contains("SD") || str2.contains("SD 卡"))) {
                    return str;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public int getUSBDiskCount() {
        return getUSBDisks().size();
    }

    public String getUSBDisk(int i) {
        if (i < 0) {
            return null;
        }
        List<String> uSBDisks = getUSBDisks();
        if (uSBDisks.size() > 0 && i < uSBDisks.size()) {
            return uSBDisks.get(i);
        }
        return null;
    }

    public List<String> getUSBDisks() {
        String str = (getAndroidVersion().contains("5.1.1") || getAndroidVersion().contains("4.4")) ? "/mnt/usb_storage/" : "/storage/";
        ArrayList arrayList = new ArrayList();
        File file = new File(str);
        try {
            if (!file.exists() || !file.isDirectory()) {
                return arrayList;
            }
            File[] fileArrListFiles = file.listFiles();
            if (fileArrListFiles.length <= 0) {
                return arrayList;
            }
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
            return arrayList.size() > 0 ? Collections.singletonList(arrayList.get(0)) : arrayList;
        } catch (Exception e) {
            e.printStackTrace();
            return arrayList;
        }
    }

    public String getUsbStoragePath() {
        try {
            List<String> uSBDisks = getUSBDisks();
            if (uSBDisks.size() == 0) {
                return null;
            }
            return uSBDisks.get(0);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String getSataStoragePath() {
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
        String[] list = new File("sys/class/scsi_disk").list();
        Log.e(TAG, list.toString());
        for (String str2 : list) {
            if (str2.equals("0:0:0:0")) {
                return (String) arrayList.get(arrayList.size() - 1);
            }
        }
        return null;
    }

    public void sleep() {
        if (this.mContext == null) {
            Log.e("上下文为空，不执行", "请检查是否已调用setContext()");
            return;
        }
        ComponentName componentName = new ComponentName("com.ztl.helper", "com.ztl.helper.ZTLHelperService");
        Intent intent = new Intent();
        intent.setComponent(componentName);
        intent.putExtra("cmd", "sleep");
        this.mContext.startService(intent);
    }

    public void setNTPServer(String str) {
        if (this.mContext == null) {
            Log.e("上下文为空，不执行", "请检查是否已调用setContext()");
            return;
        }
        ComponentName componentName = new ComponentName("com.ztl.helper", "com.ztl.helper.ZTLHelperService");
        Intent intent = new Intent();
        intent.setComponent(componentName);
        intent.putExtra("cmd", " setNTPServer");
        intent.putExtra("value", str);
        this.mContext.startService(intent);
    }

    public void awake() {
        if (this.mContext == null) {
            Log.e("上下文为空，不执行", "请检查是否已调用setContext()");
            return;
        }
        ComponentName componentName = new ComponentName("com.ztl.helper", "com.ztl.helper.ZTLHelperService");
        Intent intent = new Intent();
        intent.setComponent(componentName);
        intent.putExtra("cmd", "awake");
        this.mContext.startService(intent);
    }

    public void setScreenOffTimeout(long j) {
        if (this.mContext == null) {
            Log.e("上下文为空，不执行", "请检查是否已调用setContext()");
            return;
        }
        ComponentName componentName = new ComponentName("com.ztl.helper", "com.ztl.helper.ZTLHelperService");
        Intent intent = new Intent();
        intent.setComponent(componentName);
        intent.putExtra("cmd", "screen_off_timeout");
        intent.putExtra("timeout", j);
        this.mContext.startService(intent);
    }

    public long getScreenOffTimeout() {
        Context context = this.mContext;
        if (context == null) {
            Log.e("上下文为空，不执行", "请检查是否已调用setContext()");
            return -1L;
        }
        long j = Settings.System.getLong(context.getContentResolver(), "screen_off_timeout", -1L);
        if (j == 2147483647L) {
            return 0L;
        }
        return j;
    }

    public void shutdown() {
        if (this.mContext == null) {
            Log.e("上下文为空，不执行", "请检查是否已调用setContext()");
            return;
        }
        ComponentName componentName = new ComponentName("com.ztl.helper", "com.ztl.helper.ZTLHelperService");
        Intent intent = new Intent();
        intent.setComponent(componentName);
        intent.putExtra("cmd", "shutdown");
        this.mContext.startService(intent);
    }

    public void reboot(int i) {
        if (this.mContext == null) {
            Log.e("上下文为空，不执行", "请检查是否已调用setContext()");
            return;
        }
        ComponentName componentName = new ComponentName("com.ztl.helper", "com.ztl.helper.ZTLHelperService");
        Intent intent = new Intent();
        intent.setComponent(componentName);
        intent.putExtra("cmd", "reboot");
        intent.putExtra("delay", i);
        this.mContext.startService(intent);
    }

    public void startScreenShot(String str, String str2) {
        if (isExist(str)) {
            execRootCmdSilent("screencap -p " + (str + MqttTopic.TOPIC_LEVEL_SEPARATOR + str2));
        } else {
            Log.e(TAG, "file path " + str + " not exist");
        }
    }

    public void setLauncher(String str, String str2) {
        if (this.mContext == null) {
            Log.e("上下文为空，不执行", "请检查是否已调用setContext()");
            return;
        }
        ComponentName componentName = new ComponentName("com.ztl.helper", "com.ztl.helper.ZTLHelperService");
        Intent intent = new Intent();
        intent.setComponent(componentName);
        intent.putExtra("cmd", "setLauncher");
        intent.putExtra("package", str);
        intent.putExtra(TimeoutPredicate.activity, str2);
        this.mContext.startService(intent);
    }

    public void setDesktop(String str) {
        setSystemProperty("persist.ztl.desktopName", str);
        execRootCmdSilent("sync");
    }

    public String getDesktop() {
        return getSystemProperty("persist.ztl.desktopName", "");
    }

    public boolean isAppExist(String str) {
        Context context = this.mContext;
        if (context == null) {
            Log.e("上下文为空，不执行", "请检查是否已调用setContext()");
            return false;
        }
        try {
            return context.getPackageManager().getApplicationInfo(str, 0) != null;
        } catch (PackageManager.NameNotFoundException unused) {
            return false;
        }
    }

    public void resetSystem() {
        if (this.mContext == null) {
            Log.e("上下文为空，不执行", "请检查是否已调用setContext()");
            return;
        }
        ComponentName componentName = new ComponentName("com.ztl.helper", "com.ztl.helper.ZTLHelperService");
        Intent intent = new Intent();
        intent.setComponent(componentName);
        intent.putExtra("cmd", "resetSystem");
        this.mContext.startService(intent);
    }

    public void startSettings() {
        Context context = this.mContext;
        if (context == null) {
            Log.e("上下文为空，不执行", "请检查是否已调用setContext()");
            return;
        }
        try {
            context.startActivity(new Intent("android.settings.SETTINGS"));
        } catch (Exception unused) {
            LOGD("start settings fail!");
        }
    }

    public void startWifiSettings() {
        Context context = this.mContext;
        if (context == null) {
            Log.e("上下文为空，不执行", "请检查是否已调用setContext()");
            return;
        }
        try {
            context.startActivity(new Intent("android.settings.WIFI_SETTINGS"));
        } catch (Exception unused) {
            LOGD("start wifi settings fail!");
        }
    }

    public void openSystemBar(boolean z) {
        if (this.mContext == null) {
            Log.e("上下文为空，不执行", "请检查是否已调用setContext()");
            return;
        }
        String str = z ? PDLayoutAttributeObject.GLYPH_ORIENTATION_VERTICAL_ZERO_DEGREES : IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE;
        Intent intent = new Intent("com.ding.systembar.chang");
        intent.putExtra("enable", str);
        this.mContext.sendBroadcast(intent);
    }

    public boolean isSystemBarOpen() {
        int i;
        String strTrim = getSystemProperty("persist.sys.barState", IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE).trim();
        if (strTrim.isEmpty()) {
            return true;
        }
        try {
            i = Integer.parseInt(strTrim);
        } catch (NumberFormatException unused) {
            i = -1;
        }
        return i != 0;
    }

    public void openUsbDebug(boolean z) {
        if (this.mContext == null) {
            Log.e("上下文为空，不执行", "请检查是否已调用setContext()");
            return;
        }
        String str = z ? IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE : PDLayoutAttributeObject.GLYPH_ORIENTATION_VERTICAL_ZERO_DEGREES;
        Intent intent = new Intent("com.ding.adbsetting");
        intent.putExtra("enable", str);
        this.mContext.sendBroadcast(intent);
    }

    public boolean isUsbDebugOpen() {
        return Integer.valueOf(getSystemProperty("persist.sys.adbState", IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE)).intValue() == 1;
    }

    public void setUSBtoPC(boolean z) throws Throwable {
        String str = z ? ExifInterface.GPS_MEASUREMENT_2D : IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE;
        setSystemProperty("persist.usb.mode", str);
        writeMethod(Constant.USB_OTG_IO, str);
    }

    public boolean getUSBtoPC() {
        try {
            return loadFileAsString(Constant.USB_OTG_IO).contains(ExifInterface.GPS_MEASUREMENT_2D);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean isDebugSerialEnable() {
        Log.e(TAG, "todo");
        try {
            if (!getSystemProperty("persist.ztl.function", "").contains("debug")) {
                Log.e(TAG, "系统不支持，请更新");
                return true;
            }
            try {
            } catch (Exception e) {
                e.printStackTrace();
            }
            return new File("/dev/ttyFIQ0").exists();
        } catch (Exception unused) {
            Log.e(TAG, "系统不支持，请更新");
            return true;
        }
    }

    public void enableDebugSerial(boolean z, boolean z2) {
        Log.e(TAG, "todo");
        String str = IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE;
        String str2 = z ? IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE : PDLayoutAttributeObject.GLYPH_ORIENTATION_VERTICAL_ZERO_DEGREES;
        if (!z2) {
            str = PDLayoutAttributeObject.GLYPH_ORIENTATION_VERTICAL_ZERO_DEGREES;
        }
        execRootCmdSilent("lcdparamservice debug " + str2 + " " + str);
    }

    public void setBuildSi(String str) {
        if (this.mContext == null) {
            Log.e("上下文为空，不执行", "请检查是否已调用setContext()");
            return;
        }
        ComponentName componentName = new ComponentName("com.ztl.helper", "com.ztl.helper.ZTLHelperService");
        Intent intent = new Intent();
        intent.setComponent(componentName);
        intent.putExtra("cmd", "buildSi");
        intent.putExtra("si", str);
        this.mContext.startService(intent);
    }

    public String getBuildSi() {
        return Build.SERIAL;
    }

    public int setBuildSerial(String str) {
        if (str == null) {
            return 0;
        }
        setSystemProperty("persist.sys.ztlsn", str);
        return 0;
    }

    public String getBuildSerial() {
        if (Build.VERSION.SDK_INT >= 26) {
            return Build.getSerial();
        }
        String systemProperty = getSystemProperty("persist.sys.ztlsn", "unknown");
        return systemProperty.equals("unknown") ? Build.SERIAL : systemProperty;
    }

    public static String get4gStatic(Context context) {
        try {
            return context.getContentResolver().getType(Uri.parse("content://com.ztl.helper.ZtlApi/get4gStatic"));
        } catch (Exception unused) {
            return null;
        }
    }

    public void WatchDog() {
        if (this.mContext == null) {
            Log.e("上下文为空，不执行", "请检查是否已调用setContext()");
            return;
        }
        ComponentName componentName = new ComponentName("com.ztl.helper", "com.ztl.helper.ZTLHelperService");
        Intent intent = new Intent();
        intent.setComponent(componentName);
        intent.putExtra("cmd", "appWatchDog");
        this.mContext.startService(intent);
    }

    public int isSwapTouch() {
        try {
            String systemProperty = GetInstance().getSystemProperty("persist.ztl.swap_touch", "");
            if (systemProperty != null && !systemProperty.equals("")) {
                return systemProperty.trim().equals("true") ? 1 : 0;
            }
            return -1;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("isSwapTouch error");
            return -1;
        }
    }

    public boolean setSwapTouch(boolean z, boolean z2) {
        boolean z3 = false;
        try {
            String systemProperty = GetInstance().getSystemProperty("persist.ztl.swap_touch", "");
            if (systemProperty != null && !systemProperty.equals("")) {
                GetInstance().setSystemProperty("persist.ztl.swap_touch", z + "");
                boolean z4 = true;
                if ((isSwapTouch() != 1 || !z) && (isSwapTouch() != 0 || z)) {
                    z4 = false;
                }
                if (z4 && z2) {
                    try {
                        reboot(0);
                    } catch (Exception e) {
                        e = e;
                        z3 = z4;
                        e.printStackTrace();
                        System.out.println("setSwapTouch error");
                        return z3;
                    }
                }
                return z4;
            }
            System.out.println("usb SwapTouch not");
            return false;
        } catch (Exception e2) {
            e = e2;
        }
    }

    public String setLayoutPasswd(String str) {
        if (str.length() < 6) {
            return "密码长度需大于六位";
        }
        setSystemProperty("persist.ztl.layoutpasswd", new StringBuffer(str).reverse().toString());
        return "设置密码成功";
    }

    public String getLayoutPasswd() {
        String systemProperty = getSystemProperty("persist.ztl.layoutpasswd", "");
        return Objects.equals(systemProperty, "") ? "未设置密码，请先设置密码" : new StringBuffer(systemProperty).reverse().toString();
    }

    public List getLayoutParam() {
        ArrayList arrayList = new ArrayList();
        if (new File("/ztloem/etc/lcdLayout.cfg").exists()) {
            return getLines("/ztloem/etc/lcdLayout.cfg", Integer.MAX_VALUE);
        }
        if (getBoardType().toLowerCase().contains(Media.f728d)) {
            arrayList.add("2x1 3840x1080");
            arrayList.add("1x2 1920x2160");
            arrayList.add("3x1 5760x1080");
            arrayList.add("1x3 1920x3240");
            arrayList.add("4x1 7680x1080");
            arrayList.add("1x4 1920x4320");
            arrayList.add("2x2 3840x2160");
            arrayList.add("5x1 9600x1080");
            arrayList.add("6x1 11520x1080");
            arrayList.add("3x2 5760x2160");
            arrayList.add("2x3 3840x3240");
        } else {
            arrayList.add("2x1 3840x1080");
            arrayList.add("1x2 1920x2160");
            arrayList.add("3x1 5760x1080");
            arrayList.add("1x3 1920x3240");
        }
        arrayList.add("2x1 7680x2160");
        arrayList.add("1x2 3840x4320");
        arrayList.add("3x1 11520x2160");
        arrayList.add("1x3 3840x6480");
        return arrayList;
    }

    public void setLayout(boolean z, String str, boolean z2, String str2) {
        if (!checkApkExist(this.mContext, "com.ztl.muiltDisplayLayout", 2)) {
            System.out.println("版本小于3，请更新");
            return;
        }
        String boardType = getBoardType();
        Intent intent = new Intent();
        if (boardType.toLowerCase().contains(Media.f728d)) {
            if (str.contains("1x2") || str.contains("2x1")) {
                intent.putExtra("lcdTypeLayout", "HDMI-1#HDMI-2");
            } else if (str.contains("1x3") || str.contains("3x1")) {
                intent.putExtra("lcdTypeLayout", "HDMI-1#HDMI-2#HDMI-3");
                String systemProperty = GetInstance().getSystemProperty("persist.ztl.lcdlayout", "");
                if (systemProperty.contains("VGA")) {
                    GetInstance().setSystemProperty("persist.ztl.lcdlayout", systemProperty.replace("VGA", "HDMI-3"));
                }
                z = false;
            } else {
                intent.putExtra("lcdTypeLayout", "4");
            }
        } else {
            intent.putExtra("lcdTypeLayout", str2);
        }
        intent.putExtra("reboot", z);
        intent.putExtra("lcdLayout", str);
        intent.putExtra("SameLcdMode", z2);
        intent.putExtra("autoout", "-1");
        intent.setPackage("com.ztl.muiltDisplayLayout");
        intent.setAction("com.ztl.updateLcdLayout");
        System.out.println("sendBroadcast>>>>>>>>>>>>>>>com.ztl.updateLcdLayout");
        this.mContext.sendBroadcast(intent);
    }

    private static boolean checkApkExist(Context context, String str, int i) {
        if (str != null && !"".equals(str)) {
            try {
                if (context.getPackageManager().getPackageInfo(str, 0).versionCode > i) {
                    return true;
                }
            } catch (Exception unused) {
            }
        }
        return false;
    }

    public void setForceout(boolean z) {
        if (this.mContext == null) {
            Log.e("上下文为空，不执行", "请检查是否已调用setContext()");
        } else {
            setSystemProperty("persist.ztl.forceout", (z ? 1 : 0) + "");
        }
    }

    public void lockScreenSettings(boolean z, String str) {
        if (this.mContext == null) {
            Log.e("上下文为空，不执行", "请检查是否已调用setContext()");
            return;
        }
        ComponentName componentName = new ComponentName("com.ztl.helper", "com.ztl.helper.ZTLHelperService");
        Intent intent = new Intent();
        intent.setComponent(componentName);
        intent.putExtra("cmd", "lock_screen_settings");
        intent.putExtra("bEnable", z);
        intent.putExtra(Cups.AuthInfoRequired.password, str);
        this.mContext.startService(intent);
    }

    public void lockScreen() {
        if (this.mContext == null) {
            Log.e("上下文为空，不执行", "请检查是否已调用setContext()");
            return;
        }
        ComponentName componentName = new ComponentName("com.ztl.helper", "com.ztl.helper.ZTLHelperService");
        Intent intent = new Intent();
        intent.setComponent(componentName);
        intent.putExtra("cmd", "lock_screen");
        this.mContext.startService(intent);
    }

    private static String ReadFile(String str) {
        File file = new File(str);
        if (file.isFile() && file.exists()) {
            StringBuffer stringBuffer = new StringBuffer();
            byte[] bArr = new byte[1024];
            try {
                FileInputStream fileInputStream = new FileInputStream(file);
                while (true) {
                    int i = fileInputStream.read(bArr);
                    if (i != -1) {
                        stringBuffer.append(new String(bArr, 0, i));
                    } else {
                        return new String(stringBuffer);
                    }
                }
            } catch (FileNotFoundException | IOException unused) {
            }
        }
        return null;
    }

    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static java.lang.String tryExec(java.lang.String r7, java.lang.String r8) throws java.lang.InterruptedException, java.io.IOException {
        /*
            java.lang.Runtime r0 = java.lang.Runtime.getRuntime()
            java.lang.Process r7 = r0.exec(r7)
            java.io.DataOutputStream r0 = new java.io.DataOutputStream
            java.io.OutputStream r1 = r7.getOutputStream()
            r0.<init>(r1)
            java.io.DataInputStream r1 = new java.io.DataInputStream
            java.io.InputStream r2 = r7.getInputStream()
            r1.<init>(r2)
            java.io.DataInputStream r2 = new java.io.DataInputStream
            java.io.InputStream r3 = r7.getErrorStream()
            r2.<init>(r3)
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.StringBuilder r8 = r3.append(r8)
            java.lang.String r3 = "\n"
            java.lang.StringBuilder r8 = r8.append(r3)
            java.lang.String r8 = r8.toString()
            r0.writeBytes(r8)
            r0.flush()
            r4 = 100
            java.lang.Thread.sleep(r4)     // Catch: java.lang.Exception -> L49
            java.lang.String r8 = "exit\n"
            r0.writeBytes(r8)     // Catch: java.lang.Exception -> L49
            r0.flush()     // Catch: java.lang.Exception -> L49
        L49:
            java.io.BufferedReader r8 = new java.io.BufferedReader
            java.io.InputStreamReader r4 = new java.io.InputStreamReader
            r4.<init>(r1)
            r8.<init>(r4)
            java.lang.String r1 = ""
            r4 = r1
        L56:
            java.lang.String r5 = r8.readLine()
            if (r5 == 0) goto L7f
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            java.lang.StringBuilder r4 = r6.append(r4)
            java.lang.StringBuilder r4 = r4.append(r5)
            java.lang.String r4 = r4.toString()
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.StringBuilder r4 = r5.append(r4)
            java.lang.StringBuilder r4 = r4.append(r3)
            java.lang.String r4 = r4.toString()
            goto L56
        L7f:
            if (r4 == 0) goto L87
            boolean r8 = r4.equals(r1)     // Catch: java.io.IOException -> Lba
            if (r8 == 0) goto Lbe
        L87:
            java.io.BufferedReader r8 = new java.io.BufferedReader     // Catch: java.io.IOException -> Lba
            java.io.InputStreamReader r1 = new java.io.InputStreamReader     // Catch: java.io.IOException -> Lba
            r1.<init>(r2)     // Catch: java.io.IOException -> Lba
            r8.<init>(r1)     // Catch: java.io.IOException -> Lba
        L91:
            java.lang.String r1 = r8.readLine()     // Catch: java.io.IOException -> Lba
            if (r1 == 0) goto Lbe
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch: java.io.IOException -> Lba
            r2.<init>()     // Catch: java.io.IOException -> Lba
            java.lang.StringBuilder r2 = r2.append(r4)     // Catch: java.io.IOException -> Lba
            java.lang.StringBuilder r1 = r2.append(r1)     // Catch: java.io.IOException -> Lba
            java.lang.String r4 = r1.toString()     // Catch: java.io.IOException -> Lba
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch: java.io.IOException -> Lba
            r1.<init>()     // Catch: java.io.IOException -> Lba
            java.lang.StringBuilder r1 = r1.append(r4)     // Catch: java.io.IOException -> Lba
            java.lang.StringBuilder r1 = r1.append(r3)     // Catch: java.io.IOException -> Lba
            java.lang.String r4 = r1.toString()     // Catch: java.io.IOException -> Lba
            goto L91
        Lba:
            r8 = move-exception
            r8.printStackTrace()
        Lbe:
            r7.waitFor()
            r0.close()
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: ZtlApi.ZtlManager.tryExec(java.lang.String, java.lang.String):java.lang.String");
    }

    public static String execRootCmd(String str) {
        try {
            if (Build.VERSION.SDK_INT > 29 && new File("/system/xbin/testsu").exists()) {
                str = tryExec("testsu", str);
            } else {
                str = tryExec("su", str);
            }
            return str;
        } catch (IOException | InterruptedException unused) {
            Log.e("exec su failed", "exec su failed.trying testsu:" + str);
            try {
                return tryExec("testsu", str);
            } catch (IOException | InterruptedException unused2) {
                Log.e("exec testsu failed", str + " exec failed.");
                try {
                    return tryExec("", str);
                } catch (Exception unused3) {
                    Log.e("exec failed", str + " exec failed.");
                    return "";
                }
            }
        }
    }

    public int execRootCmdSilent(String str) {
        try {
            if (Build.VERSION.SDK_INT > 29 && new File("/system/xbin/testsu").exists()) {
                str = _execCmdAsSU("testsu", str);
            } else {
                str = _execCmdAsSU("su", str);
            }
            return str;
        } catch (Exception e) {
            Log.e(TAG, "su失败,正在尝试testsu " + str);
            try {
                return _execCmdAsSU("testsu", str);
            } catch (Exception unused) {
                Log.e(TAG, "testsu失败\r" + str + "\r执行失败");
                e.printStackTrace();
                return -1;
            }
        }
    }

    int _execCmdAsSU(String str, String str2) throws Exception {
        Process processExec = Runtime.getRuntime().exec(str);
        DataOutputStream dataOutputStream = new DataOutputStream(processExec.getOutputStream());
        dataOutputStream.writeBytes(str2 + "\n");
        dataOutputStream.flush();
        dataOutputStream.writeBytes("exit\n");
        dataOutputStream.flush();
        int iWaitFor = processExec.waitFor();
        dataOutputStream.close();
        return iWaitFor;
    }

    public void setBootPackageActivity(String str, String str2) {
        if (str != null && str2 != null) {
            setSystemProperty("persist.sys.bootPkgName", str);
            setSystemProperty("persist.sys.bootPkgActivity", str2);
        } else {
            setSystemProperty("persist.sys.bootPkgName", "unknown");
            setSystemProperty("persist.sys.bootPkgActivity", "unknown");
        }
    }

    public void cancelBootPackageActivity() {
        setSystemProperty("persist.sys.bootPkgName", "");
        setSystemProperty("persist.sys.bootPkgActivity", "");
    }

    public String getBootPackageName() {
        return getSystemProperty("persist.sys.bootPkgName", "unknown");
    }

    public String getBootPackageActivity() {
        return getSystemProperty("persist.sys.bootPkgActivity", "unknown");
    }

    public void startActivity(String str, String str2) {
        if (this.mContext == null) {
            Log.e("上下文为空，不执行", "请检查是否已调用setContext()");
            return;
        }
        if (str != null && str2 != null) {
            try {
                ComponentName componentName = new ComponentName(str, str2);
                Intent intent = new Intent();
                intent.setComponent(componentName);
                this.mContext.startActivity(intent);
                return;
            } catch (Exception unused) {
                LOGD("start app (" + str + "," + str2 + ") fail!");
                return;
            }
        }
        Log.e(TAG, "pkg is null please check it");
    }

    public void installAppSilent(String str) {
        if (!isExist(str)) {
            Log.e(TAG, "file [" + str + "] not isExist");
        } else if (!str.contains(".apk")) {
            Log.e(TAG, "file [" + str + "] 后缀不合法");
        } else if (execRootCmdSilent("pm install -r " + str) == 139) {
            installAppSilent(str);
        }
    }

    public void installApp(String str) {
        if (this.mContext == null) {
            Log.e("上下文为空，不执行", "请检查是否已调用setContext()");
            return;
        }
        ComponentName componentName = new ComponentName("com.ztl.helper", "com.ztl.helper.ZTLHelperService");
        Intent intent = new Intent();
        intent.setComponent(componentName);
        intent.putExtra("cmd", "install");
        intent.putExtra("filepath", str);
        this.mContext.startService(intent);
    }

    public void uninstallAppSilent(String str) {
        try {
            execRootCmdSilent("pm uninstall " + str);
        } catch (Exception unused) {
            Log.e(TAG, "uninstall package " + str + " faild");
        }
    }

    public void uninstallAppAndInstall(String str, String str2) {
        try {
            execRootCmdSilent("pm uninstall " + str2);
        } catch (Exception unused) {
            Log.e(TAG, "uninstall package " + str2 + " faild");
        }
        installApp(str);
    }

    public void installAppSilentAndRebootSystem(String str, int i) {
        reboot(i);
        installApp(str);
    }

    public void installAppAndStartUp(String str, String str2) {
        if (this.mContext == null) {
            Log.e("上下文为空，不执行", "请检查是否已调用setContext()");
            return;
        }
        ComponentName componentName = new ComponentName("com.ztl.helper", "com.ztl.helper.ZTLHelperService");
        Intent intent = new Intent();
        intent.setComponent(componentName);
        intent.putExtra("cmd", "start_up_app");
        intent.putExtra("package", str2);
        this.mContext.startService(intent);
        Intent intent2 = new Intent();
        intent2.setComponent(new ComponentName("com.ztl.helper", "com.ztl.helper.ZTLHelperService"));
        intent2.putExtra("cmd", "install");
        intent2.putExtra("filepath", str);
        intent2.putExtra("package", str2);
        this.mContext.startService(intent2);
    }

    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public int setUninstallPackage(java.lang.String r14) {
        /*
            r13 = this;
            java.lang.String r0 = "persist.ztl.forbituninstall1"
            java.lang.String r1 = ";"
            java.lang.String r2 = "1"
            java.lang.String r3 = "persist.ztl.forbituninstall"
            java.lang.String r4 = r13.getSystemProperty(r3, r2)     // Catch: java.lang.Exception -> Lb9
            boolean r4 = r4.equals(r2)     // Catch: java.lang.Exception -> Lb9
            if (r4 == 0) goto L15
            r14 = -1
            goto Lbe
        L15:
            int r4 = r14.length()     // Catch: java.lang.Exception -> Lb9
            r5 = 200(0xc8, float:2.8E-43)
            r6 = 1
            r7 = -2
            if (r4 > r5) goto L32
            r13.setSystemProperty(r3, r14)     // Catch: java.lang.Exception -> Lb9
            java.lang.String r0 = r13.getSystemProperty(r3, r2)     // Catch: java.lang.Exception -> Lb9
            boolean r14 = r0.equals(r14)     // Catch: java.lang.Exception -> Lb9
            if (r14 == 0) goto L2f
        L2c:
            r14 = r6
            goto Lbe
        L2f:
            r14 = r7
            goto Lbe
        L32:
            java.lang.String[] r14 = r14.split(r1)     // Catch: java.lang.Exception -> Lb9
            java.lang.String r4 = ""
            r8 = 0
            r9 = r4
            r10 = r9
        L3b:
            int r11 = r14.length     // Catch: java.lang.Exception -> Lb9
            if (r8 >= r11) goto L9d
            r11 = r14[r8]     // Catch: java.lang.Exception -> Lb9
            java.lang.String r11 = r11.trim()     // Catch: java.lang.Exception -> Lb9
            boolean r11 = r11.equals(r4)     // Catch: java.lang.Exception -> Lb9
            if (r11 == 0) goto L4b
            goto L9a
        L4b:
            java.lang.StringBuilder r11 = new java.lang.StringBuilder     // Catch: java.lang.Exception -> Lb9
            r11.<init>()     // Catch: java.lang.Exception -> Lb9
            java.lang.StringBuilder r11 = r11.append(r9)     // Catch: java.lang.Exception -> Lb9
            r12 = r14[r8]     // Catch: java.lang.Exception -> Lb9
            java.lang.StringBuilder r11 = r11.append(r12)     // Catch: java.lang.Exception -> Lb9
            java.lang.String r11 = r11.toString()     // Catch: java.lang.Exception -> Lb9
            boolean r12 = r10.equals(r4)     // Catch: java.lang.Exception -> Lb9
            if (r12 == 0) goto L83
            int r11 = r11.length()     // Catch: java.lang.Exception -> Lb9
            if (r11 <= r5) goto L6b
            goto L83
        L6b:
            java.lang.StringBuilder r11 = new java.lang.StringBuilder     // Catch: java.lang.Exception -> Lb9
            r11.<init>()     // Catch: java.lang.Exception -> Lb9
            java.lang.StringBuilder r9 = r11.append(r9)     // Catch: java.lang.Exception -> Lb9
            r11 = r14[r8]     // Catch: java.lang.Exception -> Lb9
            java.lang.StringBuilder r9 = r9.append(r11)     // Catch: java.lang.Exception -> Lb9
            java.lang.StringBuilder r9 = r9.append(r1)     // Catch: java.lang.Exception -> Lb9
            java.lang.String r9 = r9.toString()     // Catch: java.lang.Exception -> Lb9
            goto L9a
        L83:
            java.lang.StringBuilder r11 = new java.lang.StringBuilder     // Catch: java.lang.Exception -> Lb9
            r11.<init>()     // Catch: java.lang.Exception -> Lb9
            java.lang.StringBuilder r10 = r11.append(r10)     // Catch: java.lang.Exception -> Lb9
            r11 = r14[r8]     // Catch: java.lang.Exception -> Lb9
            java.lang.StringBuilder r10 = r10.append(r11)     // Catch: java.lang.Exception -> Lb9
            java.lang.StringBuilder r10 = r10.append(r1)     // Catch: java.lang.Exception -> Lb9
            java.lang.String r10 = r10.toString()     // Catch: java.lang.Exception -> Lb9
        L9a:
            int r8 = r8 + 1
            goto L3b
        L9d:
            r13.setSystemProperty(r3, r9)     // Catch: java.lang.Exception -> Lb9
            java.lang.String r14 = r13.getSystemProperty(r3, r2)     // Catch: java.lang.Exception -> Lb9
            boolean r14 = r14.equals(r9)     // Catch: java.lang.Exception -> Lb9
            if (r14 == 0) goto L2f
            r13.setSystemProperty(r0, r10)     // Catch: java.lang.Exception -> Lb9
            java.lang.String r14 = r13.getSystemProperty(r0, r2)     // Catch: java.lang.Exception -> Lb9
            boolean r14 = r14.equals(r10)     // Catch: java.lang.Exception -> Lb9
            if (r14 == 0) goto L2f
            goto L2c
        Lb9:
            r14 = move-exception
            r14.printStackTrace()
            r14 = -3
        Lbe:
            return r14
        */
        throw new UnsupportedOperationException("Method not decompiled: ZtlApi.ZtlManager.setUninstallPackage(java.lang.String):int");
    }

    public String getUninstallPackage() {
        try {
            String systemProperty = getSystemProperty("persist.ztl.forbituninstall", IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE);
            if (systemProperty.equals(IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE)) {
                return systemProperty;
            }
            String systemProperty2 = getSystemProperty("persist.ztl.forbituninstall1", IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE);
            if (!systemProperty2.trim().equals("") && !systemProperty2.trim().equals(IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE)) {
                if (!systemProperty.trim().endsWith(";")) {
                    systemProperty = systemProperty + ";";
                }
                return systemProperty + systemProperty2;
            }
            return systemProperty;
        } catch (Exception e) {
            e.printStackTrace();
            return MqttServiceConstants.TRACE_ERROR;
        }
    }

    public void keepActivity(String str) {
        if (this.mContext == null) {
            Log.e("上下文为空，不执行", "请检查是否已调用setContext()");
            return;
        }
        ComponentName componentName = new ComponentName("com.ztl.appservice", "com.ztl.appservice.BasicService");
        Intent intent = new Intent();
        intent.setComponent(componentName);
        intent.putExtra("cmd", "keepActivity");
        intent.putExtra("package", str);
        this.mContext.startService(intent);
    }

    public void keepActivity(String str, int i) {
        if (this.mContext == null) {
            Log.e("上下文为空，不执行", "请检查是否已调用setContext()");
            return;
        }
        if (i < 2) {
            Log.e(TAG, "sec can't < 2");
        }
        ComponentName componentName = new ComponentName("com.ztl.appservice", "com.ztl.appservice.BasicService");
        Intent intent = new Intent();
        intent.setComponent(componentName);
        intent.putExtra("cmd", "keepActivity");
        intent.putExtra("package", str);
        intent.putExtra("time", i);
        this.mContext.startService(intent);
    }

    public void keepTime(int i) {
        if (this.mContext == null) {
            Log.e("上下文为空，不执行", "请检查是否已调用setContext()");
            return;
        }
        if (i < 2) {
            Log.e(TAG, "sec can't < 2");
        }
        ComponentName componentName = new ComponentName("com.ztl.appservice", "com.ztl.appservice.BasicService");
        Intent intent = new Intent();
        intent.setComponent(componentName);
        intent.putExtra("cmd", "keepActivity");
        intent.putExtra("time", i);
        this.mContext.startService(intent);
    }

    public void unKeepActivity() {
        if (this.mContext == null) {
            Log.e("上下文为空，不执行", "请检查是否已调用setContext()");
            return;
        }
        ComponentName componentName = new ComponentName("com.ztl.appservice", "com.ztl.appservice.BasicService");
        Intent intent = new Intent();
        intent.setComponent(componentName);
        intent.putExtra("cmd", "unkeepActivity");
        this.mContext.startService(intent);
    }

    public void keepService(String str, String str2) {
        if (this.mContext == null) {
            Log.e("上下文为空，不执行", "请检查是否已调用setContext()");
            return;
        }
        ComponentName componentName = new ComponentName("com.ztl.appservice", "com.ztl.appservice.BasicService");
        Intent intent = new Intent();
        intent.setComponent(componentName);
        intent.putExtra("cmd", "keepService");
        intent.putExtra("package", str);
        intent.putExtra(NotificationCompat.CATEGORY_SERVICE, str2);
        this.mContext.startService(intent);
    }

    public void keepService(String str, String str2, int i) {
        if (this.mContext == null) {
            Log.e("上下文为空，不执行", "请检查是否已调用setContext()");
            return;
        }
        if (i < 2) {
            Log.e(TAG, "参数不能小于2");
        }
        ComponentName componentName = new ComponentName("com.ztl.appservice", "com.ztl.appservice.BasicService");
        Intent intent = new Intent();
        intent.setComponent(componentName);
        intent.putExtra("cmd", "keepService");
        intent.putExtra("package", str);
        intent.putExtra(NotificationCompat.CATEGORY_SERVICE, str2);
        intent.putExtra("time", i);
        this.mContext.startService(intent);
    }

    public void unkeepService() {
        if (this.mContext == null) {
            Log.e("上下文为空，不执行", "请检查是否已调用setContext()");
            return;
        }
        ComponentName componentName = new ComponentName("com.ztl.appservice", "com.ztl.appservice.BasicService");
        Intent intent = new Intent();
        intent.setComponent(componentName);
        intent.putExtra("cmd", "unkeepService");
        this.mContext.startService(intent);
    }

    public String getSystemDate() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(1) + MqttTopic.TOPIC_LEVEL_SEPARATOR + (calendar.get(2) + 1) + MqttTopic.TOPIC_LEVEL_SEPARATOR + calendar.get(5);
    }

    public String getSystemTime() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(11) + ":" + calendar.get(12) + ":" + calendar.get(13);
    }

    public void syncNetworkTime() {
        if (this.mContext == null) {
            Log.e("上下文为空，不执行", "请检查是否已调用setContext()");
            return;
        }
        ComponentName componentName = new ComponentName("com.ztl.helper", "com.ztl.helper.ZTLHelperService");
        Intent intent = new Intent();
        intent.setComponent(componentName);
        intent.putExtra("cmd", "sync_time_now");
        this.mContext.startService(intent);
    }

    public void setSyncNetworkTimePeroid(int i) {
        if (this.mContext == null) {
            Log.e("上下文为空，不执行", "请检查是否已调用setContext()");
            return;
        }
        ComponentName componentName = new ComponentName("com.ztl.helper", "com.ztl.helper.ZTLHelperService");
        Intent intent = new Intent();
        intent.setComponent(componentName);
        intent.putExtra("cmd", "sync_time_period");
        intent.putExtra("peroid", i);
        this.mContext.startService(intent);
    }

    public void setSystemTime(long j) {
        if (this.mContext == null) {
            Log.e("上下文为空，不执行", "请检查是否已调用setContext()");
            return;
        }
        ComponentName componentName = new ComponentName("com.ztl.helper", "com.ztl.helper.ZTLHelperService");
        Intent intent = new Intent();
        intent.setComponent(componentName);
        intent.putExtra("cmd", "setSystemTime");
        intent.putExtra("time", j);
        this.mContext.startService(intent);
    }

    public void setSystemTime(Calendar calendar) {
        if (this.mContext == null) {
            Log.e("上下文为空，不执行", "请检查是否已调用setContext()");
            return;
        }
        ComponentName componentName = new ComponentName("com.ztl.helper", "com.ztl.helper.ZTLHelperService");
        Intent intent = new Intent();
        intent.setComponent(componentName);
        intent.putExtra("cmd", "setSystemTime");
        intent.putExtra("time", calendar.getTimeInMillis());
        this.mContext.startService(intent);
    }

    public boolean isAutoTimezone() {
        Context context = this.mContext;
        if (context == null) {
            Log.e("上下文为空，不执行", "请检查是否已调用setContext()");
            return false;
        }
        try {
            return Settings.Global.getInt(context.getContentResolver(), "auto_time_zone") > 0;
        } catch (Settings.SettingNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean isAutoDateTime() {
        Context context = this.mContext;
        if (context == null) {
            Log.e("上下文为空，不执行", "请检查是否已调用setContext()");
            return false;
        }
        try {
            return Settings.Global.getInt(context.getContentResolver(), "auto_time") > 0;
        } catch (Settings.SettingNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void setAutoDateTime(boolean z) {
        Context context = this.mContext;
        if (context == null) {
            Log.e("上下文为空，不执行", "请检查是否已调用setContext()");
        } else {
            Settings.Global.putInt(context.getContentResolver(), "auto_time", z ? 1 : 0);
        }
    }

    public void setAutoTimezone(boolean z) {
        Context context = this.mContext;
        if (context == null) {
            Log.e("上下文为空，不执行", "请检查是否已调用setContext()");
        } else {
            Settings.Global.putInt(context.getContentResolver(), "auto_time_zone", z ? 1 : 0);
        }
    }

    private boolean isNew() {
        Log.e("第一次设置", "getprop  powerontime" + getSystemProperty(Constant.PERSIST_SYS_POWERONTIME, "new"));
        Log.e("第一次设置", "getprop  isopenalarm" + getSystemProperty("persist.sys.isopenalarm", "new"));
        Log.e("第一次设置", "getprop  powerOffTime" + getSystemProperty("persist.sys.powerOffTime", "new"));
        Log.e("第一次设置", "getprop  powerOffEnable" + getSystemProperty("persist.sys.powerOffEnable", "new"));
        Log.e("第一次设置", "getprop  powerOffEveryday" + getSystemProperty("persist.sys.powerOffEveryday", "new"));
        Log.e("第一次设置", "getprop  powerOffTimeMillis" + getSystemProperty("persist.sys.powerOffTimeMillis", "new"));
        Log.e("第一次设置", "getprop  iseverydayalarm" + getSystemProperty("persist.sys.iseverydayalarm", "new"));
        boolean z = GetInstance().getSystemProperty(Constant.PERSIST_SYS_POWERONTIME, "new").equals("new") && GetInstance().getSystemProperty("persist.sys.isopenalarm", "new").equals("new") && GetInstance().getSystemProperty("persist.sys.powerOffTime", "new").equals("new") && GetInstance().getSystemProperty("persist.sys.powerOffEnable", "new").equals("new") && GetInstance().getSystemProperty("persist.sys.powerOffEveryday", "new").equals("new") && GetInstance().getSystemProperty("persist.sys.powerOffTimeMillis", "new").equals("new") && GetInstance().getSystemProperty("persist.sys.iseverydayalarm", "new").equals("new") && isAppInstalled("com.ztl.helper");
        Log.e(TAG, "定时开关机是否新接口" + z);
        return z;
    }

    private boolean isAppInstalled(String str) {
        Context context = this.mContext;
        if (context == null) {
            Log.e("上下文为空，不执行", "请检查是否已调用setContext()");
            return false;
        }
        try {
            context.getPackageManager().getPackageInfo(str, 1);
            return true;
        } catch (PackageManager.NameNotFoundException unused) {
            return false;
        }
    }

    public void setPoweroffEveryday(int i, int i2, boolean z) {
        setPoweroffCustom(i, i2, 127, z);
    }

    public void setPoweronEveryday(int i, int i2, boolean z) {
        setPoweronCustom(i, i2, 127, z);
    }

    public void setPoweroffOnce(int i, int i2, int i3, int i4, int i5, boolean z) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(i, i2 - 1, i3, i4, i5, 0);
        _setPowerOnOff(calendar, "timingOff", 0, z);
    }

    public void setPoweronOnce(int i, int i2, int i3, int i4, int i5, boolean z) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(i, i2 - 1, i3, i4, i5, 0);
        _setPowerOnOff(calendar, "timingOn", 0, z);
    }

    public void setPoweronCustom(int i, int i2, int i3, boolean z) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(calendar.get(1), calendar.get(2), calendar.get(5), i, i2, 0);
        _setPowerOnOff(calendar, "timingOn", i3, z);
    }

    public void setPoweroffCustom(int i, int i2, int i3, boolean z) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(calendar.get(1), calendar.get(2), calendar.get(5), i, i2, 0);
        _setPowerOnOff(calendar, "timingOff", i3, z);
    }

    private void _setPowerOnOff(Calendar calendar, String str, int i, boolean z) {
        if (!isAppInstalled("com.ztl.helper")) {
            Log.e(TAG, "助手不存在，不使用新接口");
        } else {
            Log.i(TAG, "定时关机使用新接口,设置时间为：" + calendar.getTimeInMillis());
            setSchedulePowerOnAndOff(str, calendar.getTimeInMillis(), i, z);
        }
    }

    private void setSchedulePowerOnAndOff(String str, long j, int i, boolean z) {
        ComponentName componentName = new ComponentName("com.ztl.helper", "com.ztl.helper.ZTLHelperService");
        Intent intent = new Intent();
        intent.setComponent(componentName);
        intent.putExtra("cmd", str);
        intent.putExtra("time", j);
        intent.putExtra("weekdays", i);
        intent.putExtra("enable", z);
        this.mContext.startService(intent);
    }

    public void timingRebootAlarm(int i, int i2, int i3, int i4, int i5, boolean z) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(1, i);
        calendar.set(2, i2);
        calendar.set(5, i3);
        calendar.set(11, i4);
        calendar.set(12, i5);
        setTimingReboot(calendar.getTimeInMillis(), 0, z);
        Log.i("设置一次性定时重启", "时间为" + i + "年" + (i2 + 1) + "月" + i3 + "日" + i4 + "时" + i5 + "分");
    }

    public void timingReboot(int i, int i2, int i3, boolean z) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(11, i);
        calendar.set(12, i2);
        setTimingReboot(calendar.getTimeInMillis(), i3, z);
        Log.i("设置定时重启", "时间为" + i + "时" + i2 + "分,周期为：" + i3);
    }

    private void setTimingReboot(long j, int i, boolean z) {
        if (this.mContext == null) {
            Log.e("上下文为空，不执行", "请检查是否已调用setContext()");
            return;
        }
        ComponentName componentName = new ComponentName("com.ztl.helper", "com.ztl.helper.ZTLHelperService");
        Intent intent = new Intent();
        intent.setComponent(componentName);
        intent.putExtra("cmd", "timingReboot");
        intent.putExtra("timestamp", j);
        intent.putExtra(TypedValues.CycleType.S_WAVE_PERIOD, i);
        intent.putExtra("enable", z);
        this.mContext.startService(intent);
    }

    void _setPowerOn(long j, boolean z) {
        if (j == 0) {
            setSystemProperty(this.POWER_ON_TIME, PDLayoutAttributeObject.GLYPH_ORIENTATION_VERTICAL_ZERO_DEGREES);
            setSystemProperty(this.IS_OPEN_ALARM, this.ALARM_OFF);
            return;
        }
        setSystemProperty(this.POWER_ON_TIME, j + "");
        setSystemProperty(this.IS_OPEN_ALARM, this.ALARM_ON);
        if (!z) {
            setSystemProperty("persist.sys.iseverydayalarm", PDLayoutAttributeObject.GLYPH_ORIENTATION_VERTICAL_ZERO_DEGREES);
        } else {
            setSystemProperty("persist.sys.iseverydayalarm", IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE);
        }
    }

    public long getStringToDate(String str, String str2) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(str2);
        Date date = new Date();
        try {
            date = simpleDateFormat.parse(str);
            Log.d("steve", " " + date.getYear() + " " + date.getMonth() + " " + date.getDay() + " " + date.getHours() + " " + date.getMinutes() + " " + date.getSeconds());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date.getTime();
    }

    public String getDisplayMode() {
        return getSystemProperty("persist.sys.screenmode", PDLayoutAttributeObject.GLYPH_ORIENTATION_VERTICAL_ZERO_DEGREES);
    }

    public int getDisplayHeight() {
        Context context = this.mContext;
        if (context == null) {
            Log.e("上下文为空，不执行", "请检查是否已调用setContext()");
            return -1;
        }
        WindowManager windowManager = (WindowManager) context.getSystemService("window");
        DisplayMetrics displayMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getRealMetrics(displayMetrics);
        return displayMetrics.heightPixels;
    }

    public String getHDMIState() {
        return execRootCmd("cat /sys/class/switch/hdmi/state");
    }

    public void setHDMIEnable(boolean z) {
        if (!isExist(Constant.HDMI_STATUS_3288)) {
            Log.e(TAG, "节点不存在，系统暂不支持此接口");
        } else if (z) {
            execRootCmdSilent("echo 1 > /sys/class/display/HDMI/enable");
        } else {
            execRootCmdSilent("echo 0 > /sys/class/display/HDMI/enable");
        }
    }

    public String[] getHDMIResolutions() {
        try {
            return loadFileAsString("/sys/class/display/HDMI/modes").split("\n");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void setHDMIResolution(String str) {
        Log.e("设置HDMI分辨率", "5.1 7.1待做");
    }

    public int getDisplayWidth() {
        Context context = this.mContext;
        if (context == null) {
            Log.e("上下文为空，不执行", "请检查是否已调用setContext()");
            return -1;
        }
        WindowManager windowManager = (WindowManager) context.getSystemService("window");
        DisplayMetrics displayMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getRealMetrics(displayMetrics);
        return displayMetrics.widthPixels;
    }

    public int getDisplayDensity() {
        Context context = this.mContext;
        if (context == null) {
            Log.e("上下文为空，不执行", "请检查是否已调用setContext()");
            return -1;
        }
        WindowManager windowManager = (WindowManager) context.getSystemService("window");
        DisplayMetrics displayMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getRealMetrics(displayMetrics);
        return displayMetrics.densityDpi;
    }

    public void setDisplayDensity(int i) {
        int i2;
        Log.d(TAG, "set lcd density value = " + i);
        String strValueOf = "160";
        getSystemProperty("ro.sf.lcd_density", "160");
        try {
            i2 = Integer.parseInt(getSystemProperty(this.LCD_DENSITY_PROP, PDLayoutAttributeObject.GLYPH_ORIENTATION_VERTICAL_ZERO_DEGREES));
        } catch (NumberFormatException unused) {
            i2 = -1;
        }
        if (i2 > 0) {
            String str = i2 + "";
        }
        if (i == 0) {
            strValueOf = "120";
        } else if (i != 1) {
            if (i == 2) {
                strValueOf = "240";
            } else if (i != 3) {
                i = Math.abs(i);
                strValueOf = String.valueOf(i);
            } else {
                strValueOf = "320";
            }
        }
        execRootCmdSilent("wm density " + i);
        setSystemProperty(this.LCD_DENSITY_PROP, strValueOf);
    }

    public int getDisplayDensity(int i) {
        Context context = this.mContext;
        if (context == null) {
            Log.e("上下文为空，不执行", "请检查是否已调用setContext()");
            return -1;
        }
        try {
            Display[] displays = ((DisplayManager) context.getSystemService(IdentifyAction.display)).getDisplays();
            DisplayMetrics displayMetrics = new DisplayMetrics();
            displays[i].getRealMetrics(displayMetrics);
            return displayMetrics.densityDpi;
        } catch (Exception unused) {
            return -1;
        }
    }

    public void setDisplayDensity(int i, int i2) {
        Log.d(TAG, "set lcd ID density value = " + i);
        Log.d(TAG, "set lcd density value = " + i2);
        if (i < -1 || i2 < 1) {
            Log.d(TAG, "参数错误");
            return;
        }
        if (i == -1) {
            Display[] displays = ((DisplayManager) this.mContext.getSystemService(IdentifyAction.display)).getDisplays();
            for (int i3 = 0; i3 < displays.length; i3++) {
                displays[i3].getMetrics(new DisplayMetrics());
                try {
                    Runtime.getRuntime().exec("wm density " + i2 + " -d " + displays[i3].getDisplayId());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return;
        }
        execRootCmdSilent("wm density " + i2 + " -d " + i);
    }

    public void reverseBrighness(boolean z) {
        if (z) {
            setSystemProperty("persist.ztl.reverseBri", IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE);
        } else {
            setSystemProperty("persist.ztl.reverseBri", PDLayoutAttributeObject.GLYPH_ORIENTATION_VERTICAL_ZERO_DEGREES);
        }
    }

    public boolean isReverseBrighness() {
        return getSystemProperty("persist.ztl.reverseBri", IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE).equals(IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE);
    }

    public int setLcdBackLight(int i) throws Throwable {
        if (!isExist(this.BlFile)) {
            Log.e(TAG, "lcd bl node not found");
        } else if (i == 1) {
            writeMethod(this.BlFile, this.BlOn);
        } else if (i == 0) {
            writeMethod(this.BlFile, this.BlOff);
        } else {
            Log.e(TAG, "status illegal");
        }
        if (!isExist(this.HdmiEnableFile)) {
            Log.e(TAG, "hdmi enable node not found");
            return -1;
        }
        if (i == 1) {
            writeMethod(this.HdmiEnableFile, this.BlOn);
        } else if (i == 0) {
            writeMethod(this.HdmiEnableFile, this.BlOff);
        } else {
            Log.e(TAG, "status illegal");
            return -1;
        }
        return 0;
    }

    public int getSystemBrightness() {
        Context context = this.mContext;
        if (context == null) {
            Log.e("上下文为空，不执行", "请检查是否已调用setContext()");
            return -1;
        }
        try {
            return Settings.System.getInt(context.getContentResolver(), "screen_brightness");
        } catch (Settings.SettingNotFoundException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public void increaseBrightness() {
        if (this.mContext == null) {
            Log.e("上下文为空，不执行", "请检查是否已调用setContext()");
            return;
        }
        ComponentName componentName = new ComponentName("com.ztl.helper", "com.ztl.helper.ZTLHelperService");
        Intent intent = new Intent();
        intent.setComponent(componentName);
        intent.putExtra("cmd", "increaseBrightness");
        this.mContext.startService(intent);
    }

    public void decreaseBrightness() {
        if (this.mContext == null) {
            Log.e("上下文为空，不执行", "请检查是否已调用setContext()");
            return;
        }
        ComponentName componentName = new ComponentName("com.ztl.helper", "com.ztl.helper.ZTLHelperService");
        Intent intent = new Intent();
        intent.setComponent(componentName);
        intent.putExtra("cmd", "decreaseBrightness");
        this.mContext.startService(intent);
    }

    public void setMinBri(String str) {
        setSystemProperty("persist.ztl.minBri", str);
    }

    public void setMaxBri(String str) {
        setSystemProperty("persist.ztl.maxBri", str);
    }

    public void setBrightness(int i) {
        if (this.mContext == null) {
            Log.e("上下文为空，不执行", "请检查是否已调用setContext()");
            return;
        }
        ComponentName componentName = new ComponentName("com.ztl.helper", "com.ztl.helper.ZTLHelperService");
        Intent intent = new Intent();
        intent.setComponent(componentName);
        intent.putExtra("cmd", "setBrightness");
        intent.putExtra("brightness", i);
        this.mContext.startService(intent);
    }

    public void setDisplayOrientation(int i) {
        if (this.mContext == null) {
            Log.e("上下文为空，不执行", "请检查是否已调用setContext()");
            return;
        }
        if (i == getDisplayOrientation()) {
            Log.e("当前方向", "与旋转方向一致，不执行");
            return;
        }
        int i2 = i / 90;
        try {
            Intent intent = new Intent("ACTION_ZTL_ROTATION");
            intent.putExtra(Key.ROTATION, i2);
            this.mContext.sendBroadcast(intent);
        } catch (Exception unused) {
            Log.e(TAG, "set rotation err!");
        }
    }

    public int getDisplayCount() {
        int i = 0;
        for (int i2 = 0; i2 < 10 && GetInstance().getDisplayOrientation(i2) != -1; i2++) {
            i++;
        }
        return i;
    }

    public void setExtendDisplayOrientation(int i) {
        try {
            setSystemProperty("persist.ztl.extend.rotation", i + "");
            Integer.parseInt(getSystemProperty("persist.ztl.extend.rotation", "-1"));
        } catch (NumberFormatException e) {
            e.printStackTrace();
            Log.e(TAG, "暂不支持此接口，等待后续开发");
        }
    }

    public int setDisplayOrientation(int i, int i2) {
        int i3;
        int i4;
        int i5 = i;
        int i6 = 1;
        String str = "persist.ztl.extend.rotation";
        try {
            if (i5 != -1) {
                if (i5 == 0) {
                    setDisplayOrientation(i2);
                    return 0;
                }
                if (i5 > 1) {
                    i5--;
                    str = "persist.ztl.extend.rotation" + i5;
                }
                int i7 = i5;
                System.out.println("extend-std:" + str);
                try {
                    i3 = Integer.parseInt(GetInstance().getSystemProperty(str, "-1"));
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                    i3 = -1;
                }
                if (i3 == -1) {
                    return i3;
                }
                if (i7 == 3) {
                    GetInstance().setSystemProperty("persist.ztl.extend.rotation242", i2 + "");
                } else if (i7 == 4) {
                    GetInstance().setSystemProperty("persist.ztl.extend.rotation239", i2 + "");
                }
                GetInstance().setSystemProperty(str, i2 + "");
                GetInstance().reboot(0);
                return 0;
            }
            int i8 = 1;
            while (i8 < 10) {
                String str2 = i8 > i6 ? "persist.ztl.extend.rotation" + (i8 - 1) : "persist.ztl.extend.rotation";
                System.out.println("extend3566:" + str2);
                try {
                    i4 = Integer.parseInt(GetInstance().getSystemProperty(str2, "-1"));
                } catch (NumberFormatException e2) {
                    e2.printStackTrace();
                    i4 = -1;
                }
                if (i4 == -1) {
                    break;
                }
                int i9 = i8 - 1;
                if (i9 == 3) {
                    GetInstance().setSystemProperty("persist.ztl.extend.rotation242", i2 + "");
                } else if (i9 == 4) {
                    GetInstance().setSystemProperty("persist.ztl.extend.rotation239", i2 + "");
                }
                GetInstance().setSystemProperty(str2, i2 + "");
                i8++;
                i6 = 1;
            }
            setDisplayOrientation(i2);
            GetInstance().setSystemProperty("persist.ztl.extend.rotation", i2 + "");
            reboot(0);
            return 0;
        } catch (Exception e3) {
            e3.printStackTrace();
            System.out.println("set error");
            return -1;
        }
        e3.printStackTrace();
        System.out.println("set error");
        return -1;
    }

    public int getDisplayOrientation() {
        try {
            String systemProperty = getSystemProperty("persist.ztl.hwrotation", PDLayoutAttributeObject.GLYPH_ORIENTATION_VERTICAL_ZERO_DEGREES);
            System.out.println("state:" + systemProperty + "<<<<");
            return Integer.parseInt(systemProperty);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    public int getDisplayOrientation(int i) {
        if (i < 0) {
            return -1;
        }
        if (i == 0) {
            return getDisplayOrientation();
        }
        String str = "persist.ztl.extend.rotation";
        if (i > 1) {
            str = "persist.ztl.extend.rotation" + (i - 1);
        }
        System.out.println("extend:" + str);
        try {
            return Integer.parseInt(GetInstance().getSystemProperty(str, "-1"));
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public static String setUsbTouchEnable(Context context, boolean z, boolean z2) {
        try {
            return context.getContentResolver().getType(Uri.parse("content://com.ztl.helper.ZtlApi/UsbTouch_" + z + "_" + z2));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public int getTouchOrientation() {
        try {
            return Integer.valueOf(getSystemProperty(this.TP_ORIENTATION_PROP, PDLayoutAttributeObject.GLYPH_ORIENTATION_VERTICAL_ZERO_DEGREES)).intValue() * 90;
        } catch (Exception unused) {
            return 0;
        }
    }

    public void setTouchOrientation(int i, boolean z) {
        try {
            setSystemProperty(this.TP_ORIENTATION_PROP, Integer.toString(i / 90));
            if (z) {
                rebootSystem();
            }
        } catch (Exception unused) {
        }
    }

    public void setSplitScreenLeftRightEnable(boolean z) {
        if (z) {
            setSystemProperty("persist.sys.leftRightEnable", "true");
        } else {
            setSystemProperty("persist.sys.leftRightEnable", "false");
        }
    }

    public void setSplitScreenUpDownEnable(boolean z) {
        if (z) {
            setSystemProperty("persist.sys.upDownEnable", "true");
        } else {
            setSystemProperty("persist.sys.upDownEnable", "false");
        }
    }

    public String[] getScreenModes() {
        Log.e("ztllib", "unsupport fucntion now for this board.todo later.");
        return null;
    }

    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void setScreenMode(java.lang.String r11) {
        /*
            Method dump skipped, instruction units count: 302
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: ZtlApi.ZtlManager.setScreenMode(java.lang.String):void");
    }

    public int setDisplayResolution(String str) {
        String str2;
        String str3;
        if (this.mContext == null) {
            Log.e("上下文为空，不执行", "请检查是否已调用setContext()");
            return -1;
        }
        try {
            str2 = str + ".cfg";
        } catch (Exception unused) {
        }
        if (new File("/ztloem/etc/lcd/" + str2).exists()) {
            str3 = "/ztloem/etc/lcd/" + str2;
        } else {
            if (new File("/system/etc/lcd/" + str2).exists()) {
                str3 = "/system/etc/lcd/" + str2;
            }
            return -1;
        }
        if (new File("/system/bin/ztlparamservice").exists()) {
            execRootCmd("/system/bin/ztlparamservice 1 " + str3);
            return 0;
        }
        if (new File("/system/bin/lcdparamservice").exists()) {
            execRootCmd("/system/bin/lcdparamservice 1 " + str3);
            return 0;
        }
        return -1;
    }

    public ArrayList<String> getDisplayResolutionList() {
        if (this.mContext == null) {
            Log.e("上下文为空，不执行", "请检查是否已调用setContext()");
            return null;
        }
        ArrayList<String> arrayList = new ArrayList<>();
        try {
            for (File file : new File("/ztloem/etc/lcd/").listFiles()) {
                try {
                    String name = file.getName();
                    if (name.endsWith(".cfg")) {
                        arrayList.add(name.replace(".cfg", ""));
                    }
                } catch (Exception unused) {
                }
            }
        } catch (Exception unused2) {
        }
        if (arrayList.size() <= 0) {
            try {
                for (File file2 : new File("/system/etc/lcd/").listFiles()) {
                    try {
                        String name2 = file2.getName();
                        if (name2.endsWith(".cfg")) {
                            arrayList.add(name2.replace(".cfg", ""));
                        }
                    } catch (Exception unused3) {
                    }
                }
            } catch (Exception unused4) {
            }
        }
        return arrayList;
    }

    public String getDisplayResolution() {
        if (this.mContext == null) {
            Log.e("上下文为空，不执行", "请检查是否已调用setContext()");
            return null;
        }
        try {
            String[] strArrSplit = execRootCmd("wm size").split("\n");
            String strSubstring = strArrSplit[0].trim().substring(strArrSplit[0].trim().lastIndexOf(" "));
            return Integer.parseInt(strSubstring.split("x")[0].trim()) + "x" + Integer.parseInt(strSubstring.split("x")[1].trim());
        } catch (Exception unused) {
            return null;
        }
    }

    public void setFontSize(int i) {
        try {
            setFontScale(new float[]{0.85f, 1.0f, 1.15f, 1.3f}[i]);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setFontScale(float f) {
        try {
            Settings.System.putFloat(this.mContext.getContentResolver(), "font_scale", f);
            this.mContext.getContentResolver().notifyChange(Settings.System.getUriFor("font_scale"), null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setWallpaper(String str) {
        if (this.mContext == null) {
            Log.e("上下文为空，不执行", "请检查是否已调用setContext()");
            return;
        }
        ComponentName componentName = new ComponentName("com.ztl.helper", "com.ztl.helper.ZTLHelperService");
        Intent intent = new Intent();
        intent.setComponent(componentName);
        intent.putExtra("cmd", "Wallpaper");
        intent.putExtra("filepath", str);
        this.mContext.startService(intent);
    }

    public String getMacAddress() {
        try {
            return loadFileAsString("/sys/class/net/eth0/address").toUpperCase().substring(0, 17);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String getLocalIpAddress() {
        try {
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            while (networkInterfaces.hasMoreElements()) {
                Enumeration<InetAddress> inetAddresses = networkInterfaces.nextElement().getInetAddresses();
                while (inetAddresses.hasMoreElements()) {
                    InetAddress inetAddressNextElement = inetAddresses.nextElement();
                    if (!inetAddressNextElement.isLoopbackAddress() && !inetAddressNextElement.getHostAddress().toString().contains("::")) {
                        return inetAddressNextElement.getHostAddress().toString();
                    }
                }
            }
            return null;
        } catch (SocketException unused) {
            return null;
        }
    }

    NetworkInfo getConnectedType() {
        NetworkInfo activeNetworkInfo;
        Context context = this.mContext;
        if (context == null || (activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo()) == null || !activeNetworkInfo.isAvailable()) {
            return null;
        }
        return activeNetworkInfo;
    }

    public int getNetWorkType() {
        if (this.mContext == null) {
            Log.e("上下文为空，不执行", "请检查是否已调用setContext()");
            return -1;
        }
        NetworkInfo connectedType = getConnectedType();
        if (connectedType == null) {
            return -1;
        }
        if (connectedType.getType() == 9) {
            return 0;
        }
        if (connectedType.getType() == 1) {
            return 1;
        }
        if (connectedType.getType() == 0) {
            int subtype = connectedType.getSubtype();
            TelephonyManager telephonyManager = (TelephonyManager) this.mContext.getSystemService("phone");
            int i = 2;
            if (subtype != 1 && subtype != 2 && subtype != 4 && (subtype != 16 || telephonyManager.isNetworkRoaming())) {
                i = 3;
                if (subtype != 3 && subtype != 8 && (subtype != 5 || telephonyManager.isNetworkRoaming())) {
                    if (subtype == 13) {
                        return 4;
                    }
                }
            }
            return i;
        }
        return -1;
    }

    public void keepWifiConnect(String str, String str2) {
        if (this.mContext == null) {
            Log.e("上下文为空，不执行", "请检查是否已调用setContext()");
            return;
        }
        ComponentName componentName = new ComponentName("com.ztl.helper", "com.ztl.helper.ZTLHelperService");
        Intent intent = new Intent();
        intent.setComponent(componentName);
        intent.putExtra("cmd", "keepWifiConnect");
        intent.putExtra("ssid", str);
        intent.putExtra("psw", str2);
        this.mContext.startService(intent);
    }

    public void stopKeepWifiConnect() {
        if (this.mContext == null) {
            Log.e("上下文为空，不执行", "请检查是否已调用setContext()");
            return;
        }
        ComponentName componentName = new ComponentName("com.ztl.helper", "com.ztl.helper.ZTLHelperService");
        Intent intent = new Intent();
        intent.setComponent(componentName);
        intent.putExtra("cmd", "stopKeepWifiConnect");
        this.mContext.startService(intent);
    }

    public void setNetAdb(boolean z) {
        if (this.mContext == null) {
            Log.e("上下文为空，不执行", "请检查是否已调用setContext()");
            return;
        }
        ComponentName componentName = new ComponentName("com.ztl.helper", "com.ztl.helper.ZTLHelperService");
        Intent intent = new Intent();
        intent.setComponent(componentName);
        intent.putExtra("cmd", "set_net_adb");
        intent.putExtra("enable", z);
        this.mContext.startService(intent);
    }

    public void openAp(String str, String str2) {
        if (this.mContext == null) {
            Log.e("上下文为空，不执行", "请检查是否已调用setContext()");
            return;
        }
        ComponentName componentName = new ComponentName("com.ztl.helper", "com.ztl.helper.ZTLHelperService");
        Intent intent = new Intent();
        intent.setComponent(componentName);
        intent.putExtra("cmd", "openAP");
        intent.putExtra("ssid", str);
        intent.putExtra("psw", str2);
        this.mContext.startService(intent);
    }

    public void openAp(String str, String str2, int i) {
        if (this.mContext == null) {
            Log.e("上下文为空，不执行", "请检查是否已调用setContext()");
            return;
        }
        ComponentName componentName = new ComponentName("com.ztl.helper", "com.ztl.helper.ZTLHelperService");
        Intent intent = new Intent();
        intent.setComponent(componentName);
        intent.putExtra("cmd", "openAP");
        intent.putExtra("ssid", str);
        intent.putExtra("psw", str2);
        intent.putExtra("apBand", i);
        this.mContext.startService(intent);
    }

    public void closeAp() {
        if (this.mContext == null) {
            Log.e("上下文为空，不执行", "请检查是否已调用setContext()");
            return;
        }
        ComponentName componentName = new ComponentName("com.ztl.helper", "com.ztl.helper.ZTLHelperService");
        Intent intent = new Intent();
        intent.setComponent(componentName);
        intent.putExtra("cmd", "closeAP");
        this.mContext.startService(intent);
    }

    public String getIPv4(String str) {
        try {
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            while (networkInterfaces.hasMoreElements()) {
                NetworkInterface networkInterfaceNextElement = networkInterfaces.nextElement();
                if (str.equals(networkInterfaceNextElement.getDisplayName())) {
                    Enumeration<InetAddress> inetAddresses = networkInterfaceNextElement.getInetAddresses();
                    while (inetAddresses.hasMoreElements()) {
                        InetAddress inetAddressNextElement = inetAddresses.nextElement();
                        if (!inetAddressNextElement.isLoopbackAddress() && (inetAddressNextElement instanceof Inet4Address)) {
                            return inetAddressNextElement.getHostAddress().toString();
                        }
                    }
                }
            }
            return null;
        } catch (Exception unused) {
            Log.e(TAG, "获取IP信息出错");
            return null;
        }
    }

    public void setEthIP(boolean z, String str, String str2, String str3, String str4, String str5) {
        if (this.mContext == null) {
            Log.e("上下文为空，不执行", "请检查是否已调用setContext()");
            return;
        }
        ComponentName componentName = new ComponentName("com.ztl.helper", "com.ztl.helper.ZTLHelperService");
        Intent intent = new Intent();
        intent.setComponent(componentName);
        intent.putExtra("cmd", "set_ethip");
        intent.putExtra("staitc", z);
        intent.putExtra(Constant.ETH_SET_IP, str);
        intent.putExtra("mask", str2);
        intent.putExtra("gate", str3);
        intent.putExtra("dns", str4);
        intent.putExtra(Constant.ETH_DNS2, str5);
        this.mContext.startService(intent);
    }

    public void setEthIP(boolean z, String str, String str2, String str3, String str4, String str5, String str6) {
        if (this.mContext == null) {
            Log.e("上下文为空，不执行", "请检查是否已调用setContext()");
            return;
        }
        ComponentName componentName = new ComponentName("com.ztl.helper", "com.ztl.helper.ZTLHelperService");
        Intent intent = new Intent();
        intent.setComponent(componentName);
        intent.putExtra("cmd", "set_ethip");
        intent.putExtra("staitc", z);
        intent.putExtra(NamingTable.TAG, str);
        intent.putExtra(Constant.ETH_SET_IP, str2);
        intent.putExtra("mask", str3);
        intent.putExtra("gate", str4);
        intent.putExtra("dns", str5);
        intent.putExtra(Constant.ETH_DNS2, str6);
        this.mContext.startService(intent);
    }

    public void setWifiIP(String str, String str2, boolean z, String str3, String str4, String str5, String str6, String str7) {
        if (this.mContext == null) {
            Log.e("上下文为空，不执行", "请检查是否已调用setContext()");
            return;
        }
        ComponentName componentName = new ComponentName("com.ztl.helper", "com.ztl.helper.ZTLHelperService");
        Intent intent = new Intent();
        intent.setComponent(componentName);
        intent.putExtra("cmd", "set_wifiip");
        intent.putExtra("ssid", str);
        intent.putExtra("psw", str2);
        intent.putExtra("staitc", z);
        intent.putExtra(Constant.ETH_SET_IP, str3);
        intent.putExtra("mask", str4);
        intent.putExtra("gate", str5);
        intent.putExtra("dns", str6);
        intent.putExtra(Constant.ETH_DNS2, str7);
        this.mContext.startService(intent);
    }

    public void setWifiApConfig(String str, String str2, int i, int i2, boolean z) {
        Intent intent = new Intent();
        intent.setAction("com.ztl.wifisetting");
        intent.putExtra("SSID", str);
        intent.putExtra("Password", str2);
        intent.putExtra("Channel", i);
        intent.putExtra("WifiMode", i2);
        intent.putExtra("Enable", z);
        this.mContext.sendBroadcast(intent);
    }

    public void enable4GReset(boolean z) {
        if (this.mContext == null) {
            Log.e("上下文为空，不执行", "请检查是否已调用setContext()");
            return;
        }
        ComponentName componentName = new ComponentName("com.ztl.helper", "com.ztl.helper.ZTLHelperService");
        Intent intent = new Intent();
        intent.setComponent(componentName);
        intent.putExtra("cmd", "4greset");
        intent.putExtra("enable", z);
        this.mContext.startService(intent);
    }

    public String getImei() {
        Context context = this.mContext;
        if (context == null) {
            Log.e("上下文为空，不执行", "请检查是否已调用setContext()");
            return "";
        }
        return ((TelephonyManager) context.getSystemService("phone")).getDeviceId();
    }

    public String getSimTel() {
        Context context = this.mContext;
        if (context == null) {
            Log.e("上下文为空，不执行", "请检查是否已调用setContext()");
            return "";
        }
        return ((TelephonyManager) context.getSystemService("phone")).getLine1Number();
    }

    public String getSimIccid() {
        Context context = this.mContext;
        if (context == null) {
            Log.e("上下文为空，不执行", "请检查是否已调用setContext()");
            return "";
        }
        return ((TelephonyManager) context.getSystemService("phone")).getSimSerialNumber();
    }

    public String getSimImsi() {
        Context context = this.mContext;
        if (context == null) {
            Log.e("上下文为空，不执行", "请检查是否已调用setContext()");
            return "";
        }
        return ((TelephonyManager) context.getSystemService("phone")).getSubscriberId();
    }

    public String getBaseBand() {
        try {
            Class<?> cls = Class.forName("android.os.SystemProperties");
            return (String) cls.getMethod("get", String.class, String.class).invoke(cls.newInstance(), "gsm.version.baseband", "no message");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String getSimOperator() {
        Context context = this.mContext;
        if (context == null) {
            Log.e("上下文为空，不执行", "请检查是否已调用setContext()");
            return "";
        }
        String simOperator = ((TelephonyManager) context.getSystemService("phone")).getSimOperator();
        return simOperator != null ? (simOperator.equals("46000") || simOperator.equals("46002") || simOperator.equals("46007")) ? "CMCC" : simOperator.equals("46001") ? "CUCC" : simOperator.equals("46003") ? "CT" : "" : "";
    }

    public String hasAPN(String str) {
        Context context = this.mContext;
        if (context == null) {
            Log.e("上下文为空，不执行", "请检查是否已调用setContext()");
            return null;
        }
        try {
            return context.getContentResolver().getType(Uri.parse("content://com.ztl.helper.ZtlApi/ztlapn_" + str));
        } catch (Exception unused) {
            return null;
        }
    }

    public void setAPN(String str) {
        if (this.mContext == null) {
            Log.e("上下文为空，不执行", "请检查是否已调用setContext()");
            return;
        }
        ComponentName componentName = new ComponentName("com.ztl.helper", "com.ztl.helper.ZTLHelperService");
        Intent intent = new Intent();
        intent.setComponent(componentName);
        intent.putExtra("cmd", "apn");
        intent.putExtra(NamingTable.TAG, str);
        this.mContext.startService(intent);
    }

    final void writeMethod(String str, String str2) throws Throwable {
        BufferedWriter bufferedWriter;
        BufferedWriter bufferedWriter2 = null;
        try {
            try {
                bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(str, true)));
            } catch (Throwable th) {
                th = th;
            }
        } catch (Exception e) {
            e = e;
        }
        try {
            bufferedWriter.write(str2);
            bufferedWriter.flush();
            try {
                bufferedWriter.close();
            } catch (IOException e2) {
                e2.printStackTrace();
            }
        } catch (Exception e3) {
            e = e3;
            bufferedWriter2 = bufferedWriter;
            e.printStackTrace();
            if (bufferedWriter2 != null) {
                try {
                    bufferedWriter2.close();
                } catch (IOException e4) {
                    e4.printStackTrace();
                }
            }
        } catch (Throwable th2) {
            th = th2;
            bufferedWriter2 = bufferedWriter;
            if (bufferedWriter2 != null) {
                try {
                    bufferedWriter2.close();
                } catch (IOException e5) {
                    e5.printStackTrace();
                }
            }
            throw th;
        }
    }

    public String getFileType(String str) {
        if (!isExist(str)) {
            return "";
        }
        String name = new File(str).getName();
        return name.substring(name.lastIndexOf(".") + 1);
    }

    public boolean isExist(String str) {
        try {
            return new File(str).exists();
        } catch (Exception unused) {
            return false;
        }
    }

    String loadFileAsString(String str) throws IOException {
        StringBuffer stringBuffer = new StringBuffer(1000);
        BufferedReader bufferedReader = new BufferedReader(new FileReader(str));
        char[] cArr = new char[1024];
        while (true) {
            int i = bufferedReader.read(cArr);
            if (i != -1) {
                stringBuffer.append(String.valueOf(cArr, 0, i));
            } else {
                bufferedReader.close();
                return stringBuffer.toString();
            }
        }
    }

    public boolean openZtlI2C(String str, int i, int i2) {
        if (this.ztlI2C == null) {
            ZtlI2C ztlI2C = new ZtlI2C();
            this.ztlI2C = ztlI2C;
            if (ztlI2C.open(str, i, i2)) {
                return true;
            }
            this.ztlI2C = null;
            return false;
        }
        Log.e(TAG, "openZtlI2C()  上次使用未关闭,有泄露，不执行");
        return false;
    }

    public void flashWrite(int i, byte[] bArr, int i2) {
        ZtlI2C ztlI2C = this.ztlI2C;
        if (ztlI2C == null) {
            Log.e(TAG, "flashWrite() ztlI2C 为空，不执行");
        } else {
            ztlI2C.flash_write(i, bArr, i2);
        }
    }

    public byte[] flashRead(int i, int i2) {
        ZtlI2C ztlI2C = this.ztlI2C;
        if (ztlI2C == null) {
            Log.e(TAG, "flashRead() ztlI2C 为空，不执行");
            return null;
        }
        return ztlI2C.flash_read(i, i2);
    }

    public void chipWrite(int i, byte[] bArr, int i2) {
        ZtlI2C ztlI2C = this.ztlI2C;
        if (ztlI2C == null) {
            Log.e(TAG, "chipWrite() ztlI2C 为空，不执行");
        } else {
            ztlI2C.chip_write(i, bArr, i2);
        }
    }

    public byte[] chipRead(int i, int i2) {
        ZtlI2C ztlI2C = this.ztlI2C;
        if (ztlI2C == null) {
            Log.e(TAG, "chipRead() ztlI2C 为空，不执行");
            return null;
        }
        return ztlI2C.chip_read(i, i2);
    }

    public void closeI2C() {
        ZtlI2C ztlI2C = this.ztlI2C;
        if (ztlI2C != null) {
            ztlI2C.close();
            this.ztlI2C = null;
        }
    }

    public String getADCValue() {
        if (isExist("/sys/class/adc_dev/adc_test/adc_test")) {
            try {
                return execRootCmd("cat /sys/class/adc_dev/adc_test/adc_test");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public int gpioStringToInt(String str) {
        if (!str.contains("GPIO")) {
            Log.e(TAG, "传入参数错误,请传入GPIO7_A5之类的，实际以规格书为准");
            return -1;
        }
        return (((((str.charAt(4) - '0') & 255) * 32) + ((str.charAt(6) - 'A') * 8)) + str.charAt(7)) - 48;
    }

    public int setGpioValue(int i, boolean z, boolean z2) {
        if (devType.contains("3288")) {
            devType = "rk3288";
        } else if (devType.contains("3126")) {
            devType = "rk3126";
        } else if (devType.contains("a64")) {
            devType = "A64";
        } else if (devType.contains("3368")) {
            devType = "rk3368";
        } else if (devType.contains("3399")) {
            devType = "rk3399";
        } else {
            Log.e(TAG, "该系统暂不支持该接口");
            return -1;
        }
        if (i < 0) {
            Log.e(TAG, "输入值" + i + " 错误，请输入正确的参数");
            return -2;
        }
        String[] strArr = Gpio.GpioNameMap.get(devType);
        Gpio gpio = new Gpio();
        if (!gpio.open(strArr[i])) {
            return -3;
        }
        gpio.setValue(z ? "in" : "out", z2 ? 1 : 0);
        return 1;
    }

    public int getGpioValue(int i) {
        if (!getAndroidVersion().contains("5.1")) {
            return -1;
        }
        Gpio gpio = new Gpio();
        gpio.open(new String[]{"", "GPIO0_C2", "GPIO7_B5", "GPIO8_B0", "GPIO7_B4", "GPIO7_C5", "GPIO7_B3", "GPIO8_A2", "GPIO7_A6", "GPIO8_A1", "GPIO7_A5"}[i]);
        return gpio.getValue();
    }

    public void setGpioValue(String str, int i) {
        if (!str.contains("GPIO")) {
            Log.e(TAG, "传入参数错误,请传入GPIO7_A5之类的，实际以规格书为准");
            return;
        }
        Gpio gpio = new Gpio();
        if (gpio.open(str)) {
            gpio.setValue("out", i);
        }
    }

    public void setGpioValue(int i, int i2) {
        Gpio gpio = new Gpio();
        if (gpio.open(i)) {
            gpio.setValue("out", i2);
        }
    }

    public int getGpioValue(String str) {
        if (!str.contains("GPIO")) {
            Log.e(TAG, "传入参数错误,请传入GPIO7_A5之类的，实际以规格书为准");
            return -1;
        }
        Gpio gpio = new Gpio();
        if (gpio.open(str)) {
            return gpio.getValue();
        }
        return -1;
    }

    public void setGpioDirection(String str, String str2) {
        if (!str.contains("GPIO")) {
            Log.e(TAG, "传入参数错误,请传入GPIO7_A5之类的，实际以规格书为准");
            return;
        }
        Gpio gpio = new Gpio();
        if (gpio.open(str)) {
            gpio.setDirection(str2);
        }
    }

    public String getGpioDirection(String str) {
        if (!str.contains("GPIO")) {
            Log.e(TAG, "传入参数错误,请传入GPIO7_A5之类的，实际以规格书为准");
            return null;
        }
        Gpio gpio = new Gpio();
        if (gpio.open(str)) {
            return gpio.getDirection();
        }
        return null;
    }

    public int setGpio(String str, String str2, int i) {
        System.out.println("Build.TIME=" + Build.TIME);
        byte b = -1;
        if (Build.TIME - 1685080206000L <= 0) {
            return -1;
        }
        if (!str.contains("IO")) {
            Log.e(TAG, "ioName传入参数错误,请传入IO1、IO2、IO3……之类的，实际以规格书为准");
            return -2;
        }
        try {
            String strTrim = getSystemProperty("ro.ztl.gpio", "").trim();
            if (!strTrim.equals("")) {
                String[] strArrSplit = strTrim.split(";");
                if (strArrSplit.length > 0) {
                    int i2 = 0;
                    while (i2 < strArrSplit.length) {
                        try {
                            int i3 = i2 + 1;
                            if (str.equals("IO" + i3)) {
                                String str3 = strArrSplit[i2];
                                if (str3 != null && !str3.trim().equals("")) {
                                    if (strArrSplit[i2].equals("-1")) {
                                        Log.e(TAG, "预留");
                                        return -4;
                                    }
                                    Gpio gpio = new Gpio();
                                    try {
                                        if (!gpio.open(Integer.parseInt(strArrSplit[i2]))) {
                                            Log.e(TAG, "IO打开失败");
                                            return -6;
                                        }
                                        int iHashCode = str2.hashCode();
                                        if (iHashCode != 3365) {
                                            if (iHashCode == 110414 && str2.equals("out")) {
                                                b = 0;
                                            }
                                        } else if (str2.equals("in")) {
                                            b = 1;
                                        }
                                        if (b == 0) {
                                            gpio.setValue(str2, i);
                                            return 0;
                                        }
                                        if (b == 1) {
                                            gpio.setDirection(str2);
                                            return 0;
                                        }
                                        Log.e(TAG, "direction传入参数错误,请传入out、in，实际以规格书为准");
                                        return -7;
                                    } catch (NumberFormatException e) {
                                        e.printStackTrace();
                                        Log.e(TAG, "系统属性数字错误");
                                        return -5;
                                    }
                                }
                                Log.e(TAG, "系统属性错误");
                                return -3;
                            }
                            i2 = i3;
                        } catch (Exception e2) {
                            e2.printStackTrace();
                            Log.e(TAG, "未找到IO");
                            return -8;
                        }
                    }
                }
            }
            Log.e(TAG, "系统不支持");
            return -1;
        } catch (Exception e3) {
            e3.printStackTrace();
            Log.e(TAG, "读取系统属性错误");
            return -9;
        }
    }

    public GpioValue getGpio(String str) {
        GpioValue gpioValue = new GpioValue();
        gpioValue.retFlag = -1;
        System.out.println("Build.TIME=" + Build.TIME);
        if (Build.TIME - 1685080206000L <= 0) {
            return gpioValue;
        }
        if (!str.contains("IO")) {
            Log.e(TAG, "ioName传入参数错误,请传入IO1、IO2、IO3……之类的，实际以规格书为准");
            gpioValue.retFlag = -2;
            return gpioValue;
        }
        try {
            String strTrim = getSystemProperty("ro.ztl.gpio", "").trim();
            if (!strTrim.equals("")) {
                String[] strArrSplit = strTrim.split(";");
                if (strArrSplit.length > 0) {
                    int i = 0;
                    while (i < strArrSplit.length) {
                        try {
                            int i2 = i + 1;
                            if (str.equals("IO" + i2)) {
                                String str2 = strArrSplit[i];
                                if (str2 != null && !str2.trim().equals("")) {
                                    if (strArrSplit[i].equals("-1")) {
                                        Log.e(TAG, "预留");
                                        gpioValue.retFlag = -4;
                                        return gpioValue;
                                    }
                                    Gpio gpio = new Gpio();
                                    try {
                                        if (!gpio.open(Integer.parseInt(strArrSplit[i]))) {
                                            Log.e(TAG, "IO打开失败");
                                            gpioValue.retFlag = -6;
                                            return gpioValue;
                                        }
                                        gpioValue.direction = gpio.getDirection();
                                        gpioValue.value = gpio.getValue();
                                        gpioValue.retFlag = 0;
                                        return gpioValue;
                                    } catch (NumberFormatException e) {
                                        e.printStackTrace();
                                        Log.e(TAG, "系统属性数字错误");
                                        gpioValue.retFlag = -5;
                                        return gpioValue;
                                    }
                                }
                                Log.e(TAG, "系统属性错误");
                                gpioValue.retFlag = -3;
                                return gpioValue;
                            }
                            i = i2;
                        } catch (Exception e2) {
                            e2.printStackTrace();
                            Log.e(TAG, "未找到IO");
                            gpioValue.retFlag = -8;
                            return gpioValue;
                        }
                    }
                }
            }
            Log.e(TAG, "系统不支持");
            gpioValue.retFlag = -1;
            return gpioValue;
        } catch (Exception e3) {
            e3.printStackTrace();
            Log.e(TAG, "读取系统属性错误");
            gpioValue.retFlag = -9;
            return gpioValue;
        }
    }

    public int getSystemMaxVolume() {
        Context context = this.mContext;
        if (context == null) {
            Log.e("上下文为空，不执行", "请检查是否已调用setContext()");
            return -1;
        }
        return ((AudioManager) context.getSystemService(MimeTypes.BASE_TYPE_AUDIO)).getStreamMaxVolume(3);
    }

    public int getSystemCurrenVolume() {
        Context context = this.mContext;
        if (context == null) {
            Log.e("上下文为空，不执行", "请检查是否已调用setContext()");
            return -1;
        }
        return ((AudioManager) context.getSystemService(MimeTypes.BASE_TYPE_AUDIO)).getStreamVolume(3);
    }

    public int setRaiseSystemVolume() {
        int systemCurrenVolume = getSystemCurrenVolume() + 1;
        if (systemCurrenVolume > getSystemMaxVolume()) {
            systemCurrenVolume = getSystemMaxVolume();
        }
        return setSystemVolumeIndex(systemCurrenVolume);
    }

    public int setLowerSystemVolume() {
        int systemCurrenVolume = getSystemCurrenVolume() - 1;
        if (systemCurrenVolume < 0) {
            systemCurrenVolume = 0;
        }
        return setSystemVolumeIndex(systemCurrenVolume);
    }

    public int setSystemVolumeIndex(int i) {
        Context context = this.mContext;
        if (context == null) {
            Log.e("上下文为空，不执行", "请检查是否已调用setContext()");
            return -1;
        }
        try {
            AudioManager audioManager = (AudioManager) context.getSystemService(MimeTypes.BASE_TYPE_AUDIO);
            int streamMaxVolume = audioManager.getStreamMaxVolume(3);
            if (i < 0 || i > streamMaxVolume) {
                return 0;
            }
            audioManager.setStreamVolume(3, i, 5);
            return 0;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    public void setVolume(int i, int i2) {
        if (this.mContext == null) {
            Log.e("上下文为空，不执行", "请检查是否已调用setContext()");
            return;
        }
        ComponentName componentName = new ComponentName("com.ztl.helper", "com.ztl.helper.ZTLHelperService");
        Intent intent = new Intent();
        intent.setComponent(componentName);
        intent.putExtra("cmd", "sys_sound");
        intent.putExtra("streamType", i);
        intent.putExtra("value", i2);
        this.mContext.startService(intent);
    }

    public int getVolume(int i) {
        Context context = this.mContext;
        if (context == null) {
            Log.e("上下文为空，不执行", "请检查是否已调用setContext()");
            return -1;
        }
        return ((AudioManager) context.getSystemService(MimeTypes.BASE_TYPE_AUDIO)).getStreamVolume(i);
    }

    public int getMaxVolume(int i) {
        Context context = this.mContext;
        if (context == null) {
            Log.e("上下文为空，不执行", "请检查是否已调用setContext()");
            return -1;
        }
        return ((AudioManager) context.getSystemService(MimeTypes.BASE_TYPE_AUDIO)).getStreamMaxVolume(i);
    }

    public void setHorn(int i) {
        if (this.mContext == null) {
            Log.e("上下文为空，不执行", "请检查是否已调用setContext()");
            return;
        }
        Intent intent = new Intent("ZTL.CONFIGCHANGE");
        intent.putExtra("skip_permission", true);
        intent.putExtra("config", "soundcfg");
        intent.putExtra("soundcfg", i);
        this.mContext.sendBroadcast(intent);
    }

    public void setCameraOrientation(int i) {
        if (i < 0 || i > 360) {
            Log.e(TAG, "set camera orientation value(" + i + ") err!,set close");
            return;
        }
        String str = i + "";
        Log.d(TAG, "set camera orientation value = " + i);
        try {
            setSystemProperty(this.CAMERA_ORIENTATION_PROP, str);
        } catch (Exception unused) {
            Log.w(TAG, "Unable to set camera orientation");
        }
    }

    public int getCameraOrientation() {
        try {
            return Integer.parseInt(getSystemProperty("persist.sys.cameraOrientation", PDLayoutAttributeObject.GLYPH_ORIENTATION_VERTICAL_ZERO_DEGREES));
        } catch (Exception unused) {
            return 0;
        }
    }

    public boolean isCameraMirror() {
        return !getSystemProperty("persist.ztl.ismirror", PDLayoutAttributeObject.GLYPH_ORIENTATION_VERTICAL_ZERO_DEGREES).contains(PDLayoutAttributeObject.GLYPH_ORIENTATION_VERTICAL_ZERO_DEGREES);
    }

    public void setCameraMirror(boolean z) {
        setSystemProperty("persist.ztl.ismirror", z ? IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE : PDLayoutAttributeObject.GLYPH_ORIENTATION_VERTICAL_ZERO_DEGREES);
    }

    public String getSystemProperty(String str, String str2) {
        String str3;
        try {
            Class<?> cls = Class.forName("android.os.SystemProperties");
            str3 = (String) cls.getDeclaredMethod("get", String.class).invoke(cls.newInstance(), str);
        } catch (Exception unused) {
            String strReplace = execRootCmd("getprop " + str).replace("\n", "");
            if (!strReplace.isEmpty()) {
                return strReplace;
            }
        }
        return !TextUtils.isEmpty(str3) ? str3 : str2;
    }

    public void setSystemProperty(String str, String str2) {
        try {
            Class<?> cls = Class.forName("android.os.SystemProperties");
            cls.getMethod("set", String.class, String.class).invoke(cls, str, str2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    PendingIntent getPendingIntent(String str, int i) {
        Intent intent = new Intent();
        intent.setAction(str);
        return PendingIntent.getBroadcast(this.mContext, i, intent, 268435456);
    }

    public void openMonitor() {
        if (this.mContext == null) {
            Log.e("上下文为空，不执行", "请检查是否已调用setContext()");
            return;
        }
        ComponentName componentName = new ComponentName("com.ztl.helper", "com.ztl.helper.ZTLHelperService");
        Intent intent = new Intent();
        intent.setComponent(componentName);
        intent.putExtra("cmd", "open_monitor");
        this.mContext.startService(intent);
    }

    public void open_zhuBanInfo() {
        if (this.mContext == null) {
            Log.e("上下文为空，不执行", "请检查是否已调用setContext()");
            return;
        }
        ComponentName componentName = new ComponentName("com.ztl.helper", "com.ztl.helper.ZTLHelperService");
        Intent intent = new Intent();
        intent.setComponent(componentName);
        intent.putExtra("cmd", "zhuBan_info");
        this.mContext.startService(intent);
    }

    public String[] getCPUFreq() {
        if (this.cpuInfo == null) {
            CpuInfo cpuInfo = new CpuInfo();
            this.cpuInfo = cpuInfo;
            cpuInfo.Init(null);
        }
        return CpuInfo.SubCore.getFreq();
    }

    public void setCPUFreq(String str) {
        if (this.cpuInfo == null) {
            CpuInfo cpuInfo = new CpuInfo();
            this.cpuInfo = cpuInfo;
            cpuInfo.Init(null);
        }
        this.cpuInfo.setCPUFreq(str);
    }

    public int getCPUTemp() {
        String onelinevalue = getOnelinevalue("/sys/bus/platform/drivers/tsadc/ff280000.tsadc/temp1_input");
        if (onelinevalue != null) {
            return Integer.valueOf(onelinevalue).intValue();
        }
        return -1;
    }

    static String getOnelinevalue(String str) {
        List<String> lines = getLines(str, 1);
        if (lines != null) {
            return lines.get(0);
        }
        return null;
    }

    static List<String> getLines(String str, int i) {
        ArrayList arrayList = new ArrayList();
        try {
            FileReader fileReader = new FileReader(str);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            int i2 = 0;
            while (i2 < i) {
                String line = bufferedReader.readLine();
                if (line == null) {
                    break;
                }
                i2++;
                arrayList.add(line);
            }
            fileReader.close();
            return arrayList;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    @Deprecated
    public void goToSleep() {
        Context context = this.mContext;
        if (context == null) {
            Log.e("上下文为空，不执行", "请检查是否已调用setContext()");
            return;
        }
        PowerManager powerManager = (PowerManager) context.getSystemService("power");
        try {
            powerManager.getClass().getMethod("goToSleep", Long.TYPE).invoke(powerManager, Long.valueOf(SystemClock.uptimeMillis()));
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e2) {
            e2.printStackTrace();
        } catch (InvocationTargetException e3) {
            e3.printStackTrace();
        }
    }

    @Deprecated
    public void setSystemVolumeValue(int i) {
        if (this.mContext == null) {
            Log.e("上下文为空，不执行", "请检查是否已调用setContext()");
            return;
        }
        ComponentName componentName = new ComponentName("com.ztl.helper", "com.ztl.helper.ZTLHelperService");
        Intent intent = new Intent();
        intent.setComponent(componentName);
        intent.putExtra("cmd", "sys_sound");
        intent.putExtra("value", i);
        this.mContext.startService(intent);
    }

    @Deprecated
    public int setAutoDateTime(int i) {
        Context context = this.mContext;
        if (context == null) {
            Log.e("上下文为空，不执行", "请检查是否已调用setContext()");
            return -1;
        }
        try {
            Settings.Global.putInt(context.getContentResolver(), "auto_time", i);
            return 0;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Deprecated
    public String getSystemVersion() {
        return Build.VERSION.RELEASE;
    }

    @Deprecated
    public void wakeUp() {
        PowerManager powerManager = (PowerManager) this.mContext.getSystemService("power");
        try {
            powerManager.getClass().getMethod("wakeUp", Long.TYPE).invoke(powerManager, Long.valueOf(SystemClock.uptimeMillis()));
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e2) {
            e2.printStackTrace();
        } catch (InvocationTargetException e3) {
            e3.printStackTrace();
        }
    }

    @Deprecated
    public void shutDownSystem() {
        execRootCmdSilent("reboot -p");
    }

    @Deprecated
    public void rebootSystem() {
        if (this.mContext == null) {
            Log.e("上下文为空，不执行", "请检查是否已调用setContext()");
            return;
        }
        this.mContext.sendBroadcast(new Intent("reboot"));
        execRootCmdSilent("reboot");
    }

    @Deprecated
    public void recoverySystem() {
        if (this.mContext == null) {
            Log.e("上下文为空，不执行", "请检查是否已调用setContext()");
            return;
        }
        Intent intent = new Intent("android.intent.action.MASTER_CLEAR");
        intent.putExtra("isReformate", true);
        this.mContext.sendBroadcast(intent);
    }

    @Deprecated
    public void setOpenSystemBar() {
        if (this.mContext == null) {
            Log.e("上下文为空，不执行", "请检查是否已调用setContext()");
            return;
        }
        Intent intent = new Intent("com.ding.systembar.chang");
        intent.putExtra("enable", PDLayoutAttributeObject.GLYPH_ORIENTATION_VERTICAL_ZERO_DEGREES);
        this.mContext.sendBroadcast(intent);
    }

    @Deprecated
    public void setCloseSystemBar() {
        if (this.mContext == null) {
            Log.e("上下文为空，不执行", "请检查是否已调用setContext()");
            return;
        }
        Intent intent = new Intent("com.ding.systembar.chang");
        intent.putExtra("enable", IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE);
        this.mContext.sendBroadcast(intent);
    }

    @Deprecated
    public int getSystemBarState() {
        int i;
        try {
            i = Integer.parseInt(getSystemProperty("persist.sys.barState", IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE));
        } catch (NumberFormatException unused) {
            i = -1;
        }
        return i == 0 ? 1 : 0;
    }

    @Deprecated
    public void setOpenUsbDebug() {
        if (this.mContext == null) {
            Log.e("上下文为空，不执行", "请检查是否已调用setContext()");
            return;
        }
        Intent intent = new Intent("com.ding.adbsetting");
        intent.putExtra("enable", IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE);
        this.mContext.sendBroadcast(intent);
    }

    @Deprecated
    public void setCloseUsbDebug() {
        if (this.mContext == null) {
            Log.e("上下文为空，不执行", "请检查是否已调用setContext()");
            return;
        }
        Intent intent = new Intent("com.ding.adbsetting");
        intent.putExtra("enable", PDLayoutAttributeObject.GLYPH_ORIENTATION_VERTICAL_ZERO_DEGREES);
        this.mContext.sendBroadcast(intent);
    }

    @Deprecated
    public int getUsbDebugState() {
        return Integer.valueOf(getSystemProperty("persist.sys.adbState", IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE)).intValue();
    }

    @Deprecated
    public void getDefaultInputMethod(String str) {
        if (this.mContext == null) {
            Log.e("上下文为空，不执行", "getDefaultInputMethod请检查是否已调用setContext()");
            return;
        }
        ComponentName componentName = new ComponentName("com.ztl.helper", "com.ztl.helper.ZTLHelperService");
        Intent intent = new Intent();
        intent.setComponent(componentName);
        intent.putExtra("cmd", "default_input_method");
        intent.putExtra("id", str);
        this.mContext.startService(intent);
    }

    @Deprecated
    public void setSystemDate(int i, int i2, int i3) {
        LOGD("set system Date " + i + MqttTopic.TOPIC_LEVEL_SEPARATOR + i2 + MqttTopic.TOPIC_LEVEL_SEPARATOR + i3);
        Calendar calendar = Calendar.getInstance();
        calendar.set(1, i);
        calendar.set(2, i2 - 1);
        calendar.set(5, i3);
        SystemClock.setCurrentTimeMillis(calendar.getTimeInMillis());
    }

    @Deprecated
    public void setSystemTime(int i, int i2, int i3, int i4) {
        LOGD("set system time " + i + ":" + i2);
        Calendar calendar = Calendar.getInstance();
        calendar.set(11, i);
        calendar.set(12, i2);
        calendar.set(13, i3);
        calendar.set(14, i4);
        SystemClock.setCurrentTimeMillis(calendar.getTimeInMillis());
    }

    @Deprecated
    public void setSystemDateAndTime(int i, int i2, int i3, int i4, int i5) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(1, Integer.parseInt(String.valueOf(i)));
        calendar.set(2, Integer.parseInt(String.valueOf(i2)) - 1);
        calendar.set(5, Integer.parseInt(String.valueOf(i3)));
        calendar.set(11, Integer.parseInt(String.valueOf(i4)));
        calendar.set(12, Integer.parseInt(String.valueOf(i5)));
        SystemClock.setCurrentTimeMillis(calendar.getTimeInMillis());
    }

    @Deprecated
    public int setAutoTimeZone(int i) {
        Context context = this.mContext;
        if (context == null) {
            Log.e("上下文为空，不执行", "请检查是否已调用setContext()");
            return -1;
        }
        try {
            Settings.Global.putInt(context.getContentResolver(), "auto_time_zone", i);
            return 0;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Deprecated
    public int setRaiseSystemBrightness() {
        return setSystemBrightness(getSystemBrightness() + 1);
    }

    @Deprecated
    public int setLowerSystemBrightness() {
        return setSystemBrightness(getSystemBrightness() - 1);
    }

    @Deprecated
    public int setSystemBrightness(int i) {
        Context context = this.mContext;
        if (context == null) {
            Log.e("上下文为空，不执行", "请检查是否已调用setContext()");
            return -1;
        }
        try {
            if (i >= 0 && i <= 255) {
                try {
                    Settings.System.putInt(context.getContentResolver(), "screen_brightness", i);
                    this.mContext.getContentResolver().notifyChange(Settings.System.getUriFor("screen_brightness"), null);
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

    @Deprecated
    public int getTpOrientation() {
        try {
            return Integer.valueOf(getSystemProperty(this.TP_ORIENTATION_PROP, PDLayoutAttributeObject.GLYPH_ORIENTATION_VERTICAL_ZERO_DEGREES)).intValue();
        } catch (Exception unused) {
            return 0;
        }
    }

    @Deprecated
    public void setTpOrientation(int i, boolean z) {
        if (i < 0 || i > 3) {
            return;
        }
        try {
            setSystemProperty(this.TP_ORIENTATION_PROP, Integer.toString(i));
            if (z) {
                execRootCmdSilent("reboot");
            }
        } catch (Exception unused) {
        }
    }

    @Deprecated
    public boolean hasWatchDog() {
        if (isExist("/proc/ztl-wdog/wdog")) {
            return true;
        }
        Log.e(TAG, "系统暂不支持看门狗，如需使用该功能，请联系技术支持");
        return false;
    }

    @Deprecated
    public boolean openWatchDog() {
        if (isOpenWatchDog) {
            Log.e(TAG, "已经打开看门狗，请勿重复打开");
            return true;
        }
        if (isExist("/proc/ztl-wdog/wdog")) {
            Log.e(TAG, "正在使能看门狗");
            execRootCmdSilent("echo 1 > /proc/ztl-wdog/wdog");
            isOpenWatchDog = true;
            Log.e(TAG, "已打开看门狗，进行喂狗");
            feedWatchDog();
            return true;
        }
        Log.e(TAG, "系统暂不支持看门狗，如需使用该功能，请联系技术支持");
        return false;
    }

    @Deprecated
    private void feedWatchDog() {
        if (isOpenWatchDog && this.watchDogThread == null) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        Log.e(ZtlManager.TAG, "开始喂狗");
                        ZtlManager.this.execRootCmdSilent("echo 2 > /proc/ztl-wdog/wdog");
                        try {
                            Thread.sleep(20000L);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                            Log.e(ZtlManager.TAG, "喂狗线程已退出");
                            return;
                        }
                    }
                }
            });
            this.watchDogThread = thread;
            thread.start();
        }
    }

    @Deprecated
    public boolean closeWatchDog() {
        Thread thread = this.watchDogThread;
        if (thread != null) {
            thread.interrupt();
            this.watchDogThread = null;
        }
        isOpenWatchDog = false;
        execRootCmdSilent("echo 3 > /proc/ztl-wdog/wdog");
        return isRunWatchDog();
    }

    @Deprecated
    public boolean isRunWatchDog() {
        if (ReadFile("/proc/ztl-wdog/wdog").contains(IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE) || ReadFile("/proc/ztl-wdog/wdog").contains(ExifInterface.GPS_MEASUREMENT_2D)) {
            return true;
        }
        ReadFile("/proc/ztl-wdog/wdog").contains(ExifInterface.GPS_MEASUREMENT_3D);
        return false;
    }

    @Deprecated
    public String watchDogValue() {
        return ReadFile("/proc/ztl-wdog/wdog");
    }

    @Deprecated
    public void setSchedulePowerOn(int i, int i2, boolean z) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(calendar.get(1), calendar.get(2), calendar.get(5), i, i2, 0);
        if (isNew()) {
            calendar.getTimeInMillis();
            setSchedulePowerOnAndOff("timingOn", calendar.getTimeInMillis(), 127, z);
            return;
        }
        System.currentTimeMillis();
        if (!z) {
            calendar.setTimeInMillis(0L);
        }
        _setPowerOn(calendar.getTimeInMillis() / 1000, true);
        Log.d("定时开机设置的时间：", "" + (calendar.getTimeInMillis() / 1000));
    }

    @Deprecated
    public void setSchedulePowerOff(int i, int i2, boolean z) {
        long timeInMillis;
        if (this.mContext == null) {
            Log.e("上下文为空，不执行", "请检查是否已调用setContext()");
            return;
        }
        Calendar calendar = Calendar.getInstance();
        long timeInMillis2 = calendar.getTimeInMillis();
        calendar.set(11, i);
        calendar.set(12, i2);
        calendar.set(13, 0);
        long timeInMillis3 = calendar.getTimeInMillis();
        if (timeInMillis3 < timeInMillis2) {
            calendar.add(5, 1);
            timeInMillis = calendar.getTimeInMillis();
        } else {
            timeInMillis = timeInMillis3;
        }
        if (isNew()) {
            setSchedulePowerOnAndOff("timingOff", timeInMillis, 127, z);
            return;
        }
        if (!z) {
            setSystemProperty("persist.sys.powerOffTime", "unknown");
            setSystemProperty("persist.sys.powerOffEnable", "false");
            Log.d(TAG, "已禁用定时关机");
            return;
        }
        ((AlarmManager) this.mContext.getSystemService(NotificationCompat.CATEGORY_ALARM)).set(0, calendar.getTimeInMillis(), PendingIntent.getBroadcast(this.mContext, 0, new Intent("com.android.settings.action.REQUEST_POWER_OFF"), 268435456));
        setSystemProperty("persist.sys.powerOffTime", i + ":" + i2);
        setSystemProperty("persist.sys.powerOffEnable", "true");
        setSystemProperty("persist.sys.powerOffEveryday", "true");
        setSystemProperty("persist.sys.powerOffTimeMillis", (timeInMillis / 1000) + "");
        Log.i(TAG, "Next time power off " + i + ":" + i2);
    }

    @Deprecated
    public void setPowerOnAlarm(int i, int i2, int i3, int i4, int i5, boolean z) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(i, i2 - 1, i3, i4, i5, 0);
        if (isNew()) {
            setSchedulePowerOnAndOff("timingOn", calendar.getTimeInMillis(), 0, z);
        } else {
            if (!z) {
                calendar.setTimeInMillis(0L);
            }
            _setPowerOn(calendar.getTimeInMillis() / 1000, false);
        }
        Log.d("一次性定时开机设置的时间：", "" + (calendar.getTimeInMillis() / 1000));
    }

    @Deprecated
    public void setPowerOffAlarm(int i, int i2, int i3, int i4, int i5, boolean z) {
        if (this.mContext == null) {
            Log.e("上下文为空，不执行", "请检查是否已调用setContext()");
            return;
        }
        Calendar calendar = Calendar.getInstance();
        long timeInMillis = calendar.getTimeInMillis() / 1000;
        int i6 = i2 - 1;
        calendar.set(i, i6, i3, i4, i5, 0);
        long timeInMillis2 = calendar.getTimeInMillis() / 1000;
        if (isNew()) {
            setSchedulePowerOnAndOff("timingOff", calendar.getTimeInMillis(), 0, z);
        } else if (!z) {
            setSystemProperty("persist.sys.powerOffTime", "unknown");
            setSystemProperty("persist.sys.powerOffEnable", "false");
            Log.d(TAG, "已禁用定时关机");
            return;
        } else {
            if (timeInMillis2 < timeInMillis) {
                Log.d(TAG, "set false tar " + timeInMillis2 + " cur" + timeInMillis);
                setSystemProperty("persist.sys.powerOffEnable", "false");
                return;
            }
            ((AlarmManager) this.mContext.getSystemService(NotificationCompat.CATEGORY_ALARM)).set(0, calendar.getTimeInMillis(), PendingIntent.getBroadcast(this.mContext, 0, new Intent("com.android.settings.action.REQUEST_POWER_OFF"), 268435456));
            setSystemProperty("persist.sys.powerOffTime", i4 + ":" + i5);
            setSystemProperty("persist.sys.powerOffEnable", "true");
            setSystemProperty("persist.sys.powerOffEveryday", "false");
            setSystemProperty("persist.sys.powerOffTimeMillis", timeInMillis2 + "");
        }
        Log.d(TAG, "set next time power off " + i + MqttTopic.TOPIC_LEVEL_SEPARATOR + (i6 + 1) + MqttTopic.TOPIC_LEVEL_SEPARATOR + i3 + " " + i4 + ":" + i5);
    }
}
