package com.p020hp.jipp.encoding;

import com.arthenica.ffmpegkit.MediaInformation;
import com.arthenica.ffmpegkit.StreamInformation;
import com.p020hp.jipp.encoding.AttributeGroup;
import com.p020hp.jipp.encoding.KeywordOrNameType;
import com.p020hp.jipp.encoding.NameType;
import com.p020hp.jipp.encoding.TextType;
import com.p020hp.jipp.util.PrettyPrinter;
import com.tom_roush.fontbox.ttf.NamingTable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.Unit;
import kotlin.collections.AbstractList;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u0000\u009a\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0003\n\u0002\u0010\u001c\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0016\u0018\u00002\u00020\u00012\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00030\u0002B#\b\u0007\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0012\b\u0002\u0010\u0006\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00030\u0007¢\u0006\u0002\u0010\bJ\u0014\u0010\u0015\u001a\u00020\u00162\n\u0010\u0017\u001a\u0006\u0012\u0002\b\u00030\u0003H\u0007J)\u0010\u0015\u001a\u00020\u00162\u001a\u0010\u0006\u001a\u000e\u0012\n\b\u0001\u0012\u0006\u0012\u0002\b\u00030\u00030\u0018\"\u0006\u0012\u0002\b\u00030\u0003H\u0007¢\u0006\u0002\u0010\u0019J\u001c\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u001a\u001a\u00020\u000e2\n\u0010\u0017\u001a\u0006\u0012\u0002\b\u00030\u0003H\u0007J\"\u0010\u001b\u001a\u00020\u00162\u0006\u0010\u001a\u001a\u00020\u000e2\u0010\u0010\u0006\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00030\u0007H\u0007J\u001a\u0010\u001b\u001a\u00020\u00162\u0010\u0010\u0006\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00030\u001cH\u0007JA\u0010\u001d\u001a\u00020\u0016\"\b\b\u0000\u0010\u001e*\u00020\u001f2\f\u0010 \u001a\b\u0012\u0004\u0012\u0002H\u001e0!2\u0006\u0010\"\u001a\u0002H\u001e2\u0012\u0010#\u001a\n\u0012\u0006\b\u0001\u0012\u0002H\u001e0\u0018\"\u0002H\u001eH\u0007¢\u0006\u0002\u0010$J-\u0010\u001d\u001a\u00020\u0016\"\b\b\u0000\u0010\u001e*\u00020\u001f2\f\u0010 \u001a\b\u0012\u0004\u0012\u0002H\u001e0%2\u0006\u0010\"\u001a\u0002H\u001eH\u0007¢\u0006\u0002\u0010&J\u0018\u0010\u001d\u001a\u00020\u00162\u0006\u0010'\u001a\u00020(2\u0006\u0010\"\u001a\u00020\u000bH\u0007J1\u0010\u001d\u001a\u00020\u00162\u0006\u0010'\u001a\u00020)2\u0006\u0010\"\u001a\u00020\u000b2\u0012\u0010#\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u000b0\u0018\"\u00020\u000bH\u0007¢\u0006\u0002\u0010*J\u0018\u0010\u001d\u001a\u00020\u00162\u0006\u0010+\u001a\u00020,2\u0006\u0010\"\u001a\u00020\u000bH\u0007J1\u0010\u001d\u001a\u00020\u00162\u0006\u0010+\u001a\u00020-2\u0006\u0010\"\u001a\u00020\u000b2\u0012\u0010#\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u000b0\u0018\"\u00020\u000bH\u0007¢\u0006\u0002\u0010.J)\u0010\u001d\u001a\u00020\u00162\u001a\u0010\u0017\u001a\u000e\u0012\n\b\u0001\u0012\u0006\u0012\u0002\b\u00030\u00030\u0018\"\u0006\u0012\u0002\b\u00030\u0003H\u0007¢\u0006\u0002\u0010\u0019J\u001a\u0010\u001d\u001a\u00020\u00162\u0010\u0010/\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00030\u0007H\u0007J\u0012\u00100\u001a\u0002012\n\u0010\u0017\u001a\u0006\u0012\u0002\b\u00030\u0003J&\u00100\u001a\n\u0012\u0004\u0012\u0002H\u001e\u0018\u00010\u0003\"\b\b\u0000\u0010\u001e*\u00020\u001f2\f\u00102\u001a\b\u0012\u0004\u0012\u0002H\u001e0%J\u0013\u00103\u001a\u0002012\b\u00104\u001a\u0004\u0018\u00010\u001fH\u0096\u0002J)\u00105\u001a\n\u0012\u0004\u0012\u0002H\u001e\u0018\u00010\u0003\"\b\b\u0000\u0010\u001e*\u00020\u001f2\f\u00102\u001a\b\u0012\u0004\u0012\u0002H\u001e0%H\u0096\u0002J\u0015\u00105\u001a\u0006\u0012\u0002\b\u00030\u00032\u0006\u0010\u001a\u001a\u00020\u000eH\u0096\u0002J\u0017\u00105\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u00032\u0006\u00106\u001a\u00020\u000bH\u0096\u0002J\b\u00107\u001a\u00020\u000eH\u0016J\"\u00108\u001a\u00020\u00002\u0017\u00109\u001a\u0013\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020\u00160:¢\u0006\u0002\b;H\u0086\u0002J\u0015\u0010<\u001a\u00020\u00162\n\u0010\u0017\u001a\u0006\u0012\u0002\b\u00030\u0003H\u0086\u0002J!\u0010<\u001a\u00020\u0016\"\b\b\u0000\u0010\u001e*\u00020\u001f2\f\u00102\u001a\b\u0012\u0004\u0012\u0002H\u001e0%H\u0086\u0002J\u0015\u0010=\u001a\u00020\u00162\n\u0010\u0017\u001a\u0006\u0012\u0002\b\u00030\u0003H\u0086\u0002J\u001b\u0010=\u001a\u00020\u00162\u0010\u0010\u0006\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00030\u001cH\u0086\u0002J\u001e\u0010>\u001a\u00020\u0016\"\b\b\u0000\u0010\u001e*\u00020\u001f2\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u0002H\u001e0\u0003J?\u0010>\u001a\u00020\u0016\"\b\b\u0000\u0010\u001e*\u00020\u001f2\f\u00102\u001a\b\u0012\u0004\u0012\u0002H\u001e0!2\u0006\u0010\"\u001a\u0002H\u001e2\u0012\u0010#\u001a\n\u0012\u0006\b\u0001\u0012\u0002H\u001e0\u0018\"\u0002H\u001e¢\u0006\u0002\u0010$J,\u0010>\u001a\u00020\u0016\"\b\b\u0000\u0010\u001e*\u00020\u001f2\f\u00102\u001a\b\u0012\u0004\u0012\u0002H\u001e0!2\f\u0010#\u001a\b\u0012\u0004\u0012\u0002H\u001e0\u001cJ+\u0010>\u001a\u00020\u0016\"\b\b\u0000\u0010\u001e*\u00020\u001f2\f\u00102\u001a\b\u0012\u0004\u0012\u0002H\u001e0%2\u0006\u0010\"\u001a\u0002H\u001e¢\u0006\u0002\u0010&J\u0016\u0010>\u001a\u00020\u00162\u0006\u00102\u001a\u00020?2\u0006\u0010\"\u001a\u00020\u000bJ/\u0010>\u001a\u00020\u00162\u0006\u00102\u001a\u00020@2\u0006\u0010\"\u001a\u00020\u000b2\u0012\u0010#\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u000b0\u0018\"\u00020\u000b¢\u0006\u0002\u0010AJ\u0016\u0010>\u001a\u00020\u00162\u0006\u00102\u001a\u00020(2\u0006\u0010\"\u001a\u00020\u000bJ/\u0010>\u001a\u00020\u00162\u0006\u00102\u001a\u00020)2\u0006\u0010\"\u001a\u00020\u000b2\u0012\u0010#\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u000b0\u0018\"\u00020\u000b¢\u0006\u0002\u0010*J\u0016\u0010>\u001a\u00020\u00162\u0006\u0010+\u001a\u00020,2\u0006\u0010\"\u001a\u00020\u000bJ/\u0010>\u001a\u00020\u00162\u0006\u0010+\u001a\u00020-2\u0006\u0010\"\u001a\u00020\u000b2\u0012\u0010#\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u000b0\u0018\"\u00020\u000b¢\u0006\u0002\u0010.J'\u0010>\u001a\u00020\u00162\u001a\u0010\u0006\u001a\u000e\u0012\n\b\u0001\u0012\u0006\u0012\u0002\b\u00030\u00030\u0018\"\u0006\u0012\u0002\b\u00030\u0003¢\u0006\u0002\u0010\u0019J\u0018\u0010B\u001a\u00020\u00162\u0010\u0010\u0006\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00030\u001cJ/\u0010C\u001a\u00020\u0016\"\b\b\u0000\u0010\u001e*\u00020\u001f2\f\u00102\u001a\b\u0012\u0004\u0012\u0002H\u001e0!2\f\u0010#\u001a\b\u0012\u0004\u0012\u0002H\u001e0\u001cH\u0086\u0002J.\u0010C\u001a\u00020\u0016\"\b\b\u0000\u0010\u001e*\u00020\u001f2\f\u00102\u001a\b\u0012\u0004\u0012\u0002H\u001e0%2\u0006\u0010\"\u001a\u0002H\u001eH\u0086\u0002¢\u0006\u0002\u0010&J\u0006\u0010D\u001a\u00020\u0001J\b\u0010E\u001a\u00020\u000bH\u0016R2\u0010\t\u001a&\u0012\u0004\u0012\u00020\u000b\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00030\nj\u0012\u0012\u0004\u0012\u00020\u000b\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0003`\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\r\u001a\u00020\u000e8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0010R\u001a\u0010\u0004\u001a\u00020\u0005X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014¨\u0006F"}, m1293d2 = {"Lcom/hp/jipp/encoding/MutableAttributeGroup;", "Lcom/hp/jipp/encoding/AttributeGroup;", "Lkotlin/collections/AbstractList;", "Lcom/hp/jipp/encoding/Attribute;", "tag", "Lcom/hp/jipp/encoding/DelimiterTag;", "attributes", "", "(Lcom/hp/jipp/encoding/DelimiterTag;Ljava/util/List;)V", "map", "Ljava/util/LinkedHashMap;", "", "Lkotlin/collections/LinkedHashMap;", MediaInformation.KEY_SIZE, "", "getSize", "()I", "getTag", "()Lcom/hp/jipp/encoding/DelimiterTag;", "setTag", "(Lcom/hp/jipp/encoding/DelimiterTag;)V", "add", "", "attribute", "", "([Lcom/hp/jipp/encoding/Attribute;)V", StreamInformation.KEY_INDEX, "addAll", "", "attr", "T", "", "attributeType", "Lcom/hp/jipp/encoding/AttributeSetType;", "value", "values", "(Lcom/hp/jipp/encoding/AttributeSetType;Ljava/lang/Object;[Ljava/lang/Object;)V", "Lcom/hp/jipp/encoding/AttributeType;", "(Lcom/hp/jipp/encoding/AttributeType;Ljava/lang/Object;)V", "nameType", "Lcom/hp/jipp/encoding/NameType;", "Lcom/hp/jipp/encoding/NameType$Set;", "(Lcom/hp/jipp/encoding/NameType$Set;Ljava/lang/String;[Ljava/lang/String;)V", "textType", "Lcom/hp/jipp/encoding/TextType;", "Lcom/hp/jipp/encoding/TextType$Set;", "(Lcom/hp/jipp/encoding/TextType$Set;Ljava/lang/String;[Ljava/lang/String;)V", "toAdd", "drop", "", "type", "equals", "other", "get", NamingTable.TAG, "hashCode", "invoke", "mutator", "Lkotlin/Function1;", "Lkotlin/ExtensionFunctionType;", "minusAssign", "plusAssign", "put", "Lcom/hp/jipp/encoding/KeywordOrNameType;", "Lcom/hp/jipp/encoding/KeywordOrNameType$Set;", "(Lcom/hp/jipp/encoding/KeywordOrNameType$Set;Ljava/lang/String;[Ljava/lang/String;)V", "putAll", "set", "toGroup", "toString", "jipp-core"}, m1294k = 1, m1295mv = {1, 4, 0})
public class MutableAttributeGroup extends AbstractList<Attribute<?>> implements AttributeGroup {
    private final LinkedHashMap<String, Attribute<?>> map;
    private DelimiterTag tag;

