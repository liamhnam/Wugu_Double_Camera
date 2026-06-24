package org.bouncycastle.jce.spec;

import java.math.BigInteger;
import java.security.spec.AlgorithmParameterSpec;

public class ElGamalParameterSpec implements AlgorithmParameterSpec {

    private BigInteger f3478g;

    private BigInteger f3479p;

    public ElGamalParameterSpec(BigInteger bigInteger, BigInteger bigInteger2) {
        this.f3479p = bigInteger;
        this.f3478g = bigInteger2;
    }

    public BigInteger getG() {
        return this.f3478g;
    }

    public BigInteger getP() {
        return this.f3479p;
    }
}
