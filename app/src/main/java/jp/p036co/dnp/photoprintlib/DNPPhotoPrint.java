package jp.p036co.dnp.photoprintlib;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbManager;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import p000a.p001a.p002a.p003a.C0002a;
import p000a.p001a.p002a.p003a.C0004c;

public class DNPPhotoPrint {
    public static final int COUNT_ALL = 0;
    public static final int COUNT_PD = 2;
    public static final int COUNT_SD = 1;
    public static final int CSPSTATUS_TIMEOUT = -2147483647;
    public static final int CSP_2L = 2;
    public static final int CSP_4P5x4P5 = 57;
    public static final int CSP_4P5x6 = 58;
    public static final int CSP_4P5x8 = 59;
    public static final int CSP_4x4 = 54;
    public static final int CSP_4x6 = 55;
    public static final int CSP_4x8 = 56;
    public static final int CSP_5x5 = 10;
    public static final int CSP_6x4P5 = 12;
    public static final int CSP_6x4P5_REWIND = 14;
    public static final int CSP_6x4P5x2 = 13;
    public static final int CSP_6x6 = 11;
    public static final int CSP_8x10 = 31;
    public static final int CSP_8x12 = 32;
    public static final int CSP_8x4 = 33;
    public static final int CSP_8x4_REWIND = 48;
    public static final int CSP_8x4x2 = 37;
    public static final int CSP_8x4x3 = 44;
    public static final int CSP_8x5 = 34;
    public static final int CSP_8x5_8x4 = 40;
    public static final int CSP_8x5_REWIND = 49;
    public static final int CSP_8x5x2 = 38;
    public static final int CSP_8x6 = 35;
    public static final int CSP_8x6_8x4 = 41;
    public static final int CSP_8x6_8x5 = 42;
    public static final int CSP_8x6_REWIND = 50;
    public static final int CSP_8x6x2 = 39;
    public static final int CSP_8x7 = 46;
    public static final int CSP_8x8 = 36;
    public static final int CSP_8x8_8x4 = 43;
    public static final int CSP_8x9 = 47;
    public static final int CSP_A4FORMAT = 78;
    public static final int CSP_A4_LENGTH = 45;
    public static final int CSP_A4x10 = 77;
    public static final int CSP_A4x5 = 74;
    public static final int CSP_A4x5_REWIND = 80;
    public static final int CSP_A4x5x2 = 79;
    public static final int CSP_A4x6 = 75;
    public static final int CSP_A4x8 = 76;
    public static final int CSP_A5 = 4;
    public static final int CSP_A5FORMAT = 71;
    public static final int CSP_A5W = 5;
    public static final int CSP_A5_REWIND = 73;
    public static final int CSP_A5x2 = 72;
    public static final int CSP_L = 1;
    public static final int CSP_L_REWIND = 9;
    public static final int CSP_Lx2 = 7;
    public static final int CSP_PC = 3;
    public static final int CSP_PC_REWIND = 8;
    public static final int CSP_PCx2 = 6;
    public static final int CUTTER_MODE_2INCHCUT = 120;
    public static final int CUTTER_MODE_NONSCRAP = 1;
    public static final int CUTTER_MODE_STANDARD = 0;
    public static final int CXW_DEF = 4;
    public static final int CX_DEF = 3;
    public static final int CY_DEF = 5;
    public static final int DECURL_MODE_AUTO = 2;
    public static final int DECURL_MODE_OFF = 0;
    public static final int DECURL_MODE_ON = 1;
    public static final int DS620_DEF = 20;
    public static final int DS820_DEF = 30;
    public static final int GROUP_HARDWARE = 262144;
    public static final int GROUP_SETTING = 131072;
    public static final int GROUP_SYSTEM = 524288;
    public static final int GROUP_USUALLY = 65536;
    public static final int OVERCOAT_FINISH_FINEMATTE = 21;
    public static final int OVERCOAT_FINISH_GLOSSY = 0;
    public static final int OVERCOAT_FINISH_LUSTER = 22;
    public static final int OVERCOAT_FINISH_MATTE1 = 1;
    public static final int OVERCOAT_FINISH_PMATTE11 = 101;
    public static final int OVERCOAT_FINISH_PMATTE12 = 121;
    public static final int OVERCOAT_FINISH_PMATTE13 = 122;
    public static final int OVERCOAT_FINISH_WATERMARK = 15;
    public static final int PRINTSPEED_HIGH_DENSITY = 3;
    public static final int PRINTSPEED_HIGH_QUALITY = 2;
    public static final int PRINTSPEED_NORMAL = 0;
    public static final int PRINT_RETRY_OFF = 0;
    public static final int PRINT_RETRY_ON = 1;
    public static final int QW410_DEF = 35;
    public static final int RESOLUTION300 = 300;
    public static final int RESOLUTION600 = 600;
    public static final int STATUS_CXM_RS422_STOP = 263168;
    public static final int STATUS_ERROR = Integer.MIN_VALUE;
    public static final int STATUS_HARDWARE_ERR01 = 262145;
    public static final int STATUS_HARDWARE_ERR02 = 262146;
    public static final int STATUS_HARDWARE_ERR03 = 262148;
    public static final int STATUS_HARDWARE_ERR04 = 262152;
    public static final int STATUS_HARDWARE_ERR05 = 262160;
    public static final int STATUS_HARDWARE_ERR06 = 262176;
    public static final int STATUS_HARDWARE_ERR07 = 262208;
    public static final int STATUS_HARDWARE_ERR08 = 262272;
    public static final int STATUS_HARDWARE_ERR09 = 262400;
    public static final int STATUS_HARDWARE_ERR10 = 262656;
    public static final int STATUS_HARDWARE_ERR11 = 263168;
    public static final int STATUS_HARDWARE_ERR12 = 264192;
    public static final int STATUS_SETTING_COVER_OPEN = 131073;
    public static final int STATUS_SETTING_DATA_ERR = 131088;
    public static final int STATUS_SETTING_PAPER_ERR = 131080;
    public static final int STATUS_SETTING_PAPER_JAM = 131074;
    public static final int STATUS_SETTING_RIBBON_ERR = 131076;
    public static final int STATUS_SETTING_SCRAPBOX_ERR = 131104;
    public static final int STATUS_SYSTEM_ERR01 = 524289;
    public static final int STATUS_USUALLY_BACKPRINT = 65792;
    public static final int STATUS_USUALLY_COOLING = 65568;
    public static final int STATUS_USUALLY_IDLE = 65537;
    public static final int STATUS_USUALLY_MOTCOOLING = 65600;
    public static final int STATUS_USUALLY_PAPER_END = 65544;
    public static final int STATUS_USUALLY_PRINTING = 65538;
    public static final int STATUS_USUALLY_RIBBON_END = 65552;
    public static final int STATUS_USUALLY_SHOOTING = 65664;
    public static final int STATUS_USUALLY_STANDBY_MODE = 65664;
    public static final int STATUS_USUALLY_STANDSTILL = 65540;

