package org.bouncycastle.crypto.params;

import java.math.BigInteger;

public class DHPrivateKeyParameters extends DHKeyParameters {

    private BigInteger f3349x;

    public DHPrivateKeyParameters(BigInteger bigInteger, DHParameters dHParameters) {
        super(true, dHParameters);
        this.f3349x = bigInteger;
    }

    @Override
    public boolean equals(Object obj) {
        return (obj instanceof DHPrivateKeyParameters) && ((DHPrivateKeyParameters) obj).getX().equals(this.f3349x) && super.equals(obj);
    }

    public BigInteger getX() {
        return this.f3349x;
    }

    @Override
    public int hashCode() {
        return this.f3349x.hashCode() ^ super.hashCode();
    }
}
