package org.bouncycastle.math.p058ec.custom.sec;

import java.math.BigInteger;
import org.bouncycastle.math.p058ec.ECFieldElement;
import org.bouncycastle.math.raw.Nat256;
import org.bouncycastle.util.Arrays;

public class SecT193FieldElement extends ECFieldElement.AbstractF2m {

    protected long[] f3586x;

    public SecT193FieldElement() {
        this.f3586x = Nat256.create64();
    }

    public SecT193FieldElement(BigInteger bigInteger) {
        if (bigInteger == null || bigInteger.signum() < 0 || bigInteger.bitLength() > 193) {
            throw new IllegalArgumentException("x value invalid for SecT193FieldElement");
        }
        this.f3586x = SecT193Field.fromBigInteger(bigInteger);
    }

    protected SecT193FieldElement(long[] jArr) {
        this.f3586x = jArr;
    }

    @Override
    public ECFieldElement add(ECFieldElement eCFieldElement) {
        long[] jArrCreate64 = Nat256.create64();
        SecT193Field.add(this.f3586x, ((SecT193FieldElement) eCFieldElement).f3586x, jArrCreate64);
        return new SecT193FieldElement(jArrCreate64);
    }

    @Override
    public ECFieldElement addOne() {
        long[] jArrCreate64 = Nat256.create64();
        SecT193Field.addOne(this.f3586x, jArrCreate64);
        return new SecT193FieldElement(jArrCreate64);
    }

    @Override
    public ECFieldElement divide(ECFieldElement eCFieldElement) {
        return multiply(eCFieldElement.invert());
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof SecT193FieldElement) {
            return Nat256.eq64(this.f3586x, ((SecT193FieldElement) obj).f3586x);
        }
        return false;
    }

    @Override
    public String getFieldName() {
        return "SecT193Field";
    }

    @Override
    public int getFieldSize() {
        return 193;
    }

    public int getK1() {
        return 15;
    }

    public int getK2() {
        return 0;
    }

    public int getK3() {
        return 0;
    }

    public int getM() {
        return 193;
    }

    public int getRepresentation() {
        return 2;
    }

    @Override
    public ECFieldElement halfTrace() {
        long[] jArrCreate64 = Nat256.create64();
        SecT193Field.halfTrace(this.f3586x, jArrCreate64);
        return new SecT193FieldElement(jArrCreate64);
    }

    @Override
    public boolean hasFastTrace() {
        return true;
    }

    public int hashCode() {
        return Arrays.hashCode(this.f3586x, 0, 4) ^ 1930015;
    }

    @Override
    public ECFieldElement invert() {
        long[] jArrCreate64 = Nat256.create64();
        SecT193Field.invert(this.f3586x, jArrCreate64);
        return new SecT193FieldElement(jArrCreate64);
    }

    @Override
    public boolean isOne() {
        return Nat256.isOne64(this.f3586x);
    }

    @Override
    public boolean isZero() {
        return Nat256.isZero64(this.f3586x);
    }

    @Override
    public ECFieldElement multiply(ECFieldElement eCFieldElement) {
        long[] jArrCreate64 = Nat256.create64();
        SecT193Field.multiply(this.f3586x, ((SecT193FieldElement) eCFieldElement).f3586x, jArrCreate64);
        return new SecT193FieldElement(jArrCreate64);
    }

    @Override
    public ECFieldElement multiplyMinusProduct(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2, ECFieldElement eCFieldElement3) {
        return multiplyPlusProduct(eCFieldElement, eCFieldElement2, eCFieldElement3);
    }

    @Override
    public ECFieldElement multiplyPlusProduct(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2, ECFieldElement eCFieldElement3) {
        long[] jArr = this.f3586x;
        long[] jArr2 = ((SecT193FieldElement) eCFieldElement).f3586x;
        long[] jArr3 = ((SecT193FieldElement) eCFieldElement2).f3586x;
        long[] jArr4 = ((SecT193FieldElement) eCFieldElement3).f3586x;
        long[] jArrCreateExt64 = Nat256.createExt64();
        SecT193Field.multiplyAddToExt(jArr, jArr2, jArrCreateExt64);
        SecT193Field.multiplyAddToExt(jArr3, jArr4, jArrCreateExt64);
        long[] jArrCreate64 = Nat256.create64();
        SecT193Field.reduce(jArrCreateExt64, jArrCreate64);
        return new SecT193FieldElement(jArrCreate64);
    }

    @Override
    public ECFieldElement negate() {
        return this;
    }

    @Override
    public ECFieldElement sqrt() {
        long[] jArrCreate64 = Nat256.create64();
        SecT193Field.sqrt(this.f3586x, jArrCreate64);
        return new SecT193FieldElement(jArrCreate64);
    }

    @Override
    public ECFieldElement square() {
        long[] jArrCreate64 = Nat256.create64();
        SecT193Field.square(this.f3586x, jArrCreate64);
        return new SecT193FieldElement(jArrCreate64);
    }

    @Override
    public ECFieldElement squareMinusProduct(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2) {
        return squarePlusProduct(eCFieldElement, eCFieldElement2);
    }

    @Override
    public ECFieldElement squarePlusProduct(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2) {
        long[] jArr = this.f3586x;
        long[] jArr2 = ((SecT193FieldElement) eCFieldElement).f3586x;
        long[] jArr3 = ((SecT193FieldElement) eCFieldElement2).f3586x;
        long[] jArrCreateExt64 = Nat256.createExt64();
        SecT193Field.squareAddToExt(jArr, jArrCreateExt64);
        SecT193Field.multiplyAddToExt(jArr2, jArr3, jArrCreateExt64);
        long[] jArrCreate64 = Nat256.create64();
        SecT193Field.reduce(jArrCreateExt64, jArrCreate64);
        return new SecT193FieldElement(jArrCreate64);
    }

    @Override
    public ECFieldElement squarePow(int i) {
        if (i < 1) {
            return this;
        }
        long[] jArrCreate64 = Nat256.create64();
        SecT193Field.squareN(this.f3586x, i, jArrCreate64);
        return new SecT193FieldElement(jArrCreate64);
    }

    @Override
    public ECFieldElement subtract(ECFieldElement eCFieldElement) {
        return add(eCFieldElement);
    }

    @Override
    public boolean testBitZero() {
        return (this.f3586x[0] & 1) != 0;
    }

    @Override
    public BigInteger toBigInteger() {
        return Nat256.toBigInteger64(this.f3586x);
    }

    @Override
    public int trace() {
        return SecT193Field.trace(this.f3586x);
    }
}
