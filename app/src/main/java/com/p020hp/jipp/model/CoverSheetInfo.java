package com.p020hp.jipp.model;

import com.p020hp.jipp.encoding.Attribute;
import com.p020hp.jipp.encoding.AttributeCollection;
import com.p020hp.jipp.encoding.AttributeType;
import com.p020hp.jipp.encoding.Text;
import com.p020hp.jipp.encoding.TextType;
import com.p020hp.jipp.encoding.UriType;
import com.p020hp.jipp.util.PrettyPrinter;
import java.net.URI;
import java.util.List;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b \n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\b\u0086\b\u0018\u0000 52\u00020\u0001:\u00015B\u0007\b\u0016¢\u0006\u0002\u0010\u0002BM\u0012\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u0004¢\u0006\u0002\u0010\u000bJ\u000b\u0010'\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\u000b\u0010(\u001a\u0004\u0018\u00010\u0006HÆ\u0003J\u000b\u0010)\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\u000b\u0010*\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\u000b\u0010+\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\u000b\u0010,\u001a\u0004\u0018\u00010\u0004HÆ\u0003JQ\u0010-\u001a\u00020\u00002\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u0004HÆ\u0001J\u0013\u0010.\u001a\u00020/2\b\u00100\u001a\u0004\u0018\u000101HÖ\u0003J\t\u00102\u001a\u000203HÖ\u0001J\b\u00104\u001a\u00020\u0004H\u0016R\u001e\u0010\f\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u000e0\r8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0010R\u001e\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u0010\n\u0002\b\u0015\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\u001e\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0086\u000e¢\u0006\u0010\n\u0002\b\u001a\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019R\u001e\u0010\u0007\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u0010\n\u0002\b\u001d\u001a\u0004\b\u001b\u0010\u0012\"\u0004\b\u001c\u0010\u0014R\u001e\u0010\b\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u0010\n\u0002\b \u001a\u0004\b\u001e\u0010\u0012\"\u0004\b\u001f\u0010\u0014R\u001e\u0010\t\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u0010\n\u0002\b#\u001a\u0004\b!\u0010\u0012\"\u0004\b\"\u0010\u0014R\u001e\u0010\n\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u0010\n\u0002\b&\u001a\u0004\b$\u0010\u0012\"\u0004\b%\u0010\u0014¨\u00066"}, m1293d2 = {"Lcom/hp/jipp/model/CoverSheetInfo;", "Lcom/hp/jipp/encoding/AttributeCollection;", "()V", "fromName", "", "logo", "Ljava/net/URI;", "message", "organizationName", "subject", "toName", "(Ljava/lang/String;Ljava/net/URI;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "attributes", "", "Lcom/hp/jipp/encoding/Attribute;", "getAttributes", "()Ljava/util/List;", "getFromName", "()Ljava/lang/String;", "setFromName", "(Ljava/lang/String;)V", "fromName$1", "getLogo", "()Ljava/net/URI;", "setLogo", "(Ljava/net/URI;)V", "logo$1", "getMessage", "setMessage", "message$1", "getOrganizationName", "setOrganizationName", "organizationName$1", "getSubject", "setSubject", "subject$1", "getToName", "setToName", "toName$1", "component1", "component2", "component3", "component4", "component5", "component6", PrinterServiceType.copy, "equals", "", "other", "", "hashCode", "", "toString", "Companion", "jipp-core"}, m1294k = 1, m1295mv = {1, 4, 0})
public final class CoverSheetInfo implements AttributeCollection {

    public static final Companion INSTANCE;
    public static final Companion Types;
    private static final Class<CoverSheetInfo> cls;
    public static final TextType fromName;
    public static final UriType logo;
    public static final TextType message;
    public static final TextType organizationName;
    public static final TextType subject;
    public static final TextType toName;

    private String fromName;

    private URI logo;

    private String message;

    private String organizationName;

    private String subject;

    private String toName;

