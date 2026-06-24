package org.bouncycastle.crypto.params;

import java.math.BigInteger;

public class ElGamalPrivateKeyParameters extends ElGamalKeyParameters {

    private BigInteger f3366x;

    public ElGamalPrivateKeyParameters(BigInteger bigInteger, ElGamalParameters elGamalParameters) {
        super(true, elGamalParameters);
        this.f3366x = bigInteger;
    }

    @Override
    public boolean equals(Object obj) {
        if ((obj instanceof ElGamalPrivateKeyParameters) && ((ElGamalPrivateKeyParameters) obj).getX().equals(this.f3366x)) {
            return super.equals(obj);
        }
        return false;
    }

    public BigInteger getX() {
        return this.f3366x;
    }

    @Override
    public int hashCode() {
        return getX().hashCode();
    }
}
