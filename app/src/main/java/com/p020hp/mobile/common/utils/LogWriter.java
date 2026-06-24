package com.p020hp.mobile.common.utils;

import android.content.ContentValues;
import android.content.Context;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import androidx.core.app.NotificationCompat;
import com.hjq.permissions.Permission;
import com.tom_roush.fontbox.ttf.NamingTable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Date;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import p066do.p026do.p027do.C2057do;
import p066do.p026do.p027do.Ccase;
import p066do.p026do.p027do.p067goto.C2058do;
import p066do.p026do.p027do.p067goto.C2059if;
import p066do.p026do.p027do.p067goto.Cfor;
import p066do.p026do.p027do.p067goto.Cnew;

@Metadata(m1292d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\bÆ\u0002\u0018\u00002\u00020\u0001:\u0001*B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0016J\u0010\u0010\f\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0016J\u0012\u0010\r\u001a\u0004\u0018\u00010\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0016J\b\u0010\u0011\u001a\u00020\tH\u0016J\u0013\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00140\u0013H\u0016¢\u0006\u0002\u0010\u0015J\u0010\u0010\u0016\u001a\u00020\t2\u0006\u0010\u000f\u001a\u00020\u0010H\u0016J\u0018\u0010\u0016\u001a\u00020\t2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0017\u001a\u00020\u0018H\u0016J\b\u0010\u0019\u001a\u00020\u000bH\u0016J\b\u0010\u001a\u001a\u00020\u000bH\u0016J\b\u0010\u001b\u001a\u00020\u000bH\u0016J\b\u0010\u001c\u001a\u00020\u000bH\u0016J\u0018\u0010\u001d\u001a\u00020\t2\u0006\u0010\u001e\u001a\u00020\u000e2\u0006\u0010\u001f\u001a\u00020 H\u0016J\u0010\u0010!\u001a\u00020\t2\u0006\u0010\"\u001a\u00020\u000bH\u0016J$\u0010#\u001a\u00020\t2\u001a\u0010$\u001a\u0016\u0012\u0006\u0012\u0004\u0018\u00010\u000e\u0012\u0004\u0012\u00020 \u0012\u0004\u0012\u00020\t0%H\u0016J \u0010&\u001a\u00020\t2\u0006\u0010'\u001a\u00020\u000e2\u0006\u0010(\u001a\u00020\u000e2\u0006\u0010)\u001a\u00020 H\u0016R\"\u0010\u0005\u001a\u0004\u0018\u00010\u00042\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006+"}, m1293d2 = {"Lcom/hp/mobile/common/utils/LogWriter;", "Lcom/hp/mobile/common/utils/LogWriterDef;", "()V", "<set-?>", "Lcom/hp/hlog/open/HlogConfig;", "config", "getConfig", "()Lcom/hp/hlog/open/HlogConfig;", "enableLogs", "", "isEnabled", "", "enableSaveLogs", "exportLogFile", "", "context", "Landroid/content/Context;", "flush", "getAllFilesInfo", "", "Ljava/io/File;", "()[Ljava/io/File;", "init", "logConfig", "Lcom/hp/mobile/common/utils/LogWriter$LogConfig;", "isDebug", "isInitialised", "isLogsEnabled", "isSaveLogsEnabled", "onListenerLogWriteStatus", NamingTable.TAG, NotificationCompat.CATEGORY_STATUS, "", "setDebug", "debug", "setOnHlogProtocolStatus", "callback", "Lkotlin/Function2;", "writeLog", "tag", "content", "level", "LogConfig", "common-lib_release"}, m1294k = 1, m1295mv = {1, 6, 0}, m1297xi = 48)
public final class LogWriter implements LogWriterDef {
    public static final LogWriter INSTANCE = new LogWriter();
    public static C2059if config;

