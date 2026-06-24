package com.p020hp.jipp.encoding;

import com.p020hp.jipp.encoding.AttributeSetType;
import com.p020hp.jipp.encoding.Codec;
import com.p020hp.jipp.util.ParseError;
import com.tom_roush.fontbox.ttf.NamingTable;
import java.util.Arrays;
import java.util.Calendar;
import java.util.TimeZone;
import kotlin.Metadata;
import kotlin.UByte;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;

@Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\b\u0016\u0018\u0000 \u00072\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0002\u0007\bB\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\b\u0010\u0006\u001a\u00020\u0004H\u0016¨\u0006\t"}, m1293d2 = {"Lcom/hp/jipp/encoding/DateTimeType;", "Lcom/hp/jipp/encoding/AttributeTypeImpl;", "Ljava/util/Calendar;", NamingTable.TAG, "", "(Ljava/lang/String;)V", "toString", "Companion", "Set", "jipp-core"}, m1294k = 1, m1295mv = {1, 4, 0})
public class DateTimeType extends Type<Calendar> {
    private static final int BYTE_MASK = 255;
    private static final int CALENDAR_LENGTH = 11;

    public static final Companion INSTANCE = new Companion(null);
    private static final Codec<Calendar> codec;

    public DateTimeType(String name) {
        super(name, Calendar.class);
        Intrinsics.checkNotNullParameter(name, "name");
    }

    @Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\u0018\u00002\u00020\u00012\b\u0012\u0004\u0012\u00020\u00030\u0002B\r\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\b\u0010\u0007\u001a\u00020\u0005H\u0016¨\u0006\b"}, m1293d2 = {"Lcom/hp/jipp/encoding/DateTimeType$Set;", "Lcom/hp/jipp/encoding/DateTimeType;", "Lcom/hp/jipp/encoding/AttributeSetType;", "Ljava/util/Calendar;", NamingTable.TAG, "", "(Ljava/lang/String;)V", "toString", "jipp-core"}, m1294k = 1, m1295mv = {1, 4, 0})
    public static final class Set extends DateTimeType implements AttributeSetType<Calendar> {
        public Set(String name) {
            super(name);
            Intrinsics.checkNotNullParameter(name, "name");
        }

        @Override
        public Attribute<Calendar> mo417of(Iterable<? extends Calendar> values) {
            Intrinsics.checkNotNullParameter(values, "values");
            return AttributeSetType.DefaultImpls.m420of((AttributeSetType) this, (Iterable) values);
        }

        @Override
        public Attribute<Calendar> mo419of(Calendar value, Calendar... values) {
            Intrinsics.checkNotNullParameter(value, "value");
            Intrinsics.checkNotNullParameter(values, "values");
            return AttributeSetType.DefaultImpls.m422of(this, value, values);
        }

        @Override
        public String toString() {
            return "DateTimeType.Set(" + getName() + ')';
        }
    }

    @Override
    public String toString() {
        return "DateTimeType(" + getName() + ')';
    }

    @Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0005\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\f\u0010\u000b\u001a\u00020\u0004*\u00020\fH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u0017\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\r"}, m1293d2 = {"Lcom/hp/jipp/encoding/DateTimeType$Companion;", "", "()V", "BYTE_MASK", "", "CALENDAR_LENGTH", "codec", "Lcom/hp/jipp/encoding/Codec;", "Ljava/util/Calendar;", "getCodec", "()Lcom/hp/jipp/encoding/Codec;", "toUint", "", "jipp-core"}, m1294k = 1, m1295mv = {1, 4, 0})
    public static final class Companion {
        public final int toUint(byte b) {
            return b & UByte.MAX_VALUE;
        }

        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final Codec<Calendar> getCodec() {
            return DateTimeType.codec;
        }
    }

    static {
        Codec.Companion companion = Codec.INSTANCE;
        final ValueTag valueTag = Tag.dateTime;
        codec = new Codec<Calendar>() {
            private final Class<Calendar> cls = Calendar.class;

            @Override
            public Class<Calendar> getCls() {
                return this.cls;
            }

            @Override
            public ValueTag tagOf(Object value) {
                Intrinsics.checkNotNullParameter(value, "value");
                return valueTag;
            }

            @Override
            public boolean handlesTag(ValueTag tag) {
                Intrinsics.checkNotNullParameter(tag, "tag");
                return Intrinsics.areEqual(valueTag, tag);
            }

            @Override
            public void writeValue(IppOutputStream output, Object value) {
                Intrinsics.checkNotNullParameter(output, "output");
                Intrinsics.checkNotNullParameter(value, "value");
                Calendar calendar = (Calendar) value;
                output.writeShort(11);
                output.writeShort(calendar.get(1));
                output.writeByte(calendar.get(2) + 1);
                output.writeByte(calendar.get(5));
                output.writeByte(calendar.get(11));
                output.writeByte(calendar.get(12));
                output.writeByte(calendar.get(13));
                output.writeByte(calendar.get(14) / 100);
                TimeZone timeZone = calendar.getTimeZone();
                Intrinsics.checkNotNullExpressionValue(timeZone, "it.timeZone");
                int rawOffset = timeZone.getRawOffset();
                if (rawOffset < 0) {
                    output.writeByte(45);
                    rawOffset = -rawOffset;
                } else {
                    output.writeByte(43);
                }
                int i = (rawOffset / 1000) / 60;
                output.writeByte(i / 60);
                output.writeByte(i % 60);
            }

            @Override
            public Calendar readValue(IppInputStream input, ValueTag startTag) throws ParseError {
                Intrinsics.checkNotNullParameter(input, "input");
                Intrinsics.checkNotNullParameter(startTag, "startTag");
                byte[] valueBytes$jipp_core = input.readValueBytes$jipp_core();
                if (valueBytes$jipp_core.length != 11) {
                    throw new ParseError("Invalid byte count " + valueBytes$jipp_core.length + " for dateTime, must be 11");
                }
                Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
                calendar.set(1, (valueBytes$jipp_core[0] << 8) + DateTimeType.INSTANCE.toUint(valueBytes$jipp_core[1]));
                calendar.set(2, DateTimeType.INSTANCE.toUint(valueBytes$jipp_core[2]) - 1);
                calendar.set(5, DateTimeType.INSTANCE.toUint(valueBytes$jipp_core[3]));
                calendar.set(11, DateTimeType.INSTANCE.toUint(valueBytes$jipp_core[4]));
                calendar.set(12, DateTimeType.INSTANCE.toUint(valueBytes$jipp_core[5]));
                calendar.set(13, DateTimeType.INSTANCE.toUint(valueBytes$jipp_core[6]));
                calendar.set(14, DateTimeType.INSTANCE.toUint(valueBytes$jipp_core[7]) * 100);
                StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
                String str = String.format("GMT%s%02d%02d", Arrays.copyOf(new Object[]{Character.valueOf((char) valueBytes$jipp_core[8]), Integer.valueOf(DateTimeType.INSTANCE.toUint(valueBytes$jipp_core[9])), Integer.valueOf(DateTimeType.INSTANCE.toUint(valueBytes$jipp_core[10]))}, 3));
                Intrinsics.checkNotNullExpressionValue(str, "java.lang.String.format(format, *args)");
                Intrinsics.checkNotNullExpressionValue(calendar, "calendar");
                calendar.setTimeZone(TimeZone.getTimeZone(str));
                return calendar;
            }
        };
    }
}
