package org.bouncycastle.crypto.digests;

import com.brother.sdk.common.socket.devicemanagement.snmp.westhawk.SnmpConstants;
import java.util.Iterator;
import java.util.Stack;
import org.bouncycastle.crypto.ExtendedDigest;
import org.bouncycastle.crypto.Xof;
import org.bouncycastle.crypto.params.Blake3Parameters;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.Integers;
import org.bouncycastle.util.Memoable;
import org.bouncycastle.util.Pack;

public class Blake3Digest implements ExtendedDigest, Memoable, Xof {
    private static final int BLOCKLEN = 64;
    private static final int CHAINING0 = 0;
    private static final int CHAINING1 = 1;
    private static final int CHAINING2 = 2;
    private static final int CHAINING3 = 3;
    private static final int CHAINING4 = 4;
    private static final int CHAINING5 = 5;
    private static final int CHAINING6 = 6;
    private static final int CHAINING7 = 7;
    private static final int CHUNKEND = 2;
    private static final int CHUNKLEN = 1024;
    private static final int CHUNKSTART = 1;
    private static final int COUNT0 = 12;
    private static final int COUNT1 = 13;
    private static final int DATALEN = 14;
    private static final int DERIVECONTEXT = 32;
    private static final int DERIVEKEY = 64;
    private static final String ERR_OUTPUTTING = "Already outputting";
    private static final int FLAGS = 15;
    private static final int IV0 = 8;
    private static final int IV1 = 9;
    private static final int IV2 = 10;
    private static final int IV3 = 11;
    private static final int KEYEDHASH = 16;
    private static final int NUMWORDS = 8;
    private static final int PARENT = 4;
    private static final int ROOT = 8;
    private static final int ROUNDS = 7;
    private boolean outputting;
    private final byte[] theBuffer;
    private final int[] theChaining;
    private long theCounter;
    private int theCurrBytes;
    private final int theDigestLen;
    private final byte[] theIndices;
    private final int[] theK;
    private final int[] theM;
    private int theMode;
    private int theOutputDataLen;
    private int theOutputMode;
    private int thePos;
    private final Stack theStack;
    private final int[] theV;
    private static final byte[] SIGMA = {2, 6, 3, 10, 7, 0, 4, 13, 1, 11, 12, 5, 9, 14, SnmpConstants.SNMP_ERR_UNDOFAILED, 8};
    private static final byte[] ROTATE = {16, 12, 8, 7};

    private static final int[] f2967IV = {1779033703, -1150833019, 1013904242, -1521486534, 1359893119, -1694144372, 528734635, 1541459225};

    public Blake3Digest() {
        this(32);
    }

    public Blake3Digest(int i) {
        this.theBuffer = new byte[64];
        this.theK = new int[8];
        this.theChaining = new int[8];
        this.theV = new int[16];
        this.theM = new int[16];
        this.theIndices = new byte[16];
        this.theStack = new Stack();
        this.theDigestLen = i;
        init(null);
    }

    private Blake3Digest(Blake3Digest blake3Digest) {
        this.theBuffer = new byte[64];
        this.theK = new int[8];
        this.theChaining = new int[8];
        this.theV = new int[16];
        this.theM = new int[16];
        this.theIndices = new byte[16];
        this.theStack = new Stack();
        this.theDigestLen = blake3Digest.theDigestLen;
        reset(blake3Digest);
    }

    private void adjustChaining() {
        if (!this.outputting) {
            for (int i = 0; i < 8; i++) {
                int[] iArr = this.theChaining;
                int[] iArr2 = this.theV;
                iArr[i] = iArr2[i + 8] ^ iArr2[i];
            }
            return;
        }
        for (int i2 = 0; i2 < 8; i2++) {
            int[] iArr3 = this.theV;
            int i3 = i2 + 8;
            iArr3[i2] = iArr3[i2] ^ iArr3[i3];
            iArr3[i3] = iArr3[i3] ^ this.theChaining[i2];
        }
        for (int i4 = 0; i4 < 16; i4++) {
            Pack.intToLittleEndian(this.theV[i4], this.theBuffer, i4 * 4);
        }
        this.thePos = 0;
    }

