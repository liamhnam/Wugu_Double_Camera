package org.bouncycastle.math.p058ec;

import java.math.BigInteger;
import java.util.Random;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.BigIntegers;
import org.bouncycastle.util.Integers;

public abstract class ECFieldElement implements ECConstants {

    public static abstract class AbstractF2m extends ECFieldElement {
        public ECFieldElement halfTrace() {
            int fieldSize = getFieldSize();
            if ((fieldSize & 1) == 0) {
                throw new IllegalStateException("Half-trace only defined for odd m");
            }
            int i = (fieldSize + 1) >>> 1;
            int iNumberOfLeadingZeros = 31 - Integers.numberOfLeadingZeros(i);
            ECFieldElement eCFieldElementAdd = this;
            int i2 = 1;
            while (iNumberOfLeadingZeros > 0) {
                eCFieldElementAdd = eCFieldElementAdd.squarePow(i2 << 1).add(eCFieldElementAdd);
                iNumberOfLeadingZeros--;
                i2 = i >>> iNumberOfLeadingZeros;
                if ((i2 & 1) != 0) {
                    eCFieldElementAdd = eCFieldElementAdd.squarePow(2).add(this);
                }
            }
            return eCFieldElementAdd;
        }

        public boolean hasFastTrace() {
            return false;
        }

        public int trace() {
            int fieldSize = getFieldSize();
            int iNumberOfLeadingZeros = 31 - Integers.numberOfLeadingZeros(fieldSize);
            ECFieldElement eCFieldElementAdd = this;
            int i = 1;
            while (iNumberOfLeadingZeros > 0) {
                eCFieldElementAdd = eCFieldElementAdd.squarePow(i).add(eCFieldElementAdd);
                iNumberOfLeadingZeros--;
                i = fieldSize >>> iNumberOfLeadingZeros;
                if ((i & 1) != 0) {
                    eCFieldElementAdd = eCFieldElementAdd.square().add(this);
                }
            }
            if (eCFieldElementAdd.isZero()) {
                return 0;
            }
            if (eCFieldElementAdd.isOne()) {
                return 1;
            }
            throw new IllegalStateException("Internal error in trace calculation");
        }
    }

    public static abstract class AbstractFp extends ECFieldElement {
    }

    public static class F2m extends AbstractF2m {
        public static final int GNB = 1;
        public static final int PPB = 3;
        public static final int TPB = 2;

        private int[] f3502ks;

        private int f3503m;
        private int representation;

        LongArray f3504x;

        public F2m(int i, int i2, int i3, int i4, BigInteger bigInteger) {
            if (bigInteger == null || bigInteger.signum() < 0 || bigInteger.bitLength() > i) {
                throw new IllegalArgumentException("x value invalid in F2m field element");
            }
            if (i3 == 0 && i4 == 0) {
                this.representation = 2;
                this.f3502ks = new int[]{i2};
            } else {
                if (i3 >= i4) {
                    throw new IllegalArgumentException("k2 must be smaller than k3");
                }
                if (i3 <= 0) {
                    throw new IllegalArgumentException("k2 must be larger than 0");
                }
                this.representation = 3;
                this.f3502ks = new int[]{i2, i3, i4};
            }
            this.f3503m = i;
            this.f3504x = new LongArray(bigInteger);
        }

        F2m(int i, int[] iArr, LongArray longArray) {
            this.f3503m = i;
            this.representation = iArr.length == 1 ? 2 : 3;
            this.f3502ks = iArr;
            this.f3504x = longArray;
        }

        @Override
        public ECFieldElement add(ECFieldElement eCFieldElement) {
            LongArray longArray = (LongArray) this.f3504x.clone();
            longArray.addShiftedByWords(((F2m) eCFieldElement).f3504x, 0);
            return new F2m(this.f3503m, this.f3502ks, longArray);
        }

        @Override
        public ECFieldElement addOne() {
            return new F2m(this.f3503m, this.f3502ks, this.f3504x.addOne());
        }

        @Override
        public int bitLength() {
            return this.f3504x.degree();
        }

