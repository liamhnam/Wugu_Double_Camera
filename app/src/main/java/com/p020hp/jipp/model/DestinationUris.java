package com.p020hp.jipp.model;

import com.p020hp.jipp.encoding.Attribute;
import com.p020hp.jipp.encoding.AttributeCollection;
import com.p020hp.jipp.encoding.AttributeType;
import com.p020hp.jipp.encoding.IntType;
import com.p020hp.jipp.encoding.Text;
import com.p020hp.jipp.encoding.TextType;
import com.p020hp.jipp.encoding.UntypedCollection;
import com.p020hp.jipp.encoding.UriType;
import com.p020hp.jipp.util.PrettyPrinter;
import java.net.URI;
import java.util.List;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b!\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0004\b\u0086\b\u0018\u0000 62\u00020\u0001:\u00016B\u0007\b\u0016¢\u0006\u0002\u0010\u0002BG\u0012\u0010\b\u0002\u0010\u0003\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0004\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\t\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\f¢\u0006\u0002\u0010\rJ\u0011\u0010)\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0004HÆ\u0003J\u000b\u0010*\u001a\u0004\u0018\u00010\u0007HÆ\u0003J\u000b\u0010+\u001a\u0004\u0018\u00010\tHÆ\u0003J\u000b\u0010,\u001a\u0004\u0018\u00010\tHÆ\u0003J\u0010\u0010-\u001a\u0004\u0018\u00010\fHÆ\u0003¢\u0006\u0002\u0010$JP\u0010.\u001a\u00020\u00002\u0010\b\u0002\u0010\u0003\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u00042\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\fHÆ\u0001¢\u0006\u0002\u0010/J\u0013\u00100\u001a\u0002012\b\u00102\u001a\u0004\u0018\u000103HÖ\u0003J\t\u00104\u001a\u00020\fHÖ\u0001J\b\u00105\u001a\u00020\tH\u0016R\u001e\u0010\u000e\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u000f0\u00048VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u0011R$\u0010\u0003\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0004X\u0086\u000e¢\u0006\u0010\n\u0002\b\u0015\u001a\u0004\b\u0012\u0010\u0011\"\u0004\b\u0013\u0010\u0014R\u001e\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0086\u000e¢\u0006\u0010\n\u0002\b\u001a\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019R\u001e\u0010\b\u001a\u0004\u0018\u00010\tX\u0086\u000e¢\u0006\u0010\n\u0002\b\u001f\u001a\u0004\b\u001b\u0010\u001c\"\u0004\b\u001d\u0010\u001eR\u001e\u0010\n\u001a\u0004\u0018\u00010\tX\u0086\u000e¢\u0006\u0010\n\u0002\b\"\u001a\u0004\b \u0010\u001c\"\u0004\b!\u0010\u001eR \u0010\u000b\u001a\u0004\u0018\u00010\fX\u0086\u000e¢\u0006\u0012\n\u0004\b'\u0010(\u001a\u0004\b#\u0010$\"\u0004\b%\u0010&¨\u00067"}, m1293d2 = {"Lcom/hp/jipp/model/DestinationUris;", "Lcom/hp/jipp/encoding/AttributeCollection;", "()V", "destinationAttributes", "", "Lcom/hp/jipp/encoding/UntypedCollection;", "destinationUri", "Ljava/net/URI;", "postDialString", "", "preDialString", "t33Subaddress", "", "(Ljava/util/List;Ljava/net/URI;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;)V", "attributes", "Lcom/hp/jipp/encoding/Attribute;", "getAttributes", "()Ljava/util/List;", "getDestinationAttributes", "setDestinationAttributes", "(Ljava/util/List;)V", "destinationAttributes$1", "getDestinationUri", "()Ljava/net/URI;", "setDestinationUri", "(Ljava/net/URI;)V", "destinationUri$1", "getPostDialString", "()Ljava/lang/String;", "setPostDialString", "(Ljava/lang/String;)V", "postDialString$1", "getPreDialString", "setPreDialString", "preDialString$1", "getT33Subaddress", "()Ljava/lang/Integer;", "setT33Subaddress", "(Ljava/lang/Integer;)V", "t33Subaddress$1", "Ljava/lang/Integer;", "component1", "component2", "component3", "component4", "component5", PrinterServiceType.copy, "(Ljava/util/List;Ljava/net/URI;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;)Lcom/hp/jipp/model/DestinationUris;", "equals", "", "other", "", "hashCode", "toString", "Companion", "jipp-core"}, m1294k = 1, m1295mv = {1, 4, 0})
public final class DestinationUris implements AttributeCollection {

