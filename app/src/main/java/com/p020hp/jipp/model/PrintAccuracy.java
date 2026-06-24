package com.p020hp.jipp.model;

import com.p020hp.jipp.encoding.Attribute;
import com.p020hp.jipp.encoding.AttributeCollection;
import com.p020hp.jipp.encoding.AttributeType;
import com.p020hp.jipp.encoding.IntType;
import com.p020hp.jipp.encoding.KeywordType;
import com.p020hp.jipp.util.PrettyPrinter;
import java.util.List;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0015\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0004\b\u0086\b\u0018\u0000 ,2\u00020\u0001:\u0001,B\u0007\b\u0016¢\u0006\u0002\u0010\u0002B5\u0012\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0006¢\u0006\u0002\u0010\tJ\u000b\u0010 \u001a\u0004\u0018\u00010\u0004HÆ\u0003J\u0010\u0010!\u001a\u0004\u0018\u00010\u0006HÆ\u0003¢\u0006\u0002\u0010\u0015J\u0010\u0010\"\u001a\u0004\u0018\u00010\u0006HÆ\u0003¢\u0006\u0002\u0010\u0015J\u0010\u0010#\u001a\u0004\u0018\u00010\u0006HÆ\u0003¢\u0006\u0002\u0010\u0015J>\u0010$\u001a\u00020\u00002\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0006HÆ\u0001¢\u0006\u0002\u0010%J\u0013\u0010&\u001a\u00020'2\b\u0010(\u001a\u0004\u0018\u00010)HÖ\u0003J\t\u0010*\u001a\u00020\u0006HÖ\u0001J\b\u0010+\u001a\u00020\u0004H\u0016R\u001e\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u0010\n\u0002\b\u000e\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR\u001e\u0010\u000f\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00110\u00108VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0013R \u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0018\u0010\u0019\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R \u0010\u0007\u001a\u0004\u0018\u00010\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u001c\u0010\u0019\u001a\u0004\b\u001a\u0010\u0015\"\u0004\b\u001b\u0010\u0017R \u0010\b\u001a\u0004\u0018\u00010\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u001f\u0010\u0019\u001a\u0004\b\u001d\u0010\u0015\"\u0004\b\u001e\u0010\u0017¨\u0006-"}, m1293d2 = {"Lcom/hp/jipp/model/PrintAccuracy;", "Lcom/hp/jipp/encoding/AttributeCollection;", "()V", "accuracyUnits", "", "xAccuracy", "", "yAccuracy", "zAccuracy", "(Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;)V", "getAccuracyUnits", "()Ljava/lang/String;", "setAccuracyUnits", "(Ljava/lang/String;)V", "accuracyUnits$1", "attributes", "", "Lcom/hp/jipp/encoding/Attribute;", "getAttributes", "()Ljava/util/List;", "getXAccuracy", "()Ljava/lang/Integer;", "setXAccuracy", "(Ljava/lang/Integer;)V", "xAccuracy$1", "Ljava/lang/Integer;", "getYAccuracy", "setYAccuracy", "yAccuracy$1", "getZAccuracy", "setZAccuracy", "zAccuracy$1", "component1", "component2", "component3", "component4", PrinterServiceType.copy, "(Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;)Lcom/hp/jipp/model/PrintAccuracy;", "equals", "", "other", "", "hashCode", "toString", "Companion", "jipp-core"}, m1294k = 1, m1295mv = {1, 4, 0})
public final class PrintAccuracy implements AttributeCollection {

    public static final Companion INSTANCE;
    public static final Companion Types;
    public static final KeywordType accuracyUnits;
    private static final Class<PrintAccuracy> cls;
    public static final IntType xAccuracy;
    public static final IntType yAccuracy;
    public static final IntType zAccuracy;

    private String accuracyUnits;

    private Integer xAccuracy;

    private Integer yAccuracy;

    private Integer zAccuracy;

    public static PrintAccuracy copy$default(PrintAccuracy printAccuracy, String str, Integer num, Integer num2, Integer num3, int i, Object obj) {
        if ((i & 1) != 0) {
            str = printAccuracy.accuracyUnits;
        }
        if ((i & 2) != 0) {
            num = printAccuracy.xAccuracy;
        }
        if ((i & 4) != 0) {
            num2 = printAccuracy.yAccuracy;
        }
        if ((i & 8) != 0) {
            num3 = printAccuracy.zAccuracy;
        }
        return printAccuracy.copy(str, num, num2, num3);
    }

    public final String getAccuracyUnits() {
        return this.accuracyUnits;
    }

    public final Integer getXAccuracy() {
        return this.xAccuracy;
    }

    public final Integer getYAccuracy() {
        return this.yAccuracy;
    }

    public final Integer getZAccuracy() {
        return this.zAccuracy;
    }

