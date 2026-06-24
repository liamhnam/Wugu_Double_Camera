package com.p020hp.jipp.encoding;

import com.p020hp.jipp.encoding.AttributeSetType;
import com.p020hp.jipp.encoding.Codec;
import com.tom_roush.fontbox.ttf.NamingTable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0016\u0018\u0000 \t2\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0002\t\nB\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00020\u00072\u0006\u0010\b\u001a\u00020\u0004¨\u0006\u000b"}, m1293d2 = {"Lcom/hp/jipp/encoding/NameType;", "Lcom/hp/jipp/encoding/AttributeTypeImpl;", "Lcom/hp/jipp/encoding/Name;", NamingTable.TAG, "", "(Ljava/lang/String;)V", "of", "Lcom/hp/jipp/encoding/Attribute;", "value", "Companion", "Set", "jipp-core"}, m1294k = 1, m1295mv = {1, 4, 0})
public class NameType extends Type<Name> {

    public static final Companion INSTANCE = new Companion(null);
    private static final Codec<Name> codec;

    public NameType(String name) {
        super(name, Name.class);
        Intrinsics.checkNotNullParameter(name, "name");
    }

    @Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0010\u001c\n\u0002\b\u0002\u0018\u00002\u00020\u00012\b\u0012\u0004\u0012\u00020\u00030\u0002B\r\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J-\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00030\b2\u0006\u0010\t\u001a\u00020\u00052\u0012\u0010\n\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00050\u000b\"\u00020\u0005¢\u0006\u0002\u0010\fJ\u001a\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00030\b2\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00050\u000eJ\b\u0010\u000f\u001a\u00020\u0005H\u0016¨\u0006\u0010"}, m1293d2 = {"Lcom/hp/jipp/encoding/NameType$Set;", "Lcom/hp/jipp/encoding/NameType;", "Lcom/hp/jipp/encoding/AttributeSetType;", "Lcom/hp/jipp/encoding/Name;", NamingTable.TAG, "", "(Ljava/lang/String;)V", "of", "Lcom/hp/jipp/encoding/Attribute;", "value", "values", "", "(Ljava/lang/String;[Ljava/lang/String;)Lcom/hp/jipp/encoding/Attribute;", "ofStrings", "", "toString", "jipp-core"}, m1294k = 1, m1295mv = {1, 4, 0})
    public static final class Set extends NameType implements AttributeSetType<Name> {
        public Set(String name) {
            super(name);
            Intrinsics.checkNotNullParameter(name, "name");
        }

        @Override
        public Attribute<Name> mo419of(Name value, Name... values) {
            Intrinsics.checkNotNullParameter(value, "value");
            Intrinsics.checkNotNullParameter(values, "values");
            return AttributeSetType.DefaultImpls.m422of(this, value, values);
        }

        @Override
        public Attribute<Name> mo417of(Iterable<? extends Name> values) {
            Intrinsics.checkNotNullParameter(values, "values");
            return AttributeSetType.DefaultImpls.m420of((AttributeSetType) this, (Iterable) values);
        }

        public final Attribute<Name> m441of(String value, String... values) {
            Intrinsics.checkNotNullParameter(value, "value");
            Intrinsics.checkNotNullParameter(values, "values");
            List listPlus = CollectionsKt.plus((Collection) CollectionsKt.listOf(value), (Object[]) values);
            ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(listPlus, 10));
            Iterator it = listPlus.iterator();
            while (it.hasNext()) {
                arrayList.add(new Name((String) it.next()));
            }
            return mo417of((Iterable<? extends Name>) arrayList);
        }

        @Override
        public String toString() {
            return "NameType.Set(" + getName() + ')';
        }

        public final Attribute<Name> ofStrings(Iterable<String> values) {
            Intrinsics.checkNotNullParameter(values, "values");
            ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(values, 10));
            Iterator<String> it = values.iterator();
            while (it.hasNext()) {
                arrayList.add(new Name(it.next()));
            }
            return mo417of((Iterable<? extends Name>) arrayList);
        }
    }

    public final Attribute<Name> m440of(String value) {
        Intrinsics.checkNotNullParameter(value, "value");
        return mo418of(new Name(value));
    }

    @Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0017\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, m1293d2 = {"Lcom/hp/jipp/encoding/NameType$Companion;", "", "()V", "codec", "Lcom/hp/jipp/encoding/Codec;", "Lcom/hp/jipp/encoding/Name;", "getCodec", "()Lcom/hp/jipp/encoding/Codec;", "jipp-core"}, m1294k = 1, m1295mv = {1, 4, 0})
    public static final class Companion {
        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final Codec<Name> getCodec() {
            return NameType.codec;
        }
    }

    static {
        Codec.Companion companion = Codec.INSTANCE;
        codec = new Codec<Name>() {
            private final Class<Name> cls = Name.class;

            @Override
            public Class<Name> getCls() {
                return this.cls;
            }

            @Override
            public ValueTag tagOf(Object value) {
                Intrinsics.checkNotNullParameter(value, "value");
                return ((Name) value).getTag();
            }

            @Override
            public void writeValue(IppOutputStream output, Object value) {
                Intrinsics.checkNotNullParameter(output, "output");
                Intrinsics.checkNotNullParameter(value, "value");
                Name name = (Name) value;
                if (Intrinsics.areEqual(name.getTag(), Tag.nameWithLanguage)) {
                    IppStreams ippStreams = IppStreams.INSTANCE;
                    String lang = name.getLang();
                    Intrinsics.checkNotNull(lang);
                    output.writeShort(ippStreams.stringLength(lang) + IppStreams.INSTANCE.stringLength(name.getValue()));
                    output.writeStringValue$jipp_core(name.getLang());
                    output.writeStringValue$jipp_core(name.getValue());
                    return;
                }
                output.writeStringValue$jipp_core(name.getValue());
            }

            @Override
            public boolean handlesTag(ValueTag tag) {
                Intrinsics.checkNotNullParameter(tag, "tag");
                return Intrinsics.areEqual(tag, Tag.nameWithLanguage) || Intrinsics.areEqual(tag, Tag.nameWithoutLanguage);
            }

            @Override
            public Name readValue(IppInputStream input, ValueTag startTag) {
                Name name;
                Intrinsics.checkNotNullParameter(input, "input");
                Intrinsics.checkNotNullParameter(startTag, "startTag");
                if (Intrinsics.areEqual(startTag, Tag.nameWithLanguage)) {
                    input.readShort();
                    name = new Name(input.readString$jipp_core(), input.readString$jipp_core());
                } else {
                    name = new Name(input.readString$jipp_core());
                }
                return name;
            }
        };
    }
}
