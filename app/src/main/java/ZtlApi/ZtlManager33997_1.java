package ZtlApi;

import ZtlApi.ZtlManagerU202;
import android.content.Intent;
import android.os.SystemProperties;
import android.util.Log;
import androidx.exifinterface.media.ExifInterface;
import com.google.android.exoplayer2.metadata.icy.IcyHeaders;
import com.tom_roush.pdfbox.pdmodel.documentinterchange.taggedpdf.PDLayoutAttributeObject;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class ZtlManager33997_1 extends ZtlManager {
    ZtlManager33997_1() {
        this.DEBUG_ZTL = SystemProperties.get("persist.sys.ztl.debug", "false").equals("true");
    }

    @Override
    public boolean isUsbDebugOpen() {
        return !getSystemProperty("persist.usb.mode", IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE).contains(IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE);
    }

    @Override
    public String getHDMIState() {
        return execRootCmd("cat /sys/devices/virtual/switch/hdmi/state");
    }

    @Override
    public void setHDMIEnable(boolean z) {
        if (z) {
            execRootCmdSilent("echo on > /sys/class/drm/card0-HDMI-A-1/status");
        } else {
            execRootCmdSilent("echo off > /sys/class/drm/card0-HDMI-A-1/status");
        }
    }

    @Override
    public int getGpioValue(int i) {
        if (!getAndroidVersion().contains("3399") || !getAndroidVersion().contains("7_1")) {
            return -1;
        }
        Gpio gpio = new Gpio();
        gpio.open(new String[]{"", "GPIO2_B2", "GPIO2_A5", "GPIO2_A3", "GPIO2_A1", "GPIO2_A6", "GPIO2_B0", "GPIO2_A4", "GPIO2_B1", "GPIO2_B4", "GPIO2_A0", "GPIO2_A7", "GPIO2_A2"}[i]);
        return gpio.getValue();
    }

    @Override
    public int getUsbDebugState() {
        String str = IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE;
        String systemProperty = getSystemProperty("persist.usb.mode", IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE);
        if (!systemProperty.equals(PDLayoutAttributeObject.GLYPH_ORIENTATION_VERTICAL_ZERO_DEGREES) && !systemProperty.equals(ExifInterface.GPS_MEASUREMENT_2D)) {
            str = PDLayoutAttributeObject.GLYPH_ORIENTATION_VERTICAL_ZERO_DEGREES;
        }
        return Integer.valueOf(str).intValue();
    }

    @Override
    public boolean getUSBtoPC() {
        return getSystemProperty("persist.usb.mode", "").equals(ExifInterface.GPS_MEASUREMENT_2D);
    }

    @Override
    public void setUSBtoPC(boolean z) {
        if (z) {
            setSystemProperty("persist.usb.mode", ExifInterface.GPS_MEASUREMENT_2D);
            writeMethod("/sys/kernel/debug/usb@fe800000/rk_usb_force_mode", ExifInterface.GPS_MEASUREMENT_2D);
        } else {
            setSystemProperty("persist.usb.mode", IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE);
            writeMethod("/sys/kernel/debug/usb@fe800000/rk_usb_force_mode", IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE);
        }
    }

    @Override
    public void setSplitScreenLeftRightEnable(boolean z) {
        Log.e("ztllib", "unsupport fucntion now for this board.todo later.");
    }

    @Override
    public void setSplitScreenUpDownEnable(boolean z) {
        Log.e("ztllib", "unsupport fucntion now for this board.todo later.");
    }

    @Override
    public String[] getHDMIResolutions() {
        try {
            String[] strArrSplit = loadFileAsString("/sys/class/drm/card0-HDMI-A-1/modes").split("\n");
            ArrayList arrayList = new ArrayList();
            for (String str : strArrSplit) {
                arrayList.add(new ZtlManagerU202.rotaionString(str));
            }
            Collections.sort(arrayList);
            int size = arrayList.size();
            String[] strArr = new String[size];
            for (int i = 0; i < size; i++) {
                strArr[i] = ((ZtlManagerU202.rotaionString) arrayList.get(i)).res;
            }
            return strArr;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public String[] getScreenModes() {
        return getSystemProperty("persist.sys.displaymdoes", "").split(",");
    }

    @Override
    public void setScreenMode(String str) {
        if (this.mContext == null) {
            Log.e("上下文为空，不执行", "请检查是否已调用setContext()");
            return;
        }
        Intent intent = new Intent("android.ztl.action.SET_SCREEN_MODE");
        intent.putExtra("mode", str);
        this.mContext.sendBroadcast(intent);
    }

    @Override
    public void setGPUMode(String str) {
        execRootCmdSilent(String.format("echo " + str + " >/sys/bus/platform/devices/ff9a0000.gpu/devfreq/ff9a0000.gpu/governor", new Object[0]));
    }
}
