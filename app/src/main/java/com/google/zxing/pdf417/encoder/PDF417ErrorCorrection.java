package com.google.zxing.pdf417.encoder;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.core.app.FrameMetricsAggregator;
import androidx.core.view.InputDeviceCompat;
import androidx.recyclerview.widget.ItemTouchHelper;
import cc.uling.usdk.constants.BoardConst;
import cc.uling.usdk.constants.ErrorConst;
import com.epson.isv.eprinterdriver.Common.ErrorCode;
import com.epson.isv.eprinterdriver.Ctrl.PageAttribute;
import com.google.android.exoplayer2.extractor.p018ts.PsExtractor;
import com.google.android.exoplayer2.extractor.p018ts.TsExtractor;
import com.google.android.material.internal.ViewUtils;
import com.google.zxing.WriterException;
import com.google.zxing.pdf417.PDF417Common;
import com.p020hp.jipp.model.PowerState;
import com.tom_roush.fontbox.ttf.OS2WindowsMetricsTable;
import com.tom_roush.fontbox.ttf.WGL4Names;
import com.wugu.doublecamera.enums.UiPosIndexEnum;
import jp.p036co.dnp.photoprintlib.DNPPhotoPrint;
import okhttp3.internal.p040ws.WebSocketProtocol;
import org.apache.http.HttpStatus;
import org.apache.log4j.net.SyslogAppender;

