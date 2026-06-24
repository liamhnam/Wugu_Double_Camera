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

@Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\b\u0018\u0000  2\u00020\u0001:\u0001 B\u0007\b\u0016¢\u0006\u0002\u0010\u0002B\u001d\u0012\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0004¢\u0006\u0002\u0010\u0006J\u0010\u0010\u0015\u001a\u0004\u0018\u00010\u0004HÆ\u0003¢\u0006\u0002\u0010\rJ\u0010\u0010\u0016\u001a\u0004\u0018\u00010\u0004HÆ\u0003¢\u0006\u0002\u0010\rJ&\u0010\u0017\u001a\u00020\u00002\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0004HÆ\u0001¢\u0006\u0002\u0010\u0018J\u0013\u0010\u0019\u001a\u00020\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\u001cHÖ\u0003J\t\u0010\u001d\u001a\u00020\u0004HÖ\u0001J\b\u0010\u001e\u001a\u00020\u001fH\u0016R\u001e\u0010\u0007\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\t0\b8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\u000bR \u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0010\u0010\u0011\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR \u0010\u0005\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0014\u0010\u0011\u001a\u0004\b\u0012\u0010\r\"\u0004\b\u0013\u0010\u000f¨\u0006!"}, m1293d2 = {"Lcom/hp/jipp/model/OutputAttributes;", "Lcom/hp/jipp/encoding/AttributeCollection;", "()V", "noiseRemoval", "", "outputCompressionQualityFactor", "(Ljava/lang/Integer;Ljava/lang/Integer;)V", "attributes", "", "Lcom/hp/jipp/encoding/Attribute;", "getAttributes", "()Ljava/util/List;", "getNoiseRemoval", "()Ljava/lang/Integer;", "setNoiseRemoval", "(Ljava/lang/Integer;)V", "noiseRemoval$1", "Ljava/lang/Integer;", "getOutputCompressionQualityFactor", "setOutputCompressionQualityFactor", "outputCompressionQualityFactor$1", "component1", "component2", PrinterServiceType.copy, "(Ljava/lang/Integer;Ljava/lang/Integer;)Lcom/hp/jipp/model/OutputAttributes;", "equals", "", "other", "", "hashCode", "toString", "", "Companion", "jipp-core"}, m1294k = 1, m1295mv = {1, 4, 0})
public final class OutputAttributes implements AttributeCollection {

    public static final Companion INSTANCE;
    public static final Companion Types;
    private static final Class<OutputAttributes> cls;
    public static final IntType noiseRemoval;
    public static final IntType outputCompressionQualityFactor;

    private Integer noiseRemoval;

    private Integer outputCompressionQualityFactor;

    public static OutputAttributes copy$default(OutputAttributes outputAttributes, Integer num, Integer num2, int i, Object obj) {
        if ((i & 1) != 0) {
            num = outputAttributes.noiseRemoval;
        }
        if ((i & 2) != 0) {
            num2 = outputAttributes.outputCompressionQualityFactor;
        }
        return outputAttributes.copy(num, num2);
    }

    public final Integer getNoiseRemoval() {
        return this.noiseRemoval;
    }

    public final Integer getOutputCompressionQualityFactor() {
        return this.outputCompressionQualityFactor;
    }