    private void adjustStack() {
        for (long j = this.theCounter; j > 0 && (j & 1) != 1; j >>= 1) {
            System.arraycopy((int[]) this.theStack.pop(), 0, this.theM, 0, 8);
            System.arraycopy(this.theChaining, 0, this.theM, 8, 8);
            initParentBlock();
            compress();
        }
        this.theStack.push(Arrays.copyOf(this.theChaining, 8));
    }

    private void compress() {
        initIndices();
        int i = 0;
        while (true) {
            performRound();
            if (i >= 6) {
                adjustChaining();
                return;
            } else {
                permuteIndices();
                i++;
            }
        }
    }

    private void compressBlock(byte[] bArr, int i) {
        initChunkBlock(64, false);
        initM(bArr, i);
        compress();
        if (this.theCurrBytes == 0) {
            adjustStack();
        }
    }

    private void compressFinalBlock(int i) {
        initChunkBlock(i, true);
        initM(this.theBuffer, 0);
        compress();
        processStack();
    }

    private void incrementBlockCount() {
        this.theCounter++;
        this.theCurrBytes = 0;
    }

    private void initChunkBlock(int i, boolean z) {
        System.arraycopy(this.theCurrBytes == 0 ? this.theK : this.theChaining, 0, this.theV, 0, 8);
        System.arraycopy(f2967IV, 0, this.theV, 8, 4);
        int[] iArr = this.theV;
        long j = this.theCounter;
        iArr[12] = (int) j;
        iArr[13] = (int) (j >> 32);
        iArr[14] = i;
        int i2 = this.theMode;
        int i3 = this.theCurrBytes;
        iArr[15] = i2 + (i3 == 0 ? 1 : 0) + (z ? 2 : 0);
        int i4 = i3 + i;
        this.theCurrBytes = i4;
        if (i4 >= 1024) {
            incrementBlockCount();
            int[] iArr2 = this.theV;
            iArr2[15] = iArr2[15] | 2;
        }
        if (z && this.theStack.isEmpty()) {
            setRoot();
        }
    }

    private void initIndices() {
        byte b = 0;
        while (true) {
            byte[] bArr = this.theIndices;
            if (b >= bArr.length) {
                return;
            }
            bArr[b] = b;
            b = (byte) (b + 1);
        }
    }

    private void initKey(byte[] bArr) {
        for (int i = 0; i < 8; i++) {
            this.theK[i] = Pack.littleEndianToInt(bArr, i * 4);
        }
        this.theMode = 16;
    }

    private void initKeyFromContext() {
        System.arraycopy(this.theV, 0, this.theK, 0, 8);
        this.theMode = 64;
    }

    private void initM(byte[] bArr, int i) {
        for (int i2 = 0; i2 < 16; i2++) {
            this.theM[i2] = Pack.littleEndianToInt(bArr, (i2 * 4) + i);
        }
    }

    private void initNullKey() {
        System.arraycopy(f2967IV, 0, this.theK, 0, 8);
    }

    private void initParentBlock() {
        System.arraycopy(this.theK, 0, this.theV, 0, 8);
        System.arraycopy(f2967IV, 0, this.theV, 8, 4);
        int[] iArr = this.theV;
        iArr[12] = 0;
        iArr[13] = 0;
        iArr[14] = 64;
        iArr[15] = this.theMode | 4;
    }

