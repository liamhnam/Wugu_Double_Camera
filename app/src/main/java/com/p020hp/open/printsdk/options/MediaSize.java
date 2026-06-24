package com.p020hp.open.printsdk.options;

import com.p020hp.jipp.model.MediaCol;
import com.p020hp.printsdk.C1661R;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.text.Regex;
import kotlin.text.RegexOption;
import org.apache.log4j.helpers.UtilLoggingLevel;

@Metadata(m1292d1 = {"\u0000¼\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:'\u000b\f\r\u000e\u000f\u0010\u0011\u0012\u0013\u0014\u0015\u0016\u0017\u0018\u0019\u001a\u001b\u001c\u001d\u001e\u001f !\"#$%&'()*+,-./01B\u001f\b\u0004\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n\u0082\u0001'23456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWX¨\u0006Y"}, m1293d2 = {"Lcom/hp/open/printsdk/options/MediaSize;", "Lcom/hp/open/printsdk/options/Type;", "labelId", "", "mediaSize", "Lcom/hp/jipp/model/MediaCol$MediaSize;", "regex", "Lkotlin/text/Regex;", "(ILcom/hp/jipp/model/MediaCol$MediaSize;Lkotlin/text/Regex;)V", "getMediaSize", "()Lcom/hp/jipp/model/MediaCol$MediaSize;", "Custom_100_254", "INCH_3_5x5", "INCH_3_625x6_5", "INCH_3_875x7_5", "INCH_3x5", "INCH_4_125x9_5", "INCH_4_375x5_75", "INCH_4x12", "INCH_4x5", "INCH_4x6", "INCH_5_5x8_5", "INCH_5x5", "INCH_5x7", "INCH_5x8", "INCH_7_25x10_5", "INCH_7_75x10_75", "INCH_8_5x11", "INCH_8_5x13", "INCH_8_5x13_4", "INCH_8_5x14", "INCH_8x10", "ISO_A3", "ISO_A4", "ISO_A5", "ISO_A6", "ISO_B5", "ISO_C5", "ISO_C6", "ISO_DL", "JIS_B5", "JIS_B6", "JPN_CHOU3", "JPN_CHOU4", "JPN_HAGAKI", "JPN_OUFUKU", "JPN_PHOTO", "OM_SMALL_PHOTO", "PRC_16k_184x260", "PRC_16k_195x270", "Lcom/hp/open/printsdk/options/MediaSize$INCH_7_25x10_5;", "Lcom/hp/open/printsdk/options/MediaSize$INCH_8_5x11;", "Lcom/hp/open/printsdk/options/MediaSize$INCH_8_5x14;", "Lcom/hp/open/printsdk/options/MediaSize$INCH_8x10;", "Lcom/hp/open/printsdk/options/MediaSize$INCH_5_5x8_5;", "Lcom/hp/open/printsdk/options/MediaSize$JIS_B5;", "Lcom/hp/open/printsdk/options/MediaSize$JPN_HAGAKI;", "Lcom/hp/open/printsdk/options/MediaSize$ISO_A6;", "Lcom/hp/open/printsdk/options/MediaSize$INCH_3x5;", "Lcom/hp/open/printsdk/options/MediaSize$OM_SMALL_PHOTO;", "Lcom/hp/open/printsdk/options/MediaSize$INCH_5x8;", "Lcom/hp/open/printsdk/options/MediaSize$INCH_3_875x7_5;", "Lcom/hp/open/printsdk/options/MediaSize$INCH_4_125x9_5;", "Lcom/hp/open/printsdk/options/MediaSize$ISO_DL;", "Lcom/hp/open/printsdk/options/MediaSize$ISO_C5;", "Lcom/hp/open/printsdk/options/MediaSize$ISO_C6;", "Lcom/hp/open/printsdk/options/MediaSize$INCH_4_375x5_75;", "Lcom/hp/open/printsdk/options/MediaSize$INCH_3_625x6_5;", "Lcom/hp/open/printsdk/options/MediaSize$JPN_CHOU3;", "Lcom/hp/open/printsdk/options/MediaSize$JPN_CHOU4;", "Lcom/hp/open/printsdk/options/MediaSize$INCH_3_5x5;", "Lcom/hp/open/printsdk/options/MediaSize$JPN_PHOTO;", "Lcom/hp/open/printsdk/options/MediaSize$INCH_8_5x13;", "Lcom/hp/open/printsdk/options/MediaSize$INCH_4x5;", "Lcom/hp/open/printsdk/options/MediaSize$INCH_4x12;", "Lcom/hp/open/printsdk/options/MediaSize$INCH_8_5x13_4;", "Lcom/hp/open/printsdk/options/MediaSize$JIS_B6;", "Lcom/hp/open/printsdk/options/MediaSize$PRC_16k_195x270;", "Lcom/hp/open/printsdk/options/MediaSize$PRC_16k_184x260;", "Lcom/hp/open/printsdk/options/MediaSize$INCH_7_75x10_75;", "Lcom/hp/open/printsdk/options/MediaSize$JPN_OUFUKU;", "Lcom/hp/open/printsdk/options/MediaSize$ISO_B5;", "Lcom/hp/open/printsdk/options/MediaSize$INCH_5x5;", "Lcom/hp/open/printsdk/options/MediaSize$ISO_A4;", "Lcom/hp/open/printsdk/options/MediaSize$INCH_5x7;", "Lcom/hp/open/printsdk/options/MediaSize$INCH_4x6;", "Lcom/hp/open/printsdk/options/MediaSize$ISO_A5;", "Lcom/hp/open/printsdk/options/MediaSize$ISO_A3;", "Lcom/hp/open/printsdk/options/MediaSize$Custom_100_254;", "print-core_thirdPartyRelease"}, m1294k = 1, m1295mv = {1, 6, 0}, m1297xi = 48)
public abstract class MediaSize extends Type {

