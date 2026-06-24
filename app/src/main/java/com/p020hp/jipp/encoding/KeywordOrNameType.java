package com.p020hp.jipp.encoding;

import com.p020hp.jipp.encoding.AttributeSetType;
import com.p020hp.jipp.encoding.AttributeType;
import com.p020hp.jipp.encoding.Codec;
import com.p020hp.jipp.encoding.KeywordOrNameType$asString$2;
import com.tom_roush.fontbox.ttf.NamingTable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0016\u0018\u0000 \u00122\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0002\u0012\u0013B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\u0012\u0010\f\u001a\u0004\u0018\u00010\u00022\u0006\u0010\r\u001a\u00020\u000eH\u0016J\u0014\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00020\u00102\u0006\u0010\r\u001a\u00020\u0011J\u0014\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00020\u00102\u0006\u0010\r\u001a\u00020\u0004R!\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00040\u00078FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\b\u0010\t¨\u0006\u0014"}, m1293d2 = {"Lcom/hp/jipp/encoding/KeywordOrNameType;", "Lcom/hp/jipp/encoding/AttributeTypeImpl;", "Lcom/hp/jipp/encoding/KeywordOrName;", NamingTable.TAG, "", "(Ljava/lang/String;)V", "asString", "Lcom/hp/jipp/encoding/AttributeType;", "getAsString", "()Lcom/hp/jipp/encoding/AttributeType;", "asString$delegate", "Lkotlin/Lazy;", "coerce", "value", "", "of", "Lcom/hp/jipp/encoding/Attribute;", "Lcom/hp/jipp/encoding/Name;", "Companion", "Set", "jipp-core"}, m1294k = 1, m1295mv = {1, 4, 0})
public class KeywordOrNameType extends Type<KeywordOrName> {

    public static final Companion INSTANCE = new Companion(null);
    private static final Codec<KeywordOrName> codec;

    private final Lazy asString;

    public final AttributeType<String> getAsString() {
        return (AttributeType) this.asString.getValue();
    }

    public KeywordOrNameType(String name) {
        super(name, KeywordOrName.class);
        Intrinsics.checkNotNullParameter(name, "name");
        this.asString = LazyKt.lazy(new Function0<KeywordOrNameType$asString$2.C16151>() {
            {
                super(0);
            }

            @Override
            public final C16151 invoke() {
                return new AttributeType<String>() {
                    private final String name;

                    {
                        this.name = KeywordOrNameType$asString$2.this.this$0.getName();
                    }

                    @Override
                    public Attribute<String> coerce(Attribute<?> attribute) {
                        Intrinsics.checkNotNullParameter(attribute, "attribute");
                        return AttributeType.DefaultImpls.coerce(this, attribute);
                    }

                    @Override
                    public Attribute<String> empty(OutOfBandTag tag) {
                        Intrinsics.checkNotNullParameter(tag, "tag");
                        return AttributeType.DefaultImpls.empty(this, tag);
                    }

                    @Override
                    public Attribute<String> noValue() {
                        return AttributeType.DefaultImpls.noValue(this);
                    }

                    @Override
                    public Attribute<String> mo418of(String value) {
                        Intrinsics.checkNotNullParameter(value, "value");
                        return AttributeType.DefaultImpls.m423of(this, value);
                    }

                    @Override
                    public Attribute<String> unknown() {
                        return AttributeType.DefaultImpls.unknown(this);
                    }

                    @Override
                    public Attribute<String> unsupported() {
                        return AttributeType.DefaultImpls.unsupported(this);
                    }

                    @Override
                    public String getName() {
                        return this.name;
                    }

                    @Override
                    public String coerce(Object value) {
                        Intrinsics.checkNotNullParameter(value, "value");
                        if (!(value instanceof KeywordOrName)) {
                            if (value instanceof Name) {
                                return ((Name) value).getValue();
                            }
                            if (value instanceof String) {
                                return (String) value;
                            }
                            return null;
                        }
                        KeywordOrName keywordOrName = (KeywordOrName) value;
                        String keyword = keywordOrName.getKeyword();
                        if (keyword != null) {
                            return keyword;
                        }
                        Name name2 = keywordOrName.getName();
                        if (name2 != null) {
                            return name2.getValue();
                        }
                        return null;
                    }
                };
            }
        });
    }

