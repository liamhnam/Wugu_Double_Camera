package p000a.p001a.p002a.p003a;

import android.content.Context;
import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbDeviceConnection;
import android.hardware.usb.UsbEndpoint;
import android.hardware.usb.UsbInterface;
import android.hardware.usb.UsbManager;
import androidx.core.view.InputDeviceCompat;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import jp.p036co.dnp.photoprintlib.DNPPhotoPrint;
import p000a.p001a.p002a.p003a.C0003b;

public class C0002a {

    public static final HashMap<String, Integer> f0l = new a();

    public static final HashMap<Integer, String> f1m = new b();

    public UsbManager f2a;

    public UsbDevice f3b;

    public int f4c;

    public int f10i;

    public UsbDeviceConnection f5d = null;

    public UsbInterface f6e = null;

    public UsbEndpoint f7f = null;

    public UsbEndpoint f8g = null;

    public boolean f9h = false;

    public List<C0003b> f11j = new ArrayList();

    public int f12k = 1048576;

    public static class a extends HashMap<String, Integer> {
        public a() {
            put("00000", 65537);
            put("00001", 65538);
            put("01100", Integer.valueOf(DNPPhotoPrint.STATUS_USUALLY_PAPER_END));
            put("01200", Integer.valueOf(DNPPhotoPrint.STATUS_USUALLY_RIBBON_END));
            put("00500", Integer.valueOf(DNPPhotoPrint.STATUS_USUALLY_COOLING));
            put("00510", Integer.valueOf(DNPPhotoPrint.STATUS_USUALLY_MOTCOOLING));
            put("00900", 65664);
            put("01000", 131073);
            put("01300", 131074);
            put("01400", 131076);
            put("01500", 131080);
            put("01600", 131088);
            put("01010", Integer.valueOf(DNPPhotoPrint.STATUS_SETTING_SCRAPBOX_ERR));
            put("02000", 262145);
            put("02100", 262146);
            put("02200", 262148);
            put("02300", Integer.valueOf(DNPPhotoPrint.STATUS_HARDWARE_ERR04));
            put("02400", Integer.valueOf(DNPPhotoPrint.STATUS_HARDWARE_ERR05));
            put("02500", Integer.valueOf(DNPPhotoPrint.STATUS_HARDWARE_ERR06));
            put("02600", Integer.valueOf(DNPPhotoPrint.STATUS_HARDWARE_ERR07));
            put("02700", Integer.valueOf(DNPPhotoPrint.STATUS_HARDWARE_ERR08));
            put("02800", Integer.valueOf(DNPPhotoPrint.STATUS_HARDWARE_ERR09));
            put("02610", Integer.valueOf(DNPPhotoPrint.STATUS_HARDWARE_ERR10));
            put("02010", Integer.valueOf(DNPPhotoPrint.STATUS_HARDWARE_ERR12));
            put("03000", 524289);
        }
    }

    public static class b extends HashMap<Integer, String> {
        public b() {
            put(1, "00000001");
            put(2, "00000003");
            put(3, "00000002");
            put(4, "00000004");
            put(5, "00000005");
            put(6, "00000012");
            put(7, "00000022");
            put(8, "00000402");
            put(9, "00000401");
            put(10, "00000029");
            put(11, "00000027");
            put(12, "00000030");
            put(13, "00000031");
            put(14, "00000430");
            put(31, "00000006");
            put(32, "00000007");
            put(33, "00000008");
            put(34, "00000009");
            put(35, "00000010");
            put(36, "00000011");
            put(37, "00000013");
            put(38, "00000014");
            put(39, "00000015");
            put(40, "00000016");
            put(41, "00000017");
            put(42, "00000018");
            put(43, "00000019");
            put(44, "00000020");
            put(45, "00000021");
            put(46, "00000032");
            put(47, "00000033");
            put(48, "00000408");
            put(49, "00000409");
            put(50, "00000410");
            put(71, "00000034");
            put(72, "00000035");
            put(73, "00000434");
            put(74, "00000037");
            put(75, "00000038");
            put(76, "00000039");
            put(77, "00000040");
            put(78, "00000041");
            put(79, "00000043");
            put(80, "00000437");
            put(54, "00000047");
            put(55, "00000048");
            put(56, "00000049");
            put(57, "00000050");
            put(58, "00000051");
            put(59, "00000052");
        }
    }

