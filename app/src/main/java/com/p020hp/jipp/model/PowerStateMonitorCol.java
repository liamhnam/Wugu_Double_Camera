package com.p020hp.jipp.model;

import com.p020hp.jipp.encoding.Attribute;
import com.p020hp.jipp.encoding.AttributeCollection;
import com.p020hp.jipp.encoding.AttributeType;
import com.p020hp.jipp.encoding.BooleanType;
import com.p020hp.jipp.encoding.IntType;
import com.p020hp.jipp.encoding.KeywordType;
import com.p020hp.jipp.encoding.Text;
import com.p020hp.jipp.encoding.TextType;
import com.p020hp.jipp.util.PrettyPrinter;
import java.util.List;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b*\n\u0002\u0010\u0000\n\u0002\b\u0004\b\u0086\b\u0018\u0000 >2\u00020\u0001:\u0001>B\u0007\b\u0016¢\u0006\u0002\u0010\u0002BY\u0012\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\n\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\b¢\u0006\u0002\u0010\rJ\u0010\u00100\u001a\u0004\u0018\u00010\u0004HÆ\u0003¢\u0006\u0002\u0010\u0014J\u0010\u00101\u001a\u0004\u0018\u00010\u0004HÆ\u0003¢\u0006\u0002\u0010\u0014J\u0010\u00102\u001a\u0004\u0018\u00010\u0004HÆ\u0003¢\u0006\u0002\u0010\u0014J\u0010\u00103\u001a\u0004\u0018\u00010\bHÆ\u0003¢\u0006\u0002\u0010 J\u000b\u00104\u001a\u0004\u0018\u00010\nHÆ\u0003J\u000b\u00105\u001a\u0004\u0018\u00010\nHÆ\u0003J\u0010\u00106\u001a\u0004\u0018\u00010\bHÆ\u0003¢\u0006\u0002\u0010 Jb\u00107\u001a\u00020\u00002\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b2\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n2\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\n2\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\bHÆ\u0001¢\u0006\u0002\u00108J\u0013\u00109\u001a\u00020\b2\b\u0010:\u001a\u0004\u0018\u00010;HÖ\u0003J\t\u0010<\u001a\u00020\u0004HÖ\u0001J\b\u0010=\u001a\u00020\nH\u0016R\u001e\u0010\u000e\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00100\u000f8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u0012R \u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0017\u0010\u0018\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R \u0010\u0005\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u0012\n\u0004\b\u001b\u0010\u0018\u001a\u0004\b\u0019\u0010\u0014\"\u0004\b\u001a\u0010\u0016R \u0010\u0006\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u0012\n\u0004\b\u001e\u0010\u0018\u001a\u0004\b\u001c\u0010\u0014\"\u0004\b\u001d\u0010\u0016R \u0010\u0007\u001a\u0004\u0018\u00010\bX\u0086\u000e¢\u0006\u0012\n\u0004\b#\u0010$\u001a\u0004\b\u001f\u0010 \"\u0004\b!\u0010\"R\u001e\u0010\t\u001a\u0004\u0018\u00010\nX\u0086\u000e¢\u0006\u0010\n\u0002\b)\u001a\u0004\b%\u0010&\"\u0004\b'\u0010(R\u001e\u0010\u000b\u001a\u0004\u0018\u00010\nX\u0086\u000e¢\u0006\u0010\n\u0002\b,\u001a\u0004\b*\u0010&\"\u0004\b+\u0010(R \u0010\f\u001a\u0004\u0018\u00010\bX\u0086\u000e¢\u0006\u0012\n\u0004\b/\u0010$\u001a\u0004\b-\u0010 \"\u0004\b.\u0010\"¨\u0006?"}, m1293d2 = {"Lcom/hp/jipp/model/PowerStateMonitorCol;", "Lcom/hp/jipp/encoding/AttributeCollection;", "()V", "currentMonthKwh", "", "currentWatts", "lifetimeKwh", "metersAreActual", "", "powerState", "", "powerStateMessage", "powerUsageIsRmsWatts", "(Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Boolean;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Boolean;)V", "attributes", "", "Lcom/hp/jipp/encoding/Attribute;", "getAttributes", "()Ljava/util/List;", "getCurrentMonthKwh", "()Ljava/lang/Integer;", "setCurrentMonthKwh", "(Ljava/lang/Integer;)V", "currentMonthKwh$1", "Ljava/lang/Integer;", "getCurrentWatts", "setCurrentWatts", "currentWatts$1", "getLifetimeKwh", "setLifetimeKwh", "lifetimeKwh$1", "getMetersAreActual", "()Ljava/lang/Boolean;", "setMetersAreActual", "(Ljava/lang/Boolean;)V", "metersAreActual$1", "Ljava/lang/Boolean;", "getPowerState", "()Ljava/lang/String;", "setPowerState", "(Ljava/lang/String;)V", "powerState$1", "getPowerStateMessage", "setPowerStateMessage", "powerStateMessage$1", "getPowerUsageIsRmsWatts", "setPowerUsageIsRmsWatts", "powerUsageIsRmsWatts$1", "component1", "component2", "component3", "component4", "component5", "component6", "component7", PrinterServiceType.copy, "(Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Boolean;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Boolean;)Lcom/hp/jipp/model/PowerStateMonitorCol;", "equals", "other", "", "hashCode", "toString", "Companion", "jipp-core"}, m1294k = 1, m1295mv = {1, 4, 0})
public final class PowerStateMonitorCol implements AttributeCollection {

