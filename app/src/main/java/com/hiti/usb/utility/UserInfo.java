package com.hiti.usb.utility;

import android.content.Context;
import android.util.Pair;
import com.google.android.exoplayer2.metadata.icy.IcyHeaders;
import com.hiti.jni.hello.C1588a;
import p000a.p001a.p002a.p004b.C0007a;
import p000a.p001a.p002a.p006d.C0016g;
import p000a.p001a.p002a.p006d.C0017h;

public class UserInfo {
    private static final boolean localLOG = false;
    private static final String tag = "UserInfo";

    public static void ChangeUserDueToOtherApp(Context context, String str) {
        if (str == null || str.length() <= 0) {
            return;
        }
        UserLogin(context, str);
    }

    public static void FakeUserLogin(Context context, String str, String str2) {
        String strEncryptStr = EncryptAndDecryptAES.EncryptStr((IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE + EncryptAndDecryptAES.EncryptStrNoPadding(str + C0007a.f69a + str2, C1588a.m401a(context, 7894), C1588a.m402b(context, 7894))) + MobileInfo.GetIMEI(context), C1588a.m401a(context, 7895), C1588a.m402b(context, 7895));
        C0016g c0016g = new C0016g(context);
        C0017h c0017h = new C0017h(context);
        c0016g.m83h();
        c0016g.m76a(strEncryptStr);
        c0016g.m75a(0);
        c0016g.m84i();
        c0017h.m87d();
        c0017h.m85a(1);
        c0017h.m88e();
    }

    public static Pair<String, String> GetUP(Context context, String str) {
        String strDecryptStr;
        if (!HaveUpLoader(str) || (strDecryptStr = EncryptAndDecryptAES.DecryptStr(str, C1588a.m401a(context, 7895), C1588a.m402b(context, 7895))) == null) {
            return null;
        }
        String strReplace = strDecryptStr.replace(MobileInfo.GetIMEI(context), "");
        if (strDecryptStr.length() == strReplace.length()) {
            return null;
        }
        C0007a c0007a = new C0007a(context);
        String strM47a = c0007a.m47a(strReplace, C1588a.m401a(context, 7894), C1588a.m402b(context, 7894));
        String strM48b = c0007a.m48b(strM47a);
        String strM46a = c0007a.m46a(strM47a);
        if (HaveUP(strM48b, strM46a)) {
            return new Pair<>(strM48b, strM46a);
        }
        return null;
    }

    public static String GetUploader(Context context) {
        C0016g c0016g = new C0016g(context);
        c0016g.m83h();
        return c0016g.m82g();
    }

    public static String GetUser(Context context) {
        C0016g c0016g = new C0016g(context);
        c0016g.m83h();
        Pair<String, String> pairGetUP = GetUP(context, c0016g.m82g());
        if (pairGetUP == null) {
            return null;
        }
        return (String) pairGetUP.first;
    }

    public static boolean HaveLogin(Context context) {
        C0016g c0016g = new C0016g(context);
        c0016g.m83h();
        C0017h c0017h = new C0017h(context);
        c0017h.m87d();
        return c0017h.m86c() == 1 && HaveUpLoader(c0016g.m82g());
    }

    public static boolean HaveUP(String str, String str2) {
        return (str == null || str2 == null || str.length() == 0 || str2.length() == 0) ? false : true;
    }

    public static boolean HaveUpLoader(String str) {
        return str != null && str.length() > 0;
    }

    private static String ReEncryptEUploader(Context context, String str, String str2, String str3) {
        return str.substring(0, 1) + EncryptAndDecryptAES.DecryptStrNoPadding(str.substring(1), str2, str3);
    }

    public static void UserLogin(Context context, String str) {
        C0016g c0016g = new C0016g(context);
        C0017h c0017h = new C0017h(context);
        c0016g.m83h();
        c0016g.m76a(str);
        c0016g.m75a(0);
        c0016g.m84i();
        c0017h.m87d();
        c0017h.m85a(1);
        c0017h.m88e();
    }

    public static void UserLogin(Context context, String str, String str2, String str3) {
        if (C0007a.m45c(str)) {
            String strEncryptStr = EncryptAndDecryptAES.EncryptStr(ReEncryptEUploader(context, str, str2, str3) + MobileInfo.GetIMEI(context), C1588a.m401a(context, 7895), C1588a.m402b(context, 7895));
            C0016g c0016g = new C0016g(context);
            C0017h c0017h = new C0017h(context);
            c0016g.m83h();
            c0016g.m76a(strEncryptStr);
            c0016g.m75a(0);
            c0016g.m84i();
            c0017h.m87d();
            c0017h.m85a(1);
            c0017h.m88e();
        }
    }

    public static void UserLogout(Context context) {
        C0016g c0016g = new C0016g(context);
        C0017h c0017h = new C0017h(context);
        c0016g.m83h();
        c0016g.m75a(0);
        c0016g.m84i();
        c0017h.m87d();
        c0017h.m85a(0);
        c0017h.m88e();
    }
}
