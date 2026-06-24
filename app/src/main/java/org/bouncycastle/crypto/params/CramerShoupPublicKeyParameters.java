package org.bouncycastle.crypto.params;

import java.math.BigInteger;

public class CramerShoupPublicKeyParameters extends CramerShoupKeyParameters {

    private BigInteger f3340c;

    private BigInteger f3341d;

    private BigInteger f3342h;

    public CramerShoupPublicKeyParameters(CramerShoupParameters cramerShoupParameters, BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3) {
        super(false, cramerShoupParameters);
        this.f3340c = bigInteger;
        this.f3341d = bigInteger2;
        this.f3342h = bigInteger3;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof CramerShoupPublicKeyParameters)) {
            return false;
        }
        CramerShoupPublicKeyParameters cramerShoupPublicKeyParameters = (CramerShoupPublicKeyParameters) obj;
        return cramerShoupPublicKeyParameters.getC().equals(this.f3340c) && cramerShoupPublicKeyParameters.getD().equals(this.f3341d) && cramerShoupPublicKeyParameters.getH().equals(this.f3342h) && super.equals(obj);
    }

    public BigInteger getC() {
        return this.f3340c;
    }

    public BigInteger getD() {
        return this.f3341d;
    }

    public BigInteger getH() {
        return this.f3342h;
    }

    @Override
    public int hashCode() {
        return ((this.f3340c.hashCode() ^ this.f3341d.hashCode()) ^ this.f3342h.hashCode()) ^ super.hashCode();
    }
}
