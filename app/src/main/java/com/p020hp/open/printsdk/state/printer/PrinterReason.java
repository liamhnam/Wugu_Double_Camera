package com.p020hp.open.printsdk.state.printer;

import com.p020hp.open.printsdk.CoreManager;
import com.p020hp.open.printsdk.state.Reason;
import com.p020hp.printsdk.C1661R;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(m1292d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0019\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u000e\u0011\u0012\u0013\u0014\u0015\u0016\u0017\u0018\u0019\u001a\u001b\u001c\u001d\u001eB\u001b\b\u0004\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\b\u0010\u0010\u001a\u00020\u0005H\u0016R\u001e\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u0010\n\u0002\u0010\u000b\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000f\u0082\u0001\u000e\u001f !\"#$%&'()*+,¨\u0006-"}, m1293d2 = {"Lcom/hp/open/printsdk/state/printer/PrinterReason;", "Lcom/hp/open/printsdk/state/Reason;", "id", "", "keyword", "", "(Ljava/lang/Integer;Ljava/lang/String;)V", "getId", "()Ljava/lang/Integer;", "setId", "(Ljava/lang/Integer;)V", "Ljava/lang/Integer;", "getKeyword", "()Ljava/lang/String;", "setKeyword", "(Ljava/lang/String;)V", "toString", "Canceling", "CartridgeDepleted", "CartridgeLow", "CartridgeMissing", "DeviceBusy", "DoorOpen", "OutOfPaper", "PrinterError", "PrinterJam", "PrinterOffline", "Ready", "ShuttingDown", "TrayMissing", "Unknown", "Lcom/hp/open/printsdk/state/printer/PrinterReason$Unknown;", "Lcom/hp/open/printsdk/state/printer/PrinterReason$Ready;", "Lcom/hp/open/printsdk/state/printer/PrinterReason$DoorOpen;", "Lcom/hp/open/printsdk/state/printer/PrinterReason$TrayMissing;", "Lcom/hp/open/printsdk/state/printer/PrinterReason$PrinterJam;", "Lcom/hp/open/printsdk/state/printer/PrinterReason$OutOfPaper;", "Lcom/hp/open/printsdk/state/printer/PrinterReason$CartridgeDepleted;", "Lcom/hp/open/printsdk/state/printer/PrinterReason$CartridgeMissing;", "Lcom/hp/open/printsdk/state/printer/PrinterReason$CartridgeLow;", "Lcom/hp/open/printsdk/state/printer/PrinterReason$PrinterError;", "Lcom/hp/open/printsdk/state/printer/PrinterReason$PrinterOffline;", "Lcom/hp/open/printsdk/state/printer/PrinterReason$Canceling;", "Lcom/hp/open/printsdk/state/printer/PrinterReason$ShuttingDown;", "Lcom/hp/open/printsdk/state/printer/PrinterReason$DeviceBusy;", "print-core_thirdPartyRelease"}, m1294k = 1, m1295mv = {1, 6, 0}, m1297xi = 48)
public abstract class PrinterReason extends Reason {

    public Integer f862a;

    public String f863b;

    @Metadata(m1292d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, m1293d2 = {"Lcom/hp/open/printsdk/state/printer/PrinterReason$Canceling;", "Lcom/hp/open/printsdk/state/printer/PrinterReason;", "()V", "print-core_thirdPartyRelease"}, m1294k = 1, m1295mv = {1, 6, 0}, m1297xi = 48)
    public static final class Canceling extends PrinterReason {
        public static final Canceling INSTANCE = new Canceling();

        public Canceling() {
            super(Integer.valueOf(C1661R.string.hp_print_sdk_printer_state_canceling), null, 2, 0 == true ? 1 : 0);
        }
    }

    @Metadata(m1292d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, m1293d2 = {"Lcom/hp/open/printsdk/state/printer/PrinterReason$CartridgeDepleted;", "Lcom/hp/open/printsdk/state/printer/PrinterReason;", "()V", "print-core_thirdPartyRelease"}, m1294k = 1, m1295mv = {1, 6, 0}, m1297xi = 48)
    public static final class CartridgeDepleted extends PrinterReason {
        public static final CartridgeDepleted INSTANCE = new CartridgeDepleted();

        public CartridgeDepleted() {
            super(Integer.valueOf(C1661R.string.hp_print_sdk_printer_state_cartridge_depleted), null, 2, 0 == true ? 1 : 0);
        }
    }

