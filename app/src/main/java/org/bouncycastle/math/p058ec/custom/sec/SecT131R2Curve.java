package org.bouncycastle.math.p058ec.custom.sec;

import java.math.BigInteger;
import org.bouncycastle.math.p058ec.AbstractECLookupTable;
import org.bouncycastle.math.p058ec.ECConstants;
import org.bouncycastle.math.p058ec.ECCurve;
import org.bouncycastle.math.p058ec.ECFieldElement;
import org.bouncycastle.math.p058ec.ECLookupTable;
import org.bouncycastle.math.p058ec.ECPoint;
import org.bouncycastle.math.raw.Nat192;
import org.bouncycastle.util.encoders.Hex;

public class SecT131R2Curve extends ECCurve.AbstractF2m {
    private static final ECFieldElement[] SECT131R2_AFFINE_ZS = {new SecT131FieldElement(ECConstants.ONE)};
    private static final int SECT131R2_DEFAULT_COORDS = 6;
    protected SecT131R2Point infinity;

    public SecT131R2Curve() {
        super(131, 2, 3, 8);
        this.infinity = new SecT131R2Point(this, null, null);
        this.f3493a = fromBigInteger(new BigInteger(1, Hex.decodeStrict("03E5A88919D7CAFCBF415F07C2176573B2")));
        this.f3494b = fromBigInteger(new BigInteger(1, Hex.decodeStrict("04B8266A46C55657AC734CE38F018F2192")));
        this.order = new BigInteger(1, Hex.decodeStrict("0400000000000000016954A233049BA98F"));
        this.cofactor = BigInteger.valueOf(2L);
        this.coord = 6;
    }

    @Override
    protected ECCurve cloneCurve() {
        return new SecT131R2Curve();
    }

    @Override
    public ECLookupTable createCacheSafeLookupTable(ECPoint[] eCPointArr, int i, final int i2) {
        final long[] jArr = new long[i2 * 3 * 2];
        int i3 = 0;
        for (int i4 = 0; i4 < i2; i4++) {
            ECPoint eCPoint = eCPointArr[i + i4];
            Nat192.copy64(((SecT131FieldElement) eCPoint.getRawXCoord()).f3584x, 0, jArr, i3);
            int i5 = i3 + 3;
            Nat192.copy64(((SecT131FieldElement) eCPoint.getRawYCoord()).f3584x, 0, jArr, i5);
            i3 = i5 + 3;
        }
        return new AbstractECLookupTable() {
            private ECPoint createPoint(long[] jArr2, long[] jArr3) {
                return SecT131R2Curve.this.createRawPoint(new SecT131FieldElement(jArr2), new SecT131FieldElement(jArr3), SecT131R2Curve.SECT131R2_AFFINE_ZS);
            }

            @Override
            public int getSize() {
                return i2;
            }

            @Override
            public ECPoint lookup(int i6) {
                long[] jArrCreate64 = Nat192.create64();
                long[] jArrCreate642 = Nat192.create64();
                int i7 = 0;
                for (int i8 = 0; i8 < i2; i8++) {
                    long j = ((i8 ^ i6) - 1) >> 31;
                    for (int i9 = 0; i9 < 3; i9++) {
                        long j2 = jArrCreate64[i9];
                        long[] jArr2 = jArr;
                        jArrCreate64[i9] = j2 ^ (jArr2[i7 + i9] & j);
                        jArrCreate642[i9] = jArrCreate642[i9] ^ (jArr2[(i7 + 3) + i9] & j);
                    }
                    i7 += 6;
                }
                return createPoint(jArrCreate64, jArrCreate642);
            }

            @Override
            public ECPoint lookupVar(int i6) {
                long[] jArrCreate64 = Nat192.create64();
                long[] jArrCreate642 = Nat192.create64();
                int i7 = i6 * 3 * 2;
                for (int i8 = 0; i8 < 3; i8++) {
                    long[] jArr2 = jArr;
                    jArrCreate64[i8] = jArr2[i7 + i8];
                    jArrCreate642[i8] = jArr2[i7 + 3 + i8];
                }
                return createPoint(jArrCreate64, jArrCreate642);
            }
        };
    }

    @Override
    protected ECPoint createRawPoint(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2) {
        return new SecT131R2Point(this, eCFieldElement, eCFieldElement2);
    }

    @Override
    protected ECPoint createRawPoint(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2, ECFieldElement[] eCFieldElementArr) {
        return new SecT131R2Point(this, eCFieldElement, eCFieldElement2, eCFieldElementArr);
    }

    @Override
    public ECFieldElement fromBigInteger(BigInteger bigInteger) {
        return new SecT131FieldElement(bigInteger);
    }

    @Override
    public int getFieldSize() {
        return 131;
    }

    @Override
    public ECPoint getInfinity() {
        return this.infinity;
    }

    public int getK1() {
        return 2;
    }

    public int getK2() {
        return 3;
    }

    public int getK3() {
        return 8;
    }

    public int getM() {
        return 131;
    }

    @Override
    public boolean isKoblitz() {
        return false;
    }

    public boolean isTrinomial() {
        return false;
    }

    @Override
    public boolean supportsCoordinateSystem(int i) {
        return i == 6;
    }
}
