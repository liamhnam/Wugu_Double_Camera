package com.p020hp.printsdk;

import com.p020hp.mobile.common.utils.Logger;
import com.p020hp.open.printsdk.state.printer.PrinterReason;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.eclipse.paho.android.service.MqttServiceConstants;

public final class C1782w3 {

    public static final Logger f1952a = new Logger("IppPrinterReason");

    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final com.p020hp.open.printsdk.state.printer.PrinterReason m679a(java.lang.String r5) {
        /*
            Method dump skipped, instruction units count: 646
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.p020hp.printsdk.C1782w3.m679a(java.lang.String):com.hp.open.printsdk.state.printer.PrinterReason");
    }

    public static final Map<String, List<PrinterReason>> m680a(List<String> list) {
        Intrinsics.checkNotNullParameter(list, "<this>");
        HashMap map = new HashMap();
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        ArrayList arrayList4 = new ArrayList();
        for (String str : list) {
            PrinterReason printerReasonM679a = m679a(str);
            if (printerReasonM679a != null) {
                if (StringsKt.endsWith$default(str, "-error", false, 2, (Object) null)) {
                    arrayList2.add(printerReasonM679a);
                } else if (StringsKt.endsWith$default(str, "-warning", false, 2, (Object) null)) {
                    arrayList.add(printerReasonM679a);
                } else if (StringsKt.endsWith$default(str, "-report", false, 2, (Object) null)) {
                    arrayList3.add(printerReasonM679a);
                } else {
                    arrayList4.add(printerReasonM679a);
                }
            }
        }
        map.put("warning", CollectionsKt.distinct(arrayList));
        map.put(MqttServiceConstants.TRACE_ERROR, CollectionsKt.distinct(arrayList2));
        map.put("report", CollectionsKt.distinct(arrayList3));
        map.put("unknown", CollectionsKt.distinct(arrayList4));
        return map;
    }
}
