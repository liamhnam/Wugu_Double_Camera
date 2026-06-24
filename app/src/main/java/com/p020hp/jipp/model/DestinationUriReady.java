package com.p020hp.jipp.model;

import com.p020hp.jipp.encoding.Attribute;
import com.p020hp.jipp.encoding.AttributeCollection;
import com.p020hp.jipp.encoding.AttributeType;
import com.p020hp.jipp.encoding.BooleanType;
import com.p020hp.jipp.encoding.KeywordType;
import com.p020hp.jipp.encoding.Name;
import com.p020hp.jipp.encoding.NameType;
import com.p020hp.jipp.encoding.OctetsType;
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
import org.eclipse.paho.android.service.MqttServiceConstants;

@Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0012\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b6\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\b\u0086\b\u0018\u0000 P2\u00020\u0001:\u0001PB\u0007\b\u0016¢\u0006\u0002\u0010\u0002B\u009b\u0001\u0012\u0010\b\u0002\u0010\u0003\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0004\u0012\u0010\b\u0002\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0004\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n\u0012\u0010\b\u0002\u0010\u000b\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0004\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u0007\u0012\u0010\b\u0002\u0010\r\u001a\n\u0012\u0004\u0012\u00020\u000e\u0018\u00010\u0004\u0012\u0010\b\u0002\u0010\u000f\u001a\n\u0012\u0004\u0012\u00020\u000e\u0018\u00010\u0004\u0012\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u0011\u0012\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u0011¢\u0006\u0002\u0010\u0013J\u0011\u0010>\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0004HÆ\u0003J\u000b\u0010?\u001a\u0004\u0018\u00010\u0011HÆ\u0003J\u0011\u0010@\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0004HÆ\u0003J\u000b\u0010A\u001a\u0004\u0018\u00010\u0007HÆ\u0003J\u0010\u0010B\u001a\u0004\u0018\u00010\nHÆ\u0003¢\u0006\u0002\u0010%J\u0011\u0010C\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0004HÆ\u0003J\u000b\u0010D\u001a\u0004\u0018\u00010\u0007HÆ\u0003J\u0011\u0010E\u001a\n\u0012\u0004\u0012\u00020\u000e\u0018\u00010\u0004HÆ\u0003J\u0011\u0010F\u001a\n\u0012\u0004\u0012\u00020\u000e\u0018\u00010\u0004HÆ\u0003J\u000b\u0010G\u001a\u0004\u0018\u00010\u0011HÆ\u0003J¤\u0001\u0010H\u001a\u00020\u00002\u0010\b\u0002\u0010\u0003\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u00042\u0010\b\u0002\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u00042\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n2\u0010\b\u0002\u0010\u000b\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u00042\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u00072\u0010\b\u0002\u0010\r\u001a\n\u0012\u0004\u0012\u00020\u000e\u0018\u00010\u00042\u0010\b\u0002\u0010\u000f\u001a\n\u0012\u0004\u0012\u00020\u000e\u0018\u00010\u00042\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u00112\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u0011HÆ\u0001¢\u0006\u0002\u0010IJ\u0013\u0010J\u001a\u00020\n2\b\u0010K\u001a\u0004\u0018\u00010LHÖ\u0003J\t\u0010M\u001a\u00020NHÖ\u0001J\b\u0010O\u001a\u00020\u0007H\u0016R\u001e\u0010\u0014\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00150\u00048VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0016\u0010\u0017R$\u0010\u0003\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0004X\u0086\u000e¢\u0006\u0010\n\u0002\b\u001b\u001a\u0004\b\u0018\u0010\u0017\"\u0004\b\u0019\u0010\u001aR$\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0004X\u0086\u000e¢\u0006\u0010\n\u0002\b\u001e\u001a\u0004\b\u001c\u0010\u0017\"\u0004\b\u001d\u0010\u001aR\u001e\u0010\b\u001a\u0004\u0018\u00010\u0007X\u0086\u000e¢\u0006\u0010\n\u0002\b#\u001a\u0004\b\u001f\u0010 \"\u0004\b!\u0010\"R \u0010\t\u001a\u0004\u0018\u00010\nX\u0086\u000e¢\u0006\u0012\n\u0004\b(\u0010)\u001a\u0004\b$\u0010%\"\u0004\b&\u0010'R$\u0010\u000b\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0004X\u0086\u000e¢\u0006\u0010\n\u0002\b,\u001a\u0004\b*\u0010\u0017\"\u0004\b+\u0010\u001aR\u001e\u0010\f\u001a\u0004\u0018\u00010\u0007X\u0086\u000e¢\u0006\u0010\n\u0002\b/\u001a\u0004\b-\u0010 \"\u0004\b.\u0010\"R$\u0010\r\u001a\n\u0012\u0004\u0012\u00020\u000e\u0018\u00010\u0004X\u0086\u000e¢\u0006\u0010\n\u0002\b2\u001a\u0004\b0\u0010\u0017\"\u0004\b1\u0010\u001aR$\u0010\u000f\u001a\n\u0012\u0004\u0012\u00020\u000e\u0018\u00010\u0004X\u0086\u000e¢\u0006\u0010\n\u0002\b5\u001a\u0004\b3\u0010\u0017\"\u0004\b4\u0010\u001aR\u001e\u0010\u0010\u001a\u0004\u0018\u00010\u0011X\u0086\u000e¢\u0006\u0010\n\u0002\b:\u001a\u0004\b6\u00107\"\u0004\b8\u00109R\u001e\u0010\u0012\u001a\u0004\u0018\u00010\u0011X\u0086\u000e¢\u0006\u0010\n\u0002\b=\u001a\u0004\b;\u00107\"\u0004\b<\u00109¨\u0006Q"}, m1293d2 = {"Lcom/hp/jipp/model/DestinationUriReady;", "Lcom/hp/jipp/encoding/AttributeCollection;", "()V", "destinationAttributes", "", "Lcom/hp/jipp/encoding/UntypedCollection;", "destinationAttributesSupported", "", "destinationInfo", "destinationIsDirectory", "", "destinationMandatoryAccessAttributes", MqttServiceConstants.DESTINATION_NAME, "destinationOauthScope", "", "destinationOauthToken", "destinationOauthUri", "Ljava/net/URI;", "destinationUri", "(Ljava/util/List;Ljava/util/List;Ljava/lang/String;Ljava/lang/Boolean;Ljava/util/List;Ljava/lang/String;Ljava/util/List;Ljava/util/List;Ljava/net/URI;Ljava/net/URI;)V", "attributes", "Lcom/hp/jipp/encoding/Attribute;", "getAttributes", "()Ljava/util/List;", "getDestinationAttributes", "setDestinationAttributes", "(Ljava/util/List;)V", "destinationAttributes$1", "getDestinationAttributesSupported", "setDestinationAttributesSupported", "destinationAttributesSupported$1", "getDestinationInfo", "()Ljava/lang/String;", "setDestinationInfo", "(Ljava/lang/String;)V", "destinationInfo$1", "getDestinationIsDirectory", "()Ljava/lang/Boolean;", "setDestinationIsDirectory", "(Ljava/lang/Boolean;)V", "destinationIsDirectory$1", "Ljava/lang/Boolean;", "getDestinationMandatoryAccessAttributes", "setDestinationMandatoryAccessAttributes", "destinationMandatoryAccessAttributes$1", "getDestinationName", "setDestinationName", "destinationName$1", "getDestinationOauthScope", "setDestinationOauthScope", "destinationOauthScope$1", "getDestinationOauthToken", "setDestinationOauthToken", "destinationOauthToken$1", "getDestinationOauthUri", "()Ljava/net/URI;", "setDestinationOauthUri", "(Ljava/net/URI;)V", "destinationOauthUri$1", "getDestinationUri", "setDestinationUri", "destinationUri$1", "component1", "component10", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", PrinterServiceType.copy, "(Ljava/util/List;Ljava/util/List;Ljava/lang/String;Ljava/lang/Boolean;Ljava/util/List;Ljava/lang/String;Ljava/util/List;Ljava/util/List;Ljava/net/URI;Ljava/net/URI;)Lcom/hp/jipp/model/DestinationUriReady;", "equals", "other", "", "hashCode", "", "toString", "Companion", "jipp-core"}, m1294k = 1, m1295mv = {1, 4, 0})
public final class DestinationUriReady implements AttributeCollection {

    public static final Companion INSTANCE;
    public static final Companion Types;
    private static final Class<DestinationUriReady> cls;
    public static final UntypedCollection.SetType destinationAttributes;
    public static final KeywordType.Set destinationAttributesSupported;
    public static final TextType destinationInfo;
    public static final BooleanType destinationIsDirectory;
    public static final KeywordType.Set destinationMandatoryAccessAttributes;
    public static final NameType destinationName;
    public static final OctetsType.Set destinationOauthScope;
    public static final OctetsType.Set destinationOauthToken;
    public static final UriType destinationOauthUri;
    public static final UriType destinationUri;

    private List<UntypedCollection> destinationAttributes;

    private List<String> destinationAttributesSupported;

    private String destinationInfo;

    private Boolean destinationIsDirectory;

    private List<String> destinationMandatoryAccessAttributes;

    private String destinationName;

    private List<byte[]> destinationOauthScope;

    private List<byte[]> destinationOauthToken;

    private URI destinationOauthUri;

    private URI destinationUri;

    public final List<UntypedCollection> component1() {
        return this.destinationAttributes;
    }

    public final URI getDestinationUri() {
        return this.destinationUri;
    }

    public final List<String> component2() {
        return this.destinationAttributesSupported;
    }

    public final String getDestinationInfo() {
        return this.destinationInfo;
    }

    public final Boolean getDestinationIsDirectory() {
        return this.destinationIsDirectory;
    }

    public final List<String> component5() {
        return this.destinationMandatoryAccessAttributes;
    }

    public final String getDestinationName() {
        return this.destinationName;
    }

    public final List<byte[]> component7() {
        return this.destinationOauthScope;
    }

    public final List<byte[]> component8() {
        return this.destinationOauthToken;
    }

    public final URI getDestinationOauthUri() {
        return this.destinationOauthUri;
    }

    public final DestinationUriReady copy(List<UntypedCollection> destinationAttributes2, List<String> destinationAttributesSupported2, String destinationInfo2, Boolean destinationIsDirectory2, List<String> destinationMandatoryAccessAttributes2, String destinationName2, List<byte[]> destinationOauthScope2, List<byte[]> destinationOauthToken2, URI destinationOauthUri2, URI destinationUri2) {
        return new DestinationUriReady(destinationAttributes2, destinationAttributesSupported2, destinationInfo2, destinationIsDirectory2, destinationMandatoryAccessAttributes2, destinationName2, destinationOauthScope2, destinationOauthToken2, destinationOauthUri2, destinationUri2);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof DestinationUriReady)) {
            return false;
        }
        DestinationUriReady destinationUriReady = (DestinationUriReady) other;
        return Intrinsics.areEqual(this.destinationAttributes, destinationUriReady.destinationAttributes) && Intrinsics.areEqual(this.destinationAttributesSupported, destinationUriReady.destinationAttributesSupported) && Intrinsics.areEqual(this.destinationInfo, destinationUriReady.destinationInfo) && Intrinsics.areEqual(this.destinationIsDirectory, destinationUriReady.destinationIsDirectory) && Intrinsics.areEqual(this.destinationMandatoryAccessAttributes, destinationUriReady.destinationMandatoryAccessAttributes) && Intrinsics.areEqual(this.destinationName, destinationUriReady.destinationName) && Intrinsics.areEqual(this.destinationOauthScope, destinationUriReady.destinationOauthScope) && Intrinsics.areEqual(this.destinationOauthToken, destinationUriReady.destinationOauthToken) && Intrinsics.areEqual(this.destinationOauthUri, destinationUriReady.destinationOauthUri) && Intrinsics.areEqual(this.destinationUri, destinationUriReady.destinationUri);
    }

    public int hashCode() {
        List<UntypedCollection> list = this.destinationAttributes;
        int iHashCode = (list != null ? list.hashCode() : 0) * 31;
        List<String> list2 = this.destinationAttributesSupported;
        int iHashCode2 = (iHashCode + (list2 != null ? list2.hashCode() : 0)) * 31;
        String str = this.destinationInfo;
        int iHashCode3 = (iHashCode2 + (str != null ? str.hashCode() : 0)) * 31;
        Boolean bool = this.destinationIsDirectory;
        int iHashCode4 = (iHashCode3 + (bool != null ? bool.hashCode() : 0)) * 31;
        List<String> list3 = this.destinationMandatoryAccessAttributes;
        int iHashCode5 = (iHashCode4 + (list3 != null ? list3.hashCode() : 0)) * 31;
        String str2 = this.destinationName;
        int iHashCode6 = (iHashCode5 + (str2 != null ? str2.hashCode() : 0)) * 31;
        List<byte[]> list4 = this.destinationOauthScope;
        int iHashCode7 = (iHashCode6 + (list4 != null ? list4.hashCode() : 0)) * 31;
        List<byte[]> list5 = this.destinationOauthToken;
        int iHashCode8 = (iHashCode7 + (list5 != null ? list5.hashCode() : 0)) * 31;
        URI uri = this.destinationOauthUri;
        int iHashCode9 = (iHashCode8 + (uri != null ? uri.hashCode() : 0)) * 31;
        URI uri2 = this.destinationUri;
        return iHashCode9 + (uri2 != null ? uri2.hashCode() : 0);
    }

    @Override
    public void print(PrettyPrinter printer) {
        Intrinsics.checkNotNullParameter(printer, "printer");
        AttributeCollection.DefaultImpls.print(this, printer);
    }

    public DestinationUriReady(List<UntypedCollection> list, List<String> list2, String str, Boolean bool, List<String> list3, String str2, List<byte[]> list4, List<byte[]> list5, URI uri, URI uri2) {
        this.destinationAttributes = list;
        this.destinationAttributesSupported = list2;
        this.destinationInfo = str;
        this.destinationIsDirectory = bool;
        this.destinationMandatoryAccessAttributes = list3;
        this.destinationName = str2;
        this.destinationOauthScope = list4;
        this.destinationOauthToken = list5;
        this.destinationOauthUri = uri;
        this.destinationUri = uri2;
    }

    public DestinationUriReady(List list, List list2, String str, Boolean bool, List list3, String str2, List list4, List list5, URI uri, URI uri2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        List list6;
        List list7;
        String str3;
        Boolean bool2;
        List list8;
        String str4;
        List list9;
        List list10;
        URI uri3;
        URI uri4 = null;
        if ((i & 1) != 0) {
            list6 = null;
        } else {
            list6 = list;
        }
        if ((i & 2) != 0) {
            list7 = null;
        } else {
            list7 = list2;
        }
        if ((i & 4) != 0) {
            str3 = null;
        } else {
            str3 = str;
        }
        if ((i & 8) != 0) {
            bool2 = null;
        } else {
            bool2 = bool;
        }
        if ((i & 16) != 0) {
            list8 = null;
        } else {
            list8 = list3;
        }
        if ((i & 32) != 0) {
            str4 = null;
        } else {
            str4 = str2;
        }
        if ((i & 64) != 0) {
            list9 = null;
        } else {
            list9 = list4;
        }
        if ((i & 128) != 0) {
            list10 = null;
        } else {
            list10 = list5;
        }
        if ((i & 256) != 0) {
            uri3 = null;
        } else {
            uri3 = uri;
        }
        if ((i & 512) != 0) {
        } else {
            uri4 = uri2;
        }
        this(list6, list7, str3, bool2, list8, str4, list9, list10, uri3, uri4);
    }

    public final List<UntypedCollection> getDestinationAttributes() {
        return this.destinationAttributes;
    }

    public final void setDestinationAttributes(List<UntypedCollection> list) {
        this.destinationAttributes = list;
    }

    public final List<String> getDestinationAttributesSupported() {
        return this.destinationAttributesSupported;
    }

    public final void setDestinationAttributesSupported(List<String> list) {
        this.destinationAttributesSupported = list;
    }

    public final String getDestinationInfo() {
        return this.destinationInfo;
    }

    public final void setDestinationInfo(String str) {
        this.destinationInfo = str;
    }

    public final Boolean getDestinationIsDirectory() {
        return this.destinationIsDirectory;
    }

    public final void setDestinationIsDirectory(Boolean bool) {
        this.destinationIsDirectory = bool;
    }

    public final List<String> getDestinationMandatoryAccessAttributes() {
        return this.destinationMandatoryAccessAttributes;
    }

    public final void setDestinationMandatoryAccessAttributes(List<String> list) {
        this.destinationMandatoryAccessAttributes = list;
    }

    public final String getDestinationName() {
        return this.destinationName;
    }

    public final void setDestinationName(String str) {
        this.destinationName = str;
    }

    public final List<byte[]> getDestinationOauthScope() {
        return this.destinationOauthScope;
    }

    public final void setDestinationOauthScope(List<byte[]> list) {
        this.destinationOauthScope = list;
    }

    public final List<byte[]> getDestinationOauthToken() {
        return this.destinationOauthToken;
    }

    public final void setDestinationOauthToken(List<byte[]> list) {
        this.destinationOauthToken = list;
    }

    public final URI getDestinationOauthUri() {
        return this.destinationOauthUri;
    }

    public final void setDestinationOauthUri(URI uri) {
        this.destinationOauthUri = uri;
    }

    public final URI getDestinationUri() {
        return this.destinationUri;
    }

    public final void setDestinationUri(URI uri) {
        this.destinationUri = uri;
    }

    public DestinationUriReady() {
        this(null, null, null, null, null, null, null, null, null, null);
    }

    @Override
    public List<Attribute<?>> getAttributes() {
        Attribute[] attributeArr = new Attribute[10];
        List<UntypedCollection> list = this.destinationAttributes;
        attributeArr[0] = list != null ? destinationAttributes.mo417of((Iterable<? extends UntypedCollection>) list) : null;
        List<String> list2 = this.destinationAttributesSupported;
        attributeArr[1] = list2 != null ? destinationAttributesSupported.mo417of((Iterable<? extends String>) list2) : null;
        String str = this.destinationInfo;
        attributeArr[2] = str != null ? destinationInfo.m442of(str) : null;
        Boolean bool = this.destinationIsDirectory;
        attributeArr[3] = bool != null ? destinationIsDirectory.mo418of(Boolean.valueOf(bool.booleanValue())) : null;
        List<String> list3 = this.destinationMandatoryAccessAttributes;
        attributeArr[4] = list3 != null ? destinationMandatoryAccessAttributes.mo417of((Iterable<? extends String>) list3) : null;
        String str2 = this.destinationName;
        attributeArr[5] = str2 != null ? destinationName.m440of(str2) : null;
        List<byte[]> list4 = this.destinationOauthScope;
        attributeArr[6] = list4 != null ? destinationOauthScope.mo417of((Iterable<? extends byte[]>) list4) : null;
        List<byte[]> list5 = this.destinationOauthToken;
        attributeArr[7] = list5 != null ? destinationOauthToken.mo417of((Iterable<? extends byte[]>) list5) : null;
        URI uri = this.destinationOauthUri;
        attributeArr[8] = uri != null ? destinationOauthUri.mo418of(uri) : null;
        URI uri2 = this.destinationUri;
        attributeArr[9] = uri2 != null ? destinationUri.mo418of(uri2) : null;
        return CollectionsKt.listOfNotNull((Object[]) attributeArr);
    }

    @Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003J\u001a\u0010\u001b\u001a\u00020\u00022\u0010\u0010\u001c\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u001e0\u001dH\u0016R\u0016\u0010\u0004\u001a\u00020\u00008\u0006X\u0087\u0004¢\u0006\b\n\u0000\u0012\u0004\b\u0005\u0010\u0003R\u001a\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00020\u0007X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0010\u0010\n\u001a\u00020\u000b8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u00020\r8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u00020\u000f8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0010\u001a\u00020\u00118\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0012\u001a\u00020\r8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0013\u001a\u00020\u00148\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0015\u001a\u00020\u00168\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0017\u001a\u00020\u00168\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0018\u001a\u00020\u00198\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u001a\u001a\u00020\u00198\u0006X\u0087\u0004¢\u0006\u0002\n\u0000¨\u0006\u001f"}, m1293d2 = {"Lcom/hp/jipp/model/DestinationUriReady$Companion;", "Lcom/hp/jipp/encoding/AttributeCollection$Converter;", "Lcom/hp/jipp/model/DestinationUriReady;", "()V", "Types", "getTypes$annotations", "cls", "Ljava/lang/Class;", "getCls", "()Ljava/lang/Class;", "destinationAttributes", "Lcom/hp/jipp/encoding/UntypedCollection$SetType;", "destinationAttributesSupported", "Lcom/hp/jipp/encoding/KeywordType$Set;", "destinationInfo", "Lcom/hp/jipp/encoding/TextType;", "destinationIsDirectory", "Lcom/hp/jipp/encoding/BooleanType;", "destinationMandatoryAccessAttributes", MqttServiceConstants.DESTINATION_NAME, "Lcom/hp/jipp/encoding/NameType;", "destinationOauthScope", "Lcom/hp/jipp/encoding/OctetsType$Set;", "destinationOauthToken", "destinationOauthUri", "Lcom/hp/jipp/encoding/UriType;", "destinationUri", "convert", "attributes", "", "Lcom/hp/jipp/encoding/Attribute;", "jipp-core"}, m1294k = 1, m1295mv = {1, 4, 0})
    public static final class Companion implements AttributeCollection.Converter<DestinationUriReady> {
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
        public DestinationUriReady convert(List<? extends Attribute<?>> attributes) {
            Intrinsics.checkNotNullParameter(attributes, "attributes");
            List listExtractAll = extractAll(attributes, DestinationUriReady.destinationAttributes);
            List listExtractAll2 = extractAll(attributes, DestinationUriReady.destinationAttributesSupported);
            Text text = (Text) extractOne(attributes, DestinationUriReady.destinationInfo);
            String value = text != null ? text.getValue() : null;
            Boolean bool = (Boolean) extractOne(attributes, DestinationUriReady.destinationIsDirectory);
            List listExtractAll3 = extractAll(attributes, DestinationUriReady.destinationMandatoryAccessAttributes);
            Name name = (Name) extractOne(attributes, DestinationUriReady.destinationName);
            return new DestinationUriReady(listExtractAll, listExtractAll2, value, bool, listExtractAll3, name != null ? name.getValue() : null, extractAll(attributes, DestinationUriReady.destinationOauthScope), extractAll(attributes, DestinationUriReady.destinationOauthToken), (URI) extractOne(attributes, DestinationUriReady.destinationOauthUri), (URI) extractOne(attributes, DestinationUriReady.destinationUri));
        }

        @Override
        public Class<DestinationUriReady> getCls() {
            return DestinationUriReady.cls;
        }
    }

    static {
        Companion companion = new Companion(null);
        INSTANCE = companion;
        cls = DestinationUriReady.class;
        Types = companion;
        destinationAttributes = new UntypedCollection.SetType("destination-attributes");
        destinationAttributesSupported = new KeywordType.Set("destination-attributes-supported");
        destinationInfo = new TextType("destination-info");
        destinationIsDirectory = new BooleanType("destination-is-directory");
        destinationMandatoryAccessAttributes = new KeywordType.Set("destination-mandatory-access-attributes");
        destinationName = new NameType("destination-name");
        destinationOauthScope = new OctetsType.Set("destination-oauth-scope");
        destinationOauthToken = new OctetsType.Set("destination-oauth-token");
        destinationOauthUri = new UriType("destination-oauth-uri");
        destinationUri = new UriType("destination-uri");
    }

    public String toString() {
        return "DestinationUriReady(" + CollectionsKt.joinToString$default(getAttributes(), null, null, null, 0, null, null, 63, null) + ')';
    }
}
