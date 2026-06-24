package com.p020hp.open.printsdk.options;

import com.p020hp.printsdk.C1661R;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.text.Regex;
import kotlin.text.RegexOption;

@Metadata(m1292d1 = {"\u0000Þ\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b2\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:1\u0007\b\t\n\u000b\f\r\u000e\u000f\u0010\u0011\u0012\u0013\u0014\u0015\u0016\u0017\u0018\u0019\u001a\u001b\u001c\u001d\u001e\u001f !\"#$%&'()*+,-./01234567B\u0017\b\u0004\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006\u0082\u0001189:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[\\]^_`abcdefgh¨\u0006i"}, m1293d2 = {"Lcom/hp/open/printsdk/options/InputTrayType;", "Lcom/hp/open/printsdk/options/Type;", "labelId", "", "regex", "Lkotlin/text/Regex;", "(ILkotlin/text/Regex;)V", "ALTERNATE", "ALTERNATE_ROLL", "AUTO", "BOTTOM", "BY_PASS_TRAY", "CENTER", "DISC", "ENVELOPE", "HAGAKI", "LARGE_CAPACITY", "LEFT", "MAIN", "MAIN_ROLL", "MANUAL", "MIDDLE", "PHOTO", "REAR", "RIGHT", "ROLL_1", "ROLL_2", "ROLL_3", "ROLL_4", "ROLL_5", "ROLL_6", "ROLL_7", "ROLL_8", "ROLL_9", "SIDE", "TOP", "TRAY_1", "TRAY_10", "TRAY_11", "TRAY_12", "TRAY_13", "TRAY_14", "TRAY_15", "TRAY_16", "TRAY_17", "TRAY_18", "TRAY_19", "TRAY_2", "TRAY_20", "TRAY_3", "TRAY_4", "TRAY_5", "TRAY_6", "TRAY_7", "TRAY_8", "TRAY_9", "Lcom/hp/open/printsdk/options/InputTrayType$AUTO;", "Lcom/hp/open/printsdk/options/InputTrayType$TOP;", "Lcom/hp/open/printsdk/options/InputTrayType$SIDE;", "Lcom/hp/open/printsdk/options/InputTrayType$RIGHT;", "Lcom/hp/open/printsdk/options/InputTrayType$REAR;", "Lcom/hp/open/printsdk/options/InputTrayType$PHOTO;", "Lcom/hp/open/printsdk/options/InputTrayType$MIDDLE;", "Lcom/hp/open/printsdk/options/InputTrayType$MANUAL;", "Lcom/hp/open/printsdk/options/InputTrayType$MAIN_ROLL;", "Lcom/hp/open/printsdk/options/InputTrayType$MAIN;", "Lcom/hp/open/printsdk/options/InputTrayType$LEFT;", "Lcom/hp/open/printsdk/options/InputTrayType$LARGE_CAPACITY;", "Lcom/hp/open/printsdk/options/InputTrayType$HAGAKI;", "Lcom/hp/open/printsdk/options/InputTrayType$ENVELOPE;", "Lcom/hp/open/printsdk/options/InputTrayType$BOTTOM;", "Lcom/hp/open/printsdk/options/InputTrayType$CENTER;", "Lcom/hp/open/printsdk/options/InputTrayType$DISC;", "Lcom/hp/open/printsdk/options/InputTrayType$BY_PASS_TRAY;", "Lcom/hp/open/printsdk/options/InputTrayType$ALTERNATE_ROLL;", "Lcom/hp/open/printsdk/options/InputTrayType$ALTERNATE;", "Lcom/hp/open/printsdk/options/InputTrayType$TRAY_1;", "Lcom/hp/open/printsdk/options/InputTrayType$TRAY_2;", "Lcom/hp/open/printsdk/options/InputTrayType$TRAY_3;", "Lcom/hp/open/printsdk/options/InputTrayType$TRAY_4;", "Lcom/hp/open/printsdk/options/InputTrayType$TRAY_5;", "Lcom/hp/open/printsdk/options/InputTrayType$TRAY_6;", "Lcom/hp/open/printsdk/options/InputTrayType$TRAY_7;", "Lcom/hp/open/printsdk/options/InputTrayType$TRAY_8;", "Lcom/hp/open/printsdk/options/InputTrayType$TRAY_9;", "Lcom/hp/open/printsdk/options/InputTrayType$TRAY_10;", "Lcom/hp/open/printsdk/options/InputTrayType$TRAY_11;", "Lcom/hp/open/printsdk/options/InputTrayType$TRAY_12;", "Lcom/hp/open/printsdk/options/InputTrayType$TRAY_13;", "Lcom/hp/open/printsdk/options/InputTrayType$TRAY_14;", "Lcom/hp/open/printsdk/options/InputTrayType$TRAY_15;", "Lcom/hp/open/printsdk/options/InputTrayType$TRAY_16;", "Lcom/hp/open/printsdk/options/InputTrayType$TRAY_17;", "Lcom/hp/open/printsdk/options/InputTrayType$TRAY_18;", "Lcom/hp/open/printsdk/options/InputTrayType$TRAY_19;", "Lcom/hp/open/printsdk/options/InputTrayType$TRAY_20;", "Lcom/hp/open/printsdk/options/InputTrayType$ROLL_1;", "Lcom/hp/open/printsdk/options/InputTrayType$ROLL_2;", "Lcom/hp/open/printsdk/options/InputTrayType$ROLL_3;", "Lcom/hp/open/printsdk/options/InputTrayType$ROLL_4;", "Lcom/hp/open/printsdk/options/InputTrayType$ROLL_5;", "Lcom/hp/open/printsdk/options/InputTrayType$ROLL_6;", "Lcom/hp/open/printsdk/options/InputTrayType$ROLL_7;", "Lcom/hp/open/printsdk/options/InputTrayType$ROLL_8;", "Lcom/hp/open/printsdk/options/InputTrayType$ROLL_9;", "print-core_thirdPartyRelease"}, m1294k = 1, m1295mv = {1, 6, 0}, m1297xi = 48)
public abstract class InputTrayType extends Type {

