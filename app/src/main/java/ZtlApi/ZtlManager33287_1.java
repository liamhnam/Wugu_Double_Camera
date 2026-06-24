package ZtlApi;

import android.content.Intent;
import android.util.Log;
import androidx.exifinterface.media.ExifInterface;
import com.google.android.exoplayer2.metadata.icy.IcyHeaders;
import com.tom_roush.pdfbox.pdmodel.documentinterchange.taggedpdf.PDLayoutAttributeObject;

public class ZtlManager33287_1 extends ZtlManager {
    @Override
    public void setSplitScreenLeftRightEnable(boolean z) {
    }

    @Override
    public void setSplitScreenUpDownEnable(boolean z) {
    }

    @Override
    public void goToSleep() {
        if (this.mContext == null) {
            Log.e("上下文为空，不执行", "请检查是否已调用setContext()");
            return;
        }
        Intent intent = new Intent("com.ztl.action.boardstate");
        intent.putExtra("state", 0);
        this.mContext.sendBroadcast(intent);
    }

    @Override
    public void wakeUp() {
        if (this.mContext == null) {
            Log.e("上下文为空，不执行", "请检查是否已调用setContext()");
            return;
        }
        Intent intent = new Intent("com.ztl.action.boardstate");
        intent.putExtra("state", 1);
        this.mContext.sendBroadcast(intent);
    }

    @Override
    public int getGpioValue(int i) {
        if (!getAndroidVersion().contains("3328")) {
            return -1;
        }
        Gpio gpio = new Gpio();
        gpio.open(new String[]{"", "GPIO0_B1", "GPIO0_B3", "GPIO0_B5", "GPIO0_B6", "GPIO1_A0", "GPIO1_A1", "GPIO1_A2", "GPIO1_A3", "GPIO1_A4", "GPIO1_A5"}[i]);
        return gpio.getValue();
    }

    @Override
    public void setAutoTimezone(boolean z) {
        if (this.mContext == null) {
            Log.e("上下文为空，不执行", "请检查是否已调用setContext()");
            return;
        }
        Intent intent = new Intent("com.ztl.action.autotimezone");
        intent.putExtra("checked", z ? 1 : 0);
        this.mContext.sendBroadcast(intent);
    }

    @Override
    public int setAutoTimeZone(int i) {
        if (this.mContext == null) {
            Log.e("上下文为空，不执行", "请检查是否已调用setContext()");
            return -1;
        }
        Intent intent = new Intent("com.ztl.action.autotimezone");
        intent.putExtra("checked", i);
        this.mContext.sendBroadcast(intent);
        return 0;
    }

    @Override
    public void setAutoDateTime(boolean z) {
        if (this.mContext == null) {
            Log.e("上下文为空，不执行", "请检查是否已调用setContext()");
            return;
        }
        Intent intent = new Intent("com.ztl.action.autodatetime");
        intent.putExtra("checked", z ? 1 : 0);
        this.mContext.sendBroadcast(intent);
    }

    @Override
    public int setAutoDateTime(int i) {
        if (this.mContext == null) {
            Log.e("上下文为空，不执行", "请检查是否已调用setContext()");
            return -1;
        }
        Intent intent = new Intent("com.ztl.action.autodatetime");
        intent.putExtra("checked", i);
        this.mContext.sendBroadcast(intent);
        return 0;
    }

    @Override
    public int setRaiseSystemVolume() {
        return setSystemVolumeIndex(getSystemCurrenVolume() + 1);
    }

    @Override
    public int setLowerSystemVolume() {
        return setSystemVolumeIndex(getSystemCurrenVolume() - 1);
    }

    @Override
    public int setSystemBrightness(int i) {
        if (this.mContext == null) {
            Log.e("上下文为空，不执行", "请检查是否已调用setContext()");
            return -1;
        }
        Intent intent = new Intent("com.ztl.action.setbrightness");
        intent.putExtra("brightness", i);
        this.mContext.sendBroadcast(intent);
        return 0;
    }

    @Override
    public void recoverySystem() {
        if (this.mContext == null) {
            Log.e("上下文为空，不执行", "请检查是否已调用setContext()");
        } else {
            this.mContext.sendBroadcast(new Intent("com.ztl.action.recovery"));
        }
    }

    @Override
    public int getDisplayOrientation() {
        return Integer.valueOf(getSystemProperty("persist.sys.ztlOrientation", PDLayoutAttributeObject.GLYPH_ORIENTATION_VERTICAL_ZERO_DEGREES)).intValue();
    }

    @Override
    public boolean isUsbDebugOpen() {
        return Integer.valueOf(getSystemProperty("persist.usb.mode", IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE)).intValue() == 1;
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
}
