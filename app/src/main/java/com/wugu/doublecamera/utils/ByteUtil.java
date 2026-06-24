package com.wugu.doublecamera.utils;

import androidx.core.view.ViewCompat;
import com.brother.sdk.common.socket.devicemanagement.snmp.westhawk.SnmpConstants;
import com.tom_roush.pdfbox.pdmodel.documentinterchange.taggedpdf.PDLayoutAttributeObject;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;
import kotlin.UByte;

public class ByteUtil {
    private static char forDigit(int i, int i2) {
        if (i >= i2 || i < 0 || i2 < 2 || i2 > 36) {
            return (char) 0;
        }
        return (char) (i < 10 ? i + 48 : i + 55);
    }

    public static int fromByteToInt(byte b) {
        return b & UByte.MAX_VALUE;
    }

    private static int hexChar2byte(char c) {
        switch (c) {
            case '0':
                return 0;
            case '1':
                return 1;
            case '2':
                return 2;
            case '3':
                return 3;
            case '4':
                return 4;
            case '5':
                return 5;
            case '6':
                return 6;
            case '7':
                return 7;
            case '8':
                return 8;
            case '9':
                return 9;
            default:
                switch (c) {
                    case 'A':
                        return 10;
                    case 'B':
                        return 11;
                    case 'C':
                        return 12;
                    case 'D':
                        return 13;
                    case 'E':
                        return 14;
                    case 'F':
                        return 15;
                    default:
                        switch (c) {
                            case 'a':
                                return 10;
                            case 'b':
                                return 11;
                            case 'c':
                                return 12;
                            case 'd':
                                return 13;
                            case 'e':
                                return 14;
                            case 'f':
                                return 15;
                            default:
                                return -1;
                        }
                }
        }
    }

    public static byte[] intToBytesBig(int i) {
        return new byte[]{(byte) ((i >> 24) & 255), (byte) ((i >> 16) & 255), (byte) ((i >> 8) & 255), (byte) (i & 255)};
    }

    public static byte[] intToBytesLittle(int i) {
        return new byte[]{(byte) (i & 255), (byte) ((i >> 8) & 255), (byte) ((i >> 16) & 255), (byte) ((i >> 24) & 255)};
    }

    public static int isOdd(int i) {
        return i & 1;
    }

    public static boolean isInRange(int[] iArr, float f) {
        return iArr != null && iArr.length >= 2 && f >= ((float) iArr[0]) && f <= ((float) iArr[1]);
    }

    @Deprecated
    public static float byte2Float(byte[] bArr) {
        return Float.intBitsToFloat(((bArr[3] & UByte.MAX_VALUE) << 24) | 0 | ((bArr[0] & UByte.MAX_VALUE) << 0) | ((bArr[1] & UByte.MAX_VALUE) << 8) | ((bArr[2] & UByte.MAX_VALUE) << 16));
    }

    public static float hexStr2Float(String str) {
        return Float.intBitsToFloat(new BigInteger(str, 16).intValue());
    }

    public static float hexStr2Float(byte[] bArr) {
        return Float.intBitsToFloat(new BigInteger(bytesToHexStr(bArr), 16).intValue());
    }

    public static byte hexToByte(String str) {
        return (byte) Integer.parseInt(str, 16);
    }

    public static int hexToInt(String str) {
        return Integer.parseInt(str, 16);
    }

    public static String intToHex(int i) {
        return Integer.toHexString(i).toUpperCase();
    }

    public static String byteToHex(Byte b) {
        return String.format("%02x", b).toUpperCase();
    }

    public static byte[] hexToBytesArray(String str) {
        byte[] bArr;
        int length = str.length();
        if (isOdd(length) == 1) {
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
            bArr[i2] = hexToByte(str.substring(i, i3));
            i2++;
            i = i3;
        }
        return bArr;
    }

