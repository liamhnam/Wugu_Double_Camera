package com.p020hp.open.printsdk.state.printer;

import com.p020hp.open.printsdk.state.printer.PrinterReason;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(m1292d1 = {"\u00002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\b\n\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\u00020\u0003:\u0004\u0011\u0012\u0013\u0014B?\b\u0004\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0005\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00028\u00000\u0005\u0012\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00028\u00000\u0005\u0012\f\u0010\b\u001a\b\u0012\u0004\u0012\u00028\u00000\u0005¢\u0006\u0002\u0010\tJ\u0006\u0010\u000f\u001a\u00020\u0010R\u0017\u0010\u0006\u001a\b\u0012\u0004\u0012\u00028\u00000\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0017\u0010\u0007\u001a\b\u0012\u0004\u0012\u00028\u00000\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u000bR\u0017\u0010\b\u001a\b\u0012\u0004\u0012\u00028\u00000\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000bR\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000b\u0082\u0001\u0004\u0015\u0016\u0017\u0018¨\u0006\u0019"}, m1293d2 = {"Lcom/hp/open/printsdk/state/printer/HpPrinterState;", "T", "Lcom/hp/open/printsdk/state/printer/PrinterReason;", "", "warningReasons", "", "errorReasons", "reportReasons", "unknownReasons", "(Ljava/util/List;Ljava/util/List;Ljava/util/List;Ljava/util/List;)V", "getErrorReasons", "()Ljava/util/List;", "getReportReasons", "getUnknownReasons", "getWarningReasons", "toString", "", "Idle", "Offline", "Processing", "Stopped", "Lcom/hp/open/printsdk/state/printer/HpPrinterState$Offline;", "Lcom/hp/open/printsdk/state/printer/HpPrinterState$Idle;", "Lcom/hp/open/printsdk/state/printer/HpPrinterState$Processing;", "Lcom/hp/open/printsdk/state/printer/HpPrinterState$Stopped;", "print-core_thirdPartyRelease"}, m1294k = 1, m1295mv = {1, 6, 0}, m1297xi = 48)
public abstract class HpPrinterState<T extends PrinterReason> {

    public final List<T> f858a;

    public final List<T> f859b;

    public final List<T> f860c;

    public final List<T> f861d;

