package com.p020hp.jipp.encoding;

import com.arthenica.ffmpegkit.MediaInformation;
import com.arthenica.ffmpegkit.StreamInformation;
import com.p020hp.jipp.encoding.AttributeGroup;
import com.p020hp.jipp.util.BuildError;
import com.p020hp.jipp.util.PrettyPrinter;
import com.tom_roush.fontbox.ttf.NamingTable;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.function.UnaryOperator;
import kotlin.Metadata;
import kotlin.jvm.internal.CollectionToArray;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.markers.KMappedMarker;

@Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u001e\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010(\n\u0002\b\u0002\n\u0002\u0010*\n\u0002\b\u0005\b\u0000\u0018\u00002\u00020\u00012\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00030\u0002B\u001f\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0010\u0010\u0006\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00030\u0002¢\u0006\u0002\u0010\u0007J\u0015\u0010\u000e\u001a\u00020\u000f2\n\u0010\u0010\u001a\u0006\u0012\u0002\b\u00030\u0003H\u0096\u0003J\u001b\u0010\u0011\u001a\u00020\u000f2\u0010\u0010\u0012\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00030\u0013H\u0096\u0001J\u0013\u0010\u0014\u001a\u00020\u000f2\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016H\u0096\u0002J)\u0010\u0017\u001a\n\u0012\u0004\u0012\u0002H\u0018\u0018\u00010\u0003\"\b\b\u0000\u0010\u0018*\u00020\u00162\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u0002H\u00180\u001aH\u0096\u0002J\u0015\u0010\u0017\u001a\u0006\u0012\u0002\b\u00030\u00032\u0006\u0010\u001b\u001a\u00020\tH\u0096\u0003J\u0017\u0010\u0017\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u00032\u0006\u0010\u001c\u001a\u00020\u001dH\u0096\u0002J\b\u0010\u001e\u001a\u00020\tH\u0016J\u0015\u0010\u001f\u001a\u00020\t2\n\u0010\u0010\u001a\u0006\u0012\u0002\b\u00030\u0003H\u0096\u0001J\t\u0010 \u001a\u00020\u000fH\u0096\u0001J\u0013\u0010!\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00030\"H\u0096\u0003J\u0015\u0010#\u001a\u00020\t2\n\u0010\u0010\u001a\u0006\u0012\u0002\b\u00030\u0003H\u0096\u0001J\u0013\u0010$\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00030%H\u0096\u0001J\u001b\u0010$\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00030%2\u0006\u0010\u001b\u001a\u00020\tH\u0096\u0001J#\u0010&\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00030\u00022\u0006\u0010'\u001a\u00020\t2\u0006\u0010(\u001a\u00020\tH\u0096\u0001J\b\u0010)\u001a\u00020\u001dH\u0016R\u0018\u0010\u0006\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00030\u0002X\u0082\u0004¢\u0006\u0002\n\u0000R\u0012\u0010\b\u001a\u00020\tX\u0096\u0005¢\u0006\u0006\u001a\u0004\b\n\u0010\u000bR\u0014\u0010\u0004\u001a\u00020\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r¨\u0006*"}, m1293d2 = {"Lcom/hp/jipp/encoding/AttributeGroupImpl;", "Lcom/hp/jipp/encoding/AttributeGroup;", "", "Lcom/hp/jipp/encoding/Attribute;", "tag", "Lcom/hp/jipp/encoding/DelimiterTag;", "attributes", "(Lcom/hp/jipp/encoding/DelimiterTag;Ljava/util/List;)V", MediaInformation.KEY_SIZE, "", "getSize", "()I", "getTag", "()Lcom/hp/jipp/encoding/DelimiterTag;", "contains", "", "element", "containsAll", "elements", "", "equals", "other", "", "get", "T", "type", "Lcom/hp/jipp/encoding/AttributeType;", StreamInformation.KEY_INDEX, NamingTable.TAG, "", "hashCode", "indexOf", "isEmpty", "iterator", "", "lastIndexOf", "listIterator", "", "subList", "fromIndex", "toIndex", "toString", "jipp-core"}, m1294k = 1, m1295mv = {1, 4, 0})
public final class AttributeGroupImpl implements AttributeGroup, List<Attribute<?>>, KMappedMarker {
    private final List<Attribute<?>> attributes;
    private final DelimiterTag tag;

    public void add2(int i, Attribute<?> attribute) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override
    public void add(int i, Attribute<?> attribute) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    public boolean add(Attribute<?> attribute) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override
    public boolean add(Object obj) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override
    public boolean addAll(int i, Collection<? extends Attribute<?>> collection) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override
    public boolean addAll(Collection<? extends Attribute<?>> collection) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    public boolean contains(Attribute<?> element) {
        Intrinsics.checkNotNullParameter(element, "element");
        return this.attributes.contains(element);
    }

