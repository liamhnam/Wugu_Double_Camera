package org.bouncycastle.jce.spec;

import org.bouncycastle.math.p058ec.ECPoint;

public class ECPublicKeySpec extends ECKeySpec {

    private ECPoint f3477q;

    public ECPublicKeySpec(ECPoint eCPoint, ECParameterSpec eCParameterSpec) {
        super(eCParameterSpec);
        this.f3477q = eCPoint.getCurve() != null ? eCPoint.normalize() : eCPoint;
    }

    public ECPoint getQ() {
        return this.f3477q;
    }
}
