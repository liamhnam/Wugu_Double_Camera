package org.bouncycastle.math.p058ec.custom.sec;

import java.math.BigInteger;
import org.bouncycastle.math.p058ec.ECFieldElement;
import org.bouncycastle.math.raw.Nat256;
import org.bouncycastle.util.Arrays;

public class SecT233FieldElement extends ECFieldElement.AbstractF2m {

    protected long[] f3587x;

    public SecT233FieldElement() {
        this.f3587x = Nat256.create64();
    }

    public SecT233FieldElement(BigInteger bigInteger) {
        if (bigInteger == null || bigInteger.signum() < 0 || bigInteger.bitLength() > 233) {
            throw new IllegalArgumentException("x value invalid for SecT233FieldElement");
        }
        this.f3587x = SecT233Field.fromBigInteger(bigInteger);
    }

    protected SecT233FieldElement(long[] jArr) {
        this.f3587x = jArr;
    }

    @Override
    public ECFieldElement add(ECFieldElement eCFieldElement) {
        long[] jArrCreate64 = Nat256.create64();
        SecT233Field.add(this.f3587x, ((SecT233FieldElement) eCFieldElement).f3587x, jArrCreate64);
        return new SecT233FieldElement(jArrCreate64);
    }

    @Override
    public ECFieldElement addOne() {
        long[] jArrCreate64 = Nat256.create64();
        SecT233Field.addOne(this.f3587x, jArrCreate64);
        return new SecT233FieldElement(jArrCreate64);
    }

    @Override
    public ECFieldElement divide(ECFieldElement eCFieldElement) {
        return multiply(eCFieldElement.invert());
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof SecT233FieldElement) {
            return Nat256.eq64(this.f3587x, ((SecT233FieldElement) obj).f3587x);
        }
        return false;
    }

    @Override
    public String getFieldName() {
        return "SecT233Field";
    }

    @Override
    public int getFieldSize() {
        return 233;
    }

    public int getK1() {
        return 74;
    }

    public int getK2() {
        return 0;
    }

    public int getK3() {
        return 0;
    }

    public int getM() {
        return 233;
    }

    public int getRepresentation() {
        return 2;
    }

    @Override
    public ECFieldElement halfTrace() {
        long[] jArrCreate64 = Nat256.create64();
        SecT233Field.halfTrace(this.f3587x, jArrCreate64);
        return new SecT233FieldElement(jArrCreate64);
    }

    @Override
    public boolean hasFastTrace() {
        return true;
    }

    public int hashCode() {
        return Arrays.hashCode(this.f3587x, 0, 4) ^ 2330074;
    }

    @Override
    public ECFieldElement invert() {
        long[] jArrCreate64 = Nat256.create64();
        SecT233Field.invert(this.f3587x, jArrCreate64);
        return new SecT233FieldElement(jArrCreate64);
    }

    @Override
    public boolean isOne() {
        return Nat256.isOne64(this.f3587x);
    }

    @Override
    public boolean isZero() {
        return Nat256.isZero64(this.f3587x);
    }

    @Override
    public ECFieldElement multiply(ECFieldElement eCFieldElement) {
        long[] jArrCreate64 = Nat256.create64();
        SecT233Field.multiply(this.f3587x, ((SecT233FieldElement) eCFieldElement).f3587x, jArrCreate64);
        return new SecT233FieldElement(jArrCreate64);
    }

    @Override
    public ECFieldElement multiplyMinusProduct(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2, ECFieldElement eCFieldElement3) {
        return multiplyPlusProduct(eCFieldElement, eCFieldElement2, eCFieldElement3);
    }

    @Override
    public ECFieldElement multiplyPlusProduct(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2, ECFieldElement eCFieldElement3) {
        long[] jArr = this.f3587x;
        long[] jArr2 = ((SecT233FieldElement) eCFieldElement).f3587x;
        long[] jArr3 = ((SecT233FieldElement) eCFieldElement2).f3587x;
        long[] jArr4 = ((SecT233FieldElement) eCFieldElement3).f3587x;
        long[] jArrCreateExt64 = Nat256.createExt64();
        SecT233Field.multiplyAddToExt(jArr, jArr2, jArrCreateExt64);
        SecT233Field.multiplyAddToExt(jArr3, jArr4, jArrCreateExt64);
        long[] jArrCreate64 = Nat256.create64();
        SecT233Field.reduce(jArrCreateExt64, jArrCreate64);
        return new SecT233FieldElement(jArrCreate64);
    }

    @Override
    public ECFieldElement negate() {
        return this;
    }

    @Override
    public ECFieldElement sqrt() {
        long[] jArrCreate64 = Nat256.create64();
        SecT233Field.sqrt(this.f3587x, jArrCreate64);
        return new SecT233FieldElement(jArrCreate64);
    }

    @Override
    public ECFieldElement square() {
        long[] jArrCreate64 = Nat256.create64();
        SecT233Field.square(this.f3587x, jArrCreate64);
        return new SecT233FieldElement(jArrCreate64);
    }

    @Override
    public ECFieldElement squareMinusProduct(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2) {
        return squarePlusProduct(eCFieldElement, eCFieldElement2);
    }

    @Override
    public ECFieldElement squarePlusProduct(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2) {
        long[] jArr = this.f3587x;
        long[] jArr2 = ((SecT233FieldElement) eCFieldElement).f3587x;
        long[] jArr3 = ((SecT233FieldElement) eCFieldElement2).f3587x;
        long[] jArrCreateExt64 = Nat256.createExt64();
        SecT233Field.squareAddToExt(jArr, jArrCreateExt64);
        SecT233Field.multiplyAddToExt(jArr2, jArr3, jArrCreateExt64);
        long[] jArrCreate64 = Nat256.create64();
        SecT233Field.reduce(jArrCreateExt64, jArrCreate64);
        return new SecT233FieldElement(jArrCreate64);
    }

    @Override
    public ECFieldElement squarePow(int i) {
        if (i < 1) {
            return this;
        }
        long[] jArrCreate64 = Nat256.create64();
        SecT233Field.squareN(this.f3587x, i, jArrCreate64);
        return new SecT233FieldElement(jArrCreate64);
    }

    @Override
    public ECFieldElement subtract(ECFieldElement eCFieldElement) {
        return add(eCFieldElement);
    }

    @Override
    public boolean testBitZero() {
        return (this.f3587x[0] & 1) != 0;
    }

    @Override
    public BigInteger toBigInteger() {
        return Nat256.toBigInteger64(this.f3587x);
    }

    @Override
    public int trace() {
        return SecT233Field.trace(this.f3587x);
    }
}
