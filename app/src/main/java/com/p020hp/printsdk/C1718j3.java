package com.p020hp.printsdk;

import com.p020hp.open.printsdk.state.printjob.HpPrintJobState;
import com.p020hp.open.printsdk.state.printjob.HpPrintJobStateKt;
import java.net.URI;
import java.util.UUID;
import kotlin.jvm.internal.Intrinsics;

public final class C1718j3 {
    public final int m564a(HpPrintJobState info) {
        Intrinsics.checkNotNullParameter(info, "info");
        return info.getF867b();
    }

    public final HpPrintJobState m565a(int i) {
        return HpPrintJobStateKt.toHpPrintJobState$default(i, null, 1, null);
    }

    public final String m566a(UUID uuid) {
        if (uuid != null) {
            return uuid.toString();
        }
        return null;
    }

    public final URI m567a(String str) {
        if (str != null) {
            return URI.create(str);
        }
        return null;
    }
}
