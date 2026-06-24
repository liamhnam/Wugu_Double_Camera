package com.p020hp.open.printsdk.options;

import com.p020hp.jipp.model.PrintScaling;
import com.p020hp.printsdk.C1661R;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.text.Regex;
import kotlin.text.RegexOption;

@Metadata(m1292d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0004\u0007\b\t\nB\u0017\b\u0004\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006\u0082\u0001\u0004\u000b\f\r\u000e¨\u0006\u000f"}, m1293d2 = {"Lcom/hp/open/printsdk/options/ScaleType;", "Lcom/hp/open/printsdk/options/Type;", "labelId", "", "regex", "Lkotlin/text/Regex;", "(ILkotlin/text/Regex;)V", "AUTOFIT", "Fill", "Fit", "NONE", "Lcom/hp/open/printsdk/options/ScaleType$Fill;", "Lcom/hp/open/printsdk/options/ScaleType$Fit;", "Lcom/hp/open/printsdk/options/ScaleType$AUTOFIT;", "Lcom/hp/open/printsdk/options/ScaleType$NONE;", "print-core_thirdPartyRelease"}, m1294k = 1, m1295mv = {1, 6, 0}, m1297xi = 48)
public abstract class ScaleType extends Type {

    @Metadata(m1292d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, m1293d2 = {"Lcom/hp/open/printsdk/options/ScaleType$AUTOFIT;", "Lcom/hp/open/printsdk/options/ScaleType;", "()V", "print-core_thirdPartyRelease"}, m1294k = 1, m1295mv = {1, 6, 0}, m1297xi = 48)
    public static final class AUTOFIT extends ScaleType {
        public static final AUTOFIT INSTANCE = new AUTOFIT();

        public AUTOFIT() {
            super(C1661R.string.hp_print_sdk_types_auto_fit, new Regex(PrintScaling.autoFit, RegexOption.IGNORE_CASE), null);
        }
    }

    @Metadata(m1292d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, m1293d2 = {"Lcom/hp/open/printsdk/options/ScaleType$Fill;", "Lcom/hp/open/printsdk/options/ScaleType;", "()V", "print-core_thirdPartyRelease"}, m1294k = 1, m1295mv = {1, 6, 0}, m1297xi = 48)
    public static final class Fill extends ScaleType {
        public static final Fill INSTANCE = new Fill();

        public Fill() {
            super(C1661R.string.hp_print_sdk_types_fill, new Regex(".*fill.*", RegexOption.IGNORE_CASE), null);
        }
    }

    @Metadata(m1292d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, m1293d2 = {"Lcom/hp/open/printsdk/options/ScaleType$Fit;", "Lcom/hp/open/printsdk/options/ScaleType;", "()V", "print-core_thirdPartyRelease"}, m1294k = 1, m1295mv = {1, 6, 0}, m1297xi = 48)
    public static final class Fit extends ScaleType {
        public static final Fit INSTANCE = new Fit();

        public Fit() {
            super(C1661R.string.hp_print_sdk_types_fit, new Regex(".*fit.*", RegexOption.IGNORE_CASE), null);
        }
    }

    @Metadata(m1292d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, m1293d2 = {"Lcom/hp/open/printsdk/options/ScaleType$NONE;", "Lcom/hp/open/printsdk/options/ScaleType;", "()V", "print-core_thirdPartyRelease"}, m1294k = 1, m1295mv = {1, 6, 0}, m1297xi = 48)
    public static final class NONE extends ScaleType {
        public static final NONE INSTANCE = new NONE();

        public NONE() {
            super(C1661R.string.hp_print_sdk_types_none, new Regex("none", RegexOption.IGNORE_CASE), null);
        }
    }

    public ScaleType(int i, Regex regex) {
        super(i, regex);
    }

    public ScaleType(int i, Regex regex, DefaultConstructorMarker defaultConstructorMarker) {
        this(i, regex);
    }
}
