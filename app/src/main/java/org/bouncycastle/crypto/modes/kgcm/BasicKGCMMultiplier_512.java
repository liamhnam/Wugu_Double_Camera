package org.bouncycastle.crypto.modes.kgcm;

public class BasicKGCMMultiplier_512 implements KGCMMultiplier {

    private final long[] f3326H = new long[8];

    @Override
    public void init(long[] jArr) {
        KGCMUtil_512.copy(jArr, this.f3326H);
    }

    @Override
    public void multiplyH(long[] jArr) {
        KGCMUtil_512.multiply(jArr, this.f3326H, jArr);
    }
}
