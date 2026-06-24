package com.wugu.doublecamera.utils;

import android.util.Base64;
import android.util.Log;
import com.google.android.exoplayer2.metadata.icy.IcyHeaders;
import com.google.gson.Gson;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

public class WuJieAiUtil {
    private static final String ALGORITHM_RSA = "RSA";
    private static final String ALGORITHM_SIGNATURE = "SHA256withRSA";

    private static class SignContentDTO {
        public String appId;
        public Long timestamp;

        private SignContentDTO() {
        }
    }

    private static class AuthDTO {
        public String appId;
        public String original;
        public String secretKeyVersion;
        public String sign;

        private AuthDTO() {
        }
    }

    public static String getAuth() {
        long jCurrentTimeMillis = System.currentTimeMillis() / 1000;
        SignContentDTO signContentDTO = new SignContentDTO();
        signContentDTO.appId = "wjmxhk6o1bxp4k7fkm";
        signContentDTO.timestamp = Long.valueOf(jCurrentTimeMillis);
        String json = new Gson().toJson(signContentDTO);
        try {
            String strReplaceAll = signByPrivateKey("MIIBVQIBADANBgkqhkiG9w0BAQEFAASCAT8wggE7AgEAAkEApsCF3IvXx68oLwgpNZ5RtL+u/tPEOJwDRQAny6JD9XJvuA7wA1wBEUVYde+uiPBRKekkS0/nCjxFP14x0UYxmwIDAQABAkBEbiRg5fiGpyA/8MLskIxqNvFq/N989bU3x0ENhYkK+6Ij7fgBaGE7PCKJ47uilZblNqoqDrZ7Jbtn/El6Pq59AiEA1e83KroVK+nsHKsKHmKwVrzHqgJxBm45Rt4JCWgX3s8CIQDHiktrHLwyUkdwyGU/EPpxytdwFVGSgVkNLzcJTuwTdQIhAKKaU3/yN61M+t0/U02NDSKgEdIyUN3mYTpwAMZZi+sZAiEAxfpw2dWo5ES+nSg3TFuMIVsgZhTA2pt7v5kZAiNMGAECIHBJqND0Ski86vYDWrklKjHl2s/mVzlTbwg9e73fzePf", json).replaceAll("\n", "");
            AuthDTO authDTO = new AuthDTO();
            authDTO.sign = strReplaceAll;
            authDTO.secretKeyVersion = IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE;
            authDTO.appId = "wjmxhk6o1bxp4k7fkm";
            authDTO.original = json;
            return new Gson().toJson(authDTO);
        } catch (Exception unused) {
            return null;
        }
    }

    public static String signByPrivateKey(String str, String str2) throws Exception {
        return Base64.encodeToString(sign(generatePrivateByString(str), str2.getBytes()), 0);
    }

    private static byte[] sign(PrivateKey privateKey, byte[] bArr) throws Exception {
        Signature signature = Signature.getInstance(ALGORITHM_SIGNATURE);
        signature.initSign(privateKey);
        signature.update(bArr);
        return signature.sign();
    }

    public static boolean verifyByPublicKeyStr(String str, String str2, String str3) {
        try {
            return verify(generatePublicByString(str), str2.getBytes(), Base64.decode(str3, 0));
        } catch (Exception e) {
            Log.e("WuJieAiUtil", "verifyByPublicKeyStr error" + e);
            return false;
        }
    }

    private static boolean verify(PublicKey publicKey, byte[] bArr, byte[] bArr2) throws Exception {
        Signature signature = Signature.getInstance(ALGORITHM_SIGNATURE);
        signature.initVerify(publicKey);
        signature.update(bArr);
        return signature.verify(bArr2);
    }

    private static PublicKey generatePublicByString(String str) throws Exception {
        return generatePublic(Base64.decode(str, 0));
    }

    private static PrivateKey generatePrivateByString(String str) throws Exception {
        return generatePrivate(Base64.decode(str, 0));
    }

    private static PublicKey generatePublic(byte[] bArr) throws Exception {
        return KeyFactory.getInstance(ALGORITHM_RSA).generatePublic(new X509EncodedKeySpec(bArr));
    }

    private static PrivateKey generatePrivate(byte[] bArr) throws Exception {
        return KeyFactory.getInstance(ALGORITHM_RSA).generatePrivate(new PKCS8EncodedKeySpec(bArr));
    }
}
