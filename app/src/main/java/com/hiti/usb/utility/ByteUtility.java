package com.hiti.usb.utility;

import android.content.Context;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Arrays;
import kotlin.UByte;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EncodingUtils;

public class ByteUtility {
    private static final boolean localLOG = true;
    private static final String tag = "ByteUtility";

    public static String Ascii2string(byte[] bArr) {
        if (bArr == null || bArr.length == 0) {
            return null;
        }
        StringBuffer stringBuffer = new StringBuffer();
        for (byte b : bArr) {
            stringBuffer.append((char) b);
        }
        return stringBuffer.toString();
    }

    public static String Ascii2string(byte[] bArr, int i, int i2) {
        if (bArr == null || bArr.length == 0 || i2 == 0 || i >= bArr.length) {
            return null;
        }
        if (i + i2 > bArr.length) {
            i2 = bArr.length - i;
        }
        StringBuffer stringBuffer = new StringBuffer();
        while (i < i2) {
            stringBuffer.append((char) bArr[i]);
            i++;
        }
        return stringBuffer.toString();
    }

    public static int byte2Int(byte[] bArr) {
        return new BigInteger(bArr).intValue();
    }

    public static int byte2Int(byte[] bArr, int i, int i2) {
        if (i + i2 > bArr.length) {
            i2 = bArr.length - i;
        }
        byte[] bArr2 = new byte[i2];
        System.arraycopy(bArr, i, bArr2, 0, i2);
        return new BigInteger(bArr2).intValue();
    }

    public static String byteToFile(Context context, byte[] bArr, String str) {
        if (bArr != null && bArr.length != 0) {
            String str2 = context.getExternalFilesDir(null).getAbsolutePath() + File.separator + str;
            try {
                FileOutputStream fileOutputStream = new FileOutputStream(str2, false);
                fileOutputStream.write(bArr, 0, bArr.length);
                fileOutputStream.flush();
                fileOutputStream.close();
                return str2;
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e2) {
                e2.printStackTrace();
                return null;
            }
        }
        return null;
    }

    public static int[] getDate(byte[] bArr) {
        if (bArr.length <= 32) {
            return null;
        }
        byte[] bArrCopyOfRange = Arrays.copyOfRange(bArr, 8, 32);
        int[] iArr = new int[6];
        for (int i = 0; i < 6; i++) {
            iArr[i] = ByteConvertUtility.ByteToInt(bArrCopyOfRange, i * 4, 4);
        }
        return iArr;
    }

    public static String getEncodingName(byte[] bArr) {
        int length = bArr.length - 32;
        if (length <= 0) {
            return null;
        }
        byte b = bArr[2];
        String str = b == 16 ? "UNICODE" : HTTP.ASCII;
        if (b == 16) {
            length -= length % 2;
        }
        byte[] bArrCopyOfRange = Arrays.copyOfRange(bArr, 32, length + 32);
        if (b == 16) {
            int i = 0;
            while (true) {
                int i2 = i + 1;
                if (i2 >= length) {
                    break;
                }
                byte b2 = bArrCopyOfRange[i];
                bArrCopyOfRange[i] = bArrCopyOfRange[i2];
                bArrCopyOfRange[i2] = b2;
                i += 2;
            }
        }
        return EncodingUtils.getString(bArrCopyOfRange, str);
    }

    public static String getEncodingString(String str, String str2) {
        StringBuilder sb = new StringBuilder();
        boolean z = Integer.parseInt(str2, 16) == 16;
        int length = str.length() - (str.length() % 4);
        for (int i = 0; i < length; i += 4) {
            StringBuilder sb2 = new StringBuilder();
            if (i + 3 < length) {
                sb.append((char) Integer.parseInt(str.substring(i + 2, i + 4), 16));
            }
            if (i + 1 < length) {
                sb.append((char) Integer.parseInt(str.substring(i, i + 2), 16));
            }
            sb.append(sb2.toString());
        }
        return EncodingUtils.getString(sb.toString().getBytes(), z ? "UNICODE" : HTTP.ASCII);
    }

    public static int getSize(byte[] bArr) {
        if (bArr.length <= 8) {
            return 0;
        }
        return ByteConvertUtility.ByteToInt(Arrays.copyOfRange(bArr, 4, 8), 0, 4);
    }

    public static byte[] hexStringToAscii(String str) {
        int length = str.length();
        byte[] bArr = new byte[length / 2];
        for (int i = 0; i < length; i += 2) {
            bArr[i / 2] = (byte) ((Character.digit(str.charAt(i), 16) << 4) + Character.digit(str.charAt(i + 1), 16));
        }
        return bArr;
    }

    public static String printHexString(byte[] bArr) {
        StringBuffer stringBuffer = new StringBuffer();
        for (byte b : bArr) {
            stringBuffer.append(String.format("%02x", Integer.valueOf(b & UByte.MAX_VALUE)));
        }
        return stringBuffer.toString();
    }

    public static String printHexString(byte[] bArr, int i, int i2) {
        StringBuffer stringBuffer = new StringBuffer();
        if (i + i2 > bArr.length) {
            i2 = bArr.length - i;
        }
        int i3 = i2 + i;
        while (i < i3) {
            stringBuffer.append(String.format("%02x", Integer.valueOf(bArr[i] & UByte.MAX_VALUE)));
            i++;
        }
        return stringBuffer.toString();
    }
}
