package org.bouncycastle.math.p058ec.custom.sec;

import org.bouncycastle.math.p058ec.ECCurve;
import org.bouncycastle.math.p058ec.ECFieldElement;
import org.bouncycastle.math.p058ec.ECPoint;
import org.bouncycastle.math.raw.Nat;
import org.bouncycastle.math.raw.Nat256;

public class SecP256K1Point extends ECPoint.AbstractFp {
    SecP256K1Point(ECCurve eCCurve, ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2) {
        super(eCCurve, eCFieldElement, eCFieldElement2);
    }

    SecP256K1Point(ECCurve eCCurve, ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2, ECFieldElement[] eCFieldElementArr) {
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
        SecP256K1FieldElement secP256K1FieldElement = (SecP256K1FieldElement) this.f3508x;
        SecP256K1FieldElement secP256K1FieldElement2 = (SecP256K1FieldElement) this.f3509y;
        SecP256K1FieldElement secP256K1FieldElement3 = (SecP256K1FieldElement) eCPoint.getXCoord();
        SecP256K1FieldElement secP256K1FieldElement4 = (SecP256K1FieldElement) eCPoint.getYCoord();
        SecP256K1FieldElement secP256K1FieldElement5 = (SecP256K1FieldElement) this.f3510zs[0];
        SecP256K1FieldElement secP256K1FieldElement6 = (SecP256K1FieldElement) eCPoint.getZCoord(0);
        int[] iArrCreateExt = Nat256.createExt();
        int[] iArrCreate = Nat256.create();
        int[] iArrCreate2 = Nat256.create();
        int[] iArrCreate3 = Nat256.create();
        boolean zIsOne = secP256K1FieldElement5.isOne();
        if (zIsOne) {
            iArr = secP256K1FieldElement3.f3567x;
            iArr2 = secP256K1FieldElement4.f3567x;
        } else {
            SecP256K1Field.square(secP256K1FieldElement5.f3567x, iArrCreate2);
            SecP256K1Field.multiply(iArrCreate2, secP256K1FieldElement3.f3567x, iArrCreate);
            SecP256K1Field.multiply(iArrCreate2, secP256K1FieldElement5.f3567x, iArrCreate2);
            SecP256K1Field.multiply(iArrCreate2, secP256K1FieldElement4.f3567x, iArrCreate2);
            iArr = iArrCreate;
            iArr2 = iArrCreate2;
        }
        boolean zIsOne2 = secP256K1FieldElement6.isOne();
        if (zIsOne2) {
            iArr3 = secP256K1FieldElement.f3567x;
            iArr4 = secP256K1FieldElement2.f3567x;
        } else {
            SecP256K1Field.square(secP256K1FieldElement6.f3567x, iArrCreate3);
            SecP256K1Field.multiply(iArrCreate3, secP256K1FieldElement.f3567x, iArrCreateExt);
            SecP256K1Field.multiply(iArrCreate3, secP256K1FieldElement6.f3567x, iArrCreate3);
            SecP256K1Field.multiply(iArrCreate3, secP256K1FieldElement2.f3567x, iArrCreate3);
            iArr3 = iArrCreateExt;
            iArr4 = iArrCreate3;
        }
        int[] iArrCreate4 = Nat256.create();
        SecP256K1Field.subtract(iArr3, iArr, iArrCreate4);
        SecP256K1Field.subtract(iArr4, iArr2, iArrCreate);
        if (Nat256.isZero(iArrCreate4)) {
            return Nat256.isZero(iArrCreate) ? twice() : curve.getInfinity();
        }
        SecP256K1Field.square(iArrCreate4, iArrCreate2);
        int[] iArrCreate5 = Nat256.create();
        SecP256K1Field.multiply(iArrCreate2, iArrCreate4, iArrCreate5);
        SecP256K1Field.multiply(iArrCreate2, iArr3, iArrCreate2);
        SecP256K1Field.negate(iArrCreate5, iArrCreate5);
        Nat256.mul(iArr4, iArrCreate5, iArrCreateExt);
        SecP256K1Field.reduce32(Nat256.addBothTo(iArrCreate2, iArrCreate2, iArrCreate5), iArrCreate5);
        SecP256K1FieldElement secP256K1FieldElement7 = new SecP256K1FieldElement(iArrCreate3);
        SecP256K1Field.square(iArrCreate, secP256K1FieldElement7.f3567x);
        SecP256K1Field.subtract(secP256K1FieldElement7.f3567x, iArrCreate5, secP256K1FieldElement7.f3567x);
        SecP256K1FieldElement secP256K1FieldElement8 = new SecP256K1FieldElement(iArrCreate5);
        SecP256K1Field.subtract(iArrCreate2, secP256K1FieldElement7.f3567x, secP256K1FieldElement8.f3567x);
        SecP256K1Field.multiplyAddToExt(secP256K1FieldElement8.f3567x, iArrCreate, iArrCreateExt);
        SecP256K1Field.reduce(iArrCreateExt, secP256K1FieldElement8.f3567x);
        SecP256K1FieldElement secP256K1FieldElement9 = new SecP256K1FieldElement(iArrCreate4);
        if (!zIsOne) {
            SecP256K1Field.multiply(secP256K1FieldElement9.f3567x, secP256K1FieldElement5.f3567x, secP256K1FieldElement9.f3567x);
        }
        if (!zIsOne2) {
            SecP256K1Field.multiply(secP256K1FieldElement9.f3567x, secP256K1FieldElement6.f3567x, secP256K1FieldElement9.f3567x);
        }
        return new SecP256K1Point(curve, secP256K1FieldElement7, secP256K1FieldElement8, new ECFieldElement[]{secP256K1FieldElement9});
    }

