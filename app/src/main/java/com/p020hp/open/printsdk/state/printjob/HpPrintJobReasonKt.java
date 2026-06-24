package com.p020hp.open.printsdk.state.printjob;

import com.p020hp.open.printsdk.state.Reason;
import com.p020hp.open.printsdk.state.printjob.HpPrintJobReason;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

@Metadata(m1292d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u000e\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\u001a\u0018\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010*\n\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0010\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0003\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0004\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0005\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0006\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0007\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\b\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\t\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\n\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u000b\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\f\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\r\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u000e\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0012"}, m1293d2 = {"keyword_aborted_by_system", "", "keyword_job_canceled_at_device", "keyword_job_canceled_by_operator", "keyword_job_canceled_by_user", "keyword_job_canceled_due_to_sdk_expiration", "keyword_job_canceled_due_to_un_support_printer", "keyword_job_completed_successfully", "keyword_job_completed_with_errors", "keyword_job_completed_with_warnings", "keyword_job_data_insufficient", "keyword_job_printing", "keyword_none", "keyword_other", "keyword_printer_stopped", "toJobReasons", "", "Lcom/hp/open/printsdk/state/printjob/HpPrintJobReason;", "print-core_thirdPartyRelease"}, m1294k = 2, m1295mv = {1, 6, 0}, m1297xi = 48)
public final class HpPrintJobReasonKt {
    public static final List<HpPrintJobReason> toJobReasons(List<String> list) {
        Object next;
        if (list != null) {
            ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
            for (String str : list) {
                Iterator<T> it = HpPrintJobReason.INSTANCE.getValues().iterator();
                while (true) {
                    if (!it.hasNext()) {
                        next = null;
                        break;
                    }
                    next = it.next();
                    if (Intrinsics.areEqual(((HpPrintJobReason) next).getF865a(), str)) {
                        break;
                    }
                }
                Reason reason = (HpPrintJobReason) next;
                if (reason == null) {
                    reason = HpPrintJobReason.Other.INSTANCE;
                }
                arrayList.add(reason);
            }
            List<HpPrintJobReason> listDistinct = CollectionsKt.distinct(arrayList);
            if (listDistinct != null) {
                return listDistinct;
            }
        }
        return new ArrayList();
    }
}
