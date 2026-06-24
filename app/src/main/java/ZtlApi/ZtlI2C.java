package ZtlApi;

import android.util.Log;

public class ZtlI2C {
    int chipID;
    String filePath;

    public static native void i2c_close();

    public static native int i2c_open(String str, int i, int i2);

    public static native byte[] i2c_read(int i, int i2);

    public static native int i2c_write(int i, byte[] bArr, int i2);

    static {
        try {
            System.loadLibrary("customi2c");
        } catch (Exception e) {
            Log.e("ZtlI2C", "缺少so库，请联系智通利技术支持");
            e.printStackTrace();
        }
    }

    public boolean open(String str, int i, int i2) {
        this.filePath = str;
        this.chipID = i;
        return i2c_open(str, i, i2) > 0;
    }

    public void flash_write(int i, byte[] bArr, int i2) {
        i2c_write(i, bArr, i2);
    }

    public byte[] flash_read(int i, int i2) {
        return i2c_read(i, i2);
    }

    public void chip_write(int i, byte[] bArr, int i2) {
        i2c_write(i, bArr, i2);
    }

    public byte[] chip_read(int i, int i2) {
        return i2c_read(i, i2);
    }

    public void close() {
        i2c_close();
    }
}
