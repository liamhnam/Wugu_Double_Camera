package com.p020hp.jipp.model;

import com.p020hp.jipp.encoding.Attribute;
import com.p020hp.jipp.encoding.AttributeCollection;
import com.p020hp.jipp.encoding.AttributeType;
import com.p020hp.jipp.encoding.DateTimeType;
import com.p020hp.jipp.encoding.IntType;
import com.p020hp.jipp.encoding.KeywordType;
import com.p020hp.jipp.encoding.Text;
import com.p020hp.jipp.encoding.TextType;
import com.p020hp.jipp.util.PrettyPrinter;
import java.util.Calendar;
import java.util.List;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u001c\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0004\b\u0086\b\u0018\u0000 /2\u00020\u0001:\u0001/B\u0007\b\u0016¢\u0006\u0002\u0010\u0002B5\u0012\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0006¢\u0006\u0002\u0010\nJ\u0010\u0010#\u001a\u0004\u0018\u00010\u0004HÆ\u0003¢\u0006\u0002\u0010\u0011J\u000b\u0010$\u001a\u0004\u0018\u00010\u0006HÆ\u0003J\u000b\u0010%\u001a\u0004\u0018\u00010\bHÆ\u0003J\u000b\u0010&\u001a\u0004\u0018\u00010\u0006HÆ\u0003J>\u0010'\u001a\u00020\u00002\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b2\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0006HÆ\u0001¢\u0006\u0002\u0010(J\u0013\u0010)\u001a\u00020*2\b\u0010+\u001a\u0004\u0018\u00010,HÖ\u0003J\t\u0010-\u001a\u00020\u0004HÖ\u0001J\b\u0010.\u001a\u00020\u0006H\u0016R\u001e\u0010\u000b\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\r0\f8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000fR \u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0014\u0010\u0015\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\u001e\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0086\u000e¢\u0006\u0010\n\u0002\b\u001a\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019R\u001e\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0086\u000e¢\u0006\u0010\n\u0002\b\u001f\u001a\u0004\b\u001b\u0010\u001c\"\u0004\b\u001d\u0010\u001eR\u001e\u0010\t\u001a\u0004\u0018\u00010\u0006X\u0086\u000e¢\u0006\u0010\n\u0002\b\"\u001a\u0004\b \u0010\u0017\"\u0004\b!\u0010\u0019¨\u00060"}, m1293d2 = {"Lcom/hp/jipp/model/PowerLogCol;", "Lcom/hp/jipp/encoding/AttributeCollection;", "()V", "logId", "", "powerState", "", "powerStateDateTime", "Ljava/util/Calendar;", "powerStateMessage", "(Ljava/lang/Integer;Ljava/lang/String;Ljava/util/Calendar;Ljava/lang/String;)V", "attributes", "", "Lcom/hp/jipp/encoding/Attribute;", "getAttributes", "()Ljava/util/List;", "getLogId", "()Ljava/lang/Integer;", "setLogId", "(Ljava/lang/Integer;)V", "logId$1", "Ljava/lang/Integer;", "getPowerState", "()Ljava/lang/String;", "setPowerState", "(Ljava/lang/String;)V", "powerState$1", "getPowerStateDateTime", "()Ljava/util/Calendar;", "setPowerStateDateTime", "(Ljava/util/Calendar;)V", "powerStateDateTime$1", "getPowerStateMessage", "setPowerStateMessage", "powerStateMessage$1", "component1", "component2", "component3", "component4", PrinterServiceType.copy, "(Ljava/lang/Integer;Ljava/lang/String;Ljava/util/Calendar;Ljava/lang/String;)Lcom/hp/jipp/model/PowerLogCol;", "equals", "", "other", "", "hashCode", "toString", "Companion", "jipp-core"}, m1294k = 1, m1295mv = {1, 4, 0})
public final class PowerLogCol implements AttributeCollection {

    public static final Companion INSTANCE;
    public static final Companion Types;
    private static final Class<PowerLogCol> cls;
    public static final IntType logId;
    public static final KeywordType powerState;
    public static final DateTimeType powerStateDateTime;
    public static final TextType powerStateMessage;

    private Integer logId;

    private String powerState;

    private Calendar powerStateDateTime;

    private String powerStateMessage;

    public static PowerLogCol copy$default(PowerLogCol powerLogCol, Integer num, String str, Calendar calendar, String str2, int i, Object obj) {
        if ((i & 1) != 0) {
            num = powerLogCol.logId;
        }
        if ((i & 2) != 0) {
            str = powerLogCol.powerState;
        }
        if ((i & 4) != 0) {
            calendar = powerLogCol.powerStateDateTime;
        }
        if ((i & 8) != 0) {
            str2 = powerLogCol.powerStateMessage;
        }
        return powerLogCol.copy(num, str, calendar, str2);
    }

    public final Integer getLogId() {
        return this.logId;
    }

    public final String getPowerState() {
        return this.powerState;
    }

    public final Calendar getPowerStateDateTime() {
        return this.powerStateDateTime;
    }

    public final String getPowerStateMessage() {
        return this.powerStateMessage;
    }

