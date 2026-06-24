package org.bouncycastle.crypto.macs;

import com.faceunity.wrapper.faceunity;
import org.bouncycastle.asn1.cmc.BodyPartID;
import org.bouncycastle.crypto.BlockCipher;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.Mac;
import org.bouncycastle.crypto.OutputLengthException;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.crypto.params.ParametersWithIV;
import org.bouncycastle.util.Pack;

public class Poly1305 implements Mac {
    private static final int BLOCK_SIZE = 16;
    private final BlockCipher cipher;
    private final byte[] currentBlock;
    private int currentBlockOffset;

    private int f3246h0;

    private int f3247h1;

    private int f3248h2;

    private int f3249h3;

    private int f3250h4;

    private int f3251k0;

    private int f3252k1;

    private int f3253k2;

    private int f3254k3;

    private int f3255r0;

    private int f3256r1;

    private int f3257r2;

    private int f3258r3;

    private int f3259r4;

    private int f3260s1;

    private int f3261s2;

    private int f3262s3;

    private int f3263s4;
    private final byte[] singleByte;

    public Poly1305() {
        this.singleByte = new byte[1];
        this.currentBlock = new byte[16];
        this.currentBlockOffset = 0;
        this.cipher = null;
    }

    public Poly1305(BlockCipher blockCipher) {
        this.singleByte = new byte[1];
        this.currentBlock = new byte[16];
        this.currentBlockOffset = 0;
        if (blockCipher.getBlockSize() != 16) {
            throw new IllegalArgumentException("Poly1305 requires a 128 bit block cipher.");
        }
        this.cipher = blockCipher;
    }

    private static final long mul32x32_64(int i, int i2) {
        return (((long) i) & BodyPartID.bodyIdMax) * ((long) i2);
    }

    private void processBlock() {
        int i = this.currentBlockOffset;
        if (i < 16) {
            this.currentBlock[i] = 1;
            for (int i2 = i + 1; i2 < 16; i2++) {
                this.currentBlock[i2] = 0;
            }
        }
        long jLittleEndianToInt = ((long) Pack.littleEndianToInt(this.currentBlock, 0)) & BodyPartID.bodyIdMax;
        long jLittleEndianToInt2 = ((long) Pack.littleEndianToInt(this.currentBlock, 4)) & BodyPartID.bodyIdMax;
        long jLittleEndianToInt3 = ((long) Pack.littleEndianToInt(this.currentBlock, 8)) & BodyPartID.bodyIdMax;
        long jLittleEndianToInt4 = BodyPartID.bodyIdMax & ((long) Pack.littleEndianToInt(this.currentBlock, 12));
        int i3 = (int) (((long) this.f3246h0) + (jLittleEndianToInt & 67108863));
        this.f3246h0 = i3;
        this.f3247h1 = (int) (((long) this.f3247h1) + ((((jLittleEndianToInt2 << 32) | jLittleEndianToInt) >>> 26) & 67108863));
        this.f3248h2 = (int) (((long) this.f3248h2) + (((jLittleEndianToInt2 | (jLittleEndianToInt3 << 32)) >>> 20) & 67108863));
        this.f3249h3 = (int) (((long) this.f3249h3) + ((((jLittleEndianToInt4 << 32) | jLittleEndianToInt3) >>> 14) & 67108863));
        int i4 = (int) (((long) this.f3250h4) + (jLittleEndianToInt4 >>> 8));
        this.f3250h4 = i4;
        if (this.currentBlockOffset == 16) {
            this.f3250h4 = i4 + 16777216;
        }
        long jMul32x32_64 = mul32x32_64(i3, this.f3255r0) + mul32x32_64(this.f3247h1, this.f3263s4) + mul32x32_64(this.f3248h2, this.f3262s3) + mul32x32_64(this.f3249h3, this.f3261s2) + mul32x32_64(this.f3250h4, this.f3260s1);
        long jMul32x32_642 = mul32x32_64(this.f3246h0, this.f3256r1) + mul32x32_64(this.f3247h1, this.f3255r0) + mul32x32_64(this.f3248h2, this.f3263s4) + mul32x32_64(this.f3249h3, this.f3262s3) + mul32x32_64(this.f3250h4, this.f3261s2);
        long jMul32x32_643 = mul32x32_64(this.f3246h0, this.f3257r2) + mul32x32_64(this.f3247h1, this.f3256r1) + mul32x32_64(this.f3248h2, this.f3255r0) + mul32x32_64(this.f3249h3, this.f3263s4) + mul32x32_64(this.f3250h4, this.f3262s3);
        long jMul32x32_644 = mul32x32_64(this.f3246h0, this.f3258r3) + mul32x32_64(this.f3247h1, this.f3257r2) + mul32x32_64(this.f3248h2, this.f3256r1) + mul32x32_64(this.f3249h3, this.f3255r0) + mul32x32_64(this.f3250h4, this.f3263s4);
        long jMul32x32_645 = mul32x32_64(this.f3246h0, this.f3259r4) + mul32x32_64(this.f3247h1, this.f3258r3) + mul32x32_64(this.f3248h2, this.f3257r2) + mul32x32_64(this.f3249h3, this.f3256r1) + mul32x32_64(this.f3250h4, this.f3255r0);
        long j = jMul32x32_642 + (jMul32x32_64 >>> 26);
        long j2 = jMul32x32_643 + (j >>> 26);
        this.f3248h2 = ((int) j2) & 67108863;
        long j3 = jMul32x32_644 + (j2 >>> 26);
        this.f3249h3 = ((int) j3) & 67108863;
        long j4 = jMul32x32_645 + (j3 >>> 26);
        this.f3250h4 = ((int) j4) & 67108863;
        int i5 = (((int) jMul32x32_64) & 67108863) + (((int) (j4 >>> 26)) * 5);
        this.f3247h1 = (((int) j) & 67108863) + (i5 >>> 26);
        this.f3246h0 = i5 & 67108863;
    }

