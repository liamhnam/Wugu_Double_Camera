package com.p020hp.jipp.encoding;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntRange;

@Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\n\b&\u0018\u0000 \r2\u00020\u0001:\u0001\rB\u0005¢\u0006\u0002\u0010\u0002J\r\u0010\u000b\u001a\u00020\u0004H\u0000¢\u0006\u0002\b\fR\u0011\u0010\u0003\u001a\u00020\u00048F¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0005R\u0011\u0010\u0006\u001a\u00020\u00048F¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0005R\u0011\u0010\u0007\u001a\u00020\u00048F¢\u0006\u0006\u001a\u0004\b\u0007\u0010\u0005R\u0011\u0010\b\u001a\u00020\u00048F¢\u0006\u0006\u001a\u0004\b\b\u0010\u0005R\u0011\u0010\t\u001a\u00020\u00048F¢\u0006\u0006\u001a\u0004\b\t\u0010\u0005R\u0011\u0010\n\u001a\u00020\u00048F¢\u0006\u0006\u001a\u0004\b\n\u0010\u0005¨\u0006\u000e"}, m1293d2 = {"Lcom/hp/jipp/encoding/Tag;", "Lcom/hp/jipp/encoding/Enum;", "()V", "isCharString", "", "()Z", "isCollection", "isDelimiter", "isInteger", "isOctetString", "isOutOfBand", "isEndOfValueStream", "isEndOfValueStream$jipp_core", "Companion", "jipp-core"}, m1294k = 1, m1295mv = {1, 4, 0})
public abstract class Tag extends Enum {
    public static final OutOfBandTag adminDefine;
    public static final List<Tag> all;
    public static final ValueTag beginCollection;
    public static final ValueTag booleanValue;
    public static final ValueTag charset;
    private static final Map<Integer, Tag> codeMap;
    public static final ValueTag dateTime;
    public static final OutOfBandTag deleteAttribute;
    public static final List<DelimiterTag> delimiterTags;
    public static final ValueTag endCollection;
    public static final DelimiterTag endOfAttributes;
    public static final ValueTag enumValue;
    public static final ValueTag integerValue;
    public static final DelimiterTag jobAttributes;
    public static final ValueTag keyword;
    public static final ValueTag memberAttributeName;
    public static final ValueTag mimeMediaType;
    public static final ValueTag nameWithLanguage;
    public static final ValueTag nameWithoutLanguage;
    public static final ValueTag naturalLanguage;
    public static final OutOfBandTag noValue;
    public static final OutOfBandTag notSettable;
    public static final ValueTag octetString;
    public static final DelimiterTag operationAttributes;
    public static final List<OutOfBandTag> outOfBandTag;
    public static final DelimiterTag printerAttributes;
    public static final ValueTag rangeOfInteger;
    public static final ValueTag resolution;
    public static final ValueTag textWithLanguage;
    public static final ValueTag textWithoutLanguage;
    public static final OutOfBandTag unknown;
    public static final OutOfBandTag unsupported;
    public static final DelimiterTag unsupportedAttributes;
    public static final ValueTag uri;
    public static final ValueTag uriScheme;
    public static final List<ValueTag> valueTags;

    public static final Companion INSTANCE = new Companion(null);
    private static final IntRange delimiterRange = new IntRange(1, 15);
    private static final IntRange outOfBandRange = new IntRange(16, 31);

    @JvmStatic
    public static final Tag fromInt(int i) {
        return INSTANCE.fromInt(i);
    }

    public final boolean isDelimiter() {
        return delimiterRange.contains(getCode());
    }

    public final boolean isOutOfBand() {
        return outOfBandRange.contains(getCode());
    }

    public final boolean isCollection() {
        return Intrinsics.areEqual(this, beginCollection) || Intrinsics.areEqual(this, endCollection) || Intrinsics.areEqual(this, memberAttributeName);
    }

    public final boolean isInteger() {
        int code = getCode();
        return 32 <= code && 47 >= code;
    }

    public final boolean isOctetString() {
        int code = getCode();
        return 48 <= code && 63 >= code;
    }

    public final boolean isCharString() {
        int code = getCode();
        return 64 <= code && 79 >= code;
    }

    public final boolean isEndOfValueStream$jipp_core() {
        return isDelimiter() || isOutOfBand() || Intrinsics.areEqual(this, memberAttributeName) || Intrinsics.areEqual(this, endCollection);
    }

