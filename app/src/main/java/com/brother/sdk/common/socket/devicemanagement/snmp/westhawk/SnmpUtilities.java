package com.brother.sdk.common.socket.devicemanagement.snmp.westhawk;

public class SnmpUtilities {
    static final char[] HEX_DIGIT = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    public static String getSnmpVersionString(int i) {
        return i != 0 ? i != 1 ? i != 3 ? "Unsupported version no " + i : "SNMPv3" : "SNMPv2c" : "SNMPv1";
    }

    public static byte[] toBytes(String str) {
        int i = 0;
        byte[] bArr = new byte[0];
        if (str == null) {
            return bArr;
        }
        String upperCase = str.toUpperCase();
        int length = upperCase.length();
        byte[] bArr2 = new byte[length / 2];
        int i2 = 0;
        while (i < length) {
            bArr2[i2] = (byte) ((Character.digit(upperCase.charAt(i), 16) * 16) + Character.digit(upperCase.charAt(i + 1), 16));
            i += 2;
            i2++;
        }
        return bArr2;
    }

    public static void dumpBytes(String str, byte[] bArr) {
        System.out.println();
        System.out.println(str);
        System.out.println("bytes.length: " + bArr.length);
        int length = bArr.length;
        int i = 0;
        while (i < length) {
            System.out.print(toHex(bArr[i]) + " ");
            i++;
            if (i % 8 == 0) {
                System.out.println();
            }
        }
        System.out.println();
        System.out.println();
    }

    public static String toHexString(byte[] bArr) {
        if (bArr == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (byte b : bArr) {
            sb.append(toHex(b));
        }
        return sb.toString();
    }

    public static String toHex(int i) {
        int i2 = (i >> 4) & 15;
        StringBuilder sb = new StringBuilder("");
        char[] cArr = HEX_DIGIT;
        return sb.append(cArr[i2]).append(cArr[i & 15]).toString();
    }

    public static boolean areBytesEqual(byte[] bArr, byte[] bArr2) {
        int length = bArr.length;
        if (length != bArr2.length) {
            return false;
        }
        boolean z = true;
        for (int i = 0; i < length && z; i++) {
            z = bArr[i] == bArr2[i];
        }
        return z;
    }
}
