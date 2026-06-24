package org.bouncycastle.math.p058ec.custom.sec;

import org.bouncycastle.math.p058ec.ECCurve;
import org.bouncycastle.math.p058ec.ECFieldElement;
import org.bouncycastle.math.p058ec.ECPoint;
import org.bouncycastle.math.raw.Nat;
import org.bouncycastle.math.raw.Nat192;

public class SecP192R1Point extends ECPoint.AbstractFp {
    SecP192R1Point(ECCurve eCCurve, ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2) {
        super(eCCurve, eCFieldElement, eCFieldElement2);
    }

    SecP192R1Point(ECCurve eCCurve, ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2, ECFieldElement[] eCFieldElementArr) {
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
        SecP192R1FieldElement secP192R1FieldElement = (SecP192R1FieldElement) this.f3508x;
        SecP192R1FieldElement secP192R1FieldElement2 = (SecP192R1FieldElement) this.f3509y;
        SecP192R1FieldElement secP192R1FieldElement3 = (SecP192R1FieldElement) eCPoint.getXCoord();
        SecP192R1FieldElement secP192R1FieldElement4 = (SecP192R1FieldElement) eCPoint.getYCoord();
        SecP192R1FieldElement secP192R1FieldElement5 = (SecP192R1FieldElement) this.f3510zs[0];
        SecP192R1FieldElement secP192R1FieldElement6 = (SecP192R1FieldElement) eCPoint.getZCoord(0);
        int[] iArrCreateExt = Nat192.createExt();
        int[] iArrCreate = Nat192.create();
        int[] iArrCreate2 = Nat192.create();
        int[] iArrCreate3 = Nat192.create();
        boolean zIsOne = secP192R1FieldElement5.isOne();
        if (zIsOne) {
            iArr = secP192R1FieldElement3.f3551x;
            iArr2 = secP192R1FieldElement4.f3551x;
        } else {
            SecP192R1Field.square(secP192R1FieldElement5.f3551x, iArrCreate2);
            SecP192R1Field.multiply(iArrCreate2, secP192R1FieldElement3.f3551x, iArrCreate);
            SecP192R1Field.multiply(iArrCreate2, secP192R1FieldElement5.f3551x, iArrCreate2);
            SecP192R1Field.multiply(iArrCreate2, secP192R1FieldElement4.f3551x, iArrCreate2);
            iArr = iArrCreate;
            iArr2 = iArrCreate2;
        }
        boolean zIsOne2 = secP192R1FieldElement6.isOne();
        if (zIsOne2) {
            iArr3 = secP192R1FieldElement.f3551x;
            iArr4 = secP192R1FieldElement2.f3551x;
        } else {
            SecP192R1Field.square(secP192R1FieldElement6.f3551x, iArrCreate3);
            SecP192R1Field.multiply(iArrCreate3, secP192R1FieldElement.f3551x, iArrCreateExt);
            SecP192R1Field.multiply(iArrCreate3, secP192R1FieldElement6.f3551x, iArrCreate3);
            SecP192R1Field.multiply(iArrCreate3, secP192R1FieldElement2.f3551x, iArrCreate3);
            iArr3 = iArrCreateExt;
            iArr4 = iArrCreate3;
        }
        int[] iArrCreate4 = Nat192.create();
        SecP192R1Field.subtract(iArr3, iArr, iArrCreate4);
        SecP192R1Field.subtract(iArr4, iArr2, iArrCreate);
        if (Nat192.isZero(iArrCreate4)) {
            return Nat192.isZero(iArrCreate) ? twice() : curve.getInfinity();
        }
        SecP192R1Field.square(iArrCreate4, iArrCreate2);
        int[] iArrCreate5 = Nat192.create();
        SecP192R1Field.multiply(iArrCreate2, iArrCreate4, iArrCreate5);
        SecP192R1Field.multiply(iArrCreate2, iArr3, iArrCreate2);
        SecP192R1Field.negate(iArrCreate5, iArrCreate5);
        Nat192.mul(iArr4, iArrCreate5, iArrCreateExt);
        SecP192R1Field.reduce32(Nat192.addBothTo(iArrCreate2, iArrCreate2, iArrCreate5), iArrCreate5);
        SecP192R1FieldElement secP192R1FieldElement7 = new SecP192R1FieldElement(iArrCreate3);
        SecP192R1Field.square(iArrCreate, secP192R1FieldElement7.f3551x);
        SecP192R1Field.subtract(secP192R1FieldElement7.f3551x, iArrCreate5, secP192R1FieldElement7.f3551x);
        SecP192R1FieldElement secP192R1FieldElement8 = new SecP192R1FieldElement(iArrCreate5);
        SecP192R1Field.subtract(iArrCreate2, secP192R1FieldElement7.f3551x, secP192R1FieldElement8.f3551x);
        SecP192R1Field.multiplyAddToExt(secP192R1FieldElement8.f3551x, iArrCreate, iArrCreateExt);
        SecP192R1Field.reduce(iArrCreateExt, secP192R1FieldElement8.f3551x);
        SecP192R1FieldElement secP192R1FieldElement9 = new SecP192R1FieldElement(iArrCreate4);
        if (!zIsOne) {
            SecP192R1Field.multiply(secP192R1FieldElement9.f3551x, secP192R1FieldElement5.f3551x, secP192R1FieldElement9.f3551x);
        }
        if (!zIsOne2) {
            SecP192R1Field.multiply(secP192R1FieldElement9.f3551x, secP192R1FieldElement6.f3551x, secP192R1FieldElement9.f3551x);
        }
        return new SecP192R1Point(curve, secP192R1FieldElement7, secP192R1FieldElement8, new ECFieldElement[]{secP192R1FieldElement9});
    }

