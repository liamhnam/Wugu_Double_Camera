package com.tom_roush.fontbox.cff;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.recyclerview.widget.ItemTouchHelper;
import com.google.android.exoplayer2.extractor.p018ts.PsExtractor;
import com.p020hp.jipp.model.PowerState;
import com.tom_roush.fontbox.ttf.WGL4Names;
import com.wugu.doublecamera.enums.UiPosIndexEnum;

public final class CFFExpertSubsetCharset extends CFFCharset {
    private static final Object[][] CFF_EXPERT_SUBSET_CHARSET_TABLE;
    private static final int CHAR_CODE = 0;
    private static final int CHAR_NAME = 1;
    private static final CFFExpertSubsetCharset INSTANCE;

    static {
        Object[][] objArr = {new Object[]{0, ".notdef"}, new Object[]{1, "space"}, new Object[]{231, "dollaroldstyle"}, new Object[]{232, "dollarsuperior"}, new Object[]{235, "parenleftsuperior"}, new Object[]{236, "parenrightsuperior"}, new Object[]{237, "twodotenleader"}, new Object[]{238, "onedotenleader"}, new Object[]{13, "comma"}, new Object[]{14, "hyphen"}, new Object[]{15, TypedValues.CycleType.S_WAVE_PERIOD}, new Object[]{99, "fraction"}, new Object[]{239, "zerooldstyle"}, new Object[]{Integer.valueOf(PsExtractor.VIDEO_STREAM_MASK), "oneoldstyle"}, new Object[]{241, "twooldstyle"}, new Object[]{242, "threeoldstyle"}, new Object[]{243, "fouroldstyle"}, new Object[]{244, "fiveoldstyle"}, new Object[]{245, "sixoldstyle"}, new Object[]{246, "sevenoldstyle"}, new Object[]{247, "eightoldstyle"}, new Object[]{248, "nineoldstyle"}, new Object[]{27, "colon"}, new Object[]{28, "semicolon"}, new Object[]{249, "commasuperior"}, new Object[]{Integer.valueOf(ItemTouchHelper.Callback.DEFAULT_SWIPE_ANIMATION_DURATION), "threequartersemdash"}, new Object[]{251, "periodsuperior"}, new Object[]{253, "asuperior"}, new Object[]{254, "bsuperior"}, new Object[]{255, "centsuperior"}, new Object[]{256, "dsuperior"}, new Object[]{257, "esuperior"}, new Object[]{Integer.valueOf(WGL4Names.NUMBER_OF_MAC_GLYPHS), "isuperior"}, new Object[]{259, "lsuperior"}, new Object[]{260, "msuperior"}, new Object[]{261, "nsuperior"}, new Object[]{262, "osuperior"}, new Object[]{263, "rsuperior"}, new Object[]{264, "ssuperior"}, new Object[]{265, "tsuperior"}, new Object[]{266, "ff"}, new Object[]{109, "fi"}, new Object[]{110, "fl"}, new Object[]{267, "ffi"}, new Object[]{268, "ffl"}, new Object[]{269, "parenleftinferior"}, new Object[]{270, "parenrightinferior"}, new Object[]{272, "hyphensuperior"}, new Object[]{300, "colonmonetary"}, new Object[]{301, "onefitted"}, new Object[]{302, "rupiah"}, new Object[]{305, "centoldstyle"}, new Object[]{314, "figuredash"}, new Object[]{315, "hypheninferior"}, new Object[]{158, "onequarter"}, new Object[]{155, "onehalf"}, new Object[]{163, "threequarters"}, new Object[]{Integer.valueOf(UiPosIndexEnum.PAYMENT_ZHIFUBAO_IC), "oneeighth"}, new Object[]{321, "threeeighths"}, new Object[]{Integer.valueOf(UiPosIndexEnum.PAYMENT_COIN_IC), "fiveeighths"}, new Object[]{Integer.valueOf(UiPosIndexEnum.PAYMENT_CASH_IC), "seveneighths"}, new Object[]{Integer.valueOf(UiPosIndexEnum.PAYMENT_GAME_COIN_IC), "onethird"}, new Object[]{Integer.valueOf(UiPosIndexEnum.PAYMENT_CARD_IC), "twothirds"}, new Object[]{Integer.valueOf(UiPosIndexEnum.PAYMENT_PRICE_TEXT), "zerosuperior"}, new Object[]{Integer.valueOf(PowerState.Code.resetSoftGraceful), "onesuperior"}, new Object[]{164, "twosuperior"}, new Object[]{169, "threesuperior"}, new Object[]{Integer.valueOf(UiPosIndexEnum.PAYMENT_BALANCE), "foursuperior"}, new Object[]{Integer.valueOf(UiPosIndexEnum.PAYMENT_CODE_EX_BG), "fivesuperior"}, new Object[]{Integer.valueOf(UiPosIndexEnum.PAYMENT_CODE_INPUT), "sixsuperior"}, new Object[]{Integer.valueOf(UiPosIndexEnum.PAYMENT_ARROW_IC), "sevensuperior"}, new Object[]{Integer.valueOf(UiPosIndexEnum.PHOTO_TRANSITION), "eightsuperior"}, new Object[]{Integer.valueOf(UiPosIndexEnum.PAYMENT_MEI_TUAN_IC), "ninesuperior"}, new Object[]{Integer.valueOf(UiPosIndexEnum.PAYMENT_DOU_YIN_IC), "zeroinferior"}, new Object[]{Integer.valueOf(UiPosIndexEnum.PAYMENT_MEI_TUAN_BG), "oneinferior"}, new Object[]{Integer.valueOf(UiPosIndexEnum.PAYMENT_DOU_YIN_BG), "twoinferior"}, new Object[]{Integer.valueOf(UiPosIndexEnum.PAYMENT_XIE_CHENG_IC), "threeinferior"}, new Object[]{337, "fourinferior"}, new Object[]{338, "fiveinferior"}, new Object[]{339, "sixinferior"}, new Object[]{340, "seveninferior"}, new Object[]{341, "eightinferior"}, new Object[]{342, "nineinferior"}, new Object[]{343, "centinferior"}, new Object[]{344, "dollarinferior"}, new Object[]{345, "periodinferior"}, new Object[]{346, "commainferior"}};
        CFF_EXPERT_SUBSET_CHARSET_TABLE = objArr;
        INSTANCE = new CFFExpertSubsetCharset();
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

    private CFFExpertSubsetCharset() {
        super(false);
    }

    public static CFFExpertSubsetCharset getInstance() {
        return INSTANCE;
    }
}
