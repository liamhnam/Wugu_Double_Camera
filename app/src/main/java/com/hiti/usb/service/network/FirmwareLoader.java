package com.hiti.usb.service.network;

import android.content.Context;
import android.util.Pair;
import androidx.exifinterface.media.ExifInterface;
import com.hiti.jni.hello.C1588a;
import com.hiti.usb.utility.EncryptAndDecryptAES;
import com.hiti.usb.utility.FileUtility;
import com.hiti.usb.utility.MobileInfo;
import com.hiti.usb.utility.UserInfo;
import com.hiti.usb.utility.ZipUtility;
import com.wugu.doublecamera.enums.UiPosIndexEnum;
import hiti.p035a.C2093a;
import hiti.p035a.EnumC2094b;
import java.io.File;
import org.eclipse.paho.client.mqttv3.MqttTopic;
import p000a.p001a.p002a.p006d.C0014e;
import p000a.p001a.p002a.p006d.C0016g;
import p000a.p001a.p002a.p007e.p008a.C0019b;
import p000a.p001a.p002a.p007e.p008a.C0020c;
import p000a.p001a.p002a.p007e.p009b.C0021a;
import p000a.p001a.p002a.p007e.p009b.C0022b;

public abstract class FirmwareLoader {
    private static final boolean localLOG = false;
    private static final String tag = "FirmwareLoader";
    private Context context;
    private C0016g m_GVUploadInfo;
    private String m_strXMLVersion = ExifInterface.GPS_MEASUREMENT_2D;
    private EnumC2094b productId;

    public FirmwareLoader(Context context, EnumC2094b enumC2094b) {
        this.m_GVUploadInfo = null;
        this.context = context;
        this.productId = enumC2094b;
        this.m_GVUploadInfo = new C0016g(context);
    }

    private C0021a GetFWXML(Context context, String str, int i, int i2, int i3) throws InterruptedException {
        if (Thread.interrupted()) {
            throw new InterruptedException();
        }
        C0022b c0022b = new C0022b(context);
        String strM96a = c0022b.m96a(str, String.valueOf(i), String.valueOf(i2), String.valueOf(i3));
        if (strM96a == null) {
            return null;
        }
        C0021a c0021aM95a = c0022b.m95a(c0022b.m98b(strM96a));
        if (C0021a.m92a(c0021aM95a) && c0021aM95a.f105b != "") {
            return c0021aM95a;
        }
        return null;
    }

    private int GetPANumber(C0016g c0016g) {
        Pair<String, String> pairGetUP = UserInfo.GetUP(this.context, c0016g.m82g());
        if (pairGetUP == null) {
            return 0;
        }
        boolean zEquals = ((String) pairGetUP.first).equals("pringoae1");
        if (((String) pairGetUP.first).equals("pringopatest")) {
            return 2;
        }
        return zEquals ? 1 : 0;
    }

    private int GetReleaseFlag(C0016g c0016g) {
        return GetPANumber(c0016g) == 0 ? 0 : 1;
    }

    private String downloadFWFromFTP(Context context, C0021a c0021a) throws InterruptedException {
        if (Thread.interrupted()) {
            throw new InterruptedException();
        }
        if (c0021a.f107d.length() <= 0) {
            return null;
        }
        String str = c0021a.f107d.contains(".zip") ? ".zip" : ".bin";
        FileUtility.CreateFolder(FileUtility.GetSDAppRootPath(context) + "/FW_PRINGO");
        String str2 = FileUtility.GetSDAppRootPath(context) + "/FW_PRINGO/" + MobileInfo.GetDateStamp() + str;
        if (!C0020c.m91a(C0019b.m89a(c0021a.f106c, "21", c0021a.f108e, EncryptAndDecryptAES.MakeMD5(c0021a.f109f + C1588a.m402b(context, UiPosIndexEnum.PE_WATER_MARK)), c0021a.f107d, str2))) {
            FileUtility.DeleteFile(str2);
            return null;
        }
        String strM1273a = C2093a.m1273a(this.productId);
        FileUtility.DeleteFile(C2093a.m1272a(context, this.productId));
        String strUnzipFW = str2.contains(".zip") ? unzipFW(str2, strM1273a) : FileUtility.ReNameFile(str2, strM1273a);
        if (strUnzipFW != null) {
            C0014e c0014e = new C0014e(context);
            c0014e.m62c();
            c0014e.m61a(this.productId, c0021a.f105b);
            c0014e.m63d();
        }
        return strUnzipFW;
    }

