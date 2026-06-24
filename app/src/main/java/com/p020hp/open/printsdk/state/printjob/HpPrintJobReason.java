package com.p020hp.open.printsdk.state.printjob;

import com.p020hp.jipp.model.JobStateReason;
import com.p020hp.open.printsdk.state.Reason;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(m1292d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0013\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u0000 \r2\u00020\u0001:\u0010\u0007\b\t\n\u000b\f\r\u000e\u000f\u0010\u0011\u0012\u0013\u0014\u0015\u0016B\u000f\b\u0004\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u0082\u0001\u000f\u0017\u0018\u0019\u001a\u001b\u001c\u001d\u001e\u001f !\"#$%¨\u0006&"}, m1293d2 = {"Lcom/hp/open/printsdk/state/printjob/HpPrintJobReason;", "Lcom/hp/open/printsdk/state/Reason;", "keyword", "", "(Ljava/lang/String;)V", "getKeyword", "()Ljava/lang/String;", "AbortedBySystem", "CanceledAtDevice", "CanceledByOperator", "CanceledByUser", "CanceledDueToPrinterNotSupport", "CanceledDueToSdkExpiration", "Companion", "CompletedSuccessfully", "CompletedWithErrors", "CompletedWithWarnings", "DataInsufficient", "Exception", "None", "Other", "PrinterStopped", "Printing", "Lcom/hp/open/printsdk/state/printjob/HpPrintJobReason$None;", "Lcom/hp/open/printsdk/state/printjob/HpPrintJobReason$DataInsufficient;", "Lcom/hp/open/printsdk/state/printjob/HpPrintJobReason$PrinterStopped;", "Lcom/hp/open/printsdk/state/printjob/HpPrintJobReason$Printing;", "Lcom/hp/open/printsdk/state/printjob/HpPrintJobReason$CanceledByUser;", "Lcom/hp/open/printsdk/state/printjob/HpPrintJobReason$CanceledAtDevice;", "Lcom/hp/open/printsdk/state/printjob/HpPrintJobReason$CanceledByOperator;", "Lcom/hp/open/printsdk/state/printjob/HpPrintJobReason$CanceledDueToSdkExpiration;", "Lcom/hp/open/printsdk/state/printjob/HpPrintJobReason$CanceledDueToPrinterNotSupport;", "Lcom/hp/open/printsdk/state/printjob/HpPrintJobReason$AbortedBySystem;", "Lcom/hp/open/printsdk/state/printjob/HpPrintJobReason$CompletedSuccessfully;", "Lcom/hp/open/printsdk/state/printjob/HpPrintJobReason$CompletedWithWarnings;", "Lcom/hp/open/printsdk/state/printjob/HpPrintJobReason$CompletedWithErrors;", "Lcom/hp/open/printsdk/state/printjob/HpPrintJobReason$Other;", "Lcom/hp/open/printsdk/state/printjob/HpPrintJobReason$Exception;", "print-core_thirdPartyRelease"}, m1294k = 1, m1295mv = {1, 6, 0}, m1297xi = 48)
public abstract class HpPrintJobReason extends Reason {

    public static final Companion INSTANCE = new Companion(null);

    public static final ArrayList<HpPrintJobReason> f864b = CollectionsKt.arrayListOf(None.INSTANCE, DataInsufficient.INSTANCE, PrinterStopped.INSTANCE, Printing.INSTANCE, CanceledByUser.INSTANCE, CanceledAtDevice.INSTANCE, CanceledByOperator.INSTANCE, CanceledDueToSdkExpiration.INSTANCE, AbortedBySystem.INSTANCE, CompletedSuccessfully.INSTANCE, CompletedWithWarnings.INSTANCE, CompletedWithErrors.INSTANCE, Other.INSTANCE);

    public final String f865a;

    @Metadata(m1292d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, m1293d2 = {"Lcom/hp/open/printsdk/state/printjob/HpPrintJobReason$AbortedBySystem;", "Lcom/hp/open/printsdk/state/printjob/HpPrintJobReason;", "()V", "print-core_thirdPartyRelease"}, m1294k = 1, m1295mv = {1, 6, 0}, m1297xi = 48)
    public static final class AbortedBySystem extends HpPrintJobReason {
        public static final AbortedBySystem INSTANCE = new AbortedBySystem();

        public AbortedBySystem() {
            super("aborted-by-system", null);
        }
    }

    @Metadata(m1292d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, m1293d2 = {"Lcom/hp/open/printsdk/state/printjob/HpPrintJobReason$CanceledAtDevice;", "Lcom/hp/open/printsdk/state/printjob/HpPrintJobReason;", "()V", "print-core_thirdPartyRelease"}, m1294k = 1, m1295mv = {1, 6, 0}, m1297xi = 48)
    public static final class CanceledAtDevice extends HpPrintJobReason {
        public static final CanceledAtDevice INSTANCE = new CanceledAtDevice();