    @Metadata(m1292d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, m1293d2 = {"Lcom/hp/open/printsdk/options/InputTrayType$ALTERNATE;", "Lcom/hp/open/printsdk/options/InputTrayType;", "()V", "print-core_thirdPartyRelease"}, m1294k = 1, m1295mv = {1, 6, 0}, m1297xi = 48)
    public static final class ALTERNATE extends InputTrayType {
        public static final ALTERNATE INSTANCE = new ALTERNATE();

        public ALTERNATE() {
            super(C1661R.string.hp_print_sdk_printer_input_tray_alternate, new Regex(".*alternate.*", RegexOption.IGNORE_CASE), null);
        }
    }

    @Metadata(m1292d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, m1293d2 = {"Lcom/hp/open/printsdk/options/InputTrayType$ALTERNATE_ROLL;", "Lcom/hp/open/printsdk/options/InputTrayType;", "()V", "print-core_thirdPartyRelease"}, m1294k = 1, m1295mv = {1, 6, 0}, m1297xi = 48)
    public static final class ALTERNATE_ROLL extends InputTrayType {
        public static final ALTERNATE_ROLL INSTANCE = new ALTERNATE_ROLL();

        public ALTERNATE_ROLL() {
            super(C1661R.string.hp_print_sdk_printer_input_tray_alternate_roll, new Regex(".*alternate-roll.*", RegexOption.IGNORE_CASE), null);
        }
    }

    @Metadata(m1292d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, m1293d2 = {"Lcom/hp/open/printsdk/options/InputTrayType$AUTO;", "Lcom/hp/open/printsdk/options/InputTrayType;", "()V", "print-core_thirdPartyRelease"}, m1294k = 1, m1295mv = {1, 6, 0}, m1297xi = 48)
    public static final class AUTO extends InputTrayType {
        public static final AUTO INSTANCE = new AUTO();

        public AUTO() {
            super(C1661R.string.hp_print_sdk_printer_input_tray_auto, new Regex(".*auto.*", RegexOption.IGNORE_CASE), null);
        }
    }

    @Metadata(m1292d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, m1293d2 = {"Lcom/hp/open/printsdk/options/InputTrayType$BOTTOM;", "Lcom/hp/open/printsdk/options/InputTrayType;", "()V", "print-core_thirdPartyRelease"}, m1294k = 1, m1295mv = {1, 6, 0}, m1297xi = 48)
    public static final class BOTTOM extends InputTrayType {
        public static final BOTTOM INSTANCE = new BOTTOM();

        public BOTTOM() {
            super(C1661R.string.hp_print_sdk_printer_input_tray_bottom, new Regex(".*bottom.*", RegexOption.IGNORE_CASE), null);
        }
    }

    @Metadata(m1292d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, m1293d2 = {"Lcom/hp/open/printsdk/options/InputTrayType$BY_PASS_TRAY;", "Lcom/hp/open/printsdk/options/InputTrayType;", "()V", "print-core_thirdPartyRelease"}, m1294k = 1, m1295mv = {1, 6, 0}, m1297xi = 48)
    public static final class BY_PASS_TRAY extends InputTrayType {
        public static final BY_PASS_TRAY INSTANCE = new BY_PASS_TRAY();

        public BY_PASS_TRAY() {
            super(C1661R.string.hp_print_sdk_printer_input_tray_by_pass_tray, new Regex(".*by-pass-tray.*", RegexOption.IGNORE_CASE), null);
        }
    }

