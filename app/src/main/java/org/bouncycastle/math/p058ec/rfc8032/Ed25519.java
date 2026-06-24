package org.bouncycastle.math.p058ec.rfc8032;

import cc.uling.usdk.constants.ErrorConst;
import com.brother.sdk.common.socket.devicemanagement.snmp.westhawk.SnmpConstants;
import java.security.SecureRandom;
import kotlin.UByte;
import kotlin.jvm.internal.ByteCompanionObject;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.digests.SHA512Digest;
import org.bouncycastle.math.p058ec.rfc7748.X25519;
import org.bouncycastle.math.p058ec.rfc7748.X25519Field;
import org.bouncycastle.math.raw.Interleave;
import org.bouncycastle.math.raw.Nat;
import org.bouncycastle.math.raw.Nat256;
import org.bouncycastle.util.Arrays;

public abstract class Ed25519 {
    private static final int COORD_INTS = 8;

    private static final int f3596L0 = -50998291;

    private static final int f3597L1 = 19280294;

    private static final int f3598L2 = 127719000;

    private static final int f3599L3 = -6428113;

    private static final int f3600L4 = 5343;
    private static final long M08L = 255;
    private static final long M28L = 268435455;
    private static final long M32L = 4294967295L;
    private static final int POINT_BYTES = 32;
    private static final int PRECOMP_BLOCKS = 8;
    private static final int PRECOMP_MASK = 7;
    private static final int PRECOMP_POINTS = 8;
    private static final int PRECOMP_SPACING = 8;
    private static final int PRECOMP_TEETH = 4;
    public static final int PREHASH_SIZE = 64;
    public static final int PUBLIC_KEY_SIZE = 32;
    private static final int SCALAR_BYTES = 32;
    private static final int SCALAR_INTS = 8;
    public static final int SECRET_KEY_SIZE = 32;
    public static final int SIGNATURE_SIZE = 64;
    private static final int WNAF_WIDTH_BASE = 7;
    private static final byte[] DOM2_PREFIX = {83, 105, 103, SnmpConstants.NSAP_ADDRESS, 100, 50, 53, 53, 49, 57, 32, 110, 111, 32, SnmpConstants.NSAP_ADDRESS, 100, 50, 53, 53, 49, 57, 32, 99, 111, 108, 108, 105, 115, 105, 111, 110, 115};

    private static final int[] f3601P = {-19, -1, -1, -1, -1, -1, -1, Integer.MAX_VALUE};

    private static final int[] f3595L = {1559614445, 1477600026, -1560830762, 350157278, 0, 0, 0, 268435456};
    private static final int[] B_x = {52811034, 25909283, 8072341, 50637101, 13785486, 30858332, 20483199, 20966410, 43936626, 4379245};
    private static final int[] B_y = {40265304, 26843545, 6710886, 53687091, 13421772, 40265318, 26843545, 6710886, 53687091, 13421772};
    private static final int[] C_d = {56195235, 47411844, 25868126, 40503822, 57364, 58321048, 30416477, 31930572, 57760639, 10749657};
    private static final int[] C_d2 = {45281625, 27714825, 18181821, 13898781, 114729, 49533232, 60832955, 30306712, 48412415, 4722099};
    private static final int[] C_d4 = {23454386, 55429651, 2809210, 27797563, 229458, 31957600, 54557047, 27058993, 29715967, 9444199};
    private static final Object precompLock = new Object();
    private static PointExt[] precompBaseTable = null;
    private static int[] precompBase = null;

    public static final class Algorithm {
        public static final int Ed25519 = 0;
        public static final int Ed25519ctx = 1;
        public static final int Ed25519ph = 2;
    }

    private static class C3205F extends X25519Field {
        private C3205F() {
        }
    }

    private static class PointAccum {

        int[] f3602u;

        int[] f3603v;

        int[] f3604x;

        int[] f3605y;

        int[] f3606z;

        private PointAccum() {
            this.f3604x = C3205F.create();
            this.f3605y = C3205F.create();
            this.f3606z = C3205F.create();
            this.f3602u = C3205F.create();
            this.f3603v = C3205F.create();
        }
    }

    private static class PointAffine {

        int[] f3607x;

        int[] f3608y;

        private PointAffine() {
            this.f3607x = C3205F.create();
            this.f3608y = C3205F.create();
        }
    }

    private static class PointExt {

        int[] f3609t;

        int[] f3610x;

        int[] f3611y;

        int[] f3612z;

        private PointExt() {
            this.f3610x = C3205F.create();
            this.f3611y = C3205F.create();
            this.f3612z = C3205F.create();
            this.f3609t = C3205F.create();
        }
    }

    private static class PointPrecomp {
        int[] xyd;
        int[] ymx_h;
        int[] ypx_h;

        private PointPrecomp() {
            this.ypx_h = C3205F.create();
            this.ymx_h = C3205F.create();
            this.xyd = C3205F.create();
        }
    }

    private static byte[] calculateS(byte[] bArr, byte[] bArr2, byte[] bArr3) {
        int[] iArr = new int[16];
        decodeScalar(bArr, 0, iArr);
        int[] iArr2 = new int[8];
        decodeScalar(bArr2, 0, iArr2);
        int[] iArr3 = new int[8];
        decodeScalar(bArr3, 0, iArr3);
        Nat256.mulAddTo(iArr2, iArr3, iArr);
        byte[] bArr4 = new byte[64];
        for (int i = 0; i < 16; i++) {
            encode32(iArr[i], bArr4, i * 4);
        }
        return reduceScalar(bArr4);
    }

    private static boolean checkContextVar(byte[] bArr, byte b) {
        return (bArr == null && b == 0) || (bArr != null && bArr.length < 256);
    }

    private static int checkPoint(int[] iArr, int[] iArr2) {
        int[] iArrCreate = C3205F.create();
        int[] iArrCreate2 = C3205F.create();
        int[] iArrCreate3 = C3205F.create();
        C3205F.sqr(iArr, iArrCreate2);
        C3205F.sqr(iArr2, iArrCreate3);
        C3205F.mul(iArrCreate2, iArrCreate3, iArrCreate);
        C3205F.sub(iArrCreate3, iArrCreate2, iArrCreate3);
        C3205F.mul(iArrCreate, C_d, iArrCreate);
        C3205F.addOne(iArrCreate);
        C3205F.sub(iArrCreate, iArrCreate3, iArrCreate);
        C3205F.normalize(iArrCreate);
        return C3205F.isZero(iArrCreate);
    }

    private static int checkPoint(int[] iArr, int[] iArr2, int[] iArr3) {
        int[] iArrCreate = C3205F.create();
        int[] iArrCreate2 = C3205F.create();
        int[] iArrCreate3 = C3205F.create();
        int[] iArrCreate4 = C3205F.create();
        C3205F.sqr(iArr, iArrCreate2);
        C3205F.sqr(iArr2, iArrCreate3);
        C3205F.sqr(iArr3, iArrCreate4);
        C3205F.mul(iArrCreate2, iArrCreate3, iArrCreate);
        C3205F.sub(iArrCreate3, iArrCreate2, iArrCreate3);
        C3205F.mul(iArrCreate3, iArrCreate4, iArrCreate3);
        C3205F.sqr(iArrCreate4, iArrCreate4);
        C3205F.mul(iArrCreate, C_d, iArrCreate);
        C3205F.add(iArrCreate, iArrCreate4, iArrCreate);
        C3205F.sub(iArrCreate, iArrCreate3, iArrCreate);
        C3205F.normalize(iArrCreate);
        return C3205F.isZero(iArrCreate);
    }

