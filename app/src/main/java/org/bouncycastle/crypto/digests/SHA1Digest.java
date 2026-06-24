package org.bouncycastle.crypto.digests;

import kotlin.UByte;
import org.bouncycastle.pqc.jcajce.spec.McElieceCCA2KeyGenParameterSpec;
import org.bouncycastle.util.Memoable;
import org.bouncycastle.util.Pack;

public class SHA1Digest extends GeneralDigest implements EncodableDigest {
    private static final int DIGEST_LENGTH = 20;

    private static final int f3052Y1 = 1518500249;

    private static final int f3053Y2 = 1859775393;

    private static final int f3054Y3 = -1894007588;

    private static final int f3055Y4 = -899497514;

    private int f3056H1;

    private int f3057H2;

    private int f3058H3;

    private int f3059H4;

    private int f3060H5;

    private int[] f3061X;
    private int xOff;

    public SHA1Digest() {
        this.f3061X = new int[80];
        reset();
    }

    public SHA1Digest(SHA1Digest sHA1Digest) {
        super(sHA1Digest);
        this.f3061X = new int[80];
        copyIn(sHA1Digest);
    }

    public SHA1Digest(byte[] bArr) {
        super(bArr);
        this.f3061X = new int[80];
        this.f3056H1 = Pack.bigEndianToInt(bArr, 16);
        this.f3057H2 = Pack.bigEndianToInt(bArr, 20);
        this.f3058H3 = Pack.bigEndianToInt(bArr, 24);
        this.f3059H4 = Pack.bigEndianToInt(bArr, 28);
        this.f3060H5 = Pack.bigEndianToInt(bArr, 32);
        this.xOff = Pack.bigEndianToInt(bArr, 36);
        for (int i = 0; i != this.xOff; i++) {
            this.f3061X[i] = Pack.bigEndianToInt(bArr, (i * 4) + 40);
        }
    }

    private void copyIn(SHA1Digest sHA1Digest) {
        this.f3056H1 = sHA1Digest.f3056H1;
        this.f3057H2 = sHA1Digest.f3057H2;
        this.f3058H3 = sHA1Digest.f3058H3;
        this.f3059H4 = sHA1Digest.f3059H4;
        this.f3060H5 = sHA1Digest.f3060H5;
        int[] iArr = sHA1Digest.f3061X;
        System.arraycopy(iArr, 0, this.f3061X, 0, iArr.length);
        this.xOff = sHA1Digest.xOff;
    }

    private int m1393f(int i, int i2, int i3) {
        return ((~i) & i3) | (i2 & i);
    }

    private int m1394g(int i, int i2, int i3) {
        return (i & i3) | (i & i2) | (i2 & i3);
    }

    private int m1395h(int i, int i2, int i3) {
        return (i ^ i2) ^ i3;
    }

    @Override
    public Memoable copy() {
        return new SHA1Digest(this);
    }

    @Override
    public int doFinal(byte[] bArr, int i) {
        finish();
        Pack.intToBigEndian(this.f3056H1, bArr, i);
        Pack.intToBigEndian(this.f3057H2, bArr, i + 4);
        Pack.intToBigEndian(this.f3058H3, bArr, i + 8);
        Pack.intToBigEndian(this.f3059H4, bArr, i + 12);
        Pack.intToBigEndian(this.f3060H5, bArr, i + 16);
        reset();
        return 20;
    }

    @Override
    public String getAlgorithmName() {
        return McElieceCCA2KeyGenParameterSpec.SHA1;
    }

    @Override
    public int getDigestSize() {
        return 20;
    }

    @Override
    public byte[] getEncodedState() {
        byte[] bArr = new byte[(this.xOff * 4) + 40];
        super.populateState(bArr);
        Pack.intToBigEndian(this.f3056H1, bArr, 16);
        Pack.intToBigEndian(this.f3057H2, bArr, 20);
        Pack.intToBigEndian(this.f3058H3, bArr, 24);
        Pack.intToBigEndian(this.f3059H4, bArr, 28);
        Pack.intToBigEndian(this.f3060H5, bArr, 32);
        Pack.intToBigEndian(this.xOff, bArr, 36);
        for (int i = 0; i != this.xOff; i++) {
            Pack.intToBigEndian(this.f3061X[i], bArr, (i * 4) + 40);
        }
        return bArr;
    }

