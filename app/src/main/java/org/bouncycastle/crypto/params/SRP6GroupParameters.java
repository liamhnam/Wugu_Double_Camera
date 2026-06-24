package org.bouncycastle.crypto.params;

import java.math.BigInteger;

public class SRP6GroupParameters {

    private BigInteger f3393N;

    private BigInteger f3394g;

    public SRP6GroupParameters(BigInteger bigInteger, BigInteger bigInteger2) {
        this.f3393N = bigInteger;
        this.f3394g = bigInteger2;
    }

    public BigInteger getG() {
        return this.f3394g;
    }

    public BigInteger getN() {
        return this.f3393N;
    }
}