    public C0002a(Context context, UsbManager usbManager, UsbDevice usbDevice, int i) {
        this.f2a = usbManager;
        this.f3b = usbDevice;
        this.f4c = i;
        usbDevice.getVendorId();
        this.f3b.getProductId();
        this.f10i = 0;
    }

    public static int m0a(byte[] bArr, int i) {
        try {
            return Integer.parseInt(new String(Arrays.copyOf(bArr, i), Charset.forName("US-ASCII")));
        } catch (Exception unused) {
            return 0;
        }
    }

    public static byte[] m1a(String str, String str2, int i) {
        byte[] bArr = new byte[32];
        Arrays.fill(bArr, (byte) 32);
        byte[] bArr2 = {27, 80};
        byte[] bytes = str.getBytes(Charset.forName("US-ASCII"));
        byte[] bytes2 = str2 != null ? str2.getBytes(Charset.forName("US-ASCII")) : null;
        byte[] bytes3 = i >= 0 ? String.format("%08d", Integer.valueOf(i)).getBytes(Charset.forName("US-ASCII")) : null;
        System.arraycopy(bArr2, 0, bArr, 0, 2);
        System.arraycopy(bytes, 0, bArr, 2, bytes.length);
        if (bytes2 != null) {
            System.arraycopy(bytes2, 0, bArr, 8, bytes2.length);
        }
        if (bytes3 != null) {
            System.arraycopy(bytes3, 0, bArr, 24, bytes3.length);
        }
        return bArr;
    }