    @Metadata(m1292d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, m1293d2 = {"Lcom/hp/open/printsdk/options/InputTrayType$CENTER;", "Lcom/hp/open/printsdk/options/InputTrayType;", "()V", "print-core_thirdPartyRelease"}, m1294k = 1, m1295mv = {1, 6, 0}, m1297xi = 48)
    public static final class CENTER extends InputTrayType {
        public static final CENTER INSTANCE = new CENTER();

        public CENTER() {
            super(C1661R.string.hp_print_sdk_printer_input_tray_center, new Regex(".*center.*", RegexOption.IGNORE_CASE), null);
        }
    }

    @Metadata(m1292d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, m1293d2 = {"Lcom/hp/open/printsdk/options/InputTrayType$DISC;", "Lcom/hp/open/printsdk/options/InputTrayType;", "()V", "print-core_thirdPartyRelease"}, m1294k = 1, m1295mv = {1, 6, 0}, m1297xi = 48)
    public static final class DISC extends InputTrayType {
        public static final DISC INSTANCE = new DISC();

        public DISC() {
            super(C1661R.string.hp_print_sdk_printer_input_tray_disc, new Regex(".*disc.*", RegexOption.IGNORE_CASE), null);
        }
    }

    @Metadata(m1292d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, m1293d2 = {"Lcom/hp/open/printsdk/options/InputTrayType$ENVELOPE;", "Lcom/hp/open/printsdk/options/InputTrayType;", "()V", "print-core_thirdPartyRelease"}, m1294k = 1, m1295mv = {1, 6, 0}, m1297xi = 48)
    public static final class ENVELOPE extends InputTrayType {
        public static final ENVELOPE INSTANCE = new ENVELOPE();

        public ENVELOPE() {
            super(C1661R.string.hp_print_sdk_printer_input_tray_envelope, new Regex(".*envelope.*", RegexOption.IGNORE_CASE), null);
        }
    }

    @Metadata(m1292d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, m1293d2 = {"Lcom/hp/open/printsdk/options/InputTrayType$HAGAKI;", "Lcom/hp/open/printsdk/options/InputTrayType;", "()V", "print-core_thirdPartyRelease"}, m1294k = 1, m1295mv = {1, 6, 0}, m1297xi = 48)
    public static final class HAGAKI extends InputTrayType {
        public static final HAGAKI INSTANCE = new HAGAKI();

        public HAGAKI() {
            super(C1661R.string.hp_print_sdk_printer_input_tray_hagaki, new Regex(".*hagaki.*", RegexOption.IGNORE_CASE), null);
        }
    }

    @Metadata(m1292d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, m1293d2 = {"Lcom/hp/open/printsdk/options/InputTrayType$LARGE_CAPACITY;", "Lcom/hp/open/printsdk/options/InputTrayType;", "()V", "print-core_thirdPartyRelease"}, m1294k = 1, m1295mv = {1, 6, 0}, m1297xi = 48)
    public static final class LARGE_CAPACITY extends InputTrayType {
        public static final LARGE_CAPACITY INSTANCE = new LARGE_CAPACITY();

        public LARGE_CAPACITY() {
            super(C1661R.string.hp_print_sdk_printer_input_tray_large_capacity, new Regex(".*large-capacity.*", RegexOption.IGNORE_CASE), null);
        }
    }

    @Metadata(m1292d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, m1293d2 = {"Lcom/hp/open/printsdk/options/InputTrayType$LEFT;", "Lcom/hp/open/printsdk/options/InputTrayType;", "()V", "print-core_thirdPartyRelease"}, m1294k = 1, m1295mv = {1, 6, 0}, m1297xi = 48)
    public static final class LEFT extends InputTrayType {
        public static final LEFT INSTANCE = new LEFT();

        public LEFT() {
            super(C1661R.string.hp_print_sdk_printer_input_tray_left, new Regex(".*left.*", RegexOption.IGNORE_CASE), null);
        }
    }

    @Metadata(m1292d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, m1293d2 = {"Lcom/hp/open/printsdk/options/InputTrayType$MAIN;", "Lcom/hp/open/printsdk/options/InputTrayType;", "()V", "print-core_thirdPartyRelease"}, m1294k = 1, m1295mv = {1, 6, 0}, m1297xi = 48)
    public static final class MAIN extends InputTrayType {
        public static final MAIN INSTANCE = new MAIN();

        public MAIN() {
            super(C1661R.string.hp_print_sdk_printer_input_tray_main, new Regex(".*main.*", RegexOption.IGNORE_CASE), null);
        }
    }

    @Metadata(m1292d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, m1293d2 = {"Lcom/hp/open/printsdk/options/InputTrayType$MAIN_ROLL;", "Lcom/hp/open/printsdk/options/InputTrayType;", "()V", "print-core_thirdPartyRelease"}, m1294k = 1, m1295mv = {1, 6, 0}, m1297xi = 48)
    public static final class MAIN_ROLL extends InputTrayType {
        public static final MAIN_ROLL INSTANCE = new MAIN_ROLL();

