package org.bouncycastle.math.p058ec.custom.p059gm;

import org.bouncycastle.math.p058ec.ECCurve;
import org.bouncycastle.math.p058ec.ECFieldElement;
import org.bouncycastle.math.p058ec.ECPoint;
import org.bouncycastle.math.raw.Nat;
import org.bouncycastle.math.raw.Nat256;

public class SM2P256V1Point extends ECPoint.AbstractFp {
    SM2P256V1Point(ECCurve eCCurve, ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2) {
        super(eCCurve, eCFieldElement, eCFieldElement2);
    }

    SM2P256V1Point(ECCurve eCCurve, ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2, ECFieldElement[] eCFieldElementArr) {
        super(eCCurve, eCFieldElement, eCFieldElement2, eCFieldElementArr);
    }

    @Override
    public ECPoint add(ECPoint eCPoint) {
        int[] iArr;
        int[] iArr2;
        int[] iArr3;
        int[] iArr4;
        if (isInfinity()) {
            return eCPoint;
        }
        if (eCPoint.isInfinity()) {
            return this;
        }
        if (this == eCPoint) {
            return twice();
        }
        ECCurve curve = getCurve();
        SM2P256V1FieldElement sM2P256V1FieldElement = (SM2P256V1FieldElement) this.f3508x;
        SM2P256V1FieldElement sM2P256V1FieldElement2 = (SM2P256V1FieldElement) this.f3509y;
        SM2P256V1FieldElement sM2P256V1FieldElement3 = (SM2P256V1FieldElement) eCPoint.getXCoord();
        SM2P256V1FieldElement sM2P256V1FieldElement4 = (SM2P256V1FieldElement) eCPoint.getYCoord();
        SM2P256V1FieldElement sM2P256V1FieldElement5 = (SM2P256V1FieldElement) this.f3510zs[0];
        SM2P256V1FieldElement sM2P256V1FieldElement6 = (SM2P256V1FieldElement) eCPoint.getZCoord(0);
        int[] iArrCreateExt = Nat256.createExt();
        int[] iArrCreate = Nat256.create();
        int[] iArrCreate2 = Nat256.create();
        int[] iArrCreate3 = Nat256.create();
        boolean zIsOne = sM2P256V1FieldElement5.isOne();
        if (zIsOne) {
            iArr = sM2P256V1FieldElement3.f3523x;
            iArr2 = sM2P256V1FieldElement4.f3523x;
        } else {
            SM2P256V1Field.square(sM2P256V1FieldElement5.f3523x, iArrCreate2);
            SM2P256V1Field.multiply(iArrCreate2, sM2P256V1FieldElement3.f3523x, iArrCreate);
            SM2P256V1Field.multiply(iArrCreate2, sM2P256V1FieldElement5.f3523x, iArrCreate2);
            SM2P256V1Field.multiply(iArrCreate2, sM2P256V1FieldElement4.f3523x, iArrCreate2);
            iArr = iArrCreate;
            iArr2 = iArrCreate2;
        }
        boolean zIsOne2 = sM2P256V1FieldElement6.isOne();
        if (zIsOne2) {
            iArr3 = sM2P256V1FieldElement.f3523x;
            iArr4 = sM2P256V1FieldElement2.f3523x;
        } else {
            SM2P256V1Field.square(sM2P256V1FieldElement6.f3523x, iArrCreate3);
            SM2P256V1Field.multiply(iArrCreate3, sM2P256V1FieldElement.f3523x, iArrCreateExt);
            SM2P256V1Field.multiply(iArrCreate3, sM2P256V1FieldElement6.f3523x, iArrCreate3);
            SM2P256V1Field.multiply(iArrCreate3, sM2P256V1FieldElement2.f3523x, iArrCreate3);
            iArr3 = iArrCreateExt;
            iArr4 = iArrCreate3;
        }
        int[] iArrCreate4 = Nat256.create();
        SM2P256V1Field.subtract(iArr3, iArr, iArrCreate4);
        SM2P256V1Field.subtract(iArr4, iArr2, iArrCreate);
        if (Nat256.isZero(iArrCreate4)) {
            return Nat256.isZero(iArrCreate) ? twice() : curve.getInfinity();
        }
        SM2P256V1Field.square(iArrCreate4, iArrCreate2);
        int[] iArrCreate5 = Nat256.create();
        SM2P256V1Field.multiply(iArrCreate2, iArrCreate4, iArrCreate5);
        SM2P256V1Field.multiply(iArrCreate2, iArr3, iArrCreate2);
        SM2P256V1Field.negate(iArrCreate5, iArrCreate5);
        Nat256.mul(iArr4, iArrCreate5, iArrCreateExt);
        SM2P256V1Field.reduce32(Nat256.addBothTo(iArrCreate2, iArrCreate2, iArrCreate5), iArrCreate5);
        SM2P256V1FieldElement sM2P256V1FieldElement7 = new SM2P256V1FieldElement(iArrCreate3);
        SM2P256V1Field.square(iArrCreate, sM2P256V1FieldElement7.f3523x);
        SM2P256V1Field.subtract(sM2P256V1FieldElement7.f3523x, iArrCreate5, sM2P256V1FieldElement7.f3523x);
        SM2P256V1FieldElement sM2P256V1FieldElement8 = new SM2P256V1FieldElement(iArrCreate5);
        SM2P256V1Field.subtract(iArrCreate2, sM2P256V1FieldElement7.f3523x, sM2P256V1FieldElement8.f3523x);
        SM2P256V1Field.multiplyAddToExt(sM2P256V1FieldElement8.f3523x, iArrCreate, iArrCreateExt);
        SM2P256V1Field.reduce(iArrCreateExt, sM2P256V1FieldElement8.f3523x);
        SM2P256V1FieldElement sM2P256V1FieldElement9 = new SM2P256V1FieldElement(iArrCreate4);
        if (!zIsOne) {
            SM2P256V1Field.multiply(sM2P256V1FieldElement9.f3523x, sM2P256V1FieldElement5.f3523x, sM2P256V1FieldElement9.f3523x);
        }
        if (!zIsOne2) {
            SM2P256V1Field.multiply(sM2P256V1FieldElement9.f3523x, sM2P256V1FieldElement6.f3523x, sM2P256V1FieldElement9.f3523x);
        }
        return new SM2P256V1Point(curve, sM2P256V1FieldElement7, sM2P256V1FieldElement8, new ECFieldElement[]{sM2P256V1FieldElement9});
    }

