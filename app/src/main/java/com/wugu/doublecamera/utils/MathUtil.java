package com.wugu.doublecamera.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class MathUtil {
    public static int getPriceValue(float f) {
        return new BigDecimal(String.valueOf(f)).multiply(new BigDecimal(100)).setScale(0, RoundingMode.DOWN).intValue();
    }

    public static int floatToInt(float f) {
        if (f == 0.0f) {
            return 0;
        }
        return new BigDecimal(String.valueOf(f)).setScale(0, RoundingMode.DOWN).intValue();
    }

    public static float stringToFloat(String str, float f) {
        if (str != null && !str.trim().isEmpty()) {
            try {
                return new BigDecimal(str).floatValue();
            } catch (NumberFormatException unused) {
            }
        }
        return f;
    }
}
