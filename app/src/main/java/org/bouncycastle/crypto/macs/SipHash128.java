package org.bouncycastle.crypto.macs;

import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.util.Pack;

public class SipHash128 extends SipHash {
    public SipHash128() {
    }

    public SipHash128(int i, int i2) {
        super(i, i2);
    }

    @Override
    public int doFinal(byte[] bArr, int i) throws IllegalStateException, DataLengthException {
        this.f3268m >>>= (7 - this.wordPos) << 3;
        this.f3268m >>>= 8;
        this.f3268m |= (((long) ((this.wordCount << 3) + this.wordPos)) & 255) << 56;
        processMessageWord();
        this.f3271v2 ^= 238;
        applySipRounds(this.f3265d);
        long j = ((this.f3269v0 ^ this.f3270v1) ^ this.f3271v2) ^ this.f3272v3;
        this.f3270v1 ^= 221;
        applySipRounds(this.f3265d);
        long j2 = ((this.f3269v0 ^ this.f3270v1) ^ this.f3271v2) ^ this.f3272v3;
        reset();
        Pack.longToLittleEndian(j, bArr, i);
        Pack.longToLittleEndian(j2, bArr, i + 8);
        return 16;
    }

    @Override
    public long doFinal() throws IllegalStateException, DataLengthException {
        throw new UnsupportedOperationException("doFinal() is not supported");
    }

    @Override
    public String getAlgorithmName() {
        return "SipHash128-" + this.f3264c + "-" + this.f3265d;
    }

    @Override
    public int getMacSize() {
        return 16;
    }

    @Override
    public void reset() {
        super.reset();
        this.f3270v1 ^= 238;
    }
}
