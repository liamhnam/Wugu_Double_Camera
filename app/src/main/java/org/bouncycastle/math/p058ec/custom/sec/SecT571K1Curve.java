package org.bouncycastle.math.p058ec.custom.sec;

import java.math.BigInteger;
import org.bouncycastle.math.p058ec.AbstractECLookupTable;
import org.bouncycastle.math.p058ec.ECConstants;
import org.bouncycastle.math.p058ec.ECCurve;
import org.bouncycastle.math.p058ec.ECFieldElement;
import org.bouncycastle.math.p058ec.ECLookupTable;
import org.bouncycastle.math.p058ec.ECMultiplier;
import org.bouncycastle.math.p058ec.ECPoint;
import org.bouncycastle.math.p058ec.WTauNafMultiplier;
import org.bouncycastle.math.raw.Nat576;
import org.bouncycastle.util.encoders.Hex;

public class SecT571K1Curve extends ECCurve.AbstractF2m {
    private static final ECFieldElement[] SECT571K1_AFFINE_ZS = {new SecT571FieldElement(ECConstants.ONE)};
    private static final int SECT571K1_DEFAULT_COORDS = 6;
    protected SecT571K1Point infinity;

    public SecT571K1Curve() {
        super(571, 2, 5, 10);
        this.infinity = new SecT571K1Point(this, null, null);
        this.f3493a = fromBigInteger(BigInteger.valueOf(0L));
        this.f3494b = fromBigInteger(BigInteger.valueOf(1L));
        this.order = new BigInteger(1, Hex.decodeStrict("020000000000000000000000000000000000000000000000000000000000000000000000131850E1F19A63E4B391A8DB917F4138B630D84BE5D639381E91DEB45CFE778F637C1001"));
        this.cofactor = BigInteger.valueOf(4L);
        this.coord = 6;
    }

    @Override
    protected ECCurve cloneCurve() {
        return new SecT571K1Curve();
    }

    @Override
    public ECLookupTable createCacheSafeLookupTable(ECPoint[] eCPointArr, int i, final int i2) {
        final long[] jArr = new long[i2 * 9 * 2];
        int i3 = 0;
        for (int i4 = 0; i4 < i2; i4++) {
            ECPoint eCPoint = eCPointArr[i + i4];
            Nat576.copy64(((SecT571FieldElement) eCPoint.getRawXCoord()).f3591x, 0, jArr, i3);
            int i5 = i3 + 9;
            Nat576.copy64(((SecT571FieldElement) eCPoint.getRawYCoord()).f3591x, 0, jArr, i5);
            i3 = i5 + 9;
        }
        return new AbstractECLookupTable() {
            private ECPoint createPoint(long[] jArr2, long[] jArr3) {
                return SecT571K1Curve.this.createRawPoint(new SecT571FieldElement(jArr2), new SecT571FieldElement(jArr3), SecT571K1Curve.SECT571K1_AFFINE_ZS);
            }

            @Override
            public int getSize() {
                return i2;
            }

            @Override
            public ECPoint lookup(int i6) {
                long[] jArrCreate64 = Nat576.create64();
                long[] jArrCreate642 = Nat576.create64();
                int i7 = 0;
                for (int i8 = 0; i8 < i2; i8++) {
                    long j = ((i8 ^ i6) - 1) >> 31;
                    for (int i9 = 0; i9 < 9; i9++) {
                        long j2 = jArrCreate64[i9];
                        long[] jArr2 = jArr;
                        jArrCreate64[i9] = j2 ^ (jArr2[i7 + i9] & j);
                        jArrCreate642[i9] = jArrCreate642[i9] ^ (jArr2[(i7 + 9) + i9] & j);
                    }
                    i7 += 18;
                }
                return createPoint(jArrCreate64, jArrCreate642);
            }

            @Override
            public ECPoint lookupVar(int i6) {
                long[] jArrCreate64 = Nat576.create64();
                long[] jArrCreate642 = Nat576.create64();
                int i7 = i6 * 9 * 2;
                for (int i8 = 0; i8 < 9; i8++) {
                    long[] jArr2 = jArr;
                    jArrCreate64[i8] = jArr2[i7 + i8];
                    jArrCreate642[i8] = jArr2[i7 + 9 + i8];
                }
                return createPoint(jArrCreate64, jArrCreate642);
            }
        };
    }

    @Override
    protected ECMultiplier createDefaultMultiplier() {
        return new WTauNafMultiplier();
    }

    @Override
    protected ECPoint createRawPoint(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2) {
        return new SecT571K1Point(this, eCFieldElement, eCFieldElement2);
    }

    @Override
    protected ECPoint createRawPoint(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2, ECFieldElement[] eCFieldElementArr) {
        return new SecT571K1Point(this, eCFieldElement, eCFieldElement2, eCFieldElementArr);
    }

    @Override
    public ECFieldElement fromBigInteger(BigInteger bigInteger) {
        return new SecT571FieldElement(bigInteger);
    }

    @Override
    public int getFieldSize() {
        return 571;
    }

    @Override
    public ECPoint getInfinity() {
        return this.infinity;
    }

    public int getK1() {
        return 2;
    }

    public int getK2() {
        return 5;
    }

    public int getK3() {
        return 10;
    }

    public int getM() {
        return 571;
    }

    @Override
    public boolean isKoblitz() {
        return true;
    }

    public boolean isTrinomial() {
        return false;
    }

    @Override
    public boolean supportsCoordinateSystem(int i) {
        return i == 6;
    }
}
