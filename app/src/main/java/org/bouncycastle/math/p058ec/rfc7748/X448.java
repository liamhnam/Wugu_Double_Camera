package org.bouncycastle.math.p058ec.rfc7748;

import java.security.SecureRandom;
import kotlin.UByte;
import org.bouncycastle.math.p058ec.rfc8032.Ed448;
import org.bouncycastle.util.Arrays;

public abstract class X448 {
    private static final int C_A = 156326;
    private static final int C_A24 = 39082;
    public static final int POINT_SIZE = 56;
    public static final int SCALAR_SIZE = 56;

    private static class C3203F extends X448Field {
        private C3203F() {
        }
    }

    public static class Friend {
        private static final Friend INSTANCE = new Friend();

        private Friend() {
        }
    }

    public static boolean calculateAgreement(byte[] bArr, int i, byte[] bArr2, int i2, byte[] bArr3, int i3) {
        scalarMult(bArr, i, bArr2, i2, bArr3, i3);
        return !Arrays.areAllZeroes(bArr3, i3, 56);
    }

    private static int decode32(byte[] bArr, int i) {
        int i2 = bArr[i] & UByte.MAX_VALUE;
        int i3 = i + 1;
        int i4 = i2 | ((bArr[i3] & UByte.MAX_VALUE) << 8);
        int i5 = i3 + 1;
        return (bArr[i5 + 1] << 24) | i4 | ((bArr[i5] & UByte.MAX_VALUE) << 16);
    }

    private static void decodeScalar(byte[] bArr, int i, int[] iArr) {
        for (int i2 = 0; i2 < 14; i2++) {
            iArr[i2] = decode32(bArr, (i2 * 4) + i);
        }
        iArr[0] = iArr[0] & (-4);
        iArr[13] = iArr[13] | Integer.MIN_VALUE;
    }

    public static void generatePrivateKey(SecureRandom secureRandom, byte[] bArr) {
        secureRandom.nextBytes(bArr);
        bArr[0] = (byte) (bArr[0] & 252);
        bArr[55] = (byte) (bArr[55] | 128);
    }

    public static void generatePublicKey(byte[] bArr, int i, byte[] bArr2, int i2) {
        scalarMultBase(bArr, i, bArr2, i2);
    }

    private static void pointDouble(int[] iArr, int[] iArr2) {
        int[] iArrCreate = C3203F.create();
        int[] iArrCreate2 = C3203F.create();
        C3203F.add(iArr, iArr2, iArrCreate);
        C3203F.sub(iArr, iArr2, iArrCreate2);
        C3203F.sqr(iArrCreate, iArrCreate);
        C3203F.sqr(iArrCreate2, iArrCreate2);
        C3203F.mul(iArrCreate, iArrCreate2, iArr);
        C3203F.sub(iArrCreate, iArrCreate2, iArrCreate);
        C3203F.mul(iArrCreate, C_A24, iArr2);
        C3203F.add(iArr2, iArrCreate2, iArr2);
        C3203F.mul(iArr2, iArrCreate, iArr2);
    }

    public static void precompute() {
        Ed448.precompute();
    }

    public static void scalarMult(byte[] bArr, int i, byte[] bArr2, int i2, byte[] bArr3, int i3) {
        int[] iArr = new int[14];
        decodeScalar(bArr, i, iArr);
        int[] iArrCreate = C3203F.create();
        C3203F.decode(bArr2, i2, iArrCreate);
        int[] iArrCreate2 = C3203F.create();
        C3203F.copy(iArrCreate, 0, iArrCreate2, 0);
        int[] iArrCreate3 = C3203F.create();
        iArrCreate3[0] = 1;
        int[] iArrCreate4 = C3203F.create();
        iArrCreate4[0] = 1;
        int[] iArrCreate5 = C3203F.create();
        int[] iArrCreate6 = C3203F.create();
        int[] iArrCreate7 = C3203F.create();
        int i4 = 447;
        int i5 = 1;
        while (true) {
            C3203F.add(iArrCreate4, iArrCreate5, iArrCreate6);
            C3203F.sub(iArrCreate4, iArrCreate5, iArrCreate4);
            C3203F.add(iArrCreate2, iArrCreate3, iArrCreate5);
            C3203F.sub(iArrCreate2, iArrCreate3, iArrCreate2);
            C3203F.mul(iArrCreate6, iArrCreate2, iArrCreate6);
            C3203F.mul(iArrCreate4, iArrCreate5, iArrCreate4);
            C3203F.sqr(iArrCreate5, iArrCreate5);
            C3203F.sqr(iArrCreate2, iArrCreate2);
            C3203F.sub(iArrCreate5, iArrCreate2, iArrCreate7);
            C3203F.mul(iArrCreate7, C_A24, iArrCreate3);
            C3203F.add(iArrCreate3, iArrCreate2, iArrCreate3);
            C3203F.mul(iArrCreate3, iArrCreate7, iArrCreate3);
            C3203F.mul(iArrCreate2, iArrCreate5, iArrCreate2);
            C3203F.sub(iArrCreate6, iArrCreate4, iArrCreate5);
            C3203F.add(iArrCreate6, iArrCreate4, iArrCreate4);
            C3203F.sqr(iArrCreate4, iArrCreate4);
            C3203F.sqr(iArrCreate5, iArrCreate5);
            C3203F.mul(iArrCreate5, iArrCreate, iArrCreate5);
            i4--;
            int i6 = (iArr[i4 >>> 5] >>> (i4 & 31)) & 1;
            int i7 = i5 ^ i6;
            C3203F.cswap(i7, iArrCreate2, iArrCreate4);
            C3203F.cswap(i7, iArrCreate3, iArrCreate5);
            if (i4 < 2) {
                break;
            } else {
                i5 = i6;
            }
        }
        for (int i8 = 0; i8 < 2; i8++) {
            pointDouble(iArrCreate2, iArrCreate3);
        }
        C3203F.inv(iArrCreate3, iArrCreate3);
        C3203F.mul(iArrCreate2, iArrCreate3, iArrCreate2);
        C3203F.normalize(iArrCreate2);
        C3203F.encode(iArrCreate2, bArr3, i3);
    }

    public static void scalarMultBase(byte[] bArr, int i, byte[] bArr2, int i2) {
        int[] iArrCreate = C3203F.create();
        int[] iArrCreate2 = C3203F.create();
        Ed448.scalarMultBaseXY(Friend.INSTANCE, bArr, i, iArrCreate, iArrCreate2);
        C3203F.inv(iArrCreate, iArrCreate);
        C3203F.mul(iArrCreate, iArrCreate2, iArrCreate);
        C3203F.sqr(iArrCreate, iArrCreate);
        C3203F.normalize(iArrCreate);
        C3203F.encode(iArrCreate, bArr2, i2);
    }
}
