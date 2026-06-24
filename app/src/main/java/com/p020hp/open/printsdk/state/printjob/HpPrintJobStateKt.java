package com.p020hp.open.printsdk.state.printjob;

import com.p020hp.jipp.model.WhichJobs;
import com.p020hp.open.printsdk.state.Reason;
import com.p020hp.open.printsdk.state.printjob.HpPrintJobReason;
import com.p020hp.open.printsdk.state.printjob.HpPrintJobState;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

@Metadata(m1292d1 = {"\u0000\u001c\n\u0000\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\u001a\u0006\u0010\n\u001a\u00020\u000b\u001a\u0006\u0010\f\u001a\u00020\u000b\u001a\u001a\u0010\r\u001a\u00020\u000b*\u00020\u00012\u000e\b\u0002\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000f\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0003\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0004\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0005\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0006\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0007\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\b\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\t\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0011"}, m1293d2 = {WhichJobs.aborted, "", WhichJobs.canceled, WhichJobs.completed, "expired", WhichJobs.pending, "pendingHeld", "processing", "processingStopped", "unknown", "issueCanceledDueToPrinterNotSupportPrintJobState", "Lcom/hp/open/printsdk/state/printjob/HpPrintJobState;", "issueExpiredPrintJobState", "toHpPrintJobState", "reasons", "", "Lcom/hp/open/printsdk/state/Reason;", "print-core_thirdPartyRelease"}, m1294k = 2, m1295mv = {1, 6, 0}, m1297xi = 48)
public final class HpPrintJobStateKt {
    public static final HpPrintJobState issueCanceledDueToPrinterNotSupportPrintJobState() {
        return toHpPrintJobState(7, CollectionsKt.listOf(HpPrintJobReason.CanceledDueToPrinterNotSupport.INSTANCE));
    }

    public static final HpPrintJobState issueExpiredPrintJobState() {
        return toHpPrintJobState(10, CollectionsKt.listOf(HpPrintJobReason.CanceledDueToSdkExpiration.INSTANCE));
    }

    public static final HpPrintJobState toHpPrintJobState(int i, List<? extends Reason> reasons) {
        Intrinsics.checkNotNullParameter(reasons, "reasons");
        if (i == -1) {
            return new HpPrintJobState.Unknown(reasons);
        }
        switch (i) {
        }
        return new HpPrintJobState.Unknown(reasons);
    }

    public static HpPrintJobState toHpPrintJobState$default(int i, List list, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            list = new ArrayList();
        }
        return toHpPrintJobState(i, list);
    }
}