    @Override
    protected void processBlock() {
        for (int i = 16; i < 80; i++) {
            int[] iArr = this.f3061X;
            int i2 = ((iArr[i - 3] ^ iArr[i - 8]) ^ iArr[i - 14]) ^ iArr[i - 16];
            iArr[i] = (i2 >>> 31) | (i2 << 1);
        }
        int iM1395h = this.f3056H1;
        int iM1395h2 = this.f3057H2;
        int i3 = this.f3058H3;
        int i4 = this.f3059H4;
        int i5 = this.f3060H5;
        int i6 = 0;
        int i7 = 0;
        while (i6 < 4) {
            int i8 = i7 + 1;
            int iM1393f = i5 + ((iM1395h << 5) | (iM1395h >>> 27)) + m1393f(iM1395h2, i3, i4) + this.f3061X[i7] + f3052Y1;
            int i9 = (iM1395h2 >>> 2) | (iM1395h2 << 30);
            int i10 = i8 + 1;
            int iM1393f2 = i4 + ((iM1393f << 5) | (iM1393f >>> 27)) + m1393f(iM1395h, i9, i3) + this.f3061X[i8] + f3052Y1;
            int i11 = (iM1395h >>> 2) | (iM1395h << 30);
            int i12 = i10 + 1;
            int iM1393f3 = i3 + ((iM1393f2 << 5) | (iM1393f2 >>> 27)) + m1393f(iM1393f, i11, i9) + this.f3061X[i10] + f3052Y1;
            i5 = (iM1393f >>> 2) | (iM1393f << 30);
            int i13 = i12 + 1;
            iM1395h2 = i9 + ((iM1393f3 << 5) | (iM1393f3 >>> 27)) + m1393f(iM1393f2, i5, i11) + this.f3061X[i12] + f3052Y1;
            i4 = (iM1393f2 >>> 2) | (iM1393f2 << 30);
            iM1395h = i11 + ((iM1395h2 << 5) | (iM1395h2 >>> 27)) + m1393f(iM1393f3, i4, i5) + this.f3061X[i13] + f3052Y1;
            i3 = (iM1393f3 >>> 2) | (iM1393f3 << 30);
            i6++;
            i7 = i13 + 1;
        }
        int i14 = 0;
        while (i14 < 4) {
            int i15 = i7 + 1;
            int iM1395h3 = i5 + ((iM1395h << 5) | (iM1395h >>> 27)) + m1395h(iM1395h2, i3, i4) + this.f3061X[i7] + f3053Y2;
            int i16 = (iM1395h2 >>> 2) | (iM1395h2 << 30);
            int i17 = i15 + 1;
            int iM1395h4 = i4 + ((iM1395h3 << 5) | (iM1395h3 >>> 27)) + m1395h(iM1395h, i16, i3) + this.f3061X[i15] + f3053Y2;
            int i18 = (iM1395h >>> 2) | (iM1395h << 30);
            int i19 = i17 + 1;
            int iM1395h5 = i3 + ((iM1395h4 << 5) | (iM1395h4 >>> 27)) + m1395h(iM1395h3, i18, i16) + this.f3061X[i17] + f3053Y2;
            i5 = (iM1395h3 >>> 2) | (iM1395h3 << 30);
            int i20 = i19 + 1;
            iM1395h2 = i16 + ((iM1395h5 << 5) | (iM1395h5 >>> 27)) + m1395h(iM1395h4, i5, i18) + this.f3061X[i19] + f3053Y2;
            i4 = (iM1395h4 >>> 2) | (iM1395h4 << 30);
            iM1395h = i18 + ((iM1395h2 << 5) | (iM1395h2 >>> 27)) + m1395h(iM1395h5, i4, i5) + this.f3061X[i20] + f3053Y2;
            i3 = (iM1395h5 >>> 2) | (iM1395h5 << 30);
            i14++;
            i7 = i20 + 1;
        }
        int i21 = 0;
        while (i21 < 4) {
            int i22 = i7 + 1;
            int iM1394g = i5 + ((iM1395h << 5) | (iM1395h >>> 27)) + m1394g(iM1395h2, i3, i4) + this.f3061X[i7] + f3054Y3;
            int i23 = (iM1395h2 >>> 2) | (iM1395h2 << 30);
            int i24 = i22 + 1;
            int iM1394g2 = i4 + ((iM1394g << 5) | (iM1394g >>> 27)) + m1394g(iM1395h, i23, i3) + this.f3061X[i22] + f3054Y3;
            int i25 = (iM1395h >>> 2) | (iM1395h << 30);
            int i26 = i24 + 1;
            int iM1394g3 = i3 + ((iM1394g2 << 5) | (iM1394g2 >>> 27)) + m1394g(iM1394g, i25, i23) + this.f3061X[i24] + f3054Y3;
            i5 = (iM1394g >>> 2) | (iM1394g << 30);
            int i27 = i26 + 1;
            iM1395h2 = i23 + ((iM1394g3 << 5) | (iM1394g3 >>> 27)) + m1394g(iM1394g2, i5, i25) + this.f3061X[i26] + f3054Y3;
            i4 = (iM1394g2 >>> 2) | (iM1394g2 << 30);
            iM1395h = i25 + ((iM1395h2 << 5) | (iM1395h2 >>> 27)) + m1394g(iM1394g3, i4, i5) + this.f3061X[i27] + f3054Y3;
            i3 = (iM1394g3 >>> 2) | (iM1394g3 << 30);
            i21++;
            i7 = i27 + 1;
        }
        int i28 = 0;
        while (i28 <= 3) {
            int i29 = i7 + 1;
            int iM1395h6 = i5 + ((iM1395h << 5) | (iM1395h >>> 27)) + m1395h(iM1395h2, i3, i4) + this.f3061X[i7] + f3055Y4;
            int i30 = (iM1395h2 >>> 2) | (iM1395h2 << 30);
            int i31 = i29 + 1;
            int iM1395h7 = i4 + ((iM1395h6 << 5) | (iM1395h6 >>> 27)) + m1395h(iM1395h, i30, i3) + this.f3061X[i29] + f3055Y4;
            int i32 = (iM1395h >>> 2) | (iM1395h << 30);
            int i33 = i31 + 1;
            int iM1395h8 = i3 + ((iM1395h7 << 5) | (iM1395h7 >>> 27)) + m1395h(iM1395h6, i32, i30) + this.f3061X[i31] + f3055Y4;
            i5 = (iM1395h6 >>> 2) | (iM1395h6 << 30);
            int i34 = i33 + 1;
            iM1395h2 = i30 + ((iM1395h8 << 5) | (iM1395h8 >>> 27)) + m1395h(iM1395h7, i5, i32) + this.f3061X[i33] + f3055Y4;
            i4 = (iM1395h7 >>> 2) | (iM1395h7 << 30);
            iM1395h = i32 + ((iM1395h2 << 5) | (iM1395h2 >>> 27)) + m1395h(iM1395h8, i4, i5) + this.f3061X[i34] + f3055Y4;
            i3 = (iM1395h8 >>> 2) | (iM1395h8 << 30);
            i28++;
            i7 = i34 + 1;
        }
        this.f3056H1 += iM1395h;
        this.f3057H2 += iM1395h2;
        this.f3058H3 += i3;
        this.f3059H4 += i4;
        this.f3060H5 += i5;
        this.xOff = 0;
        for (int i35 = 0; i35 < 16; i35++) {
            this.f3061X[i35] = 0;
        }
    }