final class PDF417ErrorCorrection {
    private static final int[][] EC_COEFFICIENTS = {new int[]{27, 917}, new int[]{522, UiPosIndexEnum.PHOTO_CONFIRM_BG, 723, 809}, new int[]{237, 308, UiPosIndexEnum.KEYBOARD_6, 284, 646, 653, 428, 379}, new int[]{274, UiPosIndexEnum.PHOTO_TV_RETAKE_COUNT, 232, 755, 599, 524, 801, 132, 295, 116, UiPosIndexEnum.PAYMENT_KEYBOARD_POS, 428, 295, 42, 176, 65}, new int[]{361, 575, 922, 525, 176, 586, 640, 321, 536, 742, UiPosIndexEnum.PRINT_BTN_PRINT, 742, UiPosIndexEnum.PRINT_BTN_SIGN, 284, 193, 517, 273, 494, 263, 147, 593, OS2WindowsMetricsTable.WEIGHT_CLASS_EXTRA_BOLD, 571, UiPosIndexEnum.PAYMENT_ZHIFUBAO_IC, 803, 133, 231, 390, UiPosIndexEnum.PRINT_BTN_BACK, UiPosIndexEnum.PAYMENT_ARROW_IC, 63, HttpStatus.SC_GONE}, new int[]{539, 422, 6, 93, 862, 771, 453, 106, TypedValues.MotionType.TYPE_QUANTIZE_MOTIONSTEPS, 287, ErrorCode.PrinterError.EPS_PRNERR_JPG_LIMIT, 505, 733, 877, 381, TypedValues.MotionType.TYPE_QUANTIZE_INTERPOLATOR_ID, 723, 476, 462, TsExtractor.TS_STREAM_TYPE_AC4, UiPosIndexEnum.KEYBOARD_0, TypedValues.MotionType.TYPE_POLAR_RELATIVETO, 858, 822, 543, 376, FrameMetricsAggregator.EVERY_DURATION, 400, UiPosIndexEnum.PRINT_BG_SIGN, 762, 283, SyslogAppender.LOG_LOCAL7, UiPosIndexEnum.KEYBOARD_DEL, 35, 519, 31, 460, 594, 225, 535, 517, 352, TypedValues.MotionType.TYPE_ANIMATE_RELATIVE_TO, 158, 651, 201, 488, 502, 648, 733, 717, 83, HttpStatus.SC_NOT_FOUND, 97, 280, 771, 840, 629, 4, 381, 843, 623, 264, 543}, new int[]{521, 310, 864, UiPosIndexEnum.PHOTO_BEAUTY_NONE, 858, 580, 296, 379, 53, 779, 897, 444, 400, 925, 749, HttpStatus.SC_UNSUPPORTED_MEDIA_TYPE, 822, 93, 217, 208, PDF417Common.MAX_CODEWORDS_IN_BARCODE, 244, 583, 620, 246, 148, 447, 631, 292, UiPosIndexEnum.UPLOAD_UP_BG, 490, TypedValues.TransitionType.TYPE_AUTO_TRANSITION, 516, WGL4Names.NUMBER_OF_MAC_GLYPHS, 457, UiPosIndexEnum.UPLOAD_ADD_PRINT_TEXT, 594, 723, UiPosIndexEnum.PRINT_COUNT_ADD, 292, 272, 96, UiPosIndexEnum.PRINT_BTN_ADD_TEXT, UiPosIndexEnum.KEYBOARD_2, UiPosIndexEnum.PRINT_COUNTDOWN, TypedValues.MotionType.TYPE_ANIMATE_CIRCLEANGLE_TO, 860, 569, 193, 219, 129, 186, 236, 287, 192, 775, 278, 173, 40, 379, 712, 463, 646, 776, 171, 491, 297, 763, 156, 732, 95, 270, 447, 90, 507, 48, 228, 821, 808, 898, 784, 663, 627, 378, 382, 262, 380, TypedValues.MotionType.TYPE_QUANTIZE_MOTION_PHASE, 754, UiPosIndexEnum.PAYMENT_XIE_CHENG_IC, 89, 614, 87, UiPosIndexEnum.KEYBOARD_2, UiPosIndexEnum.PRINT_STICKER_BG, 616, 157, 374, 242, 726, 600, 269, 375, 898, 845, 454, 354, 130, 814, 587, 804, 34, 211, UiPosIndexEnum.PAYMENT_ARROW_IC, 539, 297, 827, 865, 37, 517, 834, 315, UiPosIndexEnum.PHOTO_BEAUTY_THREE, 86, 801, 4, 108, 539}, new int[]{524, 894, 75, 766, 882, 857, 74, 204, 82, 586, 708, ItemTouchHelper.Callback.DEFAULT_SWIPE_ANIMATION_DURATION, 905, 786, TsExtractor.TS_STREAM_TYPE_DTS, 720, 858, 194, 311, 913, 275, PowerState.Code.noChange, 375, 850, UiPosIndexEnum.KEYBOARD_8, 733, 194, 280, 201, 280, 828, 757, 710, 814, 919, 89, 68, 569, 11, 204, 796, TypedValues.MotionType.TYPE_ANIMATE_RELATIVE_TO, 540, 913, 801, 700, 799, 137, UiPosIndexEnum.KEYBOARD_9, 418, 592, 668, 353, 859, 370, UiPosIndexEnum.PRINT_SIGN_UNDO, UiPosIndexEnum.PAYMENT_CARD_IC, PsExtractor.VIDEO_STREAM_MASK, 216, 257, 284, UiPosIndexEnum.PHOTO_BEAUTY_TWO, BoardConst.CODE_ERR_ADDR, 884, 315, 70, UiPosIndexEnum.PAYMENT_CODE_INPUT, 793, 490, 274, 877, 162, 749, 812, UiPosIndexEnum.PRINT_BTN_ADD_TEXT, 461, UiPosIndexEnum.PAYMENT_MEI_TUAN_BG, 376, 849, 521, 307, 291, 803, 712, 19, 358, 399, UiPosIndexEnum.UPLOAD_UP_BG, 103, FrameMetricsAggregator.EVERY_DURATION, 51, 8, 517, 225, 289, 470, 637, 731, 66, 255, 917, 269, 463, 830, 730, UiPosIndexEnum.KEYBOARD_3, 848, 585, SyslogAppender.LOG_LOCAL1, 538, 906, 90, 2, 290, 743, 199, 655, 903, UiPosIndexEnum.PAYMENT_CODE_INPUT, 49, 802, 580, 355, 588, 188, 462, 10, TsExtractor.TS_STREAM_TYPE_SPLICE_INFO, 628, UiPosIndexEnum.PAYMENT_ZHIFUBAO_IC, 479, 130, 739, 71, 263, 318, 374, 601, 192, TypedValues.MotionType.TYPE_ANIMATE_RELATIVE_TO, PageAttribute.MediaTypeID.EPS_MTID_PLOOFING_WHITE_MAT, UiPosIndexEnum.PRINT_COUNT_TEXT, UiPosIndexEnum.PRINT_BTN_SIGN, 234, 722, 384, 177, 752, TypedValues.MotionType.TYPE_PATHMOTION_ARC, 640, 455, 193, UiPosIndexEnum.PRINT_PRICE_POS, TypedValues.TransitionType.TYPE_TRANSITION_FLAGS, 805, 641, 48, 60, 732, 621, 895, 544, 261, 852, 655, 309, UiPosIndexEnum.PRINT_SIGN_CUSTOM, 755, 756, 60, 231, 773, UiPosIndexEnum.KEYBOARD_4, 421, 726, 528, 503, 118, 49, 795, 32, SyslogAppender.LOG_LOCAL2, 500, 238, 836, 394, 280, UiPosIndexEnum.PHOTO_FILTER_LIST, UiPosIndexEnum.PAYMENT_WECHAT_IC, 9, 647, UiPosIndexEnum.PHOTO_BEAUTY_THREE, 73, 914, 342, WebSocketProtocol.PAYLOAD_SHORT, 32, UiPosIndexEnum.PRINT_QRCODE1, UiPosIndexEnum.PHOTO_TRANSITION, 792, 620, 60, TypedValues.MotionType.TYPE_POLAR_RELATIVETO, UiPosIndexEnum.KEYBOARD_OK, 180, 791, 893, 754, TypedValues.MotionType.TYPE_ANIMATE_RELATIVE_TO, 383, 228, 749, 760, 213, 54, 297, TsExtractor.TS_STREAM_TYPE_SPLICE_INFO, 54, 834, 299, 922, 191, 910, 532, TypedValues.MotionType.TYPE_POLAR_RELATIVETO, 829, PsExtractor.PRIVATE_STREAM_1, 20, 167, 29, 872, 449, 83, 402, 41, 656, 505, 579, 481, 173, HttpStatus.SC_NOT_FOUND, 251, 688, 95, 497, UiPosIndexEnum.PHOTO_BTN_CONFIRM, 642, 543, 307, 159, 924, UiPosIndexEnum.PHOTO_BTN_MAKEUP, 648, 55, 497, 10}, new int[]{352, 77, 373, 504, 35, 599, 428, 207, HttpStatus.SC_CONFLICT, 574, 118, 498, 285, 380, 350, 492, 197, 265, 920, 155, 914, 299, 229, 643, 294, 871, 306, 88, 87, 193, 352, 781, 846, 75, UiPosIndexEnum.PAYMENT_BALANCE, 520, UiPosIndexEnum.KEYBOARD_5, 543, 203, 666, 249, 346, 781, 621, 640, 268, 794, 534, 539, 781, HttpStatus.SC_REQUEST_TIMEOUT, 390, 644, 102, 476, 499, 290, 632, 545, 37, 858, 916, UiPosIndexEnum.PHOTO_PICTURE_LIST, 41, UiPosIndexEnum.PHOTO_COUNTDOWN_BG, 289, DNPPhotoPrint.OVERCOAT_FINISH_PMATTE13, 272, 383, OS2WindowsMetricsTable.WEIGHT_CLASS_EXTRA_BOLD, 485, 98, 752, 472, 761, ErrorCode.PrinterError.EPS_PRNERR_JPG_LIMIT, 784, 860, 658, 741, 290, 204, UiPosIndexEnum.PRINT_QRCODE1, HttpStatus.SC_PROXY_AUTHENTICATION_REQUIRED, 855, 85, 99, 62, 482, 180, 20, 297, 451, 593, 913, PageAttribute.MediaTypeID.EPS_MTID_PLOOFING_WHITE_MAT, 808, UiPosIndexEnum.PRINT_BTN_ADD_TEXT, 287, 536, UiPosIndexEnum.PHOTO_COUNTDOWN, 76, 653, 899, 729, UiPosIndexEnum.PHOTO_PREVIEW_BG, 744, 390, InputDeviceCompat.SOURCE_DPAD, 192, 516, WGL4Names.NUMBER_OF_MAC_GLYPHS, PsExtractor.VIDEO_STREAM_MASK, 518, 794, 395, ViewUtils.EDGE_TO_EDGE_FLAGS, 848, 51, TypedValues.MotionType.TYPE_QUANTIZE_MOTIONSTEPS, 384, SyslogAppender.LOG_LOCAL5, PowerState.Code.noChange, 826, UiPosIndexEnum.PAYMENT_CODE_EX_BG, 596, 786, 303, 570, 381, HttpStatus.SC_UNSUPPORTED_MEDIA_TYPE, 641, 156, 237, 151, 429, 531, 207, UiPosIndexEnum.PRINT_COUNT_NUM, 710, 89, SyslogAppender.LOG_LOCAL5, 304, 402, 40, 708, 575, 162, 864, 229, 65, 861, 841, 512, 164, 477, 221, 92, 358, 785, 288, 357, 850, 836, 827, 736, TypedValues.TransitionType.TYPE_TRANSITION_FLAGS, 94, 8, 494, 114, 521, 2, 499, 851, 543, SyslogAppender.LOG_LOCAL3, 729, 771, 95, 248, 361, 578, UiPosIndexEnum.PAYMENT_CASH_IC, 856, 797, 289, 51, UiPosIndexEnum.PRINT_BTN_ADD_TEXT, 466, 533, 820, UiPosIndexEnum.PRINT_STICKER_SIGN_POS, 45, 902, 452, 167, 342, 244, 173, 35, 463, 651, 51, 699, 591, 452, 578, 37, 124, 298, UiPosIndexEnum.PAYMENT_MEI_TUAN_IC, UiPosIndexEnum.PHOTO_PICTURE_LIST, 43, 427, 119, 662, 777, 475, 850, 764, 364, 578, 911, 283, 711, 472, 420, 245, 288, 594, 394, FrameMetricsAggregator.EVERY_DURATION, UiPosIndexEnum.PAYMENT_BALANCE, 589, 777, 699, 688, 43, HttpStatus.SC_REQUEST_TIMEOUT, 842, 383, 721, 521, UiPosIndexEnum.PHOTO_BEAUTY_POS, 644, 714, UiPosIndexEnum.PHOTO_BTN_EFFECT, 62, 145, 873, 663, 713, 159, UiPosIndexEnum.PRINT_BG_SIGN, 729, 624, 59, 193, HttpStatus.SC_EXPECTATION_FAILED, 158, BoardConst.CODE_ERR_ADDR, UiPosIndexEnum.PHOTO_BTN_PHOTO, 564, 343, 693, 109, TypedValues.MotionType.TYPE_DRAW_PATH, UiPosIndexEnum.PHOTO_BTN_PHOTO, 365, 181, 772, UiPosIndexEnum.PRINT_BTN_PRINT, 310, 248, 353, 708, HttpStatus.SC_GONE, 579, 870, 617, 841, 632, 860, 289, 536, 35, 777, 618, 586, 424, 833, 77, 597, 346, 269, 757, 632, UiPosIndexEnum.PRINT_SIGN_REDO, 751, UiPosIndexEnum.PHOTO_TRANSITION, 247, SyslogAppender.LOG_LOCAL7, 45, 787, 680, 18, 66, HttpStatus.SC_PROXY_AUTHENTICATION_REQUIRED, 369, 54, 492, 228, 613, 830, 922, UiPosIndexEnum.KEYBOARD_7, 519, 644, 905, 789, 420, 305, UiPosIndexEnum.KEYBOARD_OK, 207, 300, 892, 827, 141, 537, 381, 662, InputDeviceCompat.SOURCE_DPAD, 56, ErrorConst.MDB_ERR_CANT_OPEN, 341, 242, 797, 838, 837, 720, 224, 307, 631, 61, 87, UiPosIndexEnum.PHOTO_BEAUTY_POS, 310, 756, 665, 397, 808, 851, 309, 473, 795, 378, 31, 647, 915, 459, 806, 590, 731, TypedValues.CycleType.TYPE_WAVE_PHASE, 216, UiPosIndexEnum.PHOTO_BEAUTY_ONE, 249, 321, 881, 699, 535, UiPosIndexEnum.PRINT_COUNT_TEXT, 782, 210, 815, 905, 303, 843, 922, 281, 73, 469, 791, 660, 162, 498, 308, 155, 422, UiPosIndexEnum.UPLOAD_ADD_PRINT_TEXT, 817, 187, 62, 16, TypedValues.CycleType.TYPE_WAVE_PHASE, 535, UiPosIndexEnum.PAYMENT_XIE_CHENG_IC, 286, UiPosIndexEnum.KEYBOARD_7, 375, 273, TypedValues.MotionType.TYPE_QUANTIZE_MOTIONSTEPS, 296, PageAttribute.MediaTypeID.EPS_MTID_LABEL, 923, 116, UiPosIndexEnum.PRINT_SIGN_BG, 751, 353, 62, 366, 691, 379, UiPosIndexEnum.PRINT_BTN_SIGN, 842, 37, 357, 720, 742, UiPosIndexEnum.PAYMENT_ARROW_IC, 5, 39, 923, 311, 424, 242, 749, 321, 54, UiPosIndexEnum.PRINT_STICKER_SIGN_POS, 316, 342, 299, 534, ErrorCode.PrinterError.EPS_PRNERR_TRAYCLOSE, UiPosIndexEnum.PRINT_SIGN_BG, 488, 640, UiPosIndexEnum.PRINT_BG_SIGN, 576, 540, 316, 486, 721, TypedValues.MotionType.TYPE_QUANTIZE_MOTIONSTEPS, 46, 656, 447, 171, 616, 464, PowerState.Code.noChange, 531, 297, 321, 762, 752, 533, 175, TsExtractor.TS_STREAM_TYPE_SPLICE_INFO, 14, 381, UiPosIndexEnum.KEYBOARD_3, 717, 45, 111, 20, 596, 284, 736, TsExtractor.TS_STREAM_TYPE_DTS, 646, HttpStatus.SC_LENGTH_REQUIRED, 877, UiPosIndexEnum.PRINT_STICKER_SIGN_POS, 141, 919, 45, 780, HttpStatus.SC_PROXY_AUTHENTICATION_REQUIRED, 164, UiPosIndexEnum.PAYMENT_MEI_TUAN_IC, 899, 165, 726, 600, UiPosIndexEnum.PAYMENT_CARD_IC, 498, 655, 357, 752, ViewUtils.EDGE_TO_EDGE_FLAGS, 223, 849, 647, 63, 310, 863, 251, 366, 304, 282, 738, UiPosIndexEnum.PRINT_COUNT_SUB, HttpStatus.SC_GONE, 389, 244, 31, DNPPhotoPrint.OVERCOAT_FINISH_PMATTE12, 303, 263}};

