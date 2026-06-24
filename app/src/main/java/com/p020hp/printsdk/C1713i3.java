package com.p020hp.printsdk;

import java.util.List;
import kotlin.jvm.internal.Intrinsics;

public final class C1713i3 {

    public final C1708h3 f1342a;

    public final List<C1766t2> f1343b;

    public C1713i3(C1708h3 printer, List<C1766t2> jobLists) {
        Intrinsics.checkNotNullParameter(printer, "printer");
        Intrinsics.checkNotNullParameter(jobLists, "jobLists");
        this.f1342a = printer;
        this.f1343b = jobLists;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof C1713i3)) {
            return false;
        }
        C1713i3 c1713i3 = (C1713i3) obj;
        return Intrinsics.areEqual(this.f1342a, c1713i3.f1342a) && Intrinsics.areEqual(this.f1343b, c1713i3.f1343b);
    }

    public int hashCode() {
        return (this.f1342a.hashCode() * 31) + this.f1343b.hashCode();
    }

    public String toString() {
        return "InnerPrinterWithJobs(printer=" + this.f1342a + ", jobLists=" + this.f1343b + ')';
    }
}
