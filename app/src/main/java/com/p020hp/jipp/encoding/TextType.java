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

@Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0016\u0018\u0000 \u000b2\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0002\u000b\fB\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00020\t2\u0006\u0010\n\u001a\u00020\u0004R\u0014\u0010\u0003\u001a\u00020\u0004X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\r"}, m1293d2 = {"Lcom/hp/jipp/encoding/TextType;", "Lcom/hp/jipp/encoding/AttributeTypeImpl;", "Lcom/hp/jipp/encoding/Text;", NamingTable.TAG, "", "(Ljava/lang/String;)V", "getName", "()Ljava/lang/String;", "of", "Lcom/hp/jipp/encoding/Attribute;", "value", "Companion", "Set", "jipp-core"}, m1294k = 1, m1295mv = {1, 4, 0})
public class TextType extends Type<Text> {

    public static final Companion INSTANCE = new Companion(null);
    private static final Codec<Text> codec;
    private final String name;

    public TextType(String name) {
        super(name, Text.class);
        Intrinsics.checkNotNullParameter(name, "name");
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0010\u001c\n\u0002\b\u0002\u0018\u00002\u00020\u00012\b\u0012\u0004\u0012\u00020\u00030\u0002B\r\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J-\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00030\b2\u0006\u0010\t\u001a\u00020\u00052\u0012\u0010\n\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00050\u000b\"\u00020\u0005¢\u0006\u0002\u0010\fJ\u001a\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00030\b2\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00050\u000eJ\b\u0010\u000f\u001a\u00020\u0005H\u0016¨\u0006\u0010"}, m1293d2 = {"Lcom/hp/jipp/encoding/TextType$Set;", "Lcom/hp/jipp/encoding/TextType;", "Lcom/hp/jipp/encoding/AttributeSetType;", "Lcom/hp/jipp/encoding/Text;", NamingTable.TAG, "", "(Ljava/lang/String;)V", "of", "Lcom/hp/jipp/encoding/Attribute;", "value", "values", "", "(Ljava/lang/String;[Ljava/lang/String;)Lcom/hp/jipp/encoding/Attribute;", "ofStrings", "", "toString", "jipp-core"}, m1294k = 1, m1295mv = {1, 4, 0})
    public static final class Set extends TextType implements AttributeSetType<Text> {
        public Set(String name) {
            super(name);
            Intrinsics.checkNotNullParameter(name, "name");
        }

        @Override
        public Attribute<Text> mo419of(Text value, Text... values) {
            Intrinsics.checkNotNullParameter(value, "value");
            Intrinsics.checkNotNullParameter(values, "values");
            return AttributeSetType.DefaultImpls.m422of(this, value, values);
        }

        @Override
        public Attribute<Text> mo417of(Iterable<? extends Text> values) {
            Intrinsics.checkNotNullParameter(values, "values");
            return AttributeSetType.DefaultImpls.m420of((AttributeSetType) this, (Iterable) values);
        }

        public final Attribute<Text> m443of(String value, String... values) {
            Intrinsics.checkNotNullParameter(value, "value");
            Intrinsics.checkNotNullParameter(values, "values");
            List listPlus = CollectionsKt.plus((Collection) CollectionsKt.listOf(value), (Object[]) values);
            ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(listPlus, 10));
            Iterator it = listPlus.iterator();
            while (it.hasNext()) {
                arrayList.add(new Text((String) it.next()));
            }
            return mo417of((Iterable<? extends Text>) arrayList);
        }

        @Override
        public String toString() {
            return "TextType.Set(" + getName() + ')';
        }

        public final Attribute<Text> ofStrings(Iterable<String> values) {
            Intrinsics.checkNotNullParameter(values, "values");
            ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(values, 10));
            Iterator<String> it = values.iterator();
            while (it.hasNext()) {
                arrayList.add(new Text(it.next()));
            }
            return mo417of((Iterable<? extends Text>) arrayList);
        }
    }

    public final Attribute<Text> m442of(String value) {
        Intrinsics.checkNotNullParameter(value, "value");
        return mo418of(new Text(value));
    }

    @Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0017\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, m1293d2 = {"Lcom/hp/jipp/encoding/TextType$Companion;", "", "()V", "codec", "Lcom/hp/jipp/encoding/Codec;", "Lcom/hp/jipp/encoding/Text;", "getCodec", "()Lcom/hp/jipp/encoding/Codec;", "jipp-core"}, m1294k = 1, m1295mv = {1, 4, 0})
    public static final class Companion {
        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final Codec<Text> getCodec() {
            return TextType.codec;
        }
    }

    static {
        Codec.Companion companion = Codec.INSTANCE;
        codec = new Codec<Text>() {
            private final Class<Text> cls = Text.class;

            @Override
            public Class<Text> getCls() {
                return this.cls;
            }

            @Override
            public ValueTag tagOf(Object value) {
                Intrinsics.checkNotNullParameter(value, "value");
                return ((Text) value).getTag();
            }

            @Override
            public void writeValue(IppOutputStream output, Object value) {
                Intrinsics.checkNotNullParameter(output, "output");
                Intrinsics.checkNotNullParameter(value, "value");
                Text text = (Text) value;
                if (Intrinsics.areEqual(text.getTag(), Tag.textWithLanguage)) {
                    IppStreams ippStreams = IppStreams.INSTANCE;
                    String lang = text.getLang();
                    Intrinsics.checkNotNull(lang);
                    output.writeShort(ippStreams.stringLength(lang) + IppStreams.INSTANCE.stringLength(text.getValue()));
                    output.writeStringValue$jipp_core(text.getLang());
                    output.writeStringValue$jipp_core(text.getValue());
                    return;
                }
                output.writeStringValue$jipp_core(text.getValue());
            }

            @Override
            public boolean handlesTag(ValueTag tag) {
                Intrinsics.checkNotNullParameter(tag, "tag");
                return Intrinsics.areEqual(tag, Tag.textWithLanguage) || Intrinsics.areEqual(tag, Tag.textWithoutLanguage);
            }

            @Override
            public Text readValue(IppInputStream input, ValueTag startTag) {
                Text text;
                Intrinsics.checkNotNullParameter(input, "input");
                Intrinsics.checkNotNullParameter(startTag, "startTag");
                if (Intrinsics.areEqual(startTag, Tag.textWithLanguage)) {
                    input.readShort();
                    text = new Text(input.readString$jipp_core(), input.readString$jipp_core());
                } else {
                    text = new Text(input.readString$jipp_core());
                }
                return text;
            }
        };
    }
}