    private int getElementID(EnumC2094b enumC2094b) {
        if (enumC2094b == EnumC2094b.P310W) {
            return 9;
        }
        if (enumC2094b == EnumC2094b.P520L) {
            return 7;
        }
        if (enumC2094b == EnumC2094b.P525N) {
            return 18;
        }
        if (enumC2094b == EnumC2094b.P750L) {
            return 8;
        }
        if (enumC2094b == EnumC2094b.P530D) {
            return 11;
        }
        if (enumC2094b == EnumC2094b.P461) {
            return 10;
        }
        if (enumC2094b == EnumC2094b.CS200E) {
            return 14;
        }
        if (enumC2094b == EnumC2094b.CS220E) {
            return 15;
        }
        if (enumC2094b == EnumC2094b.CS290E) {
            return 16;
        }
        return enumC2094b == EnumC2094b.CS280E ? 17 : -1;
    }

    private String unzipFW(String str, String str2) {
        String str3 = FileUtility.GetFolderFullPath(str) + File.separator + FileUtility.GetFileNameWithoutExt(str2);
        FileUtility.CreateFolder(str3);
        if (FileUtility.FileExist(str3)) {
            String strUnpackZipForFW = ZipUtility.UnpackZipForFW(str3, str);
            FileUtility.DeleteFile(str);
            if (strUnpackZipForFW != null && strUnpackZipForFW.length() != 0) {
                String str4 = strUnpackZipForFW.substring(0, strUnpackZipForFW.lastIndexOf(MqttTopic.TOPIC_LEVEL_SEPARATOR)) + ".bin";
                boolean zReFullPathFile = FileUtility.ReFullPathFile(strUnpackZipForFW, str4);
                FileUtility.DeleteFile(strUnpackZipForFW);
                FileUtility.DeleteALLFolder(str3);
                if (zReFullPathFile) {
                    return str4;
                }
            }
        }
        return null;
    }

    public abstract void AfterCheckFirmwareDone(String str, String str2);

    public void downloadFW() throws InterruptedException {
        C0016g c0016g;
        int elementID = getElementID(this.productId);
        if (elementID == -1 || (c0016g = this.m_GVUploadInfo) == null || this.context == null) {
            return;
        }
        c0016g.m83h();
        C0021a c0021aGetFWXML = GetFWXML(this.context, this.m_strXMLVersion, 2, elementID, GetReleaseFlag(this.m_GVUploadInfo));
        String strDownloadFWFromFTP = (c0021aGetFWXML == null || !isDownloadFwFile(c0021aGetFWXML, this.productId)) ? null : downloadFWFromFTP(this.context, c0021aGetFWXML);
        String sdFwVersion = FirmwareUtility.getSdFwVersion(this.context, this.productId, true);
        if (sdFwVersion != null) {
            strDownloadFWFromFTP = C2093a.m1272a(this.context, this.productId);
        }
        AfterCheckFirmwareDone(sdFwVersion, strDownloadFWFromFTP);
    }

    boolean isDownloadFwFile(C0021a c0021a, EnumC2094b enumC2094b) {
        return Integer.parseInt(FirmwareUtility.simpleFwFormat(c0021a.f105b)) > Integer.parseInt((String) FirmwareUtility.getCurrentFWVersion(this.context, enumC2094b, true).second);
    }
}
