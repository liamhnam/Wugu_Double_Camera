package com.tom_roush.pdfbox.contentstream.operator.graphics;

import com.tom_roush.pdfbox.pdmodel.interactive.measurement.PDNumberFormatDictionary;

public final class LegacyFillNonZeroRule extends FillNonZeroRule {
    @Override
    public String getName() {
        return PDNumberFormatDictionary.FRACTIONAL_DISPLAY_FRACTION;
    }
}
