package ZtlApi;

import android.content.Intent;
import android.os.PowerManager;
import android.os.SystemClock;
import android.os.SystemProperties;
import android.util.Log;
import androidx.exifinterface.media.ExifInterface;
import com.google.android.exoplayer2.metadata.icy.IcyHeaders;
import com.tom_roush.pdfbox.pdmodel.documentinterchange.taggedpdf.PDLayoutAttributeObject;
import java.lang.reflect.InvocationTargetException;

public class ZtlManager3368 extends ZtlManager {
    @Override
    public void setSplitScreenLeftRightEnable(boolean z) {
    }

    @Override
    public void setSplitScreenUpDownEnable(boolean z) {
    }

    ZtlManager3368() {
        this.DEBUG_ZTL = SystemProperties.get("persist.sys.ztl.debug", "false").equals("true");
    }

    @Override
    public void goToSleep() {
        PowerManager powerManager = (PowerManager) this.mContext.getSystemService("power");
        try {
            powerManager.getClass().getMethod("goToSleep", Long.TYPE).invoke(powerManager, Long.valueOf(SystemClock.uptimeMillis()));
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e2) {
            e2.printStackTrace();
        } catch (InvocationTargetException e3) {
            e3.printStackTrace();
        }
    }

    @Override
    public void wakeUp() {
        PowerManager powerManager = (PowerManager) this.mContext.getSystemService("power");
        try {
            powerManager.getClass().getMethod("wakeUp", Long.TYPE).invoke(powerManager, Long.valueOf(SystemClock.uptimeMillis()));
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e2) {
            e2.printStackTrace();
        } catch (InvocationTargetException e3) {
            e3.printStackTrace();
        }
    }

    @Override
    public void setDisplayOrientation(int i) {
        if (this.mContext == null) {
            Log.e("上下文为空", "不执行");
            return;
        }
        if (i == getDisplayOrientation()) {
            Log.e("当前方向", "与旋转方向一致，不执行");
            return;
        }
        try {
            setSystemProperty("persist.ztl.hwrotation", i + "");
            execRootCmdSilent("reboot");
        } catch (Exception unused) {
            Log.e(TAG, "set rotation err!");
        }
    }

    @Override
    public int getGpioValue(int i) {
        if (!getAndroidVersion().contains("3368")) {
            return -1;
        }
        Gpio gpio = new Gpio();
        gpio.open(new String[]{"", "GPIO1_A2", "GPIO1_B6", "GPIO1_A3", "GPIO1_B7", "GPIO1_A4", "GPIO1_C0", "GPIO1_A5", "GPIO1_C1", "GPIO1_A6", "GPIO1_A7", "GPIOD_1"}[i]);
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
    public int getSystemBarState() {
        return Integer.valueOf(getSystemProperty("persist.sys.barState", IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE)).intValue();
    }

    @Override
    public String[] getScreenModes() {
        return getSystemProperty("persist.sys.displaymdoes", "").split(",");
    }

    @Override
    public void setScreenMode(String str) {
        Intent intent = new Intent("android.ztl.action.SET_SCREEN_MODE");
        intent.putExtra("mode", str);
        this.mContext.sendBroadcast(intent);
    }
}
