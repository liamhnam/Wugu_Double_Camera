package com.p020hp.jipp.model;

import com.p020hp.jipp.encoding.Attribute;
import com.p020hp.jipp.encoding.AttributeCollection;
import com.p020hp.jipp.encoding.AttributeType;
import com.p020hp.jipp.encoding.Name;
import com.p020hp.jipp.encoding.NameType;
import com.p020hp.jipp.encoding.UriType;
import com.p020hp.jipp.util.PrettyPrinter;
import java.net.URI;
import java.util.List;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\b\u0086\b\u0018\u0000 !2\u00020\u0001:\u0001!B\u0007\b\u0016¢\u0006\u0002\u0010\u0002B\u001d\u0012\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\u0002\u0010\u0007J\u000b\u0010\u0017\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\u000b\u0010\u0018\u001a\u0004\u0018\u00010\u0006HÆ\u0003J!\u0010\u0019\u001a\u00020\u00002\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006HÆ\u0001J\u0013\u0010\u001a\u001a\u00020\u001b2\b\u0010\u001c\u001a\u0004\u0018\u00010\u001dHÖ\u0003J\t\u0010\u001e\u001a\u00020\u001fHÖ\u0001J\b\u0010 \u001a\u00020\u0004H\u0016R\u001e\u0010\b\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\n0\t8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\fR\u001e\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u0010\n\u0002\b\u0011\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u001e\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0086\u000e¢\u0006\u0010\n\u0002\b\u0016\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015¨\u0006\""}, m1293d2 = {"Lcom/hp/jipp/model/PrinterIccProfiles;", "Lcom/hp/jipp/encoding/AttributeCollection;", "()V", "profileName", "", "profileUrl", "Ljava/net/URI;", "(Ljava/lang/String;Ljava/net/URI;)V", "attributes", "", "Lcom/hp/jipp/encoding/Attribute;", "getAttributes", "()Ljava/util/List;", "getProfileName", "()Ljava/lang/String;", "setProfileName", "(Ljava/lang/String;)V", "profileName$1", "getProfileUrl", "()Ljava/net/URI;", "setProfileUrl", "(Ljava/net/URI;)V", "profileUrl$1", "component1", "component2", PrinterServiceType.copy, "equals", "", "other", "", "hashCode", "", "toString", "Companion", "jipp-core"}, m1294k = 1, m1295mv = {1, 4, 0})
public final class PrinterIccProfiles implements AttributeCollection {

    public static final Companion INSTANCE;
    public static final Companion Types;
    private static final Class<PrinterIccProfiles> cls;
    public static final NameType profileName;
    public static final UriType profileUrl;

    private String profileName;

    private URI profileUrl;

    public static PrinterIccProfiles copy$default(PrinterIccProfiles printerIccProfiles, String str, URI uri, int i, Object obj) {
        if ((i & 1) != 0) {
            str = printerIccProfiles.profileName;
        }
        if ((i & 2) != 0) {
            uri = printerIccProfiles.profileUrl;
        }
        return printerIccProfiles.copy(str, uri);
    }

    public final String getProfileName() {
        return this.profileName;
    }

    public final URI getProfileUrl() {
        return this.profileUrl;
    }

    public final PrinterIccProfiles copy(String profileName2, URI profileUrl2) {
        return new PrinterIccProfiles(profileName2, profileUrl2);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof PrinterIccProfiles)) {
            return false;
        }
        PrinterIccProfiles printerIccProfiles = (PrinterIccProfiles) other;
        return Intrinsics.areEqual(this.profileName, printerIccProfiles.profileName) && Intrinsics.areEqual(this.profileUrl, printerIccProfiles.profileUrl);
    }

    public int hashCode() {
        String str = this.profileName;
        int iHashCode = (str != null ? str.hashCode() : 0) * 31;
        URI uri = this.profileUrl;
        return iHashCode + (uri != null ? uri.hashCode() : 0);
    }

    @Override
    public void print(PrettyPrinter printer) {
        Intrinsics.checkNotNullParameter(printer, "printer");
        AttributeCollection.DefaultImpls.print(this, printer);
    }

    public PrinterIccProfiles(String str, URI uri) {
        this.profileName = str;
        this.profileUrl = uri;
    }

    public PrinterIccProfiles(String str, URI uri, int i, DefaultConstructorMarker defaultConstructorMarker) {
        if ((i & 1) != 0) {
            str = null;
        }
        if ((i & 2) != 0) {
            uri = null;
        }
        this(str, uri);
    }

    public final String getProfileName() {
        return this.profileName;
    }

    public final void setProfileName(String str) {
        this.profileName = str;
    }

    public final URI getProfileUrl() {
        return this.profileUrl;
    }

    public final void setProfileUrl(URI uri) {
        this.profileUrl = uri;
    }

    public PrinterIccProfiles() {
        this(null, null);
    }

    @Override
    public List<Attribute<?>> getAttributes() {
        Attribute[] attributeArr = new Attribute[2];
        String str = this.profileName;
        attributeArr[0] = str != null ? profileName.m440of(str) : null;
        URI uri = this.profileUrl;
        attributeArr[1] = uri != null ? profileUrl.mo418of(uri) : null;
        return CollectionsKt.listOfNotNull((Object[]) attributeArr);
    }

    @Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003J\u001a\u0010\u000e\u001a\u00020\u00022\u0010\u0010\u000f\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00110\u0010H\u0016R\u0016\u0010\u0004\u001a\u00020\u00008\u0006X\u0087\u0004¢\u0006\b\n\u0000\u0012\u0004\b\u0005\u0010\u0003R\u001a\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00020\u0007X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0010\u0010\n\u001a\u00020\u000b8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u00020\r8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000¨\u0006\u0012"}, m1293d2 = {"Lcom/hp/jipp/model/PrinterIccProfiles$Companion;", "Lcom/hp/jipp/encoding/AttributeCollection$Converter;", "Lcom/hp/jipp/model/PrinterIccProfiles;", "()V", "Types", "getTypes$annotations", "cls", "Ljava/lang/Class;", "getCls", "()Ljava/lang/Class;", "profileName", "Lcom/hp/jipp/encoding/NameType;", "profileUrl", "Lcom/hp/jipp/encoding/UriType;", "convert", "attributes", "", "Lcom/hp/jipp/encoding/Attribute;", "jipp-core"}, m1294k = 1, m1295mv = {1, 4, 0})
    public static final class Companion implements AttributeCollection.Converter<PrinterIccProfiles> {
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
        public PrinterIccProfiles convert(List<? extends Attribute<?>> attributes) {
            Intrinsics.checkNotNullParameter(attributes, "attributes");
            Name name = (Name) extractOne(attributes, PrinterIccProfiles.profileName);
            return new PrinterIccProfiles(name != null ? name.getValue() : null, (URI) extractOne(attributes, PrinterIccProfiles.profileUrl));
        }

        @Override
        public Class<PrinterIccProfiles> getCls() {
            return PrinterIccProfiles.cls;
        }
    }

    static {
        Companion companion = new Companion(null);
        INSTANCE = companion;
        cls = PrinterIccProfiles.class;
        Types = companion;
        profileName = new NameType("profile-name");
        profileUrl = new UriType("profile-url");
    }

    public String toString() {
        return "PrinterIccProfiles(" + CollectionsKt.joinToString$default(getAttributes(), null, null, null, 0, null, null, 63, null) + ')';
    }
}
