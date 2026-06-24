package com.p020hp.jipp.model;

import com.p020hp.jipp.encoding.Attribute;
import com.p020hp.jipp.encoding.AttributeCollection;
import com.p020hp.jipp.encoding.AttributeType;
import com.p020hp.jipp.encoding.IntRangeType;
import com.p020hp.jipp.util.PrettyPrinter;
import java.util.List;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntRange;

@Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0016\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\b\u0018\u0000 )2\u00020\u0001:\u0001)B\u0007\b\u0016¢\u0006\u0002\u0010\u0002B5\u0012\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0004¢\u0006\u0002\u0010\bJ\u000b\u0010\u001c\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\u000b\u0010\u001d\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\u000b\u0010\u001e\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\u000b\u0010\u001f\u001a\u0004\u0018\u00010\u0004HÆ\u0003J9\u0010 \u001a\u00020\u00002\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0004HÆ\u0001J\u0013\u0010!\u001a\u00020\"2\b\u0010#\u001a\u0004\u0018\u00010$HÖ\u0003J\t\u0010%\u001a\u00020&HÖ\u0001J\b\u0010'\u001a\u00020(H\u0016R\u001e\u0010\t\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u000b0\n8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\rR\u001e\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u0010\n\u0002\b\u0012\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u001e\u0010\u0005\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u0010\n\u0002\b\u0015\u001a\u0004\b\u0013\u0010\u000f\"\u0004\b\u0014\u0010\u0011R\u001e\u0010\u0006\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u0010\n\u0002\b\u0018\u001a\u0004\b\u0016\u0010\u000f\"\u0004\b\u0017\u0010\u0011R\u001e\u0010\u0007\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u0010\n\u0002\b\u001b\u001a\u0004\b\u0019\u0010\u000f\"\u0004\b\u001a\u0010\u0011¨\u0006*"}, m1293d2 = {"Lcom/hp/jipp/model/InputScanRegionsSupported;", "Lcom/hp/jipp/encoding/AttributeCollection;", "()V", "xDimension", "Lkotlin/ranges/IntRange;", "xOrigin", "yDimension", "yOrigin", "(Lkotlin/ranges/IntRange;Lkotlin/ranges/IntRange;Lkotlin/ranges/IntRange;Lkotlin/ranges/IntRange;)V", "attributes", "", "Lcom/hp/jipp/encoding/Attribute;", "getAttributes", "()Ljava/util/List;", "getXDimension", "()Lkotlin/ranges/IntRange;", "setXDimension", "(Lkotlin/ranges/IntRange;)V", "xDimension$1", "getXOrigin", "setXOrigin", "xOrigin$1", "getYDimension", "setYDimension", "yDimension$1", "getYOrigin", "setYOrigin", "yOrigin$1", "component1", "component2", "component3", "component4", PrinterServiceType.copy, "equals", "", "other", "", "hashCode", "", "toString", "", "Companion", "jipp-core"}, m1294k = 1, m1295mv = {1, 4, 0})
public final class InputScanRegionsSupported implements AttributeCollection {

    public static final Companion INSTANCE;
    public static final Companion Types;
    private static final Class<InputScanRegionsSupported> cls;
    public static final IntRangeType xDimension;
    public static final IntRangeType xOrigin;
    public static final IntRangeType yDimension;
    public static final IntRangeType yOrigin;

    private IntRange xDimension;

    private IntRange xOrigin;

    private IntRange yDimension;

    private IntRange yOrigin;

    public static InputScanRegionsSupported copy$default(InputScanRegionsSupported inputScanRegionsSupported, IntRange intRange, IntRange intRange2, IntRange intRange3, IntRange intRange4, int i, Object obj) {
        if ((i & 1) != 0) {
            intRange = inputScanRegionsSupported.xDimension;
        }
        if ((i & 2) != 0) {
            intRange2 = inputScanRegionsSupported.xOrigin;
        }
        if ((i & 4) != 0) {
            intRange3 = inputScanRegionsSupported.yDimension;
        }
        if ((i & 8) != 0) {
            intRange4 = inputScanRegionsSupported.yOrigin;
        }
        return inputScanRegionsSupported.copy(intRange, intRange2, intRange3, intRange4);
    }

    public final IntRange getXDimension() {
        return this.xDimension;
    }

    public final IntRange getXOrigin() {
        return this.xOrigin;
    }

    public final IntRange getYDimension() {
        return this.yDimension;
    }

    public final IntRange getYOrigin() {
        return this.yOrigin;
    }