    public static final Companion INSTANCE;
    public static final Companion Types;
    private static final Class<PowerStateMonitorCol> cls;
    public static final IntType currentMonthKwh;
    public static final IntType currentWatts;
    public static final IntType lifetimeKwh;
    public static final BooleanType metersAreActual;
    public static final KeywordType powerState;
    public static final TextType powerStateMessage;
    public static final BooleanType powerUsageIsRmsWatts;

    private Integer currentMonthKwh;

    private Integer currentWatts;

    private Integer lifetimeKwh;

    private Boolean metersAreActual;

    private String powerState;

    private String powerStateMessage;

    private Boolean powerUsageIsRmsWatts;

    public static PowerStateMonitorCol copy$default(PowerStateMonitorCol powerStateMonitorCol, Integer num, Integer num2, Integer num3, Boolean bool, String str, String str2, Boolean bool2, int i, Object obj) {
        if ((i & 1) != 0) {
            num = powerStateMonitorCol.currentMonthKwh;
        }
        if ((i & 2) != 0) {
            num2 = powerStateMonitorCol.currentWatts;
        }
        Integer num4 = num2;
        if ((i & 4) != 0) {
            num3 = powerStateMonitorCol.lifetimeKwh;
        }
        Integer num5 = num3;
        if ((i & 8) != 0) {
            bool = powerStateMonitorCol.metersAreActual;
        }
        Boolean bool3 = bool;
        if ((i & 16) != 0) {
            str = powerStateMonitorCol.powerState;
        }
        String str3 = str;
        if ((i & 32) != 0) {
            str2 = powerStateMonitorCol.powerStateMessage;
        }
        String str4 = str2;
        if ((i & 64) != 0) {
            bool2 = powerStateMonitorCol.powerUsageIsRmsWatts;
        }
        return powerStateMonitorCol.copy(num, num4, num5, bool3, str3, str4, bool2);
    }

    public final Integer getCurrentMonthKwh() {
        return this.currentMonthKwh;
    }

    public final Integer getCurrentWatts() {
        return this.currentWatts;
    }

    public final Integer getLifetimeKwh() {
        return this.lifetimeKwh;
    }

    public final Boolean getMetersAreActual() {
        return this.metersAreActual;
    }

    public final String getPowerState() {
        return this.powerState;
    }

    public final String getPowerStateMessage() {
        return this.powerStateMessage;
    }

    public final Boolean getPowerUsageIsRmsWatts() {
        return this.powerUsageIsRmsWatts;
    }

