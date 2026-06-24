package ZtlApi;

import android.os.SystemProperties;
import android.util.Log;
import java.io.File;

public class ZtlManagerA40i extends ZtlManager {
    private boolean DEBUG_ZTL;
    private String TAG = "ZtlManagerA40i";

    @Override
    public void setScreenMode(String str) {
    }

    public ZtlManagerA40i() {
        this.DEBUG_ZTL = false;
        this.DEBUG_ZTL = SystemProperties.get("persist.sys.ztl.debug", "false").equals("true");
    }

    @Override
    void LOGD(String str) {
        if (this.DEBUG_ZTL) {
            Log.d(this.TAG, str);
        }
    }

    @Override
    public String getUsbStoragePath() {
        try {
            if (new File("/storage/udisk3").exists()) {
                return "/storage/udisk3";
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public int gpioStringToInt(String str) {
        if (!str.startsWith("P")) {
            Log.e(this.TAG, "传入参数错误,请传入PE7之类的，实际以规格书为准");
            return -1;
        }
        int iCharAt = str.charAt(1) - 'A';
        if (iCharAt <= 9 && iCharAt >= 0) {
            int i = Integer.parseInt(str.substring(2));
            if (i <= 31 && i >= 0) {
                return (iCharAt * 32) + i;
            }
            Log.e(this.TAG, "传入参数错误,请传入PE7之类的，实际以规格书为准");
            return -1;
        }
        Log.e(this.TAG, "传入参数错误,请传入PE7之类的，实际以规格书为准");
        return -1;
    }
}
