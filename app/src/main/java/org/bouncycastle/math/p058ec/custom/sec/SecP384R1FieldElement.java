package org.bouncycastle.math.p058ec.custom.sec;

import java.math.BigInteger;
import org.bouncycastle.math.p058ec.ECFieldElement;
import org.bouncycastle.math.raw.Nat;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.encoders.Hex;

public class SecP384R1FieldElement extends ECFieldElement.AbstractFp {

    public static final BigInteger f3577Q = new BigInteger(1, Hex.decodeStrict("FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFEFFFFFFFF0000000000000000FFFFFFFF"));

    protected int[] f3578x;

    public SecP384R1FieldElement() {
        this.f3578x = Nat.create(12);
    }

    public SecP384R1FieldElement(BigInteger bigInteger) {
        if (bigInteger == null || bigInteger.signum() < 0 || bigInteger.compareTo(f3577Q) >= 0) {
            throw new IllegalArgumentException("x value invalid for SecP384R1FieldElement");
        }
        this.f3578x = SecP384R1Field.fromBigInteger(bigInteger);
    }

    protected SecP384R1FieldElement(int[] iArr) {
        this.f3578x = iArr;
    }

    @Override
    public ECFieldElement add(ECFieldElement eCFieldElement) {
        int[] iArrCreate = Nat.create(12);
        SecP384R1Field.add(this.f3578x, ((SecP384R1FieldElement) eCFieldElement).f3578x, iArrCreate);
        return new SecP384R1FieldElement(iArrCreate);
    }

    @Override
    public ECFieldElement addOne() {
        int[] iArrCreate = Nat.create(12);
        SecP384R1Field.addOne(this.f3578x, iArrCreate);
        return new SecP384R1FieldElement(iArrCreate);
    }

    @Override
    public ECFieldElement divide(ECFieldElement eCFieldElement) {
        int[] iArrCreate = Nat.create(12);
        SecP384R1Field.inv(((SecP384R1FieldElement) eCFieldElement).f3578x, iArrCreate);
        SecP384R1Field.multiply(iArrCreate, this.f3578x, iArrCreate);
        return new SecP384R1FieldElement(iArrCreate);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof SecP384R1FieldElement) {
            return Nat.m1447eq(12, this.f3578x, ((SecP384R1FieldElement) obj).f3578x);
        }
        return false;
    }

    @Override
    public String getFieldName() {
        return "SecP384R1Field";
    }

    @Override
    public int getFieldSize() {
        return f3577Q.bitLength();
    }

    public int hashCode() {
        return f3577Q.hashCode() ^ Arrays.hashCode(this.f3578x, 0, 12);
    }

    @Override
    public ECFieldElement invert() {
        int[] iArrCreate = Nat.create(12);
        SecP384R1Field.inv(this.f3578x, iArrCreate);
        return new SecP384R1FieldElement(iArrCreate);
    }

    @Override
    public boolean isOne() {
        return Nat.isOne(12, this.f3578x);
    }

    @Override
    public boolean isZero() {
        return Nat.isZero(12, this.f3578x);
    }

    @Override
    public ECFieldElement multiply(ECFieldElement eCFieldElement) {
        int[] iArrCreate = Nat.create(12);
        SecP384R1Field.multiply(this.f3578x, ((SecP384R1FieldElement) eCFieldElement).f3578x, iArrCreate);
        return new SecP384R1FieldElement(iArrCreate);
    }

    @Override
    public ECFieldElement negate() {
        int[] iArrCreate = Nat.create(12);
        SecP384R1Field.negate(this.f3578x, iArrCreate);
        return new SecP384R1FieldElement(iArrCreate);
    }

    @Override
    public ECFieldElement sqrt() {
        int[] iArr = this.f3578x;
        if (Nat.isZero(12, iArr) || Nat.isOne(12, iArr)) {
            return this;
        }
        int[] iArrCreate = Nat.create(12);
        int[] iArrCreate2 = Nat.create(12);
        int[] iArrCreate3 = Nat.create(12);
        int[] iArrCreate4 = Nat.create(12);
        SecP384R1Field.square(iArr, iArrCreate);
        SecP384R1Field.multiply(iArrCreate, iArr, iArrCreate);
        SecP384R1Field.squareN(iArrCreate, 2, iArrCreate2);
        SecP384R1Field.multiply(iArrCreate2, iArrCreate, iArrCreate2);
        SecP384R1Field.square(iArrCreate2, iArrCreate2);
        SecP384R1Field.multiply(iArrCreate2, iArr, iArrCreate2);
        SecP384R1Field.squareN(iArrCreate2, 5, iArrCreate3);
        SecP384R1Field.multiply(iArrCreate3, iArrCreate2, iArrCreate3);
        SecP384R1Field.squareN(iArrCreate3, 5, iArrCreate4);
        SecP384R1Field.multiply(iArrCreate4, iArrCreate2, iArrCreate4);
        SecP384R1Field.squareN(iArrCreate4, 15, iArrCreate2);
        SecP384R1Field.multiply(iArrCreate2, iArrCreate4, iArrCreate2);
        SecP384R1Field.squareN(iArrCreate2, 2, iArrCreate3);
        SecP384R1Field.multiply(iArrCreate, iArrCreate3, iArrCreate);
        SecP384R1Field.squareN(iArrCreate3, 28, iArrCreate3);
        SecP384R1Field.multiply(iArrCreate2, iArrCreate3, iArrCreate2);
        SecP384R1Field.squareN(iArrCreate2, 60, iArrCreate3);
        SecP384R1Field.multiply(iArrCreate3, iArrCreate2, iArrCreate3);
        SecP384R1Field.squareN(iArrCreate3, 120, iArrCreate2);
        SecP384R1Field.multiply(iArrCreate2, iArrCreate3, iArrCreate2);
        SecP384R1Field.squareN(iArrCreate2, 15, iArrCreate2);
        SecP384R1Field.multiply(iArrCreate2, iArrCreate4, iArrCreate2);
        SecP384R1Field.squareN(iArrCreate2, 33, iArrCreate2);
        SecP384R1Field.multiply(iArrCreate2, iArrCreate, iArrCreate2);
        SecP384R1Field.squareN(iArrCreate2, 64, iArrCreate2);
        SecP384R1Field.multiply(iArrCreate2, iArr, iArrCreate2);
        SecP384R1Field.squareN(iArrCreate2, 30, iArrCreate);
        SecP384R1Field.square(iArrCreate, iArrCreate2);
        if (Nat.m1447eq(12, iArr, iArrCreate2)) {
            return new SecP384R1FieldElement(iArrCreate);
        }
        return null;
    }

    @Override
    public ECFieldElement square() {
        int[] iArrCreate = Nat.create(12);
        SecP384R1Field.square(this.f3578x, iArrCreate);
        return new SecP384R1FieldElement(iArrCreate);
    }

    @Override
    public ECFieldElement subtract(ECFieldElement eCFieldElement) {
        int[] iArrCreate = Nat.create(12);
        SecP384R1Field.subtract(this.f3578x, ((SecP384R1FieldElement) eCFieldElement).f3578x, iArrCreate);
        return new SecP384R1FieldElement(iArrCreate);
    }

    @Override
    public boolean testBitZero() {
        return Nat.getBit(this.f3578x, 0) == 1;
    }

    @Override
    public BigInteger toBigInteger() {
        return Nat.toBigInteger(12, this.f3578x);
    }
}
