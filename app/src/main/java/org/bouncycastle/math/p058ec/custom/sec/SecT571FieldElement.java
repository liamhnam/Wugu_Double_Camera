package org.bouncycastle.math.p058ec.custom.sec;

import java.math.BigInteger;
import org.bouncycastle.math.p058ec.ECFieldElement;
import org.bouncycastle.math.raw.Nat576;
import org.bouncycastle.util.Arrays;

public class SecT571FieldElement extends ECFieldElement.AbstractF2m {

    protected long[] f3591x;

    public SecT571FieldElement() {
        this.f3591x = Nat576.create64();
    }

    public SecT571FieldElement(BigInteger bigInteger) {
        if (bigInteger == null || bigInteger.signum() < 0 || bigInteger.bitLength() > 571) {
            throw new IllegalArgumentException("x value invalid for SecT571FieldElement");
        }
        this.f3591x = SecT571Field.fromBigInteger(bigInteger);
    }

    protected SecT571FieldElement(long[] jArr) {
        this.f3591x = jArr;
    }

    @Override
    public ECFieldElement add(ECFieldElement eCFieldElement) {
        long[] jArrCreate64 = Nat576.create64();
        SecT571Field.add(this.f3591x, ((SecT571FieldElement) eCFieldElement).f3591x, jArrCreate64);
        return new SecT571FieldElement(jArrCreate64);
    }

    @Override
    public ECFieldElement addOne() {
        long[] jArrCreate64 = Nat576.create64();
        SecT571Field.addOne(this.f3591x, jArrCreate64);
        return new SecT571FieldElement(jArrCreate64);
    }

    @Override
    public ECFieldElement divide(ECFieldElement eCFieldElement) {
        return multiply(eCFieldElement.invert());
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof SecT571FieldElement) {
            return Nat576.eq64(this.f3591x, ((SecT571FieldElement) obj).f3591x);
        }
        return false;
    }

    @Override
    public String getFieldName() {
        return "SecT571Field";
    }

    @Override
    public int getFieldSize() {
        return 571;
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

    public int getRepresentation() {
        return 3;
    }

    @Override
    public ECFieldElement halfTrace() {
        long[] jArrCreate64 = Nat576.create64();
        SecT571Field.halfTrace(this.f3591x, jArrCreate64);
        return new SecT571FieldElement(jArrCreate64);
    }

    @Override
    public boolean hasFastTrace() {
        return true;
    }

    public int hashCode() {
        return Arrays.hashCode(this.f3591x, 0, 9) ^ 5711052;
    }

    @Override
    public ECFieldElement invert() {
        long[] jArrCreate64 = Nat576.create64();
        SecT571Field.invert(this.f3591x, jArrCreate64);
        return new SecT571FieldElement(jArrCreate64);
    }

    @Override
    public boolean isOne() {
        return Nat576.isOne64(this.f3591x);
    }

    @Override
    public boolean isZero() {
        return Nat576.isZero64(this.f3591x);
    }

    @Override
    public ECFieldElement multiply(ECFieldElement eCFieldElement) {
        long[] jArrCreate64 = Nat576.create64();
        SecT571Field.multiply(this.f3591x, ((SecT571FieldElement) eCFieldElement).f3591x, jArrCreate64);
        return new SecT571FieldElement(jArrCreate64);
    }

    @Override
    public ECFieldElement multiplyMinusProduct(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2, ECFieldElement eCFieldElement3) {
        return multiplyPlusProduct(eCFieldElement, eCFieldElement2, eCFieldElement3);
    }

    @Override
    public ECFieldElement multiplyPlusProduct(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2, ECFieldElement eCFieldElement3) {
        long[] jArr = this.f3591x;
        long[] jArr2 = ((SecT571FieldElement) eCFieldElement).f3591x;
        long[] jArr3 = ((SecT571FieldElement) eCFieldElement2).f3591x;
        long[] jArr4 = ((SecT571FieldElement) eCFieldElement3).f3591x;
        long[] jArrCreateExt64 = Nat576.createExt64();
        SecT571Field.multiplyAddToExt(jArr, jArr2, jArrCreateExt64);
        SecT571Field.multiplyAddToExt(jArr3, jArr4, jArrCreateExt64);
        long[] jArrCreate64 = Nat576.create64();
        SecT571Field.reduce(jArrCreateExt64, jArrCreate64);
        return new SecT571FieldElement(jArrCreate64);
    }

    @Override
    public ECFieldElement negate() {
        return this;
    }

    @Override
    public ECFieldElement sqrt() {
        long[] jArrCreate64 = Nat576.create64();
        SecT571Field.sqrt(this.f3591x, jArrCreate64);
        return new SecT571FieldElement(jArrCreate64);
    }

    @Override
    public ECFieldElement square() {
        long[] jArrCreate64 = Nat576.create64();
        SecT571Field.square(this.f3591x, jArrCreate64);
        return new SecT571FieldElement(jArrCreate64);
    }

    @Override
    public ECFieldElement squareMinusProduct(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2) {
        return squarePlusProduct(eCFieldElement, eCFieldElement2);
    }

    @Override
    public ECFieldElement squarePlusProduct(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2) {
        long[] jArr = this.f3591x;
        long[] jArr2 = ((SecT571FieldElement) eCFieldElement).f3591x;
        long[] jArr3 = ((SecT571FieldElement) eCFieldElement2).f3591x;
        long[] jArrCreateExt64 = Nat576.createExt64();
        SecT571Field.squareAddToExt(jArr, jArrCreateExt64);
        SecT571Field.multiplyAddToExt(jArr2, jArr3, jArrCreateExt64);
        long[] jArrCreate64 = Nat576.create64();
        SecT571Field.reduce(jArrCreateExt64, jArrCreate64);
        return new SecT571FieldElement(jArrCreate64);
    }

    @Override
    public ECFieldElement squarePow(int i) {
        if (i < 1) {
            return this;
        }
        long[] jArrCreate64 = Nat576.create64();
        SecT571Field.squareN(this.f3591x, i, jArrCreate64);
        return new SecT571FieldElement(jArrCreate64);
    }

    @Override
    public ECFieldElement subtract(ECFieldElement eCFieldElement) {
        return add(eCFieldElement);
    }

    @Override
    public boolean testBitZero() {
        return (this.f3591x[0] & 1) != 0;
    }

    @Override
    public BigInteger toBigInteger() {
        return Nat576.toBigInteger64(this.f3591x);
    }

    @Override
    public int trace() {
        return SecT571Field.trace(this.f3591x);
    }
}
