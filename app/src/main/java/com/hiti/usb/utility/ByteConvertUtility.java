package com.hiti.usb.utility;

import android.util.Log;
import kotlin.UByte;

public class ByteConvertUtility {
    public static int ByteToInt(byte[] bArr, int i, int i2) {
        int i3 = 0;
        for (int i4 = 0; i4 < i2; i4++) {
            i3 |= (bArr[i4 + i] & 255) << (24 - (i4 * 8));
        }
        return i3;
    }

    public static long ByteToLong(byte[] bArr, int i, int i2) {
        long j = 0;
        for (int i3 = 0; i3 < i2; i3++) {
            j |= ((long) (bArr[i3 + i] & UByte.MAX_VALUE)) << (24 - (i3 * 8));
        }
        return j;
    }

    public static String ByteToString(byte[] bArr, int i, int i2) {
        char[] cArr = new char[i2];
        for (int i3 = 0; i3 < i2; i3++) {
            cArr[i3] = (char) bArr[i3];
        }
        return String.valueOf(cArr);
    }

    public static byte CheckBit(byte b, byte b2) {
        return (byte) (b & b2);
    }

    public static byte[] IntToByte(int i) {
        byte[] bArr = new byte[4];
        for (int i2 = 0; i2 < 4; i2++) {
            bArr[i2] = (byte) ((i >> (24 - (i2 * 8))) & 255);
        }
        return bArr;
    }

    public static int String10ToInt16(String str) {
        byte[] bArr = new byte[4];
        int i = 0;
        while (i < str.length()) {
            int i2 = i + 2;
            String strSubstring = str.substring(i, i2);
            Log.e("s", strSubstring);
            bArr[i / 2] = (byte) (Integer.parseInt(strSubstring, 16) & 255);
            i = i2;
        }
        return ByteToInt(bArr, 0, 4);
    }
}
