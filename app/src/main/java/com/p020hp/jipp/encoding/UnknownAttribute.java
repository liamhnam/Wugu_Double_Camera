package com.p020hp.jipp.encoding;

import com.p020hp.jipp.encoding.AttributeSetType;
import com.p020hp.jipp.encoding.AttributeType;
import com.tom_roush.fontbox.ttf.NamingTable;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

@Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0000\n\u0002\u0010 \n\u0002\b\u0004\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0002\u000b\fB+\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0002\u0012\u0012\u0010\u0006\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00020\u0007\"\u00020\u0002¢\u0006\u0002\u0010\bB\u001b\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00020\t¢\u0006\u0002\u0010\n¨\u0006\r"}, m1293d2 = {"Lcom/hp/jipp/encoding/UnknownAttribute;", "Lcom/hp/jipp/encoding/AttributeImpl;", "", NamingTable.TAG, "", "value", "values", "", "(Ljava/lang/String;Ljava/lang/Object;[Ljava/lang/Object;)V", "", "(Ljava/lang/String;Ljava/util/List;)V", "SetType", "Type", "jipp-core"}, m1294k = 1, m1295mv = {1, 4, 0})
public final class UnknownAttribute extends AttributeImpl<Object> {
    public UnknownAttribute(String name, List<? extends Object> values) {
        super(name, new Type(name), values);
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(values, "values");
    }

    public UnknownAttribute(String name, Object value, Object... values) {
        this(name, CollectionsKt.plus((Collection) CollectionsKt.listOf(value), values));
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(value, "value");
        Intrinsics.checkNotNullParameter(values, "values");
    }

    @Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\u0012\u0010\b\u001a\u0004\u0018\u00010\u00022\u0006\u0010\t\u001a\u00020\u0002H\u0016R\u0014\u0010\u0003\u001a\u00020\u0004X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\n"}, m1293d2 = {"Lcom/hp/jipp/encoding/UnknownAttribute$Type;", "Lcom/hp/jipp/encoding/AttributeType;", "", NamingTable.TAG, "", "(Ljava/lang/String;)V", "getName", "()Ljava/lang/String;", "coerce", "value", "jipp-core"}, m1294k = 1, m1295mv = {1, 4, 0})
    public static final class Type implements AttributeType<Object> {
        private final String name;

        @Override
        public Object coerce(Object value) {
            Intrinsics.checkNotNullParameter(value, "value");
            return value;
        }

        public Type(String name) {
            Intrinsics.checkNotNullParameter(name, "name");
            this.name = name;
        }

        @Override
        public Attribute<Object> coerce(Attribute<?> attribute) {
            Intrinsics.checkNotNullParameter(attribute, "attribute");
            return AttributeType.DefaultImpls.coerce(this, attribute);
        }

        @Override
        public Attribute<Object> empty(OutOfBandTag tag) {
            Intrinsics.checkNotNullParameter(tag, "tag");
            return AttributeType.DefaultImpls.empty(this, tag);
        }

        @Override
        public String getName() {
            return this.name;
        }

        @Override
        public Attribute<Object> noValue() {
            return AttributeType.DefaultImpls.noValue(this);
        }

        @Override
        public Attribute<Object> mo418of(Object value) {
            Intrinsics.checkNotNullParameter(value, "value");
            return AttributeType.DefaultImpls.m423of(this, value);
        }

        @Override
        public Attribute<Object> unknown() {
            return AttributeType.DefaultImpls.unknown(this);
        }

        @Override
        public Attribute<Object> unsupported() {
            return AttributeType.DefaultImpls.unsupported(this);
        }
    }

    @Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\u0012\u0010\b\u001a\u0004\u0018\u00010\u00022\u0006\u0010\t\u001a\u00020\u0002H\u0016R\u0014\u0010\u0003\u001a\u00020\u0004X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\n"}, m1293d2 = {"Lcom/hp/jipp/encoding/UnknownAttribute$SetType;", "Lcom/hp/jipp/encoding/AttributeSetType;", "", NamingTable.TAG, "", "(Ljava/lang/String;)V", "getName", "()Ljava/lang/String;", "coerce", "value", "jipp-core"}, m1294k = 1, m1295mv = {1, 4, 0})
    public static final class SetType implements AttributeSetType<Object> {
        private final String name;

        @Override
        public Object coerce(Object value) {
            Intrinsics.checkNotNullParameter(value, "value");
            return value;
        }

        public SetType(String name) {
            Intrinsics.checkNotNullParameter(name, "name");
            this.name = name;
        }

        @Override
        public Attribute<Object> coerce(Attribute<?> attribute) {
            Intrinsics.checkNotNullParameter(attribute, "attribute");
            return AttributeSetType.DefaultImpls.coerce(this, attribute);
        }

        @Override
        public Attribute<Object> empty(OutOfBandTag tag) {
            Intrinsics.checkNotNullParameter(tag, "tag");
            return AttributeSetType.DefaultImpls.empty(this, tag);
        }

        @Override
        public String getName() {
            return this.name;
        }

        @Override
        public Attribute<Object> noValue() {
            return AttributeSetType.DefaultImpls.noValue(this);
        }

        @Override
        public Attribute<Object> mo417of(Iterable<? extends Object> values) {
            Intrinsics.checkNotNullParameter(values, "values");
            return AttributeSetType.DefaultImpls.m420of((AttributeSetType) this, (Iterable) values);
        }

        @Override
        public Attribute<Object> mo418of(Object value) {
            Intrinsics.checkNotNullParameter(value, "value");
            return AttributeSetType.DefaultImpls.m421of(this, value);
        }

        @Override
        public Attribute<Object> mo419of(Object value, Object... values) {
            Intrinsics.checkNotNullParameter(value, "value");
            Intrinsics.checkNotNullParameter(values, "values");
            return AttributeSetType.DefaultImpls.m422of(this, value, values);
        }

        @Override
        public Attribute<Object> unknown() {
            return AttributeSetType.DefaultImpls.unknown(this);
        }

        @Override
        public Attribute<Object> unsupported() {
            return AttributeSetType.DefaultImpls.unsupported(this);
        }
    }
}