    @Override
    protected void processLength(long j) {
        if (this.xOff > 14) {
            processBlock();
        }
        int[] iArr = this.f3061X;
        iArr[14] = (int) (j >>> 32);
        iArr[15] = (int) j;
    }

    @Override
    protected void processWord(byte[] bArr, int i) {
        int i2 = bArr[i] << 24;
        int i3 = i + 1;
        int i4 = i2 | ((bArr[i3] & UByte.MAX_VALUE) << 16);
        int i5 = i3 + 1;
        int i6 = (bArr[i5 + 1] & UByte.MAX_VALUE) | i4 | ((bArr[i5] & UByte.MAX_VALUE) << 8);
        int[] iArr = this.f3061X;
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
        this.f3056H1 = 1732584193;
        this.f3057H2 = -271733879;
        this.f3058H3 = -1732584194;
        this.f3059H4 = 271733878;
        this.f3060H5 = -1009589776;
        this.xOff = 0;
        int i = 0;
        while (true) {
            int[] iArr = this.f3061X;
            if (i == iArr.length) {
                return;
            }
            iArr[i] = 0;
            i++;
        }
    }

    @Override
    public void reset(Memoable memoable) {
        SHA1Digest sHA1Digest = (SHA1Digest) memoable;
        super.copyIn((GeneralDigest) sHA1Digest);
        copyIn(sHA1Digest);
    }
}
