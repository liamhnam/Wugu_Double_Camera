package com.p020hp.jipp.model;

import com.p020hp.jipp.encoding.Attribute;
import com.p020hp.jipp.encoding.AttributeCollection;
import com.p020hp.jipp.encoding.AttributeType;
import com.p020hp.jipp.encoding.IntRangeType;
import com.p020hp.jipp.util.PrettyPrinter;
import java.util.List;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntRange;

@Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\b\u0018\u0000 #2\u00020\u0001:\u0001#B\u0007\b\u0016¢\u0006\u0002\u0010\u0002B;\u0012\u0010\b\u0002\u0010\u0003\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0004\u0012\u0010\b\u0002\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0004\u0012\u0010\b\u0002\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0004¢\u0006\u0002\u0010\bJ\u0011\u0010\u0017\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0004HÆ\u0003J\u0011\u0010\u0018\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0004HÆ\u0003J\u0011\u0010\u0019\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0004HÆ\u0003J?\u0010\u001a\u001a\u00020\u00002\u0010\b\u0002\u0010\u0003\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u00042\u0010\b\u0002\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u00042\u0010\b\u0002\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0004HÆ\u0001J\u0013\u0010\u001b\u001a\u00020\u001c2\b\u0010\u001d\u001a\u0004\u0018\u00010\u001eHÖ\u0003J\t\u0010\u001f\u001a\u00020 HÖ\u0001J\b\u0010!\u001a\u00020\"H\u0016R\u001e\u0010\t\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\n0\u00048VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\fR$\u0010\u0003\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0004X\u0086\u000e¢\u0006\u0010\n\u0002\b\u0010\u001a\u0004\b\r\u0010\f\"\u0004\b\u000e\u0010\u000fR$\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0004X\u0086\u000e¢\u0006\u0010\n\u0002\b\u0013\u001a\u0004\b\u0011\u0010\f\"\u0004\b\u0012\u0010\u000fR$\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0004X\u0086\u000e¢\u0006\u0010\n\u0002\b\u0016\u001a\u0004\b\u0014\u0010\f\"\u0004\b\u0015\u0010\u000f¨\u0006$"}, m1293d2 = {"Lcom/hp/jipp/model/Overrides;", "Lcom/hp/jipp/encoding/AttributeCollection;", "()V", "documentCopies", "", "Lkotlin/ranges/IntRange;", "documentNumbers", "pages", "(Ljava/util/List;Ljava/util/List;Ljava/util/List;)V", "attributes", "Lcom/hp/jipp/encoding/Attribute;", "getAttributes", "()Ljava/util/List;", "getDocumentCopies", "setDocumentCopies", "(Ljava/util/List;)V", "documentCopies$1", "getDocumentNumbers", "setDocumentNumbers", "documentNumbers$1", "getPages", "setPages", "pages$1", "component1", "component2", "component3", PrinterServiceType.copy, "equals", "", "other", "", "hashCode", "", "toString", "", "Companion", "jipp-core"}, m1294k = 1, m1295mv = {1, 4, 0})
public final class Overrides implements AttributeCollection {

    public static final Companion INSTANCE;
    public static final Companion Types;
    private static final Class<Overrides> cls;
    public static final IntRangeType.Set documentCopies;
    public static final IntRangeType.Set documentNumbers;
    public static final IntRangeType.Set pages;

    private List<IntRange> documentCopies;

    private List<IntRange> documentNumbers;

    private List<IntRange> pages;

    public static Overrides copy$default(Overrides overrides, List list, List list2, List list3, int i, Object obj) {
        if ((i & 1) != 0) {
            list = overrides.documentCopies;
        }
        if ((i & 2) != 0) {
            list2 = overrides.documentNumbers;
        }
        if ((i & 4) != 0) {
            list3 = overrides.pages;
        }
        return overrides.copy(list, list2, list3);
    }

    public final List<IntRange> component1() {
        return this.documentCopies;
    }

    public final List<IntRange> component2() {
        return this.documentNumbers;
    }

    public final List<IntRange> component3() {
        return this.pages;
    }