    @Metadata(m1292d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\t\n\u0002\u0010\b\n\u0002\b\u000e\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u001a\u0010\u0005\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001e\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0086\u000e¢\u0006\u0010\n\u0002\u0010\u0011\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u001a\u0010\u0012\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\b\"\u0004\b\u0014\u0010\nR\u001a\u0010\u0015\u001a\u00020\u0016X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001aR\u001e\u0010\u001b\u001a\u0004\u0018\u00010\fX\u0086\u000e¢\u0006\u0010\n\u0002\u0010\u0011\u001a\u0004\b\u001c\u0010\u000e\"\u0004\b\u001d\u0010\u0010R\u001e\u0010\u001e\u001a\u0004\u0018\u00010\fX\u0086\u000e¢\u0006\u0010\n\u0002\u0010\u0011\u001a\u0004\b\u001f\u0010\u000e\"\u0004\b \u0010\u0010R\u001a\u0010!\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\"\u0010\b\"\u0004\b#\u0010\n¨\u0006$"}, m1293d2 = {"Lcom/hp/mobile/common/utils/LogWriter$LogConfig;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "cachePath", "", "getCachePath", "()Ljava/lang/String;", "setCachePath", "(Ljava/lang/String;)V", "days", "", "getDays", "()Ljava/lang/Long;", "setDays", "(Ljava/lang/Long;)V", "Ljava/lang/Long;", "encryptKey16", "getEncryptKey16", "setEncryptKey16", "logLevel", "", "getLogLevel", "()I", "setLogLevel", "(I)V", "maxFile", "getMaxFile", "setMaxFile", "minSDCard", "getMinSDCard", "setMinSDCard", "path", "getPath", "setPath", "common-lib_release"}, m1294k = 1, m1295mv = {1, 6, 0}, m1297xi = 48)
    public static final class LogConfig {
        public String cachePath;
        public Long days;
        public String encryptKey16;
        public int logLevel;
        public Long maxFile;
        public Long minSDCard;
        public String path;

        public LogConfig(Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            String absolutePath = context.getFilesDir().getAbsolutePath();
            Intrinsics.checkNotNullExpressionValue(absolutePath, "context.filesDir.absolutePath");
            this.cachePath = absolutePath;
            this.path = context.getFilesDir().getAbsolutePath() + File.separator + "HPSdkLog";
            this.days = 10L;
            this.maxFile = 10L;
            this.logLevel = 3;
            this.encryptKey16 = "0123456789012345";
            this.minSDCard = 50L;
        }

        public final String getCachePath() {
            return this.cachePath;
        }

        public final Long getDays() {
            return this.days;
        }

        public final String getEncryptKey16() {
            return this.encryptKey16;
        }

        public final int getLogLevel() {
            return this.logLevel;
        }

        public final Long getMaxFile() {
            return this.maxFile;
        }

        public final Long getMinSDCard() {
            return this.minSDCard;
        }

        public final String getPath() {
            return this.path;
        }

        public final void setCachePath(String str) {
            Intrinsics.checkNotNullParameter(str, "<set-?>");
            this.cachePath = str;
        }

        public final void setDays(Long l) {
            this.days = l;
        }

        public final void setEncryptKey16(String str) {
            Intrinsics.checkNotNullParameter(str, "<set-?>");
            this.encryptKey16 = str;
        }

        public final void setLogLevel(int i) {
            this.logLevel = i;
        }

        public final void setMaxFile(Long l) {
            this.maxFile = l;
        }

        public final void setMinSDCard(Long l) {
            this.minSDCard = l;
        }

        public final void setPath(String str) {
            Intrinsics.checkNotNullParameter(str, "<set-?>");
            this.path = str;
        }
    }

    public static final void m1571setOnHlogProtocolStatus$lambda6(Function2 tmp0, String str, int i) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(str, Integer.valueOf(i));
    }

    public void enableLogs(boolean isEnabled) {
        Logger.INSTANCE.setEnabled(isEnabled);
    }

    public void enableSaveLogs(boolean isEnabled) {
        Logger.INSTANCE.setSaveLogEnabled(isEnabled);
    }

