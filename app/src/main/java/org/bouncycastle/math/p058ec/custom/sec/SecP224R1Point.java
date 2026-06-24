package org.bouncycastle.math.p058ec.custom.sec;

import org.bouncycastle.math.p058ec.ECCurve;
import org.bouncycastle.math.p058ec.ECFieldElement;
import org.bouncycastle.math.p058ec.ECPoint;
import org.bouncycastle.math.raw.Nat;
import org.bouncycastle.math.raw.Nat224;

public class SecP224R1Point extends ECPoint.AbstractFp {
    SecP224R1Point(ECCurve eCCurve, ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2) {
        super(eCCurve, eCFieldElement, eCFieldElement2);
    }

    SecP224R1Point(ECCurve eCCurve, ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2, ECFieldElement[] eCFieldElementArr) {
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
        SecP224R1FieldElement secP224R1FieldElement = (SecP224R1FieldElement) this.f3508x;
        SecP224R1FieldElement secP224R1FieldElement2 = (SecP224R1FieldElement) this.f3509y;
        SecP224R1FieldElement secP224R1FieldElement3 = (SecP224R1FieldElement) eCPoint.getXCoord();
        SecP224R1FieldElement secP224R1FieldElement4 = (SecP224R1FieldElement) eCPoint.getYCoord();
        SecP224R1FieldElement secP224R1FieldElement5 = (SecP224R1FieldElement) this.f3510zs[0];
        SecP224R1FieldElement secP224R1FieldElement6 = (SecP224R1FieldElement) eCPoint.getZCoord(0);
        int[] iArrCreateExt = Nat224.createExt();
        int[] iArrCreate = Nat224.create();
        int[] iArrCreate2 = Nat224.create();
        int[] iArrCreate3 = Nat224.create();
        boolean zIsOne = secP224R1FieldElement5.isOne();
        if (zIsOne) {
            iArr = secP224R1FieldElement3.f3562x;
            iArr2 = secP224R1FieldElement4.f3562x;
        } else {
            SecP224R1Field.square(secP224R1FieldElement5.f3562x, iArrCreate2);
            SecP224R1Field.multiply(iArrCreate2, secP224R1FieldElement3.f3562x, iArrCreate);
            SecP224R1Field.multiply(iArrCreate2, secP224R1FieldElement5.f3562x, iArrCreate2);
            SecP224R1Field.multiply(iArrCreate2, secP224R1FieldElement4.f3562x, iArrCreate2);
            iArr = iArrCreate;
            iArr2 = iArrCreate2;
        }
        boolean zIsOne2 = secP224R1FieldElement6.isOne();
        if (zIsOne2) {
            iArr3 = secP224R1FieldElement.f3562x;
            iArr4 = secP224R1FieldElement2.f3562x;
        } else {
            SecP224R1Field.square(secP224R1FieldElement6.f3562x, iArrCreate3);
            SecP224R1Field.multiply(iArrCreate3, secP224R1FieldElement.f3562x, iArrCreateExt);
            SecP224R1Field.multiply(iArrCreate3, secP224R1FieldElement6.f3562x, iArrCreate3);
            SecP224R1Field.multiply(iArrCreate3, secP224R1FieldElement2.f3562x, iArrCreate3);
            iArr3 = iArrCreateExt;
            iArr4 = iArrCreate3;
        }
        int[] iArrCreate4 = Nat224.create();
        SecP224R1Field.subtract(iArr3, iArr, iArrCreate4);
        SecP224R1Field.subtract(iArr4, iArr2, iArrCreate);
        if (Nat224.isZero(iArrCreate4)) {
            return Nat224.isZero(iArrCreate) ? twice() : curve.getInfinity();
        }
        SecP224R1Field.square(iArrCreate4, iArrCreate2);
        int[] iArrCreate5 = Nat224.create();
        SecP224R1Field.multiply(iArrCreate2, iArrCreate4, iArrCreate5);
        SecP224R1Field.multiply(iArrCreate2, iArr3, iArrCreate2);
        SecP224R1Field.negate(iArrCreate5, iArrCreate5);
        Nat224.mul(iArr4, iArrCreate5, iArrCreateExt);
        SecP224R1Field.reduce32(Nat224.addBothTo(iArrCreate2, iArrCreate2, iArrCreate5), iArrCreate5);
        SecP224R1FieldElement secP224R1FieldElement7 = new SecP224R1FieldElement(iArrCreate3);
        SecP224R1Field.square(iArrCreate, secP224R1FieldElement7.f3562x);
        SecP224R1Field.subtract(secP224R1FieldElement7.f3562x, iArrCreate5, secP224R1FieldElement7.f3562x);
        SecP224R1FieldElement secP224R1FieldElement8 = new SecP224R1FieldElement(iArrCreate5);
        SecP224R1Field.subtract(iArrCreate2, secP224R1FieldElement7.f3562x, secP224R1FieldElement8.f3562x);
        SecP224R1Field.multiplyAddToExt(secP224R1FieldElement8.f3562x, iArrCreate, iArrCreateExt);
        SecP224R1Field.reduce(iArrCreateExt, secP224R1FieldElement8.f3562x);
        SecP224R1FieldElement secP224R1FieldElement9 = new SecP224R1FieldElement(iArrCreate4);
        if (!zIsOne) {
            SecP224R1Field.multiply(secP224R1FieldElement9.f3562x, secP224R1FieldElement5.f3562x, secP224R1FieldElement9.f3562x);
        }
        if (!zIsOne2) {
            SecP224R1Field.multiply(secP224R1FieldElement9.f3562x, secP224R1FieldElement6.f3562x, secP224R1FieldElement9.f3562x);
        }
        return new SecP224R1Point(curve, secP224R1FieldElement7, secP224R1FieldElement8, new ECFieldElement[]{secP224R1FieldElement9});
    }