    public static byte[] hexStrToBytes(String str) {
        int length = str.length() / 2;
        byte[] bArr = new byte[length];
        char[] charArray = str.toUpperCase().toCharArray();
        for (int i = 0; i < length; i++) {
            int i2 = i * 2;
            bArr[i] = (byte) (hexChar2byte(charArray[i2 + 1]) | (hexChar2byte(charArray[i2]) << 4));
        }
        return bArr;
    }

    public static String bytesToHexStr(byte[] bArr) {
        StringBuilder sb = new StringBuilder();
        if (bArr == null || bArr.length == 0) {
            return "";
        }
        char[] cArr = new char[2];
        for (byte b : bArr) {
            cArr[0] = forDigit((b >>> 4) & 15, 16);
            cArr[1] = forDigit(b & SnmpConstants.SNMP_ERR_UNDOFAILED, 16);
            sb.append(cArr);
        }
        return sb.toString();
    }

    public static String bytesToHexStr(byte[] bArr, int i, int i2) {
        byte[] bArr2 = new byte[i2];
        System.arraycopy(bArr, i, bArr2, 0, i2);
        return bytesToHexStr(bArr2);
    }

    public static long hexStrToDecimal(String str) {
        return Long.parseLong(str, 16);
    }

    public static String decimalToFitHex(long j) {
        String upperCase = Long.toHexString(j).toUpperCase();
        if (upperCase.length() % 2 != 0) {
            return PDLayoutAttributeObject.GLYPH_ORIENTATION_VERTICAL_ZERO_DEGREES + upperCase;
        }
        return upperCase.toUpperCase();
    }

    public static String decimalToFitHex(long j, int i) {
        StringBuilder sb = new StringBuilder(decimalToFitHex(j));
        while (sb.length() < i) {
            sb.insert(0, '0');
        }
        return sb.toString();
    }

    public static String fitDecimalStr(int i, int i2) {
        StringBuilder sb = new StringBuilder(String.valueOf(i));
        while (sb.length() < i2) {
            sb.insert(0, PDLayoutAttributeObject.GLYPH_ORIENTATION_VERTICAL_ZERO_DEGREES);
        }
        return sb.toString();
    }

    public static String strToHexString(String str, String str2) {
        byte[] bytes;
        char[] charArray = "0123456789ABCDEF".toCharArray();
        StringBuilder sb = new StringBuilder();
        try {
            bytes = str.getBytes(str2);
        } catch (Exception e) {
            e.printStackTrace();
            bytes = null;
        }
        for (int i = 0; i < bytes.length; i++) {
            sb.append(charArray[(bytes[i] & 240) >> 4]);
            sb.append(charArray[bytes[i] & SnmpConstants.SNMP_ERR_UNDOFAILED]);
        }
        return sb.toString();
    }

    public static String utf8StrToHexString(String str) {
        return strToHexString(str, "UTF-8");
    }

    public static String gb2312StrToHexString(String str) {
        return strToHexString(str, "gb2312");
    }

    public static byte[] long2bytes(long j, int i) {
        byte[] bArr = new byte[i];
        for (int i2 = 0; i2 < i; i2++) {
            bArr[i2] = (byte) ((j >>> (((i - i2) - 1) * 8)) & 255);
        }
        return bArr;
    }