    public synchronized int m2a(char[] cArr) {
        if (!m20g()) {
            return -1;
        }
        byte[] bArrM1a = m1a("INFO", "FVER", -1);
        if (m11c(bArrM1a, bArrM1a.length) != bArrM1a.length) {
            return -1;
        }
        byte[] bArr = new byte[64];
        if (m8b(bArr, 8) != 8) {
            return -1;
        }
        int iM0a = m0a(bArr, 8);
        if (iM0a <= 0) {
            return -1;
        }
        if (m8b(bArr, iM0a) != iM0a) {
            return -1;
        }
        if (cArr.length < iM0a) {
            return -1;
        }
        for (int i = 0; i < iM0a; i++) {
            cArr[i] = (char) bArr[i];
        }
        return iM0a;
    }

    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final synchronized int m3a(int... r10) {
        /*
            Method dump skipped, instruction units count: 324
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: p000a.p001a.p002a.p003a.C0002a.m3a(int[]):int");
    }

    public synchronized void m4a() {
        UsbDeviceConnection usbDeviceConnection = this.f5d;
        if (usbDeviceConnection != null) {
            usbDeviceConnection.close();
        }
        this.f5d = null;
        this.f6e = null;
        this.f7f = null;
        this.f8g = null;
    }

    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public synchronized boolean m7a(byte[] r21, int r22, int r23, int r24, int r25) {
        /*
            Method dump skipped, instruction units count: 444
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: p000a.p001a.p002a.p003a.C0002a.m7a(byte[], int, int, int, int):boolean");
    }

    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final synchronized int m8b(byte[] r10, int r11) {
        /*
            r9 = this;
            monitor-enter(r9)
            r0 = -1
            if (r11 > 0) goto L6
            monitor-exit(r9)
            return r0
        L6:
            int r1 = r10.length     // Catch: java.lang.Throwable -> L4a
            if (r1 >= r11) goto Lb
            monitor-exit(r9)
            return r0
        Lb:
            boolean r1 = r9.m26j()     // Catch: java.lang.Throwable -> L4a
            if (r1 != 0) goto L13
            monitor-exit(r9)
            return r0
        L13:
            int r1 = r9.f10i     // Catch: java.lang.Throwable -> L4a
            int r1 = r1 * 1000
            long r2 = java.lang.System.currentTimeMillis()     // Catch: java.lang.Throwable -> L4a
            r4 = r11
        L1c:
            if (r4 == 0) goto L37
            android.hardware.usb.UsbDeviceConnection r5 = r9.f5d     // Catch: java.lang.Throwable -> L4a
            android.hardware.usb.UsbEndpoint r6 = r9.f7f     // Catch: java.lang.Throwable -> L4a
            int r5 = r5.bulkTransfer(r6, r10, r4, r1)     // Catch: java.lang.Throwable -> L4a
            if (r5 >= 0) goto L3c
            if (r1 == 0) goto L3a
            long r5 = java.lang.System.currentTimeMillis()     // Catch: java.lang.Throwable -> L4a
            long r7 = (long) r1
            long r5 = r5 - r2
            r1 = 1000(0x3e8, double:4.94E-321)
            long r5 = r5 + r1
            int r10 = (r7 > r5 ? 1 : (r7 == r5 ? 0 : -1))
            if (r10 > 0) goto L3a
        L37:
            int r11 = r11 - r4
            monitor-exit(r9)
            return r11
        L3a:
            monitor-exit(r9)
            return r0
        L3c:
            int r4 = r4 - r5
            if (r1 == 0) goto L1c
            long r5 = java.lang.System.currentTimeMillis()     // Catch: java.lang.Throwable -> L4a
            long r7 = (long) r1
            long r5 = r5 - r2
            int r5 = (r7 > r5 ? 1 : (r7 == r5 ? 0 : -1))
            if (r5 > 0) goto L1c
            goto L37
        L4a:
            r10 = move-exception
            monitor-exit(r9)
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: p000a.p001a.p002a.p003a.C0002a.m8b(byte[], int):int");
    }

    public synchronized boolean m9b() {
        boolean zM44d = false;
        if (!m20g()) {
            return false;
        }
        C0003b c0003bM12c = m12c();
        if (!c0003bM12c.m43c() && c0003bM12c.f39z > 0 && c0003bM12c.f24k) {
            c0003bM12c.f24k = false;
            zM44d = c0003bM12c.m44d();
        }
        return zM44d;
    }

    public final synchronized int m11c(byte[] bArr, int i) {
        if (bArr == null || i <= 0) {
            return -1;
        }
        if (bArr.length < i) {
            return -1;
        }
        if (!m26j()) {
            return -1;
        }
        byte[] bArrCopyOfRange = bArr;
        int i2 = i;
        int i3 = 0;
        while (i2 != 0) {
            int i4 = this.f12k;
            if (i4 <= 0 || i4 >= i2) {
                i4 = i2;
            }
            if (i2 - i4 == 0 && i4 % 512 == 0) {
                i4 += InputDeviceCompat.SOURCE_ANY;
            }
            if (i3 != 0) {
                bArrCopyOfRange = Arrays.copyOfRange(bArr, i3, i3 + i4);
            }
            int iBulkTransfer = this.f5d.bulkTransfer(this.f8g, bArrCopyOfRange, i4, 0);
            if (iBulkTransfer < 0) {
                return -1;
            }
            i2 -= iBulkTransfer;
            i3 += iBulkTransfer;
            bArrCopyOfRange = null;
        }
        return i - i2;
    }

    public final synchronized C0003b m12c() {
        C0003b c0003b;
        int i = 0;
        while (true) {
            if (i >= this.f11j.size()) {
                c0003b = null;
                break;
            }
            c0003b = this.f11j.get(i);
            if (!c0003b.m43c()) {
                break;
            }
            i++;
        }
        if (c0003b == null) {
            c0003b = new C0003b(this);
            this.f11j.add(c0003b);
        }
        return c0003b;
    }

    public synchronized boolean m13c(int i) {
        boolean z;
        boolean z2 = false;
        if (!m20g()) {
            return false;
        }
        switch (i) {
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
            case 12:
            case 13:
            case 14:
                int i2 = this.f4c;
                z = i2 == 3 || i2 == 5 || i2 == 20;
                break;
            default:
                switch (i) {
                    default:
                        switch (i) {
                            case 54:
                            case 55:
                            case 56:
                            case 57:
                            case 58:
                            case 59:
                                if (this.f4c != 35) {
                                }
                                break;
                            default:
                                switch (i) {
                                    case 71:
                                    case 72:
                                    case 73:
                                    case 74:
                                    case 75:
                                    case 76:
                                    case 77:
                                    case 78:
                                    case 79:
                                    case 80:
                                        break;
                                    default:
                                        break;
                                }
                                break;
                        }
                    case 31:
                    case 32:
                    case 33:
                    case 34:
                    case 35:
                    case 36:
                    case 37:
                    case 38:
                    case 39:
                    case 40:
                    case 41:
                    case 42:
                    case 43:
                    case 44:
                    case 45:
                    case 46:
                    case 47:
                    case 48:
                    case 49:
                    case 50:
                        int i3 = this.f4c;
                        if (i3 != 4 && i3 != 30) {
                        }
                        break;
                }
                break;
        }
        if (!z) {
            return false;
        }
        C0003b c0003bM12c = m12c();
        if (!c0003bM12c.m43c()) {
            if (c0003bM12c.f17d != i) {
                c0003bM12c.m41a();
            }
            c0003bM12c.f17d = i;
            z2 = true;
        }
        return z2;
    }

    public synchronized int m14d() {
        if (!m20g()) {
            return -1;
        }
        int i = 0;
        for (int i2 = 0; i2 < this.f11j.size(); i2++) {
            C0003b c0003b = this.f11j.get(i2);
            i += c0003b.m43c() ? c0003b.f13A.f68d : 0;
        }
        return i;
    }

    public synchronized int m16e() {
        if (!m20g()) {
            return Integer.MIN_VALUE;
        }
        byte[] bArrM1a = m1a("STATUS", null, -1);
        if (m11c(bArrM1a, bArrM1a.length) == bArrM1a.length) {
            byte[] bArr = new byte[64];
            int iM8b = m8b(bArr, 8);
            if (iM8b == -1) {
                return Integer.MIN_VALUE;
            }
            if (iM8b < 8) {
                return DNPPhotoPrint.CSPSTATUS_TIMEOUT;
            }
            int iM0a = m0a(bArr, 8);
            if (iM0a <= 0) {
                return Integer.MIN_VALUE;
            }
            int iM8b2 = m8b(bArr, iM0a);
            if (iM8b2 == -1) {
                return Integer.MIN_VALUE;
            }
            if (iM8b2 < iM0a) {
                return DNPPhotoPrint.CSPSTATUS_TIMEOUT;
            }
            String str = new String(Arrays.copyOf(bArr, 5), Charset.forName("US-ASCII"));
            HashMap<String, Integer> map = f0l;
            if (map.containsKey(str)) {
                return map.get(str).intValue();
            }
        }
        return Integer.MIN_VALUE;
    }

    public synchronized boolean m17e(int i) {
        boolean z = false;
        if (!m20g()) {
            return false;
        }
        if (i <= 0) {
            return false;
        }
        C0003b c0003bM12c = m12c();
        if (!c0003bM12c.m43c()) {
            c0003bM12c.f16c = i;
            z = true;
        }
        return z;
    }

    public synchronized boolean m18f() {
        boolean zM44d = false;
        if (!m20g()) {
            return false;
        }
        C0003b c0003bM12c = m12c();
        if (!c0003bM12c.m43c() && !c0003bM12c.f24k && c0003bM12c.f39z > 0) {
            zM44d = c0003bM12c.m44d();
        }
        return zM44d;
    }

    public synchronized boolean m20g() {
        UsbDevice usbDevice;
        if (m26j()) {
            return true;
        }
        UsbManager usbManager = this.f2a;
        if (usbManager != null && (usbDevice = this.f3b) != null) {
            this.f6e = null;
            this.f8g = null;
            this.f7f = null;
            UsbDeviceConnection usbDeviceConnectionOpenDevice = usbManager.openDevice(usbDevice);
            this.f5d = usbDeviceConnectionOpenDevice;
            if (usbDeviceConnectionOpenDevice == null) {
                return false;
            }
            int interfaceCount = this.f3b.getInterfaceCount();
            int i = 0;
            while (true) {
                if (i >= interfaceCount) {
                    break;
                }
                UsbInterface usbInterface = this.f3b.getInterface(i);
                if (usbInterface == null) {
                    return false;
                }
                if (usbInterface.getInterfaceClass() == 7) {
                    int endpointCount = usbInterface.getEndpointCount();
                    if (endpointCount < 2) {
                        return false;
                    }
                    for (int i2 = 0; i2 < endpointCount; i2++) {
                        UsbEndpoint endpoint = usbInterface.getEndpoint(i2);
                        if (endpoint.getType() == 2) {
                            if (endpoint.getDirection() == 128) {
                                this.f7f = endpoint;
                            } else {
                                this.f8g = endpoint;
                            }
                        }
                    }
                    if (this.f7f != null && this.f8g != null) {
                        this.f6e = usbInterface;
                        break;
                    }
                }
                i++;
            }
            if (this.f7f != null && this.f8g != null) {
                return this.f5d.claimInterface(this.f6e, true);
            }
            return false;
        }
        return false;
    }

    public synchronized boolean m21g(int i) {
        boolean z = false;
        if (!m20g()) {
            return false;
        }
        if (!(i == 300 || i == 600)) {
            return false;
        }
        C0003b c0003bM12c = m12c();
        if (!c0003bM12c.m43c()) {
            if (c0003bM12c.f18e != i && c0003bM12c.f39z != -1) {
                c0003bM12c.m41a();
            }
            c0003bM12c.f18e = i;
            z = true;
        }
        return z;
    }

    public synchronized boolean m23h(int i) {
        boolean z = false;
        if (!m20g()) {
            return false;
        }
        if (i != 0 && i != 1) {
            return false;
        }
        C0003b c0003bM12c = m12c();
        if (!c0003bM12c.m43c()) {
            c0003bM12c.f23j = i;
            z = true;
        }
        return z;
    }

    public int m24i() {
        return this.f4c;
    }

    public synchronized boolean m25i(int i) {
        if (!m20g()) {
            return false;
        }
        byte[] bArrM1a = m1a("CNTRL", "BUFFCNTRL", 8);
        if (m11c(bArrM1a, bArrM1a.length) != bArrM1a.length) {
            return false;
        }
        byte[] bytes = String.format("%08d", Integer.valueOf(i)).getBytes(Charset.forName("US-ASCII"));
        return m11c(bytes, bytes.length) == 8;
    }

    public final synchronized boolean m26j() {
        if (this.f5d == null) {
            return false;
        }
        if (this.f6e == null) {
            return false;
        }
        if (this.f8g == null) {
            return false;
        }
        return this.f7f != null;
    }

    public synchronized boolean m27j(int i) {
        if (!m20g()) {
            return false;
        }
        byte[] bArrM1a = m1a("CNTRL", "CUTTER", 8);
        if (m11c(bArrM1a, bArrM1a.length) != bArrM1a.length) {
            return false;
        }
        byte[] bytes = String.format("%08d", Integer.valueOf(i)).getBytes(Charset.forName("US-ASCII"));
        return m11c(bytes, bytes.length) == 8;
    }

    public synchronized boolean m28k() {
        if (!m20g()) {
            return false;
        }
        byte[] bArrM1a = m1a("CNTRL", "START", -1);
        return m11c(bArrM1a, bArrM1a.length) == bArrM1a.length;
    }

    public synchronized boolean m29k(int i) {
        if (!m20g()) {
            return false;
        }
        byte[] bArrM1a = m1a("CNTRL", "DECURL", 12);
        if (m11c(bArrM1a, bArrM1a.length) != bArrM1a.length) {
            return false;
        }
        byte[] bytes = String.format("%02d0000000000", Integer.valueOf(i)).getBytes(Charset.forName("US-ASCII"));
        return m11c(bytes, bytes.length) == 12;
    }

    public synchronized boolean m30l(int i) {
        if (!m20g()) {
            return false;
        }
        byte[] bArrM1a = m1a("CNTRL", "OVERCOAT", 8);
        if (m11c(bArrM1a, bArrM1a.length) != bArrM1a.length) {
            return false;
        }
        byte[] bytes = String.format("%08d", Integer.valueOf(i)).getBytes(Charset.forName("US-ASCII"));
        return m11c(bytes, bytes.length) == 8;
    }

    public synchronized boolean m31m(int i) {
        if (!m20g()) {
            return false;
        }
        byte[] bArrM1a = m1a("CNTRL", "PRINTSPEED", 8);
        if (m11c(bArrM1a, bArrM1a.length) != bArrM1a.length) {
            return false;
        }
        byte[] bytes = String.format("%07d0", Integer.valueOf(i)).getBytes(Charset.forName("US-ASCII"));
        return m11c(bytes, bytes.length) == 8;
    }

    public synchronized boolean m32n(int i) {
        if (!m20g()) {
            return false;
        }
        byte[] bArrM1a = m1a("IMAGE", "MULTICUT", 8);
        if (m11c(bArrM1a, bArrM1a.length) != bArrM1a.length) {
            return false;
        }
        String str = f1m.get(Integer.valueOf(i));
        if (str != null) {
            byte[] bytes = str.getBytes(Charset.forName("US-ASCII"));
            if (m11c(bytes, bytes.length) == 8) {
                return true;
            }
        }
        return false;
    }

    public synchronized boolean m10b(int i) {
        boolean z = false;
        if (!m20g()) {
            return false;
        }
        C0003b c0003bM12c = m12c();
        if (!c0003bM12c.m43c()) {
            c0003bM12c.f20g = i;
            z = true;
        }
        return z;
    }

    public synchronized boolean m15d(int i) {
        boolean z = false;
        if (!m20g()) {
            return false;
        }
        C0003b c0003bM12c = m12c();
        if (!c0003bM12c.m43c() && (c0003bM12c.f31r == C0003b.a.UNDEFINED || C0003b.m35a(i) == C0003b.m35a(c0003bM12c.f21h))) {
            c0003bM12c.f21h = i;
            z = true;
        }
        return z;
    }

    public synchronized boolean m19f(int i) {
        boolean z = false;
        if (!m20g()) {
            return false;
        }
        C0003b c0003bM12c = m12c();
        if (!c0003bM12c.m43c()) {
            c0003bM12c.f19f = i;
            z = true;
        }
        return z;
    }

    public synchronized boolean m22h() {
        boolean z = false;
        if (!m20g()) {
            return false;
        }
        C0003b c0003bM12c = m12c();
        if (!c0003bM12c.m43c()) {
            z = true;
            c0003bM12c.f24k = true;
        }
        return z;
    }

    public synchronized boolean m5a(int i) {
        boolean z = false;
        if (!m20g()) {
            return false;
        }
        C0003b c0003bM12c = m12c();
        if (!c0003bM12c.m43c()) {
            c0003bM12c.f22i = i;
            z = true;
        }
        return z;
    }

    public synchronized boolean m6a(int i, int i2, int i3, byte[] bArr, int i4) {
        String str;
        String str2;
        if (!m20g()) {
            return false;
        }
        int i5 = (((i2 + 3) / 4) * 4 * i3) + 1088;
        byte[] bArr2 = new byte[1088];
        ByteBuffer byteBufferWrap = ByteBuffer.wrap(bArr2);
        if (byteBufferWrap == null) {
            bArr2 = null;
        } else {
            byteBufferWrap.order(ByteOrder.LITTLE_ENDIAN);
            byteBufferWrap.putShort((short) 19778);
            byteBufferWrap.putInt(i5);
            byteBufferWrap.putShort((short) 0);
            byteBufferWrap.putShort((short) 0);
            byteBufferWrap.putInt(1088);
            byteBufferWrap.putInt(40);
            byteBufferWrap.putInt(i2);
            byteBufferWrap.putInt(i3);
            byteBufferWrap.putShort((short) 1);
            byteBufferWrap.putShort((short) 8);
            byteBufferWrap.putInt(0);
            byteBufferWrap.putInt(0);
            byteBufferWrap.putInt(0);
            byteBufferWrap.putInt(0);
            byteBufferWrap.putInt(0);
            byteBufferWrap.putInt(0);
            for (int i6 = 0; i6 < 256; i6++) {
                byte b2 = (byte) (i6 & 255);
                byteBufferWrap.put(b2);
                byteBufferWrap.put(b2);
                byteBufferWrap.put(b2);
                byteBufferWrap.put((byte) 0);
            }
            for (int i7 = 0; i7 < 10; i7++) {
                byteBufferWrap.put((byte) 0);
            }
        }
        if (bArr2 == null) {
            return false;
        }
        int length = bArr2.length + i4;
        if (i == 0) {
            str = "IMAGE";
            str2 = "YPLANE";
        } else if (i != 1) {
            str = "IMAGE";
            str2 = "CPLANE";
        } else {
            str = "IMAGE";
            str2 = "MPLANE";
        }
        byte[] bArrM1a = m1a(str, str2, length);
        if (m11c(bArrM1a, bArrM1a.length) != bArrM1a.length) {
            return false;
        }
        if (m11c(bArr2, bArr2.length) != bArr2.length) {
            return false;
        }
        return m11c(bArr, i4) == i4;
    }
}
