package com.p020hp.jipp.model;

import com.p020hp.jipp.encoding.Attribute;
import com.p020hp.jipp.encoding.AttributeCollection;
import com.p020hp.jipp.encoding.AttributeType;
import com.p020hp.jipp.encoding.Name;
import com.p020hp.jipp.encoding.NameType;
import com.p020hp.jipp.encoding.Text;
import com.p020hp.jipp.encoding.TextType;
import com.p020hp.jipp.encoding.UriType;
import com.p020hp.jipp.util.PrettyPrinter;
import java.net.URI;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0015\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\b\u0086\b\u0018\u0000 '2\u00020\u0001:\u0001'B\u0007\b\u0016¢\u0006\u0002\u0010\u0002B/\u0012\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\u0010\b\u0002\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\b¢\u0006\u0002\u0010\tJ\u000b\u0010\u001c\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\u000b\u0010\u001d\u001a\u0004\u0018\u00010\u0006HÆ\u0003J\u0011\u0010\u001e\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\bHÆ\u0003J3\u0010\u001f\u001a\u00020\u00002\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u0010\b\u0002\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\bHÆ\u0001J\u0013\u0010 \u001a\u00020!2\b\u0010\"\u001a\u0004\u0018\u00010#HÖ\u0003J\t\u0010$\u001a\u00020%HÖ\u0001J\b\u0010&\u001a\u00020\u0004H\u0016R\u001e\u0010\n\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u000b0\b8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\rR\u001e\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u0010\n\u0002\b\u0012\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u001e\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0086\u000e¢\u0006\u0010\n\u0002\b\u0017\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R$\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\bX\u0086\u000e¢\u0006\u0010\n\u0002\b\u001b\u001a\u0004\b\u0018\u0010\r\"\u0004\b\u0019\u0010\u001a¨\u0006("}, m1293d2 = {"Lcom/hp/jipp/model/PrinterContactCol;", "Lcom/hp/jipp/encoding/AttributeCollection;", "()V", "contactName", "", "contactUri", "Ljava/net/URI;", "contactVcard", "", "(Ljava/lang/String;Ljava/net/URI;Ljava/util/List;)V", "attributes", "Lcom/hp/jipp/encoding/Attribute;", "getAttributes", "()Ljava/util/List;", "getContactName", "()Ljava/lang/String;", "setContactName", "(Ljava/lang/String;)V", "contactName$1", "getContactUri", "()Ljava/net/URI;", "setContactUri", "(Ljava/net/URI;)V", "contactUri$1", "getContactVcard", "setContactVcard", "(Ljava/util/List;)V", "contactVcard$1", "component1", "component2", "component3", PrinterServiceType.copy, "equals", "", "other", "", "hashCode", "", "toString", "Companion", "jipp-core"}, m1294k = 1, m1295mv = {1, 4, 0})
public final class PrinterContactCol implements AttributeCollection {

    public static final Companion INSTANCE;
    public static final Companion Types;
    private static final Class<PrinterContactCol> cls;
    public static final NameType contactName;
    public static final UriType contactUri;
    public static final TextType.Set contactVcard;

    private String contactName;

    private URI contactUri;

    private List<String> contactVcard;

    public static PrinterContactCol copy$default(PrinterContactCol printerContactCol, String str, URI uri, List list, int i, Object obj) {
        if ((i & 1) != 0) {
            str = printerContactCol.contactName;
        }
        if ((i & 2) != 0) {
            uri = printerContactCol.contactUri;
        }
        if ((i & 4) != 0) {
            list = printerContactCol.contactVcard;
        }
        return printerContactCol.copy(str, uri, list);
    }

    public final String getContactName() {
        return this.contactName;
    }

    public final URI getContactUri() {
        return this.contactUri;
    }

    public final List<String> component3() {
        return this.contactVcard;
    }

