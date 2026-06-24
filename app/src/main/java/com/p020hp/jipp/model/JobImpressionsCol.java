package com.p020hp.jipp.model;

import com.p020hp.jipp.encoding.Attribute;
import com.p020hp.jipp.encoding.AttributeCollection;
import com.p020hp.jipp.encoding.AttributeType;
import com.p020hp.jipp.encoding.IntType;
import com.p020hp.jipp.util.PrettyPrinter;
import java.util.List;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b(\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\b\u0018\u0000 >2\u00020\u0001:\u0001>B\u0007\b\u0016¢\u0006\u0002\u0010\u0002Be\u0012\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u0004¢\u0006\u0002\u0010\fJ\u0010\u0010-\u001a\u0004\u0018\u00010\u0004HÆ\u0003¢\u0006\u0002\u0010\u0013J\u0010\u0010.\u001a\u0004\u0018\u00010\u0004HÆ\u0003¢\u0006\u0002\u0010\u0013J\u0010\u0010/\u001a\u0004\u0018\u00010\u0004HÆ\u0003¢\u0006\u0002\u0010\u0013J\u0010\u00100\u001a\u0004\u0018\u00010\u0004HÆ\u0003¢\u0006\u0002\u0010\u0013J\u0010\u00101\u001a\u0004\u0018\u00010\u0004HÆ\u0003¢\u0006\u0002\u0010\u0013J\u0010\u00102\u001a\u0004\u0018\u00010\u0004HÆ\u0003¢\u0006\u0002\u0010\u0013J\u0010\u00103\u001a\u0004\u0018\u00010\u0004HÆ\u0003¢\u0006\u0002\u0010\u0013J\u0010\u00104\u001a\u0004\u0018\u00010\u0004HÆ\u0003¢\u0006\u0002\u0010\u0013Jn\u00105\u001a\u00020\u00002\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u0004HÆ\u0001¢\u0006\u0002\u00106J\u0013\u00107\u001a\u0002082\b\u00109\u001a\u0004\u0018\u00010:HÖ\u0003J\t\u0010;\u001a\u00020\u0004HÖ\u0001J\b\u0010<\u001a\u00020=H\u0016R\u001e\u0010\r\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u000f0\u000e8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u0011R \u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0016\u0010\u0017\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R \u0010\u0005\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u0012\n\u0004\b\u001a\u0010\u0017\u001a\u0004\b\u0018\u0010\u0013\"\u0004\b\u0019\u0010\u0015R \u0010\u0006\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u0012\n\u0004\b\u001d\u0010\u0017\u001a\u0004\b\u001b\u0010\u0013\"\u0004\b\u001c\u0010\u0015R \u0010\u0007\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u0012\n\u0004\b \u0010\u0017\u001a\u0004\b\u001e\u0010\u0013\"\u0004\b\u001f\u0010\u0015R \u0010\b\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u0012\n\u0004\b#\u0010\u0017\u001a\u0004\b!\u0010\u0013\"\u0004\b\"\u0010\u0015R \u0010\t\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u0012\n\u0004\b&\u0010\u0017\u001a\u0004\b$\u0010\u0013\"\u0004\b%\u0010\u0015R \u0010\n\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u0012\n\u0004\b)\u0010\u0017\u001a\u0004\b'\u0010\u0013\"\u0004\b(\u0010\u0015R \u0010\u000b\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u0012\n\u0004\b,\u0010\u0017\u001a\u0004\b*\u0010\u0013\"\u0004\b+\u0010\u0015¨\u0006?"}, m1293d2 = {"Lcom/hp/jipp/model/JobImpressionsCol;", "Lcom/hp/jipp/encoding/AttributeCollection;", "()V", MediaPrePrinted.blank, "", "blankTwoSided", "fullColor", "fullColorTwoSided", "highlightColor", "highlightColorTwoSided", "monochrome", "monochromeTwoSided", "(Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;)V", "attributes", "", "Lcom/hp/jipp/encoding/Attribute;", "getAttributes", "()Ljava/util/List;", "getBlank", "()Ljava/lang/Integer;", "setBlank", "(Ljava/lang/Integer;)V", "blank$1", "Ljava/lang/Integer;", "getBlankTwoSided", "setBlankTwoSided", "blankTwoSided$1", "getFullColor", "setFullColor", "fullColor$1", "getFullColorTwoSided", "setFullColorTwoSided", "fullColorTwoSided$1", "getHighlightColor", "setHighlightColor", "highlightColor$1", "getHighlightColorTwoSided", "setHighlightColorTwoSided", "highlightColorTwoSided$1", "getMonochrome", "setMonochrome", "monochrome$1", "getMonochromeTwoSided", "setMonochromeTwoSided", "monochromeTwoSided$1", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", PrinterServiceType.copy, "(Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;)Lcom/hp/jipp/model/JobImpressionsCol;", "equals", "", "other", "", "hashCode", "toString", "", "Companion", "jipp-core"}, m1294k = 1, m1295mv = {1, 4, 0})
public final class JobImpressionsCol implements AttributeCollection {

    public static final Companion INSTANCE;
    public static final Companion Types;
    public static final IntType blank;
    public static final IntType blankTwoSided;
    private static final Class<JobImpressionsCol> cls;
    public static final IntType fullColor;
    public static final IntType fullColorTwoSided;
    public static final IntType highlightColor;
    public static final IntType highlightColorTwoSided;
    public static final IntType monochrome;
    public static final IntType monochromeTwoSided;

    private Integer blank;

    private Integer blankTwoSided;

    private Integer fullColor;

    private Integer fullColorTwoSided;

    private Integer highlightColor;

    private Integer highlightColorTwoSided;

    private Integer monochrome;

    private Integer monochromeTwoSided;

    public final Integer getBlank() {
        return this.blank;
    }

    public final Integer getBlankTwoSided() {
        return this.blankTwoSided;
    }

    public final Integer getFullColor() {
        return this.fullColor;
    }

    public final Integer getFullColorTwoSided() {
        return this.fullColorTwoSided;
    }

    public final Integer getHighlightColor() {
        return this.highlightColor;
    }

    public final Integer getHighlightColorTwoSided() {
        return this.highlightColorTwoSided;
    }

    public final Integer getMonochrome() {
        return this.monochrome;
    }

    public final Integer getMonochromeTwoSided() {
        return this.monochromeTwoSided;
    }

    public final JobImpressionsCol copy(Integer blank2, Integer blankTwoSided2, Integer fullColor2, Integer fullColorTwoSided2, Integer highlightColor2, Integer highlightColorTwoSided2, Integer monochrome2, Integer monochromeTwoSided2) {
        return new JobImpressionsCol(blank2, blankTwoSided2, fullColor2, fullColorTwoSided2, highlightColor2, highlightColorTwoSided2, monochrome2, monochromeTwoSided2);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof JobImpressionsCol)) {
            return false;
        }
        JobImpressionsCol jobImpressionsCol = (JobImpressionsCol) other;
        return Intrinsics.areEqual(this.blank, jobImpressionsCol.blank) && Intrinsics.areEqual(this.blankTwoSided, jobImpressionsCol.blankTwoSided) && Intrinsics.areEqual(this.fullColor, jobImpressionsCol.fullColor) && Intrinsics.areEqual(this.fullColorTwoSided, jobImpressionsCol.fullColorTwoSided) && Intrinsics.areEqual(this.highlightColor, jobImpressionsCol.highlightColor) && Intrinsics.areEqual(this.highlightColorTwoSided, jobImpressionsCol.highlightColorTwoSided) && Intrinsics.areEqual(this.monochrome, jobImpressionsCol.monochrome) && Intrinsics.areEqual(this.monochromeTwoSided, jobImpressionsCol.monochromeTwoSided);
    }

    public int hashCode() {
        Integer num = this.blank;
        int iHashCode = (num != null ? num.hashCode() : 0) * 31;
        Integer num2 = this.blankTwoSided;
        int iHashCode2 = (iHashCode + (num2 != null ? num2.hashCode() : 0)) * 31;
        Integer num3 = this.fullColor;
        int iHashCode3 = (iHashCode2 + (num3 != null ? num3.hashCode() : 0)) * 31;
        Integer num4 = this.fullColorTwoSided;
        int iHashCode4 = (iHashCode3 + (num4 != null ? num4.hashCode() : 0)) * 31;
        Integer num5 = this.highlightColor;
        int iHashCode5 = (iHashCode4 + (num5 != null ? num5.hashCode() : 0)) * 31;
        Integer num6 = this.highlightColorTwoSided;
        int iHashCode6 = (iHashCode5 + (num6 != null ? num6.hashCode() : 0)) * 31;
        Integer num7 = this.monochrome;
        int iHashCode7 = (iHashCode6 + (num7 != null ? num7.hashCode() : 0)) * 31;
        Integer num8 = this.monochromeTwoSided;
        return iHashCode7 + (num8 != null ? num8.hashCode() : 0);
    }

    @Override
    public void print(PrettyPrinter printer) {
        Intrinsics.checkNotNullParameter(printer, "printer");
        AttributeCollection.DefaultImpls.print(this, printer);
    }

    public JobImpressionsCol(Integer num, Integer num2, Integer num3, Integer num4, Integer num5, Integer num6, Integer num7, Integer num8) {
        this.blank = num;
        this.blankTwoSided = num2;
        this.fullColor = num3;
        this.fullColorTwoSided = num4;
        this.highlightColor = num5;
        this.highlightColorTwoSided = num6;
        this.monochrome = num7;
        this.monochromeTwoSided = num8;
    }

    public JobImpressionsCol(Integer num, Integer num2, Integer num3, Integer num4, Integer num5, Integer num6, Integer num7, Integer num8, int i, DefaultConstructorMarker defaultConstructorMarker) {
        Integer num9;
        Integer num10;
        Integer num11;
        Integer num12;
        Integer num13;
        Integer num14;
        Integer num15;
        Integer num16 = null;
        if ((i & 1) != 0) {
            num9 = null;
        } else {
            num9 = num;
        }
        if ((i & 2) != 0) {
            num10 = null;
        } else {
            num10 = num2;
        }
        if ((i & 4) != 0) {
            num11 = null;
        } else {
            num11 = num3;
        }
        if ((i & 8) != 0) {
            num12 = null;
        } else {
            num12 = num4;
        }
        if ((i & 16) != 0) {
            num13 = null;
        } else {
            num13 = num5;
        }
        if ((i & 32) != 0) {
            num14 = null;
        } else {
            num14 = num6;
        }
        if ((i & 64) != 0) {
            num15 = null;
        } else {
            num15 = num7;
        }
        if ((i & 128) != 0) {
        } else {
            num16 = num8;
        }
        this(num9, num10, num11, num12, num13, num14, num15, num16);
    }

    public final Integer getBlank() {
        return this.blank;
    }

    public final void setBlank(Integer num) {
        this.blank = num;
    }

    public final Integer getBlankTwoSided() {
        return this.blankTwoSided;
    }

    public final void setBlankTwoSided(Integer num) {
        this.blankTwoSided = num;
    }

    public final Integer getFullColor() {
        return this.fullColor;
    }

    public final void setFullColor(Integer num) {
        this.fullColor = num;
    }

    public final Integer getFullColorTwoSided() {
        return this.fullColorTwoSided;
    }

    public final void setFullColorTwoSided(Integer num) {
        this.fullColorTwoSided = num;
    }

    public final Integer getHighlightColor() {
        return this.highlightColor;
    }

    public final void setHighlightColor(Integer num) {
        this.highlightColor = num;
    }

    public final Integer getHighlightColorTwoSided() {
        return this.highlightColorTwoSided;
    }

    public final void setHighlightColorTwoSided(Integer num) {
        this.highlightColorTwoSided = num;
    }

    public final Integer getMonochrome() {
        return this.monochrome;
    }

    public final void setMonochrome(Integer num) {
        this.monochrome = num;
    }

    public final Integer getMonochromeTwoSided() {
        return this.monochromeTwoSided;
    }

    public final void setMonochromeTwoSided(Integer num) {
        this.monochromeTwoSided = num;
    }

    public JobImpressionsCol() {
        this(null, null, null, null, null, null, null, null);
    }

    @Override
    public List<Attribute<?>> getAttributes() {
        Attribute<Integer> attributeOf;
        Attribute<Integer> attributeOf2;
        Attribute<Integer> attributeOf3;
        Attribute<Integer> attributeOf4;
        Attribute<Integer> attributeOf5;
        Attribute<Integer> attributeOf6;
        Attribute<Integer> attributeOf7;
        Attribute[] attributeArr = new Attribute[8];
        Integer num = this.blank;
        Attribute<Integer> attributeOf8 = null;
        if (num != null) {
            attributeOf = blank.mo418of(Integer.valueOf(num.intValue()));
        } else {
            attributeOf = null;
        }
        attributeArr[0] = attributeOf;
        Integer num2 = this.blankTwoSided;
        if (num2 != null) {
            attributeOf2 = blankTwoSided.mo418of(Integer.valueOf(num2.intValue()));
        } else {
            attributeOf2 = null;
        }
        attributeArr[1] = attributeOf2;
        Integer num3 = this.fullColor;
        if (num3 != null) {
            attributeOf3 = fullColor.mo418of(Integer.valueOf(num3.intValue()));
        } else {
            attributeOf3 = null;
        }
        attributeArr[2] = attributeOf3;
        Integer num4 = this.fullColorTwoSided;
        if (num4 != null) {
            attributeOf4 = fullColorTwoSided.mo418of(Integer.valueOf(num4.intValue()));
        } else {
            attributeOf4 = null;
        }
        attributeArr[3] = attributeOf4;
        Integer num5 = this.highlightColor;
        if (num5 != null) {
            attributeOf5 = highlightColor.mo418of(Integer.valueOf(num5.intValue()));
        } else {
            attributeOf5 = null;
        }
        attributeArr[4] = attributeOf5;
        Integer num6 = this.highlightColorTwoSided;
        if (num6 != null) {
            attributeOf6 = highlightColorTwoSided.mo418of(Integer.valueOf(num6.intValue()));
        } else {
            attributeOf6 = null;
        }
        attributeArr[5] = attributeOf6;
        Integer num7 = this.monochrome;
        if (num7 != null) {
            attributeOf7 = monochrome.mo418of(Integer.valueOf(num7.intValue()));
        } else {
            attributeOf7 = null;
        }
        attributeArr[6] = attributeOf7;
        Integer num8 = this.monochromeTwoSided;
        if (num8 != null) {
            attributeOf8 = monochromeTwoSided.mo418of(Integer.valueOf(num8.intValue()));
        }
        attributeArr[7] = attributeOf8;
        return CollectionsKt.listOfNotNull((Object[]) attributeArr);
    }

    @Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003J\u001a\u0010\u0013\u001a\u00020\u00022\u0010\u0010\u0014\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00160\u0015H\u0016R\u0016\u0010\u0004\u001a\u00020\u00008\u0006X\u0087\u0004¢\u0006\b\n\u0000\u0012\u0004\b\u0005\u0010\u0003R\u0010\u0010\u0006\u001a\u00020\u00078\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u00020\u00078\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00020\nX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0010\u0010\r\u001a\u00020\u00078\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u00020\u00078\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u000f\u001a\u00020\u00078\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0010\u001a\u00020\u00078\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0011\u001a\u00020\u00078\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0012\u001a\u00020\u00078\u0006X\u0087\u0004¢\u0006\u0002\n\u0000¨\u0006\u0017"}, m1293d2 = {"Lcom/hp/jipp/model/JobImpressionsCol$Companion;", "Lcom/hp/jipp/encoding/AttributeCollection$Converter;", "Lcom/hp/jipp/model/JobImpressionsCol;", "()V", "Types", "getTypes$annotations", MediaPrePrinted.blank, "Lcom/hp/jipp/encoding/IntType;", "blankTwoSided", "cls", "Ljava/lang/Class;", "getCls", "()Ljava/lang/Class;", "fullColor", "fullColorTwoSided", "highlightColor", "highlightColorTwoSided", "monochrome", "monochromeTwoSided", "convert", "attributes", "", "Lcom/hp/jipp/encoding/Attribute;", "jipp-core"}, m1294k = 1, m1295mv = {1, 4, 0})
    public static final class Companion implements AttributeCollection.Converter<JobImpressionsCol> {
        @Deprecated(message = "Remove this symbol")
        public static void getTypes$annotations() {
        }

        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @Override
        public <T> Attribute<T> coerced(List<? extends Attribute<?>> attributes, AttributeType<T> type) {
            Intrinsics.checkNotNullParameter(attributes, "attributes");
            Intrinsics.checkNotNullParameter(type, "type");
            return AttributeCollection.Converter.DefaultImpls.coerced(this, attributes, type);
        }

        @Override
        public AttributeCollection convert(List list) {
            return convert((List<? extends Attribute<?>>) list);
        }

        @Override
        public <T> List<T> extractAll(List<? extends Attribute<?>> attributes, AttributeType<T> type) {
            Intrinsics.checkNotNullParameter(attributes, "attributes");
            Intrinsics.checkNotNullParameter(type, "type");
            return AttributeCollection.Converter.DefaultImpls.extractAll(this, attributes, type);
        }

        @Override
        public <T> T extractOne(List<? extends Attribute<?>> attributes, AttributeType<T> type) {
            Intrinsics.checkNotNullParameter(attributes, "attributes");
            Intrinsics.checkNotNullParameter(type, "type");
            return (T) AttributeCollection.Converter.DefaultImpls.extractOne(this, attributes, type);
        }

        @Override
        public JobImpressionsCol convert(List<? extends Attribute<?>> attributes) {
            Intrinsics.checkNotNullParameter(attributes, "attributes");
            return new JobImpressionsCol((Integer) extractOne(attributes, JobImpressionsCol.blank), (Integer) extractOne(attributes, JobImpressionsCol.blankTwoSided), (Integer) extractOne(attributes, JobImpressionsCol.fullColor), (Integer) extractOne(attributes, JobImpressionsCol.fullColorTwoSided), (Integer) extractOne(attributes, JobImpressionsCol.highlightColor), (Integer) extractOne(attributes, JobImpressionsCol.highlightColorTwoSided), (Integer) extractOne(attributes, JobImpressionsCol.monochrome), (Integer) extractOne(attributes, JobImpressionsCol.monochromeTwoSided));
        }

        @Override
        public Class<JobImpressionsCol> getCls() {
            return JobImpressionsCol.cls;
        }
    }

    static {
        Companion companion = new Companion(null);
        INSTANCE = companion;
        cls = JobImpressionsCol.class;
        Types = companion;
        blank = new IntType(MediaPrePrinted.blank);
        blankTwoSided = new IntType("blank-two-sided");
        fullColor = new IntType("full-color");
        fullColorTwoSided = new IntType("full-color-two-sided");
        highlightColor = new IntType("highlight-color");
        highlightColorTwoSided = new IntType("highlight-color-two-sided");
        monochrome = new IntType("monochrome");
        monochromeTwoSided = new IntType("monochrome-two-sided");
    }

    public String toString() {
        return "JobImpressionsCol(" + CollectionsKt.joinToString$default(getAttributes(), null, null, null, 0, null, null, 63, null) + ')';
    }
}
