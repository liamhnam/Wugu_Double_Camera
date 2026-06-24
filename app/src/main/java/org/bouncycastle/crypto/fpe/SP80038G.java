package org.bouncycastle.crypto.fpe;

import java.math.BigInteger;
import kotlin.UByte;
import kotlin.UShort;
import org.bouncycastle.crypto.BlockCipher;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.BigIntegers;
import org.bouncycastle.util.Pack;

class SP80038G {
    protected static final int BLOCK_SIZE = 16;
    static final String FF1_DISABLED = "org.bouncycastle.fpe.disable_ff1";
    static final String FPE_DISABLED = "org.bouncycastle.fpe.disable";
    protected static final double LOG2 = Math.log(2.0d);
    protected static final double TWO_TO_96 = Math.pow(2.0d, 96.0d);

    SP80038G() {
    }

    protected static BigInteger[] calculateModUV(BigInteger bigInteger, int i, int i2) {
        BigInteger bigIntegerPow = bigInteger.pow(i);
        BigInteger[] bigIntegerArr = {bigIntegerPow, bigIntegerPow};
        if (i2 != i) {
            bigIntegerArr[1] = bigIntegerPow.multiply(bigInteger);
        }
        return bigIntegerArr;
    }

    protected static byte[] calculateP_FF1(int i, byte b, int i2, int i3) {
        byte[] bArr = {1, 2, 1, 0, (byte) (i >> 8), (byte) i, 10, b, 0, 0, 0, 0, 0, 0, 0, 0};
        Pack.intToBigEndian(i2, bArr, 8);
        Pack.intToBigEndian(i3, bArr, 12);
        return bArr;
    }

    protected static byte[] calculateTweak64_FF3_1(byte[] bArr) {
        return new byte[]{bArr[0], bArr[1], bArr[2], (byte) (bArr[3] & 240), bArr[4], bArr[5], bArr[6], (byte) (bArr[3] << 4)};
    }

    protected static BigInteger calculateY_FF1(BlockCipher blockCipher, BigInteger bigInteger, byte[] bArr, int i, int i2, int i3, byte[] bArr2, short[] sArr) {
        int length = bArr.length;
        byte[] bArrAsUnsignedByteArray = BigIntegers.asUnsignedByteArray(num(bigInteger, sArr));
        int i4 = ((-(length + i + 1)) & 15) + length;
        int i5 = i4 + 1 + i;
        byte[] bArr3 = new byte[i5];
        System.arraycopy(bArr, 0, bArr3, 0, length);
        bArr3[i4] = (byte) i3;
        System.arraycopy(bArrAsUnsignedByteArray, 0, bArr3, i5 - bArrAsUnsignedByteArray.length, bArrAsUnsignedByteArray.length);
        byte[] bArrPrf = prf(blockCipher, Arrays.concatenate(bArr2, bArr3));
        if (i2 > 16) {
            int i6 = ((i2 + 16) - 1) / 16;
            byte[] bArr4 = new byte[i6 * 16];
            System.arraycopy(bArrPrf, 0, bArr4, 0, 16);
            byte[] bArr5 = new byte[4];
            for (int i7 = 1; i7 < i6; i7++) {
                int i8 = i7 * 16;
                System.arraycopy(bArrPrf, 0, bArr4, i8, 16);
                Pack.intToBigEndian(i7, bArr5, 0);
                xor(bArr5, 0, bArr4, (i8 + 16) - 4, 4);
                blockCipher.processBlock(bArr4, i8, bArr4, i8);
            }
            bArrPrf = bArr4;
        }
        return num(bArrPrf, 0, i2);
    }

    protected static BigInteger calculateY_FF3(BlockCipher blockCipher, BigInteger bigInteger, byte[] bArr, int i, int i2, short[] sArr) {
        byte[] bArr2 = new byte[16];
        Pack.intToBigEndian(i2, bArr2, 0);
        xor(bArr, i, bArr2, 0, 4);
        byte[] bArrAsUnsignedByteArray = BigIntegers.asUnsignedByteArray(num(bigInteger, sArr));
        if (16 - bArrAsUnsignedByteArray.length < 4) {
            throw new IllegalStateException("input out of range");
        }
        System.arraycopy(bArrAsUnsignedByteArray, 0, bArr2, 16 - bArrAsUnsignedByteArray.length, bArrAsUnsignedByteArray.length);
        rev(bArr2);
        blockCipher.processBlock(bArr2, 0, bArr2, 0);
        rev(bArr2);
        return num(bArr2, 0, 16);
    }