    public String exportLogFile(Context context) throws Throwable {
        FileOutputStream fileOutputStream;
        FileInputStream fileInputStream;
        OutputStream outputStreamOpenOutputStream;
        Exception e;
        FileInputStream fileInputStream2;
        Intrinsics.checkNotNullParameter(context, "context");
        String str = "";
        if (context != null && C2058do.f2471if != null) {
            C2058do.m1188do();
            File[] fileArrM1190if = C2058do.m1190if();
            if (fileArrM1190if != null && fileArrM1190if.length != 0) {
                int i = 0;
                FileInputStream fileInputStream3 = null;
                if (Build.VERSION.SDK_INT >= 29) {
                    str = Environment.DIRECTORY_DOWNLOADS + "/hp-sdk-log";
                    while (i < fileArrM1190if.length) {
                        try {
                            ContentValues contentValues = new ContentValues();
                            File file = fileArrM1190if[i];
                            contentValues.put("_display_name", Ccase.f2463do.format(new Date(Long.parseLong(file.getName()))));
                            contentValues.put("relative_path", str);
                            outputStreamOpenOutputStream = context.getContentResolver().openOutputStream(context.getContentResolver().insert(MediaStore.Downloads.EXTERNAL_CONTENT_URI, contentValues));
                            try {
                                fileInputStream2 = new FileInputStream(file);
                                try {
                                    try {
                                        new Cfor(C2058do.f3872new.f3876goto, C2058do.f3872new.f3878this).m1191do(fileInputStream2, outputStreamOpenOutputStream);
                                    } catch (Exception e2) {
                                        e = e2;
                                        e.printStackTrace();
                                    }
                                } catch (Throwable th) {
                                    th = th;
                                    fileInputStream3 = fileInputStream2;
                                    try {
                                        fileInputStream3.close();
                                    } catch (Exception e3) {
                                        e3.printStackTrace();
                                    }
                                    try {
                                        outputStreamOpenOutputStream.close();
                                        throw th;
                                    } catch (Exception e4) {
                                        e4.printStackTrace();
                                        throw th;
                                    }
                                }
                            } catch (Exception e5) {
                                e = e5;
                                fileInputStream2 = null;
                                e.printStackTrace();
                                fileInputStream2.close();
                                outputStreamOpenOutputStream.close();
                                i++;
                            } catch (Throwable th2) {
                                th = th2;
                            }
                        } catch (Exception e6) {
                            e = e6;
                            outputStreamOpenOutputStream = null;
                        } catch (Throwable th3) {
                            th = th3;
                            outputStreamOpenOutputStream = null;
                        }
                        try {
                            fileInputStream2.close();
                        } catch (Exception e7) {
                            e7.printStackTrace();
                        }
                        try {
                            outputStreamOpenOutputStream.close();
                        } catch (Exception e8) {
                            e8.printStackTrace();
                        }
                        i++;
                    }
                } else {
                    if (context.checkSelfPermission(Permission.WRITE_EXTERNAL_STORAGE) != 0) {
                        throw new RuntimeException("Please grant the WRITE_EXTERNAL_STORAGE permission");
                    }
                    str = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath() + "/hp-sdk-log";
                    File file2 = new File(str);
                    if (!file2.exists()) {
                        file2.mkdir();
                    }
                    while (i < fileArrM1190if.length) {
                        try {
                            File file3 = fileArrM1190if[i];
                            String str2 = Ccase.f2463do.format(new Date(Long.parseLong(file3.getName())));
                            fileInputStream = new FileInputStream(file3);
                            try {
                                File file4 = new File(file2, str2);
                                if (file4.exists()) {
                                    file4.delete();
                                }
                                fileOutputStream = new FileOutputStream(file4);
                                try {
                                    try {
                                        new Cfor(C2058do.f3872new.f3876goto, C2058do.f3872new.f3878this).m1191do(fileInputStream, fileOutputStream);
                                        try {
                                            fileInputStream.close();
                                        } catch (Exception e9) {
                                            e9.printStackTrace();
                                        }
                                    } catch (Exception e10) {
                                        e = e10;
                                        e.printStackTrace();
                                        try {
                                            fileInputStream.close();
                                        } catch (Exception e11) {
                                            e11.printStackTrace();
                                        }
                                    }
                                } catch (Throwable th4) {
                                    th = th4;
                                    fileInputStream3 = fileInputStream;
                                    try {
                                        fileInputStream3.close();
                                    } catch (Exception e12) {
                                        e12.printStackTrace();
                                    }
                                    try {
                                        fileOutputStream.close();
                                        throw th;
                                    } catch (Exception e13) {
                                        e13.printStackTrace();
                                        throw th;
                                    }
                                }
                            } catch (Exception e14) {
                                e = e14;
                                fileOutputStream = null;
                            } catch (Throwable th5) {
                                th = th5;
                                fileOutputStream = null;
                            }
                        } catch (Exception e15) {
                            e = e15;
                            fileOutputStream = null;
                            fileInputStream = null;
                        } catch (Throwable th6) {
                            th = th6;
                            fileOutputStream = null;
                        }
                        try {
                            fileOutputStream.close();
                        } catch (Exception e16) {
                            e16.printStackTrace();
                        }
                        i++;
                    }
                }
            }
        }
        return str;
    }

    public void flush() {
        C2058do.m1188do();
    }

    public File[] getAllFilesInfo() {
        File[] fileArrM1190if = C2058do.m1190if();
        Intrinsics.checkNotNullExpressionValue(fileArrM1190if, "getAllFilesInfo()");
        return fileArrM1190if;
    }

    public final C2059if getConfig() {
        return config;
    }

    public void init(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        init(context, new LogConfig(context));
    }

