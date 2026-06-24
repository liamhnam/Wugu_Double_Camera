package com.p020hp.jipp.model;

import com.p020hp.jipp.encoding.Attribute;
import com.p020hp.jipp.encoding.AttributeCollection;
import com.p020hp.jipp.encoding.AttributeType;
import com.p020hp.jipp.encoding.KeywordType;
import com.p020hp.jipp.encoding.UriType;
import com.p020hp.jipp.util.PrettyPrinter;
import java.net.URI;
import java.util.List;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0014\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\b\u0086\b\u0018\u0000 &2\u00020\u0001:\u0001&B\u0007\b\u0016¢\u0006\u0002\u0010\u0002B)\u0012\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\u0002\u0010\bJ\u000b\u0010\u001b\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\u000b\u0010\u001c\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\u000b\u0010\u001d\u001a\u0004\u0018\u00010\u0007HÆ\u0003J-\u0010\u001e\u001a\u00020\u00002\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007HÆ\u0001J\u0013\u0010\u001f\u001a\u00020 2\b\u0010!\u001a\u0004\u0018\u00010\"HÖ\u0003J\t\u0010#\u001a\u00020$HÖ\u0001J\b\u0010%\u001a\u00020\u0004H\u0016R\u001e\u0010\t\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u000b0\n8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\rR\u001e\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u0010\n\u0002\b\u0012\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u001e\u0010\u0005\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u0010\n\u0002\b\u0015\u001a\u0004\b\u0013\u0010\u000f\"\u0004\b\u0014\u0010\u0011R\u001e\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0086\u000e¢\u0006\u0010\n\u0002\b\u001a\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019¨\u0006'"}, m1293d2 = {"Lcom/hp/jipp/model/PrinterXriSupported;", "Lcom/hp/jipp/encoding/AttributeCollection;", "()V", "xriAuthentication", "", "xriSecurity", "xriUri", "Ljava/net/URI;", "(Ljava/lang/String;Ljava/lang/String;Ljava/net/URI;)V", "attributes", "", "Lcom/hp/jipp/encoding/Attribute;", "getAttributes", "()Ljava/util/List;", "getXriAuthentication", "()Ljava/lang/String;", "setXriAuthentication", "(Ljava/lang/String;)V", "xriAuthentication$1", "getXriSecurity", "setXriSecurity", "xriSecurity$1", "getXriUri", "()Ljava/net/URI;", "setXriUri", "(Ljava/net/URI;)V", "xriUri$1", "component1", "component2", "component3", PrinterServiceType.copy, "equals", "", "other", "", "hashCode", "", "toString", "Companion", "jipp-core"}, m1294k = 1, m1295mv = {1, 4, 0})
public final class PrinterXriSupported implements AttributeCollection {

    public static final Companion INSTANCE;
    public static final Companion Types;
    private static final Class<PrinterXriSupported> cls;
    public static final KeywordType xriAuthentication;
    public static final KeywordType xriSecurity;
    public static final UriType xriUri;

    private String xriAuthentication;

    private String xriSecurity;

    private URI xriUri;

    public static PrinterXriSupported copy$default(PrinterXriSupported printerXriSupported, String str, String str2, URI uri, int i, Object obj) {
        if ((i & 1) != 0) {
            str = printerXriSupported.xriAuthentication;
        }
        if ((i & 2) != 0) {
            str2 = printerXriSupported.xriSecurity;
        }
        if ((i & 4) != 0) {
            uri = printerXriSupported.xriUri;
        }
        return printerXriSupported.copy(str, str2, uri);
    }

    public final String getXriAuthentication() {
        return this.xriAuthentication;
    }

    public final String getXriSecurity() {
        return this.xriSecurity;
    }

    public final URI getXriUri() {
        return this.xriUri;
    }

