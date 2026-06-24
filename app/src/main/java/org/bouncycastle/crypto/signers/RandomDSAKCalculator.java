package org.bouncycastle.crypto.signers;

import java.math.BigInteger;
import java.security.SecureRandom;
import org.bouncycastle.util.BigIntegers;

public class RandomDSAKCalculator implements DSAKCalculator {
    private static final BigInteger ZERO = BigInteger.valueOf(0);

    private BigInteger f3415q;
    private SecureRandom random;

    @Override
    public void init(BigInteger bigInteger, BigInteger bigInteger2, byte[] bArr) {
        throw new IllegalStateException("Operation not supported");
    }

    @Override
    public void init(BigInteger bigInteger, SecureRandom secureRandom) {
        this.f3415q = bigInteger;
        this.random = secureRandom;
    }

    @Override
    public boolean isDeterministic() {
        return false;
    }

    @Override
    public BigInteger nextK() {
        int iBitLength = this.f3415q.bitLength();
        while (true) {
            BigInteger bigIntegerCreateRandomBigInteger = BigIntegers.createRandomBigInteger(iBitLength, this.random);
            if (!bigIntegerCreateRandomBigInteger.equals(ZERO) && bigIntegerCreateRandomBigInteger.compareTo(this.f3415q) < 0) {
                return bigIntegerCreateRandomBigInteger;
            }
        }
    }
}
