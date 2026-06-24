package com.wugu.doublecamera.enums;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class FragmentEnum {
    public static final int ADMIN_FRAGMENT = 6;
    public static final int AI_PRINT_FRAGMENT = 11;
    public static final int BURST_SELECT_FRAGMENT = 4;
    public static final int CERTIFY_PHOTO_FRAGMENT = 9;
    public static final int CERTIFY_PRINT_FRAGMENT = 10;
    public static final int CHECK_FRAME_FRAGMENT = 7;
    public static final int CHOOSE_AI_FRAMES = 211;
    public static final int CHOOSE_CARD_FRAMES = 210;
    public static final int CHOOSE_CERTIFY_FRAMES = 28;
    public static final int CHOOSE_HEAD_FRAMES = 27;
    public static final int CHOOSE_IP_FRAMES = 212;
    public static final int CHOOSE_POSTER_FRAMES = 31;
    public static final int CHOOSE_REPLACE_BG_FRAMES = 213;
    public static final int CHOOSE_UPLOAD_PRINT_FRAMES = 29;
    public static final int HOME_FRAGMENT = 0;
    public static final int PAYMENT_FRAGMENT = 2;
    public static final int PHOTO_FRAGMENT = 3;
    public static final int POSTER_EASY_FRAGMENT = 14;
    public static final int POSTER_PAYMENT_FRAGMENT = 32;
    public static final int POSTER_PRE_PRINT_FRAGMENT = 33;
    public static final int PRE_PRINT_FRAGMENT = 5;
    public static final int PRINTING_FRAGMENT = 8;
    public static final int PRINT_USB_DRIVER_FRAGMENT = 13;
    public static final int REPLACE_BG_FRAGMENT = 12;

    @Retention(RetentionPolicy.SOURCE)
    public @interface FragmentType {
    }

    public static String getFragmentName(int i) {
        if (i == 0) {
            return "HomeFragment";
        }
        switch (i) {
            case 2:
                return "PaymentFragment";
            case 3:
                return "PhotoFragment";
            case 4:
                return "BurstSelectFragment";
            case 5:
                return "PrePrintFragment";
            case 6:
                return "AdminFragment";
            case 7:
                return "CheckFrameFragment";
            case 8:
                return "PrintingFragment";
            case 9:
                return "CertifyPhotoFragment";
            case 10:
                return "CertifyPrintFragment";
            case 11:
                return "AiPrintFragment";
            case 12:
                return "ReplaceBgFragment";
            default:
                switch (i) {
                    case 27:
                        return "ChooseHeadFrames";
                    case 28:
                        return "ChooseCertifyFrames";
                    case 29:
                        return "ChoosePrintFrames";
                    default:
                        switch (i) {
                            case 31:
                                return "ChoosePosterFrames";
                            case 32:
                                return "PosterPaymentFragment";
                            case 33:
                                return "PosterPrePrintFragment";
                            default:
                                switch (i) {
                                    case 210:
                                        return "ChooseCardFrames";
                                    case 211:
                                        return "ChooseAiFrames";
                                    case 212:
                                        return "ChooseIpFrames";
                                    case 213:
                                        return "ChooseReplaceBgFrames";
                                    default:
                                        return "UnknownFragment";
                                }
                        }
                }
        }
    }
}
