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

@Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0018\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\b\u0018\u0000 *2\u00020\u0001:\u0001*B\u0007\b\u0016¢\u0006\u0002\u0010\u0002B5\u0012\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0004¢\u0006\u0002\u0010\bJ\u0010\u0010\u001d\u001a\u0004\u0018\u00010\u0004HÆ\u0003¢\u0006\u0002\u0010\u000fJ\u0010\u0010\u001e\u001a\u0004\u0018\u00010\u0004HÆ\u0003¢\u0006\u0002\u0010\u000fJ\u0010\u0010\u001f\u001a\u0004\u0018\u00010\u0004HÆ\u0003¢\u0006\u0002\u0010\u000fJ\u0010\u0010 \u001a\u0004\u0018\u00010\u0004HÆ\u0003¢\u0006\u0002\u0010\u000fJ>\u0010!\u001a\u00020\u00002\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0004HÆ\u0001¢\u0006\u0002\u0010\"J\u0013\u0010#\u001a\u00020$2\b\u0010%\u001a\u0004\u0018\u00010&HÖ\u0003J\t\u0010'\u001a\u00020\u0004HÖ\u0001J\b\u0010(\u001a\u00020)H\u0016R\u001e\u0010\t\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u000b0\n8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\rR \u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0012\u0010\u0013\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R \u0010\u0005\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0016\u0010\u0013\u001a\u0004\b\u0014\u0010\u000f\"\u0004\b\u0015\u0010\u0011R \u0010\u0006\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0019\u0010\u0013\u001a\u0004\b\u0017\u0010\u000f\"\u0004\b\u0018\u0010\u0011R \u0010\u0007\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u0012\n\u0004\b\u001c\u0010\u0013\u001a\u0004\b\u001a\u0010\u000f\"\u0004\b\u001b\u0010\u0011¨\u0006+"}, m1293d2 = {"Lcom/hp/jipp/model/PowerStateCountersCol;", "Lcom/hp/jipp/encoding/AttributeCollection;", "()V", "hibernateTransitions", "", "onTransitions", "standbyTransitions", "suspendTransitions", "(Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;)V", "attributes", "", "Lcom/hp/jipp/encoding/Attribute;", "getAttributes", "()Ljava/util/List;", "getHibernateTransitions", "()Ljava/lang/Integer;", "setHibernateTransitions", "(Ljava/lang/Integer;)V", "hibernateTransitions$1", "Ljava/lang/Integer;", "getOnTransitions", "setOnTransitions", "onTransitions$1", "getStandbyTransitions", "setStandbyTransitions", "standbyTransitions$1", "getSuspendTransitions", "setSuspendTransitions", "suspendTransitions$1", "component1", "component2", "component3", "component4", PrinterServiceType.copy, "(Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;)Lcom/hp/jipp/model/PowerStateCountersCol;", "equals", "", "other", "", "hashCode", "toString", "", "Companion", "jipp-core"}, m1294k = 1, m1295mv = {1, 4, 0})
public final class PowerStateCountersCol implements AttributeCollection {

    public static final Companion INSTANCE;
    public static final Companion Types;
    private static final Class<PowerStateCountersCol> cls;
    public static final IntType hibernateTransitions;
    public static final IntType onTransitions;
    public static final IntType standbyTransitions;
    public static final IntType suspendTransitions;

    private Integer hibernateTransitions;

    private Integer onTransitions;

    private Integer standbyTransitions;

    private Integer suspendTransitions;

    public static PowerStateCountersCol copy$default(PowerStateCountersCol powerStateCountersCol, Integer num, Integer num2, Integer num3, Integer num4, int i, Object obj) {
        if ((i & 1) != 0) {
            num = powerStateCountersCol.hibernateTransitions;
        }
        if ((i & 2) != 0) {
            num2 = powerStateCountersCol.onTransitions;
        }
        if ((i & 4) != 0) {
            num3 = powerStateCountersCol.standbyTransitions;
        }
        if ((i & 8) != 0) {
            num4 = powerStateCountersCol.suspendTransitions;
        }
        return powerStateCountersCol.copy(num, num2, num3, num4);
    }

    public final Integer getHibernateTransitions() {
        return this.hibernateTransitions;
    }

    public final Integer getOnTransitions() {
        return this.onTransitions;
    }

    public final Integer getStandbyTransitions() {
        return this.standbyTransitions;
    }

    public final Integer getSuspendTransitions() {
        return this.suspendTransitions;
    }