    public Context f2683a;

    public UsbManager f2684b;

    public List<C0002a> f2685c = new ArrayList();

    public HashMap<UsbDevice, C0002a> f2686d = new HashMap<>();

    public BroadcastReceiver f2687e = null;

    public DNPPhotoPrint(Context context) {
        this.f2684b = null;
        this.f2683a = context;
        if (context != null) {
            this.f2684b = (UsbManager) context.getSystemService("usb");
        }
        m1290b();
    }

    public boolean EndPageLayout(int i) {
        C0002a c0002aM1288a = m1288a(i);
        if (c0002aM1288a != null) {
            return c0002aM1288a.m9b();
        }
        return false;
    }

    public int GetCounterA(int i) {
        int iM3a;
        C0002a c0002aM1288a = m1288a(i);
        if (c0002aM1288a == null) {
            return -1;
        }
        synchronized (c0002aM1288a) {
            iM3a = c0002aM1288a.m20g() ? c0002aM1288a.m3a(1) : -1;
        }
        return iM3a;
    }

    public int GetCounterB(int i) {
        int iM3a;
        C0002a c0002aM1288a = m1288a(i);
        if (c0002aM1288a == null) {
            return -1;
        }
        synchronized (c0002aM1288a) {
            iM3a = c0002aM1288a.m20g() ? c0002aM1288a.m3a(2) : -1;
        }
        return iM3a;
    }

