package org.bouncycastle.crypto.params;

import java.math.BigInteger;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.util.Memoable;

public class CramerShoupParameters implements CipherParameters {

    private Digest f3330H;

    private BigInteger f3331g1;

    private BigInteger f3332g2;

    private BigInteger f3333p;

    public CramerShoupParameters(BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3, Digest digest) {
        this.f3333p = bigInteger;
        this.f3331g1 = bigInteger2;
        this.f3332g2 = bigInteger3;
        Digest digest2 = (Digest) ((Memoable) digest).copy();
        this.f3330H = digest2;
        digest2.reset();
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof CramerShoupParameters)) {
            return false;
        }
        CramerShoupParameters cramerShoupParameters = (CramerShoupParameters) obj;
        return cramerShoupParameters.getP().equals(this.f3333p) && cramerShoupParameters.getG1().equals(this.f3331g1) && cramerShoupParameters.getG2().equals(this.f3332g2);
    }

    public BigInteger getG1() {
        return this.f3331g1;
    }

    public BigInteger getG2() {
        return this.f3332g2;
    }

    public Digest getH() {
        return (Digest) ((Memoable) this.f3330H).copy();
    }

    public BigInteger getP() {
        return this.f3333p;
    }

    public int hashCode() {
        return (getP().hashCode() ^ getG1().hashCode()) ^ getG2().hashCode();
    }
}
