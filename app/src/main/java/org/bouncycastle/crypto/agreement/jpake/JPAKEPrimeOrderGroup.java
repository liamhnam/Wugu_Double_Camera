package org.bouncycastle.crypto.agreement.jpake;

import com.google.android.exoplayer2.text.ttml.TtmlNode;
import com.p020hp.jipp.model.MaterialAmountUnit;
import java.math.BigInteger;

public class JPAKEPrimeOrderGroup {

    private final BigInteger f2928g;

    private final BigInteger f2929p;

    private final BigInteger f2930q;

    public JPAKEPrimeOrderGroup(BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3) {
        this(bigInteger, bigInteger2, bigInteger3, false);
    }

    JPAKEPrimeOrderGroup(BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3, boolean z) {
        JPAKEUtil.validateNotNull(bigInteger, TtmlNode.TAG_P);
        JPAKEUtil.validateNotNull(bigInteger2, "q");
        JPAKEUtil.validateNotNull(bigInteger3, MaterialAmountUnit.f719g);
        if (!z) {
            if (!bigInteger.subtract(JPAKEUtil.ONE).mod(bigInteger2).equals(JPAKEUtil.ZERO)) {
                throw new IllegalArgumentException("p-1 must be evenly divisible by q");
            }
            if (bigInteger3.compareTo(BigInteger.valueOf(2L)) == -1 || bigInteger3.compareTo(bigInteger.subtract(JPAKEUtil.ONE)) == 1) {
                throw new IllegalArgumentException("g must be in [2, p-1]");
            }
            if (!bigInteger3.modPow(bigInteger2, bigInteger).equals(JPAKEUtil.ONE)) {
                throw new IllegalArgumentException("g^q mod p must equal 1");
            }
            if (!bigInteger.isProbablePrime(20)) {
                throw new IllegalArgumentException("p must be prime");
            }
            if (!bigInteger2.isProbablePrime(20)) {
                throw new IllegalArgumentException("q must be prime");
            }
        }
        this.f2929p = bigInteger;
        this.f2930q = bigInteger2;
        this.f2928g = bigInteger3;
    }

    public BigInteger getG() {
        return this.f2928g;
    }

    public BigInteger getP() {
        return this.f2929p;
    }

    public BigInteger getQ() {
        return this.f2930q;
    }
}