    @Metadata(m1292d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, m1293d2 = {"Lcom/hp/open/printsdk/state/printer/PrinterReason$CartridgeLow;", "Lcom/hp/open/printsdk/state/printer/PrinterReason;", "()V", "print-core_thirdPartyRelease"}, m1294k = 1, m1295mv = {1, 6, 0}, m1297xi = 48)
    public static final class CartridgeLow extends PrinterReason {
        public static final CartridgeLow INSTANCE = new CartridgeLow();

        public CartridgeLow() {
            super(Integer.valueOf(C1661R.string.hp_print_sdk_printer_state_cartridge_low), null, 2, 0 == true ? 1 : 0);
        }
    }

    @Metadata(m1292d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, m1293d2 = {"Lcom/hp/open/printsdk/state/printer/PrinterReason$CartridgeMissing;", "Lcom/hp/open/printsdk/state/printer/PrinterReason;", "()V", "print-core_thirdPartyRelease"}, m1294k = 1, m1295mv = {1, 6, 0}, m1297xi = 48)
    public static final class CartridgeMissing extends PrinterReason {
        public static final CartridgeMissing INSTANCE = new CartridgeMissing();

        public CartridgeMissing() {
            super(Integer.valueOf(C1661R.string.hp_print_sdk_printer_state_cartridge_missing), null, 2, 0 == true ? 1 : 0);
        }
    }

    @Metadata(m1292d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, m1293d2 = {"Lcom/hp/open/printsdk/state/printer/PrinterReason$DeviceBusy;", "Lcom/hp/open/printsdk/state/printer/PrinterReason;", "()V", "print-core_thirdPartyRelease"}, m1294k = 1, m1295mv = {1, 6, 0}, m1297xi = 48)
    public static final class DeviceBusy extends PrinterReason {
        public static final DeviceBusy INSTANCE = new DeviceBusy();

        public DeviceBusy() {
            super(Integer.valueOf(C1661R.string.hp_print_sdk_printer_state_device_busy), null, 2, 0 == true ? 1 : 0);
        }
    }

    @Metadata(m1292d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, m1293d2 = {"Lcom/hp/open/printsdk/state/printer/PrinterReason$DoorOpen;", "Lcom/hp/open/printsdk/state/printer/PrinterReason;", "()V", "print-core_thirdPartyRelease"}, m1294k = 1, m1295mv = {1, 6, 0}, m1297xi = 48)
    public static final class DoorOpen extends PrinterReason {
        public static final DoorOpen INSTANCE = new DoorOpen();

        public DoorOpen() {
            super(Integer.valueOf(C1661R.string.hp_print_sdk_printer_state_door_open), null, 2, 0 == true ? 1 : 0);
        }
    }

    @Metadata(m1292d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, m1293d2 = {"Lcom/hp/open/printsdk/state/printer/PrinterReason$OutOfPaper;", "Lcom/hp/open/printsdk/state/printer/PrinterReason;", "()V", "print-core_thirdPartyRelease"}, m1294k = 1, m1295mv = {1, 6, 0}, m1297xi = 48)
    public static final class OutOfPaper extends PrinterReason {
        public static final OutOfPaper INSTANCE = new OutOfPaper();

        public OutOfPaper() {
            super(Integer.valueOf(C1661R.string.hp_print_sdk_printer_state_out_of_paper), null, 2, 0 == true ? 1 : 0);
        }
    }

