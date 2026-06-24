package org.bouncycastle.crypto.digests;

import kotlin.UByte;
import org.bouncycastle.util.Memoable;
import org.bouncycastle.util.Pack;

public class MD5Digest extends GeneralDigest implements EncodableDigest {
    private static final int DIGEST_LENGTH = 16;
    private static final int S11 = 7;
    private static final int S12 = 12;
    private static final int S13 = 17;
    private static final int S14 = 22;
    private static final int S21 = 5;
    private static final int S22 = 9;
    private static final int S23 = 14;
    private static final int S24 = 20;
    private static final int S31 = 4;
    private static final int S32 = 11;
    private static final int S33 = 16;
    private static final int S34 = 23;
    private static final int S41 = 6;
    private static final int S42 = 10;
    private static final int S43 = 15;
    private static final int S44 = 21;

    private int f3015H1;

    private int f3016H2;

    private int f3017H3;

    private int f3018H4;

    private int[] f3019X;
    private int xOff;

    public MD5Digest() {
        this.f3019X = new int[16];
        reset();
    }

    public MD5Digest(MD5Digest mD5Digest) {
        super(mD5Digest);
        this.f3019X = new int[16];
        copyIn(mD5Digest);
    }

    public MD5Digest(byte[] bArr) {
        super(bArr);
        this.f3019X = new int[16];
        this.f3015H1 = Pack.bigEndianToInt(bArr, 16);
        this.f3016H2 = Pack.bigEndianToInt(bArr, 20);
        this.f3017H3 = Pack.bigEndianToInt(bArr, 24);
        this.f3018H4 = Pack.bigEndianToInt(bArr, 28);
        this.xOff = Pack.bigEndianToInt(bArr, 32);
        for (int i = 0; i != this.xOff; i++) {
            this.f3019X[i] = Pack.bigEndianToInt(bArr, (i * 4) + 36);
        }
    }

    private int m1359F(int i, int i2, int i3) {
        return ((~i) & i3) | (i2 & i);
    }

    private int m1360G(int i, int i2, int i3) {
        return (i & i3) | (i2 & (~i3));
    }

    private int m1361H(int i, int i2, int i3) {
        return (i ^ i2) ^ i3;
    }

    private int m1362K(int i, int i2, int i3) {
        return (i | (~i3)) ^ i2;
    }

    private void copyIn(MD5Digest mD5Digest) {
        super.copyIn((GeneralDigest) mD5Digest);
        this.f3015H1 = mD5Digest.f3015H1;
        this.f3016H2 = mD5Digest.f3016H2;
        this.f3017H3 = mD5Digest.f3017H3;
        this.f3018H4 = mD5Digest.f3018H4;
        int[] iArr = mD5Digest.f3019X;
        System.arraycopy(iArr, 0, this.f3019X, 0, iArr.length);
        this.xOff = mD5Digest.xOff;
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
        return new MD5Digest(this);
    }

    @Override
    public int doFinal(byte[] bArr, int i) {
        finish();
        unpackWord(this.f3015H1, bArr, i);
        unpackWord(this.f3016H2, bArr, i + 4);
        unpackWord(this.f3017H3, bArr, i + 8);
        unpackWord(this.f3018H4, bArr, i + 12);
        reset();
        return 16;
    }

    @Override
    public String getAlgorithmName() {
        return "MD5";
    }

    @Override
    public int getDigestSize() {
        return 16;
    }

    @Override
    public byte[] getEncodedState() {
        byte[] bArr = new byte[(this.xOff * 4) + 36];
        super.populateState(bArr);
        Pack.intToBigEndian(this.f3015H1, bArr, 16);
        Pack.intToBigEndian(this.f3016H2, bArr, 20);
        Pack.intToBigEndian(this.f3017H3, bArr, 24);
        Pack.intToBigEndian(this.f3018H4, bArr, 28);
        Pack.intToBigEndian(this.xOff, bArr, 32);
        for (int i = 0; i != this.xOff; i++) {
            Pack.intToBigEndian(this.f3019X[i], bArr, (i * 4) + 36);
        }
        return bArr;
    }

