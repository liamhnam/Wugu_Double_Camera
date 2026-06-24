package android.p012xc;

public class RKGpio {
    private static native int native_GetGpioValue(int i);

    private static native void native_InitNative();

    private static native void native_SetGpioValue(int i, int i2);

    public static void Init() {
        native_InitNative();
    }

    public static void RKSetGpioValue(int i, int i2) {
        native_SetGpioValue(i, i2);
    }

    public static int RKGetGpioValue(int i) {
        return native_GetGpioValue(i);
    }

    static {
        System.loadLibrary("androidjni");
    }
}
