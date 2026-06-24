package com.tom_roush.pdfbox.pdmodel.font.encoding;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.exifinterface.media.ExifInterface;
import androidx.recyclerview.widget.ItemTouchHelper;
import cc.uling.usdk.constants.BoardConst;
import cc.uling.usdk.constants.ErrorConst;
import com.epson.isv.eprinterdriver.Common.ErrorCode;
import com.epson.isv.eprinterdriver.Ctrl.PageAttribute;
import com.google.android.exoplayer2.extractor.p018ts.TsExtractor;
import com.google.android.exoplayer2.text.ttml.TtmlNode;
import com.p020hp.jipp.model.MaterialAmountUnit;
import com.p020hp.jipp.model.Media;
import com.p020hp.jipp.model.PowerState;
import com.tom_roush.pdfbox.cos.COSBase;
import com.tom_roush.pdfbox.cos.COSName;
import com.tom_roush.pdfbox.pdmodel.common.PDPageLabelRange;
import com.tom_roush.pdfbox.pdmodel.documentinterchange.taggedpdf.StandardStructureTypes;
import com.tom_roush.pdfbox.pdmodel.interactive.annotation.PDAnnotationLink;
import com.tom_roush.pdfbox.pdmodel.interactive.annotation.PDBorderStyleDictionary;
import com.tom_roush.pdfbox.pdmodel.interactive.measurement.PDNumberFormatDictionary;
import com.wugu.doublecamera.enums.UiPosIndexEnum;
import jp.p036co.dnp.photoprintlib.DNPPhotoPrint;
import okhttp3.internal.p040ws.WebSocketProtocol;
import org.apache.log4j.net.SyslogAppender;

