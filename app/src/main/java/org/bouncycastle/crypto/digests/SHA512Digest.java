package org.bouncycastle.crypto.digests;

import org.bouncycastle.util.Memoable;
import org.bouncycastle.util.Pack;

public class SHA512Digest extends LongDigest {
    private static final int DIGEST_LENGTH = 64;

    public SHA512Digest() {
    }

    public SHA512Digest(SHA512Digest sHA512Digest) {
        super(sHA512Digest);
    }

    public SHA512Digest(byte[] bArr) {
        restoreState(bArr);
    }

    @Override
    public Memoable copy() {
        return new SHA512Digest(this);
    }

    @Override
    public int doFinal(byte[] bArr, int i) {
        finish();
        Pack.longToBigEndian(this.f2997H1, bArr, i);
        Pack.longToBigEndian(this.f2998H2, bArr, i + 8);
        Pack.longToBigEndian(this.f2999H3, bArr, i + 16);
        Pack.longToBigEndian(this.f3000H4, bArr, i + 24);
        Pack.longToBigEndian(this.f3001H5, bArr, i + 32);
        Pack.longToBigEndian(this.f3002H6, bArr, i + 40);
        Pack.longToBigEndian(this.f3003H7, bArr, i + 48);
        Pack.longToBigEndian(this.f3004H8, bArr, i + 56);
        reset();
        return 64;
    }

    @Override
    public String getAlgorithmName() {
        return "SHA-512";
    }

    @Override
    public int getDigestSize() {
        return 64;
    }

    @Override
    public byte[] getEncodedState() {
        byte[] bArr = new byte[getEncodedStateSize()];
        super.populateState(bArr);
        return bArr;
    }

    @Override
    public void reset() {
        super.reset();
        this.f2997H1 = 7640891576956012808L;
        this.f2998H2 = -4942790177534073029L;
        this.f2999H3 = 4354685564936845355L;
        this.f3000H4 = -6534734903238641935L;
        this.f3001H5 = 5840696475078001361L;
        this.f3002H6 = -7276294671716946913L;
        this.f3003H7 = 2270897969802886507L;
        this.f3004H8 = 6620516959819538809L;
    }

    @Override
    public void reset(Memoable memoable) {
        copyIn((SHA512Digest) memoable);
    }
}