    @Override
    protected void processBlock() {
        int i = this.f3015H1;
        int i2 = this.f3016H2;
        int i3 = this.f3017H3;
        int i4 = this.f3018H4;
        int iRotateLeft = rotateLeft(((i + m1359F(i2, i3, i4)) + this.f3019X[0]) - 680876936, 7) + i2;
        int iRotateLeft2 = rotateLeft(((i4 + m1359F(iRotateLeft, i2, i3)) + this.f3019X[1]) - 389564586, 12) + iRotateLeft;
        int iRotateLeft3 = rotateLeft(i3 + m1359F(iRotateLeft2, iRotateLeft, i2) + this.f3019X[2] + 606105819, 17) + iRotateLeft2;
        int iRotateLeft4 = rotateLeft(((i2 + m1359F(iRotateLeft3, iRotateLeft2, iRotateLeft)) + this.f3019X[3]) - 1044525330, 22) + iRotateLeft3;
        int iRotateLeft5 = rotateLeft(((iRotateLeft + m1359F(iRotateLeft4, iRotateLeft3, iRotateLeft2)) + this.f3019X[4]) - 176418897, 7) + iRotateLeft4;
        int iRotateLeft6 = rotateLeft(iRotateLeft2 + m1359F(iRotateLeft5, iRotateLeft4, iRotateLeft3) + this.f3019X[5] + 1200080426, 12) + iRotateLeft5;
        int iRotateLeft7 = rotateLeft(((iRotateLeft3 + m1359F(iRotateLeft6, iRotateLeft5, iRotateLeft4)) + this.f3019X[6]) - 1473231341, 17) + iRotateLeft6;
        int iRotateLeft8 = rotateLeft(((iRotateLeft4 + m1359F(iRotateLeft7, iRotateLeft6, iRotateLeft5)) + this.f3019X[7]) - 45705983, 22) + iRotateLeft7;
        int iRotateLeft9 = rotateLeft(iRotateLeft5 + m1359F(iRotateLeft8, iRotateLeft7, iRotateLeft6) + this.f3019X[8] + 1770035416, 7) + iRotateLeft8;
        int iRotateLeft10 = rotateLeft(((iRotateLeft6 + m1359F(iRotateLeft9, iRotateLeft8, iRotateLeft7)) + this.f3019X[9]) - 1958414417, 12) + iRotateLeft9;
        int iRotateLeft11 = rotateLeft(((iRotateLeft7 + m1359F(iRotateLeft10, iRotateLeft9, iRotateLeft8)) + this.f3019X[10]) - 42063, 17) + iRotateLeft10;
        int iRotateLeft12 = rotateLeft(((iRotateLeft8 + m1359F(iRotateLeft11, iRotateLeft10, iRotateLeft9)) + this.f3019X[11]) - 1990404162, 22) + iRotateLeft11;
        int iRotateLeft13 = rotateLeft(iRotateLeft9 + m1359F(iRotateLeft12, iRotateLeft11, iRotateLeft10) + this.f3019X[12] + 1804603682, 7) + iRotateLeft12;
        int iRotateLeft14 = rotateLeft(((iRotateLeft10 + m1359F(iRotateLeft13, iRotateLeft12, iRotateLeft11)) + this.f3019X[13]) - 40341101, 12) + iRotateLeft13;
        int iRotateLeft15 = rotateLeft(((iRotateLeft11 + m1359F(iRotateLeft14, iRotateLeft13, iRotateLeft12)) + this.f3019X[14]) - 1502002290, 17) + iRotateLeft14;
        int iRotateLeft16 = rotateLeft(iRotateLeft12 + m1359F(iRotateLeft15, iRotateLeft14, iRotateLeft13) + this.f3019X[15] + 1236535329, 22) + iRotateLeft15;
        int iRotateLeft17 = rotateLeft(((iRotateLeft13 + m1360G(iRotateLeft16, iRotateLeft15, iRotateLeft14)) + this.f3019X[1]) - 165796510, 5) + iRotateLeft16;
        int iRotateLeft18 = rotateLeft(((iRotateLeft14 + m1360G(iRotateLeft17, iRotateLeft16, iRotateLeft15)) + this.f3019X[6]) - 1069501632, 9) + iRotateLeft17;
        int iRotateLeft19 = rotateLeft(iRotateLeft15 + m1360G(iRotateLeft18, iRotateLeft17, iRotateLeft16) + this.f3019X[11] + 643717713, 14) + iRotateLeft18;
        int iRotateLeft20 = rotateLeft(((iRotateLeft16 + m1360G(iRotateLeft19, iRotateLeft18, iRotateLeft17)) + this.f3019X[0]) - 373897302, 20) + iRotateLeft19;
        int iRotateLeft21 = rotateLeft(((iRotateLeft17 + m1360G(iRotateLeft20, iRotateLeft19, iRotateLeft18)) + this.f3019X[5]) - 701558691, 5) + iRotateLeft20;
        int iRotateLeft22 = rotateLeft(iRotateLeft18 + m1360G(iRotateLeft21, iRotateLeft20, iRotateLeft19) + this.f3019X[10] + 38016083, 9) + iRotateLeft21;
        int iRotateLeft23 = rotateLeft(((iRotateLeft19 + m1360G(iRotateLeft22, iRotateLeft21, iRotateLeft20)) + this.f3019X[15]) - 660478335, 14) + iRotateLeft22;
        int iRotateLeft24 = rotateLeft(((iRotateLeft20 + m1360G(iRotateLeft23, iRotateLeft22, iRotateLeft21)) + this.f3019X[4]) - 405537848, 20) + iRotateLeft23;
        int iRotateLeft25 = rotateLeft(iRotateLeft21 + m1360G(iRotateLeft24, iRotateLeft23, iRotateLeft22) + this.f3019X[9] + 568446438, 5) + iRotateLeft24;
        int iRotateLeft26 = rotateLeft(((iRotateLeft22 + m1360G(iRotateLeft25, iRotateLeft24, iRotateLeft23)) + this.f3019X[14]) - 1019803690, 9) + iRotateLeft25;
        int iRotateLeft27 = rotateLeft(((iRotateLeft23 + m1360G(iRotateLeft26, iRotateLeft25, iRotateLeft24)) + this.f3019X[3]) - 187363961, 14) + iRotateLeft26;
        int iRotateLeft28 = rotateLeft(iRotateLeft24 + m1360G(iRotateLeft27, iRotateLeft26, iRotateLeft25) + this.f3019X[8] + 1163531501, 20) + iRotateLeft27;
        int iRotateLeft29 = rotateLeft(((iRotateLeft25 + m1360G(iRotateLeft28, iRotateLeft27, iRotateLeft26)) + this.f3019X[13]) - 1444681467, 5) + iRotateLeft28;
        int iRotateLeft30 = rotateLeft(((iRotateLeft26 + m1360G(iRotateLeft29, iRotateLeft28, iRotateLeft27)) + this.f3019X[2]) - 51403784, 9) + iRotateLeft29;
        int iRotateLeft31 = rotateLeft(iRotateLeft27 + m1360G(iRotateLeft30, iRotateLeft29, iRotateLeft28) + this.f3019X[7] + 1735328473, 14) + iRotateLeft30;
        int iRotateLeft32 = rotateLeft(((iRotateLeft28 + m1360G(iRotateLeft31, iRotateLeft30, iRotateLeft29)) + this.f3019X[12]) - 1926607734, 20) + iRotateLeft31;
        int iRotateLeft33 = rotateLeft(((iRotateLeft29 + m1361H(iRotateLeft32, iRotateLeft31, iRotateLeft30)) + this.f3019X[5]) - 378558, 4) + iRotateLeft32;
        int iRotateLeft34 = rotateLeft(((iRotateLeft30 + m1361H(iRotateLeft33, iRotateLeft32, iRotateLeft31)) + this.f3019X[8]) - 2022574463, 11) + iRotateLeft33;
        int iRotateLeft35 = rotateLeft(iRotateLeft31 + m1361H(iRotateLeft34, iRotateLeft33, iRotateLeft32) + this.f3019X[11] + 1839030562, 16) + iRotateLeft34;
        int iRotateLeft36 = rotateLeft(((iRotateLeft32 + m1361H(iRotateLeft35, iRotateLeft34, iRotateLeft33)) + this.f3019X[14]) - 35309556, 23) + iRotateLeft35;
        int iRotateLeft37 = rotateLeft(((iRotateLeft33 + m1361H(iRotateLeft36, iRotateLeft35, iRotateLeft34)) + this.f3019X[1]) - 1530992060, 4) + iRotateLeft36;
        int iRotateLeft38 = rotateLeft(iRotateLeft34 + m1361H(iRotateLeft37, iRotateLeft36, iRotateLeft35) + this.f3019X[4] + 1272893353, 11) + iRotateLeft37;
        int iRotateLeft39 = rotateLeft(((iRotateLeft35 + m1361H(iRotateLeft38, iRotateLeft37, iRotateLeft36)) + this.f3019X[7]) - 155497632, 16) + iRotateLeft38;
        int iRotateLeft40 = rotateLeft(((iRotateLeft36 + m1361H(iRotateLeft39, iRotateLeft38, iRotateLeft37)) + this.f3019X[10]) - 1094730640, 23) + iRotateLeft39;
        int iRotateLeft41 = rotateLeft(iRotateLeft37 + m1361H(iRotateLeft40, iRotateLeft39, iRotateLeft38) + this.f3019X[13] + 681279174, 4) + iRotateLeft40;
        int iRotateLeft42 = rotateLeft(((iRotateLeft38 + m1361H(iRotateLeft41, iRotateLeft40, iRotateLeft39)) + this.f3019X[0]) - 358537222, 11) + iRotateLeft41;
        int iRotateLeft43 = rotateLeft(((iRotateLeft39 + m1361H(iRotateLeft42, iRotateLeft41, iRotateLeft40)) + this.f3019X[3]) - 722521979, 16) + iRotateLeft42;
        int iRotateLeft44 = rotateLeft(iRotateLeft40 + m1361H(iRotateLeft43, iRotateLeft42, iRotateLeft41) + this.f3019X[6] + 76029189, 23) + iRotateLeft43;
        int iRotateLeft45 = rotateLeft(((iRotateLeft41 + m1361H(iRotateLeft44, iRotateLeft43, iRotateLeft42)) + this.f3019X[9]) - 640364487, 4) + iRotateLeft44;
        int iRotateLeft46 = rotateLeft(((iRotateLeft42 + m1361H(iRotateLeft45, iRotateLeft44, iRotateLeft43)) + this.f3019X[12]) - 421815835, 11) + iRotateLeft45;
        int iRotateLeft47 = rotateLeft(iRotateLeft43 + m1361H(iRotateLeft46, iRotateLeft45, iRotateLeft44) + this.f3019X[15] + 530742520, 16) + iRotateLeft46;
        int iRotateLeft48 = rotateLeft(((iRotateLeft44 + m1361H(iRotateLeft47, iRotateLeft46, iRotateLeft45)) + this.f3019X[2]) - 995338651, 23) + iRotateLeft47;
        int iRotateLeft49 = rotateLeft(((iRotateLeft45 + m1362K(iRotateLeft48, iRotateLeft47, iRotateLeft46)) + this.f3019X[0]) - 198630844, 6) + iRotateLeft48;
        int iRotateLeft50 = rotateLeft(iRotateLeft46 + m1362K(iRotateLeft49, iRotateLeft48, iRotateLeft47) + this.f3019X[7] + 1126891415, 10) + iRotateLeft49;
        int iRotateLeft51 = rotateLeft(((iRotateLeft47 + m1362K(iRotateLeft50, iRotateLeft49, iRotateLeft48)) + this.f3019X[14]) - 1416354905, 15) + iRotateLeft50;
        int iRotateLeft52 = rotateLeft(((iRotateLeft48 + m1362K(iRotateLeft51, iRotateLeft50, iRotateLeft49)) + this.f3019X[5]) - 57434055, 21) + iRotateLeft51;
        int iRotateLeft53 = rotateLeft(iRotateLeft49 + m1362K(iRotateLeft52, iRotateLeft51, iRotateLeft50) + this.f3019X[12] + 1700485571, 6) + iRotateLeft52;
        int iRotateLeft54 = rotateLeft(((iRotateLeft50 + m1362K(iRotateLeft53, iRotateLeft52, iRotateLeft51)) + this.f3019X[3]) - 1894986606, 10) + iRotateLeft53;
        int iRotateLeft55 = rotateLeft(((iRotateLeft51 + m1362K(iRotateLeft54, iRotateLeft53, iRotateLeft52)) + this.f3019X[10]) - 1051523, 15) + iRotateLeft54;
        int iRotateLeft56 = rotateLeft(((iRotateLeft52 + m1362K(iRotateLeft55, iRotateLeft54, iRotateLeft53)) + this.f3019X[1]) - 2054922799, 21) + iRotateLeft55;
        int iRotateLeft57 = rotateLeft(iRotateLeft53 + m1362K(iRotateLeft56, iRotateLeft55, iRotateLeft54) + this.f3019X[8] + 1873313359, 6) + iRotateLeft56;
        int iRotateLeft58 = rotateLeft(((iRotateLeft54 + m1362K(iRotateLeft57, iRotateLeft56, iRotateLeft55)) + this.f3019X[15]) - 30611744, 10) + iRotateLeft57;
        int iRotateLeft59 = rotateLeft(((iRotateLeft55 + m1362K(iRotateLeft58, iRotateLeft57, iRotateLeft56)) + this.f3019X[6]) - 1560198380, 15) + iRotateLeft58;
        int iRotateLeft60 = rotateLeft(iRotateLeft56 + m1362K(iRotateLeft59, iRotateLeft58, iRotateLeft57) + this.f3019X[13] + 1309151649, 21) + iRotateLeft59;
        int iRotateLeft61 = rotateLeft(((iRotateLeft57 + m1362K(iRotateLeft60, iRotateLeft59, iRotateLeft58)) + this.f3019X[4]) - 145523070, 6) + iRotateLeft60;
        int iRotateLeft62 = rotateLeft(((iRotateLeft58 + m1362K(iRotateLeft61, iRotateLeft60, iRotateLeft59)) + this.f3019X[11]) - 1120210379, 10) + iRotateLeft61;
        int iRotateLeft63 = rotateLeft(iRotateLeft59 + m1362K(iRotateLeft62, iRotateLeft61, iRotateLeft60) + this.f3019X[2] + 718787259, 15) + iRotateLeft62;
        int iRotateLeft64 = rotateLeft(((iRotateLeft60 + m1362K(iRotateLeft63, iRotateLeft62, iRotateLeft61)) + this.f3019X[9]) - 343485551, 21) + iRotateLeft63;
        this.f3015H1 += iRotateLeft61;
        this.f3016H2 += iRotateLeft64;
        this.f3017H3 += iRotateLeft63;
        this.f3018H4 += iRotateLeft62;
        this.xOff = 0;
        int i5 = 0;
        while (true) {
            int[] iArr = this.f3019X;
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
        int[] iArr = this.f3019X;
        iArr[14] = (int) ((-1) & j);
        iArr[15] = (int) (j >>> 32);
    }

    @Override
    protected void processWord(byte[] bArr, int i) {
        int[] iArr = this.f3019X;
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
        this.f3015H1 = 1732584193;
        this.f3016H2 = -271733879;
        this.f3017H3 = -1732584194;
        this.f3018H4 = 271733878;
        this.xOff = 0;
        int i = 0;
        while (true) {
            int[] iArr = this.f3019X;
            if (i == iArr.length) {
                return;
            }
            iArr[i] = 0;
            i++;
        }
    }

    @Override
    public void reset(Memoable memoable) {
        copyIn((MD5Digest) memoable);
    }
}
