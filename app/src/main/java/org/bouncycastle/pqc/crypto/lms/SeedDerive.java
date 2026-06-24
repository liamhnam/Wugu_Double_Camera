package org.bouncycastle.pqc.crypto.lms;

import org.bouncycastle.crypto.Digest;

class SeedDerive {

    private final byte[] f3684I;
    private final Digest digest;

    private int f3685j;
    private final byte[] masterSeed;

    private int f3686q;

    public SeedDerive(byte[] bArr, byte[] bArr2, Digest digest) {
        this.f3684I = bArr;
        this.masterSeed = bArr2;
        this.digest = digest;
    }

    public void deriveSeed(byte[] bArr, boolean z) {
        deriveSeed(bArr, z, 0);
    }

    public void deriveSeed(byte[] bArr, boolean z, int i) {
        deriveSeed(bArr, i);
        if (z) {
            this.f3685j++;
        }
    }

    public byte[] deriveSeed(byte[] bArr, int i) {
        if (bArr.length < this.digest.getDigestSize()) {
            throw new IllegalArgumentException("target length is less than digest size.");
        }
        Digest digest = this.digest;
        byte[] bArr2 = this.f3684I;
        digest.update(bArr2, 0, bArr2.length);
        this.digest.update((byte) (this.f3686q >>> 24));
        this.digest.update((byte) (this.f3686q >>> 16));
        this.digest.update((byte) (this.f3686q >>> 8));
        this.digest.update((byte) this.f3686q);
        this.digest.update((byte) (this.f3685j >>> 8));
        this.digest.update((byte) this.f3685j);
        this.digest.update((byte) -1);
        Digest digest2 = this.digest;
        byte[] bArr3 = this.masterSeed;
        digest2.update(bArr3, 0, bArr3.length);
        this.digest.doFinal(bArr, i);
        return bArr;
    }

    public byte[] getI() {
        return this.f3684I;
    }

    public int getJ() {
        return this.f3685j;
    }

    public byte[] getMasterSeed() {
        return this.masterSeed;
    }

    public int getQ() {
        return this.f3686q;
    }

    public void setJ(int i) {
        this.f3685j = i;
    }

    public void setQ(int i) {
        this.f3686q = i;
    }
}
