package com.p020hp.printsdk;

import com.p020hp.open.printsdk.HpPrintJob;
import com.p020hp.open.printsdk.HpPrintRequest;
import com.p020hp.open.printsdk.state.printjob.HpPrintJobState;
import java.util.UUID;
import kotlin.jvm.internal.Intrinsics;

public final class C1692e4 extends HpPrintJob {

    public final long f1193b = System.currentTimeMillis();

    public final HpPrintJobState f1194c;

    public final HpPrintRequest f1195d;

    public C1692e4(HpPrintJobState hpPrintJobState, HpPrintRequest hpPrintRequest) {
        this.f1194c = hpPrintJobState;
        this.f1195d = hpPrintRequest;
    }

    @Override
    public long getCreateTime() {
        return this.f1193b;
    }

    @Override
    public UUID getId() {
        UUID uuidRandomUUID = UUID.randomUUID();
        Intrinsics.checkNotNullExpressionValue(uuidRandomUUID, "randomUUID()");
        return uuidRandomUUID;
    }

    @Override
    public long getJobDoneTime() {
        return this.f1193b;
    }

    @Override
    public String getJobName() {
        return "";
    }

    @Override
    public HpPrintJobState getJobStateInfo() {
        return this.f1194c;
    }

    @Override
    public UUID getPrinterId() {
        return this.f1195d.getPrinter().getId();
    }

    @Override
    public long getSubmitTime() {
        return this.f1193b;
    }
}
