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

@Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0014\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\b\u0086\b\u0018\u0000 &2\u00020\u0001:\u0001&B\u0007\b\u0016¢\u0006\u0002\u0010\u0002B)\u0012\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0004¢\u0006\u0002\u0010\bJ\u000b\u0010\u001b\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\u000b\u0010\u001c\u001a\u0004\u0018\u00010\u0006HÆ\u0003J\u000b\u0010\u001d\u001a\u0004\u0018\u00010\u0004HÆ\u0003J-\u0010\u001e\u001a\u00020\u00002\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0004HÆ\u0001J\u0013\u0010\u001f\u001a\u00020 2\b\u0010!\u001a\u0004\u0018\u00010\"HÖ\u0003J\t\u0010#\u001a\u00020$HÖ\u0001J\b\u0010%\u001a\u00020\u0004H\u0016R\u001e\u0010\t\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u000b0\n8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\rR\u001e\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u0010\n\u0002\b\u0012\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u001e\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0086\u000e¢\u0006\u0010\n\u0002\b\u0017\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R\u001e\u0010\u0007\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u0010\n\u0002\b\u001a\u001a\u0004\b\u0018\u0010\u000f\"\u0004\b\u0019\u0010\u0011¨\u0006'"}, m1293d2 = {"Lcom/hp/jipp/model/PdlInitFile;", "Lcom/hp/jipp/encoding/AttributeCollection;", "()V", "pdlInitFileEntry", "", "pdlInitFileLocation", "Ljava/net/URI;", "pdlInitFileName", "(Ljava/lang/String;Ljava/net/URI;Ljava/lang/String;)V", "attributes", "", "Lcom/hp/jipp/encoding/Attribute;", "getAttributes", "()Ljava/util/List;", "getPdlInitFileEntry", "()Ljava/lang/String;", "setPdlInitFileEntry", "(Ljava/lang/String;)V", "pdlInitFileEntry$1", "getPdlInitFileLocation", "()Ljava/net/URI;", "setPdlInitFileLocation", "(Ljava/net/URI;)V", "pdlInitFileLocation$1", "getPdlInitFileName", "setPdlInitFileName", "pdlInitFileName$1", "component1", "component2", "component3", PrinterServiceType.copy, "equals", "", "other", "", "hashCode", "", "toString", "Companion", "jipp-core"}, m1294k = 1, m1295mv = {1, 4, 0})
public final class PdlInitFile implements AttributeCollection {

    public static final Companion INSTANCE;
    public static final Companion Types;
    private static final Class<PdlInitFile> cls;
    public static final NameType pdlInitFileEntry;
    public static final UriType pdlInitFileLocation;
    public static final NameType pdlInitFileName;

    private String pdlInitFileEntry;

    private URI pdlInitFileLocation;

    private String pdlInitFileName;

    public static PdlInitFile copy$default(PdlInitFile pdlInitFile, String str, URI uri, String str2, int i, Object obj) {
        if ((i & 1) != 0) {
            str = pdlInitFile.pdlInitFileEntry;
        }
        if ((i & 2) != 0) {
            uri = pdlInitFile.pdlInitFileLocation;
        }
        if ((i & 4) != 0) {
            str2 = pdlInitFile.pdlInitFileName;
        }
        return pdlInitFile.copy(str, uri, str2);
    }

    public final String getPdlInitFileEntry() {
        return this.pdlInitFileEntry;
    }

    public final URI getPdlInitFileLocation() {
        return this.pdlInitFileLocation;
    }

    public final String getPdlInitFileName() {
        return this.pdlInitFileName;
    }

