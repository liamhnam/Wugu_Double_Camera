package org.bouncycastle.crypto.params;

import java.math.BigInteger;
import org.bouncycastle.crypto.CipherParameters;

public class DSAParameters implements CipherParameters {

    private BigInteger f3353g;

    private BigInteger f3354p;

    private BigInteger f3355q;
    private DSAValidationParameters validation;

    public DSAParameters(BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3) {
        this.f3353g = bigInteger3;
        this.f3354p = bigInteger;
        this.f3355q = bigInteger2;
    }

    public DSAParameters(BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3, DSAValidationParameters dSAValidationParameters) {
        this.f3353g = bigInteger3;
        this.f3354p = bigInteger;
        this.f3355q = bigInteger2;
        this.validation = dSAValidationParameters;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof DSAParameters)) {
            return false;
        }
        DSAParameters dSAParameters = (DSAParameters) obj;
        return dSAParameters.getP().equals(this.f3354p) && dSAParameters.getQ().equals(this.f3355q) && dSAParameters.getG().equals(this.f3353g);
    }

    public BigInteger getG() {
        return this.f3353g;
    }

    public BigInteger getP() {
        return this.f3354p;
    }

    public BigInteger getQ() {
        return this.f3355q;
    }

    public DSAValidationParameters getValidationParameters() {
        return this.validation;
    }

    public int hashCode() {
        return (getP().hashCode() ^ getQ().hashCode()) ^ getG().hashCode();
    }
}
