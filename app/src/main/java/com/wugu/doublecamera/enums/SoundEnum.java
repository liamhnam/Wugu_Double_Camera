package com.wugu.doublecamera.enums;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class SoundEnum {
    public static final int CLICK_BUTTON = 3;
    public static final int CLICK_FILTER = 6;
    public static final int CLICK_FRAME_SOUND = 2;
    public static final int CLICK_FRAME_THEME = 1;
    public static final int PHOTO_CAPTURE = 5;
    public static final int SOUND_CHOOSE_FRAME = 7;
    public static final int SOUND_LOOK_CAMERA = 10;
    public static final int SOUND_PAYMENT = 8;
    public static final int SOUND_PAY_PRINT = 17;
    public static final int SOUND_PE_CHANGE_FRAME = 14;
    public static final int SOUND_PE_RETAKE = 16;
    public static final int SOUND_PE_USE_CODE = 15;
    public static final int SOUND_PHOTO_COUNTDOWN = 4;
    public static final int SOUND_PREVIEW = 13;
    public static final int SOUND_PRE_PRINT = 11;
    public static final int SOUND_PRINT_FINISH = 9;
    public static final int SOUND_START_PRINT = 12;

    @Retention(RetentionPolicy.SOURCE)
    public @interface SoundType {
    }

    public static String getSoundTypeName(int i) {
        switch (i) {
            case 1:
                return "click_frame_theme_sound";
            case 2:
                return "click_frame_sound";
            case 3:
                return "click_button_sound";
            case 4:
                return "photo_countdown_sound";
            case 5:
                return "photo_sound";
            case 6:
                return "click_filter_sound";
            case 7:
                return "sound_choose_frame";
            case 8:
                return "sound_payment";
            case 9:
                return "sound_print_finish";
            case 10:
                return "sound_look_camera";
            case 11:
                return "sound_pre_print";
            case 12:
                return "sound_start_print";
            case 13:
                return "sound_preview_photo";
            case 14:
                return "sound_pe_change_frame";
            case 15:
                return "sound_pe_use_code";
            case 16:
                return "sound_pe_retake";
            case 17:
                return "sound_pay_print";
            default:
                return null;
        }
    }
}
