package com.p020hp.open.printsdk.options;

import com.p020hp.printsdk.C1661R;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

@Metadata(m1292d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0004\u0005\u0006\u0007\bB\u000f\b\u0004\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004\u0082\u0001\u0004\t\n\u000b\f¨\u0006\r"}, m1293d2 = {"Lcom/hp/open/printsdk/options/PageRangesType;", "Lcom/hp/open/printsdk/options/Type;", "labelId", "", "(I)V", "ALL", "CUSTOM", "EVEN", "ODD", "Lcom/hp/open/printsdk/options/PageRangesType$ALL;", "Lcom/hp/open/printsdk/options/PageRangesType$ODD;", "Lcom/hp/open/printsdk/options/PageRangesType$EVEN;", "Lcom/hp/open/printsdk/options/PageRangesType$CUSTOM;", "print-core_thirdPartyRelease"}, m1294k = 1, m1295mv = {1, 6, 0}, m1297xi = 48)
public abstract class PageRangesType extends Type {

    @Metadata(m1292d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, m1293d2 = {"Lcom/hp/open/printsdk/options/PageRangesType$ALL;", "Lcom/hp/open/printsdk/options/PageRangesType;", "()V", "print-core_thirdPartyRelease"}, m1294k = 1, m1295mv = {1, 6, 0}, m1297xi = 48)
    public static final class ALL extends PageRangesType {
        public static final ALL INSTANCE = new ALL();

        public ALL() {
            super(C1661R.string.hp_print_sdk_page_range_all, null);
        }
    }

    @Metadata(m1292d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, m1293d2 = {"Lcom/hp/open/printsdk/options/PageRangesType$CUSTOM;", "Lcom/hp/open/printsdk/options/PageRangesType;", "()V", "print-core_thirdPartyRelease"}, m1294k = 1, m1295mv = {1, 6, 0}, m1297xi = 48)
    public static final class CUSTOM extends PageRangesType {
        public static final CUSTOM INSTANCE = new CUSTOM();

        public CUSTOM() {
            super(C1661R.string.hp_print_sdk_page_range_custom, null);
        }
    }

    @Metadata(m1292d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, m1293d2 = {"Lcom/hp/open/printsdk/options/PageRangesType$EVEN;", "Lcom/hp/open/printsdk/options/PageRangesType;", "()V", "print-core_thirdPartyRelease"}, m1294k = 1, m1295mv = {1, 6, 0}, m1297xi = 48)
    public static final class EVEN extends PageRangesType {
        public static final EVEN INSTANCE = new EVEN();

        public EVEN() {
            super(C1661R.string.hp_print_sdk_page_range_even, null);
        }
    }

    @Metadata(m1292d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, m1293d2 = {"Lcom/hp/open/printsdk/options/PageRangesType$ODD;", "Lcom/hp/open/printsdk/options/PageRangesType;", "()V", "print-core_thirdPartyRelease"}, m1294k = 1, m1295mv = {1, 6, 0}, m1297xi = 48)
    public static final class ODD extends PageRangesType {
        public static final ODD INSTANCE = new ODD();

        public ODD() {
            super(C1661R.string.hp_print_sdk_page_range_odd, null);
        }
    }

    public PageRangesType(int i) {
        super(i, null, 2, null);
    }

    public PageRangesType(int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(i);
    }
}
