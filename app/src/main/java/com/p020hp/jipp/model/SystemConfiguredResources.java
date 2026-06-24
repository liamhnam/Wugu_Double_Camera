package com.p020hp.jipp.model;

import com.p020hp.jipp.encoding.Attribute;
import com.p020hp.jipp.encoding.AttributeCollection;
import com.p020hp.jipp.encoding.AttributeType;
import com.p020hp.jipp.encoding.IntType;
import com.p020hp.jipp.encoding.KeywordType;
import com.p020hp.jipp.encoding.Name;
import com.p020hp.jipp.encoding.NameType;
import com.p020hp.jipp.encoding.StringType;
import com.p020hp.jipp.encoding.Tag;
import com.p020hp.jipp.encoding.Text;
import com.p020hp.jipp.encoding.TextType;
import com.p020hp.jipp.model.ResourceState;
import com.p020hp.jipp.util.PrettyPrinter;
import java.util.List;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b$\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0004\b\u0086\b\u0018\u0000 92\u00020\u0001:\u00019B\u0007\b\u0016¢\u0006\u0002\u0010\u0002BM\u0012\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u0004¢\u0006\u0002\u0010\fJ\u000b\u0010+\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\u0010\u0010,\u001a\u0004\u0018\u00010\u0006HÆ\u0003¢\u0006\u0002\u0010\u0018J\u000b\u0010-\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\u000b\u0010.\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\u000b\u0010/\u001a\u0004\u0018\u00010\nHÆ\u0003J\u000b\u00100\u001a\u0004\u0018\u00010\u0004HÆ\u0003JV\u00101\u001a\u00020\u00002\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n2\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u0004HÆ\u0001¢\u0006\u0002\u00102J\u0013\u00103\u001a\u0002042\b\u00105\u001a\u0004\u0018\u000106HÖ\u0003J\t\u00107\u001a\u00020\u0006HÖ\u0001J\b\u00108\u001a\u00020\u0004H\u0016R\u001e\u0010\r\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u000f0\u000e8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u0011R\u001e\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u0010\n\u0002\b\u0016\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R \u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u001b\u0010\u001c\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001aR\u001e\u0010\u0007\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u0010\n\u0002\b\u001f\u001a\u0004\b\u001d\u0010\u0013\"\u0004\b\u001e\u0010\u0015R\u001e\u0010\b\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u0010\n\u0002\b\"\u001a\u0004\b \u0010\u0013\"\u0004\b!\u0010\u0015R\u001e\u0010\t\u001a\u0004\u0018\u00010\nX\u0086\u000e¢\u0006\u0010\n\u0002\b'\u001a\u0004\b#\u0010$\"\u0004\b%\u0010&R\u001e\u0010\u000b\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u0010\n\u0002\b*\u001a\u0004\b(\u0010\u0013\"\u0004\b)\u0010\u0015¨\u0006:"}, m1293d2 = {"Lcom/hp/jipp/model/SystemConfiguredResources;", "Lcom/hp/jipp/encoding/AttributeCollection;", "()V", "resourceFormat", "", "resourceId", "", "resourceInfo", "resourceName", "resourceState", "Lcom/hp/jipp/model/ResourceState;", "resourceType", "(Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Lcom/hp/jipp/model/ResourceState;Ljava/lang/String;)V", "attributes", "", "Lcom/hp/jipp/encoding/Attribute;", "getAttributes", "()Ljava/util/List;", "getResourceFormat", "()Ljava/lang/String;", "setResourceFormat", "(Ljava/lang/String;)V", "resourceFormat$1", "getResourceId", "()Ljava/lang/Integer;", "setResourceId", "(Ljava/lang/Integer;)V", "resourceId$1", "Ljava/lang/Integer;", "getResourceInfo", "setResourceInfo", "resourceInfo$1", "getResourceName", "setResourceName", "resourceName$1", "getResourceState", "()Lcom/hp/jipp/model/ResourceState;", "setResourceState", "(Lcom/hp/jipp/model/ResourceState;)V", "resourceState$1", "getResourceType", "setResourceType", "resourceType$1", "component1", "component2", "component3", "component4", "component5", "component6", PrinterServiceType.copy, "(Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Lcom/hp/jipp/model/ResourceState;Ljava/lang/String;)Lcom/hp/jipp/model/SystemConfiguredResources;", "equals", "", "other", "", "hashCode", "toString", "Companion", "jipp-core"}, m1294k = 1, m1295mv = {1, 4, 0})
public final class SystemConfiguredResources implements AttributeCollection {

    public static final Companion INSTANCE;
    public static final Companion Types;
    private static final Class<SystemConfiguredResources> cls;
    public static final StringType resourceFormat;
    public static final IntType resourceId;
    public static final TextType resourceInfo;
    public static final NameType resourceName;
    public static final ResourceState.Type resourceState;
    public static final KeywordType resourceType;

    private String resourceFormat;

    private Integer resourceId;

    private String resourceInfo;

    private String resourceName;

    private ResourceState resourceState;

    private String resourceType;

