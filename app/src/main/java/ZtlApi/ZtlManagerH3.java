package ZtlApi;

import android.content.Intent;
import android.util.Log;
import androidx.exifinterface.media.ExifInterface;
import com.google.android.exoplayer2.metadata.icy.IcyHeaders;
import com.tom_roush.pdfbox.pdmodel.documentinterchange.taggedpdf.PDLayoutAttributeObject;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ZtlManagerH3 extends ZtlManager {
    private String TAG = "ZtlManagerH3";

    ZtlManagerH3() {
        this.DEBUG_ZTL = getSystemProperty("persist.sys.ztl.debug", "false").equals("true");
    }

    @Override
    public void setUSBtoPC(boolean z) {
        setSystemProperty("persist.usb.mode", z ? IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE : PDLayoutAttributeObject.GLYPH_ORIENTATION_VERTICAL_ZERO_DEGREES);
        if (z) {
            writeMethod("/sys/devices/platform/sunxi_usb_udc/otg_role", ExifInterface.GPS_MEASUREMENT_2D);
            simpleWriteFile("/mnt/Reserve0/user_setting.fex", IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE.getBytes());
        } else {
            writeMethod("/sys/devices/platform/sunxi_usb_udc/otg_role", IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE);
            simpleWriteFile("/mnt/Reserve0/user_setting.fex", PDLayoutAttributeObject.GLYPH_ORIENTATION_VERTICAL_ZERO_DEGREES.getBytes());
        }
    }

    public static void simpleWriteFile(String str, byte[] bArr) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(new File(str));
            fileOutputStream.write(bArr, 0, bArr.length);
            fileOutputStream.flush();
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean getUSBtoPC() {
        try {
            return loadFileAsString("/sys/devices/platform/sunxi_usb_udc/otg_role").contains(ExifInterface.GPS_MEASUREMENT_2D);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public void openSystemBar(boolean z) {
        if (this.mContext == null) {
            Log.e(this.TAG, "上下文为空,不执行");
            return;
        }
        Intent intent = new Intent("com.ztl.action.systembar");
        intent.setPackage("com.android.systemui");
        intent.putExtra("skip_permission", true);
        intent.putExtra("enable", z);
        this.mContext.sendBroadcast(intent);
    }

    @Override
    public boolean isSystemBarOpen() {
        return Boolean.valueOf(getSystemProperty("persist.ztl.systembar", "true")).booleanValue();
    }

    @Override
    public void setDisplayOrientation(int i) {
        if (this.mContext == null) {
            Log.e("上下文为空", "不执行");
        } else {
            if (i == getDisplayOrientation()) {
                Log.e("当前方向", "与旋转方向一致，不执行");
                return;
            }
            try {
                setSystemProperty("persist.ztl.hwrotation", i + "");
            } catch (Exception unused) {
                Log.e(this.TAG, "set rotation err!");
            }
        }
    }

    @Override
    public List<String> getUSBDisks() {
        ArrayList arrayList = new ArrayList();
        File file = new File("/mnt/usbhost/");
        try {
            if (!file.exists() || !file.isDirectory()) {
                return arrayList;
            }
            File[] fileArrListFiles = file.listFiles();
            if (fileArrListFiles.length <= 0) {
                return arrayList;
            }
            for (File file2 : fileArrListFiles) {
                String absolutePath = file2.getAbsolutePath();
                File file3 = new File(absolutePath);
                if (file3.exists() && file3.isDirectory()) {
                    arrayList.add(absolutePath);
                }
            }
            return arrayList.size() > 0 ? Collections.singletonList(arrayList.get(0)) : arrayList;
        } catch (Exception e) {
            e.printStackTrace();
            return arrayList;
        }
    }
}
