package org.bouncycastle.crypto.params;

import java.math.BigInteger;

public class RSAPrivateCrtKeyParameters extends RSAKeyParameters {

    private BigInteger f3388dP;

    private BigInteger f3389dQ;

    private BigInteger f3390e;

    private BigInteger f3391p;

    private BigInteger f3392q;
    private BigInteger qInv;

    public RSAPrivateCrtKeyParameters(BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3, BigInteger bigInteger4, BigInteger bigInteger5, BigInteger bigInteger6, BigInteger bigInteger7, BigInteger bigInteger8) {
        super(true, bigInteger, bigInteger3);
        this.f3390e = bigInteger2;
        this.f3391p = bigInteger4;
        this.f3392q = bigInteger5;
        this.f3388dP = bigInteger6;
        this.f3389dQ = bigInteger7;
        this.qInv = bigInteger8;
    }

    public BigInteger getDP() {
        return this.f3388dP;
    }

    public BigInteger getDQ() {
        return this.f3389dQ;
    }

    public BigInteger getP() {
        return this.f3391p;
    }

    public BigInteger getPublicExponent() {
        return this.f3390e;
    }

    public BigInteger getQ() {
        return this.f3392q;
    }

    public BigInteger getQInv() {
        return this.qInv;
    }
}