    public final MediaCol.MediaSize f827c;

    @Metadata(m1292d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, m1293d2 = {"Lcom/hp/open/printsdk/options/MediaSize$Custom_100_254;", "Lcom/hp/open/printsdk/options/MediaSize;", "()V", "print-core_thirdPartyRelease"}, m1294k = 1, m1295mv = {1, 6, 0}, m1297xi = 48)
    public static final class Custom_100_254 extends MediaSize {
        public static final Custom_100_254 INSTANCE = new Custom_100_254();

        public Custom_100_254() {
            super(C1661R.string.hp_print_sdk_types_custom_media_100x254, new MediaCol.MediaSize(10000, 25400), new Regex("custom_max_216x356mm", RegexOption.IGNORE_CASE), null);
        }
    }

    @Metadata(m1292d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, m1293d2 = {"Lcom/hp/open/printsdk/options/MediaSize$INCH_3_5x5;", "Lcom/hp/open/printsdk/options/MediaSize;", "()V", "print-core_thirdPartyRelease"}, m1294k = 1, m1295mv = {1, 6, 0}, m1297xi = 48)
    public static final class INCH_3_5x5 extends MediaSize {
        public static final INCH_3_5x5 INSTANCE = new INCH_3_5x5();

        public INCH_3_5x5() {
            super(C1661R.string.hp_print_sdk_types_inch_3_5x5, new MediaCol.MediaSize(8890, 12700), new Regex(".*3.5x5in.*", RegexOption.IGNORE_CASE), null);
        }
    }

    @Metadata(m1292d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, m1293d2 = {"Lcom/hp/open/printsdk/options/MediaSize$INCH_3_625x6_5;", "Lcom/hp/open/printsdk/options/MediaSize;", "()V", "print-core_thirdPartyRelease"}, m1294k = 1, m1295mv = {1, 6, 0}, m1297xi = 48)
    public static final class INCH_3_625x6_5 extends MediaSize {
        public static final INCH_3_625x6_5 INSTANCE = new INCH_3_625x6_5();

        public INCH_3_625x6_5() {
            super(C1661R.string.hp_print_sdk_types_inch_3_625x6_5, new MediaCol.MediaSize(9207, 16510), new Regex(".*3.625x6.5in.*", RegexOption.IGNORE_CASE), null);
        }
    }

    @Metadata(m1292d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, m1293d2 = {"Lcom/hp/open/printsdk/options/MediaSize$INCH_3_875x7_5;", "Lcom/hp/open/printsdk/options/MediaSize;", "()V", "print-core_thirdPartyRelease"}, m1294k = 1, m1295mv = {1, 6, 0}, m1297xi = 48)
    public static final class INCH_3_875x7_5 extends MediaSize {
        public static final INCH_3_875x7_5 INSTANCE = new INCH_3_875x7_5();

        public INCH_3_875x7_5() {
            super(C1661R.string.hp_print_sdk_types_inch_3_875x7_5, new MediaCol.MediaSize(9842, 19050), new Regex(".*3.875x7.5in.*", RegexOption.IGNORE_CASE), null);
        }
    }

