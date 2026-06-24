package org.bouncycastle.crypto.engines;

import kotlin.UByte;
import org.bouncycastle.crypto.BlockCipher;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.OutputLengthException;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.crypto.signers.PSSSigner;
import org.bouncycastle.util.Integers;
import org.bouncycastle.util.Pack;

public class NoekeonEngine implements BlockCipher {
    private static final int SIZE = 16;
    private static final byte[] roundConstants = {-128, 27, 54, 108, -40, -85, 77, -102, 47, 94, PSSSigner.TRAILER_IMPLICIT, 99, -58, -105, 53, 106, -44};
    private boolean _forEncryption;

    private final int[] f3179k = new int[4];
    private boolean _initialised = false;

    private int decryptBlock(byte[] bArr, int i, byte[] bArr2, int i2) {
        int iBigEndianToInt = Pack.bigEndianToInt(bArr, i);
        int iBigEndianToInt2 = Pack.bigEndianToInt(bArr, i + 4);
        int iBigEndianToInt3 = Pack.bigEndianToInt(bArr, i + 8);
        int iBigEndianToInt4 = Pack.bigEndianToInt(bArr, i + 12);
        int[] iArr = this.f3179k;
        int i3 = iArr[0];
        int i4 = iArr[1];
        int i5 = iArr[2];
        int i6 = iArr[3];
        int i7 = 16;
        while (true) {
            int i8 = iBigEndianToInt ^ iBigEndianToInt3;
            int iRotateLeft = i8 ^ (Integers.rotateLeft(i8, 8) ^ Integers.rotateLeft(i8, 24));
            int i9 = iBigEndianToInt2 ^ i4;
            int i10 = iBigEndianToInt4 ^ i6;
            int i11 = i9 ^ i10;
            int iRotateLeft2 = (Integers.rotateLeft(i11, 24) ^ Integers.rotateLeft(i11, 8)) ^ i11;
            int i12 = i9 ^ iRotateLeft;
            int i13 = (iBigEndianToInt3 ^ i5) ^ iRotateLeft2;
            int i14 = i10 ^ iRotateLeft;
            int i15 = ((iBigEndianToInt ^ i3) ^ iRotateLeft2) ^ (roundConstants[i7] & UByte.MAX_VALUE);
            i7--;
            if (i7 < 0) {
                Pack.intToBigEndian(i15, bArr2, i2);
                Pack.intToBigEndian(i12, bArr2, i2 + 4);
                Pack.intToBigEndian(i13, bArr2, i2 + 8);
                Pack.intToBigEndian(i14, bArr2, i2 + 12);
                return 16;
            }
            int iRotateLeft3 = Integers.rotateLeft(i12, 1);
            int iRotateLeft4 = Integers.rotateLeft(i13, 5);
            int iRotateLeft5 = Integers.rotateLeft(i14, 2);
            int i16 = iRotateLeft3 ^ (iRotateLeft5 | iRotateLeft4);
            int i17 = ~i16;
            int i18 = i15 ^ (iRotateLeft4 & i17);
            int i19 = (iRotateLeft4 ^ (i17 ^ iRotateLeft5)) ^ i18;
            int i20 = i16 ^ (i18 | i19);
            int i21 = iRotateLeft5 ^ (i19 & i20);
            iBigEndianToInt2 = Integers.rotateLeft(i20, 31);
            iBigEndianToInt3 = Integers.rotateLeft(i19, 27);
            int iRotateLeft6 = Integers.rotateLeft(i18, 30);
            iBigEndianToInt = i21;
            iBigEndianToInt4 = iRotateLeft6;
        }
    }