    public static SystemConfiguredResources copy$default(SystemConfiguredResources systemConfiguredResources, String str, Integer num, String str2, String str3, ResourceState resourceState2, String str4, int i, Object obj) {
        if ((i & 1) != 0) {
            str = systemConfiguredResources.resourceFormat;
        }
        if ((i & 2) != 0) {
            num = systemConfiguredResources.resourceId;
        }
        Integer num2 = num;
        if ((i & 4) != 0) {
            str2 = systemConfiguredResources.resourceInfo;
        }
        String str5 = str2;
        if ((i & 8) != 0) {
            str3 = systemConfiguredResources.resourceName;
        }
        String str6 = str3;
        if ((i & 16) != 0) {
            resourceState2 = systemConfiguredResources.resourceState;
        }
        ResourceState resourceState3 = resourceState2;
        if ((i & 32) != 0) {
            str4 = systemConfiguredResources.resourceType;
        }
        return systemConfiguredResources.copy(str, num2, str5, str6, resourceState3, str4);
    }

    public final String getResourceFormat() {
        return this.resourceFormat;
    }

    public final Integer getResourceId() {
        return this.resourceId;
    }

    public final String getResourceInfo() {
        return this.resourceInfo;
    }

    public final String getResourceName() {
        return this.resourceName;
    }

    public final ResourceState getResourceState() {
        return this.resourceState;
    }

    public final String getResourceType() {
        return this.resourceType;
    }