    private void mixG(int i, int i2, int i3, int i4, int i5) {
        int i6 = i << 1;
        int[] iArr = this.theV;
        int i7 = i6 + 1;
        int i8 = iArr[i2] + iArr[i3] + this.theM[this.theIndices[i6]];
        iArr[i2] = i8;
        int i9 = iArr[i5] ^ i8;
        byte[] bArr = ROTATE;
        iArr[i5] = Integers.rotateRight(i9, bArr[0]);
        int[] iArr2 = this.theV;
        int i10 = iArr2[i4] + iArr2[i5];
        iArr2[i4] = i10;
        iArr2[i3] = Integers.rotateRight(i10 ^ iArr2[i3], bArr[1]);
        int[] iArr3 = this.theV;
        int i11 = iArr3[i2] + iArr3[i3] + this.theM[this.theIndices[i7]];
        iArr3[i2] = i11;
        iArr3[i5] = Integers.rotateRight(iArr3[i5] ^ i11, bArr[2]);
        int[] iArr4 = this.theV;
        int i12 = iArr4[i4] + iArr4[i5];
        iArr4[i4] = i12;
        iArr4[i3] = Integers.rotateRight(i12 ^ iArr4[i3], bArr[3]);
    }

    private void nextOutputBlock() {
        this.theCounter++;
        System.arraycopy(this.theChaining, 0, this.theV, 0, 8);
        System.arraycopy(f2967IV, 0, this.theV, 8, 4);
        int[] iArr = this.theV;
        long j = this.theCounter;
        iArr[12] = (int) j;
        iArr[13] = (int) (j >> 32);
        iArr[14] = this.theOutputDataLen;
        iArr[15] = this.theOutputMode;
        compress();
    }

    private void performRound() {
        mixG(0, 0, 4, 8, 12);
        mixG(1, 1, 5, 9, 13);
        mixG(2, 2, 6, 10, 14);
        mixG(3, 3, 7, 11, 15);
        mixG(4, 0, 5, 10, 15);
        mixG(5, 1, 6, 11, 12);
        mixG(6, 2, 7, 8, 13);
        mixG(7, 3, 4, 9, 14);
    }

    private void permuteIndices() {
        byte b = 0;
        while (true) {
            byte[] bArr = this.theIndices;
            if (b >= bArr.length) {
                return;
            }
            bArr[b] = SIGMA[bArr[b]];
            b = (byte) (b + 1);
        }
    }

    private void processStack() {
        while (!this.theStack.isEmpty()) {
            System.arraycopy((int[]) this.theStack.pop(), 0, this.theM, 0, 8);
            System.arraycopy(this.theChaining, 0, this.theM, 8, 8);
            initParentBlock();
            if (this.theStack.isEmpty()) {
                setRoot();
            }
            compress();
        }
    }

    private void resetBlockCount() {
        this.theCounter = 0L;
        this.theCurrBytes = 0;
    }

    private void setRoot() {
        int[] iArr = this.theV;
        int i = iArr[15] | 8;
        iArr[15] = i;
        this.theOutputMode = i;
        this.theOutputDataLen = iArr[14];
        this.theCounter = 0L;
        this.outputting = true;
        System.arraycopy(iArr, 0, this.theChaining, 0, 8);
    }

    @Override
    public Memoable copy() {
        return new Blake3Digest(this);
    }

    @Override
    public int doFinal(byte[] bArr, int i) {
        return doFinal(bArr, i, getDigestSize());
    }

    @Override
    public int doFinal(byte[] bArr, int i, int i2) {
        if (this.outputting) {
            throw new IllegalStateException(ERR_OUTPUTTING);
        }
        int iDoOutput = doOutput(bArr, i, i2);
        reset();
        return iDoOutput;
    }

    @Override
    public int doOutput(byte[] bArr, int i, int i2) {
        int i3;
        if (!this.outputting) {
            compressFinalBlock(this.thePos);
        }
        int i4 = this.thePos;
        if (i4 < 64) {
            int iMin = Math.min(i2, 64 - i4);
            System.arraycopy(this.theBuffer, this.thePos, bArr, i, iMin);
            this.thePos += iMin;
            i += iMin;
            i3 = i2 - iMin;
        } else {
            i3 = i2;
        }
        while (i3 > 0) {
            nextOutputBlock();
            int iMin2 = Math.min(i3, 64);
            System.arraycopy(this.theBuffer, 0, bArr, i, iMin2);
            this.thePos += iMin2;
            i += iMin2;
            i3 -= iMin2;
        }
        return i2;
    }

    @Override
    public String getAlgorithmName() {
        return "BLAKE3";
    }

