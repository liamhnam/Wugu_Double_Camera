package com.p020hp.jipp.model;

import com.p020hp.jipp.encoding.Enum;
import com.p020hp.jipp.encoding.EnumType;
import com.tom_roush.fontbox.ttf.NamingTable;
import com.tom_roush.pdfbox.pdmodel.documentinterchange.taggedpdf.StandardStructureTypes;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0007\b\u0086\b\u0018\u0000 \u00152\u00020\u0001:\u0004\u0014\u0015\u0016\u0017B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\t\u0010\f\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0003HÖ\u0001J\b\u0010\u0013\u001a\u00020\u0005H\u0016R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0014\u0010\u0004\u001a\u00020\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0018"}, m1293d2 = {"Lcom/hp/jipp/model/Status;", "Lcom/hp/jipp/encoding/Enum;", "code", "", NamingTable.TAG, "", "(ILjava/lang/String;)V", "getCode", "()I", "getName", "()Ljava/lang/String;", "component1", "component2", PrinterServiceType.copy, "equals", "", "other", "", "hashCode", "toString", StandardStructureTypes.CODE, "Companion", "SetType", "Type", "jipp-core"}, m1294k = 1, m1295mv = {1, 4, 0})
public final class Status extends Enum {

    public static final Companion INSTANCE = new Companion(null);
    public static final Map<Integer, Status> all;
    public static final Status clientErrorAccountAuthorizationFailed;
    public static final Status clientErrorAccountClosed;
    public static final Status clientErrorAccountInfoNeeded;
    public static final Status clientErrorAccountLimitReached;
    public static final Status clientErrorAttributesNotSettable;
    public static final Status clientErrorAttributesOrValuesNotSupported;
    public static final Status clientErrorBadRequest;
    public static final Status clientErrorCharsetNotSupported;
    public static final Status clientErrorCompressionError;
    public static final Status clientErrorCompressionNotSupported;
    public static final Status clientErrorConflictingAttributes;
    public static final Status clientErrorDocumentAccessError;
    public static final Status clientErrorDocumentFormatError;
    public static final Status clientErrorDocumentFormatNotSupported;
    public static final Status clientErrorDocumentPasswordError;
    public static final Status clientErrorDocumentPermissionError;
    public static final Status clientErrorDocumentSecurityError;
    public static final Status clientErrorDocumentUnprintableError;
    public static final Status clientErrorForbidden;
    public static final Status clientErrorGone;
    public static final Status clientErrorIgnoredAllSubscriptions;
    public static final Status clientErrorNotAuthenticated;
    public static final Status clientErrorNotAuthorized;
    public static final Status clientErrorNotFetchable;
    public static final Status clientErrorNotFound;
    public static final Status clientErrorNotPossible;
    public static final Status clientErrorRequestEntityTooLarge;
    public static final Status clientErrorRequestValueTooLong;
    public static final Status clientErrorTimeout;
    public static final Status clientErrorTooManySubscriptions;
    public static final Status clientErrorUriSchemeNotSupported;
    public static final Status serverErrorBusy;
    public static final Status serverErrorDeviceError;
    public static final Status serverErrorInternalError;
    public static final Status serverErrorJobCanceled;
    public static final Status serverErrorMultipleDocumentJobsNotSupported;
    public static final Status serverErrorNotAcceptingJobs;
    public static final Status serverErrorOperationNotSupported;
    public static final Status serverErrorPrinterIsDeactivated;
    public static final Status serverErrorServiceUnavailable;
    public static final Status serverErrorTemporaryError;
    public static final Status serverErrorTooManyDocuments;
    public static final Status serverErrorTooManyJobs;
    public static final Status serverErrorVersionNotSupported;
    public static final Status successfulOk;
    public static final Status successfulOkConflictingAttributes;
    public static final Status successfulOkEventsComplete;
    public static final Status successfulOkIgnoredOrSubstitutedAttributes;
    public static final Status successfulOkIgnoredSubscriptions;
    public static final Status successfulOkTooManyEvents;
    private final int code;
    private final String name;