    public final PowerLogCol copy(Integer logId2, String powerState2, Calendar powerStateDateTime2, String powerStateMessage2) {
        return new PowerLogCol(logId2, powerState2, powerStateDateTime2, powerStateMessage2);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof PowerLogCol)) {
            return false;
        }
        PowerLogCol powerLogCol = (PowerLogCol) other;
        return Intrinsics.areEqual(this.logId, powerLogCol.logId) && Intrinsics.areEqual(this.powerState, powerLogCol.powerState) && Intrinsics.areEqual(this.powerStateDateTime, powerLogCol.powerStateDateTime) && Intrinsics.areEqual(this.powerStateMessage, powerLogCol.powerStateMessage);
    }

    public int hashCode() {
        Integer num = this.logId;
        int iHashCode = (num != null ? num.hashCode() : 0) * 31;
        String str = this.powerState;
        int iHashCode2 = (iHashCode + (str != null ? str.hashCode() : 0)) * 31;
        Calendar calendar = this.powerStateDateTime;
        int iHashCode3 = (iHashCode2 + (calendar != null ? calendar.hashCode() : 0)) * 31;
        String str2 = this.powerStateMessage;
        return iHashCode3 + (str2 != null ? str2.hashCode() : 0);
    }

    @Override
    public void print(PrettyPrinter printer) {
        Intrinsics.checkNotNullParameter(printer, "printer");
        AttributeCollection.DefaultImpls.print(this, printer);
    }

    public PowerLogCol(Integer num, String str, Calendar calendar, String str2) {
        this.logId = num;
        this.powerState = str;
        this.powerStateDateTime = calendar;
        this.powerStateMessage = str2;
    }

    public PowerLogCol(Integer num, String str, Calendar calendar, String str2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        if ((i & 1) != 0) {
            num = null;
        }
        if ((i & 2) != 0) {
            str = null;
        }
        if ((i & 4) != 0) {
            calendar = null;
        }
        if ((i & 8) != 0) {
            str2 = null;
        }
        this(num, str, calendar, str2);
    }

    public final Integer getLogId() {
        return this.logId;
    }

    public final void setLogId(Integer num) {
        this.logId = num;
    }

    public final String getPowerState() {
        return this.powerState;
    }

    public final void setPowerState(String str) {
        this.powerState = str;
    }

    public final Calendar getPowerStateDateTime() {
        return this.powerStateDateTime;
    }

    public final void setPowerStateDateTime(Calendar calendar) {
        this.powerStateDateTime = calendar;
    }

    public final String getPowerStateMessage() {
        return this.powerStateMessage;
    }

    public final void setPowerStateMessage(String str) {
        this.powerStateMessage = str;
    }

    public PowerLogCol() {
        this(null, null, null, null);
    }

    @Override
    public List<Attribute<?>> getAttributes() {
        Attribute[] attributeArr = new Attribute[4];
        Integer num = this.logId;
        attributeArr[0] = num != null ? logId.mo418of(Integer.valueOf(num.intValue())) : null;
        String str = this.powerState;
        attributeArr[1] = str != null ? powerState.mo418of(str) : null;
        Calendar calendar = this.powerStateDateTime;
        attributeArr[2] = calendar != null ? powerStateDateTime.mo418of(calendar) : null;
        String str2 = this.powerStateMessage;
        attributeArr[3] = str2 != null ? powerStateMessage.m442of(str2) : null;
        return CollectionsKt.listOfNotNull((Object[]) attributeArr);
    }

    @Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003J\u001a\u0010\u0012\u001a\u00020\u00022\u0010\u0010\u0013\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00150\u0014H\u0016R\u0016\u0010\u0004\u001a\u00020\u00008\u0006X\u0087\u0004¢\u0006\b\n\u0000\u0012\u0004\b\u0005\u0010\u0003R\u001a\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00020\u0007X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0010\u0010\n\u001a\u00020\u000b8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u00020\r8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u00020\u000f8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0010\u001a\u00020\u00118\u0006X\u0087\u0004¢\u0006\u0002\n\u0000¨\u0006\u0016"}, m1293d2 = {"Lcom/hp/jipp/model/PowerLogCol$Companion;", "Lcom/hp/jipp/encoding/AttributeCollection$Converter;", "Lcom/hp/jipp/model/PowerLogCol;", "()V", "Types", "getTypes$annotations", "cls", "Ljava/lang/Class;", "getCls", "()Ljava/lang/Class;", "logId", "Lcom/hp/jipp/encoding/IntType;", "powerState", "Lcom/hp/jipp/encoding/KeywordType;", "powerStateDateTime", "Lcom/hp/jipp/encoding/DateTimeType;", "powerStateMessage", "Lcom/hp/jipp/encoding/TextType;", "convert", "attributes", "", "Lcom/hp/jipp/encoding/Attribute;", "jipp-core"}, m1294k = 1, m1295mv = {1, 4, 0})
    public static final class Companion implements AttributeCollection.Converter<PowerLogCol> {
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
        public PowerLogCol convert(List<? extends Attribute<?>> attributes) {
            Intrinsics.checkNotNullParameter(attributes, "attributes");
            Integer num = (Integer) extractOne(attributes, PowerLogCol.logId);
            String str = (String) extractOne(attributes, PowerLogCol.powerState);
            Calendar calendar = (Calendar) extractOne(attributes, PowerLogCol.powerStateDateTime);
            Text text = (Text) extractOne(attributes, PowerLogCol.powerStateMessage);
            return new PowerLogCol(num, str, calendar, text != null ? text.getValue() : null);
        }

        @Override
        public Class<PowerLogCol> getCls() {
            return PowerLogCol.cls;
        }
    }

    static {
        Companion companion = new Companion(null);
        INSTANCE = companion;
        cls = PowerLogCol.class;
        Types = companion;
        logId = new IntType("log-id");
        powerState = new KeywordType("power-state");
        powerStateDateTime = new DateTimeType("power-state-date-time");
        powerStateMessage = new TextType("power-state-message");
    }

    public String toString() {
        return "PowerLogCol(" + CollectionsKt.joinToString$default(getAttributes(), null, null, null, 0, null, null, 63, null) + ')';
    }
}