        public MAIN_ROLL() {
            super(C1661R.string.hp_print_sdk_printer_input_tray_main_roll, new Regex(".*main-roll.*", RegexOption.IGNORE_CASE), null);
        }
    }

    @Metadata(m1292d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, m1293d2 = {"Lcom/hp/open/printsdk/options/InputTrayType$MANUAL;", "Lcom/hp/open/printsdk/options/InputTrayType;", "()V", "print-core_thirdPartyRelease"}, m1294k = 1, m1295mv = {1, 6, 0}, m1297xi = 48)
    public static final class MANUAL extends InputTrayType {
        public static final MANUAL INSTANCE = new MANUAL();

        public MANUAL() {
            super(C1661R.string.hp_print_sdk_printer_input_tray_manual, new Regex(".*manual.*", RegexOption.IGNORE_CASE), null);
        }
    }

    @Metadata(m1292d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, m1293d2 = {"Lcom/hp/open/printsdk/options/InputTrayType$MIDDLE;", "Lcom/hp/open/printsdk/options/InputTrayType;", "()V", "print-core_thirdPartyRelease"}, m1294k = 1, m1295mv = {1, 6, 0}, m1297xi = 48)
    public static final class MIDDLE extends InputTrayType {
        public static final MIDDLE INSTANCE = new MIDDLE();

        public MIDDLE() {
            super(C1661R.string.hp_print_sdk_printer_input_tray_middle, new Regex(".*middle.*", RegexOption.IGNORE_CASE), null);
        }
    }

    @Metadata(m1292d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, m1293d2 = {"Lcom/hp/open/printsdk/options/InputTrayType$PHOTO;", "Lcom/hp/open/printsdk/options/InputTrayType;", "()V", "print-core_thirdPartyRelease"}, m1294k = 1, m1295mv = {1, 6, 0}, m1297xi = 48)
    public static final class PHOTO extends InputTrayType {
        public static final PHOTO INSTANCE = new PHOTO();

        public PHOTO() {
            super(C1661R.string.hp_print_sdk_printer_input_tray_photo, new Regex(".*photo.*", RegexOption.IGNORE_CASE), null);
        }
    }

    @Metadata(m1292d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, m1293d2 = {"Lcom/hp/open/printsdk/options/InputTrayType$REAR;", "Lcom/hp/open/printsdk/options/InputTrayType;", "()V", "print-core_thirdPartyRelease"}, m1294k = 1, m1295mv = {1, 6, 0}, m1297xi = 48)
    public static final class REAR extends InputTrayType {
        public static final REAR INSTANCE = new REAR();

        public REAR() {
            super(C1661R.string.hp_print_sdk_printer_input_tray_rear, new Regex(".*rear.*", RegexOption.IGNORE_CASE), null);
        }
    }

    @Metadata(m1292d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, m1293d2 = {"Lcom/hp/open/printsdk/options/InputTrayType$RIGHT;", "Lcom/hp/open/printsdk/options/InputTrayType;", "()V", "print-core_thirdPartyRelease"}, m1294k = 1, m1295mv = {1, 6, 0}, m1297xi = 48)
    public static final class RIGHT extends InputTrayType {
        public static final RIGHT INSTANCE = new RIGHT();

        public RIGHT() {
            super(C1661R.string.hp_print_sdk_printer_input_tray_right, new Regex(".*right.*", RegexOption.IGNORE_CASE), null);
        }
    }

    @Metadata(m1292d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, m1293d2 = {"Lcom/hp/open/printsdk/options/InputTrayType$ROLL_1;", "Lcom/hp/open/printsdk/options/InputTrayType;", "()V", "print-core_thirdPartyRelease"}, m1294k = 1, m1295mv = {1, 6, 0}, m1297xi = 48)
    public static final class ROLL_1 extends InputTrayType {
        public static final ROLL_1 INSTANCE = new ROLL_1();

        public ROLL_1() {
            super(C1661R.string.hp_print_sdk_printer_input_tray_roll_1, new Regex(".*roll-1.*", RegexOption.IGNORE_CASE), null);
        }
    }

    @Metadata(m1292d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, m1293d2 = {"Lcom/hp/open/printsdk/options/InputTrayType$ROLL_2;", "Lcom/hp/open/printsdk/options/InputTrayType;", "()V", "print-core_thirdPartyRelease"}, m1294k = 1, m1295mv = {1, 6, 0}, m1297xi = 48)
    public static final class ROLL_2 extends InputTrayType {
        public static final ROLL_2 INSTANCE = new ROLL_2();

        public ROLL_2() {
            super(C1661R.string.hp_print_sdk_printer_input_tray_roll_2, new Regex(".*roll-2.*", RegexOption.IGNORE_CASE), null);
        }
    }

