package com.hiti.usb.service.network;

import android.content.Context;
import android.graphics.Bitmap;
import com.hiti.usb.utility.FileUtility;
import com.hiti.usb.utility.MobileInfo;
import org.eclipse.paho.client.mqttv3.MqttTopic;
import p000a.p001a.p002a.p006d.C0016g;
import p000a.p001a.p002a.p006d.C0017h;
import p000a.p001a.p002a.p010f.C0023a;

public class UploadUtility {
    public static boolean AddUploadPhoto(Context context, Bitmap bitmap, Bitmap bitmap2) {
        C0016g c0016g = new C0016g(context);
        c0016g.m83h();
        if (c0016g.m78c() < 1) {
            return false;
        }
        String str = FileUtility.GetSDAppRootPath(context) + "/print";
        String str2 = str + MqttTopic.TOPIC_LEVEL_SEPARATOR + FileUtility.GetNewNameWithExt(".jpg", "");
        FileUtility.CreateFolder(str);
        if (bitmap == null || !FileUtility.SaveBitmap(str2, bitmap, Bitmap.CompressFormat.JPEG)) {
            return false;
        }
        c0016g.m77a(str2, MobileInfo.GetTimeStamp());
        c0016g.m84i();
        return true;
    }

    public static boolean HaveCanUpload(C0016g c0016g) {
        return c0016g.m78c() == 1;
    }

    public static boolean HaveUploadE03(C0016g c0016g) {
        return c0016g.m79d() == 1;
    }

    public static boolean HaveUploadPhoto(C0016g c0016g) {
        return c0016g.m80e() > 0;
    }

    public static boolean HaveVerify(C0017h c0017h) {
        return c0017h.m86c() == 1;
    }

    public static boolean HaveWifiUploadMethod(Context context, C0016g c0016g) {
        return c0016g.m81f() == 0 && !C0023a.m99a(context);
    }

    public static boolean NeedUploadPhoto(Context context) {
        C0016g c0016g = new C0016g(context);
        c0016g.m83h();
        return c0016g.m78c() >= 1;
    }
}
