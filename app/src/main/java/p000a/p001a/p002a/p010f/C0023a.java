package p000a.p001a.p002a.p010f;

import android.content.Context;
import android.net.ConnectivityManager;

public class C0023a {
    public static boolean m99a(Context context) {
        return ((ConnectivityManager) context.getSystemService("connectivity")).getNetworkInfo(1).isConnected();
    }
}