    @Metadata(m1292d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004¨\u0006\u0005"}, m1293d2 = {"Lcom/hp/open/printsdk/state/printer/PrinterReason$PrinterError;", "Lcom/hp/open/printsdk/state/printer/PrinterReason;", "keyword", "", "(Ljava/lang/String;)V", "print-core_thirdPartyRelease"}, m1294k = 1, m1295mv = {1, 6, 0}, m1297xi = 48)
    public static final class PrinterError extends PrinterReason {
        public PrinterError(String keyword) {
            super(null, keyword, 0 == true ? 1 : 0);
            Intrinsics.checkNotNullParameter(keyword, "keyword");
        }
    }

    @Metadata(m1292d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, m1293d2 = {"Lcom/hp/open/printsdk/state/printer/PrinterReason$PrinterJam;", "Lcom/hp/open/printsdk/state/printer/PrinterReason;", "()V", "print-core_thirdPartyRelease"}, m1294k = 1, m1295mv = {1, 6, 0}, m1297xi = 48)
    public static final class PrinterJam extends PrinterReason {
        public static final PrinterJam INSTANCE = new PrinterJam();

        public PrinterJam() {
            super(Integer.valueOf(C1661R.string.hp_print_sdk_printer_state_printer_jam), null, 2, 0 == true ? 1 : 0);
        }
    }

    @Metadata(m1292d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, m1293d2 = {"Lcom/hp/open/printsdk/state/printer/PrinterReason$PrinterOffline;", "Lcom/hp/open/printsdk/state/printer/PrinterReason;", "()V", "print-core_thirdPartyRelease"}, m1294k = 1, m1295mv = {1, 6, 0}, m1297xi = 48)
    public static final class PrinterOffline extends PrinterReason {
        public static final PrinterOffline INSTANCE = new PrinterOffline();

        public PrinterOffline() {
            super(Integer.valueOf(C1661R.string.hp_print_sdk_printer_state_printer_offline), null, 2, 0 == true ? 1 : 0);
        }
    }

    @Metadata(m1292d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, m1293d2 = {"Lcom/hp/open/printsdk/state/printer/PrinterReason$Ready;", "Lcom/hp/open/printsdk/state/printer/PrinterReason;", "()V", "print-core_thirdPartyRelease"}, m1294k = 1, m1295mv = {1, 6, 0}, m1297xi = 48)
    public static final class Ready extends PrinterReason {
        public static final Ready INSTANCE = new Ready();

        public Ready() {
            super(Integer.valueOf(C1661R.string.hp_print_sdk_printer_state_ready), null, 2, 0 == true ? 1 : 0);
        }
    }

    @Metadata(m1292d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, m1293d2 = {"Lcom/hp/open/printsdk/state/printer/PrinterReason$ShuttingDown;", "Lcom/hp/open/printsdk/state/printer/PrinterReason;", "()V", "print-core_thirdPartyRelease"}, m1294k = 1, m1295mv = {1, 6, 0}, m1297xi = 48)
    public static final class ShuttingDown extends PrinterReason {
        public static final ShuttingDown INSTANCE = new ShuttingDown();

        public ShuttingDown() {
            super(Integer.valueOf(C1661R.string.hp_print_sdk_printer_state_shutting_down), null, 2, 0 == true ? 1 : 0);
        }
    }

    @Metadata(m1292d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, m1293d2 = {"Lcom/hp/open/printsdk/state/printer/PrinterReason$TrayMissing;", "Lcom/hp/open/printsdk/state/printer/PrinterReason;", "()V", "print-core_thirdPartyRelease"}, m1294k = 1, m1295mv = {1, 6, 0}, m1297xi = 48)
    public static final class TrayMissing extends PrinterReason {
        public static final TrayMissing INSTANCE = new TrayMissing();

        public TrayMissing() {
            super(Integer.valueOf(C1661R.string.hp_print_sdk_printer_state_tray_missing), null, 2, 0 == true ? 1 : 0);
        }
    }

    @Metadata(m1292d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, m1293d2 = {"Lcom/hp/open/printsdk/state/printer/PrinterReason$Unknown;", "Lcom/hp/open/printsdk/state/printer/PrinterReason;", "()V", "print-core_thirdPartyRelease"}, m1294k = 1, m1295mv = {1, 6, 0}, m1297xi = 48)
    public static final class Unknown extends PrinterReason {
        public static final Unknown INSTANCE = new Unknown();

        public Unknown() {
            super(Integer.valueOf(C1661R.string.hp_print_sdk_printer_state_unknown), null, 2, 0 == true ? 1 : 0);
        }
    }

    public PrinterReason(Integer num, String str) {
        this.f862a = num;
        this.f863b = str;
    }

    public PrinterReason(Integer num, String str, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(num, (i & 2) != 0 ? "" : str, null);
    }

    public PrinterReason(Integer num, String str, DefaultConstructorMarker defaultConstructorMarker) {
        this(num, str);
    }

    public final Integer getF862a() {
        return this.f862a;
    }

    public final String getF863b() {
        return this.f863b;
    }

    public final void setId(Integer num) {
        this.f862a = num;
    }

    public final void setKeyword(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.f863b = str;
    }

    @Override
    public String toString() {
        Integer num = this.f862a;
        String string = num != null ? CoreManager.INSTANCE.getContext$print_core_thirdPartyRelease().getString(num.intValue()) : null;
        return string == null ? this.f863b : string;
    }
}
