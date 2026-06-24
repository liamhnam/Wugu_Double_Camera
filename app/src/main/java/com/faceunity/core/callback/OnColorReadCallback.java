package com.faceunity.core.callback;

import com.p020hp.jipp.model.MaterialAmountUnit;
import com.p020hp.jipp.model.Media;
import com.tom_roush.pdfbox.pdmodel.common.PDPageLabelRange;
import kotlin.Metadata;

@Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\bf\u0018\u00002\u00020\u0001J(\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\u0005H&¨\u0006\t"}, m1293d2 = {"Lcom/faceunity/core/callback/OnColorReadCallback;", "", "onReadRgba", "", PDPageLabelRange.STYLE_ROMAN_LOWER, "", MaterialAmountUnit.f719g, Media.f726b, "a", "fu_core_all_featureRelease"}, m1294k = 1, m1295mv = {1, 1, 16})
public interface OnColorReadCallback {
    void onReadRgba(int r, int g, int b, int a2);
}
