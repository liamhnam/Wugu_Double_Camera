package com.p020hp.open.printsdk.options;

import com.p020hp.printsdk.C1661R;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.text.Regex;
import kotlin.text.RegexOption;

@Metadata(m1292d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0004\u0007\b\t\nB\u0019\b\u0004\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u0006\u0082\u0001\u0004\u000b\f\r\u000e¨\u0006\u000f"}, m1293d2 = {"Lcom/hp/open/printsdk/options/PrintType;", "Lcom/hp/open/printsdk/options/Type;", "labelId", "", "regex", "Lkotlin/text/Regex;", "(ILkotlin/text/Regex;)V", "JPEG_Direct", "PCLm", "PDF_Direct", "Pwg", "Lcom/hp/open/printsdk/options/PrintType$PCLm;", "Lcom/hp/open/printsdk/options/PrintType$Pwg;", "Lcom/hp/open/printsdk/options/PrintType$JPEG_Direct;", "Lcom/hp/open/printsdk/options/PrintType$PDF_Direct;", "print-core_thirdPartyRelease"}, m1294k = 1, m1295mv = {1, 6, 0}, m1297xi = 48)
public abstract class PrintType extends Type {

    @Metadata(m1292d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, m1293d2 = {"Lcom/hp/open/printsdk/options/PrintType$JPEG_Direct;", "Lcom/hp/open/printsdk/options/PrintType;", "()V", "print-core_thirdPartyRelease"}, m1294k = 1, m1295mv = {1, 6, 0}, m1297xi = 48)
    public static final class JPEG_Direct extends PrintType {
        public static final JPEG_Direct INSTANCE = new JPEG_Direct();

        public JPEG_Direct() {
            super(C1661R.string.hp_print_sdk_label_print_type_direct_jpeg, new Regex(".*image/jpeg.*", RegexOption.IGNORE_CASE), null);
        }
    }

    @Metadata(m1292d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, m1293d2 = {"Lcom/hp/open/printsdk/options/PrintType$PCLm;", "Lcom/hp/open/printsdk/options/PrintType;", "()V", "print-core_thirdPartyRelease"}, m1294k = 1, m1295mv = {1, 6, 0}, m1297xi = 48)
    public static final class PCLm extends PrintType {
        public static final PCLm INSTANCE = new PCLm();

        public PCLm() {
            super(C1661R.string.hp_print_sdk_label_print_type_pclm, new Regex(".*application/PCLm.*", RegexOption.IGNORE_CASE), null);
        }
    }

    @Metadata(m1292d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, m1293d2 = {"Lcom/hp/open/printsdk/options/PrintType$PDF_Direct;", "Lcom/hp/open/printsdk/options/PrintType;", "()V", "print-core_thirdPartyRelease"}, m1294k = 1, m1295mv = {1, 6, 0}, m1297xi = 48)
    public static final class PDF_Direct extends PrintType {
        public static final PDF_Direct INSTANCE = new PDF_Direct();

        public PDF_Direct() {
            super(C1661R.string.hp_print_sdk_label_print_type_direct_pdf, new Regex(".*application/pdf.*", RegexOption.IGNORE_CASE), null);
        }
    }

    @Metadata(m1292d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, m1293d2 = {"Lcom/hp/open/printsdk/options/PrintType$Pwg;", "Lcom/hp/open/printsdk/options/PrintType;", "()V", "print-core_thirdPartyRelease"}, m1294k = 1, m1295mv = {1, 6, 0}, m1297xi = 48)
    public static final class Pwg extends PrintType {
        public static final Pwg INSTANCE = new Pwg();

        public Pwg() {
            super(C1661R.string.hp_print_sdk_label_print_type_pwg, new Regex(".*image/pwg-raster.*", RegexOption.IGNORE_CASE), null);
        }
    }

    public PrintType(int i, Regex regex) {
        super(i, regex);
    }

    public PrintType(int i, Regex regex, DefaultConstructorMarker defaultConstructorMarker) {
        this(i, regex);
    }
}
