package org.bouncycastle.crypto.digests;

import org.bouncycastle.pqc.jcajce.spec.McElieceCCA2KeyGenParameterSpec;
import org.bouncycastle.util.Memoable;
import org.bouncycastle.util.Pack;

public class SHA384Digest extends LongDigest {
    private static final int DIGEST_LENGTH = 48;

    public SHA384Digest() {
    }

    public SHA384Digest(SHA384Digest sHA384Digest) {
        super(sHA384Digest);
    }

    public SHA384Digest(byte[] bArr) {
        restoreState(bArr);
    }

    @Override
    public Memoable copy() {
        return new SHA384Digest(this);
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
        reset();
        return 48;
    }

    @Override
    public String getAlgorithmName() {
        return McElieceCCA2KeyGenParameterSpec.SHA384;
    }

    @Override
    public int getDigestSize() {
        return 48;
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
        this.f2997H1 = -3766243637369397544L;
        this.f2998H2 = 7105036623409894663L;
        this.f2999H3 = -7973340178411365097L;
        this.f3000H4 = 1526699215303891257L;
        this.f3001H5 = 7436329637833083697L;
        this.f3002H6 = -8163818279084223215L;
        this.f3003H7 = -2662702644619276377L;
        this.f3004H8 = 5167115440072839076L;
    }

    @Override
    public void reset(Memoable memoable) {
        super.copyIn((SHA384Digest) memoable);
    }
}
