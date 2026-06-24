package com.proembed.service;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.IBinder;
import android.util.Log;
import com.p020hp.jipp.encoding.IppPacket;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.nio.charset.Charset;

public class Utils {
    public static final String TAG = "Utils";

    public static void setValueToProp(String str, String str2) {
        try {
            Class<?> cls = Class.forName("android.os.SystemProperties");
            cls.getDeclaredMethod("set", String.class, String.class).invoke(cls, str, str2);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e2) {
            e2.printStackTrace();
        } catch (InvocationTargetException e3) {
            e3.printStackTrace();
        } catch (Exception e4) {
            e4.printStackTrace();
        }
    }

    public static String getValueFromProp(String str) {
        try {
            Class<?> cls = Class.forName("android.os.SystemProperties");
            return (String) cls.getDeclaredMethod("get", String.class).invoke(cls, str);
        } catch (Exception unused) {
            return "";
        }
    }

    public static boolean checkfile(String str) {
        return new File(str).exists();
    }

    public static void do_exec(String str) {
        try {
            Process processExec = Runtime.getRuntime().exec("su");
            processExec.getOutputStream().write((str + "\nexit\n").getBytes());
            Log.d(TAG, "cmd=" + str);
            Log.d(TAG, "su.waitFor() = " + processExec.waitFor());
            if (processExec.waitFor() == 0) {
                return;
            }
            System.out.println("cmd=" + str + " error!");
            throw new SecurityException();
        } catch (Exception e) {
            Log.d(TAG, "e = " + e.getMessage());
            e.printStackTrace();
        }
    }

    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static boolean execFor7AndReboot(java.lang.String r7) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 256
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.proembed.service.Utils.execFor7AndReboot(java.lang.String):boolean");
    }

    public static boolean execFor7(String str) throws Throwable {
        DataOutputStream dataOutputStream;
        Throwable th;
        Exception e;
        BufferedReader bufferedReader;
        Process processExec;
        Log.d(TAG, "command = " + ((String) str));
        try {
            try {
                processExec = Runtime.getRuntime().exec("su");
                dataOutputStream = new DataOutputStream(processExec.getOutputStream());
            } catch (Throwable th2) {
                th = th2;
            }
            try {
                dataOutputStream.write((((String) str) + "\n").getBytes(Charset.forName(IppPacket.DEFAULT_CHARSET)));
                dataOutputStream.flush();
                dataOutputStream.writeBytes("exit\n");
                dataOutputStream.flush();
                processExec.waitFor();
                bufferedReader = new BufferedReader(new InputStreamReader(processExec.getErrorStream()));
                String str2 = "";
                while (true) {
                    try {
                        String line = bufferedReader.readLine();
                        if (line == null) {
                            Log.d(TAG, "execFor7 msg is " + str2);
                            boolean z = !str2.contains("Failure");
                            try {
                                dataOutputStream.close();
                                bufferedReader.close();
                                return z;
                            } catch (IOException e2) {
                                Log.e(TAG, e2.getMessage(), e2);
                                return z;
                            }
                        }
                        str2 = str2 + line;
                    } catch (Exception e3) {
                        e = e3;
                        Log.e(TAG, e.getMessage(), e);
                        if (dataOutputStream != null) {
                            try {
                                dataOutputStream.close();
                            } catch (IOException e4) {
                                Log.e(TAG, e4.getMessage(), e4);
                                return false;
                            }
                        }
                        if (bufferedReader != null) {
                            bufferedReader.close();
                        }
                        return false;
                    }
                }
            } catch (Exception e5) {
                e = e5;
                bufferedReader = null;
            } catch (Throwable th3) {
                th = th3;
                str = 0;
                if (dataOutputStream != null) {
                    try {
                        dataOutputStream.close();
                    } catch (IOException e6) {
                        Log.e(TAG, e6.getMessage(), e6);
                        throw th;
                    }
                }
                if (str != 0) {
                    str.close();
                }
                throw th;
            }
        } catch (Exception e7) {
            dataOutputStream = null;
            e = e7;
            bufferedReader = null;
        } catch (Throwable th4) {
            dataOutputStream = null;
            th = th4;
            str = 0;
        }
    }

    public static void reboot() throws Throwable {
        execFor7("reboot");
    }

    public static String getEthernet(Context context) {
        try {
            return (String) Class.forName("android.net.EthernetManager").getDeclaredMethod("getIpAddress", new Class[0]).invoke(context.getSystemService("ethernet"), new Object[0]);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static Bitmap compressImage(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        int i = 90;
        while (byteArrayOutputStream.toByteArray().length / 1024 > 100) {
            byteArrayOutputStream.reset();
            bitmap.compress(Bitmap.CompressFormat.JPEG, i, byteArrayOutputStream);
            i -= 10;
        }
        return BitmapFactory.decodeStream(new ByteArrayInputStream(byteArrayOutputStream.toByteArray()), null, null);
    }

    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static android.graphics.Bitmap compressScale(android.graphics.Bitmap r7) {
        /*
            java.io.ByteArrayOutputStream r0 = new java.io.ByteArrayOutputStream
            r0.<init>()
            android.graphics.Bitmap$CompressFormat r1 = android.graphics.Bitmap.CompressFormat.JPEG
            r2 = 100
            r7.compress(r1, r2, r0)
            r0.reset()
            android.graphics.Bitmap$CompressFormat r1 = android.graphics.Bitmap.CompressFormat.JPEG
            r2 = 80
            r7.compress(r1, r2, r0)
            java.io.ByteArrayInputStream r7 = new java.io.ByteArrayInputStream
            byte[] r1 = r0.toByteArray()
            r7.<init>(r1)
            android.graphics.BitmapFactory$Options r1 = new android.graphics.BitmapFactory$Options
            r1.<init>()
            r2 = 1
            r1.inJustDecodeBounds = r2
            r3 = 0
            r4 = r3
            android.graphics.Rect r4 = (android.graphics.Rect) r4
            android.graphics.BitmapFactory.decodeStream(r7, r3, r1)
            r7 = 0
            r1.inJustDecodeBounds = r7
            int r7 = r1.outWidth
            int r4 = r1.outHeight
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.StringBuilder r5 = r5.append(r7)
            java.lang.String r6 = "---------------"
            java.lang.StringBuilder r5 = r5.append(r6)
            java.lang.StringBuilder r5 = r5.append(r4)
            java.lang.String r5 = r5.toString()
            java.lang.String r6 = "Utils"
            android.util.Log.i(r6, r5)
            if (r7 <= r4) goto L60
            float r5 = (float) r7
            r6 = 1139802112(0x43f00000, float:480.0)
            int r5 = (r5 > r6 ? 1 : (r5 == r6 ? 0 : -1))
            if (r5 <= 0) goto L60
            int r7 = r1.outWidth
            float r7 = (float) r7
            float r7 = r7 / r6
        L5e:
            int r7 = (int) r7
            goto L6f
        L60:
            if (r7 >= r4) goto L6e
            float r7 = (float) r4
            r4 = 1145569280(0x44480000, float:800.0)
            int r7 = (r7 > r4 ? 1 : (r7 == r4 ? 0 : -1))
            if (r7 <= 0) goto L6e
            int r7 = r1.outHeight
            float r7 = (float) r7
            float r7 = r7 / r4
            goto L5e
        L6e:
            r7 = r2
        L6f:
            if (r7 > 0) goto L72
            goto L73
        L72:
            r2 = r7
        L73:
            r1.inSampleSize = r2
            android.graphics.Bitmap$Config r7 = android.graphics.Bitmap.Config.RGB_565
            r1.inPreferredConfig = r7
            java.io.ByteArrayInputStream r7 = new java.io.ByteArrayInputStream
            byte[] r0 = r0.toByteArray()
            r7.<init>(r0)
            android.graphics.Bitmap r7 = android.graphics.BitmapFactory.decodeStream(r7, r3, r1)
            android.graphics.Bitmap r7 = compressImage(r7)
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.proembed.service.Utils.compressScale(android.graphics.Bitmap):android.graphics.Bitmap");
    }

    public static void hideBar() {
        try {
            Object objInvoke = Class.forName("com.android.internal.statusbar.IStatusBarService$Stub").getDeclaredMethod("asInterface", IBinder.class).invoke(null, getService("statusbar"));
            objInvoke.getClass().getDeclaredMethod("hideBar", null).invoke(objInvoke, null);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e2) {
            e2.printStackTrace();
        } catch (InvocationTargetException e3) {
            e3.printStackTrace();
        } catch (Exception e4) {
            e4.printStackTrace();
        }
    }

    public static void hideNavigationBar() {
        try {
            Object objInvoke = Class.forName("com.android.internal.statusbar.IStatusBarService$Stub").getDeclaredMethod("asInterface", IBinder.class).invoke(null, getService("statusbar"));
            objInvoke.getClass().getDeclaredMethod("hideNavigationBar", null).invoke(objInvoke, null);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e2) {
            e2.printStackTrace();
        } catch (InvocationTargetException e3) {
            e3.printStackTrace();
        } catch (Exception e4) {
            e4.printStackTrace();
        }
    }

    private static IBinder getService(String str) {
        try {
            Class<?> cls = Class.forName("android.os.ServiceManager");
            return (IBinder) cls.getMethod("getService", String.class).invoke(cls.newInstance(), str);
        } catch (Exception unused) {
            return null;
        }
    }

    public static boolean isRoot() {
        try {
            Process processExec = Runtime.getRuntime().exec("su");
            DataOutputStream dataOutputStream = new DataOutputStream(processExec.getOutputStream());
            dataOutputStream.writeBytes("exit\n");
            dataOutputStream.flush();
            return processExec.waitFor() == 0;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } catch (InterruptedException e2) {
            e2.printStackTrace();
            return false;
        }
    }

    public static String readFileLong(String str) {
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

    public static Activity findActivity(Context context) {
        if (context instanceof Activity) {
            return (Activity) context;
        }
        if (context instanceof ContextWrapper) {
            return findActivity(((ContextWrapper) context).getBaseContext());
        }
        return null;
    }

    public static String readGpioPG(String str) {
        File file = new File(str);
        if (!file.exists()) {
            return "";
        }
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            byte[] bArr = new byte[16];
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

    public static String readGpioValue(String str) {
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

    public static String getDatafromFile(String str, int i) throws Throwable {
        String str2 = "";
        ?? r1 = 0;
        int i2 = 0;
        r1 = 0;
        r1 = 0;
        try {
            try {
                try {
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(str), "UTF-8"));
                    int i3 = i;
                    while (true) {
                        try {
                            String line = bufferedReader.readLine();
                            if (line == null) {
                                break;
                            }
                            i2 = i3 - 1;
                            if (i3 <= 0) {
                                break;
                            }
                            str2 = str2 + line;
                            i3 = i2;
                            i2 = i2;
                        } catch (IOException e) {
                            e = e;
                            r1 = bufferedReader;
                            e.printStackTrace();
                            if (r1 != 0) {
                                r1.close();
                                r1 = r1;
                            }
                            return str2;
                        } catch (Throwable th) {
                            th = th;
                            r1 = bufferedReader;
                            if (r1 != 0) {
                                try {
                                    r1.close();
                                } catch (IOException e2) {
                                    e2.printStackTrace();
                                }
                            }
                            throw th;
                        }
                    }
                    bufferedReader.close();
                    bufferedReader.close();
                    r1 = i2;
                } catch (IOException e3) {
                    e3.printStackTrace();
                }
            } catch (IOException e4) {
                e = e4;
            }
            return str2;
        } catch (Throwable th2) {
            th = th2;
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

    public static boolean canParseInt(String str) {
        if (str == null) {
            return false;
        }
        return str.matches("\\d+");
    }

    public static int str2int(String str) {
        if (canParseInt(str)) {
            return Integer.parseInt(str);
        }
        return 0;
    }
}
