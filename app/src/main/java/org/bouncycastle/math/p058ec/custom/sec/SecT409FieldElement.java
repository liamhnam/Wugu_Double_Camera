package org.bouncycastle.math.p058ec.custom.sec;

import java.math.BigInteger;
import org.apache.http.HttpStatus;
import org.bouncycastle.math.p058ec.ECFieldElement;
import org.bouncycastle.math.raw.Nat;
import org.bouncycastle.math.raw.Nat448;
import org.bouncycastle.util.Arrays;

public class SecT409FieldElement extends ECFieldElement.AbstractF2m {

    protected long[] f3590x;

    public SecT409FieldElement() {
        this.f3590x = Nat448.create64();
    }

    public SecT409FieldElement(BigInteger bigInteger) {
        if (bigInteger == null || bigInteger.signum() < 0 || bigInteger.bitLength() > 409) {
            throw new IllegalArgumentException("x value invalid for SecT409FieldElement");
        }
        this.f3590x = SecT409Field.fromBigInteger(bigInteger);
    }

    protected SecT409FieldElement(long[] jArr) {
        this.f3590x = jArr;
    }

    @Override
    public ECFieldElement add(ECFieldElement eCFieldElement) {
        long[] jArrCreate64 = Nat448.create64();
        SecT409Field.add(this.f3590x, ((SecT409FieldElement) eCFieldElement).f3590x, jArrCreate64);
        return new SecT409FieldElement(jArrCreate64);
    }

    @Override
    public ECFieldElement addOne() {
        long[] jArrCreate64 = Nat448.create64();
        SecT409Field.addOne(this.f3590x, jArrCreate64);
        return new SecT409FieldElement(jArrCreate64);
    }

    @Override
    public ECFieldElement divide(ECFieldElement eCFieldElement) {
        return multiply(eCFieldElement.invert());
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof SecT409FieldElement) {
            return Nat448.eq64(this.f3590x, ((SecT409FieldElement) obj).f3590x);
        }
        return false;
    }

    @Override
    public String getFieldName() {
        return "SecT409Field";
    }

    @Override
    public int getFieldSize() {
        return HttpStatus.SC_CONFLICT;
    }

    public int getK1() {
        return 87;
    }

    public int getK2() {
        return 0;
    }

    public int getK3() {
        return 0;
    }

    public int getM() {
        return HttpStatus.SC_CONFLICT;
    }

    public int getRepresentation() {
        return 2;
    }

    @Override
    public ECFieldElement halfTrace() {
        long[] jArrCreate64 = Nat448.create64();
        SecT409Field.halfTrace(this.f3590x, jArrCreate64);
        return new SecT409FieldElement(jArrCreate64);
    }

    @Override
    public boolean hasFastTrace() {
        return true;
    }

    public int hashCode() {
        return Arrays.hashCode(this.f3590x, 0, 7) ^ 4090087;
    }

    @Override
    public ECFieldElement invert() {
        long[] jArrCreate64 = Nat448.create64();
        SecT409Field.invert(this.f3590x, jArrCreate64);
        return new SecT409FieldElement(jArrCreate64);
    }

    @Override
    public boolean isOne() {
        return Nat448.isOne64(this.f3590x);
    }

    @Override
    public boolean isZero() {
        return Nat448.isZero64(this.f3590x);
    }

    @Override
    public ECFieldElement multiply(ECFieldElement eCFieldElement) {
        long[] jArrCreate64 = Nat448.create64();
        SecT409Field.multiply(this.f3590x, ((SecT409FieldElement) eCFieldElement).f3590x, jArrCreate64);
        return new SecT409FieldElement(jArrCreate64);
    }

    @Override
    public ECFieldElement multiplyMinusProduct(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2, ECFieldElement eCFieldElement3) {
        return multiplyPlusProduct(eCFieldElement, eCFieldElement2, eCFieldElement3);
    }

    @Override
    public ECFieldElement multiplyPlusProduct(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2, ECFieldElement eCFieldElement3) {
        long[] jArr = this.f3590x;
        long[] jArr2 = ((SecT409FieldElement) eCFieldElement).f3590x;
        long[] jArr3 = ((SecT409FieldElement) eCFieldElement2).f3590x;
        long[] jArr4 = ((SecT409FieldElement) eCFieldElement3).f3590x;
        long[] jArrCreate64 = Nat.create64(13);
        SecT409Field.multiplyAddToExt(jArr, jArr2, jArrCreate64);
        SecT409Field.multiplyAddToExt(jArr3, jArr4, jArrCreate64);
        long[] jArrCreate642 = Nat448.create64();
        SecT409Field.reduce(jArrCreate64, jArrCreate642);
        return new SecT409FieldElement(jArrCreate642);
    }

    @Override
    public ECFieldElement negate() {
        return this;
    }

    @Override
    public ECFieldElement sqrt() {
        long[] jArrCreate64 = Nat448.create64();
        SecT409Field.sqrt(this.f3590x, jArrCreate64);
        return new SecT409FieldElement(jArrCreate64);
    }

    @Override
    public ECFieldElement square() {
        long[] jArrCreate64 = Nat448.create64();
        SecT409Field.square(this.f3590x, jArrCreate64);
        return new SecT409FieldElement(jArrCreate64);
    }

    @Override
    public ECFieldElement squareMinusProduct(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2) {
        return squarePlusProduct(eCFieldElement, eCFieldElement2);
    }

    @Override
    public ECFieldElement squarePlusProduct(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2) {
        long[] jArr = this.f3590x;
        long[] jArr2 = ((SecT409FieldElement) eCFieldElement).f3590x;
        long[] jArr3 = ((SecT409FieldElement) eCFieldElement2).f3590x;
        long[] jArrCreate64 = Nat.create64(13);
        SecT409Field.squareAddToExt(jArr, jArrCreate64);
        SecT409Field.multiplyAddToExt(jArr2, jArr3, jArrCreate64);
        long[] jArrCreate642 = Nat448.create64();
        SecT409Field.reduce(jArrCreate64, jArrCreate642);
        return new SecT409FieldElement(jArrCreate642);
    }

    @Override
    public ECFieldElement squarePow(int i) {
        if (i < 1) {
            return this;
        }
        long[] jArrCreate64 = Nat448.create64();
        SecT409Field.squareN(this.f3590x, i, jArrCreate64);
        return new SecT409FieldElement(jArrCreate64);
    }

    @Override
    public ECFieldElement subtract(ECFieldElement eCFieldElement) {
        return add(eCFieldElement);
    }

    @Override
    public boolean testBitZero() {
        return (this.f3590x[0] & 1) != 0;
    }

    @Override
    public BigInteger toBigInteger() {
        return Nat448.toBigInteger64(this.f3590x);
    }

    @Override
    public int trace() {
        return SecT409Field.trace(this.f3590x);
    }
}
