package org.bouncycastle.math.p058ec.custom.sec;

import com.wugu.doublecamera.enums.UiPosIndexEnum;
import java.math.BigInteger;
import org.bouncycastle.math.p058ec.AbstractECLookupTable;
import org.bouncycastle.math.p058ec.ECConstants;
import org.bouncycastle.math.p058ec.ECCurve;
import org.bouncycastle.math.p058ec.ECFieldElement;
import org.bouncycastle.math.p058ec.ECLookupTable;
import org.bouncycastle.math.p058ec.ECPoint;
import org.bouncycastle.math.raw.Nat128;
import org.bouncycastle.util.encoders.Hex;

public class SecT113R2Curve extends ECCurve.AbstractF2m {
    private static final ECFieldElement[] SECT113R2_AFFINE_ZS = {new SecT113FieldElement(ECConstants.ONE)};
    private static final int SECT113R2_DEFAULT_COORDS = 6;
    protected SecT113R2Point infinity;

    public SecT113R2Curve() {
        super(UiPosIndexEnum.HOME_REPLACE_BG_TAB, 9, 0, 0);
        this.infinity = new SecT113R2Point(this, null, null);
        this.f3493a = fromBigInteger(new BigInteger(1, Hex.decodeStrict("00689918DBEC7E5A0DD6DFC0AA55C7")));
        this.f3494b = fromBigInteger(new BigInteger(1, Hex.decodeStrict("0095E9A9EC9B297BD4BF36E059184F")));
        this.order = new BigInteger(1, Hex.decodeStrict("010000000000000108789B2496AF93"));
        this.cofactor = BigInteger.valueOf(2L);
        this.coord = 6;
    }

    @Override
    protected ECCurve cloneCurve() {
        return new SecT113R2Curve();
    }

    @Override
    public ECLookupTable createCacheSafeLookupTable(ECPoint[] eCPointArr, int i, final int i2) {
        final long[] jArr = new long[i2 * 2 * 2];
        int i3 = 0;
        for (int i4 = 0; i4 < i2; i4++) {
            ECPoint eCPoint = eCPointArr[i + i4];
            Nat128.copy64(((SecT113FieldElement) eCPoint.getRawXCoord()).f3583x, 0, jArr, i3);
            int i5 = i3 + 2;
            Nat128.copy64(((SecT113FieldElement) eCPoint.getRawYCoord()).f3583x, 0, jArr, i5);
            i3 = i5 + 2;
        }
        return new AbstractECLookupTable() {
            private ECPoint createPoint(long[] jArr2, long[] jArr3) {
                return SecT113R2Curve.this.createRawPoint(new SecT113FieldElement(jArr2), new SecT113FieldElement(jArr3), SecT113R2Curve.SECT113R2_AFFINE_ZS);
            }

            @Override
            public int getSize() {
                return i2;
            }

            @Override
            public ECPoint lookup(int i6) {
                long[] jArrCreate64 = Nat128.create64();
                long[] jArrCreate642 = Nat128.create64();
                int i7 = 0;
                for (int i8 = 0; i8 < i2; i8++) {
                    long j = ((i8 ^ i6) - 1) >> 31;
                    for (int i9 = 0; i9 < 2; i9++) {
                        long j2 = jArrCreate64[i9];
                        long[] jArr2 = jArr;
                        jArrCreate64[i9] = j2 ^ (jArr2[i7 + i9] & j);
                        jArrCreate642[i9] = jArrCreate642[i9] ^ (jArr2[(i7 + 2) + i9] & j);
                    }
                    i7 += 4;
                }
                return createPoint(jArrCreate64, jArrCreate642);
            }

            @Override
            public ECPoint lookupVar(int i6) {
                long[] jArrCreate64 = Nat128.create64();
                long[] jArrCreate642 = Nat128.create64();
                int i7 = i6 * 2 * 2;
                for (int i8 = 0; i8 < 2; i8++) {
                    long[] jArr2 = jArr;
                    jArrCreate64[i8] = jArr2[i7 + i8];
                    jArrCreate642[i8] = jArr2[i7 + 2 + i8];
                }
                return createPoint(jArrCreate64, jArrCreate642);
            }
        };
    }

    @Override
    protected ECPoint createRawPoint(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2) {
        return new SecT113R2Point(this, eCFieldElement, eCFieldElement2);
    }

    @Override
    protected ECPoint createRawPoint(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2, ECFieldElement[] eCFieldElementArr) {
        return new SecT113R2Point(this, eCFieldElement, eCFieldElement2, eCFieldElementArr);
    }

    @Override
    public ECFieldElement fromBigInteger(BigInteger bigInteger) {
        return new SecT113FieldElement(bigInteger);
    }

    @Override
    public int getFieldSize() {
        return UiPosIndexEnum.HOME_REPLACE_BG_TAB;
    }

    @Override
    public ECPoint getInfinity() {
        return this.infinity;
    }

    public int getK1() {
        return 9;
    }

    public int getK2() {
        return 0;
    }

    public int getK3() {
        return 0;
    }

    public int getM() {
        return UiPosIndexEnum.HOME_REPLACE_BG_TAB;
    }

    @Override
    public boolean isKoblitz() {
        return false;
    }

    public boolean isTrinomial() {
        return true;
    }

    @Override
    public boolean supportsCoordinateSystem(int i) {
        return i == 6;
    }
}
