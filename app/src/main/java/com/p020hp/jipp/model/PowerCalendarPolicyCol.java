package com.p020hp.jipp.model;

import com.p020hp.jipp.encoding.Attribute;
import com.p020hp.jipp.encoding.AttributeCollection;
import com.p020hp.jipp.encoding.AttributeType;
import com.p020hp.jipp.encoding.BooleanType;
import com.p020hp.jipp.encoding.IntType;
import com.p020hp.jipp.encoding.KeywordType;
import com.p020hp.jipp.util.PrettyPrinter;
import com.proembed.service.Constant;
import java.util.List;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b.\n\u0002\u0010\u0000\n\u0002\b\u0004\b\u0086\b\u0018\u0000 C2\u00020\u0001:\u0001CB\u0007\b\u0016¢\u0006\u0002\u0010\u0002Be\u0012\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000b\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\r¢\u0006\u0002\u0010\u000eJ\u0010\u00104\u001a\u0004\u0018\u00010\u0004HÆ\u0003¢\u0006\u0002\u0010\u0015J\u0010\u00105\u001a\u0004\u0018\u00010\u0004HÆ\u0003¢\u0006\u0002\u0010\u0015J\u0010\u00106\u001a\u0004\u0018\u00010\u0004HÆ\u0003¢\u0006\u0002\u0010\u0015J\u0010\u00107\u001a\u0004\u0018\u00010\u0004HÆ\u0003¢\u0006\u0002\u0010\u0015J\u0010\u00108\u001a\u0004\u0018\u00010\u0004HÆ\u0003¢\u0006\u0002\u0010\u0015J\u0010\u00109\u001a\u0004\u0018\u00010\u0004HÆ\u0003¢\u0006\u0002\u0010\u0015J\u000b\u0010:\u001a\u0004\u0018\u00010\u000bHÆ\u0003J\u0010\u0010;\u001a\u0004\u0018\u00010\rHÆ\u0003¢\u0006\u0002\u0010/Jn\u0010<\u001a\u00020\u00002\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000b2\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\rHÆ\u0001¢\u0006\u0002\u0010=J\u0013\u0010>\u001a\u00020\r2\b\u0010?\u001a\u0004\u0018\u00010@HÖ\u0003J\t\u0010A\u001a\u00020\u0004HÖ\u0001J\b\u0010B\u001a\u00020\u000bH\u0016R\u001e\u0010\u000f\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00110\u00108VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0013R \u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0018\u0010\u0019\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R \u0010\u0005\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u0012\n\u0004\b\u001c\u0010\u0019\u001a\u0004\b\u001a\u0010\u0015\"\u0004\b\u001b\u0010\u0017R \u0010\u0006\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u0012\n\u0004\b\u001f\u0010\u0019\u001a\u0004\b\u001d\u0010\u0015\"\u0004\b\u001e\u0010\u0017R \u0010\u0007\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u0012\n\u0004\b\"\u0010\u0019\u001a\u0004\b \u0010\u0015\"\u0004\b!\u0010\u0017R \u0010\b\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u0012\n\u0004\b%\u0010\u0019\u001a\u0004\b#\u0010\u0015\"\u0004\b$\u0010\u0017R \u0010\t\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u0012\n\u0004\b(\u0010\u0019\u001a\u0004\b&\u0010\u0015\"\u0004\b'\u0010\u0017R\u001e\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0086\u000e¢\u0006\u0010\n\u0002\b-\u001a\u0004\b)\u0010*\"\u0004\b+\u0010,R \u0010\f\u001a\u0004\u0018\u00010\rX\u0086\u000e¢\u0006\u0012\n\u0004\b2\u00103\u001a\u0004\b.\u0010/\"\u0004\b0\u00101¨\u0006D"}, m1293d2 = {"Lcom/hp/jipp/model/PowerCalendarPolicyCol;", "Lcom/hp/jipp/encoding/AttributeCollection;", "()V", "calendarId", "", "dayOfMonth", "dayOfWeek", Constant.POWER_ON_HOUR, Constant.POWER_ON_MINUTE, Constant.POWER_ON_Month, "requestPowerState", "", "runOnce", "", "(Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/Boolean;)V", "attributes", "", "Lcom/hp/jipp/encoding/Attribute;", "getAttributes", "()Ljava/util/List;", "getCalendarId", "()Ljava/lang/Integer;", "setCalendarId", "(Ljava/lang/Integer;)V", "calendarId$1", "Ljava/lang/Integer;", "getDayOfMonth", "setDayOfMonth", "dayOfMonth$1", "getDayOfWeek", "setDayOfWeek", "dayOfWeek$1", "getHour", "setHour", "hour$1", "getMinute", "setMinute", "minute$1", "getMonth", "setMonth", "month$1", "getRequestPowerState", "()Ljava/lang/String;", "setRequestPowerState", "(Ljava/lang/String;)V", "requestPowerState$1", "getRunOnce", "()Ljava/lang/Boolean;", "setRunOnce", "(Ljava/lang/Boolean;)V", "runOnce$1", "Ljava/lang/Boolean;", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", PrinterServiceType.copy, "(Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/Boolean;)Lcom/hp/jipp/model/PowerCalendarPolicyCol;", "equals", "other", "", "hashCode", "toString", "Companion", "jipp-core"}, m1294k = 1, m1295mv = {1, 4, 0})
public final class PowerCalendarPolicyCol implements AttributeCollection {

    public static final Companion INSTANCE;
    public static final Companion Types;
    public static final IntType calendarId;
    private static final Class<PowerCalendarPolicyCol> cls;
    public static final IntType dayOfMonth;
    public static final IntType dayOfWeek;
    public static final IntType hour;
    public static final IntType minute;
    public static final IntType month;
    public static final KeywordType requestPowerState;
    public static final BooleanType runOnce;

    private Integer calendarId;

    private Integer dayOfMonth;

    private Integer dayOfWeek;

    private Integer hour;

    private Integer minute;

    private Integer month;

    private String requestPowerState;

    private Boolean runOnce;

    public final Integer getCalendarId() {
        return this.calendarId;
    }

    public final Integer getDayOfMonth() {
        return this.dayOfMonth;
    }

    public final Integer getDayOfWeek() {
        return this.dayOfWeek;
    }

    public final Integer getHour() {
        return this.hour;
    }

    public final Integer getMinute() {
        return this.minute;
    }

    public final Integer getMonth() {
        return this.month;
    }

    public final String getRequestPowerState() {
        return this.requestPowerState;
    }

    public final Boolean getRunOnce() {
        return this.runOnce;
    }

    public final PowerCalendarPolicyCol copy(Integer calendarId2, Integer dayOfMonth2, Integer dayOfWeek2, Integer hour2, Integer minute2, Integer month2, String requestPowerState2, Boolean runOnce2) {
        return new PowerCalendarPolicyCol(calendarId2, dayOfMonth2, dayOfWeek2, hour2, minute2, month2, requestPowerState2, runOnce2);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof PowerCalendarPolicyCol)) {
            return false;
        }
        PowerCalendarPolicyCol powerCalendarPolicyCol = (PowerCalendarPolicyCol) other;
        return Intrinsics.areEqual(this.calendarId, powerCalendarPolicyCol.calendarId) && Intrinsics.areEqual(this.dayOfMonth, powerCalendarPolicyCol.dayOfMonth) && Intrinsics.areEqual(this.dayOfWeek, powerCalendarPolicyCol.dayOfWeek) && Intrinsics.areEqual(this.hour, powerCalendarPolicyCol.hour) && Intrinsics.areEqual(this.minute, powerCalendarPolicyCol.minute) && Intrinsics.areEqual(this.month, powerCalendarPolicyCol.month) && Intrinsics.areEqual(this.requestPowerState, powerCalendarPolicyCol.requestPowerState) && Intrinsics.areEqual(this.runOnce, powerCalendarPolicyCol.runOnce);
    }

    public int hashCode() {
        Integer num = this.calendarId;
        int iHashCode = (num != null ? num.hashCode() : 0) * 31;
        Integer num2 = this.dayOfMonth;
        int iHashCode2 = (iHashCode + (num2 != null ? num2.hashCode() : 0)) * 31;
        Integer num3 = this.dayOfWeek;
        int iHashCode3 = (iHashCode2 + (num3 != null ? num3.hashCode() : 0)) * 31;
        Integer num4 = this.hour;
        int iHashCode4 = (iHashCode3 + (num4 != null ? num4.hashCode() : 0)) * 31;
        Integer num5 = this.minute;
        int iHashCode5 = (iHashCode4 + (num5 != null ? num5.hashCode() : 0)) * 31;
        Integer num6 = this.month;
        int iHashCode6 = (iHashCode5 + (num6 != null ? num6.hashCode() : 0)) * 31;
        String str = this.requestPowerState;
        int iHashCode7 = (iHashCode6 + (str != null ? str.hashCode() : 0)) * 31;
        Boolean bool = this.runOnce;
        return iHashCode7 + (bool != null ? bool.hashCode() : 0);
    }

    @Override
    public void print(PrettyPrinter printer) {
        Intrinsics.checkNotNullParameter(printer, "printer");
        AttributeCollection.DefaultImpls.print(this, printer);
    }

    public PowerCalendarPolicyCol(Integer num, Integer num2, Integer num3, Integer num4, Integer num5, Integer num6, String str, Boolean bool) {
        this.calendarId = num;
        this.dayOfMonth = num2;
        this.dayOfWeek = num3;
        this.hour = num4;
        this.minute = num5;
        this.month = num6;
        this.requestPowerState = str;
        this.runOnce = bool;
    }

    public PowerCalendarPolicyCol(Integer num, Integer num2, Integer num3, Integer num4, Integer num5, Integer num6, String str, Boolean bool, int i, DefaultConstructorMarker defaultConstructorMarker) {
        Integer num7;
        Integer num8;
        Integer num9;
        Integer num10;
        Integer num11;
        Integer num12;
        String str2;
        Boolean bool2 = null;
        if ((i & 1) != 0) {
            num7 = null;
        } else {
            num7 = num;
        }
        if ((i & 2) != 0) {
            num8 = null;
        } else {
            num8 = num2;
        }
        if ((i & 4) != 0) {
            num9 = null;
        } else {
            num9 = num3;
        }
        if ((i & 8) != 0) {
            num10 = null;
        } else {
            num10 = num4;
        }
        if ((i & 16) != 0) {
            num11 = null;
        } else {
            num11 = num5;
        }
        if ((i & 32) != 0) {
            num12 = null;
        } else {
            num12 = num6;
        }
        if ((i & 64) != 0) {
            str2 = null;
        } else {
            str2 = str;
        }
        if ((i & 128) != 0) {
        } else {
            bool2 = bool;
        }
        this(num7, num8, num9, num10, num11, num12, str2, bool2);
    }

    public final Integer getCalendarId() {
        return this.calendarId;
    }

    public final void setCalendarId(Integer num) {
        this.calendarId = num;
    }

    public final Integer getDayOfMonth() {
        return this.dayOfMonth;
    }

    public final void setDayOfMonth(Integer num) {
        this.dayOfMonth = num;
    }

    public final Integer getDayOfWeek() {
        return this.dayOfWeek;
    }

    public final void setDayOfWeek(Integer num) {
        this.dayOfWeek = num;
    }

    public final Integer getHour() {
        return this.hour;
    }

    public final void setHour(Integer num) {
        this.hour = num;
    }

    public final Integer getMinute() {
        return this.minute;
    }

    public final void setMinute(Integer num) {
        this.minute = num;
    }

    public final Integer getMonth() {
        return this.month;
    }

    public final void setMonth(Integer num) {
        this.month = num;
    }

    public final String getRequestPowerState() {
        return this.requestPowerState;
    }

    public final void setRequestPowerState(String str) {
        this.requestPowerState = str;
    }

    public final Boolean getRunOnce() {
        return this.runOnce;
    }

    public final void setRunOnce(Boolean bool) {
        this.runOnce = bool;
    }

    public PowerCalendarPolicyCol() {
        this(null, null, null, null, null, null, null, null);
    }

    @Override
    public List<Attribute<?>> getAttributes() {
        Attribute[] attributeArr = new Attribute[8];
        Integer num = this.calendarId;
        attributeArr[0] = num != null ? calendarId.mo418of(Integer.valueOf(num.intValue())) : null;
        Integer num2 = this.dayOfMonth;
        attributeArr[1] = num2 != null ? dayOfMonth.mo418of(Integer.valueOf(num2.intValue())) : null;
        Integer num3 = this.dayOfWeek;
        attributeArr[2] = num3 != null ? dayOfWeek.mo418of(Integer.valueOf(num3.intValue())) : null;
        Integer num4 = this.hour;
        attributeArr[3] = num4 != null ? hour.mo418of(Integer.valueOf(num4.intValue())) : null;
        Integer num5 = this.minute;
        attributeArr[4] = num5 != null ? minute.mo418of(Integer.valueOf(num5.intValue())) : null;
        Integer num6 = this.month;
        attributeArr[5] = num6 != null ? month.mo418of(Integer.valueOf(num6.intValue())) : null;
        String str = this.requestPowerState;
        attributeArr[6] = str != null ? requestPowerState.mo418of(str) : null;
        Boolean bool = this.runOnce;
        attributeArr[7] = bool != null ? runOnce.mo418of(Boolean.valueOf(bool.booleanValue())) : null;
        return CollectionsKt.listOfNotNull((Object[]) attributeArr);
    }

    @Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003J\u001a\u0010\u0015\u001a\u00020\u00022\u0010\u0010\u0016\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00180\u0017H\u0016R\u0016\u0010\u0004\u001a\u00020\u00008\u0006X\u0087\u0004¢\u0006\b\n\u0000\u0012\u0004\b\u0005\u0010\u0003R\u0010\u0010\u0006\u001a\u00020\u00078\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00020\tX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0010\u0010\f\u001a\u00020\u00078\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u00020\u00078\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u00020\u00078\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u000f\u001a\u00020\u00078\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0010\u001a\u00020\u00078\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0011\u001a\u00020\u00128\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0013\u001a\u00020\u00148\u0006X\u0087\u0004¢\u0006\u0002\n\u0000¨\u0006\u0019"}, m1293d2 = {"Lcom/hp/jipp/model/PowerCalendarPolicyCol$Companion;", "Lcom/hp/jipp/encoding/AttributeCollection$Converter;", "Lcom/hp/jipp/model/PowerCalendarPolicyCol;", "()V", "Types", "getTypes$annotations", "calendarId", "Lcom/hp/jipp/encoding/IntType;", "cls", "Ljava/lang/Class;", "getCls", "()Ljava/lang/Class;", "dayOfMonth", "dayOfWeek", Constant.POWER_ON_HOUR, Constant.POWER_ON_MINUTE, Constant.POWER_ON_Month, "requestPowerState", "Lcom/hp/jipp/encoding/KeywordType;", "runOnce", "Lcom/hp/jipp/encoding/BooleanType;", "convert", "attributes", "", "Lcom/hp/jipp/encoding/Attribute;", "jipp-core"}, m1294k = 1, m1295mv = {1, 4, 0})
    public static final class Companion implements AttributeCollection.Converter<PowerCalendarPolicyCol> {
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
        public PowerCalendarPolicyCol convert(List<? extends Attribute<?>> attributes) {
            Intrinsics.checkNotNullParameter(attributes, "attributes");
            return new PowerCalendarPolicyCol((Integer) extractOne(attributes, PowerCalendarPolicyCol.calendarId), (Integer) extractOne(attributes, PowerCalendarPolicyCol.dayOfMonth), (Integer) extractOne(attributes, PowerCalendarPolicyCol.dayOfWeek), (Integer) extractOne(attributes, PowerCalendarPolicyCol.hour), (Integer) extractOne(attributes, PowerCalendarPolicyCol.minute), (Integer) extractOne(attributes, PowerCalendarPolicyCol.month), (String) extractOne(attributes, PowerCalendarPolicyCol.requestPowerState), (Boolean) extractOne(attributes, PowerCalendarPolicyCol.runOnce));
        }

        @Override
        public Class<PowerCalendarPolicyCol> getCls() {
            return PowerCalendarPolicyCol.cls;
        }
    }

    static {
        Companion companion = new Companion(null);
        INSTANCE = companion;
        cls = PowerCalendarPolicyCol.class;
        Types = companion;
        calendarId = new IntType("calendar-id");
        dayOfMonth = new IntType("day-of-month");
        dayOfWeek = new IntType("day-of-week");
        hour = new IntType(Constant.POWER_ON_HOUR);
        minute = new IntType(Constant.POWER_ON_MINUTE);
        month = new IntType(Constant.POWER_ON_Month);
        requestPowerState = new KeywordType("request-power-state");
        runOnce = new BooleanType("run-once");
    }

    public String toString() {
        return "PowerCalendarPolicyCol(" + CollectionsKt.joinToString$default(getAttributes(), null, null, null, 0, null, null, 63, null) + ')';
    }
}
