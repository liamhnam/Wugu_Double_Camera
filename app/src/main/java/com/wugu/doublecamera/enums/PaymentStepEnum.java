package com.wugu.doublecamera.enums;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class PaymentStepEnum {
    public static final int CASH_PAYMENT_ADD = 5;
    public static final int PAYMENT_OK = 3;
    public static final int SCANNER_REC = 6;

    @Retention(RetentionPolicy.SOURCE)
    public @interface paymentStep {
    }
}