public class MacRomanEncoding extends Encoding {
    private static final int CHAR_CODE = 0;
    private static final int CHAR_NAME = 1;
    private static final Object[][] MAC_ROMAN_ENCODING_TABLE = {new Object[]{65, "A"}, new Object[]{Integer.valueOf(PageAttribute.MediaTypeID.EPS_MTID_BARYTA), "AE"}, new Object[]{231, "Aacute"}, new Object[]{229, "Acircumflex"}, new Object[]{128, "Adieresis"}, new Object[]{203, "Agrave"}, new Object[]{129, "Aring"}, new Object[]{204, "Atilde"}, new Object[]{66, "B"}, new Object[]{67, "C"}, new Object[]{130, "Ccedilla"}, new Object[]{68, "D"}, new Object[]{69, ExifInterface.LONGITUDE_EAST}, new Object[]{131, "Eacute"}, new Object[]{230, "Ecircumflex"}, new Object[]{232, "Edieresis"}, new Object[]{233, "Egrave"}, new Object[]{70, PDNumberFormatDictionary.FRACTIONAL_DISPLAY_FRACTION}, new Object[]{71, "G"}, new Object[]{72, StandardStructureTypes.f2367H}, new Object[]{73, "I"}, new Object[]{234, "Iacute"}, new Object[]{235, "Icircumflex"}, new Object[]{236, "Idieresis"}, new Object[]{237, "Igrave"}, new Object[]{74, "J"}, new Object[]{75, "K"}, new Object[]{76, "L"}, new Object[]{77, "M"}, new Object[]{78, "N"}, new Object[]{132, "Ntilde"}, new Object[]{79, PDAnnotationLink.HIGHLIGHT_MODE_OUTLINE}, new Object[]{206, "OE"}, new Object[]{238, "Oacute"}, new Object[]{239, "Ocircumflex"}, new Object[]{133, "Odieresis"}, new Object[]{241, "Ograve"}, new Object[]{175, "Oslash"}, new Object[]{205, "Otilde"}, new Object[]{80, "P"}, new Object[]{81, "Q"}, new Object[]{82, "R"}, new Object[]{83, "S"}, new Object[]{84, "T"}, new Object[]{85, PDBorderStyleDictionary.STYLE_UNDERLINE}, new Object[]{242, "Uacute"}, new Object[]{243, "Ucircumflex"}, new Object[]{Integer.valueOf(TsExtractor.TS_STREAM_TYPE_SPLICE_INFO), "Udieresis"}, new Object[]{244, "Ugrave"}, new Object[]{86, ExifInterface.GPS_MEASUREMENT_INTERRUPTED}, new Object[]{87, "W"}, new Object[]{88, "X"}, new Object[]{89, "Y"}, new Object[]{217, "Ydieresis"}, new Object[]{90, "Z"}, new Object[]{97, "a"}, new Object[]{Integer.valueOf(TsExtractor.TS_STREAM_TYPE_E_AC3), "aacute"}, new Object[]{137, "acircumflex"}, new Object[]{171, "acute"}, new Object[]{Integer.valueOf(TsExtractor.TS_STREAM_TYPE_DTS), "adieresis"}, new Object[]{Integer.valueOf(PowerState.Code.noChange), "ae"}, new Object[]{Integer.valueOf(SyslogAppender.LOG_LOCAL1), "agrave"}, new Object[]{38, "ampersand"}, new Object[]{Integer.valueOf(PowerState.Code.resetMbrGraceful), "aring"}, new Object[]{94, "asciicircum"}, new Object[]{Integer.valueOf(WebSocketProtocol.PAYLOAD_SHORT), "asciitilde"}, new Object[]{42, "asterisk"}, new Object[]{64, "at"}, new Object[]{139, "atilde"}, new Object[]{98, Media.f726b}, new Object[]{92, "backslash"}, new Object[]{124, "bar"}, new Object[]{123, "braceleft"}, new Object[]{125, "braceright"}, new Object[]{91, "bracketleft"}, new Object[]{93, "bracketright"}, new Object[]{249, "breve"}, new Object[]{165, "bullet"}, new Object[]{99, Media.f727c}, new Object[]{255, "caron"}, new Object[]{141, "ccedilla"}, new Object[]{Integer.valueOf(ErrorConst.MDB_ERR_CANT_OPEN), "cedilla"}, new Object[]{162, "cent"}, new Object[]{246, "circumflex"}, new Object[]{58, "colon"}, new Object[]{44, "comma"}, new Object[]{169, "copyright"}, new Object[]{219, "currency"}, new Object[]{100, Media.f728d}, new Object[]{160, "dagger"}, new Object[]{224, "daggerdbl"}, new Object[]{161, "degree"}, new Object[]{Integer.valueOf(TsExtractor.TS_STREAM_TYPE_AC4), "dieresis"}, new Object[]{Integer.valueOf(BoardConst.CODE_NOT_OPENED), "divide"}, new Object[]{36, "dollar"}, new Object[]{Integer.valueOf(ItemTouchHelper.Callback.DEFAULT_SWIPE_ANIMATION_DURATION), "dotaccent"}, new Object[]{245, "dotlessi"}, new Object[]{101, Media.f729e}, new Object[]{Integer.valueOf(PageAttribute.MediaTypeID.EPS_MTID_PLOOFING_WHITE_MAT), "eacute"}, new Object[]{Integer.valueOf(SyslogAppender.LOG_LOCAL2), "ecircumflex"}, new Object[]{145, "edieresis"}, new Object[]{143, "egrave"}, new Object[]{56, "eight"}, new Object[]{201, "ellipsis"}, new Object[]{Integer.valueOf(BoardConst.CODE_ERR_ADDR), "emdash"}, new Object[]{208, "endash"}, new Object[]{61, "equal"}, new Object[]{33, "exclam"}, new Object[]{193, "exclamdown"}, new Object[]{102, Media.f730f}, new Object[]{222, "fi"}, new Object[]{53, "five"}, new Object[]{223, "fl"}, new Object[]{196, "florin"}, new Object[]{52, "four"}, new Object[]{218, "fraction"}, new Object[]{103, MaterialAmountUnit.f719g}, new Object[]{167, "germandbls"}, new Object[]{96, "grave"}, new Object[]{62, "greater"}, new Object[]{199, "guillemotleft"}, new Object[]{200, "guillemotright"}, new Object[]{220, "guilsinglleft"}, new Object[]{221, "guilsinglright"}, new Object[]{104, "h"}, new Object[]{253, "hungarumlaut"}, new Object[]{45, "hyphen"}, new Object[]{Integer.valueOf(ErrorCode.PrinterError.EPS_PRNERR_TRAYCLOSE), "i"}, new Object[]{146, "iacute"}, new Object[]{148, "icircumflex"}, new Object[]{149, "idieresis"}, new Object[]{147, "igrave"}, new Object[]{106, "j"}, new Object[]{Integer.valueOf(ErrorCode.PrinterError.EPS_PRNERR_JPG_LIMIT), "k"}, new Object[]{108, MaterialAmountUnit.f721l}, new Object[]{60, "less"}, new Object[]{194, "logicalnot"}, new Object[]{109, MaterialAmountUnit.f722m}, new Object[]{248, "macron"}, new Object[]{181, "mu"}, new Object[]{110, "n"}, new Object[]{57, "nine"}, new Object[]{Integer.valueOf(PowerState.Code.resetSoftGraceful), "ntilde"}, new Object[]{35, "numbersign"}, new Object[]{111, "o"}, new Object[]{151, "oacute"}, new Object[]{153, "ocircumflex"}, new Object[]{154, "odieresis"}, new Object[]{207, "oe"}, new Object[]{254, "ogonek"}, new Object[]{Integer.valueOf(SyslogAppender.LOG_LOCAL3), "ograve"}, new Object[]{49, "one"}, new Object[]{187, "ordfeminine"}, new Object[]{188, "ordmasculine"}, new Object[]{191, "oslash"}, new Object[]{155, "otilde"}, new Object[]{Integer.valueOf(UiPosIndexEnum.HOME_COUNTDOWN), TtmlNode.TAG_P}, new Object[]{166, "paragraph"}, new Object[]{40, "parenleft"}, new Object[]{41, "parenright"}, new Object[]{37, "percent"}, new Object[]{46, TypedValues.CycleType.S_WAVE_PERIOD}, new Object[]{225, "periodcentered"}, new Object[]{228, "perthousand"}, new Object[]{43, "plus"}, new Object[]{177, "plusminus"}, new Object[]{Integer.valueOf(UiPosIndexEnum.HOME_REPLACE_BG_TAB), "q"}, new Object[]{63, "question"}, new Object[]{192, "questiondown"}, new Object[]{34, "quotedbl"}, new Object[]{227, "quotedblbase"}, new Object[]{210, "quotedblleft"}, new Object[]{211, "quotedblright"}, new Object[]{212, "quoteleft"}, new Object[]{213, "quoteright"}, new Object[]{226, "quotesinglbase"}, new Object[]{39, "quotesingle"}, new Object[]{114, PDPageLabelRange.STYLE_ROMAN_LOWER}, new Object[]{Integer.valueOf(SyslogAppender.LOG_LOCAL5), "registered"}, new Object[]{251, "ring"}, new Object[]{115, "s"}, new Object[]{164, "section"}, new Object[]{59, "semicolon"}, new Object[]{55, "seven"}, new Object[]{54, "six"}, new Object[]{47, "slash"}, new Object[]{32, "space"}, new Object[]{163, "sterling"}, new Object[]{116, "t"}, new Object[]{51, "three"}, new Object[]{247, "tilde"}, new Object[]{Integer.valueOf(PowerState.Code.resetInit), "trademark"}, new Object[]{50, "two"}, new Object[]{117, "u"}, new Object[]{156, "uacute"}, new Object[]{158, "ucircumflex"}, new Object[]{159, "udieresis"}, new Object[]{157, "ugrave"}, new Object[]{95, "underscore"}, new Object[]{118, "v"}, new Object[]{119, "w"}, new Object[]{120, "x"}, new Object[]{Integer.valueOf(DNPPhotoPrint.OVERCOAT_FINISH_PMATTE12), "y"}, new Object[]{216, "ydieresis"}, new Object[]{180, "yen"}, new Object[]{Integer.valueOf(DNPPhotoPrint.OVERCOAT_FINISH_PMATTE13), "z"}, new Object[]{48, "zero"}, new Object[]{202, "space"}};
    public static final MacRomanEncoding INSTANCE = new MacRomanEncoding();

    @Override
    public String getEncodingName() {
        return "MacRomanEncoding";
    }

    public MacRomanEncoding() {
        for (Object[] objArr : MAC_ROMAN_ENCODING_TABLE) {
            add(((Integer) objArr[0]).intValue(), objArr[1].toString());
        }
    }

    @Override
    public COSBase getCOSObject() {
        return COSName.MAC_ROMAN_ENCODING;
    }
}