    @Metadata(m1292d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, m1293d2 = {"Lcom/hp/open/printsdk/options/InputTrayType$ROLL_3;", "Lcom/hp/open/printsdk/options/InputTrayType;", "()V", "print-core_thirdPartyRelease"}, m1294k = 1, m1295mv = {1, 6, 0}, m1297xi = 48)
    public static final class ROLL_3 extends InputTrayType {
        public static final ROLL_3 INSTANCE = new ROLL_3();

        public ROLL_3() {
            super(C1661R.string.hp_print_sdk_printer_input_tray_roll_3, new Regex(".*roll-3.*", RegexOption.IGNORE_CASE), null);
        }
    }

    @Metadata(m1292d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, m1293d2 = {"Lcom/hp/open/printsdk/options/InputTrayType$ROLL_4;", "Lcom/hp/open/printsdk/options/InputTrayType;", "()V", "print-core_thirdPartyRelease"}, m1294k = 1, m1295mv = {1, 6, 0}, m1297xi = 48)
    public static final class ROLL_4 extends InputTrayType {
        public static final ROLL_4 INSTANCE = new ROLL_4();

        public ROLL_4() {
            super(C1661R.string.hp_print_sdk_printer_input_tray_roll_4, new Regex(".*roll-4.*", RegexOption.IGNORE_CASE), null);
        }
    }

    @Metadata(m1292d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, m1293d2 = {"Lcom/hp/open/printsdk/options/InputTrayType$ROLL_5;", "Lcom/hp/open/printsdk/options/InputTrayType;", "()V", "print-core_thirdPartyRelease"}, m1294k = 1, m1295mv = {1, 6, 0}, m1297xi = 48)
    public static final class ROLL_5 extends InputTrayType {
        public static final ROLL_5 INSTANCE = new ROLL_5();

        public ROLL_5() {
            super(C1661R.string.hp_print_sdk_printer_input_tray_roll_5, new Regex(".*roll-5.*", RegexOption.IGNORE_CASE), null);
        }
    }

    @Metadata(m1292d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, m1293d2 = {"Lcom/hp/open/printsdk/options/InputTrayType$ROLL_6;", "Lcom/hp/open/printsdk/options/InputTrayType;", "()V", "print-core_thirdPartyRelease"}, m1294k = 1, m1295mv = {1, 6, 0}, m1297xi = 48)
    public static final class ROLL_6 extends InputTrayType {
        public static final ROLL_6 INSTANCE = new ROLL_6();

        public ROLL_6() {
            super(C1661R.string.hp_print_sdk_printer_input_tray_roll_6, new Regex(".*roll-6.*", RegexOption.IGNORE_CASE), null);
        }
    }

    @Metadata(m1292d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, m1293d2 = {"Lcom/hp/open/printsdk/options/InputTrayType$ROLL_7;", "Lcom/hp/open/printsdk/options/InputTrayType;", "()V", "print-core_thirdPartyRelease"}, m1294k = 1, m1295mv = {1, 6, 0}, m1297xi = 48)
    public static final class ROLL_7 extends InputTrayType {
        public static final ROLL_7 INSTANCE = new ROLL_7();

        public ROLL_7() {
            super(C1661R.string.hp_print_sdk_printer_input_tray_roll_7, new Regex(".*roll-7.*", RegexOption.IGNORE_CASE), null);
        }
    }

    @Metadata(m1292d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, m1293d2 = {"Lcom/hp/open/printsdk/options/InputTrayType$ROLL_8;", "Lcom/hp/open/printsdk/options/InputTrayType;", "()V", "print-core_thirdPartyRelease"}, m1294k = 1, m1295mv = {1, 6, 0}, m1297xi = 48)
    public static final class ROLL_8 extends InputTrayType {
        public static final ROLL_8 INSTANCE = new ROLL_8();

        public ROLL_8() {
            super(C1661R.string.hp_print_sdk_printer_input_tray_roll_8, new Regex(".*roll-8.*", RegexOption.IGNORE_CASE), null);
        }
    }

    @Metadata(m1292d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, m1293d2 = {"Lcom/hp/open/printsdk/options/InputTrayType$ROLL_9;", "Lcom/hp/open/printsdk/options/InputTrayType;", "()V", "print-core_thirdPartyRelease"}, m1294k = 1, m1295mv = {1, 6, 0}, m1297xi = 48)
    public static final class ROLL_9 extends InputTrayType {
        public static final ROLL_9 INSTANCE = new ROLL_9();

        public ROLL_9() {
            super(C1661R.string.hp_print_sdk_printer_input_tray_roll_9, new Regex(".*roll-9.*", RegexOption.IGNORE_CASE), null);
        }
    }