    public final OutputAttributes copy(Integer noiseRemoval2, Integer outputCompressionQualityFactor2) {
        return new OutputAttributes(noiseRemoval2, outputCompressionQualityFactor2);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof OutputAttributes)) {
            return false;
        }
        OutputAttributes outputAttributes = (OutputAttributes) other;
        return Intrinsics.areEqual(this.noiseRemoval, outputAttributes.noiseRemoval) && Intrinsics.areEqual(this.outputCompressionQualityFactor, outputAttributes.outputCompressionQualityFactor);
    }

    public int hashCode() {
        Integer num = this.noiseRemoval;
        int iHashCode = (num != null ? num.hashCode() : 0) * 31;
        Integer num2 = this.outputCompressionQualityFactor;
        return iHashCode + (num2 != null ? num2.hashCode() : 0);
    }

    @Override
    public void print(PrettyPrinter printer) {
        Intrinsics.checkNotNullParameter(printer, "printer");
        AttributeCollection.DefaultImpls.print(this, printer);
    }

    public OutputAttributes(Integer num, Integer num2) {
        this.noiseRemoval = num;
        this.outputCompressionQualityFactor = num2;
    }

    public OutputAttributes(Integer num, Integer num2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        if ((i & 1) != 0) {
            num = null;
        }
        if ((i & 2) != 0) {
            num2 = null;
        }
        this(num, num2);
    }

    public final Integer getNoiseRemoval() {
        return this.noiseRemoval;
    }

    public final void setNoiseRemoval(Integer num) {
        this.noiseRemoval = num;
    }

    public final Integer getOutputCompressionQualityFactor() {
        return this.outputCompressionQualityFactor;
    }

    public final void setOutputCompressionQualityFactor(Integer num) {
        this.outputCompressionQualityFactor = num;
    }

    public OutputAttributes() {
        this(null, null);
    }

    @Override
    public List<Attribute<?>> getAttributes() {
        Attribute<Integer> attributeOf;
        Attribute[] attributeArr = new Attribute[2];
        Integer num = this.noiseRemoval;
        Attribute<Integer> attributeOf2 = null;
        if (num != null) {
            attributeOf = noiseRemoval.mo418of(Integer.valueOf(num.intValue()));
        } else {
            attributeOf = null;
        }
        attributeArr[0] = attributeOf;
        Integer num2 = this.outputCompressionQualityFactor;
        if (num2 != null) {
            attributeOf2 = outputCompressionQualityFactor.mo418of(Integer.valueOf(num2.intValue()));
        }
        attributeArr[1] = attributeOf2;
        return CollectionsKt.listOfNotNull((Object[]) attributeArr);
    }

    @Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003J\u001a\u0010\r\u001a\u00020\u00022\u0010\u0010\u000e\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00100\u000fH\u0016R\u0016\u0010\u0004\u001a\u00020\u00008\u0006X\u0087\u0004¢\u0006\b\n\u0000\u0012\u0004\b\u0005\u0010\u0003R\u001a\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00020\u0007X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0010\u0010\n\u001a\u00020\u000b8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u00020\u000b8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000¨\u0006\u0011"}, m1293d2 = {"Lcom/hp/jipp/model/OutputAttributes$Companion;", "Lcom/hp/jipp/encoding/AttributeCollection$Converter;", "Lcom/hp/jipp/model/OutputAttributes;", "()V", "Types", "getTypes$annotations", "cls", "Ljava/lang/Class;", "getCls", "()Ljava/lang/Class;", "noiseRemoval", "Lcom/hp/jipp/encoding/IntType;", "outputCompressionQualityFactor", "convert", "attributes", "", "Lcom/hp/jipp/encoding/Attribute;", "jipp-core"}, m1294k = 1, m1295mv = {1, 4, 0})
    public static final class Companion implements AttributeCollection.Converter<OutputAttributes> {
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
        public OutputAttributes convert(List<? extends Attribute<?>> attributes) {
            Intrinsics.checkNotNullParameter(attributes, "attributes");
            return new OutputAttributes((Integer) extractOne(attributes, OutputAttributes.noiseRemoval), (Integer) extractOne(attributes, OutputAttributes.outputCompressionQualityFactor));
        }

        @Override
        public Class<OutputAttributes> getCls() {
            return OutputAttributes.cls;
        }
    }

    static {
        Companion companion = new Companion(null);
        INSTANCE = companion;
        cls = OutputAttributes.class;
        Types = companion;
        noiseRemoval = new IntType("noise-removal");
        outputCompressionQualityFactor = new IntType("output-compression-quality-factor");
    }

    public String toString() {
        return "OutputAttributes(" + CollectionsKt.joinToString$default(getAttributes(), null, null, null, 0, null, null, 63, null) + ')';
    }
}
