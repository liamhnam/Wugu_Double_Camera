package com.p020hp.jipp.model;

import com.p020hp.jipp.encoding.Attribute;
import com.p020hp.jipp.encoding.AttributeCollection;
import com.p020hp.jipp.encoding.AttributeType;
import com.p020hp.jipp.encoding.KeywordType;
import com.p020hp.jipp.encoding.Name;
import com.p020hp.jipp.encoding.NameType;
import com.p020hp.jipp.encoding.StringType;
import com.p020hp.jipp.encoding.Tag;
import com.p020hp.jipp.encoding.UriType;
import com.p020hp.jipp.util.PrettyPrinter;
import java.net.URI;
import java.util.List;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\b\u0086\b\u0018\u0000  2\u00020\u0001:\u0002 !B\u0007\b\u0016¢\u0006\u0002\u0010\u0002B#\u0012\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u0004\u0012\u0010\b\u0002\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0006¢\u0006\u0002\u0010\bJ\u000b\u0010\u0016\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\u0011\u0010\u0017\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0006HÆ\u0003J'\u0010\u0018\u001a\u00020\u00002\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u00042\u0010\b\u0002\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0006HÆ\u0001J\u0013\u0010\u0019\u001a\u00020\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\u001cHÖ\u0003J\t\u0010\u001d\u001a\u00020\u001eHÖ\u0001J\b\u0010\u001f\u001a\u00020\u0004H\u0016R\u001e\u0010\t\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\n0\u00068VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\fR\u001e\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u0010\n\u0002\b\u0011\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R$\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0006X\u0086\u000e¢\u0006\u0010\n\u0002\b\u0015\u001a\u0004\b\u0012\u0010\f\"\u0004\b\u0013\u0010\u0014¨\u0006\""}, m1293d2 = {"Lcom/hp/jipp/model/JobSaveDisposition;", "Lcom/hp/jipp/encoding/AttributeCollection;", "()V", "saveDisposition", "", "saveInfo", "", "Lcom/hp/jipp/model/JobSaveDisposition$SaveInfo;", "(Ljava/lang/String;Ljava/util/List;)V", "attributes", "Lcom/hp/jipp/encoding/Attribute;", "getAttributes", "()Ljava/util/List;", "getSaveDisposition", "()Ljava/lang/String;", "setSaveDisposition", "(Ljava/lang/String;)V", "saveDisposition$1", "getSaveInfo", "setSaveInfo", "(Ljava/util/List;)V", "saveInfo$1", "component1", "component2", PrinterServiceType.copy, "equals", "", "other", "", "hashCode", "", "toString", "Companion", "SaveInfo", "jipp-core"}, m1294k = 1, m1295mv = {1, 4, 0})
public final class JobSaveDisposition implements AttributeCollection {

    public static final Companion INSTANCE;
    public static final Companion Types;
    private static final Class<JobSaveDisposition> cls;
    public static final KeywordType saveDisposition;
    public static final AttributeCollection.SetType<SaveInfo> saveInfo;

    private String saveDisposition;

    private List<SaveInfo> saveInfo;

    public static JobSaveDisposition copy$default(JobSaveDisposition jobSaveDisposition, String str, List list, int i, Object obj) {
        if ((i & 1) != 0) {
            str = jobSaveDisposition.saveDisposition;
        }
        if ((i & 2) != 0) {
            list = jobSaveDisposition.saveInfo;
        }
        return jobSaveDisposition.copy(str, list);
    }

    public final String getSaveDisposition() {
        return this.saveDisposition;
    }

    public final List<SaveInfo> component2() {
        return this.saveInfo;
    }

