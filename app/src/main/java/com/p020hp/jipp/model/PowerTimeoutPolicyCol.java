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

@Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u001a\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0004\b\u0086\b\u0018\u0000 ,2\u00020\u0001:\u0001,B\u0007\b\u0016¢\u0006\u0002\u0010\u0002B5\u0012\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0006¢\u0006\u0002\u0010\tJ\u000b\u0010 \u001a\u0004\u0018\u00010\u0004HÆ\u0003J\u0010\u0010!\u001a\u0004\u0018\u00010\u0006HÆ\u0003¢\u0006\u0002\u0010\u0015J\u000b\u0010\"\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\u0010\u0010#\u001a\u0004\u0018\u00010\u0006HÆ\u0003¢\u0006\u0002\u0010\u0015J>\u0010$\u001a\u00020\u00002\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0006HÆ\u0001¢\u0006\u0002\u0010%J\u0013\u0010&\u001a\u00020'2\b\u0010(\u001a\u0004\u0018\u00010)HÖ\u0003J\t\u0010*\u001a\u00020\u0006HÖ\u0001J\b\u0010+\u001a\u00020\u0004H\u0016R\u001e\u0010\n\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\f0\u000b8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\r\u0010\u000eR\u001e\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u0010\n\u0002\b\u0013\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R \u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0018\u0010\u0019\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R\u001e\u0010\u0007\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u0010\n\u0002\b\u001c\u001a\u0004\b\u001a\u0010\u0010\"\u0004\b\u001b\u0010\u0012R \u0010\b\u001a\u0004\u0018\u00010\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u001f\u0010\u0019\u001a\u0004\b\u001d\u0010\u0015\"\u0004\b\u001e\u0010\u0017¨\u0006-"}, m1293d2 = {"Lcom/hp/jipp/model/PowerTimeoutPolicyCol;", "Lcom/hp/jipp/encoding/AttributeCollection;", "()V", "startPowerState", "", "timeoutId", "", "timeoutPredicate", "timeoutSeconds", "(Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/Integer;)V", "attributes", "", "Lcom/hp/jipp/encoding/Attribute;", "getAttributes", "()Ljava/util/List;", "getStartPowerState", "()Ljava/lang/String;", "setStartPowerState", "(Ljava/lang/String;)V", "startPowerState$1", "getTimeoutId", "()Ljava/lang/Integer;", "setTimeoutId", "(Ljava/lang/Integer;)V", "timeoutId$1", "Ljava/lang/Integer;", "getTimeoutPredicate", "setTimeoutPredicate", "timeoutPredicate$1", "getTimeoutSeconds", "setTimeoutSeconds", "timeoutSeconds$1", "component1", "component2", "component3", "component4", PrinterServiceType.copy, "(Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/Integer;)Lcom/hp/jipp/model/PowerTimeoutPolicyCol;", "equals", "", "other", "", "hashCode", "toString", "Companion", "jipp-core"}, m1294k = 1, m1295mv = {1, 4, 0})
public final class PowerTimeoutPolicyCol implements AttributeCollection {

    public static final Companion INSTANCE;
    public static final Companion Types;
    private static final Class<PowerTimeoutPolicyCol> cls;
    public static final KeywordType startPowerState;
    public static final IntType timeoutId;
    public static final KeywordType timeoutPredicate;
    public static final IntType timeoutSeconds;

    private String startPowerState;

    private Integer timeoutId;

    private String timeoutPredicate;

    private Integer timeoutSeconds;

    public static PowerTimeoutPolicyCol copy$default(PowerTimeoutPolicyCol powerTimeoutPolicyCol, String str, Integer num, String str2, Integer num2, int i, Object obj) {
        if ((i & 1) != 0) {
            str = powerTimeoutPolicyCol.startPowerState;
        }
        if ((i & 2) != 0) {
            num = powerTimeoutPolicyCol.timeoutId;
        }
        if ((i & 4) != 0) {
            str2 = powerTimeoutPolicyCol.timeoutPredicate;
        }
        if ((i & 8) != 0) {
            num2 = powerTimeoutPolicyCol.timeoutSeconds;
        }
        return powerTimeoutPolicyCol.copy(str, num, str2, num2);
    }

    public final String getStartPowerState() {
        return this.startPowerState;
    }

    public final Integer getTimeoutId() {
        return this.timeoutId;
    }

    public final String getTimeoutPredicate() {
        return this.timeoutPredicate;
    }

    public final Integer getTimeoutSeconds() {
        return this.timeoutSeconds;
    }

