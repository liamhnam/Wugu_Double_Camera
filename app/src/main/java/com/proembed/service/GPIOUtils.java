package com.proembed.service;

import android.os.Build;
import android.util.Log;
import com.google.android.exoplayer2.metadata.icy.IcyHeaders;
import com.tom_roush.pdfbox.pdmodel.documentinterchange.taggedpdf.PDLayoutAttributeObject;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;

public class GPIOUtils {
    private static final String TAG = "GPIOUtils";
    private static int mFailTimes;
    private static long mTime;

    private boolean checkFilePermission(String str) {
        File file = new File(str);
        return file.exists() && file.canRead() && file.canWrite();
    }

    private boolean checkfileExists(String str) {
        return new File(str).exists();
    }

    private boolean checkfile(int i) throws Throwable {
        String str = new String("/sys/class/gpio/gpio" + i + "/direction");
        String str2 = new String("/sys/class/gpio/export");
        if (checkfileExists(str)) {
            return true;
        }
        if (!checkFilePermission(str2)) {
            upgradeRootPermission(str2);
        }
        exportGpio(i);
        return checkfileExists(str);
    }

    public static String getGpioDebugDir(int i) {
        try {
            File file = new File("/d/gpio");
            if (file.exists()) {
                Scanner scanner = new Scanner(file);
                while (scanner.hasNext()) {
                    String strNextLine = scanner.nextLine();
                    if (strNextLine.startsWith("gpio-" + String.valueOf(i) + " ")) {
                        return strNextLine.substring(32).contains("out") ? "out" : "in";
                    }
                }
                return "";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public static int getGpioDebugValue(int i) {
        try {
            File file = new File("/d/gpio");
            if (file.exists()) {
                Scanner scanner = new Scanner(file);
                while (scanner.hasNext()) {
                    String strNextLine = scanner.nextLine();
                    if (strNextLine.startsWith("gpio-" + String.valueOf(i) + " ")) {
                        return strNextLine.substring(32).contains("hi") ? 1 : 0;
                    }
                }
                return -1;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    public static void writeGpio(int i, int i2) {
        if (i < Constant.IO_OUTPUTS.length) {
            File file = new File(Constant.IO_OUTPUTS[i]);
            file.setExecutable(true);
            file.setReadable(true);
            file.setWritable(true);
            Utils.do_exec("busybox echo " + i2 + " > " + Constant.IO_OUTPUTS[i]);
        }
    }

    public static int readGpio(int i) {
        String str;
        if (i >= Constant.IO_INPUTS.length) {
            return 1;
        }
        try {
            FileInputStream fileInputStream = new FileInputStream(new File(Constant.IO_INPUTS[i]));
            byte[] bArr = new byte[1];
            fileInputStream.read(bArr);
            fileInputStream.close();
            str = new String(bArr);
        } catch (IOException e) {
            e.printStackTrace();
            str = "";
        }
        return !str.equals(PDLayoutAttributeObject.GLYPH_ORIENTATION_VERTICAL_ZERO_DEGREES) ? 1 : 0;
    }

    public static boolean isHDMIOut() {
        File file;
        String strReplace = "";
        String androidModle = VersionUtils.getAndroidModle();
        if (!"22".equals(Build.VERSION.SDK) && !androidModle.contains("rk3328") && !androidModle.contains("rk3128")) {
            if ("25".equals(Build.VERSION.SDK)) {
                file = new File(Constant.HDMI_STATUS_3399);
            } else if (androidModle.contains("rk3128") && "25".equals(Build.VERSION.SDK)) {
                file = new File(Constant.HDMI_STATUS_3128);
            } else {
                if ("27".equals(Build.VERSION.SDK)) {
                    return IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE.equals(readGpioPG("/sys/class/graphics/fb1/connected"));
                }
                file = null;
            }
        } else {
            file = new File(Constant.HDMI_STATUS_3288);
        }
        try {
            if (file.exists()) {
                FileInputStream fileInputStream = new FileInputStream(file);
                byte[] bArr = new byte[1024];
                StringBuffer stringBuffer = new StringBuffer();
                while (true) {
                    int i = fileInputStream.read(bArr);
                    if (i == -1) {
                        break;
                    }
                    stringBuffer.append(new String(bArr, 0, i));
                }
                fileInputStream.close();
                strReplace = new String(stringBuffer).replace("\n", "");
                Log.d("gpioutils", "str=" + strReplace);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (!"22".equals(Build.VERSION.SDK) && !androidModle.contains("rk3328") && !androidModle.contains("rk3128")) {
            if ("25".equals(Build.VERSION.SDK)) {
                return strReplace.equals("connected");
            }
            return false;
        }
        return strReplace.equals(IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE);
    }

    public static void writeIntFileUnder7(String str, String str2) throws InterruptedException, IOException {
        File file = new File(str2);
        file.setExecutable(true);
        file.setReadable(true);
        file.setWritable(true);
        do_exec("busybox echo " + str + " >" + str2);
    }

    public static void writeIntFileFor7(String str, String str2) throws Throwable {
        File file = new File(str2);
        file.setExecutable(true);
        file.setReadable(true);
        file.setWritable(true);
        if (str.equals(PDLayoutAttributeObject.GLYPH_ORIENTATION_VERTICAL_ZERO_DEGREES)) {
            Utils.execFor7("busybox echo 0 > " + str2);
        } else {
            Utils.execFor7("busybox echo 1 > " + str2);
        }
    }

    public static void writeStringFileFor7(File file, String str) throws Throwable {
        FileOutputStream fileOutputStream;
        FileOutputStream fileOutputStream2 = null;
        try {
            try {
                try {
                    fileOutputStream = new FileOutputStream(file);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } catch (FileNotFoundException e2) {
                e = e2;
            } catch (IOException e3) {
                e = e3;
            }
        } catch (Throwable th) {
            th = th;
        }
        try {
            fileOutputStream.write(str.getBytes());
            fileOutputStream.close();
            fileOutputStream.close();
        } catch (FileNotFoundException e4) {
            e = e4;
            fileOutputStream2 = fileOutputStream;
            e.printStackTrace();
            if (fileOutputStream2 != null) {
                fileOutputStream2.close();
            }
        } catch (IOException e5) {
            e = e5;
            fileOutputStream2 = fileOutputStream;
            e.printStackTrace();
            if (fileOutputStream2 != null) {
                fileOutputStream2.close();
            }
        } catch (Throwable th2) {
            th = th2;
            fileOutputStream2 = fileOutputStream;
            if (fileOutputStream2 != null) {
                try {
                    fileOutputStream2.close();
                } catch (IOException e6) {
                    e6.printStackTrace();
                }
            }
            throw th;
        }
    }

    public static void writeScreenBrightFile(String str, String str2) throws InterruptedException, IOException {
        File file = new File(str2);
        file.setExecutable(true);
        file.setReadable(true);
        file.setWritable(true);
        do_exec("busybox echo " + str + " > " + str2);
    }

    public static void do_exec(String str) {
        try {
            Process processExec = Runtime.getRuntime().exec("su");
            processExec.getOutputStream().write((str + "\nexit\n").getBytes());
            Log.d(TAG, "cmd：" + str);
            if (processExec.waitFor() == 0) {
                return;
            }
            Log.d(TAG, "cmd=" + str + " error!");
            throw new SecurityException();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String readGpioPG(String str) {
        try {
            FileInputStream fileInputStream = new FileInputStream(new File(str));
            byte[] bArr = new byte[1];
            fileInputStream.read(bArr);
            fileInputStream.close();
            return new String(bArr);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return "";
        } catch (UnsupportedEncodingException e2) {
            e2.printStackTrace();
            return "";
        } catch (IOException e3) {
            e3.printStackTrace();
            return "";
        }
    }

    public static String readGpioPGForLong(String str) {
        try {
            FileInputStream fileInputStream = new FileInputStream(new File(str));
            byte[] bArr = new byte[1024];
            fileInputStream.read(bArr);
            fileInputStream.close();
            return new String(bArr);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return "";
        } catch (UnsupportedEncodingException e2) {
            e2.printStackTrace();
            return "";
        } catch (IOException e3) {
            e3.printStackTrace();
            return "";
        }
    }

    public static void upgradeRootPermissionForExport() throws Throwable {
        upgradeRootPermission("/sys/class/gpio/export");
    }

    public static boolean exportGpio(int i) throws Throwable {
        upgradeRootPermissionForExport();
        boolean zWriteNode = writeNode("/sys/class/gpio/export", "" + i);
        if (zWriteNode) {
            upgradeRootPermissionForGpio(i);
        }
        return zWriteNode;
    }

    public static void upgradeRootPermissionForGpio(int i) throws Throwable {
        upgradeRootPermission("/sys/class/gpio/gpio" + i + "/direction");
        upgradeRootPermission("/sys/class/gpio/gpio" + i + "/value");
    }

    public static boolean setGpioDirection(int i, String str) {
        return writeNode(new String("/sys/class/gpio/gpio" + i + "/direction"), str);
    }

    public static String getGpioDirection(int i) {
        return readNode("/sys/class/gpio/gpio" + i + "/direction");
    }

    public static String getGpioDirection1(int i) {
        return readNode("/sys/class/gpio/gpio" + i + "/direction");
    }

    public static boolean writeGpioValue(int i, int i2) {
        return writeNode(new String("/sys/class/gpio/gpio" + i + "/value"), String.valueOf(i2));
    }

    public static String getGpioValue(int i) {
        return readNode("/sys/class/gpio/gpio" + i + "/value");
    }

    private static boolean upgradeRootPermission(String str) throws Throwable {
        DataOutputStream dataOutputStream = null;
        try {
            try {
                String str2 = "chmod 777 " + str;
                Process processExec = Runtime.getRuntime().exec("su");
                DataOutputStream dataOutputStream2 = new DataOutputStream(processExec.getOutputStream());
                try {
                    dataOutputStream2.writeBytes(str2 + "\n");
                    dataOutputStream2.writeBytes("exit\n");
                    dataOutputStream2.flush();
                    processExec.waitFor();
                    dataOutputStream2.close();
                } catch (Exception unused) {
                    dataOutputStream = dataOutputStream2;
                    if (dataOutputStream == null) {
                        return true;
                    }
                    dataOutputStream.close();
                } catch (Throwable th) {
                    th = th;
                    dataOutputStream = dataOutputStream2;
                    if (dataOutputStream != null) {
                        try {
                            dataOutputStream.close();
                        } catch (Exception unused2) {
                        }
                    }
                    throw th;
                }
            } catch (Exception unused3) {
                return true;
            }
        } catch (Exception unused4) {
        } catch (Throwable th2) {
            th = th2;
        }
        return true;
    }

    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static boolean writeNode(java.lang.String r8, java.lang.String r9) throws java.lang.Throwable {
        /*
            java.lang.String r0 = "Gpio_test write node error!! path"
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "Gpio_test set node path: "
            r1.<init>(r2)
            java.lang.StringBuilder r1 = r1.append(r8)
            java.lang.String r2 = " arg: "
            java.lang.StringBuilder r1 = r1.append(r2)
            java.lang.StringBuilder r1 = r1.append(r9)
            java.lang.String r1 = r1.toString()
            java.lang.String r3 = "GPIOUtils"
            android.util.Log.d(r3, r1)
            r1 = 0
            if (r8 == 0) goto L74
            if (r9 == 0) goto L74
            r4 = 0
            java.io.FileWriter r5 = new java.io.FileWriter     // Catch: java.lang.Throwable -> L3d java.lang.Exception -> L3f
            r5.<init>(r8)     // Catch: java.lang.Throwable -> L3d java.lang.Exception -> L3f
            r5.write(r9)     // Catch: java.lang.Throwable -> L38 java.lang.Exception -> L3b
            r5.close()     // Catch: java.io.IOException -> L32
            goto L36
        L32:
            r8 = move-exception
            r8.printStackTrace()
        L36:
            r8 = 1
            return r8
        L38:
            r8 = move-exception
            r4 = r5
            goto L69
        L3b:
            r4 = move-exception
            goto L43
        L3d:
            r8 = move-exception
            goto L69
        L3f:
            r5 = move-exception
            r7 = r5
            r5 = r4
            r4 = r7
        L43:
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L38
            r6.<init>(r0)     // Catch: java.lang.Throwable -> L38
            java.lang.StringBuilder r8 = r6.append(r8)     // Catch: java.lang.Throwable -> L38
            java.lang.StringBuilder r8 = r8.append(r2)     // Catch: java.lang.Throwable -> L38
            java.lang.StringBuilder r8 = r8.append(r9)     // Catch: java.lang.Throwable -> L38
            java.lang.String r8 = r8.toString()     // Catch: java.lang.Throwable -> L38
            android.util.Log.e(r3, r8)     // Catch: java.lang.Throwable -> L38
            r4.printStackTrace()     // Catch: java.lang.Throwable -> L38
            if (r5 == 0) goto L68
            r5.close()     // Catch: java.io.IOException -> L64
            goto L68
        L64:
            r8 = move-exception
            r8.printStackTrace()
        L68:
            return r1
        L69:
            if (r4 == 0) goto L73
            r4.close()     // Catch: java.io.IOException -> L6f
            goto L73
        L6f:
            r9 = move-exception
            r9.printStackTrace()
        L73:
            throw r8
        L74:
            java.lang.String r8 = "set node error"
            android.util.Log.e(r3, r8)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.proembed.service.GPIOUtils.writeNode(java.lang.String, java.lang.String):boolean");
    }

    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static java.lang.String readNode(java.lang.String r9) throws java.lang.Throwable {
        /*
            java.lang.String r0 = "GPIOUtils"
            java.lang.String r1 = ""
            r2 = 0
            java.io.FileReader r3 = new java.io.FileReader     // Catch: java.lang.Throwable -> L2e java.io.IOException -> L31
            r3.<init>(r9)     // Catch: java.lang.Throwable -> L2e java.io.IOException -> L31
            java.io.BufferedReader r9 = new java.io.BufferedReader     // Catch: java.lang.Throwable -> L27 java.io.IOException -> L29
            r9.<init>(r3)     // Catch: java.lang.Throwable -> L27 java.io.IOException -> L29
            java.lang.String r2 = r9.readLine()     // Catch: java.io.IOException -> L25 java.lang.Throwable -> L6e
            if (r2 == 0) goto L16
            r1 = r2
        L16:
            r2 = 0
            com.proembed.service.GPIOUtils.mFailTimes = r2     // Catch: java.io.IOException -> L25 java.lang.Throwable -> L6e
            r9.close()     // Catch: java.io.IOException -> L20
            r3.close()     // Catch: java.io.IOException -> L20
            goto L6d
        L20:
            r9 = move-exception
            r9.printStackTrace()
            goto L6d
        L25:
            r2 = move-exception
            goto L35
        L27:
            r0 = move-exception
            goto L70
        L29:
            r9 = move-exception
            r8 = r2
            r2 = r9
            r9 = r8
            goto L35
        L2e:
            r0 = move-exception
            r3 = r2
            goto L70
        L31:
            r9 = move-exception
            r3 = r2
            r2 = r9
            r9 = r3
        L35:
            java.lang.String r4 = "IO Exception"
            android.util.Log.e(r0, r4)     // Catch: java.lang.Throwable -> L6e
            r2.printStackTrace()     // Catch: java.lang.Throwable -> L6e
            long r4 = com.proembed.service.GPIOUtils.mTime     // Catch: java.lang.Throwable -> L6e
            r6 = 0
            int r2 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r2 == 0) goto L52
            long r4 = android.os.SystemClock.uptimeMillis()     // Catch: java.lang.Throwable -> L6e
            long r6 = com.proembed.service.GPIOUtils.mTime     // Catch: java.lang.Throwable -> L6e
            long r4 = r4 - r6
            r6 = 1000(0x3e8, double:4.94E-321)
            int r2 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r2 >= 0) goto L58
        L52:
            int r2 = com.proembed.service.GPIOUtils.mFailTimes     // Catch: java.lang.Throwable -> L6e
            int r2 = r2 + 1
            com.proembed.service.GPIOUtils.mFailTimes = r2     // Catch: java.lang.Throwable -> L6e
        L58:
            int r2 = com.proembed.service.GPIOUtils.mFailTimes     // Catch: java.lang.Throwable -> L6e
            r4 = 3
            if (r2 < r4) goto L63
            java.lang.String r2 = "read format node continuous failed three times, exist thread"
            android.util.Log.d(r0, r2)     // Catch: java.lang.Throwable -> L6e
        L63:
            if (r9 == 0) goto L68
            r9.close()     // Catch: java.io.IOException -> L20
        L68:
            if (r3 == 0) goto L6d
            r3.close()     // Catch: java.io.IOException -> L20
        L6d:
            return r1
        L6e:
            r0 = move-exception
            r2 = r9
        L70:
            if (r2 == 0) goto L78
            r2.close()     // Catch: java.io.IOException -> L76
            goto L78
        L76:
            r9 = move-exception
            goto L7e
        L78:
            if (r3 == 0) goto L81
            r3.close()     // Catch: java.io.IOException -> L76
            goto L81
        L7e:
            r9.printStackTrace()
        L81:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.proembed.service.GPIOUtils.readNode(java.lang.String):java.lang.String");
    }
}
