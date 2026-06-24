package com.p020hp.printsdk;

import com.p020hp.open.printsdk.HpPrintJob;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

public final class C1756r2 extends Lambda implements Function0<String> {

    public final HpPrintJob f1675a;

    public C1756r2(HpPrintJob hpPrintJob) {
        super(0);
        this.f1675a = hpPrintJob;
    }

    @Override
    public String invoke() {
        return "Update database: " + this.f1675a;
    }
}
