package org.simpleframework.xml.transform;

import java.util.Currency;

class CurrencyTransform implements Transform<Currency> {
    CurrencyTransform() {
    }

    @Override
    public Currency read(String str) {
        return Currency.getInstance(str);
    }

    @Override
    public String write(Currency currency) {
        return currency.toString();
    }
}