    public int GetCounterL(int i) {
        int iM3a;
        C0002a c0002aM1288a = m1288a(i);
        if (c0002aM1288a == null) {
            return -1;
        }
        synchronized (c0002aM1288a) {
            iM3a = c0002aM1288a.m20g() ? c0002aM1288a.m3a(0) : -1;
        }
        return iM3a;
    }

    public int GetCounterLifeEX(int i, int i2) {
        int iM3a;
        C0002a c0002aM1288a = m1288a(i);
        if (c0002aM1288a == null) {
            return -1;
        }
        synchronized (c0002aM1288a) {
            iM3a = c0002aM1288a.m20g() ? c0002aM1288a.m3a(0, i2) : -1;
        }
        return iM3a;
    }

    public int GetCounterM(int i) {
        int iM3a;
        C0002a c0002aM1288a = m1288a(i);
        if (c0002aM1288a == null) {
            return -1;
        }
        synchronized (c0002aM1288a) {
            iM3a = c0002aM1288a.m20g() ? c0002aM1288a.m3a(5) : -1;
        }
        return iM3a;
    }

    public int GetCounterMatte(int i) {
        int iM3a;
        C0002a c0002aM1288a = m1288a(i);
        if (c0002aM1288a == null) {
            return -1;
        }
        synchronized (c0002aM1288a) {
            iM3a = c0002aM1288a.m20g() ? c0002aM1288a.m3a(4) : -1;
        }
        return iM3a;
    }

    public int GetCounterMatteEX(int i, int i2) {
        int iM3a;
        C0002a c0002aM1288a = m1288a(i);
        if (c0002aM1288a == null) {
            return -1;
        }
        synchronized (c0002aM1288a) {
            iM3a = c0002aM1288a.m20g() ? c0002aM1288a.m3a(4, i2) : -1;
        }
        return iM3a;
    }

    public int GetCounterP(int i) {
        int iM3a;
        C0002a c0002aM1288a = m1288a(i);
        if (c0002aM1288a == null) {
            return -1;
        }
        synchronized (c0002aM1288a) {
            iM3a = c0002aM1288a.m20g() ? c0002aM1288a.m3a(3) : -1;
        }
        return iM3a;
    }

    public int GetFirmwVersion(int i, char[] cArr) {
        C0002a c0002aM1288a = m1288a(i);
        if (c0002aM1288a != null) {
            return c0002aM1288a.m2a(cArr);
        }
        return -1;
    }

    public int GetFreeBuffer(int i) {
        int iM3a;
        C0002a c0002aM1288a = m1288a(i);
        if (c0002aM1288a == null) {
            return -1;
        }
        synchronized (c0002aM1288a) {
            iM3a = c0002aM1288a.m20g() ? c0002aM1288a.m3a(6) : -1;
        }
        return iM3a;
    }

    public int GetInitialMediaCount(int i) {
        int iM3a;
        C0002a c0002aM1288a = m1288a(i);
        if (c0002aM1288a == null) {
            return -1;
        }
        synchronized (c0002aM1288a) {
            iM3a = c0002aM1288a.m20g() ? c0002aM1288a.m3a(8) : -1;
        }
        return iM3a;
    }