    private static boolean checkPointVar(byte[] bArr) {
        int[] iArr = new int[8];
        decode32(bArr, 0, iArr, 0, 8);
        iArr[7] = iArr[7] & Integer.MAX_VALUE;
        return !Nat256.gte(iArr, f3601P);
    }

    private static boolean checkScalarVar(byte[] bArr, int[] iArr) {
        decodeScalar(bArr, 0, iArr);
        return !Nat256.gte(iArr, f3595L);
    }

    private static byte[] copy(byte[] bArr, int i, int i2) {
        byte[] bArr2 = new byte[i2];
        System.arraycopy(bArr, i, bArr2, 0, i2);
        return bArr2;
    }

    private static Digest createDigest() {
        return new SHA512Digest();
    }

    public static Digest createPrehash() {
        return createDigest();
    }

    private static int decode24(byte[] bArr, int i) {
        int i2 = bArr[i] & UByte.MAX_VALUE;
        int i3 = i + 1;
        return ((bArr[i3 + 1] & UByte.MAX_VALUE) << 16) | i2 | ((bArr[i3] & UByte.MAX_VALUE) << 8);
    }

    private static int decode32(byte[] bArr, int i) {
        int i2 = bArr[i] & UByte.MAX_VALUE;
        int i3 = i + 1;
        int i4 = i2 | ((bArr[i3] & UByte.MAX_VALUE) << 8);
        int i5 = i3 + 1;
        return (bArr[i5 + 1] << 24) | i4 | ((bArr[i5] & UByte.MAX_VALUE) << 16);
    }

    private static void decode32(byte[] bArr, int i, int[] iArr, int i2, int i3) {
        for (int i4 = 0; i4 < i3; i4++) {
            iArr[i2 + i4] = decode32(bArr, (i4 * 4) + i);
        }
    }

    private static boolean decodePointVar(byte[] bArr, int i, boolean z, PointAffine pointAffine) {
        byte[] bArrCopy = copy(bArr, i, 32);
        if (!checkPointVar(bArrCopy)) {
            return false;
        }
        byte b = bArrCopy[31];
        int i2 = (b & 128) >>> 7;
        bArrCopy[31] = (byte) (b & ByteCompanionObject.MAX_VALUE);
        C3205F.decode(bArrCopy, 0, pointAffine.f3608y);
        int[] iArrCreate = C3205F.create();
        int[] iArrCreate2 = C3205F.create();
        C3205F.sqr(pointAffine.f3608y, iArrCreate);
        C3205F.mul(C_d, iArrCreate, iArrCreate2);
        C3205F.subOne(iArrCreate);
        C3205F.addOne(iArrCreate2);
        if (!C3205F.sqrtRatioVar(iArrCreate, iArrCreate2, pointAffine.f3607x)) {
            return false;
        }
        C3205F.normalize(pointAffine.f3607x);
        if (i2 == 1 && C3205F.isZeroVar(pointAffine.f3607x)) {
            return false;
        }
        if (z ^ (i2 != (pointAffine.f3607x[0] & 1))) {
            C3205F.negate(pointAffine.f3607x, pointAffine.f3607x);
        }
        return true;
    }

    private static void decodeScalar(byte[] bArr, int i, int[] iArr) {
        decode32(bArr, i, iArr, 0, 8);
    }

    private static void dom2(Digest digest, byte b, byte[] bArr) {
        if (bArr != null) {
            byte[] bArr2 = DOM2_PREFIX;
            int length = bArr2.length;
            int i = length + 2;
            int length2 = bArr.length + i;
            byte[] bArr3 = new byte[length2];
            System.arraycopy(bArr2, 0, bArr3, 0, length);
            bArr3[length] = b;
            bArr3[length + 1] = (byte) bArr.length;
            System.arraycopy(bArr, 0, bArr3, i, bArr.length);
            digest.update(bArr3, 0, length2);
        }
    }

    private static void encode24(int i, byte[] bArr, int i2) {
        bArr[i2] = (byte) i;
        int i3 = i2 + 1;
        bArr[i3] = (byte) (i >>> 8);
        bArr[i3 + 1] = (byte) (i >>> 16);
    }

    private static void encode32(int i, byte[] bArr, int i2) {
        bArr[i2] = (byte) i;
        int i3 = i2 + 1;
        bArr[i3] = (byte) (i >>> 8);
        int i4 = i3 + 1;
        bArr[i4] = (byte) (i >>> 16);
        bArr[i4 + 1] = (byte) (i >>> 24);
    }

    private static void encode56(long j, byte[] bArr, int i) {
        encode32((int) j, bArr, i);
        encode24((int) (j >>> 32), bArr, i + 4);
    }

    private static int encodePoint(PointAccum pointAccum, byte[] bArr, int i) {
        int[] iArrCreate = C3205F.create();
        int[] iArrCreate2 = C3205F.create();
        C3205F.inv(pointAccum.f3606z, iArrCreate2);
        C3205F.mul(pointAccum.f3604x, iArrCreate2, iArrCreate);
        C3205F.mul(pointAccum.f3605y, iArrCreate2, iArrCreate2);
        C3205F.normalize(iArrCreate);
        C3205F.normalize(iArrCreate2);
        int iCheckPoint = checkPoint(iArrCreate, iArrCreate2);
        C3205F.encode(iArrCreate2, bArr, i);
        int i2 = (i + 32) - 1;
        bArr[i2] = (byte) (((iArrCreate[0] & 1) << 7) | bArr[i2]);
        return iCheckPoint;
    }

    public static void generatePrivateKey(SecureRandom secureRandom, byte[] bArr) {
        secureRandom.nextBytes(bArr);
    }

    public static void generatePublicKey(byte[] bArr, int i, byte[] bArr2, int i2) {
        Digest digestCreateDigest = createDigest();
        byte[] bArr3 = new byte[digestCreateDigest.getDigestSize()];
        digestCreateDigest.update(bArr, i, 32);
        digestCreateDigest.doFinal(bArr3, 0);
        byte[] bArr4 = new byte[32];
        pruneScalar(bArr3, 0, bArr4);
        scalarMultBaseEncoded(bArr4, bArr2, i2);
    }

    private static int getWindow4(int[] iArr, int i) {
        return (iArr[i >>> 3] >>> ((i & 7) << 2)) & 15;
    }

    private static byte[] getWnafVar(int[] iArr, int i) {
        int[] iArr2 = new int[16];
        int i2 = 0;
        int i3 = 8;
        int i4 = 16;
        int i5 = 0;
        while (true) {
            i3--;
            if (i3 < 0) {
                break;
            }
            int i6 = iArr[i3];
            int i7 = i4 - 1;
            iArr2[i7] = (i5 << 16) | (i6 >>> 16);
            i4 = i7 - 1;
            iArr2[i4] = i6;
            i5 = i6;
        }
        byte[] bArr = new byte[253];
        int i8 = 32 - i;
        int i9 = 0;
        int i10 = 0;
        while (i2 < 16) {
            int i11 = iArr2[i2];
            while (i9 < 16) {
                int i12 = i11 >>> i9;
                if ((i12 & 1) == i10) {
                    i9++;
                } else {
                    int i13 = (i12 | 1) << i8;
                    bArr[(i2 << 4) + i9] = (byte) (i13 >> i8);
                    i9 += i;
                    i10 = i13 >>> 31;
                }
            }
            i2++;
            i9 -= 16;
        }
        return bArr;
    }

