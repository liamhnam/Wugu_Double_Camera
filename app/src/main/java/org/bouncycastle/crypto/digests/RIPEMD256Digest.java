package org.bouncycastle.crypto.digests;

import kotlin.UByte;
import org.bouncycastle.util.Memoable;

public class RIPEMD256Digest extends GeneralDigest {
    private static final int DIGEST_LENGTH = 32;

    private int f3032H0;

    private int f3033H1;

    private int f3034H2;

    private int f3035H3;

    private int f3036H4;

    private int f3037H5;

    private int f3038H6;

    private int f3039H7;

    private int[] f3040X;
    private int xOff;

    public RIPEMD256Digest() {
        this.f3040X = new int[16];
        reset();
    }

    public RIPEMD256Digest(RIPEMD256Digest rIPEMD256Digest) {
        super(rIPEMD256Digest);
        this.f3040X = new int[16];
        copyIn(rIPEMD256Digest);
    }

    private int m1378F1(int i, int i2, int i3, int i4, int i5, int i6) {
        return m1382RL(i + m1383f1(i2, i3, i4) + i5, i6);
    }

    private int m1379F2(int i, int i2, int i3, int i4, int i5, int i6) {
        return m1382RL(i + m1384f2(i2, i3, i4) + i5 + 1518500249, i6);
    }

    private int m1380F3(int i, int i2, int i3, int i4, int i5, int i6) {
        return m1382RL(i + m1385f3(i2, i3, i4) + i5 + 1859775393, i6);
    }

    private int m1381F4(int i, int i2, int i3, int i4, int i5, int i6) {
        return m1382RL(((i + m1386f4(i2, i3, i4)) + i5) - 1894007588, i6);
    }

    private int FF1(int i, int i2, int i3, int i4, int i5, int i6) {
        return m1382RL(i + m1383f1(i2, i3, i4) + i5, i6);
    }

    private int FF2(int i, int i2, int i3, int i4, int i5, int i6) {
        return m1382RL(i + m1384f2(i2, i3, i4) + i5 + 1836072691, i6);
    }

    private int FF3(int i, int i2, int i3, int i4, int i5, int i6) {
        return m1382RL(i + m1385f3(i2, i3, i4) + i5 + 1548603684, i6);
    }

    private int FF4(int i, int i2, int i3, int i4, int i5, int i6) {
        return m1382RL(i + m1386f4(i2, i3, i4) + i5 + 1352829926, i6);
    }

    private int m1382RL(int i, int i2) {
        return (i >>> (32 - i2)) | (i << i2);
    }

    private void copyIn(RIPEMD256Digest rIPEMD256Digest) {
        super.copyIn((GeneralDigest) rIPEMD256Digest);
        this.f3032H0 = rIPEMD256Digest.f3032H0;
        this.f3033H1 = rIPEMD256Digest.f3033H1;
        this.f3034H2 = rIPEMD256Digest.f3034H2;
        this.f3035H3 = rIPEMD256Digest.f3035H3;
        this.f3036H4 = rIPEMD256Digest.f3036H4;
        this.f3037H5 = rIPEMD256Digest.f3037H5;
        this.f3038H6 = rIPEMD256Digest.f3038H6;
        this.f3039H7 = rIPEMD256Digest.f3039H7;
        int[] iArr = rIPEMD256Digest.f3040X;
        System.arraycopy(iArr, 0, this.f3040X, 0, iArr.length);
        this.xOff = rIPEMD256Digest.xOff;
    }

    private int m1383f1(int i, int i2, int i3) {
        return (i ^ i2) ^ i3;
    }

    private int m1384f2(int i, int i2, int i3) {
        return ((~i) & i3) | (i2 & i);
    }

    private int m1385f3(int i, int i2, int i3) {
        return (i | (~i2)) ^ i3;
    }

    private int m1386f4(int i, int i2, int i3) {
        return (i & i3) | (i2 & (~i3));
    }

