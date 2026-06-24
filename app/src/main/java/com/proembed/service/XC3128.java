package com.proembed.service;

import android.content.Context;
import android.content.Intent;
import android.provider.Settings;
import android.util.Log;
import android.widget.Toast;
import androidx.exifinterface.media.ExifInterface;
import com.google.android.exoplayer2.metadata.icy.IcyHeaders;
import com.tom_roush.pdfbox.pdmodel.documentinterchange.taggedpdf.PDLayoutAttributeObject;
import java.io.File;
import java.io.IOException;

public class XC3128 extends AbstractC1848XC {
    private static final String BACKLIGHT_IO_PATH = "/sys/devices/fb.9/graphics/fb0/pwr_bl";
    static final String RTC_PATH = "/sys/devices/20072000.i2c/i2c-0/0-0051/rtc/rtc0/time";
    static final String[] LED_PATH = {"/sys/devices/misc_power_en.19/out8", "/sys/devices/misc_power_en.18/out8"};
    public static final XC3128 INSTANCE = new XC3128();

    @Override
    public int getCPUTemperature() {
        return 0;
    }

    @Override
    public String getRtcPath() {
        return RTC_PATH;
    }

    private XC3128() {
    }

    @Override
    public String getLedPath() {
        return filterPath(LED_PATH);
    }

    @Override
    public void takeBrightness(Context context) {
        Intent intent = new Intent("com.ys.show_brightness_dialog");
        intent.addFlags(268435456);
        context.sendBroadcast(intent);
    }

    @Override
    public void setEthMacAddress(Context context, String str) {
        Toast.makeText(context, "暂不支持此功能", 1).show();
    }

    @Override
    public void rotateScreen(Context context, String str) {
        ScreenUtils.rotationScreen(context, getDisplayRot(str));
    }

    @Override
    public boolean getNavBarHideState(Context context) {
        return Settings.System.getInt(context.getContentResolver(), "hidden_state_bar", 0) == 1;
    }

    @Override
    public boolean isSlideShowNavBarOpen() {
        return Utils.getValueFromProp(Constant.PROP_SWIPE_STATUSBAR_LU).equals(IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE);
    }

    @Override
    public void setSlideShowNavBar(Context context, boolean z) {
        Intent intent = new Intent("com.ys.slide.systembar");
        intent.putExtra("barMode", "navigationbar");
        intent.putExtra("isSlide", z);
        context.sendBroadcast(intent);
    }

    @Override
    public boolean isSlideShowNotificationBarOpen() {
        return Utils.getValueFromProp(Constant.PROP_SWIPE_NOTIFIBAR_LU).equals(PDLayoutAttributeObject.GLYPH_ORIENTATION_VERTICAL_ZERO_DEGREES);
    }

    @Override
    public void setSlideShowNotificationBar(Context context, boolean z) {
        Intent intent = new Intent("com.ys.slide.systembar");
        intent.putExtra("barMode", "notificationbar");
        intent.putExtra("isSlide", z);
        context.sendBroadcast(intent);
    }

    @Override
    public void turnOffBackLight() {
        try {
            GPIOUtils.writeIntFileUnder7(PDLayoutAttributeObject.GLYPH_ORIENTATION_VERTICAL_ZERO_DEGREES, "/sys/devices/fb.9/graphics/fb0/pwr_bl");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e2) {
            e2.printStackTrace();
        }
    }

    @Override
    public void turnOnBackLight() {
        try {
            GPIOUtils.writeIntFileUnder7(IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE, "/sys/devices/fb.9/graphics/fb0/pwr_bl");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e2) {
            e2.printStackTrace();
        }
    }

    @Override
    public boolean isBackLightOn() {
        return IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE.equals(GPIOUtils.readGpioPG("/sys/devices/fb.9/graphics/fb0/pwr_bl"));
    }

    @Override
    public void rebootRecovery(Context context) {
        Utils.do_exec("reboot recovery");
    }

    @Override
    public boolean silentInstallApk(String str) {
        return SilentInstallUtils.install(str);
    }

    @Override
    public void changeScreenLight(Context context, int i) throws Throwable {
        GPIOUtils.writeStringFileFor7(new File("/sys/class/backlight/rk28_bl/brightness"), String.valueOf((i * 255) / 100));
        Intent intent = new Intent("com.ys.set_screen_bright");
        intent.putExtra("brightValue", i);
        context.sendBroadcast(intent);
        Log.i("yuanhang", "brightValue");
    }

    @Override
    public void turnOnHDMI() {
        try {
            GPIOUtils.writeIntFileUnder7(IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE, Constant.HDMI_STATUS_3128);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e2) {
            e2.printStackTrace();
        }
    }

    @Override
    public void turnOffHDMI() {
        try {
            GPIOUtils.writeIntFileUnder7("0 ", Constant.HDMI_STATUS_3128);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e2) {
            e2.printStackTrace();
        }
    }

    @Override
    public void setSoftKeyboardHidden(boolean z) {
        if (z) {
            Utils.setValueToProp("persist.sys.softkeyboard", PDLayoutAttributeObject.GLYPH_ORIENTATION_VERTICAL_ZERO_DEGREES);
        } else {
            Utils.setValueToProp("persist.sys.softkeyboard", IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE);
        }
    }

    @Override
    public void setDormantInterval(Context context, long j) {
        Intent intent = new Intent(Constant.DORMANT_INTERVAL);
        intent.putExtra("time_interval", j);
        context.sendBroadcast(intent);
    }

    @Override
    public void setADBOpen(boolean z) {
        if (z) {
            try {
                GPIOUtils.writeIntFileUnder7(ExifInterface.GPS_MEASUREMENT_2D, Constant.USB_OTG_IO);
                return;
            } catch (IOException e) {
                e.printStackTrace();
                return;
            } catch (InterruptedException e2) {
                e2.printStackTrace();
                return;
            }
        }
        try {
            GPIOUtils.writeIntFileUnder7(IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE, Constant.USB_OTG_IO);
        } catch (IOException e3) {
            e3.printStackTrace();
        } catch (InterruptedException e4) {
            e4.printStackTrace();
        }
    }

    private int getDisplayRot(String str) {
        str.hashCode();
        byte b = -1;
        switch (str.hashCode()) {
            case 48:
                if (str.equals(PDLayoutAttributeObject.GLYPH_ORIENTATION_VERTICAL_ZERO_DEGREES)) {
                    b = 0;
                }
                break;
            case 1815:
                if (str.equals(PDLayoutAttributeObject.GLYPH_ORIENTATION_VERTICAL_90_DEGREES)) {
                    b = 1;
                }
                break;
            case 48873:
                if (str.equals(PDLayoutAttributeObject.GLYPH_ORIENTATION_VERTICAL_180_DEGREES)) {
                    b = 2;
                }
                break;
            case 49803:
                if (str.equals(PDLayoutAttributeObject.GLYPH_ORIENTATION_VERTICAL_270_DEGREES)) {
                    b = 3;
                }
                break;
        }
        switch (b) {
            case 0:
            default:
                return 0;
            case 1:
                return 1;
            case 2:
                return 2;
            case 3:
                return 3;
        }
    }
}
