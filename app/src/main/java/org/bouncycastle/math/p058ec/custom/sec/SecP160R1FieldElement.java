package org.bouncycastle.math.p058ec.custom.sec;

import java.math.BigInteger;
import org.bouncycastle.math.p058ec.ECFieldElement;
import org.bouncycastle.math.raw.Nat160;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.encoders.Hex;

public class SecP160R1FieldElement extends ECFieldElement.AbstractFp {

    public static final BigInteger f3534Q = new BigInteger(1, Hex.decodeStrict("FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF7FFFFFFF"));

    protected int[] f3535x;

    public SecP160R1FieldElement() {
        this.f3535x = Nat160.create();
    }

    public SecP160R1FieldElement(BigInteger bigInteger) {
        if (bigInteger == null || bigInteger.signum() < 0 || bigInteger.compareTo(f3534Q) >= 0) {
            throw new IllegalArgumentException("x value invalid for SecP160R1FieldElement");
        }
        this.f3535x = SecP160R1Field.fromBigInteger(bigInteger);
    }

    protected SecP160R1FieldElement(int[] iArr) {
        this.f3535x = iArr;
    }

    @Override
    public ECFieldElement add(ECFieldElement eCFieldElement) {
        int[] iArrCreate = Nat160.create();
        SecP160R1Field.add(this.f3535x, ((SecP160R1FieldElement) eCFieldElement).f3535x, iArrCreate);
        return new SecP160R1FieldElement(iArrCreate);
    }

    @Override
    public ECFieldElement addOne() {
        int[] iArrCreate = Nat160.create();
        SecP160R1Field.addOne(this.f3535x, iArrCreate);
        return new SecP160R1FieldElement(iArrCreate);
    }

    @Override
    public ECFieldElement divide(ECFieldElement eCFieldElement) {
        int[] iArrCreate = Nat160.create();
        SecP160R1Field.inv(((SecP160R1FieldElement) eCFieldElement).f3535x, iArrCreate);
        SecP160R1Field.multiply(iArrCreate, this.f3535x, iArrCreate);
        return new SecP160R1FieldElement(iArrCreate);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof SecP160R1FieldElement) {
            return Nat160.m1449eq(this.f3535x, ((SecP160R1FieldElement) obj).f3535x);
        }
        return false;
    }

    @Override
    public String getFieldName() {
        return "SecP160R1Field";
    }

    @Override
    public int getFieldSize() {
        return f3534Q.bitLength();
    }

    public int hashCode() {
        return f3534Q.hashCode() ^ Arrays.hashCode(this.f3535x, 0, 5);
    }

    @Override
    public ECFieldElement invert() {
        int[] iArrCreate = Nat160.create();
        SecP160R1Field.inv(this.f3535x, iArrCreate);
        return new SecP160R1FieldElement(iArrCreate);
    }

    @Override
    public boolean isOne() {
        return Nat160.isOne(this.f3535x);
    }

    @Override
    public boolean isZero() {
        return Nat160.isZero(this.f3535x);
    }

    @Override
    public ECFieldElement multiply(ECFieldElement eCFieldElement) {
        int[] iArrCreate = Nat160.create();
        SecP160R1Field.multiply(this.f3535x, ((SecP160R1FieldElement) eCFieldElement).f3535x, iArrCreate);
        return new SecP160R1FieldElement(iArrCreate);
    }

    @Override
    public ECFieldElement negate() {
        int[] iArrCreate = Nat160.create();
        SecP160R1Field.negate(this.f3535x, iArrCreate);
        return new SecP160R1FieldElement(iArrCreate);
    }

    @Override
    public ECFieldElement sqrt() {
        int[] iArr = this.f3535x;
        if (Nat160.isZero(iArr) || Nat160.isOne(iArr)) {
            return this;
        }
        int[] iArrCreate = Nat160.create();
        SecP160R1Field.square(iArr, iArrCreate);
        SecP160R1Field.multiply(iArrCreate, iArr, iArrCreate);
        int[] iArrCreate2 = Nat160.create();
        SecP160R1Field.squareN(iArrCreate, 2, iArrCreate2);
        SecP160R1Field.multiply(iArrCreate2, iArrCreate, iArrCreate2);
        SecP160R1Field.squareN(iArrCreate2, 4, iArrCreate);
        SecP160R1Field.multiply(iArrCreate, iArrCreate2, iArrCreate);
        SecP160R1Field.squareN(iArrCreate, 8, iArrCreate2);
        SecP160R1Field.multiply(iArrCreate2, iArrCreate, iArrCreate2);
        SecP160R1Field.squareN(iArrCreate2, 16, iArrCreate);
        SecP160R1Field.multiply(iArrCreate, iArrCreate2, iArrCreate);
        SecP160R1Field.squareN(iArrCreate, 32, iArrCreate2);
        SecP160R1Field.multiply(iArrCreate2, iArrCreate, iArrCreate2);
        SecP160R1Field.squareN(iArrCreate2, 64, iArrCreate);
        SecP160R1Field.multiply(iArrCreate, iArrCreate2, iArrCreate);
        SecP160R1Field.square(iArrCreate, iArrCreate2);
        SecP160R1Field.multiply(iArrCreate2, iArr, iArrCreate2);
        SecP160R1Field.squareN(iArrCreate2, 29, iArrCreate2);
        SecP160R1Field.square(iArrCreate2, iArrCreate);
        if (Nat160.m1449eq(iArr, iArrCreate)) {
            return new SecP160R1FieldElement(iArrCreate2);
        }
        return null;
    }

    @Override
    public ECFieldElement square() {
        int[] iArrCreate = Nat160.create();
        SecP160R1Field.square(this.f3535x, iArrCreate);
        return new SecP160R1FieldElement(iArrCreate);
    }

    @Override
    public ECFieldElement subtract(ECFieldElement eCFieldElement) {
        int[] iArrCreate = Nat160.create();
        SecP160R1Field.subtract(this.f3535x, ((SecP160R1FieldElement) eCFieldElement).f3535x, iArrCreate);
        return new SecP160R1FieldElement(iArrCreate);
    }

    @Override
    public boolean testBitZero() {
        return Nat160.getBit(this.f3535x, 0) == 1;
    }

    @Override
    public BigInteger toBigInteger() {
        return Nat160.toBigInteger(this.f3535x);
    }
}