    private void unpackWord(int i, byte[] bArr, int i2) {
        bArr[i2] = (byte) i;
        bArr[i2 + 1] = (byte) (i >>> 8);
        bArr[i2 + 2] = (byte) (i >>> 16);
        bArr[i2 + 3] = (byte) (i >>> 24);
    }

    @Override
    public Memoable copy() {
        return new RIPEMD256Digest(this);
    }

    @Override
    public int doFinal(byte[] bArr, int i) {
        finish();
        unpackWord(this.f3032H0, bArr, i);
        unpackWord(this.f3033H1, bArr, i + 4);
        unpackWord(this.f3034H2, bArr, i + 8);
        unpackWord(this.f3035H3, bArr, i + 12);
        unpackWord(this.f3036H4, bArr, i + 16);
        unpackWord(this.f3037H5, bArr, i + 20);
        unpackWord(this.f3038H6, bArr, i + 24);
        unpackWord(this.f3039H7, bArr, i + 28);
        reset();
        return 32;
    }

    @Override
    public String getAlgorithmName() {
        return "RIPEMD256";
    }

    @Override
    public int getDigestSize() {
        return 32;
    }

    @Override
    protected void processBlock() {
        int i = this.f3032H0;
        int i2 = this.f3033H1;
        int i3 = this.f3034H2;
        int i4 = this.f3035H3;
        int i5 = this.f3036H4;
        int i6 = this.f3037H5;
        int i7 = this.f3038H6;
        int i8 = this.f3039H7;
        int iM1378F1 = m1378F1(i, i2, i3, i4, this.f3040X[0], 11);
        int iM1378F12 = m1378F1(i4, iM1378F1, i2, i3, this.f3040X[1], 14);
        int iM1378F13 = m1378F1(i3, iM1378F12, iM1378F1, i2, this.f3040X[2], 15);
        int iM1378F14 = m1378F1(i2, iM1378F13, iM1378F12, iM1378F1, this.f3040X[3], 12);
        int iM1378F15 = m1378F1(iM1378F1, iM1378F14, iM1378F13, iM1378F12, this.f3040X[4], 5);
        int iM1378F16 = m1378F1(iM1378F12, iM1378F15, iM1378F14, iM1378F13, this.f3040X[5], 8);
        int iM1378F17 = m1378F1(iM1378F13, iM1378F16, iM1378F15, iM1378F14, this.f3040X[6], 7);
        int iM1378F18 = m1378F1(iM1378F14, iM1378F17, iM1378F16, iM1378F15, this.f3040X[7], 9);
        int iM1378F19 = m1378F1(iM1378F15, iM1378F18, iM1378F17, iM1378F16, this.f3040X[8], 11);
        int iM1378F110 = m1378F1(iM1378F16, iM1378F19, iM1378F18, iM1378F17, this.f3040X[9], 13);
        int iM1378F111 = m1378F1(iM1378F17, iM1378F110, iM1378F19, iM1378F18, this.f3040X[10], 14);
        int iM1378F112 = m1378F1(iM1378F18, iM1378F111, iM1378F110, iM1378F19, this.f3040X[11], 15);
        int iM1378F113 = m1378F1(iM1378F19, iM1378F112, iM1378F111, iM1378F110, this.f3040X[12], 6);
        int iM1378F114 = m1378F1(iM1378F110, iM1378F113, iM1378F112, iM1378F111, this.f3040X[13], 7);
        int iM1378F115 = m1378F1(iM1378F111, iM1378F114, iM1378F113, iM1378F112, this.f3040X[14], 9);
        int iM1378F116 = m1378F1(iM1378F112, iM1378F115, iM1378F114, iM1378F113, this.f3040X[15], 8);
        int iFF4 = FF4(i5, i6, i7, i8, this.f3040X[5], 8);
        int iFF42 = FF4(i8, iFF4, i6, i7, this.f3040X[14], 9);
        int iFF43 = FF4(i7, iFF42, iFF4, i6, this.f3040X[7], 9);
        int iFF44 = FF4(i6, iFF43, iFF42, iFF4, this.f3040X[0], 11);
        int iFF45 = FF4(iFF4, iFF44, iFF43, iFF42, this.f3040X[9], 13);
        int iFF46 = FF4(iFF42, iFF45, iFF44, iFF43, this.f3040X[2], 15);
        int iFF47 = FF4(iFF43, iFF46, iFF45, iFF44, this.f3040X[11], 15);
        int iFF48 = FF4(iFF44, iFF47, iFF46, iFF45, this.f3040X[4], 5);
        int iFF49 = FF4(iFF45, iFF48, iFF47, iFF46, this.f3040X[13], 7);
        int iFF410 = FF4(iFF46, iFF49, iFF48, iFF47, this.f3040X[6], 7);
        int iFF411 = FF4(iFF47, iFF410, iFF49, iFF48, this.f3040X[15], 8);
        int iFF412 = FF4(iFF48, iFF411, iFF410, iFF49, this.f3040X[8], 11);
        int iFF413 = FF4(iFF49, iFF412, iFF411, iFF410, this.f3040X[1], 14);
        int iFF414 = FF4(iFF410, iFF413, iFF412, iFF411, this.f3040X[10], 14);
        int iFF415 = FF4(iFF411, iFF414, iFF413, iFF412, this.f3040X[3], 12);
        int iFF416 = FF4(iFF412, iFF415, iFF414, iFF413, this.f3040X[12], 6);
        int iM1379F2 = m1379F2(iFF413, iM1378F116, iM1378F115, iM1378F114, this.f3040X[7], 7);
        int iM1379F22 = m1379F2(iM1378F114, iM1379F2, iM1378F116, iM1378F115, this.f3040X[4], 6);
        int iM1379F23 = m1379F2(iM1378F115, iM1379F22, iM1379F2, iM1378F116, this.f3040X[13], 8);
        int iM1379F24 = m1379F2(iM1378F116, iM1379F23, iM1379F22, iM1379F2, this.f3040X[1], 13);
        int iM1379F25 = m1379F2(iM1379F2, iM1379F24, iM1379F23, iM1379F22, this.f3040X[10], 11);
        int iM1379F26 = m1379F2(iM1379F22, iM1379F25, iM1379F24, iM1379F23, this.f3040X[6], 9);
        int iM1379F27 = m1379F2(iM1379F23, iM1379F26, iM1379F25, iM1379F24, this.f3040X[15], 7);
        int iM1379F28 = m1379F2(iM1379F24, iM1379F27, iM1379F26, iM1379F25, this.f3040X[3], 15);
        int iM1379F29 = m1379F2(iM1379F25, iM1379F28, iM1379F27, iM1379F26, this.f3040X[12], 7);
        int iM1379F210 = m1379F2(iM1379F26, iM1379F29, iM1379F28, iM1379F27, this.f3040X[0], 12);
        int iM1379F211 = m1379F2(iM1379F27, iM1379F210, iM1379F29, iM1379F28, this.f3040X[9], 15);
        int iM1379F212 = m1379F2(iM1379F28, iM1379F211, iM1379F210, iM1379F29, this.f3040X[5], 9);
        int iM1379F213 = m1379F2(iM1379F29, iM1379F212, iM1379F211, iM1379F210, this.f3040X[2], 11);
        int iM1379F214 = m1379F2(iM1379F210, iM1379F213, iM1379F212, iM1379F211, this.f3040X[14], 7);
        int iM1379F215 = m1379F2(iM1379F211, iM1379F214, iM1379F213, iM1379F212, this.f3040X[11], 13);
        int iM1379F216 = m1379F2(iM1379F212, iM1379F215, iM1379F214, iM1379F213, this.f3040X[8], 12);
        int iFF3 = FF3(iM1378F113, iFF416, iFF415, iFF414, this.f3040X[6], 9);
        int iFF32 = FF3(iFF414, iFF3, iFF416, iFF415, this.f3040X[11], 13);
        int iFF33 = FF3(iFF415, iFF32, iFF3, iFF416, this.f3040X[3], 15);
        int iFF34 = FF3(iFF416, iFF33, iFF32, iFF3, this.f3040X[7], 7);
        int iFF35 = FF3(iFF3, iFF34, iFF33, iFF32, this.f3040X[0], 12);
        int iFF36 = FF3(iFF32, iFF35, iFF34, iFF33, this.f3040X[13], 8);
        int iFF37 = FF3(iFF33, iFF36, iFF35, iFF34, this.f3040X[5], 9);
        int iFF38 = FF3(iFF34, iFF37, iFF36, iFF35, this.f3040X[10], 11);
        int iFF39 = FF3(iFF35, iFF38, iFF37, iFF36, this.f3040X[14], 7);
        int iFF310 = FF3(iFF36, iFF39, iFF38, iFF37, this.f3040X[15], 7);
        int iFF311 = FF3(iFF37, iFF310, iFF39, iFF38, this.f3040X[8], 12);
        int iFF312 = FF3(iFF38, iFF311, iFF310, iFF39, this.f3040X[12], 7);
        int iFF313 = FF3(iFF39, iFF312, iFF311, iFF310, this.f3040X[4], 6);
        int iFF314 = FF3(iFF310, iFF313, iFF312, iFF311, this.f3040X[9], 15);
        int iFF315 = FF3(iFF311, iFF314, iFF313, iFF312, this.f3040X[1], 13);
        int iFF316 = FF3(iFF312, iFF315, iFF314, iFF313, this.f3040X[2], 11);
        int iM1380F3 = m1380F3(iM1379F213, iFF316, iM1379F215, iM1379F214, this.f3040X[3], 11);
        int iM1380F32 = m1380F3(iM1379F214, iM1380F3, iFF316, iM1379F215, this.f3040X[10], 13);
        int iM1380F33 = m1380F3(iM1379F215, iM1380F32, iM1380F3, iFF316, this.f3040X[14], 6);
        int iM1380F34 = m1380F3(iFF316, iM1380F33, iM1380F32, iM1380F3, this.f3040X[4], 7);
        int iM1380F35 = m1380F3(iM1380F3, iM1380F34, iM1380F33, iM1380F32, this.f3040X[9], 14);
        int iM1380F36 = m1380F3(iM1380F32, iM1380F35, iM1380F34, iM1380F33, this.f3040X[15], 9);
        int iM1380F37 = m1380F3(iM1380F33, iM1380F36, iM1380F35, iM1380F34, this.f3040X[8], 13);
        int iM1380F38 = m1380F3(iM1380F34, iM1380F37, iM1380F36, iM1380F35, this.f3040X[1], 15);
        int iM1380F39 = m1380F3(iM1380F35, iM1380F38, iM1380F37, iM1380F36, this.f3040X[2], 14);
        int iM1380F310 = m1380F3(iM1380F36, iM1380F39, iM1380F38, iM1380F37, this.f3040X[7], 8);
        int iM1380F311 = m1380F3(iM1380F37, iM1380F310, iM1380F39, iM1380F38, this.f3040X[0], 13);
        int iM1380F312 = m1380F3(iM1380F38, iM1380F311, iM1380F310, iM1380F39, this.f3040X[6], 6);
        int iM1380F313 = m1380F3(iM1380F39, iM1380F312, iM1380F311, iM1380F310, this.f3040X[13], 5);
        int iM1380F314 = m1380F3(iM1380F310, iM1380F313, iM1380F312, iM1380F311, this.f3040X[11], 12);
        int iM1380F315 = m1380F3(iM1380F311, iM1380F314, iM1380F313, iM1380F312, this.f3040X[5], 7);
        int iM1380F316 = m1380F3(iM1380F312, iM1380F315, iM1380F314, iM1380F313, this.f3040X[12], 5);
        int iFF2 = FF2(iFF313, iM1379F216, iFF315, iFF314, this.f3040X[15], 9);
        int iFF22 = FF2(iFF314, iFF2, iM1379F216, iFF315, this.f3040X[5], 7);
        int iFF23 = FF2(iFF315, iFF22, iFF2, iM1379F216, this.f3040X[1], 15);
        int iFF24 = FF2(iM1379F216, iFF23, iFF22, iFF2, this.f3040X[3], 11);
        int iFF25 = FF2(iFF2, iFF24, iFF23, iFF22, this.f3040X[7], 8);
        int iFF26 = FF2(iFF22, iFF25, iFF24, iFF23, this.f3040X[14], 6);
        int iFF27 = FF2(iFF23, iFF26, iFF25, iFF24, this.f3040X[6], 6);
        int iFF28 = FF2(iFF24, iFF27, iFF26, iFF25, this.f3040X[9], 14);
        int iFF29 = FF2(iFF25, iFF28, iFF27, iFF26, this.f3040X[11], 12);
        int iFF210 = FF2(iFF26, iFF29, iFF28, iFF27, this.f3040X[8], 13);
        int iFF211 = FF2(iFF27, iFF210, iFF29, iFF28, this.f3040X[12], 5);
        int iFF212 = FF2(iFF28, iFF211, iFF210, iFF29, this.f3040X[2], 14);
        int iFF213 = FF2(iFF29, iFF212, iFF211, iFF210, this.f3040X[10], 13);
        int iFF214 = FF2(iFF210, iFF213, iFF212, iFF211, this.f3040X[0], 13);
        int iFF215 = FF2(iFF211, iFF214, iFF213, iFF212, this.f3040X[4], 7);
        int iFF216 = FF2(iFF212, iFF215, iFF214, iFF213, this.f3040X[13], 5);
        int iM1381F4 = m1381F4(iM1380F313, iM1380F316, iFF215, iM1380F314, this.f3040X[1], 11);
        int iM1381F42 = m1381F4(iM1380F314, iM1381F4, iM1380F316, iFF215, this.f3040X[9], 12);
        int iM1381F43 = m1381F4(iFF215, iM1381F42, iM1381F4, iM1380F316, this.f3040X[11], 14);
        int iM1381F44 = m1381F4(iM1380F316, iM1381F43, iM1381F42, iM1381F4, this.f3040X[10], 15);
        int iM1381F45 = m1381F4(iM1381F4, iM1381F44, iM1381F43, iM1381F42, this.f3040X[0], 14);
        int iM1381F46 = m1381F4(iM1381F42, iM1381F45, iM1381F44, iM1381F43, this.f3040X[8], 15);
        int iM1381F47 = m1381F4(iM1381F43, iM1381F46, iM1381F45, iM1381F44, this.f3040X[12], 9);
        int iM1381F48 = m1381F4(iM1381F44, iM1381F47, iM1381F46, iM1381F45, this.f3040X[4], 8);
        int iM1381F49 = m1381F4(iM1381F45, iM1381F48, iM1381F47, iM1381F46, this.f3040X[13], 9);
        int iM1381F410 = m1381F4(iM1381F46, iM1381F49, iM1381F48, iM1381F47, this.f3040X[3], 14);
        int iM1381F411 = m1381F4(iM1381F47, iM1381F410, iM1381F49, iM1381F48, this.f3040X[7], 5);
        int iM1381F412 = m1381F4(iM1381F48, iM1381F411, iM1381F410, iM1381F49, this.f3040X[15], 6);
        int iM1381F413 = m1381F4(iM1381F49, iM1381F412, iM1381F411, iM1381F410, this.f3040X[14], 8);
        int iM1381F414 = m1381F4(iM1381F410, iM1381F413, iM1381F412, iM1381F411, this.f3040X[5], 6);
        int iM1381F415 = m1381F4(iM1381F411, iM1381F414, iM1381F413, iM1381F412, this.f3040X[6], 5);
        int iM1381F416 = m1381F4(iM1381F412, iM1381F415, iM1381F414, iM1381F413, this.f3040X[2], 12);
        int iFF1 = FF1(iFF213, iFF216, iM1380F315, iFF214, this.f3040X[8], 15);
        int iFF12 = FF1(iFF214, iFF1, iFF216, iM1380F315, this.f3040X[6], 5);
        int iFF13 = FF1(iM1380F315, iFF12, iFF1, iFF216, this.f3040X[4], 8);
        int iFF14 = FF1(iFF216, iFF13, iFF12, iFF1, this.f3040X[1], 11);
        int iFF15 = FF1(iFF1, iFF14, iFF13, iFF12, this.f3040X[3], 14);
        int iFF16 = FF1(iFF12, iFF15, iFF14, iFF13, this.f3040X[11], 14);
        int iFF17 = FF1(iFF13, iFF16, iFF15, iFF14, this.f3040X[15], 6);
        int iFF18 = FF1(iFF14, iFF17, iFF16, iFF15, this.f3040X[0], 14);
        int iFF19 = FF1(iFF15, iFF18, iFF17, iFF16, this.f3040X[5], 6);
        int iFF110 = FF1(iFF16, iFF19, iFF18, iFF17, this.f3040X[12], 9);
        int iFF111 = FF1(iFF17, iFF110, iFF19, iFF18, this.f3040X[2], 12);
        int iFF112 = FF1(iFF18, iFF111, iFF110, iFF19, this.f3040X[13], 9);
        int iFF113 = FF1(iFF19, iFF112, iFF111, iFF110, this.f3040X[9], 12);
        int iFF114 = FF1(iFF110, iFF113, iFF112, iFF111, this.f3040X[7], 5);
        int iFF115 = FF1(iFF111, iFF114, iFF113, iFF112, this.f3040X[10], 15);
        int iFF116 = FF1(iFF112, iFF115, iFF114, iFF113, this.f3040X[14], 8);
        this.f3032H0 += iM1381F413;
        this.f3033H1 += iM1381F416;
        this.f3034H2 += iM1381F415;
        this.f3035H3 += iFF114;
        this.f3036H4 += iFF113;
        this.f3037H5 += iFF116;
        this.f3038H6 += iFF115;
        this.f3039H7 += iM1381F414;
        this.xOff = 0;
        int i9 = 0;
        while (true) {
            int[] iArr = this.f3040X;
            if (i9 == iArr.length) {
                return;
            }
            iArr[i9] = 0;
            i9++;
        }
    }

