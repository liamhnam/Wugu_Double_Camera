package ZtlApi;

import android.content.ComponentName;
import android.content.Intent;
import android.util.Log;
import androidx.constraintlayout.motion.widget.Key;
import com.google.android.exoplayer2.metadata.icy.IcyHeaders;
import com.p020hp.jipp.model.IdentifyAction;

public class ZtlManagerkt11_32 extends ZtlManager {
    @Override
    public void openSystemBar(boolean z) {
        if (this.mContext == null) {
            Log.e(TAG, "上下文为空,不执行");
            return;
        }
        Intent intent = new Intent("com.ztl.action.systembar");
        intent.putExtra("enable", z);
        this.mContext.sendBroadcast(intent);
    }

    @Override
    public boolean isSystemBarOpen() {
        return Boolean.valueOf(getSystemProperty("persist.ztl.systembar", "true")).booleanValue();
    }

    @Override
    public String getDeviceID() {
        return execRootCmd("cat /sys/block/mmcblk0/device/cid");
    }

    @Override
    public void setUSBtoPC(boolean z) {
        ComponentName componentName = new ComponentName("com.yian.yiansettings", "com.yian.yiansettings.ZTLService");
        Intent intent = new Intent();
        intent.setComponent(componentName);
        intent.putExtra("cmd", "set_otg");
        intent.putExtra("isotg", z);
        intent.setPackage("com.yian.yiansettings");
        this.mContext.startService(intent);
    }

    @Override
    public boolean getUSBtoPC() {
        try {
            return getSystemProperty("persist.usb.mode", "").contains(IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public void setDisplayOrientation(int i) {
        if (i == getDisplayOrientation()) {
            Log.e("当前方向", "与旋转方向一致，不执行");
            return;
        }
        setPrimaryDisplayOrientation(i);
        setExtendDisplayOrientation(i);
        reboot(1);
    }

    @Override
    public void setPrimaryDisplayOrientation(int i) {
        ComponentName componentName = new ComponentName("com.yian.yiansettings", "com.yian.yiansettings.ZTLService");
        if (i == getDisplayOrientation()) {
            Log.e("当前方向", "与旋转方向一致，不执行");
            return;
        }
        Intent intent = new Intent();
        intent.setComponent(componentName);
        intent.putExtra("cmd", "set_display_rotation");
        intent.putExtra(Key.ROTATION, i);
        intent.setPackage("com.yian.yiansettings");
        intent.putExtra(IdentifyAction.display, "primary");
        this.mContext.startService(intent);
    }

    @Override
    public void setExtendDisplayOrientation(int i) {
        ComponentName componentName = new ComponentName("com.yian.yiansettings", "com.yian.yiansettings.ZTLService");
        if (i == getDisplayOrientation()) {
            Log.e("当前方向", "与旋转方向一致，不执行");
            return;
        }
        Intent intent = new Intent();
        intent.setComponent(componentName);
        intent.putExtra("cmd", "set_display_rotation");
        intent.putExtra(Key.ROTATION, i);
        intent.putExtra(IdentifyAction.display, "extend");
        intent.setPackage("com.yian.yiansettings");
        this.mContext.startService(intent);
    }

    @Override
    public String[] getHDMIResolutions() {
        try {
            return new String[]{execRootCmd("wm size") + "\r mipi屏不支持获取HDMI分辨率列表"};
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public int setBuildSerial(String str) {
        ComponentName componentName = new ComponentName("com.yian.yiansettings", "com.yian.yiansettings.ZTLService");
        Intent intent = new Intent();
        intent.setComponent(componentName);
        intent.setPackage("com.yian.yiansettings");
        intent.putExtra("cmd", "set_sn");
        intent.putExtra("value", str);
        this.mContext.startService(intent);
        return 0;
    }
}
