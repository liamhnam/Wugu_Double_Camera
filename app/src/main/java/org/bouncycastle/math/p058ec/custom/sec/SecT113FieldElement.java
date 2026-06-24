package org.bouncycastle.math.p058ec.custom.sec;

import com.wugu.doublecamera.enums.UiPosIndexEnum;
import java.math.BigInteger;
import org.bouncycastle.math.p058ec.ECFieldElement;
import org.bouncycastle.math.raw.Nat128;
import org.bouncycastle.util.Arrays;

public class SecT113FieldElement extends ECFieldElement.AbstractF2m {

    protected long[] f3583x;

    public SecT113FieldElement() {
        this.f3583x = Nat128.create64();
    }

    public SecT113FieldElement(BigInteger bigInteger) {
        if (bigInteger == null || bigInteger.signum() < 0 || bigInteger.bitLength() > 113) {
            throw new IllegalArgumentException("x value invalid for SecT113FieldElement");
        }
        this.f3583x = SecT113Field.fromBigInteger(bigInteger);
    }

    protected SecT113FieldElement(long[] jArr) {
        this.f3583x = jArr;
    }

    @Override
    public ECFieldElement add(ECFieldElement eCFieldElement) {
        long[] jArrCreate64 = Nat128.create64();
        SecT113Field.add(this.f3583x, ((SecT113FieldElement) eCFieldElement).f3583x, jArrCreate64);
        return new SecT113FieldElement(jArrCreate64);
    }

    @Override
    public ECFieldElement addOne() {
        long[] jArrCreate64 = Nat128.create64();
        SecT113Field.addOne(this.f3583x, jArrCreate64);
        return new SecT113FieldElement(jArrCreate64);
    }

    @Override
    public ECFieldElement divide(ECFieldElement eCFieldElement) {
        return multiply(eCFieldElement.invert());
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof SecT113FieldElement) {
            return Nat128.eq64(this.f3583x, ((SecT113FieldElement) obj).f3583x);
        }
        return false;
    }

    @Override
    public String getFieldName() {
        return "SecT113Field";
    }

    @Override
    public int getFieldSize() {
        return UiPosIndexEnum.HOME_REPLACE_BG_TAB;
    }

    public int getK1() {
        return 9;
    }

    public int getK2() {
        return 0;
    }

    public int getK3() {
        return 0;
    }

    public int getM() {
        return UiPosIndexEnum.HOME_REPLACE_BG_TAB;
    }

    public int getRepresentation() {
        return 2;
    }

    @Override
    public ECFieldElement halfTrace() {
        long[] jArrCreate64 = Nat128.create64();
        SecT113Field.halfTrace(this.f3583x, jArrCreate64);
        return new SecT113FieldElement(jArrCreate64);
    }

    @Override
    public boolean hasFastTrace() {
        return true;
    }

    public int hashCode() {
        return Arrays.hashCode(this.f3583x, 0, 2) ^ 113009;
    }

    @Override
    public ECFieldElement invert() {
        long[] jArrCreate64 = Nat128.create64();
        SecT113Field.invert(this.f3583x, jArrCreate64);
        return new SecT113FieldElement(jArrCreate64);
    }

    @Override
    public boolean isOne() {
        return Nat128.isOne64(this.f3583x);
    }

    @Override
    public boolean isZero() {
        return Nat128.isZero64(this.f3583x);
    }

    @Override
    public ECFieldElement multiply(ECFieldElement eCFieldElement) {
        long[] jArrCreate64 = Nat128.create64();
        SecT113Field.multiply(this.f3583x, ((SecT113FieldElement) eCFieldElement).f3583x, jArrCreate64);
        return new SecT113FieldElement(jArrCreate64);
    }

    @Override
    public ECFieldElement multiplyMinusProduct(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2, ECFieldElement eCFieldElement3) {
        return multiplyPlusProduct(eCFieldElement, eCFieldElement2, eCFieldElement3);
    }

    @Override
    public ECFieldElement multiplyPlusProduct(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2, ECFieldElement eCFieldElement3) {
        long[] jArr = this.f3583x;
        long[] jArr2 = ((SecT113FieldElement) eCFieldElement).f3583x;
        long[] jArr3 = ((SecT113FieldElement) eCFieldElement2).f3583x;
        long[] jArr4 = ((SecT113FieldElement) eCFieldElement3).f3583x;
        long[] jArrCreateExt64 = Nat128.createExt64();
        SecT113Field.multiplyAddToExt(jArr, jArr2, jArrCreateExt64);
        SecT113Field.multiplyAddToExt(jArr3, jArr4, jArrCreateExt64);
        long[] jArrCreate64 = Nat128.create64();
        SecT113Field.reduce(jArrCreateExt64, jArrCreate64);
        return new SecT113FieldElement(jArrCreate64);
    }

    @Override
    public ECFieldElement negate() {
        return this;
    }

    @Override
    public ECFieldElement sqrt() {
        long[] jArrCreate64 = Nat128.create64();
        SecT113Field.sqrt(this.f3583x, jArrCreate64);
        return new SecT113FieldElement(jArrCreate64);
    }

    @Override
    public ECFieldElement square() {
        long[] jArrCreate64 = Nat128.create64();
        SecT113Field.square(this.f3583x, jArrCreate64);
        return new SecT113FieldElement(jArrCreate64);
    }

    @Override
    public ECFieldElement squareMinusProduct(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2) {
        return squarePlusProduct(eCFieldElement, eCFieldElement2);
    }

    @Override
    public ECFieldElement squarePlusProduct(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2) {
        long[] jArr = this.f3583x;
        long[] jArr2 = ((SecT113FieldElement) eCFieldElement).f3583x;
        long[] jArr3 = ((SecT113FieldElement) eCFieldElement2).f3583x;
        long[] jArrCreateExt64 = Nat128.createExt64();
        SecT113Field.squareAddToExt(jArr, jArrCreateExt64);
        SecT113Field.multiplyAddToExt(jArr2, jArr3, jArrCreateExt64);
        long[] jArrCreate64 = Nat128.create64();
        SecT113Field.reduce(jArrCreateExt64, jArrCreate64);
        return new SecT113FieldElement(jArrCreate64);
    }

    @Override
    public ECFieldElement squarePow(int i) {
        if (i < 1) {
            return this;
        }
        long[] jArrCreate64 = Nat128.create64();
        SecT113Field.squareN(this.f3583x, i, jArrCreate64);
        return new SecT113FieldElement(jArrCreate64);
    }

    @Override
    public ECFieldElement subtract(ECFieldElement eCFieldElement) {
        return add(eCFieldElement);
    }

    @Override
    public boolean testBitZero() {
        return (this.f3583x[0] & 1) != 0;
    }

    @Override
    public BigInteger toBigInteger() {
        return Nat128.toBigInteger64(this.f3583x);
    }

    @Override
    public int trace() {
        return SecT113Field.trace(this.f3583x);
    }
}
