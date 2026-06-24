package org.bouncycastle.math.p058ec.custom.sec;

import java.math.BigInteger;
import org.bouncycastle.math.p058ec.ECFieldElement;
import org.bouncycastle.math.raw.Nat;
import org.bouncycastle.math.raw.Nat320;
import org.bouncycastle.util.Arrays;

public class SecT283FieldElement extends ECFieldElement.AbstractF2m {

    protected long[] f3589x;

    public SecT283FieldElement() {
        this.f3589x = Nat320.create64();
    }

    public SecT283FieldElement(BigInteger bigInteger) {
        if (bigInteger == null || bigInteger.signum() < 0 || bigInteger.bitLength() > 283) {
            throw new IllegalArgumentException("x value invalid for SecT283FieldElement");
        }
        this.f3589x = SecT283Field.fromBigInteger(bigInteger);
    }

    protected SecT283FieldElement(long[] jArr) {
        this.f3589x = jArr;
    }

    @Override
    public ECFieldElement add(ECFieldElement eCFieldElement) {
        long[] jArrCreate64 = Nat320.create64();
        SecT283Field.add(this.f3589x, ((SecT283FieldElement) eCFieldElement).f3589x, jArrCreate64);
        return new SecT283FieldElement(jArrCreate64);
    }

    @Override
    public ECFieldElement addOne() {
        long[] jArrCreate64 = Nat320.create64();
        SecT283Field.addOne(this.f3589x, jArrCreate64);
        return new SecT283FieldElement(jArrCreate64);
    }

    @Override
    public ECFieldElement divide(ECFieldElement eCFieldElement) {
        return multiply(eCFieldElement.invert());
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof SecT283FieldElement) {
            return Nat320.eq64(this.f3589x, ((SecT283FieldElement) obj).f3589x);
        }
        return false;
    }

    @Override
    public String getFieldName() {
        return "SecT283Field";
    }

    @Override
    public int getFieldSize() {
        return 283;
    }

    public int getK1() {
        return 5;
    }

    public int getK2() {
        return 7;
    }

    public int getK3() {
        return 12;
    }

    public int getM() {
        return 283;
    }

    public int getRepresentation() {
        return 3;
    }

    @Override
    public ECFieldElement halfTrace() {
        long[] jArrCreate64 = Nat320.create64();
        SecT283Field.halfTrace(this.f3589x, jArrCreate64);
        return new SecT283FieldElement(jArrCreate64);
    }

    @Override
    public boolean hasFastTrace() {
        return true;
    }

    public int hashCode() {
        return Arrays.hashCode(this.f3589x, 0, 5) ^ 2831275;
    }

    @Override
    public ECFieldElement invert() {
        long[] jArrCreate64 = Nat320.create64();
        SecT283Field.invert(this.f3589x, jArrCreate64);
        return new SecT283FieldElement(jArrCreate64);
    }

    @Override
    public boolean isOne() {
        return Nat320.isOne64(this.f3589x);
    }

    @Override
    public boolean isZero() {
        return Nat320.isZero64(this.f3589x);
    }

    @Override
    public ECFieldElement multiply(ECFieldElement eCFieldElement) {
        long[] jArrCreate64 = Nat320.create64();
        SecT283Field.multiply(this.f3589x, ((SecT283FieldElement) eCFieldElement).f3589x, jArrCreate64);
        return new SecT283FieldElement(jArrCreate64);
    }

    @Override
    public ECFieldElement multiplyMinusProduct(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2, ECFieldElement eCFieldElement3) {
        return multiplyPlusProduct(eCFieldElement, eCFieldElement2, eCFieldElement3);
    }

    @Override
    public ECFieldElement multiplyPlusProduct(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2, ECFieldElement eCFieldElement3) {
        long[] jArr = this.f3589x;
        long[] jArr2 = ((SecT283FieldElement) eCFieldElement).f3589x;
        long[] jArr3 = ((SecT283FieldElement) eCFieldElement2).f3589x;
        long[] jArr4 = ((SecT283FieldElement) eCFieldElement3).f3589x;
        long[] jArrCreate64 = Nat.create64(9);
        SecT283Field.multiplyAddToExt(jArr, jArr2, jArrCreate64);
        SecT283Field.multiplyAddToExt(jArr3, jArr4, jArrCreate64);
        long[] jArrCreate642 = Nat320.create64();
        SecT283Field.reduce(jArrCreate64, jArrCreate642);
        return new SecT283FieldElement(jArrCreate642);
    }

    @Override
    public ECFieldElement negate() {
        return this;
    }

    @Override
    public ECFieldElement sqrt() {
        long[] jArrCreate64 = Nat320.create64();
        SecT283Field.sqrt(this.f3589x, jArrCreate64);
        return new SecT283FieldElement(jArrCreate64);
    }

    @Override
    public ECFieldElement square() {
        long[] jArrCreate64 = Nat320.create64();
        SecT283Field.square(this.f3589x, jArrCreate64);
        return new SecT283FieldElement(jArrCreate64);
    }

    @Override
    public ECFieldElement squareMinusProduct(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2) {
        return squarePlusProduct(eCFieldElement, eCFieldElement2);
    }

    @Override
    public ECFieldElement squarePlusProduct(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2) {
        long[] jArr = this.f3589x;
        long[] jArr2 = ((SecT283FieldElement) eCFieldElement).f3589x;
        long[] jArr3 = ((SecT283FieldElement) eCFieldElement2).f3589x;
        long[] jArrCreate64 = Nat.create64(9);
        SecT283Field.squareAddToExt(jArr, jArrCreate64);
        SecT283Field.multiplyAddToExt(jArr2, jArr3, jArrCreate64);
        long[] jArrCreate642 = Nat320.create64();
        SecT283Field.reduce(jArrCreate64, jArrCreate642);
        return new SecT283FieldElement(jArrCreate642);
    }

    @Override
    public ECFieldElement squarePow(int i) {
        if (i < 1) {
            return this;
        }
        long[] jArrCreate64 = Nat320.create64();
        SecT283Field.squareN(this.f3589x, i, jArrCreate64);
        return new SecT283FieldElement(jArrCreate64);
    }

    @Override
    public ECFieldElement subtract(ECFieldElement eCFieldElement) {
        return add(eCFieldElement);
    }

    @Override
    public boolean testBitZero() {
        return (this.f3589x[0] & 1) != 0;
    }

    @Override
    public BigInteger toBigInteger() {
        return Nat320.toBigInteger64(this.f3589x);
    }

    @Override
    public int trace() {
        return SecT283Field.trace(this.f3589x);
    }
}
