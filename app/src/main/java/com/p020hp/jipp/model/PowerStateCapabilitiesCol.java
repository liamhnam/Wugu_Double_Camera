package com.p020hp.jipp.model;

import com.p020hp.jipp.encoding.Attribute;
import com.p020hp.jipp.encoding.AttributeCollection;
import com.p020hp.jipp.encoding.AttributeType;
import com.p020hp.jipp.encoding.BooleanType;
import com.p020hp.jipp.encoding.IntType;
import com.p020hp.jipp.encoding.KeywordType;
import com.p020hp.jipp.util.PrettyPrinter;
import java.util.List;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\"\n\u0002\u0010\u0000\n\u0002\b\u0004\b\u0086\b\u0018\u0000 42\u00020\u0001:\u00014B\u0007\b\u0016¢\u0006\u0002\u0010\u0002BA\u0012\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n¢\u0006\u0002\u0010\u000bJ\u0010\u0010(\u001a\u0004\u0018\u00010\u0004HÆ\u0003¢\u0006\u0002\u0010\u0012J\u0010\u0010)\u001a\u0004\u0018\u00010\u0004HÆ\u0003¢\u0006\u0002\u0010\u0012J\u0010\u0010*\u001a\u0004\u0018\u00010\u0007HÆ\u0003¢\u0006\u0002\u0010\u001bJ\u0010\u0010+\u001a\u0004\u0018\u00010\u0007HÆ\u0003¢\u0006\u0002\u0010\u001bJ\u000b\u0010,\u001a\u0004\u0018\u00010\nHÆ\u0003JJ\u0010-\u001a\u00020\u00002\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\nHÆ\u0001¢\u0006\u0002\u0010.J\u0013\u0010/\u001a\u00020\u00042\b\u00100\u001a\u0004\u0018\u000101HÖ\u0003J\t\u00102\u001a\u00020\u0007HÖ\u0001J\b\u00103\u001a\u00020\nH\u0016R\u001e\u0010\f\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u000e0\r8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0010R \u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0015\u0010\u0016\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R \u0010\u0005\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0019\u0010\u0016\u001a\u0004\b\u0017\u0010\u0012\"\u0004\b\u0018\u0010\u0014R \u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0086\u000e¢\u0006\u0012\n\u0004\b\u001e\u0010\u001f\u001a\u0004\b\u001a\u0010\u001b\"\u0004\b\u001c\u0010\u001dR \u0010\b\u001a\u0004\u0018\u00010\u0007X\u0086\u000e¢\u0006\u0012\n\u0004\b\"\u0010\u001f\u001a\u0004\b \u0010\u001b\"\u0004\b!\u0010\u001dR\u001e\u0010\t\u001a\u0004\u0018\u00010\nX\u0086\u000e¢\u0006\u0010\n\u0002\b'\u001a\u0004\b#\u0010$\"\u0004\b%\u0010&¨\u00065"}, m1293d2 = {"Lcom/hp/jipp/model/PowerStateCapabilitiesCol;", "Lcom/hp/jipp/encoding/AttributeCollection;", "()V", "canAcceptJobs", "", "canProcessJobs", "powerActiveWatts", "", "powerInactiveWatts", "powerState", "", "(Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/String;)V", "attributes", "", "Lcom/hp/jipp/encoding/Attribute;", "getAttributes", "()Ljava/util/List;", "getCanAcceptJobs", "()Ljava/lang/Boolean;", "setCanAcceptJobs", "(Ljava/lang/Boolean;)V", "canAcceptJobs$1", "Ljava/lang/Boolean;", "getCanProcessJobs", "setCanProcessJobs", "canProcessJobs$1", "getPowerActiveWatts", "()Ljava/lang/Integer;", "setPowerActiveWatts", "(Ljava/lang/Integer;)V", "powerActiveWatts$1", "Ljava/lang/Integer;", "getPowerInactiveWatts", "setPowerInactiveWatts", "powerInactiveWatts$1", "getPowerState", "()Ljava/lang/String;", "setPowerState", "(Ljava/lang/String;)V", "powerState$1", "component1", "component2", "component3", "component4", "component5", PrinterServiceType.copy, "(Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/String;)Lcom/hp/jipp/model/PowerStateCapabilitiesCol;", "equals", "other", "", "hashCode", "toString", "Companion", "jipp-core"}, m1294k = 1, m1295mv = {1, 4, 0})
public final class PowerStateCapabilitiesCol implements AttributeCollection {

    public static final Companion INSTANCE;
    public static final Companion Types;
    public static final BooleanType canAcceptJobs;
    public static final BooleanType canProcessJobs;
    private static final Class<PowerStateCapabilitiesCol> cls;
    public static final IntType powerActiveWatts;
    public static final IntType powerInactiveWatts;
    public static final KeywordType powerState;

    private Boolean canAcceptJobs;

    private Boolean canProcessJobs;