    @Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u001f\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u00101\u001a\u00020\u00072\u0006\u00102\u001a\u00020\u000eH\u0007R\u0010\u0010\u0003\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00068\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u00020\t8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u00020\t8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u00020\t8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u00070\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u000f\u001a\u00020\t8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0010\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00140\u00068\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0015\u001a\u00020\t8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0016\u001a\u00020\u00148\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0017\u001a\u00020\t8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0018\u001a\u00020\t8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0019\u001a\u00020\u00148\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u001a\u001a\u00020\t8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u001b\u001a\u00020\t8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u001c\u001a\u00020\t8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u001d\u001a\u00020\t8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u001e\u001a\u00020\t8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u001f\u001a\u00020\t8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010 \u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010!\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\"\u001a\u00020\t8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010#\u001a\u00020\u00148\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u000e\u0010$\u001a\u00020\u0012X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010%\u001a\b\u0012\u0004\u0012\u00020\u00040\u00068\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010&\u001a\u00020\u00148\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010'\u001a\u00020\t8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010(\u001a\u00020\t8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010)\u001a\u00020\t8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010*\u001a\u00020\t8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010+\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010,\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010-\u001a\u00020\u00148\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010.\u001a\u00020\t8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010/\u001a\u00020\t8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0016\u00100\u001a\b\u0012\u0004\u0012\u00020\t0\u00068\u0006X\u0087\u0004¢\u0006\u0002\n\u0000¨\u00063"}, m1293d2 = {"Lcom/hp/jipp/encoding/Tag$Companion;", "", "()V", "adminDefine", "Lcom/hp/jipp/encoding/OutOfBandTag;", "all", "", "Lcom/hp/jipp/encoding/Tag;", "beginCollection", "Lcom/hp/jipp/encoding/ValueTag;", "booleanValue", "charset", "codeMap", "", "", "dateTime", "deleteAttribute", "delimiterRange", "Lkotlin/ranges/IntRange;", "delimiterTags", "Lcom/hp/jipp/encoding/DelimiterTag;", "endCollection", "endOfAttributes", "enumValue", "integerValue", "jobAttributes", "keyword", "memberAttributeName", "mimeMediaType", "nameWithLanguage", "nameWithoutLanguage", "naturalLanguage", "noValue", "notSettable", "octetString", "operationAttributes", "outOfBandRange", "outOfBandTag", "printerAttributes", "rangeOfInteger", "resolution", "textWithLanguage", "textWithoutLanguage", "unknown", "unsupported", "unsupportedAttributes", "uri", "uriScheme", "valueTags", "fromInt", "value", "jipp-core"}, m1294k = 1, m1295mv = {1, 4, 0})
    public static final class Companion {
        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        public final Tag fromInt(int value) {
            Tag tag = (Tag) Tag.codeMap.get(Integer.valueOf(value));
            if (tag != null) {
                return tag;
            }
            if (Tag.delimiterRange.contains(value)) {
                String str = String.format("tag(x%x)", Arrays.copyOf(new Object[]{Integer.valueOf(value)}, 1));
                Intrinsics.checkNotNullExpressionValue(str, "java.lang.String.format(this, *args)");
                return new DelimiterTag(value, str);
            }
            if (Tag.outOfBandRange.contains(value)) {
                String str2 = String.format("tag(x%x)", Arrays.copyOf(new Object[]{Integer.valueOf(value)}, 1));
                Intrinsics.checkNotNullExpressionValue(str2, "java.lang.String.format(this, *args)");
                return new OutOfBandTag(value, str2);
            }
            String str3 = String.format("tag(x%x)", Arrays.copyOf(new Object[]{Integer.valueOf(value)}, 1));
            Intrinsics.checkNotNullExpressionValue(str3, "java.lang.String.format(this, *args)");
            return new ValueTag(value, str3);
        }
    }

    static {
        DelimiterTag delimiterTag = new DelimiterTag(1, "operation-attributes");
        operationAttributes = delimiterTag;
        DelimiterTag delimiterTag2 = new DelimiterTag(2, "job-attributes");
        jobAttributes = delimiterTag2;
        DelimiterTag delimiterTag3 = new DelimiterTag(3, "end-of-attributes");
        endOfAttributes = delimiterTag3;
        DelimiterTag delimiterTag4 = new DelimiterTag(4, "printer-attributes");
        printerAttributes = delimiterTag4;
        DelimiterTag delimiterTag5 = new DelimiterTag(5, "unsupported-attributes");
        unsupportedAttributes = delimiterTag5;
        OutOfBandTag outOfBandTag2 = new OutOfBandTag(16, "unsupported");
        unsupported = outOfBandTag2;
        OutOfBandTag outOfBandTag3 = new OutOfBandTag(18, "unknown");
        unknown = outOfBandTag3;
        OutOfBandTag outOfBandTag4 = new OutOfBandTag(19, "no-value");
        noValue = outOfBandTag4;
        OutOfBandTag outOfBandTag5 = new OutOfBandTag(21, "not-settable");
        notSettable = outOfBandTag5;
        OutOfBandTag outOfBandTag6 = new OutOfBandTag(22, "delete-attribute");
        deleteAttribute = outOfBandTag6;
        OutOfBandTag outOfBandTag7 = new OutOfBandTag(23, "admin-define");
        adminDefine = outOfBandTag7;
        ValueTag valueTag = new ValueTag(33, TypedValues.Custom.S_INT);
        integerValue = valueTag;
        ValueTag valueTag2 = new ValueTag(34, TypedValues.Custom.S_BOOLEAN);
        booleanValue = valueTag2;
        ValueTag valueTag3 = new ValueTag(35, "enum");
        enumValue = valueTag3;
        ValueTag valueTag4 = new ValueTag(48, "octetString");
        octetString = valueTag4;
        ValueTag valueTag5 = new ValueTag(49, "dateTime");
        dateTime = valueTag5;
        ValueTag valueTag6 = new ValueTag(50, "resolution");
        resolution = valueTag6;
        ValueTag valueTag7 = new ValueTag(51, "rangeOfInteger");
        rangeOfInteger = valueTag7;
        ValueTag valueTag8 = new ValueTag(52, "begCollection");
        beginCollection = valueTag8;
        ValueTag valueTag9 = new ValueTag(53, "textWithLanguage");
        textWithLanguage = valueTag9;
        ValueTag valueTag10 = new ValueTag(54, "nameWithLanguage");
        nameWithLanguage = valueTag10;
        ValueTag valueTag11 = new ValueTag(55, "endCollection");
        endCollection = valueTag11;
        ValueTag valueTag12 = new ValueTag(65, "textWithoutLanguage");
        textWithoutLanguage = valueTag12;
        ValueTag valueTag13 = new ValueTag(66, "nameWithoutLanguage");
        nameWithoutLanguage = valueTag13;
        ValueTag valueTag14 = new ValueTag(68, "keyword");
        keyword = valueTag14;
        ValueTag valueTag15 = new ValueTag(69, "uri");
        uri = valueTag15;
        ValueTag valueTag16 = new ValueTag(70, "uriScheme");
        uriScheme = valueTag16;
        ValueTag valueTag17 = new ValueTag(71, "charset");
        charset = valueTag17;
        ValueTag valueTag18 = new ValueTag(72, "naturalLanguage");
        naturalLanguage = valueTag18;
        ValueTag valueTag19 = new ValueTag(73, "mimeMediaType");
        mimeMediaType = valueTag19;
        ValueTag valueTag20 = new ValueTag(74, "memberAttrName");
        memberAttributeName = valueTag20;
        List<DelimiterTag> listListOf = CollectionsKt.listOf((Object[]) new DelimiterTag[]{delimiterTag, delimiterTag2, delimiterTag3, delimiterTag4, delimiterTag5});
        delimiterTags = listListOf;
        List<OutOfBandTag> listListOf2 = CollectionsKt.listOf((Object[]) new OutOfBandTag[]{outOfBandTag2, outOfBandTag3, outOfBandTag4, outOfBandTag5, outOfBandTag6, outOfBandTag7});
        outOfBandTag = listListOf2;
        List<ValueTag> listListOf3 = CollectionsKt.listOf((Object[]) new ValueTag[]{valueTag, valueTag2, valueTag3, valueTag4, valueTag5, valueTag6, valueTag7, valueTag8, valueTag9, valueTag10, valueTag11, valueTag12, valueTag13, valueTag14, valueTag15, valueTag16, valueTag17, valueTag18, valueTag19, valueTag20});
        valueTags = listListOf3;
        List<Tag> listPlus = CollectionsKt.plus((Collection) CollectionsKt.plus((Collection) listListOf, (Iterable) listListOf2), (Iterable) listListOf3);
        all = listPlus;
        codeMap = Enum.INSTANCE.toCodeMap(listPlus);
    }
}
