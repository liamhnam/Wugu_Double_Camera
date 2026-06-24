package org.bouncycastle.crypto.agreement.srp;

import java.math.BigInteger;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.params.SRP6GroupParameters;

public class SRP6VerifierGenerator {

    protected BigInteger f2958N;
    protected Digest digest;

    protected BigInteger f2959g;

    public BigInteger generateVerifier(byte[] bArr, byte[] bArr2, byte[] bArr3) {
        return this.f2959g.modPow(SRP6Util.calculateX(this.digest, this.f2958N, bArr, bArr2, bArr3), this.f2958N);
    }

    public void init(BigInteger bigInteger, BigInteger bigInteger2, Digest digest) {
        this.f2958N = bigInteger;
        this.f2959g = bigInteger2;
        this.digest = digest;
    }

    public void init(SRP6GroupParameters sRP6GroupParameters, Digest digest) {
        this.f2958N = sRP6GroupParameters.getN();
        this.f2959g = sRP6GroupParameters.getG();
        this.digest = digest;
    }
}
