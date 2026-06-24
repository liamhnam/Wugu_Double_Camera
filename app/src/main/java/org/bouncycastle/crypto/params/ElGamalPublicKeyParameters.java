package org.bouncycastle.crypto.params;

import java.math.BigInteger;

public class ElGamalPublicKeyParameters extends ElGamalKeyParameters {

    private BigInteger f3367y;

    public ElGamalPublicKeyParameters(BigInteger bigInteger, ElGamalParameters elGamalParameters) {
        super(false, elGamalParameters);
        this.f3367y = bigInteger;
    }

    @Override
    public boolean equals(Object obj) {
        return (obj instanceof ElGamalPublicKeyParameters) && ((ElGamalPublicKeyParameters) obj).getY().equals(this.f3367y) && super.equals(obj);
    }

    public BigInteger getY() {
        return this.f3367y;
    }

    @Override
    public int hashCode() {
        return this.f3367y.hashCode() ^ super.hashCode();
    }
}
