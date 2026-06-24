package com.wugu.doublecamera.utils;

import ZtlApi.ZtlManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.media.AudioManager;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import androidx.fragment.app.FragmentActivity;
import com.google.android.exoplayer2.util.MimeTypes;
import com.proembed.service.MyService;
import com.wugu.doublecamera.App;
import com.wugu.doublecamera.database.SpManager;
import com.wugu.doublecamera.enums.BoardModelEnum;
import com.wugu.doublecamera.widget.ThreadClock;
import com.wugu.facebeauty.FaceBeautyMain;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public final class AppUtil {
    private static final Handler sHandler = new Handler(Looper.getMainLooper());

    public static void runOnUI(Runnable runnable) {
        sHandler.post(runnable);
    }

    public static void runOnUIDelayed(Runnable runnable, long j) {
        sHandler.postDelayed(runnable, j);
    }

    public static void BlockFuAddress() {
        RunSuCmd("iptables -I INPUT -s 118.31.212.138 -j DROP");
        RunSuCmd("iptables -I INPUT -s 58.49.216.113 -j DROP");
        RunSuCmd("iptables -I INPUT -s internal.faceunity.com -j DROP");
        RunSuCmd("settings put global auto_time_zone 0");
        RunSuCmd("setprop persist.sys.timezone Asia/Shanghai");
    }

    private static void RunSuCmd(String str) {
        try {
            Process processExec = Runtime.getRuntime().exec("su");
            DataOutputStream dataOutputStream = new DataOutputStream(processExec.getOutputStream());
            dataOutputStream.writeBytes(str + "\n");
            dataOutputStream.writeBytes("exit\n");
            dataOutputStream.flush();
            processExec.waitFor();
        } catch (IOException | InterruptedException e) {
            FaceBeautyMain.writeCrashLog(e.toString());
        }
    }

    public static boolean isOutsideView(View view, MotionEvent motionEvent) {
        if (view == null) {
            return true;
        }
        int[] iArr = {0, 0};
        view.getLocationInWindow(iArr);
        int i = iArr[0];
        int i2 = iArr[1];
        return motionEvent.getX() <= ((float) i) || motionEvent.getX() >= ((float) (view.getWidth() + i)) || motionEvent.getY() <= ((float) i2) || motionEvent.getY() >= ((float) (view.getHeight() + i2));
    }

    public static void hideInputImm(FragmentActivity fragmentActivity) {
        InputMethodManager inputMethodManager = (InputMethodManager) fragmentActivity.getSystemService("input_method");
        View currentFocus = fragmentActivity.getCurrentFocus();
        if (inputMethodManager == null || currentFocus == null) {
            return;
        }
        inputMethodManager.hideSoftInputFromWindow(currentFocus.getWindowToken(), 2);
    }

    public static String getInternalSDCardPath() {
        return Environment.getExternalStorageDirectory().getAbsolutePath();
    }

    public static String getCPUSerial() {
        String randomNumber;
        LineNumberReader lineNumberReader;
        int i;
        String line;
        try {
            lineNumberReader = new LineNumberReader(new InputStreamReader(Runtime.getRuntime().exec("cat /proc/cpuinfo").getInputStream()));
        } catch (IOException unused) {
        }
        for (i = 1; i < 100 && (line = lineNumberReader.readLine()) != null; i++) {
            if (line.contains("Serial")) {
                randomNumber = line.substring(line.indexOf(":") + 1).trim();
                break;
            }
            randomNumber = "0000000000000000";
        }
        randomNumber = "0000000000000000";
        if (randomNumber.equals("0000000000000000")) {
            randomNumber = StringUtil.getRandomNumber(16);
        }
        return checkModifyCpuNum(randomNumber);
    }

    private static String checkModifyCpuNum(String str) {
        return str.equals("c11e7e572f4dd78d") ? "c11e7e572f4dd78c" : str;
    }

    public static void setSystemBar(boolean z) {
        if (App.mBoardModel.equals(BoardModelEnum.DING_CHANG_3568)) {
            ZtlManager.GetInstance().openSystemBar(z);
        } else {
            new MyService(App.getInstance()).setHideNavBar(!z);
        }
    }

    public static void setBootStartUp(String str, String str2) {
        if (App.mBoardModel.equals(BoardModelEnum.DING_CHANG_3568)) {
            ZtlManager.GetInstance().setBootPackageActivity(str, str2);
        } else {
            new MyService(App.getInstance()).setAppBoot(str);
        }
    }

    public static void keepActivity(String str) {
        if (App.mBoardModel.equals(BoardModelEnum.DING_CHANG_3568)) {
            ZtlManager.GetInstance().keepActivity(str);
        } else {
            new MyService(App.getInstance()).setAppListen(str, 30);
        }
    }

    public static void keepActivity(String str, int i) {
        if (App.mBoardModel.equals(BoardModelEnum.DING_CHANG_3568)) {
            ZtlManager.GetInstance().keepActivity(str);
        } else {
            new MyService(App.getInstance()).setAppListen(str, i);
        }
    }

    public static void unKeepActivity() {
        if (App.mBoardModel.equals(BoardModelEnum.DING_CHANG_3568)) {
            ZtlManager.GetInstance().unKeepActivity();
        } else {
            new MyService(App.getInstance()).setAppListen(null, 30);
        }
    }

    public static String getSystemTime(String str) {
        return new SimpleDateFormat(str, Locale.getDefault()).format(new Date(System.currentTimeMillis()));
    }

    public static void setSystemTime(int i, int i2, int i3, int i4, int i5, int i6) {
        if (App.mBoardModel.equals(BoardModelEnum.DING_CHANG_3568)) {
            try {
                ZtlManager.GetInstance().setSystemTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).parse(i + "-" + i2 + "-" + i3 + " " + i4 + ":" + i5 + ":" + i6).getTime());
                return;
            } catch (NullPointerException | ParseException e) {
                e.printStackTrace();
                return;
            }
        }
        new MyService(App.getInstance()).setSystemTime(i, i2, i3, i4, i5, i6);
    }

    public static void setSystemTimeMillis(long j) {
        if (App.mBoardModel.equals(BoardModelEnum.DING_CHANG_3568)) {
            ZtlManager.GetInstance().setSystemTime(j);
        } else {
            new MyService(App.getInstance()).setSystemTimeMillis(j);
        }
    }

    public static void switchAutoTime(boolean z) {
        if (App.mBoardModel.equals(BoardModelEnum.DING_CHANG_3568)) {
            return;
        }
        new MyService(App.getInstance()).switchAutoTime(z);
    }

    public static int getVersionCode() {
        try {
            return App.getInstance().getPackageManager().getPackageInfo(App.getInstance().getPackageName(), 0).versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static void installApp(String str, String str2) {
        if (App.mBoardModel.equals(BoardModelEnum.DING_CHANG_3568)) {
            ZtlManager.GetInstance().installApp(str);
        } else {
            new MyService(App.getInstance()).silentInstallApk(str, str2, true);
        }
    }

    public static void powerOff() {
        if (App.mBoardModel.equals(BoardModelEnum.DING_CHANG_3568)) {
            ZtlManager.GetInstance().shutdown();
        } else {
            new MyService(App.getInstance()).shutdownSystem();
        }
    }

    public static void reboot() {
        if (App.mBoardModel.equals(BoardModelEnum.DING_CHANG_3568)) {
            ZtlManager.GetInstance().reboot(0);
        } else {
            new MyService(App.getInstance()).rebootSystem();
        }
    }

    public static boolean isDebug() {
        return (App.getInstance().getApplicationInfo().flags & 2) != 0;
    }

    public static boolean isAvailableNetwork() {
        return App.mIsNetAvailable;
    }

    public static void setVolumeLower() {
        try {
            ((AudioManager) App.getInstance().getSystemService(MimeTypes.BASE_TYPE_AUDIO)).adjustVolume(-1, 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void setVolumeIncrease() {
        try {
            ((AudioManager) App.getInstance().getSystemService(MimeTypes.BASE_TYPE_AUDIO)).adjustVolume(1, 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void setVolumeMute() {
        try {
            ((AudioManager) App.getInstance().getSystemService(MimeTypes.BASE_TYPE_AUDIO)).setStreamVolume(3, 0, 4);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getUsbPath() {
        if (App.mBoardModel.equals(BoardModelEnum.DING_CHANG_3568)) {
            return ZtlManager.GetInstance().getUsbStoragePath();
        }
        return new MyService(App.getInstance()).getUSBStoragePath();
    }

    public static boolean isPortrait(Context context) {
        return context.getResources().getConfiguration().orientation == 1;
    }

    public static boolean isChineseLan(Context context) {
        return "zh".equals(context.getResources().getConfiguration().getLocales().get(0).getLanguage());
    }

    public static String getCountryTimeWeekStr(Context context) {
        String str;
        String customPeDataFormat = SpManager.getInstance().getCustomPeDataFormat();
        if (!TextUtils.isEmpty(customPeDataFormat)) {
            return getSystemTime(customPeDataFormat);
        }
        String language = context.getResources().getConfiguration().getLocales().get(0).getLanguage();
        if ("en".equals(language)) {
            str = "EEEE, MM/dd, yyyy";
        } else {
            str = "vi".equals(language) ? "EEEE, dd/MM/yyyy" : "yyyy年MM月dd日 EEEE";
        }
        return getSystemTime(str);
    }

    public static int getCpuTemp() {
        if (App.mBoardModel.equals(BoardModelEnum.DING_CHANG_3568)) {
            return 0;
        }
        return new MyService(App.getInstance()).getCPUTemperature();
    }

    public static void reopenUsbPower() throws Throwable {
        if (App.mBoardModel.equals(BoardModelEnum.XIANG_CHENG_3399)) {
            FileUtil.saveDataToFile("/proc/proembed/gpio", "150 0");
            FileUtil.saveDataToFile("/proc/proembed/gpio", "153 0");
            ThreadClock.sleep(1000L);
            FileUtil.saveDataToFile("/proc/proembed/gpio", "150 1");
            FileUtil.saveDataToFile("/proc/proembed/gpio", "153 1");
        }
    }
}