    @Override
    protected ECPoint detach() {
        return new SecP256K1Point(null, getAffineXCoord(), getAffineYCoord());
    }

    @Override
    public ECPoint negate() {
        return isInfinity() ? this : new SecP256K1Point(this.curve, this.f3508x, this.f3509y.negate(), this.f3510zs);
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
        SecP256K1FieldElement secP256K1FieldElement = (SecP256K1FieldElement) this.f3509y;
        if (secP256K1FieldElement.isZero()) {
            return curve.getInfinity();
        }
        SecP256K1FieldElement secP256K1FieldElement2 = (SecP256K1FieldElement) this.f3508x;
        SecP256K1FieldElement secP256K1FieldElement3 = (SecP256K1FieldElement) this.f3510zs[0];
        int[] iArrCreate = Nat256.create();
        SecP256K1Field.square(secP256K1FieldElement.f3567x, iArrCreate);
        int[] iArrCreate2 = Nat256.create();
        SecP256K1Field.square(iArrCreate, iArrCreate2);
        int[] iArrCreate3 = Nat256.create();
        SecP256K1Field.square(secP256K1FieldElement2.f3567x, iArrCreate3);
        SecP256K1Field.reduce32(Nat256.addBothTo(iArrCreate3, iArrCreate3, iArrCreate3), iArrCreate3);
        SecP256K1Field.multiply(iArrCreate, secP256K1FieldElement2.f3567x, iArrCreate);
        SecP256K1Field.reduce32(Nat.shiftUpBits(8, iArrCreate, 2, 0), iArrCreate);
        int[] iArrCreate4 = Nat256.create();
        SecP256K1Field.reduce32(Nat.shiftUpBits(8, iArrCreate2, 3, 0, iArrCreate4), iArrCreate4);
        SecP256K1FieldElement secP256K1FieldElement4 = new SecP256K1FieldElement(iArrCreate2);
        SecP256K1Field.square(iArrCreate3, secP256K1FieldElement4.f3567x);
        SecP256K1Field.subtract(secP256K1FieldElement4.f3567x, iArrCreate, secP256K1FieldElement4.f3567x);
        SecP256K1Field.subtract(secP256K1FieldElement4.f3567x, iArrCreate, secP256K1FieldElement4.f3567x);
        SecP256K1FieldElement secP256K1FieldElement5 = new SecP256K1FieldElement(iArrCreate);
        SecP256K1Field.subtract(iArrCreate, secP256K1FieldElement4.f3567x, secP256K1FieldElement5.f3567x);
        SecP256K1Field.multiply(secP256K1FieldElement5.f3567x, iArrCreate3, secP256K1FieldElement5.f3567x);
        SecP256K1Field.subtract(secP256K1FieldElement5.f3567x, iArrCreate4, secP256K1FieldElement5.f3567x);
        SecP256K1FieldElement secP256K1FieldElement6 = new SecP256K1FieldElement(iArrCreate3);
        SecP256K1Field.twice(secP256K1FieldElement.f3567x, secP256K1FieldElement6.f3567x);
        if (!secP256K1FieldElement3.isOne()) {
            SecP256K1Field.multiply(secP256K1FieldElement6.f3567x, secP256K1FieldElement3.f3567x, secP256K1FieldElement6.f3567x);
        }
        return new SecP256K1Point(curve, secP256K1FieldElement4, secP256K1FieldElement5, new ECFieldElement[]{secP256K1FieldElement6});
    }

    @Override
    public ECPoint twicePlus(ECPoint eCPoint) {
        return this == eCPoint ? threeTimes() : isInfinity() ? eCPoint : eCPoint.isInfinity() ? twice() : this.f3509y.isZero() ? eCPoint : twice().add(eCPoint);
    }
}
