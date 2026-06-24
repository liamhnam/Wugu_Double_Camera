package com.p020hp.jipp.encoding;

import com.p020hp.jipp.encoding.AttributeSetType;
import com.tom_roush.fontbox.ttf.NamingTable;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;

@Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0004\b\u0016\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\u000fB\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\u0012\u0010\u0006\u001a\u0004\u0018\u00010\u00022\u0006\u0010\u0007\u001a\u00020\bH\u0016J%\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00020\n2\u0012\u0010\u000b\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00040\f\"\u00020\u0004¢\u0006\u0002\u0010\rJ\b\u0010\u000e\u001a\u00020\u0004H\u0016¨\u0006\u0010"}, m1293d2 = {"Lcom/hp/jipp/encoding/KeyValuesType;", "Lcom/hp/jipp/encoding/AttributeTypeImpl;", "Lcom/hp/jipp/encoding/KeyValues;", NamingTable.TAG, "", "(Ljava/lang/String;)V", "coerce", "value", "", "of", "Lcom/hp/jipp/encoding/Attribute;", "keyValues", "", "([Ljava/lang/String;)Lcom/hp/jipp/encoding/Attribute;", "toString", "Set", "jipp-core"}, m1294k = 1, m1295mv = {1, 4, 0})
public class KeyValuesType extends Type<KeyValues> {
    public KeyValuesType(String name) {
        super(name, KeyValues.class);
        Intrinsics.checkNotNullParameter(name, "name");
    }

    @Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\u0018\u00002\u00020\u00012\b\u0012\u0004\u0012\u00020\u00030\u0002B\r\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\b\u0010\u0007\u001a\u00020\u0005H\u0016¨\u0006\b"}, m1293d2 = {"Lcom/hp/jipp/encoding/KeyValuesType$Set;", "Lcom/hp/jipp/encoding/KeyValuesType;", "Lcom/hp/jipp/encoding/AttributeSetType;", "Lcom/hp/jipp/encoding/KeyValues;", NamingTable.TAG, "", "(Ljava/lang/String;)V", "toString", "jipp-core"}, m1294k = 1, m1295mv = {1, 4, 0})
    public static final class Set extends KeyValuesType implements AttributeSetType<KeyValues> {
        public Set(String name) {
            super(name);
            Intrinsics.checkNotNullParameter(name, "name");
        }

        @Override
        public Attribute<KeyValues> mo419of(KeyValues value, KeyValues... values) {
            Intrinsics.checkNotNullParameter(value, "value");
            Intrinsics.checkNotNullParameter(values, "values");
            return AttributeSetType.DefaultImpls.m422of(this, value, values);
        }

        @Override
        public Attribute<KeyValues> mo417of(Iterable<? extends KeyValues> values) {
            Intrinsics.checkNotNullParameter(values, "values");
            return AttributeSetType.DefaultImpls.m420of((AttributeSetType) this, (Iterable) values);
        }

        @Override
        public String toString() {
            return "KeyValuesType.Set(" + getName() + ')';
        }
    }

    public final Attribute<KeyValues> m435of(String... keyValues) {
        Intrinsics.checkNotNullParameter(keyValues, "keyValues");
        return mo418of(new KeyValues((String[]) Arrays.copyOf(keyValues, keyValues.length)));
    }

    @Override
    public KeyValues coerce(Object value) {
        Intrinsics.checkNotNullParameter(value, "value");
        if (value instanceof String) {
            return KeyValues.INSTANCE.parse((String) value);
        }
        if (value instanceof byte[]) {
            return coerce((Object) new String((byte[]) value, Charsets.UTF_8));
        }
        if (value instanceof KeyValues) {
            return (KeyValues) value;
        }
        return null;
    }

    @Override
    public String toString() {
        return "KeyValuesType(" + getName() + ')';
    }
}
