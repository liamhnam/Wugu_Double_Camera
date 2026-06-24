package com.p020hp.open.printsdk;

import com.p020hp.open.printsdk.state.printjob.HpPrintJobState;
import java.util.List;
import java.util.UUID;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;

@Metadata(m1292d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010 \n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\b&\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u000e\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u00140 H\u0016J\b\u0010!\u001a\u00020\"H\u0016J\u0006\u0010#\u001a\u00020\u0014R\u0012\u0010\u0003\u001a\u00020\u0004X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006R\u0012\u0010\u0007\u001a\u00020\bX¦\u0004¢\u0006\u0006\u001a\u0004\b\t\u0010\nR\u001a\u0010\u000b\u001a\u00020\fX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u0012\u0010\u0011\u001a\u00020\u0004X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0006R\u0012\u0010\u0013\u001a\u00020\u0014X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u0016R\u0012\u0010\u0017\u001a\u00020\u0018X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0019\u0010\u001aR\u0012\u0010\u001b\u001a\u00020\bX¦\u0004¢\u0006\u0006\u001a\u0004\b\u001c\u0010\nR\u0012\u0010\u001d\u001a\u00020\u0004X¦\u0004¢\u0006\u0006\u001a\u0004\b\u001e\u0010\u0006¨\u0006$"}, m1293d2 = {"Lcom/hp/open/printsdk/HpPrintJob;", "", "()V", "createTime", "", "getCreateTime", "()J", "id", "Ljava/util/UUID;", "getId", "()Ljava/util/UUID;", "impressionsCompleted", "", "getImpressionsCompleted", "()I", "setImpressionsCompleted", "(I)V", "jobDoneTime", "getJobDoneTime", "jobName", "", "getJobName", "()Ljava/lang/String;", "jobStateInfo", "Lcom/hp/open/printsdk/state/printjob/HpPrintJobState;", "getJobStateInfo", "()Lcom/hp/open/printsdk/state/printjob/HpPrintJobState;", "printerId", "getPrinterId", "submitTime", "getSubmitTime", "getIppStateReasons", "", "isDone", "", "toString", "print-core_thirdPartyRelease"}, m1294k = 1, m1295mv = {1, 6, 0}, m1297xi = 48)
public abstract class HpPrintJob {

    public int f787a = -1;

    public abstract long getCreateTime();

    public abstract UUID getId();

    public int getF787a() {
        return this.f787a;
    }

    public List<String> getIppStateReasons() {
        return CollectionsKt.emptyList();
    }

    public abstract long getJobDoneTime();

    public abstract String getJobName();

    public abstract HpPrintJobState getJobStateInfo();

    public abstract UUID getPrinterId();

    public abstract long getSubmitTime();

    public boolean isDone() {
        return (getJobStateInfo() instanceof HpPrintJobState.Canceled) || (getJobStateInfo() instanceof HpPrintJobState.Aborted) || (getJobStateInfo() instanceof HpPrintJobState.Completed) || (getJobStateInfo() instanceof HpPrintJobState.Unknown);
    }

    public void setImpressionsCompleted(int i) {
        this.f787a = i;
    }

    public final String toString() {
        return "HpPrintJob(id = " + getId() + ", name = " + getJobName() + ", impressionsCompleted = " + getF787a() + ", createTime = " + getCreateTime() + ", submitTime = " + getSubmitTime() + ", jobDoneTime = " + getJobDoneTime() + ")[" + getJobStateInfo() + ']';
    }
}