    public final PdlInitFile copy(String pdlInitFileEntry2, URI pdlInitFileLocation2, String pdlInitFileName2) {
        return new PdlInitFile(pdlInitFileEntry2, pdlInitFileLocation2, pdlInitFileName2);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof PdlInitFile)) {
            return false;
        }
        PdlInitFile pdlInitFile = (PdlInitFile) other;
        return Intrinsics.areEqual(this.pdlInitFileEntry, pdlInitFile.pdlInitFileEntry) && Intrinsics.areEqual(this.pdlInitFileLocation, pdlInitFile.pdlInitFileLocation) && Intrinsics.areEqual(this.pdlInitFileName, pdlInitFile.pdlInitFileName);
    }

    public int hashCode() {
        String str = this.pdlInitFileEntry;
        int iHashCode = (str != null ? str.hashCode() : 0) * 31;
        URI uri = this.pdlInitFileLocation;
        int iHashCode2 = (iHashCode + (uri != null ? uri.hashCode() : 0)) * 31;
        String str2 = this.pdlInitFileName;
        return iHashCode2 + (str2 != null ? str2.hashCode() : 0);
    }

    @Override
    public void print(PrettyPrinter printer) {
        Intrinsics.checkNotNullParameter(printer, "printer");
        AttributeCollection.DefaultImpls.print(this, printer);
    }

    public PdlInitFile(String str, URI uri, String str2) {
        this.pdlInitFileEntry = str;
        this.pdlInitFileLocation = uri;
        this.pdlInitFileName = str2;
    }

    public PdlInitFile(String str, URI uri, String str2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        if ((i & 1) != 0) {
            str = null;
        }
        if ((i & 2) != 0) {
            uri = null;
        }
        if ((i & 4) != 0) {
            str2 = null;
        }
        this(str, uri, str2);
    }

    public final String getPdlInitFileEntry() {
        return this.pdlInitFileEntry;
    }

    public final void setPdlInitFileEntry(String str) {
        this.pdlInitFileEntry = str;
    }

    public final URI getPdlInitFileLocation() {
        return this.pdlInitFileLocation;
    }

    public final void setPdlInitFileLocation(URI uri) {
        this.pdlInitFileLocation = uri;
    }

    public final String getPdlInitFileName() {
        return this.pdlInitFileName;
    }

    public final void setPdlInitFileName(String str) {
        this.pdlInitFileName = str;
    }

    public PdlInitFile() {
        this(null, null, null);
    }

    @Override
    public List<Attribute<?>> getAttributes() {
        Attribute[] attributeArr = new Attribute[3];
        String str = this.pdlInitFileEntry;
        attributeArr[0] = str != null ? pdlInitFileEntry.m440of(str) : null;
        URI uri = this.pdlInitFileLocation;
        attributeArr[1] = uri != null ? pdlInitFileLocation.mo418of(uri) : null;
        String str2 = this.pdlInitFileName;
        attributeArr[2] = str2 != null ? pdlInitFileName.m440of(str2) : null;
        return CollectionsKt.listOfNotNull((Object[]) attributeArr);
    }

    @Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003J\u001a\u0010\u000f\u001a\u00020\u00022\u0010\u0010\u0010\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00120\u0011H\u0016R\u0016\u0010\u0004\u001a\u00020\u00008\u0006X\u0087\u0004¢\u0006\b\n\u0000\u0012\u0004\b\u0005\u0010\u0003R\u001a\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00020\u0007X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0010\u0010\n\u001a\u00020\u000b8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u00020\r8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u00020\u000b8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000¨\u0006\u0013"}, m1293d2 = {"Lcom/hp/jipp/model/PdlInitFile$Companion;", "Lcom/hp/jipp/encoding/AttributeCollection$Converter;", "Lcom/hp/jipp/model/PdlInitFile;", "()V", "Types", "getTypes$annotations", "cls", "Ljava/lang/Class;", "getCls", "()Ljava/lang/Class;", "pdlInitFileEntry", "Lcom/hp/jipp/encoding/NameType;", "pdlInitFileLocation", "Lcom/hp/jipp/encoding/UriType;", "pdlInitFileName", "convert", "attributes", "", "Lcom/hp/jipp/encoding/Attribute;", "jipp-core"}, m1294k = 1, m1295mv = {1, 4, 0})
    public static final class Companion implements AttributeCollection.Converter<PdlInitFile> {
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
        public PdlInitFile convert(List<? extends Attribute<?>> attributes) {
            Intrinsics.checkNotNullParameter(attributes, "attributes");
            Name name = (Name) extractOne(attributes, PdlInitFile.pdlInitFileEntry);
            String value = name != null ? name.getValue() : null;
            URI uri = (URI) extractOne(attributes, PdlInitFile.pdlInitFileLocation);
            Name name2 = (Name) extractOne(attributes, PdlInitFile.pdlInitFileName);
            return new PdlInitFile(value, uri, name2 != null ? name2.getValue() : null);
        }

        @Override
        public Class<PdlInitFile> getCls() {
            return PdlInitFile.cls;
        }
    }

    static {
        Companion companion = new Companion(null);
        INSTANCE = companion;
        cls = PdlInitFile.class;
        Types = companion;
        pdlInitFileEntry = new NameType(PdlInitFileSupported.pdlInitFileEntry);
        pdlInitFileLocation = new UriType(PdlInitFileSupported.pdlInitFileLocation);
        pdlInitFileName = new NameType(PdlInitFileSupported.pdlInitFileName);
    }

    public String toString() {
        return "PdlInitFile(" + CollectionsKt.joinToString$default(getAttributes(), null, null, null, 0, null, null, 63, null) + ')';
    }
}