        public CanceledAtDevice() {
            super(JobStateReason.jobCanceledAtDevice, null);
        }
    }

    @Metadata(m1292d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, m1293d2 = {"Lcom/hp/open/printsdk/state/printjob/HpPrintJobReason$CanceledByOperator;", "Lcom/hp/open/printsdk/state/printjob/HpPrintJobReason;", "()V", "print-core_thirdPartyRelease"}, m1294k = 1, m1295mv = {1, 6, 0}, m1297xi = 48)
    public static final class CanceledByOperator extends HpPrintJobReason {
        public static final CanceledByOperator INSTANCE = new CanceledByOperator();

        public CanceledByOperator() {
            super(JobStateReason.jobCanceledByOperator, null);
        }
    }

    @Metadata(m1292d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, m1293d2 = {"Lcom/hp/open/printsdk/state/printjob/HpPrintJobReason$CanceledByUser;", "Lcom/hp/open/printsdk/state/printjob/HpPrintJobReason;", "()V", "print-core_thirdPartyRelease"}, m1294k = 1, m1295mv = {1, 6, 0}, m1297xi = 48)
    public static final class CanceledByUser extends HpPrintJobReason {
        public static final CanceledByUser INSTANCE = new CanceledByUser();

        public CanceledByUser() {
            super(JobStateReason.jobCanceledByUser, null);
        }
    }

    @Metadata(m1292d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, m1293d2 = {"Lcom/hp/open/printsdk/state/printjob/HpPrintJobReason$CanceledDueToPrinterNotSupport;", "Lcom/hp/open/printsdk/state/printjob/HpPrintJobReason;", "()V", "print-core_thirdPartyRelease"}, m1294k = 1, m1295mv = {1, 6, 0}, m1297xi = 48)
    public static final class CanceledDueToPrinterNotSupport extends HpPrintJobReason {
        public static final CanceledDueToPrinterNotSupport INSTANCE = new CanceledDueToPrinterNotSupport();

        public CanceledDueToPrinterNotSupport() {
            super("job-canceled-due-to-un-support-printer", null);
        }
    }

    @Metadata(m1292d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, m1293d2 = {"Lcom/hp/open/printsdk/state/printjob/HpPrintJobReason$CanceledDueToSdkExpiration;", "Lcom/hp/open/printsdk/state/printjob/HpPrintJobReason;", "()V", "print-core_thirdPartyRelease"}, m1294k = 1, m1295mv = {1, 6, 0}, m1297xi = 48)
    public static final class CanceledDueToSdkExpiration extends HpPrintJobReason {
        public static final CanceledDueToSdkExpiration INSTANCE = new CanceledDueToSdkExpiration();

        public CanceledDueToSdkExpiration() {
            super("job-canceled-due-to-sdk-expiration", null);
        }
    }