    @Metadata(m1292d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, m1293d2 = {"Lcom/hp/open/printsdk/options/MediaSize$INCH_3x5;", "Lcom/hp/open/printsdk/options/MediaSize;", "()V", "print-core_thirdPartyRelease"}, m1294k = 1, m1295mv = {1, 6, 0}, m1297xi = 48)
    public static final class INCH_3x5 extends MediaSize {
        public static final INCH_3x5 INSTANCE = new INCH_3x5();

        public INCH_3x5() {
            super(C1661R.string.hp_print_sdk_types_inch_3x5, new MediaCol.MediaSize(7620, 12700), new Regex(".*3x5in.*", RegexOption.IGNORE_CASE), null);
        }
    }

    @Metadata(m1292d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, m1293d2 = {"Lcom/hp/open/printsdk/options/MediaSize$INCH_4_125x9_5;", "Lcom/hp/open/printsdk/options/MediaSize;", "()V", "print-core_thirdPartyRelease"}, m1294k = 1, m1295mv = {1, 6, 0}, m1297xi = 48)
    public static final class INCH_4_125x9_5 extends MediaSize {
        public static final INCH_4_125x9_5 INSTANCE = new INCH_4_125x9_5();

        public INCH_4_125x9_5() {
            super(C1661R.string.hp_print_sdk_types_inch_4_125x9_5, new MediaCol.MediaSize(10477, 24130), new Regex(".*10_4.125x9.5in.*", RegexOption.IGNORE_CASE), null);
        }
    }

    @Metadata(m1292d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, m1293d2 = {"Lcom/hp/open/printsdk/options/MediaSize$INCH_4_375x5_75;", "Lcom/hp/open/printsdk/options/MediaSize;", "()V", "print-core_thirdPartyRelease"}, m1294k = 1, m1295mv = {1, 6, 0}, m1297xi = 48)
    public static final class INCH_4_375x5_75 extends MediaSize {
        public static final INCH_4_375x5_75 INSTANCE = new INCH_4_375x5_75();

        public INCH_4_375x5_75() {
            super(C1661R.string.hp_print_sdk_types_inch_4_375x5_75, new MediaCol.MediaSize(11112, 14605), new Regex(".*4.375x5.75in.*", RegexOption.IGNORE_CASE), null);
        }
    }

    @Metadata(m1292d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, m1293d2 = {"Lcom/hp/open/printsdk/options/MediaSize$INCH_4x12;", "Lcom/hp/open/printsdk/options/MediaSize;", "()V", "print-core_thirdPartyRelease"}, m1294k = 1, m1295mv = {1, 6, 0}, m1297xi = 48)
    public static final class INCH_4x12 extends MediaSize {
        public static final INCH_4x12 INSTANCE = new INCH_4x12();

        public INCH_4x12() {
            super(C1661R.string.hp_print_sdk_types_inch_4x12, new MediaCol.MediaSize(10160, 30480), new Regex(".*4x12in.*", RegexOption.IGNORE_CASE), null);
        }
    }

    @Metadata(m1292d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, m1293d2 = {"Lcom/hp/open/printsdk/options/MediaSize$INCH_4x5;", "Lcom/hp/open/printsdk/options/MediaSize;", "()V", "print-core_thirdPartyRelease"}, m1294k = 1, m1295mv = {1, 6, 0}, m1297xi = 48)
    public static final class INCH_4x5 extends MediaSize {
        public static final INCH_4x5 INSTANCE = new INCH_4x5();

        public INCH_4x5() {
            super(C1661R.string.hp_print_sdk_types_inch_4x5, new MediaCol.MediaSize(10160, 12700), new Regex(".*4x5in.*", RegexOption.IGNORE_CASE), null);
        }
    }

    @Metadata(m1292d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, m1293d2 = {"Lcom/hp/open/printsdk/options/MediaSize$INCH_4x6;", "Lcom/hp/open/printsdk/options/MediaSize;", "()V", "print-core_thirdPartyRelease"}, m1294k = 1, m1295mv = {1, 6, 0}, m1297xi = 48)
    public static final class INCH_4x6 extends MediaSize {
        public static final INCH_4x6 INSTANCE = new INCH_4x6();

        public INCH_4x6() {
            super(C1661R.string.hp_print_sdk_types_inch_4x6, new MediaCol.MediaSize(10160, 15240), new Regex(".*4x6in.*", RegexOption.IGNORE_CASE), null);
        }
    }