    public final PrintAccuracy copy(String accuracyUnits2, Integer xAccuracy2, Integer yAccuracy2, Integer zAccuracy2) {
        return new PrintAccuracy(accuracyUnits2, xAccuracy2, yAccuracy2, zAccuracy2);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof PrintAccuracy)) {
            return false;
        }
        PrintAccuracy printAccuracy = (PrintAccuracy) other;
        return Intrinsics.areEqual(this.accuracyUnits, printAccuracy.accuracyUnits) && Intrinsics.areEqual(this.xAccuracy, printAccuracy.xAccuracy) && Intrinsics.areEqual(this.yAccuracy, printAccuracy.yAccuracy) && Intrinsics.areEqual(this.zAccuracy, printAccuracy.zAccuracy);
    }

    public int hashCode() {
        String str = this.accuracyUnits;
        int iHashCode = (str != null ? str.hashCode() : 0) * 31;
        Integer num = this.xAccuracy;
        int iHashCode2 = (iHashCode + (num != null ? num.hashCode() : 0)) * 31;
        Integer num2 = this.yAccuracy;
        int iHashCode3 = (iHashCode2 + (num2 != null ? num2.hashCode() : 0)) * 31;
        Integer num3 = this.zAccuracy;
        return iHashCode3 + (num3 != null ? num3.hashCode() : 0);
    }

    @Override
    public void print(PrettyPrinter printer) {
        Intrinsics.checkNotNullParameter(printer, "printer");
        AttributeCollection.DefaultImpls.print(this, printer);
    }

    public PrintAccuracy(String str, Integer num, Integer num2, Integer num3) {
        this.accuracyUnits = str;
        this.xAccuracy = num;
        this.yAccuracy = num2;
        this.zAccuracy = num3;
    }

    public PrintAccuracy(String str, Integer num, Integer num2, Integer num3, int i, DefaultConstructorMarker defaultConstructorMarker) {
        if ((i & 1) != 0) {
            str = null;
        }
        if ((i & 2) != 0) {
            num = null;
        }
        if ((i & 4) != 0) {
            num2 = null;
        }
        if ((i & 8) != 0) {
            num3 = null;
        }
        this(str, num, num2, num3);
    }

    public final String getAccuracyUnits() {
        return this.accuracyUnits;
    }

    public final void setAccuracyUnits(String str) {
        this.accuracyUnits = str;
    }

    public final Integer getXAccuracy() {
        return this.xAccuracy;
    }

    public final void setXAccuracy(Integer num) {
        this.xAccuracy = num;
    }

    public final Integer getYAccuracy() {
        return this.yAccuracy;
    }

    public final void setYAccuracy(Integer num) {
        this.yAccuracy = num;
    }

    public final Integer getZAccuracy() {
        return this.zAccuracy;
    }

    public final void setZAccuracy(Integer num) {
        this.zAccuracy = num;
    }

    public PrintAccuracy() {
        this(null, null, null, null);
    }

    @Override
    public List<Attribute<?>> getAttributes() {
        Attribute[] attributeArr = new Attribute[4];
        String str = this.accuracyUnits;
        attributeArr[0] = str != null ? accuracyUnits.mo418of(str) : null;
        Integer num = this.xAccuracy;
        attributeArr[1] = num != null ? xAccuracy.mo418of(Integer.valueOf(num.intValue())) : null;
        Integer num2 = this.yAccuracy;
        attributeArr[2] = num2 != null ? yAccuracy.mo418of(Integer.valueOf(num2.intValue())) : null;
        Integer num3 = this.zAccuracy;
        attributeArr[3] = num3 != null ? zAccuracy.mo418of(Integer.valueOf(num3.intValue())) : null;
        return CollectionsKt.listOfNotNull((Object[]) attributeArr);
    }

    @Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003J\u001a\u0010\u0010\u001a\u00020\u00022\u0010\u0010\u0011\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00130\u0012H\u0016R\u0016\u0010\u0004\u001a\u00020\u00008\u0006X\u0087\u0004¢\u0006\b\n\u0000\u0012\u0004\b\u0005\u0010\u0003R\u0010\u0010\u0006\u001a\u00020\u00078\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00020\tX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0010\u0010\f\u001a\u00020\r8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u00020\r8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u000f\u001a\u00020\r8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000¨\u0006\u0014"}, m1293d2 = {"Lcom/hp/jipp/model/PrintAccuracy$Companion;", "Lcom/hp/jipp/encoding/AttributeCollection$Converter;", "Lcom/hp/jipp/model/PrintAccuracy;", "()V", "Types", "getTypes$annotations", "accuracyUnits", "Lcom/hp/jipp/encoding/KeywordType;", "cls", "Ljava/lang/Class;", "getCls", "()Ljava/lang/Class;", "xAccuracy", "Lcom/hp/jipp/encoding/IntType;", "yAccuracy", "zAccuracy", "convert", "attributes", "", "Lcom/hp/jipp/encoding/Attribute;", "jipp-core"}, m1294k = 1, m1295mv = {1, 4, 0})
    public static final class Companion implements AttributeCollection.Converter<PrintAccuracy> {
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
        public PrintAccuracy convert(List<? extends Attribute<?>> attributes) {
            Intrinsics.checkNotNullParameter(attributes, "attributes");
            return new PrintAccuracy((String) extractOne(attributes, PrintAccuracy.accuracyUnits), (Integer) extractOne(attributes, PrintAccuracy.xAccuracy), (Integer) extractOne(attributes, PrintAccuracy.yAccuracy), (Integer) extractOne(attributes, PrintAccuracy.zAccuracy));
        }

        @Override
        public Class<PrintAccuracy> getCls() {
            return PrintAccuracy.cls;
        }
    }

    static {
        Companion companion = new Companion(null);
        INSTANCE = companion;
        cls = PrintAccuracy.class;
        Types = companion;
        accuracyUnits = new KeywordType("accuracy-units");
        xAccuracy = new IntType("x-accuracy");
        yAccuracy = new IntType("y-accuracy");
        zAccuracy = new IntType("z-accuracy");
    }

    public String toString() {
        return "PrintAccuracy(" + CollectionsKt.joinToString$default(getAttributes(), null, null, null, 0, null, null, 63, null) + ')';
    }
}
