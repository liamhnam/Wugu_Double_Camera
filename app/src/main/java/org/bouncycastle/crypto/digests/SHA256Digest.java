package org.bouncycastle.crypto.digests;

import kotlin.UByte;
import org.bouncycastle.util.Memoable;
import org.bouncycastle.util.Pack;

public class SHA256Digest extends GeneralDigest implements EncodableDigest {
    private static final int DIGEST_LENGTH = 32;

    static final int[] f3072K = {1116352408, 1899447441, -1245643825, -373957723, 961987163, 1508970993, -1841331548, -1424204075, -670586216, 310598401, 607225278, 1426881987, 1925078388, -2132889090, -1680079193, -1046744716, -459576895, -272742522, 264347078, 604807628, 770255983, 1249150122, 1555081692, 1996064986, -1740746414, -1473132947, -1341970488, -1084653625, -958395405, -710438585, 113926993, 338241895, 666307205, 773529912, 1294757372, 1396182291, 1695183700, 1986661051, -2117940946, -1838011259, -1564481375, -1474664885, -1035236496, -949202525, -778901479, -694614492, -200395387, 275423344, 430227734, 506948616, 659060556, 883997877, 958139571, 1322822218, 1537002063, 1747873779, 1955562222, 2024104815, -2067236844, -1933114872, -1866530822, -1538233109, -1090935817, -965641998};

    private int f3073H1;

    private int f3074H2;

    private int f3075H3;

    private int f3076H4;

    private int f3077H5;

    private int f3078H6;

    private int f3079H7;

    private int f3080H8;

    private int[] f3081X;
    private int xOff;

    public SHA256Digest() {
        this.f3081X = new int[64];
        reset();
    }

    public SHA256Digest(SHA256Digest sHA256Digest) {
        super(sHA256Digest);
        this.f3081X = new int[64];
        copyIn(sHA256Digest);
    }

    public SHA256Digest(byte[] bArr) {
        super(bArr);
        this.f3081X = new int[64];
        this.f3073H1 = Pack.bigEndianToInt(bArr, 16);
        this.f3074H2 = Pack.bigEndianToInt(bArr, 20);
        this.f3075H3 = Pack.bigEndianToInt(bArr, 24);
        this.f3076H4 = Pack.bigEndianToInt(bArr, 28);
        this.f3077H5 = Pack.bigEndianToInt(bArr, 32);
        this.f3078H6 = Pack.bigEndianToInt(bArr, 36);
        this.f3079H7 = Pack.bigEndianToInt(bArr, 40);
        this.f3080H8 = Pack.bigEndianToInt(bArr, 44);
        this.xOff = Pack.bigEndianToInt(bArr, 48);
        for (int i = 0; i != this.xOff; i++) {
            this.f3081X[i] = Pack.bigEndianToInt(bArr, (i * 4) + 52);
        }
    }

    private static int m1397Ch(int i, int i2, int i3) {
        return ((~i) & i3) ^ (i2 & i);
    }

    private static int Maj(int i, int i2, int i3) {
        return ((i ^ i2) & i3) | (i & i2);
    }

    private static int Sum0(int i) {
        return ((i << 10) | (i >>> 22)) ^ (((i >>> 2) | (i << 30)) ^ ((i >>> 13) | (i << 19)));
    }

    private static int Sum1(int i) {
        return ((i << 7) | (i >>> 25)) ^ (((i >>> 6) | (i << 26)) ^ ((i >>> 11) | (i << 21)));
    }

    private static int Theta0(int i) {
        return (i >>> 3) ^ (((i >>> 7) | (i << 25)) ^ ((i >>> 18) | (i << 14)));
    }

    private static int Theta1(int i) {
        return (i >>> 10) ^ (((i >>> 17) | (i << 15)) ^ ((i >>> 19) | (i << 13)));
    }