    private void setKey(byte[] bArr, byte[] bArr2) {
        if (bArr.length != 32) {
            throw new IllegalArgumentException("Poly1305 key must be 256 bits.");
        }
        int i = 16;
        if (this.cipher != null && (bArr2 == null || bArr2.length != 16)) {
            throw new IllegalArgumentException("Poly1305 requires a 128 bit IV.");
        }
        int iLittleEndianToInt = Pack.littleEndianToInt(bArr, 0);
        int iLittleEndianToInt2 = Pack.littleEndianToInt(bArr, 4);
        int iLittleEndianToInt3 = Pack.littleEndianToInt(bArr, 8);
        int iLittleEndianToInt4 = Pack.littleEndianToInt(bArr, 12);
        this.f3255r0 = 67108863 & iLittleEndianToInt;
        int i2 = ((iLittleEndianToInt >>> 26) | (iLittleEndianToInt2 << 6)) & 67108611;
        this.f3256r1 = i2;
        int i3 = ((iLittleEndianToInt2 >>> 20) | (iLittleEndianToInt3 << 12)) & 67092735;
        this.f3257r2 = i3;
        int i4 = ((iLittleEndianToInt3 >>> 14) | (iLittleEndianToInt4 << 18)) & 66076671;
        this.f3258r3 = i4;
        int i5 = (iLittleEndianToInt4 >>> 8) & 1048575;
        this.f3259r4 = i5;
        this.f3260s1 = i2 * 5;
        this.f3261s2 = i3 * 5;
        this.f3262s3 = i4 * 5;
        this.f3263s4 = i5 * 5;
        BlockCipher blockCipher = this.cipher;
        if (blockCipher != null) {
            byte[] bArr3 = new byte[16];
            blockCipher.init(true, new KeyParameter(bArr, 16, 16));
            this.cipher.processBlock(bArr2, 0, bArr3, 0);
            i = 0;
            bArr = bArr3;
        }
        this.f3251k0 = Pack.littleEndianToInt(bArr, i + 0);
        this.f3252k1 = Pack.littleEndianToInt(bArr, i + 4);
        this.f3253k2 = Pack.littleEndianToInt(bArr, i + 8);
        this.f3254k3 = Pack.littleEndianToInt(bArr, i + 12);
    }

