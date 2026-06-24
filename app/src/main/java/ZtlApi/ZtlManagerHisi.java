package ZtlApi;

import android.content.Intent;
import android.util.Log;

public class ZtlManagerHisi extends ZtlManager {
    @Override
    public int getDisplayCount() {
        return 1;
    }

    @Override
    public void setDisplayOrientation(int i) {
        setSystemProperty("persist.ztl.hwrotation", Integer.toString(i));
        reboot(1);
    }

    @Override
    public void openSystemBar(boolean z) {
        if (this.mContext == null) {
            Log.e(TAG, "上下文为空,不执行");
            return;
        }
        Intent intent = new Intent("com.ztl.action.systembar");
        intent.putExtra("enable", z);
        intent.putExtra("skip_permission", true);
        this.mContext.sendBroadcast(intent);
    }

    @Override
    public boolean isSystemBarOpen() {
        return Boolean.valueOf(getSystemProperty("persist.ztl.systembar", "true")).booleanValue();
    }

    @Override
    public int gpioStringToInt(String str) {
        if (!str.contains("GPIO")) {
            Log.e(TAG, "传入参数错误,请传入GPIO0_2之类的，实际以规格书为准");
            return -1;
        }
        try {
            int i = (511 - (Integer.parseInt(str.substring(4, str.indexOf("_"))) * 8)) - ((8 - Integer.parseInt(str.substring(str.indexOf("_") + 1))) - 1);
            if (i >= 416 || i <= 511) {
                return i;
            }
            Log.e(TAG, "传入参数错误,请传入GPIO0_2之类的，实际以规格书为准");
            return -1;
        } catch (NumberFormatException unused) {
            Log.e(TAG, "传入参数错误,请传入GPIO0_2之类的，实际以规格书为准");
            return -1;
        }
    }
}
