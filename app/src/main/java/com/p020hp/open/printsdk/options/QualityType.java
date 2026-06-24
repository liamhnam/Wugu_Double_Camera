package com.p020hp.open.printsdk.options;

import com.p020hp.printsdk.C1661R;
import com.tom_roush.pdfbox.pdmodel.documentinterchange.taggedpdf.PDLayoutAttributeObject;
import com.tom_roush.pdfbox.pdmodel.interactive.annotation.PDAnnotationRubberStamp;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.text.Regex;
import kotlin.text.RegexOption;

@Metadata(m1292d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0003\u0007\b\tB\u0017\b\u0004\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006\u0082\u0001\u0003\n\u000b\f¨\u0006\r"}, m1293d2 = {"Lcom/hp/open/printsdk/options/QualityType;", "Lcom/hp/open/printsdk/options/Type;", "labelId", "", "regex", "Lkotlin/text/Regex;", "(ILkotlin/text/Regex;)V", PDAnnotationRubberStamp.NAME_DRAFT, "High", PDLayoutAttributeObject.LINE_HEIGHT_NORMAL, "Lcom/hp/open/printsdk/options/QualityType$Draft;", "Lcom/hp/open/printsdk/options/QualityType$Normal;", "Lcom/hp/open/printsdk/options/QualityType$High;", "print-core_thirdPartyRelease"}, m1294k = 1, m1295mv = {1, 6, 0}, m1297xi = 48)
public abstract class QualityType extends Type {

    @Metadata(m1292d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, m1293d2 = {"Lcom/hp/open/printsdk/options/QualityType$Draft;", "Lcom/hp/open/printsdk/options/QualityType;", "()V", "print-core_thirdPartyRelease"}, m1294k = 1, m1295mv = {1, 6, 0}, m1297xi = 48)
    public static final class Draft extends QualityType {
        public static final Draft INSTANCE = new Draft();

        public Draft() {
            super(C1661R.string.hp_print_sdk_types_draft, new Regex(".*draft.*", RegexOption.IGNORE_CASE), null);
        }
    }

    @Metadata(m1292d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, m1293d2 = {"Lcom/hp/open/printsdk/options/QualityType$High;", "Lcom/hp/open/printsdk/options/QualityType;", "()V", "print-core_thirdPartyRelease"}, m1294k = 1, m1295mv = {1, 6, 0}, m1297xi = 48)
    public static final class High extends QualityType {
        public static final High INSTANCE = new High();

        public High() {
            super(C1661R.string.hp_print_sdk_types_high, new Regex(".*high.*", RegexOption.IGNORE_CASE), null);
        }
    }

    @Metadata(m1292d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, m1293d2 = {"Lcom/hp/open/printsdk/options/QualityType$Normal;", "Lcom/hp/open/printsdk/options/QualityType;", "()V", "print-core_thirdPartyRelease"}, m1294k = 1, m1295mv = {1, 6, 0}, m1297xi = 48)
    public static final class Normal extends QualityType {
        public static final Normal INSTANCE = new Normal();

        public Normal() {
            super(C1661R.string.hp_print_sdk_types_normal, new Regex(".*normal.*", RegexOption.IGNORE_CASE), null);
        }
    }

    public QualityType(int i, Regex regex) {
        super(i, regex);
    }

    public QualityType(int i, Regex regex, DefaultConstructorMarker defaultConstructorMarker) {
        this(i, regex);
    }
}