    private static void implSign(Digest digest, byte[] bArr, byte[] bArr2, byte[] bArr3, int i, byte[] bArr4, byte b, byte[] bArr5, int i2, int i3, byte[] bArr6, int i4) {
        dom2(digest, b, bArr4);
        digest.update(bArr, 32, 32);
        digest.update(bArr5, i2, i3);
        digest.doFinal(bArr, 0);
        byte[] bArrReduceScalar = reduceScalar(bArr);
        byte[] bArr7 = new byte[32];
        scalarMultBaseEncoded(bArrReduceScalar, bArr7, 0);
        dom2(digest, b, bArr4);
        digest.update(bArr7, 0, 32);
        digest.update(bArr3, i, 32);
        digest.update(bArr5, i2, i3);
        digest.doFinal(bArr, 0);
        byte[] bArrCalculateS = calculateS(bArrReduceScalar, reduceScalar(bArr), bArr2);
        System.arraycopy(bArr7, 0, bArr6, i4, 32);
        System.arraycopy(bArrCalculateS, 0, bArr6, i4 + 32, 32);
    }

    private static void implSign(byte[] bArr, int i, byte[] bArr2, byte b, byte[] bArr3, int i2, int i3, byte[] bArr4, int i4) {
        if (!checkContextVar(bArr2, b)) {
            throw new IllegalArgumentException("ctx");
        }
        Digest digestCreateDigest = createDigest();
        byte[] bArr5 = new byte[digestCreateDigest.getDigestSize()];
        digestCreateDigest.update(bArr, i, 32);
        digestCreateDigest.doFinal(bArr5, 0);
        byte[] bArr6 = new byte[32];
        pruneScalar(bArr5, 0, bArr6);
        byte[] bArr7 = new byte[32];
        scalarMultBaseEncoded(bArr6, bArr7, 0);
        implSign(digestCreateDigest, bArr5, bArr6, bArr7, 0, bArr2, b, bArr3, i2, i3, bArr4, i4);
    }

    private static void implSign(byte[] bArr, int i, byte[] bArr2, int i2, byte[] bArr3, byte b, byte[] bArr4, int i3, int i4, byte[] bArr5, int i5) {
        if (!checkContextVar(bArr3, b)) {
            throw new IllegalArgumentException("ctx");
        }
        Digest digestCreateDigest = createDigest();
        byte[] bArr6 = new byte[digestCreateDigest.getDigestSize()];
        digestCreateDigest.update(bArr, i, 32);
        digestCreateDigest.doFinal(bArr6, 0);
        byte[] bArr7 = new byte[32];
        pruneScalar(bArr6, 0, bArr7);
        implSign(digestCreateDigest, bArr6, bArr7, bArr2, i2, bArr3, b, bArr4, i3, i4, bArr5, i5);
    }

    private static boolean implVerify(byte[] bArr, int i, byte[] bArr2, int i2, byte[] bArr3, byte b, byte[] bArr4, int i3, int i4) {
        if (!checkContextVar(bArr3, b)) {
            throw new IllegalArgumentException("ctx");
        }
        byte[] bArrCopy = copy(bArr, i, 32);
        byte[] bArrCopy2 = copy(bArr, i + 32, 32);
        if (!checkPointVar(bArrCopy)) {
            return false;
        }
        int[] iArr = new int[8];
        if (!checkScalarVar(bArrCopy2, iArr)) {
            return false;
        }
        PointAffine pointAffine = new PointAffine();
        if (!decodePointVar(bArr2, i2, true, pointAffine)) {
            return false;
        }
        Digest digestCreateDigest = createDigest();
        byte[] bArr5 = new byte[digestCreateDigest.getDigestSize()];
        dom2(digestCreateDigest, b, bArr3);
        digestCreateDigest.update(bArrCopy, 0, 32);
        digestCreateDigest.update(bArr2, i2, 32);
        digestCreateDigest.update(bArr4, i3, i4);
        digestCreateDigest.doFinal(bArr5, 0);
        int[] iArr2 = new int[8];
        decodeScalar(reduceScalar(bArr5), 0, iArr2);
        PointAccum pointAccum = new PointAccum();
        scalarMultStrausVar(iArr, iArr2, pointAffine, pointAccum);
        byte[] bArr6 = new byte[32];
        return encodePoint(pointAccum, bArr6, 0) != 0 && Arrays.areEqual(bArr6, bArrCopy);
    }

    private static boolean isNeutralElementVar(int[] iArr, int[] iArr2) {
        return C3205F.isZeroVar(iArr) && C3205F.isOneVar(iArr2);
    }

    private static boolean isNeutralElementVar(int[] iArr, int[] iArr2, int[] iArr3) {
        return C3205F.isZeroVar(iArr) && C3205F.areEqualVar(iArr2, iArr3);
    }

    private static void pointAdd(PointExt pointExt, PointAccum pointAccum) {
        int[] iArrCreate = C3205F.create();
        int[] iArrCreate2 = C3205F.create();
        int[] iArrCreate3 = C3205F.create();
        int[] iArrCreate4 = C3205F.create();
        int[] iArr = pointAccum.f3602u;
        int[] iArrCreate5 = C3205F.create();
        int[] iArrCreate6 = C3205F.create();
        int[] iArr2 = pointAccum.f3603v;
        C3205F.apm(pointAccum.f3605y, pointAccum.f3604x, iArrCreate2, iArrCreate);
        C3205F.apm(pointExt.f3611y, pointExt.f3610x, iArrCreate4, iArrCreate3);
        C3205F.mul(iArrCreate, iArrCreate3, iArrCreate);
        C3205F.mul(iArrCreate2, iArrCreate4, iArrCreate2);
        C3205F.mul(pointAccum.f3602u, pointAccum.f3603v, iArrCreate3);
        C3205F.mul(iArrCreate3, pointExt.f3609t, iArrCreate3);
        C3205F.mul(iArrCreate3, C_d2, iArrCreate3);
        C3205F.mul(pointAccum.f3606z, pointExt.f3612z, iArrCreate4);
        C3205F.add(iArrCreate4, iArrCreate4, iArrCreate4);
        C3205F.apm(iArrCreate2, iArrCreate, iArr2, iArr);
        C3205F.apm(iArrCreate4, iArrCreate3, iArrCreate6, iArrCreate5);
        C3205F.carry(iArrCreate6);
        C3205F.mul(iArr, iArrCreate5, pointAccum.f3604x);
        C3205F.mul(iArrCreate6, iArr2, pointAccum.f3605y);
        C3205F.mul(iArrCreate5, iArrCreate6, pointAccum.f3606z);
    }

    private static void pointAdd(PointExt pointExt, PointExt pointExt2) {
        int[] iArrCreate = C3205F.create();
        int[] iArrCreate2 = C3205F.create();
        int[] iArrCreate3 = C3205F.create();
        int[] iArrCreate4 = C3205F.create();
        int[] iArrCreate5 = C3205F.create();
        int[] iArrCreate6 = C3205F.create();
        int[] iArrCreate7 = C3205F.create();
        int[] iArrCreate8 = C3205F.create();
        C3205F.apm(pointExt.f3611y, pointExt.f3610x, iArrCreate2, iArrCreate);
        C3205F.apm(pointExt2.f3611y, pointExt2.f3610x, iArrCreate4, iArrCreate3);
        C3205F.mul(iArrCreate, iArrCreate3, iArrCreate);
        C3205F.mul(iArrCreate2, iArrCreate4, iArrCreate2);
        C3205F.mul(pointExt.f3609t, pointExt2.f3609t, iArrCreate3);
        C3205F.mul(iArrCreate3, C_d2, iArrCreate3);
        C3205F.mul(pointExt.f3612z, pointExt2.f3612z, iArrCreate4);
        C3205F.add(iArrCreate4, iArrCreate4, iArrCreate4);
        C3205F.apm(iArrCreate2, iArrCreate, iArrCreate8, iArrCreate5);
        C3205F.apm(iArrCreate4, iArrCreate3, iArrCreate7, iArrCreate6);
        C3205F.carry(iArrCreate7);
        C3205F.mul(iArrCreate5, iArrCreate6, pointExt2.f3610x);
        C3205F.mul(iArrCreate7, iArrCreate8, pointExt2.f3611y);
        C3205F.mul(iArrCreate6, iArrCreate7, pointExt2.f3612z);
        C3205F.mul(iArrCreate5, iArrCreate8, pointExt2.f3609t);
    }