    @Metadata(m1292d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, m1293d2 = {"Lcom/hp/open/printsdk/options/MediaSize$INCH_5_5x8_5;", "Lcom/hp/open/printsdk/options/MediaSize;", "()V", "print-core_thirdPartyRelease"}, m1294k = 1, m1295mv = {1, 6, 0}, m1297xi = 48)
    public static final class INCH_5_5x8_5 extends MediaSize {
        public static final INCH_5_5x8_5 INSTANCE = new INCH_5_5x8_5();

        public INCH_5_5x8_5() {
            super(C1661R.string.hp_print_sdk_types_inch_5_5x8_5, new MediaCol.MediaSize(13970, 21590), new Regex(".*5.5x8.5in.*", RegexOption.IGNORE_CASE), null);
        }
    }

    @Metadata(m1292d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, m1293d2 = {"Lcom/hp/open/printsdk/options/MediaSize$INCH_5x5;", "Lcom/hp/open/printsdk/options/MediaSize;", "()V", "print-core_thirdPartyRelease"}, m1294k = 1, m1295mv = {1, 6, 0}, m1297xi = 48)
    public static final class INCH_5x5 extends MediaSize {
        public static final INCH_5x5 INSTANCE = new INCH_5x5();

        public INCH_5x5() {
            super(C1661R.string.hp_print_sdk_types_inch_5x5, new MediaCol.MediaSize(12700, 12700), new Regex(".*5x5in.*", RegexOption.IGNORE_CASE), null);
        }
    }

    @Metadata(m1292d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, m1293d2 = {"Lcom/hp/open/printsdk/options/MediaSize$INCH_5x7;", "Lcom/hp/open/printsdk/options/MediaSize;", "()V", "print-core_thirdPartyRelease"}, m1294k = 1, m1295mv = {1, 6, 0}, m1297xi = 48)
    public static final class INCH_5x7 extends MediaSize {
        public static final INCH_5x7 INSTANCE = new INCH_5x7();

        public INCH_5x7() {
            super(C1661R.string.hp_print_sdk_types_inch_5x7, new MediaCol.MediaSize(12700, 17780), new Regex(".*5x7in.*", RegexOption.IGNORE_CASE), null);
        }
    }

    @Metadata(m1292d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, m1293d2 = {"Lcom/hp/open/printsdk/options/MediaSize$INCH_5x8;", "Lcom/hp/open/printsdk/options/MediaSize;", "()V", "print-core_thirdPartyRelease"}, m1294k = 1, m1295mv = {1, 6, 0}, m1297xi = 48)
    public static final class INCH_5x8 extends MediaSize {
        public static final INCH_5x8 INSTANCE = new INCH_5x8();

        public INCH_5x8() {
            super(C1661R.string.hp_print_sdk_types_inch_5x8, new MediaCol.MediaSize(12700, 20320), new Regex(".*5x8in.*", RegexOption.IGNORE_CASE), null);
        }
    }

    @Metadata(m1292d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, m1293d2 = {"Lcom/hp/open/printsdk/options/MediaSize$INCH_7_25x10_5;", "Lcom/hp/open/printsdk/options/MediaSize;", "()V", "print-core_thirdPartyRelease"}, m1294k = 1, m1295mv = {1, 6, 0}, m1297xi = 48)
    public static final class INCH_7_25x10_5 extends MediaSize {
        public static final INCH_7_25x10_5 INSTANCE = new INCH_7_25x10_5();

        public INCH_7_25x10_5() {
            super(C1661R.string.hp_print_sdk_types_inch_7_25x10_5, new MediaCol.MediaSize(18415, 26670), new Regex(".*7.25x10.5in.*", RegexOption.IGNORE_CASE), null);
        }
    }

    @Metadata(m1292d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, m1293d2 = {"Lcom/hp/open/printsdk/options/MediaSize$INCH_7_75x10_75;", "Lcom/hp/open/printsdk/options/MediaSize;", "()V", "print-core_thirdPartyRelease"}, m1294k = 1, m1295mv = {1, 6, 0}, m1297xi = 48)
    public static final class INCH_7_75x10_75 extends MediaSize {
        public static final INCH_7_75x10_75 INSTANCE = new INCH_7_75x10_75();

