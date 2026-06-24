package com.p020hp.jipp.model;

import com.p020hp.jipp.encoding.Attribute;
import com.p020hp.jipp.encoding.AttributeCollection;
import com.p020hp.jipp.encoding.AttributeType;
import com.p020hp.jipp.encoding.IntType;
import com.p020hp.jipp.encoding.KeywordType;
import com.p020hp.jipp.encoding.Name;
import com.p020hp.jipp.encoding.NameType;
import com.p020hp.jipp.util.PrettyPrinter;
import java.util.List;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0016\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0004\b\u0086\b\u0018\u0000 '2\u00020\u0001:\u0001'B\u0007\b\u0016¢\u0006\u0002\u0010\u0002B)\u0012\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0006¢\u0006\u0002\u0010\bJ\u0010\u0010\u001c\u001a\u0004\u0018\u00010\u0004HÆ\u0003¢\u0006\u0002\u0010\u000fJ\u000b\u0010\u001d\u001a\u0004\u0018\u00010\u0006HÆ\u0003J\u000b\u0010\u001e\u001a\u0004\u0018\u00010\u0006HÆ\u0003J2\u0010\u001f\u001a\u00020\u00002\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0006HÆ\u0001¢\u0006\u0002\u0010 J\u0013\u0010!\u001a\u00020\"2\b\u0010#\u001a\u0004\u0018\u00010$HÖ\u0003J\t\u0010%\u001a\u00020\u0004HÖ\u0001J\b\u0010&\u001a\u00020\u0006H\u0016R\u001e\u0010\t\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u000b0\n8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\rR \u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0012\u0010\u0013\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u001e\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0086\u000e¢\u0006\u0010\n\u0002\b\u0018\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R\u001e\u0010\u0007\u001a\u0004\u0018\u00010\u0006X\u0086\u000e¢\u0006\u0010\n\u0002\b\u001b\u001a\u0004\b\u0019\u0010\u0015\"\u0004\b\u001a\u0010\u0017¨\u0006("}, m1293d2 = {"Lcom/hp/jipp/model/PowerEventPolicyCol;", "Lcom/hp/jipp/encoding/AttributeCollection;", "()V", "eventId", "", "eventName", "", "requestPowerState", "(Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;)V", "attributes", "", "Lcom/hp/jipp/encoding/Attribute;", "getAttributes", "()Ljava/util/List;", "getEventId", "()Ljava/lang/Integer;", "setEventId", "(Ljava/lang/Integer;)V", "eventId$1", "Ljava/lang/Integer;", "getEventName", "()Ljava/lang/String;", "setEventName", "(Ljava/lang/String;)V", "eventName$1", "getRequestPowerState", "setRequestPowerState", "requestPowerState$1", "component1", "component2", "component3", PrinterServiceType.copy, "(Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;)Lcom/hp/jipp/model/PowerEventPolicyCol;", "equals", "", "other", "", "hashCode", "toString", "Companion", "jipp-core"}, m1294k = 1, m1295mv = {1, 4, 0})
public final class PowerEventPolicyCol implements AttributeCollection {

    public static final Companion INSTANCE;
    public static final Companion Types;
    private static final Class<PowerEventPolicyCol> cls;
    public static final IntType eventId;
    public static final NameType eventName;
    public static final KeywordType requestPowerState;

    private Integer eventId;

    private String eventName;

    private String requestPowerState;

    public static PowerEventPolicyCol copy$default(PowerEventPolicyCol powerEventPolicyCol, Integer num, String str, String str2, int i, Object obj) {
        if ((i & 1) != 0) {
            num = powerEventPolicyCol.eventId;
        }
        if ((i & 2) != 0) {
            str = powerEventPolicyCol.eventName;
        }
        if ((i & 4) != 0) {
            str2 = powerEventPolicyCol.requestPowerState;
        }
        return powerEventPolicyCol.copy(num, str, str2);
    }

    public final Integer getEventId() {
        return this.eventId;
    }

    public final String getEventName() {
        return this.eventName;
    }

    public final String getRequestPowerState() {
        return this.requestPowerState;
    }

