package org.bouncycastle.crypto.digests;

import kotlin.UByte;
import org.bouncycastle.util.Memoable;

public class RIPEMD128Digest extends GeneralDigest {
    private static final int DIGEST_LENGTH = 16;

    private int f3021H0;

    private int f3022H1;

    private int f3023H2;

    private int f3024H3;

    private int[] f3025X;
    private int xOff;

    public RIPEMD128Digest() {
        this.f3025X = new int[16];
        reset();
    }

    public RIPEMD128Digest(RIPEMD128Digest rIPEMD128Digest) {
        super(rIPEMD128Digest);
        this.f3025X = new int[16];
        copyIn(rIPEMD128Digest);
    }

    private int m1363F1(int i, int i2, int i3, int i4, int i5, int i6) {
        return m1367RL(i + m1368f1(i2, i3, i4) + i5, i6);
    }

    private int m1364F2(int i, int i2, int i3, int i4, int i5, int i6) {
        return m1367RL(i + m1369f2(i2, i3, i4) + i5 + 1518500249, i6);
    }

    private int m1365F3(int i, int i2, int i3, int i4, int i5, int i6) {
        return m1367RL(i + m1370f3(i2, i3, i4) + i5 + 1859775393, i6);
    }

    private int m1366F4(int i, int i2, int i3, int i4, int i5, int i6) {
        return m1367RL(((i + m1371f4(i2, i3, i4)) + i5) - 1894007588, i6);
    }

    private int FF1(int i, int i2, int i3, int i4, int i5, int i6) {
        return m1367RL(i + m1368f1(i2, i3, i4) + i5, i6);
    }

    private int FF2(int i, int i2, int i3, int i4, int i5, int i6) {
        return m1367RL(i + m1369f2(i2, i3, i4) + i5 + 1836072691, i6);
    }

    private int FF3(int i, int i2, int i3, int i4, int i5, int i6) {
        return m1367RL(i + m1370f3(i2, i3, i4) + i5 + 1548603684, i6);
    }

    private int FF4(int i, int i2, int i3, int i4, int i5, int i6) {
        return m1367RL(i + m1371f4(i2, i3, i4) + i5 + 1352829926, i6);
    }

    private int m1367RL(int i, int i2) {
        return (i >>> (32 - i2)) | (i << i2);
    }

    private void copyIn(RIPEMD128Digest rIPEMD128Digest) {
        super.copyIn((GeneralDigest) rIPEMD128Digest);
        this.f3021H0 = rIPEMD128Digest.f3021H0;
        this.f3022H1 = rIPEMD128Digest.f3022H1;
        this.f3023H2 = rIPEMD128Digest.f3023H2;
        this.f3024H3 = rIPEMD128Digest.f3024H3;
        int[] iArr = rIPEMD128Digest.f3025X;
        System.arraycopy(iArr, 0, this.f3025X, 0, iArr.length);
        this.xOff = rIPEMD128Digest.xOff;
    }

    private int m1368f1(int i, int i2, int i3) {
        return (i ^ i2) ^ i3;
    }

    private int m1369f2(int i, int i2, int i3) {
        return ((~i) & i3) | (i2 & i);
    }

    private int m1370f3(int i, int i2, int i3) {
        return (i | (~i2)) ^ i3;
    }

    private int m1371f4(int i, int i2, int i3) {
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
        return new RIPEMD128Digest(this);
    }

    @Override
    public int doFinal(byte[] bArr, int i) {
        finish();
        unpackWord(this.f3021H0, bArr, i);
        unpackWord(this.f3022H1, bArr, i + 4);
        unpackWord(this.f3023H2, bArr, i + 8);
        unpackWord(this.f3024H3, bArr, i + 12);
        reset();
        return 16;
    }

    @Override
    public String getAlgorithmName() {
        return "RIPEMD128";
    }

    @Override
    public int getDigestSize() {
        return 16;
    }

