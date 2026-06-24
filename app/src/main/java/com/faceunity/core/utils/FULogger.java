package com.faceunity.core.utils;

import android.util.Log;
import androidx.core.app.NotificationCompat;
import com.p020hp.jipp.model.Media;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import org.apache.http.client.methods.HttpTrace;

@Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\f\bÆ\u0002\u0018\u00002\u00020\u0001:\u0001\u0013B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\bH\u0007J\u0018\u0010\n\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\bH\u0007J\u0018\u0010\u000b\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\bH\u0007J\u0018\u0010\f\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\bH\u0007J\b\u0010\r\u001a\u00020\u0006H\u0007J\u0015\u0010\u000e\u001a\u00020\u00062\u0006\u0010\u000f\u001a\u00020\u0004H\u0000¢\u0006\u0002\b\u0010J\u0018\u0010\u0011\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\bH\u0007J\u0018\u0010\u0012\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\bH\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0014"}, m1293d2 = {"Lcom/faceunity/core/utils/FULogger;", "", "()V", "_logLevel", "Lcom/faceunity/core/utils/FULogger$LogLevel;", Media.f727c, "", "tag", "", NotificationCompat.CATEGORY_MESSAGE, Media.f728d, Media.f729e, "i", "printCallStack", "setLogLevel", "level", "setLogLevel$fu_core_all_featureRelease", "t", "w", "LogLevel", "fu_core_all_featureRelease"}, m1294k = 1, m1295mv = {1, 1, 16})
public final class FULogger {
    public static final FULogger INSTANCE = new FULogger();
    private static LogLevel _logLevel = LogLevel.OFF;

    @Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\b\n\u0002\b\t\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000b¨\u0006\f"}, m1293d2 = {"Lcom/faceunity/core/utils/FULogger$LogLevel;", "", "ordinal", "", "(Ljava/lang/String;II)V", HttpTrace.METHOD_NAME, "DEBUG", "INFO", "WARN", "ERROR", "CRITICAL", "OFF", "fu_core_all_featureRelease"}, m1294k = 1, m1295mv = {1, 1, 16})
    public enum LogLevel {
        TRACE(0),
        DEBUG(1),
        INFO(2),
        WARN(3),
        ERROR(4),
        CRITICAL(5),
        OFF(6);

        LogLevel(int i) {
        }
    }

    private FULogger() {
    }

    public final void setLogLevel$fu_core_all_featureRelease(LogLevel level) {
        Intrinsics.checkParameterIsNotNull(level, "level");
        _logLevel = level;
    }

    @JvmStatic
    public static final void printCallStack() {
        StackTraceElement[] stackElements = new Throwable().getStackTrace();
        Intrinsics.checkExpressionValueIsNotNull(stackElements, "stackElements");
        int length = stackElements.length;
        for (int i = 0; i < length; i++) {
            StringBuilder sb = new StringBuilder();
            StackTraceElement stackTraceElement = stackElements[i];
            Intrinsics.checkExpressionValueIsNotNull(stackTraceElement, "stackElements[i]");
            System.out.print((Object) sb.append(stackTraceElement.getClassName()).append("/t").toString());
            StringBuilder sb2 = new StringBuilder();
            StackTraceElement stackTraceElement2 = stackElements[i];
            Intrinsics.checkExpressionValueIsNotNull(stackTraceElement2, "stackElements[i]");
            System.out.print((Object) sb2.append(stackTraceElement2.getFileName()).append("/t").toString());
            StringBuilder sb3 = new StringBuilder();
            StackTraceElement stackTraceElement3 = stackElements[i];
            Intrinsics.checkExpressionValueIsNotNull(stackTraceElement3, "stackElements[i]");
            System.out.print((Object) sb3.append(String.valueOf(stackTraceElement3.getLineNumber())).append("/t").toString());
            StackTraceElement stackTraceElement4 = stackElements[i];
            Intrinsics.checkExpressionValueIsNotNull(stackTraceElement4, "stackElements[i]");
            System.out.println((Object) stackTraceElement4.getMethodName());
            System.out.println((Object) "-----------------------------------");
        }
    }

    @JvmStatic
    public static final void m296t(String tag, String msg) {
        Intrinsics.checkParameterIsNotNull(tag, "tag");
        Intrinsics.checkParameterIsNotNull(msg, "msg");
        if (_logLevel.ordinal() <= LogLevel.TRACE.ordinal()) {
            Log.v(tag, msg);
        }
    }

    @JvmStatic
    public static final void m293d(String tag, String msg) {
        Intrinsics.checkParameterIsNotNull(tag, "tag");
        Intrinsics.checkParameterIsNotNull(msg, "msg");
        if (_logLevel.ordinal() <= LogLevel.DEBUG.ordinal()) {
            Log.d(tag, msg);
        }
    }

    @JvmStatic
    public static final void m295i(String tag, String msg) {
        Intrinsics.checkParameterIsNotNull(tag, "tag");
        Intrinsics.checkParameterIsNotNull(msg, "msg");
        if (_logLevel.ordinal() <= LogLevel.INFO.ordinal()) {
            Log.i(tag, msg);
        }
    }

    @JvmStatic
    public static final void m297w(String tag, String msg) {
        Intrinsics.checkParameterIsNotNull(tag, "tag");
        Intrinsics.checkParameterIsNotNull(msg, "msg");
        if (_logLevel.ordinal() <= LogLevel.WARN.ordinal()) {
            Log.w(tag, msg);
        }
    }

    @JvmStatic
    public static final void m294e(String tag, String msg) {
        Intrinsics.checkParameterIsNotNull(tag, "tag");
        Intrinsics.checkParameterIsNotNull(msg, "msg");
        if (_logLevel.ordinal() <= LogLevel.ERROR.ordinal()) {
            Log.e(tag, msg);
        }
    }

    @JvmStatic
    public static final void m292c(String tag, String msg) {
        Intrinsics.checkParameterIsNotNull(tag, "tag");
        Intrinsics.checkParameterIsNotNull(msg, "msg");
        if (_logLevel.ordinal() <= LogLevel.CRITICAL.ordinal()) {
            Log.e(tag, msg);
        }
    }
}