    @Override
    public boolean containsAll(Collection<? extends Object> elements) {
        Intrinsics.checkNotNullParameter(elements, "elements");
        return this.attributes.containsAll(elements);
    }

    @Override
    public Attribute<?> get(int index) {
        Attribute<?> attribute = this.attributes.get(index);
        Intrinsics.checkNotNullExpressionValue(attribute, "get(...)");
        return attribute;
    }

    public int getSize() {
        return this.attributes.size();
    }

    public int indexOf(Attribute<?> element) {
        Intrinsics.checkNotNullParameter(element, "element");
        return this.attributes.indexOf(element);
    }

    @Override
    public boolean isEmpty() {
        return this.attributes.isEmpty();
    }

    @Override
    public Iterator<Attribute<?>> iterator() {
        return this.attributes.iterator();
    }

    public int lastIndexOf(Attribute<?> element) {
        Intrinsics.checkNotNullParameter(element, "element");
        return this.attributes.lastIndexOf(element);
    }

    @Override
    public ListIterator<Attribute<?>> listIterator() {
        return this.attributes.listIterator();
    }

    @Override
    public ListIterator<Attribute<?>> listIterator(int index) {
        return this.attributes.listIterator(index);
    }

    @Override
    public Attribute<?> remove(int i) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override
    public Attribute<?> remove(int i) {
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
    public void replaceAll(UnaryOperator<Attribute<?>> unaryOperator) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override
    public boolean retainAll(Collection<? extends Object> collection) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    public Attribute<?> set2(int i, Attribute<?> attribute) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override
    public Attribute<?> set(int i, Attribute<?> attribute) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override
    public void sort(Comparator<? super Attribute<?>> comparator) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override
    public List<Attribute<?>> subList(int fromIndex, int toIndex) {
        return this.attributes.subList(fromIndex, toIndex);
    }

    @Override
    public Object[] toArray() {
        return CollectionToArray.toArray(this);
    }

    @Override
    public <T> T[] toArray(T[] tArr) {
        return (T[]) CollectionToArray.toArray(this, tArr);
    }

    public AttributeGroupImpl(DelimiterTag tag, List<? extends Attribute<?>> attributes) {
        Intrinsics.checkNotNullParameter(tag, "tag");
        Intrinsics.checkNotNullParameter(attributes, "attributes");
        this.tag = tag;
        this.attributes = attributes;
        HashSet hashSet = new HashSet();
        Iterator it = attributes.iterator();
        while (it.hasNext()) {
            String name = ((Attribute) it.next()).getName();
            if (hashSet.contains(name)) {
                throw new BuildError("Attribute Group contains more than one '" + name + "` in " + this.attributes);
            }
            hashSet.add(name);
        }
    }

    @Override
    public final boolean contains(Object obj) {
        if (obj instanceof Attribute) {
            return contains((Attribute<?>) obj);
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

    @Override
    public final int indexOf(Object obj) {
        if (obj instanceof Attribute) {
            return indexOf((Attribute<?>) obj);
        }
        return -1;
    }

    @Override
    public final int lastIndexOf(Object obj) {
        if (obj instanceof Attribute) {
            return lastIndexOf((Attribute<?>) obj);
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
    public final int size() {
        return getSize();
    }

    @Override
    public MutableAttributeGroup toMutable() {
        return AttributeGroup.DefaultImpls.toMutable(this);
    }

    @Override
    public DelimiterTag getTag() {
        return this.tag;
    }

    @Override
    public <T> Attribute<T> get(AttributeType<T> type) {
        Intrinsics.checkNotNullParameter(type, "type");
        Attribute<?> attribute = get(type.getName());
        if (attribute != null) {
            return type.coerce(attribute);
        }
        return null;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (other instanceof AttributeGroup) {
            if (Intrinsics.areEqual(((AttributeGroup) other).getTag(), getTag()) && Intrinsics.areEqual(this.attributes, other)) {
                return true;
            }
        } else if (other instanceof List) {
            return Intrinsics.areEqual(this.attributes, other);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return this.attributes.hashCode();
    }

    public String toString() {
        return "AttributeGroup(" + getTag() + ", " + this.attributes + ')';
    }

    @Override
    public Attribute<?> get(String name) {
        Attribute<?> next;
        Intrinsics.checkNotNullParameter(name, "name");
        Iterator<Attribute<?>> it = iterator();
        while (true) {
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
            if (Intrinsics.areEqual(next.getName(), name)) {
                break;
            }
        }
        return next;
    }
}