    public final PowerTimeoutPolicyCol copy(String startPowerState2, Integer timeoutId2, String timeoutPredicate2, Integer timeoutSeconds2) {
        return new PowerTimeoutPolicyCol(startPowerState2, timeoutId2, timeoutPredicate2, timeoutSeconds2);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof PowerTimeoutPolicyCol)) {
            return false;
        }
        PowerTimeoutPolicyCol powerTimeoutPolicyCol = (PowerTimeoutPolicyCol) other;
        return Intrinsics.areEqual(this.startPowerState, powerTimeoutPolicyCol.startPowerState) && Intrinsics.areEqual(this.timeoutId, powerTimeoutPolicyCol.timeoutId) && Intrinsics.areEqual(this.timeoutPredicate, powerTimeoutPolicyCol.timeoutPredicate) && Intrinsics.areEqual(this.timeoutSeconds, powerTimeoutPolicyCol.timeoutSeconds);
    }

    public int hashCode() {
        String str = this.startPowerState;
        int iHashCode = (str != null ? str.hashCode() : 0) * 31;
        Integer num = this.timeoutId;
        int iHashCode2 = (iHashCode + (num != null ? num.hashCode() : 0)) * 31;
        String str2 = this.timeoutPredicate;
        int iHashCode3 = (iHashCode2 + (str2 != null ? str2.hashCode() : 0)) * 31;
        Integer num2 = this.timeoutSeconds;
        return iHashCode3 + (num2 != null ? num2.hashCode() : 0);
    }

    @Override
    public void print(PrettyPrinter printer) {
        Intrinsics.checkNotNullParameter(printer, "printer");
        AttributeCollection.DefaultImpls.print(this, printer);
    }

    public PowerTimeoutPolicyCol(String str, Integer num, String str2, Integer num2) {
        this.startPowerState = str;
        this.timeoutId = num;
        this.timeoutPredicate = str2;
        this.timeoutSeconds = num2;
    }

    public PowerTimeoutPolicyCol(String str, Integer num, String str2, Integer num2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        if ((i & 1) != 0) {
            str = null;
        }
        if ((i & 2) != 0) {
            num = null;
        }
        if ((i & 4) != 0) {
            str2 = null;
        }
        if ((i & 8) != 0) {
            num2 = null;
        }
        this(str, num, str2, num2);
    }

    public final String getStartPowerState() {
        return this.startPowerState;
    }

    public final void setStartPowerState(String str) {
        this.startPowerState = str;
    }

    public final Integer getTimeoutId() {
        return this.timeoutId;
    }

    public final void setTimeoutId(Integer num) {
        this.timeoutId = num;
    }

    public final String getTimeoutPredicate() {
        return this.timeoutPredicate;
    }

    public final void setTimeoutPredicate(String str) {
        this.timeoutPredicate = str;
    }

    public final Integer getTimeoutSeconds() {
        return this.timeoutSeconds;
    }

    public final void setTimeoutSeconds(Integer num) {
        this.timeoutSeconds = num;
    }

    public PowerTimeoutPolicyCol() {
        this(null, null, null, null);
    }

    @Override
    public List<Attribute<?>> getAttributes() {
        Attribute[] attributeArr = new Attribute[4];
        String str = this.startPowerState;
        attributeArr[0] = str != null ? startPowerState.mo418of(str) : null;
        Integer num = this.timeoutId;
        attributeArr[1] = num != null ? timeoutId.mo418of(Integer.valueOf(num.intValue())) : null;
        String str2 = this.timeoutPredicate;
        attributeArr[2] = str2 != null ? timeoutPredicate.mo418of(str2) : null;
        Integer num2 = this.timeoutSeconds;
        attributeArr[3] = num2 != null ? timeoutSeconds.mo418of(Integer.valueOf(num2.intValue())) : null;
        return CollectionsKt.listOfNotNull((Object[]) attributeArr);
    }

    @Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003J\u001a\u0010\u0010\u001a\u00020\u00022\u0010\u0010\u0011\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00130\u0012H\u0016R\u0016\u0010\u0004\u001a\u00020\u00008\u0006X\u0087\u0004¢\u0006\b\n\u0000\u0012\u0004\b\u0005\u0010\u0003R\u001a\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00020\u0007X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0010\u0010\n\u001a\u00020\u000b8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u00020\r8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u00020\u000b8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u000f\u001a\u00020\r8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000¨\u0006\u0014"}, m1293d2 = {"Lcom/hp/jipp/model/PowerTimeoutPolicyCol$Companion;", "Lcom/hp/jipp/encoding/AttributeCollection$Converter;", "Lcom/hp/jipp/model/PowerTimeoutPolicyCol;", "()V", "Types", "getTypes$annotations", "cls", "Ljava/lang/Class;", "getCls", "()Ljava/lang/Class;", "startPowerState", "Lcom/hp/jipp/encoding/KeywordType;", "timeoutId", "Lcom/hp/jipp/encoding/IntType;", "timeoutPredicate", "timeoutSeconds", "convert", "attributes", "", "Lcom/hp/jipp/encoding/Attribute;", "jipp-core"}, m1294k = 1, m1295mv = {1, 4, 0})
    public static final class Companion implements AttributeCollection.Converter<PowerTimeoutPolicyCol> {
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
        public PowerTimeoutPolicyCol convert(List<? extends Attribute<?>> attributes) {
            Intrinsics.checkNotNullParameter(attributes, "attributes");
            return new PowerTimeoutPolicyCol((String) extractOne(attributes, PowerTimeoutPolicyCol.startPowerState), (Integer) extractOne(attributes, PowerTimeoutPolicyCol.timeoutId), (String) extractOne(attributes, PowerTimeoutPolicyCol.timeoutPredicate), (Integer) extractOne(attributes, PowerTimeoutPolicyCol.timeoutSeconds));
        }

        @Override
        public Class<PowerTimeoutPolicyCol> getCls() {
            return PowerTimeoutPolicyCol.cls;
        }
    }

    static {
        Companion companion = new Companion(null);
        INSTANCE = companion;
        cls = PowerTimeoutPolicyCol.class;
        Types = companion;
        startPowerState = new KeywordType("start-power-state");
        timeoutId = new IntType("timeout-id");
        timeoutPredicate = new KeywordType("timeout-predicate");
        timeoutSeconds = new IntType("timeout-seconds");
    }

    public String toString() {
        return "PowerTimeoutPolicyCol(" + CollectionsKt.joinToString$default(getAttributes(), null, null, null, 0, null, null, 63, null) + ')';
    }
}