    @Metadata(m1292d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0005\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B=\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00020\u0004\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00020\u0004\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00020\u0004\u0012\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00020\u0004¢\u0006\u0002\u0010\b¨\u0006\t"}, m1293d2 = {"Lcom/hp/open/printsdk/state/printer/HpPrinterState$Idle;", "Lcom/hp/open/printsdk/state/printer/HpPrinterState;", "Lcom/hp/open/printsdk/state/printer/PrinterReason;", "warningReasons", "", "errorReasons", "reportReasons", "unknownReasons", "(Ljava/util/List;Ljava/util/List;Ljava/util/List;Ljava/util/List;)V", "print-core_thirdPartyRelease"}, m1294k = 1, m1295mv = {1, 6, 0}, m1297xi = 48)
    public static final class Idle extends HpPrinterState<PrinterReason> {
        public Idle(List<? extends PrinterReason> warningReasons, List<? extends PrinterReason> errorReasons, List<? extends PrinterReason> reportReasons, List<? extends PrinterReason> unknownReasons) {
            super(warningReasons, errorReasons, reportReasons, unknownReasons, null);
            Intrinsics.checkNotNullParameter(warningReasons, "warningReasons");
            Intrinsics.checkNotNullParameter(errorReasons, "errorReasons");
            Intrinsics.checkNotNullParameter(reportReasons, "reportReasons");
            Intrinsics.checkNotNullParameter(unknownReasons, "unknownReasons");
        }
    }

    @Metadata(m1292d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003¨\u0006\u0004"}, m1293d2 = {"Lcom/hp/open/printsdk/state/printer/HpPrinterState$Offline;", "Lcom/hp/open/printsdk/state/printer/HpPrinterState;", "Lcom/hp/open/printsdk/state/printer/PrinterReason;", "()V", "print-core_thirdPartyRelease"}, m1294k = 1, m1295mv = {1, 6, 0}, m1297xi = 48)
    public static final class Offline extends HpPrinterState<PrinterReason> {
        public Offline() {
            super(CollectionsKt.emptyList(), CollectionsKt.emptyList(), CollectionsKt.emptyList(), CollectionsKt.emptyList(), null);
        }
    }

    @Metadata(m1292d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0005\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B=\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00020\u0004\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00020\u0004\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00020\u0004\u0012\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00020\u0004¢\u0006\u0002\u0010\b¨\u0006\t"}, m1293d2 = {"Lcom/hp/open/printsdk/state/printer/HpPrinterState$Processing;", "Lcom/hp/open/printsdk/state/printer/HpPrinterState;", "Lcom/hp/open/printsdk/state/printer/PrinterReason;", "warningReasons", "", "errorReasons", "reportReasons", "unknownReasons", "(Ljava/util/List;Ljava/util/List;Ljava/util/List;Ljava/util/List;)V", "print-core_thirdPartyRelease"}, m1294k = 1, m1295mv = {1, 6, 0}, m1297xi = 48)
    public static final class Processing extends HpPrinterState<PrinterReason> {
        public Processing(List<? extends PrinterReason> warningReasons, List<? extends PrinterReason> errorReasons, List<? extends PrinterReason> reportReasons, List<? extends PrinterReason> unknownReasons) {
            super(warningReasons, errorReasons, reportReasons, unknownReasons, null);
            Intrinsics.checkNotNullParameter(warningReasons, "warningReasons");
            Intrinsics.checkNotNullParameter(errorReasons, "errorReasons");
            Intrinsics.checkNotNullParameter(reportReasons, "reportReasons");
            Intrinsics.checkNotNullParameter(unknownReasons, "unknownReasons");
        }
    }

    @Metadata(m1292d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0005\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B=\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00020\u0004\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00020\u0004\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00020\u0004\u0012\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00020\u0004¢\u0006\u0002\u0010\b¨\u0006\t"}, m1293d2 = {"Lcom/hp/open/printsdk/state/printer/HpPrinterState$Stopped;", "Lcom/hp/open/printsdk/state/printer/HpPrinterState;", "Lcom/hp/open/printsdk/state/printer/PrinterReason;", "warningReasons", "", "errorReasons", "reportReasons", "unknownReasons", "(Ljava/util/List;Ljava/util/List;Ljava/util/List;Ljava/util/List;)V", "print-core_thirdPartyRelease"}, m1294k = 1, m1295mv = {1, 6, 0}, m1297xi = 48)
    public static final class Stopped extends HpPrinterState<PrinterReason> {
        public Stopped(List<? extends PrinterReason> warningReasons, List<? extends PrinterReason> errorReasons, List<? extends PrinterReason> reportReasons, List<? extends PrinterReason> unknownReasons) {
            super(warningReasons, errorReasons, reportReasons, unknownReasons, null);
            Intrinsics.checkNotNullParameter(warningReasons, "warningReasons");
            Intrinsics.checkNotNullParameter(errorReasons, "errorReasons");
            Intrinsics.checkNotNullParameter(reportReasons, "reportReasons");
            Intrinsics.checkNotNullParameter(unknownReasons, "unknownReasons");
        }
    }

    public HpPrinterState(List<? extends T> list, List<? extends T> list2, List<? extends T> list3, List<? extends T> list4) {
        this.f858a = list;
        this.f859b = list2;
        this.f860c = list3;
        this.f861d = list4;
    }

    public HpPrinterState(List list, List list2, List list3, List list4, DefaultConstructorMarker defaultConstructorMarker) {
        this(list, list2, list3, list4);
    }

    public final List<T> getErrorReasons() {
        return this.f859b;
    }

    public final List<T> getReportReasons() {
        return this.f860c;
    }

    public final List<T> getUnknownReasons() {
        return this.f861d;
    }

    public final List<T> getWarningReasons() {
        return this.f858a;
    }

    public final String toString() {
        return getClass().getSimpleName() + "(reportReasons: " + this.f860c + ", warningReasons: " + this.f858a + ", errorReasons: " + this.f859b + ", unknownReasons: " + this.f861d + ')';
    }
}
