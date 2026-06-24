package org.bouncycastle.math.p058ec.custom.sec;

import java.math.BigInteger;
import org.bouncycastle.math.p058ec.ECFieldElement;
import org.bouncycastle.math.raw.Nat;
import org.bouncycastle.math.raw.Nat192;
import org.bouncycastle.util.Arrays;

public class SecT131FieldElement extends ECFieldElement.AbstractF2m {

    protected long[] f3584x;

    public SecT131FieldElement() {
        this.f3584x = Nat192.create64();
    }

    public SecT131FieldElement(BigInteger bigInteger) {
        if (bigInteger == null || bigInteger.signum() < 0 || bigInteger.bitLength() > 131) {
            throw new IllegalArgumentException("x value invalid for SecT131FieldElement");
        }
        this.f3584x = SecT131Field.fromBigInteger(bigInteger);
    }

    protected SecT131FieldElement(long[] jArr) {
        this.f3584x = jArr;
    }

    @Override
    public ECFieldElement add(ECFieldElement eCFieldElement) {
        long[] jArrCreate64 = Nat192.create64();
        SecT131Field.add(this.f3584x, ((SecT131FieldElement) eCFieldElement).f3584x, jArrCreate64);
        return new SecT131FieldElement(jArrCreate64);
    }

    @Override
    public ECFieldElement addOne() {
        long[] jArrCreate64 = Nat192.create64();
        SecT131Field.addOne(this.f3584x, jArrCreate64);
        return new SecT131FieldElement(jArrCreate64);
    }

    @Override
    public ECFieldElement divide(ECFieldElement eCFieldElement) {
        return multiply(eCFieldElement.invert());
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof SecT131FieldElement) {
            return Nat192.eq64(this.f3584x, ((SecT131FieldElement) obj).f3584x);
        }
        return false;
    }

    @Override
    public String getFieldName() {
        return "SecT131Field";
    }

    @Override
    public int getFieldSize() {
        return 131;
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

    public int getRepresentation() {
        return 3;
    }

    @Override
    public ECFieldElement halfTrace() {
        long[] jArrCreate64 = Nat192.create64();
        SecT131Field.halfTrace(this.f3584x, jArrCreate64);
        return new SecT131FieldElement(jArrCreate64);
    }

    @Override
    public boolean hasFastTrace() {
        return true;
    }

    public int hashCode() {
        return Arrays.hashCode(this.f3584x, 0, 3) ^ 131832;
    }

    @Override
    public ECFieldElement invert() {
        long[] jArrCreate64 = Nat192.create64();
        SecT131Field.invert(this.f3584x, jArrCreate64);
        return new SecT131FieldElement(jArrCreate64);
    }

    @Override
    public boolean isOne() {
        return Nat192.isOne64(this.f3584x);
    }

    @Override
    public boolean isZero() {
        return Nat192.isZero64(this.f3584x);
    }

    @Override
    public ECFieldElement multiply(ECFieldElement eCFieldElement) {
        long[] jArrCreate64 = Nat192.create64();
        SecT131Field.multiply(this.f3584x, ((SecT131FieldElement) eCFieldElement).f3584x, jArrCreate64);
        return new SecT131FieldElement(jArrCreate64);
    }

    @Override
    public ECFieldElement multiplyMinusProduct(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2, ECFieldElement eCFieldElement3) {
        return multiplyPlusProduct(eCFieldElement, eCFieldElement2, eCFieldElement3);
    }

    @Override
    public ECFieldElement multiplyPlusProduct(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2, ECFieldElement eCFieldElement3) {
        long[] jArr = this.f3584x;
        long[] jArr2 = ((SecT131FieldElement) eCFieldElement).f3584x;
        long[] jArr3 = ((SecT131FieldElement) eCFieldElement2).f3584x;
        long[] jArr4 = ((SecT131FieldElement) eCFieldElement3).f3584x;
        long[] jArrCreate64 = Nat.create64(5);
        SecT131Field.multiplyAddToExt(jArr, jArr2, jArrCreate64);
        SecT131Field.multiplyAddToExt(jArr3, jArr4, jArrCreate64);
        long[] jArrCreate642 = Nat192.create64();
        SecT131Field.reduce(jArrCreate64, jArrCreate642);
        return new SecT131FieldElement(jArrCreate642);
    }

    @Override
    public ECFieldElement negate() {
        return this;
    }

    @Override
    public ECFieldElement sqrt() {
        long[] jArrCreate64 = Nat192.create64();
        SecT131Field.sqrt(this.f3584x, jArrCreate64);
        return new SecT131FieldElement(jArrCreate64);
    }

    @Override
    public ECFieldElement square() {
        long[] jArrCreate64 = Nat192.create64();
        SecT131Field.square(this.f3584x, jArrCreate64);
        return new SecT131FieldElement(jArrCreate64);
    }

    @Override
    public ECFieldElement squareMinusProduct(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2) {
        return squarePlusProduct(eCFieldElement, eCFieldElement2);
    }

    @Override
    public ECFieldElement squarePlusProduct(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2) {
        long[] jArr = this.f3584x;
        long[] jArr2 = ((SecT131FieldElement) eCFieldElement).f3584x;
        long[] jArr3 = ((SecT131FieldElement) eCFieldElement2).f3584x;
        long[] jArrCreate64 = Nat.create64(5);
        SecT131Field.squareAddToExt(jArr, jArrCreate64);
        SecT131Field.multiplyAddToExt(jArr2, jArr3, jArrCreate64);
        long[] jArrCreate642 = Nat192.create64();
        SecT131Field.reduce(jArrCreate64, jArrCreate642);
        return new SecT131FieldElement(jArrCreate642);
    }

    @Override
    public ECFieldElement squarePow(int i) {
        if (i < 1) {
            return this;
        }
        long[] jArrCreate64 = Nat192.create64();
        SecT131Field.squareN(this.f3584x, i, jArrCreate64);
        return new SecT131FieldElement(jArrCreate64);
    }

    @Override
    public ECFieldElement subtract(ECFieldElement eCFieldElement) {
        return add(eCFieldElement);
    }

    @Override
    public boolean testBitZero() {
        return (this.f3584x[0] & 1) != 0;
    }

    @Override
    public BigInteger toBigInteger() {
        return Nat192.toBigInteger64(this.f3584x);
    }

    @Override
    public int trace() {
        return SecT131Field.trace(this.f3584x);
    }
}
