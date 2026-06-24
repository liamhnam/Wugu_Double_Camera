package android.p012xc;

public class Disk {
    static final String TAG = "Disk";

    private native void native_mmc_clear(String str, int i, int i2);

    private native String native_mmc_read(String str, int i, int i2);

    private native int native_mmc_write(String str, int i, String str2, int i2);

    static {
        System.loadLibrary("androidjni");
    }
}
