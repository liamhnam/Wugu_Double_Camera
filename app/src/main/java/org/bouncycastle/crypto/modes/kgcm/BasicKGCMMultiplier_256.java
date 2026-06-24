package org.bouncycastle.crypto.modes.kgcm;

public class BasicKGCMMultiplier_256 implements KGCMMultiplier {

    private final long[] f3325H = new long[4];

    @Override
    public void init(long[] jArr) {
        KGCMUtil_256.copy(jArr, this.f3325H);
    }

    @Override
    public void multiplyH(long[] jArr) {
        KGCMUtil_256.multiply(jArr, this.f3325H, jArr);
    }
}
