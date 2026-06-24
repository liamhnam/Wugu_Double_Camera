package org.bouncycastle.math.p058ec.custom.sec;

import java.math.BigInteger;
import org.bouncycastle.math.p058ec.ECFieldElement;
import org.bouncycastle.math.raw.Mod;
import org.bouncycastle.math.raw.Nat;
import org.bouncycastle.math.raw.Nat224;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.encoders.Hex;

public class SecP224R1FieldElement extends ECFieldElement.AbstractFp {

    public static final BigInteger f3561Q = new BigInteger(1, Hex.decodeStrict("FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF000000000000000000000001"));

    protected int[] f3562x;

    public SecP224R1FieldElement() {
        this.f3562x = Nat224.create();
    }

    public SecP224R1FieldElement(BigInteger bigInteger) {
        if (bigInteger == null || bigInteger.signum() < 0 || bigInteger.compareTo(f3561Q) >= 0) {
            throw new IllegalArgumentException("x value invalid for SecP224R1FieldElement");
        }
        this.f3562x = SecP224R1Field.fromBigInteger(bigInteger);
    }

    protected SecP224R1FieldElement(int[] iArr) {
        this.f3562x = iArr;
    }

    private static void m1444RM(int[] iArr, int[] iArr2, int[] iArr3, int[] iArr4, int[] iArr5, int[] iArr6, int[] iArr7) {
        SecP224R1Field.multiply(iArr5, iArr3, iArr7);
        SecP224R1Field.multiply(iArr7, iArr, iArr7);
        SecP224R1Field.multiply(iArr4, iArr2, iArr6);
        SecP224R1Field.add(iArr6, iArr7, iArr6);
        SecP224R1Field.multiply(iArr4, iArr3, iArr7);
        Nat224.copy(iArr6, iArr4);
        SecP224R1Field.multiply(iArr5, iArr2, iArr5);
        SecP224R1Field.add(iArr5, iArr7, iArr5);
        SecP224R1Field.square(iArr5, iArr6);
        SecP224R1Field.multiply(iArr6, iArr, iArr6);
    }

    private static void m1445RP(int[] iArr, int[] iArr2, int[] iArr3, int[] iArr4, int[] iArr5) {
        Nat224.copy(iArr, iArr4);
        int[] iArrCreate = Nat224.create();
        int[] iArrCreate2 = Nat224.create();
        for (int i = 0; i < 7; i++) {
            Nat224.copy(iArr2, iArrCreate);
            Nat224.copy(iArr3, iArrCreate2);
            int i2 = 1 << i;
            while (true) {
                i2--;
                if (i2 >= 0) {
                    m1446RS(iArr2, iArr3, iArr4, iArr5);
                }
            }
            m1444RM(iArr, iArrCreate, iArrCreate2, iArr2, iArr3, iArr4, iArr5);
        }
    }

    private static void m1446RS(int[] iArr, int[] iArr2, int[] iArr3, int[] iArr4) {
        SecP224R1Field.multiply(iArr2, iArr, iArr2);
        SecP224R1Field.twice(iArr2, iArr2);
        SecP224R1Field.square(iArr, iArr4);
        SecP224R1Field.add(iArr3, iArr4, iArr);
        SecP224R1Field.multiply(iArr3, iArr4, iArr3);
        SecP224R1Field.reduce32(Nat.shiftUpBits(7, iArr3, 2, 0), iArr3);
    }

    private static boolean isSquare(int[] iArr) {
        int[] iArrCreate = Nat224.create();
        int[] iArrCreate2 = Nat224.create();
        Nat224.copy(iArr, iArrCreate);
        for (int i = 0; i < 7; i++) {
            Nat224.copy(iArrCreate, iArrCreate2);
            SecP224R1Field.squareN(iArrCreate, 1 << i, iArrCreate);
            SecP224R1Field.multiply(iArrCreate, iArrCreate2, iArrCreate);
        }
        SecP224R1Field.squareN(iArrCreate, 95, iArrCreate);
        return Nat224.isOne(iArrCreate);
    }

    private static boolean trySqrt(int[] iArr, int[] iArr2, int[] iArr3) {
        int[] iArrCreate = Nat224.create();
        Nat224.copy(iArr2, iArrCreate);
        int[] iArrCreate2 = Nat224.create();
        iArrCreate2[0] = 1;
        int[] iArrCreate3 = Nat224.create();
        m1445RP(iArr, iArrCreate, iArrCreate2, iArrCreate3, iArr3);
        int[] iArrCreate4 = Nat224.create();
        int[] iArrCreate5 = Nat224.create();
        for (int i = 1; i < 96; i++) {
            Nat224.copy(iArrCreate, iArrCreate4);
            Nat224.copy(iArrCreate2, iArrCreate5);
            m1446RS(iArrCreate, iArrCreate2, iArrCreate3, iArr3);
            if (Nat224.isZero(iArrCreate)) {
                SecP224R1Field.inv(iArrCreate5, iArr3);
                SecP224R1Field.multiply(iArr3, iArrCreate4, iArr3);
                return true;
            }
        }
        return false;
    }