    private void copyIn(SHA256Digest sHA256Digest) {
        super.copyIn((GeneralDigest) sHA256Digest);
        this.f3073H1 = sHA256Digest.f3073H1;
        this.f3074H2 = sHA256Digest.f3074H2;
        this.f3075H3 = sHA256Digest.f3075H3;
        this.f3076H4 = sHA256Digest.f3076H4;
        this.f3077H5 = sHA256Digest.f3077H5;
        this.f3078H6 = sHA256Digest.f3078H6;
        this.f3079H7 = sHA256Digest.f3079H7;
        this.f3080H8 = sHA256Digest.f3080H8;
        int[] iArr = sHA256Digest.f3081X;
        System.arraycopy(iArr, 0, this.f3081X, 0, iArr.length);
        this.xOff = sHA256Digest.xOff;
    }

    @Override
    public Memoable copy() {
        return new SHA256Digest(this);
    }

    @Override
    public int doFinal(byte[] bArr, int i) {
        finish();
        Pack.intToBigEndian(this.f3073H1, bArr, i);
        Pack.intToBigEndian(this.f3074H2, bArr, i + 4);
        Pack.intToBigEndian(this.f3075H3, bArr, i + 8);
        Pack.intToBigEndian(this.f3076H4, bArr, i + 12);
        Pack.intToBigEndian(this.f3077H5, bArr, i + 16);
        Pack.intToBigEndian(this.f3078H6, bArr, i + 20);
        Pack.intToBigEndian(this.f3079H7, bArr, i + 24);
        Pack.intToBigEndian(this.f3080H8, bArr, i + 28);
        reset();
        return 32;
    }

    @Override
    public String getAlgorithmName() {
        return "SHA-256";
    }

    @Override
    public int getDigestSize() {
        return 32;
    }

    @Override
    public byte[] getEncodedState() {
        byte[] bArr = new byte[(this.xOff * 4) + 52];
        super.populateState(bArr);
        Pack.intToBigEndian(this.f3073H1, bArr, 16);
        Pack.intToBigEndian(this.f3074H2, bArr, 20);
        Pack.intToBigEndian(this.f3075H3, bArr, 24);
        Pack.intToBigEndian(this.f3076H4, bArr, 28);
        Pack.intToBigEndian(this.f3077H5, bArr, 32);
        Pack.intToBigEndian(this.f3078H6, bArr, 36);
        Pack.intToBigEndian(this.f3079H7, bArr, 40);
        Pack.intToBigEndian(this.f3080H8, bArr, 44);
        Pack.intToBigEndian(this.xOff, bArr, 48);
        for (int i = 0; i != this.xOff; i++) {
            Pack.intToBigEndian(this.f3081X[i], bArr, (i * 4) + 52);
        }
        return bArr;
    }

