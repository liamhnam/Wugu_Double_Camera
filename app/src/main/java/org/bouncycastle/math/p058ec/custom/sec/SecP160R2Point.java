package org.bouncycastle.math.p058ec.custom.sec;

import org.bouncycastle.math.p058ec.ECCurve;
import org.bouncycastle.math.p058ec.ECFieldElement;
import org.bouncycastle.math.p058ec.ECPoint;
import org.bouncycastle.math.raw.Nat;
import org.bouncycastle.math.raw.Nat160;

public class SecP160R2Point extends ECPoint.AbstractFp {
    SecP160R2Point(ECCurve eCCurve, ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2) {
        super(eCCurve, eCFieldElement, eCFieldElement2);
    }

    SecP160R2Point(ECCurve eCCurve, ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2, ECFieldElement[] eCFieldElementArr) {
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
        SecP160R2FieldElement secP160R2FieldElement = (SecP160R2FieldElement) this.f3508x;
        SecP160R2FieldElement secP160R2FieldElement2 = (SecP160R2FieldElement) this.f3509y;
        SecP160R2FieldElement secP160R2FieldElement3 = (SecP160R2FieldElement) eCPoint.getXCoord();
        SecP160R2FieldElement secP160R2FieldElement4 = (SecP160R2FieldElement) eCPoint.getYCoord();
        SecP160R2FieldElement secP160R2FieldElement5 = (SecP160R2FieldElement) this.f3510zs[0];
        SecP160R2FieldElement secP160R2FieldElement6 = (SecP160R2FieldElement) eCPoint.getZCoord(0);
        int[] iArrCreateExt = Nat160.createExt();
        int[] iArrCreate = Nat160.create();
        int[] iArrCreate2 = Nat160.create();
        int[] iArrCreate3 = Nat160.create();
        boolean zIsOne = secP160R2FieldElement5.isOne();
        if (zIsOne) {
            iArr = secP160R2FieldElement3.f3540x;
            iArr2 = secP160R2FieldElement4.f3540x;
        } else {
            SecP160R2Field.square(secP160R2FieldElement5.f3540x, iArrCreate2);
            SecP160R2Field.multiply(iArrCreate2, secP160R2FieldElement3.f3540x, iArrCreate);
            SecP160R2Field.multiply(iArrCreate2, secP160R2FieldElement5.f3540x, iArrCreate2);
            SecP160R2Field.multiply(iArrCreate2, secP160R2FieldElement4.f3540x, iArrCreate2);
            iArr = iArrCreate;
            iArr2 = iArrCreate2;
        }
        boolean zIsOne2 = secP160R2FieldElement6.isOne();
        if (zIsOne2) {
            iArr3 = secP160R2FieldElement.f3540x;
            iArr4 = secP160R2FieldElement2.f3540x;
        } else {
            SecP160R2Field.square(secP160R2FieldElement6.f3540x, iArrCreate3);
            SecP160R2Field.multiply(iArrCreate3, secP160R2FieldElement.f3540x, iArrCreateExt);
            SecP160R2Field.multiply(iArrCreate3, secP160R2FieldElement6.f3540x, iArrCreate3);
            SecP160R2Field.multiply(iArrCreate3, secP160R2FieldElement2.f3540x, iArrCreate3);
            iArr3 = iArrCreateExt;
            iArr4 = iArrCreate3;
        }
        int[] iArrCreate4 = Nat160.create();
        SecP160R2Field.subtract(iArr3, iArr, iArrCreate4);
        SecP160R2Field.subtract(iArr4, iArr2, iArrCreate);
        if (Nat160.isZero(iArrCreate4)) {
            return Nat160.isZero(iArrCreate) ? twice() : curve.getInfinity();
        }
        SecP160R2Field.square(iArrCreate4, iArrCreate2);
        int[] iArrCreate5 = Nat160.create();
        SecP160R2Field.multiply(iArrCreate2, iArrCreate4, iArrCreate5);
        SecP160R2Field.multiply(iArrCreate2, iArr3, iArrCreate2);
        SecP160R2Field.negate(iArrCreate5, iArrCreate5);
        Nat160.mul(iArr4, iArrCreate5, iArrCreateExt);
        SecP160R2Field.reduce32(Nat160.addBothTo(iArrCreate2, iArrCreate2, iArrCreate5), iArrCreate5);
        SecP160R2FieldElement secP160R2FieldElement7 = new SecP160R2FieldElement(iArrCreate3);
        SecP160R2Field.square(iArrCreate, secP160R2FieldElement7.f3540x);
        SecP160R2Field.subtract(secP160R2FieldElement7.f3540x, iArrCreate5, secP160R2FieldElement7.f3540x);
        SecP160R2FieldElement secP160R2FieldElement8 = new SecP160R2FieldElement(iArrCreate5);
        SecP160R2Field.subtract(iArrCreate2, secP160R2FieldElement7.f3540x, secP160R2FieldElement8.f3540x);
        SecP160R2Field.multiplyAddToExt(secP160R2FieldElement8.f3540x, iArrCreate, iArrCreateExt);
        SecP160R2Field.reduce(iArrCreateExt, secP160R2FieldElement8.f3540x);
        SecP160R2FieldElement secP160R2FieldElement9 = new SecP160R2FieldElement(iArrCreate4);
        if (!zIsOne) {
            SecP160R2Field.multiply(secP160R2FieldElement9.f3540x, secP160R2FieldElement5.f3540x, secP160R2FieldElement9.f3540x);
        }
        if (!zIsOne2) {
            SecP160R2Field.multiply(secP160R2FieldElement9.f3540x, secP160R2FieldElement6.f3540x, secP160R2FieldElement9.f3540x);
        }
        return new SecP160R2Point(curve, secP160R2FieldElement7, secP160R2FieldElement8, new ECFieldElement[]{secP160R2FieldElement9});
    }