    @Metadata(m1292d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, m1293d2 = {"Lcom/hp/open/printsdk/options/InputTrayType$SIDE;", "Lcom/hp/open/printsdk/options/InputTrayType;", "()V", "print-core_thirdPartyRelease"}, m1294k = 1, m1295mv = {1, 6, 0}, m1297xi = 48)
    public static final class SIDE extends InputTrayType {
        public static final SIDE INSTANCE = new SIDE();

        public SIDE() {
            super(C1661R.string.hp_print_sdk_printer_input_tray_side, new Regex(".*side.*", RegexOption.IGNORE_CASE), null);
        }
    }

    @Metadata(m1292d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, m1293d2 = {"Lcom/hp/open/printsdk/options/InputTrayType$TOP;", "Lcom/hp/open/printsdk/options/InputTrayType;", "()V", "print-core_thirdPartyRelease"}, m1294k = 1, m1295mv = {1, 6, 0}, m1297xi = 48)
    public static final class TOP extends InputTrayType {
        public static final TOP INSTANCE = new TOP();

        public TOP() {
            super(C1661R.string.hp_print_sdk_printer_input_tray_top, new Regex(".*top.*", RegexOption.IGNORE_CASE), null);
        }
    }

    @Metadata(m1292d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, m1293d2 = {"Lcom/hp/open/printsdk/options/InputTrayType$TRAY_1;", "Lcom/hp/open/printsdk/options/InputTrayType;", "()V", "print-core_thirdPartyRelease"}, m1294k = 1, m1295mv = {1, 6, 0}, m1297xi = 48)
    public static final class TRAY_1 extends InputTrayType {
        public static final TRAY_1 INSTANCE = new TRAY_1();

        public TRAY_1() {
            super(C1661R.string.hp_print_sdk_printer_input_tray_1, new Regex(".*tray-1.*", RegexOption.IGNORE_CASE), null);
        }
    }

    @Metadata(m1292d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, m1293d2 = {"Lcom/hp/open/printsdk/options/InputTrayType$TRAY_10;", "Lcom/hp/open/printsdk/options/InputTrayType;", "()V", "print-core_thirdPartyRelease"}, m1294k = 1, m1295mv = {1, 6, 0}, m1297xi = 48)
    public static final class TRAY_10 extends InputTrayType {
        public static final TRAY_10 INSTANCE = new TRAY_10();

        public TRAY_10() {
            super(C1661R.string.hp_print_sdk_printer_input_tray_10, new Regex(".*tray-10.*", RegexOption.IGNORE_CASE), null);
        }
    }

    @Metadata(m1292d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, m1293d2 = {"Lcom/hp/open/printsdk/options/InputTrayType$TRAY_11;", "Lcom/hp/open/printsdk/options/InputTrayType;", "()V", "print-core_thirdPartyRelease"}, m1294k = 1, m1295mv = {1, 6, 0}, m1297xi = 48)
    public static final class TRAY_11 extends InputTrayType {
        public static final TRAY_11 INSTANCE = new TRAY_11();

        public TRAY_11() {
            super(C1661R.string.hp_print_sdk_printer_input_tray_11, new Regex(".*tray-11.*", RegexOption.IGNORE_CASE), null);
        }
    }

    @Metadata(m1292d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, m1293d2 = {"Lcom/hp/open/printsdk/options/InputTrayType$TRAY_12;", "Lcom/hp/open/printsdk/options/InputTrayType;", "()V", "print-core_thirdPartyRelease"}, m1294k = 1, m1295mv = {1, 6, 0}, m1297xi = 48)
    public static final class TRAY_12 extends InputTrayType {
        public static final TRAY_12 INSTANCE = new TRAY_12();

        public TRAY_12() {
            super(C1661R.string.hp_print_sdk_printer_input_tray_12, new Regex(".*tray-12.*", RegexOption.IGNORE_CASE), null);
        }
    }

    @Metadata(m1292d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, m1293d2 = {"Lcom/hp/open/printsdk/options/InputTrayType$TRAY_13;", "Lcom/hp/open/printsdk/options/InputTrayType;", "()V", "print-core_thirdPartyRelease"}, m1294k = 1, m1295mv = {1, 6, 0}, m1297xi = 48)
    public static final class TRAY_13 extends InputTrayType {
        public static final TRAY_13 INSTANCE = new TRAY_13();

        public TRAY_13() {
            super(C1661R.string.hp_print_sdk_printer_input_tray_13, new Regex(".*tray-13.*", RegexOption.IGNORE_CASE), null);
        }
    }

    @Metadata(m1292d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, m1293d2 = {"Lcom/hp/open/printsdk/options/InputTrayType$TRAY_14;", "Lcom/hp/open/printsdk/options/InputTrayType;", "()V", "print-core_thirdPartyRelease"}, m1294k = 1, m1295mv = {1, 6, 0}, m1297xi = 48)
    public static final class TRAY_14 extends InputTrayType {
        public static final TRAY_14 INSTANCE = new TRAY_14();

