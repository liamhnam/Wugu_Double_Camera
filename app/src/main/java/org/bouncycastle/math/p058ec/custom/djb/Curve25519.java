package org.bouncycastle.math.p058ec.custom.djb;

import java.math.BigInteger;
import java.security.SecureRandom;
import org.bouncycastle.math.p058ec.AbstractECLookupTable;
import org.bouncycastle.math.p058ec.ECConstants;
import org.bouncycastle.math.p058ec.ECCurve;
import org.bouncycastle.math.p058ec.ECFieldElement;
import org.bouncycastle.math.p058ec.ECLookupTable;
import org.bouncycastle.math.p058ec.ECPoint;
import org.bouncycastle.math.raw.Nat256;
import org.bouncycastle.util.encoders.Hex;

public class Curve25519 extends ECCurve.AbstractFp {
    private static final ECFieldElement[] CURVE25519_AFFINE_ZS;
    private static final int CURVE25519_DEFAULT_COORDS = 4;
    private static final BigInteger C_a;
    private static final BigInteger C_b;

    public static final BigInteger f3513q = Curve25519FieldElement.f3517Q;
    protected Curve25519Point infinity;

    static {
        BigInteger bigInteger = new BigInteger(1, Hex.decodeStrict("2AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA984914A144"));
        C_a = bigInteger;
        C_b = new BigInteger(1, Hex.decodeStrict("7B425ED097B425ED097B425ED097B425ED097B425ED097B4260B5E9C7710C864"));
        CURVE25519_AFFINE_ZS = new ECFieldElement[]{new Curve25519FieldElement(ECConstants.ONE), new Curve25519FieldElement(bigInteger)};
    }

    public Curve25519() {
        super(f3513q);
        this.infinity = new Curve25519Point(this, null, null);
        this.f3493a = fromBigInteger(C_a);
        this.f3494b = fromBigInteger(C_b);
        this.order = new BigInteger(1, Hex.decodeStrict("1000000000000000000000000000000014DEF9DEA2F79CD65812631A5CF5D3ED"));
        this.cofactor = BigInteger.valueOf(8L);
        this.coord = 4;
    }

    @Override
    protected ECCurve cloneCurve() {
        return new Curve25519();
    }

    @Override
    public ECLookupTable createCacheSafeLookupTable(ECPoint[] eCPointArr, int i, final int i2) {
        final int[] iArr = new int[i2 * 8 * 2];
        int i3 = 0;
        for (int i4 = 0; i4 < i2; i4++) {
            ECPoint eCPoint = eCPointArr[i + i4];
            Nat256.copy(((Curve25519FieldElement) eCPoint.getRawXCoord()).f3518x, 0, iArr, i3);
            int i5 = i3 + 8;
            Nat256.copy(((Curve25519FieldElement) eCPoint.getRawYCoord()).f3518x, 0, iArr, i5);
            i3 = i5 + 8;
        }
        return new AbstractECLookupTable() {
            private ECPoint createPoint(int[] iArr2, int[] iArr3) {
                return Curve25519.this.createRawPoint(new Curve25519FieldElement(iArr2), new Curve25519FieldElement(iArr3), Curve25519.CURVE25519_AFFINE_ZS);
            }

            @Override
            public int getSize() {
                return i2;
            }

            @Override
            public ECPoint lookup(int i6) {
                int[] iArrCreate = Nat256.create();
                int[] iArrCreate2 = Nat256.create();
                int i7 = 0;
                for (int i8 = 0; i8 < i2; i8++) {
                    int i9 = ((i8 ^ i6) - 1) >> 31;
                    for (int i10 = 0; i10 < 8; i10++) {
                        int i11 = iArrCreate[i10];
                        int[] iArr2 = iArr;
                        iArrCreate[i10] = i11 ^ (iArr2[i7 + i10] & i9);
                        iArrCreate2[i10] = iArrCreate2[i10] ^ (iArr2[(i7 + 8) + i10] & i9);
                    }
                    i7 += 16;
                }
                return createPoint(iArrCreate, iArrCreate2);
            }

            @Override
            public ECPoint lookupVar(int i6) {
                int[] iArrCreate = Nat256.create();
                int[] iArrCreate2 = Nat256.create();
                int i7 = i6 * 8 * 2;
                for (int i8 = 0; i8 < 8; i8++) {
                    int[] iArr2 = iArr;
                    iArrCreate[i8] = iArr2[i7 + i8];
                    iArrCreate2[i8] = iArr2[i7 + 8 + i8];
                }
                return createPoint(iArrCreate, iArrCreate2);
            }
        };
    }

    @Override
    protected ECPoint createRawPoint(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2) {
        return new Curve25519Point(this, eCFieldElement, eCFieldElement2);
    }

    @Override
    protected ECPoint createRawPoint(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2, ECFieldElement[] eCFieldElementArr) {
        return new Curve25519Point(this, eCFieldElement, eCFieldElement2, eCFieldElementArr);
    }

    @Override
    public ECFieldElement fromBigInteger(BigInteger bigInteger) {
        return new Curve25519FieldElement(bigInteger);
    }

    @Override
    public int getFieldSize() {
        return f3513q.bitLength();
    }

    @Override
    public ECPoint getInfinity() {
        return this.infinity;
    }

    public BigInteger getQ() {
        return f3513q;
    }

    @Override
    public ECFieldElement randomFieldElement(SecureRandom secureRandom) {
        int[] iArrCreate = Nat256.create();
        Curve25519Field.random(secureRandom, iArrCreate);
        return new Curve25519FieldElement(iArrCreate);
    }

    @Override
    public ECFieldElement randomFieldElementMult(SecureRandom secureRandom) {
        int[] iArrCreate = Nat256.create();
        Curve25519Field.randomMult(secureRandom, iArrCreate);
        return new Curve25519FieldElement(iArrCreate);
    }

    @Override
    public boolean supportsCoordinateSystem(int i) {
        return i == 4;
    }
}
