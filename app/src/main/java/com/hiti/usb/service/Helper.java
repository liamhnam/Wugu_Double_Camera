package com.hiti.usb.service;

public class Helper {
    public static String getCallerCallerClassName() {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        String className = null;
        for (int i = 1; i < stackTrace.length; i++) {
            StackTraceElement stackTraceElement = stackTrace[i];
            if (!stackTraceElement.getClassName().equals(Helper.class.getName()) && stackTraceElement.getClassName().indexOf("java.lang.Thread") != 0) {
                if (className == null) {
                    className = stackTraceElement.getClassName();
                } else if (!className.equals(stackTraceElement.getClassName())) {
                    return stackTraceElement.getClassName();
                }
            }
        }
        return null;
    }

    public static String getCallerClassName() {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        for (int i = 1; i < stackTrace.length; i++) {
            StackTraceElement stackTraceElement = stackTrace[i];
            if (!stackTraceElement.getClassName().equals(Helper.class.getName()) && stackTraceElement.getClassName().indexOf("java.lang.Thread") != 0) {
                return stackTraceElement.getClassName();
            }
        }
        return null;
    }
}
