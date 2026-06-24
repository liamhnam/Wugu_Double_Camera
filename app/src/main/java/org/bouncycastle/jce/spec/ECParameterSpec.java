package org.bouncycastle.jce.spec;

import java.math.BigInteger;
import java.security.spec.AlgorithmParameterSpec;
import org.bouncycastle.math.p058ec.ECCurve;
import org.bouncycastle.math.p058ec.ECPoint;

public class ECParameterSpec implements AlgorithmParameterSpec {

    private ECPoint f3473G;
    private ECCurve curve;

    private BigInteger f3474h;

    private BigInteger f3475n;
    private byte[] seed;

    public ECParameterSpec(ECCurve eCCurve, ECPoint eCPoint, BigInteger bigInteger) {
        this.curve = eCCurve;
        this.f3473G = eCPoint.normalize();
        this.f3475n = bigInteger;
        this.f3474h = BigInteger.valueOf(1L);
        this.seed = null;
    }

    public ECParameterSpec(ECCurve eCCurve, ECPoint eCPoint, BigInteger bigInteger, BigInteger bigInteger2) {
        this.curve = eCCurve;
        this.f3473G = eCPoint.normalize();
        this.f3475n = bigInteger;
        this.f3474h = bigInteger2;
        this.seed = null;
    }

    public ECParameterSpec(ECCurve eCCurve, ECPoint eCPoint, BigInteger bigInteger, BigInteger bigInteger2, byte[] bArr) {
        this.curve = eCCurve;
        this.f3473G = eCPoint.normalize();
        this.f3475n = bigInteger;
        this.f3474h = bigInteger2;
        this.seed = bArr;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof ECParameterSpec)) {
            return false;
        }
        ECParameterSpec eCParameterSpec = (ECParameterSpec) obj;
        return getCurve().equals(eCParameterSpec.getCurve()) && getG().equals(eCParameterSpec.getG());
    }

    public ECCurve getCurve() {
        return this.curve;
    }

    public ECPoint getG() {
        return this.f3473G;
    }

    public BigInteger getH() {
        return this.f3474h;
    }

    public BigInteger getN() {
        return this.f3475n;
    }

    public byte[] getSeed() {
        return this.seed;
    }

    public int hashCode() {
        return getCurve().hashCode() ^ getG().hashCode();
    }
}
