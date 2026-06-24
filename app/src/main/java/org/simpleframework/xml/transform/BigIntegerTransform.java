package org.simpleframework.xml.transform;

import java.math.BigInteger;

class BigIntegerTransform implements Transform<BigInteger> {
    BigIntegerTransform() {
    }

    @Override
    public BigInteger read(String str) {
        return new BigInteger(str);
    }

    @Override
    public String write(BigInteger bigInteger) {
        return bigInteger.toString();
    }
}
