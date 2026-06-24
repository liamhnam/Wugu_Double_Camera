package com.p020hp.jipp.model;

import com.p020hp.jipp.encoding.Attribute;
import com.p020hp.jipp.encoding.AttributeCollection;
import com.p020hp.jipp.encoding.AttributeType;
import com.p020hp.jipp.encoding.OctetsType;
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

@Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0010\u0012\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0019\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\b\u0086\b\u0018\u0000 22\u00020\u0001:\u00012B\u0007\b\u0016¢\u0006\u0002\u0010\u0002BG\u0012\u0010\b\u0002\u0010\u0003\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0004\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\t\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\t¢\u0006\u0002\u0010\fJ\u0011\u0010%\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0004HÆ\u0003J\u000b\u0010&\u001a\u0004\u0018\u00010\u0007HÆ\u0003J\u000b\u0010'\u001a\u0004\u0018\u00010\tHÆ\u0003J\u000b\u0010(\u001a\u0004\u0018\u00010\tHÆ\u0003J\u000b\u0010)\u001a\u0004\u0018\u00010\tHÆ\u0003JK\u0010*\u001a\u00020\u00002\u0010\b\u0002\u0010\u0003\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u00042\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\tHÆ\u0001J\u0013\u0010+\u001a\u00020,2\b\u0010-\u001a\u0004\u0018\u00010.HÖ\u0003J\t\u0010/\u001a\u000200HÖ\u0001J\b\u00101\u001a\u00020\tH\u0016R$\u0010\u0003\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0004X\u0086\u000e¢\u0006\u0010\n\u0002\b\u0011\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u001e\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0086\u000e¢\u0006\u0010\n\u0002\b\u0016\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R\u001e\u0010\b\u001a\u0004\u0018\u00010\tX\u0086\u000e¢\u0006\u0010\n\u0002\b\u001b\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001aR\u001e\u0010\n\u001a\u0004\u0018\u00010\tX\u0086\u000e¢\u0006\u0010\n\u0002\b\u001e\u001a\u0004\b\u001c\u0010\u0018\"\u0004\b\u001d\u0010\u001aR\u001e\u0010\u000b\u001a\u0004\u0018\u00010\tX\u0086\u000e¢\u0006\u0010\n\u0002\b!\u001a\u0004\b\u001f\u0010\u0018\"\u0004\b \u0010\u001aR\u001e\u0010\"\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030#0\u00048VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b$\u0010\u000e¨\u00063"}, m1293d2 = {"Lcom/hp/jipp/model/DestinationAccesses;", "Lcom/hp/jipp/encoding/AttributeCollection;", "()V", "accessOauthToken", "", "", "accessOauthUri", "Ljava/net/URI;", "accessPassword", "", "accessPin", "accessUserName", "(Ljava/util/List;Ljava/net/URI;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getAccessOauthToken", "()Ljava/util/List;", "setAccessOauthToken", "(Ljava/util/List;)V", "accessOauthToken$1", "getAccessOauthUri", "()Ljava/net/URI;", "setAccessOauthUri", "(Ljava/net/URI;)V", "accessOauthUri$1", "getAccessPassword", "()Ljava/lang/String;", "setAccessPassword", "(Ljava/lang/String;)V", "accessPassword$1", "getAccessPin", "setAccessPin", "accessPin$1", "getAccessUserName", "setAccessUserName", "accessUserName$1", "attributes", "Lcom/hp/jipp/encoding/Attribute;", "getAttributes", "component1", "component2", "component3", "component4", "component5", PrinterServiceType.copy, "equals", "", "other", "", "hashCode", "", "toString", "Companion", "jipp-core"}, m1294k = 1, m1295mv = {1, 4, 0})
public final class DestinationAccesses implements AttributeCollection {

    public static final Companion INSTANCE;
    public static final Companion Types;
    public static final OctetsType.Set accessOauthToken;
    public static final UriType accessOauthUri;
    public static final TextType accessPassword;
    public static final TextType accessPin;
    public static final TextType accessUserName;
    private static final Class<DestinationAccesses> cls;

    private List<byte[]> accessOauthToken;

    private URI accessOauthUri;

    private String accessPassword;

    private String accessPin;

    private String accessUserName;

