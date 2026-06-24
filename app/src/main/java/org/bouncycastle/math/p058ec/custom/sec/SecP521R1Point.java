package org.bouncycastle.math.p058ec.custom.sec;

import org.bouncycastle.math.p058ec.ECCurve;
import org.bouncycastle.math.p058ec.ECFieldElement;
import org.bouncycastle.math.p058ec.ECPoint;
import org.bouncycastle.math.raw.Nat;

public class SecP521R1Point extends ECPoint.AbstractFp {
    SecP521R1Point(ECCurve eCCurve, ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2) {
        super(eCCurve, eCFieldElement, eCFieldElement2);
    }

    SecP521R1Point(ECCurve eCCurve, ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2, ECFieldElement[] eCFieldElementArr) {
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
        SecP521R1FieldElement secP521R1FieldElement = (SecP521R1FieldElement) this.f3508x;
        SecP521R1FieldElement secP521R1FieldElement2 = (SecP521R1FieldElement) this.f3509y;
        SecP521R1FieldElement secP521R1FieldElement3 = (SecP521R1FieldElement) eCPoint.getXCoord();
        SecP521R1FieldElement secP521R1FieldElement4 = (SecP521R1FieldElement) eCPoint.getYCoord();
        SecP521R1FieldElement secP521R1FieldElement5 = (SecP521R1FieldElement) this.f3510zs[0];
        SecP521R1FieldElement secP521R1FieldElement6 = (SecP521R1FieldElement) eCPoint.getZCoord(0);
        int[] iArrCreate = Nat.create(17);
        int[] iArrCreate2 = Nat.create(17);
        int[] iArrCreate3 = Nat.create(17);
        int[] iArrCreate4 = Nat.create(17);
        boolean zIsOne = secP521R1FieldElement5.isOne();
        if (zIsOne) {
            iArr = secP521R1FieldElement3.f3582x;
            iArr2 = secP521R1FieldElement4.f3582x;
        } else {
            SecP521R1Field.square(secP521R1FieldElement5.f3582x, iArrCreate3);
            SecP521R1Field.multiply(iArrCreate3, secP521R1FieldElement3.f3582x, iArrCreate2);
            SecP521R1Field.multiply(iArrCreate3, secP521R1FieldElement5.f3582x, iArrCreate3);
            SecP521R1Field.multiply(iArrCreate3, secP521R1FieldElement4.f3582x, iArrCreate3);
            iArr = iArrCreate2;
            iArr2 = iArrCreate3;
        }
        boolean zIsOne2 = secP521R1FieldElement6.isOne();
        if (zIsOne2) {
            iArr3 = secP521R1FieldElement.f3582x;
            iArr4 = secP521R1FieldElement2.f3582x;
        } else {
            SecP521R1Field.square(secP521R1FieldElement6.f3582x, iArrCreate4);
            SecP521R1Field.multiply(iArrCreate4, secP521R1FieldElement.f3582x, iArrCreate);
            SecP521R1Field.multiply(iArrCreate4, secP521R1FieldElement6.f3582x, iArrCreate4);
            SecP521R1Field.multiply(iArrCreate4, secP521R1FieldElement2.f3582x, iArrCreate4);
            iArr3 = iArrCreate;
            iArr4 = iArrCreate4;
        }
        int[] iArrCreate5 = Nat.create(17);
        SecP521R1Field.subtract(iArr3, iArr, iArrCreate5);
        SecP521R1Field.subtract(iArr4, iArr2, iArrCreate2);
        if (Nat.isZero(17, iArrCreate5)) {
            return Nat.isZero(17, iArrCreate2) ? twice() : curve.getInfinity();
        }
        SecP521R1Field.square(iArrCreate5, iArrCreate3);
        int[] iArrCreate6 = Nat.create(17);
        SecP521R1Field.multiply(iArrCreate3, iArrCreate5, iArrCreate6);
        SecP521R1Field.multiply(iArrCreate3, iArr3, iArrCreate3);
        SecP521R1Field.multiply(iArr4, iArrCreate6, iArrCreate);
        SecP521R1FieldElement secP521R1FieldElement7 = new SecP521R1FieldElement(iArrCreate4);
        SecP521R1Field.square(iArrCreate2, secP521R1FieldElement7.f3582x);
        SecP521R1Field.add(secP521R1FieldElement7.f3582x, iArrCreate6, secP521R1FieldElement7.f3582x);
        SecP521R1Field.subtract(secP521R1FieldElement7.f3582x, iArrCreate3, secP521R1FieldElement7.f3582x);
        SecP521R1Field.subtract(secP521R1FieldElement7.f3582x, iArrCreate3, secP521R1FieldElement7.f3582x);
        SecP521R1FieldElement secP521R1FieldElement8 = new SecP521R1FieldElement(iArrCreate6);
        SecP521R1Field.subtract(iArrCreate3, secP521R1FieldElement7.f3582x, secP521R1FieldElement8.f3582x);
        SecP521R1Field.multiply(secP521R1FieldElement8.f3582x, iArrCreate2, iArrCreate2);
        SecP521R1Field.subtract(iArrCreate2, iArrCreate, secP521R1FieldElement8.f3582x);
        SecP521R1FieldElement secP521R1FieldElement9 = new SecP521R1FieldElement(iArrCreate5);
        if (!zIsOne) {
            SecP521R1Field.multiply(secP521R1FieldElement9.f3582x, secP521R1FieldElement5.f3582x, secP521R1FieldElement9.f3582x);
        }
        if (!zIsOne2) {
            SecP521R1Field.multiply(secP521R1FieldElement9.f3582x, secP521R1FieldElement6.f3582x, secP521R1FieldElement9.f3582x);
        }
        return new SecP521R1Point(curve, secP521R1FieldElement7, secP521R1FieldElement8, new ECFieldElement[]{secP521R1FieldElement9});
    }