    private static void pointAddPrecomp(PointPrecomp pointPrecomp, PointAccum pointAccum) {
        int[] iArrCreate = C3205F.create();
        int[] iArrCreate2 = C3205F.create();
        int[] iArrCreate3 = C3205F.create();
        int[] iArr = pointAccum.f3602u;
        int[] iArrCreate4 = C3205F.create();
        int[] iArrCreate5 = C3205F.create();
        int[] iArr2 = pointAccum.f3603v;
        C3205F.apm(pointAccum.f3605y, pointAccum.f3604x, iArrCreate2, iArrCreate);
        C3205F.mul(iArrCreate, pointPrecomp.ymx_h, iArrCreate);
        C3205F.mul(iArrCreate2, pointPrecomp.ypx_h, iArrCreate2);
        C3205F.mul(pointAccum.f3602u, pointAccum.f3603v, iArrCreate3);
        C3205F.mul(iArrCreate3, pointPrecomp.xyd, iArrCreate3);
        C3205F.apm(iArrCreate2, iArrCreate, iArr2, iArr);
        C3205F.apm(pointAccum.f3606z, iArrCreate3, iArrCreate5, iArrCreate4);
        C3205F.carry(iArrCreate5);
        C3205F.mul(iArr, iArrCreate4, pointAccum.f3604x);
        C3205F.mul(iArrCreate5, iArr2, pointAccum.f3605y);
        C3205F.mul(iArrCreate4, iArrCreate5, pointAccum.f3606z);
    }

    private static void pointAddVar(boolean z, PointExt pointExt, PointAccum pointAccum) {
        int[] iArr;
        int[] iArr2;
        int[] iArr3;
        int[] iArr4;
        int[] iArrCreate = C3205F.create();
        int[] iArrCreate2 = C3205F.create();
        int[] iArrCreate3 = C3205F.create();
        int[] iArrCreate4 = C3205F.create();
        int[] iArr5 = pointAccum.f3602u;
        int[] iArrCreate5 = C3205F.create();
        int[] iArrCreate6 = C3205F.create();
        int[] iArr6 = pointAccum.f3603v;
        if (z) {
            iArr2 = iArrCreate3;
            iArr = iArrCreate4;
            iArr4 = iArrCreate5;
            iArr3 = iArrCreate6;
        } else {
            iArr = iArrCreate3;
            iArr2 = iArrCreate4;
            iArr3 = iArrCreate5;
            iArr4 = iArrCreate6;
        }
        C3205F.apm(pointAccum.f3605y, pointAccum.f3604x, iArrCreate2, iArrCreate);
        C3205F.apm(pointExt.f3611y, pointExt.f3610x, iArr2, iArr);
        C3205F.mul(iArrCreate, iArrCreate3, iArrCreate);
        C3205F.mul(iArrCreate2, iArrCreate4, iArrCreate2);
        C3205F.mul(pointAccum.f3602u, pointAccum.f3603v, iArrCreate3);
        C3205F.mul(iArrCreate3, pointExt.f3609t, iArrCreate3);
        C3205F.mul(iArrCreate3, C_d2, iArrCreate3);
        C3205F.mul(pointAccum.f3606z, pointExt.f3612z, iArrCreate4);
        C3205F.add(iArrCreate4, iArrCreate4, iArrCreate4);
        C3205F.apm(iArrCreate2, iArrCreate, iArr6, iArr5);
        C3205F.apm(iArrCreate4, iArrCreate3, iArr4, iArr3);
        C3205F.carry(iArr4);
        C3205F.mul(iArr5, iArrCreate5, pointAccum.f3604x);
        C3205F.mul(iArrCreate6, iArr6, pointAccum.f3605y);
        C3205F.mul(iArrCreate5, iArrCreate6, pointAccum.f3606z);
    }

    private static void pointAddVar(boolean z, PointExt pointExt, PointExt pointExt2, PointExt pointExt3) {
        int[] iArr;
        int[] iArr2;
        int[] iArr3;
        int[] iArr4;
        int[] iArrCreate = C3205F.create();
        int[] iArrCreate2 = C3205F.create();
        int[] iArrCreate3 = C3205F.create();
        int[] iArrCreate4 = C3205F.create();
        int[] iArrCreate5 = C3205F.create();
        int[] iArrCreate6 = C3205F.create();
        int[] iArrCreate7 = C3205F.create();
        int[] iArrCreate8 = C3205F.create();
        if (z) {
            iArr2 = iArrCreate3;
            iArr = iArrCreate4;
            iArr4 = iArrCreate6;
            iArr3 = iArrCreate7;
        } else {
            iArr = iArrCreate3;
            iArr2 = iArrCreate4;
            iArr3 = iArrCreate6;
            iArr4 = iArrCreate7;
        }
        C3205F.apm(pointExt.f3611y, pointExt.f3610x, iArrCreate2, iArrCreate);
        C3205F.apm(pointExt2.f3611y, pointExt2.f3610x, iArr2, iArr);
        C3205F.mul(iArrCreate, iArrCreate3, iArrCreate);
        C3205F.mul(iArrCreate2, iArrCreate4, iArrCreate2);
        C3205F.mul(pointExt.f3609t, pointExt2.f3609t, iArrCreate3);
        C3205F.mul(iArrCreate3, C_d2, iArrCreate3);
        C3205F.mul(pointExt.f3612z, pointExt2.f3612z, iArrCreate4);
        C3205F.add(iArrCreate4, iArrCreate4, iArrCreate4);
        C3205F.apm(iArrCreate2, iArrCreate, iArrCreate8, iArrCreate5);
        C3205F.apm(iArrCreate4, iArrCreate3, iArr4, iArr3);
        C3205F.carry(iArr4);
        C3205F.mul(iArrCreate5, iArrCreate6, pointExt3.f3610x);
        C3205F.mul(iArrCreate7, iArrCreate8, pointExt3.f3611y);
        C3205F.mul(iArrCreate6, iArrCreate7, pointExt3.f3612z);
        C3205F.mul(iArrCreate5, iArrCreate8, pointExt3.f3609t);
    }

    private static PointExt pointCopy(PointAccum pointAccum) {
        PointExt pointExt = new PointExt();
        C3205F.copy(pointAccum.f3604x, 0, pointExt.f3610x, 0);
        C3205F.copy(pointAccum.f3605y, 0, pointExt.f3611y, 0);
        C3205F.copy(pointAccum.f3606z, 0, pointExt.f3612z, 0);
        C3205F.mul(pointAccum.f3602u, pointAccum.f3603v, pointExt.f3609t);
        return pointExt;
    }

