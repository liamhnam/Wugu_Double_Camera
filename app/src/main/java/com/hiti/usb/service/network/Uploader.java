package com.hiti.usb.service.network;

import android.content.Context;
import android.location.Location;
import com.hiti.usb.service.network.INet;
import com.hiti.usb.utility.MobileInfo;
import hiti.p035a.EnumC2094b;
import java.util.HashMap;
import p000a.p001a.p002a.p006d.C0012c;
import p000a.p001a.p002a.p006d.C0015f;
import p000a.p001a.p002a.p006d.C0017h;
import p000a.p001a.p002a.p007e.p009b.C0022b;

public abstract class Uploader {
    private static final boolean localLOG = false;
    private static final String tag = "Uploader";
    private Context m_Context;
    public EnumC2094b productId;
    private String strLatitude = null;
    private String strLongitude = null;

    public Uploader(Context context, EnumC2094b enumC2094b) {
        this.m_Context = context;
        this.productId = enumC2094b;
        new C0012c(this.m_Context).m54c();
    }

    private String SendPrinterInfoXML(int i, String str, HashMap<String, String> map, String str2, String str3) throws InterruptedException {
        if (Thread.interrupted()) {
            throw new InterruptedException();
        }
        return new C0022b(this.m_Context).m97a(String.valueOf(i), str, map, str2, str3);
    }

    private int getElementID(EnumC2094b enumC2094b) {
        if (enumC2094b == EnumC2094b.P310W) {
            return 9;
        }
        if (enumC2094b == EnumC2094b.P461) {
            return 10;
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

    public C0015f CheckPrintedRecord(EnumC2094b enumC2094b) {
        C0015f c0015f = new C0015f(this.m_Context, enumC2094b.name());
        c0015f.m70f();
        if (c0015f.m69e()) {
            return null;
        }
        return c0015f;
    }

    public void UploadProcess() throws InterruptedException {
        if (beforeUpload()) {
            Location locationGetLocation = MobileInfo.GetLocation(this.m_Context, false);
            if (locationGetLocation != null) {
                this.strLatitude = String.valueOf(locationGetLocation.getLatitude());
                this.strLongitude = String.valueOf(locationGetLocation.getLongitude());
            }
            C0017h c0017h = new C0017h(this.m_Context);
            c0017h.m87d();
            if (!UploadUtility.HaveVerify(c0017h)) {
                uploaded(INet.Result.VerifyFail, null);
            } else {
                uploaded(INet.Result.Uploaded, UploadTotalPrintedRecord(this.productId, this.strLatitude, this.strLongitude));
            }
        }
    }

    public String UploadTotalPrintedRecord(EnumC2094b enumC2094b, String str, String str2) throws InterruptedException {
        int elementID;
        new HashMap();
        C0015f c0015fCheckPrintedRecord = CheckPrintedRecord(enumC2094b);
        if (c0015fCheckPrintedRecord == null || (elementID = getElementID(enumC2094b)) == -1) {
            return null;
        }
        String strSendPrinterInfoXML = SendPrinterInfoXML(elementID, c0015fCheckPrintedRecord.m68d(), c0015fCheckPrintedRecord.m67c(), str, str2);
        if (strSendPrinterInfoXML != null) {
            c0015fCheckPrintedRecord.m51a();
        }
        return strSendPrinterInfoXML;
    }

    public abstract boolean beforeUpload();

    public abstract void uploaded(INet.Result result, String str);
}
