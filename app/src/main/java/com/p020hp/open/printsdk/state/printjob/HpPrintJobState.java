package com.p020hp.open.printsdk.state.printjob;

import com.p020hp.open.printsdk.state.Reason;
import com.p020hp.printsdk.state.base.State;
import com.tom_roush.pdfbox.pdmodel.interactive.annotation.PDAnnotationRubberStamp;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(m1292d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010 \n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001:\t\n\u000b\f\r\u000e\u000f\u0010\u0011\u0012B\u001d\b\u0004\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00020\u0006¢\u0006\u0002\u0010\u0007R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t\u0082\u0001\t\u0013\u0014\u0015\u0016\u0017\u0018\u0019\u001a\u001b¨\u0006\u001c"}, m1293d2 = {"Lcom/hp/open/printsdk/state/printjob/HpPrintJobState;", "Lcom/hp/printsdk/state/base/State;", "Lcom/hp/open/printsdk/state/Reason;", "code", "", "reasons", "", "(ILjava/util/List;)V", "getCode", "()I", "Aborted", "Canceled", "Completed", PDAnnotationRubberStamp.NAME_EXPIRED, "Pending", "PendingHeld", "Processing", "ProcessingStopped", "Unknown", "Lcom/hp/open/printsdk/state/printjob/HpPrintJobState$Unknown;", "Lcom/hp/open/printsdk/state/printjob/HpPrintJobState$Pending;", "Lcom/hp/open/printsdk/state/printjob/HpPrintJobState$PendingHeld;", "Lcom/hp/open/printsdk/state/printjob/HpPrintJobState$Processing;", "Lcom/hp/open/printsdk/state/printjob/HpPrintJobState$ProcessingStopped;", "Lcom/hp/open/printsdk/state/printjob/HpPrintJobState$Canceled;", "Lcom/hp/open/printsdk/state/printjob/HpPrintJobState$Aborted;", "Lcom/hp/open/printsdk/state/printjob/HpPrintJobState$Completed;", "Lcom/hp/open/printsdk/state/printjob/HpPrintJobState$Expired;", "print-core_thirdPartyRelease"}, m1294k = 1, m1295mv = {1, 6, 0}, m1297xi = 48)
public abstract class HpPrintJobState extends State<Reason> {

    public final int f867b;

    @Metadata(m1292d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0013\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0002\u0010\u0005¨\u0006\u0006"}, m1293d2 = {"Lcom/hp/open/printsdk/state/printjob/HpPrintJobState$Aborted;", "Lcom/hp/open/printsdk/state/printjob/HpPrintJobState;", "reasons", "", "Lcom/hp/open/printsdk/state/Reason;", "(Ljava/util/List;)V", "print-core_thirdPartyRelease"}, m1294k = 1, m1295mv = {1, 6, 0}, m1297xi = 48)
    public static final class Aborted extends HpPrintJobState {
        public Aborted(List<? extends Reason> reasons) {
            super(8, reasons, null);
            Intrinsics.checkNotNullParameter(reasons, "reasons");
        }
    }

    @Metadata(m1292d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0013\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0002\u0010\u0005¨\u0006\u0006"}, m1293d2 = {"Lcom/hp/open/printsdk/state/printjob/HpPrintJobState$Canceled;", "Lcom/hp/open/printsdk/state/printjob/HpPrintJobState;", "reasons", "", "Lcom/hp/open/printsdk/state/Reason;", "(Ljava/util/List;)V", "print-core_thirdPartyRelease"}, m1294k = 1, m1295mv = {1, 6, 0}, m1297xi = 48)
    public static final class Canceled extends HpPrintJobState {
        public Canceled(List<? extends Reason> reasons) {
            super(7, reasons, null);
            Intrinsics.checkNotNullParameter(reasons, "reasons");
        }
    }

    @Metadata(m1292d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0013\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0002\u0010\u0005¨\u0006\u0006"}, m1293d2 = {"Lcom/hp/open/printsdk/state/printjob/HpPrintJobState$Completed;", "Lcom/hp/open/printsdk/state/printjob/HpPrintJobState;", "reasons", "", "Lcom/hp/open/printsdk/state/Reason;", "(Ljava/util/List;)V", "print-core_thirdPartyRelease"}, m1294k = 1, m1295mv = {1, 6, 0}, m1297xi = 48)
    public static final class Completed extends HpPrintJobState {
        public Completed(List<? extends Reason> reasons) {
            super(9, reasons, null);
            Intrinsics.checkNotNullParameter(reasons, "reasons");
        }
    }

    @Metadata(m1292d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0013\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0002\u0010\u0005¨\u0006\u0006"}, m1293d2 = {"Lcom/hp/open/printsdk/state/printjob/HpPrintJobState$Expired;", "Lcom/hp/open/printsdk/state/printjob/HpPrintJobState;", "reasons", "", "Lcom/hp/open/printsdk/state/Reason;", "(Ljava/util/List;)V", "print-core_thirdPartyRelease"}, m1294k = 1, m1295mv = {1, 6, 0}, m1297xi = 48)
    public static final class Expired extends HpPrintJobState {
        public Expired(List<? extends Reason> reasons) {
            super(10, reasons, null);
            Intrinsics.checkNotNullParameter(reasons, "reasons");
        }
    }

    @Metadata(m1292d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0013\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0002\u0010\u0005¨\u0006\u0006"}, m1293d2 = {"Lcom/hp/open/printsdk/state/printjob/HpPrintJobState$Pending;", "Lcom/hp/open/printsdk/state/printjob/HpPrintJobState;", "reasons", "", "Lcom/hp/open/printsdk/state/Reason;", "(Ljava/util/List;)V", "print-core_thirdPartyRelease"}, m1294k = 1, m1295mv = {1, 6, 0}, m1297xi = 48)
    public static final class Pending extends HpPrintJobState {
        public Pending(List<? extends Reason> reasons) {
            super(3, reasons, null);
            Intrinsics.checkNotNullParameter(reasons, "reasons");
        }
    }

    @Metadata(m1292d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0013\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0002\u0010\u0005¨\u0006\u0006"}, m1293d2 = {"Lcom/hp/open/printsdk/state/printjob/HpPrintJobState$PendingHeld;", "Lcom/hp/open/printsdk/state/printjob/HpPrintJobState;", "reasons", "", "Lcom/hp/open/printsdk/state/Reason;", "(Ljava/util/List;)V", "print-core_thirdPartyRelease"}, m1294k = 1, m1295mv = {1, 6, 0}, m1297xi = 48)
    public static final class PendingHeld extends HpPrintJobState {
        public PendingHeld(List<? extends Reason> reasons) {
            super(4, reasons, null);
            Intrinsics.checkNotNullParameter(reasons, "reasons");
        }
    }

    @Metadata(m1292d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0013\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0002\u0010\u0005¨\u0006\u0006"}, m1293d2 = {"Lcom/hp/open/printsdk/state/printjob/HpPrintJobState$Processing;", "Lcom/hp/open/printsdk/state/printjob/HpPrintJobState;", "reasons", "", "Lcom/hp/open/printsdk/state/Reason;", "(Ljava/util/List;)V", "print-core_thirdPartyRelease"}, m1294k = 1, m1295mv = {1, 6, 0}, m1297xi = 48)
    public static final class Processing extends HpPrintJobState {
        public Processing(List<? extends Reason> reasons) {
            super(5, reasons, null);
            Intrinsics.checkNotNullParameter(reasons, "reasons");
        }
    }

    @Metadata(m1292d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0013\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0002\u0010\u0005¨\u0006\u0006"}, m1293d2 = {"Lcom/hp/open/printsdk/state/printjob/HpPrintJobState$ProcessingStopped;", "Lcom/hp/open/printsdk/state/printjob/HpPrintJobState;", "reasons", "", "Lcom/hp/open/printsdk/state/Reason;", "(Ljava/util/List;)V", "print-core_thirdPartyRelease"}, m1294k = 1, m1295mv = {1, 6, 0}, m1297xi = 48)
    public static final class ProcessingStopped extends HpPrintJobState {
        public ProcessingStopped(List<? extends Reason> reasons) {
            super(6, reasons, null);
            Intrinsics.checkNotNullParameter(reasons, "reasons");
        }
    }

    @Metadata(m1292d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0013\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0002\u0010\u0005¨\u0006\u0006"}, m1293d2 = {"Lcom/hp/open/printsdk/state/printjob/HpPrintJobState$Unknown;", "Lcom/hp/open/printsdk/state/printjob/HpPrintJobState;", "reasons", "", "Lcom/hp/open/printsdk/state/Reason;", "(Ljava/util/List;)V", "print-core_thirdPartyRelease"}, m1294k = 1, m1295mv = {1, 6, 0}, m1297xi = 48)
    public static final class Unknown extends HpPrintJobState {
        public Unknown(List<? extends Reason> reasons) {
            super(-1, reasons, null);
            Intrinsics.checkNotNullParameter(reasons, "reasons");
        }
    }

    public HpPrintJobState(int i, List<? extends Reason> list) {
        super(list);
        this.f867b = i;
    }

    public HpPrintJobState(int i, List list, DefaultConstructorMarker defaultConstructorMarker) {
        this(i, list);
    }

    public final int getF867b() {
        return this.f867b;
    }
}