    private static PointExt pointCopy(PointAffine pointAffine) {
        PointExt pointExt = new PointExt();
        C3205F.copy(pointAffine.f3607x, 0, pointExt.f3610x, 0);
        C3205F.copy(pointAffine.f3608y, 0, pointExt.f3611y, 0);
        pointExtendXY(pointExt);
        return pointExt;
    }

    private static PointExt pointCopy(PointExt pointExt) {
        PointExt pointExt2 = new PointExt();
        pointCopy(pointExt, pointExt2);
        return pointExt2;
    }

    private static void pointCopy(PointAffine pointAffine, PointAccum pointAccum) {
        C3205F.copy(pointAffine.f3607x, 0, pointAccum.f3604x, 0);
        C3205F.copy(pointAffine.f3608y, 0, pointAccum.f3605y, 0);
        pointExtendXY(pointAccum);
    }

    private static void pointCopy(PointExt pointExt, PointExt pointExt2) {
        C3205F.copy(pointExt.f3610x, 0, pointExt2.f3610x, 0);
        C3205F.copy(pointExt.f3611y, 0, pointExt2.f3611y, 0);
        C3205F.copy(pointExt.f3612z, 0, pointExt2.f3612z, 0);
        C3205F.copy(pointExt.f3609t, 0, pointExt2.f3609t, 0);
    }

    private static void pointDouble(PointAccum pointAccum) {
        int[] iArrCreate = C3205F.create();
        int[] iArrCreate2 = C3205F.create();
        int[] iArrCreate3 = C3205F.create();
        int[] iArr = pointAccum.f3602u;
        int[] iArrCreate4 = C3205F.create();
        int[] iArrCreate5 = C3205F.create();
        int[] iArr2 = pointAccum.f3603v;
        C3205F.sqr(pointAccum.f3604x, iArrCreate);
        C3205F.sqr(pointAccum.f3605y, iArrCreate2);
        C3205F.sqr(pointAccum.f3606z, iArrCreate3);
        C3205F.add(iArrCreate3, iArrCreate3, iArrCreate3);
        C3205F.apm(iArrCreate, iArrCreate2, iArr2, iArrCreate5);
        C3205F.add(pointAccum.f3604x, pointAccum.f3605y, iArr);
        C3205F.sqr(iArr, iArr);
        C3205F.sub(iArr2, iArr, iArr);
        C3205F.add(iArrCreate3, iArrCreate5, iArrCreate4);
        C3205F.carry(iArrCreate4);
        C3205F.mul(iArr, iArrCreate4, pointAccum.f3604x);
        C3205F.mul(iArrCreate5, iArr2, pointAccum.f3605y);
        C3205F.mul(iArrCreate4, iArrCreate5, pointAccum.f3606z);
    }

    private static void pointExtendXY(PointAccum pointAccum) {
        C3205F.one(pointAccum.f3606z);
        C3205F.copy(pointAccum.f3604x, 0, pointAccum.f3602u, 0);
        C3205F.copy(pointAccum.f3605y, 0, pointAccum.f3603v, 0);
    }

    private static void pointExtendXY(PointExt pointExt) {
        C3205F.one(pointExt.f3612z);
        C3205F.mul(pointExt.f3610x, pointExt.f3611y, pointExt.f3609t);
    }

    private static void pointLookup(int i, int i2, PointPrecomp pointPrecomp) {
        int i3 = i * 8 * 3 * 10;
        for (int i4 = 0; i4 < 8; i4++) {
            int i5 = ((i4 ^ i2) - 1) >> 31;
            C3205F.cmov(i5, precompBase, i3, pointPrecomp.ypx_h, 0);
            int i6 = i3 + 10;
            C3205F.cmov(i5, precompBase, i6, pointPrecomp.ymx_h, 0);
            int i7 = i6 + 10;
            C3205F.cmov(i5, precompBase, i7, pointPrecomp.xyd, 0);
            i3 = i7 + 10;
        }
    }

    private static void pointLookup(int[] iArr, int i, PointExt pointExt) {
        int i2 = i * 40;
        C3205F.copy(iArr, i2, pointExt.f3610x, 0);
        int i3 = i2 + 10;
        C3205F.copy(iArr, i3, pointExt.f3611y, 0);
        int i4 = i3 + 10;
        C3205F.copy(iArr, i4, pointExt.f3612z, 0);
        C3205F.copy(iArr, i4 + 10, pointExt.f3609t, 0);
    }

    private static void pointLookup(int[] iArr, int i, int[] iArr2, PointExt pointExt) {
        int window4 = getWindow4(iArr, i);
        int i2 = (window4 >>> 3) ^ 1;
        int i3 = (window4 ^ (-i2)) & 7;
        int i4 = 0;
        for (int i5 = 0; i5 < 8; i5++) {
            int i6 = ((i5 ^ i3) - 1) >> 31;
            C3205F.cmov(i6, iArr2, i4, pointExt.f3610x, 0);
            int i7 = i4 + 10;
            C3205F.cmov(i6, iArr2, i7, pointExt.f3611y, 0);
            int i8 = i7 + 10;
            C3205F.cmov(i6, iArr2, i8, pointExt.f3612z, 0);
            int i9 = i8 + 10;
            C3205F.cmov(i6, iArr2, i9, pointExt.f3609t, 0);
            i4 = i9 + 10;
        }
        C3205F.cnegate(i2, pointExt.f3610x);
        C3205F.cnegate(i2, pointExt.f3609t);
    }

    private static int[] pointPrecompute(PointAffine pointAffine, int i) {
        PointExt pointExtPointCopy = pointCopy(pointAffine);
        PointExt pointExtPointCopy2 = pointCopy(pointExtPointCopy);
        pointAdd(pointExtPointCopy, pointExtPointCopy2);
        int[] iArrCreateTable = C3205F.createTable(i * 4);
        int i2 = 0;
        int i3 = 0;
        while (true) {
            C3205F.copy(pointExtPointCopy.f3610x, 0, iArrCreateTable, i2);
            int i4 = i2 + 10;
            C3205F.copy(pointExtPointCopy.f3611y, 0, iArrCreateTable, i4);
            int i5 = i4 + 10;
            C3205F.copy(pointExtPointCopy.f3612z, 0, iArrCreateTable, i5);
            int i6 = i5 + 10;
            C3205F.copy(pointExtPointCopy.f3609t, 0, iArrCreateTable, i6);
            i2 = i6 + 10;
            i3++;
            if (i3 == i) {
                return iArrCreateTable;
            }
            pointAdd(pointExtPointCopy2, pointExtPointCopy);
        }
    }

    private static PointExt[] pointPrecomputeVar(PointExt pointExt, int i) {
        PointExt pointExt2 = new PointExt();
        pointAddVar(false, pointExt, pointExt, pointExt2);
        PointExt[] pointExtArr = new PointExt[i];
        pointExtArr[0] = pointCopy(pointExt);
        for (int i2 = 1; i2 < i; i2++) {
            PointExt pointExt3 = pointExtArr[i2 - 1];
            PointExt pointExt4 = new PointExt();
            pointExtArr[i2] = pointExt4;
            pointAddVar(false, pointExt3, pointExt2, pointExt4);
        }
        return pointExtArr;
    }

    private static void pointSetNeutral(PointAccum pointAccum) {
        C3205F.zero(pointAccum.f3604x);
        C3205F.one(pointAccum.f3605y);
        C3205F.one(pointAccum.f3606z);
        C3205F.zero(pointAccum.f3602u);
        C3205F.one(pointAccum.f3603v);
    }

