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

@Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0014\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\b\u0018\u0000 %2\u00020\u0001:\u0001%B\u0007\b\u0016¢\u0006\u0002\u0010\u0002B)\u0012\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0004¢\u0006\u0002\u0010\u0007J\u0010\u0010\u0019\u001a\u0004\u0018\u00010\u0004HÆ\u0003¢\u0006\u0002\u0010\u000eJ\u0010\u0010\u001a\u001a\u0004\u0018\u00010\u0004HÆ\u0003¢\u0006\u0002\u0010\u000eJ\u0010\u0010\u001b\u001a\u0004\u0018\u00010\u0004HÆ\u0003¢\u0006\u0002\u0010\u000eJ2\u0010\u001c\u001a\u00020\u00002\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0004HÆ\u0001¢\u0006\u0002\u0010\u001dJ\u0013\u0010\u001e\u001a\u00020\u001f2\b\u0010 \u001a\u0004\u0018\u00010!HÖ\u0003J\t\u0010\"\u001a\u00020\u0004HÖ\u0001J\b\u0010#\u001a\u00020$H\u0016R\u001e\u0010\b\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\n0\t8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\fR \u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0011\u0010\u0012\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R \u0010\u0005\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0015\u0010\u0012\u001a\u0004\b\u0013\u0010\u000e\"\u0004\b\u0014\u0010\u0010R \u0010\u0006\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0018\u0010\u0012\u001a\u0004\b\u0016\u0010\u000e\"\u0004\b\u0017\u0010\u0010¨\u0006&"}, m1293d2 = {"Lcom/hp/jipp/model/PrinterVolumeSupported;", "Lcom/hp/jipp/encoding/AttributeCollection;", "()V", "xDimension", "", "yDimension", "zDimension", "(Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;)V", "attributes", "", "Lcom/hp/jipp/encoding/Attribute;", "getAttributes", "()Ljava/util/List;", "getXDimension", "()Ljava/lang/Integer;", "setXDimension", "(Ljava/lang/Integer;)V", "xDimension$1", "Ljava/lang/Integer;", "getYDimension", "setYDimension", "yDimension$1", "getZDimension", "setZDimension", "zDimension$1", "component1", "component2", "component3", PrinterServiceType.copy, "(Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;)Lcom/hp/jipp/model/PrinterVolumeSupported;", "equals", "", "other", "", "hashCode", "toString", "", "Companion", "jipp-core"}, m1294k = 1, m1295mv = {1, 4, 0})
public final class PrinterVolumeSupported implements AttributeCollection {

    public static final Companion INSTANCE;
    public static final Companion Types;
    private static final Class<PrinterVolumeSupported> cls;
    public static final IntType xDimension;
    public static final IntType yDimension;
    public static final IntType zDimension;

    private Integer xDimension;

    private Integer yDimension;

    private Integer zDimension;

    public static PrinterVolumeSupported copy$default(PrinterVolumeSupported printerVolumeSupported, Integer num, Integer num2, Integer num3, int i, Object obj) {
        if ((i & 1) != 0) {
            num = printerVolumeSupported.xDimension;
        }
        if ((i & 2) != 0) {
            num2 = printerVolumeSupported.yDimension;
        }
        if ((i & 4) != 0) {
            num3 = printerVolumeSupported.zDimension;
        }
        return printerVolumeSupported.copy(num, num2, num3);
    }

    public final Integer getXDimension() {
        return this.xDimension;
    }

    public final Integer getYDimension() {
        return this.yDimension;
    }

    public final Integer getZDimension() {
        return this.zDimension;
    }