    protected static void checkArgs(BlockCipher blockCipher, boolean z, int i, byte[] bArr, int i2, int i3) {
        checkCipher(blockCipher);
        if (i < 2 || i > 256) {
            throw new IllegalArgumentException();
        }
        checkData(z, i, bArr, i2, i3);
    }

    protected static void checkArgs(BlockCipher blockCipher, boolean z, int i, short[] sArr, int i2, int i3) {
        checkCipher(blockCipher);
        if (i < 2 || i > 65536) {
            throw new IllegalArgumentException();
        }
        checkData(z, i, sArr, i2, i3);
    }

    protected static void checkCipher(BlockCipher blockCipher) {
        if (16 != blockCipher.getBlockSize()) {
            throw new IllegalArgumentException();
        }
    }

    protected static void checkData(boolean z, int i, byte[] bArr, int i2, int i3) {
        checkLength(z, i, i3);
        for (int i4 = 0; i4 < i3; i4++) {
            if ((bArr[i2 + i4] & UByte.MAX_VALUE) >= i) {
                throw new IllegalArgumentException("input data outside of radix");
            }
        }
    }

    protected static void checkData(boolean z, int i, short[] sArr, int i2, int i3) {
        checkLength(z, i, i3);
        for (int i4 = 0; i4 < i3; i4++) {
            if ((sArr[i2 + i4] & UShort.MAX_VALUE) >= i) {
                throw new IllegalArgumentException("input data outside of radix");
            }
        }
    }

    private static void checkLength(boolean z, int i, int i2) {
        int iFloor;
        if (i2 >= 2) {
            double d = i;
            if (Math.pow(d, i2) >= 1000000.0d) {
                if (!z && i2 > (iFloor = ((int) Math.floor(Math.log(TWO_TO_96) / Math.log(d))) * 2)) {
                    throw new IllegalArgumentException("maximum input length is " + iFloor);
                }
                return;
            }
        }
        throw new IllegalArgumentException("input too short");
    }

    static short[] decFF1(BlockCipher blockCipher, int i, byte[] bArr, int i2, int i3, int i4, short[] sArr, short[] sArr2) {
        int length = bArr.length;
        int iCeil = (((int) Math.ceil((Math.log(i) * ((double) i4)) / LOG2)) + 7) / 8;
        int i5 = (((iCeil + 3) / 4) * 4) + 4;
        byte[] bArrCalculateP_FF1 = calculateP_FF1(i, (byte) i3, i2, length);
        BigInteger bigIntegerValueOf = BigInteger.valueOf(i);
        BigInteger[] bigIntegerArrCalculateModUV = calculateModUV(bigIntegerValueOf, i3, i4);
        short[] sArr3 = sArr;
        short[] sArr4 = sArr2;
        int i6 = i3;
        int i7 = 9;
        while (i7 >= 0) {
            short[] sArr5 = sArr4;
            sArr4 = sArr3;
            i6 = i2 - i6;
            str(bigIntegerValueOf, num(bigIntegerValueOf, sArr5).subtract(calculateY_FF1(blockCipher, bigIntegerValueOf, bArr, iCeil, i5, i7, bArrCalculateP_FF1, sArr4)).mod(bigIntegerArrCalculateModUV[i7 & 1]), i6, sArr5, 0);
            i7--;
            sArr3 = sArr5;
        }
        return Arrays.concatenate(sArr3, sArr4);
    }

    private static short[] decFF3_1(BlockCipher blockCipher, int i, byte[] bArr, int i2, int i3, int i4, short[] sArr, short[] sArr2) {
        BigInteger bigIntegerValueOf = BigInteger.valueOf(i);
        int i5 = i4;
        BigInteger[] bigIntegerArrCalculateModUV = calculateModUV(bigIntegerValueOf, i3, i5);
        rev(sArr);
        rev(sArr2);
        short[] sArr3 = sArr;
        short[] sArr4 = sArr2;
        int i6 = 7;
        while (i6 >= 0) {
            int i7 = i2 - i5;
            int i8 = i6 & 1;
            str(bigIntegerValueOf, num(bigIntegerValueOf, sArr4).subtract(calculateY_FF3(blockCipher, bigIntegerValueOf, bArr, 4 - (i8 * 4), i6, sArr3)).mod(bigIntegerArrCalculateModUV[1 - i8]), i7, sArr4, 0);
            i6--;
            i5 = i7;
            short[] sArr5 = sArr4;
            sArr4 = sArr3;
            sArr3 = sArr5;
        }
        rev(sArr3);
        rev(sArr4);
        return Arrays.concatenate(sArr3, sArr4);
    }

