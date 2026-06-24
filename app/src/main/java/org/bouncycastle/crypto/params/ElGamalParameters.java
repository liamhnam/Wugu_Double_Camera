package org.bouncycastle.crypto.params;

import java.math.BigInteger;
import org.bouncycastle.crypto.CipherParameters;

public class ElGamalParameters implements CipherParameters {

    private BigInteger f3363g;

    private int f3364l;

    private BigInteger f3365p;

    public ElGamalParameters(BigInteger bigInteger, BigInteger bigInteger2) {
        this(bigInteger, bigInteger2, 0);
    }

    public ElGamalParameters(BigInteger bigInteger, BigInteger bigInteger2, int i) {
        this.f3363g = bigInteger2;
        this.f3365p = bigInteger;
        this.f3364l = i;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof ElGamalParameters)) {
            return false;
        }
        ElGamalParameters elGamalParameters = (ElGamalParameters) obj;
        return elGamalParameters.getP().equals(this.f3365p) && elGamalParameters.getG().equals(this.f3363g) && elGamalParameters.getL() == this.f3364l;
    }

    public BigInteger getG() {
        return this.f3363g;
    }

    public int getL() {
        return this.f3364l;
    }

    public BigInteger getP() {
        return this.f3365p;
    }

    public int hashCode() {
        return (getP().hashCode() ^ getG().hashCode()) + this.f3364l;
    }
}
