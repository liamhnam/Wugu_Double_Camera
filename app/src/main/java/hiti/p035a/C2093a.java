package hiti.p035a;

import android.content.Context;
import com.hiti.usb.utility.FileUtility;

public class C2093a {
    public static String m1272a(Context context, EnumC2094b enumC2094b) {
        return FileUtility.GetSDAppRootPath(context) + "/FW_PRINGO/" + m1273a(enumC2094b);
    }

    public static String m1273a(EnumC2094b enumC2094b) {
        return "ROM_ALL_" + enumC2094b.name() + ".bin";
    }
}