    private static void pointSetNeutral(PointExt pointExt) {
        C3205F.zero(pointExt.f3610x);
        C3205F.one(pointExt.f3611y);
        C3205F.one(pointExt.f3612z);
        C3205F.zero(pointExt.f3609t);
    }

    public static void precompute() {
        int i;
        synchronized (precompLock) {
            if (precompBase != null) {
                return;
            }
            PointExt pointExt = new PointExt();
            int[] iArr = B_x;
            C3205F.copy(iArr, 0, pointExt.f3610x, 0);
            int[] iArr2 = B_y;
            C3205F.copy(iArr2, 0, pointExt.f3611y, 0);
            pointExtendXY(pointExt);
            precompBaseTable = pointPrecomputeVar(pointExt, 32);
            PointAccum pointAccum = new PointAccum();
            C3205F.copy(iArr, 0, pointAccum.f3604x, 0);
            C3205F.copy(iArr2, 0, pointAccum.f3605y, 0);
            pointExtendXY(pointAccum);
            precompBase = C3205F.createTable(192);
            int i2 = 0;
            for (int i3 = 0; i3 < 8; i3++) {
                PointExt[] pointExtArr = new PointExt[4];
                PointExt pointExt2 = new PointExt();
                pointSetNeutral(pointExt2);
                int i4 = 0;
                while (true) {
                    i = 1;
                    if (i4 >= 4) {
                        break;
                    }
                    pointAddVar(true, pointExt2, pointCopy(pointAccum), pointExt2);
                    pointDouble(pointAccum);
                    pointExtArr[i4] = pointCopy(pointAccum);
                    if (i3 + i4 != 10) {
                        while (i < 8) {
                            pointDouble(pointAccum);
                            i++;
                        }
                    }
                    i4++;
                }
                PointExt[] pointExtArr2 = new PointExt[8];
                pointExtArr2[0] = pointExt2;
                int i5 = 0;
                int i6 = 1;
                while (i5 < 3) {
                    int i7 = i << i5;
                    int i8 = 0;
                    while (i8 < i7) {
                        PointExt pointExt3 = pointExtArr2[i6 - i7];
                        PointExt pointExt4 = pointExtArr[i5];
                        PointExt pointExt5 = new PointExt();
                        pointExtArr2[i6] = pointExt5;
                        pointAddVar(false, pointExt3, pointExt4, pointExt5);
                        i8++;
                        i6++;
                    }
                    i5++;
                    i = 1;
                }
                int[] iArrCreateTable = C3205F.createTable(8);
                int[] iArrCreate = C3205F.create();
                C3205F.copy(pointExtArr2[0].f3612z, 0, iArrCreate, 0);
                C3205F.copy(iArrCreate, 0, iArrCreateTable, 0);
                int i9 = 0;
                while (true) {
                    i9++;
                    if (i9 >= 8) {
                        break;
                    }
                    C3205F.mul(iArrCreate, pointExtArr2[i9].f3612z, iArrCreate);
                    C3205F.copy(iArrCreate, 0, iArrCreateTable, i9 * 10);
                }
                C3205F.add(iArrCreate, iArrCreate, iArrCreate);
                C3205F.invVar(iArrCreate, iArrCreate);
                int i10 = i9 - 1;
                int[] iArrCreate2 = C3205F.create();
                while (i10 > 0) {
                    int i11 = i10 - 1;
                    C3205F.copy(iArrCreateTable, i11 * 10, iArrCreate2, 0);
                    C3205F.mul(iArrCreate2, iArrCreate, iArrCreate2);
                    C3205F.copy(iArrCreate2, 0, iArrCreateTable, i10 * 10);
                    C3205F.mul(iArrCreate, pointExtArr2[i10].f3612z, iArrCreate);
                    i10 = i11;
                }
                C3205F.copy(iArrCreate, 0, iArrCreateTable, 0);
                for (int i12 = 0; i12 < 8; i12++) {
                    PointExt pointExt6 = pointExtArr2[i12];
                    int[] iArrCreate3 = C3205F.create();
                    int[] iArrCreate4 = C3205F.create();
                    C3205F.copy(iArrCreateTable, i12 * 10, iArrCreate4, 0);
                    C3205F.mul(pointExt6.f3610x, iArrCreate4, iArrCreate3);
                    C3205F.mul(pointExt6.f3611y, iArrCreate4, iArrCreate4);
                    PointPrecomp pointPrecomp = new PointPrecomp();
                    C3205F.apm(iArrCreate4, iArrCreate3, pointPrecomp.ypx_h, pointPrecomp.ymx_h);
                    C3205F.mul(iArrCreate3, iArrCreate4, pointPrecomp.xyd);
                    C3205F.mul(pointPrecomp.xyd, C_d4, pointPrecomp.xyd);
                    C3205F.normalize(pointPrecomp.ypx_h);
                    C3205F.normalize(pointPrecomp.ymx_h);
                    C3205F.copy(pointPrecomp.ypx_h, 0, precompBase, i2);
                    int i13 = i2 + 10;
                    C3205F.copy(pointPrecomp.ymx_h, 0, precompBase, i13);
                    int i14 = i13 + 10;
                    C3205F.copy(pointPrecomp.xyd, 0, precompBase, i14);
                    i2 = i14 + 10;
                }
            }
        }
    }

    private static void pruneScalar(byte[] bArr, int i, byte[] bArr2) {
        System.arraycopy(bArr, i, bArr2, 0, 32);
        bArr2[0] = (byte) (bArr2[0] & 248);
        byte b = (byte) (bArr2[31] & ByteCompanionObject.MAX_VALUE);
        bArr2[31] = b;
        bArr2[31] = (byte) (b | 64);
    }

