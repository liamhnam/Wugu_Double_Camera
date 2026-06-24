package org.bouncycastle.math.p058ec.custom.p059gm;

import java.math.BigInteger;
import org.bouncycastle.math.p058ec.ECFieldElement;
import org.bouncycastle.math.raw.Nat256;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.encoders.Hex;

public class SM2P256V1FieldElement extends ECFieldElement.AbstractFp {

    public static final BigInteger f3522Q = new BigInteger(1, Hex.decodeStrict("FFFFFFFEFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF00000000FFFFFFFFFFFFFFFF"));

    protected int[] f3523x;

    public SM2P256V1FieldElement() {
        this.f3523x = Nat256.create();
    }

    public SM2P256V1FieldElement(BigInteger bigInteger) {
        if (bigInteger == null || bigInteger.signum() < 0 || bigInteger.compareTo(f3522Q) >= 0) {
            throw new IllegalArgumentException("x value invalid for SM2P256V1FieldElement");
        }
        this.f3523x = SM2P256V1Field.fromBigInteger(bigInteger);
    }

    protected SM2P256V1FieldElement(int[] iArr) {
        this.f3523x = iArr;
    }

    @Override
    public ECFieldElement add(ECFieldElement eCFieldElement) {
        int[] iArrCreate = Nat256.create();
        SM2P256V1Field.add(this.f3523x, ((SM2P256V1FieldElement) eCFieldElement).f3523x, iArrCreate);
        return new SM2P256V1FieldElement(iArrCreate);
    }

    @Override
    public ECFieldElement addOne() {
        int[] iArrCreate = Nat256.create();
        SM2P256V1Field.addOne(this.f3523x, iArrCreate);
        return new SM2P256V1FieldElement(iArrCreate);
    }

    @Override
    public ECFieldElement divide(ECFieldElement eCFieldElement) {
        int[] iArrCreate = Nat256.create();
        SM2P256V1Field.inv(((SM2P256V1FieldElement) eCFieldElement).f3523x, iArrCreate);
        SM2P256V1Field.multiply(iArrCreate, this.f3523x, iArrCreate);
        return new SM2P256V1FieldElement(iArrCreate);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof SM2P256V1FieldElement) {
            return Nat256.m1452eq(this.f3523x, ((SM2P256V1FieldElement) obj).f3523x);
        }
        return false;
    }

    @Override
    public String getFieldName() {
        return "SM2P256V1Field";
    }

    @Override
    public int getFieldSize() {
        return f3522Q.bitLength();
    }

    public int hashCode() {
        return f3522Q.hashCode() ^ Arrays.hashCode(this.f3523x, 0, 8);
    }

    @Override
    public ECFieldElement invert() {
        int[] iArrCreate = Nat256.create();
        SM2P256V1Field.inv(this.f3523x, iArrCreate);
        return new SM2P256V1FieldElement(iArrCreate);
    }

    @Override
    public boolean isOne() {
        return Nat256.isOne(this.f3523x);
    }

    @Override
    public boolean isZero() {
        return Nat256.isZero(this.f3523x);
    }

    @Override
    public ECFieldElement multiply(ECFieldElement eCFieldElement) {
        int[] iArrCreate = Nat256.create();
        SM2P256V1Field.multiply(this.f3523x, ((SM2P256V1FieldElement) eCFieldElement).f3523x, iArrCreate);
        return new SM2P256V1FieldElement(iArrCreate);
    }

    @Override
    public ECFieldElement negate() {
        int[] iArrCreate = Nat256.create();
        SM2P256V1Field.negate(this.f3523x, iArrCreate);
        return new SM2P256V1FieldElement(iArrCreate);
    }

    @Override
    public ECFieldElement sqrt() {
        int[] iArr = this.f3523x;
        if (Nat256.isZero(iArr) || Nat256.isOne(iArr)) {
            return this;
        }
        int[] iArrCreate = Nat256.create();
        SM2P256V1Field.square(iArr, iArrCreate);
        SM2P256V1Field.multiply(iArrCreate, iArr, iArrCreate);
        int[] iArrCreate2 = Nat256.create();
        SM2P256V1Field.squareN(iArrCreate, 2, iArrCreate2);
        SM2P256V1Field.multiply(iArrCreate2, iArrCreate, iArrCreate2);
        int[] iArrCreate3 = Nat256.create();
        SM2P256V1Field.squareN(iArrCreate2, 2, iArrCreate3);
        SM2P256V1Field.multiply(iArrCreate3, iArrCreate, iArrCreate3);
        SM2P256V1Field.squareN(iArrCreate3, 6, iArrCreate);
        SM2P256V1Field.multiply(iArrCreate, iArrCreate3, iArrCreate);
        int[] iArrCreate4 = Nat256.create();
        SM2P256V1Field.squareN(iArrCreate, 12, iArrCreate4);
        SM2P256V1Field.multiply(iArrCreate4, iArrCreate, iArrCreate4);
        SM2P256V1Field.squareN(iArrCreate4, 6, iArrCreate);
        SM2P256V1Field.multiply(iArrCreate, iArrCreate3, iArrCreate);
        SM2P256V1Field.square(iArrCreate, iArrCreate3);
        SM2P256V1Field.multiply(iArrCreate3, iArr, iArrCreate3);
        SM2P256V1Field.squareN(iArrCreate3, 31, iArrCreate4);
        SM2P256V1Field.multiply(iArrCreate4, iArrCreate3, iArrCreate);
        SM2P256V1Field.squareN(iArrCreate4, 32, iArrCreate4);
        SM2P256V1Field.multiply(iArrCreate4, iArrCreate, iArrCreate4);
        SM2P256V1Field.squareN(iArrCreate4, 62, iArrCreate4);
        SM2P256V1Field.multiply(iArrCreate4, iArrCreate, iArrCreate4);
        SM2P256V1Field.squareN(iArrCreate4, 4, iArrCreate4);
        SM2P256V1Field.multiply(iArrCreate4, iArrCreate2, iArrCreate4);
        SM2P256V1Field.squareN(iArrCreate4, 32, iArrCreate4);
        SM2P256V1Field.multiply(iArrCreate4, iArr, iArrCreate4);
        SM2P256V1Field.squareN(iArrCreate4, 62, iArrCreate4);
        SM2P256V1Field.square(iArrCreate4, iArrCreate2);
        if (Nat256.m1452eq(iArr, iArrCreate2)) {
            return new SM2P256V1FieldElement(iArrCreate4);
        }
        return null;
    }

    @Override
    public ECFieldElement square() {
        int[] iArrCreate = Nat256.create();
        SM2P256V1Field.square(this.f3523x, iArrCreate);
        return new SM2P256V1FieldElement(iArrCreate);
    }

    @Override
    public ECFieldElement subtract(ECFieldElement eCFieldElement) {
        int[] iArrCreate = Nat256.create();
        SM2P256V1Field.subtract(this.f3523x, ((SM2P256V1FieldElement) eCFieldElement).f3523x, iArrCreate);
        return new SM2P256V1FieldElement(iArrCreate);
    }

    @Override
    public boolean testBitZero() {
        return Nat256.getBit(this.f3523x, 0) == 1;
    }

    @Override
    public BigInteger toBigInteger() {
        return Nat256.toBigInteger(this.f3523x);
    }
}
