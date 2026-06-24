package com.p020hp.jipp.encoding;

import com.p020hp.jipp.encoding.AttributeCollection;
import com.p020hp.jipp.encoding.AttributeSetType;
import com.p020hp.jipp.model.PrinterServiceType;
import com.p020hp.jipp.util.PrettyPrinter;
import com.tom_roush.fontbox.ttf.NamingTable;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0086\b\u0018\u00002\u00020\u0001:\u0002\u0012\u0013B\u0017\u0012\u0010\u0010\u0002\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00040\u0003¢\u0006\u0002\u0010\u0005J\u0013\u0010\b\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00040\u0003HÆ\u0003J\u001d\u0010\t\u001a\u00020\u00002\u0012\b\u0002\u0010\u0002\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00040\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rHÖ\u0003J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001J\t\u0010\u0010\u001a\u00020\u0011HÖ\u0001R\u001e\u0010\u0002\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00040\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0014"}, m1293d2 = {"Lcom/hp/jipp/encoding/UntypedCollection;", "Lcom/hp/jipp/encoding/AttributeCollection;", "attributes", "", "Lcom/hp/jipp/encoding/Attribute;", "(Ljava/util/List;)V", "getAttributes", "()Ljava/util/List;", "component1", PrinterServiceType.copy, "equals", "", "other", "", "hashCode", "", "toString", "", "SetType", "Type", "jipp-core"}, m1294k = 1, m1295mv = {1, 4, 0})
public final class UntypedCollection implements AttributeCollection {
    private final List<Attribute<?>> attributes;

    public static UntypedCollection copy$default(UntypedCollection untypedCollection, List list, int i, Object obj) {
        if ((i & 1) != 0) {
            list = untypedCollection.getAttributes();
        }
        return untypedCollection.copy(list);
    }

    public final List<Attribute<?>> component1() {
        return getAttributes();
    }

    public final UntypedCollection copy(List<? extends Attribute<?>> attributes) {
        Intrinsics.checkNotNullParameter(attributes, "attributes");
        return new UntypedCollection(attributes);
    }

    public boolean equals(Object other) {
        if (this != other) {
            return (other instanceof UntypedCollection) && Intrinsics.areEqual(getAttributes(), ((UntypedCollection) other).getAttributes());
        }
        return true;
    }

    public int hashCode() {
        List<Attribute<?>> attributes = getAttributes();
        if (attributes != null) {
            return attributes.hashCode();
        }
        return 0;
    }

    public String toString() {
        return "UntypedCollection(attributes=" + getAttributes() + ")";
    }

    public UntypedCollection(List<? extends Attribute<?>> attributes) {
        Intrinsics.checkNotNullParameter(attributes, "attributes");
        this.attributes = attributes;
    }

    @Override
    public List<Attribute<?>> getAttributes() {
        return this.attributes;
    }

    @Override
    public void print(PrettyPrinter printer) {
        Intrinsics.checkNotNullParameter(printer, "printer");
        AttributeCollection.DefaultImpls.print(this, printer);
    }

    @Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005¨\u0006\u0006"}, m1293d2 = {"Lcom/hp/jipp/encoding/UntypedCollection$Type;", "Lcom/hp/jipp/encoding/AttributeTypeImpl;", "Lcom/hp/jipp/encoding/UntypedCollection;", NamingTable.TAG, "", "(Ljava/lang/String;)V", "jipp-core"}, m1294k = 1, m1295mv = {1, 4, 0})
    public static final class Type extends com.p020hp.jipp.encoding.Type<UntypedCollection> {
        public Type(String name) {
            super(name, UntypedCollection.class);
            Intrinsics.checkNotNullParameter(name, "name");
        }
    }

    @Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\b\u0012\u0004\u0012\u00020\u00020\u0003B\r\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\b\u0010\u0007\u001a\u00020\u0005H\u0016¨\u0006\b"}, m1293d2 = {"Lcom/hp/jipp/encoding/UntypedCollection$SetType;", "Lcom/hp/jipp/encoding/AttributeTypeImpl;", "Lcom/hp/jipp/encoding/UntypedCollection;", "Lcom/hp/jipp/encoding/AttributeSetType;", NamingTable.TAG, "", "(Ljava/lang/String;)V", "toString", "jipp-core"}, m1294k = 1, m1295mv = {1, 4, 0})
    public static final class SetType extends com.p020hp.jipp.encoding.Type<UntypedCollection> implements AttributeSetType<UntypedCollection> {
        public SetType(String name) {
            super(name, UntypedCollection.class);
            Intrinsics.checkNotNullParameter(name, "name");
        }

        @Override
        public Attribute<UntypedCollection> mo419of(UntypedCollection value, UntypedCollection... values) {
            Intrinsics.checkNotNullParameter(value, "value");
            Intrinsics.checkNotNullParameter(values, "values");
            return AttributeSetType.DefaultImpls.m422of(this, value, values);
        }

        @Override
        public Attribute<UntypedCollection> mo417of(Iterable<? extends UntypedCollection> values) {
            Intrinsics.checkNotNullParameter(values, "values");
            return AttributeSetType.DefaultImpls.m420of((AttributeSetType) this, (Iterable) values);
        }

        @Override
        public String toString() {
            return "UntypedCollection.Set(" + getName() + ')';
        }
    }
}
