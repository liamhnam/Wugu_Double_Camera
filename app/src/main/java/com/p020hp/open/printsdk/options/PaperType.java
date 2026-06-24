package com.p020hp.open.printsdk.options;

import com.p020hp.jipp.model.MediaType;
import com.p020hp.printsdk.C1661R;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.text.Regex;
import kotlin.text.RegexOption;

@Metadata(m1292d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0002\u0007\bB\u0017\b\u0004\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006\u0082\u0001\u0002\t\n¨\u0006\u000b"}, m1293d2 = {"Lcom/hp/open/printsdk/options/PaperType;", "Lcom/hp/open/printsdk/options/Type;", "labelId", "", "regex", "Lkotlin/text/Regex;", "(ILkotlin/text/Regex;)V", "PhotoGlossPaper", "PlainPaper", "Lcom/hp/open/printsdk/options/PaperType$PlainPaper;", "Lcom/hp/open/printsdk/options/PaperType$PhotoGlossPaper;", "print-core_thirdPartyRelease"}, m1294k = 1, m1295mv = {1, 6, 0}, m1297xi = 48)
public abstract class PaperType extends Type {

    @Metadata(m1292d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, m1293d2 = {"Lcom/hp/open/printsdk/options/PaperType$PhotoGlossPaper;", "Lcom/hp/open/printsdk/options/PaperType;", "()V", "print-core_thirdPartyRelease"}, m1294k = 1, m1295mv = {1, 6, 0}, m1297xi = 48)
    public static final class PhotoGlossPaper extends PaperType {
        public static final PhotoGlossPaper INSTANCE = new PhotoGlossPaper();

        public PhotoGlossPaper() {
            super(C1661R.string.hp_print_sdk_label_paper_type_photo_glossy, new Regex("(.*photographic-glossy|com.hp.advanced-photo)", RegexOption.IGNORE_CASE), null);
        }
    }

    @Metadata(m1292d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, m1293d2 = {"Lcom/hp/open/printsdk/options/PaperType$PlainPaper;", "Lcom/hp/open/printsdk/options/PaperType;", "()V", "print-core_thirdPartyRelease"}, m1294k = 1, m1295mv = {1, 6, 0}, m1297xi = 48)
    public static final class PlainPaper extends PaperType {
        public static final PlainPaper INSTANCE = new PlainPaper();

        public PlainPaper() {
            super(C1661R.string.hp_print_sdk_label_paper_type_plain, new Regex(MediaType.stationery, RegexOption.IGNORE_CASE), null);
        }
    }

    public PaperType(int i, Regex regex) {
        super(i, regex);
    }

    public PaperType(int i, Regex regex, DefaultConstructorMarker defaultConstructorMarker) {
        this(i, regex);
    }
}
