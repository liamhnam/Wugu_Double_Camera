package com.tom_roush.fontbox.cff;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.exifinterface.media.ExifInterface;
import cc.uling.usdk.constants.BoardConst;
import com.epson.isv.eprinterdriver.Common.ErrorCode;
import com.epson.isv.eprinterdriver.Ctrl.PageAttribute;
import com.google.android.exoplayer2.extractor.p018ts.PsExtractor;
import com.google.android.exoplayer2.extractor.p018ts.TsExtractor;
import com.google.android.exoplayer2.text.ttml.TtmlNode;
import com.p020hp.jipp.model.MaterialAmountUnit;
import com.p020hp.jipp.model.Media;
import com.p020hp.jipp.model.PowerState;
import com.tom_roush.pdfbox.pdmodel.common.PDPageLabelRange;
import com.tom_roush.pdfbox.pdmodel.documentinterchange.taggedpdf.StandardStructureTypes;
import com.tom_roush.pdfbox.pdmodel.interactive.annotation.PDAnnotationLink;
import com.tom_roush.pdfbox.pdmodel.interactive.annotation.PDBorderStyleDictionary;
import com.tom_roush.pdfbox.pdmodel.interactive.measurement.PDNumberFormatDictionary;
import com.wugu.doublecamera.enums.UiPosIndexEnum;
import jp.p036co.dnp.photoprintlib.DNPPhotoPrint;
import okhttp3.internal.p040ws.WebSocketProtocol;
import org.apache.log4j.net.SyslogAppender;

public final class CFFISOAdobeCharset extends CFFCharset {
    private static final Object[][] CFF_ISO_ADOBE_CHARSET_TABLE;
    private static final int CHAR_CODE = 0;
    private static final int CHAR_NAME = 1;
    private static final CFFISOAdobeCharset INSTANCE;

