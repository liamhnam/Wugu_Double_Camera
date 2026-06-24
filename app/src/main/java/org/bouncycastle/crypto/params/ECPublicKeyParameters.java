package org.bouncycastle.crypto.params;

import org.bouncycastle.math.p058ec.ECPoint;

public class ECPublicKeyParameters extends ECKeyParameters {

    private final ECPoint f3362q;

    public ECPublicKeyParameters(ECPoint eCPoint, ECDomainParameters eCDomainParameters) {
        super(false, eCDomainParameters);
        this.f3362q = eCDomainParameters.validatePublicPoint(eCPoint);
    }

    public ECPoint getQ() {
        return this.f3362q;
    }
}
