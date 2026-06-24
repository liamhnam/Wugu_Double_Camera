package com.proembed.service;

import android.content.Context;
import java.io.File;

public abstract class AbstractC1848XC {
    public abstract void changeScreenLight(Context context, int i);

    public abstract int getCPUTemperature();

    public abstract String getLedPath();

    public abstract boolean getNavBarHideState(Context context);

    public abstract String getRtcPath();

    public abstract boolean isBackLightOn();

    public abstract boolean isSlideShowNavBarOpen();

    public abstract boolean isSlideShowNotificationBarOpen();

    public abstract void rebootRecovery(Context context);

    public abstract void rotateScreen(Context context, String str);

    public abstract void setADBOpen(boolean z);

    public abstract void setDormantInterval(Context context, long j);

    public abstract void setEthMacAddress(Context context, String str);

    public abstract void setSlideShowNavBar(Context context, boolean z);

    public abstract void setSlideShowNotificationBar(Context context, boolean z);

    public abstract void setSoftKeyboardHidden(boolean z);

    public abstract boolean silentInstallApk(String str);

    public abstract void takeBrightness(Context context);

    public abstract void turnOffBackLight();

    public abstract void turnOffHDMI();

    public abstract void turnOnBackLight();

    public abstract void turnOnHDMI();

    protected String filterPath(String[] strArr) {
        if (strArr == null) {
            return null;
        }
        for (String str : strArr) {
            if (new File(str).exists()) {
                return str;
            }
        }
        return null;
    }
}