        @Override
        public ECFieldElement divide(ECFieldElement eCFieldElement) {
            return multiply(eCFieldElement.invert());
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof F2m)) {
                return false;
            }
            F2m f2m = (F2m) obj;
            return this.f3503m == f2m.f3503m && this.representation == f2m.representation && Arrays.areEqual(this.f3502ks, f2m.f3502ks) && this.f3504x.equals(f2m.f3504x);
        }

        @Override
        public String getFieldName() {
            return "F2m";
        }

        @Override
        public int getFieldSize() {
            return this.f3503m;
        }

        public int getK1() {
            return this.f3502ks[0];
        }

        public int getK2() {
            int[] iArr = this.f3502ks;
            if (iArr.length >= 2) {
                return iArr[1];
            }
            return 0;
        }

        public int getK3() {
            int[] iArr = this.f3502ks;
            if (iArr.length >= 3) {
                return iArr[2];
            }
            return 0;
        }

        public int getM() {
            return this.f3503m;
        }

        public int getRepresentation() {
            return this.representation;
        }

        public int hashCode() {
            return (this.f3504x.hashCode() ^ this.f3503m) ^ Arrays.hashCode(this.f3502ks);
        }

        @Override
        public ECFieldElement invert() {
            int i = this.f3503m;
            int[] iArr = this.f3502ks;
            return new F2m(i, iArr, this.f3504x.modInverse(i, iArr));
        }

        @Override
        public boolean isOne() {
            return this.f3504x.isOne();
        }

        @Override
        public boolean isZero() {
            return this.f3504x.isZero();
        }

        @Override
        public ECFieldElement multiply(ECFieldElement eCFieldElement) {
            int i = this.f3503m;
            int[] iArr = this.f3502ks;
            return new F2m(i, iArr, this.f3504x.modMultiply(((F2m) eCFieldElement).f3504x, i, iArr));
        }

        @Override
        public ECFieldElement multiplyMinusProduct(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2, ECFieldElement eCFieldElement3) {
            return multiplyPlusProduct(eCFieldElement, eCFieldElement2, eCFieldElement3);
        }

        @Override
        public ECFieldElement multiplyPlusProduct(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2, ECFieldElement eCFieldElement3) {
            LongArray longArray = this.f3504x;
            LongArray longArray2 = ((F2m) eCFieldElement).f3504x;
            LongArray longArray3 = ((F2m) eCFieldElement2).f3504x;
            LongArray longArray4 = ((F2m) eCFieldElement3).f3504x;
            LongArray longArrayMultiply = longArray.multiply(longArray2, this.f3503m, this.f3502ks);
            LongArray longArrayMultiply2 = longArray3.multiply(longArray4, this.f3503m, this.f3502ks);
            if (longArrayMultiply == longArray || longArrayMultiply == longArray2) {
                longArrayMultiply = (LongArray) longArrayMultiply.clone();
            }
            longArrayMultiply.addShiftedByWords(longArrayMultiply2, 0);
            longArrayMultiply.reduce(this.f3503m, this.f3502ks);
            return new F2m(this.f3503m, this.f3502ks, longArrayMultiply);
        }

        @Override
        public ECFieldElement negate() {
            return this;
        }

        @Override
        public ECFieldElement sqrt() {
            return (this.f3504x.isZero() || this.f3504x.isOne()) ? this : squarePow(this.f3503m - 1);
        }

        @Override
        public ECFieldElement square() {
            int i = this.f3503m;
            int[] iArr = this.f3502ks;
            return new F2m(i, iArr, this.f3504x.modSquare(i, iArr));
        }

        @Override
        public ECFieldElement squareMinusProduct(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2) {
            return squarePlusProduct(eCFieldElement, eCFieldElement2);
        }

        @Override
        public ECFieldElement squarePlusProduct(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2) {
            LongArray longArray = this.f3504x;
            LongArray longArray2 = ((F2m) eCFieldElement).f3504x;
            LongArray longArray3 = ((F2m) eCFieldElement2).f3504x;
            LongArray longArraySquare = longArray.square(this.f3503m, this.f3502ks);
            LongArray longArrayMultiply = longArray2.multiply(longArray3, this.f3503m, this.f3502ks);
            if (longArraySquare == longArray) {
                longArraySquare = (LongArray) longArraySquare.clone();
            }
            longArraySquare.addShiftedByWords(longArrayMultiply, 0);
            longArraySquare.reduce(this.f3503m, this.f3502ks);
            return new F2m(this.f3503m, this.f3502ks, longArraySquare);
        }

        @Override
        public ECFieldElement squarePow(int i) {
            if (i < 1) {
                return this;
            }
            int i2 = this.f3503m;
            int[] iArr = this.f3502ks;
            return new F2m(i2, iArr, this.f3504x.modSquareN(i, i2, iArr));
        }

        @Override
        public ECFieldElement subtract(ECFieldElement eCFieldElement) {
            return add(eCFieldElement);
        }

        @Override
        public boolean testBitZero() {
            return this.f3504x.testBitZero();
        }

        @Override
        public BigInteger toBigInteger() {
            return this.f3504x.toBigInteger();
        }
    }

    public static class C3161Fp extends AbstractFp {

        BigInteger f3505q;

        BigInteger f3506r;

        BigInteger f3507x;

        public C3161Fp(BigInteger bigInteger, BigInteger bigInteger2) {
            this(bigInteger, calculateResidue(bigInteger), bigInteger2);
        }

        C3161Fp(BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3) {
            if (bigInteger3 == null || bigInteger3.signum() < 0 || bigInteger3.compareTo(bigInteger) >= 0) {
                throw new IllegalArgumentException("x value invalid in Fp field element");
            }
            this.f3505q = bigInteger;
            this.f3506r = bigInteger2;
            this.f3507x = bigInteger3;
        }

        static BigInteger calculateResidue(BigInteger bigInteger) {
            int iBitLength = bigInteger.bitLength();
            if (iBitLength < 96 || bigInteger.shiftRight(iBitLength - 64).longValue() != -1) {
                return null;
            }
            return ONE.shiftLeft(iBitLength).subtract(bigInteger);
        }

        private ECFieldElement checkSqrt(ECFieldElement eCFieldElement) {
            if (eCFieldElement.square().equals(this)) {
                return eCFieldElement;
            }
            return null;
        }

        private BigInteger[] lucasSequence(BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3) {
            int iBitLength = bigInteger3.bitLength();
            int lowestSetBit = bigInteger3.getLowestSetBit();
            BigInteger bigIntegerModReduce = ECConstants.ONE;
            BigInteger bigIntegerModReduce2 = ECConstants.TWO;
            BigInteger bigIntegerModMult = ECConstants.ONE;
            BigInteger bigIntegerModMult2 = ECConstants.ONE;
            BigInteger bigIntegerModReduce3 = bigInteger;
            for (int i = iBitLength - 1; i >= lowestSetBit + 1; i--) {
                bigIntegerModMult = modMult(bigIntegerModMult, bigIntegerModMult2);
                if (bigInteger3.testBit(i)) {
                    bigIntegerModMult2 = modMult(bigIntegerModMult, bigInteger2);
                    bigIntegerModReduce = modMult(bigIntegerModReduce, bigIntegerModReduce3);
                    bigIntegerModReduce2 = modReduce(bigIntegerModReduce3.multiply(bigIntegerModReduce2).subtract(bigInteger.multiply(bigIntegerModMult)));
                    bigIntegerModReduce3 = modReduce(bigIntegerModReduce3.multiply(bigIntegerModReduce3).subtract(bigIntegerModMult2.shiftLeft(1)));
                } else {
                    bigIntegerModReduce = modReduce(bigIntegerModReduce.multiply(bigIntegerModReduce2).subtract(bigIntegerModMult));
                    BigInteger bigIntegerModReduce4 = modReduce(bigIntegerModReduce3.multiply(bigIntegerModReduce2).subtract(bigInteger.multiply(bigIntegerModMult)));
                    bigIntegerModReduce2 = modReduce(bigIntegerModReduce2.multiply(bigIntegerModReduce2).subtract(bigIntegerModMult.shiftLeft(1)));
                    bigIntegerModReduce3 = bigIntegerModReduce4;
                    bigIntegerModMult2 = bigIntegerModMult;
                }
            }
            BigInteger bigIntegerModMult3 = modMult(bigIntegerModMult, bigIntegerModMult2);
            BigInteger bigIntegerModMult4 = modMult(bigIntegerModMult3, bigInteger2);
            BigInteger bigIntegerModReduce5 = modReduce(bigIntegerModReduce.multiply(bigIntegerModReduce2).subtract(bigIntegerModMult3));
            BigInteger bigIntegerModReduce6 = modReduce(bigIntegerModReduce3.multiply(bigIntegerModReduce2).subtract(bigInteger.multiply(bigIntegerModMult3)));
            BigInteger bigIntegerModMult5 = modMult(bigIntegerModMult3, bigIntegerModMult4);
            for (int i2 = 1; i2 <= lowestSetBit; i2++) {
                bigIntegerModReduce5 = modMult(bigIntegerModReduce5, bigIntegerModReduce6);
                bigIntegerModReduce6 = modReduce(bigIntegerModReduce6.multiply(bigIntegerModReduce6).subtract(bigIntegerModMult5.shiftLeft(1)));
                bigIntegerModMult5 = modMult(bigIntegerModMult5, bigIntegerModMult5);
            }
            return new BigInteger[]{bigIntegerModReduce5, bigIntegerModReduce6};
        }

        @Override
        public ECFieldElement add(ECFieldElement eCFieldElement) {
            return new C3161Fp(this.f3505q, this.f3506r, modAdd(this.f3507x, eCFieldElement.toBigInteger()));
        }

        @Override
        public ECFieldElement addOne() {
            BigInteger bigIntegerAdd = this.f3507x.add(ECConstants.ONE);
            if (bigIntegerAdd.compareTo(this.f3505q) == 0) {
                bigIntegerAdd = ECConstants.ZERO;
            }
            return new C3161Fp(this.f3505q, this.f3506r, bigIntegerAdd);
        }

        @Override
        public ECFieldElement divide(ECFieldElement eCFieldElement) {
            return new C3161Fp(this.f3505q, this.f3506r, modMult(this.f3507x, modInverse(eCFieldElement.toBigInteger())));
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof C3161Fp)) {
                return false;
            }
            C3161Fp c3161Fp = (C3161Fp) obj;
            return this.f3505q.equals(c3161Fp.f3505q) && this.f3507x.equals(c3161Fp.f3507x);
        }

        @Override
        public String getFieldName() {
            return "Fp";
        }

        @Override
        public int getFieldSize() {
            return this.f3505q.bitLength();
        }

        public BigInteger getQ() {
            return this.f3505q;
        }

        public int hashCode() {
            return this.f3505q.hashCode() ^ this.f3507x.hashCode();
        }

        @Override
        public ECFieldElement invert() {
            return new C3161Fp(this.f3505q, this.f3506r, modInverse(this.f3507x));
        }

        protected BigInteger modAdd(BigInteger bigInteger, BigInteger bigInteger2) {
            BigInteger bigIntegerAdd = bigInteger.add(bigInteger2);
            return bigIntegerAdd.compareTo(this.f3505q) >= 0 ? bigIntegerAdd.subtract(this.f3505q) : bigIntegerAdd;
        }

        protected BigInteger modDouble(BigInteger bigInteger) {
            BigInteger bigIntegerShiftLeft = bigInteger.shiftLeft(1);
            return bigIntegerShiftLeft.compareTo(this.f3505q) >= 0 ? bigIntegerShiftLeft.subtract(this.f3505q) : bigIntegerShiftLeft;
        }

        protected BigInteger modHalf(BigInteger bigInteger) {
            if (bigInteger.testBit(0)) {
                bigInteger = this.f3505q.add(bigInteger);
            }
            return bigInteger.shiftRight(1);
        }

        protected BigInteger modHalfAbs(BigInteger bigInteger) {
            if (bigInteger.testBit(0)) {
                bigInteger = this.f3505q.subtract(bigInteger);
            }
            return bigInteger.shiftRight(1);
        }

        protected BigInteger modInverse(BigInteger bigInteger) {
            return BigIntegers.modOddInverse(this.f3505q, bigInteger);
        }

        protected BigInteger modMult(BigInteger bigInteger, BigInteger bigInteger2) {
            return modReduce(bigInteger.multiply(bigInteger2));
        }

        protected BigInteger modReduce(BigInteger bigInteger) {
            if (this.f3506r == null) {
                return bigInteger.mod(this.f3505q);
            }
            boolean z = bigInteger.signum() < 0;
            if (z) {
                bigInteger = bigInteger.abs();
            }
            int iBitLength = this.f3505q.bitLength();
            boolean zEquals = this.f3506r.equals(ECConstants.ONE);
            while (bigInteger.bitLength() > iBitLength + 1) {
                BigInteger bigIntegerShiftRight = bigInteger.shiftRight(iBitLength);
                BigInteger bigIntegerSubtract = bigInteger.subtract(bigIntegerShiftRight.shiftLeft(iBitLength));
                if (!zEquals) {
                    bigIntegerShiftRight = bigIntegerShiftRight.multiply(this.f3506r);
                }
                bigInteger = bigIntegerShiftRight.add(bigIntegerSubtract);
            }
            while (bigInteger.compareTo(this.f3505q) >= 0) {
                bigInteger = bigInteger.subtract(this.f3505q);
            }
            return (!z || bigInteger.signum() == 0) ? bigInteger : this.f3505q.subtract(bigInteger);
        }

        protected BigInteger modSubtract(BigInteger bigInteger, BigInteger bigInteger2) {
            BigInteger bigIntegerSubtract = bigInteger.subtract(bigInteger2);
            return bigIntegerSubtract.signum() < 0 ? bigIntegerSubtract.add(this.f3505q) : bigIntegerSubtract;
        }

        @Override
        public ECFieldElement multiply(ECFieldElement eCFieldElement) {
            return new C3161Fp(this.f3505q, this.f3506r, modMult(this.f3507x, eCFieldElement.toBigInteger()));
        }

        @Override
        public ECFieldElement multiplyMinusProduct(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2, ECFieldElement eCFieldElement3) {
            BigInteger bigInteger = this.f3507x;
            BigInteger bigInteger2 = eCFieldElement.toBigInteger();
            BigInteger bigInteger3 = eCFieldElement2.toBigInteger();
            BigInteger bigInteger4 = eCFieldElement3.toBigInteger();
            return new C3161Fp(this.f3505q, this.f3506r, modReduce(bigInteger.multiply(bigInteger2).subtract(bigInteger3.multiply(bigInteger4))));
        }

        @Override
        public ECFieldElement multiplyPlusProduct(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2, ECFieldElement eCFieldElement3) {
            BigInteger bigInteger = this.f3507x;
            BigInteger bigInteger2 = eCFieldElement.toBigInteger();
            BigInteger bigInteger3 = eCFieldElement2.toBigInteger();
            BigInteger bigInteger4 = eCFieldElement3.toBigInteger();
            return new C3161Fp(this.f3505q, this.f3506r, modReduce(bigInteger.multiply(bigInteger2).add(bigInteger3.multiply(bigInteger4))));
        }

        @Override
        public ECFieldElement negate() {
            if (this.f3507x.signum() == 0) {
                return this;
            }
            BigInteger bigInteger = this.f3505q;
            return new C3161Fp(bigInteger, this.f3506r, bigInteger.subtract(this.f3507x));
        }

        @Override
        public ECFieldElement sqrt() {
            if (isZero() || isOne()) {
                return this;
            }
            if (!this.f3505q.testBit(0)) {
                throw new RuntimeException("not done yet");
            }
            if (this.f3505q.testBit(1)) {
                BigInteger bigIntegerAdd = this.f3505q.shiftRight(2).add(ECConstants.ONE);
                BigInteger bigInteger = this.f3505q;
                return checkSqrt(new C3161Fp(bigInteger, this.f3506r, this.f3507x.modPow(bigIntegerAdd, bigInteger)));
            }
            if (this.f3505q.testBit(2)) {
                BigInteger bigIntegerModPow = this.f3507x.modPow(this.f3505q.shiftRight(3), this.f3505q);
                BigInteger bigIntegerModMult = modMult(bigIntegerModPow, this.f3507x);
                if (modMult(bigIntegerModMult, bigIntegerModPow).equals(ECConstants.ONE)) {
                    return checkSqrt(new C3161Fp(this.f3505q, this.f3506r, bigIntegerModMult));
                }
                return checkSqrt(new C3161Fp(this.f3505q, this.f3506r, modMult(bigIntegerModMult, ECConstants.TWO.modPow(this.f3505q.shiftRight(2), this.f3505q))));
            }
            BigInteger bigIntegerShiftRight = this.f3505q.shiftRight(1);
            if (!this.f3507x.modPow(bigIntegerShiftRight, this.f3505q).equals(ECConstants.ONE)) {
                return null;
            }
            BigInteger bigInteger2 = this.f3507x;
            BigInteger bigIntegerModDouble = modDouble(modDouble(bigInteger2));
            BigInteger bigIntegerAdd2 = bigIntegerShiftRight.add(ECConstants.ONE);
            BigInteger bigIntegerSubtract = this.f3505q.subtract(ECConstants.ONE);
            Random random = new Random();
            while (true) {
                BigInteger bigInteger3 = new BigInteger(this.f3505q.bitLength(), random);
                if (bigInteger3.compareTo(this.f3505q) < 0 && modReduce(bigInteger3.multiply(bigInteger3).subtract(bigIntegerModDouble)).modPow(bigIntegerShiftRight, this.f3505q).equals(bigIntegerSubtract)) {
                    BigInteger[] bigIntegerArrLucasSequence = lucasSequence(bigInteger3, bigInteger2, bigIntegerAdd2);
                    BigInteger bigInteger4 = bigIntegerArrLucasSequence[0];
                    BigInteger bigInteger5 = bigIntegerArrLucasSequence[1];
                    if (modMult(bigInteger5, bigInteger5).equals(bigIntegerModDouble)) {
                        return new C3161Fp(this.f3505q, this.f3506r, modHalfAbs(bigInteger5));
                    }
                    if (!bigInteger4.equals(ECConstants.ONE) && !bigInteger4.equals(bigIntegerSubtract)) {
                        return null;
                    }
                }
            }
        }

        @Override
        public ECFieldElement square() {
            BigInteger bigInteger = this.f3505q;
            BigInteger bigInteger2 = this.f3506r;
            BigInteger bigInteger3 = this.f3507x;
            return new C3161Fp(bigInteger, bigInteger2, modMult(bigInteger3, bigInteger3));
        }

        @Override
        public ECFieldElement squareMinusProduct(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2) {
            BigInteger bigInteger = this.f3507x;
            BigInteger bigInteger2 = eCFieldElement.toBigInteger();
            BigInteger bigInteger3 = eCFieldElement2.toBigInteger();
            return new C3161Fp(this.f3505q, this.f3506r, modReduce(bigInteger.multiply(bigInteger).subtract(bigInteger2.multiply(bigInteger3))));
        }

        @Override
        public ECFieldElement squarePlusProduct(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2) {
            BigInteger bigInteger = this.f3507x;
            BigInteger bigInteger2 = eCFieldElement.toBigInteger();
            BigInteger bigInteger3 = eCFieldElement2.toBigInteger();
            return new C3161Fp(this.f3505q, this.f3506r, modReduce(bigInteger.multiply(bigInteger).add(bigInteger2.multiply(bigInteger3))));
        }

        @Override
        public ECFieldElement subtract(ECFieldElement eCFieldElement) {
            return new C3161Fp(this.f3505q, this.f3506r, modSubtract(this.f3507x, eCFieldElement.toBigInteger()));
        }

        @Override
        public BigInteger toBigInteger() {
            return this.f3507x;
        }
    }

    public abstract ECFieldElement add(ECFieldElement eCFieldElement);

    public abstract ECFieldElement addOne();

    public int bitLength() {
        return toBigInteger().bitLength();
    }

    public abstract ECFieldElement divide(ECFieldElement eCFieldElement);

    public byte[] getEncoded() {
        return BigIntegers.asUnsignedByteArray((getFieldSize() + 7) / 8, toBigInteger());
    }

    public abstract String getFieldName();

    public abstract int getFieldSize();

    public abstract ECFieldElement invert();

    public boolean isOne() {
        return bitLength() == 1;
    }

    public boolean isZero() {
        return toBigInteger().signum() == 0;
    }

    public abstract ECFieldElement multiply(ECFieldElement eCFieldElement);

    public ECFieldElement multiplyMinusProduct(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2, ECFieldElement eCFieldElement3) {
        return multiply(eCFieldElement).subtract(eCFieldElement2.multiply(eCFieldElement3));
    }

    public ECFieldElement multiplyPlusProduct(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2, ECFieldElement eCFieldElement3) {
        return multiply(eCFieldElement).add(eCFieldElement2.multiply(eCFieldElement3));
    }

    public abstract ECFieldElement negate();

    public abstract ECFieldElement sqrt();

    public abstract ECFieldElement square();

    public ECFieldElement squareMinusProduct(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2) {
        return square().subtract(eCFieldElement.multiply(eCFieldElement2));
    }

    public ECFieldElement squarePlusProduct(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2) {
        return square().add(eCFieldElement.multiply(eCFieldElement2));
    }

    public ECFieldElement squarePow(int i) {
        ECFieldElement eCFieldElementSquare = this;
        for (int i2 = 0; i2 < i; i2++) {
            eCFieldElementSquare = eCFieldElementSquare.square();
        }
        return eCFieldElementSquare;
    }

    public abstract ECFieldElement subtract(ECFieldElement eCFieldElement);

    public boolean testBitZero() {
        return toBigInteger().testBit(0);
    }

    public abstract BigInteger toBigInteger();

    public String toString() {
        return toBigInteger().toString(16);
    }
}
