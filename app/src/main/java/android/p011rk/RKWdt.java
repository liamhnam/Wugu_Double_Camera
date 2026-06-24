package android.p011rk;

public class RKWdt {
    private static native int native_DisableWdt();

    private static native int native_EnableWtd(int i);

    private static native int native_FeedWdt();

    private static native int native_getTimeoutWdt();

    public static int EnableWtd(int i) {
        return native_EnableWtd(i);
    }

    public static int FeedWdt() {
        return native_FeedWdt();
    }

    public static int DisableWdt() {
        return native_DisableWdt();
    }

    public static int getTimeoutWdt() {
        return native_getTimeoutWdt();
    }

    static {
        System.loadLibrary("wdt_x");
    }
}
