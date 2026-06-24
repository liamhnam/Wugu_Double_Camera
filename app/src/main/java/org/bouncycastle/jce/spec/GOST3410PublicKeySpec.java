package org.bouncycastle.jce.spec;

import java.math.BigInteger;
import java.security.spec.KeySpec;

public class GOST3410PublicKeySpec implements KeySpec {

    private BigInteger f3489a;

    private BigInteger f3490p;

    private BigInteger f3491q;

    private BigInteger f3492y;

    public GOST3410PublicKeySpec(BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3, BigInteger bigInteger4) {
        this.f3492y = bigInteger;
        this.f3490p = bigInteger2;
        this.f3491q = bigInteger3;
        this.f3489a = bigInteger4;
    }

    public BigInteger getA() {
        return this.f3489a;
    }

    public BigInteger getP() {
        return this.f3490p;
    }

    public BigInteger getQ() {
        return this.f3491q;
    }

    public BigInteger getY() {
        return this.f3492y;
    }
}