    static byte[] decryptFF1(BlockCipher blockCipher, int i, byte[] bArr, byte[] bArr2, int i2, int i3) {
        checkArgs(blockCipher, true, i, bArr2, i2, i3);
        int i4 = i3 / 2;
        int i5 = i3 - i4;
        return toByte(decFF1(blockCipher, i, bArr, i3, i4, i5, toShort(bArr2, i2, i4), toShort(bArr2, i2 + i4, i5)));
    }

    static short[] decryptFF1w(BlockCipher blockCipher, int i, byte[] bArr, short[] sArr, int i2, int i3) {
        checkArgs(blockCipher, true, i, sArr, i2, i3);
        int i4 = i3 / 2;
        int i5 = i3 - i4;
        short[] sArr2 = new short[i4];
        short[] sArr3 = new short[i5];
        System.arraycopy(sArr, i2, sArr2, 0, i4);
        System.arraycopy(sArr, i2 + i4, sArr3, 0, i5);
        return decFF1(blockCipher, i, bArr, i3, i4, i5, sArr2, sArr3);
    }

    static byte[] decryptFF3(BlockCipher blockCipher, int i, byte[] bArr, byte[] bArr2, int i2, int i3) {
        checkArgs(blockCipher, false, i, bArr2, i2, i3);
        if (bArr.length == 8) {
            return implDecryptFF3(blockCipher, i, bArr, bArr2, i2, i3);
        }
        throw new IllegalArgumentException();
    }

    static byte[] decryptFF3_1(BlockCipher blockCipher, int i, byte[] bArr, byte[] bArr2, int i2, int i3) {
        checkArgs(blockCipher, false, i, bArr2, i2, i3);
        if (bArr.length == 7) {
            return implDecryptFF3(blockCipher, i, calculateTweak64_FF3_1(bArr), bArr2, i2, i3);
        }
        throw new IllegalArgumentException("tweak should be 56 bits");
    }

    static short[] decryptFF3_1w(BlockCipher blockCipher, int i, byte[] bArr, short[] sArr, int i2, int i3) {
        checkArgs(blockCipher, false, i, sArr, i2, i3);
        if (bArr.length == 7) {
            return implDecryptFF3w(blockCipher, i, calculateTweak64_FF3_1(bArr), sArr, i2, i3);
        }
        throw new IllegalArgumentException("tweak should be 56 bits");
    }

    private static short[] encFF1(BlockCipher blockCipher, int i, byte[] bArr, int i2, int i3, int i4, short[] sArr, short[] sArr2) {
        int length = bArr.length;
        int iCeil = (((int) Math.ceil((Math.log(i) * ((double) i4)) / LOG2)) + 7) / 8;
        int i5 = (((iCeil + 3) / 4) * 4) + 4;
        byte[] bArrCalculateP_FF1 = calculateP_FF1(i, (byte) i3, i2, length);
        BigInteger bigIntegerValueOf = BigInteger.valueOf(i);
        BigInteger[] bigIntegerArrCalculateModUV = calculateModUV(bigIntegerValueOf, i3, i4);
        short[] sArr3 = sArr;
        short[] sArr4 = sArr2;
        int i6 = i4;
        int i7 = 0;
        while (i7 < 10) {
            int i8 = i7;
            short[] sArr5 = sArr3;
            sArr3 = sArr4;
            int i9 = i2 - i6;
            str(bigIntegerValueOf, num(bigIntegerValueOf, sArr5).add(calculateY_FF1(blockCipher, bigIntegerValueOf, bArr, iCeil, i5, i7, bArrCalculateP_FF1, sArr3)).mod(bigIntegerArrCalculateModUV[i8 & 1]), i9, sArr5, 0);
            i7 = i8 + 1;
            i6 = i9;
            sArr4 = sArr5;
        }
        return Arrays.concatenate(sArr3, sArr4);
    }