    @Override
    protected ECPoint detach() {
        return new SecP521R1Point(null, getAffineXCoord(), getAffineYCoord());
    }

    protected ECFieldElement doubleProductFromSquares(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2, ECFieldElement eCFieldElement3, ECFieldElement eCFieldElement4) {
        return eCFieldElement.add(eCFieldElement2).square().subtract(eCFieldElement3).subtract(eCFieldElement4);
    }

    protected ECFieldElement eight(ECFieldElement eCFieldElement) {
        return four(two(eCFieldElement));
    }

    protected ECFieldElement four(ECFieldElement eCFieldElement) {
        return two(two(eCFieldElement));
    }

    @Override
    public ECPoint negate() {
        return isInfinity() ? this : new SecP521R1Point(this.curve, this.f3508x, this.f3509y.negate(), this.f3510zs);
    }

    protected ECFieldElement three(ECFieldElement eCFieldElement) {
        return two(eCFieldElement).add(eCFieldElement);
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
        SecP521R1FieldElement secP521R1FieldElement = (SecP521R1FieldElement) this.f3509y;
        if (secP521R1FieldElement.isZero()) {
            return curve.getInfinity();
        }
        SecP521R1FieldElement secP521R1FieldElement2 = (SecP521R1FieldElement) this.f3508x;
        SecP521R1FieldElement secP521R1FieldElement3 = (SecP521R1FieldElement) this.f3510zs[0];
        int[] iArrCreate = Nat.create(17);
        int[] iArrCreate2 = Nat.create(17);
        int[] iArrCreate3 = Nat.create(17);
        SecP521R1Field.square(secP521R1FieldElement.f3582x, iArrCreate3);
        int[] iArrCreate4 = Nat.create(17);
        SecP521R1Field.square(iArrCreate3, iArrCreate4);
        boolean zIsOne = secP521R1FieldElement3.isOne();
        int[] iArr = secP521R1FieldElement3.f3582x;
        if (!zIsOne) {
            SecP521R1Field.square(secP521R1FieldElement3.f3582x, iArrCreate2);
            iArr = iArrCreate2;
        }
        SecP521R1Field.subtract(secP521R1FieldElement2.f3582x, iArr, iArrCreate);
        SecP521R1Field.add(secP521R1FieldElement2.f3582x, iArr, iArrCreate2);
        SecP521R1Field.multiply(iArrCreate2, iArrCreate, iArrCreate2);
        Nat.addBothTo(17, iArrCreate2, iArrCreate2, iArrCreate2);
        SecP521R1Field.reduce23(iArrCreate2);
        SecP521R1Field.multiply(iArrCreate3, secP521R1FieldElement2.f3582x, iArrCreate3);
        Nat.shiftUpBits(17, iArrCreate3, 2, 0);
        SecP521R1Field.reduce23(iArrCreate3);
        Nat.shiftUpBits(17, iArrCreate4, 3, 0, iArrCreate);
        SecP521R1Field.reduce23(iArrCreate);
        SecP521R1FieldElement secP521R1FieldElement4 = new SecP521R1FieldElement(iArrCreate4);
        SecP521R1Field.square(iArrCreate2, secP521R1FieldElement4.f3582x);
        SecP521R1Field.subtract(secP521R1FieldElement4.f3582x, iArrCreate3, secP521R1FieldElement4.f3582x);
        SecP521R1Field.subtract(secP521R1FieldElement4.f3582x, iArrCreate3, secP521R1FieldElement4.f3582x);
        SecP521R1FieldElement secP521R1FieldElement5 = new SecP521R1FieldElement(iArrCreate3);
        SecP521R1Field.subtract(iArrCreate3, secP521R1FieldElement4.f3582x, secP521R1FieldElement5.f3582x);
        SecP521R1Field.multiply(secP521R1FieldElement5.f3582x, iArrCreate2, secP521R1FieldElement5.f3582x);
        SecP521R1Field.subtract(secP521R1FieldElement5.f3582x, iArrCreate, secP521R1FieldElement5.f3582x);
        SecP521R1FieldElement secP521R1FieldElement6 = new SecP521R1FieldElement(iArrCreate2);
        SecP521R1Field.twice(secP521R1FieldElement.f3582x, secP521R1FieldElement6.f3582x);
        if (!zIsOne) {
            SecP521R1Field.multiply(secP521R1FieldElement6.f3582x, secP521R1FieldElement3.f3582x, secP521R1FieldElement6.f3582x);
        }
        return new SecP521R1Point(curve, secP521R1FieldElement4, secP521R1FieldElement5, new ECFieldElement[]{secP521R1FieldElement6});
    }

    @Override
    public ECPoint twicePlus(ECPoint eCPoint) {
        return this == eCPoint ? threeTimes() : isInfinity() ? eCPoint : eCPoint.isInfinity() ? twice() : this.f3509y.isZero() ? eCPoint : twice().add(eCPoint);
    }

    protected ECFieldElement two(ECFieldElement eCFieldElement) {
        return eCFieldElement.add(eCFieldElement);
    }
}
