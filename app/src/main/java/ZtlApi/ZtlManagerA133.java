package ZtlApi;

import android.app.usage.StorageStatsManager;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Build;
import android.os.storage.StorageManager;
import android.util.Log;
import androidx.constraintlayout.motion.widget.Key;
import com.google.android.exoplayer2.metadata.icy.IcyHeaders;
import com.p020hp.jipp.model.IdentifyAction;
import com.tom_roush.pdfbox.pdmodel.documentinterchange.taggedpdf.PDLayoutAttributeObject;
import java.io.IOException;

public class ZtlManagerA133 extends ZtlManager {
    private String TAG = "ZtlManagerU202";

    ZtlManagerA133() {
        this.DEBUG_ZTL = getSystemProperty("persist.sys.ztl.debug", "false").equals("true");
    }

    @Override
    public long getTotalInternalMemorySize() {
        if (Build.VERSION.SDK_INT < 26) {
            return -1L;
        }
        StorageStatsManager storageStatsManager = (StorageStatsManager) this.mContext.getSystemService("storagestats");
        try {
            long totalBytes = storageStatsManager.getTotalBytes(StorageManager.UUID_DEFAULT);
            storageStatsManager.getFreeBytes(StorageManager.UUID_DEFAULT);
            return totalBytes;
        } catch (IOException e) {
            e.printStackTrace();
            return -1L;
        }
    }

    @Override
    public void openUsbDebug(boolean z) {
        if (this.mContext == null) {
            Log.e("上下文为空，不执行", "请检查是否已调用setContext()");
            return;
        }
        String str = z ? IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE : PDLayoutAttributeObject.GLYPH_ORIENTATION_VERTICAL_ZERO_DEGREES;
        Intent intent = new Intent("com.ding.adbsetting");
        intent.putExtra("enable", str);
        intent.setPackage("com.yian.yiansettings");
        intent.putExtra("skip_permission", true);
        this.mContext.sendBroadcast(intent);
    }

    @Override
    public void setUSBtoPC(boolean z) {
        if (z) {
            execRootCmd("cat sys/devices//platform/soc/usbc0/usb_device");
        } else {
            execRootCmd("cat sys/devices/platform/soc/usbc0/usb_host");
        }
    }

    @Override
    public boolean getUSBtoPC() {
        try {
            return loadFileAsString("/sys/devices/platform/soc/usbc0/otg_role").contains("usb_device");
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean isDebugSerialEnable() {
        String systemProperty = getSystemProperty("ro.ztl.debugSerialState", "-1");
        if (systemProperty.equals(IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE)) {
            return true;
        }
        if (systemProperty.equals(PDLayoutAttributeObject.GLYPH_ORIENTATION_VERTICAL_ZERO_DEGREES)) {
            return false;
        }
        Log.e(this.TAG, "系统不支持，请更新");
        return true;
    }

    @Override
    public void enableDebugSerial(boolean z, boolean z2) {
        ComponentName componentName = new ComponentName("com.droidlogic.tv.settings", "com.droidlogic.tv.settings.ZTLService");
        Intent intent = new Intent();
        intent.setComponent(componentName);
        intent.setPackage("com.droidlogic.tv.settings");
        intent.putExtra("cmd", "setDebugSerial");
        intent.putExtra("enable", z);
        this.mContext.startService(intent);
        if (z2) {
            reboot(0);
        }
    }

    @Override
    public void setDisplayOrientation(int i) {
        if (i == getDisplayOrientation()) {
            Log.e("当前方向", "与旋转方向一致，不执行");
        } else {
            setPrimaryDisplayOrientation(i);
            reboot(1);
        }
    }

    @Override
    public int setDisplayOrientation(int i, int i2) {
        if (i == 0) {
            setPrimaryDisplayOrientation(i2);
            reboot(1);
            return 0;
        }
        if (i == 1) {
            setExtendDisplayOrientation(i2);
            reboot(1);
            return 0;
        }
        if (i != -1) {
            return 0;
        }
        setPrimaryDisplayOrientation(i2);
        setExtendDisplayOrientation(i2);
        reboot(1);
        return 0;
    }

    @Override
    public void setPrimaryDisplayOrientation(int i) {
        setSystemProperty("persist.ztl.hwrotation", Integer.toString(i));
    }

    @Override
    public void setExtendDisplayOrientation(int i) {
        ComponentName componentName = new ComponentName("com.droidlogic.tv.settings", "com.droidlogic.tv.settings.ZTLService");
        if (i == getDisplayOrientation(1)) {
            Log.e("当前方向", "与旋转方向一致，不执行");
            return;
        }
        Intent intent = new Intent();
        intent.setComponent(componentName);
        intent.putExtra("cmd", "set_display_rotation");
        intent.putExtra(Key.ROTATION, i);
        intent.putExtra(IdentifyAction.display, "extend");
        this.mContext.startService(intent);
    }

    @Override
    public void setCloseSystemBar() {
        openSystemBar(false);
    }

    @Override
    public void openSystemBar(boolean z) {
        if (this.mContext == null) {
            Log.e(this.TAG, "上下文为空,不执行");
            return;
        }
        Intent intent = new Intent("com.ztl.action.systembar");
        intent.putExtra("enable", z);
        intent.putExtra("skip_permission", true);
        this.mContext.sendBroadcast(intent);
    }

    @Override
    @Deprecated
    public void setOpenSystemBar() {
        if (this.mContext == null) {
            Log.e("上下文为空，不执行", "请检查是否已调用setContext()");
            return;
        }
        Intent intent = new Intent("com.ztl.action.systembar");
        intent.putExtra("enable", true);
        intent.putExtra("skip_permission", true);
        this.mContext.sendBroadcast(intent);
    }

    @Override
    public boolean isSystemBarOpen() {
        return Boolean.valueOf(getSystemProperty("persist.ztl.systembar", "true")).booleanValue();
    }
}
