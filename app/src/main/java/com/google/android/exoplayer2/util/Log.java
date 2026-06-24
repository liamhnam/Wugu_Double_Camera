package com.google.android.exoplayer2.util;

import android.text.TextUtils;

public final class Log {
    public static final int LOG_LEVEL_ALL = 0;
    public static final int LOG_LEVEL_ERROR = 3;
    public static final int LOG_LEVEL_INFO = 1;
    public static final int LOG_LEVEL_OFF = Integer.MAX_VALUE;
    public static final int LOG_LEVEL_WARNING = 2;
    private static int logLevel = 0;
    private static boolean logStackTraces = true;

    private Log() {
    }

    public static int getLogLevel() {
        return logLevel;
    }

    public boolean getLogStackTraces() {
        return logStackTraces;
    }

    public static void setLogLevel(int i) {
        logLevel = i;
    }

    public static void setLogStackTraces(boolean z) {
        logStackTraces = z;
    }

    public static void m340d(String str, String str2) {
        if (logLevel == 0) {
            android.util.Log.d(str, str2);
        }
    }

    public static void m341d(String str, String str2, Throwable th) {
        if (!logStackTraces) {
            m340d(str, appendThrowableMessage(str2, th));
        } else if (logLevel == 0) {
            android.util.Log.d(str, str2, th);
        }
    }

    public static void m344i(String str, String str2) {
        if (logLevel <= 1) {
            android.util.Log.i(str, str2);
        }
    }

    public static void m345i(String str, String str2, Throwable th) {
        if (!logStackTraces) {
            m344i(str, appendThrowableMessage(str2, th));
        } else if (logLevel <= 1) {
            android.util.Log.i(str, str2, th);
        }
    }

    public static void m346w(String str, String str2) {
        if (logLevel <= 2) {
            android.util.Log.w(str, str2);
        }
    }

    public static void m347w(String str, String str2, Throwable th) {
        if (!logStackTraces) {
            m346w(str, appendThrowableMessage(str2, th));
        } else if (logLevel <= 2) {
            android.util.Log.w(str, str2, th);
        }
    }

    public static void m342e(String str, String str2) {
        if (logLevel <= 3) {
            android.util.Log.e(str, str2);
        }
    }

    public static void m343e(String str, String str2, Throwable th) {
        if (!logStackTraces) {
            m342e(str, appendThrowableMessage(str2, th));
        } else if (logLevel <= 3) {
            android.util.Log.e(str, str2, th);
        }
    }

    private static String appendThrowableMessage(String str, Throwable th) {
        if (th == null) {
            return str;
        }
        String message = th.getMessage();
        return TextUtils.isEmpty(message) ? str : str + " - " + message;
    }
}
