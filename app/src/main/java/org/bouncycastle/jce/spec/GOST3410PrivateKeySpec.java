package org.bouncycastle.jce.spec;

import java.math.BigInteger;
import java.security.spec.KeySpec;

public class GOST3410PrivateKeySpec implements KeySpec {

    private BigInteger f3482a;

    private BigInteger f3483p;

    private BigInteger f3484q;

    private BigInteger f3485x;

    public GOST3410PrivateKeySpec(BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3, BigInteger bigInteger4) {
        this.f3485x = bigInteger;
        this.f3483p = bigInteger2;
        this.f3484q = bigInteger3;
        this.f3482a = bigInteger4;
    }

    public BigInteger getA() {
        return this.f3482a;
    }

    public BigInteger getP() {
        return this.f3483p;
    }

    public BigInteger getQ() {
        return this.f3484q;
    }

    public BigInteger getX() {
        return this.f3485x;
    }
}