    public MutableAttributeGroup(DelimiterTag delimiterTag) {
        this(delimiterTag, null, 2, 0 == true ? 1 : 0);
    }

    public boolean contains(Attribute attribute) {
        return super.contains(attribute);
    }

    @Override
    public final boolean contains(Object obj) {
        if (obj instanceof Attribute) {
            return contains((Attribute) obj);
        }
        return false;
    }

    @Override
    public <T> String getString(AttributeType<T> type) {
        Intrinsics.checkNotNullParameter(type, "type");
        return AttributeGroup.DefaultImpls.getString(this, type);
    }

    @Override
    public <T> List<String> getStrings(AttributeType<T> type) {
        Intrinsics.checkNotNullParameter(type, "type");
        return AttributeGroup.DefaultImpls.getStrings(this, type);
    }

    @Override
    public <T> T getValue(AttributeType<T> type) {
        Intrinsics.checkNotNullParameter(type, "type");
        return (T) AttributeGroup.DefaultImpls.getValue(this, type);
    }

    @Override
    public <T> List<T> getValues(AttributeType<T> type) {
        Intrinsics.checkNotNullParameter(type, "type");
        return AttributeGroup.DefaultImpls.getValues(this, type);
    }

    public int indexOf(Attribute attribute) {
        return super.indexOf(attribute);
    }