    public static DestinationAccesses copy$default(DestinationAccesses destinationAccesses, List list, URI uri, String str, String str2, String str3, int i, Object obj) {
        if ((i & 1) != 0) {
            list = destinationAccesses.accessOauthToken;
        }
        if ((i & 2) != 0) {
            uri = destinationAccesses.accessOauthUri;
        }
        URI uri2 = uri;
        if ((i & 4) != 0) {
            str = destinationAccesses.accessPassword;
        }
        String str4 = str;
        if ((i & 8) != 0) {
            str2 = destinationAccesses.accessPin;
        }
        String str5 = str2;
        if ((i & 16) != 0) {
            str3 = destinationAccesses.accessUserName;
        }
        return destinationAccesses.copy(list, uri2, str4, str5, str3);
    }

    public final List<byte[]> component1() {
        return this.accessOauthToken;
    }

    public final URI getAccessOauthUri() {
        return this.accessOauthUri;
    }

    public final String getAccessPassword() {
        return this.accessPassword;
    }

    public final String getAccessPin() {
        return this.accessPin;
    }

    public final String getAccessUserName() {
        return this.accessUserName;
    }

    public final DestinationAccesses copy(List<byte[]> accessOauthToken2, URI accessOauthUri2, String accessPassword2, String accessPin2, String accessUserName2) {
        return new DestinationAccesses(accessOauthToken2, accessOauthUri2, accessPassword2, accessPin2, accessUserName2);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof DestinationAccesses)) {
            return false;
        }
        DestinationAccesses destinationAccesses = (DestinationAccesses) other;
        return Intrinsics.areEqual(this.accessOauthToken, destinationAccesses.accessOauthToken) && Intrinsics.areEqual(this.accessOauthUri, destinationAccesses.accessOauthUri) && Intrinsics.areEqual(this.accessPassword, destinationAccesses.accessPassword) && Intrinsics.areEqual(this.accessPin, destinationAccesses.accessPin) && Intrinsics.areEqual(this.accessUserName, destinationAccesses.accessUserName);
    }

    public int hashCode() {
        List<byte[]> list = this.accessOauthToken;
        int iHashCode = (list != null ? list.hashCode() : 0) * 31;
        URI uri = this.accessOauthUri;
        int iHashCode2 = (iHashCode + (uri != null ? uri.hashCode() : 0)) * 31;
        String str = this.accessPassword;
        int iHashCode3 = (iHashCode2 + (str != null ? str.hashCode() : 0)) * 31;
        String str2 = this.accessPin;
        int iHashCode4 = (iHashCode3 + (str2 != null ? str2.hashCode() : 0)) * 31;
        String str3 = this.accessUserName;
        return iHashCode4 + (str3 != null ? str3.hashCode() : 0);
    }

    @Override
    public void print(PrettyPrinter printer) {
        Intrinsics.checkNotNullParameter(printer, "printer");
        AttributeCollection.DefaultImpls.print(this, printer);
    }

    public DestinationAccesses(List<byte[]> list, URI uri, String str, String str2, String str3) {
        this.accessOauthToken = list;
        this.accessOauthUri = uri;
        this.accessPassword = str;
        this.accessPin = str2;
        this.accessUserName = str3;
    }

    public DestinationAccesses(List list, URI uri, String str, String str2, String str3, int i, DefaultConstructorMarker defaultConstructorMarker) {
        URI uri2;
        String str4;
        String str5;
        String str6 = null;
        if ((i & 1) != 0) {
            list = null;
        }
        if ((i & 2) != 0) {
            uri2 = null;
        } else {
            uri2 = uri;
        }
        if ((i & 4) != 0) {
            str4 = null;
        } else {
            str4 = str;
        }
        if ((i & 8) != 0) {
            str5 = null;
        } else {
            str5 = str2;
        }
        if ((i & 16) != 0) {
        } else {
            str6 = str3;
        }
        this(list, uri2, str4, str5, str6);
    }

    public final List<byte[]> getAccessOauthToken() {
        return this.accessOauthToken;
    }

    public final void setAccessOauthToken(List<byte[]> list) {
        this.accessOauthToken = list;
    }

    public final URI getAccessOauthUri() {
        return this.accessOauthUri;
    }

    public final void setAccessOauthUri(URI uri) {
        this.accessOauthUri = uri;
    }

    public final String getAccessPassword() {
        return this.accessPassword;
    }

    public final void setAccessPassword(String str) {
        this.accessPassword = str;
    }

    public final String getAccessPin() {
        return this.accessPin;
    }

    public final void setAccessPin(String str) {
        this.accessPin = str;
    }

    public final String getAccessUserName() {
        return this.accessUserName;
    }

    public final void setAccessUserName(String str) {
        this.accessUserName = str;
    }

    public DestinationAccesses() {
        this(null, null, null, null, null);
    }

    @Override
    public List<Attribute<?>> getAttributes() {
        Attribute[] attributeArr = new Attribute[5];
        List<byte[]> list = this.accessOauthToken;
        attributeArr[0] = list != null ? accessOauthToken.mo417of((Iterable<? extends byte[]>) list) : null;
        URI uri = this.accessOauthUri;
        attributeArr[1] = uri != null ? accessOauthUri.mo418of(uri) : null;
        String str = this.accessPassword;
        attributeArr[2] = str != null ? accessPassword.m442of(str) : null;
        String str2 = this.accessPin;
        attributeArr[3] = str2 != null ? accessPin.m442of(str2) : null;
        String str3 = this.accessUserName;
        attributeArr[4] = str3 != null ? accessUserName.m442of(str3) : null;
        return CollectionsKt.listOfNotNull((Object[]) attributeArr);
    }

    @Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003J\u001a\u0010\u0012\u001a\u00020\u00022\u0010\u0010\u0013\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00150\u0014H\u0016R\u0016\u0010\u0004\u001a\u00020\u00008\u0006X\u0087\u0004¢\u0006\b\n\u0000\u0012\u0004\b\u0005\u0010\u0003R\u0010\u0010\u0006\u001a\u00020\u00078\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u00020\t8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u00020\u000b8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u00020\u000b8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u00020\u000b8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00020\u000fX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u0016"}, m1293d2 = {"Lcom/hp/jipp/model/DestinationAccesses$Companion;", "Lcom/hp/jipp/encoding/AttributeCollection$Converter;", "Lcom/hp/jipp/model/DestinationAccesses;", "()V", "Types", "getTypes$annotations", "accessOauthToken", "Lcom/hp/jipp/encoding/OctetsType$Set;", "accessOauthUri", "Lcom/hp/jipp/encoding/UriType;", "accessPassword", "Lcom/hp/jipp/encoding/TextType;", "accessPin", "accessUserName", "cls", "Ljava/lang/Class;", "getCls", "()Ljava/lang/Class;", "convert", "attributes", "", "Lcom/hp/jipp/encoding/Attribute;", "jipp-core"}, m1294k = 1, m1295mv = {1, 4, 0})
    public static final class Companion implements AttributeCollection.Converter<DestinationAccesses> {
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
        public DestinationAccesses convert(List<? extends Attribute<?>> attributes) {
            Intrinsics.checkNotNullParameter(attributes, "attributes");
            List listExtractAll = extractAll(attributes, DestinationAccesses.accessOauthToken);
            URI uri = (URI) extractOne(attributes, DestinationAccesses.accessOauthUri);
            Text text = (Text) extractOne(attributes, DestinationAccesses.accessPassword);
            String value = text != null ? text.getValue() : null;
            Text text2 = (Text) extractOne(attributes, DestinationAccesses.accessPin);
            String value2 = text2 != null ? text2.getValue() : null;
            Text text3 = (Text) extractOne(attributes, DestinationAccesses.accessUserName);
            return new DestinationAccesses(listExtractAll, uri, value, value2, text3 != null ? text3.getValue() : null);
        }

        @Override
        public Class<DestinationAccesses> getCls() {
            return DestinationAccesses.cls;
        }
    }

    static {
        Companion companion = new Companion(null);
        INSTANCE = companion;
        cls = DestinationAccesses.class;
        Types = companion;
        accessOauthToken = new OctetsType.Set("access-oauth-token");
        accessOauthUri = new UriType("access-oauth-uri");
        accessPassword = new TextType("access-password");
        accessPin = new TextType("access-pin");
        accessUserName = new TextType("access-user-name");
    }

    public String toString() {
        return "DestinationAccesses(" + CollectionsKt.joinToString$default(getAttributes(), null, null, null, 0, null, null, 63, null) + ')';
    }
}
