package com.hiti.usb.utility;

import android.content.Context;
import android.util.Base64;
import com.tom_roush.pdfbox.pdmodel.documentinterchange.taggedpdf.PDLayoutAttributeObject;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Pattern;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import kotlin.UByte;
import org.apache.log4j.helpers.DateLayout;
import org.eclipse.paho.client.mqttv3.MqttTopic;
import p000a.p001a.p002a.p006d.C0016g;
import p000a.p001a.p002a.p006d.C0017h;

public class EncryptAndDecryptAES {
    private static byte[] DecryptAES(byte[] bArr, byte[] bArr2, byte[] bArr3, String str) {
        try {
            IvParameterSpec ivParameterSpec = new IvParameterSpec(bArr);
            SecretKeySpec secretKeySpec = new SecretKeySpec(bArr2, "AES");
            Cipher cipher = Cipher.getInstance(str);
            cipher.init(2, secretKeySpec, ivParameterSpec);
            return cipher.doFinal(bArr3);
        } catch (Exception unused) {
            return null;
        }
    }

    public static String DecryptStr(String str, String str2, String str3) {
        try {
            return new String(DecryptAES(str2.getBytes("UTF-8"), str3.getBytes("UTF-8"), Base64.decode(str.getBytes("UTF-8"), 0), "AES/CBC/PKCS5Padding"), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        } catch (IllegalArgumentException e2) {
            e2.printStackTrace();
            return null;
        } catch (NullPointerException e3) {
            e3.printStackTrace();
            return null;
        }
    }

    public static String DecryptStrNoPadding(String str, String str2, String str3) {
        String str4 = null;
        try {
            byte[] bArrDecryptAES = DecryptAES(str2.getBytes("UTF-8"), str3.getBytes("UTF-8"), Base64.decode(str.getBytes("UTF-8"), 0), "AES/CBC/NoPadding");
            String str5 = new String(bArrDecryptAES, "UTF-8");
            int i = 0;
            for (int i2 = 0; i2 < bArrDecryptAES.length && bArrDecryptAES[i2] != 0; i2++) {
                try {
                    i++;
                } catch (UnsupportedEncodingException e) {
                    e = e;
                    str4 = str5;
                    e.printStackTrace();
                    return str4;
                } catch (IllegalArgumentException e2) {
                    e = e2;
                    str4 = str5;
                    e.printStackTrace();
                    return str4;
                }
            }
            return str5.substring(0, i);
        } catch (UnsupportedEncodingException e3) {
            e = e3;
        } catch (IllegalArgumentException e4) {
            e = e4;
        }
    }

    private static byte[] EncryptAES(byte[] bArr, byte[] bArr2, byte[] bArr3, String str) {
        try {
            IvParameterSpec ivParameterSpec = new IvParameterSpec(bArr);
            SecretKeySpec secretKeySpec = new SecretKeySpec(bArr2, "AES");
            Cipher cipher = Cipher.getInstance(str);
            cipher.init(1, secretKeySpec, ivParameterSpec);
            return cipher.doFinal(bArr3);
        } catch (Exception unused) {
            return null;
        }
    }

    public static String EncryptStr(String str, String str2, String str3) {
        try {
            return Base64.encodeToString(EncryptAES(str2.getBytes("UTF-8"), str3.getBytes("UTF-8"), str.getBytes("UTF-8"), "AES/CBC/PKCS5Padding"), 0);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String EncryptStrNoPadding(String str, String str2, String str3) {
        try {
            return Base64.encodeToString(EncryptAES(str2.getBytes("UTF-8"), str3.getBytes("UTF-8"), PadString(str).getBytes("UTF-8"), "AES/CBC/NoPadding"), 0);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String MakeAESCount(Context context, int i, String str) {
        return DateLayout.NULL_DATE_FORMAT;
    }

    public static String MakeGoodString(String str) {
        char cCharAt = str.charAt(0);
        String strValueOf = String.valueOf(cCharAt);
        int i = (cCharAt % 4) + 1;
        for (int i2 = 0; i2 < i; i2++) {
            strValueOf = strValueOf + String.valueOf("AQgwBRhxCSiyDTjzEUk0FVl1GWm2HXn3IYo4JZp5Kaq6Lbr7Mcs8Ndt9OeuPfv".charAt((int) (Math.random() * ((double) 62))));
        }
        return strValueOf + str.substring(1, str.length());
    }

    public static String MakeIVFromIMEI(Context context) {
        String strGetIMEI = MobileInfo.GetIMEI(context);
        if (strGetIMEI.length() > 16) {
            strGetIMEI = strGetIMEI.substring(0, 16);
        }
        if (strGetIMEI.contains("\"")) {
            strGetIMEI.replace("\"", "");
        }
        if (strGetIMEI.contains("\n")) {
            strGetIMEI.replace("\n", "");
        }
        int length = strGetIMEI.length();
        for (int i = 0; i < 16 - length; i++) {
            strGetIMEI = strGetIMEI + PDLayoutAttributeObject.GLYPH_ORIENTATION_VERTICAL_ZERO_DEGREES;
        }
        return strGetIMEI;
    }

    public static String MakeIVFromUser(Context context) {
        C0017h c0017h = new C0017h(context);
        c0017h.m87d();
        if (c0017h.m86c() == 0) {
            return "0000000000000000";
        }
        C0016g c0016g = new C0016g(context);
        c0016g.m83h();
        String strReplaceAll = Pattern.compile("[.,\"\\?!:'@]").matcher((String) UserInfo.GetUP(context, c0016g.m82g()).first).replaceAll("");
        if (strReplaceAll.length() > 16) {
            strReplaceAll = strReplaceAll.substring(0, 16);
        }
        if (strReplaceAll.contains("\"")) {
            strReplaceAll.replace("\"", "");
        }
        if (strReplaceAll.contains("\n")) {
            strReplaceAll.replace("\n", "");
        }
        int length = strReplaceAll.length();
        for (int i = 0; i < 16 - length; i++) {
            strReplaceAll = strReplaceAll + PDLayoutAttributeObject.GLYPH_ORIENTATION_VERTICAL_ZERO_DEGREES;
        }
        return strReplaceAll;
    }

    public static String MakeMD5(String str) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(str.getBytes());
            byte[] bArrDigest = messageDigest.digest();
            StringBuffer stringBuffer = new StringBuffer();
            for (byte b : bArrDigest) {
                int i = b & UByte.MAX_VALUE;
                if (i < 16) {
                    stringBuffer.append(PDLayoutAttributeObject.GLYPH_ORIENTATION_VERTICAL_ZERO_DEGREES);
                }
                stringBuffer.append(Integer.toHexString(i));
            }
            return stringBuffer.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String OpenAESCount(Context context, String str, String str2) {
        if (str.length() == 0 || !str.substring(0, MobileInfo.GetIMEI(context).length()).equals(MobileInfo.GetIMEI(context))) {
            return "";
        }
        String strReplace = str.replace(MobileInfo.GetIMEI(context) + MqttTopic.MULTI_LEVEL_WILDCARD, "");
        String strSubstring = strReplace.substring(0, str2.length());
        return !strSubstring.equals(str2) ? "" : strReplace.substring(strSubstring.length());
    }

    public static String OpenAESCount(Context context, String str, String str2, String str3) {
        if (str.length() == 0) {
            return "";
        }
        String strReplace = DecryptStr(str, str2, str3).replace(MobileInfo.GetIMEI(context) + MqttTopic.MULTI_LEVEL_WILDCARD, "");
        return strReplace.substring(strReplace.substring(0, MobileInfo.GetTimeStamp().length()).length());
    }

    public static String OpenAESCount(Context context, String str, String str2, String str3, String str4) {
        if (str.length() == 0) {
            return "";
        }
        String strDecryptStr = DecryptStr(str, str3, str4);
        if (!strDecryptStr.substring(0, MobileInfo.GetIMEI(context).length()).equals(MobileInfo.GetIMEI(context))) {
            return "";
        }
        String strReplace = strDecryptStr.replace(MobileInfo.GetIMEI(context) + MqttTopic.MULTI_LEVEL_WILDCARD, "");
        String strSubstring = strReplace.substring(0, str2.length());
        return !strSubstring.equals(str2) ? "" : strReplace.substring(strSubstring.length());
    }

    public static String OpenAESCountGetTime(Context context, String str, String str2, String str3) {
        return str.length() == 0 ? "" : DecryptStr(str, str2, str3).replace(MobileInfo.GetIMEI(context) + MqttTopic.MULTI_LEVEL_WILDCARD, "").substring(0, MobileInfo.GetTimeStamp().length());
    }

    public static String OpenCount(Context context, String str) {
        str.length();
        return PDLayoutAttributeObject.GLYPH_ORIENTATION_VERTICAL_ZERO_DEGREES;
    }

    public static String OpenCount(Context context, String str, String str2, String str3) {
        String strDecryptStr;
        return (str.length() == 0 || (strDecryptStr = DecryptStr(str, str2, str3)) == null) ? PDLayoutAttributeObject.GLYPH_ORIENTATION_VERTICAL_ZERO_DEGREES : strDecryptStr;
    }

    private static String PadString(String str) {
        int length = 16 - (str.length() % 16);
        for (int i = 0; i < length; i++) {
            str = str + (char) 0;
        }
        return str;
    }

    public static String ReEncryptStr(Context context, String str, String str2, String str3, String str4, String str5) {
        if (str == null || str.length() == 0) {
            return "";
        }
        String strDecryptStr = DecryptStr(str, str2, str3);
        if (strDecryptStr != null) {
            EncryptStr(strDecryptStr, str4, str5);
        }
        return strDecryptStr == null ? "" : strDecryptStr;
    }

    public static String SendToServer(String str) {
        return str.replace(MqttTopic.SINGLE_LEVEL_WILDCARD, "%2b").replace(MqttTopic.TOPIC_LEVEL_SEPARATOR, "%2f");
    }

    public static String SendToURL(String str) {
        return str.replace(MqttTopic.SINGLE_LEVEL_WILDCARD, "-").replace(MqttTopic.TOPIC_LEVEL_SEPARATOR, "_");
    }
}