    private static short[] encFF3_1(BlockCipher blockCipher, int i, byte[] bArr, int i2, int i3, int i4, short[] sArr, short[] sArr2) {
        BigInteger bigIntegerValueOf = BigInteger.valueOf(i);
        int i5 = i3;
        BigInteger[] bigIntegerArrCalculateModUV = calculateModUV(bigIntegerValueOf, i5, i4);
        rev(sArr);
        rev(sArr2);
        short[] sArr3 = sArr;
        short[] sArr4 = sArr2;
        int i6 = 0;
        while (i6 < 8) {
            i5 = i2 - i5;
            int i7 = i6 & 1;
            str(bigIntegerValueOf, num(bigIntegerValueOf, sArr3).add(calculateY_FF3(blockCipher, bigIntegerValueOf, bArr, 4 - (i7 * 4), i6, sArr4)).mod(bigIntegerArrCalculateModUV[1 - i7]), i5, sArr3, 0);
            i6++;
            short[] sArr5 = sArr4;
            sArr4 = sArr3;
            sArr3 = sArr5;
        }
        rev(sArr3);
        rev(sArr4);
        return Arrays.concatenate(sArr3, sArr4);
    }

    static byte[] encryptFF1(BlockCipher blockCipher, int i, byte[] bArr, byte[] bArr2, int i2, int i3) {
        checkArgs(blockCipher, true, i, bArr2, i2, i3);
        int i4 = i3 / 2;
        int i5 = i3 - i4;
        return toByte(encFF1(blockCipher, i, bArr, i3, i4, i5, toShort(bArr2, i2, i4), toShort(bArr2, i2 + i4, i5)));
    }

    static short[] encryptFF1w(BlockCipher blockCipher, int i, byte[] bArr, short[] sArr, int i2, int i3) {
        checkArgs(blockCipher, true, i, sArr, i2, i3);
        int i4 = i3 / 2;
        int i5 = i3 - i4;
        short[] sArr2 = new short[i4];
        short[] sArr3 = new short[i5];
        System.arraycopy(sArr, i2, sArr2, 0, i4);
        System.arraycopy(sArr, i2 + i4, sArr3, 0, i5);
        return encFF1(blockCipher, i, bArr, i3, i4, i5, sArr2, sArr3);
    }

    static byte[] encryptFF3(BlockCipher blockCipher, int i, byte[] bArr, byte[] bArr2, int i2, int i3) {
        checkArgs(blockCipher, false, i, bArr2, i2, i3);
        if (bArr.length == 8) {
            return implEncryptFF3(blockCipher, i, bArr, bArr2, i2, i3);
        }
        throw new IllegalArgumentException();
    }

    static byte[] encryptFF3_1(BlockCipher blockCipher, int i, byte[] bArr, byte[] bArr2, int i2, int i3) {
        checkArgs(blockCipher, false, i, bArr2, i2, i3);
        if (bArr.length == 7) {
            return encryptFF3(blockCipher, i, calculateTweak64_FF3_1(bArr), bArr2, i2, i3);
        }
        throw new IllegalArgumentException("tweak should be 56 bits");
    }

    static short[] encryptFF3_1w(BlockCipher blockCipher, int i, byte[] bArr, short[] sArr, int i2, int i3) {
        checkArgs(blockCipher, false, i, sArr, i2, i3);
        if (bArr.length == 7) {
            return encryptFF3w(blockCipher, i, calculateTweak64_FF3_1(bArr), sArr, i2, i3);
        }
        throw new IllegalArgumentException("tweak should be 56 bits");
    }

    static short[] encryptFF3w(BlockCipher blockCipher, int i, byte[] bArr, short[] sArr, int i2, int i3) {
        checkArgs(blockCipher, false, i, sArr, i2, i3);
        if (bArr.length == 8) {
            return implEncryptFF3w(blockCipher, i, bArr, sArr, i2, i3);
        }
        throw new IllegalArgumentException();
    }

    protected static byte[] implDecryptFF3(BlockCipher blockCipher, int i, byte[] bArr, byte[] bArr2, int i2, int i3) {
        int i4 = i3 / 2;
        int i5 = i3 - i4;
        return toByte(decFF3_1(blockCipher, i, bArr, i3, i4, i5, toShort(bArr2, i2, i5), toShort(bArr2, i2 + i5, i4)));
    }

    protected static short[] implDecryptFF3w(BlockCipher blockCipher, int i, byte[] bArr, short[] sArr, int i2, int i3) {
        int i4 = i3 / 2;
        int i5 = i3 - i4;
        short[] sArr2 = new short[i5];
        short[] sArr3 = new short[i4];
        System.arraycopy(sArr, i2, sArr2, 0, i5);
        System.arraycopy(sArr, i2 + i5, sArr3, 0, i4);
        return decFF3_1(blockCipher, i, bArr, i3, i4, i5, sArr2, sArr3);
    }

