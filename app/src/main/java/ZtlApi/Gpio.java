package ZtlApi;

import android.util.Log;
import com.google.android.exoplayer2.metadata.icy.IcyHeaders;
import com.tom_roush.pdfbox.pdmodel.documentinterchange.taggedpdf.PDLayoutAttributeObject;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class Gpio {
    private static final String TAG = "GPIO";
    String gpio_name;
    private int mPort;
    public static final String[] rk3288 = {"", "GPIO0_C2", "GPIO7_B5", "GPIO8_B0", "GPIO7_B4", "GPIO7_C5", "GPIO7_B3", "GPIO8_A2", "GPIO7_A6", "GPIO8_A1", "GPIO7_A5"};
    public static final String[] rk3126c = {"", "GPIO1_C7", "GPIO2_A2", "GPIO1_A7", "GPIO2_A0", "GPIO2_A1", "GPIO0_C7", "GPIO2_A6", "GPIO2_A3", "GPIO1_A3", "GPIO3_C5"};
    public static final String[] CQA64 = {"", "PE7", "PE4", "PE3", "PE2", "PE1"};
    public static final String[] rk3368 = {"", "GPIO1_A2", "GPIO1_B6", "GPIO1_A3", "GPIO1_B7", "GPIO1_A4", "GPIO1_C0", "GPIO1_A5", "GPIO1_C1", "GPIO1_A6", "GPIO1_A7", "GPIOD_1"};
    public static final String[] rk3399 = {"", "GPIO2_B2", "GPIO2_A5", "GPIO2_A3", "GPIO2_A1", "GPIO2_A6", "GPIO2_B0", "GPIO2_A4", "GPIO2_B1", "GPIO2_B4", "GPIO2_A0", "GPIO2_A7", "GPIO2_A2"};
    public static final HashMap<String, String[]> GpioNameMap = new HashMap<String, String[]>() {
        {
            put("rk3288", Gpio.rk3288);
            put("rk3126", Gpio.rk3126c);
            put("A64", Gpio.CQA64);
            put("rk3368", Gpio.rk3368);
            put("rk3399", Gpio.rk3399);
        }
    };
    private boolean isGpioPortPrepared = false;
    private File mGpioExport = null;
    private File mGpioUnExport = null;
    private File mGpioPort = null;
    private File mGpioPortDirection = null;
    private File mGpioPortValue = null;
    private String gpio_export = "/sys/class/gpio/export";
    private String gpio_unexport = "/sys/class/gpio/unexport";
    private String gpio_port = "/sys/class/gpio/gpio";
    String lastError = "";
    int trynCount = 0;

    public boolean open(String str) {
        if (str == null || str.isEmpty()) {
            return false;
        }
        this.gpio_name = str;
        int iGpioStringToInt = ZtlManager.GetInstance().gpioStringToInt(str);
        if (iGpioStringToInt == -1) {
            return false;
        }
        this.mPort = iGpioStringToInt;
        this.mGpioExport = new File(this.gpio_export);
        this.mGpioUnExport = new File(this.gpio_unexport);
        boolean zPrepare_gpio_port = prepare_gpio_port(this.mPort);
        this.isGpioPortPrepared = zPrepare_gpio_port;
        return zPrepare_gpio_port;
    }

    public boolean open(int i) {
        this.gpio_name = i + "";
        if (i == -1) {
            return false;
        }
        this.mPort = i;
        this.mGpioExport = new File(this.gpio_export);
        this.mGpioUnExport = new File(this.gpio_unexport);
        boolean zPrepare_gpio_port = prepare_gpio_port(this.mPort);
        this.isGpioPortPrepared = zPrepare_gpio_port;
        return zPrepare_gpio_port;
    }

    public String getDirection() {
        File file;
        return (this.isGpioPortPrepared && (file = this.mGpioPortDirection) != null && file.exists()) ? readGpioNode(this.mGpioPortDirection) : "unknown";
    }

    public void setDirection(String str) {
        if (!this.isGpioPortPrepared || this.mGpioPortDirection == null || getDirection().equals(str)) {
            return;
        }
        writeGpioNode(this.mGpioPortDirection, str);
    }

    public int getValue() {
        String gpioNode;
        if (!this.isGpioPortPrepared || this.mGpioPortDirection == null || (gpioNode = readGpioNode(this.mGpioPortValue)) == null) {
            return -1;
        }
        if (gpioNode.equals(PDLayoutAttributeObject.GLYPH_ORIENTATION_VERTICAL_ZERO_DEGREES)) {
            return 0;
        }
        if (gpioNode.equals(IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE)) {
            return 1;
        }
        try {
            return Integer.valueOf(gpioNode).intValue();
        } catch (NumberFormatException unused) {
            return -1;
        }
    }

    public int getValue(String str) {
        if (!this.isGpioPortPrepared || !this.mGpioPort.exists() || !this.mGpioPortDirection.exists()) {
            return -1;
        }
        if (readGpioNode(this.mGpioPortDirection).equals(str)) {
            return getValue();
        }
        setDirection(str);
        return getValue();
    }

    public void setValue(int i) {
        if (this.isGpioPortPrepared && getValue() != i) {
            writeGpioNode(this.mGpioPortValue, Integer.toString(i));
        }
    }

    public void setValue(String str, int i) {
        if (this.isGpioPortPrepared) {
            if (!getDirection().equals(str)) {
                writeGpioNode(this.mGpioPortDirection, str);
            }
            writeGpioNode(this.mGpioPortValue, Integer.toString(i));
        }
    }

    private void writeGpioNode(File file, String str) {
        if (file.exists() && file.exists()) {
            try {
                FileOutputStream fileOutputStream = new FileOutputStream(file);
                fileOutputStream.write(str.getBytes(), 0, str.getBytes().length);
                fileOutputStream.flush();
                fileOutputStream.close();
                this.trynCount = 0;
            } catch (IOException e) {
                if (e.toString().contains("Permission denied")) {
                    this.trynCount++;
                    ZtlManager.GetInstance().execRootCmdSilent("chmod 777 " + file.getAbsolutePath());
                    Log.e(TAG, "正在申请权限");
                    if (this.trynCount > 2) {
                        Log.e(TAG, "权限申请不通过");
                        return;
                    } else {
                        writeGpioNode(file, str);
                        return;
                    }
                }
                this.trynCount++;
                Log.e(TAG, "writeGpioNode " + this.gpio_name + " 错误");
                e.printStackTrace();
                if (this.trynCount > 2) {
                    Log.e(TAG, "权限申请不通过");
                }
            }
        }
    }

    private boolean prepare_gpio_port(int i) {
        File file = this.mGpioExport;
        if (file == null || !file.exists()) {
            return false;
        }
        String str = this.gpio_port + i;
        if (!ZtlManager.GetInstance().isExist(str)) {
            writeGpioNode(this.mGpioExport, Integer.toString(i));
        }
        String str2 = str + "/direction";
        String str3 = str + "/value";
        File file2 = new File(str);
        this.mGpioPort = file2;
        if (!file2.exists()) {
            if (this.gpio_name != null) {
                Log.e(TAG, "系统没有导出" + this.gpio_name + " 请看文档或查询定昌技术支持");
            }
            this.lastError = "系统没有导出这个io口。请看文档或查询定昌技术支持" + this.mPort;
            return false;
        }
        this.mGpioPortDirection = new File(str2);
        this.mGpioPortValue = new File(str3);
        return this.mGpioPort.exists() && this.mGpioPortDirection.exists() && this.mGpioPortValue.exists();
    }

    public String getLastError() {
        String str = this.lastError;
        this.lastError = "";
        return str;
    }

    private boolean gpio_request() {
        return this.isGpioPortPrepared;
    }

    void gpio_free() {
        if (this.isGpioPortPrepared) {
            writeGpioNode(this.mGpioUnExport, Integer.toString(this.mPort));
        }
    }

    private String readGpioNode(File file) {
        String line = null;
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            line = bufferedReader.readLine();
            bufferedReader.close();
            return line;
        } catch (IOException e) {
            e.printStackTrace();
            return line;
        }
    }
}