    public final PrinterVolumeSupported copy(Integer xDimension2, Integer yDimension2, Integer zDimension2) {
        return new PrinterVolumeSupported(xDimension2, yDimension2, zDimension2);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof PrinterVolumeSupported)) {
            return false;
        }
        PrinterVolumeSupported printerVolumeSupported = (PrinterVolumeSupported) other;
        return Intrinsics.areEqual(this.xDimension, printerVolumeSupported.xDimension) && Intrinsics.areEqual(this.yDimension, printerVolumeSupported.yDimension) && Intrinsics.areEqual(this.zDimension, printerVolumeSupported.zDimension);
    }

    public int hashCode() {
        Integer num = this.xDimension;
        int iHashCode = (num != null ? num.hashCode() : 0) * 31;
        Integer num2 = this.yDimension;
        int iHashCode2 = (iHashCode + (num2 != null ? num2.hashCode() : 0)) * 31;
        Integer num3 = this.zDimension;
        return iHashCode2 + (num3 != null ? num3.hashCode() : 0);
    }

    @Override
    public void print(PrettyPrinter printer) {
        Intrinsics.checkNotNullParameter(printer, "printer");
        AttributeCollection.DefaultImpls.print(this, printer);
    }

    public PrinterVolumeSupported(Integer num, Integer num2, Integer num3) {
        this.xDimension = num;
        this.yDimension = num2;
        this.zDimension = num3;
    }

    public PrinterVolumeSupported(Integer num, Integer num2, Integer num3, int i, DefaultConstructorMarker defaultConstructorMarker) {
        if ((i & 1) != 0) {
            num = null;
        }
        if ((i & 2) != 0) {
            num2 = null;
        }
        if ((i & 4) != 0) {
            num3 = null;
        }
        this(num, num2, num3);
    }

    public final Integer getXDimension() {
        return this.xDimension;
    }

    public final void setXDimension(Integer num) {
        this.xDimension = num;
    }

    public final Integer getYDimension() {
        return this.yDimension;
    }

    public final void setYDimension(Integer num) {
        this.yDimension = num;
    }

    public final Integer getZDimension() {
        return this.zDimension;
    }

    public final void setZDimension(Integer num) {
        this.zDimension = num;
    }

    public PrinterVolumeSupported() {
        this(null, null, null);
    }

    @Override
    public List<Attribute<?>> getAttributes() {
        Attribute<Integer> attributeOf;
        Attribute<Integer> attributeOf2;
        Attribute[] attributeArr = new Attribute[3];
        Integer num = this.xDimension;
        Attribute<Integer> attributeOf3 = null;
        if (num != null) {
            attributeOf = xDimension.mo418of(Integer.valueOf(num.intValue()));
        } else {
            attributeOf = null;
        }
        attributeArr[0] = attributeOf;
        Integer num2 = this.yDimension;
        if (num2 != null) {
            attributeOf2 = yDimension.mo418of(Integer.valueOf(num2.intValue()));
        } else {
            attributeOf2 = null;
        }
        attributeArr[1] = attributeOf2;
        Integer num3 = this.zDimension;
        if (num3 != null) {
            attributeOf3 = zDimension.mo418of(Integer.valueOf(num3.intValue()));
        }
        attributeArr[2] = attributeOf3;
        return CollectionsKt.listOfNotNull((Object[]) attributeArr);
    }

    @Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003J\u001a\u0010\u000e\u001a\u00020\u00022\u0010\u0010\u000f\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00110\u0010H\u0016R\u0016\u0010\u0004\u001a\u00020\u00008\u0006X\u0087\u0004¢\u0006\b\n\u0000\u0012\u0004\b\u0005\u0010\u0003R\u001a\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00020\u0007X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0010\u0010\n\u001a\u00020\u000b8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u00020\u000b8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u00020\u000b8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000¨\u0006\u0012"}, m1293d2 = {"Lcom/hp/jipp/model/PrinterVolumeSupported$Companion;", "Lcom/hp/jipp/encoding/AttributeCollection$Converter;", "Lcom/hp/jipp/model/PrinterVolumeSupported;", "()V", "Types", "getTypes$annotations", "cls", "Ljava/lang/Class;", "getCls", "()Ljava/lang/Class;", "xDimension", "Lcom/hp/jipp/encoding/IntType;", "yDimension", "zDimension", "convert", "attributes", "", "Lcom/hp/jipp/encoding/Attribute;", "jipp-core"}, m1294k = 1, m1295mv = {1, 4, 0})
    public static final class Companion implements AttributeCollection.Converter<PrinterVolumeSupported> {
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
        public PrinterVolumeSupported convert(List<? extends Attribute<?>> attributes) {
            Intrinsics.checkNotNullParameter(attributes, "attributes");
            return new PrinterVolumeSupported((Integer) extractOne(attributes, PrinterVolumeSupported.xDimension), (Integer) extractOne(attributes, PrinterVolumeSupported.yDimension), (Integer) extractOne(attributes, PrinterVolumeSupported.zDimension));
        }

        @Override
        public Class<PrinterVolumeSupported> getCls() {
            return PrinterVolumeSupported.cls;
        }
    }

    static {
        Companion companion = new Companion(null);
        INSTANCE = companion;
        cls = PrinterVolumeSupported.class;
        Types = companion;
        xDimension = new IntType("x-dimension");
        yDimension = new IntType("y-dimension");
        zDimension = new IntType("z-dimension");
    }

    public String toString() {
        return "PrinterVolumeSupported(" + CollectionsKt.joinToString$default(getAttributes(), null, null, null, 0, null, null, 63, null) + ')';
    }
}