    private Integer powerActiveWatts;

    private Integer powerInactiveWatts;

    private String powerState;

    public static PowerStateCapabilitiesCol copy$default(PowerStateCapabilitiesCol powerStateCapabilitiesCol, Boolean bool, Boolean bool2, Integer num, Integer num2, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            bool = powerStateCapabilitiesCol.canAcceptJobs;
        }
        if ((i & 2) != 0) {
            bool2 = powerStateCapabilitiesCol.canProcessJobs;
        }
        Boolean bool3 = bool2;
        if ((i & 4) != 0) {
            num = powerStateCapabilitiesCol.powerActiveWatts;
        }
        Integer num3 = num;
        if ((i & 8) != 0) {
            num2 = powerStateCapabilitiesCol.powerInactiveWatts;
        }
        Integer num4 = num2;
        if ((i & 16) != 0) {
            str = powerStateCapabilitiesCol.powerState;
        }
        return powerStateCapabilitiesCol.copy(bool, bool3, num3, num4, str);
    }

    public final Boolean getCanAcceptJobs() {
        return this.canAcceptJobs;
    }

    public final Boolean getCanProcessJobs() {
        return this.canProcessJobs;
    }

    public final Integer getPowerActiveWatts() {
        return this.powerActiveWatts;
    }

    public final Integer getPowerInactiveWatts() {
        return this.powerInactiveWatts;
    }

    public final String getPowerState() {
        return this.powerState;
    }

    public final PowerStateCapabilitiesCol copy(Boolean canAcceptJobs2, Boolean canProcessJobs2, Integer powerActiveWatts2, Integer powerInactiveWatts2, String powerState2) {
        return new PowerStateCapabilitiesCol(canAcceptJobs2, canProcessJobs2, powerActiveWatts2, powerInactiveWatts2, powerState2);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof PowerStateCapabilitiesCol)) {
            return false;
        }
        PowerStateCapabilitiesCol powerStateCapabilitiesCol = (PowerStateCapabilitiesCol) other;
        return Intrinsics.areEqual(this.canAcceptJobs, powerStateCapabilitiesCol.canAcceptJobs) && Intrinsics.areEqual(this.canProcessJobs, powerStateCapabilitiesCol.canProcessJobs) && Intrinsics.areEqual(this.powerActiveWatts, powerStateCapabilitiesCol.powerActiveWatts) && Intrinsics.areEqual(this.powerInactiveWatts, powerStateCapabilitiesCol.powerInactiveWatts) && Intrinsics.areEqual(this.powerState, powerStateCapabilitiesCol.powerState);
    }

    public int hashCode() {
        Boolean bool = this.canAcceptJobs;
        int iHashCode = (bool != null ? bool.hashCode() : 0) * 31;
        Boolean bool2 = this.canProcessJobs;
        int iHashCode2 = (iHashCode + (bool2 != null ? bool2.hashCode() : 0)) * 31;
        Integer num = this.powerActiveWatts;
        int iHashCode3 = (iHashCode2 + (num != null ? num.hashCode() : 0)) * 31;
        Integer num2 = this.powerInactiveWatts;
        int iHashCode4 = (iHashCode3 + (num2 != null ? num2.hashCode() : 0)) * 31;
        String str = this.powerState;
        return iHashCode4 + (str != null ? str.hashCode() : 0);
    }

    @Override
    public void print(PrettyPrinter printer) {
        Intrinsics.checkNotNullParameter(printer, "printer");
        AttributeCollection.DefaultImpls.print(this, printer);
    }

    public PowerStateCapabilitiesCol(Boolean bool, Boolean bool2, Integer num, Integer num2, String str) {
        this.canAcceptJobs = bool;
        this.canProcessJobs = bool2;
        this.powerActiveWatts = num;
        this.powerInactiveWatts = num2;
        this.powerState = str;
    }

    public PowerStateCapabilitiesCol(Boolean bool, Boolean bool2, Integer num, Integer num2, String str, int i, DefaultConstructorMarker defaultConstructorMarker) {
        Boolean bool3;
        Integer num3;
        Integer num4;
        String str2 = null;
        if ((i & 1) != 0) {
            bool = null;
        }
        if ((i & 2) != 0) {
            bool3 = null;
        } else {
            bool3 = bool2;
        }
        if ((i & 4) != 0) {
            num3 = null;
        } else {
            num3 = num;
        }
        if ((i & 8) != 0) {
            num4 = null;
        } else {
            num4 = num2;
        }
        if ((i & 16) != 0) {
        } else {
            str2 = str;
        }
        this(bool, bool3, num3, num4, str2);
    }

    public final Boolean getCanAcceptJobs() {
        return this.canAcceptJobs;
    }

    public final void setCanAcceptJobs(Boolean bool) {
        this.canAcceptJobs = bool;
    }

    public final Boolean getCanProcessJobs() {
        return this.canProcessJobs;
    }

    public final void setCanProcessJobs(Boolean bool) {
        this.canProcessJobs = bool;
    }

    public final Integer getPowerActiveWatts() {
        return this.powerActiveWatts;
    }

    public final void setPowerActiveWatts(Integer num) {
        this.powerActiveWatts = num;
    }

    public final Integer getPowerInactiveWatts() {
        return this.powerInactiveWatts;
    }

    public final void setPowerInactiveWatts(Integer num) {
        this.powerInactiveWatts = num;
    }

    public final String getPowerState() {
        return this.powerState;
    }

    public final void setPowerState(String str) {
        this.powerState = str;
    }

    public PowerStateCapabilitiesCol() {
        this(null, null, null, null, null);
    }

    @Override
    public List<Attribute<?>> getAttributes() {
        Attribute[] attributeArr = new Attribute[5];
        Boolean bool = this.canAcceptJobs;
        attributeArr[0] = bool != null ? canAcceptJobs.mo418of(Boolean.valueOf(bool.booleanValue())) : null;
        Boolean bool2 = this.canProcessJobs;
        attributeArr[1] = bool2 != null ? canProcessJobs.mo418of(Boolean.valueOf(bool2.booleanValue())) : null;
        Integer num = this.powerActiveWatts;
        attributeArr[2] = num != null ? powerActiveWatts.mo418of(Integer.valueOf(num.intValue())) : null;
        Integer num2 = this.powerInactiveWatts;
        attributeArr[3] = num2 != null ? powerInactiveWatts.mo418of(Integer.valueOf(num2.intValue())) : null;
        String str = this.powerState;
        attributeArr[4] = str != null ? powerState.mo418of(str) : null;
        return CollectionsKt.listOfNotNull((Object[]) attributeArr);
    }

    @Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003J\u001a\u0010\u0012\u001a\u00020\u00022\u0010\u0010\u0013\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00150\u0014H\u0016R\u0016\u0010\u0004\u001a\u00020\u00008\u0006X\u0087\u0004¢\u0006\b\n\u0000\u0012\u0004\b\u0005\u0010\u0003R\u0010\u0010\u0006\u001a\u00020\u00078\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u00020\u00078\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00020\nX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0010\u0010\r\u001a\u00020\u000e8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u000f\u001a\u00020\u000e8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0010\u001a\u00020\u00118\u0006X\u0087\u0004¢\u0006\u0002\n\u0000¨\u0006\u0016"}, m1293d2 = {"Lcom/hp/jipp/model/PowerStateCapabilitiesCol$Companion;", "Lcom/hp/jipp/encoding/AttributeCollection$Converter;", "Lcom/hp/jipp/model/PowerStateCapabilitiesCol;", "()V", "Types", "getTypes$annotations", "canAcceptJobs", "Lcom/hp/jipp/encoding/BooleanType;", "canProcessJobs", "cls", "Ljava/lang/Class;", "getCls", "()Ljava/lang/Class;", "powerActiveWatts", "Lcom/hp/jipp/encoding/IntType;", "powerInactiveWatts", "powerState", "Lcom/hp/jipp/encoding/KeywordType;", "convert", "attributes", "", "Lcom/hp/jipp/encoding/Attribute;", "jipp-core"}, m1294k = 1, m1295mv = {1, 4, 0})
    public static final class Companion implements AttributeCollection.Converter<PowerStateCapabilitiesCol> {
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
        public PowerStateCapabilitiesCol convert(List<? extends Attribute<?>> attributes) {
            Intrinsics.checkNotNullParameter(attributes, "attributes");
            return new PowerStateCapabilitiesCol((Boolean) extractOne(attributes, PowerStateCapabilitiesCol.canAcceptJobs), (Boolean) extractOne(attributes, PowerStateCapabilitiesCol.canProcessJobs), (Integer) extractOne(attributes, PowerStateCapabilitiesCol.powerActiveWatts), (Integer) extractOne(attributes, PowerStateCapabilitiesCol.powerInactiveWatts), (String) extractOne(attributes, PowerStateCapabilitiesCol.powerState));
        }

        @Override
        public Class<PowerStateCapabilitiesCol> getCls() {
            return PowerStateCapabilitiesCol.cls;
        }
    }

    static {
        Companion companion = new Companion(null);
        INSTANCE = companion;
        cls = PowerStateCapabilitiesCol.class;
        Types = companion;
        canAcceptJobs = new BooleanType("can-accept-jobs");
        canProcessJobs = new BooleanType("can-process-jobs");
        powerActiveWatts = new IntType("power-active-watts");
        powerInactiveWatts = new IntType("power-inactive-watts");
        powerState = new KeywordType("power-state");
    }

    public String toString() {
        return "PowerStateCapabilitiesCol(" + CollectionsKt.joinToString$default(getAttributes(), null, null, null, 0, null, null, 63, null) + ')';
    }
}
