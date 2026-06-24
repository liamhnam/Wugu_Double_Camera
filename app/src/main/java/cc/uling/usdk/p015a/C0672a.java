package cc.uling.usdk.p015a;

import cc.uling.usdk.InterfaceC0680d;
import com.tom_roush.pdfbox.pdmodel.documentinterchange.taggedpdf.PDLayoutAttributeObject;
import kotlin.UByte;

public class C0672a {
    public static byte m160a(boolean[] zArr) {
        if (zArr == null || zArr.length <= 0) {
            return (byte) 0;
        }
        byte b = 0;
        for (int i = 0; i < Math.min(zArr.length, 8); i++) {
            if (zArr[i]) {
                b = (byte) (b + (1 << i));
            }
        }
        return b;
    }

    public static String m161a(byte b) {
        return String.format("%02X", Byte.valueOf(b));
    }

    public static String m162a(byte[] bArr) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bArr) {
            sb.append(String.format("%02X ", Byte.valueOf(b)));
        }
        return sb.toString();
    }

    public static void m163a(long j, InterfaceC0680d interfaceC0680d) {
        long jCurrentTimeMillis = System.currentTimeMillis();
        do {
            if (interfaceC0680d != null) {
                try {
                    interfaceC0680d.m255a();
                    if (interfaceC0680d.m256b()) {
                        return;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } while (System.currentTimeMillis() - jCurrentTimeMillis < j);
    }

    public static byte[] m164a(int i) {
        return new byte[]{(byte) ((i >> 8) & 255), (byte) (i & 255)};
    }

    public static byte[] m165a(String str) {
        byte[] bArr;
        int length = str.length();
        if (length % 2 == 1) {
            length++;
            bArr = new byte[length / 2];
            str = PDLayoutAttributeObject.GLYPH_ORIENTATION_VERTICAL_ZERO_DEGREES + str;
        } else {
            bArr = new byte[length / 2];
        }
        int i = 0;
        int i2 = 0;
        while (i < length) {
            int i3 = i + 2;
            bArr[i2] = m168b(str.substring(i, i3));
            i2++;
            i = i3;
        }
        return bArr;
    }

    public static byte[] m166a(short s, boolean z) {
        byte[] bArr = new byte[2];
        if (z) {
            bArr[1] = (byte) (s & 255);
            bArr[0] = (byte) ((s >> 8) & 255);
        } else {
            bArr[0] = (byte) (s & 255);
            bArr[1] = (byte) ((s >> 8) & 255);
        }
        return bArr;
    }

    public static byte[] m167a(byte[] bArr, int i) {
        int i2 = 65535;
        for (int i3 = 0; i3 < i; i3++) {
            i2 = ((i2 & 255) ^ (bArr[i3] & UByte.MAX_VALUE)) | (65280 & i2);
            for (int i4 = 0; i4 < 8; i4++) {
                int i5 = i2 & 1;
                i2 >>= 1;
                if (i5 > 0) {
                    i2 ^= 40961;
                }
            }
        }
        return m171b(i2);
    }

    public static byte m168b(String str) {
        return (byte) Integer.parseInt(str, 16);
    }

    public static int m169b(byte[] bArr) {
        if (bArr.length == 2) {
            bArr = new byte[]{0, 0, bArr[0], bArr[1]};
        }
        int i = 0;
        for (int i2 = 0; i2 < bArr.length; i2++) {
            i += (bArr[i2] & UByte.MAX_VALUE) << ((3 - i2) * 8);
        }
        return i;
    }

    public static byte[] m170b(byte b) {
        byte[] bArr = new byte[8];
        for (int i = 7; i >= 0; i--) {
            bArr[i] = (byte) (b & 1);
            b = (byte) (b >> 1);
        }
        return bArr;
    }

    public static byte[] m171b(int i) {
        return new byte[]{(byte) (i & 255), (byte) ((i >> 8) & 255)};
    }

    public static String m172c(String str) {
        int length = str.length();
        int i = length / 4;
        if (length % 4 != 0) {
            i++;
        }
        String str2 = "";
        while ((str2.length() + length) % 4 != 0) {
            str2 = str2 + PDLayoutAttributeObject.GLYPH_ORIENTATION_VERTICAL_ZERO_DEGREES;
        }
        String str3 = str2 + str;
        StringBuilder sb = new StringBuilder();
        for (int i2 = 0; i2 < i; i2++) {
            int iPow = 0;
            for (int i3 = 0; i3 < 4; i3++) {
                int i4 = (i2 * 4) + i3;
                if (i4 < length && str3.charAt(i4) == '1') {
                    iPow += (int) Math.pow(2.0d, 3 - i3);
                }
            }
            sb.append(Integer.toHexString(iPow));
        }
        return sb.toString();
    }

    public static byte[] m173c(int i) {
        return new byte[]{(byte) ((i >> 24) & 255), (byte) ((i >> 16) & 255), (byte) ((i >> 8) & 255), (byte) (i & 255)};
    }

    public static String m174d(String str) {
        return Integer.toBinaryString(Integer.parseInt(str.replace(" ", ""), 16));
    }

    public static byte[] m175d(int i) {
        return m165a(Integer.toHexString(i));
    }

    public static byte m176e(int i) {
        return m168b(Integer.toString(i, 16));
    }

    public static byte[] m177f(int i) {
        byte[] bArr = new byte[2];
        if (i < 256) {
            bArr[1] = (byte) i;
        } else {
            bArr[1] = (byte) (i & 255);
            bArr[0] = (byte) (i >> 8);
        }
        return bArr;
    }
}
