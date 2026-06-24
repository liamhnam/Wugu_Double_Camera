package com.p020hp.open.printsdk;

import com.p020hp.jipp.model.PrinterServiceType;
import com.p020hp.open.printsdk.options.PrintOptions;
import com.wugu.doublecamera.enums.MqttCmdEnum;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(m1292d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0007HÆ\u0003J'\u0010\u0012\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0007HÆ\u0001J\u0013\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0016\u001a\u00020\u0017HÖ\u0001J\t\u0010\u0018\u001a\u00020\u0019HÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e¨\u0006\u001a"}, m1293d2 = {"Lcom/hp/open/printsdk/HpPrintRequest;", "", MqttCmdEnum.C2S_PRINTER_ERROR, "Lcom/hp/open/printsdk/HpPrinter;", "printFile", "Lcom/hp/open/printsdk/HpPrintFile;", "printOptions", "Lcom/hp/open/printsdk/options/PrintOptions;", "(Lcom/hp/open/printsdk/HpPrinter;Lcom/hp/open/printsdk/HpPrintFile;Lcom/hp/open/printsdk/options/PrintOptions;)V", "getPrintFile", "()Lcom/hp/open/printsdk/HpPrintFile;", "getPrintOptions", "()Lcom/hp/open/printsdk/options/PrintOptions;", "getPrinter", "()Lcom/hp/open/printsdk/HpPrinter;", "component1", "component2", "component3", PrinterServiceType.copy, "equals", "", "other", "hashCode", "", "toString", "", "print-core_thirdPartyRelease"}, m1294k = 1, m1295mv = {1, 6, 0}, m1297xi = 48)
public final class HpPrintRequest {

    public final HpPrinter f788a;

    public final HpPrintFile f789b;

    public final PrintOptions f790c;

    public HpPrintRequest(HpPrinter printer, HpPrintFile printFile, PrintOptions printOptions) {
        Intrinsics.checkNotNullParameter(printer, "printer");
        Intrinsics.checkNotNullParameter(printFile, "printFile");
        Intrinsics.checkNotNullParameter(printOptions, "printOptions");
        this.f788a = printer;
        this.f789b = printFile;
        this.f790c = printOptions;
    }

    public static HpPrintRequest copy$default(HpPrintRequest hpPrintRequest, HpPrinter hpPrinter, HpPrintFile hpPrintFile, PrintOptions printOptions, int i, Object obj) {
        if ((i & 1) != 0) {
            hpPrinter = hpPrintRequest.f788a;
        }
        if ((i & 2) != 0) {
            hpPrintFile = hpPrintRequest.f789b;
        }
        if ((i & 4) != 0) {
            printOptions = hpPrintRequest.f790c;
        }
        return hpPrintRequest.copy(hpPrinter, hpPrintFile, printOptions);
    }

    public final HpPrinter getF788a() {
        return this.f788a;
    }

    public final HpPrintFile getF789b() {
        return this.f789b;
    }

    public final PrintOptions getF790c() {
        return this.f790c;
    }

    public final HpPrintRequest copy(HpPrinter printer, HpPrintFile printFile, PrintOptions printOptions) {
        Intrinsics.checkNotNullParameter(printer, "printer");
        Intrinsics.checkNotNullParameter(printFile, "printFile");
        Intrinsics.checkNotNullParameter(printOptions, "printOptions");
        return new HpPrintRequest(printer, printFile, printOptions);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof HpPrintRequest)) {
            return false;
        }
        HpPrintRequest hpPrintRequest = (HpPrintRequest) other;
        return Intrinsics.areEqual(this.f788a, hpPrintRequest.f788a) && Intrinsics.areEqual(this.f789b, hpPrintRequest.f789b) && Intrinsics.areEqual(this.f790c, hpPrintRequest.f790c);
    }

    public final HpPrintFile getPrintFile() {
        return this.f789b;
    }

    public final PrintOptions getPrintOptions() {
        return this.f790c;
    }

    public final HpPrinter getPrinter() {
        return this.f788a;
    }

    public int hashCode() {
        return (((this.f788a.hashCode() * 31) + this.f789b.hashCode()) * 31) + this.f790c.hashCode();
    }

    public String toString() {
        return "HpPrintRequest(printer=" + this.f788a + ", printFile=" + this.f789b + ", printOptions=" + this.f790c + ')';
    }
}
