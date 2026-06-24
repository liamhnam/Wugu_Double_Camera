package com.wugu.doublecamera.widget;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Process;
import com.wugu.doublecamera.AppConfig;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.Thread;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class CrashHandler implements Thread.UncaughtExceptionHandler {
    private static final String FILE_NAME = "crash";
    private static final String FILE_NAME_SUFFIX = ".log";
    private static final String TAG = "CrashHandler";
    private Context mContext;
    private Thread.UncaughtExceptionHandler mDefaultCrashHandler;

    private CrashHandler() {
    }

    private static class Inner {
        private static final CrashHandler INSTANCE = new CrashHandler();

        private Inner() {
        }
    }

    public static synchronized CrashHandler getInstance() {
        return Inner.INSTANCE;
    }

    public void init(Context context) {
        this.mDefaultCrashHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(this);
        this.mContext = context.getApplicationContext();
    }

    @Override
    public void uncaughtException(Thread thread, Throwable th) {
        try {
            saveExceptionInfo(th);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Thread.UncaughtExceptionHandler uncaughtExceptionHandler = this.mDefaultCrashHandler;
        if (uncaughtExceptionHandler != null) {
            uncaughtExceptionHandler.uncaughtException(thread, th);
        } else {
            Process.killProcess(Process.myPid());
        }
    }

    private void saveExceptionInfo(Throwable th) throws IOException {
        File file = new File(AppConfig.CRASH_LOG_DIR);
        if (!file.exists()) {
            file.mkdirs();
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);
        String str = simpleDateFormat.format(new Date(System.currentTimeMillis()));
        String str2 = simpleDateFormat2.format(new Date(System.currentTimeMillis()));
        try {
            FileWriter fileWriter = new FileWriter(file.getAbsolutePath() + File.separator + FILE_NAME + str + FILE_NAME_SUFFIX, true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            PrintWriter printWriter = new PrintWriter(bufferedWriter);
            printWriter.println(str2);
            dumpPhoneInfo(printWriter);
            printWriter.println();
            th.printStackTrace(printWriter);
            printWriter.flush();
            printWriter.close();
            bufferedWriter.close();
            fileWriter.close();
        } catch (Exception unused) {
        }
    }

    private void dumpPhoneInfo(PrintWriter printWriter) throws PackageManager.NameNotFoundException {
        PackageInfo packageInfo = this.mContext.getPackageManager().getPackageInfo(this.mContext.getPackageName(), 1);
        printWriter.println(String.format("APP Version : %s_%s", packageInfo.versionName, Integer.valueOf(packageInfo.versionCode)));
        printWriter.println(String.format("OS Version : %s_%s", Build.VERSION.RELEASE, Integer.valueOf(Build.VERSION.SDK_INT)));
        printWriter.println(String.format("CPU ABI : %s", Build.CPU_ABI));
        printWriter.println(String.format("Vendor : %s", Build.MANUFACTURER));
        printWriter.println(String.format("Model : %s", Build.MODEL));
    }
}