    private static byte[] reduceScalar(byte[] bArr) {
        long jDecode32 = ((long) decode32(bArr, 0)) & 4294967295L;
        long jDecode24 = ((long) (decode24(bArr, 4) << 4)) & 4294967295L;
        long jDecode322 = ((long) decode32(bArr, 7)) & 4294967295L;
        long jDecode242 = ((long) (decode24(bArr, 11) << 4)) & 4294967295L;
        long jDecode323 = ((long) decode32(bArr, 14)) & 4294967295L;
        long jDecode243 = ((long) (decode24(bArr, 18) << 4)) & 4294967295L;
        long jDecode324 = ((long) decode32(bArr, 21)) & 4294967295L;
        long jDecode244 = ((long) (decode24(bArr, 25) << 4)) & 4294967295L;
        long jDecode325 = ((long) decode32(bArr, 28)) & 4294967295L;
        long jDecode245 = ((long) (decode24(bArr, 32) << 4)) & 4294967295L;
        long jDecode326 = ((long) decode32(bArr, 35)) & 4294967295L;
        long jDecode246 = ((long) (decode24(bArr, 39) << 4)) & 4294967295L;
        long jDecode327 = ((long) decode32(bArr, 42)) & 4294967295L;
        long jDecode247 = ((long) (decode24(bArr, 46) << 4)) & 4294967295L;
        long jDecode328 = ((long) decode32(bArr, 49)) & 4294967295L;
        long jDecode248 = ((long) (decode24(bArr, 53) << 4)) & 4294967295L;
        long jDecode329 = ((long) decode32(bArr, 56)) & 4294967295L;
        long jDecode249 = ((long) (decode24(bArr, 60) << 4)) & 4294967295L;
        long j = ((long) bArr[63]) & M08L;
        long j2 = jDecode249 + (jDecode329 >> 28);
        long j3 = jDecode329 & M28L;
        long j4 = jDecode325 - (j2 * (-50998291));
        long j5 = (jDecode245 - (j * (-50998291))) - (j2 * 19280294);
        long j6 = (jDecode326 - (j * 19280294)) - (j2 * 127719000);
        long j7 = (jDecode246 - (j * 127719000)) - (j2 * (-6428113));
        long j8 = (jDecode327 - (j * (-6428113))) - (j2 * 5343);
        long j9 = j7 - (j3 * 5343);
        long j10 = jDecode248 + (jDecode328 >> 28);
        long j11 = jDecode328 & M28L;
        long j12 = (j6 - (j3 * (-6428113))) - (j10 * 5343);
        long j13 = ((jDecode244 - (j3 * (-50998291))) - (j10 * 19280294)) - (j11 * 127719000);
        long j14 = ((j5 - (j3 * 127719000)) - (j10 * (-6428113))) - (j11 * 5343);
        long j15 = (jDecode247 - (j * 5343)) + (j8 >> 28);
        long j16 = (j8 & M28L) + (j9 >> 28);
        long j17 = jDecode242 - (j16 * (-50998291));
        long j18 = (jDecode323 - (j15 * (-50998291))) - (j16 * 19280294);
        long j19 = ((jDecode243 - (j11 * (-50998291))) - (j15 * 19280294)) - (j16 * 127719000);
        long j20 = (((jDecode324 - (j10 * (-50998291))) - (j11 * 19280294)) - (j15 * 127719000)) - (j16 * (-6428113));
        long j21 = (j13 - (j15 * (-6428113))) - (j16 * 5343);
        long j22 = (j9 & M28L) + (j12 >> 28);
        long j23 = j12 & M28L;
        long j24 = jDecode322 - (j22 * (-50998291));
        long j25 = j17 - (j22 * 19280294);
        long j26 = j18 - (j22 * 127719000);
        long j27 = j19 - (j22 * (-6428113));
        long j28 = j20 - (j22 * 5343);
        long j29 = j23 + (j14 >> 28);
        long j30 = j14 & M28L;
        long j31 = jDecode24 - (j29 * (-50998291));
        long j32 = j24 - (j29 * 19280294);
        long j33 = j25 - (j29 * 127719000);
        long j34 = j26 - (j29 * (-6428113));
        long j35 = j27 - (j29 * 5343);
        long j36 = ((((j4 - (j3 * 19280294)) - (j10 * 127719000)) - (j11 * (-6428113))) - (j15 * 5343)) + (j21 >> 28);
        long j37 = j21 & M28L;
        long j38 = j36 & M28L;
        long j39 = j38 >>> 27;
        long j40 = j30 + (j36 >> 28) + j39;
        long j41 = jDecode32 - (j40 * (-50998291));
        long j42 = (j31 - (j40 * 19280294)) + (j41 >> 28);
        long j43 = j41 & M28L;
        long j44 = (j32 - (j40 * 127719000)) + (j42 >> 28);
        long j45 = j42 & M28L;
        long j46 = (j33 - (j40 * (-6428113))) + (j44 >> 28);
        long j47 = j44 & M28L;
        long j48 = (j34 - (j40 * 5343)) + (j46 >> 28);
        long j49 = j46 & M28L;
        long j50 = j35 + (j48 >> 28);
        long j51 = j48 & M28L;
        long j52 = j28 + (j50 >> 28);
        long j53 = j50 & M28L;
        long j54 = j37 + (j52 >> 28);
        long j55 = j52 & M28L;
        long j56 = j38 + (j54 >> 28);
        long j57 = j54 & M28L;
        long j58 = j56 >> 28;
        long j59 = j56 & M28L;
        long j60 = j58 - j39;
        long j61 = j43 + (j60 & (-50998291));
        long j62 = j45 + (j60 & 19280294) + (j61 >> 28);
        long j63 = j61 & M28L;
        long j64 = j47 + (j60 & 127719000) + (j62 >> 28);
        long j65 = j62 & M28L;
        long j66 = j49 + (j60 & (-6428113)) + (j64 >> 28);
        long j67 = j64 & M28L;
        long j68 = j51 + (j60 & 5343) + (j66 >> 28);
        long j69 = j66 & M28L;
        long j70 = j53 + (j68 >> 28);
        long j71 = j68 & M28L;
        long j72 = j55 + (j70 >> 28);
        long j73 = j70 & M28L;
        long j74 = j57 + (j72 >> 28);
        long j75 = j72 & M28L;
        long j76 = j59 + (j74 >> 28);
        long j77 = j74 & M28L;
        byte[] bArr2 = new byte[32];
        encode56(j63 | (j65 << 28), bArr2, 0);
        encode56((j69 << 28) | j67, bArr2, 7);
        encode56(j71 | (j73 << 28), bArr2, 14);
        encode56(j75 | (j77 << 28), bArr2, 21);
        encode32((int) j76, bArr2, 28);
        return bArr2;
    }

    private static void scalarMult(byte[] bArr, PointAffine pointAffine, PointAccum pointAccum) {
        int[] iArr = new int[8];
        decodeScalar(bArr, 0, iArr);
        Nat.shiftDownBits(8, iArr, 3, 1);
        Nat.cadd(8, (~iArr[0]) & 1, iArr, f3595L, iArr);
        Nat.shiftDownBit(8, iArr, 0);
        int[] iArrPointPrecompute = pointPrecompute(pointAffine, 8);
        PointExt pointExt = new PointExt();
        pointCopy(pointAffine, pointAccum);
        pointLookup(iArrPointPrecompute, 7, pointExt);
        pointAdd(pointExt, pointAccum);
        int i = 62;
        while (true) {
            pointLookup(iArr, i, iArrPointPrecompute, pointExt);
            pointAdd(pointExt, pointAccum);
            pointDouble(pointAccum);
            pointDouble(pointAccum);
            pointDouble(pointAccum);
            i--;
            if (i < 0) {
                return;
            } else {
                pointDouble(pointAccum);
            }
        }
    }

    private static void scalarMultBase(byte[] bArr, PointAccum pointAccum) {
        precompute();
        int[] iArr = new int[8];
        decodeScalar(bArr, 0, iArr);
        Nat.cadd(8, (~iArr[0]) & 1, iArr, f3595L, iArr);
        Nat.shiftDownBit(8, iArr, 1);
        for (int i = 0; i < 8; i++) {
            iArr[i] = Interleave.shuffle2(iArr[i]);
        }
        PointPrecomp pointPrecomp = new PointPrecomp();
        pointSetNeutral(pointAccum);
        int i2 = 28;
        while (true) {
            for (int i3 = 0; i3 < 8; i3++) {
                int i4 = iArr[i3] >>> i2;
                int i5 = (i4 >>> 3) & 1;
                pointLookup(i3, (i4 ^ (-i5)) & 7, pointPrecomp);
                C3205F.cswap(i5, pointPrecomp.ypx_h, pointPrecomp.ymx_h);
                C3205F.cnegate(i5, pointPrecomp.xyd);
                pointAddPrecomp(pointPrecomp, pointAccum);
            }
            i2 -= 4;
            if (i2 < 0) {
                return;
            } else {
                pointDouble(pointAccum);
            }
        }
    }

    private static void scalarMultBaseEncoded(byte[] bArr, byte[] bArr2, int i) {
        PointAccum pointAccum = new PointAccum();
        scalarMultBase(bArr, pointAccum);
        if (encodePoint(pointAccum, bArr2, i) == 0) {
            throw new IllegalStateException();
        }
    }

