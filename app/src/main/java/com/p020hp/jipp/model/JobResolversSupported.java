package com.p020hp.jipp.model;

import com.p020hp.jipp.encoding.Attribute;
import com.p020hp.jipp.encoding.AttributeCollection;
import com.p020hp.jipp.encoding.AttributeType;
import com.p020hp.jipp.encoding.Name;
import com.p020hp.jipp.encoding.NameType;
import com.p020hp.jipp.util.PrettyPrinter;
import java.util.List;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\b\u0086\b\u0018\u0000 \u00182\u00020\u0001:\u0001\u0018B\u0007\b\u0016¢\u0006\u0002\u0010\u0002B\u0011\u0012\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u0004¢\u0006\u0002\u0010\u0005J\u000b\u0010\u000f\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\u0015\u0010\u0010\u001a\u00020\u00002\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u0004HÆ\u0001J\u0013\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014HÖ\u0003J\t\u0010\u0015\u001a\u00020\u0016HÖ\u0001J\b\u0010\u0017\u001a\u00020\u0004H\u0016R\u001e\u0010\u0006\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\b0\u00078VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\t\u0010\nR\u001e\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u0010\n\u0002\b\u000e\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u0005¨\u0006\u0019"}, m1293d2 = {"Lcom/hp/jipp/model/JobResolversSupported;", "Lcom/hp/jipp/encoding/AttributeCollection;", "()V", "resolverName", "", "(Ljava/lang/String;)V", "attributes", "", "Lcom/hp/jipp/encoding/Attribute;", "getAttributes", "()Ljava/util/List;", "getResolverName", "()Ljava/lang/String;", "setResolverName", "resolverName$1", "component1", PrinterServiceType.copy, "equals", "", "other", "", "hashCode", "", "toString", "Companion", "jipp-core"}, m1294k = 1, m1295mv = {1, 4, 0})
public final class JobResolversSupported implements AttributeCollection {

    public static final Companion INSTANCE;
    public static final Companion Types;
    private static final Class<JobResolversSupported> cls;
    public static final NameType resolverName;

    private String resolverName;

    public static JobResolversSupported copy$default(JobResolversSupported jobResolversSupported, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            str = jobResolversSupported.resolverName;
        }
        return jobResolversSupported.copy(str);
    }

    public final String getResolverName() {
        return this.resolverName;
    }

    public final JobResolversSupported copy(String resolverName2) {
        return new JobResolversSupported(resolverName2);
    }

    public boolean equals(Object other) {
        if (this != other) {
            return (other instanceof JobResolversSupported) && Intrinsics.areEqual(this.resolverName, ((JobResolversSupported) other).resolverName);
        }
        return true;
    }

    public int hashCode() {
        String str = this.resolverName;
        if (str != null) {
            return str.hashCode();
        }
        return 0;
    }

    @Override
    public void print(PrettyPrinter printer) {
        Intrinsics.checkNotNullParameter(printer, "printer");
        AttributeCollection.DefaultImpls.print(this, printer);
    }

    public JobResolversSupported(String str) {
        this.resolverName = str;
    }

    public JobResolversSupported(String str, int i, DefaultConstructorMarker defaultConstructorMarker) {
        if ((i & 1) != 0) {
            str = null;
        }
        this(str);
    }

    public final String getResolverName() {
        return this.resolverName;
    }

    public final void setResolverName(String str) {
        this.resolverName = str;
    }

    public JobResolversSupported() {
        this(null);
    }

    @Override
    public List<Attribute<?>> getAttributes() {
        String str = this.resolverName;
        return CollectionsKt.listOfNotNull(str != null ? resolverName.m440of(str) : null);
    }

    @Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003J\u001a\u0010\f\u001a\u00020\u00022\u0010\u0010\r\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u000f0\u000eH\u0016R\u0016\u0010\u0004\u001a\u00020\u00008\u0006X\u0087\u0004¢\u0006\b\n\u0000\u0012\u0004\b\u0005\u0010\u0003R\u001a\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00020\u0007X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0010\u0010\n\u001a\u00020\u000b8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000¨\u0006\u0010"}, m1293d2 = {"Lcom/hp/jipp/model/JobResolversSupported$Companion;", "Lcom/hp/jipp/encoding/AttributeCollection$Converter;", "Lcom/hp/jipp/model/JobResolversSupported;", "()V", "Types", "getTypes$annotations", "cls", "Ljava/lang/Class;", "getCls", "()Ljava/lang/Class;", "resolverName", "Lcom/hp/jipp/encoding/NameType;", "convert", "attributes", "", "Lcom/hp/jipp/encoding/Attribute;", "jipp-core"}, m1294k = 1, m1295mv = {1, 4, 0})
    public static final class Companion implements AttributeCollection.Converter<JobResolversSupported> {
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
        public JobResolversSupported convert(List<? extends Attribute<?>> attributes) {
            Intrinsics.checkNotNullParameter(attributes, "attributes");
            Name name = (Name) extractOne(attributes, JobResolversSupported.resolverName);
            return new JobResolversSupported(name != null ? name.getValue() : null);
        }

        @Override
        public Class<JobResolversSupported> getCls() {
            return JobResolversSupported.cls;
        }
    }

    static {
        Companion companion = new Companion(null);
        INSTANCE = companion;
        cls = JobResolversSupported.class;
        Types = companion;
        resolverName = new NameType("resolver-name");
    }

    public String toString() {
        return "JobResolversSupported(" + CollectionsKt.joinToString$default(getAttributes(), null, null, null, 0, null, null, 63, null) + ')';
    }
}