    @Override
    public int doFinal(byte[] bArr, int i) throws IllegalStateException, DataLengthException {
        if (i + 16 > bArr.length) {
            throw new OutputLengthException("Output buffer is too short.");
        }
        if (this.currentBlockOffset > 0) {
            processBlock();
        }
        int i2 = this.f3247h1;
        int i3 = this.f3246h0;
        int i4 = i2 + (i3 >>> 26);
        int i5 = this.f3248h2 + (i4 >>> 26);
        int i6 = this.f3249h3 + (i5 >>> 26);
        int i7 = i5 & 67108863;
        int i8 = this.f3250h4 + (i6 >>> 26);
        int i9 = i6 & 67108863;
        int i10 = (i3 & 67108863) + ((i8 >>> 26) * 5);
        int i11 = i8 & 67108863;
        int i12 = (i4 & 67108863) + (i10 >>> 26);
        int i13 = i10 & 67108863;
        int i14 = i13 + 5;
        int i15 = (i14 >>> 26) + i12;
        int i16 = (i15 >>> 26) + i7;
        int i17 = (i16 >>> 26) + i9;
        int i18 = 67108863 & i17;
        int i19 = ((i17 >>> 26) + i11) - faceunity.FUAITYPE_FACEPROCESSOR_DISNEYGAN;
        int i20 = (i19 >>> 31) - 1;
        int i21 = ~i20;
        int i22 = (i13 & i21) | (i14 & 67108863 & i20);
        this.f3246h0 = i22;
        int i23 = (i12 & i21) | (i15 & 67108863 & i20);
        this.f3247h1 = i23;
        int i24 = (i7 & i21) | (i16 & 67108863 & i20);
        this.f3248h2 = i24;
        int i25 = (i18 & i20) | (i9 & i21);
        this.f3249h3 = i25;
        int i26 = (i11 & i21) | (i19 & i20);
        this.f3250h4 = i26;
        long j = (((long) (i22 | (i23 << 26))) & BodyPartID.bodyIdMax) + (((long) this.f3251k0) & BodyPartID.bodyIdMax);
        long j2 = (((long) ((i23 >>> 6) | (i24 << 20))) & BodyPartID.bodyIdMax) + (((long) this.f3252k1) & BodyPartID.bodyIdMax);
        long j3 = (((long) ((i24 >>> 12) | (i25 << 14))) & BodyPartID.bodyIdMax) + (((long) this.f3253k2) & BodyPartID.bodyIdMax);
        long j4 = (((long) ((i25 >>> 18) | (i26 << 8))) & BodyPartID.bodyIdMax) + (BodyPartID.bodyIdMax & ((long) this.f3254k3));
        Pack.intToLittleEndian((int) j, bArr, i);
        long j5 = j2 + (j >>> 32);
        Pack.intToLittleEndian((int) j5, bArr, i + 4);
        long j6 = j3 + (j5 >>> 32);
        Pack.intToLittleEndian((int) j6, bArr, i + 8);
        Pack.intToLittleEndian((int) (j4 + (j6 >>> 32)), bArr, i + 12);
        reset();
        return 16;
    }

    @Override
    public String getAlgorithmName() {
        return this.cipher == null ? "Poly1305" : "Poly1305-" + this.cipher.getAlgorithmName();
    }

    @Override
    public int getMacSize() {
        return 16;
    }

    @Override
    public void init(CipherParameters cipherParameters) throws IllegalArgumentException {
        byte[] iv;
        if (this.cipher == null) {
            iv = null;
        } else {
            if (!(cipherParameters instanceof ParametersWithIV)) {
                throw new IllegalArgumentException("Poly1305 requires an IV when used with a block cipher.");
            }
            ParametersWithIV parametersWithIV = (ParametersWithIV) cipherParameters;
            iv = parametersWithIV.getIV();
            cipherParameters = parametersWithIV.getParameters();
        }
        if (!(cipherParameters instanceof KeyParameter)) {
            throw new IllegalArgumentException("Poly1305 requires a key.");
        }
        setKey(((KeyParameter) cipherParameters).getKey(), iv);
        reset();
    }

    @Override
    public void reset() {
        this.currentBlockOffset = 0;
        this.f3250h4 = 0;
        this.f3249h3 = 0;
        this.f3248h2 = 0;
        this.f3247h1 = 0;
        this.f3246h0 = 0;
    }

    @Override
    public void update(byte b) throws IllegalStateException {
        byte[] bArr = this.singleByte;
        bArr[0] = b;
        update(bArr, 0, 1);
    }

    @Override
    public void update(byte[] bArr, int i, int i2) throws IllegalStateException, DataLengthException {
        int i3 = 0;
        while (i2 > i3) {
            if (this.currentBlockOffset == 16) {
                processBlock();
                this.currentBlockOffset = 0;
            }
            int iMin = Math.min(i2 - i3, 16 - this.currentBlockOffset);
            System.arraycopy(bArr, i3 + i, this.currentBlock, this.currentBlockOffset, iMin);
            i3 += iMin;
            this.currentBlockOffset += iMin;
        }
    }
}
