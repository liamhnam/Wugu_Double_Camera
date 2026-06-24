package org.bouncycastle.crypto.digests;

import org.bouncycastle.crypto.ExtendedDigest;

public class NonMemoableDigest implements ExtendedDigest {
    private ExtendedDigest baseDigest;

    public NonMemoableDigest(ExtendedDigest extendedDigest) {
        if (extendedDigest == null) {
            throw new IllegalArgumentException("baseDigest must not be null");
        }
        this.baseDigest = extendedDigest;
    }

    @Override
    public int doFinal(byte[] bArr, int i) {
        return this.baseDigest.doFinal(bArr, i);
    }

    @Override
    public String getAlgorithmName() {
        return this.baseDigest.getAlgorithmName();
    }

    @Override
    public int getByteLength() {
        return this.baseDigest.getByteLength();
    }

    @Override
    public int getDigestSize() {
        return this.baseDigest.getDigestSize();
    }

    @Override
    public void reset() {
        this.baseDigest.reset();
    }

    @Override
    public void update(byte b) {
        this.baseDigest.update(b);
    }

    @Override
    public void update(byte[] bArr, int i, int i2) {
        this.baseDigest.update(bArr, i, i2);
    }
}