    public final PowerStateMonitorCol copy(Integer currentMonthKwh2, Integer currentWatts2, Integer lifetimeKwh2, Boolean metersAreActual2, String powerState2, String powerStateMessage2, Boolean powerUsageIsRmsWatts2) {
        return new PowerStateMonitorCol(currentMonthKwh2, currentWatts2, lifetimeKwh2, metersAreActual2, powerState2, powerStateMessage2, powerUsageIsRmsWatts2);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof PowerStateMonitorCol)) {
            return false;
        }
        PowerStateMonitorCol powerStateMonitorCol = (PowerStateMonitorCol) other;
        return Intrinsics.areEqual(this.currentMonthKwh, powerStateMonitorCol.currentMonthKwh) && Intrinsics.areEqual(this.currentWatts, powerStateMonitorCol.currentWatts) && Intrinsics.areEqual(this.lifetimeKwh, powerStateMonitorCol.lifetimeKwh) && Intrinsics.areEqual(this.metersAreActual, powerStateMonitorCol.metersAreActual) && Intrinsics.areEqual(this.powerState, powerStateMonitorCol.powerState) && Intrinsics.areEqual(this.powerStateMessage, powerStateMonitorCol.powerStateMessage) && Intrinsics.areEqual(this.powerUsageIsRmsWatts, powerStateMonitorCol.powerUsageIsRmsWatts);
    }

    public int hashCode() {
        Integer num = this.currentMonthKwh;
        int iHashCode = (num != null ? num.hashCode() : 0) * 31;
        Integer num2 = this.currentWatts;
        int iHashCode2 = (iHashCode + (num2 != null ? num2.hashCode() : 0)) * 31;
        Integer num3 = this.lifetimeKwh;
        int iHashCode3 = (iHashCode2 + (num3 != null ? num3.hashCode() : 0)) * 31;
        Boolean bool = this.metersAreActual;
        int iHashCode4 = (iHashCode3 + (bool != null ? bool.hashCode() : 0)) * 31;
        String str = this.powerState;
        int iHashCode5 = (iHashCode4 + (str != null ? str.hashCode() : 0)) * 31;
        String str2 = this.powerStateMessage;
        int iHashCode6 = (iHashCode5 + (str2 != null ? str2.hashCode() : 0)) * 31;
        Boolean bool2 = this.powerUsageIsRmsWatts;
        return iHashCode6 + (bool2 != null ? bool2.hashCode() : 0);
    }

    @Override
    public void print(PrettyPrinter printer) {
        Intrinsics.checkNotNullParameter(printer, "printer");
        AttributeCollection.DefaultImpls.print(this, printer);
    }

    public PowerStateMonitorCol(Integer num, Integer num2, Integer num3, Boolean bool, String str, String str2, Boolean bool2) {
        this.currentMonthKwh = num;
        this.currentWatts = num2;
        this.lifetimeKwh = num3;
        this.metersAreActual = bool;
        this.powerState = str;
        this.powerStateMessage = str2;
        this.powerUsageIsRmsWatts = bool2;
    }

    public PowerStateMonitorCol(Integer num, Integer num2, Integer num3, Boolean bool, String str, String str2, Boolean bool2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        Integer num4;
        Integer num5;
        Boolean bool3;
        String str3;
        String str4;
        Boolean bool4 = null;
        if ((i & 1) != 0) {
            num = null;
        }
        if ((i & 2) != 0) {
            num4 = null;
        } else {
            num4 = num2;
        }
        if ((i & 4) != 0) {
            num5 = null;
        } else {
            num5 = num3;
        }
        if ((i & 8) != 0) {
            bool3 = null;
        } else {
            bool3 = bool;
        }
        if ((i & 16) != 0) {
            str3 = null;
        } else {
            str3 = str;
        }
        if ((i & 32) != 0) {
            str4 = null;
        } else {
            str4 = str2;
        }
        if ((i & 64) != 0) {
        } else {
            bool4 = bool2;
        }
        this(num, num4, num5, bool3, str3, str4, bool4);
    }

    public final Integer getCurrentMonthKwh() {
        return this.currentMonthKwh;
    }

    public final void setCurrentMonthKwh(Integer num) {
        this.currentMonthKwh = num;
    }

    public final Integer getCurrentWatts() {
        return this.currentWatts;
    }

    public final void setCurrentWatts(Integer num) {
        this.currentWatts = num;
    }

    public final Integer getLifetimeKwh() {
        return this.lifetimeKwh;
    }

    public final void setLifetimeKwh(Integer num) {
        this.lifetimeKwh = num;
    }

    public final Boolean getMetersAreActual() {
        return this.metersAreActual;
    }

    public final void setMetersAreActual(Boolean bool) {
        this.metersAreActual = bool;
    }

    public final String getPowerState() {
        return this.powerState;
    }

    public final void setPowerState(String str) {
        this.powerState = str;
    }

    public final String getPowerStateMessage() {
        return this.powerStateMessage;
    }

    public final void setPowerStateMessage(String str) {
        this.powerStateMessage = str;
    }

    public final Boolean getPowerUsageIsRmsWatts() {
        return this.powerUsageIsRmsWatts;
    }

    public final void setPowerUsageIsRmsWatts(Boolean bool) {
        this.powerUsageIsRmsWatts = bool;
    }

    public PowerStateMonitorCol() {
        this(null, null, null, null, null, null, null);
    }

    @Override
    public List<Attribute<?>> getAttributes() {
        Attribute[] attributeArr = new Attribute[7];
        Integer num = this.currentMonthKwh;
        attributeArr[0] = num != null ? currentMonthKwh.mo418of(Integer.valueOf(num.intValue())) : null;
        Integer num2 = this.currentWatts;
        attributeArr[1] = num2 != null ? currentWatts.mo418of(Integer.valueOf(num2.intValue())) : null;
        Integer num3 = this.lifetimeKwh;
        attributeArr[2] = num3 != null ? lifetimeKwh.mo418of(Integer.valueOf(num3.intValue())) : null;
        Boolean bool = this.metersAreActual;
        attributeArr[3] = bool != null ? metersAreActual.mo418of(Boolean.valueOf(bool.booleanValue())) : null;
        String str = this.powerState;
        attributeArr[4] = str != null ? powerState.mo418of(str) : null;
        String str2 = this.powerStateMessage;
        attributeArr[5] = str2 != null ? powerStateMessage.m442of(str2) : null;
        Boolean bool2 = this.powerUsageIsRmsWatts;
        attributeArr[6] = bool2 != null ? powerUsageIsRmsWatts.mo418of(Boolean.valueOf(bool2.booleanValue())) : null;
        return CollectionsKt.listOfNotNull((Object[]) attributeArr);
    }

    @Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003J\u001a\u0010\u0015\u001a\u00020\u00022\u0010\u0010\u0016\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00180\u0017H\u0016R\u0016\u0010\u0004\u001a\u00020\u00008\u0006X\u0087\u0004¢\u0006\b\n\u0000\u0012\u0004\b\u0005\u0010\u0003R\u001a\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00020\u0007X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0010\u0010\n\u001a\u00020\u000b8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u00020\u000b8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u00020\u000b8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u00020\u000f8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0010\u001a\u00020\u00118\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0012\u001a\u00020\u00138\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0014\u001a\u00020\u000f8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000¨\u0006\u0019"}, m1293d2 = {"Lcom/hp/jipp/model/PowerStateMonitorCol$Companion;", "Lcom/hp/jipp/encoding/AttributeCollection$Converter;", "Lcom/hp/jipp/model/PowerStateMonitorCol;", "()V", "Types", "getTypes$annotations", "cls", "Ljava/lang/Class;", "getCls", "()Ljava/lang/Class;", "currentMonthKwh", "Lcom/hp/jipp/encoding/IntType;", "currentWatts", "lifetimeKwh", "metersAreActual", "Lcom/hp/jipp/encoding/BooleanType;", "powerState", "Lcom/hp/jipp/encoding/KeywordType;", "powerStateMessage", "Lcom/hp/jipp/encoding/TextType;", "powerUsageIsRmsWatts", "convert", "attributes", "", "Lcom/hp/jipp/encoding/Attribute;", "jipp-core"}, m1294k = 1, m1295mv = {1, 4, 0})
    public static final class Companion implements AttributeCollection.Converter<PowerStateMonitorCol> {
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
        public PowerStateMonitorCol convert(List<? extends Attribute<?>> attributes) {
            Intrinsics.checkNotNullParameter(attributes, "attributes");
            Integer num = (Integer) extractOne(attributes, PowerStateMonitorCol.currentMonthKwh);
            Integer num2 = (Integer) extractOne(attributes, PowerStateMonitorCol.currentWatts);
            Integer num3 = (Integer) extractOne(attributes, PowerStateMonitorCol.lifetimeKwh);
            Boolean bool = (Boolean) extractOne(attributes, PowerStateMonitorCol.metersAreActual);
            String str = (String) extractOne(attributes, PowerStateMonitorCol.powerState);
            Text text = (Text) extractOne(attributes, PowerStateMonitorCol.powerStateMessage);
            return new PowerStateMonitorCol(num, num2, num3, bool, str, text != null ? text.getValue() : null, (Boolean) extractOne(attributes, PowerStateMonitorCol.powerUsageIsRmsWatts));
        }

        @Override
        public Class<PowerStateMonitorCol> getCls() {
            return PowerStateMonitorCol.cls;
        }
    }

    static {
        Companion companion = new Companion(null);
        INSTANCE = companion;
        cls = PowerStateMonitorCol.class;
        Types = companion;
        currentMonthKwh = new IntType("current-month-kwh");
        currentWatts = new IntType("current-watts");
        lifetimeKwh = new IntType("lifetime-kwh");
        metersAreActual = new BooleanType("meters-are-actual");
        powerState = new KeywordType("power-state");
        powerStateMessage = new TextType("power-state-message");
        powerUsageIsRmsWatts = new BooleanType("power-usage-is-rms-watts");
    }

    public String toString() {
        return "PowerStateMonitorCol(" + CollectionsKt.joinToString$default(getAttributes(), null, null, null, 0, null, null, 63, null) + ')';
    }
}