    @Override
    public final int indexOf(Object obj) {
        if (obj instanceof Attribute) {
            return indexOf((Attribute) obj);
        }
        return -1;
    }

    public int lastIndexOf(Attribute attribute) {
        return super.lastIndexOf(attribute);
    }

    @Override
    public final int lastIndexOf(Object obj) {
        if (obj instanceof Attribute) {
            return lastIndexOf((Attribute) obj);
        }
        return -1;
    }

    @Override
    public AttributeGroup plus(List<? extends Attribute<?>> attributes) {
        Intrinsics.checkNotNullParameter(attributes, "attributes");
        return AttributeGroup.DefaultImpls.plus(this, attributes);
    }

    @Override
    public String prettyPrint(int i, String indent) {
        Intrinsics.checkNotNullParameter(indent, "indent");
        return AttributeGroup.DefaultImpls.prettyPrint(this, i, indent);
    }

    @Override
    public void print(PrettyPrinter printer) {
        Intrinsics.checkNotNullParameter(printer, "printer");
        AttributeGroup.DefaultImpls.print(this, printer);
    }

    @Override
    public MutableAttributeGroup toMutable() {
        return AttributeGroup.DefaultImpls.toMutable(this);
    }

    @Override
    public DelimiterTag getTag() {
        return this.tag;
    }