    @Override
    public ECFieldElement add(ECFieldElement eCFieldElement) {
        int[] iArrCreate = Nat224.create();
        SecP224R1Field.add(this.f3562x, ((SecP224R1FieldElement) eCFieldElement).f3562x, iArrCreate);
        return new SecP224R1FieldElement(iArrCreate);
    }

    @Override
    public ECFieldElement addOne() {
        int[] iArrCreate = Nat224.create();
        SecP224R1Field.addOne(this.f3562x, iArrCreate);
        return new SecP224R1FieldElement(iArrCreate);
    }

    @Override
    public ECFieldElement divide(ECFieldElement eCFieldElement) {
        int[] iArrCreate = Nat224.create();
        SecP224R1Field.inv(((SecP224R1FieldElement) eCFieldElement).f3562x, iArrCreate);
        SecP224R1Field.multiply(iArrCreate, this.f3562x, iArrCreate);
        return new SecP224R1FieldElement(iArrCreate);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof SecP224R1FieldElement) {
            return Nat224.m1451eq(this.f3562x, ((SecP224R1FieldElement) obj).f3562x);
        }
        return false;
    }

    @Override
    public String getFieldName() {
        return "SecP224R1Field";
    }

    @Override
    public int getFieldSize() {
        return f3561Q.bitLength();
    }

    public int hashCode() {
        return f3561Q.hashCode() ^ Arrays.hashCode(this.f3562x, 0, 7);
    }

    @Override
    public ECFieldElement invert() {
        int[] iArrCreate = Nat224.create();
        SecP224R1Field.inv(this.f3562x, iArrCreate);
        return new SecP224R1FieldElement(iArrCreate);
    }

    @Override
    public boolean isOne() {
        return Nat224.isOne(this.f3562x);
    }

    @Override
    public boolean isZero() {
        return Nat224.isZero(this.f3562x);
    }

    @Override
    public ECFieldElement multiply(ECFieldElement eCFieldElement) {
        int[] iArrCreate = Nat224.create();
        SecP224R1Field.multiply(this.f3562x, ((SecP224R1FieldElement) eCFieldElement).f3562x, iArrCreate);
        return new SecP224R1FieldElement(iArrCreate);
    }

    @Override
    public ECFieldElement negate() {
        int[] iArrCreate = Nat224.create();
        SecP224R1Field.negate(this.f3562x, iArrCreate);
        return new SecP224R1FieldElement(iArrCreate);
    }

    @Override
    public ECFieldElement sqrt() {
        int[] iArr = this.f3562x;
        if (Nat224.isZero(iArr) || Nat224.isOne(iArr)) {
            return this;
        }
        int[] iArrCreate = Nat224.create();
        SecP224R1Field.negate(iArr, iArrCreate);
        int[] iArrRandom = Mod.random(SecP224R1Field.f3559P);
        int[] iArrCreate2 = Nat224.create();
        if (!isSquare(iArr)) {
            return null;
        }
        while (!trySqrt(iArrCreate, iArrRandom, iArrCreate2)) {
            SecP224R1Field.addOne(iArrRandom, iArrRandom);
        }
        SecP224R1Field.square(iArrCreate2, iArrRandom);
        if (Nat224.m1451eq(iArr, iArrRandom)) {
            return new SecP224R1FieldElement(iArrCreate2);
        }
        return null;
    }

    @Override
    public ECFieldElement square() {
        int[] iArrCreate = Nat224.create();
        SecP224R1Field.square(this.f3562x, iArrCreate);
        return new SecP224R1FieldElement(iArrCreate);
    }

    @Override
    public ECFieldElement subtract(ECFieldElement eCFieldElement) {
        int[] iArrCreate = Nat224.create();
        SecP224R1Field.subtract(this.f3562x, ((SecP224R1FieldElement) eCFieldElement).f3562x, iArrCreate);
        return new SecP224R1FieldElement(iArrCreate);
    }

    @Override
    public boolean testBitZero() {
        return Nat224.getBit(this.f3562x, 0) == 1;
    }

    @Override
    public BigInteger toBigInteger() {
        return Nat224.toBigInteger(this.f3562x);
    }
}