    public int GetMedia(int i, char[] cArr) {
        int iM0a;
        C0002a c0002aM1288a = m1288a(i);
        int i2 = -1;
        if (c0002aM1288a == null) {
            return -1;
        }
        synchronized (c0002aM1288a) {
            if (c0002aM1288a.m20g()) {
                byte[] bArrM1a = C0002a.m1a("INFO", "MEDIA", -1);
                if (c0002aM1288a.m11c(bArrM1a, bArrM1a.length) == bArrM1a.length) {
                    byte[] bArr = new byte[64];
                    if (c0002aM1288a.m8b(bArr, 8) == 8 && (iM0a = C0002a.m0a(bArr, 8)) > 0 && c0002aM1288a.m8b(bArr, iM0a) == iM0a) {
                        if (2 <= iM0a) {
                            int i3 = 0;
                            if (bArr[0] == 77 && bArr[1] == 84) {
                                for (int i4 = 2; i4 < iM0a; i4++) {
                                    if (i3 >= cArr.length) {
                                        break;
                                    }
                                    byte b = bArr[i4];
                                    cArr[i3] = (char) b;
                                    i3++;
                                    if (b == 13) {
                                        break;
                                    }
                                }
                                i2 = i3;
                            }
                        }
                    }
                }
            }
        }
        return i2;
    }

    public int GetMediaCounter(int i) {
        int iM3a;
        C0002a c0002aM1288a = m1288a(i);
        if (c0002aM1288a == null) {
            return -1;
        }
        synchronized (c0002aM1288a) {
            iM3a = c0002aM1288a.m20g() ? c0002aM1288a.m3a(7) : -1;
        }
        return iM3a;
    }

    public int GetMediaCounterH(int i) {
        C0002a c0002aM1288a = m1288a(i);
        int iM3a = -1;
        if (c0002aM1288a == null) {
            return -1;
        }
        synchronized (c0002aM1288a) {
            if (c0002aM1288a.m20g()) {
                int i2 = c0002aM1288a.f4c;
                if (i2 == 20 || i2 == 30) {
                    iM3a = c0002aM1288a.m3a(9);
                }
            }
        }
        return iM3a;
    }

    public int GetMediaLotNo(int i, char[] cArr) {
        int iM0a;
        C0002a c0002aM1288a = m1288a(i);
        if (c0002aM1288a == null) {
            return -1;
        }
        synchronized (c0002aM1288a) {
            if (c0002aM1288a.m20g()) {
                byte[] bArrM1a = C0002a.m1a("INFO", "MLOT", -1);
                if (c0002aM1288a.m11c(bArrM1a, bArrM1a.length) == bArrM1a.length) {
                    byte[] bArr = new byte[64];
                    if (c0002aM1288a.m8b(bArr, 8) == 8 && (iM0a = C0002a.m0a(bArr, 8)) > 0 && c0002aM1288a.m8b(bArr, iM0a) == iM0a && cArr.length >= iM0a) {
                        for (int i2 = 0; i2 < iM0a; i2++) {
                            cArr[i2] = (char) bArr[i2];
                        }
                        return iM0a;
                    }
                }
            }
            return -1;
        }
    }