    public static byte[] long2bytes(long j, byte[] bArr, int i, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            bArr[i + i3] = (byte) ((j >>> (((i2 - i3) - 1) * 8)) & 255);
        }
        return bArr;
    }

    public static long bytes2long(byte[] bArr, int i, int i2) {
        long j = 0;
        for (int i3 = 0; i3 < i2; i3++) {
            j |= (((long) bArr[i + i3]) & 255) << (((i2 - 1) - i3) * 8);
        }
        return j;
    }

    public static long convertToLong(byte[] bArr, boolean z) {
        ByteBuffer byteBufferWrap = ByteBuffer.wrap(bArr);
        if (z) {
            byteBufferWrap.order(ByteOrder.LITTLE_ENDIAN);
        }
        return byteBufferWrap.getLong();
    }

    public static byte[] conversion(byte[] bArr) {
        return hexStrToBytes(bytesToHexStr(bArr).replace("7D01", "7D").replace("7D02", "7E"));
    }

    public static String toBinString(long j, int i) {
        int i2 = i * 8;
        char[] cArr = new char[i2];
        Arrays.fill(cArr, '0');
        do {
            if ((1 & j) > 0) {
                i2--;
                cArr[i2] = '1';
            }
            j >>>= 1;
            if (j == 0) {
                break;
            }
        } while (i2 > 0);
        return new String(cArr);
    }

    public static char[] toBinChars(long j, int i) {
        int i2 = i * 8;
        char[] cArr = new char[i2];
        Arrays.fill(cArr, '0');
        do {
            if ((1 & j) > 0) {
                i2--;
                cArr[i2] = '1';
            }
            j >>>= 1;
            if (j == 0) {
                break;
            }
        } while (i2 > 0);
        return cArr;
    }

    public static byte getXOR(byte[] bArr, int i, int i2) {
        return getXOR(bArr, i, i2, (byte) 0);
    }

    public static byte getXOR(byte[] bArr, int i, int i2, byte b) {
        for (int i3 = 0; i3 < i2; i3++) {
            b = (byte) (b ^ bArr[i3 + i]);
        }
        return b;
    }

    private static byte check_sum_8_bit(byte[] bArr, int i, int i2) {
        byte b = 0;
        for (int i3 = i; i3 < i2 + i; i3++) {
            b = (byte) (b + bArr[i3]);
        }
        return b;
    }

    public static byte check_sum_1_byte(byte[] bArr, int i, int i2) {
        return check_sum_8_bit(bArr, i, i2);
    }

    public static int check_sum_4_byte(byte[] bArr, int i, int i2) {
        int i3 = 0;
        for (int i4 = i; i4 < i2 + i; i4++) {
            i3 += bArr[i4] & UByte.MAX_VALUE;
        }
        return i3;
    }

    public static byte[] int2Bytes(int i, int i2) {
        byte[] bArr = new byte[i2];
        for (int i3 = 0; i3 < i2; i3++) {
            bArr[(i2 - i3) - 1] = (byte) ((i >> (i3 * 8)) & 255);
        }
        return bArr;
    }

    public static byte[] lowInt2Bytes(int i) {
        byte[] bArr = new byte[2];
        for (int i2 = 0; i2 < 2; i2++) {
            bArr[i2] = (byte) ((i >> (i2 * 8)) & 255);
        }
        return bArr;
    }

    public static int byteToInt(byte[] bArr) {
        int i = 0;
        for (byte b : bArr) {
            i = (i << 8) | (b & UByte.MAX_VALUE);
        }
        return i;
    }

    public static int lowByteToInt(byte[] bArr) {
        if (bArr == null || bArr.length == 0) {
            return 0;
        }
        int length = bArr.length;
        int i = 0;
        for (int i2 = 0; i2 < length; i2++) {
            i = (i << 8) | (bArr[(length - i2) - 1] & UByte.MAX_VALUE);
        }
        return i;
    }

    public static int byteArrayToInt(byte[] bArr) {
        if (bArr == null || bArr.length == 0) {
            return 0;
        }
        int length = bArr.length;
        int i = 0;
        for (int i2 = 0; i2 < length; i2++) {
            i += (bArr[i2] & UByte.MAX_VALUE) << (((length - 1) - i2) * 8);
        }
        return i;
    }

    public static String byteArrToBinStr(byte[] bArr) {
        StringBuffer stringBuffer = new StringBuffer();
        for (byte b : bArr) {
            StringBuilder sb = new StringBuilder(Long.toString(b & UByte.MAX_VALUE, 2));
            int length = 8 - sb.length();
            for (int i = 0; i < length; i++) {
                sb.insert(0, PDLayoutAttributeObject.GLYPH_ORIENTATION_VERTICAL_ZERO_DEGREES);
            }
            stringBuffer.append((CharSequence) sb);
        }
        return stringBuffer.toString();
    }

    public static int bytesToIntBig(byte[] bArr, int i) {
        return (bArr[i + 3] & UByte.MAX_VALUE) | ((bArr[i] & UByte.MAX_VALUE) << 24) | ((bArr[i + 1] & UByte.MAX_VALUE) << 16) | ((bArr[i + 2] & UByte.MAX_VALUE) << 8);
    }

    public static int bytesToIntLittle(byte[] bArr, int i) {
        return ((bArr[i + 3] & UByte.MAX_VALUE) << 24) | (bArr[i] & UByte.MAX_VALUE) | ((bArr[i + 1] & UByte.MAX_VALUE) << 8) | ((bArr[i + 2] & UByte.MAX_VALUE) << 16);
    }

    public static String byteToBinString(byte b) {
        String binaryString = Integer.toBinaryString(b & UByte.MAX_VALUE);
        StringBuilder sb = new StringBuilder();
        if (binaryString.length() < 8) {
            int length = 8 - binaryString.length();
            for (int i = 0; i < length; i++) {
                sb.append(PDLayoutAttributeObject.GLYPH_ORIENTATION_VERTICAL_ZERO_DEGREES);
            }
        }
        return sb.append(binaryString).toString();
    }

    public static float covertToFloat(byte[] bArr) {
        return Float.intBitsToFloat(byteToInt(bArr));
    }

    public static String convertStringAsciiToHex(int i, int i2) {
        StringBuilder sb = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();
        String str = i + "";
        for (int i3 = 0; i3 < str.length(); i3++) {
            sb.append(Integer.toHexString(str.charAt(i3)));
        }
        int length = i2 - str.length();
        if (length > 0) {
            for (int i4 = 0; i4 < length; i4++) {
                sb2.append(Integer.toHexString(32));
            }
        }
        sb2.append(sb.toString());
        return sb2.toString();
    }

    public static byte[] arrayAppend(byte[] bArr, byte[] bArr2, int i) {
        if (bArr == null && bArr2 == null) {
            return null;
        }
        if (bArr == null) {
            byte[] bArr3 = new byte[i];
            System.arraycopy(bArr2, 0, bArr3, 0, i);
            return bArr3;
        }
        if (bArr2 == null) {
            byte[] bArr4 = new byte[bArr.length];
            System.arraycopy(bArr, 0, bArr4, 0, bArr.length);
            return bArr4;
        }
        byte[] bArr5 = new byte[bArr.length + i];
        System.arraycopy(bArr, 0, bArr5, 0, bArr.length);
        System.arraycopy(bArr2, 0, bArr5, bArr.length, i);
        return bArr5;
    }

    public static byte[] arrayCopyEnd(byte[] bArr, int i) {
        if (bArr == null || bArr.length <= i) {
            return null;
        }
        int length = bArr.length - i;
        byte[] bArr2 = new byte[length];
        System.arraycopy(bArr, i, bArr2, 0, length);
        return bArr2;
    }

    public static String intToInverseHexStr(int i) {
        return String.format("%08x", Integer.valueOf(((((byte) ((i & ViewCompat.MEASURED_STATE_MASK) >> 24)) & UByte.MAX_VALUE) << 0) | ((((byte) ((16711680 & i) >> 16)) & UByte.MAX_VALUE) << 8) | ((((byte) ((65280 & i) >> 8)) & UByte.MAX_VALUE) << 16) | ((((byte) (i & 255)) & UByte.MAX_VALUE) << 24))).toUpperCase();
    }

    public static String intToInverse16HexStr(int i) {
        byte b = (byte) (i & 255);
        byte b2 = (byte) ((65280 & i) >> 8);
        byte b3 = (byte) ((16711680 & i) >> 16);
        byte b4 = (byte) ((i & ViewCompat.MEASURED_STATE_MASK) >> 24);
        return String.format("%08x", Integer.valueOf(((b4 & UByte.MAX_VALUE) << 8) | ((b3 & UByte.MAX_VALUE) << 0) | ((b & UByte.MAX_VALUE) << 16) | ((b2 & UByte.MAX_VALUE) << 24))).toUpperCase();
    }
}
