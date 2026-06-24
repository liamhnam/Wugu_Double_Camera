package com.p020hp.open.printsdk;

import com.p020hp.jipp.model.PrinterServiceType;
import com.wugu.doublecamera.enums.MqttCmdEnum;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(m1292d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u001b\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0002\u0010\u0007J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\u000f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005HÆ\u0003J#\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001J\t\u0010\u0014\u001a\u00020\u0015HÖ\u0001R\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0016"}, m1293d2 = {"Lcom/hp/open/printsdk/PrinterWithJobs;", "", MqttCmdEnum.C2S_PRINTER_ERROR, "Lcom/hp/open/printsdk/HpPrinter;", "jobLists", "", "Lcom/hp/open/printsdk/HpPrintJob;", "(Lcom/hp/open/printsdk/HpPrinter;Ljava/util/List;)V", "getJobLists", "()Ljava/util/List;", "getPrinter", "()Lcom/hp/open/printsdk/HpPrinter;", "component1", "component2", PrinterServiceType.copy, "equals", "", "other", "hashCode", "", "toString", "", "print-core_thirdPartyRelease"}, m1294k = 1, m1295mv = {1, 6, 0}, m1297xi = 48)
public final class PrinterWithJobs {

    public final HpPrinter f811a;

    public final List<HpPrintJob> f812b;

    public PrinterWithJobs(HpPrinter printer, List<? extends HpPrintJob> jobLists) {
        Intrinsics.checkNotNullParameter(printer, "printer");
        Intrinsics.checkNotNullParameter(jobLists, "jobLists");
        this.f811a = printer;
        this.f812b = jobLists;
    }

    public static PrinterWithJobs copy$default(PrinterWithJobs printerWithJobs, HpPrinter hpPrinter, List list, int i, Object obj) {
        if ((i & 1) != 0) {
            hpPrinter = printerWithJobs.f811a;
        }
        if ((i & 2) != 0) {
            list = printerWithJobs.f812b;
        }
        return printerWithJobs.copy(hpPrinter, list);
    }

    public final HpPrinter getF811a() {
        return this.f811a;
    }

    public final List<HpPrintJob> component2() {
        return this.f812b;
    }

    public final PrinterWithJobs copy(HpPrinter printer, List<? extends HpPrintJob> jobLists) {
        Intrinsics.checkNotNullParameter(printer, "printer");
        Intrinsics.checkNotNullParameter(jobLists, "jobLists");
        return new PrinterWithJobs(printer, jobLists);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof PrinterWithJobs)) {
            return false;
        }
        PrinterWithJobs printerWithJobs = (PrinterWithJobs) other;
        return Intrinsics.areEqual(this.f811a, printerWithJobs.f811a) && Intrinsics.areEqual(this.f812b, printerWithJobs.f812b);
    }

    public final List<HpPrintJob> getJobLists() {
        return this.f812b;
    }

    public final HpPrinter getPrinter() {
        return this.f811a;
    }

    public int hashCode() {
        return (this.f811a.hashCode() * 31) + this.f812b.hashCode();
    }

    public String toString() {
        return "PrinterWithJobs(printer=" + this.f811a + ", jobLists=" + this.f812b + ')';
    }
}
