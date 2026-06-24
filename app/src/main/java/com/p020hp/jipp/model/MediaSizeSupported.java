package com.p020hp.jipp.model;

import com.p020hp.jipp.encoding.Attribute;
import com.p020hp.jipp.encoding.AttributeCollection;
import com.p020hp.jipp.encoding.AttributeType;
import com.p020hp.jipp.encoding.IntOrIntRange;
import com.p020hp.jipp.encoding.IntOrIntRangeType;
import com.p020hp.jipp.util.PrettyPrinter;
import java.util.List;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\b\u0018\u0000 \u001f2\u00020\u0001:\u0001\u001fB\u0007\b\u0016¢\u0006\u0002\u0010\u0002B\u001d\u0012\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0004¢\u0006\u0002\u0010\u0006J\u000b\u0010\u0014\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\u000b\u0010\u0015\u001a\u0004\u0018\u00010\u0004HÆ\u0003J!\u0010\u0016\u001a\u00020\u00002\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0004HÆ\u0001J\u0013\u0010\u0017\u001a\u00020\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aHÖ\u0003J\t\u0010\u001b\u001a\u00020\u001cHÖ\u0001J\b\u0010\u001d\u001a\u00020\u001eH\u0016R\u001e\u0010\u0007\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\t0\b8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\u000bR\u001e\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u0010\n\u0002\b\u0010\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u001e\u0010\u0005\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u0010\n\u0002\b\u0013\u001a\u0004\b\u0011\u0010\r\"\u0004\b\u0012\u0010\u000f¨\u0006 "}, m1293d2 = {"Lcom/hp/jipp/model/MediaSizeSupported;", "Lcom/hp/jipp/encoding/AttributeCollection;", "()V", "xDimension", "Lcom/hp/jipp/encoding/IntOrIntRange;", "yDimension", "(Lcom/hp/jipp/encoding/IntOrIntRange;Lcom/hp/jipp/encoding/IntOrIntRange;)V", "attributes", "", "Lcom/hp/jipp/encoding/Attribute;", "getAttributes", "()Ljava/util/List;", "getXDimension", "()Lcom/hp/jipp/encoding/IntOrIntRange;", "setXDimension", "(Lcom/hp/jipp/encoding/IntOrIntRange;)V", "xDimension$1", "getYDimension", "setYDimension", "yDimension$1", "component1", "component2", PrinterServiceType.copy, "equals", "", "other", "", "hashCode", "", "toString", "", "Companion", "jipp-core"}, m1294k = 1, m1295mv = {1, 4, 0})
public final class MediaSizeSupported implements AttributeCollection {

    public static final Companion INSTANCE;
    public static final Companion Types;
    private static final Class<MediaSizeSupported> cls;
    public static final IntOrIntRangeType xDimension;
    public static final IntOrIntRangeType yDimension;

    private IntOrIntRange xDimension;

    private IntOrIntRange yDimension;

    public static MediaSizeSupported copy$default(MediaSizeSupported mediaSizeSupported, IntOrIntRange intOrIntRange, IntOrIntRange intOrIntRange2, int i, Object obj) {
        if ((i & 1) != 0) {
            intOrIntRange = mediaSizeSupported.xDimension;
        }
        if ((i & 2) != 0) {
            intOrIntRange2 = mediaSizeSupported.yDimension;
        }
        return mediaSizeSupported.copy(intOrIntRange, intOrIntRange2);
    }

    public final IntOrIntRange getXDimension() {
        return this.xDimension;
    }

    public final IntOrIntRange getYDimension() {
        return this.yDimension;
    }