    public final InputScanRegionsSupported copy(IntRange xDimension2, IntRange xOrigin2, IntRange yDimension2, IntRange yOrigin2) {
        return new InputScanRegionsSupported(xDimension2, xOrigin2, yDimension2, yOrigin2);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof InputScanRegionsSupported)) {
            return false;
        }
        InputScanRegionsSupported inputScanRegionsSupported = (InputScanRegionsSupported) other;
        return Intrinsics.areEqual(this.xDimension, inputScanRegionsSupported.xDimension) && Intrinsics.areEqual(this.xOrigin, inputScanRegionsSupported.xOrigin) && Intrinsics.areEqual(this.yDimension, inputScanRegionsSupported.yDimension) && Intrinsics.areEqual(this.yOrigin, inputScanRegionsSupported.yOrigin);
    }

    public int hashCode() {
        IntRange intRange = this.xDimension;
        int iHashCode = (intRange != null ? intRange.hashCode() : 0) * 31;
        IntRange intRange2 = this.xOrigin;
        int iHashCode2 = (iHashCode + (intRange2 != null ? intRange2.hashCode() : 0)) * 31;
        IntRange intRange3 = this.yDimension;
        int iHashCode3 = (iHashCode2 + (intRange3 != null ? intRange3.hashCode() : 0)) * 31;
        IntRange intRange4 = this.yOrigin;
        return iHashCode3 + (intRange4 != null ? intRange4.hashCode() : 0);
    }

    @Override
    public void print(PrettyPrinter printer) {
        Intrinsics.checkNotNullParameter(printer, "printer");
        AttributeCollection.DefaultImpls.print(this, printer);
    }

    public InputScanRegionsSupported(IntRange intRange, IntRange intRange2, IntRange intRange3, IntRange intRange4) {
        this.xDimension = intRange;
        this.xOrigin = intRange2;
        this.yDimension = intRange3;
        this.yOrigin = intRange4;
    }

    public InputScanRegionsSupported(IntRange intRange, IntRange intRange2, IntRange intRange3, IntRange intRange4, int i, DefaultConstructorMarker defaultConstructorMarker) {
        if ((i & 1) != 0) {
            intRange = null;
        }
        if ((i & 2) != 0) {
            intRange2 = null;
        }
        if ((i & 4) != 0) {
            intRange3 = null;
        }
        if ((i & 8) != 0) {
            intRange4 = null;
        }
        this(intRange, intRange2, intRange3, intRange4);
    }

    public final IntRange getXDimension() {
        return this.xDimension;
    }

    public final void setXDimension(IntRange intRange) {
        this.xDimension = intRange;
    }

    public final IntRange getXOrigin() {
        return this.xOrigin;
    }

    public final void setXOrigin(IntRange intRange) {
        this.xOrigin = intRange;
    }

    public final IntRange getYDimension() {
        return this.yDimension;
    }

    public final void setYDimension(IntRange intRange) {
        this.yDimension = intRange;
    }

    public final IntRange getYOrigin() {
        return this.yOrigin;
    }

    public final void setYOrigin(IntRange intRange) {
        this.yOrigin = intRange;
    }

    public InputScanRegionsSupported() {
        this(null, null, null, null);
    }

    @Override
    public List<Attribute<?>> getAttributes() {
        Attribute[] attributeArr = new Attribute[4];
        IntRange intRange = this.xDimension;
        attributeArr[0] = intRange != null ? xDimension.mo418of(intRange) : null;
        IntRange intRange2 = this.xOrigin;
        attributeArr[1] = intRange2 != null ? xOrigin.mo418of(intRange2) : null;
        IntRange intRange3 = this.yDimension;
        attributeArr[2] = intRange3 != null ? yDimension.mo418of(intRange3) : null;
        IntRange intRange4 = this.yOrigin;
        attributeArr[3] = intRange4 != null ? yOrigin.mo418of(intRange4) : null;
        return CollectionsKt.listOfNotNull((Object[]) attributeArr);
    }

    @Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003J\u001a\u0010\u000f\u001a\u00020\u00022\u0010\u0010\u0010\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00120\u0011H\u0016R\u0016\u0010\u0004\u001a\u00020\u00008\u0006X\u0087\u0004¢\u0006\b\n\u0000\u0012\u0004\b\u0005\u0010\u0003R\u001a\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00020\u0007X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0010\u0010\n\u001a\u00020\u000b8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u00020\u000b8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u00020\u000b8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u00020\u000b8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000¨\u0006\u0013"}, m1293d2 = {"Lcom/hp/jipp/model/InputScanRegionsSupported$Companion;", "Lcom/hp/jipp/encoding/AttributeCollection$Converter;", "Lcom/hp/jipp/model/InputScanRegionsSupported;", "()V", "Types", "getTypes$annotations", "cls", "Ljava/lang/Class;", "getCls", "()Ljava/lang/Class;", "xDimension", "Lcom/hp/jipp/encoding/IntRangeType;", "xOrigin", "yDimension", "yOrigin", "convert", "attributes", "", "Lcom/hp/jipp/encoding/Attribute;", "jipp-core"}, m1294k = 1, m1295mv = {1, 4, 0})
    public static final class Companion implements AttributeCollection.Converter<InputScanRegionsSupported> {
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
        public InputScanRegionsSupported convert(List<? extends Attribute<?>> attributes) {
            Intrinsics.checkNotNullParameter(attributes, "attributes");
            return new InputScanRegionsSupported((IntRange) extractOne(attributes, InputScanRegionsSupported.xDimension), (IntRange) extractOne(attributes, InputScanRegionsSupported.xOrigin), (IntRange) extractOne(attributes, InputScanRegionsSupported.yDimension), (IntRange) extractOne(attributes, InputScanRegionsSupported.yOrigin));
        }

        @Override
        public Class<InputScanRegionsSupported> getCls() {
            return InputScanRegionsSupported.cls;
        }
    }

    static {
        Companion companion = new Companion(null);
        INSTANCE = companion;
        cls = InputScanRegionsSupported.class;
        Types = companion;
        xDimension = new IntRangeType("x-dimension");
        xOrigin = new IntRangeType("x-origin");
        yDimension = new IntRangeType("y-dimension");
        yOrigin = new IntRangeType("y-origin");
    }

    public String toString() {
        return "InputScanRegionsSupported(" + CollectionsKt.joinToString$default(getAttributes(), null, null, null, 0, null, null, 63, null) + ')';
    }
}
