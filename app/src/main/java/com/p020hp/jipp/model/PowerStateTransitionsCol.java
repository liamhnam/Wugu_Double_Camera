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

@Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0016\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0004\b\u0086\b\u0018\u0000 '2\u00020\u0001:\u0001'B\u0007\b\u0016¢\u0006\u0002\u0010\u0002B)\u0012\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\u0002\u0010\bJ\u000b\u0010\u001c\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\u000b\u0010\u001d\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\u0010\u0010\u001e\u001a\u0004\u0018\u00010\u0007HÆ\u0003¢\u0006\u0002\u0010\u0017J2\u0010\u001f\u001a\u00020\u00002\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007HÆ\u0001¢\u0006\u0002\u0010 J\u0013\u0010!\u001a\u00020\"2\b\u0010#\u001a\u0004\u0018\u00010$HÖ\u0003J\t\u0010%\u001a\u00020\u0007HÖ\u0001J\b\u0010&\u001a\u00020\u0004H\u0016R\u001e\u0010\t\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u000b0\n8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\rR\u001e\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u0010\n\u0002\b\u0012\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u001e\u0010\u0005\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u0010\n\u0002\b\u0015\u001a\u0004\b\u0013\u0010\u000f\"\u0004\b\u0014\u0010\u0011R \u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0086\u000e¢\u0006\u0012\n\u0004\b\u001a\u0010\u001b\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019¨\u0006("}, m1293d2 = {"Lcom/hp/jipp/model/PowerStateTransitionsCol;", "Lcom/hp/jipp/encoding/AttributeCollection;", "()V", "endPowerState", "", "startPowerState", "stateTransitionSeconds", "", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;)V", "attributes", "", "Lcom/hp/jipp/encoding/Attribute;", "getAttributes", "()Ljava/util/List;", "getEndPowerState", "()Ljava/lang/String;", "setEndPowerState", "(Ljava/lang/String;)V", "endPowerState$1", "getStartPowerState", "setStartPowerState", "startPowerState$1", "getStateTransitionSeconds", "()Ljava/lang/Integer;", "setStateTransitionSeconds", "(Ljava/lang/Integer;)V", "stateTransitionSeconds$1", "Ljava/lang/Integer;", "component1", "component2", "component3", PrinterServiceType.copy, "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;)Lcom/hp/jipp/model/PowerStateTransitionsCol;", "equals", "", "other", "", "hashCode", "toString", "Companion", "jipp-core"}, m1294k = 1, m1295mv = {1, 4, 0})
public final class PowerStateTransitionsCol implements AttributeCollection {

    public static final Companion INSTANCE;
    public static final Companion Types;
    private static final Class<PowerStateTransitionsCol> cls;
    public static final KeywordType endPowerState;
    public static final KeywordType startPowerState;
    public static final IntType stateTransitionSeconds;

    private String endPowerState;

    private String startPowerState;

    private Integer stateTransitionSeconds;

    public static PowerStateTransitionsCol copy$default(PowerStateTransitionsCol powerStateTransitionsCol, String str, String str2, Integer num, int i, Object obj) {
        if ((i & 1) != 0) {
            str = powerStateTransitionsCol.endPowerState;
        }
        if ((i & 2) != 0) {
            str2 = powerStateTransitionsCol.startPowerState;
        }
        if ((i & 4) != 0) {
            num = powerStateTransitionsCol.stateTransitionSeconds;
        }
        return powerStateTransitionsCol.copy(str, str2, num);
    }

    public final String getEndPowerState() {
        return this.endPowerState;
    }

    public final String getStartPowerState() {
        return this.startPowerState;
    }

    public final Integer getStateTransitionSeconds() {
        return this.stateTransitionSeconds;
    }