    private PDF417ErrorCorrection() {
    }

    static int getErrorCorrectionCodewordCount(int i) {
        if (i < 0 || i > 8) {
            throw new IllegalArgumentException("Error correction level must be between 0 and 8!");
        }
        return 1 << (i + 1);
    }

    static int getRecommendedMinimumErrorCorrectionLevel(int i) throws WriterException {
        if (i <= 0) {
            throw new IllegalArgumentException("n must be > 0");
        }
        if (i <= 40) {
            return 2;
        }
        if (i <= 160) {
            return 3;
        }
        if (i <= 320) {
            return 4;
        }
        if (i <= 863) {
            return 5;
        }
        throw new WriterException("No recommendation possible");
    }

    static String generateErrorCorrection(CharSequence charSequence, int i) {
        int errorCorrectionCodewordCount = getErrorCorrectionCodewordCount(i);
        char[] cArr = new char[errorCorrectionCodewordCount];
        int length = charSequence.length();
        for (int i2 = 0; i2 < length; i2++) {
            int i3 = errorCorrectionCodewordCount - 1;
            int iCharAt = (charSequence.charAt(i2) + cArr[i3]) % PDF417Common.NUMBER_OF_CODEWORDS;
            while (i3 > 0) {
                cArr[i3] = (char) ((cArr[i3 - 1] + (929 - ((EC_COEFFICIENTS[i][i3] * iCharAt) % PDF417Common.NUMBER_OF_CODEWORDS))) % PDF417Common.NUMBER_OF_CODEWORDS);
                i3--;
            }
            cArr[0] = (char) ((929 - ((iCharAt * EC_COEFFICIENTS[i][0]) % PDF417Common.NUMBER_OF_CODEWORDS)) % PDF417Common.NUMBER_OF_CODEWORDS);
        }
        StringBuilder sb = new StringBuilder(errorCorrectionCodewordCount);
        for (int i4 = errorCorrectionCodewordCount - 1; i4 >= 0; i4--) {
            char c = cArr[i4];
            if (c != 0) {
                cArr[i4] = (char) (929 - c);
            }
            sb.append(cArr[i4]);
        }
        return sb.toString();
    }
}