    public final PowerEventPolicyCol copy(Integer eventId2, String eventName2, String requestPowerState2) {
        return new PowerEventPolicyCol(eventId2, eventName2, requestPowerState2);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof PowerEventPolicyCol)) {
            return false;
        }
        PowerEventPolicyCol powerEventPolicyCol = (PowerEventPolicyCol) other;
        return Intrinsics.areEqual(this.eventId, powerEventPolicyCol.eventId) && Intrinsics.areEqual(this.eventName, powerEventPolicyCol.eventName) && Intrinsics.areEqual(this.requestPowerState, powerEventPolicyCol.requestPowerState);
    }

    public int hashCode() {
        Integer num = this.eventId;
        int iHashCode = (num != null ? num.hashCode() : 0) * 31;
        String str = this.eventName;
        int iHashCode2 = (iHashCode + (str != null ? str.hashCode() : 0)) * 31;
        String str2 = this.requestPowerState;
        return iHashCode2 + (str2 != null ? str2.hashCode() : 0);
    }

    @Override
    public void print(PrettyPrinter printer) {
        Intrinsics.checkNotNullParameter(printer, "printer");
        AttributeCollection.DefaultImpls.print(this, printer);
    }

    public PowerEventPolicyCol(Integer num, String str, String str2) {
        this.eventId = num;
        this.eventName = str;
        this.requestPowerState = str2;
    }

    public PowerEventPolicyCol(Integer num, String str, String str2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        if ((i & 1) != 0) {
            num = null;
        }
        if ((i & 2) != 0) {
            str = null;
        }
        if ((i & 4) != 0) {
            str2 = null;
        }
        this(num, str, str2);
    }

    public final Integer getEventId() {
        return this.eventId;
    }

    public final void setEventId(Integer num) {
        this.eventId = num;
    }

    public final String getEventName() {
        return this.eventName;
    }

    public final void setEventName(String str) {
        this.eventName = str;
    }

    public final String getRequestPowerState() {
        return this.requestPowerState;
    }

    public final void setRequestPowerState(String str) {
        this.requestPowerState = str;
    }

    public PowerEventPolicyCol() {
        this(null, null, null);
    }

    @Override
    public List<Attribute<?>> getAttributes() {
        Attribute[] attributeArr = new Attribute[3];
        Integer num = this.eventId;
        attributeArr[0] = num != null ? eventId.mo418of(Integer.valueOf(num.intValue())) : null;
        String str = this.eventName;
        attributeArr[1] = str != null ? eventName.m440of(str) : null;
        String str2 = this.requestPowerState;
        attributeArr[2] = str2 != null ? requestPowerState.mo418of(str2) : null;
        return CollectionsKt.listOfNotNull((Object[]) attributeArr);
    }

    @Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003J\u001a\u0010\u0010\u001a\u00020\u00022\u0010\u0010\u0011\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00130\u0012H\u0016R\u0016\u0010\u0004\u001a\u00020\u00008\u0006X\u0087\u0004¢\u0006\b\n\u0000\u0012\u0004\b\u0005\u0010\u0003R\u001a\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00020\u0007X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0010\u0010\n\u001a\u00020\u000b8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u00020\r8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u00020\u000f8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000¨\u0006\u0014"}, m1293d2 = {"Lcom/hp/jipp/model/PowerEventPolicyCol$Companion;", "Lcom/hp/jipp/encoding/AttributeCollection$Converter;", "Lcom/hp/jipp/model/PowerEventPolicyCol;", "()V", "Types", "getTypes$annotations", "cls", "Ljava/lang/Class;", "getCls", "()Ljava/lang/Class;", "eventId", "Lcom/hp/jipp/encoding/IntType;", "eventName", "Lcom/hp/jipp/encoding/NameType;", "requestPowerState", "Lcom/hp/jipp/encoding/KeywordType;", "convert", "attributes", "", "Lcom/hp/jipp/encoding/Attribute;", "jipp-core"}, m1294k = 1, m1295mv = {1, 4, 0})
    public static final class Companion implements AttributeCollection.Converter<PowerEventPolicyCol> {
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
        public PowerEventPolicyCol convert(List<? extends Attribute<?>> attributes) {
            Intrinsics.checkNotNullParameter(attributes, "attributes");
            Integer num = (Integer) extractOne(attributes, PowerEventPolicyCol.eventId);
            Name name = (Name) extractOne(attributes, PowerEventPolicyCol.eventName);
            return new PowerEventPolicyCol(num, name != null ? name.getValue() : null, (String) extractOne(attributes, PowerEventPolicyCol.requestPowerState));
        }

        @Override
        public Class<PowerEventPolicyCol> getCls() {
            return PowerEventPolicyCol.cls;
        }
    }

    static {
        Companion companion = new Companion(null);
        INSTANCE = companion;
        cls = PowerEventPolicyCol.class;
        Types = companion;
        eventId = new IntType("event-id");
        eventName = new NameType("event-name");
        requestPowerState = new KeywordType("request-power-state");
    }

    public String toString() {
        return "PowerEventPolicyCol(" + CollectionsKt.joinToString$default(getAttributes(), null, null, null, 0, null, null, 63, null) + ')';
    }
}
