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

@Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0007\b\u0086\b\u0018\u0000 \u00152\u00020\u0001:\u0004\u0014\u0015\u0016\u0017B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\t\u0010\f\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0003HÖ\u0001J\b\u0010\u0013\u001a\u00020\u0005H\u0016R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0014\u0010\u0004\u001a\u00020\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0018"}, m1293d2 = {"Lcom/hp/jipp/model/TransmissionStatus;", "Lcom/hp/jipp/encoding/Enum;", "code", "", NamingTable.TAG, "", "(ILjava/lang/String;)V", "getCode", "()I", "getName", "()Ljava/lang/String;", "component1", "component2", PrinterServiceType.copy, "equals", "", "other", "", "hashCode", "toString", StandardStructureTypes.CODE, "Companion", "SetType", "Type", "jipp-core"}, m1294k = 1, m1295mv = {1, 4, 0})
public final class TransmissionStatus extends Enum {

    public static final Companion INSTANCE = new Companion(null);
    public static final TransmissionStatus aborted;
    public static final Map<Integer, TransmissionStatus> all;
    public static final TransmissionStatus canceled;
    public static final TransmissionStatus completed;
    public static final TransmissionStatus pending;
    public static final TransmissionStatus pendingRetry;
    public static final TransmissionStatus processing;
    private final int code;
    private final String name;

    public static TransmissionStatus copy$default(TransmissionStatus transmissionStatus, int i, String str, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = transmissionStatus.getCode();
        }
        if ((i2 & 2) != 0) {
            str = transmissionStatus.getName();
        }
        return transmissionStatus.copy(i, str);
    }

    public final int component1() {
        return getCode();
    }

    public final String component2() {
        return getName();
    }

    public final TransmissionStatus copy(int code, String name) {
        Intrinsics.checkNotNullParameter(name, "name");
        return new TransmissionStatus(code, name);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof TransmissionStatus)) {
            return false;
        }
        TransmissionStatus transmissionStatus = (TransmissionStatus) other;
        return getCode() == transmissionStatus.getCode() && Intrinsics.areEqual(getName(), transmissionStatus.getName());
    }

    public int hashCode() {
        int iHashCode = Integer.hashCode(getCode()) * 31;
        String name = getName();
        return iHashCode + (name != null ? name.hashCode() : 0);
    }

    public TransmissionStatus(int i, String name) {
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

    @Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005¨\u0006\u0006"}, m1293d2 = {"Lcom/hp/jipp/model/TransmissionStatus$Type;", "Lcom/hp/jipp/encoding/EnumType;", "Lcom/hp/jipp/model/TransmissionStatus;", NamingTable.TAG, "", "(Ljava/lang/String;)V", "jipp-core"}, m1294k = 1, m1295mv = {1, 4, 0})
    public static final class Type extends EnumType<TransmissionStatus> {
        public Type(String name) {
            super(name, new Function1<Integer, TransmissionStatus>() {
                public final TransmissionStatus invoke(int i) {
                    return TransmissionStatus.INSTANCE.get(i);
                }

                @Override
                public TransmissionStatus invoke(Integer num) {
                    return invoke(num.intValue());
                }
            });
            Intrinsics.checkNotNullParameter(name, "name");
        }
    }

    @Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005¨\u0006\u0006"}, m1293d2 = {"Lcom/hp/jipp/model/TransmissionStatus$SetType;", "Lcom/hp/jipp/encoding/EnumType$Set;", "Lcom/hp/jipp/model/TransmissionStatus;", NamingTable.TAG, "", "(Ljava/lang/String;)V", "jipp-core"}, m1294k = 1, m1295mv = {1, 4, 0})
    public static final class SetType extends EnumType.Set<TransmissionStatus> {
        public SetType(String name) {
            super(name, new Function1<Integer, TransmissionStatus>() {
                public final TransmissionStatus invoke(int i) {
                    return TransmissionStatus.INSTANCE.get(i);
                }

                @Override
                public TransmissionStatus invoke(Integer num) {
                    return invoke(num.intValue());
                }
            });
            Intrinsics.checkNotNullParameter(name, "name");
        }
    }

    @Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0006\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\n"}, m1293d2 = {"Lcom/hp/jipp/model/TransmissionStatus$Code;", "", "()V", WhichJobs.aborted, "", WhichJobs.canceled, WhichJobs.completed, WhichJobs.pending, "pendingRetry", "processing", "jipp-core"}, m1294k = 1, m1295mv = {1, 4, 0})
    public static final class Code {
        public static final Code INSTANCE = new Code();
        public static final int aborted = 8;
        public static final int canceled = 7;
        public static final int completed = 9;
        public static final int pending = 3;
        public static final int pendingRetry = 4;
        public static final int processing = 5;

        private Code() {
        }
    }

    @Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\b\n\u0002\b\b\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0011\u0010\r\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\u0007H\u0086\u0002R\u0010\u0010\u0003\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00040\u00068\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\u0002\n\u0000¨\u0006\u000f"}, m1293d2 = {"Lcom/hp/jipp/model/TransmissionStatus$Companion;", "", "()V", WhichJobs.aborted, "Lcom/hp/jipp/model/TransmissionStatus;", "all", "", "", WhichJobs.canceled, WhichJobs.completed, WhichJobs.pending, "pendingRetry", "processing", "get", "value", "jipp-core"}, m1294k = 1, m1295mv = {1, 4, 0})
    public static final class Companion {
        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final TransmissionStatus get(int value) {
            TransmissionStatus transmissionStatus = TransmissionStatus.all.get(Integer.valueOf(value));
            return transmissionStatus != null ? transmissionStatus : new TransmissionStatus(value, "???");
        }
    }

    static {
        TransmissionStatus transmissionStatus = new TransmissionStatus(3, WhichJobs.pending);
        pending = transmissionStatus;
        TransmissionStatus transmissionStatus2 = new TransmissionStatus(4, "pending-retry");
        pendingRetry = transmissionStatus2;
        TransmissionStatus transmissionStatus3 = new TransmissionStatus(5, "processing");
        processing = transmissionStatus3;
        TransmissionStatus transmissionStatus4 = new TransmissionStatus(7, WhichJobs.canceled);
        canceled = transmissionStatus4;
        TransmissionStatus transmissionStatus5 = new TransmissionStatus(8, WhichJobs.aborted);
        aborted = transmissionStatus5;
        TransmissionStatus transmissionStatus6 = new TransmissionStatus(9, WhichJobs.completed);
        completed = transmissionStatus6;
        List<TransmissionStatus> listListOf = CollectionsKt.listOf((Object[]) new TransmissionStatus[]{transmissionStatus, transmissionStatus2, transmissionStatus3, transmissionStatus4, transmissionStatus5, transmissionStatus6});
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(listListOf, 10));
        for (TransmissionStatus transmissionStatus7 : listListOf) {
            arrayList.add(TuplesKt.m1300to(Integer.valueOf(transmissionStatus7.getCode()), transmissionStatus7));
        }
        all = MapsKt.toMap(arrayList);
    }
}
