package com.tom_roush.pdfbox.pdmodel.font.encoding;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.exifinterface.media.ExifInterface;
import androidx.recyclerview.widget.ItemTouchHelper;
import cc.uling.usdk.constants.BoardConst;
import cc.uling.usdk.constants.ErrorConst;
import com.epson.isv.eprinterdriver.Common.ErrorCode;
import com.epson.isv.eprinterdriver.Ctrl.PageAttribute;
import com.google.android.exoplayer2.extractor.p018ts.PsExtractor;
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

public class WinAnsiEncoding extends Encoding {
    private static final int CHAR_CODE = 0;
    private static final int CHAR_NAME = 1;
    private static final Object[][] WIN_ANSI_ENCODING_TABLE = {new Object[]{65, "A"}, new Object[]{198, "AE"}, new Object[]{193, "Aacute"}, new Object[]{194, "Acircumflex"}, new Object[]{196, "Adieresis"}, new Object[]{192, "Agrave"}, new Object[]{197, "Aring"}, new Object[]{195, "Atilde"}, new Object[]{66, "B"}, new Object[]{67, "C"}, new Object[]{199, "Ccedilla"}, new Object[]{68, "D"}, new Object[]{69, ExifInterface.LONGITUDE_EAST}, new Object[]{201, "Eacute"}, new Object[]{202, "Ecircumflex"}, new Object[]{203, "Edieresis"}, new Object[]{200, "Egrave"}, new Object[]{208, "Eth"}, new Object[]{128, "Euro"}, new Object[]{70, PDNumberFormatDictionary.FRACTIONAL_DISPLAY_FRACTION}, new Object[]{71, "G"}, new Object[]{72, StandardStructureTypes.f2367H}, new Object[]{73, "I"}, new Object[]{205, "Iacute"}, new Object[]{206, "Icircumflex"}, new Object[]{207, "Idieresis"}, new Object[]{204, "Igrave"}, new Object[]{74, "J"}, new Object[]{75, "K"}, new Object[]{76, "L"}, new Object[]{77, "M"}, new Object[]{78, "N"}, new Object[]{Integer.valueOf(BoardConst.CODE_ERR_ADDR), "Ntilde"}, new Object[]{79, PDAnnotationLink.HIGHLIGHT_MODE_OUTLINE}, new Object[]{Integer.valueOf(PowerState.Code.resetMbrGraceful), "OE"}, new Object[]{211, "Oacute"}, new Object[]{212, "Ocircumflex"}, new Object[]{Integer.valueOf(BoardConst.CODE_NOT_OPENED), "Odieresis"}, new Object[]{210, "Ograve"}, new Object[]{216, "Oslash"}, new Object[]{213, "Otilde"}, new Object[]{80, "P"}, new Object[]{81, "Q"}, new Object[]{82, "R"}, new Object[]{83, "S"}, new Object[]{Integer.valueOf(TsExtractor.TS_STREAM_TYPE_DTS), "Scaron"}, new Object[]{84, "T"}, new Object[]{222, "Thorn"}, new Object[]{85, PDBorderStyleDictionary.STYLE_UNDERLINE}, new Object[]{218, "Uacute"}, new Object[]{219, "Ucircumflex"}, new Object[]{220, "Udieresis"}, new Object[]{217, "Ugrave"}, new Object[]{86, ExifInterface.GPS_MEASUREMENT_INTERRUPTED}, new Object[]{87, "W"}, new Object[]{88, "X"}, new Object[]{89, "Y"}, new Object[]{221, "Yacute"}, new Object[]{159, "Ydieresis"}, new Object[]{90, "Z"}, new Object[]{Integer.valueOf(PageAttribute.MediaTypeID.EPS_MTID_PLOOFING_WHITE_MAT), "Zcaron"}, new Object[]{97, "a"}, new Object[]{225, "aacute"}, new Object[]{226, "acircumflex"}, new Object[]{180, "acute"}, new Object[]{228, "adieresis"}, new Object[]{230, "ae"}, new Object[]{224, "agrave"}, new Object[]{38, "ampersand"}, new Object[]{229, "aring"}, new Object[]{94, "asciicircum"}, new Object[]{Integer.valueOf(WebSocketProtocol.PAYLOAD_SHORT), "asciitilde"}, new Object[]{42, "asterisk"}, new Object[]{64, "at"}, new Object[]{227, "atilde"}, new Object[]{98, Media.f726b}, new Object[]{92, "backslash"}, new Object[]{124, "bar"}, new Object[]{123, "braceleft"}, new Object[]{125, "braceright"}, new Object[]{91, "bracketleft"}, new Object[]{93, "bracketright"}, new Object[]{166, "brokenbar"}, new Object[]{149, "bullet"}, new Object[]{99, Media.f727c}, new Object[]{231, "ccedilla"}, new Object[]{Integer.valueOf(SyslogAppender.LOG_LOCAL7), "cedilla"}, new Object[]{162, "cent"}, new Object[]{Integer.valueOf(SyslogAppender.LOG_LOCAL1), "circumflex"}, new Object[]{58, "colon"}, new Object[]{44, "comma"}, new Object[]{169, "copyright"}, new Object[]{164, "currency"}, new Object[]{100, Media.f728d}, new Object[]{Integer.valueOf(TsExtractor.TS_STREAM_TYPE_SPLICE_INFO), "dagger"}, new Object[]{Integer.valueOf(TsExtractor.TS_STREAM_TYPE_E_AC3), "daggerdbl"}, new Object[]{176, "degree"}, new Object[]{Integer.valueOf(SyslogAppender.LOG_LOCAL5), "dieresis"}, new Object[]{247, "divide"}, new Object[]{36, "dollar"}, new Object[]{101, Media.f729e}, new Object[]{233, "eacute"}, new Object[]{234, "ecircumflex"}, new Object[]{235, "edieresis"}, new Object[]{232, "egrave"}, new Object[]{56, "eight"}, new Object[]{133, "ellipsis"}, new Object[]{151, "emdash"}, new Object[]{Integer.valueOf(PowerState.Code.resetSoftGraceful), "endash"}, new Object[]{61, "equal"}, new Object[]{Integer.valueOf(PsExtractor.VIDEO_STREAM_MASK), "eth"}, new Object[]{33, "exclam"}, new Object[]{161, "exclamdown"}, new Object[]{102, Media.f730f}, new Object[]{53, "five"}, new Object[]{131, "florin"}, new Object[]{52, "four"}, new Object[]{103, MaterialAmountUnit.f719g}, new Object[]{223, "germandbls"}, new Object[]{96, "grave"}, new Object[]{62, "greater"}, new Object[]{171, "guillemotleft"}, new Object[]{187, "guillemotright"}, new Object[]{139, "guilsinglleft"}, new Object[]{155, "guilsinglright"}, new Object[]{104, "h"}, new Object[]{45, "hyphen"}, new Object[]{Integer.valueOf(ErrorCode.PrinterError.EPS_PRNERR_TRAYCLOSE), "i"}, new Object[]{237, "iacute"}, new Object[]{238, "icircumflex"}, new Object[]{239, "idieresis"}, new Object[]{236, "igrave"}, new Object[]{106, "j"}, new Object[]{Integer.valueOf(ErrorCode.PrinterError.EPS_PRNERR_JPG_LIMIT), "k"}, new Object[]{108, MaterialAmountUnit.f721l}, new Object[]{60, "less"}, new Object[]{Integer.valueOf(TsExtractor.TS_STREAM_TYPE_AC4), "logicalnot"}, new Object[]{109, MaterialAmountUnit.f722m}, new Object[]{175, "macron"}, new Object[]{181, "mu"}, new Object[]{Integer.valueOf(BoardConst.CODE_BOARD_FAULT), "multiply"}, new Object[]{110, "n"}, new Object[]{57, "nine"}, new Object[]{241, "ntilde"}, new Object[]{35, "numbersign"}, new Object[]{111, "o"}, new Object[]{243, "oacute"}, new Object[]{244, "ocircumflex"}, new Object[]{246, "odieresis"}, new Object[]{156, "oe"}, new Object[]{242, "ograve"}, new Object[]{49, "one"}, new Object[]{Integer.valueOf(PsExtractor.PRIVATE_STREAM_1), "onehalf"}, new Object[]{188, "onequarter"}, new Object[]{185, "onesuperior"}, new Object[]{Integer.valueOf(PowerState.Code.resetInit), "ordfeminine"}, new Object[]{186, "ordmasculine"}, new Object[]{248, "oslash"}, new Object[]{245, "otilde"}, new Object[]{Integer.valueOf(UiPosIndexEnum.HOME_COUNTDOWN), TtmlNode.TAG_P}, new Object[]{182, "paragraph"}, new Object[]{40, "parenleft"}, new Object[]{41, "parenright"}, new Object[]{37, "percent"}, new Object[]{46, TypedValues.CycleType.S_WAVE_PERIOD}, new Object[]{Integer.valueOf(PageAttribute.MediaTypeID.EPS_MTID_LABEL), "periodcentered"}, new Object[]{137, "perthousand"}, new Object[]{43, "plus"}, new Object[]{177, "plusminus"}, new Object[]{Integer.valueOf(UiPosIndexEnum.HOME_REPLACE_BG_TAB), "q"}, new Object[]{63, "question"}, new Object[]{191, "questiondown"}, new Object[]{34, "quotedbl"}, new Object[]{132, "quotedblbase"}, new Object[]{147, "quotedblleft"}, new Object[]{148, "quotedblright"}, new Object[]{145, "quoteleft"}, new Object[]{146, "quoteright"}, new Object[]{130, "quotesinglbase"}, new Object[]{39, "quotesingle"}, new Object[]{114, PDPageLabelRange.STYLE_ROMAN_LOWER}, new Object[]{Integer.valueOf(PageAttribute.MediaTypeID.EPS_MTID_BARYTA), "registered"}, new Object[]{115, "s"}, new Object[]{154, "scaron"}, new Object[]{167, "section"}, new Object[]{59, "semicolon"}, new Object[]{55, "seven"}, new Object[]{54, "six"}, new Object[]{47, "slash"}, new Object[]{32, "space"}, new Object[]{163, "sterling"}, new Object[]{116, "t"}, new Object[]{254, "thorn"}, new Object[]{51, "three"}, new Object[]{Integer.valueOf(PowerState.Code.noChange), "threequarters"}, new Object[]{179, "threesuperior"}, new Object[]{Integer.valueOf(SyslogAppender.LOG_LOCAL3), "tilde"}, new Object[]{153, "trademark"}, new Object[]{50, "two"}, new Object[]{178, "twosuperior"}, new Object[]{117, "u"}, new Object[]{Integer.valueOf(ItemTouchHelper.Callback.DEFAULT_SWIPE_ANIMATION_DURATION), "uacute"}, new Object[]{251, "ucircumflex"}, new Object[]{Integer.valueOf(ErrorConst.MDB_ERR_CANT_OPEN), "udieresis"}, new Object[]{249, "ugrave"}, new Object[]{95, "underscore"}, new Object[]{118, "v"}, new Object[]{119, "w"}, new Object[]{120, "x"}, new Object[]{Integer.valueOf(DNPPhotoPrint.OVERCOAT_FINISH_PMATTE12), "y"}, new Object[]{253, "yacute"}, new Object[]{255, "ydieresis"}, new Object[]{165, "yen"}, new Object[]{Integer.valueOf(DNPPhotoPrint.OVERCOAT_FINISH_PMATTE13), "z"}, new Object[]{158, "zcaron"}, new Object[]{48, "zero"}, new Object[]{160, "space"}, new Object[]{173, "hyphen"}};
    public static final WinAnsiEncoding INSTANCE = new WinAnsiEncoding();

    @Override
    public String getEncodingName() {
        return "WinAnsiEncoding";
    }

    public WinAnsiEncoding() {
        for (Object[] objArr : WIN_ANSI_ENCODING_TABLE) {
            add(((Integer) objArr[0]).intValue(), objArr[1].toString());
        }
        for (int i = 33; i <= 255; i++) {
            if (!this.codeToName.containsKey(Integer.valueOf(i))) {
                add(i, "bullet");
            }
        }
    }

    @Override
    public COSBase getCOSObject() {
        return COSName.WIN_ANSI_ENCODING;
    }
}
