package com.tom_roush.pdfbox.cos;

import com.tom_roush.pdfbox.pdmodel.documentinterchange.taggedpdf.PDLayoutAttributeObject;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;

public class COSFloat extends COSNumber {
    private BigDecimal value;
    private String valueAsString;

    public COSFloat(float f) {
        BigDecimal bigDecimal = new BigDecimal(String.valueOf(f));
        this.value = bigDecimal;
        this.valueAsString = removeNullDigits(bigDecimal.toPlainString());
    }

    public COSFloat(String str) throws IOException {
        try {
            this.valueAsString = str;
            this.value = new BigDecimal(this.valueAsString);
            checkMinMaxValues();
        } catch (NumberFormatException e) {
            if (str.startsWith("0.00000-")) {
                try {
                    this.valueAsString = "-0.00000" + str.substring(8);
                    this.value = new BigDecimal(this.valueAsString);
                    checkMinMaxValues();
                    return;
                } catch (NumberFormatException e2) {
                    throw new IOException("Error expected floating point number actual='" + str + "'", e2);
                }
            }
            throw new IOException("Error expected floating point number actual='" + str + "'", e);
        }
    }

    private void checkMinMaxValues() {
        float f;
        float f2;
        float fFloatValue = this.value.floatValue();
        double dDoubleValue = this.value.doubleValue();
        boolean z = true;
        if (fFloatValue == Float.NEGATIVE_INFINITY || fFloatValue == Float.POSITIVE_INFINITY) {
            if (Math.abs(dDoubleValue) > 3.4028234663852886E38d) {
                f = fFloatValue == Float.POSITIVE_INFINITY ? 1 : -1;
                f2 = Float.MAX_VALUE;
                fFloatValue = f * f2;
            }
            z = false;
        } else {
            if (fFloatValue == 0.0f && dDoubleValue != 0.0d && Math.abs(dDoubleValue) < 1.1754943508222875E-38d) {
                f = dDoubleValue >= 0.0d ? 1.0f : -1.0f;
                f2 = Float.MIN_NORMAL;
                fFloatValue = f * f2;
            }
            z = false;
        }
        if (z) {
            BigDecimal bigDecimal = new BigDecimal(fFloatValue);
            this.value = bigDecimal;
            this.valueAsString = removeNullDigits(bigDecimal.toPlainString());
        }
    }

    private String removeNullDigits(String str) {
        if (str.indexOf(46) > -1 && !str.endsWith(".0")) {
            while (str.endsWith(PDLayoutAttributeObject.GLYPH_ORIENTATION_VERTICAL_ZERO_DEGREES) && !str.endsWith(".0")) {
                str = str.substring(0, str.length() - 1);
            }
        }
        return str;
    }

    @Override
    public float floatValue() {
        return this.value.floatValue();
    }

    @Override
    public double doubleValue() {
        return this.value.doubleValue();
    }

    @Override
    public long longValue() {
        return this.value.longValue();
    }

    @Override
    public int intValue() {
        return this.value.intValue();
    }

    public boolean equals(Object obj) {
        return (obj instanceof COSFloat) && Float.floatToIntBits(((COSFloat) obj).value.floatValue()) == Float.floatToIntBits(this.value.floatValue());
    }

    public int hashCode() {
        return this.value.hashCode();
    }

    public String toString() {
        return "COSFloat{" + this.valueAsString + "}";
    }

    @Override
    public Object accept(ICOSVisitor iCOSVisitor) throws IOException {
        return iCOSVisitor.visitFromFloat(this);
    }

    public void writePDF(OutputStream outputStream) throws IOException {
        outputStream.write(this.valueAsString.getBytes("ISO-8859-1"));
    }
}
