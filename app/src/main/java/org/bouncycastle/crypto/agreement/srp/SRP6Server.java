package org.bouncycastle.crypto.agreement.srp;

import java.math.BigInteger;
import java.security.SecureRandom;
import org.bouncycastle.crypto.CryptoException;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.params.SRP6GroupParameters;

public class SRP6Server {

    protected BigInteger f2948A;

    protected BigInteger f2949B;
    protected BigInteger Key;

    protected BigInteger f2950M1;

    protected BigInteger f2951M2;

    protected BigInteger f2952N;

    protected BigInteger f2953S;

    protected BigInteger f2954b;
    protected Digest digest;

    protected BigInteger f2955g;
    protected SecureRandom random;

    protected BigInteger f2956u;

    protected BigInteger f2957v;

    private BigInteger calculateS() {
        return this.f2957v.modPow(this.f2956u, this.f2952N).multiply(this.f2948A).mod(this.f2952N).modPow(this.f2954b, this.f2952N);
    }

    public BigInteger calculateSecret(BigInteger bigInteger) throws CryptoException {
        BigInteger bigIntegerValidatePublicValue = SRP6Util.validatePublicValue(this.f2952N, bigInteger);
        this.f2948A = bigIntegerValidatePublicValue;
        this.f2956u = SRP6Util.calculateU(this.digest, this.f2952N, bigIntegerValidatePublicValue, this.f2949B);
        BigInteger bigIntegerCalculateS = calculateS();
        this.f2953S = bigIntegerCalculateS;
        return bigIntegerCalculateS;
    }

    public BigInteger calculateServerEvidenceMessage() throws CryptoException {
        BigInteger bigInteger;
        BigInteger bigInteger2;
        BigInteger bigInteger3 = this.f2948A;
        if (bigInteger3 == null || (bigInteger = this.f2950M1) == null || (bigInteger2 = this.f2953S) == null) {
            throw new CryptoException("Impossible to compute M2: some data are missing from the previous operations (A,M1,S)");
        }
        BigInteger bigIntegerCalculateM2 = SRP6Util.calculateM2(this.digest, this.f2952N, bigInteger3, bigInteger, bigInteger2);
        this.f2951M2 = bigIntegerCalculateM2;
        return bigIntegerCalculateM2;
    }

    public BigInteger calculateSessionKey() throws CryptoException {
        BigInteger bigInteger = this.f2953S;
        if (bigInteger == null || this.f2950M1 == null || this.f2951M2 == null) {
            throw new CryptoException("Impossible to compute Key: some data are missing from the previous operations (S,M1,M2)");
        }
        BigInteger bigIntegerCalculateKey = SRP6Util.calculateKey(this.digest, this.f2952N, bigInteger);
        this.Key = bigIntegerCalculateKey;
        return bigIntegerCalculateKey;
    }

    public BigInteger generateServerCredentials() {
        BigInteger bigIntegerCalculateK = SRP6Util.calculateK(this.digest, this.f2952N, this.f2955g);
        this.f2954b = selectPrivateValue();
        BigInteger bigIntegerMod = bigIntegerCalculateK.multiply(this.f2957v).mod(this.f2952N).add(this.f2955g.modPow(this.f2954b, this.f2952N)).mod(this.f2952N);
        this.f2949B = bigIntegerMod;
        return bigIntegerMod;
    }

    public void init(BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3, Digest digest, SecureRandom secureRandom) {
        this.f2952N = bigInteger;
        this.f2955g = bigInteger2;
        this.f2957v = bigInteger3;
        this.random = secureRandom;
        this.digest = digest;
    }

    public void init(SRP6GroupParameters sRP6GroupParameters, BigInteger bigInteger, Digest digest, SecureRandom secureRandom) {
        init(sRP6GroupParameters.getN(), sRP6GroupParameters.getG(), bigInteger, digest, secureRandom);
    }

    protected BigInteger selectPrivateValue() {
        return SRP6Util.generatePrivateValue(this.digest, this.f2952N, this.f2955g, this.random);
    }

    public boolean verifyClientEvidenceMessage(BigInteger bigInteger) throws CryptoException {
        BigInteger bigInteger2;
        BigInteger bigInteger3;
        BigInteger bigInteger4 = this.f2948A;
        if (bigInteger4 == null || (bigInteger2 = this.f2949B) == null || (bigInteger3 = this.f2953S) == null) {
            throw new CryptoException("Impossible to compute and verify M1: some data are missing from the previous operations (A,B,S)");
        }
        if (!SRP6Util.calculateM1(this.digest, this.f2952N, bigInteger4, bigInteger2, bigInteger3).equals(bigInteger)) {
            return false;
        }
        this.f2950M1 = bigInteger;
        return true;
    }
}