    private int encryptBlock(byte[] bArr, int i, byte[] bArr2, int i2) {
        int iBigEndianToInt = Pack.bigEndianToInt(bArr, i);
        int iBigEndianToInt2 = Pack.bigEndianToInt(bArr, i + 4);
        int iBigEndianToInt3 = Pack.bigEndianToInt(bArr, i + 8);
        int iBigEndianToInt4 = Pack.bigEndianToInt(bArr, i + 12);
        int[] iArr = this.f3179k;
        int i3 = 0;
        int i4 = iArr[0];
        int i5 = iArr[1];
        int i6 = iArr[2];
        int i7 = iArr[3];
        while (true) {
            int i8 = iBigEndianToInt ^ (roundConstants[i3] & UByte.MAX_VALUE);
            int i9 = i8 ^ iBigEndianToInt3;
            int iRotateLeft = i9 ^ (Integers.rotateLeft(i9, 8) ^ Integers.rotateLeft(i9, 24));
            int i10 = iBigEndianToInt2 ^ i5;
            int i11 = iBigEndianToInt4 ^ i7;
            int i12 = i10 ^ i11;
            int iRotateLeft2 = i12 ^ (Integers.rotateLeft(i12, 24) ^ Integers.rotateLeft(i12, 8));
            int i13 = (i8 ^ i4) ^ iRotateLeft2;
            int i14 = i10 ^ iRotateLeft;
            int i15 = (iBigEndianToInt3 ^ i6) ^ iRotateLeft2;
            int i16 = i11 ^ iRotateLeft;
            i3++;
            if (i3 > 16) {
                Pack.intToBigEndian(i13, bArr2, i2);
                Pack.intToBigEndian(i14, bArr2, i2 + 4);
                Pack.intToBigEndian(i15, bArr2, i2 + 8);
                Pack.intToBigEndian(i16, bArr2, i2 + 12);
                return 16;
            }
            int iRotateLeft3 = Integers.rotateLeft(i14, 1);
            int iRotateLeft4 = Integers.rotateLeft(i15, 5);
            int iRotateLeft5 = Integers.rotateLeft(i16, 2);
            int i17 = iRotateLeft3 ^ (iRotateLeft5 | iRotateLeft4);
            int i18 = ~i17;
            int i19 = i13 ^ (iRotateLeft4 & i18);
            int i20 = (iRotateLeft4 ^ (i18 ^ iRotateLeft5)) ^ i19;
            int i21 = i17 ^ (i19 | i20);
            int i22 = iRotateLeft5 ^ (i20 & i21);
            iBigEndianToInt2 = Integers.rotateLeft(i21, 31);
            iBigEndianToInt3 = Integers.rotateLeft(i20, 27);
            int iRotateLeft6 = Integers.rotateLeft(i19, 30);
            iBigEndianToInt = i22;
            iBigEndianToInt4 = iRotateLeft6;
        }
    }

    @Override
    public String getAlgorithmName() {
        return "Noekeon";
    }

    @Override
    public int getBlockSize() {
        return 16;
    }

    @Override
    public void init(boolean z, CipherParameters cipherParameters) {
        if (!(cipherParameters instanceof KeyParameter)) {
            throw new IllegalArgumentException("invalid parameter passed to Noekeon init - " + cipherParameters.getClass().getName());
        }
        byte[] key = ((KeyParameter) cipherParameters).getKey();
        if (key.length != 16) {
            throw new IllegalArgumentException("Key length not 128 bits.");
        }
        Pack.bigEndianToInt(key, 0, this.f3179k, 0, 4);
        if (!z) {
            int[] iArr = this.f3179k;
            int i = iArr[0];
            int i2 = iArr[1];
            int i3 = iArr[2];
            int i4 = iArr[3];
            int i5 = i ^ i3;
            int iRotateLeft = i5 ^ (Integers.rotateLeft(i5, 8) ^ Integers.rotateLeft(i5, 24));
            int i6 = i2 ^ i4;
            int iRotateLeft2 = (Integers.rotateLeft(i6, 8) ^ Integers.rotateLeft(i6, 24)) ^ i6;
            int i7 = i2 ^ iRotateLeft;
            int i8 = i4 ^ iRotateLeft;
            int[] iArr2 = this.f3179k;
            iArr2[0] = i ^ iRotateLeft2;
            iArr2[1] = i7;
            iArr2[2] = i3 ^ iRotateLeft2;
            iArr2[3] = i8;
        }
        this._forEncryption = z;
        this._initialised = true;
    }

    @Override
    public int processBlock(byte[] bArr, int i, byte[] bArr2, int i2) {
        if (!this._initialised) {
            throw new IllegalStateException(getAlgorithmName() + " not initialised");
        }
        if (i > bArr.length - 16) {
            throw new DataLengthException("input buffer too short");
        }
        if (i2 <= bArr2.length - 16) {
            return this._forEncryption ? encryptBlock(bArr, i, bArr2, i2) : decryptBlock(bArr, i, bArr2, i2);
        }
        throw new OutputLengthException("output buffer too short");
    }

    @Override
    public void reset() {
    }
}
