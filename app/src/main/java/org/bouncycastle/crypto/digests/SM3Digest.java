package org.bouncycastle.crypto.digests;

import kotlin.UByte;
import org.bouncycastle.util.Memoable;
import org.bouncycastle.util.Pack;

public class SM3Digest extends GeneralDigest {
    private static final int BLOCK_SIZE = 16;
    private static final int DIGEST_LENGTH = 32;

    private static final int[] f3082T = new int[64];

    private int[] f3083V;

    private int[] f3084W;
    private int[] inwords;
    private int xOff;

    static {
        int i;
        int i2 = 0;
        while (true) {
            if (i2 >= 16) {
                break;
            }
            f3082T[i2] = (2043430169 >>> (32 - i2)) | (2043430169 << i2);
            i2++;
        }
        for (i = 16; i < 64; i++) {
            int i3 = i % 32;
            f3082T[i] = (2055708042 >>> (32 - i3)) | (2055708042 << i3);
        }
    }

    public SM3Digest() {
        this.f3083V = new int[8];
        this.inwords = new int[16];
        this.f3084W = new int[68];
        reset();
    }

    public SM3Digest(SM3Digest sM3Digest) {
        super(sM3Digest);
        this.f3083V = new int[8];
        this.inwords = new int[16];
        this.f3084W = new int[68];
        copyIn(sM3Digest);
    }

    private int FF0(int i, int i2, int i3) {
        return (i ^ i2) ^ i3;
    }

    private int FF1(int i, int i2, int i3) {
        return (i & i3) | (i & i2) | (i2 & i3);
    }

    private int GG0(int i, int i2, int i3) {
        return (i ^ i2) ^ i3;
    }

    private int GG1(int i, int i2, int i3) {
        return ((~i) & i3) | (i2 & i);
    }

    private int m1398P0(int i) {
        return (i ^ ((i << 9) | (i >>> 23))) ^ ((i << 17) | (i >>> 15));
    }

    private int m1399P1(int i) {
        return (i ^ ((i << 15) | (i >>> 17))) ^ ((i << 23) | (i >>> 9));
    }

    private void copyIn(SM3Digest sM3Digest) {
        int[] iArr = sM3Digest.f3083V;
        int[] iArr2 = this.f3083V;
        System.arraycopy(iArr, 0, iArr2, 0, iArr2.length);
        int[] iArr3 = sM3Digest.inwords;
        int[] iArr4 = this.inwords;
        System.arraycopy(iArr3, 0, iArr4, 0, iArr4.length);
        this.xOff = sM3Digest.xOff;
    }

    @Override
    public Memoable copy() {
        return new SM3Digest(this);
    }

    @Override
    public int doFinal(byte[] bArr, int i) {
        finish();
        Pack.intToBigEndian(this.f3083V, bArr, i);
        reset();
        return 32;
    }

    @Override
    public String getAlgorithmName() {
        return "SM3";
    }

    @Override
    public int getDigestSize() {
        return 32;
    }

