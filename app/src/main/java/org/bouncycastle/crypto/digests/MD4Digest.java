package org.bouncycastle.crypto.digests;

import kotlin.UByte;
import org.bouncycastle.util.Memoable;

public class MD4Digest extends GeneralDigest {
    private static final int DIGEST_LENGTH = 16;
    private static final int S11 = 3;
    private static final int S12 = 7;
    private static final int S13 = 11;
    private static final int S14 = 19;
    private static final int S21 = 3;
    private static final int S22 = 5;
    private static final int S23 = 9;
    private static final int S24 = 13;
    private static final int S31 = 3;
    private static final int S32 = 9;
    private static final int S33 = 11;
    private static final int S34 = 15;

    private int f3010H1;

    private int f3011H2;

    private int f3012H3;

    private int f3013H4;

    private int[] f3014X;
    private int xOff;

    public MD4Digest() {
        this.f3014X = new int[16];
        reset();
    }

    public MD4Digest(MD4Digest mD4Digest) {
        super(mD4Digest);
        this.f3014X = new int[16];
        copyIn(mD4Digest);
    }

    private int m1356F(int i, int i2, int i3) {
        return ((~i) & i3) | (i2 & i);
    }

    private int m1357G(int i, int i2, int i3) {
        return (i & i3) | (i & i2) | (i2 & i3);
    }

    private int m1358H(int i, int i2, int i3) {
        return (i ^ i2) ^ i3;
    }

    private void copyIn(MD4Digest mD4Digest) {
        super.copyIn((GeneralDigest) mD4Digest);
        this.f3010H1 = mD4Digest.f3010H1;
        this.f3011H2 = mD4Digest.f3011H2;
        this.f3012H3 = mD4Digest.f3012H3;
        this.f3013H4 = mD4Digest.f3013H4;
        int[] iArr = mD4Digest.f3014X;
        System.arraycopy(iArr, 0, this.f3014X, 0, iArr.length);
        this.xOff = mD4Digest.xOff;
    }

    private int rotateLeft(int i, int i2) {
        return (i >>> (32 - i2)) | (i << i2);
    }

    private void unpackWord(int i, byte[] bArr, int i2) {
        bArr[i2] = (byte) i;
        bArr[i2 + 1] = (byte) (i >>> 8);
        bArr[i2 + 2] = (byte) (i >>> 16);
        bArr[i2 + 3] = (byte) (i >>> 24);
    }

    @Override
    public Memoable copy() {
        return new MD4Digest(this);
    }

    @Override
    public int doFinal(byte[] bArr, int i) {
        finish();
        unpackWord(this.f3010H1, bArr, i);
        unpackWord(this.f3011H2, bArr, i + 4);
        unpackWord(this.f3012H3, bArr, i + 8);
        unpackWord(this.f3013H4, bArr, i + 12);
        reset();
        return 16;
    }

    @Override
    public String getAlgorithmName() {
        return "MD4";
    }

    @Override
    public int getDigestSize() {
        return 16;
    }