    public final PowerStateTransitionsCol copy(String endPowerState2, String startPowerState2, Integer stateTransitionSeconds2) {
        return new PowerStateTransitionsCol(endPowerState2, startPowerState2, stateTransitionSeconds2);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof PowerStateTransitionsCol)) {
            return false;
        }
        PowerStateTransitionsCol powerStateTransitionsCol = (PowerStateTransitionsCol) other;
        return Intrinsics.areEqual(this.endPowerState, powerStateTransitionsCol.endPowerState) && Intrinsics.areEqual(this.startPowerState, powerStateTransitionsCol.startPowerState) && Intrinsics.areEqual(this.stateTransitionSeconds, powerStateTransitionsCol.stateTransitionSeconds);
    }

    public int hashCode() {
        String str = this.endPowerState;
        int iHashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.startPowerState;
        int iHashCode2 = (iHashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
        Integer num = this.stateTransitionSeconds;
        return iHashCode2 + (num != null ? num.hashCode() : 0);
    }

    @Override
    public void print(PrettyPrinter printer) {
        Intrinsics.checkNotNullParameter(printer, "printer");
        AttributeCollection.DefaultImpls.print(this, printer);
    }

    public PowerStateTransitionsCol(String str, String str2, Integer num) {
        this.endPowerState = str;
        this.startPowerState = str2;
        this.stateTransitionSeconds = num;
    }

    public PowerStateTransitionsCol(String str, String str2, Integer num, int i, DefaultConstructorMarker defaultConstructorMarker) {
        if ((i & 1) != 0) {
            str = null;
        }
        if ((i & 2) != 0) {
            str2 = null;
        }
        if ((i & 4) != 0) {
            num = null;
        }
        this(str, str2, num);
    }

    public final String getEndPowerState() {
        return this.endPowerState;
    }

    public final void setEndPowerState(String str) {
        this.endPowerState = str;
    }

    public final String getStartPowerState() {
        return this.startPowerState;
    }

    public final void setStartPowerState(String str) {
        this.startPowerState = str;
    }

    public final Integer getStateTransitionSeconds() {
        return this.stateTransitionSeconds;
    }

    public final void setStateTransitionSeconds(Integer num) {
        this.stateTransitionSeconds = num;
    }

    public PowerStateTransitionsCol() {
        this(null, null, null);
    }

    @Override
    public List<Attribute<?>> getAttributes() {
        Attribute[] attributeArr = new Attribute[3];
        String str = this.endPowerState;
        attributeArr[0] = str != null ? endPowerState.mo418of(str) : null;
        String str2 = this.startPowerState;
        attributeArr[1] = str2 != null ? startPowerState.mo418of(str2) : null;
        Integer num = this.stateTransitionSeconds;
        attributeArr[2] = num != null ? stateTransitionSeconds.mo418of(Integer.valueOf(num.intValue())) : null;
        return CollectionsKt.listOfNotNull((Object[]) attributeArr);
    }

    @Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003J\u001a\u0010\u000f\u001a\u00020\u00022\u0010\u0010\u0010\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00120\u0011H\u0016R\u0016\u0010\u0004\u001a\u00020\u00008\u0006X\u0087\u0004¢\u0006\b\n\u0000\u0012\u0004\b\u0005\u0010\u0003R\u001a\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00020\u0007X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0010\u0010\n\u001a\u00020\u000b8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u00020\u000b8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u00020\u000e8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000¨\u0006\u0013"}, m1293d2 = {"Lcom/hp/jipp/model/PowerStateTransitionsCol$Companion;", "Lcom/hp/jipp/encoding/AttributeCollection$Converter;", "Lcom/hp/jipp/model/PowerStateTransitionsCol;", "()V", "Types", "getTypes$annotations", "cls", "Ljava/lang/Class;", "getCls", "()Ljava/lang/Class;", "endPowerState", "Lcom/hp/jipp/encoding/KeywordType;", "startPowerState", "stateTransitionSeconds", "Lcom/hp/jipp/encoding/IntType;", "convert", "attributes", "", "Lcom/hp/jipp/encoding/Attribute;", "jipp-core"}, m1294k = 1, m1295mv = {1, 4, 0})
    public static final class Companion implements AttributeCollection.Converter<PowerStateTransitionsCol> {
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
        public PowerStateTransitionsCol convert(List<? extends Attribute<?>> attributes) {
            Intrinsics.checkNotNullParameter(attributes, "attributes");
            return new PowerStateTransitionsCol((String) extractOne(attributes, PowerStateTransitionsCol.endPowerState), (String) extractOne(attributes, PowerStateTransitionsCol.startPowerState), (Integer) extractOne(attributes, PowerStateTransitionsCol.stateTransitionSeconds));
        }

        @Override
        public Class<PowerStateTransitionsCol> getCls() {
            return PowerStateTransitionsCol.cls;
        }
    }

    static {
        Companion companion = new Companion(null);
        INSTANCE = companion;
        cls = PowerStateTransitionsCol.class;
        Types = companion;
        endPowerState = new KeywordType("end-power-state");
        startPowerState = new KeywordType("start-power-state");
        stateTransitionSeconds = new IntType("state-transition-seconds");
    }

    public String toString() {
        return "PowerStateTransitionsCol(" + CollectionsKt.joinToString$default(getAttributes(), null, null, null, 0, null, null, 63, null) + ')';
    }
}