    @Override
    protected void processBlock() {
        int i;
        int i2 = 0;
        while (true) {
            if (i2 >= 16) {
                break;
            }
            this.f3084W[i2] = this.inwords[i2];
            i2++;
        }
        for (int i3 = 16; i3 < 68; i3++) {
            int[] iArr = this.f3084W;
            int i4 = iArr[i3 - 3];
            int i5 = iArr[i3 - 13];
            iArr[i3] = (m1399P1(((i4 >>> 17) | (i4 << 15)) ^ (iArr[i3 - 16] ^ iArr[i3 - 9])) ^ ((i5 >>> 25) | (i5 << 7))) ^ this.f3084W[i3 - 6];
        }
        int[] iArr2 = this.f3083V;
        int i6 = iArr2[0];
        int i7 = iArr2[1];
        int i8 = iArr2[2];
        int i9 = iArr2[3];
        int iM1398P0 = iArr2[4];
        int i10 = iArr2[5];
        int i11 = iArr2[6];
        int i12 = iArr2[7];
        int i13 = 0;
        int i14 = i11;
        for (i = 16; i13 < i; i = 16) {
            int i15 = (i6 << 12) | (i6 >>> 20);
            int i16 = i15 + iM1398P0 + f3082T[i13];
            int i17 = (i16 << 7) | (i16 >>> 25);
            int[] iArr3 = this.f3084W;
            int i18 = iArr3[i13];
            int i19 = i18 ^ iArr3[i13 + 4];
            int iFF0 = FF0(i6, i7, i8) + i9;
            int iGG0 = GG0(iM1398P0, i10, i14) + i12 + i17 + i18;
            int i20 = (i7 << 9) | (i7 >>> 23);
            int i21 = (i10 << 19) | (i10 >>> 13);
            i13++;
            i10 = iM1398P0;
            iM1398P0 = m1398P0(iGG0);
            i9 = i8;
            i8 = i20;
            i12 = i14;
            i14 = i21;
            i7 = i6;
            i6 = iFF0 + (i17 ^ i15) + i19;
        }
        int i22 = i12;
        int iM1398P02 = iM1398P0;
        int i23 = i14;
        int i24 = i9;
        int i25 = i8;
        int i26 = i7;
        int i27 = i6;
        int i28 = 16;
        while (i28 < 64) {
            int i29 = (i27 << 12) | (i27 >>> 20);
            int i30 = i29 + iM1398P02 + f3082T[i28];
            int i31 = (i30 << 7) | (i30 >>> 25);
            int[] iArr4 = this.f3084W;
            int i32 = iArr4[i28];
            int i33 = i32 ^ iArr4[i28 + 4];
            int iFF1 = FF1(i27, i26, i25) + i24;
            int iGG1 = GG1(iM1398P02, i10, i23) + i22 + i31 + i32;
            int i34 = (i10 << 19) | (i10 >>> 13);
            i28++;
            i10 = iM1398P02;
            iM1398P02 = m1398P0(iGG1);
            i24 = i25;
            i25 = (i26 >>> 23) | (i26 << 9);
            i26 = i27;
            i27 = iFF1 + (i31 ^ i29) + i33;
            i22 = i23;
            i23 = i34;
        }
        int[] iArr5 = this.f3083V;
        iArr5[0] = i27 ^ iArr5[0];
        iArr5[1] = iArr5[1] ^ i26;
        iArr5[2] = iArr5[2] ^ i25;
        iArr5[3] = iArr5[3] ^ i24;
        iArr5[4] = iArr5[4] ^ iM1398P02;
        iArr5[5] = iArr5[5] ^ i10;
        iArr5[6] = i23 ^ iArr5[6];
        iArr5[7] = iArr5[7] ^ i22;
        this.xOff = 0;
    }

    @Override
    protected void processLength(long j) {
        int i = this.xOff;
        if (i > 14) {
            this.inwords[i] = 0;
            this.xOff = i + 1;
            processBlock();
        }
        while (true) {
            int i2 = this.xOff;
            if (i2 >= 14) {
                int[] iArr = this.inwords;
                int i3 = i2 + 1;
                iArr[i2] = (int) (j >>> 32);
                this.xOff = i3 + 1;
                iArr[i3] = (int) j;
                return;
            }
            this.inwords[i2] = 0;
            this.xOff = i2 + 1;
        }
    }

    @Override
    protected void processWord(byte[] bArr, int i) {
        int i2 = (bArr[i] & UByte.MAX_VALUE) << 24;
        int i3 = i + 1;
        int i4 = i2 | ((bArr[i3] & UByte.MAX_VALUE) << 16);
        int i5 = i3 + 1;
        int i6 = (bArr[i5 + 1] & UByte.MAX_VALUE) | i4 | ((bArr[i5] & UByte.MAX_VALUE) << 8);
        int[] iArr = this.inwords;
        int i7 = this.xOff;
        iArr[i7] = i6;
        int i8 = i7 + 1;
        this.xOff = i8;
        if (i8 >= 16) {
            processBlock();
        }
    }

    @Override
    public void reset() {
        super.reset();
        int[] iArr = this.f3083V;
        iArr[0] = 1937774191;
        iArr[1] = 1226093241;
        iArr[2] = 388252375;
        iArr[3] = -628488704;
        iArr[4] = -1452330820;
        iArr[5] = 372324522;
        iArr[6] = -477237683;
        iArr[7] = -1325724082;
        this.xOff = 0;
    }

    @Override
    public void reset(Memoable memoable) {
        SM3Digest sM3Digest = (SM3Digest) memoable;
        super.copyIn((GeneralDigest) sM3Digest);
        copyIn(sM3Digest);
    }
}