    @Override
    protected void processBlock() {
        int i = this.f3010H1;
        int i2 = this.f3011H2;
        int i3 = this.f3012H3;
        int i4 = this.f3013H4;
        int iRotateLeft = rotateLeft(i + m1356F(i2, i3, i4) + this.f3014X[0], 3);
        int iRotateLeft2 = rotateLeft(i4 + m1356F(iRotateLeft, i2, i3) + this.f3014X[1], 7);
        int iRotateLeft3 = rotateLeft(i3 + m1356F(iRotateLeft2, iRotateLeft, i2) + this.f3014X[2], 11);
        int iRotateLeft4 = rotateLeft(i2 + m1356F(iRotateLeft3, iRotateLeft2, iRotateLeft) + this.f3014X[3], 19);
        int iRotateLeft5 = rotateLeft(iRotateLeft + m1356F(iRotateLeft4, iRotateLeft3, iRotateLeft2) + this.f3014X[4], 3);
        int iRotateLeft6 = rotateLeft(iRotateLeft2 + m1356F(iRotateLeft5, iRotateLeft4, iRotateLeft3) + this.f3014X[5], 7);
        int iRotateLeft7 = rotateLeft(iRotateLeft3 + m1356F(iRotateLeft6, iRotateLeft5, iRotateLeft4) + this.f3014X[6], 11);
        int iRotateLeft8 = rotateLeft(iRotateLeft4 + m1356F(iRotateLeft7, iRotateLeft6, iRotateLeft5) + this.f3014X[7], 19);
        int iRotateLeft9 = rotateLeft(iRotateLeft5 + m1356F(iRotateLeft8, iRotateLeft7, iRotateLeft6) + this.f3014X[8], 3);
        int iRotateLeft10 = rotateLeft(iRotateLeft6 + m1356F(iRotateLeft9, iRotateLeft8, iRotateLeft7) + this.f3014X[9], 7);
        int iRotateLeft11 = rotateLeft(iRotateLeft7 + m1356F(iRotateLeft10, iRotateLeft9, iRotateLeft8) + this.f3014X[10], 11);
        int iRotateLeft12 = rotateLeft(iRotateLeft8 + m1356F(iRotateLeft11, iRotateLeft10, iRotateLeft9) + this.f3014X[11], 19);
        int iRotateLeft13 = rotateLeft(iRotateLeft9 + m1356F(iRotateLeft12, iRotateLeft11, iRotateLeft10) + this.f3014X[12], 3);
        int iRotateLeft14 = rotateLeft(iRotateLeft10 + m1356F(iRotateLeft13, iRotateLeft12, iRotateLeft11) + this.f3014X[13], 7);
        int iRotateLeft15 = rotateLeft(iRotateLeft11 + m1356F(iRotateLeft14, iRotateLeft13, iRotateLeft12) + this.f3014X[14], 11);
        int iRotateLeft16 = rotateLeft(iRotateLeft12 + m1356F(iRotateLeft15, iRotateLeft14, iRotateLeft13) + this.f3014X[15], 19);
        int iRotateLeft17 = rotateLeft(iRotateLeft13 + m1357G(iRotateLeft16, iRotateLeft15, iRotateLeft14) + this.f3014X[0] + 1518500249, 3);
        int iRotateLeft18 = rotateLeft(iRotateLeft14 + m1357G(iRotateLeft17, iRotateLeft16, iRotateLeft15) + this.f3014X[4] + 1518500249, 5);
        int iRotateLeft19 = rotateLeft(iRotateLeft15 + m1357G(iRotateLeft18, iRotateLeft17, iRotateLeft16) + this.f3014X[8] + 1518500249, 9);
        int iRotateLeft20 = rotateLeft(iRotateLeft16 + m1357G(iRotateLeft19, iRotateLeft18, iRotateLeft17) + this.f3014X[12] + 1518500249, 13);
        int iRotateLeft21 = rotateLeft(iRotateLeft17 + m1357G(iRotateLeft20, iRotateLeft19, iRotateLeft18) + this.f3014X[1] + 1518500249, 3);
        int iRotateLeft22 = rotateLeft(iRotateLeft18 + m1357G(iRotateLeft21, iRotateLeft20, iRotateLeft19) + this.f3014X[5] + 1518500249, 5);
        int iRotateLeft23 = rotateLeft(iRotateLeft19 + m1357G(iRotateLeft22, iRotateLeft21, iRotateLeft20) + this.f3014X[9] + 1518500249, 9);
        int iRotateLeft24 = rotateLeft(iRotateLeft20 + m1357G(iRotateLeft23, iRotateLeft22, iRotateLeft21) + this.f3014X[13] + 1518500249, 13);
        int iRotateLeft25 = rotateLeft(iRotateLeft21 + m1357G(iRotateLeft24, iRotateLeft23, iRotateLeft22) + this.f3014X[2] + 1518500249, 3);
        int iRotateLeft26 = rotateLeft(iRotateLeft22 + m1357G(iRotateLeft25, iRotateLeft24, iRotateLeft23) + this.f3014X[6] + 1518500249, 5);
        int iRotateLeft27 = rotateLeft(iRotateLeft23 + m1357G(iRotateLeft26, iRotateLeft25, iRotateLeft24) + this.f3014X[10] + 1518500249, 9);
        int iRotateLeft28 = rotateLeft(iRotateLeft24 + m1357G(iRotateLeft27, iRotateLeft26, iRotateLeft25) + this.f3014X[14] + 1518500249, 13);
        int iRotateLeft29 = rotateLeft(iRotateLeft25 + m1357G(iRotateLeft28, iRotateLeft27, iRotateLeft26) + this.f3014X[3] + 1518500249, 3);
        int iRotateLeft30 = rotateLeft(iRotateLeft26 + m1357G(iRotateLeft29, iRotateLeft28, iRotateLeft27) + this.f3014X[7] + 1518500249, 5);
        int iRotateLeft31 = rotateLeft(iRotateLeft27 + m1357G(iRotateLeft30, iRotateLeft29, iRotateLeft28) + this.f3014X[11] + 1518500249, 9);
        int iRotateLeft32 = rotateLeft(iRotateLeft28 + m1357G(iRotateLeft31, iRotateLeft30, iRotateLeft29) + this.f3014X[15] + 1518500249, 13);
        int iRotateLeft33 = rotateLeft(iRotateLeft29 + m1358H(iRotateLeft32, iRotateLeft31, iRotateLeft30) + this.f3014X[0] + 1859775393, 3);
        int iRotateLeft34 = rotateLeft(iRotateLeft30 + m1358H(iRotateLeft33, iRotateLeft32, iRotateLeft31) + this.f3014X[8] + 1859775393, 9);
        int iRotateLeft35 = rotateLeft(iRotateLeft31 + m1358H(iRotateLeft34, iRotateLeft33, iRotateLeft32) + this.f3014X[4] + 1859775393, 11);
        int iRotateLeft36 = rotateLeft(iRotateLeft32 + m1358H(iRotateLeft35, iRotateLeft34, iRotateLeft33) + this.f3014X[12] + 1859775393, 15);
        int iRotateLeft37 = rotateLeft(iRotateLeft33 + m1358H(iRotateLeft36, iRotateLeft35, iRotateLeft34) + this.f3014X[2] + 1859775393, 3);
        int iRotateLeft38 = rotateLeft(iRotateLeft34 + m1358H(iRotateLeft37, iRotateLeft36, iRotateLeft35) + this.f3014X[10] + 1859775393, 9);
        int iRotateLeft39 = rotateLeft(iRotateLeft35 + m1358H(iRotateLeft38, iRotateLeft37, iRotateLeft36) + this.f3014X[6] + 1859775393, 11);
        int iRotateLeft40 = rotateLeft(iRotateLeft36 + m1358H(iRotateLeft39, iRotateLeft38, iRotateLeft37) + this.f3014X[14] + 1859775393, 15);
        int iRotateLeft41 = rotateLeft(iRotateLeft37 + m1358H(iRotateLeft40, iRotateLeft39, iRotateLeft38) + this.f3014X[1] + 1859775393, 3);
        int iRotateLeft42 = rotateLeft(iRotateLeft38 + m1358H(iRotateLeft41, iRotateLeft40, iRotateLeft39) + this.f3014X[9] + 1859775393, 9);
        int iRotateLeft43 = rotateLeft(iRotateLeft39 + m1358H(iRotateLeft42, iRotateLeft41, iRotateLeft40) + this.f3014X[5] + 1859775393, 11);
        int iRotateLeft44 = rotateLeft(iRotateLeft40 + m1358H(iRotateLeft43, iRotateLeft42, iRotateLeft41) + this.f3014X[13] + 1859775393, 15);
        int iRotateLeft45 = rotateLeft(iRotateLeft41 + m1358H(iRotateLeft44, iRotateLeft43, iRotateLeft42) + this.f3014X[3] + 1859775393, 3);
        int iRotateLeft46 = rotateLeft(iRotateLeft42 + m1358H(iRotateLeft45, iRotateLeft44, iRotateLeft43) + this.f3014X[11] + 1859775393, 9);
        int iRotateLeft47 = rotateLeft(iRotateLeft43 + m1358H(iRotateLeft46, iRotateLeft45, iRotateLeft44) + this.f3014X[7] + 1859775393, 11);
        int iRotateLeft48 = rotateLeft(iRotateLeft44 + m1358H(iRotateLeft47, iRotateLeft46, iRotateLeft45) + this.f3014X[15] + 1859775393, 15);
        this.f3010H1 += iRotateLeft45;
        this.f3011H2 += iRotateLeft48;
        this.f3012H3 += iRotateLeft47;
        this.f3013H4 += iRotateLeft46;
        this.xOff = 0;
        int i5 = 0;
        while (true) {
            int[] iArr = this.f3014X;
            if (i5 == iArr.length) {
                return;
            }
            iArr[i5] = 0;
            i5++;
        }
    }

    @Override
    protected void processLength(long j) {
        if (this.xOff > 14) {
            processBlock();
        }
        int[] iArr = this.f3014X;
        iArr[14] = (int) ((-1) & j);
        iArr[15] = (int) (j >>> 32);
    }

    @Override
    protected void processWord(byte[] bArr, int i) {
        int[] iArr = this.f3014X;
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
        this.f3010H1 = 1732584193;
        this.f3011H2 = -271733879;
        this.f3012H3 = -1732584194;
        this.f3013H4 = 271733878;
        this.xOff = 0;
        int i = 0;
        while (true) {
            int[] iArr = this.f3014X;
            if (i == iArr.length) {
                return;
            }
            iArr[i] = 0;
            i++;
        }
    }

    @Override
    public void reset(Memoable memoable) {
        copyIn((MD4Digest) memoable);
    }
}
