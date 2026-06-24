package p000a.p001a.p002a.p004b;

import android.content.Context;
import com.hiti.usb.utility.EncryptAndDecryptAES;
import com.hiti.usb.utility.LogManager;

public class C0007a {

    public static String f69a = "A2B9C1D2A6B2C6D8";

    public C0007a(Context context) {
        new LogManager(0);
    }

    public static boolean m45c(String str) {
        return Integer.valueOf(str.substring(0, 1)).intValue() == 1 && str.length() > 1;
    }

    public String m46a(String str) {
        return str.length() > 0 ? str.substring(str.indexOf(f69a) + f69a.length(), str.length()) : "";
    }

    public String m47a(String str, String str2, String str3) {
        return EncryptAndDecryptAES.DecryptStrNoPadding(str.substring(1), str2, str3);
    }

    public String m48b(String str) {
        return str.length() > 0 ? str.substring(0, str.indexOf(f69a)) : "";
    }
}
