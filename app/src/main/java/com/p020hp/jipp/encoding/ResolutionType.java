package com.p020hp.jipp.encoding;

import com.p020hp.jipp.encoding.AttributeSetType;
import com.p020hp.jipp.encoding.AttributeType;
import com.p020hp.jipp.encoding.Codec;
import com.p020hp.jipp.util.ParseError;
import com.tom_roush.fontbox.ttf.NamingTable;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u0000\n\u0002\b\u0004\b\u0016\u0018\u0000 \f2\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0002\f\rB\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\u0012\u0010\b\u001a\u0004\u0018\u00010\u00022\u0006\u0010\t\u001a\u00020\nH\u0016J\b\u0010\u000b\u001a\u00020\u0004H\u0016R\u0014\u0010\u0003\u001a\u00020\u0004X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u000e"}, m1293d2 = {"Lcom/hp/jipp/encoding/ResolutionType;", "Lcom/hp/jipp/encoding/AttributeType;", "Lcom/hp/jipp/encoding/Resolution;", NamingTable.TAG, "", "(Ljava/lang/String;)V", "getName", "()Ljava/lang/String;", "coerce", "value", "", "toString", "Companion", "Set", "jipp-core"}, m1294k = 1, m1295mv = {1, 4, 0})
public class ResolutionType implements AttributeType<Resolution> {

    public static final Companion INSTANCE = new Companion(null);
    private static final Codec<Resolution> codec;
    private final String name;

    public ResolutionType(String name) {
        Intrinsics.checkNotNullParameter(name, "name");
        this.name = name;
    }

    @Override
    public Attribute<Resolution> coerce(Attribute<?> attribute) {
        Intrinsics.checkNotNullParameter(attribute, "attribute");
        return AttributeType.DefaultImpls.coerce(this, attribute);
    }

    @Override
    public Attribute<Resolution> empty(OutOfBandTag tag) {
        Intrinsics.checkNotNullParameter(tag, "tag");
        return AttributeType.DefaultImpls.empty(this, tag);
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public Attribute<Resolution> noValue() {
        return AttributeType.DefaultImpls.noValue(this);
    }

    @Override
    public Attribute<Resolution> mo418of(Resolution value) {
        Intrinsics.checkNotNullParameter(value, "value");
        return AttributeType.DefaultImpls.m423of(this, value);
    }

    @Override
    public Attribute<Resolution> unknown() {
        return AttributeType.DefaultImpls.unknown(this);
    }

    @Override
    public Attribute<Resolution> unsupported() {
        return AttributeType.DefaultImpls.unsupported(this);
    }

    @Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\u0018\u00002\u00020\u00012\b\u0012\u0004\u0012\u00020\u00030\u0002B\r\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\b\u0010\u0007\u001a\u00020\u0005H\u0016¨\u0006\b"}, m1293d2 = {"Lcom/hp/jipp/encoding/ResolutionType$Set;", "Lcom/hp/jipp/encoding/ResolutionType;", "Lcom/hp/jipp/encoding/AttributeSetType;", "Lcom/hp/jipp/encoding/Resolution;", NamingTable.TAG, "", "(Ljava/lang/String;)V", "toString", "jipp-core"}, m1294k = 1, m1295mv = {1, 4, 0})
    public static final class Set extends ResolutionType implements AttributeSetType<Resolution> {
        public Set(String name) {
            super(name);
            Intrinsics.checkNotNullParameter(name, "name");
        }

        @Override
        public Attribute<Resolution> mo419of(Resolution value, Resolution... values) {
            Intrinsics.checkNotNullParameter(value, "value");
            Intrinsics.checkNotNullParameter(values, "values");
            return AttributeSetType.DefaultImpls.m422of(this, value, values);
        }

        @Override
        public Attribute<Resolution> mo417of(Iterable<? extends Resolution> values) {
            Intrinsics.checkNotNullParameter(values, "values");
            return AttributeSetType.DefaultImpls.m420of((AttributeSetType) this, (Iterable) values);
        }

        @Override
        public String toString() {
            return "ResolutionType.Set(" + getName() + ')';
        }
    }

    @Override
    public Resolution coerce(Object value) {
        Intrinsics.checkNotNullParameter(value, "value");
        if (!(value instanceof Resolution)) {
            value = null;
        }
        return (Resolution) value;
    }

    public String toString() {
        return "ResolutionType(" + getName() + ')';
    }

    @Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0017\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, m1293d2 = {"Lcom/hp/jipp/encoding/ResolutionType$Companion;", "", "()V", "codec", "Lcom/hp/jipp/encoding/Codec;", "Lcom/hp/jipp/encoding/Resolution;", "getCodec", "()Lcom/hp/jipp/encoding/Codec;", "jipp-core"}, m1294k = 1, m1295mv = {1, 4, 0})
    public static final class Companion {
        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final Codec<Resolution> getCodec() {
            return ResolutionType.codec;
        }
    }

    static {
        Codec.Companion companion = Codec.INSTANCE;
        final ValueTag valueTag = Tag.resolution;
        codec = new Codec<Resolution>() {
            private final Class<Resolution> cls = Resolution.class;

            @Override
            public Class<Resolution> getCls() {
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
                Resolution resolution = (Resolution) value;
                output.writeShort(9);
                output.writeInt(resolution.getCrossFeedResolution());
                output.writeInt(resolution.getFeedResolution());
                output.writeByte((byte) resolution.getUnit().getCode());
            }

            @Override
            public Resolution readValue(IppInputStream input, ValueTag startTag) throws ParseError {
                Intrinsics.checkNotNullParameter(input, "input");
                Intrinsics.checkNotNullParameter(startTag, "startTag");
                input.takeLength$jipp_core(9);
                return new Resolution(input.readInt(), input.readInt(), ResolutionUnit.INSTANCE.get(input.readByte()));
            }
        };
    }
}