    public final MediaSizeSupported copy(IntOrIntRange xDimension2, IntOrIntRange yDimension2) {
        return new MediaSizeSupported(xDimension2, yDimension2);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof MediaSizeSupported)) {
            return false;
        }
        MediaSizeSupported mediaSizeSupported = (MediaSizeSupported) other;
        return Intrinsics.areEqual(this.xDimension, mediaSizeSupported.xDimension) && Intrinsics.areEqual(this.yDimension, mediaSizeSupported.yDimension);
    }

    public int hashCode() {
        IntOrIntRange intOrIntRange = this.xDimension;
        int iHashCode = (intOrIntRange != null ? intOrIntRange.hashCode() : 0) * 31;
        IntOrIntRange intOrIntRange2 = this.yDimension;
        return iHashCode + (intOrIntRange2 != null ? intOrIntRange2.hashCode() : 0);
    }

    @Override
    public void print(PrettyPrinter printer) {
        Intrinsics.checkNotNullParameter(printer, "printer");
        AttributeCollection.DefaultImpls.print(this, printer);
    }

    public MediaSizeSupported(IntOrIntRange intOrIntRange, IntOrIntRange intOrIntRange2) {
        this.xDimension = intOrIntRange;
        this.yDimension = intOrIntRange2;
    }

    public MediaSizeSupported(IntOrIntRange intOrIntRange, IntOrIntRange intOrIntRange2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        if ((i & 1) != 0) {
            intOrIntRange = null;
        }
        if ((i & 2) != 0) {
            intOrIntRange2 = null;
        }
        this(intOrIntRange, intOrIntRange2);
    }

    public final IntOrIntRange getXDimension() {
        return this.xDimension;
    }

    public final void setXDimension(IntOrIntRange intOrIntRange) {
        this.xDimension = intOrIntRange;
    }

    public final IntOrIntRange getYDimension() {
        return this.yDimension;
    }

    public final void setYDimension(IntOrIntRange intOrIntRange) {
        this.yDimension = intOrIntRange;
    }

    public MediaSizeSupported() {
        this(null, null);
    }

    @Override
    public List<Attribute<?>> getAttributes() {
        Attribute[] attributeArr = new Attribute[2];
        IntOrIntRange intOrIntRange = this.xDimension;
        attributeArr[0] = intOrIntRange != null ? xDimension.mo418of(intOrIntRange) : null;
        IntOrIntRange intOrIntRange2 = this.yDimension;
        attributeArr[1] = intOrIntRange2 != null ? yDimension.mo418of(intOrIntRange2) : null;
        return CollectionsKt.listOfNotNull((Object[]) attributeArr);
    }

    @Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003J\u001a\u0010\r\u001a\u00020\u00022\u0010\u0010\u000e\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00100\u000fH\u0016R\u0016\u0010\u0004\u001a\u00020\u00008\u0006X\u0087\u0004¢\u0006\b\n\u0000\u0012\u0004\b\u0005\u0010\u0003R\u001a\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00020\u0007X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0010\u0010\n\u001a\u00020\u000b8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u00020\u000b8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000¨\u0006\u0011"}, m1293d2 = {"Lcom/hp/jipp/model/MediaSizeSupported$Companion;", "Lcom/hp/jipp/encoding/AttributeCollection$Converter;", "Lcom/hp/jipp/model/MediaSizeSupported;", "()V", "Types", "getTypes$annotations", "cls", "Ljava/lang/Class;", "getCls", "()Ljava/lang/Class;", "xDimension", "Lcom/hp/jipp/encoding/IntOrIntRangeType;", "yDimension", "convert", "attributes", "", "Lcom/hp/jipp/encoding/Attribute;", "jipp-core"}, m1294k = 1, m1295mv = {1, 4, 0})
    public static final class Companion implements AttributeCollection.Converter<MediaSizeSupported> {
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
        public MediaSizeSupported convert(List<? extends Attribute<?>> attributes) {
            Intrinsics.checkNotNullParameter(attributes, "attributes");
            return new MediaSizeSupported((IntOrIntRange) extractOne(attributes, MediaSizeSupported.xDimension), (IntOrIntRange) extractOne(attributes, MediaSizeSupported.yDimension));
        }

        @Override
        public Class<MediaSizeSupported> getCls() {
            return MediaSizeSupported.cls;
        }
    }

    static {
        Companion companion = new Companion(null);
        INSTANCE = companion;
        cls = MediaSizeSupported.class;
        Types = companion;
        xDimension = new IntOrIntRangeType("x-dimension");
        yDimension = new IntOrIntRangeType("y-dimension");
    }

    public String toString() {
        return "MediaSizeSupported(" + CollectionsKt.joinToString$default(getAttributes(), null, null, null, 0, null, null, 63, null) + ')';
    }
}