    public static final Companion INSTANCE;
    public static final Companion Types;
    private static final Class<DestinationUris> cls;
    public static final UntypedCollection.SetType destinationAttributes;
    public static final UriType destinationUri;
    public static final TextType postDialString;
    public static final TextType preDialString;
    public static final IntType t33Subaddress;

    private List<UntypedCollection> destinationAttributes;

    private URI destinationUri;

    private String postDialString;

    private String preDialString;

    private Integer t33Subaddress;

    public static DestinationUris copy$default(DestinationUris destinationUris, List list, URI uri, String str, String str2, Integer num, int i, Object obj) {
        if ((i & 1) != 0) {
            list = destinationUris.destinationAttributes;
        }
        if ((i & 2) != 0) {
            uri = destinationUris.destinationUri;
        }
        URI uri2 = uri;
        if ((i & 4) != 0) {
            str = destinationUris.postDialString;
        }
        String str3 = str;
        if ((i & 8) != 0) {
            str2 = destinationUris.preDialString;
        }
        String str4 = str2;
        if ((i & 16) != 0) {
            num = destinationUris.t33Subaddress;
        }
        return destinationUris.copy(list, uri2, str3, str4, num);
    }

    public final List<UntypedCollection> component1() {
        return this.destinationAttributes;
    }

    public final URI getDestinationUri() {
        return this.destinationUri;
    }

    public final String getPostDialString() {
        return this.postDialString;
    }

    public final String getPreDialString() {
        return this.preDialString;
    }

    public final Integer getT33Subaddress() {
        return this.t33Subaddress;
    }