    public final PrinterContactCol copy(String contactName2, URI contactUri2, List<String> contactVcard2) {
        return new PrinterContactCol(contactName2, contactUri2, contactVcard2);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof PrinterContactCol)) {
            return false;
        }
        PrinterContactCol printerContactCol = (PrinterContactCol) other;
        return Intrinsics.areEqual(this.contactName, printerContactCol.contactName) && Intrinsics.areEqual(this.contactUri, printerContactCol.contactUri) && Intrinsics.areEqual(this.contactVcard, printerContactCol.contactVcard);
    }

    public int hashCode() {
        String str = this.contactName;
        int iHashCode = (str != null ? str.hashCode() : 0) * 31;
        URI uri = this.contactUri;
        int iHashCode2 = (iHashCode + (uri != null ? uri.hashCode() : 0)) * 31;
        List<String> list = this.contactVcard;
        return iHashCode2 + (list != null ? list.hashCode() : 0);
    }

    @Override
    public void print(PrettyPrinter printer) {
        Intrinsics.checkNotNullParameter(printer, "printer");
        AttributeCollection.DefaultImpls.print(this, printer);
    }

    public PrinterContactCol(String str, URI uri, List<String> list) {
        this.contactName = str;
        this.contactUri = uri;
        this.contactVcard = list;
    }

    public PrinterContactCol(String str, URI uri, List list, int i, DefaultConstructorMarker defaultConstructorMarker) {
        if ((i & 1) != 0) {
            str = null;
        }
        if ((i & 2) != 0) {
            uri = null;
        }
        if ((i & 4) != 0) {
            list = null;
        }
        this(str, uri, list);
    }

    public final String getContactName() {
        return this.contactName;
    }

    public final void setContactName(String str) {
        this.contactName = str;
    }

    public final URI getContactUri() {
        return this.contactUri;
    }

    public final void setContactUri(URI uri) {
        this.contactUri = uri;
    }

    public final List<String> getContactVcard() {
        return this.contactVcard;
    }

    public final void setContactVcard(List<String> list) {
        this.contactVcard = list;
    }

    public PrinterContactCol() {
        this(null, null, null);
    }

    @Override
    public List<Attribute<?>> getAttributes() {
        Attribute[] attributeArr = new Attribute[3];
        String str = this.contactName;
        Attribute<Text> attributeMo417of = null;
        attributeArr[0] = str != null ? contactName.m440of(str) : null;
        URI uri = this.contactUri;
        attributeArr[1] = uri != null ? contactUri.mo418of(uri) : null;
        List<String> list = this.contactVcard;
        if (list != null) {
            TextType.Set set = contactVcard;
            List<String> list2 = list;
            ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list2, 10));
            Iterator<T> it = list2.iterator();
            while (it.hasNext()) {
                arrayList.add(new Text((String) it.next()));
            }
            attributeMo417of = set.mo417of((Iterable<? extends Text>) arrayList);
        }
        attributeArr[2] = attributeMo417of;
        return CollectionsKt.listOfNotNull((Object[]) attributeArr);
    }

    @Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003J\u001a\u0010\u0010\u001a\u00020\u00022\u0010\u0010\u0011\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00130\u0012H\u0016R\u0016\u0010\u0004\u001a\u00020\u00008\u0006X\u0087\u0004¢\u0006\b\n\u0000\u0012\u0004\b\u0005\u0010\u0003R\u001a\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00020\u0007X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0010\u0010\n\u001a\u00020\u000b8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u00020\r8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u00020\u000f8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000¨\u0006\u0014"}, m1293d2 = {"Lcom/hp/jipp/model/PrinterContactCol$Companion;", "Lcom/hp/jipp/encoding/AttributeCollection$Converter;", "Lcom/hp/jipp/model/PrinterContactCol;", "()V", "Types", "getTypes$annotations", "cls", "Ljava/lang/Class;", "getCls", "()Ljava/lang/Class;", "contactName", "Lcom/hp/jipp/encoding/NameType;", "contactUri", "Lcom/hp/jipp/encoding/UriType;", "contactVcard", "Lcom/hp/jipp/encoding/TextType$Set;", "convert", "attributes", "", "Lcom/hp/jipp/encoding/Attribute;", "jipp-core"}, m1294k = 1, m1295mv = {1, 4, 0})
    public static final class Companion implements AttributeCollection.Converter<PrinterContactCol> {
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
        public PrinterContactCol convert(List<? extends Attribute<?>> attributes) {
            Intrinsics.checkNotNullParameter(attributes, "attributes");
            Name name = (Name) extractOne(attributes, PrinterContactCol.contactName);
            ArrayList arrayList = null;
            String value = name != null ? name.getValue() : null;
            URI uri = (URI) extractOne(attributes, PrinterContactCol.contactUri);
            List listExtractAll = extractAll(attributes, PrinterContactCol.contactVcard);
            if (listExtractAll != null) {
                List list = listExtractAll;
                ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
                Iterator it = list.iterator();
                while (it.hasNext()) {
                    arrayList2.add(((Text) it.next()).getValue());
                }
                arrayList = arrayList2;
            }
            return new PrinterContactCol(value, uri, arrayList);
        }

        @Override
        public Class<PrinterContactCol> getCls() {
            return PrinterContactCol.cls;
        }
    }

    static {
        Companion companion = new Companion(null);
        INSTANCE = companion;
        cls = PrinterContactCol.class;
        Types = companion;
        contactName = new NameType("contact-name");
        contactUri = new UriType("contact-uri");
        contactVcard = new TextType.Set("contact-vcard");
    }

    public String toString() {
        return "PrinterContactCol(" + CollectionsKt.joinToString$default(getAttributes(), null, null, null, 0, null, null, 63, null) + ')';
    }
}
