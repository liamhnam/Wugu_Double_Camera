package org.bouncycastle.math.p058ec.custom.sec;

import java.math.BigInteger;
import org.bouncycastle.math.p058ec.ECFieldElement;
import org.bouncycastle.math.raw.Nat160;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.encoders.Hex;

public class SecP160R2FieldElement extends ECFieldElement.AbstractFp {

    public static final BigInteger f3539Q = new BigInteger(1, Hex.decodeStrict("FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFEFFFFAC73"));

    protected int[] f3540x;

    public SecP160R2FieldElement() {
        this.f3540x = Nat160.create();
    }

    public SecP160R2FieldElement(BigInteger bigInteger) {
        if (bigInteger == null || bigInteger.signum() < 0 || bigInteger.compareTo(f3539Q) >= 0) {
            throw new IllegalArgumentException("x value invalid for SecP160R2FieldElement");
        }
        this.f3540x = SecP160R2Field.fromBigInteger(bigInteger);
    }

    protected SecP160R2FieldElement(int[] iArr) {
        this.f3540x = iArr;
    }

    @Override
    public ECFieldElement add(ECFieldElement eCFieldElement) {
        int[] iArrCreate = Nat160.create();
        SecP160R2Field.add(this.f3540x, ((SecP160R2FieldElement) eCFieldElement).f3540x, iArrCreate);
        return new SecP160R2FieldElement(iArrCreate);
    }

    @Override
    public ECFieldElement addOne() {
        int[] iArrCreate = Nat160.create();
        SecP160R2Field.addOne(this.f3540x, iArrCreate);
        return new SecP160R2FieldElement(iArrCreate);
    }

    @Override
    public ECFieldElement divide(ECFieldElement eCFieldElement) {
        int[] iArrCreate = Nat160.create();
        SecP160R2Field.inv(((SecP160R2FieldElement) eCFieldElement).f3540x, iArrCreate);
        SecP160R2Field.multiply(iArrCreate, this.f3540x, iArrCreate);
        return new SecP160R2FieldElement(iArrCreate);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof SecP160R2FieldElement) {
            return Nat160.m1449eq(this.f3540x, ((SecP160R2FieldElement) obj).f3540x);
        }
        return false;
    }

    @Override
    public String getFieldName() {
        return "SecP160R2Field";
    }

    @Override
    public int getFieldSize() {
        return f3539Q.bitLength();
    }

    public int hashCode() {
        return f3539Q.hashCode() ^ Arrays.hashCode(this.f3540x, 0, 5);
    }

    @Override
    public ECFieldElement invert() {
        int[] iArrCreate = Nat160.create();
        SecP160R2Field.inv(this.f3540x, iArrCreate);
        return new SecP160R2FieldElement(iArrCreate);
    }

    @Override
    public boolean isOne() {
        return Nat160.isOne(this.f3540x);
    }

    @Override
    public boolean isZero() {
        return Nat160.isZero(this.f3540x);
    }

    @Override
    public ECFieldElement multiply(ECFieldElement eCFieldElement) {
        int[] iArrCreate = Nat160.create();
        SecP160R2Field.multiply(this.f3540x, ((SecP160R2FieldElement) eCFieldElement).f3540x, iArrCreate);
        return new SecP160R2FieldElement(iArrCreate);
    }

    @Override
    public ECFieldElement negate() {
        int[] iArrCreate = Nat160.create();
        SecP160R2Field.negate(this.f3540x, iArrCreate);
        return new SecP160R2FieldElement(iArrCreate);
    }

    @Override
    public ECFieldElement sqrt() {
        int[] iArr = this.f3540x;
        if (Nat160.isZero(iArr) || Nat160.isOne(iArr)) {
            return this;
        }
        int[] iArrCreate = Nat160.create();
        SecP160R2Field.square(iArr, iArrCreate);
        SecP160R2Field.multiply(iArrCreate, iArr, iArrCreate);
        int[] iArrCreate2 = Nat160.create();
        SecP160R2Field.square(iArrCreate, iArrCreate2);
        SecP160R2Field.multiply(iArrCreate2, iArr, iArrCreate2);
        int[] iArrCreate3 = Nat160.create();
        SecP160R2Field.square(iArrCreate2, iArrCreate3);
        SecP160R2Field.multiply(iArrCreate3, iArr, iArrCreate3);
        int[] iArrCreate4 = Nat160.create();
        SecP160R2Field.squareN(iArrCreate3, 3, iArrCreate4);
        SecP160R2Field.multiply(iArrCreate4, iArrCreate2, iArrCreate4);
        SecP160R2Field.squareN(iArrCreate4, 7, iArrCreate3);
        SecP160R2Field.multiply(iArrCreate3, iArrCreate4, iArrCreate3);
        SecP160R2Field.squareN(iArrCreate3, 3, iArrCreate4);
        SecP160R2Field.multiply(iArrCreate4, iArrCreate2, iArrCreate4);
        int[] iArrCreate5 = Nat160.create();
        SecP160R2Field.squareN(iArrCreate4, 14, iArrCreate5);
        SecP160R2Field.multiply(iArrCreate5, iArrCreate3, iArrCreate5);
        SecP160R2Field.squareN(iArrCreate5, 31, iArrCreate3);
        SecP160R2Field.multiply(iArrCreate3, iArrCreate5, iArrCreate3);
        SecP160R2Field.squareN(iArrCreate3, 62, iArrCreate5);
        SecP160R2Field.multiply(iArrCreate5, iArrCreate3, iArrCreate5);
        SecP160R2Field.squareN(iArrCreate5, 3, iArrCreate3);
        SecP160R2Field.multiply(iArrCreate3, iArrCreate2, iArrCreate3);
        SecP160R2Field.squareN(iArrCreate3, 18, iArrCreate3);
        SecP160R2Field.multiply(iArrCreate3, iArrCreate4, iArrCreate3);
        SecP160R2Field.squareN(iArrCreate3, 2, iArrCreate3);
        SecP160R2Field.multiply(iArrCreate3, iArr, iArrCreate3);
        SecP160R2Field.squareN(iArrCreate3, 3, iArrCreate3);
        SecP160R2Field.multiply(iArrCreate3, iArrCreate, iArrCreate3);
        SecP160R2Field.squareN(iArrCreate3, 6, iArrCreate3);
        SecP160R2Field.multiply(iArrCreate3, iArrCreate2, iArrCreate3);
        SecP160R2Field.squareN(iArrCreate3, 2, iArrCreate3);
        SecP160R2Field.multiply(iArrCreate3, iArr, iArrCreate3);
        SecP160R2Field.square(iArrCreate3, iArrCreate);
        if (Nat160.m1449eq(iArr, iArrCreate)) {
            return new SecP160R2FieldElement(iArrCreate3);
        }
        return null;
    }

    @Override
    public ECFieldElement square() {
        int[] iArrCreate = Nat160.create();
        SecP160R2Field.square(this.f3540x, iArrCreate);
        return new SecP160R2FieldElement(iArrCreate);
    }

    @Override
    public ECFieldElement subtract(ECFieldElement eCFieldElement) {
        int[] iArrCreate = Nat160.create();
        SecP160R2Field.subtract(this.f3540x, ((SecP160R2FieldElement) eCFieldElement).f3540x, iArrCreate);
        return new SecP160R2FieldElement(iArrCreate);
    }

    @Override
    public boolean testBitZero() {
        return Nat160.getBit(this.f3540x, 0) == 1;
    }

    @Override
    public BigInteger toBigInteger() {
        return Nat160.toBigInteger(this.f3540x);
    }
}