    public final Overrides copy(List<IntRange> documentCopies2, List<IntRange> documentNumbers2, List<IntRange> pages2) {
        return new Overrides(documentCopies2, documentNumbers2, pages2);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Overrides)) {
            return false;
        }
        Overrides overrides = (Overrides) other;
        return Intrinsics.areEqual(this.documentCopies, overrides.documentCopies) && Intrinsics.areEqual(this.documentNumbers, overrides.documentNumbers) && Intrinsics.areEqual(this.pages, overrides.pages);
    }

    public int hashCode() {
        List<IntRange> list = this.documentCopies;
        int iHashCode = (list != null ? list.hashCode() : 0) * 31;
        List<IntRange> list2 = this.documentNumbers;
        int iHashCode2 = (iHashCode + (list2 != null ? list2.hashCode() : 0)) * 31;
        List<IntRange> list3 = this.pages;
        return iHashCode2 + (list3 != null ? list3.hashCode() : 0);
    }

    @Override
    public void print(PrettyPrinter printer) {
        Intrinsics.checkNotNullParameter(printer, "printer");
        AttributeCollection.DefaultImpls.print(this, printer);
    }

    public Overrides(List<IntRange> list, List<IntRange> list2, List<IntRange> list3) {
        this.documentCopies = list;
        this.documentNumbers = list2;
        this.pages = list3;
    }

    public Overrides(List list, List list2, List list3, int i, DefaultConstructorMarker defaultConstructorMarker) {
        if ((i & 1) != 0) {
            list = null;
        }
        if ((i & 2) != 0) {
            list2 = null;
        }
        if ((i & 4) != 0) {
            list3 = null;
        }
        this(list, list2, list3);
    }

    public final List<IntRange> getDocumentCopies() {
        return this.documentCopies;
    }

    public final void setDocumentCopies(List<IntRange> list) {
        this.documentCopies = list;
    }

    public final List<IntRange> getDocumentNumbers() {
        return this.documentNumbers;
    }

    public final void setDocumentNumbers(List<IntRange> list) {
        this.documentNumbers = list;
    }

    public final List<IntRange> getPages() {
        return this.pages;
    }

    public final void setPages(List<IntRange> list) {
        this.pages = list;
    }

    public Overrides() {
        this(null, null, null);
    }

    @Override
    public List<Attribute<?>> getAttributes() {
        Attribute[] attributeArr = new Attribute[3];
        List<IntRange> list = this.documentCopies;
        attributeArr[0] = list != null ? documentCopies.mo417of((Iterable<? extends IntRange>) list) : null;
        List<IntRange> list2 = this.documentNumbers;
        attributeArr[1] = list2 != null ? documentNumbers.mo417of((Iterable<? extends IntRange>) list2) : null;
        List<IntRange> list3 = this.pages;
        attributeArr[2] = list3 != null ? pages.mo417of((Iterable<? extends IntRange>) list3) : null;
        return CollectionsKt.listOfNotNull((Object[]) attributeArr);
    }

    @Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003J\u001a\u0010\u000e\u001a\u00020\u00022\u0010\u0010\u000f\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00110\u0010H\u0016R\u0016\u0010\u0004\u001a\u00020\u00008\u0006X\u0087\u0004¢\u0006\b\n\u0000\u0012\u0004\b\u0005\u0010\u0003R\u001a\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00020\u0007X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0010\u0010\n\u001a\u00020\u000b8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u00020\u000b8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u00020\u000b8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000¨\u0006\u0012"}, m1293d2 = {"Lcom/hp/jipp/model/Overrides$Companion;", "Lcom/hp/jipp/encoding/AttributeCollection$Converter;", "Lcom/hp/jipp/model/Overrides;", "()V", "Types", "getTypes$annotations", "cls", "Ljava/lang/Class;", "getCls", "()Ljava/lang/Class;", "documentCopies", "Lcom/hp/jipp/encoding/IntRangeType$Set;", "documentNumbers", "pages", "convert", "attributes", "", "Lcom/hp/jipp/encoding/Attribute;", "jipp-core"}, m1294k = 1, m1295mv = {1, 4, 0})
    public static final class Companion implements AttributeCollection.Converter<Overrides> {
        @Deprecated(message = "Remove this symbol")
        public static void getTypes$annotations() {
        }

        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @Override
        public <T> Attribute<T> coerced(List<? extends Attribute<?>> attributes, AttributeType<T> type) {
            Intrinsics.checkNotNullParameter(attributes, "attributes");
            Intrinsics.checkNotNullParameter(type, "type");
            return AttributeCollection.Converter.DefaultImpls.coerced(this, attributes, type);
        }

        @Override
        public AttributeCollection convert(List list) {
            return convert((List<? extends Attribute<?>>) list);
        }

        @Override
        public <T> List<T> extractAll(List<? extends Attribute<?>> attributes, AttributeType<T> type) {
            Intrinsics.checkNotNullParameter(attributes, "attributes");
            Intrinsics.checkNotNullParameter(type, "type");
            return AttributeCollection.Converter.DefaultImpls.extractAll(this, attributes, type);
        }

        @Override
        public <T> T extractOne(List<? extends Attribute<?>> attributes, AttributeType<T> type) {
            Intrinsics.checkNotNullParameter(attributes, "attributes");
            Intrinsics.checkNotNullParameter(type, "type");
            return (T) AttributeCollection.Converter.DefaultImpls.extractOne(this, attributes, type);
        }

        @Override
        public Overrides convert(List<? extends Attribute<?>> attributes) {
            Intrinsics.checkNotNullParameter(attributes, "attributes");
            return new Overrides(extractAll(attributes, Overrides.documentCopies), extractAll(attributes, Overrides.documentNumbers), extractAll(attributes, Overrides.pages));
        }

        @Override
        public Class<Overrides> getCls() {
            return Overrides.cls;
        }
    }

    static {
        Companion companion = new Companion(null);
        INSTANCE = companion;
        cls = Overrides.class;
        Types = companion;
        documentCopies = new IntRangeType.Set("document-copies");
        documentNumbers = new IntRangeType.Set("document-numbers");
        pages = new IntRangeType.Set("pages");
    }

    public String toString() {
        return "Overrides(" + CollectionsKt.joinToString$default(getAttributes(), null, null, null, 0, null, null, 63, null) + ')';
    }
}