        public TRAY_14() {
            super(C1661R.string.hp_print_sdk_printer_input_tray_14, new Regex(".*tray-14.*", RegexOption.IGNORE_CASE), null);
        }
    }

    @Metadata(m1292d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, m1293d2 = {"Lcom/hp/open/printsdk/options/InputTrayType$TRAY_15;", "Lcom/hp/open/printsdk/options/InputTrayType;", "()V", "print-core_thirdPartyRelease"}, m1294k = 1, m1295mv = {1, 6, 0}, m1297xi = 48)
    public static final class TRAY_15 extends InputTrayType {
        public static final TRAY_15 INSTANCE = new TRAY_15();

        public TRAY_15() {
            super(C1661R.string.hp_print_sdk_printer_input_tray_15, new Regex(".*tray-15.*", RegexOption.IGNORE_CASE), null);
        }
    }

    @Metadata(m1292d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, m1293d2 = {"Lcom/hp/open/printsdk/options/InputTrayType$TRAY_16;", "Lcom/hp/open/printsdk/options/InputTrayType;", "()V", "print-core_thirdPartyRelease"}, m1294k = 1, m1295mv = {1, 6, 0}, m1297xi = 48)
    public static final class TRAY_16 extends InputTrayType {
        public static final TRAY_16 INSTANCE = new TRAY_16();

        public TRAY_16() {
            super(C1661R.string.hp_print_sdk_printer_input_tray_16, new Regex(".*tray-16.*", RegexOption.IGNORE_CASE), null);
        }
    }

    @Metadata(m1292d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, m1293d2 = {"Lcom/hp/open/printsdk/options/InputTrayType$TRAY_17;", "Lcom/hp/open/printsdk/options/InputTrayType;", "()V", "print-core_thirdPartyRelease"}, m1294k = 1, m1295mv = {1, 6, 0}, m1297xi = 48)
    public static final class TRAY_17 extends InputTrayType {
        public static final TRAY_17 INSTANCE = new TRAY_17();

        public TRAY_17() {
            super(C1661R.string.hp_print_sdk_printer_input_tray_17, new Regex(".*tray-17.*", RegexOption.IGNORE_CASE), null);
        }
    }

    @Metadata(m1292d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, m1293d2 = {"Lcom/hp/open/printsdk/options/InputTrayType$TRAY_18;", "Lcom/hp/open/printsdk/options/InputTrayType;", "()V", "print-core_thirdPartyRelease"}, m1294k = 1, m1295mv = {1, 6, 0}, m1297xi = 48)
    public static final class TRAY_18 extends InputTrayType {
        public static final TRAY_18 INSTANCE = new TRAY_18();

        public TRAY_18() {
            super(C1661R.string.hp_print_sdk_printer_input_tray_18, new Regex(".*tray-18.*", RegexOption.IGNORE_CASE), null);
        }
    }

    @Metadata(m1292d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, m1293d2 = {"Lcom/hp/open/printsdk/options/InputTrayType$TRAY_19;", "Lcom/hp/open/printsdk/options/InputTrayType;", "()V", "print-core_thirdPartyRelease"}, m1294k = 1, m1295mv = {1, 6, 0}, m1297xi = 48)
    public static final class TRAY_19 extends InputTrayType {
        public static final TRAY_19 INSTANCE = new TRAY_19();

        public TRAY_19() {
            super(C1661R.string.hp_print_sdk_printer_input_tray_19, new Regex(".*tray-19.*", RegexOption.IGNORE_CASE), null);
        }
    }

    @Metadata(m1292d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, m1293d2 = {"Lcom/hp/open/printsdk/options/InputTrayType$TRAY_2;", "Lcom/hp/open/printsdk/options/InputTrayType;", "()V", "print-core_thirdPartyRelease"}, m1294k = 1, m1295mv = {1, 6, 0}, m1297xi = 48)
    public static final class TRAY_2 extends InputTrayType {
        public static final TRAY_2 INSTANCE = new TRAY_2();

        public TRAY_2() {
            super(C1661R.string.hp_print_sdk_printer_input_tray_2, new Regex(".*tray-2.*", RegexOption.IGNORE_CASE), null);
        }
    }

    @Metadata(m1292d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, m1293d2 = {"Lcom/hp/open/printsdk/options/InputTrayType$TRAY_20;", "Lcom/hp/open/printsdk/options/InputTrayType;", "()V", "print-core_thirdPartyRelease"}, m1294k = 1, m1295mv = {1, 6, 0}, m1297xi = 48)
    public static final class TRAY_20 extends InputTrayType {
        public static final TRAY_20 INSTANCE = new TRAY_20();

        public TRAY_20() {
            super(C1661R.string.hp_print_sdk_printer_input_tray_20, new Regex(".*tray-20.*", RegexOption.IGNORE_CASE), null);
        }
    }

