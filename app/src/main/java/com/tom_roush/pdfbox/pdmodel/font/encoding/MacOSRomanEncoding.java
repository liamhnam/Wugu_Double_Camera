package com.tom_roush.pdfbox.pdmodel.font.encoding;

import com.brother.sdk.print.pdl.PrintImageUtil;
import com.tom_roush.pdfbox.cos.COSBase;
import com.wugu.doublecamera.enums.UiPosIndexEnum;

public class MacOSRomanEncoding extends MacRomanEncoding {
    private static final int CHAR_CODE = 0;
    private static final int CHAR_NAME = 1;
    private static final Object[][] MAC_OS_ROMAN_ENCODING_TABLE = {new Object[]{255, "notequal"}, new Object[]{260, "infinity"}, new Object[]{262, "lessequal"}, new Object[]{263, "greaterequal"}, new Object[]{266, "partialdiff"}, new Object[]{267, "summation"}, new Object[]{270, "product"}, new Object[]{271, "pi"}, new Object[]{272, "integral"}, new Object[]{275, "Omega"}, new Object[]{303, "radical"}, new Object[]{305, "approxequal"}, new Object[]{306, "Delta"}, new Object[]{Integer.valueOf(UiPosIndexEnum.PAYMENT_BALANCE), "lozenge"}, new Object[]{Integer.valueOf(UiPosIndexEnum.PAYMENT_DOU_YIN_IC), "Euro"}, new Object[]{Integer.valueOf(PrintImageUtil.ROUND_ROTATE), "apple"}};
    public static final MacOSRomanEncoding INSTANCE = new MacOSRomanEncoding();

    @Override
    public COSBase getCOSObject() {
        return null;
    }

    public MacOSRomanEncoding() {
        for (Object[] objArr : MAC_OS_ROMAN_ENCODING_TABLE) {
            add(((Integer) objArr[0]).intValue(), objArr[1].toString());
        }
    }
}