        public INCH_7_75x10_75() {
            super(C1661R.string.hp_print_sdk_types_inch_7_75x10_75, new MediaCol.MediaSize(19685, 27305), new Regex(".*7.75x10.75in.*", RegexOption.IGNORE_CASE), null);
        }
    }

    @Metadata(m1292d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, m1293d2 = {"Lcom/hp/open/printsdk/options/MediaSize$INCH_8_5x11;", "Lcom/hp/open/printsdk/options/MediaSize;", "()V", "print-core_thirdPartyRelease"}, m1294k = 1, m1295mv = {1, 6, 0}, m1297xi = 48)
    public static final class INCH_8_5x11 extends MediaSize {
        public static final INCH_8_5x11 INSTANCE = new INCH_8_5x11();

        public INCH_8_5x11() {
            super(C1661R.string.hp_print_sdk_types_inch_8_5x11, new MediaCol.MediaSize(21590, 27940), new Regex(".*8.5x11in.*", RegexOption.IGNORE_CASE), null);
        }
    }

    @Metadata(m1292d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, m1293d2 = {"Lcom/hp/open/printsdk/options/MediaSize$INCH_8_5x13;", "Lcom/hp/open/printsdk/options/MediaSize;", "()V", "print-core_thirdPartyRelease"}, m1294k = 1, m1295mv = {1, 6, 0}, m1297xi = 48)
    public static final class INCH_8_5x13 extends MediaSize {
        public static final INCH_8_5x13 INSTANCE = new INCH_8_5x13();

        public INCH_8_5x13() {
            super(C1661R.string.hp_print_sdk_types_inch_8_5x13, new MediaCol.MediaSize(21590, 33020), new Regex(".*8.5x13in.*", RegexOption.IGNORE_CASE), null);
        }
    }

    @Metadata(m1292d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, m1293d2 = {"Lcom/hp/open/printsdk/options/MediaSize$INCH_8_5x13_4;", "Lcom/hp/open/printsdk/options/MediaSize;", "()V", "print-core_thirdPartyRelease"}, m1294k = 1, m1295mv = {1, 6, 0}, m1297xi = 48)
    public static final class INCH_8_5x13_4 extends MediaSize {
        public static final INCH_8_5x13_4 INSTANCE = new INCH_8_5x13_4();

        public INCH_8_5x13_4() {
            super(C1661R.string.hp_print_sdk_types_inch_8_5x13_4, new MediaCol.MediaSize(21590, 34036), new Regex(".*8.5x13.4in.*", RegexOption.IGNORE_CASE), null);
        }
    }

    @Metadata(m1292d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, m1293d2 = {"Lcom/hp/open/printsdk/options/MediaSize$INCH_8_5x14;", "Lcom/hp/open/printsdk/options/MediaSize;", "()V", "print-core_thirdPartyRelease"}, m1294k = 1, m1295mv = {1, 6, 0}, m1297xi = 48)
    public static final class INCH_8_5x14 extends MediaSize {
        public static final INCH_8_5x14 INSTANCE = new INCH_8_5x14();

        public INCH_8_5x14() {
            super(C1661R.string.hp_print_sdk_types_inch_8_5x14, new MediaCol.MediaSize(21590, 35560), new Regex(".*8.5x14in.*", RegexOption.IGNORE_CASE), null);
        }
    }

    @Metadata(m1292d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, m1293d2 = {"Lcom/hp/open/printsdk/options/MediaSize$INCH_8x10;", "Lcom/hp/open/printsdk/options/MediaSize;", "()V", "print-core_thirdPartyRelease"}, m1294k = 1, m1295mv = {1, 6, 0}, m1297xi = 48)
    public static final class INCH_8x10 extends MediaSize {
        public static final INCH_8x10 INSTANCE = new INCH_8x10();

        public INCH_8x10() {
            super(C1661R.string.hp_print_sdk_types_inch_8x10, new MediaCol.MediaSize(20320, 25400), new Regex(".*8x10in.*", RegexOption.IGNORE_CASE), null);
        }
    }

    @Metadata(m1292d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, m1293d2 = {"Lcom/hp/open/printsdk/options/MediaSize$ISO_A3;", "Lcom/hp/open/printsdk/options/MediaSize;", "()V", "print-core_thirdPartyRelease"}, m1294k = 1, m1295mv = {1, 6, 0}, m1297xi = 48)
    public static final class ISO_A3 extends MediaSize {
        public static final ISO_A3 INSTANCE = new ISO_A3();

