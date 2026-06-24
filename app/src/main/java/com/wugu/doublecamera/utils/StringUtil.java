package com.wugu.doublecamera.utils;

import android.text.TextUtils;
import com.tom_roush.pdfbox.pdmodel.documentinterchange.taggedpdf.PDLayoutAttributeObject;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;
import java.util.regex.Pattern;
import kotlin.UByte;
import org.eclipse.paho.client.mqttv3.MqttTopic;

public class StringUtil {
    public static String getPriceStr(int i) {
        String str = new DecimalFormat("0.00").format(((double) i) / 100.0d);
        if (str.endsWith(".00") || str.endsWith(",00")) {
            return str.substring(0, str.length() - 3);
        }
        return str.endsWith(PDLayoutAttributeObject.GLYPH_ORIENTATION_VERTICAL_ZERO_DEGREES) ? str.substring(0, str.length() - 1) : str;
    }

    public static String getRandomNumber(int i) {
        char[] cArr = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i2 = 0; i2 < i; i2++) {
            sb.append(cArr[random.nextInt(10)]);
        }
        return sb.toString();
    }

    private static boolean isNumeric(String str) {
        return Pattern.compile("[0-9]*").matcher(str).matches();
    }

    public static int strToInt(String str, int i) {
        return (TextUtils.isEmpty(str) || !str.matches("\\d+")) ? i : Integer.parseInt(str);
    }

    public static String bytesToHex(byte[] bArr) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < bArr.length; i++) {
            String hexString = Integer.toHexString(bArr[i] & UByte.MAX_VALUE);
            if (hexString.length() == 1) {
                sb.append('0');
            }
            sb.append(hexString);
            if (i < bArr.length - 1) {
                sb.append(" ");
            }
        }
        return sb.toString();
    }

    public static String getBcdDateString(byte[] bArr) {
        if (bArr == null || bArr.length != 4) {
            return "----/--/--";
        }
        return Integer.toHexString(bArr[0] & UByte.MAX_VALUE) + Integer.toHexString(bArr[1] & UByte.MAX_VALUE) + MqttTopic.TOPIC_LEVEL_SEPARATOR + Integer.toHexString(bArr[2] & UByte.MAX_VALUE) + MqttTopic.TOPIC_LEVEL_SEPARATOR + Integer.toHexString(bArr[3] & UByte.MAX_VALUE);
    }

    public static String getSuffix(String str) {
        int iLastIndexOf;
        return (TextUtils.isEmpty(str) || (iLastIndexOf = str.lastIndexOf(46)) == -1) ? "" : str.substring(iLastIndexOf);
    }

    public static String getSha256(String str, String str2) {
        try {
            StringBuilder sb = new StringBuilder(new BigInteger(1, MessageDigest.getInstance("SHA-256").digest((str + str2).getBytes())).toString(16));
            while (sb.length() < 32) {
                sb.insert(0, '0');
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException unused) {
            return str;
        }
    }

    public static String getMd5(String str) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(str.getBytes());
            StringBuilder sb = new StringBuilder(new BigInteger(1, messageDigest.digest()).toString(16));
            while (sb.length() < 32) {
                sb.insert(0, PDLayoutAttributeObject.GLYPH_ORIENTATION_VERTICAL_ZERO_DEGREES);
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException unused) {
            return str;
        }
    }

    public static String getFileNameFromPath(String str) {
        int iLastIndexOf = str.lastIndexOf(47);
        if (iLastIndexOf == -1) {
            iLastIndexOf = str.lastIndexOf(92);
        }
        return iLastIndexOf != -1 ? str.substring(iLastIndexOf + 1) : str;
    }

    public static String[] getExtraNames(String str, String str2) {
        String[] strArrSplit = str.split(",");
        String[] strArrSplit2 = str2.split(",");
        HashSet hashSet = new HashSet(Arrays.asList(strArrSplit));
        hashSet.removeAll(new HashSet(Arrays.asList(strArrSplit2)));
        return (String[]) hashSet.toArray(new String[0]);
    }

    public static String getRemarkValueByKey(String str, String str2) {
        String str3;
        int iIndexOf;
        if (str == null || str2 == null || str.isEmpty() || str2.isEmpty() || (iIndexOf = str.indexOf((str3 = str2 + ":"))) == -1) {
            return null;
        }
        int length = iIndexOf + str3.length();
        int iIndexOf2 = str.indexOf(",", length);
        if (iIndexOf2 == -1) {
            iIndexOf2 = str.length();
        }
        return str.substring(length, iIndexOf2);
    }
}
