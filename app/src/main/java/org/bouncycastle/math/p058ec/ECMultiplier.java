package org.bouncycastle.math.p058ec;

import java.math.BigInteger;

public interface ECMultiplier {
    ECPoint multiply(ECPoint eCPoint, BigInteger bigInteger);
}