    public void init(Context context, LogConfig logConfig) {
        long j;
        long j2;
        long jLongValue;
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(logConfig, "logConfig");
        if (isInitialised()) {
            return;
        }
        String cachePath = logConfig.getCachePath();
        String path = logConfig.getPath();
        int logLevel = logConfig.getLogLevel();
        if (logLevel < 2 || logLevel > 7) {
            throw new IllegalArgumentException("Out of range value: $level, log level must between Log.VERBOSE-Log.ASSERT");
        }
        String encryptKey16 = logConfig.getEncryptKey16();
        byte[] bytes = encryptKey16.getBytes();
        byte[] bytes2 = encryptKey16.getBytes();
        Long days = logConfig.getDays();
        if (days != null) {
            long jLongValue2 = days.longValue();
            if (jLongValue2 > 30 || jLongValue2 < 1) {
                throw new IllegalArgumentException("Out of value range,Saving days value must be in [1-30]");
            }
            j = jLongValue2 * 86400000;
        } else {
            j = 604800000;
        }
        Long maxFile = logConfig.getMaxFile();
        if (maxFile != null) {
            long jLongValue3 = maxFile.longValue();
            if (jLongValue3 > 50 || jLongValue3 < 1) {
                throw new IllegalArgumentException("Out of value range,Max file size must be in [1-50]");
            }
            j2 = jLongValue3 * 1048576;
        } else {
            j2 = 10485760;
        }
        Long minSDCard = logConfig.getMinSDCard();
        if (minSDCard != null) {
            jLongValue = minSDCard.longValue();
            if (jLongValue < 50) {
                throw new IllegalArgumentException("Out of value range, Min SDCard size must be over 50");
            }
        } else {
            jLongValue = 52428800;
        }
        C2059if c2059if = new C2059if();
        c2059if.f2474do = cachePath;
        c2059if.f2475if = path;
        c2059if.f3875for = j2;
        c2059if.f3873case = jLongValue;
        c2059if.f3877new = j;
        c2059if.f3876goto = bytes;
        c2059if.f3878this = bytes2;
        c2059if.f3874else = logLevel;
        config = c2059if;
        C2058do.f3872new = c2059if;
        if (C2057do.f3856catch == null) {
            synchronized (C2057do.class) {
                if (C2057do.f3856catch == null) {
                    C2057do.f3856catch = new C2057do(c2059if);
                }
            }
        }
        C2058do.f2471if = C2057do.f3856catch;
    }

    public boolean isDebug() {
        return C2058do.f3871for;
    }

    public boolean isInitialised() {
        return config != null;
    }

    public boolean isLogsEnabled() {
        return Logger.INSTANCE.isEnabled();
    }

    public boolean isSaveLogsEnabled() {
        return Logger.INSTANCE.isSaveLogEnabled();
    }

    public void onListenerLogWriteStatus(String name, int status) {
        Intrinsics.checkNotNullParameter(name, "name");
        Cnew cnew = C2058do.f2470do;
        if (cnew != null) {
            cnew.mo445do(name, status);
        }
    }

    public void setDebug(boolean debug) {
        C2058do.f3871for = debug;
    }

    public void setOnHlogProtocolStatus(final Function2<? super String, ? super Integer, Unit> callback) {
        Intrinsics.checkNotNullParameter(callback, "callback");
        C2058do.f2470do = new Cnew() {
            @Override
            public final void mo445do(String str, int i) {
                LogWriter.m1571setOnHlogProtocolStatus$lambda6(callback, str, i);
            }
        };
    }

    public void writeLog(String tag, String content, int level) {
        Object objM1772constructorimpl;
        Intrinsics.checkNotNullParameter(tag, "tag");
        Intrinsics.checkNotNullParameter(content, "content");
        if (config != null) {
            try {
                C2058do.m1189do(tag, content, level);
                objM1772constructorimpl = Result.m1772constructorimpl(Unit.INSTANCE);
            } catch (Throwable th) {
                objM1772constructorimpl = Result.m1772constructorimpl(ResultKt.createFailure(th));
            }
            Throwable thM1775exceptionOrNullimpl = Result.m1775exceptionOrNullimpl(objM1772constructorimpl);
            if (thM1775exceptionOrNullimpl != null) {
                Log.e("HLog", "Write to log file failed. Message - `" + tag + ' ' + content + ' ' + level + "` dropped due to " + thM1775exceptionOrNullimpl.getLocalizedMessage());
            }
            Result.m1771boximpl(objM1772constructorimpl);
        }
    }
}