    @Override
    protected ECPoint detach() {
        return new SecP160R2Point(null, getAffineXCoord(), getAffineYCoord());
    }

    @Override
    public ECPoint negate() {
        return isInfinity() ? this : new SecP160R2Point(this.curve, this.f3508x, this.f3509y.negate(), this.f3510zs);
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
        SecP160R2FieldElement secP160R2FieldElement = (SecP160R2FieldElement) this.f3509y;
        if (secP160R2FieldElement.isZero()) {
            return curve.getInfinity();
        }
        SecP160R2FieldElement secP160R2FieldElement2 = (SecP160R2FieldElement) this.f3508x;
        SecP160R2FieldElement secP160R2FieldElement3 = (SecP160R2FieldElement) this.f3510zs[0];
        int[] iArrCreate = Nat160.create();
        int[] iArrCreate2 = Nat160.create();
        int[] iArrCreate3 = Nat160.create();
        SecP160R2Field.square(secP160R2FieldElement.f3540x, iArrCreate3);
        int[] iArrCreate4 = Nat160.create();
        SecP160R2Field.square(iArrCreate3, iArrCreate4);
        boolean zIsOne = secP160R2FieldElement3.isOne();
        int[] iArr = secP160R2FieldElement3.f3540x;
        if (!zIsOne) {
            SecP160R2Field.square(secP160R2FieldElement3.f3540x, iArrCreate2);
            iArr = iArrCreate2;
        }
        SecP160R2Field.subtract(secP160R2FieldElement2.f3540x, iArr, iArrCreate);
        SecP160R2Field.add(secP160R2FieldElement2.f3540x, iArr, iArrCreate2);
        SecP160R2Field.multiply(iArrCreate2, iArrCreate, iArrCreate2);
        SecP160R2Field.reduce32(Nat160.addBothTo(iArrCreate2, iArrCreate2, iArrCreate2), iArrCreate2);
        SecP160R2Field.multiply(iArrCreate3, secP160R2FieldElement2.f3540x, iArrCreate3);
        SecP160R2Field.reduce32(Nat.shiftUpBits(5, iArrCreate3, 2, 0), iArrCreate3);
        SecP160R2Field.reduce32(Nat.shiftUpBits(5, iArrCreate4, 3, 0, iArrCreate), iArrCreate);
        SecP160R2FieldElement secP160R2FieldElement4 = new SecP160R2FieldElement(iArrCreate4);
        SecP160R2Field.square(iArrCreate2, secP160R2FieldElement4.f3540x);
        SecP160R2Field.subtract(secP160R2FieldElement4.f3540x, iArrCreate3, secP160R2FieldElement4.f3540x);
        SecP160R2Field.subtract(secP160R2FieldElement4.f3540x, iArrCreate3, secP160R2FieldElement4.f3540x);
        SecP160R2FieldElement secP160R2FieldElement5 = new SecP160R2FieldElement(iArrCreate3);
        SecP160R2Field.subtract(iArrCreate3, secP160R2FieldElement4.f3540x, secP160R2FieldElement5.f3540x);
        SecP160R2Field.multiply(secP160R2FieldElement5.f3540x, iArrCreate2, secP160R2FieldElement5.f3540x);
        SecP160R2Field.subtract(secP160R2FieldElement5.f3540x, iArrCreate, secP160R2FieldElement5.f3540x);
        SecP160R2FieldElement secP160R2FieldElement6 = new SecP160R2FieldElement(iArrCreate2);
        SecP160R2Field.twice(secP160R2FieldElement.f3540x, secP160R2FieldElement6.f3540x);
        if (!zIsOne) {
            SecP160R2Field.multiply(secP160R2FieldElement6.f3540x, secP160R2FieldElement3.f3540x, secP160R2FieldElement6.f3540x);
        }
        return new SecP160R2Point(curve, secP160R2FieldElement4, secP160R2FieldElement5, new ECFieldElement[]{secP160R2FieldElement6});
    }

    @Override
    public ECPoint twicePlus(ECPoint eCPoint) {
        return this == eCPoint ? threeTimes() : isInfinity() ? eCPoint : eCPoint.isInfinity() ? twice() : this.f3509y.isZero() ? eCPoint : twice().add(eCPoint);
    }
}