    public void setTag(DelimiterTag delimiterTag) {
        Intrinsics.checkNotNullParameter(delimiterTag, "<set-?>");
        this.tag = delimiterTag;
    }

    public MutableAttributeGroup(DelimiterTag delimiterTag, List list, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(delimiterTag, (i & 2) != 0 ? CollectionsKt.emptyList() : list);
    }

    public MutableAttributeGroup(DelimiterTag tag, List<? extends Attribute<?>> attributes) {
        Intrinsics.checkNotNullParameter(tag, "tag");
        Intrinsics.checkNotNullParameter(attributes, "attributes");
        this.tag = tag;
        this.map = new LinkedHashMap<>();
        putAll(attributes);
    }

    @Override
    public int getSize() {
        return this.map.size();
    }

    public final MutableAttributeGroup invoke(Function1<? super MutableAttributeGroup, Unit> mutator) {
        Intrinsics.checkNotNullParameter(mutator, "mutator");
        mutator.invoke(this);
        return this;
    }

    public final void plusAssign(Attribute<?> attribute) {
        Intrinsics.checkNotNullParameter(attribute, "attribute");
        put(attribute);
    }

    public final void plusAssign(Iterable<? extends Attribute<?>> attributes) {
        Intrinsics.checkNotNullParameter(attributes, "attributes");
        putAll(attributes);
    }

    @Override
    public Attribute<?> get(int index) {
        Collection<Attribute<?>> collectionValues = this.map.values();
        Intrinsics.checkNotNullExpressionValue(collectionValues, "map.values");
        return (Attribute) CollectionsKt.elementAt(collectionValues, index);
    }

