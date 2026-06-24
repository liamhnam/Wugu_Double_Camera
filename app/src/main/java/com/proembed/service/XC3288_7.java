package com.proembed.service;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;
import androidx.exifinterface.media.ExifInterface;
import com.google.android.exoplayer2.metadata.icy.IcyHeaders;
import com.tom_roush.pdfbox.pdmodel.documentinterchange.taggedpdf.PDLayoutAttributeObject;
import java.io.File;
import java.io.IOException;

public class XC3288_7 extends AbstractC1848XC {
    private static final String BACKLIGHT_IO_PATH = "/sys/devices/platform/backlight/backlight/backlight/bl_power";
    public static final XC3288_7 INSTANCE = new XC3288_7();

    @Override
    public String getLedPath() {
        return null;
    }

    @Override
    public String getRtcPath() {
        return null;
    }

    @Override
    public void takeBrightness(Context context) {
    }

    @Override
    public void setEthMacAddress(Context context, String str) {
        Toast.makeText(context, "暂不支持此功能", 1).show();
    }

    @Override
    public void rotateScreen(Context context, String str) throws Throwable {
        Utils.setValueToProp("persist.sys.displayrot", str);
        File file = new File("/sys/devices/platform/ff150000.i2c/i2c-6/6-0050/rotate");
        if (file.exists()) {
            GPIOUtils.writeStringFileFor7(file, str);
        }
        Utils.reboot();
    }

    @Override
    public boolean getNavBarHideState(Context context) {
        return Utils.getValueFromProp(Constant.PROP_STATUSBAR_STATE_LU).equals(PDLayoutAttributeObject.GLYPH_ORIENTATION_VERTICAL_ZERO_DEGREES);
    }

    @Override
    public boolean isSlideShowNavBarOpen() {
        return Utils.getValueFromProp(Constant.PROP_SWIPE_STATUSBAR_LU).equals(IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE);
    }

    @Override
    public void setSlideShowNavBar(Context context, boolean z) {
        if (z) {
            Utils.setValueToProp(Constant.PROP_SWIPE_STATUSBAR_LU, IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE);
        } else {
            Utils.setValueToProp(Constant.PROP_SWIPE_STATUSBAR_LU, PDLayoutAttributeObject.GLYPH_ORIENTATION_VERTICAL_ZERO_DEGREES);
        }
    }

    @Override
    public boolean isSlideShowNotificationBarOpen() {
        return Utils.getValueFromProp(Constant.PROP_SWIPE_NOTIFIBAR_LU).equals(PDLayoutAttributeObject.GLYPH_ORIENTATION_VERTICAL_ZERO_DEGREES);
    }

    @Override
    public void setSlideShowNotificationBar(Context context, boolean z) {
        if (z) {
            Utils.setValueToProp(Constant.PROP_SWIPE_NOTIFIBAR_LU, PDLayoutAttributeObject.GLYPH_ORIENTATION_VERTICAL_ZERO_DEGREES);
        } else {
            Utils.setValueToProp(Constant.PROP_SWIPE_NOTIFIBAR_LU, IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE);
        }
    }

    @Override
    public void turnOffBackLight() throws Throwable {
        try {
            GPIOUtils.writeIntFileFor7(IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE, "/sys/devices/platform/backlight/backlight/backlight/bl_power");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e2) {
            e2.printStackTrace();
        }
    }

    @Override
    public void turnOnBackLight() throws Throwable {
        try {
            GPIOUtils.writeIntFileFor7(PDLayoutAttributeObject.GLYPH_ORIENTATION_VERTICAL_ZERO_DEGREES, "/sys/devices/platform/backlight/backlight/backlight/bl_power");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e2) {
            e2.printStackTrace();
        }
    }

    @Override
    public boolean isBackLightOn() {
        return PDLayoutAttributeObject.GLYPH_ORIENTATION_VERTICAL_ZERO_DEGREES.equals(GPIOUtils.readGpioPG("/sys/devices/platform/backlight/backlight/backlight/bl_power"));
    }

    @Override
    public void rebootRecovery(Context context) throws Throwable {
        Utils.execFor7("reboot recovery");
    }

    @Override
    public boolean silentInstallApk(String str) {
        return Utils.execFor7("pm install -r " + str);
    }

    @Override
    public void changeScreenLight(Context context, int i) {
        Intent intent = new Intent("com.ys.set_screen_bright");
        intent.putExtra("brightValue", i);
        context.sendBroadcast(intent);
    }

    @Override
    public void turnOnHDMI() throws Throwable {
        Utils.execFor7("chmod 777 /sys/devices/platform/display-subsystem/drm/card0/card0-HDMI-A-1/status");
        GPIOUtils.writeStringFileFor7(new File("/sys/devices/platform/display-subsystem/drm/card0/card0-HDMI-A-1/status"), "on");
    }

    @Override
    public void turnOffHDMI() throws Throwable {
        Utils.execFor7("chmod 777 /sys/devices/platform/display-subsystem/drm/card0/card0-HDMI-A-1/status");
        GPIOUtils.writeStringFileFor7(new File("/sys/devices/platform/display-subsystem/drm/card0/card0-HDMI-A-1/status"), "off");
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
    public int getCPUTemperature() {
        return Integer.parseInt(GPIOUtils.readGpioPGForLong("/sys/class/thermal/thermal_zone0/temp").substring(0, 5)) / 1000;
    }

    @Override
    public void setADBOpen(boolean z) throws Throwable {
        if (z) {
            Utils.setValueToProp("persist.sys.usb.otg.mode", ExifInterface.GPS_MEASUREMENT_2D);
            GPIOUtils.writeStringFileFor7(new File(Constant.USB_OTG_IO), ExifInterface.GPS_MEASUREMENT_2D);
        } else {
            Utils.setValueToProp("persist.sys.usb.otg.mode", IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE);
            GPIOUtils.writeStringFileFor7(new File(Constant.USB_OTG_IO), IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE);
        }
    }
}
