package com.proembed.service;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.widget.Toast;
import com.google.android.exoplayer2.metadata.icy.IcyHeaders;
import com.tom_roush.pdfbox.pdmodel.documentinterchange.taggedpdf.PDLayoutAttributeObject;
import com.wugu.doublecamera.enums.BoardModelEnum;
import java.io.File;

public class XC3399 extends AbstractC1848XC {
    private static final String BACKLIGHT_IO_PATH = "/sys/devices/platform/backlight/backlight/backlight/bl_power";
    static final String RTC_PATH = "/sys/devices/platform/ff120000.i2c/i2c-2/2-0051/rtc/rtc0/time";
    static final String[] LED_PATH = {"/sys/devices/platform/misc_power_en/red_led"};
    public static final XC3399 INSTANCE = new XC3399();

    @Override
    public String getRtcPath() {
        return RTC_PATH;
    }

    private XC3399() {
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
        Toast.makeText(context, "暂不支持此功能", 1).show();
    }

    @Override
    public void rotateScreen(Context context, String str) throws Throwable {
        Utils.setValueToProp("persist.sys.displayrot", str);
        if (Utils.getValueFromProp("persist.same.orientation").equals("true")) {
            Utils.setValueToProp("persist.sys.rotation.einit", (Integer.parseInt(str) / 90) + "");
        }
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
    public void turnOffBackLight() throws Throwable {
        GPIOUtils.writeStringFileFor7(new File("/sys/devices/platform/backlight/backlight/backlight/bl_power"), IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE);
    }

    @Override
    public void turnOnBackLight() throws Throwable {
        GPIOUtils.writeStringFileFor7(new File("/sys/devices/platform/backlight/backlight/backlight/bl_power"), PDLayoutAttributeObject.GLYPH_ORIENTATION_VERTICAL_ZERO_DEGREES);
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
            Utils.setValueToProp("persist.sys.usbdebug", IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE);
            if (Build.MODEL.contains(BoardModelEnum.XIANG_CHENG_3399)) {
                GPIOUtils.writeStringFileFor7(new File("/sys/devices/platform/usb@fe800000/dwc3_mode"), "peripheral");
                return;
            } else {
                GPIOUtils.writeStringFileFor7(new File("/sys/kernel/debug/usb@fe800000/rk_usb_force_mode"), "peripheral");
                return;
            }
        }
        Utils.setValueToProp("persist.sys.usbdebug", PDLayoutAttributeObject.GLYPH_ORIENTATION_VERTICAL_ZERO_DEGREES);
        if (Build.MODEL.contains(BoardModelEnum.XIANG_CHENG_3399)) {
            GPIOUtils.writeStringFileFor7(new File("/sys/devices/platform/usb@fe800000/dwc3_mode"), "host");
        } else {
            GPIOUtils.writeStringFileFor7(new File("/sys/kernel/debug/usb@fe800000/rk_usb_force_mode"), "host");
        }
    }
}