    @Override
    public Attribute<?> get(String name) {
        Intrinsics.checkNotNullParameter(name, "name");
        return this.map.get(name);
    }

    @Override
    public <T> Attribute<T> get(AttributeType<T> type) {
        Intrinsics.checkNotNullParameter(type, "type");
        Attribute<?> it = this.map.get(type.getName());
        if (it == null) {
            return null;
        }
        Intrinsics.checkNotNullExpressionValue(it, "it");
        return type.coerce(it);
    }

    public final <T> void set(AttributeType<T> type, T value) {
        Intrinsics.checkNotNullParameter(type, "type");
        Intrinsics.checkNotNullParameter(value, "value");
        this.map.put(type.getName(), type.mo418of(value));
    }

    public final <T> void set(AttributeSetType<T> type, Iterable<? extends T> values) {
        Intrinsics.checkNotNullParameter(type, "type");
        Intrinsics.checkNotNullParameter(values, "values");
        this.map.put(type.getName(), type.mo417of((Iterable) values));
    }

    public final <T> void put(AttributeType<T> type, T value) {
        Intrinsics.checkNotNullParameter(type, "type");
        Intrinsics.checkNotNullParameter(value, "value");
        this.map.put(type.getName(), type.mo418of(value));
    }

    public final <T> void put(AttributeSetType<T> type, Iterable<? extends T> values) {
        Intrinsics.checkNotNullParameter(type, "type");
        Intrinsics.checkNotNullParameter(values, "values");
        this.map.put(type.getName(), type.mo417of((Iterable) values));
    }

    public final <T> void put(AttributeSetType<T> type, T value, T... values) {
        Intrinsics.checkNotNullParameter(type, "type");
        Intrinsics.checkNotNullParameter(value, "value");
        Intrinsics.checkNotNullParameter(values, "values");
        this.map.put(type.getName(), type.mo417of((Iterable) CollectionsKt.plus((Collection) CollectionsKt.listOf(value), (Object[]) values)));
    }

    public final <T> void put(Attribute<T> attribute) {
        Intrinsics.checkNotNullParameter(attribute, "attribute");
        this.map.put(attribute.getName(), attribute);
    }

    public final void put(Attribute<?>... attributes) {
        Intrinsics.checkNotNullParameter(attributes, "attributes");
        for (Attribute<?> attribute : attributes) {
            this.map.put(attribute.getName(), attribute);
        }
    }

    public final void put(NameType type, String value) {
        Intrinsics.checkNotNullParameter(type, "type");
        Intrinsics.checkNotNullParameter(value, "value");
        put(type.m440of(value));
    }