        public ISO_A3() {
            super(C1661R.string.hp_print_sdk_types_iso_a3, new MediaCol.MediaSize(29700, 42000), new Regex(".*iso.a3.*", RegexOption.IGNORE_CASE), null);
        }
    }

    @Metadata(m1292d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, m1293d2 = {"Lcom/hp/open/printsdk/options/MediaSize$ISO_A4;", "Lcom/hp/open/printsdk/options/MediaSize;", "()V", "print-core_thirdPartyRelease"}, m1294k = 1, m1295mv = {1, 6, 0}, m1297xi = 48)
    public static final class ISO_A4 extends MediaSize {
        public static final ISO_A4 INSTANCE = new ISO_A4();

        public ISO_A4() {
            super(C1661R.string.hp_print_sdk_types_iso_a4, new MediaCol.MediaSize(Integer.valueOf(UtilLoggingLevel.WARNING_INT), 29700), new Regex(".*iso.a4.*", RegexOption.IGNORE_CASE), null);
        }
    }

    @Metadata(m1292d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, m1293d2 = {"Lcom/hp/open/printsdk/options/MediaSize$ISO_A5;", "Lcom/hp/open/printsdk/options/MediaSize;", "()V", "print-core_thirdPartyRelease"}, m1294k = 1, m1295mv = {1, 6, 0}, m1297xi = 48)
    public static final class ISO_A5 extends MediaSize {
        public static final ISO_A5 INSTANCE = new ISO_A5();

        public ISO_A5() {
            super(C1661R.string.hp_print_sdk_types_iso_a5, new MediaCol.MediaSize(14800, Integer.valueOf(UtilLoggingLevel.WARNING_INT)), new Regex(".*iso.a5.*", RegexOption.IGNORE_CASE), null);
        }
    }

    @Metadata(m1292d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, m1293d2 = {"Lcom/hp/open/printsdk/options/MediaSize$ISO_A6;", "Lcom/hp/open/printsdk/options/MediaSize;", "()V", "print-core_thirdPartyRelease"}, m1294k = 1, m1295mv = {1, 6, 0}, m1297xi = 48)
    public static final class ISO_A6 extends MediaSize {
        public static final ISO_A6 INSTANCE = new ISO_A6();

        public ISO_A6() {
            super(C1661R.string.hp_print_sdk_types_iso_a6, new MediaCol.MediaSize(10500, 14800), new Regex(".*105x148mm.*", RegexOption.IGNORE_CASE), null);
        }
    }

    @Metadata(m1292d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, m1293d2 = {"Lcom/hp/open/printsdk/options/MediaSize$ISO_B5;", "Lcom/hp/open/printsdk/options/MediaSize;", "()V", "print-core_thirdPartyRelease"}, m1294k = 1, m1295mv = {1, 6, 0}, m1297xi = 48)
    public static final class ISO_B5 extends MediaSize {
        public static final ISO_B5 INSTANCE = new ISO_B5();

        public ISO_B5() {
            super(C1661R.string.hp_print_sdk_types_ISO_B5, new MediaCol.MediaSize(17600, 25000), new Regex(".*176x250mm.*", RegexOption.IGNORE_CASE), null);
        }
    }

    @Metadata(m1292d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, m1293d2 = {"Lcom/hp/open/printsdk/options/MediaSize$ISO_C5;", "Lcom/hp/open/printsdk/options/MediaSize;", "()V", "print-core_thirdPartyRelease"}, m1294k = 1, m1295mv = {1, 6, 0}, m1297xi = 48)
    public static final class ISO_C5 extends MediaSize {
        public static final ISO_C5 INSTANCE = new ISO_C5();

        public ISO_C5() {
            super(C1661R.string.hp_print_sdk_types_iso_c5, new MediaCol.MediaSize(16200, 22900), new Regex(".*162x229mm.*", RegexOption.IGNORE_CASE), null);
        }
    }

    @Metadata(m1292d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, m1293d2 = {"Lcom/hp/open/printsdk/options/MediaSize$ISO_C6;", "Lcom/hp/open/printsdk/options/MediaSize;", "()V", "print-core_thirdPartyRelease"}, m1294k = 1, m1295mv = {1, 6, 0}, m1297xi = 48)
    public static final class ISO_C6 extends MediaSize {
        public static final ISO_C6 INSTANCE = new ISO_C6();

        public ISO_C6() {
            super(C1661R.string.hp_print_sdk_types_iso_c6, new MediaCol.MediaSize(11400, 16200), new Regex(".*114x162mm.*", RegexOption.IGNORE_CASE), null);
        }
    }

