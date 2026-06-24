package org.bouncycastle.math.p058ec.custom.sec;

import java.math.BigInteger;
import java.security.SecureRandom;
import org.bouncycastle.math.p058ec.AbstractECLookupTable;
import org.bouncycastle.math.p058ec.ECConstants;
import org.bouncycastle.math.p058ec.ECCurve;
import org.bouncycastle.math.p058ec.ECFieldElement;
import org.bouncycastle.math.p058ec.ECLookupTable;
import org.bouncycastle.math.p058ec.ECPoint;
import org.bouncycastle.math.raw.Nat224;
import org.bouncycastle.util.encoders.Hex;

public class SecP224R1Curve extends ECCurve.AbstractFp {
    private static final int SECP224R1_DEFAULT_COORDS = 2;
    protected SecP224R1Point infinity;

    public static final BigInteger f3557q = SecP224R1FieldElement.f3561Q;
    private static final ECFieldElement[] SECP224R1_AFFINE_ZS = {new SecP224R1FieldElement(ECConstants.ONE)};

    public SecP224R1Curve() {
        super(f3557q);
        this.infinity = new SecP224R1Point(this, null, null);
        this.f3493a = fromBigInteger(new BigInteger(1, Hex.decodeStrict("FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFEFFFFFFFFFFFFFFFFFFFFFFFE")));
        this.f3494b = fromBigInteger(new BigInteger(1, Hex.decodeStrict("B4050A850C04B3ABF54132565044B0B7D7BFD8BA270B39432355FFB4")));
        this.order = new BigInteger(1, Hex.decodeStrict("FFFFFFFFFFFFFFFFFFFFFFFFFFFF16A2E0B8F03E13DD29455C5C2A3D"));
        this.cofactor = BigInteger.valueOf(1L);
        this.coord = 2;
    }

    @Override
    protected ECCurve cloneCurve() {
        return new SecP224R1Curve();
    }

    @Override
    public ECLookupTable createCacheSafeLookupTable(ECPoint[] eCPointArr, int i, final int i2) {
        final int[] iArr = new int[i2 * 7 * 2];
        int i3 = 0;
        for (int i4 = 0; i4 < i2; i4++) {
            ECPoint eCPoint = eCPointArr[i + i4];
            Nat224.copy(((SecP224R1FieldElement) eCPoint.getRawXCoord()).f3562x, 0, iArr, i3);
            int i5 = i3 + 7;
            Nat224.copy(((SecP224R1FieldElement) eCPoint.getRawYCoord()).f3562x, 0, iArr, i5);
            i3 = i5 + 7;
        }
        return new AbstractECLookupTable() {
            private ECPoint createPoint(int[] iArr2, int[] iArr3) {
                return SecP224R1Curve.this.createRawPoint(new SecP224R1FieldElement(iArr2), new SecP224R1FieldElement(iArr3), SecP224R1Curve.SECP224R1_AFFINE_ZS);
            }

            @Override
            public int getSize() {
                return i2;
            }

            @Override
            public ECPoint lookup(int i6) {
                int[] iArrCreate = Nat224.create();
                int[] iArrCreate2 = Nat224.create();
                int i7 = 0;
                for (int i8 = 0; i8 < i2; i8++) {
                    int i9 = ((i8 ^ i6) - 1) >> 31;
                    for (int i10 = 0; i10 < 7; i10++) {
                        int i11 = iArrCreate[i10];
                        int[] iArr2 = iArr;
                        iArrCreate[i10] = i11 ^ (iArr2[i7 + i10] & i9);
                        iArrCreate2[i10] = iArrCreate2[i10] ^ (iArr2[(i7 + 7) + i10] & i9);
                    }
                    i7 += 14;
                }
                return createPoint(iArrCreate, iArrCreate2);
            }

            @Override
            public ECPoint lookupVar(int i6) {
                int[] iArrCreate = Nat224.create();
                int[] iArrCreate2 = Nat224.create();
                int i7 = i6 * 7 * 2;
                for (int i8 = 0; i8 < 7; i8++) {
                    int[] iArr2 = iArr;
                    iArrCreate[i8] = iArr2[i7 + i8];
                    iArrCreate2[i8] = iArr2[i7 + 7 + i8];
                }
                return createPoint(iArrCreate, iArrCreate2);
            }
        };
    }

    @Override
    protected ECPoint createRawPoint(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2) {
        return new SecP224R1Point(this, eCFieldElement, eCFieldElement2);
    }

    @Override
    protected ECPoint createRawPoint(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2, ECFieldElement[] eCFieldElementArr) {
        return new SecP224R1Point(this, eCFieldElement, eCFieldElement2, eCFieldElementArr);
    }

    @Override
    public ECFieldElement fromBigInteger(BigInteger bigInteger) {
        return new SecP224R1FieldElement(bigInteger);
    }

    @Override
    public int getFieldSize() {
        return f3557q.bitLength();
    }

    @Override
    public ECPoint getInfinity() {
        return this.infinity;
    }

    public BigInteger getQ() {
        return f3557q;
    }

    @Override
    public ECFieldElement randomFieldElement(SecureRandom secureRandom) {
        int[] iArrCreate = Nat224.create();
        SecP224R1Field.random(secureRandom, iArrCreate);
        return new SecP224R1FieldElement(iArrCreate);
    }

    @Override
    public ECFieldElement randomFieldElementMult(SecureRandom secureRandom) {
        int[] iArrCreate = Nat224.create();
        SecP224R1Field.randomMult(secureRandom, iArrCreate);
        return new SecP224R1FieldElement(iArrCreate);
    }

    @Override
    public boolean supportsCoordinateSystem(int i) {
        return i == 2;
    }
}
