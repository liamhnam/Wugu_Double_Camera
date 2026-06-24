package com.p020hp.jipp.encoding;

import com.p020hp.jipp.model.MediaCol;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;

@Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0012\u0010\r\u001a\u0004\u0018\u00010\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u0016\u0010\n\u001a\n \f*\u0004\u0018\u00010\u000b0\u000bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0011"}, m1293d2 = {"Lcom/hp/jipp/encoding/MediaSizes;", "", "()V", "HEIGHT_AT", "", "MM_HUNDREDTHS_PER_INCH", "MM_HUNDREDTHS_PER_MM", "WIDTH_AT", "WIDTH_HEIGHT_DIMENSION_COUNT", "WIDTH_HEIGHT_UNIT_AT", "widthHeightPattern", "Ljava/util/regex/Pattern;", "kotlin.jvm.PlatformType", "parse", "Lcom/hp/jipp/model/MediaCol$MediaSize;", "mediaName", "", "jipp-core"}, m1294k = 1, m1295mv = {1, 4, 0})
public final class MediaSizes {
    private static final int HEIGHT_AT = 3;
    private static final int MM_HUNDREDTHS_PER_INCH = 2540;
    private static final int MM_HUNDREDTHS_PER_MM = 100;
    private static final int WIDTH_AT = 1;
    private static final int WIDTH_HEIGHT_DIMENSION_COUNT = 4;
    private static final int WIDTH_HEIGHT_UNIT_AT = 5;
    public static final MediaSizes INSTANCE = new MediaSizes();
    private static final Pattern widthHeightPattern = Pattern.compile("_([0-9]+(\\.[0-9]+)?)?x([0-9]+(\\.[0-9]+)?)([a-z]+)?$");

    private MediaSizes() {
    }

    @JvmStatic
    public static final MediaCol.MediaSize parse(String mediaName) {
        Intrinsics.checkNotNullParameter(mediaName, "mediaName");
        Matcher matcher = widthHeightPattern.matcher(mediaName);
        if (!matcher.find() || matcher.groupCount() < 4) {
            return null;
        }
        int i = Intrinsics.areEqual(matcher.groupCount() >= 5 ? matcher.group(5) : null, "in") ? MM_HUNDREDTHS_PER_INCH : 100;
        String strGroup = matcher.group(1);
        Intrinsics.checkNotNullExpressionValue(strGroup, "matches.group(WIDTH_AT)");
        double d = i;
        int i2 = (int) (Double.parseDouble(strGroup) * d);
        String strGroup2 = matcher.group(3);
        Intrinsics.checkNotNullExpressionValue(strGroup2, "matches.group(HEIGHT_AT)");
        return new MediaCol.MediaSize(Integer.valueOf(i2), Integer.valueOf((int) (Double.parseDouble(strGroup2) * d)));
    }
}