    public final JobSaveDisposition copy(String saveDisposition2, List<SaveInfo> saveInfo2) {
        return new JobSaveDisposition(saveDisposition2, saveInfo2);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof JobSaveDisposition)) {
            return false;
        }
        JobSaveDisposition jobSaveDisposition = (JobSaveDisposition) other;
        return Intrinsics.areEqual(this.saveDisposition, jobSaveDisposition.saveDisposition) && Intrinsics.areEqual(this.saveInfo, jobSaveDisposition.saveInfo);
    }

    public int hashCode() {
        String str = this.saveDisposition;
        int iHashCode = (str != null ? str.hashCode() : 0) * 31;
        List<SaveInfo> list = this.saveInfo;
        return iHashCode + (list != null ? list.hashCode() : 0);
    }

    @Override
    public void print(PrettyPrinter printer) {
        Intrinsics.checkNotNullParameter(printer, "printer");
        AttributeCollection.DefaultImpls.print(this, printer);
    }

    public JobSaveDisposition(String str, List<SaveInfo> list) {
        this.saveDisposition = str;
        this.saveInfo = list;
    }

    public JobSaveDisposition(String str, List list, int i, DefaultConstructorMarker defaultConstructorMarker) {
        if ((i & 1) != 0) {
            str = null;
        }
        if ((i & 2) != 0) {
            list = null;
        }
        this(str, list);
    }

    public final String getSaveDisposition() {
        return this.saveDisposition;
    }

    public final void setSaveDisposition(String str) {
        this.saveDisposition = str;
    }

    public final List<SaveInfo> getSaveInfo() {
        return this.saveInfo;
    }

    public final void setSaveInfo(List<SaveInfo> list) {
        this.saveInfo = list;
    }

    public JobSaveDisposition() {
        this(null, null);
    }

    @Override
    public List<Attribute<?>> getAttributes() {
        Attribute[] attributeArr = new Attribute[2];
        String str = this.saveDisposition;
        attributeArr[0] = str != null ? saveDisposition.mo418of(str) : null;
        List<SaveInfo> list = this.saveInfo;
        attributeArr[1] = list != null ? saveInfo.mo417of((Iterable) list) : null;
        return CollectionsKt.listOfNotNull((Object[]) attributeArr);
    }

    @Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003J\u001a\u0010\u000f\u001a\u00020\u00022\u0010\u0010\u0010\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00120\u0011H\u0016R\u0016\u0010\u0004\u001a\u00020\u00008\u0006X\u0087\u0004¢\u0006\b\n\u0000\u0012\u0004\b\u0005\u0010\u0003R\u001a\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00020\u0007X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0010\u0010\n\u001a\u00020\u000b8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\r8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000¨\u0006\u0013"}, m1293d2 = {"Lcom/hp/jipp/model/JobSaveDisposition$Companion;", "Lcom/hp/jipp/encoding/AttributeCollection$Converter;", "Lcom/hp/jipp/model/JobSaveDisposition;", "()V", "Types", "getTypes$annotations", "cls", "Ljava/lang/Class;", "getCls", "()Ljava/lang/Class;", "saveDisposition", "Lcom/hp/jipp/encoding/KeywordType;", "saveInfo", "Lcom/hp/jipp/encoding/AttributeCollection$SetType;", "Lcom/hp/jipp/model/JobSaveDisposition$SaveInfo;", "convert", "attributes", "", "Lcom/hp/jipp/encoding/Attribute;", "jipp-core"}, m1294k = 1, m1295mv = {1, 4, 0})
    public static final class Companion implements AttributeCollection.Converter<JobSaveDisposition> {
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
        public JobSaveDisposition convert(List<? extends Attribute<?>> attributes) {
            Intrinsics.checkNotNullParameter(attributes, "attributes");
            return new JobSaveDisposition((String) extractOne(attributes, JobSaveDisposition.saveDisposition), extractAll(attributes, JobSaveDisposition.saveInfo));
        }

        @Override
        public Class<JobSaveDisposition> getCls() {
            return JobSaveDisposition.cls;
        }
    }

    static {
        Companion companion = new Companion(null);
        INSTANCE = companion;
        cls = JobSaveDisposition.class;
        Types = companion;
        saveDisposition = new KeywordType(JobSaveDispositionSupported.saveDisposition);
        saveInfo = new AttributeCollection.SetType<>(JobSaveDispositionSupported.saveInfo, SaveInfo.INSTANCE);
    }

    @Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0014\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\b\u0086\b\u0018\u0000 &2\u00020\u0001:\u0001&B\u0007\b\u0016¢\u0006\u0002\u0010\u0002B)\u0012\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0004¢\u0006\u0002\u0010\bJ\u000b\u0010\u001b\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\u000b\u0010\u001c\u001a\u0004\u0018\u00010\u0006HÆ\u0003J\u000b\u0010\u001d\u001a\u0004\u0018\u00010\u0004HÆ\u0003J-\u0010\u001e\u001a\u00020\u00002\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0004HÆ\u0001J\u0013\u0010\u001f\u001a\u00020 2\b\u0010!\u001a\u0004\u0018\u00010\"HÖ\u0003J\t\u0010#\u001a\u00020$HÖ\u0001J\b\u0010%\u001a\u00020\u0004H\u0016R\u001e\u0010\t\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u000b0\n8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\rR\u001e\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u0010\n\u0002\b\u0012\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u001e\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0086\u000e¢\u0006\u0010\n\u0002\b\u0017\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R\u001e\u0010\u0007\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u0010\n\u0002\b\u001a\u001a\u0004\b\u0018\u0010\u000f\"\u0004\b\u0019\u0010\u0011¨\u0006'"}, m1293d2 = {"Lcom/hp/jipp/model/JobSaveDisposition$SaveInfo;", "Lcom/hp/jipp/encoding/AttributeCollection;", "()V", "saveDocumentFormat", "", "saveLocation", "Ljava/net/URI;", "saveName", "(Ljava/lang/String;Ljava/net/URI;Ljava/lang/String;)V", "attributes", "", "Lcom/hp/jipp/encoding/Attribute;", "getAttributes", "()Ljava/util/List;", "getSaveDocumentFormat", "()Ljava/lang/String;", "setSaveDocumentFormat", "(Ljava/lang/String;)V", "saveDocumentFormat$1", "getSaveLocation", "()Ljava/net/URI;", "setSaveLocation", "(Ljava/net/URI;)V", "saveLocation$1", "getSaveName", "setSaveName", "saveName$1", "component1", "component2", "component3", PrinterServiceType.copy, "equals", "", "other", "", "hashCode", "", "toString", "Companion", "jipp-core"}, m1294k = 1, m1295mv = {1, 4, 0})
    public static final class SaveInfo implements AttributeCollection {

        public static final Companion INSTANCE;
        public static final Companion Types;
        private static final Class<SaveInfo> cls;
        public static final StringType saveDocumentFormat;
        public static final UriType saveLocation;
        public static final NameType saveName;

        private String saveDocumentFormat;

        private URI saveLocation;

        private String saveName;

        public static SaveInfo copy$default(SaveInfo saveInfo, String str, URI uri, String str2, int i, Object obj) {
            if ((i & 1) != 0) {
                str = saveInfo.saveDocumentFormat;
            }
            if ((i & 2) != 0) {
                uri = saveInfo.saveLocation;
            }
            if ((i & 4) != 0) {
                str2 = saveInfo.saveName;
            }
            return saveInfo.copy(str, uri, str2);
        }

        public final String getSaveDocumentFormat() {
            return this.saveDocumentFormat;
        }

        public final URI getSaveLocation() {
            return this.saveLocation;
        }

        public final String getSaveName() {
            return this.saveName;
        }

        public final SaveInfo copy(String saveDocumentFormat2, URI saveLocation2, String saveName2) {
            return new SaveInfo(saveDocumentFormat2, saveLocation2, saveName2);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof SaveInfo)) {
                return false;
            }
            SaveInfo saveInfo = (SaveInfo) other;
            return Intrinsics.areEqual(this.saveDocumentFormat, saveInfo.saveDocumentFormat) && Intrinsics.areEqual(this.saveLocation, saveInfo.saveLocation) && Intrinsics.areEqual(this.saveName, saveInfo.saveName);
        }

        public int hashCode() {
            String str = this.saveDocumentFormat;
            int iHashCode = (str != null ? str.hashCode() : 0) * 31;
            URI uri = this.saveLocation;
            int iHashCode2 = (iHashCode + (uri != null ? uri.hashCode() : 0)) * 31;
            String str2 = this.saveName;
            return iHashCode2 + (str2 != null ? str2.hashCode() : 0);
        }

        @Override
        public void print(PrettyPrinter printer) {
            Intrinsics.checkNotNullParameter(printer, "printer");
            AttributeCollection.DefaultImpls.print(this, printer);
        }

        public SaveInfo(String str, URI uri, String str2) {
            this.saveDocumentFormat = str;
            this.saveLocation = uri;
            this.saveName = str2;
        }

        public SaveInfo(String str, URI uri, String str2, int i, DefaultConstructorMarker defaultConstructorMarker) {
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

        public final String getSaveDocumentFormat() {
            return this.saveDocumentFormat;
        }

        public final void setSaveDocumentFormat(String str) {
            this.saveDocumentFormat = str;
        }

        public final URI getSaveLocation() {
            return this.saveLocation;
        }

        public final void setSaveLocation(URI uri) {
            this.saveLocation = uri;
        }

        public final String getSaveName() {
            return this.saveName;
        }

        public final void setSaveName(String str) {
            this.saveName = str;
        }

        public SaveInfo() {
            this(null, null, null);
        }

        @Override
        public List<Attribute<?>> getAttributes() {
            Attribute[] attributeArr = new Attribute[3];
            String str = this.saveDocumentFormat;
            attributeArr[0] = str != null ? saveDocumentFormat.mo418of(str) : null;
            URI uri = this.saveLocation;
            attributeArr[1] = uri != null ? saveLocation.mo418of(uri) : null;
            String str2 = this.saveName;
            attributeArr[2] = str2 != null ? saveName.m440of(str2) : null;
            return CollectionsKt.listOfNotNull((Object[]) attributeArr);
        }

        @Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003J\u001a\u0010\u0010\u001a\u00020\u00022\u0010\u0010\u0011\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00130\u0012H\u0016R\u0016\u0010\u0004\u001a\u00020\u00008\u0006X\u0087\u0004¢\u0006\b\n\u0000\u0012\u0004\b\u0005\u0010\u0003R\u001a\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00020\u0007X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0010\u0010\n\u001a\u00020\u000b8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u00020\r8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u00020\u000f8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000¨\u0006\u0014"}, m1293d2 = {"Lcom/hp/jipp/model/JobSaveDisposition$SaveInfo$Companion;", "Lcom/hp/jipp/encoding/AttributeCollection$Converter;", "Lcom/hp/jipp/model/JobSaveDisposition$SaveInfo;", "()V", "Types", "getTypes$annotations", "cls", "Ljava/lang/Class;", "getCls", "()Ljava/lang/Class;", "saveDocumentFormat", "Lcom/hp/jipp/encoding/StringType;", "saveLocation", "Lcom/hp/jipp/encoding/UriType;", "saveName", "Lcom/hp/jipp/encoding/NameType;", "convert", "attributes", "", "Lcom/hp/jipp/encoding/Attribute;", "jipp-core"}, m1294k = 1, m1295mv = {1, 4, 0})
        public static final class Companion implements AttributeCollection.Converter<SaveInfo> {
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
            public SaveInfo convert(List<? extends Attribute<?>> attributes) {
                Intrinsics.checkNotNullParameter(attributes, "attributes");
                String str = (String) extractOne(attributes, SaveInfo.saveDocumentFormat);
                URI uri = (URI) extractOne(attributes, SaveInfo.saveLocation);
                Name name = (Name) extractOne(attributes, SaveInfo.saveName);
                return new SaveInfo(str, uri, name != null ? name.getValue() : null);
            }

            @Override
            public Class<SaveInfo> getCls() {
                return SaveInfo.cls;
            }
        }

        static {
            Companion companion = new Companion(null);
            INSTANCE = companion;
            cls = SaveInfo.class;
            Types = companion;
            saveDocumentFormat = new StringType(Tag.mimeMediaType, SaveInfoSupported.saveDocumentFormat);
            saveLocation = new UriType(SaveInfoSupported.saveLocation);
            saveName = new NameType(SaveInfoSupported.saveName);
        }

        public String toString() {
            return "SaveInfo(" + CollectionsKt.joinToString$default(getAttributes(), null, null, null, 0, null, null, 63, null) + ')';
        }
    }

    public String toString() {
        return "JobSaveDisposition(" + CollectionsKt.joinToString$default(getAttributes(), null, null, null, 0, null, null, 63, null) + ')';
    }
}
