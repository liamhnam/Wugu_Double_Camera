package com.p020hp.open.printsdk.options;

import com.p020hp.jipp.model.PrintColorMode;
import com.p020hp.printsdk.C1661R;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.text.Regex;
import kotlin.text.RegexOption;

@Metadata(m1292d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0003\u0007\b\tB\u0017\b\u0004\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006\u0082\u0001\u0003\n\u000b\f¨\u0006\r"}, m1293d2 = {"Lcom/hp/open/printsdk/options/ColorType;", "Lcom/hp/open/printsdk/options/Type;", "labelId", "", "regex", "Lkotlin/text/Regex;", "(ILkotlin/text/Regex;)V", "Color", "Monochrome", "ProcessMonochrome", "Lcom/hp/open/printsdk/options/ColorType$Color;", "Lcom/hp/open/printsdk/options/ColorType$Monochrome;", "Lcom/hp/open/printsdk/options/ColorType$ProcessMonochrome;", "print-core_thirdPartyRelease"}, m1294k = 1, m1295mv = {1, 6, 0}, m1297xi = 48)
public abstract class ColorType extends Type {

    @Metadata(m1292d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, m1293d2 = {"Lcom/hp/open/printsdk/options/ColorType$Color;", "Lcom/hp/open/printsdk/options/ColorType;", "()V", "print-core_thirdPartyRelease"}, m1294k = 1, m1295mv = {1, 6, 0}, m1297xi = 48)
    public static final class Color extends ColorType {
        public static final Color INSTANCE = new Color();

        public Color() {
            super(C1661R.string.hp_print_sdk_types_color, new Regex(".*color.*", RegexOption.IGNORE_CASE), null);
        }
    }

    @Metadata(m1292d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, m1293d2 = {"Lcom/hp/open/printsdk/options/ColorType$Monochrome;", "Lcom/hp/open/printsdk/options/ColorType;", "()V", "print-core_thirdPartyRelease"}, m1294k = 1, m1295mv = {1, 6, 0}, m1297xi = 48)
    public static final class Monochrome extends ColorType {
        public static final Monochrome INSTANCE = new Monochrome();

        public Monochrome() {
            super(C1661R.string.hp_print_sdk_types_monochrome, new Regex("monochrome", RegexOption.IGNORE_CASE), null);
        }
    }

    @Metadata(m1292d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, m1293d2 = {"Lcom/hp/open/printsdk/options/ColorType$ProcessMonochrome;", "Lcom/hp/open/printsdk/options/ColorType;", "()V", "print-core_thirdPartyRelease"}, m1294k = 1, m1295mv = {1, 6, 0}, m1297xi = 48)
    public static final class ProcessMonochrome extends ColorType {
        public static final ProcessMonochrome INSTANCE = new ProcessMonochrome();

        public ProcessMonochrome() {
            super(C1661R.string.hp_print_sdk_types_process_monochrome, new Regex(PrintColorMode.processMonochrome, RegexOption.IGNORE_CASE), null);
        }
    }

    public ColorType(int i, Regex regex) {
        super(i, regex);
    }

    public ColorType(int i, Regex regex, DefaultConstructorMarker defaultConstructorMarker) {
        this(i, regex);
    }
}
