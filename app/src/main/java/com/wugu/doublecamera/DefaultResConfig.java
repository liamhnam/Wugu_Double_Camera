package com.wugu.doublecamera;

import android.content.Context;
import android.text.TextUtils;
import com.brother.sdk.common.socket.devicemanagement.snmp.westhawk.SnmpContextBasisFace;
import com.brother.sdk.print.pdl.PrintImageUtil;
import com.faceunity.core.model.facebeauty.FaceBeautyFilterEnum;
import com.p020hp.jipp.model.PowerState;
import com.tom_roush.fontbox.ttf.OS2WindowsMetricsTable;
import com.wugu.doublecamera.bean.FrameInfo;
import com.wugu.doublecamera.bean.FramePhotoInfo;
import com.wugu.doublecamera.bean.FrameThemeInfo;
import com.wugu.doublecamera.database.DbHelper;
import com.wugu.doublecamera.database.dbSheet.FilterEntity;
import com.wugu.doublecamera.database.dbSheet.FrameEntity;
import com.wugu.doublecamera.database.dbSheet.FramePhotoEntity;
import com.wugu.doublecamera.database.dbSheet.MakeupEntity;
import com.wugu.doublecamera.database.dbSheet.UiPositionEntity;
import com.wugu.doublecamera.enums.UiPosIndexEnum;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class DefaultResConfig {
    public static final Map<String, Integer> MAP_UI_KEY_INDEX = new HashMap<String, Integer>() {
        {
            put("homeBg", 12);
            put("homeBtn", 11);
            put("modelBg", 13);
            put("modelBackBtn", 19);
            put("modelTabIp", 111);
            put("modelTabHead", 14);
            put("modelTabAi", 18);
            put("modelTabIdPhoto", 15);
            put("modelTabUpPrint", 16);
            put("modelTabBg", Integer.valueOf(UiPosIndexEnum.HOME_REPLACE_BG_TAB));
            put("frameBackBtn", 22);
            put("frameThemeBg", 24);
            put("frameBg", 26);
            put("frameCutIcon", 25);
            put("frameAiBg", 28);
            put("frameAiModelBg", 29);
            put("frameAiInstruction", 210);
            put("payBg", 36);
            put("payModeBg", 310);
            put("payPriceBg", 318);
            put("payBackBtn", 37);
            put("payWechat", 311);
            put("payZhiFuBao", 312);
            put("payCodeEx", 313);
            put("payCash", 315);
            put("payCoin", 314);
            put("payGameCoin", 316);
            put("payCredit", 317);
            put("payMeiTuan", Integer.valueOf(UiPosIndexEnum.PAYMENT_MEI_TUAN_IC));
            put("payDouYin", Integer.valueOf(UiPosIndexEnum.PAYMENT_DOU_YIN_IC));
            put("payXieCheng", Integer.valueOf(UiPosIndexEnum.PAYMENT_XIE_CHENG_IC));
            put("payMeiTuanBg", Integer.valueOf(UiPosIndexEnum.PAYMENT_MEI_TUAN_BG));
            put("payDouYinBg", Integer.valueOf(UiPosIndexEnum.PAYMENT_DOU_YIN_BG));
            put("payArrowIc", Integer.valueOf(UiPosIndexEnum.PAYMENT_ARROW_IC));
            put("payCodeExBg", Integer.valueOf(UiPosIndexEnum.PAYMENT_CODE_EX_BG));
            put("payWechatIc", Integer.valueOf(UiPosIndexEnum.PAYMENT_WECHAT_IC));
            put("payZhiFuBaoIc", Integer.valueOf(UiPosIndexEnum.PAYMENT_ZHIFUBAO_IC));
            put("payCashIc", Integer.valueOf(UiPosIndexEnum.PAYMENT_CASH_IC));
            put("payCoinIc", Integer.valueOf(UiPosIndexEnum.PAYMENT_COIN_IC));
            put("payGameCoinIc", Integer.valueOf(UiPosIndexEnum.PAYMENT_GAME_COIN_IC));
            put("payCreditIc", Integer.valueOf(UiPosIndexEnum.PAYMENT_CARD_IC));
            put("gotoPhotoBg", Integer.valueOf(UiPosIndexEnum.PHOTO_TRANSITION));
            put("countdownBg", Integer.valueOf(UiPosIndexEnum.PHOTO_COUNTDOWN_BG));
            put("beautyTitle", Integer.valueOf(UiPosIndexEnum.PHOTO_BEAUTY_TITLE));
            put("beautyNone", Integer.valueOf(UiPosIndexEnum.PHOTO_BEAUTY_NONE));
            put("beautyWeak", Integer.valueOf(UiPosIndexEnum.PHOTO_BEAUTY_ONE));
            put("beautyMiddle", Integer.valueOf(UiPosIndexEnum.PHOTO_BEAUTY_TWO));
            put("beautyStrong", Integer.valueOf(UiPosIndexEnum.PHOTO_BEAUTY_THREE));
            put("filterBtn", Integer.valueOf(UiPosIndexEnum.PHOTO_BTN_FILTER));
            put("makeupBtn", Integer.valueOf(UiPosIndexEnum.PHOTO_BTN_MAKEUP));
            put("effectBtn", Integer.valueOf(UiPosIndexEnum.PHOTO_BTN_EFFECT));
            put("photoStartBtn", Integer.valueOf(UiPosIndexEnum.PHOTO_BTN_PHOTO));
            put("photoRetakeBtn", Integer.valueOf(UiPosIndexEnum.PHOTO_BTN_RETAKE));
            put("photoConfirmBtn", Integer.valueOf(UiPosIndexEnum.PHOTO_BTN_CONFIRM));
            put("photoPreBg", Integer.valueOf(UiPosIndexEnum.PHOTO_PREVIEW_BG));
            put("photoReadyBg", Integer.valueOf(UiPosIndexEnum.PHOTO_CONFIRM_BG));
            put("prePrintStickerBg", Integer.valueOf(UiPosIndexEnum.PRINT_BG_STICKER));
            put("prePrintSignBg", Integer.valueOf(UiPosIndexEnum.PRINT_BG_SIGN));
            put("stickerBg", Integer.valueOf(UiPosIndexEnum.PRINT_STICKER_BG));
            put("signBg", Integer.valueOf(UiPosIndexEnum.PRINT_SIGN_BG));
            put("signBtn", Integer.valueOf(UiPosIndexEnum.PRINT_BTN_SIGN));
            put("signUndoBtn", Integer.valueOf(UiPosIndexEnum.PRINT_SIGN_UNDO));
            put("signRedoBtn", Integer.valueOf(UiPosIndexEnum.PRINT_SIGN_REDO));
            put("signClearBtn", Integer.valueOf(UiPosIndexEnum.PRINT_SIGN_CLEAR));
            put("signCustomBtn", Integer.valueOf(UiPosIndexEnum.PRINT_SIGN_CUSTOM));
            put("signSizeBtn", Integer.valueOf(UiPosIndexEnum.PRINT_SIGN_SIZE));
            put("printCountText", Integer.valueOf(UiPosIndexEnum.PRINT_COUNT_TEXT));
            put("printCountSubIc", Integer.valueOf(UiPosIndexEnum.PRINT_COUNT_SUB));
            put("printCountAddIc", Integer.valueOf(UiPosIndexEnum.PRINT_COUNT_ADD));
            put("startPrintBtn", Integer.valueOf(UiPosIndexEnum.PRINT_BTN_PRINT));
            put("addQrCodeBtn", Integer.valueOf(UiPosIndexEnum.PRINT_BTN_ADD_QRCODE));
            put("printingBg", Integer.valueOf(UiPosIndexEnum.PRINTING_BG));
            put("photoQrCode", Integer.valueOf(UiPosIndexEnum.PRINT_QRCODE1));
            put("backBtn", Integer.valueOf(UiPosIndexEnum.PRINT_BTN_BACK));
            put("backBtnHome", Integer.valueOf(UiPosIndexEnum.PRINT_BTN_BACK_HOME));
            put("IdPhotoPreBg", Integer.valueOf(UiPosIndexEnum.CERTIFY_BG_PREVIEW));
            put("IdPhotoConfigBg", Integer.valueOf(UiPosIndexEnum.CERTIFY_BG_CONFIRM));
            put("IdPhotoColorBg", Integer.valueOf(UiPosIndexEnum.CERTIFY_COLOR_BG));
            put("IdPhotoRedBtn", Integer.valueOf(UiPosIndexEnum.CERTIFY_RED_BG));
            put("IdPhotoBlueBtn", Integer.valueOf(UiPosIndexEnum.CERTIFY_BLUE_BG));
            put("IdPhotoWhiteBtn", Integer.valueOf(UiPosIndexEnum.CERTIFY_WHITE_BG));
            put("IdPhotoItemBg", Integer.valueOf(UiPosIndexEnum.CERTIFY_PRINT_ITEM_BG));
            put("IdPhotoPrintBg", Integer.valueOf(UiPosIndexEnum.CERTIFY_PRINT_BG));
            put("IdPhotoPrintCount", Integer.valueOf(UiPosIndexEnum.CERTIFY_PRINT_COUNT_IC));
            put("IdPhotoAdd", Integer.valueOf(UiPosIndexEnum.CERTIFY_ADD_IC));
            put("IdPhotoSub", Integer.valueOf(UiPosIndexEnum.CERTIFY_SUB_IC));
            put("adminPwdBg", Integer.valueOf(UiPosIndexEnum.ADMIN_PWD_BG));
            put("adminBtnBack", Integer.valueOf(UiPosIndexEnum.ADMIN_BTN_BACK));
            put("adminBg", Integer.valueOf(UiPosIndexEnum.ADMIN_BG));
            put("adminExitApp", Integer.valueOf(UiPosIndexEnum.ADMIN_BTN_EXIT));
            put("adminPowerOff", Integer.valueOf(UiPosIndexEnum.ADMIN_BTN_OFF));
            put("adminReboot", Integer.valueOf(UiPosIndexEnum.ADMIN_BTN_REBOOT));
            put("adminCPwd", Integer.valueOf(UiPosIndexEnum.ADMIN_BTN_C_PWD));
            put("uploadPrintUpBg", Integer.valueOf(UiPosIndexEnum.UPLOAD_UP_BG));
            put("uploadPrintPayBg", 900);
            put("uploadPrintMainBg", 901);
            put("uploadPrintBackHomeBtn", 902);
            put("uploadPrintRvPicBg", 903);
            put("uploadPrintRvFrameBg", 905);
            put("uploadPrintPrintingBg", 906);
            put("uploadPrintAddText", Integer.valueOf(UiPosIndexEnum.UPLOAD_ADD_PRINT_TEXT));
            put("peBgRoot", Integer.valueOf(UiPosIndexEnum.PE_BG_ROOT));
            put("peBtnHomeStart", Integer.valueOf(UiPosIndexEnum.PE_BTN_HOME_START));
            put("peBgHome", Integer.valueOf(UiPosIndexEnum.PE_BG_HOME));
            put("peBgHomeArrowL", Integer.valueOf(UiPosIndexEnum.PE_HOME_ARROW_L));
            put("peBgHomeArrowR", Integer.valueOf(UiPosIndexEnum.PE_HOME_ARROW_R));
            put("peBtnBackHome", Integer.valueOf(UiPosIndexEnum.PE_BTN_BACK_HOME));
            put("peBgPay", Integer.valueOf(UiPosIndexEnum.PE_BG_PAY));
            put("peBgPrint", Integer.valueOf(UiPosIndexEnum.PE_BG_PRINT));
            put("peIcPrintSub", Integer.valueOf(UiPosIndexEnum.PE_IC_PRINT_SUB));
            put("peIcPrintAdd", Integer.valueOf(UiPosIndexEnum.PE_IC_PRINT_ADD));
            put("peBtnPrint", Integer.valueOf(UiPosIndexEnum.PE_BTN_PRINT));
            put("peBgPrintPay", Integer.valueOf(UiPosIndexEnum.PE_BG_PRINT_PAY));
            put("peBtnReturn", Integer.valueOf(UiPosIndexEnum.PE_BTN_RETURN));
            put("peBgPhotoQrcode", Integer.valueOf(UiPosIndexEnum.PE_BG_PHOTO_QRCODE));
            put("peIcKaCha", Integer.valueOf(UiPosIndexEnum.PE_IC_KACHA));
            put("peIcPrintingArrow", Integer.valueOf(UiPosIndexEnum.PE_IC_PRINTING_ARROW));
            put("peIcRecode", Integer.valueOf(UiPosIndexEnum.PE_BTN_RECODE));
            put("peWaterMark", Integer.valueOf(UiPosIndexEnum.PE_WATER_MARK));
            put("peBtnRetake", Integer.valueOf(UiPosIndexEnum.PE_BTN_RETAKE));
            put("peBtnConfirm", Integer.valueOf(UiPosIndexEnum.PE_BTN_CONFIRM));
            put("peBtnNum1", Integer.valueOf(UiPosIndexEnum.PE_BTN_NUM1));
            put("peBtnNum1Sel", Integer.valueOf(UiPosIndexEnum.PE_BTN_NUM1_SEL));
            put("peBtnNum2", Integer.valueOf(UiPosIndexEnum.PE_BTN_NUM2));
            put("peBtnNum2Sel", Integer.valueOf(UiPosIndexEnum.PE_BTN_NUM2_SEL));
            put("peBtnNum3", Integer.valueOf(UiPosIndexEnum.PE_BTN_NUM3));
            put("peBtnNum3Sel", Integer.valueOf(UiPosIndexEnum.PE_BTN_NUM3_SEL));
            put("peBtnNum4", Integer.valueOf(UiPosIndexEnum.PE_BTN_NUM4));
            put("peBtnNum4Sel", Integer.valueOf(UiPosIndexEnum.PE_BTN_NUM4_SEL));
            put("peBtnNum5", Integer.valueOf(UiPosIndexEnum.PE_BTN_NUM5));
            put("peBtnNum5Sel", Integer.valueOf(UiPosIndexEnum.PE_BTN_NUM5_SEL));
            put("peBtnNum6", Integer.valueOf(UiPosIndexEnum.PE_BTN_NUM6));
            put("peBtnNum6Sel", Integer.valueOf(UiPosIndexEnum.PE_BTN_NUM6_SEL));
            put("peBtnNum7", Integer.valueOf(UiPosIndexEnum.PE_BTN_NUM7));
            put("peBtnNum7Sel", Integer.valueOf(UiPosIndexEnum.PE_BTN_NUM7_SEL));
            put("peBtnNum8", Integer.valueOf(UiPosIndexEnum.PE_BTN_NUM8));
            put("peBtnNum8Sel", Integer.valueOf(UiPosIndexEnum.PE_BTN_NUM8_SEL));
            put("peIcHomeTip", Integer.valueOf(UiPosIndexEnum.PE_IC_HOME_TIP));
            put("peBgRetake", Integer.valueOf(UiPosIndexEnum.PE_BG_RETAKE));
            put("peBtnMtCode", Integer.valueOf(UiPosIndexEnum.PE_BTN_MT_CODE));
            put("peBtnDyCode", Integer.valueOf(UiPosIndexEnum.PE_BTN_DY_CODE));
            put("peIcMtTip", Integer.valueOf(UiPosIndexEnum.PE_IC_MT_TIPS));
            put("peIcDyTip", Integer.valueOf(UiPosIndexEnum.PE_IC_DY_TIPS));
            put("peIcUploadPrint", Integer.valueOf(UiPosIndexEnum.PE_IC_UPLOAD_PRINT));
            put("poBtnFrameEnter", 1003);
            put("poBtnBeautyNullSel", 1004);
            put("poBtnBeautyOneSel", 1005);
            put("poBtnBeautyTwoSel", 1006);
            put("poBtnBeautyThreeSel", 1007);
            put("poBtnFilterSel", 1008);
            put("poBtnMakeupSel", 1009);
            put("poBtnEffectSel", 1010);
            put("poBgSetPhoto", 1013);
            put("poBgSetPhotoList", 1014);
            put("poIcColorPrint", 1015);
            put("poIcBlackPrint", 1016);
            put("poBgPrintSel", 1017);
            put("keyboard0", Integer.valueOf(UiPosIndexEnum.KEYBOARD_0));
            put("keyboard1", Integer.valueOf(UiPosIndexEnum.KEYBOARD_1));
            put("keyboard2", Integer.valueOf(UiPosIndexEnum.KEYBOARD_2));
            put("keyboard3", Integer.valueOf(UiPosIndexEnum.KEYBOARD_3));
            put("keyboard4", Integer.valueOf(UiPosIndexEnum.KEYBOARD_4));
            put("keyboard5", Integer.valueOf(UiPosIndexEnum.KEYBOARD_5));
            put("keyboard6", Integer.valueOf(UiPosIndexEnum.KEYBOARD_6));
            put("keyboard7", Integer.valueOf(UiPosIndexEnum.KEYBOARD_7));
            put("keyboard8", Integer.valueOf(UiPosIndexEnum.KEYBOARD_8));
            put("keyboard9", Integer.valueOf(UiPosIndexEnum.KEYBOARD_9));
            put("keyboardDel", Integer.valueOf(UiPosIndexEnum.KEYBOARD_DEL));
            put("keyboardEnter", Integer.valueOf(UiPosIndexEnum.KEYBOARD_OK));
        }
    };

    public static List<UiPositionEntity> getDefaultUiPosition() {
        ArrayList arrayList = new ArrayList();
        Integer numValueOf = Integer.valueOf(PowerState.Code.resetMbrGraceful);
        Integer numValueOf2 = Integer.valueOf(UiPosIndexEnum.PHOTO_BEAUTY_POS);
        Integer numValueOf3 = Integer.valueOf(PowerState.Code.resetSoftGraceful);
        Object[][] objArr = {new Object[]{11, 750, 700, AppConfig.BASE_FOLDER + "default_ui/1-待机页面按钮.png"}, new Object[]{12, 0, 0, AppConfig.BASE_FOLDER + "default_ui/1-待机页面背景.png"}, new Object[]{19, 40, 30, AppConfig.BASE_FOLDER + "default_ui/2-返回按钮.png"}, new Object[]{13, 0, 0, AppConfig.BASE_FOLDER + "default_ui/1-选区背景.png"}, new Object[]{111, 160, numValueOf, AppConfig.BASE_FOLDER + "default_ui/1-明星IP选区.png"}, new Object[]{14, 700, numValueOf, AppConfig.BASE_FOLDER + "default_ui/1-大头照选区.png"}, new Object[]{18, Integer.valueOf(UiPosIndexEnum.PE_IC_MT_TIPS), numValueOf, AppConfig.BASE_FOLDER + "default_ui/1-ai选区.png"}, new Object[]{15, 160, numValueOf2, AppConfig.BASE_FOLDER + "default_ui/1-证件照选区.png"}, new Object[]{16, 700, numValueOf2, AppConfig.BASE_FOLDER + "default_ui/1-打印选区.png"}, new Object[]{Integer.valueOf(UiPosIndexEnum.HOME_REPLACE_BG_TAB), Integer.valueOf(UiPosIndexEnum.PE_IC_MT_TIPS), numValueOf2, AppConfig.BASE_FOLDER + "default_ui/1-魔术场景秀.png"}, new Object[]{22, 40, 30, AppConfig.BASE_FOLDER + "default_ui/2-返回按钮.png"}, new Object[]{23, 0, 180, null}, new Object[]{24, 0, 920, null}, new Object[]{25, 0, 0, AppConfig.BASE_FOLDER + "default_ui/2-对半切两张.png"}, new Object[]{26, 0, 0, AppConfig.BASE_FOLDER + "default_ui/2-相框背景.png"}, new Object[]{27, numValueOf, 20, null}, new Object[]{36, Integer.valueOf(PrintImageUtil.ROUND_ROTATE), 120, AppConfig.BASE_FOLDER + "default_ui/3-支付背景.png"}, new Object[]{37, 1320, Integer.valueOf(PowerState.Code.resetInit), AppConfig.BASE_FOLDER + "default_ui/3-支付取消.png"}, new Object[]{38, 880, 155, null}, new Object[]{39, 500, 260, null}, new Object[]{310, 950, 260, AppConfig.BASE_FOLDER + "default_ui/3-支付方式.png"}, new Object[]{311, 0, 0, AppConfig.BASE_FOLDER + "default_ui/3-微信支付.png"}, new Object[]{312, 0, 0, AppConfig.BASE_FOLDER + "default_ui/3-支付宝支付.png"}, new Object[]{313, 0, 0, AppConfig.BASE_FOLDER + "default_ui/3-券码支付.png"}, new Object[]{315, 0, 0, AppConfig.BASE_FOLDER + "default_ui/3-纸币支付.png"}, new Object[]{314, 0, 0, AppConfig.BASE_FOLDER + "default_ui/3-硬币支付.png"}, new Object[]{316, 0, 0, AppConfig.BASE_FOLDER + "default_ui/3-游戏币支付.png"}, new Object[]{317, 0, 0, AppConfig.BASE_FOLDER + "default_ui/3-nayax支付.png"}, new Object[]{318, 0, 0, AppConfig.BASE_FOLDER + "default_ui/3-支付需要金额.png"}, new Object[]{Integer.valueOf(UiPosIndexEnum.PAYMENT_WECHAT_IC), 0, 0, AppConfig.BASE_FOLDER + "default_ui/3-微信图标.png"}, new Object[]{Integer.valueOf(UiPosIndexEnum.PAYMENT_ZHIFUBAO_IC), 0, 0, AppConfig.BASE_FOLDER + "default_ui/3-支付宝图标.png"}, new Object[]{Integer.valueOf(UiPosIndexEnum.PAYMENT_CASH_IC), 0, 0, AppConfig.BASE_FOLDER + "default_ui/3-纸币图标.png"}, new Object[]{Integer.valueOf(UiPosIndexEnum.PAYMENT_COIN_IC), 0, 0, AppConfig.BASE_FOLDER + "default_ui/3-硬币图标.png"}, new Object[]{Integer.valueOf(UiPosIndexEnum.PAYMENT_GAME_COIN_IC), 0, 0, AppConfig.BASE_FOLDER + "default_ui/3-游戏币图标.png"}, new Object[]{Integer.valueOf(UiPosIndexEnum.PAYMENT_CARD_IC), 0, 0, AppConfig.BASE_FOLDER + "default_ui/3-刷卡图标.png"}, new Object[]{Integer.valueOf(UiPosIndexEnum.PAYMENT_ARROW_IC), 0, 0, AppConfig.BASE_FOLDER + "default_ui/3-支付跳转按钮.png"}, new Object[]{Integer.valueOf(UiPosIndexEnum.PAYMENT_PRICE_TEXT), 125, 65, null}, new Object[]{Integer.valueOf(UiPosIndexEnum.PAYMENT_BALANCE), 0, 300, null}, new Object[]{Integer.valueOf(UiPosIndexEnum.PAYMENT_CODE_EX_BG), 0, 0, AppConfig.BASE_FOLDER + "default_ui/3-券码背景.png"}, new Object[]{Integer.valueOf(UiPosIndexEnum.PAYMENT_CODE_INPUT), 50, 110, null}, new Object[]{Integer.valueOf(UiPosIndexEnum.PAYMENT_KEYBOARD_POS), 40, 175, null}, new Object[]{Integer.valueOf(UiPosIndexEnum.PHOTO_TRANSITION), 0, 0, AppConfig.BASE_FOLDER + "default_ui/3-进入拍照过渡背景.png"}, new Object[]{Integer.valueOf(UiPosIndexEnum.KEYBOARD_0), 0, 0, AppConfig.BASE_FOLDER + "default_ui/k-0.png"}, new Object[]{Integer.valueOf(UiPosIndexEnum.KEYBOARD_1), 0, 0, AppConfig.BASE_FOLDER + "default_ui/k-1.png"}, new Object[]{Integer.valueOf(UiPosIndexEnum.KEYBOARD_2), 0, 0, AppConfig.BASE_FOLDER + "default_ui/k-2.png"}, new Object[]{Integer.valueOf(UiPosIndexEnum.KEYBOARD_3), 0, 0, AppConfig.BASE_FOLDER + "default_ui/k-3.png"}, new Object[]{Integer.valueOf(UiPosIndexEnum.KEYBOARD_4), 0, 0, AppConfig.BASE_FOLDER + "default_ui/k-4.png"}, new Object[]{Integer.valueOf(UiPosIndexEnum.KEYBOARD_5), 0, 0, AppConfig.BASE_FOLDER + "default_ui/k-5.png"}, new Object[]{Integer.valueOf(UiPosIndexEnum.KEYBOARD_6), 0, 0, AppConfig.BASE_FOLDER + "default_ui/k-6.png"}, new Object[]{Integer.valueOf(UiPosIndexEnum.KEYBOARD_7), 0, 0, AppConfig.BASE_FOLDER + "default_ui/k-7.png"}, new Object[]{Integer.valueOf(UiPosIndexEnum.KEYBOARD_8), 0, 0, AppConfig.BASE_FOLDER + "default_ui/k-8.png"}, new Object[]{Integer.valueOf(UiPosIndexEnum.KEYBOARD_9), 0, 0, AppConfig.BASE_FOLDER + "default_ui/k-9.png"}, new Object[]{Integer.valueOf(UiPosIndexEnum.KEYBOARD_DEL), 0, 0, AppConfig.BASE_FOLDER + "default_ui/k-删除.png"}, new Object[]{Integer.valueOf(UiPosIndexEnum.KEYBOARD_OK), 0, 0, AppConfig.BASE_FOLDER + "default_ui/k-确认.png"}, new Object[]{Integer.valueOf(UiPosIndexEnum.PHOTO_COUNTDOWN_BG), 0, 0, AppConfig.BASE_FOLDER + "default_ui/p-倒计时背景.png"}, new Object[]{numValueOf2, 50, 160, null}, new Object[]{Integer.valueOf(UiPosIndexEnum.PHOTO_BEAUTY_TITLE), 0, 0, AppConfig.BASE_FOLDER + "default_ui/4-美颜按钮.png"}, new Object[]{Integer.valueOf(UiPosIndexEnum.PHOTO_BEAUTY_NONE), 0, 0, AppConfig.BASE_FOLDER + "default_ui/4-美颜强度按钮1.png"}, new Object[]{Integer.valueOf(UiPosIndexEnum.PHOTO_BEAUTY_ONE), 0, 0, AppConfig.BASE_FOLDER + "default_ui/4-美颜强度按钮2.png"}, new Object[]{Integer.valueOf(UiPosIndexEnum.PHOTO_BEAUTY_TWO), 0, 0, AppConfig.BASE_FOLDER + "default_ui/4-美颜强度按钮3.png"}, new Object[]{Integer.valueOf(UiPosIndexEnum.PHOTO_BEAUTY_THREE), 0, 0, AppConfig.BASE_FOLDER + "default_ui/4-美颜强度按钮4.png"}, new Object[]{Integer.valueOf(UiPosIndexEnum.PHOTO_PICTURE_LIST), 310, 830, null}, new Object[]{Integer.valueOf(UiPosIndexEnum.PHOTO_RETAKE_LIST), 60, 130, null}, new Object[]{Integer.valueOf(UiPosIndexEnum.PHOTO_EFFECT_FILTER_POS), Integer.valueOf(SnmpContextBasisFace.MSS), 100, null}, new Object[]{Integer.valueOf(UiPosIndexEnum.PHOTO_EFFECT_FILTER_BG), 0, 90, AppConfig.BASE_FOLDER + "default_ui/4-滤镜美妆框.png"}, new Object[]{Integer.valueOf(UiPosIndexEnum.PHOTO_PREVIEW_POS), 200, 130, null}, new Object[]{Integer.valueOf(UiPosIndexEnum.PHOTO_CONFIRM_POS), 470, 100, null}, new Object[]{1011, 200, numValueOf3, null}, new Object[]{1012, 470, numValueOf3, null}, new Object[]{Integer.valueOf(UiPosIndexEnum.PHOTO_COUNTDOWN), 10, 10, null}, new Object[]{Integer.valueOf(UiPosIndexEnum.PHOTO_TV_RETAKE_COUNT), 1520, 730, null}, new Object[]{Integer.valueOf(UiPosIndexEnum.PHOTO_BTN_PHOTO), 900, 880, AppConfig.BASE_FOLDER + "default_ui/4-拍照确定.png"}, new Object[]{Integer.valueOf(UiPosIndexEnum.PHOTO_BTN_CONFIRM), 1540, 270, AppConfig.BASE_FOLDER + "default_ui/4-拍照确定.png"}, new Object[]{Integer.valueOf(UiPosIndexEnum.PHOTO_BTN_RETAKE), 1540, 570, AppConfig.BASE_FOLDER + "default_ui/4-重拍按钮.png"}, new Object[]{Integer.valueOf(UiPosIndexEnum.PHOTO_BTN_FILTER), 50, 10, AppConfig.BASE_FOLDER + "default_ui/4-滤镜按钮.png"}, new Object[]{Integer.valueOf(UiPosIndexEnum.PHOTO_BTN_MAKEUP), 210, 10, AppConfig.BASE_FOLDER + "default_ui/4-美妆按钮.png"}, new Object[]{Integer.valueOf(UiPosIndexEnum.PHOTO_BTN_EFFECT), 380, 10, AppConfig.BASE_FOLDER + "default_ui/4-特效按钮.png"}, new Object[]{Integer.valueOf(UiPosIndexEnum.PHOTO_FILTER_LIST), 20, 100, null}, new Object[]{Integer.valueOf(UiPosIndexEnum.PHOTO_PREVIEW_BG), 0, 0, AppConfig.BASE_FOLDER + "default_ui/4-预览拍照背景.png"}, new Object[]{Integer.valueOf(UiPosIndexEnum.PHOTO_CONFIRM_BG), 0, 0, AppConfig.BASE_FOLDER + "default_ui/4-确认照片背景.png"}, new Object[]{Integer.valueOf(UiPosIndexEnum.PRINT_STICKER_SIGN_POS), 1010, 100, null}, new Object[]{Integer.valueOf(UiPosIndexEnum.PRINT_BG_STICKER), 0, 0, AppConfig.BASE_FOLDER + "default_ui/5-贴纸背景.png"}, new Object[]{Integer.valueOf(UiPosIndexEnum.PRINT_BG_SIGN), 0, 0, AppConfig.BASE_FOLDER + "default_ui/5-签名背景.png"}, new Object[]{Integer.valueOf(UiPosIndexEnum.PRINT_STICKER_BG), numValueOf3, 0, AppConfig.BASE_FOLDER + "default_ui/5-贴纸背景框.png"}, new Object[]{Integer.valueOf(UiPosIndexEnum.PRINT_SIGN_BG), 0, 0, AppConfig.BASE_FOLDER + "default_ui/5-签名背景框.png"}, new Object[]{Integer.valueOf(UiPosIndexEnum.PRINT_COUNT_TEXT), 310, 860, AppConfig.BASE_FOLDER + "default_ui/5-打印份数.png"}, new Object[]{Integer.valueOf(UiPosIndexEnum.PRINT_COUNT_SUB), 590, 875, AppConfig.BASE_FOLDER + "default_ui/5-打印减.png"}, new Object[]{Integer.valueOf(UiPosIndexEnum.PRINT_COUNT_ADD), 720, 875, AppConfig.BASE_FOLDER + "default_ui/5-打印加.png"}, new Object[]{Integer.valueOf(UiPosIndexEnum.PRINT_COUNT_NUM), 650, 870, null}, new Object[]{Integer.valueOf(UiPosIndexEnum.PRINT_BTN_PRINT), 880, 850, AppConfig.BASE_FOLDER + "default_ui/5-打印按钮.png"}, new Object[]{Integer.valueOf(UiPosIndexEnum.PRINT_FRAME_POS), 90, 130, null}, new Object[]{Integer.valueOf(UiPosIndexEnum.PRINT_COUNTDOWN), 15, 15, null}, new Object[]{Integer.valueOf(UiPosIndexEnum.PRINT_PAY_PRICE), 162, 80, null}, new Object[]{Integer.valueOf(UiPosIndexEnum.PRINT_PAY_BALANCE), 0, Integer.valueOf(PrintImageUtil.ROUND_ROTATE), null}, new Object[]{Integer.valueOf(UiPosIndexEnum.PRINT_QRCODE1), 1440, 300, null}, new Object[]{Integer.valueOf(UiPosIndexEnum.PRINT_BTN_BACK), 1500, Integer.valueOf(OS2WindowsMetricsTable.WEIGHT_CLASS_EXTRA_BOLD), AppConfig.BASE_FOLDER + "default_ui/5-返回.png"}, new Object[]{Integer.valueOf(UiPosIndexEnum.PRINT_BTN_BACK_HOME), 1460, Integer.valueOf(OS2WindowsMetricsTable.WEIGHT_CLASS_EXTRA_BOLD), AppConfig.BASE_FOLDER + "default_ui/5-返回首页.png"}, new Object[]{Integer.valueOf(UiPosIndexEnum.PRINT_BTN_SIGN), 0, 0, AppConfig.BASE_FOLDER + "default_ui/5-手绘签名按钮.png"}, new Object[]{Integer.valueOf(UiPosIndexEnum.PRINT_BTN_ADD_TEXT), 1550, 880, null}, new Object[]{Integer.valueOf(UiPosIndexEnum.PRINT_PRICE_POS), 600, 930, null}, new Object[]{Integer.valueOf(UiPosIndexEnum.PRINT_SIGN_UNDO), 0, 0, AppConfig.BASE_FOLDER + "default_ui/5-撤回按钮.png"}, new Object[]{Integer.valueOf(UiPosIndexEnum.PRINT_SIGN_REDO), 0, 0, AppConfig.BASE_FOLDER + "default_ui/5-恢复按钮.png"}, new Object[]{Integer.valueOf(UiPosIndexEnum.PRINT_SIGN_CLEAR), 0, 0, AppConfig.BASE_FOLDER + "default_ui/5-全部清除按钮.png"}, new Object[]{Integer.valueOf(UiPosIndexEnum.PRINT_SIGN_CUSTOM), 0, 0, AppConfig.BASE_FOLDER + "default_ui/5-自定义颜色按钮.png"}, new Object[]{Integer.valueOf(UiPosIndexEnum.PRINT_SIGN_SIZE), 0, 0, AppConfig.BASE_FOLDER + "default_ui/5-大小按钮.png"}, new Object[]{Integer.valueOf(UiPosIndexEnum.PRINTING_BG), 0, 0, AppConfig.BASE_FOLDER + "default_ui/5-电子二维码大背景.png"}, new Object[]{Integer.valueOf(UiPosIndexEnum.ADMIN_PWD_BG), 0, 0, AppConfig.BASE_FOLDER + "default_ui/6-密码背景.png"}, new Object[]{Integer.valueOf(UiPosIndexEnum.ADMIN_BG), 0, 0, AppConfig.BASE_FOLDER + "default_ui/6-管理背景.png"}, new Object[]{Integer.valueOf(UiPosIndexEnum.ADMIN_BTN_BACK), 1700, 20, AppConfig.BASE_FOLDER + "default_ui/3-支付取消.png"}, new Object[]{Integer.valueOf(UiPosIndexEnum.ADMIN_COUNTDOWN), 1580, 15, null}, new Object[]{Integer.valueOf(UiPosIndexEnum.ADMIN_BTN_EXIT), 0, 0, AppConfig.BASE_FOLDER + "default_ui/6-退出.png"}, new Object[]{Integer.valueOf(UiPosIndexEnum.ADMIN_BTN_OFF), 0, 0, AppConfig.BASE_FOLDER + "default_ui/6-关机.png"}, new Object[]{Integer.valueOf(UiPosIndexEnum.ADMIN_BTN_REBOOT), 0, 0, AppConfig.BASE_FOLDER + "default_ui/6-重启.png"}, new Object[]{Integer.valueOf(UiPosIndexEnum.ADMIN_BTN_C_PWD), 0, 0, AppConfig.BASE_FOLDER + "default_ui/6-修改密码.png"}, new Object[]{110, 0, 0, AppConfig.BASE_FOLDER + "default_ui/1-暂停营业.webp"}, new Object[]{Integer.valueOf(UiPosIndexEnum.CERTIFY_COLOR_BG), 0, 0, AppConfig.BASE_FOLDER + "default_ui/7-证件照底色背景框.png"}, new Object[]{Integer.valueOf(UiPosIndexEnum.CERTIFY_BG_PREVIEW), 0, 0, AppConfig.BASE_FOLDER + "default_ui/6-管理背景.png"}, new Object[]{Integer.valueOf(UiPosIndexEnum.CERTIFY_BG_CONFIRM), 0, 0, AppConfig.BASE_FOLDER + "default_ui/7-证件照确认背景.png"}, new Object[]{Integer.valueOf(UiPosIndexEnum.CERTIFY_CONFIRM_ITEM_BG), 0, 0, AppConfig.BASE_FOLDER + "default_ui/7-确认Item框.png"}, new Object[]{Integer.valueOf(UiPosIndexEnum.CERTIFY_PRINT_ITEM_BG), 0, 0, AppConfig.BASE_FOLDER + "default_ui/7-证件照相框背景.png"}, new Object[]{Integer.valueOf(UiPosIndexEnum.CERTIFY_PRINT_BG), 0, 0, AppConfig.BASE_FOLDER + "default_ui/7-证件照打印背景.png"}, new Object[]{Integer.valueOf(UiPosIndexEnum.CERTIFY_RED_BG), 0, 0, AppConfig.BASE_FOLDER + "default_ui/7-红底按钮.png"}, new Object[]{Integer.valueOf(UiPosIndexEnum.CERTIFY_BLUE_BG), 0, 0, AppConfig.BASE_FOLDER + "default_ui/7-蓝底按钮.png"}, new Object[]{Integer.valueOf(UiPosIndexEnum.CERTIFY_WHITE_BG), 0, 0, AppConfig.BASE_FOLDER + "default_ui/7-白底按钮.png"}, new Object[]{Integer.valueOf(UiPosIndexEnum.CERTIFY_PRINT_COUNT_IC), 0, 0, AppConfig.BASE_FOLDER + "default_ui/7-证件照打印份数.png"}, new Object[]{Integer.valueOf(UiPosIndexEnum.CERTIFY_ADD_IC), 0, 0, AppConfig.BASE_FOLDER + "default_ui/7-证件照加.png"}, new Object[]{Integer.valueOf(UiPosIndexEnum.CERTIFY_SUB_IC), 0, 0, AppConfig.BASE_FOLDER + "default_ui/7-证件照减.png"}, new Object[]{900, 0, 0, AppConfig.BASE_FOLDER + "default_ui/8-上传打印支付背景.png"}, new Object[]{901, 0, 0, AppConfig.BASE_FOLDER + "default_ui/8-上传打印操作背景.png"}, new Object[]{902, 0, 0, AppConfig.BASE_FOLDER + "default_ui/8-上传打印返回首页.png"}, new Object[]{903, 0, 0, AppConfig.BASE_FOLDER + "default_ui/8-上传打印图片列表背景.png"}, new Object[]{904, 0, 0, AppConfig.BASE_FOLDER + "default_ui/8-上传打印相框操作背景.png"}, new Object[]{905, 0, 0, AppConfig.BASE_FOLDER + "default_ui/8-上传打印相框列表背景.png"}, new Object[]{906, 0, 0, AppConfig.BASE_FOLDER + "default_ui/8-上传打印电子二维码背景.png"}, new Object[]{Integer.valueOf(UiPosIndexEnum.UPLOAD_ADD_PRINT_TEXT), 0, 0, AppConfig.BASE_FOLDER + "default_ui/8-上传打印打印份数.png"}, new Object[]{1003, 0, 0, AppConfig.BASE_FOLDER + "default_ui/2-确定按钮.png"}, new Object[]{1004, 0, 0, AppConfig.BASE_FOLDER + "default_ui/4-美颜强度按钮1-1.png"}, new Object[]{1005, 0, 0, AppConfig.BASE_FOLDER + "default_ui/4-美颜强度按钮2-1.png"}, new Object[]{1006, 0, 0, AppConfig.BASE_FOLDER + "default_ui/4-美颜强度按钮3-1.png"}, new Object[]{1007, 0, 0, AppConfig.BASE_FOLDER + "default_ui/4-美颜强度按钮4-1.png"}, new Object[]{1008, 50, 10, AppConfig.BASE_FOLDER + "default_ui/4-滤镜按钮-1.png"}, new Object[]{1009, 210, 10, AppConfig.BASE_FOLDER + "default_ui/4-美妆按钮-1.png"}, new Object[]{1010, 380, 10, AppConfig.BASE_FOLDER + "default_ui/4-特效按钮-1.png"}, new Object[]{1013, 0, 0, AppConfig.BASE_FOLDER + "default_ui/10-置换照片背景.png"}, new Object[]{1014, 0, 0, AppConfig.BASE_FOLDER + "default_ui/10-置换照片列表背景.png"}, new Object[]{1015, 0, 0, AppConfig.BASE_FOLDER + "default_ui/5-原色打印.png"}, new Object[]{1016, 0, 0, AppConfig.BASE_FOLDER + "default_ui/5-黑白打印.png"}, new Object[]{1017, 0, 0, AppConfig.BASE_FOLDER + "default_ui/5-选中效果框.png"}, new Object[]{Integer.valueOf(UiPosIndexEnum.IS_TEST_BG), 0, 0, AppConfig.BASE_FOLDER + "default_ui_is/1-待机背景.jpg"}, new Object[]{Integer.valueOf(UiPosIndexEnum.IS_TEST_BG_PREVIEW_P), 0, 0, AppConfig.BASE_FOLDER + "default_ui_is/3-背景.png"}, new Object[]{Integer.valueOf(UiPosIndexEnum.IS_TEST_BG_READY_P), 0, 0, AppConfig.BASE_FOLDER + "default_ui_is/4-背景.png"}, new Object[]{Integer.valueOf(UiPosIndexEnum.IS_TEST_BG_PRINT), 0, 0, AppConfig.BASE_FOLDER + "default_ui_is/5-背景.png"}, new Object[]{Integer.valueOf(UiPosIndexEnum.IS_TEST_BTN_PHOTO), 0, 0, AppConfig.BASE_FOLDER + "default_ui_is/1-开始拍照按钮.png"}, new Object[]{Integer.valueOf(UiPosIndexEnum.IS_TEST_BTN_OK), 0, 0, AppConfig.BASE_FOLDER + "default_ui_is/3-确认按钮.png"}, new Object[]{Integer.valueOf(UiPosIndexEnum.IS_TEST_BTN_PRINT), 0, 0, AppConfig.BASE_FOLDER + "default_ui_is/4-打印按钮.png"}, new Object[]{Integer.valueOf(UiPosIndexEnum.IS_TEST_BTN_BACK), 0, 0, AppConfig.BASE_FOLDER + "default_ui_is/5-返回首页按钮.png"}, new Object[]{Integer.valueOf(UiPosIndexEnum.IS_TEST_FRAME_POS), 0, 300, null}, new Object[]{Integer.valueOf(UiPosIndexEnum.IS_TEST_PREVIEW_POS), 280, 200, null}, new Object[]{Integer.valueOf(UiPosIndexEnum.IS_TEST_READY_POS), 370, numValueOf3, null}};
        for (int i = 0; i < 159; i++) {
            Object[] objArr2 = objArr[i];
            int iIntValue = ((Integer) objArr2[0]).intValue();
            int iIntValue2 = ((Integer) objArr2[1]).intValue();
            int iIntValue3 = ((Integer) objArr2[2]).intValue();
            Object obj = objArr2[3];
            arrayList.add(new UiPositionEntity(null, iIntValue, iIntValue2, iIntValue3, null, (String) obj, (String) obj, true));
        }
        return arrayList;
    }

    public static String[][] getDefaultEffectList(Context context) {
        return new String[][]{new String[]{context.getString(C1910R.string.effect_cat_ear), AppConfig.BASE_FOLDER + "effect/normal/catEar"}, new String[]{context.getString(C1910R.string.effect_cute_dragon), AppConfig.BASE_FOLDER + "effect/normal/cuteDragon"}, new String[]{context.getString(C1910R.string.effect_deer), AppConfig.BASE_FOLDER + "effect/normal/deer"}, new String[]{context.getString(C1910R.string.effect_lion_dance), AppConfig.BASE_FOLDER + "effect/normal/lionDance"}, new String[]{context.getString(C1910R.string.effect_pig_ear), AppConfig.BASE_FOLDER + "effect/normal/pigEar"}, new String[]{context.getString(C1910R.string.effect_rabbit), AppConfig.BASE_FOLDER + "effect/normal/rabbit"}, new String[]{context.getString(C1910R.string.effect_santas), AppConfig.BASE_FOLDER + "effect/normal/santas"}};
    }

    public static String[][] getDefaultMaskList(Context context) {
        return new String[][]{new String[]{context.getString(C1910R.string.mask_bluebird), AppConfig.BASE_FOLDER + "effect/ar/maskBluebird"}, new String[]{context.getString(C1910R.string.mask_blueButterfly), AppConfig.BASE_FOLDER + "effect/ar/maskBlueButterfly"}, new String[]{context.getString(C1910R.string.mask_pinkButterfly), AppConfig.BASE_FOLDER + "effect/ar/maskPinkButterfly"}, new String[]{context.getString(C1910R.string.mask_tiger_w), AppConfig.BASE_FOLDER + "effect/ar/maskTigerW"}, new String[]{context.getString(C1910R.string.mask_tiger_y), AppConfig.BASE_FOLDER + "effect/ar/maskTigerY"}};
    }

    public static String[][] getDefaultHahaMirrorList(Context context) {
        return new String[][]{new String[]{context.getString(C1910R.string.face_warp_2), AppConfig.BASE_FOLDER + "effect/facewarp/facewarp2"}, new String[]{context.getString(C1910R.string.face_warp_3), AppConfig.BASE_FOLDER + "effect/facewarp/facewarp3"}, new String[]{context.getString(C1910R.string.face_warp_4), AppConfig.BASE_FOLDER + "effect/facewarp/facewarp4"}, new String[]{context.getString(C1910R.string.face_warp_5), AppConfig.BASE_FOLDER + "effect/facewarp/facewarp5"}, new String[]{context.getString(C1910R.string.face_warp_6), AppConfig.BASE_FOLDER + "effect/facewarp/facewarp6"}};
    }

    public static int[][] getDefaultBeautyParams() {
        return new int[][]{new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 50, 50, 50, 50, 50, 0, 50, 50, 50, 0, 50, 50, 50, 0}, new int[]{1, 300, 40, 10, 0, 20, 40, 40, 20, 20, 20, 30, 0, 0, 0, 0, 0, 0, 0, 0, 30, 20, 5, 50, 50, 50, 50, 50, 0, 50, 50, 50, 0, 50, 50, 50, 0}, new int[]{2, 500, 60, 10, 0, 40, 70, 70, 40, 40, 40, 60, 0, 0, 0, 0, 0, 0, 0, 0, 60, 30, 8, 50, 50, 50, 50, 50, 0, 50, 50, 50, 0, 50, 50, 50, 0}, new int[]{3, 600, 80, 10, 0, 60, 100, 100, 60, 60, 60, 100, 0, 0, 0, 0, 0, 0, 0, 0, 80, 50, 15, 50, 50, 50, 50, 50, 0, 50, 50, 50, 0, 50, 50, 50, 0}};
    }

    public static List<FilterEntity> getDefaultFilterList() {
        ArrayList arrayList = new ArrayList();
        App app = App.getInstance();
        arrayList.add(new FilterEntity(null, 0, app.getString(C1910R.string.none), "origin", null, 0, 1, true));
        arrayList.add(new FilterEntity(null, 1, app.getString(C1910R.string.filter_ziran), FaceBeautyFilterEnum.ZIRAN_1, null, 80, 1, true));
        arrayList.add(new FilterEntity(null, 2, app.getString(C1910R.string.filter_fennen), FaceBeautyFilterEnum.FENNEN_6, null, 80, 1, true));
        arrayList.add(new FilterEntity(null, 3, app.getString(C1910R.string.filter_lensediao), FaceBeautyFilterEnum.LENGSEDIAO_4, null, 80, 1, true));
        arrayList.add(new FilterEntity(null, 4, app.getString(C1910R.string.filter_mitao), FaceBeautyFilterEnum.MITAO_2, null, 80, 1, true));
        arrayList.add(new FilterEntity(null, 5, app.getString(C1910R.string.filter_heibai2), FaceBeautyFilterEnum.HEIBAI_2, null, 100, 1, true));
        arrayList.add(new FilterEntity(null, 6, app.getString(C1910R.string.filter_gexing3), FaceBeautyFilterEnum.GEXING_3, null, 100, 1, true));
        arrayList.add(new FilterEntity(null, 7, app.getString(C1910R.string.filter_gexing11), FaceBeautyFilterEnum.GEXING_11, null, 80, 1, true));
        arrayList.add(new FilterEntity(null, 8, app.getString(C1910R.string.filter_xiaoqingxin), FaceBeautyFilterEnum.XIAOQINGXIN_1, null, 80, 1, true));
        arrayList.add(new FilterEntity(null, 9, app.getString(C1910R.string.filter_nuansediao), FaceBeautyFilterEnum.NUANSEDIAO_1, null, 80, 1, true));
        arrayList.add(new FilterEntity(null, 10, app.getString(C1910R.string.filter_bailiang), FaceBeautyFilterEnum.BAILIANG_1, null, 80, 1, true));
        arrayList.add(new FilterEntity(null, 11, app.getString(C1910R.string.filter_gexing), FaceBeautyFilterEnum.GEXING_1, null, 80, 1, true));
        arrayList.add(new FilterEntity(null, 12, app.getString(C1910R.string.filter_fugu), FaceBeautyFilterEnum.HEIBAI_3, null, 30, 1, true));
        arrayList.add(new FilterEntity(null, 13, app.getString(C1910R.string.filter_heibai4), FaceBeautyFilterEnum.HEIBAI_4, null, 100, 1, true));
        arrayList.add(new FilterEntity(null, 14, app.getString(C1910R.string.filter_zhuihuigang), FaceBeautyFilterEnum.ZHIGANHUI_4, null, 80, 1, true));
        arrayList.add(new FilterEntity(null, 15, app.getString(C1910R.string.filter_heibai), FaceBeautyFilterEnum.HEIBAI_1, null, 100, 1, true));
        arrayList.add(new FilterEntity(null, 16, app.getString(C1910R.string.filter_qianbihua), "6", null, 50, 2, true));
        arrayList.add(new FilterEntity(null, 17, app.getString(C1910R.string.filter_shahua), "4", null, 50, 2, true));
        return arrayList;
    }

    public static List<MakeupEntity> getDefaultMakeupList() {
        ArrayList arrayList = new ArrayList();
        App app = App.getInstance();
        arrayList.add(new MakeupEntity(null, 0, app.getString(C1910R.string.none), null, null, AppConfig.BASE_FOLDER + "makeup/无.png", 0, 0, true));
        arrayList.add(new MakeupEntity(null, 1, app.getString(C1910R.string.makeup_dongling), AppConfig.BASE_FOLDER + "makeup/dongling.bundle", null, AppConfig.BASE_FOLDER + "makeup/makeup_demo.png", 40, 0, true));
        arrayList.add(new MakeupEntity(null, 2, app.getString(C1910R.string.makeup_diadiatu), AppConfig.BASE_FOLDER + "makeup/diadiatu.bundle", null, AppConfig.BASE_FOLDER + "makeup/makeup_demo.png", 40, 0, true));
        arrayList.add(new MakeupEntity(null, 3, app.getString(C1910R.string.makeup_gangfeng), AppConfig.BASE_FOLDER + "makeup/gangfeng.bundle", AppConfig.BASE_FOLDER + "makeup/gangfeng.json", AppConfig.BASE_FOLDER + "makeup/makeup_demo.png", 40, 1, true));
        arrayList.add(new MakeupEntity(null, 4, app.getString(C1910R.string.makeup_jianling), AppConfig.BASE_FOLDER + "makeup/jianling.bundle", AppConfig.BASE_FOLDER + "makeup/jianling.json", AppConfig.BASE_FOLDER + "makeup/makeup_demo.png", 40, 1, true));
        arrayList.add(new MakeupEntity(null, 5, app.getString(C1910R.string.makeup_linjia), AppConfig.BASE_FOLDER + "makeup/linjia.bundle", AppConfig.BASE_FOLDER + "makeup/linjia.json", AppConfig.BASE_FOLDER + "makeup/makeup_demo.png", 40, 1, true));
        arrayList.add(new MakeupEntity(null, 6, app.getString(C1910R.string.makeup_tianmei), AppConfig.BASE_FOLDER + "makeup/tianmei.bundle", AppConfig.BASE_FOLDER + "makeup/tianmei.json", AppConfig.BASE_FOLDER + "makeup/makeup_demo.png", 40, 1, true));
        arrayList.add(new MakeupEntity(null, 7, app.getString(C1910R.string.makeup_wumei), AppConfig.BASE_FOLDER + "makeup/wumei.bundle", AppConfig.BASE_FOLDER + "makeup/wumei.json", AppConfig.BASE_FOLDER + "makeup/makeup_demo.png", 40, 1, true));
        return arrayList;
    }

    public static List<FrameThemeInfo> getInteractTestFrameList() {
        ArrayList arrayList = new ArrayList();
        FrameThemeInfo frameThemeInfo = new FrameThemeInfo();
        frameThemeInfo.setBackgroundPath(AppConfig.BASE_FOLDER + "default_ui_is/2-背景.png");
        frameThemeInfo.setThemeKey("is_theme");
        frameThemeInfo.setThemeName("huDon");
        frameThemeInfo.setEnable(true);
        ArrayList arrayList2 = new ArrayList();
        for (FrameEntity frameEntity : DbHelper.getInstance().getFramesByTheme("is_theme")) {
            if (frameEntity.getIsEnable() && !TextUtils.isEmpty(frameEntity.getFrameLocalPath())) {
                ArrayList arrayList3 = new ArrayList();
                Iterator<FramePhotoEntity> it = DbHelper.getInstance().getFramePhotos(frameEntity.getFrameKey()).iterator();
                while (it.hasNext()) {
                    arrayList3.add(new FramePhotoInfo(it.next()));
                }
                arrayList2.add(new FrameInfo(frameEntity, arrayList3));
            }
        }
        if (!arrayList2.isEmpty()) {
            frameThemeInfo.setFrameInfoList(arrayList2);
            arrayList.add(frameThemeInfo);
        }
        return arrayList;
    }

    public static void initInteractTestFrameDb() {
        DbHelper.getInstance().insertFrame(new FrameEntity(null, "interact001", "interact001", null, AppConfig.BASE_FOLDER + "default_ui_is/is_frame1.png", "is_theme", 0, 1, 0, 0, 0, 0, 0, 1, false, false, null, null, null, 1, true));
        DbHelper.getInstance().insertFrame(new FrameEntity(null, "interact002", "interact002", null, AppConfig.BASE_FOLDER + "default_ui_is/is_frame2.png", "is_theme", 0, 1, 0, 0, 0, 0, 0, 1, false, false, null, null, null, 2, true));
        DbHelper.getInstance().insertFrame(new FrameEntity(null, "interact003", "interact003", null, AppConfig.BASE_FOLDER + "default_ui_is/is_frame3.png", "is_theme", 0, 1, 0, 0, 0, 0, 0, 1, false, false, null, null, null, 3, true));
        DbHelper.getInstance().insertFrame(new FrameEntity(null, "interact004", "interact004", null, AppConfig.BASE_FOLDER + "default_ui_is/is_frame4.png", "is_theme", 0, 1, 0, 0, 0, 0, 0, 1, false, false, null, null, null, 4, true));
        DbHelper.getInstance().deleteFramePhoto("interact001");
        DbHelper.getInstance().deleteFramePhoto("interact002");
        DbHelper.getInstance().deleteFramePhoto("interact003");
        DbHelper.getInstance().deleteFramePhoto("interact004");
        DbHelper.getInstance().insertFramePhoto(new FramePhotoEntity(null, "interact001", 2100, 1800, 60, UiPosIndexEnum.PAYMENT_XIE_CHENG_IC, 0, null));
        DbHelper.getInstance().insertFramePhoto(new FramePhotoEntity(null, "interact002", 2100, 1800, 60, UiPosIndexEnum.PAYMENT_XIE_CHENG_IC, 0, null));
        DbHelper.getInstance().insertFramePhoto(new FramePhotoEntity(null, "interact003", 2100, 1800, 60, UiPosIndexEnum.PAYMENT_XIE_CHENG_IC, 0, null));
        DbHelper.getInstance().insertFramePhoto(new FramePhotoEntity(null, "interact004", 2100, 1800, 60, UiPosIndexEnum.PAYMENT_XIE_CHENG_IC, 0, null));
    }
}