    public static Status copy$default(Status status, int i, String str, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = status.getCode();
        }
        if ((i2 & 2) != 0) {
            str = status.getName();
        }
        return status.copy(i, str);
    }

    public final int component1() {
        return getCode();
    }

    public final String component2() {
        return getName();
    }

    public final Status copy(int code, String name) {
        Intrinsics.checkNotNullParameter(name, "name");
        return new Status(code, name);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Status)) {
            return false;
        }
        Status status = (Status) other;
        return getCode() == status.getCode() && Intrinsics.areEqual(getName(), status.getName());
    }

    public int hashCode() {
        int iHashCode = Integer.hashCode(getCode()) * 31;
        String name = getName();
        return iHashCode + (name != null ? name.hashCode() : 0);
    }

    public Status(int i, String name) {
        Intrinsics.checkNotNullParameter(name, "name");
        this.code = i;
        this.name = name;
    }

    @Override
    public int getCode() {
        return this.code;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005¨\u0006\u0006"}, m1293d2 = {"Lcom/hp/jipp/model/Status$Type;", "Lcom/hp/jipp/encoding/EnumType;", "Lcom/hp/jipp/model/Status;", NamingTable.TAG, "", "(Ljava/lang/String;)V", "jipp-core"}, m1294k = 1, m1295mv = {1, 4, 0})
    public static final class Type extends EnumType<Status> {
        public Type(String name) {
            super(name, new Function1<Integer, Status>() {
                public final Status invoke(int i) {
                    return Status.INSTANCE.get(i);
                }

                @Override
                public Status invoke(Integer num) {
                    return invoke(num.intValue());
                }
            });
            Intrinsics.checkNotNullParameter(name, "name");
        }
    }

    @Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005¨\u0006\u0006"}, m1293d2 = {"Lcom/hp/jipp/model/Status$SetType;", "Lcom/hp/jipp/encoding/EnumType$Set;", "Lcom/hp/jipp/model/Status;", NamingTable.TAG, "", "(Ljava/lang/String;)V", "jipp-core"}, m1294k = 1, m1295mv = {1, 4, 0})
    public static final class SetType extends EnumType.Set<Status> {
        public SetType(String name) {
            super(name, new Function1<Integer, Status>() {
                public final Status invoke(int i) {
                    return Status.INSTANCE.get(i);
                }

                @Override
                public Status invoke(Integer num) {
                    return invoke(num.intValue());
                }
            });
            Intrinsics.checkNotNullParameter(name, "name");
        }
    }

    @Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b2\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010 \u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010!\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\"\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010#\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010$\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010%\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010&\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010'\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010(\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010)\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010*\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010+\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010,\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010-\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010.\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010/\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u00100\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u00101\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u00102\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u00103\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u00104\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u00105\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u00066"}, m1293d2 = {"Lcom/hp/jipp/model/Status$Code;", "", "()V", "clientErrorAccountAuthorizationFailed", "", "clientErrorAccountClosed", "clientErrorAccountInfoNeeded", "clientErrorAccountLimitReached", "clientErrorAttributesNotSettable", "clientErrorAttributesOrValuesNotSupported", "clientErrorBadRequest", "clientErrorCharsetNotSupported", "clientErrorCompressionError", "clientErrorCompressionNotSupported", "clientErrorConflictingAttributes", "clientErrorDocumentAccessError", "clientErrorDocumentFormatError", "clientErrorDocumentFormatNotSupported", "clientErrorDocumentPasswordError", "clientErrorDocumentPermissionError", "clientErrorDocumentSecurityError", "clientErrorDocumentUnprintableError", "clientErrorForbidden", "clientErrorGone", "clientErrorIgnoredAllSubscriptions", "clientErrorNotAuthenticated", "clientErrorNotAuthorized", "clientErrorNotFetchable", "clientErrorNotFound", "clientErrorNotPossible", "clientErrorRequestEntityTooLarge", "clientErrorRequestValueTooLong", "clientErrorTimeout", "clientErrorTooManySubscriptions", "clientErrorUriSchemeNotSupported", "serverErrorBusy", "serverErrorDeviceError", "serverErrorInternalError", "serverErrorJobCanceled", "serverErrorMultipleDocumentJobsNotSupported", "serverErrorNotAcceptingJobs", "serverErrorOperationNotSupported", "serverErrorPrinterIsDeactivated", "serverErrorServiceUnavailable", "serverErrorTemporaryError", "serverErrorTooManyDocuments", "serverErrorTooManyJobs", "serverErrorVersionNotSupported", "successfulOk", "successfulOkConflictingAttributes", "successfulOkEventsComplete", "successfulOkIgnoredOrSubstitutedAttributes", "successfulOkIgnoredSubscriptions", "successfulOkTooManyEvents", "jipp-core"}, m1294k = 1, m1295mv = {1, 4, 0})
    public static final class Code {
        public static final Code INSTANCE = new Code();
        public static final int clientErrorAccountAuthorizationFailed = 1055;
        public static final int clientErrorAccountClosed = 1053;
        public static final int clientErrorAccountInfoNeeded = 1052;
        public static final int clientErrorAccountLimitReached = 1054;
        public static final int clientErrorAttributesNotSettable = 1043;
        public static final int clientErrorAttributesOrValuesNotSupported = 1035;
        public static final int clientErrorBadRequest = 1024;
        public static final int clientErrorCharsetNotSupported = 1037;
        public static final int clientErrorCompressionError = 1040;
        public static final int clientErrorCompressionNotSupported = 1039;
        public static final int clientErrorConflictingAttributes = 1038;
        public static final int clientErrorDocumentAccessError = 1042;
        public static final int clientErrorDocumentFormatError = 1041;
        public static final int clientErrorDocumentFormatNotSupported = 1034;
        public static final int clientErrorDocumentPasswordError = 1048;
        public static final int clientErrorDocumentPermissionError = 1049;
        public static final int clientErrorDocumentSecurityError = 1050;
        public static final int clientErrorDocumentUnprintableError = 1051;
        public static final int clientErrorForbidden = 1025;
        public static final int clientErrorGone = 1031;
        public static final int clientErrorIgnoredAllSubscriptions = 1044;
        public static final int clientErrorNotAuthenticated = 1026;
        public static final int clientErrorNotAuthorized = 1027;
        public static final int clientErrorNotFetchable = 1056;
        public static final int clientErrorNotFound = 1030;
        public static final int clientErrorNotPossible = 1028;
        public static final int clientErrorRequestEntityTooLarge = 1032;
        public static final int clientErrorRequestValueTooLong = 1033;
        public static final int clientErrorTimeout = 1029;
        public static final int clientErrorTooManySubscriptions = 1045;
        public static final int clientErrorUriSchemeNotSupported = 1036;
        public static final int serverErrorBusy = 1287;
        public static final int serverErrorDeviceError = 1284;
        public static final int serverErrorInternalError = 1280;
        public static final int serverErrorJobCanceled = 1288;
        public static final int serverErrorMultipleDocumentJobsNotSupported = 1289;
        public static final int serverErrorNotAcceptingJobs = 1286;
        public static final int serverErrorOperationNotSupported = 1281;
        public static final int serverErrorPrinterIsDeactivated = 1290;
        public static final int serverErrorServiceUnavailable = 1282;
        public static final int serverErrorTemporaryError = 1285;
        public static final int serverErrorTooManyDocuments = 1292;
        public static final int serverErrorTooManyJobs = 1291;
        public static final int serverErrorVersionNotSupported = 1283;
        public static final int successfulOk = 0;
        public static final int successfulOkConflictingAttributes = 2;
        public static final int successfulOkEventsComplete = 7;
        public static final int successfulOkIgnoredOrSubstitutedAttributes = 1;
        public static final int successfulOkIgnoredSubscriptions = 3;
        public static final int successfulOkTooManyEvents = 5;

        private Code() {
        }
    }

    @Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b5\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0011\u00109\u001a\u00020\u00062\u0006\u0010:\u001a\u00020\u0005H\u0086\u0002R\u001c\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u00048\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u00020\u00068\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u00020\u00068\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u00020\u00068\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u00020\u00068\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u00020\u00068\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u00020\u00068\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u00020\u00068\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u00020\u00068\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u000f\u001a\u00020\u00068\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0010\u001a\u00020\u00068\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0011\u001a\u00020\u00068\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0012\u001a\u00020\u00068\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0013\u001a\u00020\u00068\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0014\u001a\u00020\u00068\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0015\u001a\u00020\u00068\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0016\u001a\u00020\u00068\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0017\u001a\u00020\u00068\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0018\u001a\u00020\u00068\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0019\u001a\u00020\u00068\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u001a\u001a\u00020\u00068\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u001b\u001a\u00020\u00068\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u001c\u001a\u00020\u00068\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u001d\u001a\u00020\u00068\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u001e\u001a\u00020\u00068\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u001f\u001a\u00020\u00068\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010 \u001a\u00020\u00068\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010!\u001a\u00020\u00068\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\"\u001a\u00020\u00068\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010#\u001a\u00020\u00068\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010$\u001a\u00020\u00068\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010%\u001a\u00020\u00068\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010&\u001a\u00020\u00068\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010'\u001a\u00020\u00068\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010(\u001a\u00020\u00068\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010)\u001a\u00020\u00068\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010*\u001a\u00020\u00068\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010+\u001a\u00020\u00068\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010,\u001a\u00020\u00068\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010-\u001a\u00020\u00068\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010.\u001a\u00020\u00068\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010/\u001a\u00020\u00068\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u00100\u001a\u00020\u00068\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u00101\u001a\u00020\u00068\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u00102\u001a\u00020\u00068\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u00103\u001a\u00020\u00068\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u00104\u001a\u00020\u00068\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u00105\u001a\u00020\u00068\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u00106\u001a\u00020\u00068\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u00107\u001a\u00020\u00068\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u00108\u001a\u00020\u00068\u0006X\u0087\u0004¢\u0006\u0002\n\u0000¨\u0006;"}, m1293d2 = {"Lcom/hp/jipp/model/Status$Companion;", "", "()V", "all", "", "", "Lcom/hp/jipp/model/Status;", "clientErrorAccountAuthorizationFailed", "clientErrorAccountClosed", "clientErrorAccountInfoNeeded", "clientErrorAccountLimitReached", "clientErrorAttributesNotSettable", "clientErrorAttributesOrValuesNotSupported", "clientErrorBadRequest", "clientErrorCharsetNotSupported", "clientErrorCompressionError", "clientErrorCompressionNotSupported", "clientErrorConflictingAttributes", "clientErrorDocumentAccessError", "clientErrorDocumentFormatError", "clientErrorDocumentFormatNotSupported", "clientErrorDocumentPasswordError", "clientErrorDocumentPermissionError", "clientErrorDocumentSecurityError", "clientErrorDocumentUnprintableError", "clientErrorForbidden", "clientErrorGone", "clientErrorIgnoredAllSubscriptions", "clientErrorNotAuthenticated", "clientErrorNotAuthorized", "clientErrorNotFetchable", "clientErrorNotFound", "clientErrorNotPossible", "clientErrorRequestEntityTooLarge", "clientErrorRequestValueTooLong", "clientErrorTimeout", "clientErrorTooManySubscriptions", "clientErrorUriSchemeNotSupported", "serverErrorBusy", "serverErrorDeviceError", "serverErrorInternalError", "serverErrorJobCanceled", "serverErrorMultipleDocumentJobsNotSupported", "serverErrorNotAcceptingJobs", "serverErrorOperationNotSupported", "serverErrorPrinterIsDeactivated", "serverErrorServiceUnavailable", "serverErrorTemporaryError", "serverErrorTooManyDocuments", "serverErrorTooManyJobs", "serverErrorVersionNotSupported", "successfulOk", "successfulOkConflictingAttributes", "successfulOkEventsComplete", "successfulOkIgnoredOrSubstitutedAttributes", "successfulOkIgnoredSubscriptions", "successfulOkTooManyEvents", "get", "value", "jipp-core"}, m1294k = 1, m1295mv = {1, 4, 0})
    public static final class Companion {
        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final Status get(int value) {
            Status status = Status.all.get(Integer.valueOf(value));
            return status != null ? status : new Status(value, "???");
        }
    }

    static {
        Status status = new Status(0, "successful-ok");
        successfulOk = status;
        Status status2 = new Status(1, "successful-ok-ignored-or-substituted-attributes");
        successfulOkIgnoredOrSubstitutedAttributes = status2;
        Status status3 = new Status(2, "successful-ok-conflicting-attributes");
        successfulOkConflictingAttributes = status3;
        Status status4 = new Status(3, "successful-ok-ignored-subscriptions");
        successfulOkIgnoredSubscriptions = status4;
        Status status5 = new Status(5, "successful-ok-too-many-events");
        successfulOkTooManyEvents = status5;
        Status status6 = new Status(7, "successful-ok-events-complete");
        successfulOkEventsComplete = status6;
        Status status7 = new Status(1024, "client-error-bad-request");
        clientErrorBadRequest = status7;
        Status status8 = new Status(1025, "client-error-forbidden");
        clientErrorForbidden = status8;
        Status status9 = new Status(Code.clientErrorNotAuthenticated, "client-error-not-authenticated");
        clientErrorNotAuthenticated = status9;
        Status status10 = new Status(Code.clientErrorNotAuthorized, "client-error-not-authorized");
        clientErrorNotAuthorized = status10;
        Status status11 = new Status(Code.clientErrorNotPossible, "client-error-not-possible");
        clientErrorNotPossible = status11;
        Status status12 = new Status(Code.clientErrorTimeout, "client-error-timeout");
        clientErrorTimeout = status12;
        Status status13 = new Status(Code.clientErrorNotFound, "client-error-not-found");
        clientErrorNotFound = status13;
        Status status14 = new Status(Code.clientErrorGone, "client-error-gone");
        clientErrorGone = status14;
        Status status15 = new Status(Code.clientErrorRequestEntityTooLarge, "client-error-request-entity-too-large");
        clientErrorRequestEntityTooLarge = status15;
        Status status16 = new Status(1033, "client-error-request-value-too-long");
        clientErrorRequestValueTooLong = status16;
        Status status17 = new Status(Code.clientErrorDocumentFormatNotSupported, "client-error-document-format-not-supported");
        clientErrorDocumentFormatNotSupported = status17;
        Status status18 = new Status(Code.clientErrorAttributesOrValuesNotSupported, "client-error-attributes-or-values-not-supported");
        clientErrorAttributesOrValuesNotSupported = status18;
        Status status19 = new Status(Code.clientErrorUriSchemeNotSupported, "client-error-uri-scheme-not-supported");
        clientErrorUriSchemeNotSupported = status19;
        Status status20 = new Status(Code.clientErrorCharsetNotSupported, "client-error-charset-not-supported");
        clientErrorCharsetNotSupported = status20;
        Status status21 = new Status(Code.clientErrorConflictingAttributes, "client-error-conflicting-attributes");
        clientErrorConflictingAttributes = status21;
        Status status22 = new Status(Code.clientErrorCompressionNotSupported, "client-error-compression-not-supported");
        clientErrorCompressionNotSupported = status22;
        Status status23 = new Status(Code.clientErrorCompressionError, "client-error-compression-error");
        clientErrorCompressionError = status23;
        Status status24 = new Status(Code.clientErrorDocumentFormatError, "client-error-document-format-error");
        clientErrorDocumentFormatError = status24;
        Status status25 = new Status(Code.clientErrorDocumentAccessError, "client-error-document-access-error");
        clientErrorDocumentAccessError = status25;
        Status status26 = new Status(Code.clientErrorAttributesNotSettable, "client-error-attributes-not-settable");
        clientErrorAttributesNotSettable = status26;
        Status status27 = new Status(Code.clientErrorIgnoredAllSubscriptions, "client-error-ignored-all-subscriptions");
        clientErrorIgnoredAllSubscriptions = status27;
        Status status28 = new Status(Code.clientErrorTooManySubscriptions, "client-error-too-many-subscriptions");
        clientErrorTooManySubscriptions = status28;
        Status status29 = new Status(Code.clientErrorDocumentPasswordError, "client-error-document-password-error");
        clientErrorDocumentPasswordError = status29;
        Status status30 = new Status(Code.clientErrorDocumentPermissionError, "client-error-document-permission-error");
        clientErrorDocumentPermissionError = status30;
        Status status31 = new Status(Code.clientErrorDocumentSecurityError, "client-error-document-security-error");
        clientErrorDocumentSecurityError = status31;
        Status status32 = new Status(Code.clientErrorDocumentUnprintableError, "client-error-document-unprintable-error");
        clientErrorDocumentUnprintableError = status32;
        Status status33 = new Status(Code.clientErrorAccountInfoNeeded, "client-error-account-info-needed");
        clientErrorAccountInfoNeeded = status33;
        Status status34 = new Status(Code.clientErrorAccountClosed, "client-error-account-closed");
        clientErrorAccountClosed = status34;
        Status status35 = new Status(Code.clientErrorAccountLimitReached, "client-error-account-limit-reached");
        clientErrorAccountLimitReached = status35;
        Status status36 = new Status(Code.clientErrorAccountAuthorizationFailed, "client-error-account-authorization-failed");
        clientErrorAccountAuthorizationFailed = status36;
        Status status37 = new Status(Code.clientErrorNotFetchable, "client-error-not-fetchable");
        clientErrorNotFetchable = status37;
        Status status38 = new Status(Code.serverErrorInternalError, "server-error-internal-error");
        serverErrorInternalError = status38;
        Status status39 = new Status(Code.serverErrorOperationNotSupported, "server-error-operation-not-supported");
        serverErrorOperationNotSupported = status39;
        Status status40 = new Status(Code.serverErrorServiceUnavailable, "server-error-service-unavailable");
        serverErrorServiceUnavailable = status40;
        Status status41 = new Status(Code.serverErrorVersionNotSupported, "server-error-version-not-supported");
        serverErrorVersionNotSupported = status41;
        Status status42 = new Status(Code.serverErrorDeviceError, "server-error-device-error");
        serverErrorDeviceError = status42;
        Status status43 = new Status(Code.serverErrorTemporaryError, "server-error-temporary-error");
        serverErrorTemporaryError = status43;
        Status status44 = new Status(Code.serverErrorNotAcceptingJobs, "server-error-not-accepting-jobs");
        serverErrorNotAcceptingJobs = status44;
        Status status45 = new Status(Code.serverErrorBusy, "server-error-busy");
        serverErrorBusy = status45;
        Status status46 = new Status(Code.serverErrorJobCanceled, "server-error-job-canceled");
        serverErrorJobCanceled = status46;
        Status status47 = new Status(Code.serverErrorMultipleDocumentJobsNotSupported, "server-error-multiple-document-jobs-not-supported");
        serverErrorMultipleDocumentJobsNotSupported = status47;
        Status status48 = new Status(Code.serverErrorPrinterIsDeactivated, "server-error-printer-is-deactivated");
        serverErrorPrinterIsDeactivated = status48;
        Status status49 = new Status(Code.serverErrorTooManyJobs, "server-error-too-many-jobs");
        serverErrorTooManyJobs = status49;
        Status status50 = new Status(Code.serverErrorTooManyDocuments, "server-error-too-many-documents");
        serverErrorTooManyDocuments = status50;
        List<Status> listListOf = CollectionsKt.listOf((Object[]) new Status[]{status, status2, status3, status4, status5, status6, status7, status8, status9, status10, status11, status12, status13, status14, status15, status16, status17, status18, status19, status20, status21, status22, status23, status24, status25, status26, status27, status28, status29, status30, status31, status32, status33, status34, status35, status36, status37, status38, status39, status40, status41, status42, status43, status44, status45, status46, status47, status48, status49, status50});
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(listListOf, 10));
        for (Status status51 : listListOf) {
            arrayList.add(TuplesKt.m1300to(Integer.valueOf(status51.getCode()), status51));
        }
        all = MapsKt.toMap(arrayList);
    }
}