    protected static byte[] implEncryptFF3(BlockCipher blockCipher, int i, byte[] bArr, byte[] bArr2, int i2, int i3) {
        int i4 = i3 / 2;
        int i5 = i3 - i4;
        return toByte(encFF3_1(blockCipher, i, bArr, i3, i4, i5, toShort(bArr2, i2, i5), toShort(bArr2, i2 + i5, i4)));
    }

    protected static short[] implEncryptFF3w(BlockCipher blockCipher, int i, byte[] bArr, short[] sArr, int i2, int i3) {
        int i4 = i3 / 2;
        int i5 = i3 - i4;
        short[] sArr2 = new short[i5];
        short[] sArr3 = new short[i4];
        System.arraycopy(sArr, i2, sArr2, 0, i5);
        System.arraycopy(sArr, i2 + i5, sArr3, 0, i4);
        return encFF3_1(blockCipher, i, bArr, i3, i4, i5, sArr2, sArr3);
    }

    protected static BigInteger num(BigInteger bigInteger, short[] sArr) {
        BigInteger bigIntegerAdd = BigIntegers.ZERO;
        for (short s : sArr) {
            bigIntegerAdd = bigIntegerAdd.multiply(bigInteger).add(BigInteger.valueOf(s & UShort.MAX_VALUE));
        }
        return bigIntegerAdd;
    }

    protected static BigInteger num(byte[] bArr, int i, int i2) {
        return new BigInteger(1, Arrays.copyOfRange(bArr, i, i2 + i));
    }

    protected static byte[] prf(BlockCipher blockCipher, byte[] bArr) {
        if (bArr.length % 16 != 0) {
            throw new IllegalArgumentException();
        }
        int length = bArr.length / 16;
        byte[] bArr2 = new byte[16];
        for (int i = 0; i < length; i++) {
            xor(bArr, i * 16, bArr2, 0, 16);
            blockCipher.processBlock(bArr2, 0, bArr2, 0);
        }
        return bArr2;
    }

    protected static void rev(byte[] bArr) {
        int length = bArr.length / 2;
        int length2 = bArr.length - 1;
        for (int i = 0; i < length; i++) {
            byte b = bArr[i];
            int i2 = length2 - i;
            bArr[i] = bArr[i2];
            bArr[i2] = b;
        }
    }

    protected static void rev(short[] sArr) {
        int length = sArr.length / 2;
        int length2 = sArr.length - 1;
        for (int i = 0; i < length; i++) {
            short s = sArr[i];
            int i2 = length2 - i;
            sArr[i] = sArr[i2];
            sArr[i2] = s;
        }
    }

    protected static void str(BigInteger bigInteger, BigInteger bigInteger2, int i, short[] sArr, int i2) {
        if (bigInteger2.signum() < 0) {
            throw new IllegalArgumentException();
        }
        for (int i3 = 1; i3 <= i; i3++) {
            BigInteger[] bigIntegerArrDivideAndRemainder = bigInteger2.divideAndRemainder(bigInteger);
            sArr[(i2 + i) - i3] = (short) bigIntegerArrDivideAndRemainder[1].intValue();
            bigInteger2 = bigIntegerArrDivideAndRemainder[0];
        }
        if (bigInteger2.signum() != 0) {
            throw new IllegalArgumentException();
        }
    }

    private static byte[] toByte(short[] sArr) {
        int length = sArr.length;
        byte[] bArr = new byte[length];
        for (int i = 0; i != length; i++) {
            bArr[i] = (byte) sArr[i];
        }
        return bArr;
    }

    private static short[] toShort(byte[] bArr, int i, int i2) {
        short[] sArr = new short[i2];
        for (int i3 = 0; i3 != i2; i3++) {
            sArr[i3] = (short) (bArr[i + i3] & UByte.MAX_VALUE);
        }
        return sArr;
    }

    protected static void xor(byte[] bArr, int i, byte[] bArr2, int i2, int i3) {
        for (int i4 = 0; i4 < i3; i4++) {
            int i5 = i2 + i4;
            bArr2[i5] = (byte) (bArr2[i5] ^ bArr[i + i4]);
        }
    }
}
