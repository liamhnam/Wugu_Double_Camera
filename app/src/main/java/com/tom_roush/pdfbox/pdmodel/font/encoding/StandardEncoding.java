package com.tom_roush.pdfbox.pdmodel.font.encoding;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.exifinterface.media.ExifInterface;
import androidx.recyclerview.widget.ItemTouchHelper;
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

public class StandardEncoding extends Encoding {
    private static final int CHAR_CODE = 0;
    private static final int CHAR_NAME = 1;
    private static final Object[][] STANDARD_ENCODING_TABLE = {new Object[]{65, "A"}, new Object[]{225, "AE"}, new Object[]{66, "B"}, new Object[]{67, "C"}, new Object[]{68, "D"}, new Object[]{69, ExifInterface.LONGITUDE_EAST}, new Object[]{70, PDNumberFormatDictionary.FRACTIONAL_DISPLAY_FRACTION}, new Object[]{71, "G"}, new Object[]{72, StandardStructureTypes.f2367H}, new Object[]{73, "I"}, new Object[]{74, "J"}, new Object[]{75, "K"}, new Object[]{76, "L"}, new Object[]{232, "Lslash"}, new Object[]{77, "M"}, new Object[]{78, "N"}, new Object[]{79, PDAnnotationLink.HIGHLIGHT_MODE_OUTLINE}, new Object[]{234, "OE"}, new Object[]{233, "Oslash"}, new Object[]{80, "P"}, new Object[]{81, "Q"}, new Object[]{82, "R"}, new Object[]{83, "S"}, new Object[]{84, "T"}, new Object[]{85, PDBorderStyleDictionary.STYLE_UNDERLINE}, new Object[]{86, ExifInterface.GPS_MEASUREMENT_INTERRUPTED}, new Object[]{87, "W"}, new Object[]{88, "X"}, new Object[]{89, "Y"}, new Object[]{90, "Z"}, new Object[]{97, "a"}, new Object[]{194, "acute"}, new Object[]{241, "ae"}, new Object[]{38, "ampersand"}, new Object[]{94, "asciicircum"}, new Object[]{Integer.valueOf(WebSocketProtocol.PAYLOAD_SHORT), "asciitilde"}, new Object[]{42, "asterisk"}, new Object[]{64, "at"}, new Object[]{98, Media.f726b}, new Object[]{92, "backslash"}, new Object[]{124, "bar"}, new Object[]{123, "braceleft"}, new Object[]{125, "braceright"}, new Object[]{91, "bracketleft"}, new Object[]{93, "bracketright"}, new Object[]{198, "breve"}, new Object[]{Integer.valueOf(PageAttribute.MediaTypeID.EPS_MTID_LABEL), "bullet"}, new Object[]{99, Media.f727c}, new Object[]{207, "caron"}, new Object[]{203, "cedilla"}, new Object[]{162, "cent"}, new Object[]{195, "circumflex"}, new Object[]{58, "colon"}, new Object[]{44, "comma"}, new Object[]{Integer.valueOf(SyslogAppender.LOG_LOCAL5), "currency"}, new Object[]{100, Media.f728d}, new Object[]{178, "dagger"}, new Object[]{179, "daggerdbl"}, new Object[]{200, "dieresis"}, new Object[]{36, "dollar"}, new Object[]{199, "dotaccent"}, new Object[]{245, "dotlessi"}, new Object[]{101, Media.f729e}, new Object[]{56, "eight"}, new Object[]{188, "ellipsis"}, new Object[]{208, "emdash"}, new Object[]{177, "endash"}, new Object[]{61, "equal"}, new Object[]{33, "exclam"}, new Object[]{161, "exclamdown"}, new Object[]{102, Media.f730f}, new Object[]{Integer.valueOf(PageAttribute.MediaTypeID.EPS_MTID_BARYTA), "fi"}, new Object[]{53, "five"}, new Object[]{175, "fl"}, new Object[]{166, "florin"}, new Object[]{52, "four"}, new Object[]{164, "fraction"}, new Object[]{103, MaterialAmountUnit.f719g}, new Object[]{251, "germandbls"}, new Object[]{193, "grave"}, new Object[]{62, "greater"}, new Object[]{171, "guillemotleft"}, new Object[]{187, "guillemotright"}, new Object[]{Integer.valueOf(TsExtractor.TS_STREAM_TYPE_AC4), "guilsinglleft"}, new Object[]{173, "guilsinglright"}, new Object[]{104, "h"}, new Object[]{205, "hungarumlaut"}, new Object[]{45, "hyphen"}, new Object[]{Integer.valueOf(ErrorCode.PrinterError.EPS_PRNERR_TRAYCLOSE), "i"}, new Object[]{106, "j"}, new Object[]{Integer.valueOf(ErrorCode.PrinterError.EPS_PRNERR_JPG_LIMIT), "k"}, new Object[]{108, MaterialAmountUnit.f721l}, new Object[]{60, "less"}, new Object[]{248, "lslash"}, new Object[]{109, MaterialAmountUnit.f722m}, new Object[]{197, "macron"}, new Object[]{110, "n"}, new Object[]{57, "nine"}, new Object[]{35, "numbersign"}, new Object[]{111, "o"}, new Object[]{Integer.valueOf(ItemTouchHelper.Callback.DEFAULT_SWIPE_ANIMATION_DURATION), "oe"}, new Object[]{206, "ogonek"}, new Object[]{49, "one"}, new Object[]{227, "ordfeminine"}, new Object[]{235, "ordmasculine"}, new Object[]{249, "oslash"}, new Object[]{Integer.valueOf(UiPosIndexEnum.HOME_COUNTDOWN), TtmlNode.TAG_P}, new Object[]{182, "paragraph"}, new Object[]{40, "parenleft"}, new Object[]{41, "parenright"}, new Object[]{37, "percent"}, new Object[]{46, TypedValues.CycleType.S_WAVE_PERIOD}, new Object[]{180, "periodcentered"}, new Object[]{Integer.valueOf(PsExtractor.PRIVATE_STREAM_1), "perthousand"}, new Object[]{43, "plus"}, new Object[]{Integer.valueOf(UiPosIndexEnum.HOME_REPLACE_BG_TAB), "q"}, new Object[]{63, "question"}, new Object[]{191, "questiondown"}, new Object[]{34, "quotedbl"}, new Object[]{185, "quotedblbase"}, new Object[]{Integer.valueOf(PowerState.Code.resetInit), "quotedblleft"}, new Object[]{186, "quotedblright"}, new Object[]{96, "quoteleft"}, new Object[]{39, "quoteright"}, new Object[]{Integer.valueOf(SyslogAppender.LOG_LOCAL7), "quotesinglbase"}, new Object[]{169, "quotesingle"}, new Object[]{114, PDPageLabelRange.STYLE_ROMAN_LOWER}, new Object[]{202, "ring"}, new Object[]{115, "s"}, new Object[]{167, "section"}, new Object[]{59, "semicolon"}, new Object[]{55, "seven"}, new Object[]{54, "six"}, new Object[]{47, "slash"}, new Object[]{32, "space"}, new Object[]{163, "sterling"}, new Object[]{116, "t"}, new Object[]{51, "three"}, new Object[]{196, "tilde"}, new Object[]{50, "two"}, new Object[]{117, "u"}, new Object[]{95, "underscore"}, new Object[]{118, "v"}, new Object[]{119, "w"}, new Object[]{120, "x"}, new Object[]{Integer.valueOf(DNPPhotoPrint.OVERCOAT_FINISH_PMATTE12), "y"}, new Object[]{165, "yen"}, new Object[]{Integer.valueOf(DNPPhotoPrint.OVERCOAT_FINISH_PMATTE13), "z"}, new Object[]{48, "zero"}};
    public static final StandardEncoding INSTANCE = new StandardEncoding();

    @Override
    public String getEncodingName() {
        return "StandardEncoding";
    }

    public StandardEncoding() {
        for (Object[] objArr : STANDARD_ENCODING_TABLE) {
            add(((Integer) objArr[0]).intValue(), objArr[1].toString());
        }
    }

    @Override
    public COSBase getCOSObject() {
        return COSName.STANDARD_ENCODING;
    }
}