    public static void scalarMultBaseYZ(X25519.Friend friend, byte[] bArr, int i, int[] iArr, int[] iArr2) {
        if (friend == null) {
            throw new NullPointerException("This method is only for use by X25519");
        }
        byte[] bArr2 = new byte[32];
        pruneScalar(bArr, i, bArr2);
        PointAccum pointAccum = new PointAccum();
        scalarMultBase(bArr2, pointAccum);
        if (checkPoint(pointAccum.f3604x, pointAccum.f3605y, pointAccum.f3606z) == 0) {
            throw new IllegalStateException();
        }
        C3205F.copy(pointAccum.f3605y, 0, iArr, 0);
        C3205F.copy(pointAccum.f3606z, 0, iArr2, 0);
    }

    private static void scalarMultOrderVar(PointAffine pointAffine, PointAccum pointAccum) {
        byte[] wnafVar = getWnafVar(f3595L, 5);
        PointExt[] pointExtArrPointPrecomputeVar = pointPrecomputeVar(pointCopy(pointAffine), 8);
        pointSetNeutral(pointAccum);
        int i = ErrorConst.MDB_ERR_CANT_OPEN;
        while (true) {
            byte b = wnafVar[i];
            if (b != 0) {
                int i2 = b >> SnmpConstants.ASN_EXTENSION_ID;
                pointAddVar(i2 != 0, pointExtArrPointPrecomputeVar[(b ^ i2) >>> 1], pointAccum);
            }
            i--;
            if (i < 0) {
                return;
            } else {
                pointDouble(pointAccum);
            }
        }
    }

    private static void scalarMultStrausVar(int[] iArr, int[] iArr2, PointAffine pointAffine, PointAccum pointAccum) {
        precompute();
        byte[] wnafVar = getWnafVar(iArr, 7);
        byte[] wnafVar2 = getWnafVar(iArr2, 5);
        PointExt[] pointExtArrPointPrecomputeVar = pointPrecomputeVar(pointCopy(pointAffine), 8);
        pointSetNeutral(pointAccum);
        int i = ErrorConst.MDB_ERR_CANT_OPEN;
        while (true) {
            byte b = wnafVar[i];
            if (b != 0) {
                int i2 = b >> SnmpConstants.ASN_EXTENSION_ID;
                pointAddVar(i2 != 0, precompBaseTable[(b ^ i2) >>> 1], pointAccum);
            }
            byte b2 = wnafVar2[i];
            if (b2 != 0) {
                int i3 = b2 >> SnmpConstants.ASN_EXTENSION_ID;
                pointAddVar(i3 != 0, pointExtArrPointPrecomputeVar[(b2 ^ i3) >>> 1], pointAccum);
            }
            i--;
            if (i < 0) {
                return;
            } else {
                pointDouble(pointAccum);
            }
        }
    }

    public static void sign(byte[] bArr, int i, byte[] bArr2, int i2, int i3, byte[] bArr3, int i4) {
        implSign(bArr, i, null, (byte) 0, bArr2, i2, i3, bArr3, i4);
    }

    public static void sign(byte[] bArr, int i, byte[] bArr2, int i2, byte[] bArr3, int i3, int i4, byte[] bArr4, int i5) {
        implSign(bArr, i, bArr2, i2, null, (byte) 0, bArr3, i3, i4, bArr4, i5);
    }

    public static void sign(byte[] bArr, int i, byte[] bArr2, int i2, byte[] bArr3, byte[] bArr4, int i3, int i4, byte[] bArr5, int i5) {
        implSign(bArr, i, bArr2, i2, bArr3, (byte) 0, bArr4, i3, i4, bArr5, i5);
    }

    public static void sign(byte[] bArr, int i, byte[] bArr2, byte[] bArr3, int i2, int i3, byte[] bArr4, int i4) {
        implSign(bArr, i, bArr2, (byte) 0, bArr3, i2, i3, bArr4, i4);
    }

    public static void signPrehash(byte[] bArr, int i, byte[] bArr2, int i2, byte[] bArr3, Digest digest, byte[] bArr4, int i3) {
        byte[] bArr5 = new byte[64];
        if (64 != digest.doFinal(bArr5, 0)) {
            throw new IllegalArgumentException("ph");
        }
        implSign(bArr, i, bArr2, i2, bArr3, (byte) 1, bArr5, 0, 64, bArr4, i3);
    }

    public static void signPrehash(byte[] bArr, int i, byte[] bArr2, int i2, byte[] bArr3, byte[] bArr4, int i3, byte[] bArr5, int i4) {
        implSign(bArr, i, bArr2, i2, bArr3, (byte) 1, bArr4, i3, 64, bArr5, i4);
    }

    public static void signPrehash(byte[] bArr, int i, byte[] bArr2, Digest digest, byte[] bArr3, int i2) {
        byte[] bArr4 = new byte[64];
        if (64 != digest.doFinal(bArr4, 0)) {
            throw new IllegalArgumentException("ph");
        }
        implSign(bArr, i, bArr2, (byte) 1, bArr4, 0, 64, bArr3, i2);
    }

    public static void signPrehash(byte[] bArr, int i, byte[] bArr2, byte[] bArr3, int i2, byte[] bArr4, int i3) {
        implSign(bArr, i, bArr2, (byte) 1, bArr3, i2, 64, bArr4, i3);
    }

    public static boolean validatePublicKeyFull(byte[] bArr, int i) {
        PointAffine pointAffine = new PointAffine();
        if (!decodePointVar(bArr, i, false, pointAffine)) {
            return false;
        }
        C3205F.normalize(pointAffine.f3607x);
        C3205F.normalize(pointAffine.f3608y);
        if (isNeutralElementVar(pointAffine.f3607x, pointAffine.f3608y)) {
            return false;
        }
        PointAccum pointAccum = new PointAccum();
        scalarMultOrderVar(pointAffine, pointAccum);
        C3205F.normalize(pointAccum.f3604x);
        C3205F.normalize(pointAccum.f3605y);
        C3205F.normalize(pointAccum.f3606z);
        return isNeutralElementVar(pointAccum.f3604x, pointAccum.f3605y, pointAccum.f3606z);
    }

    public static boolean validatePublicKeyPartial(byte[] bArr, int i) {
        return decodePointVar(bArr, i, false, new PointAffine());
    }

    public static boolean verify(byte[] bArr, int i, byte[] bArr2, int i2, byte[] bArr3, int i3, int i4) {
        return implVerify(bArr, i, bArr2, i2, null, (byte) 0, bArr3, i3, i4);
    }

    public static boolean verify(byte[] bArr, int i, byte[] bArr2, int i2, byte[] bArr3, byte[] bArr4, int i3, int i4) {
        return implVerify(bArr, i, bArr2, i2, bArr3, (byte) 0, bArr4, i3, i4);
    }

    public static boolean verifyPrehash(byte[] bArr, int i, byte[] bArr2, int i2, byte[] bArr3, Digest digest) {
        byte[] bArr4 = new byte[64];
        if (64 == digest.doFinal(bArr4, 0)) {
            return implVerify(bArr, i, bArr2, i2, bArr3, (byte) 1, bArr4, 0, 64);
        }
        throw new IllegalArgumentException("ph");
    }

    public static boolean verifyPrehash(byte[] bArr, int i, byte[] bArr2, int i2, byte[] bArr3, byte[] bArr4, int i3) {
        return implVerify(bArr, i, bArr2, i2, bArr3, (byte) 1, bArr4, i3, 64);
    }
}