    public final PowerStateCountersCol copy(Integer hibernateTransitions2, Integer onTransitions2, Integer standbyTransitions2, Integer suspendTransitions2) {
        return new PowerStateCountersCol(hibernateTransitions2, onTransitions2, standbyTransitions2, suspendTransitions2);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof PowerStateCountersCol)) {
            return false;
        }
        PowerStateCountersCol powerStateCountersCol = (PowerStateCountersCol) other;
        return Intrinsics.areEqual(this.hibernateTransitions, powerStateCountersCol.hibernateTransitions) && Intrinsics.areEqual(this.onTransitions, powerStateCountersCol.onTransitions) && Intrinsics.areEqual(this.standbyTransitions, powerStateCountersCol.standbyTransitions) && Intrinsics.areEqual(this.suspendTransitions, powerStateCountersCol.suspendTransitions);
    }

    public int hashCode() {
        Integer num = this.hibernateTransitions;
        int iHashCode = (num != null ? num.hashCode() : 0) * 31;
        Integer num2 = this.onTransitions;
        int iHashCode2 = (iHashCode + (num2 != null ? num2.hashCode() : 0)) * 31;
        Integer num3 = this.standbyTransitions;
        int iHashCode3 = (iHashCode2 + (num3 != null ? num3.hashCode() : 0)) * 31;
        Integer num4 = this.suspendTransitions;
        return iHashCode3 + (num4 != null ? num4.hashCode() : 0);
    }

    @Override
    public void print(PrettyPrinter printer) {
        Intrinsics.checkNotNullParameter(printer, "printer");
        AttributeCollection.DefaultImpls.print(this, printer);
    }

    public PowerStateCountersCol(Integer num, Integer num2, Integer num3, Integer num4) {
        this.hibernateTransitions = num;
        this.onTransitions = num2;
        this.standbyTransitions = num3;
        this.suspendTransitions = num4;
    }

    public PowerStateCountersCol(Integer num, Integer num2, Integer num3, Integer num4, int i, DefaultConstructorMarker defaultConstructorMarker) {
        if ((i & 1) != 0) {
            num = null;
        }
        if ((i & 2) != 0) {
            num2 = null;
        }
        if ((i & 4) != 0) {
            num3 = null;
        }
        if ((i & 8) != 0) {
            num4 = null;
        }
        this(num, num2, num3, num4);
    }

    public final Integer getHibernateTransitions() {
        return this.hibernateTransitions;
    }

    public final void setHibernateTransitions(Integer num) {
        this.hibernateTransitions = num;
    }

    public final Integer getOnTransitions() {
        return this.onTransitions;
    }

    public final void setOnTransitions(Integer num) {
        this.onTransitions = num;
    }

    public final Integer getStandbyTransitions() {
        return this.standbyTransitions;
    }

    public final void setStandbyTransitions(Integer num) {
        this.standbyTransitions = num;
    }

    public final Integer getSuspendTransitions() {
        return this.suspendTransitions;
    }

    public final void setSuspendTransitions(Integer num) {
        this.suspendTransitions = num;
    }

    public PowerStateCountersCol() {
        this(null, null, null, null);
    }

    @Override
    public List<Attribute<?>> getAttributes() {
        Attribute<Integer> attributeOf;
        Attribute<Integer> attributeOf2;
        Attribute<Integer> attributeOf3;
        Attribute[] attributeArr = new Attribute[4];
        Integer num = this.hibernateTransitions;
        Attribute<Integer> attributeOf4 = null;
        if (num != null) {
            attributeOf = hibernateTransitions.mo418of(Integer.valueOf(num.intValue()));
        } else {
            attributeOf = null;
        }
        attributeArr[0] = attributeOf;
        Integer num2 = this.onTransitions;
        if (num2 != null) {
            attributeOf2 = onTransitions.mo418of(Integer.valueOf(num2.intValue()));
        } else {
            attributeOf2 = null;
        }
        attributeArr[1] = attributeOf2;
        Integer num3 = this.standbyTransitions;
        if (num3 != null) {
            attributeOf3 = standbyTransitions.mo418of(Integer.valueOf(num3.intValue()));
        } else {
            attributeOf3 = null;
        }
        attributeArr[2] = attributeOf3;
        Integer num4 = this.suspendTransitions;
        if (num4 != null) {
            attributeOf4 = suspendTransitions.mo418of(Integer.valueOf(num4.intValue()));
        }
        attributeArr[3] = attributeOf4;
        return CollectionsKt.listOfNotNull((Object[]) attributeArr);
    }

    @Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003J\u001a\u0010\u000f\u001a\u00020\u00022\u0010\u0010\u0010\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00120\u0011H\u0016R\u0016\u0010\u0004\u001a\u00020\u00008\u0006X\u0087\u0004¢\u0006\b\n\u0000\u0012\u0004\b\u0005\u0010\u0003R\u001a\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00020\u0007X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0010\u0010\n\u001a\u00020\u000b8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u00020\u000b8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u00020\u000b8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u00020\u000b8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000¨\u0006\u0013"}, m1293d2 = {"Lcom/hp/jipp/model/PowerStateCountersCol$Companion;", "Lcom/hp/jipp/encoding/AttributeCollection$Converter;", "Lcom/hp/jipp/model/PowerStateCountersCol;", "()V", "Types", "getTypes$annotations", "cls", "Ljava/lang/Class;", "getCls", "()Ljava/lang/Class;", "hibernateTransitions", "Lcom/hp/jipp/encoding/IntType;", "onTransitions", "standbyTransitions", "suspendTransitions", "convert", "attributes", "", "Lcom/hp/jipp/encoding/Attribute;", "jipp-core"}, m1294k = 1, m1295mv = {1, 4, 0})
    public static final class Companion implements AttributeCollection.Converter<PowerStateCountersCol> {
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
        public PowerStateCountersCol convert(List<? extends Attribute<?>> attributes) {
            Intrinsics.checkNotNullParameter(attributes, "attributes");
            return new PowerStateCountersCol((Integer) extractOne(attributes, PowerStateCountersCol.hibernateTransitions), (Integer) extractOne(attributes, PowerStateCountersCol.onTransitions), (Integer) extractOne(attributes, PowerStateCountersCol.standbyTransitions), (Integer) extractOne(attributes, PowerStateCountersCol.suspendTransitions));
        }

        @Override
        public Class<PowerStateCountersCol> getCls() {
            return PowerStateCountersCol.cls;
        }
    }

    static {
        Companion companion = new Companion(null);
        INSTANCE = companion;
        cls = PowerStateCountersCol.class;
        Types = companion;
        hibernateTransitions = new IntType("hibernate-transitions");
        onTransitions = new IntType("on-transitions");
        standbyTransitions = new IntType("standby-transitions");
        suspendTransitions = new IntType("suspend-transitions");
    }

    public String toString() {
        return "PowerStateCountersCol(" + CollectionsKt.joinToString$default(getAttributes(), null, null, null, 0, null, null, 63, null) + ')';
    }
}