    public final void put(NameType.Set type, String value, String... values) {
        Intrinsics.checkNotNullParameter(type, "type");
        Intrinsics.checkNotNullParameter(value, "value");
        Intrinsics.checkNotNullParameter(values, "values");
        List listPlus = CollectionsKt.plus((Collection) CollectionsKt.listOf(value), (Iterable) ArraysKt.toList(values));
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(listPlus, 10));
        Iterator it = listPlus.iterator();
        while (it.hasNext()) {
            arrayList.add(new Name((String) it.next()));
        }
        put(type.mo417of((Iterable<? extends Name>) arrayList));
    }

    public final void put(KeywordOrNameType type, String value) {
        Intrinsics.checkNotNullParameter(type, "type");
        Intrinsics.checkNotNullParameter(value, "value");
        put(type.m437of(value));
    }

    public final void put(KeywordOrNameType.Set type, String value, String... values) {
        Intrinsics.checkNotNullParameter(type, "type");
        Intrinsics.checkNotNullParameter(value, "value");
        Intrinsics.checkNotNullParameter(values, "values");
        List listPlus = CollectionsKt.plus((Collection) CollectionsKt.listOf(value), (Iterable) ArraysKt.toList(values));
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(listPlus, 10));
        Iterator it = listPlus.iterator();
        while (it.hasNext()) {
            arrayList.add(new KeywordOrName((String) it.next()));
        }
        put(type.mo417of((Iterable<? extends KeywordOrName>) arrayList));
    }

    public final void put(TextType textType, String value) {
        Intrinsics.checkNotNullParameter(textType, "textType");
        Intrinsics.checkNotNullParameter(value, "value");
        put(textType.m442of(value));
    }

    public final void put(TextType.Set textType, String value, String... values) {
        Intrinsics.checkNotNullParameter(textType, "textType");
        Intrinsics.checkNotNullParameter(value, "value");
        Intrinsics.checkNotNullParameter(values, "values");
        List listPlus = CollectionsKt.plus((Collection) CollectionsKt.listOf(value), (Iterable) ArraysKt.toList(values));
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(listPlus, 10));
        Iterator it = listPlus.iterator();
        while (it.hasNext()) {
            arrayList.add(new Text((String) it.next()));
        }
        put(textType.mo417of((Iterable<? extends Text>) arrayList));
    }

    @Deprecated(message = "use put()", replaceWith = @ReplaceWith(expression = "put(attribute)", imports = {}))
    public final void addAll(int index, List<? extends Attribute<?>> attributes) {
        Intrinsics.checkNotNullParameter(attributes, "attributes");
        putAll(attributes);
    }

    @Override
    @Deprecated(message = "use put()", replaceWith = @ReplaceWith(expression = "put(attribute)", imports = {}))
    public final void add(int index, Attribute<?> attribute) {
        Intrinsics.checkNotNullParameter(attribute, "attribute");
        put(attribute);
    }

    @Deprecated(message = "use put()", replaceWith = @ReplaceWith(expression = "put(attributeType.of(value, values...))", imports = {}))
    public final void add(Attribute<?> attribute) {
        Intrinsics.checkNotNullParameter(attribute, "attribute");
        put(attribute);
    }

    @Deprecated(message = "use put()", replaceWith = @ReplaceWith(expression = "put(attributeType.of(value, values...))", imports = {}))
    public final void add(Attribute<?>... attributes) {
        Intrinsics.checkNotNullParameter(attributes, "attributes");
        putAll(ArraysKt.toList(attributes));
    }

    @Deprecated(message = "use putAll()", replaceWith = @ReplaceWith(expression = "putAll(attributes)", imports = {}))
    public final void addAll(Iterable<? extends Attribute<?>> attributes) {
        Intrinsics.checkNotNullParameter(attributes, "attributes");
        putAll(attributes);
    }

    @Deprecated(message = "use putAll()", replaceWith = @ReplaceWith(expression = "putAll(toAdd)", imports = {}))
    public final void attr(List<? extends Attribute<?>> toAdd) {
        Intrinsics.checkNotNullParameter(toAdd, "toAdd");
        putAll(toAdd);
    }

    @Deprecated(message = "use put()", replaceWith = @ReplaceWith(expression = "put(attribute)", imports = {}))
    public final void attr(Attribute<?>... attribute) {
        Intrinsics.checkNotNullParameter(attribute, "attribute");
        putAll(ArraysKt.toList(attribute));
    }

    @Deprecated(message = "use put()", replaceWith = @ReplaceWith(expression = "put(attributeType, value)", imports = {}))
    public final <T> void attr(AttributeType<T> attributeType, T value) {
        Intrinsics.checkNotNullParameter(attributeType, "attributeType");
        Intrinsics.checkNotNullParameter(value, "value");
        put(attributeType.mo418of(value));
    }

    @Deprecated(message = "use put()", replaceWith = @ReplaceWith(expression = "put(attributeType, value, values)", imports = {}))
    public final <T> void attr(AttributeSetType<T> attributeType, T value, T... values) {
        Intrinsics.checkNotNullParameter(attributeType, "attributeType");
        Intrinsics.checkNotNullParameter(value, "value");
        Intrinsics.checkNotNullParameter(values, "values");
        put(attributeType.mo417of((Iterable) CollectionsKt.plus((Collection) CollectionsKt.listOf(value), (Iterable) ArraysKt.toList(values))));
    }

    @Deprecated(message = "use put()", replaceWith = @ReplaceWith(expression = "put(attributeType, value)", imports = {}))
    public final void attr(NameType nameType, String value) {
        Intrinsics.checkNotNullParameter(nameType, "nameType");
        Intrinsics.checkNotNullParameter(value, "value");
        put(nameType.m440of(value));
    }

    @Deprecated(message = "use put()", replaceWith = @ReplaceWith(expression = "put(attributeType, value, values)", imports = {}))
    public final void attr(NameType.Set nameType, String value, String... values) {
        Intrinsics.checkNotNullParameter(nameType, "nameType");
        Intrinsics.checkNotNullParameter(value, "value");
        Intrinsics.checkNotNullParameter(values, "values");
        List listPlus = CollectionsKt.plus((Collection) CollectionsKt.listOf(value), (Iterable) ArraysKt.toList(values));
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(listPlus, 10));
        Iterator it = listPlus.iterator();
        while (it.hasNext()) {
            arrayList.add(new Name((String) it.next()));
        }
        put(nameType.mo417of((Iterable<? extends Name>) arrayList));
    }

    @Deprecated(message = "use put()", replaceWith = @ReplaceWith(expression = "put(attributeType, value)", imports = {}))
    public final void attr(TextType textType, String value) {
        Intrinsics.checkNotNullParameter(textType, "textType");
        Intrinsics.checkNotNullParameter(value, "value");
        put(textType.m442of(value));
    }

    @Deprecated(message = "use put()", replaceWith = @ReplaceWith(expression = "put(attributeType, value, values)", imports = {}))
    public final void attr(TextType.Set textType, String value, String... values) {
        Intrinsics.checkNotNullParameter(textType, "textType");
        Intrinsics.checkNotNullParameter(value, "value");
        Intrinsics.checkNotNullParameter(values, "values");
        List listPlus = CollectionsKt.plus((Collection) CollectionsKt.listOf(value), (Iterable) ArraysKt.toList(values));
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(listPlus, 10));
        Iterator it = listPlus.iterator();
        while (it.hasNext()) {
            arrayList.add(new Text((String) it.next()));
        }
        put(textType.mo417of((Iterable<? extends Text>) arrayList));
    }

    public final <T> Attribute<T> drop(AttributeType<T> type) {
        Intrinsics.checkNotNullParameter(type, "type");
        return (Attribute) this.map.remove(type.getName());
    }

    public final boolean drop(Attribute<?> attribute) {
        Intrinsics.checkNotNullParameter(attribute, "attribute");
        return this.map.remove(attribute.getName()) != null;
    }

    public final <T> void minusAssign(AttributeType<T> type) {
        Intrinsics.checkNotNullParameter(type, "type");
        this.map.remove(type.getName());
    }

    public final void minusAssign(Attribute<?> attribute) {
        Intrinsics.checkNotNullParameter(attribute, "attribute");
        this.map.remove(attribute.getName());
    }

    public final AttributeGroup toGroup() {
        return AttributeGroup.INSTANCE.groupOf(getTag(), CollectionsKt.toList(this));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (other instanceof AttributeGroup) {
            if (Intrinsics.areEqual(((AttributeGroup) other).getTag(), getTag())) {
                Collection<Attribute<?>> collectionValues = this.map.values();
                Intrinsics.checkNotNullExpressionValue(collectionValues, "map.values");
                if (Intrinsics.areEqual(StringableKt.stringinate(collectionValues), StringableKt.stringinate((Collection) other))) {
                    return true;
                }
            }
        } else if (other instanceof List) {
            Collection<Attribute<?>> collectionValues2 = this.map.values();
            Intrinsics.checkNotNullExpressionValue(collectionValues2, "map.values");
            return Intrinsics.areEqual(StringableKt.stringinate(collectionValues2), StringableKt.stringinate((Collection) other));
        }
        return false;
    }

    @Override
    public int hashCode() {
        Collection<Attribute<?>> collectionValues = this.map.values();
        Intrinsics.checkNotNullExpressionValue(collectionValues, "map.values");
        return StringableKt.stringinate(collectionValues).hashCode();
    }

    @Override
    public String toString() {
        return "MutableAttributeGroup(" + getTag() + ", " + this.map.values() + ')';
    }

    public final void putAll(Iterable<? extends Attribute<?>> attributes) {
        Intrinsics.checkNotNullParameter(attributes, "attributes");
        for (Attribute<?> attribute : attributes) {
            this.map.put(attribute.getName(), attribute);
        }
    }
}
