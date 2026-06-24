package ZtlApi;

import android.util.Log;
import androidx.exifinterface.media.ExifInterface;
import com.google.android.exoplayer2.metadata.icy.IcyHeaders;
import com.wugu.doublecamera.enums.UiPosIndexEnum;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class ZtlManager3399Pro extends ZtlManagerU202 {
    private String TAG = "ZtlManager3399Pro";

    @Override
    public int getDisplayCount() {
        return 2;
    }

    ZtlManager3399Pro() {
        this.DEBUG_ZTL = getSystemProperty("persist.sys.ztl.debug", "false").equals("true");
    }

    class ResolutionInfo implements Comparable<ResolutionInfo> {
        String dev;
        int height;
        int width;

        public ResolutionInfo(String str) {
            this.dev = str;
            String[] strArrSplit = str.substring(0, str.indexOf(UiPosIndexEnum.HOME_COUNTDOWN)).split("x");
            this.width = Integer.valueOf(strArrSplit[0]).intValue();
            this.height = Integer.valueOf(strArrSplit[1]).intValue();
        }

        public ResolutionInfo(int i, int i2) {
            this.width = i;
            this.height = i2;
            this.dev = String.format("%dx%dp60", Integer.valueOf(i), Integer.valueOf(i2));
        }

        public String toString() {
            return this.dev;
        }

        public boolean isSame(ResolutionInfo resolutionInfo) {
            return this.width == resolutionInfo.width && this.height == resolutionInfo.height;
        }

        @Override
        public int compareTo(ResolutionInfo resolutionInfo) {
            int i = this.width;
            int i2 = resolutionInfo.width;
            if (i > i2) {
                return -1;
            }
            if (i != i2) {
                return 1;
            }
            int i3 = this.height;
            int i4 = resolutionInfo.height;
            if (i3 > i4) {
                return -1;
            }
            if (i3 < i4) {
                return 1;
            }
            String str = this.dev;
            if (str != null) {
                return -str.compareTo(resolutionInfo.dev);
            }
            return 0;
        }
    }

    @Override
    public String[] getHDMIResolutions() {
        try {
            return loadFileAsString("/sys/class/drm/card0-HDMI-A-1/modes").split("\n");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void setDisplayOrientation(int i) {
        setSystemProperty("persist.ztl.hwrotation", String.valueOf(i));
        Log.e("3399pro", "屏幕旋转角度" + i);
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
        try {
            setSystemProperty("persist.ztl.hwrotation", i + "");
        } catch (NumberFormatException e) {
            e.printStackTrace();
            Log.e(this.TAG, "暂不支持此接口，等待后续开发");
        }
    }

    @Override
    public void setExtendDisplayOrientation(int i) {
        try {
            setSystemProperty("persist.ztl.extend.rotation", i + "");
        } catch (NumberFormatException e) {
            e.printStackTrace();
            Log.e(this.TAG, "暂不支持此接口，等待后续开发");
        }
    }

    @Override
    public void setScreenMode(String str) {
        int iIndexOf = str.indexOf(UiPosIndexEnum.HOME_COUNTDOWN);
        if (iIndexOf == -1) {
            return;
        }
        setSystemProperty("persist.sys.framebuffer.main", str.substring(0, iIndexOf));
        setSystemProperty("persist.sys.resolution.aux", str);
        reboot(0);
    }

    @Override
    public String getDisplayMode() {
        try {
            return loadFileAsString("/sys/class/drm/card0-HDMI-A-1/modes");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean isValidResolution(ResolutionInfo resolutionInfo) {
        Iterator<ResolutionInfo> it = getHdmiResolu().iterator();
        while (it.hasNext()) {
            if (it.next().isSame(resolutionInfo)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int gpioStringToInt(String str) {
        if (!str.contains("GPIO")) {
            Log.e(this.TAG, "传入参数错误,请传入GPIO7_A5之类的，实际以规格书为准");
            return -1;
        }
        return (((((str.charAt(4) - '0') & 255) * 32) + ((str.charAt(6) - 'A') * 8)) + str.charAt(7)) - 48;
    }

    List<ResolutionInfo> getHdmiResolu() {
        try {
            String[] strArrSplit = loadFileAsString("/sys/class/drm/card0-HDMI-A-1/modes").split("\n");
            ArrayList arrayList = new ArrayList();
            for (String str : strArrSplit) {
                arrayList.add(new ResolutionInfo(str));
            }
            Collections.sort(arrayList);
            return arrayList;
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList();
        }
    }

    List<String> getHdmiResolutions() {
        List<ResolutionInfo> hdmiResolu = getHdmiResolu();
        ArrayList arrayList = new ArrayList();
        Iterator<ResolutionInfo> it = hdmiResolu.iterator();
        while (it.hasNext()) {
            arrayList.add(it.next().toString());
        }
        return arrayList;
    }

    @Override
    public void setUSBtoPC(boolean z) {
        if (z) {
            setSystemProperty("persist.usb.mode", ExifInterface.GPS_MEASUREMENT_2D);
            execRootCmdSilent("echo 2 > /sys/devices/platform/usb0/dwc3_mode");
        } else {
            setSystemProperty("persist.usb.mode", IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE);
            execRootCmdSilent("echo 1 > /sys/devices/platform/usb0/dwc3_mode");
        }
    }

    @Override
    public boolean getUSBtoPC() {
        return getSystemProperty("persist.usb.mode", "").equals(ExifInterface.GPS_MEASUREMENT_2D);
    }
}
