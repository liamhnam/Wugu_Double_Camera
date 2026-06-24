package com.p020hp.jipp.encoding;

import com.arthenica.ffmpegkit.MediaInformation;
import com.arthenica.ffmpegkit.StreamInformation;
import com.p020hp.jipp.encoding.Attribute;
import com.p020hp.jipp.util.BuildError;
import com.p020hp.jipp.util.PrettyPrinter;
import com.tom_roush.fontbox.ttf.NamingTable;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.function.UnaryOperator;
import kotlin.Metadata;
import kotlin.jvm.internal.CollectionToArray;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.markers.KMappedMarker;

@Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u001e\n\u0002\b\f\n\u0002\u0010(\n\u0002\b\u0002\n\u0002\u0010*\n\u0002\b\u0005\b\u0016\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\b\u0012\u0004\u0012\u0002H\u00010\u00032\b\u0012\u0004\u0012\u0002H\u00010\u0004B)\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00028\u00000\b\u0012\f\u0010\t\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004¢\u0006\u0002\u0010\nJ\u0016\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00028\u0000H\u0096\u0003¢\u0006\u0002\u0010\u0016J\u0017\u0010\u0017\u001a\u00020\u00142\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00028\u00000\u0019H\u0096\u0001J\u0013\u0010\u001a\u001a\u00020\u00142\b\u0010\u001b\u001a\u0004\u0018\u00010\u0002H\u0096\u0002J\u0016\u0010\u001c\u001a\u00028\u00002\u0006\u0010\u001d\u001a\u00020\u000eH\u0096\u0003¢\u0006\u0002\u0010\u001eJ\u000f\u0010\u001f\u001a\u0004\u0018\u00018\u0000H\u0016¢\u0006\u0002\u0010 J\b\u0010!\u001a\u00020\u000eH\u0016J\u0016\u0010\"\u001a\u00020\u000e2\u0006\u0010\u0015\u001a\u00028\u0000H\u0096\u0001¢\u0006\u0002\u0010#J\t\u0010$\u001a\u00020\u0014H\u0096\u0001J\u000f\u0010%\u001a\b\u0012\u0004\u0012\u00028\u00000&H\u0096\u0003J\u0016\u0010'\u001a\u00020\u000e2\u0006\u0010\u0015\u001a\u00028\u0000H\u0096\u0001¢\u0006\u0002\u0010#J\u000f\u0010(\u001a\b\u0012\u0004\u0012\u00028\u00000)H\u0096\u0001J\u0017\u0010(\u001a\b\u0012\u0004\u0012\u00028\u00000)2\u0006\u0010\u001d\u001a\u00020\u000eH\u0096\u0001J\u001f\u0010*\u001a\b\u0012\u0004\u0012\u00028\u00000\u00042\u0006\u0010+\u001a\u00020\u000e2\u0006\u0010,\u001a\u00020\u000eH\u0096\u0001J\b\u0010-\u001a\u00020\u0006H\u0016R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0012\u0010\r\u001a\u00020\u000eX\u0096\u0005¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0010R\u0017\u0010\u0007\u001a\b\u0012\u0004\u0012\u00028\u00000\b¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006."}, m1293d2 = {"Lcom/hp/jipp/encoding/AttributeImpl;", "T", "", "Lcom/hp/jipp/encoding/Attribute;", "", NamingTable.TAG, "", "type", "Lcom/hp/jipp/encoding/AttributeType;", "values", "(Ljava/lang/String;Lcom/hp/jipp/encoding/AttributeType;Ljava/util/List;)V", "getName", "()Ljava/lang/String;", MediaInformation.KEY_SIZE, "", "getSize", "()I", "getType", "()Lcom/hp/jipp/encoding/AttributeType;", "contains", "", "element", "(Ljava/lang/Object;)Z", "containsAll", "elements", "", "equals", "other", "get", StreamInformation.KEY_INDEX, "(I)Ljava/lang/Object;", "getValue", "()Ljava/lang/Object;", "hashCode", "indexOf", "(Ljava/lang/Object;)I", "isEmpty", "iterator", "", "lastIndexOf", "listIterator", "", "subList", "fromIndex", "toIndex", "toString", "jipp-core"}, m1294k = 1, m1295mv = {1, 4, 0})
public class AttributeImpl<T> implements Attribute<T>, List<T>, KMappedMarker {
    private final String name;
    private final AttributeType<T> type;
    private final List<T> values;

    @Override
    public void add(int i, T t) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override
    public boolean add(T t) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override
    public boolean addAll(int i, Collection<? extends T> collection) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override
    public boolean addAll(Collection<? extends T> collection) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override
    public boolean contains(Object element) {
        if (element == null) {
            return false;
        }
        Intrinsics.checkNotNullParameter(element, "element");
        return this.values.contains(element);
    }