    public final PrinterXriSupported copy(String xriAuthentication2, String xriSecurity2, URI xriUri2) {
        return new PrinterXriSupported(xriAuthentication2, xriSecurity2, xriUri2);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof PrinterXriSupported)) {
            return false;
        }
        PrinterXriSupported printerXriSupported = (PrinterXriSupported) other;
        return Intrinsics.areEqual(this.xriAuthentication, printerXriSupported.xriAuthentication) && Intrinsics.areEqual(this.xriSecurity, printerXriSupported.xriSecurity) && Intrinsics.areEqual(this.xriUri, printerXriSupported.xriUri);
    }

    public int hashCode() {
        String str = this.xriAuthentication;
        int iHashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.xriSecurity;
        int iHashCode2 = (iHashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
        URI uri = this.xriUri;
        return iHashCode2 + (uri != null ? uri.hashCode() : 0);
    }

    @Override
    public void print(PrettyPrinter printer) {
        Intrinsics.checkNotNullParameter(printer, "printer");
        AttributeCollection.DefaultImpls.print(this, printer);
    }

    public PrinterXriSupported(String str, String str2, URI uri) {
        this.xriAuthentication = str;
        this.xriSecurity = str2;
        this.xriUri = uri;
    }

    public PrinterXriSupported(String str, String str2, URI uri, int i, DefaultConstructorMarker defaultConstructorMarker) {
        if ((i & 1) != 0) {
            str = null;
        }
        if ((i & 2) != 0) {
            str2 = null;
        }
        if ((i & 4) != 0) {
            uri = null;
        }
        this(str, str2, uri);
    }

    public final String getXriAuthentication() {
        return this.xriAuthentication;
    }

    public final void setXriAuthentication(String str) {
        this.xriAuthentication = str;
    }

    public final String getXriSecurity() {
        return this.xriSecurity;
    }

    public final void setXriSecurity(String str) {
        this.xriSecurity = str;
    }

    public final URI getXriUri() {
        return this.xriUri;
    }

    public final void setXriUri(URI uri) {
        this.xriUri = uri;
    }

    public PrinterXriSupported() {
        this(null, null, null);
    }

    @Override
    public List<Attribute<?>> getAttributes() {
        Attribute[] attributeArr = new Attribute[3];
        String str = this.xriAuthentication;
        attributeArr[0] = str != null ? xriAuthentication.mo418of(str) : null;
        String str2 = this.xriSecurity;
        attributeArr[1] = str2 != null ? xriSecurity.mo418of(str2) : null;
        URI uri = this.xriUri;
        attributeArr[2] = uri != null ? xriUri.mo418of(uri) : null;
        return CollectionsKt.listOfNotNull((Object[]) attributeArr);
    }

    @Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003J\u001a\u0010\u000f\u001a\u00020\u00022\u0010\u0010\u0010\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00120\u0011H\u0016R\u0016\u0010\u0004\u001a\u00020\u00008\u0006X\u0087\u0004¢\u0006\b\n\u0000\u0012\u0004\b\u0005\u0010\u0003R\u001a\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00020\u0007X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0010\u0010\n\u001a\u00020\u000b8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u00020\u000b8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u00020\u000e8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000¨\u0006\u0013"}, m1293d2 = {"Lcom/hp/jipp/model/PrinterXriSupported$Companion;", "Lcom/hp/jipp/encoding/AttributeCollection$Converter;", "Lcom/hp/jipp/model/PrinterXriSupported;", "()V", "Types", "getTypes$annotations", "cls", "Ljava/lang/Class;", "getCls", "()Ljava/lang/Class;", "xriAuthentication", "Lcom/hp/jipp/encoding/KeywordType;", "xriSecurity", "xriUri", "Lcom/hp/jipp/encoding/UriType;", "convert", "attributes", "", "Lcom/hp/jipp/encoding/Attribute;", "jipp-core"}, m1294k = 1, m1295mv = {1, 4, 0})
    public static final class Companion implements AttributeCollection.Converter<PrinterXriSupported> {
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
        public PrinterXriSupported convert(List<? extends Attribute<?>> attributes) {
            Intrinsics.checkNotNullParameter(attributes, "attributes");
            return new PrinterXriSupported((String) extractOne(attributes, PrinterXriSupported.xriAuthentication), (String) extractOne(attributes, PrinterXriSupported.xriSecurity), (URI) extractOne(attributes, PrinterXriSupported.xriUri));
        }

        @Override
        public Class<PrinterXriSupported> getCls() {
            return PrinterXriSupported.cls;
        }
    }

    static {
        Companion companion = new Companion(null);
        INSTANCE = companion;
        cls = PrinterXriSupported.class;
        Types = companion;
        xriAuthentication = new KeywordType("xri-authentication");
        xriSecurity = new KeywordType("xri-security");
        xriUri = new UriType("xri-uri");
    }

    public String toString() {
        return "PrinterXriSupported(" + CollectionsKt.joinToString$default(getAttributes(), null, null, null, 0, null, null, 63, null) + ')';
    }
}
