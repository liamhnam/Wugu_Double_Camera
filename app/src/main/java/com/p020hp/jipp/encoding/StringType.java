package com.p020hp.jipp.encoding;

import com.p020hp.jipp.encoding.AttributeSetType;
import com.tom_roush.fontbox.ttf.NamingTable;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0000\n\u0002\b\u0002\b\u0016\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\fB\u0015\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0002¢\u0006\u0002\u0010\u0006J\u0012\u0010\t\u001a\u0004\u0018\u00010\u00022\u0006\u0010\n\u001a\u00020\u000bH\u0016R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\r"}, m1293d2 = {"Lcom/hp/jipp/encoding/StringType;", "Lcom/hp/jipp/encoding/AttributeTypeImpl;", "", "tag", "Lcom/hp/jipp/encoding/Tag;", NamingTable.TAG, "(Lcom/hp/jipp/encoding/Tag;Ljava/lang/String;)V", "getTag", "()Lcom/hp/jipp/encoding/Tag;", "coerce", "value", "", "Set", "jipp-core"}, m1294k = 1, m1295mv = {1, 4, 0})
public class StringType extends Type<String> {
    private final Tag tag;

    public StringType(Tag tag, String name) {
        super(name, String.class);
        Intrinsics.checkNotNullParameter(tag, "tag");
        Intrinsics.checkNotNullParameter(name, "name");
        this.tag = tag;
    }

    public final Tag getTag() {
        return this.tag;
    }

    @Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u00012\b\u0012\u0004\u0012\u00020\u00030\u0002B\u0015\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0003¢\u0006\u0002\u0010\u0007J\b\u0010\b\u001a\u00020\u0003H\u0016¨\u0006\t"}, m1293d2 = {"Lcom/hp/jipp/encoding/StringType$Set;", "Lcom/hp/jipp/encoding/StringType;", "Lcom/hp/jipp/encoding/AttributeSetType;", "", "tag", "Lcom/hp/jipp/encoding/Tag;", NamingTable.TAG, "(Lcom/hp/jipp/encoding/Tag;Ljava/lang/String;)V", "toString", "jipp-core"}, m1294k = 1, m1295mv = {1, 4, 0})
    public static final class Set extends StringType implements AttributeSetType<String> {
        public Set(Tag tag, String name) {
            super(tag, name);
            Intrinsics.checkNotNullParameter(tag, "tag");
            Intrinsics.checkNotNullParameter(name, "name");
        }

        @Override
        public Attribute<String> mo417of(Iterable<? extends String> values) {
            Intrinsics.checkNotNullParameter(values, "values");
            return AttributeSetType.DefaultImpls.m420of((AttributeSetType) this, (Iterable) values);
        }

        @Override
        public Attribute<String> mo419of(String value, String... values) {
            Intrinsics.checkNotNullParameter(value, "value");
            Intrinsics.checkNotNullParameter(values, "values");
            return AttributeSetType.DefaultImpls.m422of(this, value, values);
        }

        @Override
        public String toString() {
            return "StringType.Set(" + getName() + ')';
        }
    }

    @Override
    public String coerce(Object value) {
        Intrinsics.checkNotNullParameter(value, "value");
        if (value instanceof String) {
            return (String) value;
        }
        if (value instanceof OtherString) {
            return ((OtherString) value).getValue();
        }
        return null;
    }
}