    static {
        Object[][] objArr = {new Object[]{0, ".notdef"}, new Object[]{1, "space"}, new Object[]{2, "exclam"}, new Object[]{3, "quotedbl"}, new Object[]{4, "numbersign"}, new Object[]{5, "dollar"}, new Object[]{6, "percent"}, new Object[]{7, "ampersand"}, new Object[]{8, "quoteright"}, new Object[]{9, "parenleft"}, new Object[]{10, "parenright"}, new Object[]{11, "asterisk"}, new Object[]{12, "plus"}, new Object[]{13, "comma"}, new Object[]{14, "hyphen"}, new Object[]{15, TypedValues.CycleType.S_WAVE_PERIOD}, new Object[]{16, "slash"}, new Object[]{17, "zero"}, new Object[]{18, "one"}, new Object[]{19, "two"}, new Object[]{20, "three"}, new Object[]{21, "four"}, new Object[]{22, "five"}, new Object[]{23, "six"}, new Object[]{24, "seven"}, new Object[]{25, "eight"}, new Object[]{26, "nine"}, new Object[]{27, "colon"}, new Object[]{28, "semicolon"}, new Object[]{29, "less"}, new Object[]{30, "equal"}, new Object[]{31, "greater"}, new Object[]{32, "question"}, new Object[]{33, "at"}, new Object[]{34, "A"}, new Object[]{35, "B"}, new Object[]{36, "C"}, new Object[]{37, "D"}, new Object[]{38, ExifInterface.LONGITUDE_EAST}, new Object[]{39, PDNumberFormatDictionary.FRACTIONAL_DISPLAY_FRACTION}, new Object[]{40, "G"}, new Object[]{41, StandardStructureTypes.f2367H}, new Object[]{42, "I"}, new Object[]{43, "J"}, new Object[]{44, "K"}, new Object[]{45, "L"}, new Object[]{46, "M"}, new Object[]{47, "N"}, new Object[]{48, PDAnnotationLink.HIGHLIGHT_MODE_OUTLINE}, new Object[]{49, "P"}, new Object[]{50, "Q"}, new Object[]{51, "R"}, new Object[]{52, "S"}, new Object[]{53, "T"}, new Object[]{54, PDBorderStyleDictionary.STYLE_UNDERLINE}, new Object[]{55, ExifInterface.GPS_MEASUREMENT_INTERRUPTED}, new Object[]{56, "W"}, new Object[]{57, "X"}, new Object[]{58, "Y"}, new Object[]{59, "Z"}, new Object[]{60, "bracketleft"}, new Object[]{61, "backslash"}, new Object[]{62, "bracketright"}, new Object[]{63, "asciicircum"}, new Object[]{64, "underscore"}, new Object[]{65, "quoteleft"}, new Object[]{66, "a"}, new Object[]{67, Media.f726b}, new Object[]{68, Media.f727c}, new Object[]{69, Media.f728d}, new Object[]{70, Media.f729e}, new Object[]{71, Media.f730f}, new Object[]{72, MaterialAmountUnit.f719g}, new Object[]{73, "h"}, new Object[]{74, "i"}, new Object[]{75, "j"}, new Object[]{76, "k"}, new Object[]{77, MaterialAmountUnit.f721l}, new Object[]{78, MaterialAmountUnit.f722m}, new Object[]{79, "n"}, new Object[]{80, "o"}, new Object[]{81, TtmlNode.TAG_P}, new Object[]{82, "q"}, new Object[]{83, PDPageLabelRange.STYLE_ROMAN_LOWER}, new Object[]{84, "s"}, new Object[]{85, "t"}, new Object[]{86, "u"}, new Object[]{87, "v"}, new Object[]{88, "w"}, new Object[]{89, "x"}, new Object[]{90, "y"}, new Object[]{91, "z"}, new Object[]{92, "braceleft"}, new Object[]{93, "bar"}, new Object[]{94, "braceright"}, new Object[]{95, "asciitilde"}, new Object[]{96, "exclamdown"}, new Object[]{97, "cent"}, new Object[]{98, "sterling"}, new Object[]{99, "fraction"}, new Object[]{100, "yen"}, new Object[]{101, "florin"}, new Object[]{102, "section"}, new Object[]{103, "currency"}, new Object[]{104, "quotesingle"}, new Object[]{Integer.valueOf(ErrorCode.PrinterError.EPS_PRNERR_TRAYCLOSE), "quotedblleft"}, new Object[]{106, "guillemotleft"}, new Object[]{Integer.valueOf(ErrorCode.PrinterError.EPS_PRNERR_JPG_LIMIT), "guilsinglleft"}, new Object[]{108, "guilsinglright"}, new Object[]{109, "fi"}, new Object[]{110, "fl"}, new Object[]{111, "endash"}, new Object[]{Integer.valueOf(UiPosIndexEnum.HOME_COUNTDOWN), "dagger"}, new Object[]{Integer.valueOf(UiPosIndexEnum.HOME_REPLACE_BG_TAB), "daggerdbl"}, new Object[]{114, "periodcentered"}, new Object[]{115, "paragraph"}, new Object[]{116, "bullet"}, new Object[]{117, "quotesinglbase"}, new Object[]{118, "quotedblbase"}, new Object[]{119, "quotedblright"}, new Object[]{120, "guillemotright"}, new Object[]{Integer.valueOf(DNPPhotoPrint.OVERCOAT_FINISH_PMATTE12), "ellipsis"}, new Object[]{Integer.valueOf(DNPPhotoPrint.OVERCOAT_FINISH_PMATTE13), "perthousand"}, new Object[]{123, "questiondown"}, new Object[]{124, "grave"}, new Object[]{125, "acute"}, new Object[]{Integer.valueOf(WebSocketProtocol.PAYLOAD_SHORT), "circumflex"}, new Object[]{127, "tilde"}, new Object[]{128, "macron"}, new Object[]{129, "breve"}, new Object[]{130, "dotaccent"}, new Object[]{131, "dieresis"}, new Object[]{132, "ring"}, new Object[]{133, "cedilla"}, new Object[]{Integer.valueOf(TsExtractor.TS_STREAM_TYPE_SPLICE_INFO), "hungarumlaut"}, new Object[]{Integer.valueOf(TsExtractor.TS_STREAM_TYPE_E_AC3), "ogonek"}, new Object[]{Integer.valueOf(SyslogAppender.LOG_LOCAL1), "caron"}, new Object[]{137, "emdash"}, new Object[]{Integer.valueOf(TsExtractor.TS_STREAM_TYPE_DTS), "AE"}, new Object[]{139, "ordfeminine"}, new Object[]{Integer.valueOf(PowerState.Code.resetMbrGraceful), "Lslash"}, new Object[]{141, "Oslash"}, new Object[]{Integer.valueOf(PageAttribute.MediaTypeID.EPS_MTID_PLOOFING_WHITE_MAT), "OE"}, new Object[]{143, "ordmasculine"}, new Object[]{Integer.valueOf(SyslogAppender.LOG_LOCAL2), "ae"}, new Object[]{145, "dotlessi"}, new Object[]{146, "lslash"}, new Object[]{147, "oslash"}, new Object[]{148, "oe"}, new Object[]{149, "germandbls"}, new Object[]{Integer.valueOf(PowerState.Code.resetSoftGraceful), "onesuperior"}, new Object[]{151, "logicalnot"}, new Object[]{Integer.valueOf(SyslogAppender.LOG_LOCAL3), "mu"}, new Object[]{153, "trademark"}, new Object[]{154, "Eth"}, new Object[]{155, "onehalf"}, new Object[]{156, "plusminus"}, new Object[]{157, "Thorn"}, new Object[]{158, "onequarter"}, new Object[]{159, "divide"}, new Object[]{160, "brokenbar"}, new Object[]{161, "degree"}, new Object[]{162, "thorn"}, new Object[]{163, "threequarters"}, new Object[]{164, "twosuperior"}, new Object[]{165, "registered"}, new Object[]{166, "minus"}, new Object[]{167, "eth"}, new Object[]{Integer.valueOf(SyslogAppender.LOG_LOCAL5), "multiply"}, new Object[]{169, "threesuperior"}, new Object[]{Integer.valueOf(PowerState.Code.resetInit), "copyright"}, new Object[]{171, "Aacute"}, new Object[]{Integer.valueOf(TsExtractor.TS_STREAM_TYPE_AC4), "Acircumflex"}, new Object[]{173, "Adieresis"}, new Object[]{Integer.valueOf(PageAttribute.MediaTypeID.EPS_MTID_BARYTA), "Agrave"}, new Object[]{175, "Aring"}, new Object[]{176, "Atilde"}, new Object[]{177, "Ccedilla"}, new Object[]{178, "Eacute"}, new Object[]{179, "Ecircumflex"}, new Object[]{180, "Edieresis"}, new Object[]{181, "Egrave"}, new Object[]{182, "Iacute"}, new Object[]{Integer.valueOf(PageAttribute.MediaTypeID.EPS_MTID_LABEL), "Icircumflex"}, new Object[]{Integer.valueOf(SyslogAppender.LOG_LOCAL7), "Idieresis"}, new Object[]{185, "Igrave"}, new Object[]{186, "Ntilde"}, new Object[]{187, "Oacute"}, new Object[]{188, "Ocircumflex"}, new Object[]{Integer.valueOf(PsExtractor.PRIVATE_STREAM_1), "Odieresis"}, new Object[]{Integer.valueOf(PowerState.Code.noChange), "Ograve"}, new Object[]{191, "Otilde"}, new Object[]{192, "Scaron"}, new Object[]{193, "Uacute"}, new Object[]{194, "Ucircumflex"}, new Object[]{195, "Udieresis"}, new Object[]{196, "Ugrave"}, new Object[]{197, "Yacute"}, new Object[]{198, "Ydieresis"}, new Object[]{199, "Zcaron"}, new Object[]{200, "aacute"}, new Object[]{201, "acircumflex"}, new Object[]{202, "adieresis"}, new Object[]{203, "agrave"}, new Object[]{204, "aring"}, new Object[]{205, "atilde"}, new Object[]{206, "ccedilla"}, new Object[]{207, "eacute"}, new Object[]{208, "ecircumflex"}, new Object[]{Integer.valueOf(BoardConst.CODE_ERR_ADDR), "edieresis"}, new Object[]{210, "egrave"}, new Object[]{211, "iacute"}, new Object[]{212, "icircumflex"}, new Object[]{213, "idieresis"}, new Object[]{Integer.valueOf(BoardConst.CODE_NOT_OPENED), "igrave"}, new Object[]{Integer.valueOf(BoardConst.CODE_BOARD_FAULT), "ntilde"}, new Object[]{216, "oacute"}, new Object[]{217, "ocircumflex"}, new Object[]{218, "odieresis"}, new Object[]{219, "ograve"}, new Object[]{220, "otilde"}, new Object[]{221, "scaron"}, new Object[]{222, "uacute"}, new Object[]{223, "ucircumflex"}, new Object[]{224, "udieresis"}, new Object[]{225, "ugrave"}, new Object[]{226, "yacute"}, new Object[]{227, "ydieresis"}, new Object[]{228, "zcaron"}};
        CFF_ISO_ADOBE_CHARSET_TABLE = objArr;
        INSTANCE = new CFFISOAdobeCharset();
        int length = objArr.length;
        int i = 0;
        int i2 = 0;
        while (i < length) {
            Object[] objArr2 = objArr[i];
            INSTANCE.addSID(i2, ((Integer) objArr2[0]).intValue(), objArr2[1].toString());
            i++;
            i2++;
        }
    }

    private CFFISOAdobeCharset() {
        super(false);
    }

    public static CFFISOAdobeCharset getInstance() {
        return INSTANCE;
    }
}
