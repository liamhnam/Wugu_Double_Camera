package com.p020hp.jipp.encoding;

import com.p020hp.jipp.encoding.AttributeType;
import com.tom_roush.fontbox.ttf.NamingTable;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\b\u0016\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003B\u001b\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00028\u00000\u0007¢\u0006\u0002\u0010\bJ\u0017\u0010\u000b\u001a\u0004\u0018\u00018\u00002\u0006\u0010\f\u001a\u00020\u0002H\u0016¢\u0006\u0002\u0010\rJ\b\u0010\u000e\u001a\u00020\u0005H\u0016R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00028\u00000\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0004\u001a\u00020\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u000f"}, m1293d2 = {"Lcom/hp/jipp/encoding/AttributeTypeImpl;", "T", "", "Lcom/hp/jipp/encoding/AttributeType;", NamingTable.TAG, "", "cls", "Ljava/lang/Class;", "(Ljava/lang/String;Ljava/lang/Class;)V", "getName", "()Ljava/lang/String;", "coerce", "value", "(Ljava/lang/Object;)Ljava/lang/Object;", "toString", "jipp-core"}, m1294k = 1, m1295mv = {1, 4, 0})
public class Type<T> implements AttributeType<T> {
    private final Class<T> cls;
    private final String name;

    public Type(String name, Class<T> cls) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(cls, "cls");
        this.name = name;
        this.cls = cls;
    }

    @Override
    public Attribute<T> coerce(Attribute<?> attribute) {
        Intrinsics.checkNotNullParameter(attribute, "attribute");
        return AttributeType.DefaultImpls.coerce(this, attribute);
    }

    @Override
    public Attribute<T> empty(OutOfBandTag tag) {
        Intrinsics.checkNotNullParameter(tag, "tag");
        return AttributeType.DefaultImpls.empty(this, tag);
    }

    @Override
    public Attribute<T> noValue() {
        return AttributeType.DefaultImpls.noValue(this);
    }

    @Override
    public Attribute<T> mo418of(T value) {
        Intrinsics.checkNotNullParameter(value, "value");
        return AttributeType.DefaultImpls.m423of(this, value);
    }

    @Override
    public Attribute<T> unknown() {
        return AttributeType.DefaultImpls.unknown(this);
    }

    @Override
    public Attribute<T> unsupported() {
        return AttributeType.DefaultImpls.unsupported(this);
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public T coerce(Object value) {
        Intrinsics.checkNotNullParameter(value, "value");
        if (this.cls.isInstance(value)) {
            return value;
        }
        return null;
    }

    public String toString() {
        return this.cls.getSimpleName() + "Type(" + getName() + ')';
    }
}
