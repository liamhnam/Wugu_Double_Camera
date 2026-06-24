package org.bouncycastle.crypto.modes.gcm;

public class BasicGCMMultiplier implements GCMMultiplier {

    private long[] f3316H;

    @Override
    public void init(byte[] bArr) {
        this.f3316H = GCMUtil.asLongs(bArr);
    }

    @Override
    public void multiplyH(byte[] bArr) {
        long[] jArrAsLongs = GCMUtil.asLongs(bArr);
        GCMUtil.multiply(jArrAsLongs, this.f3316H);
        GCMUtil.asBytes(jArrAsLongs, bArr);
    }
}