    @Override
    public boolean containsAll(Collection<? extends Object> elements) {
        Intrinsics.checkNotNullParameter(elements, "elements");
        return this.values.containsAll(elements);
    }

    @Override
    public T get(int index) {
        T t = this.values.get(index);
        Intrinsics.checkNotNullExpressionValue(t, "get(...)");
        return t;
    }

    public int getSize() {
        return this.values.size();
    }

    @Override
    public int indexOf(Object element) {
        if (element == null) {
            return -1;
        }
        Intrinsics.checkNotNullParameter(element, "element");
        return this.values.indexOf(element);
    }

    @Override
    public boolean isEmpty() {
        return this.values.isEmpty();
    }

    @Override
    public Iterator<T> iterator() {
        return this.values.iterator();
    }

    @Override
    public int lastIndexOf(Object element) {
        if (element == null) {
            return -1;
        }
        Intrinsics.checkNotNullParameter(element, "element");
        return this.values.lastIndexOf(element);
    }

    @Override
    public ListIterator<T> listIterator() {
        return this.values.listIterator();
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        return this.values.listIterator(index);
    }

    @Override
    public T remove(int i) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override
    public boolean remove(Object obj) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override
    public boolean removeAll(Collection<? extends Object> collection) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override
    public void replaceAll(UnaryOperator<T> unaryOperator) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override
    public boolean retainAll(Collection<? extends Object> collection) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override
    public T set(int i, T t) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override
    public void sort(Comparator<? super T> comparator) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        return this.values.subList(fromIndex, toIndex);
    }

    @Override
    public Object[] toArray() {
        return CollectionToArray.toArray(this);
    }

    @Override
    public <T> T[] toArray(T[] tArr) {
        return (T[]) CollectionToArray.toArray(this, tArr);
    }

    public AttributeImpl(String name, AttributeType<T> type, List<? extends T> values) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(type, "type");
        Intrinsics.checkNotNullParameter(values, "values");
        this.name = name;
        this.type = type;
        this.values = values;
        if (values.isEmpty() && !(type instanceof EmptyAttributeType)) {
            throw new BuildError("Attribute must have values or an out-of-band tag");
        }
    }

    @Override
    public boolean isNoValue() {
        return Attribute.DefaultImpls.isNoValue(this);
    }

    @Override
    public boolean isUnknown() {
        return Attribute.DefaultImpls.isUnknown(this);
    }

    @Override
    public boolean isUnsupported() {
        return Attribute.DefaultImpls.isUnsupported(this);
    }

    @Override
    public void print(PrettyPrinter printer) {
        Intrinsics.checkNotNullParameter(printer, "printer");
        Attribute.DefaultImpls.print(this, printer);
    }

    @Override
    public final int size() {
        return getSize();
    }

    @Override
    public List<String> strings() {
        return Attribute.DefaultImpls.strings(this);
    }

    @Override
    public final String getName() {
        return this.name;
    }

    @Override
    public final AttributeType<T> getType() {
        return this.type;
    }

    @Override
    public T getValue() {
        if (this.values.isEmpty()) {
            return null;
        }
        return this.values.get(0);
    }

    public String toString() {
        int size = this.values.size();
        if (size != 0) {
            if (size == 1) {
                return this.name + '=' + this.values.get(0);
            }
            return this.name + '=' + this.values;
        }
        StringBuilder sbAppend = new StringBuilder().append(this.name).append('(');
        AttributeType<T> attributeType = this.type;
        if (attributeType != null) {
            return sbAppend.append(((EmptyAttributeType) attributeType).getTag().getName()).append(')').toString();
        }
        throw new NullPointerException("null cannot be cast to non-null type com.hp.jipp.encoding.EmptyAttributeType<T>");
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (other instanceof Attribute) {
            Attribute attribute = (Attribute) other;
            AttributeType<T> type = attribute.getType();
            boolean z = type instanceof EmptyAttributeType;
            if (z || (this.type instanceof EmptyAttributeType)) {
                if (z && (this.type instanceof EmptyAttributeType) && Intrinsics.areEqual(((EmptyAttributeType) type).getTag(), ((EmptyAttributeType) this.type).getTag())) {
                    return true;
                }
            } else if (Intrinsics.areEqual(attribute.getName(), this.name) && Intrinsics.areEqual(attribute.getType().getName(), this.type.getName()) && Intrinsics.areEqual(StringableKt.stringinate(this.values), StringableKt.stringinate((Collection) other))) {
                return true;
            }
        } else if (other instanceof List) {
            return Intrinsics.areEqual(this.values, other);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return StringableKt.stringinate(this.values).hashCode();
    }
}