    @Override
    protected void processLength(long j) {
        if (this.xOff > 14) {
            processBlock();
        }
        int[] iArr = this.f3040X;
        iArr[14] = (int) ((-1) & j);
        iArr[15] = (int) (j >>> 32);
    }

    @Override
    protected void processWord(byte[] bArr, int i) {
        int[] iArr = this.f3040X;
        int i2 = this.xOff;
        int i3 = i2 + 1;
        this.xOff = i3;
        iArr[i2] = ((bArr[i + 3] & UByte.MAX_VALUE) << 24) | (bArr[i] & UByte.MAX_VALUE) | ((bArr[i + 1] & UByte.MAX_VALUE) << 8) | ((bArr[i + 2] & UByte.MAX_VALUE) << 16);
        if (i3 == 16) {
            processBlock();
        }
    }

    @Override
    public void reset() {
        super.reset();
        this.f3032H0 = 1732584193;
        this.f3033H1 = -271733879;
        this.f3034H2 = -1732584194;
        this.f3035H3 = 271733878;
        this.f3036H4 = 1985229328;
        this.f3037H5 = -19088744;
        this.f3038H6 = -1985229329;
        this.f3039H7 = 19088743;
        this.xOff = 0;
        int i = 0;
        while (true) {
            int[] iArr = this.f3040X;
            if (i == iArr.length) {
                return;
            }
            iArr[i] = 0;
            i++;
        }
    }

    @Override
    public void reset(Memoable memoable) {
        copyIn((RIPEMD256Digest) memoable);
    }
}