    @Metadata(m1292d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R!\u0010\u0003\u001a\u0012\u0012\u0004\u0012\u00020\u00050\u0004j\b\u0012\u0004\u0012\u00020\u0005`\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\t"}, m1293d2 = {"Lcom/hp/open/printsdk/state/printjob/HpPrintJobReason$Companion;", "", "()V", "values", "Ljava/util/ArrayList;", "Lcom/hp/open/printsdk/state/printjob/HpPrintJobReason;", "Lkotlin/collections/ArrayList;", "getValues", "()Ljava/util/ArrayList;", "print-core_thirdPartyRelease"}, m1294k = 1, m1295mv = {1, 6, 0}, m1297xi = 48)
    public static final class Companion {
        public Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final ArrayList<HpPrintJobReason> getValues() {
            return HpPrintJobReason.f864b;
        }
    }

    @Metadata(m1292d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, m1293d2 = {"Lcom/hp/open/printsdk/state/printjob/HpPrintJobReason$CompletedSuccessfully;", "Lcom/hp/open/printsdk/state/printjob/HpPrintJobReason;", "()V", "print-core_thirdPartyRelease"}, m1294k = 1, m1295mv = {1, 6, 0}, m1297xi = 48)
    public static final class CompletedSuccessfully extends HpPrintJobReason {
        public static final CompletedSuccessfully INSTANCE = new CompletedSuccessfully();

        public CompletedSuccessfully() {
            super(JobStateReason.jobCompletedSuccessfully, null);
        }
    }

    @Metadata(m1292d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, m1293d2 = {"Lcom/hp/open/printsdk/state/printjob/HpPrintJobReason$CompletedWithErrors;", "Lcom/hp/open/printsdk/state/printjob/HpPrintJobReason;", "()V", "print-core_thirdPartyRelease"}, m1294k = 1, m1295mv = {1, 6, 0}, m1297xi = 48)
    public static final class CompletedWithErrors extends HpPrintJobReason {
        public static final CompletedWithErrors INSTANCE = new CompletedWithErrors();

        public CompletedWithErrors() {
            super(JobStateReason.jobCompletedWithErrors, null);
        }
    }

    @Metadata(m1292d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, m1293d2 = {"Lcom/hp/open/printsdk/state/printjob/HpPrintJobReason$CompletedWithWarnings;", "Lcom/hp/open/printsdk/state/printjob/HpPrintJobReason;", "()V", "print-core_thirdPartyRelease"}, m1294k = 1, m1295mv = {1, 6, 0}, m1297xi = 48)
    public static final class CompletedWithWarnings extends HpPrintJobReason {
        public static final CompletedWithWarnings INSTANCE = new CompletedWithWarnings();

        public CompletedWithWarnings() {
            super(JobStateReason.jobCompletedWithWarnings, null);
        }
    }

    @Metadata(m1292d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, m1293d2 = {"Lcom/hp/open/printsdk/state/printjob/HpPrintJobReason$DataInsufficient;", "Lcom/hp/open/printsdk/state/printjob/HpPrintJobReason;", "()V", "print-core_thirdPartyRelease"}, m1294k = 1, m1295mv = {1, 6, 0}, m1297xi = 48)
    public static final class DataInsufficient extends HpPrintJobReason {
        public static final DataInsufficient INSTANCE = new DataInsufficient();

        public DataInsufficient() {
            super(JobStateReason.jobDataInsufficient, null);
        }
    }

    @Metadata(m1292d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u0005\u001a\u00020\u0006H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0007"}, m1293d2 = {"Lcom/hp/open/printsdk/state/printjob/HpPrintJobReason$Exception;", "Lcom/hp/open/printsdk/state/printjob/HpPrintJobReason;", "throwable", "", "(Ljava/lang/Throwable;)V", "toString", "", "print-core_thirdPartyRelease"}, m1294k = 1, m1295mv = {1, 6, 0}, m1297xi = 48)
    public static final class Exception extends HpPrintJobReason {

        public final Throwable f866c;

        public Exception(Throwable throwable) {
            super("with exception", null);
            Intrinsics.checkNotNullParameter(throwable, "throwable");
            this.f866c = throwable;
        }

        @Override
        public String toString() {
            return this.f866c.toString();
        }
    }

    @Metadata(m1292d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, m1293d2 = {"Lcom/hp/open/printsdk/state/printjob/HpPrintJobReason$None;", "Lcom/hp/open/printsdk/state/printjob/HpPrintJobReason;", "()V", "print-core_thirdPartyRelease"}, m1294k = 1, m1295mv = {1, 6, 0}, m1297xi = 48)
    public static final class None extends HpPrintJobReason {
        public static final None INSTANCE = new None();

        public None() {
            super("none", null);
        }
    }

    @Metadata(m1292d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, m1293d2 = {"Lcom/hp/open/printsdk/state/printjob/HpPrintJobReason$Other;", "Lcom/hp/open/printsdk/state/printjob/HpPrintJobReason;", "()V", "print-core_thirdPartyRelease"}, m1294k = 1, m1295mv = {1, 6, 0}, m1297xi = 48)
    public static final class Other extends HpPrintJobReason {
        public static final Other INSTANCE = new Other();

        public Other() {
            super("other", null);
        }
    }

    @Metadata(m1292d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, m1293d2 = {"Lcom/hp/open/printsdk/state/printjob/HpPrintJobReason$PrinterStopped;", "Lcom/hp/open/printsdk/state/printjob/HpPrintJobReason;", "()V", "print-core_thirdPartyRelease"}, m1294k = 1, m1295mv = {1, 6, 0}, m1297xi = 48)
    public static final class PrinterStopped extends HpPrintJobReason {
        public static final PrinterStopped INSTANCE = new PrinterStopped();

        public PrinterStopped() {
            super("printer-stopped", null);
        }
    }

    @Metadata(m1292d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, m1293d2 = {"Lcom/hp/open/printsdk/state/printjob/HpPrintJobReason$Printing;", "Lcom/hp/open/printsdk/state/printjob/HpPrintJobReason;", "()V", "print-core_thirdPartyRelease"}, m1294k = 1, m1295mv = {1, 6, 0}, m1297xi = 48)
    public static final class Printing extends HpPrintJobReason {
        public static final Printing INSTANCE = new Printing();

        public Printing() {
            super(JobStateReason.jobPrinting, null);
        }
    }

    public HpPrintJobReason(String str) {
        this.f865a = str;
    }

    public HpPrintJobReason(String str, DefaultConstructorMarker defaultConstructorMarker) {
        this(str);
    }

    public final String getF865a() {
        return this.f865a;
    }
}
