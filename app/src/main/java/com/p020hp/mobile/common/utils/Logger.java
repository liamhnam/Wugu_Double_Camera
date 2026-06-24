package com.p020hp.mobile.common.utils;

import android.util.Log;
import androidx.core.app.NotificationCompat;
import com.p020hp.jipp.model.Media;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(m1292d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u0003\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \u00132\u00020\u0001:\u0001\u0013B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\u0007\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\u0001J\u0010\u0010\n\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\u0001J\u0018\u0010\n\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u000b\u001a\u00020\fJ\u0010\u0010\r\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\u0001J\u0013\u0010\u000e\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\u0001H\u0086\u0002J\u001b\u0010\u000e\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u000f\u001a\u00020\fH\u0086\u0002J\u001c\u0010\u0010\u001a\u00020\b2\u000e\u0010\u0011\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0012H\u0086\bø\u0001\u0000R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006\u0014"}, m1293d2 = {"Lcom/hp/mobile/common/utils/Logger;", "", "tag", "", "(Ljava/lang/String;)V", "getTag", "()Ljava/lang/String;", Media.f728d, "", "any", Media.f729e, "t", "", "i", "invoke", "thrown", "v", NotificationCompat.CATEGORY_CALL, "Lkotlin/Function0;", "Companion", "common-lib_release"}, m1294k = 1, m1295mv = {1, 6, 0}, m1297xi = 48)
public final class Logger {
    public static final String LOG_TAG_PREFIX = "HpMobileSDK";
    public static boolean isEnabled = false;
    public static final String libTag = "CL/1.0.0.5/";
    public final String tag;

    public static final Companion INSTANCE = new Companion(null);
    public static boolean isSaveLogEnabled = true;

    @Metadata(m1292d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0007\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u001a\u0010\u0005\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0007\"\u0004\b\b\u0010\tR\u001a\u0010\n\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u0007\"\u0004\b\u000b\u0010\tR\u000e\u0010\f\u001a\u00020\u0004X\u0080T¢\u0006\u0002\n\u0000¨\u0006\r"}, m1293d2 = {"Lcom/hp/mobile/common/utils/Logger$Companion;", "", "()V", "LOG_TAG_PREFIX", "", "isEnabled", "", "()Z", "setEnabled", "(Z)V", "isSaveLogEnabled", "setSaveLogEnabled", "libTag", "common-lib_release"}, m1294k = 1, m1295mv = {1, 6, 0}, m1297xi = 48)
    public static final class Companion {
        public Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final boolean isEnabled() {
            return Logger.isEnabled;
        }

        public final boolean isSaveLogEnabled() {
            return Logger.isSaveLogEnabled;
        }

        public final void setEnabled(boolean z) {
            Logger.isEnabled = z;
        }

        public final void setSaveLogEnabled(boolean z) {
            Logger.isSaveLogEnabled = z;
        }
    }

    public Logger(String tag) {
        Intrinsics.checkNotNullParameter(tag, "tag");
        this.tag = tag;
    }

    public final void m446d(Object any) {
        if (isEnabled) {
            Log.d(this.tag, String.valueOf(any));
        }
        if (isSaveLogEnabled) {
            LogWriter.INSTANCE.writeLog(this.tag, String.valueOf(any), 3);
        }
    }

    public final void m447e(Object any) {
        if (isEnabled) {
            Log.e(this.tag, String.valueOf(any));
        }
        if (isSaveLogEnabled) {
            LogWriter.INSTANCE.writeLog(this.tag, String.valueOf(any), 6);
        }
    }

    public final void m448e(Object any, Throwable t) {
        Intrinsics.checkNotNullParameter(t, "t");
        if (isEnabled) {
            Log.e(this.tag, String.valueOf(any), t);
        }
        if (isSaveLogEnabled) {
            LogWriter.INSTANCE.writeLog(this.tag, any + '\n' + Log.getStackTraceString(t), 6);
        }
    }

    public final String getTag() {
        return this.tag;
    }

    public final void m449i(Object any) {
        if (isEnabled) {
            Log.i(this.tag, String.valueOf(any));
        }
        if (isSaveLogEnabled) {
            LogWriter.INSTANCE.writeLog(this.tag, String.valueOf(any), 4);
        }
    }

    public final void invoke(Object any) {
        m449i(any);
    }

    public final void invoke(Object any, Throwable thrown) {
        Intrinsics.checkNotNullParameter(thrown, "thrown");
        m448e(any, thrown);
    }

    public final void m450v(Function0<? extends Object> call) {
        Intrinsics.checkNotNullParameter(call, "call");
    }
}