    public int GetPQTY(int i) {
        C0002a c0002aM1288a = m1288a(i);
        if (c0002aM1288a != null) {
            return c0002aM1288a.m14d();
        }
        return -1;
    }

    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public synchronized int GetPrinterPortNum(int[][] r9) {
        /*
            r8 = this;
            monitor-enter(r8)
            android.hardware.usb.UsbManager r0 = r8.f2684b     // Catch: java.lang.Throwable -> L74
            r1 = -1
            if (r0 != 0) goto L8
            monitor-exit(r8)
            return r1
        L8:
            if (r9 != 0) goto Lc
            monitor-exit(r8)
            return r1
        Lc:
            java.util.List<a.a.a.a.a> r0 = r8.f2685c     // Catch: java.lang.Throwable -> L74
            r0.clear()     // Catch: java.lang.Throwable -> L74
            boolean r0 = r8.m1289a()     // Catch: java.lang.Throwable -> L74
            if (r0 != 0) goto L19
            monitor-exit(r8)
            return r1
        L19:
            java.util.HashMap<android.hardware.usb.UsbDevice, a.a.a.a.a> r0 = r8.f2686d     // Catch: java.lang.Throwable -> L74
            java.util.Collection r0 = r0.values()     // Catch: java.lang.Throwable -> L74
            java.util.Iterator r0 = r0.iterator()     // Catch: java.lang.Throwable -> L74
            r1 = 0
            r2 = r1
        L25:
            boolean r3 = r0.hasNext()     // Catch: java.lang.Throwable -> L74
            if (r3 == 0) goto L72
            int r3 = r9.length     // Catch: java.lang.Throwable -> L74
            if (r3 > r2) goto L2f
            goto L72
        L2f:
            java.lang.Object r3 = r0.next()     // Catch: java.lang.Throwable -> L74
            a.a.a.a.a r3 = (p000a.p001a.p002a.p003a.C0002a) r3     // Catch: java.lang.Throwable -> L74
            if (r3 == 0) goto L25
            boolean r4 = r3.m20g()     // Catch: java.lang.Throwable -> L74
            r5 = 1
            if (r4 == 0) goto L5d
            int r4 = r3.f4c     // Catch: java.lang.Throwable -> L74
            r6 = 5
            if (r4 != r6) goto L5b
            r4 = 256(0x100, float:3.59E-43)
            char[] r4 = new char[r4]     // Catch: java.lang.Throwable -> L74
            int r6 = r3.m2a(r4)     // Catch: java.lang.Throwable -> L74
            r7 = 2
            if (r7 >= r6) goto L5d
            java.lang.String r6 = new java.lang.String     // Catch: java.lang.Throwable -> L74
            r6.<init>(r4, r1, r7)     // Catch: java.lang.Throwable -> L74
            java.lang.String r4 = "CY"
            boolean r4 = r6.equals(r4)     // Catch: java.lang.Throwable -> L74
            if (r4 != 0) goto L5d
        L5b:
            r4 = r5
            goto L5e
        L5d:
            r4 = r1
        L5e:
            if (r4 == 0) goto L25
            java.util.List<a.a.a.a.a> r4 = r8.f2685c     // Catch: java.lang.Throwable -> L74
            r4.add(r3)     // Catch: java.lang.Throwable -> L74
            r4 = r9[r2]     // Catch: java.lang.Throwable -> L74
            int r3 = r3.f4c     // Catch: java.lang.Throwable -> L74
            r4[r1] = r3     // Catch: java.lang.Throwable -> L74
            r3 = r9[r2]     // Catch: java.lang.Throwable -> L74
            int r2 = r2 + 1
            r3[r5] = r2     // Catch: java.lang.Throwable -> L74
            goto L25
        L72:
            monitor-exit(r8)
            return r2
        L74:
            r9 = move-exception
            monitor-exit(r8)
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: jp.p036co.dnp.photoprintlib.DNPPhotoPrint.GetPrinterPortNum(int[][]):int");
    }

    public synchronized int GetPrinterPortNumSimple(int[][] iArr) {
        if (this.f2684b == null) {
            return -1;
        }
        if (iArr == null) {
            return -1;
        }
        this.f2685c.clear();
        if (!m1289a()) {
            return -1;
        }
        Iterator<C0002a> it = this.f2686d.values().iterator();
        int i = 0;
        while (it.hasNext() && iArr.length > i) {
            C0002a next = it.next();
            if (next != null) {
                this.f2685c.add(next);
                iArr[i][0] = next.f4c;
                int[] iArr2 = iArr[i];
                i++;
                iArr2[1] = i;
            }
        }
        return i;
    }

    public int GetRewindMode(int i) {
        ?? r0;
        C0002a c0002aM1288a = m1288a(i);
        if (c0002aM1288a == null) {
            return -1;
        }
        synchronized (c0002aM1288a) {
            r0 = c0002aM1288a.m20g() ? c0002aM1288a.f9h : -1;
        }
        return r0;
    }

    public int GetRfidMediaClass(int i, char[] cArr) {
        int iM0a;
        C0002a c0002aM1288a = m1288a(i);
        if (c0002aM1288a == null) {
            return -1;
        }
        synchronized (c0002aM1288a) {
            if (c0002aM1288a.m20g()) {
                byte[] bArrM1a = C0002a.m1a("INFO", "MEDIA_CLASS_RFID", -1);
                if (c0002aM1288a.m11c(bArrM1a, bArrM1a.length) == bArrM1a.length) {
                    byte[] bArr = new byte[64];
                    if (c0002aM1288a.m8b(bArr, 8) == 8 && (iM0a = C0002a.m0a(bArr, 8)) > 0 && c0002aM1288a.m8b(bArr, iM0a) == iM0a) {
                        int i2 = 0;
                        for (int i3 = 0; i3 < iM0a; i3++) {
                            byte b = bArr[i3];
                            if (b == 13) {
                                break;
                            }
                            if (i2 < cArr.length) {
                                cArr[i3] = (char) b;
                                i2++;
                            }
                        }
                        return i2;
                    }
                }
            }
            return -1;
        }
    }

    public int GetSerialNo(int i, char[] cArr) {
        int iM0a;
        C0002a c0002aM1288a = m1288a(i);
        if (c0002aM1288a == null) {
            return -1;
        }
        synchronized (c0002aM1288a) {
            if (c0002aM1288a.m20g()) {
                byte[] bArrM1a = C0002a.m1a("INFO", "SERIAL_NUMBER", -1);
                if (c0002aM1288a.m11c(bArrM1a, bArrM1a.length) == bArrM1a.length) {
                    byte[] bArr = new byte[64];
                    if (c0002aM1288a.m8b(bArr, 8) == 8 && (iM0a = C0002a.m0a(bArr, 8)) > 0 && c0002aM1288a.m8b(bArr, iM0a) == iM0a) {
                        int i2 = 0;
                        for (int i3 = 0; i3 < iM0a; i3++) {
                            byte b = bArr[i3];
                            if (b == 13) {
                                break;
                            }
                            if (i2 < cArr.length) {
                                cArr[i3] = (char) b;
                                i2++;
                            }
                        }
                        return i2;
                    }
                }
            }
            return -1;
        }
    }

    public int GetStatus(int i) {
        C0002a c0002aM1288a = m1288a(i);
        if (c0002aM1288a != null) {
            return c0002aM1288a.m16e();
        }
        return Integer.MIN_VALUE;
    }

    public int GetSupportedMediaInfo(int i, char[] cArr) {
        int iM0a;
        C0002a c0002aM1288a = m1288a(i);
        if (c0002aM1288a == null) {
            return -1;
        }
        synchronized (c0002aM1288a) {
            if (c0002aM1288a.m20g()) {
                byte[] bArrM1a = C0002a.m1a("MNT_RD", "SUPPORTED_MEDIA", -1);
                if (c0002aM1288a.m11c(bArrM1a, bArrM1a.length) == bArrM1a.length) {
                    byte[] bArr = new byte[64];
                    if (c0002aM1288a.m8b(bArr, 8) == 8 && (iM0a = C0002a.m0a(bArr, 8)) > 0 && c0002aM1288a.m8b(bArr, iM0a) == iM0a && cArr.length >= iM0a) {
                        for (int i2 = 0; i2 < iM0a; i2++) {
                            cArr[i2] = (char) bArr[i2];
                        }
                        return iM0a;
                    }
                }
            }
            return -1;
        }
    }

    public int GetUSBSerialEnable(int i) {
        int iM0a;
        C0002a c0002aM1288a = m1288a(i);
        int i2 = -1;
        if (c0002aM1288a == null) {
            return -1;
        }
        synchronized (c0002aM1288a) {
            if (c0002aM1288a.m20g()) {
                byte[] bArrM1a = C0002a.m1a("MNT_RD", "USB_ISERI_SET", -1);
                if (c0002aM1288a.m11c(bArrM1a, bArrM1a.length) == bArrM1a.length) {
                    byte[] bArr = new byte[64];
                    if (c0002aM1288a.m8b(bArr, 8) == 8 && (iM0a = C0002a.m0a(bArr, 8)) > 0 && c0002aM1288a.m8b(bArr, iM0a) == iM0a) {
                        char[] cArr = new char[iM0a];
                        int i3 = 0;
                        for (int i4 = 0; i4 < iM0a; i4++) {
                            byte b = bArr[i4];
                            if (b == 13) {
                                break;
                            }
                            cArr[i4] = (char) b;
                            i3++;
                        }
                        try {
                            i2 = Integer.parseInt(new String(cArr, 0, i3));
                        } catch (Exception unused) {
                        }
                    }
                }
            }
        }
        return i2;
    }

    public boolean PrintImageData(int i) {
        C0002a c0002aM1288a = m1288a(i);
        if (c0002aM1288a != null) {
            return c0002aM1288a.m18f();
        }
        return false;
    }

    public boolean SendImageData(int i, byte[] bArr, int i2, int i3, int i4, int i5) {
        C0002a c0002aM1288a = m1288a(i);
        if (c0002aM1288a != null) {
            return c0002aM1288a.m7a(bArr, i2, i3, i4, i5);
        }
        return false;
    }

    public boolean SetCutterMode(int i, int i2) {
        C0002a c0002aM1288a = m1288a(i);
        if (c0002aM1288a != null) {
            return c0002aM1288a.m5a(i2);
        }
        return false;
    }

    public boolean SetDecurlCtrl(int i, int i2) {
        C0002a c0002aM1288a = m1288a(i);
        if (c0002aM1288a != null) {
            return c0002aM1288a.m10b(i2);
        }
        return false;
    }

    public boolean SetMediaSize(int i, int i2) {
        C0002a c0002aM1288a = m1288a(i);
        if (c0002aM1288a != null) {
            return c0002aM1288a.m13c(i2);
        }
        return false;
    }

    public boolean SetOvercoatFinish(int i, int i2) {
        C0002a c0002aM1288a = m1288a(i);
        if (c0002aM1288a != null) {
            return c0002aM1288a.m15d(i2);
        }
        return false;
    }

    public boolean SetPQTY(int i, int i2) {
        C0002a c0002aM1288a = m1288a(i);
        if (c0002aM1288a != null) {
            return c0002aM1288a.m17e(i2);
        }
        return false;
    }

    public boolean SetPrintSpeed(int i, int i2) {
        C0002a c0002aM1288a = m1288a(i);
        if (c0002aM1288a != null) {
            return c0002aM1288a.m19f(i2);
        }
        return false;
    }

    public boolean SetResolution(int i, int i2) {
        C0002a c0002aM1288a = m1288a(i);
        if (c0002aM1288a != null) {
            return c0002aM1288a.m21g(i2);
        }
        return false;
    }

    public boolean SetRetryControl(int i, int i2) {
        C0002a c0002aM1288a = m1288a(i);
        if (c0002aM1288a != null) {
            return c0002aM1288a.m23h(i2);
        }
        return false;
    }

    public boolean SetRewindMode(int i, int i2) {
        C0002a c0002aM1288a = m1288a(i);
        if (c0002aM1288a == null) {
            return false;
        }
        synchronized (c0002aM1288a) {
            if (c0002aM1288a.m20g()) {
                if (i2 == 0) {
                    c0002aM1288a.f9h = false;
                } else if (i2 == 1) {
                    c0002aM1288a.f9h = true;
                }
                return true;
            }
            return false;
        }
    }

    public boolean SetSysTime(int i, Date date) {
        C0002a c0002aM1288a = m1288a(i);
        if (c0002aM1288a == null) {
            return false;
        }
        synchronized (c0002aM1288a) {
            if (c0002aM1288a.m20g()) {
                byte[] bArrM1a = C0002a.m1a("CNTRL", "SET_SYS_TIME", 16);
                if (c0002aM1288a.m11c(bArrM1a, bArrM1a.length) == bArrM1a.length) {
                    byte[] bytes = String.format("%s\r\u0000", new SimpleDateFormat("yyyyMMddHHmmss", Locale.getDefault()).format(date)).getBytes(Charset.forName("US-ASCII"));
                    if (c0002aM1288a.m11c(bytes, bytes.length) == 16) {
                        return true;
                    }
                }
            }
            return false;
        }
    }

    public boolean SetUSBSerialEnable(int i, int i2) {
        Charset charsetForName;
        String str;
        C0002a c0002aM1288a = m1288a(i);
        if (c0002aM1288a == null) {
            return false;
        }
        synchronized (c0002aM1288a) {
            if (c0002aM1288a.m20g() && (i2 == 1 || i2 == 0)) {
                byte[] bArrM1a = C0002a.m1a("MNT_WT", "USB_ISERI_SET", 4);
                if (c0002aM1288a.m11c(bArrM1a, bArrM1a.length) == bArrM1a.length) {
                    if (i2 == 0) {
                        charsetForName = Charset.forName("US-ASCII");
                        str = "00\r\u0000";
                    } else {
                        charsetForName = Charset.forName("US-ASCII");
                        str = "01\r\u0000";
                    }
                    byte[] bytes = str.getBytes(charsetForName);
                    if (c0002aM1288a.m11c(bytes, bytes.length) == bytes.length) {
                        return true;
                    }
                }
            }
            return false;
        }
    }

    public boolean SetUSBTimeout(int i, int i2) {
        C0002a c0002aM1288a = m1288a(i);
        if (c0002aM1288a == null) {
            return false;
        }
        synchronized (c0002aM1288a) {
            if (i2 != 0 && (10 > i2 || i2 > 120)) {
                return false;
            }
            c0002aM1288a.f10i = i2;
            return true;
        }
    }

    public boolean StartPageLayout(int i) {
        C0002a c0002aM1288a = m1288a(i);
        if (c0002aM1288a != null) {
            return c0002aM1288a.m22h();
        }
        return false;
    }

    public final synchronized C0002a m1288a(int i) {
        C0002a c0002a;
        try {
            c0002a = this.f2685c.get(i);
        } catch (Exception unused) {
            c0002a = null;
        }
        return c0002a;
    }

    public final synchronized boolean m1289a() {
        C0002a c0002a;
        if (this.f2684b == null) {
            return false;
        }
        synchronized (this) {
            for (C0002a c0002a2 : this.f2686d.values()) {
                if (c0002a2 != null) {
                    c0002a2.m4a();
                }
            }
            this.f2686d.clear();
            for (UsbDevice usbDevice : this.f2684b.getDeviceList().values()) {
                int vendorId = usbDevice.getVendorId();
                int productId = usbDevice.getProductId();
                if (vendorId == 4931) {
                    c0002a = productId != 5 ? null : new C0002a(this.f2683a, this.f2684b, usbDevice, 5);
                } else if (vendorId == 5202) {
                    if (productId == 35585) {
                        c0002a = new C0002a(this.f2683a, this.f2684b, usbDevice, 20);
                    } else if (productId == 37377) {
                        c0002a = new C0002a(this.f2683a, this.f2684b, usbDevice, 35);
                    }
                }
                if (c0002a != null) {
                    this.f2686d.put(c0002a.f3b, c0002a);
                }
            }
        }
        return true;
    }

    public final void m1290b() {
        this.f2687e = new C0004c(this);
        IntentFilter intentFilter = new IntentFilter("jp.co.dnp.photoprintlib.USB_PERMISSION");
        intentFilter.addAction("android.hardware.usb.action.USB_DEVICE_DETACHED");
        this.f2683a.registerReceiver(this.f2687e, intentFilter);
    }
}
