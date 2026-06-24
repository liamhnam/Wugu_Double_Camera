package org.simpleframework.xml.transform;

import java.math.BigDecimal;

class BigDecimalTransform implements Transform<BigDecimal> {
    BigDecimalTransform() {
    }

    @Override
    public BigDecimal read(String str) {
        return new BigDecimal(str);
    }

    @Override
    public String write(BigDecimal bigDecimal) {
        return bigDecimal.toString();
    }
}