    @Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0004\u0018\u00002\u00020\u00012\b\u0012\u0004\u0012\u00020\u00030\u0002B\r\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J-\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00030\b2\u0006\u0010\t\u001a\u00020\n2\u0012\u0010\u000b\u001a\n\u0012\u0006\b\u0001\u0012\u00020\n0\f\"\u00020\n¢\u0006\u0002\u0010\rJ-\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00030\b2\u0006\u0010\t\u001a\u00020\u00052\u0012\u0010\u000b\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00050\f\"\u00020\u0005¢\u0006\u0002\u0010\u000eJ\b\u0010\u000f\u001a\u00020\u0005H\u0016¨\u0006\u0010"}, m1293d2 = {"Lcom/hp/jipp/encoding/KeywordOrNameType$Set;", "Lcom/hp/jipp/encoding/KeywordOrNameType;", "Lcom/hp/jipp/encoding/AttributeSetType;", "Lcom/hp/jipp/encoding/KeywordOrName;", NamingTable.TAG, "", "(Ljava/lang/String;)V", "of", "Lcom/hp/jipp/encoding/Attribute;", "value", "Lcom/hp/jipp/encoding/Name;", "values", "", "(Lcom/hp/jipp/encoding/Name;[Lcom/hp/jipp/encoding/Name;)Lcom/hp/jipp/encoding/Attribute;", "(Ljava/lang/String;[Ljava/lang/String;)Lcom/hp/jipp/encoding/Attribute;", "toString", "jipp-core"}, m1294k = 1, m1295mv = {1, 4, 0})
    public static final class Set extends KeywordOrNameType implements AttributeSetType<KeywordOrName> {
        public Set(String name) {
            super(name);
            Intrinsics.checkNotNullParameter(name, "name");
        }

        @Override
        public Attribute<KeywordOrName> mo419of(KeywordOrName value, KeywordOrName... values) {
            Intrinsics.checkNotNullParameter(value, "value");
            Intrinsics.checkNotNullParameter(values, "values");
            return AttributeSetType.DefaultImpls.m422of(this, value, values);
        }

        @Override
        public Attribute<KeywordOrName> mo417of(Iterable<? extends KeywordOrName> values) {
            Intrinsics.checkNotNullParameter(values, "values");
            return AttributeSetType.DefaultImpls.m420of((AttributeSetType) this, (Iterable) values);
        }

        public final Attribute<KeywordOrName> m439of(String value, String... values) {
            Intrinsics.checkNotNullParameter(value, "value");
            Intrinsics.checkNotNullParameter(values, "values");
            List listPlus = CollectionsKt.plus((Collection) CollectionsKt.listOf(value), (Object[]) values);
            ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(listPlus, 10));
            Iterator it = listPlus.iterator();
            while (it.hasNext()) {
                arrayList.add(new KeywordOrName((String) it.next()));
            }
            return mo417of((Iterable<? extends KeywordOrName>) arrayList);
        }

        public final Attribute<KeywordOrName> m438of(Name value, Name... values) {
            Intrinsics.checkNotNullParameter(value, "value");
            Intrinsics.checkNotNullParameter(values, "values");
            List listPlus = CollectionsKt.plus((Collection) CollectionsKt.listOf(value), (Object[]) values);
            ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(listPlus, 10));
            Iterator it = listPlus.iterator();
            while (it.hasNext()) {
                arrayList.add(new KeywordOrName((Name) it.next()));
            }
            return mo417of((Iterable<? extends KeywordOrName>) arrayList);
        }

        @Override
        public String toString() {
            return "KeywordOrNameType.Set(" + getName() + ')';
        }
    }

    @Override
    public KeywordOrName coerce(Object value) {
        KeywordOrName keywordOrName;
        Intrinsics.checkNotNullParameter(value, "value");
        if (value instanceof KeywordOrName) {
            return (KeywordOrName) value;
        }
        if (value instanceof Name) {
            keywordOrName = new KeywordOrName((Name) value);
        } else {
            if (!(value instanceof String)) {
                return null;
            }
            keywordOrName = new KeywordOrName((String) value);
        }
        return keywordOrName;
    }

    public final Attribute<KeywordOrName> m437of(String value) {
        Intrinsics.checkNotNullParameter(value, "value");
        return mo418of(new KeywordOrName(value));
    }

    public final Attribute<KeywordOrName> m436of(Name value) {
        Intrinsics.checkNotNullParameter(value, "value");
        return mo418of(new KeywordOrName(value));
    }

    @Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0017\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, m1293d2 = {"Lcom/hp/jipp/encoding/KeywordOrNameType$Companion;", "", "()V", "codec", "Lcom/hp/jipp/encoding/Codec;", "Lcom/hp/jipp/encoding/KeywordOrName;", "getCodec", "()Lcom/hp/jipp/encoding/Codec;", "jipp-core"}, m1294k = 1, m1295mv = {1, 4, 0})
    public static final class Companion {
        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final Codec<KeywordOrName> getCodec() {
            return KeywordOrNameType.codec;
        }
    }

    static {
        Codec.Companion companion = Codec.INSTANCE;
        codec = new Codec<KeywordOrName>() {
            private final Class<KeywordOrName> cls = KeywordOrName.class;

            @Override
            public boolean handlesTag(ValueTag tag) {
                Intrinsics.checkNotNullParameter(tag, "tag");
                return false;
            }

            @Override
            public Class<KeywordOrName> getCls() {
                return this.cls;
            }

            @Override
            public ValueTag tagOf(Object value) {
                Intrinsics.checkNotNullParameter(value, "value");
                return ((KeywordOrName) value).getTag();
            }

            @Override
            public void writeValue(IppOutputStream output, Object value) {
                Intrinsics.checkNotNullParameter(output, "output");
                Intrinsics.checkNotNullParameter(value, "value");
                KeywordOrName keywordOrName = (KeywordOrName) value;
                if (NameType.INSTANCE.getCodec().handlesTag(keywordOrName.getTag())) {
                    Codec<Name> codec2 = NameType.INSTANCE.getCodec();
                    Name name = keywordOrName.getName();
                    Intrinsics.checkNotNull(name);
                    codec2.writeValue(output, name);
                    return;
                }
                Codec<String> codec3 = KeywordType.INSTANCE.getCodec();
                String keyword = keywordOrName.getKeyword();
                Intrinsics.checkNotNull(keyword);
                codec3.writeValue(output, keyword);
            }

            @Override
            public KeywordOrName readValue(IppInputStream input, ValueTag startTag) {
                Intrinsics.checkNotNullParameter(input, "input");
                Intrinsics.checkNotNullParameter(startTag, "startTag");
                throw new IllegalArgumentException("This codec is not used for reading");
            }
        };
    }
}