    @Override
    public int getByteLength() {
        return 64;
    }

    @Override
    public int getDigestSize() {
        return this.theDigestLen;
    }

    public void init(Blake3Parameters blake3Parameters) {
        byte[] key = blake3Parameters == null ? null : blake3Parameters.getKey();
        byte[] context = blake3Parameters != null ? blake3Parameters.getContext() : null;
        reset();
        if (key != null) {
            initKey(key);
            Arrays.fill(key, (byte) 0);
            return;
        }
        initNullKey();
        if (context == null) {
            this.theMode = 0;
            return;
        }
        this.theMode = 32;
        update(context, 0, context.length);
        doFinal(this.theBuffer, 0);
        initKeyFromContext();
        reset();
    }

    @Override
    public void reset() {
        resetBlockCount();
        this.thePos = 0;
        this.outputting = false;
        Arrays.fill(this.theBuffer, (byte) 0);
    }

    @Override
    public void reset(Memoable memoable) {
        Blake3Digest blake3Digest = (Blake3Digest) memoable;
        this.theCounter = blake3Digest.theCounter;
        this.theCurrBytes = blake3Digest.theCurrBytes;
        this.theMode = blake3Digest.theMode;
        this.outputting = blake3Digest.outputting;
        this.theOutputMode = blake3Digest.theOutputMode;
        this.theOutputDataLen = blake3Digest.theOutputDataLen;
        int[] iArr = blake3Digest.theChaining;
        int[] iArr2 = this.theChaining;
        System.arraycopy(iArr, 0, iArr2, 0, iArr2.length);
        int[] iArr3 = blake3Digest.theK;
        int[] iArr4 = this.theK;
        System.arraycopy(iArr3, 0, iArr4, 0, iArr4.length);
        int[] iArr5 = blake3Digest.theM;
        int[] iArr6 = this.theM;
        System.arraycopy(iArr5, 0, iArr6, 0, iArr6.length);
        this.theStack.clear();
        Iterator it = blake3Digest.theStack.iterator();
        while (it.hasNext()) {
            this.theStack.push(Arrays.clone((int[]) it.next()));
        }
        byte[] bArr = blake3Digest.theBuffer;
        byte[] bArr2 = this.theBuffer;
        System.arraycopy(bArr, 0, bArr2, 0, bArr2.length);
        this.thePos = blake3Digest.thePos;
    }

    @Override
    public void update(byte b) {
        if (this.outputting) {
            throw new IllegalStateException(ERR_OUTPUTTING);
        }
        byte[] bArr = this.theBuffer;
        if (bArr.length - this.thePos == 0) {
            compressBlock(bArr, 0);
            Arrays.fill(this.theBuffer, (byte) 0);
            this.thePos = 0;
        }
        byte[] bArr2 = this.theBuffer;
        int i = this.thePos;
        bArr2[i] = b;
        this.thePos = i + 1;
    }

    @Override
    public void update(byte[] bArr, int i, int i2) {
        int i3;
        int i4;
        if (bArr == null || i2 == 0) {
            return;
        }
        if (this.outputting) {
            throw new IllegalStateException(ERR_OUTPUTTING);
        }
        int i5 = this.thePos;
        if (i5 != 0) {
            i3 = 64 - i5;
            if (i3 >= i2) {
                System.arraycopy(bArr, i, this.theBuffer, i5, i2);
                i4 = this.thePos + i2;
                this.thePos = i4;
            } else {
                System.arraycopy(bArr, i, this.theBuffer, i5, i3);
                compressBlock(this.theBuffer, 0);
                this.thePos = 0;
                Arrays.fill(this.theBuffer, (byte) 0);
            }
        } else {
            i3 = 0;
        }
        int i6 = (i + i2) - 64;
        int i7 = i3 + i;
        while (i7 < i6) {
            compressBlock(bArr, i7);
            i7 += 64;
        }
        int i8 = i + (i2 - i7);
        System.arraycopy(bArr, i7, this.theBuffer, 0, i8);
        i4 = this.thePos + i8;
        this.thePos = i4;
    }
}