    @Override
    protected ECPoint detach() {
        return new SecP192R1Point(null, getAffineXCoord(), getAffineYCoord());
    }

    @Override
    public ECPoint negate() {
        return isInfinity() ? this : new SecP192R1Point(this.curve, this.f3508x, this.f3509y.negate(), this.f3510zs);
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
        SecP192R1FieldElement secP192R1FieldElement = (SecP192R1FieldElement) this.f3509y;
        if (secP192R1FieldElement.isZero()) {
            return curve.getInfinity();
        }
        SecP192R1FieldElement secP192R1FieldElement2 = (SecP192R1FieldElement) this.f3508x;
        SecP192R1FieldElement secP192R1FieldElement3 = (SecP192R1FieldElement) this.f3510zs[0];
        int[] iArrCreate = Nat192.create();
        int[] iArrCreate2 = Nat192.create();
        int[] iArrCreate3 = Nat192.create();
        SecP192R1Field.square(secP192R1FieldElement.f3551x, iArrCreate3);
        int[] iArrCreate4 = Nat192.create();
        SecP192R1Field.square(iArrCreate3, iArrCreate4);
        boolean zIsOne = secP192R1FieldElement3.isOne();
        int[] iArr = secP192R1FieldElement3.f3551x;
        if (!zIsOne) {
            SecP192R1Field.square(secP192R1FieldElement3.f3551x, iArrCreate2);
            iArr = iArrCreate2;
        }
        SecP192R1Field.subtract(secP192R1FieldElement2.f3551x, iArr, iArrCreate);
        SecP192R1Field.add(secP192R1FieldElement2.f3551x, iArr, iArrCreate2);
        SecP192R1Field.multiply(iArrCreate2, iArrCreate, iArrCreate2);
        SecP192R1Field.reduce32(Nat192.addBothTo(iArrCreate2, iArrCreate2, iArrCreate2), iArrCreate2);
        SecP192R1Field.multiply(iArrCreate3, secP192R1FieldElement2.f3551x, iArrCreate3);
        SecP192R1Field.reduce32(Nat.shiftUpBits(6, iArrCreate3, 2, 0), iArrCreate3);
        SecP192R1Field.reduce32(Nat.shiftUpBits(6, iArrCreate4, 3, 0, iArrCreate), iArrCreate);
        SecP192R1FieldElement secP192R1FieldElement4 = new SecP192R1FieldElement(iArrCreate4);
        SecP192R1Field.square(iArrCreate2, secP192R1FieldElement4.f3551x);
        SecP192R1Field.subtract(secP192R1FieldElement4.f3551x, iArrCreate3, secP192R1FieldElement4.f3551x);
        SecP192R1Field.subtract(secP192R1FieldElement4.f3551x, iArrCreate3, secP192R1FieldElement4.f3551x);
        SecP192R1FieldElement secP192R1FieldElement5 = new SecP192R1FieldElement(iArrCreate3);
        SecP192R1Field.subtract(iArrCreate3, secP192R1FieldElement4.f3551x, secP192R1FieldElement5.f3551x);
        SecP192R1Field.multiply(secP192R1FieldElement5.f3551x, iArrCreate2, secP192R1FieldElement5.f3551x);
        SecP192R1Field.subtract(secP192R1FieldElement5.f3551x, iArrCreate, secP192R1FieldElement5.f3551x);
        SecP192R1FieldElement secP192R1FieldElement6 = new SecP192R1FieldElement(iArrCreate2);
        SecP192R1Field.twice(secP192R1FieldElement.f3551x, secP192R1FieldElement6.f3551x);
        if (!zIsOne) {
            SecP192R1Field.multiply(secP192R1FieldElement6.f3551x, secP192R1FieldElement3.f3551x, secP192R1FieldElement6.f3551x);
        }
        return new SecP192R1Point(curve, secP192R1FieldElement4, secP192R1FieldElement5, new ECFieldElement[]{secP192R1FieldElement6});
    }

    @Override
    public ECPoint twicePlus(ECPoint eCPoint) {
        return this == eCPoint ? threeTimes() : isInfinity() ? eCPoint : eCPoint.isInfinity() ? twice() : this.f3509y.isZero() ? eCPoint : twice().add(eCPoint);
    }
}