    @Metadata(m1292d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, m1293d2 = {"Lcom/hp/open/printsdk/options/MediaSize$ISO_DL;", "Lcom/hp/open/printsdk/options/MediaSize;", "()V", "print-core_thirdPartyRelease"}, m1294k = 1, m1295mv = {1, 6, 0}, m1297xi = 48)
    public static final class ISO_DL extends MediaSize {
        public static final ISO_DL INSTANCE = new ISO_DL();

        public ISO_DL() {
            super(C1661R.string.hp_print_sdk_types_iso_dl, new MediaCol.MediaSize(Integer.valueOf(UtilLoggingLevel.FINEST_INT), Integer.valueOf(UtilLoggingLevel.SEVERE_INT)), new Regex(".*110x220mm.*", RegexOption.IGNORE_CASE), null);
        }
    }

    @Metadata(m1292d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, m1293d2 = {"Lcom/hp/open/printsdk/options/MediaSize$JIS_B5;", "Lcom/hp/open/printsdk/options/MediaSize;", "()V", "print-core_thirdPartyRelease"}, m1294k = 1, m1295mv = {1, 6, 0}, m1297xi = 48)
    public static final class JIS_B5 extends MediaSize {
        public static final JIS_B5 INSTANCE = new JIS_B5();

        public JIS_B5() {
            super(C1661R.string.hp_print_sdk_types_JIS_B5, new MediaCol.MediaSize(18200, 25700), new Regex(".*182x257mm.*", RegexOption.IGNORE_CASE), null);
        }
    }

    @Metadata(m1292d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, m1293d2 = {"Lcom/hp/open/printsdk/options/MediaSize$JIS_B6;", "Lcom/hp/open/printsdk/options/MediaSize;", "()V", "print-core_thirdPartyRelease"}, m1294k = 1, m1295mv = {1, 6, 0}, m1297xi = 48)
    public static final class JIS_B6 extends MediaSize {
        public static final JIS_B6 INSTANCE = new JIS_B6();

        public JIS_B6() {
            super(C1661R.string.hp_print_sdk_types_JIS_B6, new MediaCol.MediaSize(12800, 18200), new Regex(".*128x182mm.*", RegexOption.IGNORE_CASE), null);
        }
    }

    @Metadata(m1292d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, m1293d2 = {"Lcom/hp/open/printsdk/options/MediaSize$JPN_CHOU3;", "Lcom/hp/open/printsdk/options/MediaSize;", "()V", "print-core_thirdPartyRelease"}, m1294k = 1, m1295mv = {1, 6, 0}, m1297xi = 48)
    public static final class JPN_CHOU3 extends MediaSize {
        public static final JPN_CHOU3 INSTANCE = new JPN_CHOU3();

        public JPN_CHOU3() {
            super(C1661R.string.hp_print_sdk_types_JPN_CHOU3, new MediaCol.MediaSize(Integer.valueOf(UtilLoggingLevel.FINER_INT), 23500), new Regex(".*120x235mm.*", RegexOption.IGNORE_CASE), null);
        }
    }

    @Metadata(m1292d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, m1293d2 = {"Lcom/hp/open/printsdk/options/MediaSize$JPN_CHOU4;", "Lcom/hp/open/printsdk/options/MediaSize;", "()V", "print-core_thirdPartyRelease"}, m1294k = 1, m1295mv = {1, 6, 0}, m1297xi = 48)
    public static final class JPN_CHOU4 extends MediaSize {
        public static final JPN_CHOU4 INSTANCE = new JPN_CHOU4();

        public JPN_CHOU4() {
            super(C1661R.string.hp_print_sdk_types_JPN_CHOU4, new MediaCol.MediaSize(9000, 20500), new Regex(".*90x205mm.*", RegexOption.IGNORE_CASE), null);
        }
    }

    @Metadata(m1292d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, m1293d2 = {"Lcom/hp/open/printsdk/options/MediaSize$JPN_HAGAKI;", "Lcom/hp/open/printsdk/options/MediaSize;", "()V", "print-core_thirdPartyRelease"}, m1294k = 1, m1295mv = {1, 6, 0}, m1297xi = 48)
    public static final class JPN_HAGAKI extends MediaSize {
        public static final JPN_HAGAKI INSTANCE = new JPN_HAGAKI();

