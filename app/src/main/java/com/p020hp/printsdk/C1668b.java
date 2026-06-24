package com.p020hp.printsdk;

import com.p020hp.jipp.encoding.AttributeGroup;
import com.p020hp.jipp.encoding.IppPacket;
import com.p020hp.jipp.model.JobState;
import com.p020hp.jipp.model.Types;
import java.util.UUID;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

public final class C1668b {

    public final UUID f891a;

    public final UUID f892b;

    public final AttributeGroup f893c;

    public final AttributeGroup f894d;

    public final a f895e;

    public final boolean f896f;

    public final Lazy f897g;

    public final Lazy f898h;

    public static final class a {

        public static final C3355a f899d = new C3355a();

        public final JobState f900a;

        public final Throwable f901b;

        public final IppPacket f902c;

        public static final class C3355a {
        }

        public a() {
            this(null, 0 == true ? 1 : 0, 0 == true ? 1 : 0, 7);
        }

        public a(JobState state, Throwable th, IppPacket ippPacket) {
            Intrinsics.checkNotNullParameter(state, "state");
            this.f900a = state;
            this.f901b = th;
            this.f902c = ippPacket;
        }

        public a(JobState jobState, Throwable th, IppPacket ippPacket, int i) {
            this((i & 1) != 0 ? JobState.completed : jobState, (i & 2) != 0 ? null : th, null);
        }

        public final Throwable m471a() {
            return this.f901b;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof a)) {
                return false;
            }
            a aVar = (a) obj;
            return Intrinsics.areEqual(this.f900a, aVar.f900a) && Intrinsics.areEqual(this.f901b, aVar.f901b) && Intrinsics.areEqual(this.f902c, aVar.f902c);
        }

        public int hashCode() {
            int iHashCode = this.f900a.hashCode() * 31;
            Throwable th = this.f901b;
            int iHashCode2 = (iHashCode + (th == null ? 0 : th.hashCode())) * 31;
            IppPacket ippPacket = this.f902c;
            return iHashCode2 + (ippPacket != null ? ippPacket.hashCode() : 0);
        }

        public String toString() {
            String str;
            String str2;
            StringBuilder sbAppend = new StringBuilder("Result(state=").append(this.f900a.getName());
            Throwable th = this.f901b;
            String str3 = "";
            if (th == null || (str = ", e=" + th) == null) {
                str = "";
            }
            StringBuilder sbAppend2 = sbAppend.append(str);
            IppPacket ippPacket = this.f902c;
            if (ippPacket != null && (str2 = ", " + ippPacket) != null) {
                str3 = str2;
            }
            return sbAppend2.append(str3).append(')').toString();
        }
    }

    public static final class b extends Lambda implements Function0<String> {
        public b() {
            super(0);
        }

        @Override
        public String invoke() {
            String string;
            AttributeGroup attributeGroup = C1668b.this.f893c;
            return (attributeGroup == null || (string = attributeGroup.getString(Types.jobName)) == null) ? "unknown" : string;
        }
    }

    public static final class c extends Lambda implements Function0<JobState> {
        public c() {
            super(0);
        }

        @Override
        public JobState invoke() {
            JobState jobState;
            a aVar = C1668b.this.f895e;
            if (aVar != null && (jobState = aVar.f900a) != null) {
                return jobState;
            }
            AttributeGroup attributeGroup = C1668b.this.f893c;
            JobState jobState2 = attributeGroup != null ? (JobState) attributeGroup.getValue(Types.jobState) : null;
            return jobState2 == null ? JobState.pending : jobState2;
        }
    }

    public C1668b(UUID id, UUID printerId, AttributeGroup attributeGroup, AttributeGroup attributeGroup2, a aVar) {
        Intrinsics.checkNotNullParameter(id, "id");
        Intrinsics.checkNotNullParameter(printerId, "printerId");
        this.f891a = id;
        this.f892b = printerId;
        this.f893c = attributeGroup;
        this.f894d = attributeGroup2;
        this.f895e = aVar;
        this.f896f = aVar != null;
        this.f897g = LazyKt.lazy(new b());
        this.f898h = LazyKt.lazy(new c());
    }

    public C1668b(UUID uuid, UUID uuid2, AttributeGroup attributeGroup, AttributeGroup attributeGroup2, a aVar, int i) {
        UUID uuid3;
        if ((i & 1) != 0) {
            UUID uuidRandomUUID = UUID.randomUUID();
            Intrinsics.checkNotNullExpressionValue(uuidRandomUUID, "randomUUID()");
            uuid3 = uuidRandomUUID;
        } else {
            uuid3 = null;
        }
        this(uuid3, uuid2, (i & 4) != 0 ? null : attributeGroup, null, null);
    }

    public static C1668b m465a(C1668b c1668b, UUID uuid, UUID uuid2, AttributeGroup attributeGroup, AttributeGroup attributeGroup2, a aVar, int i) {
        UUID id = (i & 1) != 0 ? c1668b.f891a : null;
        UUID printerId = (i & 2) != 0 ? c1668b.f892b : null;
        if ((i & 4) != 0) {
            attributeGroup = c1668b.f893c;
        }
        AttributeGroup attributeGroup3 = attributeGroup;
        if ((i & 8) != 0) {
            attributeGroup2 = c1668b.f894d;
        }
        AttributeGroup attributeGroup4 = attributeGroup2;
        if ((i & 16) != 0) {
            aVar = c1668b.f895e;
        }
        c1668b.getClass();
        Intrinsics.checkNotNullParameter(id, "id");
        Intrinsics.checkNotNullParameter(printerId, "printerId");
        return new C1668b(id, printerId, attributeGroup3, attributeGroup4, aVar);
    }

    public final AttributeGroup m466a() {
        return this.f893c;
    }

    public final UUID m467b() {
        return this.f891a;
    }

    public final UUID m468c() {
        return this.f892b;
    }

    public final a m469d() {
        return this.f895e;
    }

    public final JobState m470e() {
        return (JobState) this.f898h.getValue();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof C1668b)) {
            return false;
        }
        C1668b c1668b = (C1668b) obj;
        return Intrinsics.areEqual(this.f891a, c1668b.f891a) && Intrinsics.areEqual(this.f892b, c1668b.f892b) && Intrinsics.areEqual(this.f893c, c1668b.f893c) && Intrinsics.areEqual(this.f894d, c1668b.f894d) && Intrinsics.areEqual(this.f895e, c1668b.f895e);
    }

    public int hashCode() {
        int iHashCode = ((this.f891a.hashCode() * 31) + this.f892b.hashCode()) * 31;
        AttributeGroup attributeGroup = this.f893c;
        int iHashCode2 = (iHashCode + (attributeGroup == null ? 0 : attributeGroup.hashCode())) * 31;
        AttributeGroup attributeGroup2 = this.f894d;
        int iHashCode3 = (iHashCode2 + (attributeGroup2 == null ? 0 : attributeGroup2.hashCode())) * 31;
        a aVar = this.f895e;
        return iHashCode3 + (aVar != null ? aVar.hashCode() : 0);
    }

    public String toString() {
        StringBuilder sbAppend = new StringBuilder("PrintJob(").append(C1740o0.m599a(this.f891a)).append(", name=").append((String) this.f897g.getValue()).append(", ");
        Object obj = this.f895e;
        if (obj == null) {
            obj = "state=" + m470e().getName();
        }
        return sbAppend.append(obj).append(')').toString();
    }
}