    @Override
    protected ECPoint detach() {
        return new SecP224R1Point(null, getAffineXCoord(), getAffineYCoord());
    }

    @Override
    public ECPoint negate() {
        return isInfinity() ? this : new SecP224R1Point(this.curve, this.f3508x, this.f3509y.negate(), this.f3510zs);
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
        SecP224R1FieldElement secP224R1FieldElement = (SecP224R1FieldElement) this.f3509y;
        if (secP224R1FieldElement.isZero()) {
            return curve.getInfinity();
        }
        SecP224R1FieldElement secP224R1FieldElement2 = (SecP224R1FieldElement) this.f3508x;
        SecP224R1FieldElement secP224R1FieldElement3 = (SecP224R1FieldElement) this.f3510zs[0];
        int[] iArrCreate = Nat224.create();
        int[] iArrCreate2 = Nat224.create();
        int[] iArrCreate3 = Nat224.create();
        SecP224R1Field.square(secP224R1FieldElement.f3562x, iArrCreate3);
        int[] iArrCreate4 = Nat224.create();
        SecP224R1Field.square(iArrCreate3, iArrCreate4);
        boolean zIsOne = secP224R1FieldElement3.isOne();
        int[] iArr = secP224R1FieldElement3.f3562x;
        if (!zIsOne) {
            SecP224R1Field.square(secP224R1FieldElement3.f3562x, iArrCreate2);
            iArr = iArrCreate2;
        }
        SecP224R1Field.subtract(secP224R1FieldElement2.f3562x, iArr, iArrCreate);
        SecP224R1Field.add(secP224R1FieldElement2.f3562x, iArr, iArrCreate2);
        SecP224R1Field.multiply(iArrCreate2, iArrCreate, iArrCreate2);
        SecP224R1Field.reduce32(Nat224.addBothTo(iArrCreate2, iArrCreate2, iArrCreate2), iArrCreate2);
        SecP224R1Field.multiply(iArrCreate3, secP224R1FieldElement2.f3562x, iArrCreate3);
        SecP224R1Field.reduce32(Nat.shiftUpBits(7, iArrCreate3, 2, 0), iArrCreate3);
        SecP224R1Field.reduce32(Nat.shiftUpBits(7, iArrCreate4, 3, 0, iArrCreate), iArrCreate);
        SecP224R1FieldElement secP224R1FieldElement4 = new SecP224R1FieldElement(iArrCreate4);
        SecP224R1Field.square(iArrCreate2, secP224R1FieldElement4.f3562x);
        SecP224R1Field.subtract(secP224R1FieldElement4.f3562x, iArrCreate3, secP224R1FieldElement4.f3562x);
        SecP224R1Field.subtract(secP224R1FieldElement4.f3562x, iArrCreate3, secP224R1FieldElement4.f3562x);
        SecP224R1FieldElement secP224R1FieldElement5 = new SecP224R1FieldElement(iArrCreate3);
        SecP224R1Field.subtract(iArrCreate3, secP224R1FieldElement4.f3562x, secP224R1FieldElement5.f3562x);
        SecP224R1Field.multiply(secP224R1FieldElement5.f3562x, iArrCreate2, secP224R1FieldElement5.f3562x);
        SecP224R1Field.subtract(secP224R1FieldElement5.f3562x, iArrCreate, secP224R1FieldElement5.f3562x);
        SecP224R1FieldElement secP224R1FieldElement6 = new SecP224R1FieldElement(iArrCreate2);
        SecP224R1Field.twice(secP224R1FieldElement.f3562x, secP224R1FieldElement6.f3562x);
        if (!zIsOne) {
            SecP224R1Field.multiply(secP224R1FieldElement6.f3562x, secP224R1FieldElement3.f3562x, secP224R1FieldElement6.f3562x);
        }
        return new SecP224R1Point(curve, secP224R1FieldElement4, secP224R1FieldElement5, new ECFieldElement[]{secP224R1FieldElement6});
    }

    @Override
    public ECPoint twicePlus(ECPoint eCPoint) {
        return this == eCPoint ? threeTimes() : isInfinity() ? eCPoint : eCPoint.isInfinity() ? twice() : this.f3509y.isZero() ? eCPoint : twice().add(eCPoint);
    }
}