    public final SystemConfiguredResources copy(String resourceFormat2, Integer resourceId2, String resourceInfo2, String resourceName2, ResourceState resourceState2, String resourceType2) {
        return new SystemConfiguredResources(resourceFormat2, resourceId2, resourceInfo2, resourceName2, resourceState2, resourceType2);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof SystemConfiguredResources)) {
            return false;
        }
        SystemConfiguredResources systemConfiguredResources = (SystemConfiguredResources) other;
        return Intrinsics.areEqual(this.resourceFormat, systemConfiguredResources.resourceFormat) && Intrinsics.areEqual(this.resourceId, systemConfiguredResources.resourceId) && Intrinsics.areEqual(this.resourceInfo, systemConfiguredResources.resourceInfo) && Intrinsics.areEqual(this.resourceName, systemConfiguredResources.resourceName) && Intrinsics.areEqual(this.resourceState, systemConfiguredResources.resourceState) && Intrinsics.areEqual(this.resourceType, systemConfiguredResources.resourceType);
    }

    public int hashCode() {
        String str = this.resourceFormat;
        int iHashCode = (str != null ? str.hashCode() : 0) * 31;
        Integer num = this.resourceId;
        int iHashCode2 = (iHashCode + (num != null ? num.hashCode() : 0)) * 31;
        String str2 = this.resourceInfo;
        int iHashCode3 = (iHashCode2 + (str2 != null ? str2.hashCode() : 0)) * 31;
        String str3 = this.resourceName;
        int iHashCode4 = (iHashCode3 + (str3 != null ? str3.hashCode() : 0)) * 31;
        ResourceState resourceState2 = this.resourceState;
        int iHashCode5 = (iHashCode4 + (resourceState2 != null ? resourceState2.hashCode() : 0)) * 31;
        String str4 = this.resourceType;
        return iHashCode5 + (str4 != null ? str4.hashCode() : 0);
    }

    @Override
    public void print(PrettyPrinter printer) {
        Intrinsics.checkNotNullParameter(printer, "printer");
        AttributeCollection.DefaultImpls.print(this, printer);
    }

    public SystemConfiguredResources(String str, Integer num, String str2, String str3, ResourceState resourceState2, String str4) {
        this.resourceFormat = str;
        this.resourceId = num;
        this.resourceInfo = str2;
        this.resourceName = str3;
        this.resourceState = resourceState2;
        this.resourceType = str4;
    }

    public SystemConfiguredResources(String str, Integer num, String str2, String str3, ResourceState resourceState2, String str4, int i, DefaultConstructorMarker defaultConstructorMarker) {
        Integer num2;
        String str5;
        String str6;
        ResourceState resourceState3;
        String str7 = null;
        if ((i & 1) != 0) {
            str = null;
        }
        if ((i & 2) != 0) {
            num2 = null;
        } else {
            num2 = num;
        }
        if ((i & 4) != 0) {
            str5 = null;
        } else {
            str5 = str2;
        }
        if ((i & 8) != 0) {
            str6 = null;
        } else {
            str6 = str3;
        }
        if ((i & 16) != 0) {
            resourceState3 = null;
        } else {
            resourceState3 = resourceState2;
        }
        if ((i & 32) != 0) {
        } else {
            str7 = str4;
        }
        this(str, num2, str5, str6, resourceState3, str7);
    }

    public final String getResourceFormat() {
        return this.resourceFormat;
    }

    public final void setResourceFormat(String str) {
        this.resourceFormat = str;
    }

    public final Integer getResourceId() {
        return this.resourceId;
    }

    public final void setResourceId(Integer num) {
        this.resourceId = num;
    }

    public final String getResourceInfo() {
        return this.resourceInfo;
    }

    public final void setResourceInfo(String str) {
        this.resourceInfo = str;
    }

    public final String getResourceName() {
        return this.resourceName;
    }

    public final void setResourceName(String str) {
        this.resourceName = str;
    }

    public final ResourceState getResourceState() {
        return this.resourceState;
    }

    public final void setResourceState(ResourceState resourceState2) {
        this.resourceState = resourceState2;
    }

    public final String getResourceType() {
        return this.resourceType;
    }

    public final void setResourceType(String str) {
        this.resourceType = str;
    }

    public SystemConfiguredResources() {
        this(null, null, null, null, null, null);
    }

    @Override
    public List<Attribute<?>> getAttributes() {
        Attribute[] attributeArr = new Attribute[6];
        String str = this.resourceFormat;
        attributeArr[0] = str != null ? resourceFormat.mo418of(str) : null;
        Integer num = this.resourceId;
        attributeArr[1] = num != null ? resourceId.mo418of(Integer.valueOf(num.intValue())) : null;
        String str2 = this.resourceInfo;
        attributeArr[2] = str2 != null ? resourceInfo.m442of(str2) : null;
        String str3 = this.resourceName;
        attributeArr[3] = str3 != null ? resourceName.m440of(str3) : null;
        ResourceState resourceState2 = this.resourceState;
        attributeArr[4] = resourceState2 != null ? resourceState.mo418of((Object) resourceState2) : null;
        String str4 = this.resourceType;
        attributeArr[5] = str4 != null ? resourceType.mo418of(str4) : null;
        return CollectionsKt.listOfNotNull((Object[]) attributeArr);
    }

    @Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003J\u001a\u0010\u0016\u001a\u00020\u00022\u0010\u0010\u0017\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00190\u0018H\u0016R\u0016\u0010\u0004\u001a\u00020\u00008\u0006X\u0087\u0004¢\u0006\b\n\u0000\u0012\u0004\b\u0005\u0010\u0003R\u001a\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00020\u0007X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0010\u0010\n\u001a\u00020\u000b8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u00020\r8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u00020\u000f8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0010\u001a\u00020\u00118\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0012\u001a\u00020\u00138\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0014\u001a\u00020\u00158\u0006X\u0087\u0004¢\u0006\u0002\n\u0000¨\u0006\u001a"}, m1293d2 = {"Lcom/hp/jipp/model/SystemConfiguredResources$Companion;", "Lcom/hp/jipp/encoding/AttributeCollection$Converter;", "Lcom/hp/jipp/model/SystemConfiguredResources;", "()V", "Types", "getTypes$annotations", "cls", "Ljava/lang/Class;", "getCls", "()Ljava/lang/Class;", "resourceFormat", "Lcom/hp/jipp/encoding/StringType;", "resourceId", "Lcom/hp/jipp/encoding/IntType;", "resourceInfo", "Lcom/hp/jipp/encoding/TextType;", "resourceName", "Lcom/hp/jipp/encoding/NameType;", "resourceState", "Lcom/hp/jipp/model/ResourceState$Type;", "resourceType", "Lcom/hp/jipp/encoding/KeywordType;", "convert", "attributes", "", "Lcom/hp/jipp/encoding/Attribute;", "jipp-core"}, m1294k = 1, m1295mv = {1, 4, 0})
    public static final class Companion implements AttributeCollection.Converter<SystemConfiguredResources> {
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
        public SystemConfiguredResources convert(List<? extends Attribute<?>> attributes) {
            Intrinsics.checkNotNullParameter(attributes, "attributes");
            String str = (String) extractOne(attributes, SystemConfiguredResources.resourceFormat);
            Integer num = (Integer) extractOne(attributes, SystemConfiguredResources.resourceId);
            Text text = (Text) extractOne(attributes, SystemConfiguredResources.resourceInfo);
            String value = text != null ? text.getValue() : null;
            Name name = (Name) extractOne(attributes, SystemConfiguredResources.resourceName);
            return new SystemConfiguredResources(str, num, value, name != null ? name.getValue() : null, (ResourceState) extractOne(attributes, SystemConfiguredResources.resourceState), (String) extractOne(attributes, SystemConfiguredResources.resourceType));
        }

        @Override
        public Class<SystemConfiguredResources> getCls() {
            return SystemConfiguredResources.cls;
        }
    }

    static {
        Companion companion = new Companion(null);
        INSTANCE = companion;
        cls = SystemConfiguredResources.class;
        Types = companion;
        resourceFormat = new StringType(Tag.mimeMediaType, "resource-format");
        resourceId = new IntType("resource-id");
        resourceInfo = new TextType("resource-info");
        resourceName = new NameType("resource-name");
        resourceState = new ResourceState.Type("resource-state");
        resourceType = new KeywordType("resource-type");
    }

    public String toString() {
        return "SystemConfiguredResources(" + CollectionsKt.joinToString$default(getAttributes(), null, null, null, 0, null, null, 63, null) + ')';
    }
}