    public final DestinationUris copy(List<UntypedCollection> destinationAttributes2, URI destinationUri2, String postDialString2, String preDialString2, Integer t33Subaddress2) {
        return new DestinationUris(destinationAttributes2, destinationUri2, postDialString2, preDialString2, t33Subaddress2);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof DestinationUris)) {
            return false;
        }
        DestinationUris destinationUris = (DestinationUris) other;
        return Intrinsics.areEqual(this.destinationAttributes, destinationUris.destinationAttributes) && Intrinsics.areEqual(this.destinationUri, destinationUris.destinationUri) && Intrinsics.areEqual(this.postDialString, destinationUris.postDialString) && Intrinsics.areEqual(this.preDialString, destinationUris.preDialString) && Intrinsics.areEqual(this.t33Subaddress, destinationUris.t33Subaddress);
    }

    public int hashCode() {
        List<UntypedCollection> list = this.destinationAttributes;
        int iHashCode = (list != null ? list.hashCode() : 0) * 31;
        URI uri = this.destinationUri;
        int iHashCode2 = (iHashCode + (uri != null ? uri.hashCode() : 0)) * 31;
        String str = this.postDialString;
        int iHashCode3 = (iHashCode2 + (str != null ? str.hashCode() : 0)) * 31;
        String str2 = this.preDialString;
        int iHashCode4 = (iHashCode3 + (str2 != null ? str2.hashCode() : 0)) * 31;
        Integer num = this.t33Subaddress;
        return iHashCode4 + (num != null ? num.hashCode() : 0);
    }

    @Override
    public void print(PrettyPrinter printer) {
        Intrinsics.checkNotNullParameter(printer, "printer");
        AttributeCollection.DefaultImpls.print(this, printer);
    }

    public DestinationUris(List<UntypedCollection> list, URI uri, String str, String str2, Integer num) {
        this.destinationAttributes = list;
        this.destinationUri = uri;
        this.postDialString = str;
        this.preDialString = str2;
        this.t33Subaddress = num;
    }

    public DestinationUris(List list, URI uri, String str, String str2, Integer num, int i, DefaultConstructorMarker defaultConstructorMarker) {
        URI uri2;
        String str3;
        String str4;
        Integer num2 = null;
        if ((i & 1) != 0) {
            list = null;
        }
        if ((i & 2) != 0) {
            uri2 = null;
        } else {
            uri2 = uri;
        }
        if ((i & 4) != 0) {
            str3 = null;
        } else {
            str3 = str;
        }
        if ((i & 8) != 0) {
            str4 = null;
        } else {
            str4 = str2;
        }
        if ((i & 16) != 0) {
        } else {
            num2 = num;
        }
        this(list, uri2, str3, str4, num2);
    }

    public final List<UntypedCollection> getDestinationAttributes() {
        return this.destinationAttributes;
    }

    public final void setDestinationAttributes(List<UntypedCollection> list) {
        this.destinationAttributes = list;
    }

    public final URI getDestinationUri() {
        return this.destinationUri;
    }

    public final void setDestinationUri(URI uri) {
        this.destinationUri = uri;
    }

    public final String getPostDialString() {
        return this.postDialString;
    }

    public final void setPostDialString(String str) {
        this.postDialString = str;
    }

    public final String getPreDialString() {
        return this.preDialString;
    }

    public final void setPreDialString(String str) {
        this.preDialString = str;
    }

    public final Integer getT33Subaddress() {
        return this.t33Subaddress;
    }

    public final void setT33Subaddress(Integer num) {
        this.t33Subaddress = num;
    }

    public DestinationUris() {
        this(null, null, null, null, null);
    }

    @Override
    public List<Attribute<?>> getAttributes() {
        Attribute[] attributeArr = new Attribute[5];
        List<UntypedCollection> list = this.destinationAttributes;
        attributeArr[0] = list != null ? destinationAttributes.mo417of((Iterable<? extends UntypedCollection>) list) : null;
        URI uri = this.destinationUri;
        attributeArr[1] = uri != null ? destinationUri.mo418of(uri) : null;
        String str = this.postDialString;
        attributeArr[2] = str != null ? postDialString.m442of(str) : null;
        String str2 = this.preDialString;
        attributeArr[3] = str2 != null ? preDialString.m442of(str2) : null;
        Integer num = this.t33Subaddress;
        attributeArr[4] = num != null ? t33Subaddress.mo418of(Integer.valueOf(num.intValue())) : null;
        return CollectionsKt.listOfNotNull((Object[]) attributeArr);
    }

    @Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003J\u001a\u0010\u0013\u001a\u00020\u00022\u0010\u0010\u0014\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00160\u0015H\u0016R\u0016\u0010\u0004\u001a\u00020\u00008\u0006X\u0087\u0004¢\u0006\b\n\u0000\u0012\u0004\b\u0005\u0010\u0003R\u001a\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00020\u0007X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0010\u0010\n\u001a\u00020\u000b8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u00020\r8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u00020\u000f8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0010\u001a\u00020\u000f8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0011\u001a\u00020\u00128\u0006X\u0087\u0004¢\u0006\u0002\n\u0000¨\u0006\u0017"}, m1293d2 = {"Lcom/hp/jipp/model/DestinationUris$Companion;", "Lcom/hp/jipp/encoding/AttributeCollection$Converter;", "Lcom/hp/jipp/model/DestinationUris;", "()V", "Types", "getTypes$annotations", "cls", "Ljava/lang/Class;", "getCls", "()Ljava/lang/Class;", "destinationAttributes", "Lcom/hp/jipp/encoding/UntypedCollection$SetType;", "destinationUri", "Lcom/hp/jipp/encoding/UriType;", "postDialString", "Lcom/hp/jipp/encoding/TextType;", "preDialString", "t33Subaddress", "Lcom/hp/jipp/encoding/IntType;", "convert", "attributes", "", "Lcom/hp/jipp/encoding/Attribute;", "jipp-core"}, m1294k = 1, m1295mv = {1, 4, 0})
    public static final class Companion implements AttributeCollection.Converter<DestinationUris> {
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
        public DestinationUris convert(List<? extends Attribute<?>> attributes) {
            Intrinsics.checkNotNullParameter(attributes, "attributes");
            List listExtractAll = extractAll(attributes, DestinationUris.destinationAttributes);
            URI uri = (URI) extractOne(attributes, DestinationUris.destinationUri);
            Text text = (Text) extractOne(attributes, DestinationUris.postDialString);
            String value = text != null ? text.getValue() : null;
            Text text2 = (Text) extractOne(attributes, DestinationUris.preDialString);
            return new DestinationUris(listExtractAll, uri, value, text2 != null ? text2.getValue() : null, (Integer) extractOne(attributes, DestinationUris.t33Subaddress));
        }

        @Override
        public Class<DestinationUris> getCls() {
            return DestinationUris.cls;
        }
    }

    static {
        Companion companion = new Companion(null);
        INSTANCE = companion;
        cls = DestinationUris.class;
        Types = companion;
        destinationAttributes = new UntypedCollection.SetType("destination-attributes");
        destinationUri = new UriType("destination-uri");
        postDialString = new TextType("post-dial-string");
        preDialString = new TextType("pre-dial-string");
        t33Subaddress = new IntType("t33-subaddress");
    }

    public String toString() {
        return "DestinationUris(" + CollectionsKt.joinToString$default(getAttributes(), null, null, null, 0, null, null, 63, null) + ')';
    }
}
