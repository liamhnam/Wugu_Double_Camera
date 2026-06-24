package org.bouncycastle.crypto.p052ec;

import java.math.BigInteger;

public interface ECPairFactorTransform extends ECPairTransform {
    BigInteger getTransformValue();
}
