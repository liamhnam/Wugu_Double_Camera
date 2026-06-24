package org.bouncycastle.crypto.digests;

import org.bouncycastle.util.Memoable;

public class GOST3411_2012_512Digest extends GOST3411_2012Digest {

    private static final byte[] f2992IV = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};

    public GOST3411_2012_512Digest() {
        super(f2992IV);
    }

    public GOST3411_2012_512Digest(GOST3411_2012_512Digest gOST3411_2012_512Digest) {
        super(f2992IV);
        reset(gOST3411_2012_512Digest);
    }

    @Override
    public Memoable copy() {
        return new GOST3411_2012_512Digest(this);
    }

    @Override
    public String getAlgorithmName() {
        return "GOST3411-2012-512";
    }

    @Override
    public int getDigestSize() {
        return 64;
    }
}
