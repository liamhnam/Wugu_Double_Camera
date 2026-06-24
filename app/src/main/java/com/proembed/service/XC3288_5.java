package com.proembed.service;

import android.content.Context;
import android.content.Intent;
import android.provider.Settings;
import android.text.TextUtils;
import androidx.exifinterface.media.ExifInterface;
import com.google.android.exoplayer2.metadata.icy.IcyHeaders;
import com.tom_roush.pdfbox.pdmodel.documentinterchange.taggedpdf.PDLayoutAttributeObject;
import java.io.IOException;

public class XC3288_5 extends AbstractC1848XC {
    private static final String BACKLIGHT_IO_PATH = "/sys/class/graphics/fb0/pwr_bl";
    static final String RTC_PATH = "/sys/devices/ff650000.i2c/i2c-0/0-0051/rtc/rtc0/time";
    static final String[] LED_PATH = {"/sys/devices/misc_power_en.22/green_led", "/sys/devices/misc_power_en.23/green_led"};
    public static final XC3288_5 INSTANCE = new XC3288_5();

    @Override
    public int getCPUTemperature() {
        return 0;
    }

    @Override
    public String getRtcPath() {
        return RTC_PATH;
    }

    private XC3288_5() {
    }

    @Override
    public String getLedPath() {
        return filterPath(LED_PATH);
    }

    @Override
    public void takeBrightness(Context context) {
        context.startActivity(new Intent("android.intent.action.SHOW_BRIGHTNESS_DIALOG"));
    }

    @Override
    public void setEthMacAddress(Context context, String str) {
        NetUtils.setEthMAC(str);
    }

    @Override
    public void rotateScreen(Context context, String str) throws Throwable {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        if (str.equals(PDLayoutAttributeObject.GLYPH_ORIENTATION_VERTICAL_ZERO_DEGREES) || str.equals(PDLayoutAttributeObject.GLYPH_ORIENTATION_VERTICAL_90_DEGREES) || str.equals(PDLayoutAttributeObject.GLYPH_ORIENTATION_VERTICAL_180_DEGREES) || str.equals(PDLayoutAttributeObject.GLYPH_ORIENTATION_VERTICAL_270_DEGREES)) {
            ScreenUtils.rotateScreen(str);
            Utils.reboot();
        }
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
        if (!z) {
            Utils.setValueToProp(Constant.PROP_SWIPE_STATUSBAR_LU, PDLayoutAttributeObject.GLYPH_ORIENTATION_VERTICAL_ZERO_DEGREES);
        } else {
            Utils.setValueToProp(Constant.PROP_SWIPE_STATUSBAR_LU, IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE);
        }
    }

    @Override
    public boolean isSlideShowNotificationBarOpen() {
        return Utils.getValueFromProp(Constant.PROP_SWIPE_NOTIFIBAR_LU).equals(PDLayoutAttributeObject.GLYPH_ORIENTATION_VERTICAL_ZERO_DEGREES);
    }

    @Override
    public void setSlideShowNotificationBar(Context context, boolean z) {
        if (!z) {
            Utils.setValueToProp(Constant.PROP_SWIPE_NOTIFIBAR_LU, IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE);
        } else {
            Utils.setValueToProp(Constant.PROP_SWIPE_NOTIFIBAR_LU, PDLayoutAttributeObject.GLYPH_ORIENTATION_VERTICAL_ZERO_DEGREES);
        }
    }

    @Override
    public void turnOffBackLight() {
        try {
            GPIOUtils.writeIntFileUnder7(PDLayoutAttributeObject.GLYPH_ORIENTATION_VERTICAL_ZERO_DEGREES, "/sys/class/graphics/fb0/pwr_bl");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e2) {
            e2.printStackTrace();
        }
    }

    @Override
    public void turnOnBackLight() {
        try {
            GPIOUtils.writeIntFileUnder7(IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE, "/sys/class/graphics/fb0/pwr_bl");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e2) {
            e2.printStackTrace();
        }
    }

    @Override
    public boolean isBackLightOn() {
        return IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE.equals(GPIOUtils.readGpioPG("/sys/class/graphics/fb0/pwr_bl"));
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
    public void changeScreenLight(Context context, int i) {
        Intent intent = new Intent("com.ys.set_screen_bright");
        intent.putExtra("brightValue", i);
        context.sendBroadcast(intent);
    }

    @Override
    public void turnOnHDMI() {
        try {
            GPIOUtils.writeIntFileUnder7(IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE, Constant.HDMI_STATUS_3288);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e2) {
            e2.printStackTrace();
        }
    }

    @Override
    public void turnOffHDMI() {
        try {
            GPIOUtils.writeIntFileUnder7(PDLayoutAttributeObject.GLYPH_ORIENTATION_VERTICAL_ZERO_DEGREES, Constant.HDMI_STATUS_3288);
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
            Utils.setValueToProp("persist.sys.usb.otg.mode", ExifInterface.GPS_MEASUREMENT_2D);
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
        Utils.setValueToProp("persist.sys.usb.otg.mode", IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE);
        try {
            GPIOUtils.writeIntFileUnder7(IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE, Constant.USB_OTG_IO);
        } catch (IOException e3) {
            e3.printStackTrace();
        } catch (InterruptedException e4) {
            e4.printStackTrace();
        }
    }
}
