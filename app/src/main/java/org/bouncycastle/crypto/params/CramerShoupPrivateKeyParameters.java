package org.bouncycastle.crypto.params;

import java.math.BigInteger;

public class CramerShoupPrivateKeyParameters extends CramerShoupKeyParameters {

    private CramerShoupPublicKeyParameters f3334pk;

    private BigInteger f3335x1;

    private BigInteger f3336x2;

    private BigInteger f3337y1;

    private BigInteger f3338y2;

    private BigInteger f3339z;

    public CramerShoupPrivateKeyParameters(CramerShoupParameters cramerShoupParameters, BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3, BigInteger bigInteger4, BigInteger bigInteger5) {
        super(true, cramerShoupParameters);
        this.f3335x1 = bigInteger;
        this.f3336x2 = bigInteger2;
        this.f3337y1 = bigInteger3;
        this.f3338y2 = bigInteger4;
        this.f3339z = bigInteger5;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof CramerShoupPrivateKeyParameters)) {
            return false;
        }
        CramerShoupPrivateKeyParameters cramerShoupPrivateKeyParameters = (CramerShoupPrivateKeyParameters) obj;
        return cramerShoupPrivateKeyParameters.getX1().equals(this.f3335x1) && cramerShoupPrivateKeyParameters.getX2().equals(this.f3336x2) && cramerShoupPrivateKeyParameters.getY1().equals(this.f3337y1) && cramerShoupPrivateKeyParameters.getY2().equals(this.f3338y2) && cramerShoupPrivateKeyParameters.getZ().equals(this.f3339z) && super.equals(obj);
    }

    public CramerShoupPublicKeyParameters getPk() {
        return this.f3334pk;
    }

    public BigInteger getX1() {
        return this.f3335x1;
    }

    public BigInteger getX2() {
        return this.f3336x2;
    }

    public BigInteger getY1() {
        return this.f3337y1;
    }

    public BigInteger getY2() {
        return this.f3338y2;
    }

    public BigInteger getZ() {
        return this.f3339z;
    }

    @Override
    public int hashCode() {
        return ((((this.f3335x1.hashCode() ^ this.f3336x2.hashCode()) ^ this.f3337y1.hashCode()) ^ this.f3338y2.hashCode()) ^ this.f3339z.hashCode()) ^ super.hashCode();
    }

    public void setPk(CramerShoupPublicKeyParameters cramerShoupPublicKeyParameters) {
        this.f3334pk = cramerShoupPublicKeyParameters;
    }
}
