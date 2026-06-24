package com.wugu.doublecamera.utils;

import android.util.Base64;
import com.brother.sdk.common.socket.devicemanagement.snmp.westhawk.SnmpConstants;
import java.io.ByteArrayOutputStream;
import java.nio.charset.StandardCharsets;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import kotlin.UByte;
import okio.Utf8;

public class AesUtil {

    private static final byte[] f2457Iv = {115, 44, SnmpConstants.INFORM_REQ_MSG, 94, 87, -100, 72, -40, 40, 84, -32, Utf8.REPLACEMENT_BYTE, 94, 72, -71, 114};

    public static String encrypt(String str, String str2) {
        try {
            SecretKeySpec secretKeySpec = new SecretKeySpec(str2.getBytes(StandardCharsets.UTF_8), "AES");
            IvParameterSpec ivParameterSpec = new IvParameterSpec(f2457Iv);
            Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
            int blockSize = cipher.getBlockSize();
            byte[] bytes = str.getBytes();
            int length = bytes.length;
            if (length % blockSize != 0) {
                length += blockSize - (length % blockSize);
            }
            byte[] bArr = new byte[length];
            System.arraycopy(bytes, 0, bArr, 0, bytes.length);
            cipher.init(1, secretKeySpec, ivParameterSpec);
            return Base64.encodeToString(cipher.doFinal(bArr), 2);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String decrypt(String str, String str2) {
        try {
            SecretKeySpec secretKeySpec = new SecretKeySpec(str2.getBytes(StandardCharsets.UTF_8), "AES");
            IvParameterSpec ivParameterSpec = new IvParameterSpec(f2457Iv);
            Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
            cipher.init(2, secretKeySpec, ivParameterSpec);
            try {
                return new String(filterNonUtf8Bytes(cipher.doFinal(Base64.decode(str, 2))));
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static byte[] filterNonUtf8Bytes(byte[] bArr) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        int i = 0;
        while (i < bArr.length) {
            int i2 = i + 1;
            int i3 = bArr[i] & UByte.MAX_VALUE;
            if (i3 > 0 && i3 <= 127) {
                byteArrayOutputStream.write(i3);
            } else if (i3 < 194 || i3 > 223) {
                if (i3 >= 224 && i3 <= 239) {
                    int i4 = i2 + 1;
                    if (i4 < bArr.length) {
                        int i5 = bArr[i2] & UByte.MAX_VALUE;
                        int i6 = i4 + 1;
                        int i7 = bArr[i4] & UByte.MAX_VALUE;
                        if (i5 >= 128 && i5 <= 191 && i7 >= 128 && i7 <= 191) {
                            byteArrayOutputStream.write(i3);
                            byteArrayOutputStream.write(i5);
                            byteArrayOutputStream.write(i7);
                        }
                        i = i6;
                    }
                } else if (i3 >= 240 && i3 <= 244 && i2 + 2 < bArr.length) {
                    int i8 = i2 + 1;
                    int i9 = bArr[i2] & UByte.MAX_VALUE;
                    int i10 = i8 + 1;
                    int i11 = bArr[i8] & UByte.MAX_VALUE;
                    int i12 = i10 + 1;
                    int i13 = bArr[i10] & UByte.MAX_VALUE;
                    if (i9 >= 128 && i9 <= 191 && i11 >= 128 && i11 <= 191 && i13 >= 128 && i13 <= 191) {
                        byteArrayOutputStream.write(i3);
                        byteArrayOutputStream.write(i9);
                        byteArrayOutputStream.write(i11);
                        byteArrayOutputStream.write(i13);
                    }
                    i = i12;
                }
            } else if (i2 < bArr.length) {
                int i14 = i2 + 1;
                int i15 = bArr[i2] & UByte.MAX_VALUE;
                if (i15 >= 128 && i15 <= 191) {
                    byteArrayOutputStream.write(i3);
                    byteArrayOutputStream.write(i15);
                }
                i = i14;
            }
            i = i2;
        }
        return byteArrayOutputStream.toByteArray();
    }
}
