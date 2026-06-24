package com.p020hp.open.printsdk.options;

import com.p020hp.jipp.model.Sides;
import com.p020hp.printsdk.C1661R;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.text.Regex;
import kotlin.text.RegexOption;

@Metadata(m1292d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0003\u0007\b\tB\u0017\b\u0004\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006\u0082\u0001\u0003\n\u000b\f¨\u0006\r"}, m1293d2 = {"Lcom/hp/open/printsdk/options/SideType;", "Lcom/hp/open/printsdk/options/Type;", "labelId", "", "regex", "Lkotlin/text/Regex;", "(ILkotlin/text/Regex;)V", "Duplex_LONG_EDGE", "Duplex_SHORT_EDGE", "OneSide", "Lcom/hp/open/printsdk/options/SideType$OneSide;", "Lcom/hp/open/printsdk/options/SideType$Duplex_LONG_EDGE;", "Lcom/hp/open/printsdk/options/SideType$Duplex_SHORT_EDGE;", "print-core_thirdPartyRelease"}, m1294k = 1, m1295mv = {1, 6, 0}, m1297xi = 48)
public abstract class SideType extends Type {

    @Metadata(m1292d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, m1293d2 = {"Lcom/hp/open/printsdk/options/SideType$Duplex_LONG_EDGE;", "Lcom/hp/open/printsdk/options/SideType;", "()V", "print-core_thirdPartyRelease"}, m1294k = 1, m1295mv = {1, 6, 0}, m1297xi = 48)
    public static final class Duplex_LONG_EDGE extends SideType {
        public static final Duplex_LONG_EDGE INSTANCE = new Duplex_LONG_EDGE();

        public Duplex_LONG_EDGE() {
            super(C1661R.string.hp_print_sdk_types_duplex_long_edge, new Regex(Sides.twoSidedLongEdge, RegexOption.IGNORE_CASE), null);
        }
    }

    @Metadata(m1292d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, m1293d2 = {"Lcom/hp/open/printsdk/options/SideType$Duplex_SHORT_EDGE;", "Lcom/hp/open/printsdk/options/SideType;", "()V", "print-core_thirdPartyRelease"}, m1294k = 1, m1295mv = {1, 6, 0}, m1297xi = 48)
    public static final class Duplex_SHORT_EDGE extends SideType {
        public static final Duplex_SHORT_EDGE INSTANCE = new Duplex_SHORT_EDGE();

        public Duplex_SHORT_EDGE() {
            super(C1661R.string.hp_print_sdk_types_duplex_short_edge, new Regex(Sides.twoSidedShortEdge, RegexOption.IGNORE_CASE), null);
        }
    }

    @Metadata(m1292d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, m1293d2 = {"Lcom/hp/open/printsdk/options/SideType$OneSide;", "Lcom/hp/open/printsdk/options/SideType;", "()V", "print-core_thirdPartyRelease"}, m1294k = 1, m1295mv = {1, 6, 0}, m1297xi = 48)
    public static final class OneSide extends SideType {
        public static final OneSide INSTANCE = new OneSide();

        public OneSide() {
            super(C1661R.string.hp_print_sdk_types_one_side, new Regex(".*one-side.*", RegexOption.IGNORE_CASE), null);
        }
    }

    public SideType(int i, Regex regex) {
        super(i, regex);
    }

    public SideType(int i, Regex regex, DefaultConstructorMarker defaultConstructorMarker) {
        this(i, regex);
    }
}
