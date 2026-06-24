package org.bouncycastle.math.p058ec.custom.sec;

import java.math.BigInteger;
import org.bouncycastle.math.p058ec.ECFieldElement;
import org.bouncycastle.math.raw.Nat128;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.encoders.Hex;

public class SecP128R1FieldElement extends ECFieldElement.AbstractFp {

    public static final BigInteger f3527Q = new BigInteger(1, Hex.decodeStrict("FFFFFFFDFFFFFFFFFFFFFFFFFFFFFFFF"));

    protected int[] f3528x;

    public SecP128R1FieldElement() {
        this.f3528x = Nat128.create();
    }

    public SecP128R1FieldElement(BigInteger bigInteger) {
        if (bigInteger == null || bigInteger.signum() < 0 || bigInteger.compareTo(f3527Q) >= 0) {
            throw new IllegalArgumentException("x value invalid for SecP128R1FieldElement");
        }
        this.f3528x = SecP128R1Field.fromBigInteger(bigInteger);
    }

    protected SecP128R1FieldElement(int[] iArr) {
        this.f3528x = iArr;
    }

    @Override
    public ECFieldElement add(ECFieldElement eCFieldElement) {
        int[] iArrCreate = Nat128.create();
        SecP128R1Field.add(this.f3528x, ((SecP128R1FieldElement) eCFieldElement).f3528x, iArrCreate);
        return new SecP128R1FieldElement(iArrCreate);
    }

    @Override
    public ECFieldElement addOne() {
        int[] iArrCreate = Nat128.create();
        SecP128R1Field.addOne(this.f3528x, iArrCreate);
        return new SecP128R1FieldElement(iArrCreate);
    }

    @Override
    public ECFieldElement divide(ECFieldElement eCFieldElement) {
        int[] iArrCreate = Nat128.create();
        SecP128R1Field.inv(((SecP128R1FieldElement) eCFieldElement).f3528x, iArrCreate);
        SecP128R1Field.multiply(iArrCreate, this.f3528x, iArrCreate);
        return new SecP128R1FieldElement(iArrCreate);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof SecP128R1FieldElement) {
            return Nat128.m1448eq(this.f3528x, ((SecP128R1FieldElement) obj).f3528x);
        }
        return false;
    }

    @Override
    public String getFieldName() {
        return "SecP128R1Field";
    }

    @Override
    public int getFieldSize() {
        return f3527Q.bitLength();
    }

    public int hashCode() {
        return f3527Q.hashCode() ^ Arrays.hashCode(this.f3528x, 0, 4);
    }

    @Override
    public ECFieldElement invert() {
        int[] iArrCreate = Nat128.create();
        SecP128R1Field.inv(this.f3528x, iArrCreate);
        return new SecP128R1FieldElement(iArrCreate);
    }

    @Override
    public boolean isOne() {
        return Nat128.isOne(this.f3528x);
    }

    @Override
    public boolean isZero() {
        return Nat128.isZero(this.f3528x);
    }

    @Override
    public ECFieldElement multiply(ECFieldElement eCFieldElement) {
        int[] iArrCreate = Nat128.create();
        SecP128R1Field.multiply(this.f3528x, ((SecP128R1FieldElement) eCFieldElement).f3528x, iArrCreate);
        return new SecP128R1FieldElement(iArrCreate);
    }

    @Override
    public ECFieldElement negate() {
        int[] iArrCreate = Nat128.create();
        SecP128R1Field.negate(this.f3528x, iArrCreate);
        return new SecP128R1FieldElement(iArrCreate);
    }

    @Override
    public ECFieldElement sqrt() {
        int[] iArr = this.f3528x;
        if (Nat128.isZero(iArr) || Nat128.isOne(iArr)) {
            return this;
        }
        int[] iArrCreate = Nat128.create();
        SecP128R1Field.square(iArr, iArrCreate);
        SecP128R1Field.multiply(iArrCreate, iArr, iArrCreate);
        int[] iArrCreate2 = Nat128.create();
        SecP128R1Field.squareN(iArrCreate, 2, iArrCreate2);
        SecP128R1Field.multiply(iArrCreate2, iArrCreate, iArrCreate2);
        int[] iArrCreate3 = Nat128.create();
        SecP128R1Field.squareN(iArrCreate2, 4, iArrCreate3);
        SecP128R1Field.multiply(iArrCreate3, iArrCreate2, iArrCreate3);
        SecP128R1Field.squareN(iArrCreate3, 2, iArrCreate2);
        SecP128R1Field.multiply(iArrCreate2, iArrCreate, iArrCreate2);
        SecP128R1Field.squareN(iArrCreate2, 10, iArrCreate);
        SecP128R1Field.multiply(iArrCreate, iArrCreate2, iArrCreate);
        SecP128R1Field.squareN(iArrCreate, 10, iArrCreate3);
        SecP128R1Field.multiply(iArrCreate3, iArrCreate2, iArrCreate3);
        SecP128R1Field.square(iArrCreate3, iArrCreate2);
        SecP128R1Field.multiply(iArrCreate2, iArr, iArrCreate2);
        SecP128R1Field.squareN(iArrCreate2, 95, iArrCreate2);
        SecP128R1Field.square(iArrCreate2, iArrCreate3);
        if (Nat128.m1448eq(iArr, iArrCreate3)) {
            return new SecP128R1FieldElement(iArrCreate2);
        }
        return null;
    }

    @Override
    public ECFieldElement square() {
        int[] iArrCreate = Nat128.create();
        SecP128R1Field.square(this.f3528x, iArrCreate);
        return new SecP128R1FieldElement(iArrCreate);
    }

    @Override
    public ECFieldElement subtract(ECFieldElement eCFieldElement) {
        int[] iArrCreate = Nat128.create();
        SecP128R1Field.subtract(this.f3528x, ((SecP128R1FieldElement) eCFieldElement).f3528x, iArrCreate);
        return new SecP128R1FieldElement(iArrCreate);
    }

    @Override
    public boolean testBitZero() {
        return Nat128.getBit(this.f3528x, 0) == 1;
    }

    @Override
    public BigInteger toBigInteger() {
        return Nat128.toBigInteger(this.f3528x);
    }
}