    public static CoverSheetInfo copy$default(CoverSheetInfo coverSheetInfo, String str, URI uri, String str2, String str3, String str4, String str5, int i, Object obj) {
        if ((i & 1) != 0) {
            str = coverSheetInfo.fromName;
        }
        if ((i & 2) != 0) {
            uri = coverSheetInfo.logo;
        }
        URI uri2 = uri;
        if ((i & 4) != 0) {
            str2 = coverSheetInfo.message;
        }
        String str6 = str2;
        if ((i & 8) != 0) {
            str3 = coverSheetInfo.organizationName;
        }
        String str7 = str3;
        if ((i & 16) != 0) {
            str4 = coverSheetInfo.subject;
        }
        String str8 = str4;
        if ((i & 32) != 0) {
            str5 = coverSheetInfo.toName;
        }
        return coverSheetInfo.copy(str, uri2, str6, str7, str8, str5);
    }

    public final String getFromName() {
        return this.fromName;
    }

    public final URI getLogo() {
        return this.logo;
    }

    public final String getMessage() {
        return this.message;
    }

    public final String getOrganizationName() {
        return this.organizationName;
    }

    public final String getSubject() {
        return this.subject;
    }

    public final String getToName() {
        return this.toName;
    }

    public final CoverSheetInfo copy(String fromName2, URI logo2, String message2, String organizationName2, String subject2, String toName2) {
        return new CoverSheetInfo(fromName2, logo2, message2, organizationName2, subject2, toName2);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof CoverSheetInfo)) {
            return false;
        }
        CoverSheetInfo coverSheetInfo = (CoverSheetInfo) other;
        return Intrinsics.areEqual(this.fromName, coverSheetInfo.fromName) && Intrinsics.areEqual(this.logo, coverSheetInfo.logo) && Intrinsics.areEqual(this.message, coverSheetInfo.message) && Intrinsics.areEqual(this.organizationName, coverSheetInfo.organizationName) && Intrinsics.areEqual(this.subject, coverSheetInfo.subject) && Intrinsics.areEqual(this.toName, coverSheetInfo.toName);
    }

    public int hashCode() {
        String str = this.fromName;
        int iHashCode = (str != null ? str.hashCode() : 0) * 31;
        URI uri = this.logo;
        int iHashCode2 = (iHashCode + (uri != null ? uri.hashCode() : 0)) * 31;
        String str2 = this.message;
        int iHashCode3 = (iHashCode2 + (str2 != null ? str2.hashCode() : 0)) * 31;
        String str3 = this.organizationName;
        int iHashCode4 = (iHashCode3 + (str3 != null ? str3.hashCode() : 0)) * 31;
        String str4 = this.subject;
        int iHashCode5 = (iHashCode4 + (str4 != null ? str4.hashCode() : 0)) * 31;
        String str5 = this.toName;
        return iHashCode5 + (str5 != null ? str5.hashCode() : 0);
    }

    @Override
    public void print(PrettyPrinter printer) {
        Intrinsics.checkNotNullParameter(printer, "printer");
        AttributeCollection.DefaultImpls.print(this, printer);
    }

    public CoverSheetInfo(String str, URI uri, String str2, String str3, String str4, String str5) {
        this.fromName = str;
        this.logo = uri;
        this.message = str2;
        this.organizationName = str3;
        this.subject = str4;
        this.toName = str5;
    }

    public CoverSheetInfo(String str, URI uri, String str2, String str3, String str4, String str5, int i, DefaultConstructorMarker defaultConstructorMarker) {
        URI uri2;
        String str6;
        String str7;
        String str8;
        String str9 = null;
        if ((i & 1) != 0) {
            str = null;
        }
        if ((i & 2) != 0) {
            uri2 = null;
        } else {
            uri2 = uri;
        }
        if ((i & 4) != 0) {
            str6 = null;
        } else {
            str6 = str2;
        }
        if ((i & 8) != 0) {
            str7 = null;
        } else {
            str7 = str3;
        }
        if ((i & 16) != 0) {
            str8 = null;
        } else {
            str8 = str4;
        }
        if ((i & 32) != 0) {
        } else {
            str9 = str5;
        }
        this(str, uri2, str6, str7, str8, str9);
    }

    public final String getFromName() {
        return this.fromName;
    }

    public final void setFromName(String str) {
        this.fromName = str;
    }

    public final URI getLogo() {
        return this.logo;
    }

    public final void setLogo(URI uri) {
        this.logo = uri;
    }

    public final String getMessage() {
        return this.message;
    }

    public final void setMessage(String str) {
        this.message = str;
    }

    public final String getOrganizationName() {
        return this.organizationName;
    }

    public final void setOrganizationName(String str) {
        this.organizationName = str;
    }

    public final String getSubject() {
        return this.subject;
    }

    public final void setSubject(String str) {
        this.subject = str;
    }

    public final String getToName() {
        return this.toName;
    }

    public final void setToName(String str) {
        this.toName = str;
    }

    public CoverSheetInfo() {
        this(null, null, null, null, null, null);
    }

    @Override
    public List<Attribute<?>> getAttributes() {
        Attribute[] attributeArr = new Attribute[6];
        String str = this.fromName;
        attributeArr[0] = str != null ? fromName.m442of(str) : null;
        URI uri = this.logo;
        attributeArr[1] = uri != null ? logo.mo418of(uri) : null;
        String str2 = this.message;
        attributeArr[2] = str2 != null ? message.m442of(str2) : null;
        String str3 = this.organizationName;
        attributeArr[3] = str3 != null ? organizationName.m442of(str3) : null;
        String str4 = this.subject;
        attributeArr[4] = str4 != null ? subject.m442of(str4) : null;
        String str5 = this.toName;
        attributeArr[5] = str5 != null ? toName.m442of(str5) : null;
        return CollectionsKt.listOfNotNull((Object[]) attributeArr);
    }

    @Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003J\u001a\u0010\u0012\u001a\u00020\u00022\u0010\u0010\u0013\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00150\u0014H\u0016R\u0016\u0010\u0004\u001a\u00020\u00008\u0006X\u0087\u0004¢\u0006\b\n\u0000\u0012\u0004\b\u0005\u0010\u0003R\u001a\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00020\u0007X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0010\u0010\n\u001a\u00020\u000b8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u00020\r8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u00020\u000b8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u000f\u001a\u00020\u000b8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0010\u001a\u00020\u000b8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0011\u001a\u00020\u000b8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000¨\u0006\u0016"}, m1293d2 = {"Lcom/hp/jipp/model/CoverSheetInfo$Companion;", "Lcom/hp/jipp/encoding/AttributeCollection$Converter;", "Lcom/hp/jipp/model/CoverSheetInfo;", "()V", "Types", "getTypes$annotations", "cls", "Ljava/lang/Class;", "getCls", "()Ljava/lang/Class;", "fromName", "Lcom/hp/jipp/encoding/TextType;", "logo", "Lcom/hp/jipp/encoding/UriType;", "message", "organizationName", "subject", "toName", "convert", "attributes", "", "Lcom/hp/jipp/encoding/Attribute;", "jipp-core"}, m1294k = 1, m1295mv = {1, 4, 0})
    public static final class Companion implements AttributeCollection.Converter<CoverSheetInfo> {
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
        public CoverSheetInfo convert(List<? extends Attribute<?>> attributes) {
            Intrinsics.checkNotNullParameter(attributes, "attributes");
            Text text = (Text) extractOne(attributes, CoverSheetInfo.fromName);
            String value = text != null ? text.getValue() : null;
            URI uri = (URI) extractOne(attributes, CoverSheetInfo.logo);
            Text text2 = (Text) extractOne(attributes, CoverSheetInfo.message);
            String value2 = text2 != null ? text2.getValue() : null;
            Text text3 = (Text) extractOne(attributes, CoverSheetInfo.organizationName);
            String value3 = text3 != null ? text3.getValue() : null;
            Text text4 = (Text) extractOne(attributes, CoverSheetInfo.subject);
            String value4 = text4 != null ? text4.getValue() : null;
            Text text5 = (Text) extractOne(attributes, CoverSheetInfo.toName);
            return new CoverSheetInfo(value, uri, value2, value3, value4, text5 != null ? text5.getValue() : null);
        }

        @Override
        public Class<CoverSheetInfo> getCls() {
            return CoverSheetInfo.cls;
        }
    }

    static {
        Companion companion = new Companion(null);
        INSTANCE = companion;
        cls = CoverSheetInfo.class;
        Types = companion;
        fromName = new TextType("from-name");
        logo = new UriType("logo");
        message = new TextType("message");
        organizationName = new TextType("organization-name");
        subject = new TextType("subject");
        toName = new TextType("to-name");
    }

    public String toString() {
        return "CoverSheetInfo(" + CollectionsKt.joinToString$default(getAttributes(), null, null, null, 0, null, null, 63, null) + ')';
    }
}
