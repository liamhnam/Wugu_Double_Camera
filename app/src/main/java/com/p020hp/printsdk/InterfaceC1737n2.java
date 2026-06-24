package com.p020hp.printsdk;

import androidx.lifecycle.LiveData;
import com.p020hp.open.printsdk.HpPrintJob;
import com.p020hp.open.printsdk.HpPrintRequest;
import com.p020hp.open.printsdk.HpPrinter;
import com.p020hp.open.printsdk.PrinterWithJobs;
import java.util.List;
import java.util.UUID;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;

public interface InterfaceC1737n2 {
    void mo588a(HpPrinter hpPrinter, LiveData<HpPrintJob> liveData, HpPrintRequest hpPrintRequest);

    void delDoneJobHistory(UUID[] uuidArr, Function1<? super List<PrinterWithJobs>, Unit> function1);

    void jobHistory(UUID[] uuidArr, Function1<? super List<PrinterWithJobs>, Unit> function1);

    void jobs(UUID[] uuidArr, int i, int i2, Function1<? super List<PrinterWithJobs>, Unit> function1);
}