    @Override
    protected ECPoint detach() {
        return new SM2P256V1Point(null, getAffineXCoord(), getAffineYCoord());
    }

    @Override
    public ECPoint negate() {
        return isInfinity() ? this : new SM2P256V1Point(this.curve, this.f3508x, this.f3509y.negate(), this.f3510zs);
    }

    @Override
    public ECPoint threeTimes() {
        return (isInfinity() || this.f3509y.isZero()) ? this : twice().add(this);
    }

    @Override
    public ECPoint twice() {
        if (isInfinity()) {
            return this;
        }
        ECCurve curve = getCurve();
        SM2P256V1FieldElement sM2P256V1FieldElement = (SM2P256V1FieldElement) this.f3509y;
        if (sM2P256V1FieldElement.isZero()) {
            return curve.getInfinity();
        }
        SM2P256V1FieldElement sM2P256V1FieldElement2 = (SM2P256V1FieldElement) this.f3508x;
        SM2P256V1FieldElement sM2P256V1FieldElement3 = (SM2P256V1FieldElement) this.f3510zs[0];
        int[] iArrCreate = Nat256.create();
        int[] iArrCreate2 = Nat256.create();
        int[] iArrCreate3 = Nat256.create();
        SM2P256V1Field.square(sM2P256V1FieldElement.f3523x, iArrCreate3);
        int[] iArrCreate4 = Nat256.create();
        SM2P256V1Field.square(iArrCreate3, iArrCreate4);
        boolean zIsOne = sM2P256V1FieldElement3.isOne();
        int[] iArr = sM2P256V1FieldElement3.f3523x;
        if (!zIsOne) {
            SM2P256V1Field.square(sM2P256V1FieldElement3.f3523x, iArrCreate2);
            iArr = iArrCreate2;
        }
        SM2P256V1Field.subtract(sM2P256V1FieldElement2.f3523x, iArr, iArrCreate);
        SM2P256V1Field.add(sM2P256V1FieldElement2.f3523x, iArr, iArrCreate2);
        SM2P256V1Field.multiply(iArrCreate2, iArrCreate, iArrCreate2);
        SM2P256V1Field.reduce32(Nat256.addBothTo(iArrCreate2, iArrCreate2, iArrCreate2), iArrCreate2);
        SM2P256V1Field.multiply(iArrCreate3, sM2P256V1FieldElement2.f3523x, iArrCreate3);
        SM2P256V1Field.reduce32(Nat.shiftUpBits(8, iArrCreate3, 2, 0), iArrCreate3);
        SM2P256V1Field.reduce32(Nat.shiftUpBits(8, iArrCreate4, 3, 0, iArrCreate), iArrCreate);
        SM2P256V1FieldElement sM2P256V1FieldElement4 = new SM2P256V1FieldElement(iArrCreate4);
        SM2P256V1Field.square(iArrCreate2, sM2P256V1FieldElement4.f3523x);
        SM2P256V1Field.subtract(sM2P256V1FieldElement4.f3523x, iArrCreate3, sM2P256V1FieldElement4.f3523x);
        SM2P256V1Field.subtract(sM2P256V1FieldElement4.f3523x, iArrCreate3, sM2P256V1FieldElement4.f3523x);
        SM2P256V1FieldElement sM2P256V1FieldElement5 = new SM2P256V1FieldElement(iArrCreate3);
        SM2P256V1Field.subtract(iArrCreate3, sM2P256V1FieldElement4.f3523x, sM2P256V1FieldElement5.f3523x);
        SM2P256V1Field.multiply(sM2P256V1FieldElement5.f3523x, iArrCreate2, sM2P256V1FieldElement5.f3523x);
        SM2P256V1Field.subtract(sM2P256V1FieldElement5.f3523x, iArrCreate, sM2P256V1FieldElement5.f3523x);
        SM2P256V1FieldElement sM2P256V1FieldElement6 = new SM2P256V1FieldElement(iArrCreate2);
        SM2P256V1Field.twice(sM2P256V1FieldElement.f3523x, sM2P256V1FieldElement6.f3523x);
        if (!zIsOne) {
            SM2P256V1Field.multiply(sM2P256V1FieldElement6.f3523x, sM2P256V1FieldElement3.f3523x, sM2P256V1FieldElement6.f3523x);
        }
        return new SM2P256V1Point(curve, sM2P256V1FieldElement4, sM2P256V1FieldElement5, new ECFieldElement[]{sM2P256V1FieldElement6});
    }

    @Override
    public ECPoint twicePlus(ECPoint eCPoint) {
        return this == eCPoint ? threeTimes() : isInfinity() ? eCPoint : eCPoint.isInfinity() ? twice() : this.f3509y.isZero() ? eCPoint : twice().add(eCPoint);
    }
}