    @Override
    protected void processBlock() {
        for (int i = 16; i <= 63; i++) {
            int[] iArr = this.f3081X;
            int iTheta1 = Theta1(iArr[i - 2]);
            int[] iArr2 = this.f3081X;
            iArr[i] = iTheta1 + iArr2[i - 7] + Theta0(iArr2[i - 15]) + this.f3081X[i - 16];
        }
        int iSum0 = this.f3073H1;
        int iSum02 = this.f3074H2;
        int iSum03 = this.f3075H3;
        int iSum04 = this.f3076H4;
        int i2 = this.f3077H5;
        int i3 = this.f3078H6;
        int i4 = this.f3079H7;
        int i5 = this.f3080H8;
        int i6 = 0;
        for (int i7 = 0; i7 < 8; i7++) {
            int iSum1 = Sum1(i2) + m1397Ch(i2, i3, i4);
            int[] iArr3 = f3072K;
            int i8 = i5 + iSum1 + iArr3[i6] + this.f3081X[i6];
            int i9 = iSum04 + i8;
            int iSum05 = i8 + Sum0(iSum0) + Maj(iSum0, iSum02, iSum03);
            int i10 = i6 + 1;
            int iSum12 = i4 + Sum1(i9) + m1397Ch(i9, i2, i3) + iArr3[i10] + this.f3081X[i10];
            int i11 = iSum03 + iSum12;
            int iSum06 = iSum12 + Sum0(iSum05) + Maj(iSum05, iSum0, iSum02);
            int i12 = i10 + 1;
            int iSum13 = i3 + Sum1(i11) + m1397Ch(i11, i9, i2) + iArr3[i12] + this.f3081X[i12];
            int i13 = iSum02 + iSum13;
            int iSum07 = iSum13 + Sum0(iSum06) + Maj(iSum06, iSum05, iSum0);
            int i14 = i12 + 1;
            int iSum14 = i2 + Sum1(i13) + m1397Ch(i13, i11, i9) + iArr3[i14] + this.f3081X[i14];
            int i15 = iSum0 + iSum14;
            int iSum08 = iSum14 + Sum0(iSum07) + Maj(iSum07, iSum06, iSum05);
            int i16 = i14 + 1;
            int iSum15 = i9 + Sum1(i15) + m1397Ch(i15, i13, i11) + iArr3[i16] + this.f3081X[i16];
            i5 = iSum05 + iSum15;
            iSum04 = iSum15 + Sum0(iSum08) + Maj(iSum08, iSum07, iSum06);
            int i17 = i16 + 1;
            int iSum16 = i11 + Sum1(i5) + m1397Ch(i5, i15, i13) + iArr3[i17] + this.f3081X[i17];
            i4 = iSum06 + iSum16;
            iSum03 = iSum16 + Sum0(iSum04) + Maj(iSum04, iSum08, iSum07);
            int i18 = i17 + 1;
            int iSum17 = i13 + Sum1(i4) + m1397Ch(i4, i5, i15) + iArr3[i18] + this.f3081X[i18];
            i3 = iSum07 + iSum17;
            iSum02 = iSum17 + Sum0(iSum03) + Maj(iSum03, iSum04, iSum08);
            int i19 = i18 + 1;
            int iSum18 = i15 + Sum1(i3) + m1397Ch(i3, i4, i5) + iArr3[i19] + this.f3081X[i19];
            i2 = iSum08 + iSum18;
            iSum0 = iSum18 + Sum0(iSum02) + Maj(iSum02, iSum03, iSum04);
            i6 = i19 + 1;
        }
        this.f3073H1 += iSum0;
        this.f3074H2 += iSum02;
        this.f3075H3 += iSum03;
        this.f3076H4 += iSum04;
        this.f3077H5 += i2;
        this.f3078H6 += i3;
        this.f3079H7 += i4;
        this.f3080H8 += i5;
        this.xOff = 0;
        for (int i20 = 0; i20 < 16; i20++) {
            this.f3081X[i20] = 0;
        }
    }

    @Override
    protected void processLength(long j) {
        if (this.xOff > 14) {
            processBlock();
        }
        int[] iArr = this.f3081X;
        iArr[14] = (int) (j >>> 32);
        iArr[15] = (int) (j & (-1));
    }

    @Override
    protected void processWord(byte[] bArr, int i) {
        int i2 = bArr[i] << 24;
        int i3 = i + 1;
        int i4 = i2 | ((bArr[i3] & UByte.MAX_VALUE) << 16);
        int i5 = i3 + 1;
        int i6 = (bArr[i5 + 1] & UByte.MAX_VALUE) | i4 | ((bArr[i5] & UByte.MAX_VALUE) << 8);
        int[] iArr = this.f3081X;
        int i7 = this.xOff;
        iArr[i7] = i6;
        int i8 = i7 + 1;
        this.xOff = i8;
        if (i8 == 16) {
            processBlock();
        }
    }

    @Override
    public void reset() {
        super.reset();
        this.f3073H1 = 1779033703;
        this.f3074H2 = -1150833019;
        this.f3075H3 = 1013904242;
        this.f3076H4 = -1521486534;
        this.f3077H5 = 1359893119;
        this.f3078H6 = -1694144372;
        this.f3079H7 = 528734635;
        this.f3080H8 = 1541459225;
        this.xOff = 0;
        int i = 0;
        while (true) {
            int[] iArr = this.f3081X;
            if (i == iArr.length) {
                return;
            }
            iArr[i] = 0;
            i++;
        }
    }

    @Override
    public void reset(Memoable memoable) {
        copyIn((SHA256Digest) memoable);
    }
}
