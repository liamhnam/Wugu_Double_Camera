package com.proembed.service;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.util.Calendar;

public class TimeUtils {
    public static long getTimeMills(int i, int i2, int i3, int i4, int i5, int i6) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(i, i2 - 1, i3, i4, i5, i6);
        return calendar.getTimeInMillis();
    }

    public static void setPowerOnTime(Context context, int i, int i2, int i3, int i4, int i5) throws Throwable {
        if (validate(i, i2, i3, i4, i5)) {
            setPowerOnMode(1);
            Intent intent = new Intent("android.intent.PowerOnTime");
            intent.putExtra(Constant.POWER_ON_YEAR, i);
            intent.putExtra(Constant.POWER_ON_Month, i2);
            intent.putExtra(Constant.POWER_ON_DAY, i3);
            intent.putExtra(Constant.POWER_ON_HOUR, i4);
            intent.putExtra(Constant.POWER_ON_MINUTE, i5);
            context.sendBroadcast(intent);
            return;
        }
        Toast.makeText(context.getApplicationContext(), "输入时间有误请检查", 0).show();
    }

    public static void clearPowerOnTime(Context context) {
        context.sendBroadcast(new Intent(Constant.POWER_ON_CLEAR_ACTION));
    }

    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static void setPowerOnMode(int r5) throws java.lang.Throwable {
        /*
            java.lang.String r0 = "setprop persist.sys.poweronmode "
            r1 = 0
            java.lang.Runtime r2 = java.lang.Runtime.getRuntime()     // Catch: java.lang.Throwable -> L6c java.lang.Exception -> L70
            java.lang.String r3 = "su"
            java.lang.Process r2 = r2.exec(r3)     // Catch: java.lang.Throwable -> L6c java.lang.Exception -> L70
            java.io.DataOutputStream r3 = new java.io.DataOutputStream     // Catch: java.lang.Throwable -> L66 java.lang.Exception -> L69
            java.io.OutputStream r4 = r2.getOutputStream()     // Catch: java.lang.Throwable -> L66 java.lang.Exception -> L69
            r3.<init>(r4)     // Catch: java.lang.Throwable -> L66 java.lang.Exception -> L69
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L5e java.lang.Exception -> L62
            r4.<init>(r0)     // Catch: java.lang.Throwable -> L5e java.lang.Exception -> L62
            java.lang.StringBuilder r5 = r4.append(r5)     // Catch: java.lang.Throwable -> L5e java.lang.Exception -> L62
            java.lang.String r0 = " \n"
            java.lang.StringBuilder r5 = r5.append(r0)     // Catch: java.lang.Throwable -> L5e java.lang.Exception -> L62
            java.lang.String r5 = r5.toString()     // Catch: java.lang.Throwable -> L5e java.lang.Exception -> L62
            r3.writeBytes(r5)     // Catch: java.lang.Throwable -> L5e java.lang.Exception -> L62
            java.lang.String r5 = "exit\n"
            r3.writeBytes(r5)     // Catch: java.lang.Throwable -> L5e java.lang.Exception -> L62
            r3.flush()     // Catch: java.lang.Throwable -> L5e java.lang.Exception -> L62
            r2.waitFor()     // Catch: java.lang.Throwable -> L5e java.lang.Exception -> L62
            java.io.DataInputStream r5 = new java.io.DataInputStream     // Catch: java.lang.Throwable -> L5e java.lang.Exception -> L62
            java.io.InputStream r0 = r2.getInputStream()     // Catch: java.lang.Throwable -> L5e java.lang.Exception -> L62
            r5.<init>(r0)     // Catch: java.lang.Throwable -> L5e java.lang.Exception -> L62
            int r0 = r5.available()     // Catch: java.lang.Throwable -> L5a java.lang.Exception -> L5c
            byte[] r0 = new byte[r0]     // Catch: java.lang.Throwable -> L5a java.lang.Exception -> L5c
            r5.read(r0)     // Catch: java.lang.Throwable -> L5a java.lang.Exception -> L5c
            java.lang.String r1 = new java.lang.String     // Catch: java.lang.Throwable -> L5a java.lang.Exception -> L5c
            r1.<init>(r0)     // Catch: java.lang.Throwable -> L5a java.lang.Exception -> L5c
            r3.close()     // Catch: java.lang.Exception -> L7e
        L53:
            r5.close()     // Catch: java.lang.Exception -> L7e
        L56:
            r2.destroy()     // Catch: java.lang.Exception -> L7e
            goto L7e
        L5a:
            r0 = move-exception
            goto L60
        L5c:
            r0 = move-exception
            goto L64
        L5e:
            r0 = move-exception
            r5 = r1
        L60:
            r1 = r3
            goto L80
        L62:
            r0 = move-exception
            r5 = r1
        L64:
            r1 = r3
            goto L73
        L66:
            r0 = move-exception
            r5 = r1
            goto L80
        L69:
            r0 = move-exception
            r5 = r1
            goto L73
        L6c:
            r0 = move-exception
            r5 = r1
            r2 = r5
            goto L80
        L70:
            r0 = move-exception
            r5 = r1
            r2 = r5
        L73:
            r0.printStackTrace()     // Catch: java.lang.Throwable -> L7f
            if (r1 == 0) goto L7b
            r1.close()     // Catch: java.lang.Exception -> L7e
        L7b:
            if (r5 == 0) goto L56
            goto L53
        L7e:
            return
        L7f:
            r0 = move-exception
        L80:
            if (r1 == 0) goto L85
            r1.close()     // Catch: java.lang.Exception -> L8d
        L85:
            if (r5 == 0) goto L8a
            r5.close()     // Catch: java.lang.Exception -> L8d
        L8a:
            r2.destroy()     // Catch: java.lang.Exception -> L8d
        L8d:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.proembed.service.TimeUtils.setPowerOnMode(int):void");
    }

    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static void WatchDogEnable(int r5) throws java.lang.Throwable {
        /*
            java.lang.String r0 = "setprop persist.sys.watchdogen "
            r1 = 0
            java.lang.Runtime r2 = java.lang.Runtime.getRuntime()     // Catch: java.lang.Throwable -> L6c java.lang.Exception -> L70
            java.lang.String r3 = "su"
            java.lang.Process r2 = r2.exec(r3)     // Catch: java.lang.Throwable -> L6c java.lang.Exception -> L70
            java.io.DataOutputStream r3 = new java.io.DataOutputStream     // Catch: java.lang.Throwable -> L66 java.lang.Exception -> L69
            java.io.OutputStream r4 = r2.getOutputStream()     // Catch: java.lang.Throwable -> L66 java.lang.Exception -> L69
            r3.<init>(r4)     // Catch: java.lang.Throwable -> L66 java.lang.Exception -> L69
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L5e java.lang.Exception -> L62
            r4.<init>(r0)     // Catch: java.lang.Throwable -> L5e java.lang.Exception -> L62
            java.lang.StringBuilder r5 = r4.append(r5)     // Catch: java.lang.Throwable -> L5e java.lang.Exception -> L62
            java.lang.String r0 = " \n"
            java.lang.StringBuilder r5 = r5.append(r0)     // Catch: java.lang.Throwable -> L5e java.lang.Exception -> L62
            java.lang.String r5 = r5.toString()     // Catch: java.lang.Throwable -> L5e java.lang.Exception -> L62
            r3.writeBytes(r5)     // Catch: java.lang.Throwable -> L5e java.lang.Exception -> L62
            java.lang.String r5 = "exit\n"
            r3.writeBytes(r5)     // Catch: java.lang.Throwable -> L5e java.lang.Exception -> L62
            r3.flush()     // Catch: java.lang.Throwable -> L5e java.lang.Exception -> L62
            r2.waitFor()     // Catch: java.lang.Throwable -> L5e java.lang.Exception -> L62
            java.io.DataInputStream r5 = new java.io.DataInputStream     // Catch: java.lang.Throwable -> L5e java.lang.Exception -> L62
            java.io.InputStream r0 = r2.getInputStream()     // Catch: java.lang.Throwable -> L5e java.lang.Exception -> L62
            r5.<init>(r0)     // Catch: java.lang.Throwable -> L5e java.lang.Exception -> L62
            int r0 = r5.available()     // Catch: java.lang.Throwable -> L5a java.lang.Exception -> L5c
            byte[] r0 = new byte[r0]     // Catch: java.lang.Throwable -> L5a java.lang.Exception -> L5c
            r5.read(r0)     // Catch: java.lang.Throwable -> L5a java.lang.Exception -> L5c
            java.lang.String r1 = new java.lang.String     // Catch: java.lang.Throwable -> L5a java.lang.Exception -> L5c
            r1.<init>(r0)     // Catch: java.lang.Throwable -> L5a java.lang.Exception -> L5c
            r3.close()     // Catch: java.lang.Exception -> L7e
        L53:
            r5.close()     // Catch: java.lang.Exception -> L7e
        L56:
            r2.destroy()     // Catch: java.lang.Exception -> L7e
            goto L7e
        L5a:
            r0 = move-exception
            goto L60
        L5c:
            r0 = move-exception
            goto L64
        L5e:
            r0 = move-exception
            r5 = r1
        L60:
            r1 = r3
            goto L80
        L62:
            r0 = move-exception
            r5 = r1
        L64:
            r1 = r3
            goto L73
        L66:
            r0 = move-exception
            r5 = r1
            goto L80
        L69:
            r0 = move-exception
            r5 = r1
            goto L73
        L6c:
            r0 = move-exception
            r5 = r1
            r2 = r5
            goto L80
        L70:
            r0 = move-exception
            r5 = r1
            r2 = r5
        L73:
            r0.printStackTrace()     // Catch: java.lang.Throwable -> L7f
            if (r1 == 0) goto L7b
            r1.close()     // Catch: java.lang.Exception -> L7e
        L7b:
            if (r5 == 0) goto L56
            goto L53
        L7e:
            return
        L7f:
            r0 = move-exception
        L80:
            if (r1 == 0) goto L85
            r1.close()     // Catch: java.lang.Exception -> L8d
        L85:
            if (r5 == 0) goto L8a
            r5.close()     // Catch: java.lang.Exception -> L8d
        L8a:
            r2.destroy()     // Catch: java.lang.Exception -> L8d
        L8d:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.proembed.service.TimeUtils.WatchDogEnable(int):void");
    }

    public static void WatchDogFeed() throws Throwable {
        DataOutputStream dataOutputStream;
        DataInputStream dataInputStream;
        Throwable th;
        Process processExec;
        Exception e;
        try {
            try {
                try {
                    processExec = Runtime.getRuntime().exec("su");
                    try {
                        dataOutputStream = new DataOutputStream(processExec.getOutputStream());
                        try {
                            dataOutputStream.writeBytes("setprop persist.sys.watchdogfeed 60 \n");
                            dataOutputStream.writeBytes("exit\n");
                            dataOutputStream.flush();
                            processExec.waitFor();
                            dataInputStream = new DataInputStream(processExec.getInputStream());
                            try {
                                byte[] bArr = new byte[dataInputStream.available()];
                                dataInputStream.read(bArr);
                                new String(bArr);
                                dataOutputStream.close();
                            } catch (Exception e2) {
                                e = e2;
                                e.printStackTrace();
                                if (dataOutputStream != null) {
                                    dataOutputStream.close();
                                }
                                if (dataInputStream != null) {
                                }
                                processExec.destroy();
                            }
                        } catch (Exception e3) {
                            dataInputStream = null;
                            e = e3;
                        } catch (Throwable th2) {
                            dataInputStream = null;
                            th = th2;
                            if (dataOutputStream != null) {
                                try {
                                    dataOutputStream.close();
                                } catch (Exception unused) {
                                    throw th;
                                }
                            }
                            if (dataInputStream != null) {
                                dataInputStream.close();
                            }
                            processExec.destroy();
                            throw th;
                        }
                    } catch (Exception e4) {
                        dataInputStream = null;
                        e = e4;
                        dataOutputStream = null;
                    } catch (Throwable th3) {
                        dataInputStream = null;
                        th = th3;
                        dataOutputStream = null;
                    }
                } catch (Throwable th4) {
                    th = th4;
                }
            } catch (Exception e5) {
                dataOutputStream = null;
                dataInputStream = null;
                e = e5;
                processExec = null;
            } catch (Throwable th5) {
                dataOutputStream = null;
                dataInputStream = null;
                th = th5;
                processExec = null;
            }
            dataInputStream.close();
            processExec.destroy();
        } catch (Exception unused2) {
        }
    }

    public static boolean validate(int i, int i2, int i3, int i4, int i5) {
        if ((i > 2099 && i < 2017) || i2 < 0 || i2 > 11) {
            return false;
        }
        int[] iArr = {31, 31, -1, 31, 30, 31, 30, 31, 31, 30, 31, 30};
        if (isLeapYear(i)) {
            iArr[2] = 29;
        } else {
            iArr[2] = 28;
        }
        return i3 >= 1 && i3 <= iArr[i2] && i4 >= 0 && i4 <= 23 && i5 >= 0 && i5 <= 59;
    }

    private static boolean isLeapYear(int i) {
        return (i % 4 == 0 && i % 100 != 0) || i % 400 == 0;
    }
}
