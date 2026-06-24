package com.p020hp.jipp.encoding;

import com.p020hp.jipp.encoding.AttributeType;
import com.tom_roush.fontbox.ttf.NamingTable;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\b\u0016\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003B\u0015\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u0017\u0010\r\u001a\u0004\u0018\u00018\u00002\u0006\u0010\u000e\u001a\u00020\u0002H\u0016¢\u0006\u0002\u0010\u000fR\u0014\u0010\u0004\u001a\u00020\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\u0010"}, m1293d2 = {"Lcom/hp/jipp/encoding/EmptyAttributeType;", "T", "", "Lcom/hp/jipp/encoding/AttributeType;", NamingTable.TAG, "", "tag", "Lcom/hp/jipp/encoding/OutOfBandTag;", "(Ljava/lang/String;Lcom/hp/jipp/encoding/OutOfBandTag;)V", "getName", "()Ljava/lang/String;", "getTag", "()Lcom/hp/jipp/encoding/OutOfBandTag;", "coerce", "value", "(Ljava/lang/Object;)Ljava/lang/Object;", "jipp-core"}, m1294k = 1, m1295mv = {1, 4, 0})
public class EmptyAttributeType<T> implements AttributeType<T> {
    private final String name;
    private final OutOfBandTag tag;

    @Override
    public T coerce(Object value) {
        Intrinsics.checkNotNullParameter(value, "value");
        return null;
    }

    public EmptyAttributeType(String name, OutOfBandTag tag) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(tag, "tag");
        this.name = name;
        this.tag = tag;
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

    public final OutOfBandTag getTag() {
        return this.tag;
    }
}