    @Override
    protected void processBlock() {
        int i = this.f3021H0;
        int i2 = this.f3022H1;
        int i3 = this.f3023H2;
        int i4 = this.f3024H3;
        int iM1363F1 = m1363F1(i, i2, i3, i4, this.f3025X[0], 11);
        int iM1363F12 = m1363F1(i4, iM1363F1, i2, i3, this.f3025X[1], 14);
        int iM1363F13 = m1363F1(i3, iM1363F12, iM1363F1, i2, this.f3025X[2], 15);
        int iM1363F14 = m1363F1(i2, iM1363F13, iM1363F12, iM1363F1, this.f3025X[3], 12);
        int iM1363F15 = m1363F1(iM1363F1, iM1363F14, iM1363F13, iM1363F12, this.f3025X[4], 5);
        int iM1363F16 = m1363F1(iM1363F12, iM1363F15, iM1363F14, iM1363F13, this.f3025X[5], 8);
        int iM1363F17 = m1363F1(iM1363F13, iM1363F16, iM1363F15, iM1363F14, this.f3025X[6], 7);
        int iM1363F18 = m1363F1(iM1363F14, iM1363F17, iM1363F16, iM1363F15, this.f3025X[7], 9);
        int iM1363F19 = m1363F1(iM1363F15, iM1363F18, iM1363F17, iM1363F16, this.f3025X[8], 11);
        int iM1363F110 = m1363F1(iM1363F16, iM1363F19, iM1363F18, iM1363F17, this.f3025X[9], 13);
        int iM1363F111 = m1363F1(iM1363F17, iM1363F110, iM1363F19, iM1363F18, this.f3025X[10], 14);
        int iM1363F112 = m1363F1(iM1363F18, iM1363F111, iM1363F110, iM1363F19, this.f3025X[11], 15);
        int iM1363F113 = m1363F1(iM1363F19, iM1363F112, iM1363F111, iM1363F110, this.f3025X[12], 6);
        int iM1363F114 = m1363F1(iM1363F110, iM1363F113, iM1363F112, iM1363F111, this.f3025X[13], 7);
        int iM1363F115 = m1363F1(iM1363F111, iM1363F114, iM1363F113, iM1363F112, this.f3025X[14], 9);
        int iM1363F116 = m1363F1(iM1363F112, iM1363F115, iM1363F114, iM1363F113, this.f3025X[15], 8);
        int iM1364F2 = m1364F2(iM1363F113, iM1363F116, iM1363F115, iM1363F114, this.f3025X[7], 7);
        int iM1364F22 = m1364F2(iM1363F114, iM1364F2, iM1363F116, iM1363F115, this.f3025X[4], 6);
        int iM1364F23 = m1364F2(iM1363F115, iM1364F22, iM1364F2, iM1363F116, this.f3025X[13], 8);
        int iM1364F24 = m1364F2(iM1363F116, iM1364F23, iM1364F22, iM1364F2, this.f3025X[1], 13);
        int iM1364F25 = m1364F2(iM1364F2, iM1364F24, iM1364F23, iM1364F22, this.f3025X[10], 11);
        int iM1364F26 = m1364F2(iM1364F22, iM1364F25, iM1364F24, iM1364F23, this.f3025X[6], 9);
        int iM1364F27 = m1364F2(iM1364F23, iM1364F26, iM1364F25, iM1364F24, this.f3025X[15], 7);
        int iM1364F28 = m1364F2(iM1364F24, iM1364F27, iM1364F26, iM1364F25, this.f3025X[3], 15);
        int iM1364F29 = m1364F2(iM1364F25, iM1364F28, iM1364F27, iM1364F26, this.f3025X[12], 7);
        int iM1364F210 = m1364F2(iM1364F26, iM1364F29, iM1364F28, iM1364F27, this.f3025X[0], 12);
        int iM1364F211 = m1364F2(iM1364F27, iM1364F210, iM1364F29, iM1364F28, this.f3025X[9], 15);
        int iM1364F212 = m1364F2(iM1364F28, iM1364F211, iM1364F210, iM1364F29, this.f3025X[5], 9);
        int iM1364F213 = m1364F2(iM1364F29, iM1364F212, iM1364F211, iM1364F210, this.f3025X[2], 11);
        int iM1364F214 = m1364F2(iM1364F210, iM1364F213, iM1364F212, iM1364F211, this.f3025X[14], 7);
        int iM1364F215 = m1364F2(iM1364F211, iM1364F214, iM1364F213, iM1364F212, this.f3025X[11], 13);
        int iM1364F216 = m1364F2(iM1364F212, iM1364F215, iM1364F214, iM1364F213, this.f3025X[8], 12);
        int iM1365F3 = m1365F3(iM1364F213, iM1364F216, iM1364F215, iM1364F214, this.f3025X[3], 11);
        int iM1365F32 = m1365F3(iM1364F214, iM1365F3, iM1364F216, iM1364F215, this.f3025X[10], 13);
        int iM1365F33 = m1365F3(iM1364F215, iM1365F32, iM1365F3, iM1364F216, this.f3025X[14], 6);
        int iM1365F34 = m1365F3(iM1364F216, iM1365F33, iM1365F32, iM1365F3, this.f3025X[4], 7);
        int iM1365F35 = m1365F3(iM1365F3, iM1365F34, iM1365F33, iM1365F32, this.f3025X[9], 14);
        int iM1365F36 = m1365F3(iM1365F32, iM1365F35, iM1365F34, iM1365F33, this.f3025X[15], 9);
        int iM1365F37 = m1365F3(iM1365F33, iM1365F36, iM1365F35, iM1365F34, this.f3025X[8], 13);
        int iM1365F38 = m1365F3(iM1365F34, iM1365F37, iM1365F36, iM1365F35, this.f3025X[1], 15);
        int iM1365F39 = m1365F3(iM1365F35, iM1365F38, iM1365F37, iM1365F36, this.f3025X[2], 14);
        int iM1365F310 = m1365F3(iM1365F36, iM1365F39, iM1365F38, iM1365F37, this.f3025X[7], 8);
        int iM1365F311 = m1365F3(iM1365F37, iM1365F310, iM1365F39, iM1365F38, this.f3025X[0], 13);
        int iM1365F312 = m1365F3(iM1365F38, iM1365F311, iM1365F310, iM1365F39, this.f3025X[6], 6);
        int iM1365F313 = m1365F3(iM1365F39, iM1365F312, iM1365F311, iM1365F310, this.f3025X[13], 5);
        int iM1365F314 = m1365F3(iM1365F310, iM1365F313, iM1365F312, iM1365F311, this.f3025X[11], 12);
        int iM1365F315 = m1365F3(iM1365F311, iM1365F314, iM1365F313, iM1365F312, this.f3025X[5], 7);
        int iM1365F316 = m1365F3(iM1365F312, iM1365F315, iM1365F314, iM1365F313, this.f3025X[12], 5);
        int iM1366F4 = m1366F4(iM1365F313, iM1365F316, iM1365F315, iM1365F314, this.f3025X[1], 11);
        int iM1366F42 = m1366F4(iM1365F314, iM1366F4, iM1365F316, iM1365F315, this.f3025X[9], 12);
        int iM1366F43 = m1366F4(iM1365F315, iM1366F42, iM1366F4, iM1365F316, this.f3025X[11], 14);
        int iM1366F44 = m1366F4(iM1365F316, iM1366F43, iM1366F42, iM1366F4, this.f3025X[10], 15);
        int iM1366F45 = m1366F4(iM1366F4, iM1366F44, iM1366F43, iM1366F42, this.f3025X[0], 14);
        int iM1366F46 = m1366F4(iM1366F42, iM1366F45, iM1366F44, iM1366F43, this.f3025X[8], 15);
        int iM1366F47 = m1366F4(iM1366F43, iM1366F46, iM1366F45, iM1366F44, this.f3025X[12], 9);
        int iM1366F48 = m1366F4(iM1366F44, iM1366F47, iM1366F46, iM1366F45, this.f3025X[4], 8);
        int iM1366F49 = m1366F4(iM1366F45, iM1366F48, iM1366F47, iM1366F46, this.f3025X[13], 9);
        int iM1366F410 = m1366F4(iM1366F46, iM1366F49, iM1366F48, iM1366F47, this.f3025X[3], 14);
        int iM1366F411 = m1366F4(iM1366F47, iM1366F410, iM1366F49, iM1366F48, this.f3025X[7], 5);
        int iM1366F412 = m1366F4(iM1366F48, iM1366F411, iM1366F410, iM1366F49, this.f3025X[15], 6);
        int iM1366F413 = m1366F4(iM1366F49, iM1366F412, iM1366F411, iM1366F410, this.f3025X[14], 8);
        int iM1366F414 = m1366F4(iM1366F410, iM1366F413, iM1366F412, iM1366F411, this.f3025X[5], 6);
        int iM1366F415 = m1366F4(iM1366F411, iM1366F414, iM1366F413, iM1366F412, this.f3025X[6], 5);
        int iM1366F416 = m1366F4(iM1366F412, iM1366F415, iM1366F414, iM1366F413, this.f3025X[2], 12);
        int iFF4 = FF4(i, i2, i3, i4, this.f3025X[5], 8);
        int iFF42 = FF4(i4, iFF4, i2, i3, this.f3025X[14], 9);
        int iFF43 = FF4(i3, iFF42, iFF4, i2, this.f3025X[7], 9);
        int iFF44 = FF4(i2, iFF43, iFF42, iFF4, this.f3025X[0], 11);
        int iFF45 = FF4(iFF4, iFF44, iFF43, iFF42, this.f3025X[9], 13);
        int iFF46 = FF4(iFF42, iFF45, iFF44, iFF43, this.f3025X[2], 15);
        int iFF47 = FF4(iFF43, iFF46, iFF45, iFF44, this.f3025X[11], 15);
        int iFF48 = FF4(iFF44, iFF47, iFF46, iFF45, this.f3025X[4], 5);
        int iFF49 = FF4(iFF45, iFF48, iFF47, iFF46, this.f3025X[13], 7);
        int iFF410 = FF4(iFF46, iFF49, iFF48, iFF47, this.f3025X[6], 7);
        int iFF411 = FF4(iFF47, iFF410, iFF49, iFF48, this.f3025X[15], 8);
        int iFF412 = FF4(iFF48, iFF411, iFF410, iFF49, this.f3025X[8], 11);
        int iFF413 = FF4(iFF49, iFF412, iFF411, iFF410, this.f3025X[1], 14);
        int iFF414 = FF4(iFF410, iFF413, iFF412, iFF411, this.f3025X[10], 14);
        int iFF415 = FF4(iFF411, iFF414, iFF413, iFF412, this.f3025X[3], 12);
        int iFF416 = FF4(iFF412, iFF415, iFF414, iFF413, this.f3025X[12], 6);
        int iFF3 = FF3(iFF413, iFF416, iFF415, iFF414, this.f3025X[6], 9);
        int iFF32 = FF3(iFF414, iFF3, iFF416, iFF415, this.f3025X[11], 13);
        int iFF33 = FF3(iFF415, iFF32, iFF3, iFF416, this.f3025X[3], 15);
        int iFF34 = FF3(iFF416, iFF33, iFF32, iFF3, this.f3025X[7], 7);
        int iFF35 = FF3(iFF3, iFF34, iFF33, iFF32, this.f3025X[0], 12);
        int iFF36 = FF3(iFF32, iFF35, iFF34, iFF33, this.f3025X[13], 8);
        int iFF37 = FF3(iFF33, iFF36, iFF35, iFF34, this.f3025X[5], 9);
        int iFF38 = FF3(iFF34, iFF37, iFF36, iFF35, this.f3025X[10], 11);
        int iFF39 = FF3(iFF35, iFF38, iFF37, iFF36, this.f3025X[14], 7);
        int iFF310 = FF3(iFF36, iFF39, iFF38, iFF37, this.f3025X[15], 7);
        int iFF311 = FF3(iFF37, iFF310, iFF39, iFF38, this.f3025X[8], 12);
        int iFF312 = FF3(iFF38, iFF311, iFF310, iFF39, this.f3025X[12], 7);
        int iFF313 = FF3(iFF39, iFF312, iFF311, iFF310, this.f3025X[4], 6);
        int iFF314 = FF3(iFF310, iFF313, iFF312, iFF311, this.f3025X[9], 15);
        int iFF315 = FF3(iFF311, iFF314, iFF313, iFF312, this.f3025X[1], 13);
        int iFF316 = FF3(iFF312, iFF315, iFF314, iFF313, this.f3025X[2], 11);
        int iFF2 = FF2(iFF313, iFF316, iFF315, iFF314, this.f3025X[15], 9);
        int iFF22 = FF2(iFF314, iFF2, iFF316, iFF315, this.f3025X[5], 7);
        int iFF23 = FF2(iFF315, iFF22, iFF2, iFF316, this.f3025X[1], 15);
        int iFF24 = FF2(iFF316, iFF23, iFF22, iFF2, this.f3025X[3], 11);
        int iFF25 = FF2(iFF2, iFF24, iFF23, iFF22, this.f3025X[7], 8);
        int iFF26 = FF2(iFF22, iFF25, iFF24, iFF23, this.f3025X[14], 6);
        int iFF27 = FF2(iFF23, iFF26, iFF25, iFF24, this.f3025X[6], 6);
        int iFF28 = FF2(iFF24, iFF27, iFF26, iFF25, this.f3025X[9], 14);
        int iFF29 = FF2(iFF25, iFF28, iFF27, iFF26, this.f3025X[11], 12);
        int iFF210 = FF2(iFF26, iFF29, iFF28, iFF27, this.f3025X[8], 13);
        int iFF211 = FF2(iFF27, iFF210, iFF29, iFF28, this.f3025X[12], 5);
        int iFF212 = FF2(iFF28, iFF211, iFF210, iFF29, this.f3025X[2], 14);
        int iFF213 = FF2(iFF29, iFF212, iFF211, iFF210, this.f3025X[10], 13);
        int iFF214 = FF2(iFF210, iFF213, iFF212, iFF211, this.f3025X[0], 13);
        int iFF215 = FF2(iFF211, iFF214, iFF213, iFF212, this.f3025X[4], 7);
        int iFF216 = FF2(iFF212, iFF215, iFF214, iFF213, this.f3025X[13], 5);
        int iFF1 = FF1(iFF213, iFF216, iFF215, iFF214, this.f3025X[8], 15);
        int iFF12 = FF1(iFF214, iFF1, iFF216, iFF215, this.f3025X[6], 5);
        int iFF13 = FF1(iFF215, iFF12, iFF1, iFF216, this.f3025X[4], 8);
        int iFF14 = FF1(iFF216, iFF13, iFF12, iFF1, this.f3025X[1], 11);
        int iFF15 = FF1(iFF1, iFF14, iFF13, iFF12, this.f3025X[3], 14);
        int iFF16 = FF1(iFF12, iFF15, iFF14, iFF13, this.f3025X[11], 14);
        int iFF17 = FF1(iFF13, iFF16, iFF15, iFF14, this.f3025X[15], 6);
        int iFF18 = FF1(iFF14, iFF17, iFF16, iFF15, this.f3025X[0], 14);
        int iFF19 = FF1(iFF15, iFF18, iFF17, iFF16, this.f3025X[5], 6);
        int iFF110 = FF1(iFF16, iFF19, iFF18, iFF17, this.f3025X[12], 9);
        int iFF111 = FF1(iFF17, iFF110, iFF19, iFF18, this.f3025X[2], 12);
        int iFF112 = FF1(iFF18, iFF111, iFF110, iFF19, this.f3025X[13], 9);
        int iFF113 = FF1(iFF19, iFF112, iFF111, iFF110, this.f3025X[9], 12);
        int iFF114 = FF1(iFF110, iFF113, iFF112, iFF111, this.f3025X[7], 5);
        int iFF115 = FF1(iFF111, iFF114, iFF113, iFF112, this.f3025X[10], 15);
        int iFF116 = FF1(iFF112, iFF115, iFF114, iFF113, this.f3025X[14], 8);
        int i5 = iFF114 + iM1366F415 + this.f3022H1;
        this.f3022H1 = this.f3023H2 + iM1366F414 + iFF113;
        this.f3023H2 = this.f3024H3 + iM1366F413 + iFF116;
        this.f3024H3 = this.f3021H0 + iM1366F416 + iFF115;
        this.f3021H0 = i5;
        this.xOff = 0;
        int i6 = 0;
        while (true) {
            int[] iArr = this.f3025X;
            if (i6 == iArr.length) {
                return;
            }
            iArr[i6] = 0;
            i6++;
        }
    }

    @Override
    protected void processLength(long j) {
        if (this.xOff > 14) {
            processBlock();
        }
        int[] iArr = this.f3025X;
        iArr[14] = (int) ((-1) & j);
        iArr[15] = (int) (j >>> 32);
    }

    @Override
    protected void processWord(byte[] bArr, int i) {
        int[] iArr = this.f3025X;
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
        this.f3021H0 = 1732584193;
        this.f3022H1 = -271733879;
        this.f3023H2 = -1732584194;
        this.f3024H3 = 271733878;
        this.xOff = 0;
        int i = 0;
        while (true) {
            int[] iArr = this.f3025X;
            if (i == iArr.length) {
                return;
            }
            iArr[i] = 0;
            i++;
        }
    }

    @Override
    public void reset(Memoable memoable) {
        copyIn((RIPEMD128Digest) memoable);
    }
}