        public JPN_HAGAKI() {
            super(C1661R.string.hp_print_sdk_types_JPN_HAGAKI, new MediaCol.MediaSize(10000, 14800), new Regex(".*100x148mm.*", RegexOption.IGNORE_CASE), null);
        }
    }

    @Metadata(m1292d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, m1293d2 = {"Lcom/hp/open/printsdk/options/MediaSize$JPN_OUFUKU;", "Lcom/hp/open/printsdk/options/MediaSize;", "()V", "print-core_thirdPartyRelease"}, m1294k = 1, m1295mv = {1, 6, 0}, m1297xi = 48)
    public static final class JPN_OUFUKU extends MediaSize {
        public static final JPN_OUFUKU INSTANCE = new JPN_OUFUKU();

        public JPN_OUFUKU() {
            super(C1661R.string.hp_print_sdk_types_JPN_OUFUKU, new MediaCol.MediaSize(14800, 20000), new Regex(".*148x200mm.*", RegexOption.IGNORE_CASE), null);
        }
    }

    @Metadata(m1292d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, m1293d2 = {"Lcom/hp/open/printsdk/options/MediaSize$JPN_PHOTO;", "Lcom/hp/open/printsdk/options/MediaSize;", "()V", "print-core_thirdPartyRelease"}, m1294k = 1, m1295mv = {1, 6, 0}, m1297xi = 48)
    public static final class JPN_PHOTO extends MediaSize {
        public static final JPN_PHOTO INSTANCE = new JPN_PHOTO();

        public JPN_PHOTO() {
            super(C1661R.string.hp_print_sdk_types_JPN_PHOTO, new MediaCol.MediaSize(12700, 17780), new Regex(".*127x177.8mm.*", RegexOption.IGNORE_CASE), null);
        }
    }

    @Metadata(m1292d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, m1293d2 = {"Lcom/hp/open/printsdk/options/MediaSize$OM_SMALL_PHOTO;", "Lcom/hp/open/printsdk/options/MediaSize;", "()V", "print-core_thirdPartyRelease"}, m1294k = 1, m1295mv = {1, 6, 0}, m1297xi = 48)
    public static final class OM_SMALL_PHOTO extends MediaSize {
        public static final OM_SMALL_PHOTO INSTANCE = new OM_SMALL_PHOTO();

        public OM_SMALL_PHOTO() {
            super(C1661R.string.hp_print_sdk_types_om_small_photo, new MediaCol.MediaSize(10000, 15000), new Regex(".*100x150mm.*", RegexOption.IGNORE_CASE), null);
        }
    }

    @Metadata(m1292d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, m1293d2 = {"Lcom/hp/open/printsdk/options/MediaSize$PRC_16k_184x260;", "Lcom/hp/open/printsdk/options/MediaSize;", "()V", "print-core_thirdPartyRelease"}, m1294k = 1, m1295mv = {1, 6, 0}, m1297xi = 48)
    public static final class PRC_16k_184x260 extends MediaSize {
        public static final PRC_16k_184x260 INSTANCE = new PRC_16k_184x260();

        public PRC_16k_184x260() {
            super(C1661R.string.hp_print_sdk_types_PRC_16k_184x260, new MediaCol.MediaSize(18400, 26000), new Regex(".*184x260mm.*", RegexOption.IGNORE_CASE), null);
        }
    }

    @Metadata(m1292d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, m1293d2 = {"Lcom/hp/open/printsdk/options/MediaSize$PRC_16k_195x270;", "Lcom/hp/open/printsdk/options/MediaSize;", "()V", "print-core_thirdPartyRelease"}, m1294k = 1, m1295mv = {1, 6, 0}, m1297xi = 48)
    public static final class PRC_16k_195x270 extends MediaSize {
        public static final PRC_16k_195x270 INSTANCE = new PRC_16k_195x270();

        public PRC_16k_195x270() {
            super(C1661R.string.hp_print_sdk_types_PRC_16k_195x270, new MediaCol.MediaSize(19500, 27000), new Regex(".*195x270mm.*", RegexOption.IGNORE_CASE), null);
        }
    }

    public MediaSize(int i, MediaCol.MediaSize mediaSize, Regex regex) {
        super(i, regex);
        this.f827c = mediaSize;
    }

    public MediaSize(int i, MediaCol.MediaSize mediaSize, Regex regex, DefaultConstructorMarker defaultConstructorMarker) {
        this(i, mediaSize, regex);
    }

    public final MediaCol.MediaSize getF827c() {
        return this.f827c;
    }
}
