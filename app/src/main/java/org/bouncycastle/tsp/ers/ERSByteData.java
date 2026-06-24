package org.bouncycastle.tsp.ers;

import org.bouncycastle.operator.DigestCalculator;

public class ERSByteData extends ERSCachingData {
    private final byte[] content;

    public ERSByteData(byte[] bArr) {
        this.content = bArr;
    }

    @Override
    protected byte[] calculateHash(DigestCalculator digestCalculator) {
        return ERSUtil.calculateDigest(digestCalculator, this.content);
    }
}
