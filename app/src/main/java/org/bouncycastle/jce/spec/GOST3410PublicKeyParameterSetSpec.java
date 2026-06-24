package org.bouncycastle.jce.spec;

import java.math.BigInteger;

public class GOST3410PublicKeyParameterSetSpec {

    private BigInteger f3486a;

    private BigInteger f3487p;

    private BigInteger f3488q;

    public GOST3410PublicKeyParameterSetSpec(BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3) {
        this.f3487p = bigInteger;
        this.f3488q = bigInteger2;
        this.f3486a = bigInteger3;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof GOST3410PublicKeyParameterSetSpec)) {
            return false;
        }
        GOST3410PublicKeyParameterSetSpec gOST3410PublicKeyParameterSetSpec = (GOST3410PublicKeyParameterSetSpec) obj;
        return this.f3486a.equals(gOST3410PublicKeyParameterSetSpec.f3486a) && this.f3487p.equals(gOST3410PublicKeyParameterSetSpec.f3487p) && this.f3488q.equals(gOST3410PublicKeyParameterSetSpec.f3488q);
    }

    public BigInteger getA() {
        return this.f3486a;
    }

    public BigInteger getP() {
        return this.f3487p;
    }

    public BigInteger getQ() {
        return this.f3488q;
    }

    public int hashCode() {
        return (this.f3486a.hashCode() ^ this.f3487p.hashCode()) ^ this.f3488q.hashCode();
    }
}
