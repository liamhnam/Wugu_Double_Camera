package org.bouncycastle.crypto.agreement.srp;

import java.math.BigInteger;
import java.security.SecureRandom;
import org.bouncycastle.crypto.CryptoException;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.params.SRP6GroupParameters;

public class SRP6Client {

    protected BigInteger f2938A;

    protected BigInteger f2939B;
    protected BigInteger Key;

    protected BigInteger f2940M1;

    protected BigInteger f2941M2;

    protected BigInteger f2942N;

    protected BigInteger f2943S;

    protected BigInteger f2944a;
    protected Digest digest;

    protected BigInteger f2945g;
    protected SecureRandom random;

    protected BigInteger f2946u;

    protected BigInteger f2947x;

    private BigInteger calculateS() {
        BigInteger bigIntegerCalculateK = SRP6Util.calculateK(this.digest, this.f2942N, this.f2945g);
        return this.f2939B.subtract(this.f2945g.modPow(this.f2947x, this.f2942N).multiply(bigIntegerCalculateK).mod(this.f2942N)).mod(this.f2942N).modPow(this.f2946u.multiply(this.f2947x).add(this.f2944a), this.f2942N);
    }

    public BigInteger calculateClientEvidenceMessage() throws CryptoException {
        BigInteger bigInteger;
        BigInteger bigInteger2;
        BigInteger bigInteger3 = this.f2938A;
        if (bigInteger3 == null || (bigInteger = this.f2939B) == null || (bigInteger2 = this.f2943S) == null) {
            throw new CryptoException("Impossible to compute M1: some data are missing from the previous operations (A,B,S)");
        }
        BigInteger bigIntegerCalculateM1 = SRP6Util.calculateM1(this.digest, this.f2942N, bigInteger3, bigInteger, bigInteger2);
        this.f2940M1 = bigIntegerCalculateM1;
        return bigIntegerCalculateM1;
    }

    public BigInteger calculateSecret(BigInteger bigInteger) throws CryptoException {
        BigInteger bigIntegerValidatePublicValue = SRP6Util.validatePublicValue(this.f2942N, bigInteger);
        this.f2939B = bigIntegerValidatePublicValue;
        this.f2946u = SRP6Util.calculateU(this.digest, this.f2942N, this.f2938A, bigIntegerValidatePublicValue);
        BigInteger bigIntegerCalculateS = calculateS();
        this.f2943S = bigIntegerCalculateS;
        return bigIntegerCalculateS;
    }

    public BigInteger calculateSessionKey() throws CryptoException {
        BigInteger bigInteger = this.f2943S;
        if (bigInteger == null || this.f2940M1 == null || this.f2941M2 == null) {
            throw new CryptoException("Impossible to compute Key: some data are missing from the previous operations (S,M1,M2)");
        }
        BigInteger bigIntegerCalculateKey = SRP6Util.calculateKey(this.digest, this.f2942N, bigInteger);
        this.Key = bigIntegerCalculateKey;
        return bigIntegerCalculateKey;
    }

    public BigInteger generateClientCredentials(byte[] bArr, byte[] bArr2, byte[] bArr3) {
        this.f2947x = SRP6Util.calculateX(this.digest, this.f2942N, bArr, bArr2, bArr3);
        BigInteger bigIntegerSelectPrivateValue = selectPrivateValue();
        this.f2944a = bigIntegerSelectPrivateValue;
        BigInteger bigIntegerModPow = this.f2945g.modPow(bigIntegerSelectPrivateValue, this.f2942N);
        this.f2938A = bigIntegerModPow;
        return bigIntegerModPow;
    }

    public void init(BigInteger bigInteger, BigInteger bigInteger2, Digest digest, SecureRandom secureRandom) {
        this.f2942N = bigInteger;
        this.f2945g = bigInteger2;
        this.digest = digest;
        this.random = secureRandom;
    }

    public void init(SRP6GroupParameters sRP6GroupParameters, Digest digest, SecureRandom secureRandom) {
        init(sRP6GroupParameters.getN(), sRP6GroupParameters.getG(), digest, secureRandom);
    }

    protected BigInteger selectPrivateValue() {
        return SRP6Util.generatePrivateValue(this.digest, this.f2942N, this.f2945g, this.random);
    }

    public boolean verifyServerEvidenceMessage(BigInteger bigInteger) throws CryptoException {
        BigInteger bigInteger2;
        BigInteger bigInteger3;
        BigInteger bigInteger4 = this.f2938A;
        if (bigInteger4 == null || (bigInteger2 = this.f2940M1) == null || (bigInteger3 = this.f2943S) == null) {
            throw new CryptoException("Impossible to compute and verify M2: some data are missing from the previous operations (A,M1,S)");
        }
        if (!SRP6Util.calculateM2(this.digest, this.f2942N, bigInteger4, bigInteger2, bigInteger3).equals(bigInteger)) {
            return false;
        }
        this.f2941M2 = bigInteger;
        return true;
    }
}
