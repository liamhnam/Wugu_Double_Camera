package com.p020hp.printsdk;

import com.p020hp.jipp.encoding.AttributeGroup;
import com.p020hp.jipp.model.Types;
import com.p020hp.open.printsdk.HpPrintJob;
import com.p020hp.open.printsdk.state.Reason;
import com.p020hp.open.printsdk.state.printer.PrinterReason;
import com.p020hp.open.printsdk.state.printjob.HpPrintJobReason;
import com.p020hp.open.printsdk.state.printjob.HpPrintJobReasonKt;
import com.p020hp.open.printsdk.state.printjob.HpPrintJobState;
import com.p020hp.open.printsdk.state.printjob.HpPrintJobStateKt;
import com.p020hp.printsdk.C1668b;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SpreadBuilder;
import org.eclipse.paho.android.service.MqttServiceConstants;

public final class C1772u3 extends HpPrintJob {

    public final UUID f1864b;

    public final C1668b f1865c;

    public final List<String> f1866d;

    public final Long f1867e;

    public final String f1868f;

    public final HpPrintJobState f1869g;

    public final long f1870h;

    public final long f1871i;

    public final long f1872j;

    public final UUID f1873k;

    public int f1874l;

    public C1772u3(UUID id, C1668b ippPrintJob, List<String> printerStatusReasons, Long l) {
        Integer num;
        Throwable thM471a;
        String string;
        Intrinsics.checkNotNullParameter(id, "id");
        Intrinsics.checkNotNullParameter(ippPrintJob, "ippPrintJob");
        Intrinsics.checkNotNullParameter(printerStatusReasons, "printerStatusReasons");
        this.f1864b = id;
        this.f1865c = ippPrintJob;
        this.f1866d = printerStatusReasons;
        this.f1867e = l;
        AttributeGroup attributeGroupM466a = ippPrintJob.m466a();
        this.f1868f = (attributeGroupM466a == null || (string = attributeGroupM466a.getString(Types.jobName)) == null) ? "unknown content" : string;
        AttributeGroup attributeGroupM466a2 = ippPrintJob.m466a();
        HpPrintJobReason.Exception exception = null;
        List<HpPrintJobReason> jobReasons = HpPrintJobReasonKt.toJobReasons(attributeGroupM466a2 != null ? attributeGroupM466a2.getStrings(Types.jobStateReasons) : null);
        C1668b.a aVarM469d = ippPrintJob.m469d();
        if (aVarM469d != null && (thM471a = aVarM469d.m471a()) != null) {
            exception = new HpPrintJobReason.Exception(thM471a);
        }
        if (exception != null) {
            ArrayList arrayListArrayListOf = CollectionsKt.arrayListOf(exception);
            ArrayList arrayList = new ArrayList();
            for (Object obj : jobReasons) {
                if (!(((HpPrintJobReason) obj) instanceof HpPrintJobReason.None)) {
                    arrayList.add(obj);
                }
            }
            arrayListArrayListOf.addAll(arrayList);
            jobReasons = arrayListArrayListOf;
        }
        List list = (List) MapsKt.getValue(C1782w3.m680a(this.f1866d), MqttServiceConstants.TRACE_ERROR);
        SpreadBuilder spreadBuilder = new SpreadBuilder(2);
        Object[] array = list.toArray(new PrinterReason[0]);
        if (array == null) {
            throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<T of kotlin.collections.ArraysKt__ArraysJVMKt.toTypedArray>");
        }
        spreadBuilder.addSpread(array);
        Object[] array2 = jobReasons.toArray(new HpPrintJobReason[0]);
        if (array2 == null) {
            throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<T of kotlin.collections.ArraysKt__ArraysJVMKt.toTypedArray>");
        }
        spreadBuilder.addSpread(array2);
        ArrayList arrayListArrayListOf2 = CollectionsKt.arrayListOf(spreadBuilder.toArray(new Reason[spreadBuilder.size()]));
        ArrayList arrayList2 = new ArrayList();
        for (Object obj2 : arrayListArrayListOf2) {
            if (!(((Reason) obj2) instanceof HpPrintJobReason.None)) {
                arrayList2.add(obj2);
            }
        }
        this.f1869g = HpPrintJobStateKt.toHpPrintJobState(this.f1865c.m470e().getCode(), arrayList2);
        Long l2 = this.f1867e;
        this.f1870h = l2 != null ? l2.longValue() : System.currentTimeMillis();
        Long l3 = this.f1867e;
        this.f1871i = l3 != null ? l3.longValue() : System.currentTimeMillis();
        this.f1872j = isDone() ? System.currentTimeMillis() : 0L;
        this.f1873k = this.f1865c.m468c();
        AttributeGroup attributeGroupM466a3 = this.f1865c.m466a();
        this.f1874l = (attributeGroupM466a3 == null || (num = (Integer) attributeGroupM466a3.getValue(Types.jobImpressionsCompleted)) == null) ? -1 : num.intValue();
    }

    public C1772u3(UUID uuid, C1668b c1668b, List list, Long l, int i) {
        this(uuid, c1668b, (i & 4) != 0 ? CollectionsKt.emptyList() : null, l);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof C1772u3)) {
            return false;
        }
        C1772u3 c1772u3 = (C1772u3) obj;
        return Intrinsics.areEqual(this.f1864b, c1772u3.f1864b) && Intrinsics.areEqual(this.f1865c, c1772u3.f1865c) && Intrinsics.areEqual(this.f1866d, c1772u3.f1866d) && Intrinsics.areEqual(this.f1867e, c1772u3.f1867e);
    }

    @Override
    public long getCreateTime() {
        return this.f1870h;
    }

    @Override
    public UUID getId() {
        return this.f1864b;
    }

    @Override
    public int getF787a() {
        return this.f1874l;
    }

    @Override
    public List<String> getIppStateReasons() {
        List<String> listEmptyList;
        AttributeGroup attributeGroup = this.f1865c.f893c;
        if (attributeGroup == null || (listEmptyList = attributeGroup.getStrings(Types.jobStateReasons)) == null) {
            listEmptyList = CollectionsKt.emptyList();
        }
        SpreadBuilder spreadBuilder = new SpreadBuilder(2);
        Object[] array = this.f1866d.toArray(new String[0]);
        if (array == null) {
            throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<T of kotlin.collections.ArraysKt__ArraysJVMKt.toTypedArray>");
        }
        spreadBuilder.addSpread(array);
        Object[] array2 = listEmptyList.toArray(new String[0]);
        if (array2 == null) {
            throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<T of kotlin.collections.ArraysKt__ArraysJVMKt.toTypedArray>");
        }
        spreadBuilder.addSpread(array2);
        return CollectionsKt.arrayListOf(spreadBuilder.toArray(new String[spreadBuilder.size()]));
    }

    @Override
    public long getJobDoneTime() {
        return this.f1872j;
    }

    @Override
    public String getJobName() {
        return this.f1868f;
    }

    @Override
    public HpPrintJobState getJobStateInfo() {
        return this.f1869g;
    }

    @Override
    public UUID getPrinterId() {
        return this.f1873k;
    }

    @Override
    public long getSubmitTime() {
        return this.f1871i;
    }

    public int hashCode() {
        int iHashCode = ((((this.f1864b.hashCode() * 31) + this.f1865c.hashCode()) * 31) + this.f1866d.hashCode()) * 31;
        Long l = this.f1867e;
        return iHashCode + (l == null ? 0 : l.hashCode());
    }

    @Override
    public boolean isDone() {
        return this.f1865c.f896f;
    }

    @Override
    public void setImpressionsCompleted(int i) {
        this.f1874l = i;
    }
}
