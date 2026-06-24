package org.bouncycastle.crypto.modes.kgcm;

public class BasicKGCMMultiplier_128 implements KGCMMultiplier {

    private final long[] f3324H = new long[2];

    @Override
    public void init(long[] jArr) {
        KGCMUtil_128.copy(jArr, this.f3324H);
    }

    @Override
    public void multiplyH(long[] jArr) {
        KGCMUtil_128.multiply(jArr, this.f3324H, jArr);
    }
}
