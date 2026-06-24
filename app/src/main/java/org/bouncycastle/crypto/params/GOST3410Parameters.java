package org.bouncycastle.crypto.params;

import java.math.BigInteger;
import org.bouncycastle.crypto.CipherParameters;

public class GOST3410Parameters implements CipherParameters {

    private BigInteger f3368a;

    private BigInteger f3369p;

    private BigInteger f3370q;
    private GOST3410ValidationParameters validation;

    public GOST3410Parameters(BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3) {
        this.f3369p = bigInteger;
        this.f3370q = bigInteger2;
        this.f3368a = bigInteger3;
    }

    public GOST3410Parameters(BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3, GOST3410ValidationParameters gOST3410ValidationParameters) {
        this.f3368a = bigInteger3;
        this.f3369p = bigInteger;
        this.f3370q = bigInteger2;
        this.validation = gOST3410ValidationParameters;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof GOST3410Parameters)) {
            return false;
        }
        GOST3410Parameters gOST3410Parameters = (GOST3410Parameters) obj;
        return gOST3410Parameters.getP().equals(this.f3369p) && gOST3410Parameters.getQ().equals(this.f3370q) && gOST3410Parameters.getA().equals(this.f3368a);
    }

    public BigInteger getA() {
        return this.f3368a;
    }

    public BigInteger getP() {
        return this.f3369p;
    }

    public BigInteger getQ() {
        return this.f3370q;
    }

    public GOST3410ValidationParameters getValidationParameters() {
        return this.validation;
    }

    public int hashCode() {
        return (this.f3369p.hashCode() ^ this.f3370q.hashCode()) ^ this.f3368a.hashCode();
    }
}