    @Metadata(m1292d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, m1293d2 = {"Lcom/hp/open/printsdk/options/InputTrayType$TRAY_3;", "Lcom/hp/open/printsdk/options/InputTrayType;", "()V", "print-core_thirdPartyRelease"}, m1294k = 1, m1295mv = {1, 6, 0}, m1297xi = 48)
    public static final class TRAY_3 extends InputTrayType {
        public static final TRAY_3 INSTANCE = new TRAY_3();

        public TRAY_3() {
            super(C1661R.string.hp_print_sdk_printer_input_tray_3, new Regex(".*tray-3.*", RegexOption.IGNORE_CASE), null);
        }
    }

    @Metadata(m1292d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, m1293d2 = {"Lcom/hp/open/printsdk/options/InputTrayType$TRAY_4;", "Lcom/hp/open/printsdk/options/InputTrayType;", "()V", "print-core_thirdPartyRelease"}, m1294k = 1, m1295mv = {1, 6, 0}, m1297xi = 48)
    public static final class TRAY_4 extends InputTrayType {
        public static final TRAY_4 INSTANCE = new TRAY_4();

        public TRAY_4() {
            super(C1661R.string.hp_print_sdk_printer_input_tray_4, new Regex(".*tray-4.*", RegexOption.IGNORE_CASE), null);
        }
    }

    @Metadata(m1292d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, m1293d2 = {"Lcom/hp/open/printsdk/options/InputTrayType$TRAY_5;", "Lcom/hp/open/printsdk/options/InputTrayType;", "()V", "print-core_thirdPartyRelease"}, m1294k = 1, m1295mv = {1, 6, 0}, m1297xi = 48)
    public static final class TRAY_5 extends InputTrayType {
        public static final TRAY_5 INSTANCE = new TRAY_5();

        public TRAY_5() {
            super(C1661R.string.hp_print_sdk_printer_input_tray_5, new Regex(".*tray-5.*", RegexOption.IGNORE_CASE), null);
        }
    }

    @Metadata(m1292d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, m1293d2 = {"Lcom/hp/open/printsdk/options/InputTrayType$TRAY_6;", "Lcom/hp/open/printsdk/options/InputTrayType;", "()V", "print-core_thirdPartyRelease"}, m1294k = 1, m1295mv = {1, 6, 0}, m1297xi = 48)
    public static final class TRAY_6 extends InputTrayType {
        public static final TRAY_6 INSTANCE = new TRAY_6();

        public TRAY_6() {
            super(C1661R.string.hp_print_sdk_printer_input_tray_6, new Regex(".*tray-6.*", RegexOption.IGNORE_CASE), null);
        }
    }

    @Metadata(m1292d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, m1293d2 = {"Lcom/hp/open/printsdk/options/InputTrayType$TRAY_7;", "Lcom/hp/open/printsdk/options/InputTrayType;", "()V", "print-core_thirdPartyRelease"}, m1294k = 1, m1295mv = {1, 6, 0}, m1297xi = 48)
    public static final class TRAY_7 extends InputTrayType {
        public static final TRAY_7 INSTANCE = new TRAY_7();

        public TRAY_7() {
            super(C1661R.string.hp_print_sdk_printer_input_tray_7, new Regex(".*tray-7.*", RegexOption.IGNORE_CASE), null);
        }
    }

    @Metadata(m1292d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, m1293d2 = {"Lcom/hp/open/printsdk/options/InputTrayType$TRAY_8;", "Lcom/hp/open/printsdk/options/InputTrayType;", "()V", "print-core_thirdPartyRelease"}, m1294k = 1, m1295mv = {1, 6, 0}, m1297xi = 48)
    public static final class TRAY_8 extends InputTrayType {
        public static final TRAY_8 INSTANCE = new TRAY_8();

        public TRAY_8() {
            super(C1661R.string.hp_print_sdk_printer_input_tray_8, new Regex(".*tray-8.*", RegexOption.IGNORE_CASE), null);
        }
    }

    @Metadata(m1292d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, m1293d2 = {"Lcom/hp/open/printsdk/options/InputTrayType$TRAY_9;", "Lcom/hp/open/printsdk/options/InputTrayType;", "()V", "print-core_thirdPartyRelease"}, m1294k = 1, m1295mv = {1, 6, 0}, m1297xi = 48)
    public static final class TRAY_9 extends InputTrayType {
        public static final TRAY_9 INSTANCE = new TRAY_9();

        public TRAY_9() {
            super(C1661R.string.hp_print_sdk_printer_input_tray_9, new Regex(".*tray-9.*", RegexOption.IGNORE_CASE), null);
        }
    }

    public InputTrayType(int i, Regex regex) {
        super(i, regex);
    }

    public InputTrayType(int i, Regex regex, DefaultConstructorMarker defaultConstructorMarker) {
        this(i, regex);
    }
}
