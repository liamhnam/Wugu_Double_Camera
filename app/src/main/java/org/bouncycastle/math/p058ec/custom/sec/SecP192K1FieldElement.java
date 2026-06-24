package org.bouncycastle.math.p058ec.custom.sec;

import java.math.BigInteger;
import org.bouncycastle.math.p058ec.ECFieldElement;
import org.bouncycastle.math.raw.Nat192;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.encoders.Hex;

public class SecP192K1FieldElement extends ECFieldElement.AbstractFp {

    public static final BigInteger f3544Q = new BigInteger(1, Hex.decodeStrict("FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFEFFFFEE37"));

    protected int[] f3545x;

    public SecP192K1FieldElement() {
        this.f3545x = Nat192.create();
    }

    public SecP192K1FieldElement(BigInteger bigInteger) {
        if (bigInteger == null || bigInteger.signum() < 0 || bigInteger.compareTo(f3544Q) >= 0) {
            throw new IllegalArgumentException("x value invalid for SecP192K1FieldElement");
        }
        this.f3545x = SecP192K1Field.fromBigInteger(bigInteger);
    }

    protected SecP192K1FieldElement(int[] iArr) {
        this.f3545x = iArr;
    }

    @Override
    public ECFieldElement add(ECFieldElement eCFieldElement) {
        int[] iArrCreate = Nat192.create();
        SecP192K1Field.add(this.f3545x, ((SecP192K1FieldElement) eCFieldElement).f3545x, iArrCreate);
        return new SecP192K1FieldElement(iArrCreate);
    }

    @Override
    public ECFieldElement addOne() {
        int[] iArrCreate = Nat192.create();
        SecP192K1Field.addOne(this.f3545x, iArrCreate);
        return new SecP192K1FieldElement(iArrCreate);
    }

    @Override
    public ECFieldElement divide(ECFieldElement eCFieldElement) {
        int[] iArrCreate = Nat192.create();
        SecP192K1Field.inv(((SecP192K1FieldElement) eCFieldElement).f3545x, iArrCreate);
        SecP192K1Field.multiply(iArrCreate, this.f3545x, iArrCreate);
        return new SecP192K1FieldElement(iArrCreate);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof SecP192K1FieldElement) {
            return Nat192.m1450eq(this.f3545x, ((SecP192K1FieldElement) obj).f3545x);
        }
        return false;
    }

    @Override
    public String getFieldName() {
        return "SecP192K1Field";
    }

    @Override
    public int getFieldSize() {
        return f3544Q.bitLength();
    }

    public int hashCode() {
        return f3544Q.hashCode() ^ Arrays.hashCode(this.f3545x, 0, 6);
    }

    @Override
    public ECFieldElement invert() {
        int[] iArrCreate = Nat192.create();
        SecP192K1Field.inv(this.f3545x, iArrCreate);
        return new SecP192K1FieldElement(iArrCreate);
    }

    @Override
    public boolean isOne() {
        return Nat192.isOne(this.f3545x);
    }

    @Override
    public boolean isZero() {
        return Nat192.isZero(this.f3545x);
    }

    @Override
    public ECFieldElement multiply(ECFieldElement eCFieldElement) {
        int[] iArrCreate = Nat192.create();
        SecP192K1Field.multiply(this.f3545x, ((SecP192K1FieldElement) eCFieldElement).f3545x, iArrCreate);
        return new SecP192K1FieldElement(iArrCreate);
    }

    @Override
    public ECFieldElement negate() {
        int[] iArrCreate = Nat192.create();
        SecP192K1Field.negate(this.f3545x, iArrCreate);
        return new SecP192K1FieldElement(iArrCreate);
    }

    @Override
    public ECFieldElement sqrt() {
        int[] iArr = this.f3545x;
        if (Nat192.isZero(iArr) || Nat192.isOne(iArr)) {
            return this;
        }
        int[] iArrCreate = Nat192.create();
        SecP192K1Field.square(iArr, iArrCreate);
        SecP192K1Field.multiply(iArrCreate, iArr, iArrCreate);
        int[] iArrCreate2 = Nat192.create();
        SecP192K1Field.square(iArrCreate, iArrCreate2);
        SecP192K1Field.multiply(iArrCreate2, iArr, iArrCreate2);
        int[] iArrCreate3 = Nat192.create();
        SecP192K1Field.squareN(iArrCreate2, 3, iArrCreate3);
        SecP192K1Field.multiply(iArrCreate3, iArrCreate2, iArrCreate3);
        SecP192K1Field.squareN(iArrCreate3, 2, iArrCreate3);
        SecP192K1Field.multiply(iArrCreate3, iArrCreate, iArrCreate3);
        SecP192K1Field.squareN(iArrCreate3, 8, iArrCreate);
        SecP192K1Field.multiply(iArrCreate, iArrCreate3, iArrCreate);
        SecP192K1Field.squareN(iArrCreate, 3, iArrCreate3);
        SecP192K1Field.multiply(iArrCreate3, iArrCreate2, iArrCreate3);
        int[] iArrCreate4 = Nat192.create();
        SecP192K1Field.squareN(iArrCreate3, 16, iArrCreate4);
        SecP192K1Field.multiply(iArrCreate4, iArrCreate, iArrCreate4);
        SecP192K1Field.squareN(iArrCreate4, 35, iArrCreate);
        SecP192K1Field.multiply(iArrCreate, iArrCreate4, iArrCreate);
        SecP192K1Field.squareN(iArrCreate, 70, iArrCreate4);
        SecP192K1Field.multiply(iArrCreate4, iArrCreate, iArrCreate4);
        SecP192K1Field.squareN(iArrCreate4, 19, iArrCreate);
        SecP192K1Field.multiply(iArrCreate, iArrCreate3, iArrCreate);
        SecP192K1Field.squareN(iArrCreate, 20, iArrCreate);
        SecP192K1Field.multiply(iArrCreate, iArrCreate3, iArrCreate);
        SecP192K1Field.squareN(iArrCreate, 4, iArrCreate);
        SecP192K1Field.multiply(iArrCreate, iArrCreate2, iArrCreate);
        SecP192K1Field.squareN(iArrCreate, 6, iArrCreate);
        SecP192K1Field.multiply(iArrCreate, iArrCreate2, iArrCreate);
        SecP192K1Field.square(iArrCreate, iArrCreate);
        SecP192K1Field.square(iArrCreate, iArrCreate2);
        if (Nat192.m1450eq(iArr, iArrCreate2)) {
            return new SecP192K1FieldElement(iArrCreate);
        }
        return null;
    }

    @Override
    public ECFieldElement square() {
        int[] iArrCreate = Nat192.create();
        SecP192K1Field.square(this.f3545x, iArrCreate);
        return new SecP192K1FieldElement(iArrCreate);
    }

    @Override
    public ECFieldElement subtract(ECFieldElement eCFieldElement) {
        int[] iArrCreate = Nat192.create();
        SecP192K1Field.subtract(this.f3545x, ((SecP192K1FieldElement) eCFieldElement).f3545x, iArrCreate);
        return new SecP192K1FieldElement(iArrCreate);
    }

    @Override
    public boolean testBitZero() {
        return Nat192.getBit(this.f3545x, 0) == 1;
    }

    @Override
    public BigInteger toBigInteger() {
        return Nat192.toBigInteger(this.f3545x);
    }
}
